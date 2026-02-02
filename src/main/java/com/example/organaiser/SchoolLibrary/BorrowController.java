package com.example.organaiser.SchoolLibrary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private BorrowRecordRepository borrowRecordRepository;
    private BookRepository bookRepository;
    private StudentRepository studentRepository;

    public BorrowController(BorrowRecordRepository borrowRecordRepository,
                            BookRepository bookRepository,
                            StudentRepository studentRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity <Void> borrowBook(@RequestBody BorrowBody borrowBody){
        BorrowRecord borrowRecord = new BorrowRecord();
        if(bookRepository.findById(borrowBody.getBookId()).isPresent()){
            borrowRecord.setBook(bookRepository.findById(borrowBody.getBookId()).get());
        }
        if(studentRepository.findById(borrowBody.getStudentId()).isPresent()){
            borrowRecord.setStudent(studentRepository.findById(borrowBody.getStudentId()).get());
        }
        borrowRecord.setReturned(false);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setReturnDate(LocalDate.now().plusDays((long) borrowBody.getDays()));
        bookRepository.findById(borrowBody.getBookId()).get().setAvaible(false);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable("id") long id){
        Optional <BorrowRecord> borrowRecord = borrowRecordRepository.findById((int) id);
        if(borrowRecord.isPresent()){
            borrowRecord.get().setReturned(true);
            borrowRecord.get().setReturnDate(LocalDate.now());
            borrowRecord.get().getBook().setAvaible(true);
            return ResponseEntity.accepted().build();
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getBorrowRecords(){
        return ResponseEntity.ok(borrowRecordRepository.findAll());
    }

    @GetMapping("student/{studentId")
    public ResponseEntity<List<BorrowRecord>> getStudentRecords(@PathVariable("id") long id){
        Optional <Student> student = studentRepository.findById((int) id);
        if(student.isPresent()){
            return ResponseEntity.ok(borrowRecordRepository.findByStudent(student.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<BorrowRecord>> getOverdueBorrows(){
        List<BorrowRecord> notRetirnedBorrows = borrowRecordRepository.findByReturned(false);
        List <BorrowRecord> overdueBorrows = new ArrayList<>();
        for(BorrowRecord borrowRecord: notRetirnedBorrows){
            if(borrowRecord.getReturnDate().isBefore(LocalDate.now())){
                overdueBorrows.add(borrowRecord);
            }
        }
        return ResponseEntity.ok(overdueBorrows);
    }
}
