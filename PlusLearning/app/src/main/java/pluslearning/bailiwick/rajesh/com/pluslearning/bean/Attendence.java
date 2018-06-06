package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendence {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("current_month")
    @Expose
    private CurrentMonth currentMonth;
    @SerializedName("overall")
    @Expose
    private Overall overall;

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

    public CurrentMonth getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(CurrentMonth currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Overall getOverall() {
        return overall;
    }

    public void setOverall(Overall overall) {
        this.overall = overall;
    }

}
