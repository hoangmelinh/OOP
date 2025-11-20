package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements IUser{
    private String userId;
    private String username;
    private String password;
    private String name;
    private String address;
    private String contact;
    private boolean vipCheck;

    public boolean isVipCheck() {
        return vipCheck;
    }

    public User(String userId, String username, String password, String name, String address, String contact, boolean vipCheck) {
        this.userId = userId;
        this.username = username;
        setPassword(password);
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.vipCheck = vipCheck;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.equals("")) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 6 ký tự");
        }
        this.password = hashPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean checkPassword(String inputPassword) {
        return hashPassword(hashPassword(inputPassword)).equals(password);
    }

    // 🔹 Hàm mã hóa mật khẩu
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Không tìm thấy thuật toán mã hóa");
        }
    }

    @Override
    public int getBonus(){
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
