package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

public class SyllabusBean {

    private String chapter;
    private String status;

    public SyllabusBean(String chapter, String status) {
        this.chapter = chapter;
        this.status = status;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
