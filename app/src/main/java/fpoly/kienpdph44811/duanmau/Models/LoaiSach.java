package fpoly.kienpdph44811.duanmau.Models;

public class LoaiSach {
    private int MaLoai;
    private String Ten ;

    public LoaiSach(int maLoai, String ten) {
        MaLoai = maLoai;
        Ten = ten;
    }

    public LoaiSach(String ten) {
        Ten = ten;
    }

    public LoaiSach() {
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    @Override
    public String toString() {
        return "LoaiSach{" +
                "MaLoai=" + MaLoai +
                ", Ten='" + Ten + '\'' +
                '}';
    }
}
