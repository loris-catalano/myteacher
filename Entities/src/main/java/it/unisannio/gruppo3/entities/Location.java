package it.unisannio.gruppo3.entities;

public class Location {
    public Location(String city, String street, int civicNumber, Teacher teacher) {
        this.city = city;
        this.street = street;
        this.civicNumber = civicNumber;
        this.teacher = teacher;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCivicNumber() {
        return civicNumber;
    }

    public void setCivicNumber(int civicNumber) {
        this.civicNumber = civicNumber;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    private String city;
    private String street;
    private int civicNumber;
    private Teacher teacher;
}

