package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.enumerations.Category;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {

        Country germany = new Country();
        germany.setName("Germany");
        germany.setContinent("Europe");

        Country italy = new Country();
        italy.setName("Italy");
        italy.setContinent("Europe");

        Country japan = new Country();
        japan.setName("Japan");
        japan.setContinent("Asia");

        Country canada = new Country();
        canada.setName("Canada");
        canada.setContinent("North America");

        countryRepository.saveAll(List.of(germany, italy, japan, canada));

        Author author1 = new Author();
        author1.setName("Franz");
        author1.setSurname("Kafka");
        author1.setCountry(germany);

        Author author2 = new Author();
        author2.setName("Umberto");
        author2.setSurname("Eco");
        author2.setCountry(italy);

        Author author3 = new Author();
        author3.setName("Haruki");
        author3.setSurname("Murakami");
        author3.setCountry(japan);

        Author author4 = new Author();
        author4.setName("Margaret");
        author4.setSurname("Atwood");
        author4.setCountry(canada);

        authorRepository.saveAll(List.of(author1, author2, author3, author4));

        Book book1 = new Book();
        book1.setName("The Trial");
        book1.setCategory(Category.NOVEL);
        book1.setAuthor(author1);
        book1.setAvailableCopies(4);

        Book book2 = new Book();
        book2.setName("Foucault's Pendulum");
        book2.setCategory(Category.THRILLER);
        book2.setAuthor(author2);
        book2.setAvailableCopies(7);

        Book book3 = new Book();
        book3.setName("Norwegian Wood");
        book3.setCategory(Category.DRAMA);
        book3.setAuthor(author3);
        book3.setAvailableCopies(5);

        Book book4 = new Book();
        book4.setName("The Handmaid's Tale");
        book4.setCategory(Category.CLASSICS);
        book4.setAuthor(author4);
        book4.setAvailableCopies(6);

        Book book5 = new Book();
        book5.setName("Kafka on the Shore");
        book5.setCategory(Category.FANTASY);
        book5.setAuthor(author3);
        book5.setAvailableCopies(3);

        bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));
    }
}
