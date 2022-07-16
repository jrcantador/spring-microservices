package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {}
