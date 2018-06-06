package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileResponse implements Serializable {



    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("father_occupation")
    @Expose
    private String fatherOccupation;
    @SerializedName("father_contact")
    @Expose
    private String fatherContact;
    @SerializedName("mother_name")
    @Expose
    private String motherName;
    @SerializedName("mother_occupation")
    @Expose
    private String motherOccupation;
    @SerializedName("mother_contact")
    @Expose
    private String motherContact;
    @SerializedName("roll_num")
    @Expose
    private String rollNum;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("sub_course")
    @Expose
    private String subCourse;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("father_image")
    @Expose
    private String fatherImage;
    @SerializedName("mother_image")
    @Expose
    private String motherImage;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("sub_course_name")
    @Expose
    private String subCourseName;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(String fatherContact) {
        this.fatherContact = fatherContact;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherContact() {
        return motherContact;
    }

    public void setMotherContact(String motherContact) {
        this.motherContact = motherContact;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSubCourse() {
        return subCourse;
    }

    public void setSubCourse(String subCourse) {
        this.subCourse = subCourse;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFatherImage() {
        return fatherImage;
    }

    public void setFatherImage(String fatherImage) {
        this.fatherImage = fatherImage;
    }

    public String getMotherImage() {
        return motherImage;
    }

    public void setMotherImage(String motherImage) {
        this.motherImage = motherImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubCourseName() {
        return subCourseName;
    }

    public void setSubCourseName(String subCourseName) {
        this.subCourseName = subCourseName;
    }

}
