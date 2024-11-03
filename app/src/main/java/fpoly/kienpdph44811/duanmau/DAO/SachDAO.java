package fpoly.kienpdph44811.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.DataBase.DbHelper;
import fpoly.kienpdph44811.duanmau.Models.Sach;

public class SachDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        this.dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("GiaThue", sach.getGiaThue());
        values.put("MaLoai", sach.getMaLoai());
        return db.insert("Sach", null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Sach> getData(String sql, String... selectionArgs) {
        ArrayList<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaSach"))));
            obj.setTenSach(cursor.getString(cursor.getColumnIndex("TenSach")));
            obj.setGiaThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("GiaThue"))));
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaLoai"))));
            list.add(obj);
        }
        return list;
    }

    public ArrayList<Sach> getAll() {
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    @SuppressLint("Range")
    public Sach getByID(int idS) {
        String id = String.valueOf(idS);
        Cursor cursor = db.rawQuery("SELECT * FROM Sach WHERE MaSach = ?", new String[]{id});
        Sach item = new Sach();
        if (cursor.moveToFirst()) {
            item.setMaSach(cursor.getInt(cursor.getColumnIndex("MaSach")));
            item.setTenSach(cursor.getString(cursor.getColumnIndex("TenSach")));
            item.setGiaThue(cursor.getInt(cursor.getColumnIndex("GiaThue")));
            item.setMaLoai(cursor.getInt(cursor.getColumnIndex("MaLoai")));
        }
        cursor.close();
        return item;
    }


    public int removeByID(Sach sach) {
        String[] id = new String[]{String.valueOf(sach.getMaSach())};
        return db.delete("Sach", "MaSach = ?", id);
    }

    public int updateSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("TenSach" , sach.getTenSach());
        values.put("GiaThue",sach.getGiaThue());
        values.put("MaLoai",sach.getMaLoai());
        return db.update("Sach", values, "MaSach = ?", new String[]{String.valueOf(sach.getMaSach())});
    }
    ;
//    public int removeThanhVien(ThanhVien thanhVien) {
//        String[] id = new String[]{String.valueOf(thanhVien.getMaTV())};
//        return db.delete("ThanhVien", "MaTV = ?", id);
//    }
}
