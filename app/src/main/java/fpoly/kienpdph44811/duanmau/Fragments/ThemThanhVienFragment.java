package fpoly.kienpdph44811.duanmau.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import fpoly.kienpdph44811.duanmau.DAO.ThuThuDAO;
import fpoly.kienpdph44811.duanmau.Models.ThuThu;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemThanhVienFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemThanhVienFragment extends Fragment {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Khởi tạo và ánh xạ
        TextInputLayout edMaTT = view.findViewById(R.id.edMaTT);
        TextInputLayout edTenTT = view.findViewById(R.id.edTenTT);
        TextInputLayout edMkTT = view.findViewById(R.id.edMkTT);
        ThuThuDAO dao = new ThuThuDAO(getContext());

        Button btn = view.findViewById(R.id.btnAddTV);
        btn.setOnClickListener(v ->{
            String maTT = edMaTT.getEditText().getText().toString();
            String tenTT = edTenTT.getEditText().getText().toString();
            String mkTT = edMkTT.getEditText().getText().toString();

            if (maTT.equals("") || tenTT.equals("") || mkTT.equals("")){
                Toast.makeText(getContext(), "Vui lòng Nhập đủ thôn tin thành viên", Toast.LENGTH_SHORT).show();
            }else {
                dao.insertThuThu(new ThuThu(maTT , tenTT , mkTT));
            }
        });
//        Log.d("AddU", "onViewCreated: " + dao.getAll().toString());
    }

    public ThemThanhVienFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ThemThanhVienFragment newInstance(String param1, String param2) {
        ThemThanhVienFragment fragment = new ThemThanhVienFragment();
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
        return inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);
    }
}