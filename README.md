# Hệ thống Đặt Vé Xem Phim Trực Tuyến (Cinema Booking System)

## 1. Lời giới thiệu

Trong bối cảnh công nghệ thông tin phát triển mạnh mẽ, các hoạt động giải trí của con người cũng ngày càng được số hóa, đặc biệt là trong lĩnh vực điện ảnh. Với thói quen thường xuyên đi xem phim rạp, nhóm chúng tôi nhận thấy rằng việc mua vé theo phương thức truyền thống (xếp hàng tại quầy, chờ xử lý thủ công) thường gây mất thời gian và thiếu tính linh hoạt cho người dùng.

Chính từ nhu cầu thực tiễn đó, nhóm quyết định xây dựng **Hệ thống đặt vé xem phim trực tuyến** bằng ngôn ngữ **Java** (lập trình hướng đối tượng), kết hợp cùng hệ quản trị cơ sở dữ liệu quan hệ để đảm bảo việc quản lý thông tin được hiệu quả, chính xác và có khả năng mở rộng.

> **Mục tiêu:** Ứng dụng được thiết kế nhằm mang đến cho khách hàng một trải nghiệm đặt vé nhanh chóng, tiện lợi, đồng thời giúp các rạp phim nâng cao khả năng quản lý suất chiếu, phòng chiếu và số lượng vé bán ra.

---

## 2. Phân tích & Xác định yêu cầu

Ứng dụng được xây dựng nhằm hỗ trợ người dùng tìm kiếm, lựa chọn và đặt vé xem phim trực tuyến dễ dàng. Hệ thống cung cấp đầy đủ các chức năng từ đăng ký/đăng nhập, duyệt danh sách phim, xem lịch chiếu, chọn ghế, đến thanh toán online.

### a. Yêu cầu đối với người dùng (User Features)
- [x] **Quản lý tài khoản:** Cho phép người dùng đăng ký và đăng nhập vào hệ thống.
- [x] **Tìm kiếm phim:** Hỗ trợ tìm kiếm và xem thông tin chi tiết (mô tả, thể loại, thời lượng).
- [x] **Tra cứu lịch chiếu:** Cung cấp danh sách rạp, phòng chiếu và các khung giờ chiếu tương ứng.
- [x] **Đặt vé:** Quy trình chọn suất chiếu, chọn ghế ngồi trực quan và đặt vé.
- [x] **Thanh toán:** Hỗ trợ thanh toán trực tuyến hoặc xác nhận đặt vé nhanh chóng.
- [x] **Lịch sử đặt vé:** Hiển thị thông tin vé đã đặt để người dùng xem lại khi cần.

### b. Yêu cầu đối với hệ thống (System Requirements)
- **Quản lý dữ liệu:** Lưu trữ thông tin về phim, rạp, suất chiếu, ghế ngồi, vé... một cách nhất quán.
- **Real-time update:** Đảm bảo trạng thái ghế được cập nhật theo thời gian thực, tránh tình trạng trùng ghế (double booking).
- **Validation:** Tự động kiểm tra điều kiện trước khi đặt vé (ghế còn trống, suất chiếu hợp lệ...).
- **Bảo mật:** An toàn thông tin tài khoản người dùng và dữ liệu giao dịch.
- **UI/UX:** Giao diện trực quan, thân thiện, trải nghiệm mượt mà.

### c. Yêu cầu kỹ thuật (Technical Stack)
- **Ngôn ngữ:** Java (Lập trình hướng đối tượng - OOP).
- **Cơ sở dữ liệu:** Hệ quản trị CSDL quan hệ (Relational Database - MySQL/SQL Server).
- **Kiến trúc:** Mô hình phân lớp rõ ràng (**Model – DAO – Service – UI**) giúp dễ bảo trì và mở rộng.
- **Hiệu năng:** Đảm bảo khả năng xử lý đồng thời (Concurrency) khi nhiều người dùng đặt vé cùng lúc.
## 3. Báo cáo bài tập lớn : https://docs.google.com/document/d/1oa8HUThJFDVHur5r_VW0FPb329oy5L-Zs5oamkI3iZ4/edit?usp=sharing  
