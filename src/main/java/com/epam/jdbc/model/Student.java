package com.epam.jdbc.model;

import java.util.Date;

public class Student extends CommonUser {

    private String address;

    private String gender;

    private String bloodGroup;

    private int parentId;

    private int academicClassId;

    private Date birthDay;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAcademicClassId() {
        return academicClassId;
    }

    public void setAcademicClassId(int academicClassId) {
        this.academicClassId = academicClassId;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
