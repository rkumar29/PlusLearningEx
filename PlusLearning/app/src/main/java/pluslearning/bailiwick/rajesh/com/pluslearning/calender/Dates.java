package pluslearning.bailiwick.rajesh.com.pluslearning.calender;

public class Dates {

    int date;
    int months;
    int year;

    public Dates(int date, int months, int year) {
        this.date = date;
        this.months = months;
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
