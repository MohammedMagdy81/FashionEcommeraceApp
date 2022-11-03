package ddd.magdy.fashione_commerace.model;

public class User {

    private String id;
    private String userName;
    private String email;
    private String imagePath;

    public User(String userName, String email, String imagePath,String id) {
        this.userName = userName;
        this.email = email;
        this.imagePath = imagePath;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getImagePath() {
        return imagePath;
    }
}
