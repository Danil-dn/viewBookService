package com.viewBook.serv.service;

import com.viewBook.serv.Util;
import com.viewBook.serv.dto.AuthorCustomDto;
import com.viewBook.serv.dto.AuthorDto;
import com.viewBook.serv.entity.Author;
import com.viewBook.serv.exception.InvalidArgumentException;
import com.viewBook.serv.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements Util {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AuthorCustomDto getAuthorById(long authorId) {
        Author author = findByAuthorId(authorId, authorRepository);
        if (author != null) {
            return convertCustomToDto(author);
        }
        return null;
    }

    public AuthorCustomDto getAuthorByFullName(String fullName) {
        Author author = authorRepository.findByFullName(fullName);
        if (author != null) {
            return convertCustomToDto(author);
        }
        return null;
    }

    public List<AuthorDto> getAllAuthorsSortedBy(String sortBy) {
        List<Author> sortedAuthors = switch (sortBy) {
            case "fullName" -> authorRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
            case "gender" -> authorRepository.findAll(Sort.by(Sort.Direction.ASC, "gender"));
            default -> throw new InvalidArgumentException("Неверное поле для сортировки: " + sortBy);
        };

        return sortedAuthors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Author findByAuthorId(Long authorId, AuthorRepository authorRepository){
        return authorRepository.findById(authorId).orElseThrow();
    }
}
