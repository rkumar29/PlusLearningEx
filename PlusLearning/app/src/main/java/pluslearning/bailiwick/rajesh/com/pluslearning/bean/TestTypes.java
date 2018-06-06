package pluslearning.bailiwick.rajesh.com.pluslearning.bean;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestTypes {

    @SerializedName("test_id")
    @Expose
    private String testId;
    @SerializedName("test_type_id")
    @Expose
    private String testTypeId;
    @SerializedName("test_type")
    @Expose
    private String testType;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(String testTypeId) {
        this.testTypeId = testTypeId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

}