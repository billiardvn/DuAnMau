package fpoly.kienpdph44811.duanmau.Spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.Models.LoaiSach;
import fpoly.kienpdph44811.duanmau.R;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;
    TextView maLSspn , tenLSspn;
    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisach_spinner , null);
        }
        final LoaiSach item = list.get(position);
        if (item != null){
            maLSspn = v.findViewById(R.id.maLSspn);
            maLSspn.setText(item.getMaLoai() + ".");

            tenLSspn = v.findViewById(R.id.tenLSspn);
            tenLSspn.setText(item.getTen() + ". ");
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisach_spinner , null);
        }
        final LoaiSach item = list.get(position);
        if (item != null){
            maLSspn = v.findViewById(R.id.maLSspn);
            maLSspn.setText(item.getMaLoai() + ".");

            tenLSspn = v.findViewById(R.id.tenLSspn);
            tenLSspn.setText(item.getTen() + ". ");
        }
        return v;
//        return super.getDropDownView(position, convertView, parent);
    }
}
