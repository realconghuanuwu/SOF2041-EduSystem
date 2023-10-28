/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ChuyenDe;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;

/**
 *
 * @author huanl
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {
    final String INSERT_SQL = "INSERT INTO ChuyenDe(MaChuyenDe,TenChuyenDe,HocPhi,ThoiLuong,Hinh,Mota) Values(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ChuyenDe set TenChuyenDe = ?, HocPhi =?, ThoiLuong=?, Hinh=?,Mota=? WHERE MaChuyenDe=?";
    final String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaChuyenDe=?";
    final String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    final String SELECT_BY_ID = "SELECT * FROM ChuyenDe WHERE MaChuyenDe=?";
    
    @Override
    public void insert(ChuyenDe entity) {
        XJdbc.update(INSERT_SQL, entity.getMaChuyenDe(), entity.getTenChuyenDe(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa() );
    }

    @Override
    public void update(ChuyenDe entity) {
        XJdbc.update(UPDATE_SQL, entity.getTenChuyenDe(),entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaChuyenDe());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectByID(String key) {
        List<ChuyenDe> listChuyenDe = this.selectBySql(SELECT_BY_ID, key);
        if (listChuyenDe.isEmpty()) {
            return null;
        }
        return listChuyenDe.get(0);
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> listNhanVien = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                ChuyenDe entity = new ChuyenDe();
                entity.setMaChuyenDe(rs.getString("MaChuyenDe"));
                entity.setTenChuyenDe(rs.getString("TenChuyenDe"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("Mota"));
                listNhanVien.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listNhanVien;
    }
    
}
