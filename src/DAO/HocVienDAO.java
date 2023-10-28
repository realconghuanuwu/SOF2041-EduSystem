/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HocVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

/**
 *
 * @author huanl
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer>{
    final String INSERT_SQL = "INSERT INTO HocVien(MaKhoaHoc,MaNguoiHoc,Diem) Values(?,?,?)";
    final String UPDATE_SQL = "UPDATE HocVien set MaKhoaHoc = ?, MaNguoiHoc =?, Diem=? WHERE MaHocVien=?";
    final String DELETE_SQL = "DELETE FROM HocVien WHERE MaHocVien= ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    final String SELECT_BY_ID = "SELECT * FROM HocVien WHERE MaHocVien=?";
    @Override
    public void insert(HocVien entity) {
        XJdbc.update(INSERT_SQL, entity.getMaKhoaHoc(),entity.getMaNguoiHoc(),entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        XJdbc.update(UPDATE_SQL, entity.getMaKhoaHoc(),entity.getMaNguoiHoc(),entity.getDiem(),entity.getMaHocVien());
    }

    @Override
    public void delete(Integer key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectByID(Integer key) {
        List<HocVien> listHocVien = this.selectBySql(SELECT_BY_ID, key);
        if (listHocVien.isEmpty()) {
            return null;
        }
        return listHocVien.get(0);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                HocVien entity = new HocVien();
                entity.setMaHocVien(rs.getInt("MaHocVien"));
                entity.setMaKhoaHoc(rs.getInt("MaKhoaHoc"));
                entity.setMaNguoiHoc(rs.getString("MaNguoiHoc"));
                entity.setDiem(rs.getFloat("Diem"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<HocVien> selectByKhoaHoc(int makh) {
        String sql = "SELECT * FROM [HocVien] WHERE MaKhoaHoc = ?";
        return this.selectBySql(sql, makh);
    }
    
}
