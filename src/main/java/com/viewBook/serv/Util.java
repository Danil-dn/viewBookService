package com.viewBook.serv;

import com.viewBook.serv.dto.AuthorCustomDto;
import com.viewBook.serv.dto.AuthorDto;
import com.viewBook.serv.dto.BookDto;
import com.viewBook.serv.entity.Author;
import com.viewBook.serv.entity.Book;

public interface Util {

    default BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setTitle(book.getTitle());
        dto.setAuthorDto(convertToDto(book.getAuthor()));
        return dto;
    }

    default AuthorCustomDto convertCustomToDto(Author author) {
        AuthorCustomDto dto = new AuthorCustomDto();
        dto.setFullName(author.getFullName());
        dto.setGender(author.getGender());

        dto.setBookList(author.getBookList().stream().map(Book::getTitle).toList());
        return dto;
    }

    default AuthorDto convertToDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setFullName(author.getFullName());
        dto.setGender(author.getGender());

        return dto;
    }

}
