package khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.data.DBHelper;

public class SachDAO {
    DBHelper dbHelper;
    private SQLiteDatabase sqLite;

    public SachDAO(Context context) {
        dbHelper = new DBHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Sach> getDataSach(String sql, String... SelectAvg){
        ArrayList<Sach> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_Sach", SelectAvg);
        while (cursor.moveToNext()){
            Sach s = new Sach();
            s.setIdSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Sach_id"))));
            s.setTenSach(cursor.getString(cursor.getColumnIndex("Sach_tenSach")));
            s.setGiaThue(cursor.getString(cursor.getColumnIndex("Sach_giaThue")));
            s.setTenLoai(cursor.getString(cursor.getColumnIndex("loaiSach_tenLoai")));
            list.add(s);
        }
        return list;
    }
    public long insert(Sach s){
        ContentValues values = new ContentValues();
        values.put("Sach_tenSach", s.getTenSach());
        values.put("Sach_giaThue", s.getGiaThue());
        values.put("loaiSach_tenLoai", s.getTenLoai());

        return sqLite.insert("tbl_Sach", null, values);
    }
    public ArrayList<Sach> getAllData(){
        String sql = "SELECT * FROM tbl_phieuMuon";
        return getDataSach(sql);
    }
    public ArrayList<String> name() {
        String name = "SELECT Sach_tenSach FROM tbl_Sach";
        return getName(name);
    }


    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("Sach_tenSach"));
            lst.add(name);
        }
        return lst;

    }
    public long update(Sach s){
        ContentValues values = new ContentValues();

        values.put("Sach_tenSach", s.getTenSach());
        values.put("Sach_giaThue", s.getGiaThue());
        values.put("loaiSach_tenLoai", s.getTenLoai());
        return sqLite.update("tbl_Sach", values, "Sach_id = ?", new String[]{String.valueOf(s.getIdSach())});
    }
    public int delete(int ID) {
        return sqLite.delete("tbl_Sach", "Sach_id = ?", new String[]{String.valueOf(ID)});
    }

}
