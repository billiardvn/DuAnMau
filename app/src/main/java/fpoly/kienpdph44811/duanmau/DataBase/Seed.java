package fpoly.kienpdph44811.duanmau.DataBase;

public class Seed {


    // Data mẫu (Lười nên viết ít :v)
    // Đoạn Data này đang lỗi nên query tay đi :))))
    // Mô tả lỗi : các dòng query không hoạt động , data không dc thêm vào trong data base
    // Khắc phục tạm thời : query thủ công bằng App Inspection
    public static final String LoaiSachData = "insert into LoaiSach (Ten) values " +
            "('Lập Trình Web')," +
            "('Lập Trình Mobile')," +
            "('Lập Trình Unity2D')," +
            "('Lập Trình Unity3D');";
    public static final String PhieuMuonData = "insert into PhieuMuon (MaTT, MaTV, MaSach, Ngay, TraSach, TienThue) values " +
            "(1, 1, 1, '2024-02-09', 0, 0)," +
            "(2, 2, 2, '2024-02-08', 1, 15000);";
    public static final String SachData = "insert into Sach (TenSach, GiaThue, MaLoai) values " +
            "('HTML cơ bản', 50000, 1)," +
            "('Flutter cơ bản', 75000, 2)," +
            "('ReactJS cơ bản', 45000, 1);";
    public static final String ThanhVienData = "insert into ThanhVien (HoTen, NamSinh) values " +
            "('Le Van C', '1990-05-15')," +
            "('Pham Thi D', '1985-10-20');";
    public static final String ThuThuData =
            "insert into ThuThu (MaTT, HoTen, MatKhau) values" +
                    "('admin', 'admin', 'admin');";

}
