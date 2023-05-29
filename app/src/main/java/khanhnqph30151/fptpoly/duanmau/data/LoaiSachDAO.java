package khanhnqph30151.fptpoly.duanmau.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.model.LoaiSach;

public class LoaiSachDAO {
    DBHelper dbHelper;
    private SQLiteDatabase sqLite;
    public LoaiSachDAO(Context context){
        dbHelper = new DBHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<LoaiSach> getDataLoaiSach(String sql, String... SelectAvg){
        ArrayList<LoaiSach> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_loaiSach", SelectAvg);
        while (cursor.moveToNext()){
            LoaiSach ls = new LoaiSach();
            ls.setMaLoaiSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("loaiSach_id"))));
            ls.setTenLoaiSach(cursor.getString(cursor.getColumnIndex("loaiSach_tenLoai")));
            list.add(ls);
        }
        return list;
    }
    public long insert(LoaiSach ls){
        ContentValues values = new ContentValues();
        values.put("loaiSach_tenLoai", ls.getTenLoaiSach());
        return sqLite.insert("tbl_loaiSach", null, values);
    }
    public ArrayList<LoaiSach> getAllData() {
        String sql = "SELECT * FROM tbl_loaiSach";
        return getDataLoaiSach(sql);
    }
    public ArrayList<String> name() {
        String name = "SELECT loaiSach_tenLoai FROM tbl_loaiSach";
        return getName(name);
    }


    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("loaiSach_tenLoai"));
            lst.add(name);
        }
        return lst;

    }
    public long update(LoaiSach ls){
        ContentValues values = new ContentValues();
        values.put("loaiSach_id", ls.getMaLoaiSach());
        values.put("loaiSach_tenLoai", ls.getTenLoaiSach());

        return sqLite.update("tbl_loaiSach", values, "loaiSach_id = ?", new String[]{String.valueOf(ls.getMaLoaiSach())});
    }
    public int delete(int ID) {
        return sqLite.delete("tbl_loaiSach", "loaiSach_id = ?", new String[]{String.valueOf(ID)});
    }

}
