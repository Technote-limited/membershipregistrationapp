package com.example.dhl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Members  {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("middle_name")
    @Expose
    private String middleName;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("id_passport")
    @Expose
    private String idPassport;

    @SerializedName("member_number")
    @Expose
    private String memberNumber;

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("county")
    @Expose
    private String county;

    @SerializedName("constituency")
    @Expose
    private String constituency;

    @SerializedName("ward")
    @Expose
    private String ward;

    @SerializedName("member_picture")
    @Expose
    private String memberPicture;

    @SerializedName("date_joined")
    @Expose
    private String dateJoined;


    public Members() {
    }


    public Members(Integer id, String firstName, String middleName, String surname,String idPassport, String memberNumber, String phoneNumber,
                   String dob, String gender, String county, String constituency, String ward, String memberPicture, String dateJoined) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.idPassport = idPassport;
        this.memberNumber = memberNumber;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
        this.memberPicture= memberPicture;
        this.dateJoined= dateJoined;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(String idPassport) {
        this.idPassport = idPassport;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getMemberPicture() {
        return memberPicture;
    }

    public void setMemberPicture(String memberPicture) {
        this.memberPicture = memberPicture;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }
}
