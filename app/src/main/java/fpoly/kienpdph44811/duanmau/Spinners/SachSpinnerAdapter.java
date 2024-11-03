package fpoly.kienpdph44811.duanmau.Spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.Models.Sach;
import fpoly.kienpdph44811.duanmau.R;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    Context context ;
    ArrayList<Sach> list;
    TextView maSspn , tenSspn;
    public SachSpinnerAdapter(@NotNull Context context , ArrayList<Sach> list){
        super(context , 0 , list);
        this.context = context ;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sach_spinner , null);
        }
        final Sach sach = list.get(position);
        if (sach != null){
            maSspn = v.findViewById(R.id.maSspn);
            maSspn.setText(sach.getMaSach() + ".");
            tenSspn = v.findViewById(R.id.tenSspn);
            tenSspn.setText(sach.getTenSach() +".");

        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sach_spinner , null);
        }
        final Sach sach = list.get(position);
        if (sach != null){
            maSspn = v.findViewById(R.id.maSspn);
            maSspn.setText(sach.getMaSach() + ".");
            tenSspn = v.findViewById(R.id.tenSspn);
            tenSspn.setText(sach.getTenSach() +".");

        }
        return v;
    }
}

