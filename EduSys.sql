use EduSysPS28692
go
CREATE TABLE ChuyenDe (
    MaChuyenDe NCHAR(5) PRIMARY KEY NOT NULL,
    TenChuyenDe NVARCHAR(50) NOT NULL,
    HocPhi FLOAT NOT NULL,
    ThoiLuong INT NOT NULL,
    Hinh NVARCHAR(50) NOT NULL,
    MoTa NVARCHAR(255) NOT NULL
);

CREATE TABLE NhanVien
(
	MaNhanVien NVARCHAR(20) PRIMARY KEY NOT NULL,
	MatKhau NVARCHAR(50) NOT NULL,
	HoTen NVARCHAR(50) NOT NULL,
	VaiTro BIT NOT NULL,
)


go

CREATE TABLE NguoiHoc (
    MANguoiHoc NCHAR(7) PRIMARY KEY NOT NULL,
    Hoten NVARCHAR(50) NOT NULL,
    GioiTinh BIT DEFAULT 1,
    NgaySinh DATE NOT NULL,
    DienThoai NVARCHAR(24) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    GhiChu NVARCHAR(255) NULL,
    MaNhanVien NVARCHAR(20) NOT NULL,
    NgayDangKy DATE DEFAULT GETDATE(),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien (MaNhanVien) on delete no action on update cascade
);


go

CREATE TABLE KhoaHoc (
    MaKhoaHoc INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    MaChuyenDe NCHAR(5) NOT NULL,
    HocPhi FLOAT NOT NULL,
    ThoiLuong INT NOT NULL,
    NgayKhaiGiang DATE NOT NULL,
    GhiChu NVARCHAR(255) NULL,
    MaNhanVien NVARCHAR(20) NOT NULL,
    NgayTao DATE DEFAULT GETDATE(),
    FOREIGN KEY (MaChuyenDe) REFERENCES ChuyenDe (MaChuyenDe) on delete no action on update cascade,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien (MaNhanVien) on delete no action on update cascade
);

ALTER TABLE KhoaHoc ADD CONSTRAINT FK_KhoaHoc_ChuyenDe FOREIGN KEY (MaChuyenDe) REFERENCES ChuyenDe(MaChuyenDe) ON DELETE cascade ON UPDATE no action;


go

CREATE TABLE HocVien (
    MaHocVien INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    MaKhoaHoc INT NOT NULL,
    MaNguoiHoc NCHAR(7) NOT NULL,
    Diem FLOAT DEFAULT -1,
	--FOREIGN KEY (MaNguoiHoc) REFERENCES NguoiHoc (MaNguoiHoc) on delete no action on update cascade,
    --FOREIGN KEY (MaKhoaHoc) REFERENCES KhoaHoc (MaKhoaHoc) on delete no action on update cascade
    
);

ALTER TABLE HocVien ADD CONSTRAINT FK_NguoiHoc_HocVien FOREIGN KEY (MaNguoiHoc) REFERENCES NguoiHoc(MaNguoiHoc) ON DELETE cascade ON UPDATE cascade;
ALTER TABLE HocVien ADD CONSTRAINT FK_HocVien_KhoaHoc FOREIGN KEY (MaKhoaHoc) REFERENCES KhoaHoc(MaKhoaHoc) ON DELETE cascade ON UPDATE no action;



go

CREATE or ALTER PROCEDURE SearchNguoiHoc
    @Hoten NVARCHAR(100)
AS
BEGIN
    SELECT MaNguoiHoc, Hoten, GioiTinh, NgaySinh, DienThoai, Email
    FROM NguoiHoc
    WHERE Hoten LIKE '%' + @Hoten + '%';
END

go

CREATE or ALTER PROC sp_BangDiem(@MaKhoaHoc INT)
AS BEGIN
	SELECT 
		nh.MaNguoiHoc,
		nh.Hoten,
		hv.Diem
	FROM HocVien hv
		JOIN NguoiHoc nh ON nh.MaNguoiHoc=hv.MaNguoiHoc
	WHERE hv.MaKhoaHoc = @MaKhoaHoc
	ORDER BY hv.Diem DESC
END

EXECUTE sp_BangDiem [1]

GO

