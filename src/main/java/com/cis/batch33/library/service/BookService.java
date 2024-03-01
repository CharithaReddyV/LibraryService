package com.cis.batch33.library.service;

import com.cis.batch33.library.entity.Book;
import com.cis.batch33.library.model.BookDTO;
import com.cis.batch33.library.model.BookISBNDTO;
import com.cis.batch33.library.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Integer bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            return convertToDTO(optionalBook.get());
        }
        return null; // Or throw an exception indicating book not found
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDTO updateBook(Integer bookId, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book bookToUpdate = optionalBook.get();
            // Update book fields
            bookToUpdate.setTitle(bookDTO.getTitle());
            bookToUpdate.setAuthorName(bookDTO.getAuthorName());
            bookToUpdate.setYearPubished(bookDTO.getYearPubished());
            bookToUpdate.setQuantity(bookDTO.getQuantity());
            // Save the updated book
            Book updatedBook = bookRepository.save(bookToUpdate);
            return convertToDTO(updatedBook);
        }
        return null; // Or throw an exception indicating book not found
    }

    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        List<BookISBNDTO> isbnDTOs = new ArrayList<>();
        if (book.getBookIsbns() != null) {
            isbnDTOs = book.getBookIsbns().stream()
                    .map(isbn -> modelMapper.map(isbn, BookISBNDTO.class))
                    .collect(Collectors.toList());
        }
        bookDTO.setIsbns(isbnDTOs);
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
