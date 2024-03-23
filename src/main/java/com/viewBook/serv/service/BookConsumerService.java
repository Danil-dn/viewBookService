package com.viewBook.serv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewBook.serv.dto.BookDto;
import com.viewBook.serv.entity.Author;
import com.viewBook.serv.entity.Book;
import com.viewBook.serv.repo.AuthorRepository;
import com.viewBook.serv.repo.BookRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookConsumerService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookConsumerService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @KafkaListener(topics = "topic--book", groupId = "book-group")
    public void listenBookTopic(ConsumerRecord<String, String> record) throws JsonProcessingException {
        BookDto bookDto = convertJsonToBookDto(record.value());
        addBookToDatabase(bookDto);
    }

    private void addBookToDatabase(BookDto bookDto) {
        Book book = bookRepository.findByTitle(bookDto.getTitle());
        if (book == null) {
            book = new Book();
            book.setTitle(bookDto.getTitle());
            Author author = authorRepository.findByFullName(bookDto.getAuthorDto().getFullName());
            if (author == null) {
                author = new Author();
                author.setFullName(bookDto.getAuthorDto().getFullName());
                author.setGender(bookDto.getAuthorDto().getGender());
            }
            book.setAuthor(author);

            authorRepository.save(author);
            bookRepository.save(book);
        }
    }

    private BookDto convertJsonToBookDto(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, BookDto.class);
    }
}
