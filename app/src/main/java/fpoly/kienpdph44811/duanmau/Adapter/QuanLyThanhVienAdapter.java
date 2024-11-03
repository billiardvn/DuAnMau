package fpoly.kienpdph44811.duanmau.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.DAO.ThanhVienDAO;
import fpoly.kienpdph44811.duanmau.Models.ThanhVien;
import fpoly.kienpdph44811.duanmau.R;

public class QuanLyThanhVienAdapter extends RecyclerView.Adapter<QuanLyThanhVienAdapter.ViewHolder> {
    ArrayList<ThanhVien> list;
    Context context;
    ThanhVienDAO dao;
    TextInputLayout edQLvNAme;
    TextInputLayout edQLTvNam;

    public QuanLyThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.list = list;
        this.context = context;
        this.dao = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ql_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhVien tv = list.get(position);

        holder.tvNsTV.setText("Năm Sinh: " + tv.getNamSinh());
        holder.tvTenTV.setText("Họ Tên: " + tv.getHoTen());
        holder.tvMaTV.setText("Mã Tv: " + tv.getMaTV());
        holder.btnXoaTV.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xoá Thành Viên");
            builder.setMessage("Bạn có muốn xoá thành viên này ?");
            builder.setNegativeButton("No" ,((dialog, which) -> {
                //Tự động đóng dialog
                //Không thực hiện gì cả
            }));
            builder.setPositiveButton("Yes" , ((dialog, which) -> {
                if (dao.removeThanhVien(tv) > 0){
                    list.remove(tv);
                    notifyDataSetChanged();
                }
            }));
            builder.show();
        });
        holder.itemView.setOnLongClickListener(v -> {
            ShowUpdateDialog(context,position);
            return true;
        });
    }
    private void ShowUpdateDialog(Context context ,int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_ql_thanhvien_dialog, null);
         edQLvNAme = v.findViewById(R.id.edQLvNAme);
         edQLTvNam = v.findViewById(R.id.edQLTvNam);
        ThanhVien df = list.get(pos);
        edQLvNAme.getEditText().setText(df.getHoTen());
        edQLTvNam.getEditText().setText(df.getNamSinh());
        builder.setView(v);
        AlertDialog dialogq = builder.create();
        builder.setTitle("Sửa Thành Viên");

        builder.setNegativeButton("No", ((dialog, which) -> {

        }));
        builder.setPositiveButton("Yes", ((dialog, which) -> {
            if (valid(context) == 0) {
                String name = edQLvNAme.getEditText().getText().toString();
                String namSinh = edQLTvNam.getEditText().getText().toString();
                ThanhVien tv = new ThanhVien(df.getMaTV(),name, namSinh);
                if (dao.updateThanhVien(tv) > 0) {
                    Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

                list.set(pos,tv);
                notifyDataSetChanged();
            }

        }));
        builder.show();
    }

    public int valid(Context context) {
        String name = edQLvNAme.getEditText().getText().toString();
        String namSinh = edQLTvNam.getEditText().getText().toString();
        Boolean isParse = true;
        try {
            Integer.parseInt(namSinh);
        } catch (Exception e) {
            isParse = false;
        }
        if (name.equals("") || namSinh.equals("")) {
            Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return 1;
        } else if (!isParse) {
            Toast.makeText(context, "Vui lòng nhập đúng năm sinh", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTV, tvTenTV, tvNsTV;
        ImageButton btnXoaTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.tvMaTV);
            tvTenTV = itemView.findViewById(R.id.tvTenTV);
            tvNsTV = itemView.findViewById(R.id.tvNsTV);

            btnXoaTV = itemView.findViewById(R.id.btnXoaTV);
        }
    }
}
