package khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach;

public class Sach {
    private int idSach;
    private String tenSach;
    private String giaTien;
    private int idLoai;

    public Sach() {
    }

    public Sach(int idSach, String tenSach, String giaTien, int idLoai) {
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.giaTien = giaTien;
        this.idLoai = idLoai;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }
}
