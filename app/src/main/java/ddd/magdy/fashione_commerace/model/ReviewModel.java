package ddd.magdy.fashione_commerace.model;

public class ReviewModel {

    private int image;
    private String personName;
    private String personReview;
    private String time;

    public ReviewModel(int image, String personName, String personReview, String time) {
        this.image = image;
        this.personName = personName;
        this.personReview = personReview;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonReview() {
        return personReview;
    }

    public String getTime() {
        return time;
    }
}
