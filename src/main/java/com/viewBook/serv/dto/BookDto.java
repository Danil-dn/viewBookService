package com.viewBook.serv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private String title;

    private AuthorDto authorDto;
}
