package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentMonth {

    @SerializedName("present_total")
    @Expose
    private Integer presentTotal;
    @SerializedName("absent_total")
    @Expose
    private Integer absentTotal;
    @SerializedName("number_of_holidays")
    @Expose
    private Integer numberOfHolidays;

    public Integer getPresentTotal() {
        return presentTotal;
    }

    public void setPresentTotal(Integer presentTotal) {
        this.presentTotal = presentTotal;
    }

    public Integer getAbsentTotal() {
        return absentTotal;
    }

    public void setAbsentTotal(Integer absentTotal) {
        this.absentTotal = absentTotal;
    }

    public Integer getNumberOfHolidays() {
        return numberOfHolidays;
    }

    public void setNumberOfHolidays(Integer numberOfHolidays) {
        this.numberOfHolidays = numberOfHolidays;
    }

}
