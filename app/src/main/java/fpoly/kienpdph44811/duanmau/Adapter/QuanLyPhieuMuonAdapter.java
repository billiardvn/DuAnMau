package fpoly.kienpdph44811.duanmau.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.DAO.PhieuMuonDAO;
import fpoly.kienpdph44811.duanmau.DAO.SachDAO;
import fpoly.kienpdph44811.duanmau.DAO.ThanhVienDAO;
import fpoly.kienpdph44811.duanmau.Models.PhieuMuon;
import fpoly.kienpdph44811.duanmau.Models.Sach;
import fpoly.kienpdph44811.duanmau.Models.ThanhVien;
import fpoly.kienpdph44811.duanmau.R;
import fpoly.kienpdph44811.duanmau.Spinners.SachSpinnerAdapter;
import fpoly.kienpdph44811.duanmau.Spinners.ThanhVienSpinnerAdapter;

public class QuanLyPhieuMuonAdapter  extends RecyclerView.Adapter<QuanLyPhieuMuonAdapter.ViewHolder>{
    ArrayList<PhieuMuon> list;
    Context context;
    PhieuMuonDAO dao;
    ThanhVienDAO TVdao;
    SachDAO Sdao;
    Spinner spnThanhVienPM, spnSachPM;
    EditText edTienThuePM, edDatePM;
    ImageButton btnDatePickPM;
    CheckBox chkReturnBook;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public QuanLyPhieuMuonAdapter(Context context,ArrayList<PhieuMuon> list) {
        this.list = list;
        this.context = context;
        dao= new PhieuMuonDAO(context);
        TVdao = new ThanhVienDAO(context);
        Sdao = new SachDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieunuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuMuon item = list.get(position);
        holder.tvMaPM.setText("Mã phiếu :" + item.getMaPM());
        ThanhVien tv = TVdao.getByID(String.valueOf(item.getMaTV()));
        holder.tvTenTvPm.setText("Thành Viên:" + tv.getHoTen());
        Sach sach = Sdao.getByID(item.getMaSach());
        holder.tvTenSachPM.setText("Sách :" + sach.getTenSach());
        holder.tvGiaPM.setText("Giá Thuê :" +item.getTienThue());
        holder.tvNgayPM.setText("Từ ngày :" + sdf.format(item.getNgay()));
        if (item.getTraSach() == 1){
            holder.tvTrangThaiPM.setTextColor(Color.BLUE);
            holder.tvTrangThaiPM.setText("Đã Trả sách");
        }else {
            holder.tvTrangThaiPM.setTextColor(Color.RED);
            holder.tvTrangThaiPM.setText("Chưa trả sách");
        }
        holder.btnXoaPM.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xoá Phiếu mượn");
            builder.setMessage("Bạn có muốn xoá phiếu mượn này ?");
            builder.setNegativeButton("No" ,(dialog, which) -> {
                // ko làm gì cả
            });
            builder.setPositiveButton("Yes" , (dialog, which) -> {
               dao.deletePhieuMuon(String.valueOf(item.getMaPM()));
               list.remove(item);
               notifyDataSetChanged();
            });
            builder.show();
        });
        holder.itemView.setOnLongClickListener(v->{
            showDialgg(context , position);
            return true;
        });
    }
    public void showDialgg(Context context , int pos){
        PhieuMuon item = list.get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_phieumuon_dialog , null);
        ThanhVienDAO TVdao = new ThanhVienDAO(context);
        SachDAO Sdao = new SachDAO(context);
        ArrayList<ThanhVien> listTV = TVdao.getAll();
        ArrayList<Sach> listSach = Sdao.getAll();
        SachSpinnerAdapter adapterSach = new SachSpinnerAdapter(context, listSach);
        ThanhVienSpinnerAdapter adapterThanhVien = new ThanhVienSpinnerAdapter(context, listTV);
        spnSachPM = v.findViewById(R.id.spnSachPM);
        spnThanhVienPM = v.findViewById(R.id.spnThanhVienPM);
        edTienThuePM = v.findViewById(R.id.edTienThuePM);
        edDatePM = v.findViewById(R.id.edDatePM);
        chkReturnBook = v.findViewById(R.id.chkReturnBook);
        spnSachPM.setAdapter(adapterSach);
        spnThanhVienPM.setAdapter(adapterThanhVien);
        int posSach = 0;
        int posThanhVien = 0;
        for (int i = 0; i < listSach.size(); i++) {
            if (item.getMaSach() == (listSach.get(i).getMaSach())){
                posSach = i;
            }
        }
        for (int i = 0; i < listTV.size(); i++) {
            if (item.getMaTV() == (listTV.get(i).getMaTV())){
                posThanhVien = i;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        spnThanhVienPM.setSelection(posThanhVien);
        spnSachPM.setSelection(posSach);
        edDatePM.setText("Từ ngày :" + sdf.format(item.getNgay()));
        edTienThuePM.setText("Giá thuê : "+ item.getTienThue());
        spnSachPM.setEnabled(false);
        spnThanhVienPM.setEnabled(false);
        edDatePM.setEnabled(false);
        edTienThuePM.setEnabled(false);
        btnDatePickPM = v.findViewById(R.id.btnDatePickPM);
        btnDatePickPM.setEnabled(false);
        if (item.getTraSach() == 1){
            chkReturnBook.setChecked(true);
        }else {
            chkReturnBook.setChecked(false);
        }
        builder.setNegativeButton("No" , (dialog, which) -> {

        });
        builder.setPositiveButton("Yes" ,(dialog, which) -> {
            if (chkReturnBook.isChecked()) {
                item.setTraSach(1);
            } else {
                item.setTraSach(0);
            }
            if (dao.updatePhieuMuon(item) > 0){
                list.set(pos , item);
                notifyDataSetChanged();
            }
        });
        builder.setView(v);
        builder.show();

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPM , tvTenTvPm,tvTenSachPM,tvTrangThaiPM,tvGiaPM,tvNgayPM;
        ImageButton btnXoaPM;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPM = itemView.findViewById(R.id.tvMaPM);
            tvTenTvPm = itemView.findViewById(R.id.tvTenTvPm);
            tvTenSachPM = itemView.findViewById(R.id.tvTenSachPM);
            tvTrangThaiPM = itemView.findViewById(R.id.tvTrangThaiPM);
            tvGiaPM = itemView.findViewById(R.id.tvGiaPM);
            tvNgayPM = itemView.findViewById(R.id.tvNgayPM);

            btnXoaPM = itemView.findViewById(R.id.btnXoaPM);
        }
    }
}
