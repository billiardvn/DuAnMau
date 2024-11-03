package fpoly.kienpdph44811.duanmau.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.Adapter.QuanLyLoaiSachAdapter;
import fpoly.kienpdph44811.duanmau.DAO.LoaiSachDAO;
import fpoly.kienpdph44811.duanmau.Models.LoaiSach;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyLoaiSachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyLoaiSachFragment extends Fragment {
    RecyclerView recViewLoaiSach;
    FloatingActionButton fabLoaiSach;
    LoaiSachDAO dao;
    QuanLyLoaiSachAdapter adapter;
    ArrayList<LoaiSach> list;
    TextInputLayout   edTenLS;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recViewLoaiSach = view.findViewById(R.id.recViewLoaiSach);
        fabLoaiSach = view.findViewById(R.id.fabLoaiSach);

        dao = new LoaiSachDAO(getContext());
        list = dao.getAll();
        adapter = new QuanLyLoaiSachAdapter(getContext() , list);

        recViewLoaiSach.setLayoutManager(new LinearLayoutManager(getContext()));
        recViewLoaiSach.setAdapter(adapter);
        fabLoaiSach.setOnClickListener(v ->{
            showDialog(getContext());

        });
    }
    public void showDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach_dialog , null);

        edTenLS = view.findViewById(R.id.edTenLS);
        builder.setTitle("Thêm loại sách");
        builder.setNegativeButton("No" , (dialog, which) -> {
            //Không làm gì cả
        });
        builder.setPositiveButton("Yes" , (dialog, which) -> {
            String TenLS = edTenLS.getEditText().getText().toString();
            LoaiSach item = new LoaiSach(TenLS);
            item.setTen(TenLS);
            list.add(item);
            dao.insertloaiSach(item);
            adapter.notifyDataSetChanged();
            reloadFragment();
        });

        builder.setView(view);
        builder.show();
    }
    public void reloadFragment() {
        QuanLyLoaiSachFragment fragment = new QuanLyLoaiSachFragment();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    public QuanLyLoaiSachFragment() {
        // Required empty public constructor
    }


    public static QuanLyLoaiSachFragment newInstance() {
        QuanLyLoaiSachFragment fragment = new QuanLyLoaiSachFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
    }
}