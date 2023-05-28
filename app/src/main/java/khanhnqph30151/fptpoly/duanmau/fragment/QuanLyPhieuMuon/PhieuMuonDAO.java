package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyPhieuMuon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.data.DBHelper;

public class PhieuMuonDAO {

    DBHelper dbHelper;
    private SQLiteDatabase sqLite;
    public PhieuMuonDAO(Context context){
        dbHelper = new DBHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<PhieuMuon> getDataPhieuMuon(String sql, String... SelectAvg){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_phieuMuon", SelectAvg);
        while (cursor.moveToNext()){
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieuMuon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("phieuMuon_id"))));
                pm.setTenThanhVien(cursor.getString(cursor.getColumnIndex("thanhVien_hoTen")));
                pm.setMaThuThu(cursor.getString(cursor.getColumnIndex("thuThu_id")));
                pm.setTenSach(cursor.getString(cursor.getColumnIndex("Sach_tenSach")));
                pm.setNgayMuon(cursor.getString(cursor.getColumnIndex("phieuMuon_ngay")));
                pm.setGiaThue(cursor.getString(cursor.getColumnIndex("Sach_giaThue")));
                pm.setTrangThai((cursor.getString(cursor.getColumnIndex("phieuMuon_trangThai"))));
                list.add(pm);
        }
        return list;
    }
    public long insert(PhieuMuon pm) {
        ContentValues values = new ContentValues();
        values.put("thanhVien_hoTen", pm.getTenThanhVien());
        values.put("Sach_tenSach", pm.getTenSach());
        values.put("Sach_giaThue", pm.getGiaThue());
        values.put("phieuMuon_ngay", pm.getNgayMuon());
        values.put("phieuMuon_trangThai", pm.getTrangThai());

        return sqLite.insert("tbl_phieuMuon", null, values);
    }
    public ArrayList<PhieuMuon> getAllData() {
        String sql = "SELECT * FROM tbl_phieuMuon";
        return getDataPhieuMuon(sql);
    }
}
