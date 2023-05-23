package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyThanhVien;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.data.DBHelper;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach.Sach;

public class ThanhVienDAO {
    DBHelper dbHelper;
    private SQLiteDatabase sqLite;

    public ThanhVienDAO(Context context){
        dbHelper = new DBHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<ThanhVien> getDataThanhVien(String sql, String... SelectAvg){
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_thanhVien", SelectAvg);
        while (cursor.moveToNext()){
            ThanhVien tv = new ThanhVien();
            tv.setMaThanhVien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("thanhVien_id"))));
            tv.setHoTen(cursor.getString(cursor.getColumnIndex("thanhVien_hoTen")));
            tv.setNamSinh(cursor.getString(cursor.getColumnIndex("thanhVien_namSinh")));
            list.add(tv);
        }
        return list;
    }
    public long insert(ThanhVien tv){
        ContentValues values = new ContentValues();
        values.put("thanhVien_hoTen", tv.getHoTen());
        values.put("thanhVien_namSinh", tv.getNamSinh());

        return sqLite.insert("tbl_thanhVien", null, values);
    }
    public ArrayList<ThanhVien> getAllData(){
        String sql = "SELECT * FROM tbl_thanhVien";
        return getDataThanhVien(sql);
    }
    public ArrayList<String> name() {
        String name = "SELECT thanhVien_hoTen FROM tbl_thanhVien";
        return getName(name);
    }


    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("thanhVien_hoTen"));
            lst.add(name);
        }
        return lst;

    }
}
