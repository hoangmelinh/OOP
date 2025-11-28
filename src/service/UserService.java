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
    public boolean registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return false;
        }
        // kiểm tra username đã tồn tại chưa
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        userRepository.insert(user);
        return true;
    }

    // Đăng nhập
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.checkPassword(password)) {
            return user; // đăng nhập thành công
        }
        return null; // thất bại
    }


    public User getUserById(String id){
        User user =  userRepository.findByUserId(id);
        if(user.isVipCheck() == true) return new VipUser(user.getUserId(), user.getUsername(), user.getPassword(), user.getName(), user.getAddress(), user.getContact(),user.isVipCheck(), 20);
        return user;
    }

}
