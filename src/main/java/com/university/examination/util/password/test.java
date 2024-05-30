package com.university.examination.util.password;

public class test {
    public static void main(String[] args) {
        PasswordGenerator pw = new PasswordGenerator(8, 12);
        System.out.println(pw.generatePassword());
    }
}
