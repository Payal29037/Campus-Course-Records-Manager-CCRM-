package edu.ccrm.service.impl;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.TranscriptService;

import java.util.List;

public class InMemoryTranscriptService implements TranscriptService {
    private final CourseService courseService;

    public InMemoryTranscriptService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public double computeGPA(List<Enrollment> enrollments) {
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (Enrollment e : enrollments) {
            if (e.getGrade().isEmpty()) continue;
            double gp = e.getGrade().get().getPoints();
            int credits = courseService.findByCode(e.getCourseCode())
                    .map(Course::getCredits)
                    .orElse(0);
            totalPoints += gp * credits;
            totalCredits += credits;
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
}
