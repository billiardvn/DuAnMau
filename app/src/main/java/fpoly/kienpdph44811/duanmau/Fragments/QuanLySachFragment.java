package fpoly.kienpdph44811.duanmau.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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

import fpoly.kienpdph44811.duanmau.Spinners.LoaiSachSpinnerAdapter;
import fpoly.kienpdph44811.duanmau.Adapter.QuanLySachAdapter;
import fpoly.kienpdph44811.duanmau.DAO.LoaiSachDAO;
import fpoly.kienpdph44811.duanmau.DAO.SachDAO;
import fpoly.kienpdph44811.duanmau.Models.LoaiSach;
import fpoly.kienpdph44811.duanmau.Models.Sach;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLySachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLySachFragment extends Fragment {
    RecyclerView recSach;
    FloatingActionButton fabSach;
    ArrayList<Sach> list;
    LoaiSachDAO lsDao;
    SachDAO dao;
    QuanLySachAdapter adapter;
    LoaiSachSpinnerAdapter loaisachSpinnerAdapter;
    Spinner spLoaiSach;
    ArrayList<LoaiSach> ls;
    LoaiSach lsItem;
    TextView tvMaLS;
    TextInputLayout edTenS, edGiaThue;


    Button btnQLsachAdd;
    int maLoaiSach;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recSach = view.findViewById(R.id.recViewSach);
        fabSach = view.findViewById(R.id.fabSach);
        dao = new SachDAO(getContext());
        list = dao.getAll();
        adapter = new QuanLySachAdapter(getContext(), list);
        recSach.setLayoutManager(new LinearLayoutManager(getContext()));
        recSach.setAdapter(adapter);
        fabSach.setOnClickListener(v -> {
            showDialog(getContext(), 0);
        });
    }

    public void showDialog(Context context, final int type) {
//        type : insert = 0 , update = 1
        //Type cc bỏ mẹ đi , lỗi fix thế lồn nào dc :c
        lsDao = new LoaiSachDAO(getContext());
        ls = lsDao.getAll();
        loaisachSpinnerAdapter = new LoaiSachSpinnerAdapter(getContext(), ls);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.item_ql_sach_dialog, null);
        tvMaLS = v.findViewById(R.id.tvMaLS);
        edTenS = v.findViewById(R.id.edTenS);
        edGiaThue = v.findViewById(R.id.edGiaThue);
        btnQLsachAdd = v.findViewById(R.id.btnQLsachAdd);
        spLoaiSach = v.findViewById(R.id.spLoaiSach);
        tvMaLS.setEnabled(false);

        spLoaiSach.setAdapter(loaisachSpinnerAdapter);
        builder.setView(v);
        builder.setTitle("Thêm Sách");
        AlertDialog alertDialog = builder.create();
        spLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = ls.get(position).getMaLoai();
                Toast.makeText(context, "Chọn :" + ls.get(position).getTen(), Toast.LENGTH_SHORT).show();
                Log.d("TAGml", "onItemSelected: " + maLoaiSach);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnQLsachAdd.setText("Thêm");
        btnQLsachAdd.setOnClickListener(v1 -> {
            Sach item = new Sach();
            item.setTenSach(edTenS.getEditText().getText().toString());
            item.setGiaThue(Integer.parseInt(edGiaThue.getEditText().getText().toString()));
            item.setMaLoai(maLoaiSach); // Set the ID of the selected LoaiSach
            // Set the maSach field to a unique identifier for the book
            // For example, you can generate a unique ID or use some other method to ensure uniqueness
            if (dao.insertSach(item) > 0) {
                list.add(item);
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();

            } else {
                // Handle insertion failure
            }
            reload();

        });


        alertDialog.show();
    }

    public QuanLySachFragment() {
        // Required empty public constructor
    }
    public void reload(){
        QuanLySachFragment fragment = new QuanLySachFragment();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public static QuanLySachFragment newInstance(String param1, String param2) {
        QuanLySachFragment fragment = new QuanLySachFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }
}