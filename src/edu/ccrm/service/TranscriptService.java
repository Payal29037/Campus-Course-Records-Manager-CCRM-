package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;

import java.util.List;

public interface TranscriptService {
    double computeGPA(List<Enrollment> enrollments);
}
