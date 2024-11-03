package fpoly.kienpdph44811.duanmau.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.kienpdph44811.duanmau.Adapter.QuanLyPhieuMuonAdapter;
import fpoly.kienpdph44811.duanmau.DAO.PhieuMuonDAO;
import fpoly.kienpdph44811.duanmau.DAO.SachDAO;
import fpoly.kienpdph44811.duanmau.DAO.ThanhVienDAO;
import fpoly.kienpdph44811.duanmau.Models.PhieuMuon;
import fpoly.kienpdph44811.duanmau.Models.Sach;
import fpoly.kienpdph44811.duanmau.Models.ThanhVien;
import fpoly.kienpdph44811.duanmau.R;
import fpoly.kienpdph44811.duanmau.Spinners.SachSpinnerAdapter;
import fpoly.kienpdph44811.duanmau.Spinners.ThanhVienSpinnerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyPhieuMuonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyPhieuMuonFragment extends Fragment {
    RecyclerView recViewPhieuMuon;
    FloatingActionButton fabPhieuMuon;
    QuanLyPhieuMuonAdapter adapter;
    PhieuMuonDAO dao;
    ArrayList<PhieuMuon> list;
    Spinner spnThanhVienPM, spnSachPM;
    EditText edTienThuePM, edDatePM;
    ImageButton btnDatePickPM;
    CheckBox chkReturnBook;

    int tienThue;
    int maTV;
    int maS;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recViewPhieuMuon = view.findViewById(R.id.recViewPhieuMuon);
        fabPhieuMuon = view.findViewById(R.id.fabPhieuMuon);
        dao = new PhieuMuonDAO(getContext());
        list = dao.getAll();
        adapter = new QuanLyPhieuMuonAdapter(getContext(), list);
        recViewPhieuMuon.setLayoutManager(new LinearLayoutManager(getContext()));
        recViewPhieuMuon.setAdapter(adapter);
        fabPhieuMuon.setOnClickListener(v -> {
            showDialog(getContext());
        });

    }

    public void showDialog(Context context) {
        ThanhVienDAO TVdao = new ThanhVienDAO(context);
        SachDAO Sdao = new SachDAO(context);
        ArrayList<ThanhVien> listTV = TVdao.getAll();
        ArrayList<Sach> listSach = Sdao.getAll();
        SachSpinnerAdapter adapterSach = new SachSpinnerAdapter(context, listSach);
        ThanhVienSpinnerAdapter adapterThanhVien = new ThanhVienSpinnerAdapter(context, listTV);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.item_phieumuon_dialog, null);
        spnSachPM = v.findViewById(R.id.spnSachPM);
        spnThanhVienPM = v.findViewById(R.id.spnThanhVienPM);
        edTienThuePM = v.findViewById(R.id.edTienThuePM);
        edDatePM = v.findViewById(R.id.edDatePM);
        chkReturnBook = v.findViewById(R.id.chkReturnBook);
        ImageButton btnDatePickPM = v.findViewById(R.id.btnDatePickPM);
        builder.setTitle("Sửa Phiếu mượn");
        spnSachPM.setAdapter(adapterSach);
        spnThanhVienPM.setAdapter(adapterThanhVien);

        btnDatePickPM.setEnabled(false);
        edDatePM.setEnabled(false);
        edTienThuePM.setEnabled(false);
        spnThanhVienPM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTV = listTV.get(position).getMaTV();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnSachPM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maS = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                edTienThuePM.setText("Giá Thuê : "+tienThue+".VNĐ");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        edDatePM.setText("Từ ngày :" +sdf.format(new Date()));
        builder.setPositiveButton("Yes", (dialog, which) -> {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaSach(maS);
            phieuMuon.setMaTV(maTV);
            phieuMuon.setNgay(new Date());
            phieuMuon.setTienThue(tienThue);
            if (chkReturnBook.isChecked()) {
                phieuMuon.setTraSach(1);
            } else {
                phieuMuon.setTraSach(0);
            }
            if (dao.insertPhieuMuon(phieuMuon) > 0) {
                list.add(phieuMuon);
                adapter.notifyDataSetChanged();
                reload();
            }
        });
        builder.setNegativeButton("No", (dialog, which) -> {

        });


        builder.setView(v);
        builder.show();
    }

    public QuanLyPhieuMuonFragment() {

    }

    public void reload() {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public static QuanLyPhieuMuonFragment newInstance() {

        return new QuanLyPhieuMuonFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
    }
}