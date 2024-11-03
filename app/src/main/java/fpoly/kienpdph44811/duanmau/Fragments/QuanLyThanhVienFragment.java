package fpoly.kienpdph44811.duanmau.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.Adapter.QuanLyThanhVienAdapter;
import fpoly.kienpdph44811.duanmau.DAO.ThanhVienDAO;
import fpoly.kienpdph44811.duanmau.Models.Sach;
import fpoly.kienpdph44811.duanmau.Models.ThanhVien;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyThanhVienFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyThanhVienFragment extends Fragment {
    RecyclerView rec;
    ThanhVienDAO dao;
    ArrayList<ThanhVien> list;
    QuanLyThanhVienAdapter adapter;
    FloatingActionButton fabThanhVien;
    TextInputLayout edQLvNAme;
    TextInputLayout edQLTvNam;
    Sach item;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rec = view.findViewById(R.id.recViewThanhVien);
        dao = new ThanhVienDAO(getContext());
        list = dao.getAll();
        fabThanhVien = view.findViewById(R.id.fabThanhVien);
        Log.d("LOGDAO", "onViewCreated: " + list.toString());
        adapter = new QuanLyThanhVienAdapter(getContext(), list);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setAdapter(adapter);
        if (fabThanhVien != null){

        }else {
            Log.d("Daaaaaal","suck");
        }
        fabThanhVien.setOnClickListener(v -> {
            ShowAddDialog(getContext());
        });

    }

    private void ShowAddDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.item_ql_thanhvien_dialog, null);
        edQLvNAme = v.findViewById(R.id.edQLvNAme);
        edQLTvNam = v.findViewById(R.id.edQLTvNam);
        builder.setView(v);
        AlertDialog dialogq = builder.create();
        builder.setTitle("Thêm Thành Viên");

        builder.setNegativeButton("No", ((dialog, which) -> {

        }));
        builder.setPositiveButton("Yes", ((dialog, which) -> {
            if (valid(context) == 0) {
                String name = edQLvNAme.getEditText().getText().toString();
                String namSinh = edQLTvNam.getEditText().getText().toString();
                ThanhVien tv = new ThanhVien(name, namSinh);
                if (dao.insertThanhVien(tv) > 0) {
                    list.add(tv);
                    adapter.notifyDataSetChanged();
                    reloadFragment();
                    Toast.makeText(context, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

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

    public void reloadFragment() {
        QuanLyThanhVienFragment fragment = new QuanLyThanhVienFragment();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public QuanLyThanhVienFragment() {
        // Required empty public constructor
    }

    public static QuanLyThanhVienFragment newInstance() {
        QuanLyThanhVienFragment fragment = new QuanLyThanhVienFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
    }
}