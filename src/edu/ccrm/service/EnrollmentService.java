package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment add(Enrollment e);
    List<Enrollment> list();
}
