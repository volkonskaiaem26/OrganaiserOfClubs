package com.example.organaiser.SchoolLibrary;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowRecords")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @ManyToOne
    @Column
    private Book book;
    @ManyToOne
    @Column
    private Student student;
    @Column
    private LocalDate borrowDate;
    @Column
    private LocalDate returnDate;
    @Column
    private boolean returned;

    public BorrowRecord() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
