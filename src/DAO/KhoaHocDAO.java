/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhoaHoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

/**
 *
 * @author huanl
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>{
    final String INSERT_SQL = "INSERT INTO KhoaHoc(MaChuyenDe,HocPhi,ThoiLuong,NgayKhaiGiang,GhiChu,MaNhanVien,NgayTao) Values(?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhoaHoc set MaChuyenDe = ?, HocPhi =?, ThoiLuong=?, NgayKhaiGiang=?, GhiChu=? , MaNhanVien=?, NgayTao=? WHERE MaKhoaHoc=?";
    final String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKhoaHoc=?";
    final String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    final String SELECT_BY_ID = "SELECT * FROM KhoaHoc WHERE MaKhoaHoc=?";
    final String SELECT_BY_CHUYENDE = "SELECT * FROM KhoaHoc WHERE MaChuyenDe=?";
    
    @Override
    public void insert(KhoaHoc entity) {
        XJdbc.update(INSERT_SQL, entity.getMaChuyenDe(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKhaiGiang(),entity.getGhiChu(),entity.getMaNhanVien(),entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaChuyenDe(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKhaiGiang(),entity.getGhiChu(),entity.getMaNhanVien(),entity.getNgayTao(),entity.getMaKhoaHoc());
    }

    @Override
    public void delete(Integer key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectByID(Integer key) {
        List<KhoaHoc> listKhoaHoc = this.selectBySql(SELECT_BY_ID, key);
        if (listKhoaHoc.isEmpty()) {
            return null;
        }
        return listKhoaHoc.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKhoaHoc(rs.getInt("MaKhoaHoc"));
                entity.setMaChuyenDe(rs.getString("MaChuyenDe"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKhaiGiang(rs.getDate("NgayKhaiGiang"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaChuyenDe = ?";
        return this.selectBySql(sql, macd);
    }
    
    public List<KhoaHoc> selectKhoaHocByChuyenDe(String macd) {
        return this.selectBySql(SELECT_BY_CHUYENDE,macd);
    }
    
    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year(NgayKhaiGiang) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
