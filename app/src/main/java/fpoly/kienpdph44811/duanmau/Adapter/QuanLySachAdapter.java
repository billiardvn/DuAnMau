package fpoly.kienpdph44811.duanmau.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.DAO.LoaiSachDAO;
import fpoly.kienpdph44811.duanmau.DAO.SachDAO;
import fpoly.kienpdph44811.duanmau.Models.LoaiSach;
import fpoly.kienpdph44811.duanmau.Models.Sach;
import fpoly.kienpdph44811.duanmau.R;
import fpoly.kienpdph44811.duanmau.Spinners.LoaiSachSpinnerAdapter;

public class QuanLySachAdapter extends RecyclerView.Adapter<QuanLySachAdapter.ViewHolder> {
    ArrayList<Sach> list;
    Context context;
    SachDAO dao;
    TextView tvMaLS;
    TextInputLayout edTenS, edGiaThue;
    Spinner spLoaiSach;
    LoaiSachSpinnerAdapter loaiSachSpinnerAdapter;
    LoaiSachDAO lsDao;
    ArrayList<LoaiSach> listLS;
    Button btnQLsachAdd;

    int maLS;

    public QuanLySachAdapter(Context context, ArrayList<Sach> list) {
        this.list = list;
        this.context = context;
        this.dao = new SachDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ql_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach = list.get(position);
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
        LoaiSach loaiSach = loaiSachDAO.getByID(String.valueOf(sach.getMaLoai()));

        holder.tvMaS.setText("Mã Sách :" + sach.getMaSach());
        holder.tvTenS.setText("Tên Sách : " + sach.getTenSach());
        holder.tvGiaS.setText("Giá : " + sach.getGiaThue());

        if (loaiSach != null) {
            holder.tvLoaiS.setText("Loại Sách : " + loaiSach.getTen());
        } else {
            holder.tvLoaiS.setText("Loại Sách : N/A");
        }
        holder.itemView.setOnLongClickListener(v -> {
            showDialog(context, position);
            return true;
        });

        holder.btnXoaS.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xoá Sách");
            builder.setMessage("Bạn có muốn Xoá Sách này");
            builder.setNegativeButton("No", (dialog, which) -> {
                // Handle negative button click
            });
            builder.setPositiveButton("Yes", (dialog, which) -> {
                if (dao.removeByID(sach) > 0) {
                    list.remove(sach);
                    notifyDataSetChanged();

                    Log.d("QuanLySachAdapter", "Item removed successfully");
                } else {
                    // Handle removal failure
                    Log.d("QuanLySachAdapter", "Failed to remove item");
                }
            });
            builder.show();
        });
    }

    public void showDialog(Context context, int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ql_sach_dialog, null);
        spLoaiSach = view.findViewById(R.id.spLoaiSach);
        edTenS = view.findViewById(R.id.edTenS);
        edGiaThue = view.findViewById(R.id.edGiaThue);
        lsDao = new LoaiSachDAO(context);
        tvMaLS = view.findViewById(R.id.tvMaLS);
        listLS = lsDao.getAll();
        loaiSachSpinnerAdapter = new LoaiSachSpinnerAdapter(context, listLS);
        Sach sach = list.get(pos);
        tvMaLS.setText("Mã Sách : " + String.valueOf(sach.getMaSach()));
        edTenS.getEditText().setText(sach.getTenSach());
        edGiaThue.getEditText().setText(String.valueOf(sach.getGiaThue()));
        spLoaiSach.setAdapter(loaiSachSpinnerAdapter);
        builder.setView(view);
        builder.setTitle("Sửa Sách");
        int lsPos = 0;
        for (int i = 0; i < listLS.size(); i++) {
            if (sach.getMaLoai() == (listLS.get(i).getMaLoai())) {
                lsPos = i;
            }
        }

        spLoaiSach.setSelection(lsPos);
        btnQLsachAdd = view.findViewById(R.id.btnQLsachAdd);
        btnQLsachAdd.setEnabled(false);
        spLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLS = listLS.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setPositiveButton("Yes", (dialog, which) ->
        {


            Sach item = new Sach(
                    sach.getMaSach(),
                    edTenS.getEditText().getText().toString(),
                    Integer.parseInt(edGiaThue.getEditText().getText().toString()),
                    maLS);
            dao.updateSach(item);
            list.set(pos, item);
            notifyDataSetChanged();
        });

        builder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaS, tvTenS, tvGiaS, tvLoaiS;
        ImageButton btnXoaS;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaS = itemView.findViewById(R.id.tvMaS);
            tvTenS = itemView.findViewById(R.id.tvTenS);
            tvGiaS = itemView.findViewById(R.id.tvGiaS);
            tvLoaiS = itemView.findViewById(R.id.tvLoaiS);

            btnXoaS = itemView.findViewById(R.id.btnXoaS);
        }
    }
}
