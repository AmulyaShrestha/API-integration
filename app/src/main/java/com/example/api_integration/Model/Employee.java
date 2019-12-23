package com.example.api_integration.Model;

public class Employee {
    private int id;
    private String employee_name;
    private double employee_salary;
    private int employee_age;
    private String profile_img;

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public Employee(String profile_img) {
        this.profile_img = profile_img;
    }

    public Employee(int id, String employee_name, double employee_salary, int employee_age) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }

    public int getId() {
        return id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public double getEmployee_salary() {
        return employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setEmployee_salary(double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }
}
