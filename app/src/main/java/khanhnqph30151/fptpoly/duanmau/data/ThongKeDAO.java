package khanhnqph30151.fptpoly.duanmau.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import khanhnqph30151.fptpoly.duanmau.model.Sach;

public class ThongKeDAO {
    DBHelper dbHelper;
    private SQLiteDatabase sqLite;
    private Context context;

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
//    public ArrayList<TopBook> Top10Sach(){
//        String sqlTop = "SELECT Sach_tenSach,count(Sach_tenSach) as soluong FROM tbl_phieuMuon GROUP BY Sach_tenSach ORDER BY soluong DESC LIMIT 10";
//        ArrayList<TopBook> list = new ArrayList<>();
//        SachDAO sachDao = new SachDAO(context);
//        Cursor cursor = sqLite.rawQuery(sqlTop, null);
//        while (cursor.moveToNext()){
//            TopBook top = new TopBook();
//            Sach sach = sachDao.getAllData().get(cursor.getColumnIndex("Sach_tenSach"));
//            top.setTenSach(sach.getTenSach());
//            top.setSoLuong((cursor.getColumnIndex("soluong")));
//            list.add(top);
//        }
//        return list;
//    }
    public ArrayList<Sach> getTop10MuonSach() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Sach_tenSach,count(Sach_tenSach) as soluong FROM tbl_phieuMuon GROUP BY Sach_tenSach ORDER BY soluong DESC LIMIT 10", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getString(0), cursor.getInt(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(Sach_giaThue) as doanhThu FROM tbl_phieuMuon WHERE phieuMuon_ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }

        }
        return list.get(0);
    }
}
