package com.viewBook.serv.repo;

import com.viewBook.serv.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFullName(String fullName);
}
