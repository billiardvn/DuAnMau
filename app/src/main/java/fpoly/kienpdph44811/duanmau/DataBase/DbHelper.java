package fpoly.kienpdph44811.duanmau.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLIB.db";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createThuThu = "create table ThuThu(" +
                "    MaTT text primary key not null ," +
                "    HoTen text not null ," +
                "    MatKhau text not null" +
                ");";
        db.execSQL(createThuThu);

        String createThanhVien = "create table ThanhVien(" +
                "    MaTV integer primary key autoincrement ," +
                "    HoTen text not null ," +
                "    NamSinh text not null " +
                ");";
        db.execSQL(createThanhVien);

        String createLoaiSach = "create table LoaiSach(" +
                "    MaLoai integer primary key autoincrement ," +
                "    Ten text not null" +
                ");";
        db.execSQL(createLoaiSach);

        String createSach = "create table Sach(" +
                "    MaSach INTEGER primary key autoincrement ," +
                "    TenSach text not null ," +
                "    GiaThue integer not null ," +
                "    MaLoai integer not null ," +
                "    foreign key (MaLoai) references LoaiSach(MaLoai)" +
                ");";
        db.execSQL(createSach);
        String createPhieuMuon = "create table PhieuMuon(" +
                "    MaPM integer primary key autoincrement ," +
                "    MaTT text not null ," +
                "    MaTV text not null ," +
                "    MaSach integer not null ," +
                "    Ngay date not null ," +
                "    TraSach integer not null ," +
                "    TienThue integer not null," +
                "    foreign key (MaTT) references ThuThu(MaTT)," +
                "    foreign key (MaTV) references ThanhVien(MaTV)," +
                "    foreign key (MaSach) references Sach(MaSach)" +
                ");";
        db.execSQL(createPhieuMuon);
        //Đoạn này đang lỗi , đọc phần Comment trong Seed
//        db.execSQL(Seed.ThuThuData);
//        db.execSQL(Seed.SachData);
//        db.execSQL(Seed.PhieuMuonData);
//        db.execSQL(Seed.ThanhVienData);
//        db.execSQL(Seed.LoaiSachData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropThuThu = "drop table if exists ThuThu;";
        String dropThanhVien = "drop index if exists ThanhVien;";
        String dropLoaiSach = "drop table if exists LoaiSach;";
        String dropSach = "drop table if exists Sach;";
        String dropPhieuMuon = "drop table if exists PhieuMuon;";
        db.execSQL(dropThuThu);
        db.execSQL(dropThanhVien);
        db.execSQL(dropLoaiSach);
        db.execSQL(dropSach);
        db.execSQL(dropPhieuMuon);

        onCreate(db);
    }
}
