package com.cis.batch33.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cis.batch33.library.model.Book;
import com.cis.batch33.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{bookId}")
    public long getBook(@PathVariable Long bookId){
        System.out.println("Attempting to retrieve book with ID: " + bookId);
        Book book = bookService.getBook(bookId);
        if(book != null) {
            System.out.println("Book found: " + book.toString());
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
        return bookId;
    }



    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book){
        Book updatedBook = bookService.updateBook(bookId, book);
        if(updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
    }
}

