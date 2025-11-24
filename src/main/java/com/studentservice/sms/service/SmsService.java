package com.studentservice.sms.service;

import com.studentservice.sms.model.Student;
import com.studentservice.sms.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsService {

    @Autowired
    SmsRepository smsRepository;

    public Student addOneStudent(Student student) {
        return smsRepository.add(student);
    }

    public List<Student> findAllStudents() {
        return smsRepository.findAll();
    }

    public Student findById(Integer id) {
        return smsRepository.findById(id);
    }

    public Student deleteById(Integer id) {
        return smsRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        return smsRepository.update(student);
    }
}
