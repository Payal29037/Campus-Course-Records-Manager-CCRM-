package edu.ccrm.service;

import edu.ccrm.domain.Course;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface CourseService {
    Course add(Course c);
    List<Course> list();
    Optional<Course> findByCode(String code);

    // Stream-based filters
    List<Course> filter(Predicate<Course> predicate);
}
