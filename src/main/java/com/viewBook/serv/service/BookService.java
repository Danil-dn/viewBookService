package com.viewBook.serv.service;

import com.viewBook.serv.Util;
import com.viewBook.serv.dto.BookDto;
import com.viewBook.serv.entity.Book;
import com.viewBook.serv.exception.InvalidArgumentException;
import com.viewBook.serv.repo.AuthorRepository;
import com.viewBook.serv.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements Util {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksByAuthor(String authorName) {

        List<Book> books = bookRepository.findAllByAuthor_FullName(authorName);
        if (books != null) {
            return books.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        return null;
    }


    public BookDto getBookById(long bookId) {
        return convertToDto(bookRepository.findById(bookId).orElseThrow());
    }

    public List<BookDto> getAllBookSortedBy(String title) {
        if (!title.contains("title")) {
            throw new InvalidArgumentException("Неверное поле для сортировки: " + title);
        }
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, title)).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
