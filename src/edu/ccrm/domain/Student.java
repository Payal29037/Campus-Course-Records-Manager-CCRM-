package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Person {
    public enum Status { ACTIVE, INACTIVE }

    private String regNo;
    private Status status;
    private final List<String> enrolledCourseCodes = new ArrayList<>();
    private final LocalDateTime updatedAt;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = Objects.requireNonNull(regNo);
        this.status = Status.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = Objects.requireNonNull(regNo); }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = Objects.requireNonNull(status); }
    public List<String> getEnrolledCourseCodes() { return enrolledCourseCodes; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    @Override
    public String profile() { return "Student{" + getRegNo() + ", " + getFullName() + "}"; }

    @Override
    public String toString() {
        return super.toString() + ", regNo=" + regNo + ", status=" + status + ", courses=" + enrolledCourseCodes + ']';
    }
}
