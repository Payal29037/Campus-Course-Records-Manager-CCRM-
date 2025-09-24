package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Person {
    private String id;
    private String fullName;
    private String email;
    private final LocalDateTime createdAt;

    protected Person(String id, String fullName, String email) {
        assert id != null && !id.isBlank() : "id must not be null/blank"; // assertion example
        this.id = Objects.requireNonNull(id);
        this.fullName = Objects.requireNonNull(fullName);
        this.email = Objects.requireNonNull(email);
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = Objects.requireNonNull(id); }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = Objects.requireNonNull(fullName); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = Objects.requireNonNull(email); }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public abstract String profile();

    @Override public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
