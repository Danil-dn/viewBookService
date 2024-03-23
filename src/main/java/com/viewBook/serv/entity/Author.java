package com.viewBook.serv.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author extends BaseEntity {

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();
}
