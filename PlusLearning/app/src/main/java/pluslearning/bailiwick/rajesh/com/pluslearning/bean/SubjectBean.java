package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

public class SubjectBean {

    private String subject;



    public SubjectBean(String subject, boolean isSelect) {

        this.subject = subject;
    }

    public SubjectBean(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
