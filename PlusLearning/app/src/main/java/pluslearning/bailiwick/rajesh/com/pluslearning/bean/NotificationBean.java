package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

public class NotificationBean {

    private int image;
    private String notification;
    private String days_ago;

    public NotificationBean(int image, String notification, String days_ago) {
        this.image = image;
        this.notification = notification;
        this.days_ago = days_ago;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDays_ago() {
        return days_ago;
    }

    public void setDays_ago(String days_ago) {
        this.days_ago = days_ago;
    }
}
