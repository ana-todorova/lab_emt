package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.DTO.BookDTO;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDTO bookDTO) {
        Category category;
        try {
            category = Category.valueOf(bookDTO.getCategory().toString());
        } catch (IllegalArgumentException e) {
            throw new CategoryNotFoundException(bookDTO.getCategory().toString());
        }
        Author author = this.authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDTO.getAuthorId()));

        Book book = new Book(bookDTO.getName(), category, author, bookDTO.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDTO bookDTO) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (bookDTO.getName() != null) {
                        existingBook.setName(bookDTO.getName());
                    }
                    if (bookDTO.getCategory() != null) {
                        existingBook.setCategory(bookDTO.getCategory());
                    }
                    if (bookDTO.getAvailableCopies() != null) {
                        existingBook.setAvailableCopies(bookDTO.getAvailableCopies());
                    }
                    if (bookDTO.getAuthorId() != null) {
                        Author author = this.authorRepository.findById(bookDTO.getAuthorId())
                                .orElseThrow(() -> new AuthorNotFoundException(bookDTO.getAuthorId()));
                        existingBook.setAuthor(author);
                    }
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book markAsRented(Long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isPresent() && book.get().getAvailableCopies() > 0) {
            Book rentedBook = book.get();
            rentedBook.setAvailableCopies(rentedBook.getAvailableCopies() - 1);
            return bookRepository.save(rentedBook);
        }
        return null;
    }
}
