package com.example.organaiser.SchoolLibrary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/books")
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") long id){
        Optional<Book> book = bookRepository.findById((int) id);
        if(book.isPresent()){
            return ResponseEntity.ok(book.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity <Void> createBook(@RequestBody Book book){
        bookRepository.save(book);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable("id") long id, @RequestBody Book newBook){
        Optional<Book> book = bookRepository.findById((int) id);
        if(book.isPresent()){
            Book preBook = book.get();
            preBook.setTitle(newBook.getTitle());
            preBook.setAuthor(newBook.getAuthor());
            preBook.setIsbn(newBook.getIsbn());
            preBook.setPublishYear(newBook.getPublishYear());
            preBook.setAvaible(newBook.isAvaible());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id){
        bookRepository.deleteById((int) id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> getTitleBooks(@RequestParam String title){
        return ResponseEntity.ok(bookRepository.findByTitle(title));
    }

}
