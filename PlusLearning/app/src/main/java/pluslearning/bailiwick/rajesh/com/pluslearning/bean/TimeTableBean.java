package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

public class TimeTableBean {
    private String time;
    private String subject;

    public TimeTableBean(String time, String subject) {
        this.time = time;
        this.subject = subject;
    }

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
