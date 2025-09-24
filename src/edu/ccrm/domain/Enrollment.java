package edu.ccrm.domain;

import java.util.Optional;

public class Enrollment {
    private final String studentRegNo;
    private final String courseCode;
    private final Semester semester;
    private final Integer marks; // nullable -> Optional in getter
    private final Grade grade; // can be null until computed

    public Enrollment(String studentRegNo, String courseCode, Semester semester, Integer marks, Grade grade) {
        this.studentRegNo = studentRegNo;
        this.courseCode = courseCode;
        this.semester = semester;
        this.marks = marks;
        this.grade = grade;
    }

    public String getStudentRegNo() { return studentRegNo; }
    public String getCourseCode() { return courseCode; }
    public Semester getSemester() { return semester; }
    public Optional<Integer> getMarks() { return Optional.ofNullable(marks); }
    public Optional<Grade> getGrade() { return Optional.ofNullable(grade); }

    @Override public String toString() {
        return studentRegNo + " -> " + courseCode + " (" + semester + ") marks=" + marks + ", grade=" + grade;
    }
}
