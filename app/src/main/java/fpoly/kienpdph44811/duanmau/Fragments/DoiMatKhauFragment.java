package fpoly.kienpdph44811.duanmau.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import fpoly.kienpdph44811.duanmau.DAO.ThuThuDAO;
import fpoly.kienpdph44811.duanmau.Models.ThuThu;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoiMatKhauFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoiMatKhauFragment extends Fragment {
    TextInputLayout edOldPassTT, edNewPassTT, edConfPassTT;
    Button btnChangePass;
    ImageButton btnPassClearAll;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edOldPassTT = view.findViewById(R.id.edOldPassTT);
        edConfPassTT = view.findViewById(R.id.edConfNewPassTT);
        edNewPassTT = view.findViewById(R.id.edNewPassTT);
        ThuThuDAO dao = new ThuThuDAO(getContext());
        btnChangePass = view.findViewById(R.id.btnChangePass);
        btnPassClearAll = view.findViewById(R.id.btnPassClearAll);

        btnPassClearAll.setOnClickListener(v -> {
            edOldPassTT.getEditText().setText("");
            edNewPassTT.getEditText().setText("");
            edConfPassTT.getEditText().setText("");
        });
        btnChangePass.setOnClickListener(v -> {
            String oldPass = edOldPassTT.getEditText().getText().toString();
            String newPass = edNewPassTT.getEditText().getText().toString();
            String confPass = edConfPassTT.getEditText().getText().toString();

            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            if (oldPass.equals("") || newPass.equals("") || confPass.equals("")) {
                Toast.makeText(getContext(), "Vui lòng nhập đủ Thông tin !!", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confPass)) {
                Toast.makeText(getContext(), "Mật khẩu xác nhận ko khớp", Toast.LENGTH_SHORT).show();
            } else {
                ThuThu tt = dao.getByID(user);
                tt.setMatKhau(newPass);
                if (dao.updatePass(tt) > 0){
                    Toast.makeText(getContext(), "Đổi MK thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Đổi MK thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static DoiMatKhauFragment newInstance(String param1, String param2) {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }
}