package ddd.magdy.fashione_commerace.ui.onboarding;

public class ItemOnBoarding {

    private int image;
    private String title;
    private String desc;

    public ItemOnBoarding(int image, String title, String desc) {
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }


    public String getTitle() {
        return title;
    }


    public String getDesc() {
        return desc;
    }

}
