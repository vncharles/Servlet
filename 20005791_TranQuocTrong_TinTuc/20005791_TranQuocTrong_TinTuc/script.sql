CREATE DATABASE QUANLYDANHMUC;
go

use QUANLYDANHMUC;
go

create table DANHMUC
(
	MADM int IDENTITY(1, 1) primary key,
	TENDANHMUC NVARCHAR(100),
	NGUOIQUANLY NVARCHAR(100),
	GHICHU NVARCHAR(255)
)
go

create table TINTUC
(
	MATT int IDENTITY(1, 1) primary key,
	TIEUDE NVARCHAR(255),
	NOIDUNGTT NVARCHAR(255),
	LIENKET NVARCHAR(255),
	MADM int foreign key references DANHMUC(MADM)
)
go

INSERT INTO DANHMUC
values 
	(N'Thời sự', N'Quốc Trọng', null), 
	(N'Thể thao', N'Quốc Trọng', null)
go

INSERT INTO TINTUC
values 
	(N'Nạn bạo lực học đường', N'Bạo lực học đường đang diễn ra hằng ngày và vẫn còn đang tiếp diễn, cần có những biện pháp phòng chống bạo lực học đường', 'https://vi.wikipedia.org/wiki/B%E1%BA%A1o_l%E1%BB%B1c_h%E1%BB%8Dc_%C4%91%C6%B0%E1%BB%9Dng', 1), 
	(N'Cristiano Ronaldo ghi bàn thắng kỳ lạ nhất sự nghiệp', N'Rạng sáng 23.9, Cristiano Ronaldo đã ghi cú đúp trong trận thắng của CLB Al Nassr trước Al Ahli với tỷ số 4-3, nhưng bàn mở tỷ số rất kỳ lạ khi thủ môn đối phương không nhìn thấy gì.', 'https://thanhnien.vn/cristiano-ronaldo-ghi-ban-thang-ky-la-nhat-su-nghiep-18523092307301752.htm', 2)
go