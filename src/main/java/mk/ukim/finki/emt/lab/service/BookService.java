package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.DTO.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(BookDTO bookDTO);

    Optional<Book> update(Long id, BookDTO bookDTO);

    void deleteById(Long id);

    Book markAsRented(Long id);
}
