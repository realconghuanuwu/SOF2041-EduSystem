/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author huanl
 */
public class KhoaHoc {
    private int MaKhoaHoc;
    private String MaChuyenDe;
    private float HocPhi;
    private int ThoiLuong;
    private Date NgayKhaiGiang;
    private String GhiChu;
    private String MaNhanVien;
    private Date NgayTao;

    public int getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(int MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public String getMaChuyenDe() {
        return MaChuyenDe;
    }

    public void setMaChuyenDe(String MaChuyenDe) {
        this.MaChuyenDe = MaChuyenDe;
    }

    public float getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(float HocPhi) {
        this.HocPhi = HocPhi;
    }

    public int getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(int ThoiLuong) {
        this.ThoiLuong = ThoiLuong;
    }

    public Date getNgayKhaiGiang() {
        return NgayKhaiGiang;
    }

    public void setNgayKhaiGiang(Date NgayKhaiGiang) {
        this.NgayKhaiGiang = NgayKhaiGiang;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }
    
    @Override
    public String toString() {
        String makh = String.valueOf(MaKhoaHoc);
        return makh;
    }
    
//    @Override
//    public boolean equals(Object obj) {
//        KhoaHoc other = (KhoaHoc) obj;
//        return other.getMaKhoaHoc() == this.getMaKhoaHoc();
//    }
    
}
