package khanhnqph30151.fptpoly.duanmau.data;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ThuThuDAO {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    public ThuThuDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("DATA", MODE_PRIVATE);
    }

    public boolean checkLogin(String thuThu_id, String thuThu_matKhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_thuThu WHERE thuThu_id = ? AND thuThu_matKhau = ?",
                new String[]{thuThu_id, thuThu_matKhau});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("thuThu_id", cursor.getString(0));
            editor.putString("thuThu_hoTen", cursor.getString(1));
            editor.putString("thuThu_role", cursor.getString(3));
            editor.commit();
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
