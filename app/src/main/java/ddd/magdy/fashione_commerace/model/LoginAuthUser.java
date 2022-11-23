package ddd.magdy.fashione_commerace.model;

public class LoginAuthUser {

    private String userName;
    private String password;

    public LoginAuthUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}


