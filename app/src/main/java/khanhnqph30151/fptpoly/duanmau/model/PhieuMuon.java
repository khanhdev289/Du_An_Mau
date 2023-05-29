package khanhnqph30151.fptpoly.duanmau.model;

public class PhieuMuon {
    private int maPhieuMuon;
    private String tenThanhVien;
    private String maThuThu;
    private String tenSach;
    private String ngayMuon;
    private String giaThue;
    private String trangThai;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPhieuMuon, String tenThanhVien, String maThuThu, String tenSach, String ngayMuon, String giaThue, String trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.tenThanhVien = tenThanhVien;
        this.maThuThu = maThuThu;
        this.tenSach = tenSach;
        this.ngayMuon = ngayMuon;
        this.giaThue = giaThue;
        this.trangThai = trangThai;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(String giaThue) {
        this.giaThue = giaThue;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}

