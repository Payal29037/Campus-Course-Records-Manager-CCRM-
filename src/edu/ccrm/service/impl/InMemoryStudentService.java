package edu.ccrm.service.impl;

import edu.ccrm.domain.Student;
import edu.ccrm.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryStudentService implements StudentService {
    private final List<Student> students = new ArrayList<>();

    @Override
    public Student add(Student s) {
        students.add(s);
        return s;
    }

    @Override
    public List<Student> list() { return new ArrayList<>(students); }

    @Override
    public Optional<Student> findByRegNo(String regNo) {
        return students.stream().filter(st -> st.getRegNo().equalsIgnoreCase(regNo)).findFirst();
    }
}
