
package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timetable {

    @SerializedName("mon")
    @Expose
    private List<Mon> mon = null;
    @SerializedName("tue")
    @Expose
    private List<Tue> tue = null;
    @SerializedName("wed")
    @Expose
    private List<Wed> wed = null;
    @SerializedName("thu")
    @Expose
    private List<Thu> thu = null;
    @SerializedName("fri")
    @Expose
    private List<Frus> fri = null;
    @SerializedName("sat")
    @Expose
    private List<Sat> sat = null;

    public List<Mon> getMon() {
        return mon;
    }

    public void setMon(List<Mon> mon) {
        this.mon = mon;
    }

    public List<Tue> getTue() {
        return tue;
    }

    public void setTue(List<Tue> tue) {
        this.tue = tue;
    }

    public List<Wed> getWed() {
        return wed;
    }

    public void setWed(List<Wed> wed) {
        this.wed = wed;
    }

    public List<Thu> getThu() {
        return thu;
    }

    public void setThu(List<Thu> thu) {
        this.thu = thu;
    }

    public List<Frus> getFri() {
        return fri;
    }

    public void setFri(List<Frus> fri) {
        this.fri = fri;
    }

    public List<Sat> getSat() {
        return sat;
    }

    public void setSat(List<Sat> sat) {
        this.sat = sat;
    }

}
