/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.MaHoa;
import utils.XJdbc;

/**
 *
 * @author huanl
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String>{

    final String INSERT_SQL = "INSERT INTO Nhanvien(MaNhanVien,MatKhau,HoTen,VaiTro,Email) Values(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE Nhanvien set MatKhau = ?, Hoten =?, VaiTro=?, Email = ? WHERE MaNhanVien=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien=?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID = "SELECT * FROM NhanVien WHERE MaNhanVien=?";
    
    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(INSERT_SQL, entity.getMaNhanVien(), entity.getMatKhau(), entity.getHovaTen(),entity.isVaiTro(),entity.getEmail());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(UPDATE_SQL,entity.getMatKhau(), entity.getHovaTen(), entity.isVaiTro(),entity.getEmail(), entity.getMaNhanVien());
    }

    @Override
    public void delete(String key) {
        XJdbc.update(DELETE_SQL, key);
    }

    @Override
    public List<NhanVien> selectAll() {
       return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectByID(String key) {
        List<NhanVien> listNhanVien = this.selectBySql(SELECT_BY_ID, key);
        if (listNhanVien.isEmpty()) {
            return null;
        }
        return listNhanVien.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> listNhanVien = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHovaTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("Vaitro"));
                entity.setEmail(rs.getString("Email"));
                listNhanVien.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listNhanVien;
    }
    
    public static void updatePassword(String passw, String email) {
        String sql = "Update NhanVien set MatKhau = ? where Email = ?";
        XJdbc.update(sql, passw, email);
    }
    
    public NhanVien selectByEmail(String email) {
        String sql = "Select * from NhanVien where Email = ?";
        List<NhanVien> listNhanVien = this.selectBySql(sql, email);
        if (listNhanVien.isEmpty()) {
            return null;
        }
        return listNhanVien.get(0);
    }
}
