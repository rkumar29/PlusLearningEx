package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttendenceMonthly {

    @SerializedName("absent_days")
    @Expose
    private List<String> absentDays = null;
    @SerializedName("present_days")
    @Expose
    private List<String> presentDays = null;
    @SerializedName("number_of_holidays")
    @Expose
    private List<String> numberOfHolidays = null;

    public List<String> getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(List<String> absentDays) {
        this.absentDays = absentDays;
    }

    public List<String> getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(List<String> presentDays) {
        this.presentDays = presentDays;
    }

    public List<String> getNumberOfHolidays() {
        return numberOfHolidays;
    }

    public void setNumberOfHolidays(List<String> numberOfHolidays) {
        this.numberOfHolidays = numberOfHolidays;
    }

}

