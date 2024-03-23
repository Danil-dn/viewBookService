package com.viewBook.serv.repo;

import com.viewBook.serv.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_FullName(String name);

    Book findByTitle(String title);

}
