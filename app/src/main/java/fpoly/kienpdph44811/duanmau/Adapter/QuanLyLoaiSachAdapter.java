package fpoly.kienpdph44811.duanmau.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.DAO.LoaiSachDAO;
import fpoly.kienpdph44811.duanmau.Models.LoaiSach;
import fpoly.kienpdph44811.duanmau.R;

public class QuanLyLoaiSachAdapter extends RecyclerView.Adapter<QuanLyLoaiSachAdapter.ViewHolder> {
    ArrayList<LoaiSach> list;
    Context context ;
    LoaiSachDAO dao;
    TextInputLayout edTenLS;

    public QuanLyLoaiSachAdapter(Context context,ArrayList<LoaiSach> list ) {
        this.list = list;
        this.context = context;
        dao = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiSach item  = list.get(position);
        holder.tvTenItemSach.setText("Tên : "+item.getTen());
        holder.tvMaItemSach.setText("Mã Loại Sách : "+String.valueOf(item.getMaLoai()));
        holder.btnXoaLS.setOnClickListener(v ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xoá Loại Sách ");
            builder.setMessage("Bạn có muốn xoá loại sách này ko");
            builder.setNegativeButton("No" , (dialog, which) -> {
               //Không làm gì cả
            });
            builder.setPositiveButton("Yes" , (dialog, which) -> {
                dao.removeLoaiSach(item);
                list.remove(item);
                notifyDataSetChanged();
            });
            builder.show();
        });
        holder.itemView.setOnLongClickListener(v -> {
             showDialog(context , position);
            return true;
        });
    }
    public void showDialog(Context context , int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_loaisach_dialog, null);
        edTenLS = view.findViewById(R.id.edTenLS);
        LoaiSach item = list.get(pos);
        builder.setView(view);
        builder.setTitle("Cập nhật Loại Sách");
        edTenLS.getEditText().setText(item.getTen());
        builder.setNegativeButton("No" , (dialog, which) -> {

        });
        builder.setPositiveButton("Yes" , (dialog, which) -> {
            item.setTen(edTenLS.getEditText().getText().toString());
           dao.updateLoaiSach(item);
           list.set(pos,item);
           notifyDataSetChanged();
        });
        builder.show();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaItemSach,tvTenItemSach;
        ImageButton btnXoaLS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaItemSach = itemView.findViewById(R.id.tvMaItemSach);
            tvTenItemSach = itemView.findViewById(R.id.tvTenItemSach);
            btnXoaLS = itemView.findViewById(R.id.btnXoaLS);
        }
    }
}
