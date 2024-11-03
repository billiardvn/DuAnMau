package fpoly.kienpdph44811.duanmau.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.Adapter.TopAdapter;
import fpoly.kienpdph44811.duanmau.DAO.PhieuMuonDAO;
import fpoly.kienpdph44811.duanmau.Models.Top;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends Fragment {
    RecyclerView recTop;
    PhieuMuonDAO dao;
    ArrayList<Top> listTop;
    TopAdapter adapter ;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recTop = view.findViewById(R.id.recTop);
        dao = new PhieuMuonDAO(getContext());
        listTop = dao.getTop();
        recTop.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TopAdapter(listTop);
        recTop.setAdapter(adapter);

    }

    public static TopFragment newInstance(String param1, String param2) {
        TopFragment fragment = new TopFragment();
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
        return inflater.inflate(R.layout.fragment_top, container, false);
    }
}