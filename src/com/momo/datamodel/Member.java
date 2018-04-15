package com.momo.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Member {
    private SimpleStringProperty memberId; // member id
    private SimpleStringProperty firstName; // member first name
    private SimpleStringProperty lastName; // member last name
    private SimpleStringProperty phoneNumber; // member phone number
    private SimpleStringProperty email; // member email
    private SimpleStringProperty streetAddress; // member street address
    private String city; // member city
    private String zipCode; // member zipcode
    private String state; // member state
    private int id; // member id

    // Constructor
    public Member() {
        this.memberId = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.streetAddress = new SimpleStringProperty();
    }

    // return member id
    public String getMemberId() {
        return memberId.get();
    }
    // sets member id
    public void setMemberId(String memberId) {
        this.memberId.set(memberId);
    }

    // return member first name
    public String getFirstName() {
        return firstName.get();
    }

    // sets member first name
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    // return member last name
    public String getLastName() {
        return lastName.get();
    }

    // sets member last name
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    // return member phone number
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    // sets member phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    // returns member email
    public String getEmail() {
        return email.get();
    }

    // set member email
    public void setEmail(String email) {
        this.email.set(email);
    }

    // return member address
    public String getStreetAddress() {
        return streetAddress.get();
    }
    // sets member street address
    public void setStreetAddress(String streetAddress) {
        this.streetAddress.set(streetAddress);
    }

    // return member city
    public String getCity() {
        return city;
    }

    // sets members city
    public void setCity(String city) {
        this.city = city;
    }

    // return member zipcode
    public String getZipCode() {
        return zipCode;
    }

    // set member zip code
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    // return member state
    public String getState() {
        return state;
    }

    // sets member state
    public void setState(String state) {
        this.state = state;
    }

    // return member id
    public int getId() {
        return id;
    }

    // sets member id
    public void setId(int id) {
        this.id = id;
    }
}
