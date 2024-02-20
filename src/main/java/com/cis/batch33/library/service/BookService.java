package com.cis.batch33.library.service;



import com.cis.batch33.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class BookService {

    private Map<Long, Book> bookMap = new HashMap<>();

    public Book createBook(Book book){
        Long bookId = generateBookId();
        book.setBookId(bookId);
        bookMap.put(bookId, book);
        return book;
    }

    public Book getBook(Long bookId) {
        return bookMap.get(bookId);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookMap.get(bookId);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPageCount(updatedBook.getPageCount());
            existingBook.setGenre(updatedBook.getGenre());
            return existingBook;
        } else {
            return null; // or throw BookNotFoundException
        }

    }


    public void deleteBook(Long bookId) {
        bookMap.remove(bookId);
    }

    private Long generateBookId() {
        // Simulating generation of unique book IDs
        return new Random().nextLong();
    }
}
