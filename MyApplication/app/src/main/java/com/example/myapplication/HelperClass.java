package com.example.myapplication;

public class HelperClass {

    String name,phone,email,password,doctorsCount,bedsCount;
    double latitude,longitude;

    public HelperClass(String name, String phone, String email, String password, String doctorsCount, String bedsCount, double latitude, double longitude) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.doctorsCount = doctorsCount;
        this.bedsCount = bedsCount;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDoctorsCount() {
        return doctorsCount;
    }

    public void setDoctorsCount(String doctorsCount) {
        this.doctorsCount = doctorsCount;
    }

    public String getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(String bedsCount) {
        this.bedsCount = bedsCount;
    }

    public HelperClass() {
    }
}