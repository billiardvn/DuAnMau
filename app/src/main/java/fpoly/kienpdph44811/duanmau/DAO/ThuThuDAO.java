package fpoly.kienpdph44811.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.kienpdph44811.duanmau.DataBase.DbHelper;
import fpoly.kienpdph44811.duanmau.Models.ThuThu;

public class ThuThuDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        this.dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertThuThu(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("MaTT", thuThu.getMaTT());
        values.put("HoTen", thuThu.getHoTen());
        values.put("MatKhau", thuThu.getMatKhau());
        return db.insert("ThuThu", null, values);
    }

    public long updatePass(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("HoTen", obj.getHoTen());
        values.put("MatKhau", obj.getMatKhau());
        return db.update("ThuThu", values, "MaTT=?", new String[]{obj.getMaTT()});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "MaTT=?", new String[]{id});
    }

    @SuppressLint("Range")
    public ArrayList<ThuThu> getData(String sql, String... selectionArgs) {
        ArrayList<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThuThu obj = new ThuThu();
            obj.setMaTT(cursor.getString(cursor.getColumnIndex("MaTT")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("HoTen")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("MatKhau")));
            list.add(obj);
        }
        return list;
    }
    public ArrayList<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }
    public ThuThu getByID(String id){
        String sql = "SELECT * FROM ThuThu WHERE MaTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    public int checkLogin(String id , String pass){
        String sql = "SELECT * FROM ThuThu WHERE MaTT=? AND MatKhau=?";
        List<ThuThu> list = getData(sql,id,pass);
        if (list.size() == 0){
            return 0;
        }
        return 1;
    }

}
