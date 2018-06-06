package pluslearning.bailiwick.rajesh.com.pluslearning.bean;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestTypeResponse {

    @SerializedName("tests")
    @Expose
    private List<TestTypes> tests = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<TestTypes> getTests() {
        return tests;
    }

    public void setTests(List<TestTypes> tests) {
        this.tests = tests;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}