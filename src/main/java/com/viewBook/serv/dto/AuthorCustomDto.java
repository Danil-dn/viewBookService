package com.viewBook.serv.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AuthorCustomDto extends AuthorDto {

    private List<String> bookList = new ArrayList<>();
}
