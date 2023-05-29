package khanhnqph30151.fptpoly.duanmau.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ThuThuDAO {
    DBHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public boolean checkLogin(String thuThu_id, String thuThu_matKhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_thuThu WHERE thuThu_id = ? AND thuThu_matKhau = ?",
                new String[]{thuThu_id, thuThu_matKhau});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean updatePass(String user, String oPass, String nPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_thuTHu WHERE thuThu_id = ? AND thuThu_matKhau = ?", new String[]{user, oPass});
        if (cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("thuThu_matKhau", nPass);
            long check = sqLiteDatabase.update("tbl_thuThu", values, "thuThu_id = ?", new String[]{user});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
