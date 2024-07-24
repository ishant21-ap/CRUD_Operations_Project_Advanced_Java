package com.studentManagement.model;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String email;
    private Date dob;

    // Constructor used for creating a new student
    public Student(String name, String email, Date dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    // Constructor used for updating an existing student
    public Student(int id, String name, String email, Date dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
}
