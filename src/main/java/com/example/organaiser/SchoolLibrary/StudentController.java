package com.example.organaiser.SchoolLibrary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") long id){
        Optional<Student> student = studentRepository.findById((int) id);
        if(student.isPresent()){
            return ResponseEntity.ok(student.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity <Void> createStudent(@RequestBody Student student){
        studentRepository.save(student);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable("id") long id, @RequestBody Student newStudent){
        Optional<Student> student = studentRepository.findById((int) id);
        if(student.isPresent()){
            Student preStudent = student.get();
            preStudent.setFullName(newStudent.getFullName());
            preStudent.setGrade(newStudent.getGrade());
            preStudent.setEmail(newStudent.getEmail());
            preStudent.setActive(newStudent.isActive());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") long id){
        studentRepository.deleteById((int) id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentGrade(@PathVariable("grade") int grade){
        return ResponseEntity.ok(studentRepository.findByGrade(grade));
    }
}
