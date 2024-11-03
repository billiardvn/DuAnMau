package fpoly.kienpdph44811.duanmau.Models;

import java.util.Date;

public class PhieuMuon {
    private int maPM;
    private int maTT;
    private int maTV;
    private int MaSach;
    private Date Ngay;
    private int TraSach;
    private int TienThue;

    public PhieuMuon(int maPM, int maTT, int maTV, int maSach, Date ngay, int traSach, int tienThue) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        MaSach = maSach;
        Ngay = ngay;
        TraSach = traSach;
        TienThue = tienThue;
    }

    public PhieuMuon(int maTT, int maTV, int maSach, Date ngay, int traSach, int tienThue) {
        this.maTT = maTT;
        this.maTV = maTV;
        MaSach = maSach;
        Ngay = ngay;
        TraSach = traSach;
        TienThue = tienThue;
    }

    public PhieuMuon() {

    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public int getTraSach() {
        return TraSach;
    }

    public void setTraSach(int traSach) {
        TraSach = traSach;
    }

    public int getTienThue() {
        return TienThue;
    }

    public void setTienThue(int tienThue) {
        TienThue = tienThue;
    }
}
