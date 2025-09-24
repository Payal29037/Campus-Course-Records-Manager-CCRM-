/*
  * Project: Campus Course & Records Manager (CCRM)
  * File: InMemoryCourseService.java
  * Author: Payal Kushwaha
  * Created: 2025-09-25
  * Version: v0.1.0
  * Notes: Simple in-memory implementation for managing Course entities.
  */
package edu.ccrm.service.impl;

import edu.ccrm.domain.Course;
import edu.ccrm.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryCourseService implements CourseService {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public Course add(Course c) {
        courses.removeIf(existing -> existing.getCode().equalsIgnoreCase(c.getCode()));
        courses.add(c);
        return c;
    }

    @Override
    public List<Course> list() {
        return new ArrayList<>(courses);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return courses.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findFirst();
    }

    @Override
    public List<Course> filter(Predicate<Course> predicate) {
        return courses.stream().filter(predicate).collect(Collectors.toList());
    }
}
