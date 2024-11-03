package fpoly.kienpdph44811.duanmau.Models;

public class ThanhVien {
    private int MaTV;
    private String HoTen;
    private String NamSinh;

    public ThanhVien(int maTV, String hoTen, String namSinh) {
        MaTV = maTV;
        HoTen = hoTen;
        NamSinh = namSinh;
    }

    public ThanhVien(String hoTen, String namSinh) {
        HoTen = hoTen;
        NamSinh = namSinh;
    }

    public ThanhVien() {

    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int maTV) {
        MaTV = maTV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "MaTV='" + MaTV + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", NamSinh='" + NamSinh + '\'' +
                '}';
    }
}