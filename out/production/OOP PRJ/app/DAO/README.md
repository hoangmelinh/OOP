# Database Connection Setup

## Cài đặt Database

### 1. Cài đặt MySQL
- Tải và cài đặt MySQL Server từ https://dev.mysql.com/downloads/
- Hoặc sử dụng XAMPP/WAMP để có MySQL và phpMyAdmin

### 2. Tạo Database và Bảng
Chạy script `database_setup.sql` trong MySQL:

```sql
-- Tạo database
CREATE DATABASE IF NOT EXISTS csdl_ptit;
USE csdl_ptit;

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### 3. Cấu hình Kết nối
Trong file `DatabaseConnection.java`, cập nhật thông tin kết nối:

```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/csdl_ptit", 
    "root",           // username
    "123456"          // password
);
```

### 4. Thêm MySQL Driver
Đảm bảo có MySQL Connector/J trong classpath:
- Tải từ: https://dev.mysql.com/downloads/connector/j/
- Thêm vào project libraries

## Sử dụng

### Đăng nhập
- Email và password được xác thực từ database
- Hỗ trợ tìm kiếm theo email (case-insensitive)

### Đăng ký
- Kiểm tra email trùng lặp
- Tự động tạo user_id mới
- Lưu thông tin vào database

### Quên mật khẩu
- Tìm user theo email
- Hiển thị mật khẩu (trong thực tế nên gửi email reset)

## Lỗi thường gặp

1. **Connection refused**: Kiểm tra MySQL service đã chạy chưa
2. **Access denied**: Kiểm tra username/password
3. **Database not found**: Chạy script tạo database trước
4. **Table doesn't exist**: Chạy script tạo bảng trước