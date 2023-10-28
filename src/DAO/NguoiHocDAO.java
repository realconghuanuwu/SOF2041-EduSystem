/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.NguoiHoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

/**
 *
 * @author huanl
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    final String INSERT_SQL = "INSERT INTO NguoiHoc(MaNguoiHoc,Hoten,GioiTinh,NgaySinh,DienThoai,Email,GhiChu,MaNhanVien,NgayDangKy) Values(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NguoiHoc set Hoten = ?, GioiTinh =?, NgaySinh=?, DienThoai=?, Email=? , GhiChu=?  WHERE MaNguoiHoc=?";
    final String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNguoiHoc=?";
    final String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    final String SELECT_BY_ID = "SELECT * FROM NguoiHoc WHERE MaNguoiHoc=?";
    @Override
    public void insert(NguoiHoc entity) {
        XJdbc.update(INSERT_SQL, entity.getMaNguoiHoc(),entity.getHoTen(),entity.isGioiTinh(),entity.getNgaySinh(),entity.getDienThoai(), entity.getEmail(),entity.getGhiChu(),entity.getMaNhanVien(),entity.getNgayDangKy());
    }

    @Override
    public void update(NguoiHoc entity) {
        XJdbc.update(UPDATE_SQL, entity.getHoTen(),entity.isGioiTinh(),entity.getNgaySinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNguoiHoc());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectByID(String key) {
        List<NguoiHoc> listNguoiHoc = this.selectBySql(SELECT_BY_ID, key);
        if (listNguoiHoc.isEmpty()) {
            return null;
        }
        return listNguoiHoc.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNguoiHoc(rs.getString("MaNguoiHoc"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setNgayDangKy(rs.getDate("NgayDangKy"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "Select * from NguoiHoc where HoTen like ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql = "Select * from NguoiHoc where Hoten like ? and MaNguoiHoc not in (select MaNguoiHoc from HocVien Where MaKhoaHoc = ?)";
        return this.selectBySql(sql, "%"+keyword+"%",makh);
        
    }
    
    
}
