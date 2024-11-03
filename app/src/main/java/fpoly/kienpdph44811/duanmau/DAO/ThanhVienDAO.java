package fpoly.kienpdph44811.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.DataBase.DbHelper;
import fpoly.kienpdph44811.duanmau.Models.ThanhVien;

public class ThanhVienDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        this.dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertThanhVien(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("HoTen", thanhVien.getHoTen());
        values.put("NamSinh", thanhVien.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }

    @SuppressLint("Range")
    public ArrayList<ThanhVien> getData(String sql, String... selectionArgs) {
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaTV"))));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("HoTen")));
            obj.setNamSinh(cursor.getString(cursor.getColumnIndex("NamSinh")));
            list.add(obj);
        }
        return list;
    }

    public ArrayList<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getByID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE MaTV=?";
        ArrayList<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }

    public int removeThanhVien(ThanhVien thanhVien) {
        String[] id = new String[]{String.valueOf(thanhVien.getMaTV())};
        return db.delete("ThanhVien", "MaTV = ?", id);
    }

    public int updateThanhVien(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("HoTen", thanhVien.getHoTen());
        values.put("NamSinh", thanhVien.getNamSinh());
        return db.update("ThanhVien", values, "MaTV = ?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }
}
