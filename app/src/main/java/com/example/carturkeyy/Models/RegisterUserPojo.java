package com.example.carturkeyy.Models;

public class RegisterUserPojo {
    static String name="";
    static String surname="";
    static String phone="";
    static String tc="";
    static String usermail="";
    static String userpassword="";

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        RegisterUserPojo.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        RegisterUserPojo.surname = surname;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        RegisterUserPojo.phone = phone;
    }

    public static String getTc() {
        return tc;
    }

    public static void setTc(String tc) {
        RegisterUserPojo.tc = tc;
    }

    public static String getUsermail() {
        return usermail;
    }

    public static void setUsermail(String usermail) {
        RegisterUserPojo.usermail = usermail;
    }

    public static String getUserpassword() {
        return userpassword;
    }

    public static void setUserpassword(String userpassword) {
        RegisterUserPojo.userpassword = userpassword;
    }
}
