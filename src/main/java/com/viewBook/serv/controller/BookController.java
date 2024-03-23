package com.viewBook.serv.controller;

import com.viewBook.serv.dto.BookDto;
import com.viewBook.serv.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    @Operation(summary = "Получения книг")
    public List<BookDto> getAllBooks(@RequestParam(required = false) String title) {
        List<BookDto> books;
        if (title != null && !title.isEmpty()) {
            books = bookService.getAllBookSortedBy(title);
        } else {
            books = bookService.getAllBooks();
        }
        return books;
    }

    @GetMapping("/api/books/{author}")
    @Operation(summary = "Получения книг по автору")
    public List<BookDto> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/api/book/{bookId}")
    @Operation(summary = "Получения книги по id")
    public BookDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

}
