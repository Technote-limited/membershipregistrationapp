package com.example.dhl;

public class Members {
    private String memberNumber;
    private String IDNumber;
    private String surname;
    private String firstName;
    private String middleName;
    private String phoneNumber;
    private String DOB;
    private String gender;
    private String county;
    private String constituency;
    private String ward;

    public Members(String memberNumber, String idNumber, String surname, String firstName, String middleName, String phoneNumber, String dob, String gender, String county, String constituency, String ward) {
        this.memberNumber = memberNumber;
        this.IDNumber = idNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.DOB = dob;
        this.gender = gender;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    @Override
    public String toString() {
        return "Members{" +
                "memberNumber='" + memberNumber + '\'' +
                ", IDNumber='" + IDNumber + '\'' +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", DOB='" + DOB + '\'' +
                ", gender='" + gender + '\'' +
                ", county='" + county + '\'' +
                ", constituency='" + constituency + '\'' +
                ", ward='" + ward + '\'' +
                '}';
    }



}
