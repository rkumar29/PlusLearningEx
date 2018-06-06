
package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thu {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("subject")
    @Expose
    private String subject;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
