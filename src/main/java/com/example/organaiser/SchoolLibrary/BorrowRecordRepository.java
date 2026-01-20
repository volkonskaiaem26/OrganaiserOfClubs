package com.example.organaiser.SchoolLibrary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    List <BorrowRecord> findByStudent(Student student);

    List <BorrowRecord> findByReturned(boolean returned);
}
