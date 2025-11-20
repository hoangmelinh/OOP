package service;

import model.User;
import model.VipUser;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepo) {
        this.userRepository = userRepo;
    }


    // Đăng ký user mới
    public void registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        // kiểm tra username đã tồn tại chưa
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.insert(user);
    }

    // Đăng nhập
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.checkPassword(password)) {
            return user; // đăng nhập thành công
        }
        return null; // thất bại
    }

    // Lấy user theo username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Lấy tất cả user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Cập nhật user
    public void updateUser(User user) {
        userRepository.update(user);
    }

    // Xoá user
    public void deleteUser(String userId) {
        userRepository.delete(userId);
    }

    public User getUserById(String id){
        User user =  userRepository.findByUserId(id);
        if(user.isVipCheck() == true) return new VipUser(user.getUserId(), user.getUsername(), user.getPassword(), user.getName(), user.getAddress(), user.getContact(),user.isVipCheck(), 20);
        return user;
    }

}