CREATE or ALTER PROC Sp_DoanhThu (@Year INT)
AS BEGIN
	SELECT TenChuyenDe ChuyenDe,
		COUNT(DISTINCT kh.MaKhoaHoc) SoKH,
		COUNT(hv.MaKhoaHoc) SoHV,
		SUM(kh.HocPhi) DoanhThu,
		MIN(kh.HocPhi) ThapNhat,
		MAX(kh.HocPhi) CaoNhat,
		AVG(kh.HocPhi) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKhoaHoc=hv.MaKhoaHoc
		JOIN ChuyenDe cd ON cd.MaChuyenDe=kh.MaChuyenDe
	WHERE YEAR(NgayKhaiGiang) = @Year
	GROUP BY TenChuyenDe
END

EXEC Sp_DoanhThu [2023]

GO

CREATE PROC sp_LuongNguoiHoc
AS BEGIN
	SELECT
		YEAR(NgayDangKy) Nam,
		COUNT(*) SoLuong,
		MIN(NgayDangKy) DauTien,
		MAX(NgayDangKy) CuoiCung
	FROM NguoiHoc
	GROUP BY YEAR(NgayDangKy)
END

EXEC sp_LuongNguoiHoc 

GO

CREATE PROC sp_DiemChuyenDe
AS BEGIN
	SELECT
		TenChuyenDe ChuyenDe,
		COUNT(MaHocVien) SoHV,
		MIN(Diem) ThapNhat,
		MAX(Diem) CaoNhat,
		AVG(Diem) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKhoaHoc=hv.MaKhoaHoc
		JOIN ChuyenDe cd ON cd.MaChuyenDe=kh.MaChuyenDe
	GROUP BY TenChuyenDe
END

EXEC sp_DiemChuyenDe


 INSERT INTO [NguoiHoc] ([MaNguoiHoc], [HoTen], [GioiTinh], [NgaySinh], [DienThoai], [Email], MaNhanVien)
VALUES
    ('PS00001', N'Nguyễn Thị Hồng', 0 , '1990-04-15', '0987654321', 'hongnguyen90@example.com', 'NV01'),
    ('PS00002', N'Trần Văn Hải', 1, '1985-09-07', '0912345678', 'haitran85@example.com', 'NV01'),
    ('PS00003', N'Lê Thị Kim Anh', 0, '1992-11-22', '0909090909', 'kimanhle92@example.com', 'NV01'),
    ('PS00004', N'Phạm Đức Minh', 1, '1988-07-18', '0979797979', 'minhpham88@example.com', 'NV01'),
    ('PS00005', N'Hoàng Thị Thuỷ', 0, '1995-03-09', '0888888888', 'thuyhoang95@example.com', 'NV01'),
    ('PS00006', N'Vũ Quang Nam', 1, '1984-06-28', '0969696969', 'namvu84@example.com', 'NV01'),
    ('PS00007', N'Đặng Thanh Thảo', 0, '1993-12-05', '0959595959', 'thaodang93@example.com', 'NV01'),
    ('PS00008', N'Bùi Văn Đức', 1, '1991-08-10', '0945454545', 'ducbui91@example.com', 'NV01'),
    ('PS00009', N'Ngô Thị Mai', 0, '1987-05-20', '0932323232', 'maingo87@example.com', 'NV01'),
    ('PS00010', N'Trịnh Hữu Dũng', 1, '1990-02-02', '0921212121', 'dungtrinh90@example.com', 'NV01'),
    ('PS00011', N'Lý Thị Thanh Hằng', 0, '1986-09-14', '0987878787', 'hangly86@example.com', 'NV01'),
    ('PS00012', N'Hồ Đức Trung', 1, '1994-11-23', '0967676767', 'trungho94@example.com', 'NV01'),
    ('PS00013', N'Đỗ Văn An', 1, '1989-05-30', '0919191919', 'ando89@example.com', 'NV01'),
    ('PS00014', N'Vương Thị Lan', 0, '1983-02-17', '0999999999', 'lanvuong83@example.com', 'NV01'),
    ('PS00015', N'Nguyễn Văn Tú', 1, '1997-07-06', '0906060606', 'tunguyen97@example.com', 'NV01'),
    ('PS00016', N'Trần Thị Thu', 0, '1992-01-11', '0978787878', 'thutran92@example.com', 'NV01'),
    ('PS00017', N'Lê Văn Đạt', 1, '1988-12-25', '0943434343', 'datle88@example.com', 'NV01'),
    ('PS00018', N'Phạm Thị Hương', 0, '1993-06-08', '0965656565', 'huongpham93@example.com', 'NV01'),
    ('PS00019', N'Hoàng Văn Thành', 1, '1990-09-19', '0932323232', 'thanhhoang90@example.com', 'NV01'),
    ('PS00020', N'Vũ Thị Hoa', 0, '1987-04-03', '0932613765', 'hoa@example.com', 'NV01')