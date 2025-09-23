package model;

public class User {
    private String userId;
    private String fullName;
    private String email;
    private String password;

    public User Register() {
        return new User();
    }

    public boolean Login() {
        return true;
    }

    public void Update() {
    }
}
