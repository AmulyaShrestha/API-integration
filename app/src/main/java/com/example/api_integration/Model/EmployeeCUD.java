package com.example.api_integration.Model;

public class EmployeeCUD {
    private int id;
    private String name;
    private float salary;

    public int getId() {
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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public EmployeeCUD( String name, float salary, int age, String profile_image) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profile_image = profile_image;
    }

    private int age;
    private String profile_image;
}
