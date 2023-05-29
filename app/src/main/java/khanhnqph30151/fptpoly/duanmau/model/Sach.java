package khanhnqph30151.fptpoly.duanmau.model;

public class Sach {
    private int idSach;
    private String tenSach;
    private String giaThue;
    private String tenLoai;
    private int soluong;

    public Sach() {
    }

    public Sach(int idSach, String tenSach, String giaThue, String tenLoai) {
        this.idSach = idSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.tenLoai = tenLoai;
    }

    public Sach(String tenSach, String giaThue, String tenLoai) {
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.tenLoai = tenLoai;
    }

    public Sach(String tenSach, int soluong) {
        this.tenSach = tenSach;
        this.soluong = soluong;
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

    public String getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(String giaThue) {
        this.giaThue = giaThue;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
