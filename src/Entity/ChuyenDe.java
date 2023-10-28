/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author huanl
 */
public class ChuyenDe {
    private String MaChuyenDe;
    private String TenChuyenDe;
    private float HocPhi;
    private int ThoiLuong;
    private String Hinh;
    private String MoTa;

    public String getMaChuyenDe() {
        return MaChuyenDe;
    }

    public void setMaChuyenDe(String MaChuyenDe) {
        this.MaChuyenDe = MaChuyenDe;
    }

    public String getTenChuyenDe() {
        return TenChuyenDe;
    }

    public void setTenChuyenDe(String TenChuyenDe) {
        this.TenChuyenDe = TenChuyenDe;
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

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    @Override
    public String toString() {
        return this.TenChuyenDe;
    }
    
    @Override
    public boolean equals(Object obj) {
        ChuyenDe other = (ChuyenDe) obj;
        return other.getMaChuyenDe().equals(this.getMaChuyenDe());
    }
    
}
