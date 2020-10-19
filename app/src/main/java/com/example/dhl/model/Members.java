package com.example.dhl.model;

import com.google.gson.annotations.SerializedName;

public class Members {
    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("middle_name")
    private String middle_name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("id_passport")
    private String id_passport;
    @SerializedName("member_number")
    private String member_number;
    @SerializedName("phone_number")
    private String phone_number;
    @SerializedName("dob")
    private String dob;
    @SerializedName("gender")
    private String gender;
    @SerializedName("county")
    private String county;
    @SerializedName("constituency")
    private String constituency;
    @SerializedName("ward")
    private String ward;
    @SerializedName("member_picture")
    private String member_picture;

    public Members(int id, String first_name, String middle_name, String surname,String id_passport, String member_number, String phone_number,
                   String dob, String gender, String county, String constituency, String ward, String member_picture) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.surname = surname;
        this.id_passport = id_passport;
        this.member_number = member_number;
        this.phone_number = phone_number;
        this.dob = dob;
        this.gender = gender;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
        this.member_picture=member_picture;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId_passport() {
        return id_passport;
    }

    public void setId_passport(String id_passport) {
        this.id_passport = id_passport;
    }

    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
    public String getMember_picture() {
        return member_picture;
    }
    public void setMember_picture(String member_picture) {
        this.member_picture = member_picture;
    }

}
