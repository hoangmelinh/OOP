#  Hệ thống Quản lý Rạp Chiếu Phim

##  Giới thiệu
Hệ thống Quản lý Rạp Chiếu Phim được xây dựng nhằm:
- Tự động hóa quy trình đặt vé, chọn ghế và thanh toán.
- Hỗ trợ khách hàng dễ dàng đăng ký, đăng nhập, xem thông tin phim và đặt vé trực tuyến.
- Giúp nhân viên/thu ngân theo dõi lịch chiếu, quản lý tình trạng ghế, xử lý đơn đặt vé và thanh toán.
- Cho phép quản lý cập nhật thông tin phim, rạp chiếu, suất chiếu và thống kê doanh thu.

---

##  Chức năng chính

###  Khách hàng (User)
- Đăng ký, đăng nhập, cập nhật thông tin cá nhân.  
- Xem danh sách phim, chi tiết phim, lịch chiếu.  
- Chọn suất chiếu, đặt vé, chọn ghế.  
- Thanh toán vé (tiền mặt/online).  

**Thuộc tính:**  
- `userId : String`  
- `fullName : String`  
- `email : String`  
- `password : String`  

---

###  Rạp chiếu (Cinema)
- Quản lý thông tin rạp và địa chỉ.  
- Liên kết với các suất chiếu.  

**Thuộc tính:**  
- `cinemaId : String`  
- `name : String`  
- `address : String`  

---

###  Phim (Movie)
- Quản lý thông tin phim (tên, mô tả, thể loại).  
- Hiển thị danh sách phim cho khách hàng lựa chọn.  

**Thuộc tính:**  
- `movieId : String`  
- `name : String`  
- `description : String`  
- `genre : String`  

---

###  Ghế (Seat)
- Quản lý thông tin ghế trong phòng chiếu.  
- Hiển thị trạng thái ghế (còn trống/đã đặt).  

**Thuộc tính:**  
- `seatId : String`  
- `row : Int`  
- `number : Int`  

---

###  Suất chiếu (ShowTime)
- Quản lý lịch chiếu của từng phim.  
- Cho phép khách hàng đặt và hủy ghế.  

**Thuộc tính:**  
- `showTimeId : String`  
- `movieId : String`  
- `date : Date`  
- `room : String`  
- `seatStatus : Seat`  

---

###  Vé (Ticket)
- Lưu thông tin đặt vé, trạng thái vé.  
- Cho phép khách hàng xem/hủy vé.  

**Thuộc tính:**  
- `ticketId : String`  
- `cinemaId : String`  
- `showTimeId : String`  
- `seatId : String`  
- `movieId : String`  
- `status : String`  

---

###  Thanh toán (Payment)
- Xử lý thanh toán cho vé đã đặt.  
- Kiểm tra và cập nhật trạng thái thanh toán.  

**Thuộc tính:**  
- `paymentId : String`  
- `ticketId : String`  
- `amount : Int`  
- `status : Boolean`  

---

##  Mô hình lớp (Class Diagram)
Dự án được xây dựng dựa trên mô hình hướng đối tượng với các lớp chính:  
`User`, `Cinema`, `Movie`, `Seat`, `ShowTime`, `Ticket`, `Payment`.

<img width="1085" height="501" alt="image" src="https://github.com/user-attachments/assets/d95ba82a-bd5f-4402-88b6-f27556788855" />

