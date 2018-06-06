
package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Frus {

    @SerializedName("2:time")
    @Expose
    private String _2Time;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("time")
    @Expose
    private String time;

    public String get2Time() {
        return _2Time;
    }

    public void set2Time(String _2Time) {
        this._2Time = _2Time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
