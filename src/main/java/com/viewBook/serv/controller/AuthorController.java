package com.viewBook.serv.controller;

import com.viewBook.serv.dto.AuthorCustomDto;
import com.viewBook.serv.dto.AuthorDto;
import com.viewBook.serv.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/api/authors")
    @Operation(summary = "Получения списка авторов")
    public List<AuthorDto> getAuthors(
            @RequestParam(required = false) String sortBy
    ) {
        List<AuthorDto> authors;
        if (sortBy != null && !sortBy.isEmpty()) {
            authors = authorService.getAllAuthorsSortedBy(sortBy);
        } else {
            authors = authorService.getAllAuthors();
        }
        return authors;
    }

    @GetMapping("/api/author/{id}")
    @Operation(summary = "Получения автора по id")
    public AuthorCustomDto getAuthorById(@PathVariable long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/api/author")
    @Operation(summary = "Получения автора по имени")
    public AuthorCustomDto getAuthorByFullName(@RequestParam String fullName) {
        return authorService.getAuthorByFullName(fullName);
    }

}
