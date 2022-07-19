/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduPoLy.DAO;

import com.EduPoLy.Entity.NhanVien;
import com.EduPoLy.utils.XJDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUY PHUONG
 */
public class NhanVienDAO extends EduPolyDAO<NhanVien, String> {

    String INSERT_SQL = "insert into NHANVIEN(MANV,MATKHAU,HOTEN,VAITRO) values(?,?,?,?)";
    String UPDATE_SQL = "update NHANVIEN set MATKHAU=?,HOTEN=?,VAITRO=? where MANV=?";
    String DELETE_SQL = "delete from NHANVIEN where MANV=?";
    String SELECT_ALL_SQL = "select * from NHANVIEN";
    String SELECT_BY_ID_SQL = "select * from NHANVIEN where MANV=?";

    @Override
    public void insert(NhanVien entity) {
        XJDBC.update(INSERT_SQL,
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getVaiTro(),
                entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString(1));
                entity.setMatKhau(rs.getString(2));
                entity.setHoTen(rs.getString(3));
                entity.setVaiTro(rs.getBoolean(4));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
