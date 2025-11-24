package com.studentservice.sms.controller;

import com.studentservice.sms.model.Student;
import com.studentservice.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("addOne")
    public ResponseEntity<Student> addOneStudent(@RequestBody Student student) {
        student = smsService.addOneStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> studentList = smsService.findAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("findById")
    public ResponseEntity<Student> findById(@RequestParam Integer id) {
        Student student = smsService.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("deleteById")
    public ResponseEntity<Student> deleteById(@RequestParam Integer id) {
        Student student = smsService.deleteById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        student = smsService.updateStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
