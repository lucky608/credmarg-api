package com.example.credmarg.models;


import java.util.concurrent.atomic.AtomicLong;

public class Employee {

    private static final AtomicLong idGenerator = new AtomicLong(1);

    private long id;
    private String name;
    private String email;
    private String designation;
    private float ctc;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getCtc() {
        return ctc;
    }

    public void setCtc(float ctc) {
        this.ctc = ctc;
    }


}
