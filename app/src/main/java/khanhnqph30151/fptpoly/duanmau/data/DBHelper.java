package khanhnqph30151.fptpoly.duanmau.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "phuongnamlib.db";
    private static final int DB_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static final String TABLE_THU_THU_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_thuThu (" +
            "thuThu_id TEXT PRIMARY KEY , " +
            "thuThu_hoTen TEXT NOT NULL," +
            "thuThu_matKhau TEXT NOT NULL" +
            ")";
    public static final String TABLE_THANH_VIEN_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_thanhVien (" +
            "thanhVien_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "thanhVien_hoTen TEXT NOT NULL," +
            "thanhVien_namSinh TEXT NOT NULL" +
            ")";
    public static final String TABLE_LOAI_SACH_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_loaiSach (" +
            "loaiSach_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "loaiSach_tenLoai TEXT NOT NULL" +
            ")";
    public static final String TABLE_SACH_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_Sach (" +
            "Sach_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Sach_tenSach TEXT NOT NULL," +
            "Sach_giaTien TEXT NOT NULL," +
            "loaiSach_id INTEGER REFERENCES tbl_loaiSach(loaiSach_id)" +
            ")";
    public static final String TABLE_PHIEU_MUON_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_phieuMuon (" +
            "phieuMuon_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "thanhVien_hoTen INTEGER REFERENCES tbl_thanhVien(thanhVien_hoTen)," +
            "thuThu_id TEXT REFERENCES tbl_thuThu(thuThu_id)," +
            "Sach_tenSach INTEGER REFERENCES tbl_Sach(Sach_tenSach)," +
            "phieuMuon_ngay TEXT NOT NULL," +
            "phieuMuon_tienThue TEXT ," +
            "phieuMuon_trangThai TEXT " +
            ")";



    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("DBHelper", "vao oncreate");

        //Thu Thu
        db.execSQL(TABLE_THU_THU_CREATE);
        //Thanh Vien
        db.execSQL(TABLE_THANH_VIEN_CREATE);
        //Loai Sach
        db.execSQL(TABLE_LOAI_SACH_CREATE);
        //Sach
        db.execSQL(TABLE_SACH_CREATE);
        //Phieu Muon
        db.execSQL(TABLE_PHIEU_MUON_CREATE);

        db.execSQL("INSERT INTO tbl_loaiSach VALUES (1, 'Thiếu nhi'),(2,'Tình cảm'),(3, 'Giáo khoa')");
        db.execSQL("INSERT INTO tbl_Sach VALUES (1, 'Hãy đợi đấy', 2500, 1), (2, 'Thằng cuội', 1000, 1), (3, 'Lập trình Android', 2000, 3)");
        db.execSQL("INSERT INTO tbl_thuThu VALUES ('admin','Nguyễn Văn Anh','123123'),('mod','Trần Văn Hùng','123123')");
        db.execSQL("INSERT INTO tbl_thanhVien VALUES (1,'Cao Thu Trang','2000'),(2,'Trần Mỹ Kim','2000')");
        //trả sách: 1: đã trả - 0: chưa trả

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS tbl_thuThu");
            db.execSQL("DROP TABLE IF EXISTS tbl_thanhVien");
            db.execSQL("DROP TABLE IF EXISTS tbl_loaiSach");
            db.execSQL("DROP TABLE IF EXISTS tbl_Sach");
            db.execSQL("DROP TABLE IF EXISTS tbl_phieuMuon");
            onCreate(db);
        }

    }
}
