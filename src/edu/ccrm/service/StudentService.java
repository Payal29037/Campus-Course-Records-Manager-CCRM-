package edu.ccrm.service;

import edu.ccrm.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student add(Student s);
    List<Student> list();
    Optional<Student> findByRegNo(String regNo);
}
