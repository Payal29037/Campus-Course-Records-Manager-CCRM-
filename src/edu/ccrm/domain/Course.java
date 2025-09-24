package edu.ccrm.domain;

import java.util.Objects;

public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final String instructorId; // simple reference by id for now
    private final Semester semester;
    private final String department;

    private Course(Builder b) {
        this.code = Objects.requireNonNull(b.code);
        this.title = Objects.requireNonNull(b.title);
        this.credits = b.credits;
        this.instructorId = b.instructorId;
        this.semester = Objects.requireNonNull(b.semester);
        this.department = Objects.requireNonNull(b.department);
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructorId() { return instructorId; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return code + " - " + title + " (" + credits + "cr, " + semester + ", " + department + ")";
    }

    // Static nested Builder class (Builder pattern)
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructorId;
        private Semester semester = Semester.FALL;
        private String department;

        public Builder code(String code) { this.code = code; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder credits(int credits) { this.credits = credits; return this; }
        public Builder instructorId(String instructorId) { this.instructorId = instructorId; return this; }
        public Builder semester(Semester semester) { this.semester = semester; return this; }
        public Builder department(String department) { this.department = department; return this; }
        public Course build() { return new Course(this); }
    }
}
