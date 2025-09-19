Báo cáo hệ thống quản lý đặt vé xem phim
1. Giới thiệu

Hệ thống quản lý đặt vé xem phim được xây dựng nhằm hỗ trợ người dùng trong việc lựa chọn phim, đặt vé, chọn ghế và thanh toán trực tuyến. Mô hình UML class diagram mô tả cấu trúc dữ liệu, chức năng của từng lớp cũng như các mối quan hệ giữa chúng.

- link docs mô tả : https://docs.google.com/document/d/1TzOLY6r4XPx5GJ8knqD5ei02EaTnD6vWPjUKg0fLurU/edit?usp=sharing


Các lớp chính bao gồm: User, Cinema, Movie, ShowTime, Seat, Ticket, Payment.

2. Mô tả chi tiết các lớp
2.1. User

Thuộc tính:

userId: String – Mã định danh người dùng.

name: String – Tên người dùng.

email: String – Địa chỉ email.

password: String – Mật khẩu.

Phương thức:

signup(): boolean – Đăng ký tài khoản.

signin(): boolean – Đăng nhập hệ thống.

QuanLy(): void – Quản lý thông tin cá nhân.

2.2. Cinema

Thuộc tính:

cinemaId: String – Mã rạp phim.

name: String – Tên rạp.

address: String – Địa chỉ rạp.

Phương thức:

hienThiThongTin(): void – Hiển thị thông tin rạp.

2.3. Movie

Thuộc tính:

movieId: String – Mã phim.

title: String – Tên phim.

description: String – Mô tả phim.

genre: String – Thể loại phim.

Phương thức:

hienThiThongTin(): void – Hiển thị thông tin phim.

2.4. ShowTime

Thuộc tính:

showTimeId: String – Mã suất chiếu.

movieId: String – Phim được chiếu.

date: Date – Ngày chiếu.

room: String – Phòng chiếu.

Phương thức:

QuanLyPhongChieu(): void – Quản lý lịch chiếu.

2.5. Seat

Thuộc tính:

seatId: String – Mã ghế.

showTimeId: String – Suất chiếu tương ứng.

row: String – Hàng ghế.

number: int – Số ghế.

status: String – Tình trạng ghế (còn trống/đã đặt).

Phương thức:

chonGhe(): void – Chọn ghế.

2.6. Ticket

Thuộc tính:

ticketId: String – Mã vé.

showTimeId: String – Mã suất chiếu.

userId: String – Người đặt vé.

seatId: String – Ghế ngồi.

status: String – Trạng thái vé.

movieId: String – Mã phim.

Phương thức:

datVe(): void – Đặt vé.

huyVe(): void – Hủy vé.

2.7. Payment

Thuộc tính:

paymentId: String – Mã thanh toán.

ticketId: String – Vé cần thanh toán.

amount: int – Số tiền thanh toán.

status: String – Trạng thái thanh toán.

Phương thức:

thanhToan(): void – Thực hiện thanh toán.

3. Mối quan hệ giữa các lớp

User – Ticket: Một người dùng có thể đặt nhiều vé, nhưng mỗi vé chỉ thuộc về một người dùng.

Cinema – ShowTime: Một rạp có nhiều suất chiếu, mỗi suất chiếu diễn ra tại một rạp.

Movie – ShowTime: Một phim có thể được chiếu trong nhiều suất chiếu, mỗi suất chiếu gắn với một phim.

ShowTime – Seat: Một suất chiếu có nhiều ghế, mỗi ghế thuộc về một suất chiếu.

Ticket – Seat: Một vé gắn với một ghế duy nhất, mỗi ghế chỉ có tối đa một vé.

Ticket – Payment: Mỗi vé có thể có một thanh toán đi kèm, một thanh toán ứng với một vé.

4. Kết luận

Sơ đồ lớp UML trên đã mô tả đầy đủ các thành phần chính của hệ thống đặt vé xem phim, bao gồm thông tin người dùng, rạp chiếu, phim, suất chiếu, ghế ngồi, vé và thanh toán. Mối quan hệ giữa các lớp được thiết kế rõ ràng, đảm bảo tính nhất quán dữ liệu và hỗ trợ các chức năng như: quản lý phim, đặt vé, chọn ghế, và thanh toán trực tuyến.
<img width="732" height="525" alt="image" src="https://github.com/user-attachments/assets/011af974-e0c0-421d-9a24-126eacee3936" />
