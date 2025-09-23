package model;

public class User {
    private String Userid;                 // Mã định danh duy nhất (user_id trong DB)
    private String email;            // Email đăng nhập
    private String PassWord;
    private String fullName;         // Họ tên


    public User(String Userid, String email, String PassWord, String fullName) {
        this.Userid = Userid;
        this.email = email;
        this.PassWord = PassWord;
        this.fullName = fullName;
    }
}
