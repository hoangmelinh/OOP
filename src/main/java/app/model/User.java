package app.model;

// abstract class
public abstract class User {
    protected String userId;
    protected String name;
    protected String email;
    protected String password;

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    public abstract String getRole();

    public void showInfo() {
        System.out.println("ID: " + userId + ", Name: " + name + ", Role: " + getRole());
    }
}

// AdminUser
class AdminUser extends User {
    public AdminUser(String userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
}

// NormalUser
class NormalUser extends User {
    public NormalUser(String userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public String getRole() {
        return "Regular User";
    }
}
