package pluslearning.bailiwick.rajesh.com.pluslearning.bean;

public class PlBean {

    private String cat_name;
    private int logo;

    public PlBean(String cat_name, int logo) {
        this.cat_name = cat_name;
        this.logo = logo;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
