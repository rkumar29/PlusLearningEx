package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable{

    @SerializedName("sub_name")
    @Expose
    private String subName;
    @SerializedName("total_marks")
    @Expose
    private String totalMarks;
    @SerializedName("marks_obtained")
    @Expose
    private String marksObtained;
    @SerializedName("test_date")
    @Expose
    private String testDate;

    public Result(String subName, String totalMarks, String marksObtained, String testDate) {
        this.subName = subName;
        this.totalMarks = totalMarks;
        this.marksObtained = marksObtained;
        this.testDate = testDate;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

}
