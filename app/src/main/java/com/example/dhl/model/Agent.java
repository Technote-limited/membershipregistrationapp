package com.example.dhl.model;

public class Agent {
    private int id;
    private String agentName, id_passport, member_number, phone_number, dob, gender, county, constituency, ward, date_created ;

    public Agent(int id, String agentName, String id_passport, String member_number, String phone_number, String dob, String gender, String county, String constituency, String ward, String date_created)
    {
        this.id = id;
        this.agentName = agentName;
        this.id_passport = id_passport;
        this.member_number = member_number;
        this.phone_number = phone_number;
        this.dob = dob;
        this.gender= gender;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
        this.date_created = date_created;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
