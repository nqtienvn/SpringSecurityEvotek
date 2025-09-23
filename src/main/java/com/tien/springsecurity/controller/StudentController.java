package com.tien.springsecurity.controller;

import com.tien.springsecurity.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentController {
    List<Student> listOfStudent = new ArrayList<>(List.of(
            new Student(1, "tien", 10),
            new Student(2, "hung", 11)
    ));
    @GetMapping("/students")
    public List<Student> getStudent() {
        return listOfStudent;
    }
    @PostMapping("/students")
    public Student createStudetn(@RequestBody Student student) {
        listOfStudent.add(student);
        return student;
    }
    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest httpServletRequest ) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }
}
