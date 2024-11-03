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

import fpoly.kienpdph44811.duanmau.Models.ThanhVien;
import fpoly.kienpdph44811.duanmau.R;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> list;
    TextView maTVspn , tenTVspn;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context =context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_thanhvien_spinner , null);

        }
        final ThanhVien tv = list.get(position);
        if (tv != null){
            maTVspn = v.findViewById(R.id.maTVspn);
            maTVspn.setText(tv.getMaTV() +".");
            tenTVspn = v.findViewById(R.id.tenTVspn);
            tenTVspn.setText(tv.getHoTen() + ".");
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_thanhvien_spinner , null);

        }
        final ThanhVien tv = list.get(position);
        if (tv != null){
            maTVspn = v.findViewById(R.id.maTVspn);
            maTVspn.setText(tv.getMaTV() +".");
            tenTVspn = v.findViewById(R.id.tenTVspn);
            tenTVspn.setText(tv.getHoTen() + ".");
        }
        return v;
    }
}
