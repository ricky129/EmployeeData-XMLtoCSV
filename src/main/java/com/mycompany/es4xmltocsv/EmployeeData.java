package com.mycompany.es4xmltocsv;

public class EmployeeData {

    int id;
    String firstName;
    String lastName;
    String position;
    int salary;

    public EmployeeData(int id, String firstName, String lastName, String position, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    public String toCSVString() {
        return id + "," + firstName + "," + lastName + "," + position + "," + salary;
    }
}
