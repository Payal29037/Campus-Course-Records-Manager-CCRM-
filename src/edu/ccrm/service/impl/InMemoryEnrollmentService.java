package edu.ccrm.service.impl;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.service.EnrollmentService;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEnrollmentService implements EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public Enrollment add(Enrollment e) {
        // simple duplicate prevention by student+course+semester
        enrollments.removeIf(x -> x.getStudentRegNo().equalsIgnoreCase(e.getStudentRegNo())
                && x.getCourseCode().equalsIgnoreCase(e.getCourseCode())
                && x.getSemester() == e.getSemester());
        enrollments.add(e);
        return e;
    }

    @Override
    public List<Enrollment> list() {
        return new ArrayList<>(enrollments);
    }
}
