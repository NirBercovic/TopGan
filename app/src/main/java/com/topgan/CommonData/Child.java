package com.topgan.CommonData;

import java.util.Date;

public class Child {

    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private String image;
    private String parent1;
    private String parentPhone1;
    private String parent2;
    private String parentPhone2;
    private String contact1;
    private String contactPhpne1;
    private String relation1;
    private String contact2;
    private String contactPhpne2;
    private String relation2;
    private String sensitive;
    private String comments;


    public Child() {
    }

    public Child(String id, String firstName, String lastName, Date birthDate, String gender, String image, String parent1, String parentPhone1, String parent2, String parentPhone2, String contact1, String contactPhpne1, String relation1, String contact2, String contactPhpne2, String relation2, String sensitive, String comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.image = image;
        this.parent1 = parent1;
        this.parentPhone1 = parentPhone1;
        this.parent2 = parent2;
        this.parentPhone2 = parentPhone2;
        this.contact1 = contact1;
        this.contactPhpne1 = contactPhpne1;
        this.relation1 = relation1;
        this.contact2 = contact2;
        this.contactPhpne2 = contactPhpne2;
        this.relation2 = relation2;
        this.sensitive = sensitive;
        this.comments = comments;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent1() {
        return parent1;
    }

    public void setParent1(String parent1) {
        this.parent1 = parent1;
    }

    public String getParentPhone1() {
        return parentPhone1;
    }

    public void setParentPhone1(String parentPhone1) {
        this.parentPhone1 = parentPhone1;
    }

    public String getParent2() {
        return parent2;
    }

    public void setParent2(String parent2) {
        this.parent2 = parent2;
    }

    public String getParentPhone2() {
        return parentPhone2;
    }

    public void setParentPhone2(String parentPhone2) {
        this.parentPhone2 = parentPhone2;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContactPhpne1() {
        return contactPhpne1;
    }

    public void setContactPhpne1(String contactPhpne1) {
        this.contactPhpne1 = contactPhpne1;
    }

    public String getRelation1() {
        return relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getContactPhpne2() {
        return contactPhpne2;
    }

    public void setContactPhpne2(String contactPhpne2) {
        this.contactPhpne2 = contactPhpne2;
    }

    public String getRelation2() {
        return relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2;
    }

    public String getSensitive() {
        return sensitive;
    }

    public void setSensitive(String sensitive) {
        this.sensitive = sensitive;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
