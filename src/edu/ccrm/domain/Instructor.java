package edu.ccrm.domain;

import java.util.Objects;

public class Instructor extends Person {
    private String department;

    public Instructor(String id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = Objects.requireNonNull(department);
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = Objects.requireNonNull(department); }

    @Override
    public String profile() {
        return "Instructor{" + getFullName() + ", dept=" + department + "}";
    }
}
