package com.model;

public abstract class Contact {

    private String name;
    private String surname;
    private String telephone;
    private String cp;
    private String email;
    private String address;

    public Contact(){}

    public Contact(String name, String surname, String telephone, String cp, String address, String email) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.cp = cp;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCP(String cp) {
        this.cp = cp;
    }

    public String getCp() {
        return cp;
    }
}
