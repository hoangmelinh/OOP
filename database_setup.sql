-- Script để tạo database và bảng users cho hệ thống đặt vé rạp chiếu phim
-- Chạy script này trong MySQL để tạo database và bảng cần thiết

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

-- Tạo index cho email để tăng tốc độ tìm kiếm
CREATE INDEX idx_users_email ON users(email);

-- Thêm dữ liệu mẫu (tùy chọn)
INSERT INTO users (user_id, name, email, password) VALUES 
('u1', 'Nguyen Van A', 'a@example.com', '123456'),
('u2', 'Tran Thi B', 'b@example.com', '123456')
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- Kiểm tra dữ liệu
SELECT * FROM users;

