package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

public class ChapterBean {
    private String topic;
    private String status;

    public ChapterBean(String topic, String status) {
        this.topic = topic;
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
