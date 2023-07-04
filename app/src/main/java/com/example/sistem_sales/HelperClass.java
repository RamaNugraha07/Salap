package com.example.sistem_sales;

public class HelperClass {

    String nama,email, username, password;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String nama, String email, String username, String password) {
        this.nama = nama;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public HelperClass() {
    }
}
