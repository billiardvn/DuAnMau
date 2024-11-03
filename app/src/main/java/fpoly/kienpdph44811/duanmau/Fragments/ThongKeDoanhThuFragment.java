package fpoly.kienpdph44811.duanmau.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fpoly.kienpdph44811.duanmau.DAO.PhieuMuonDAO;
import fpoly.kienpdph44811.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeDoanhThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeDoanhThuFragment extends Fragment {
    ImageButton btnDateDen, btnDateTu;
    Button btnDoanhThu;
    TextView tvDoanhThu;
    EditText edDateTu, edDateDen;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDateDen = view.findViewById(R.id.btnDateDen);
        btnDateTu = view.findViewById(R.id.btnDateTu);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        edDateTu = view.findViewById(R.id.edDateTu);
        edDateDen = view.findViewById(R.id.edDateDen);
        btnDateTu.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(
                    getActivity(),
                    mDateTuNgay,
                    mYear,
                    mMonth,
                    mDay
            );
            dialog.show();
        });
        btnDateDen.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(
                    getActivity(),
                    mDateDenNgay,
                    mYear,
                    mMonth,
                    mDay
            );
            dialog.show();
        });
        btnDoanhThu.setOnClickListener(v -> {
            String tuNgay = edDateTu.getText().toString();
            String denNgay = edDateDen.getText().toString();
            PhieuMuonDAO dao = new PhieuMuonDAO(getContext());

            tvDoanhThu.setText("Doanh Thu : " + dao.getDoanThu(tuNgay,denNgay) + " VNĐ");
        });
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = (view, year, month, dayOfMonth) -> {
        mYear = year;
        mMonth = month;
        mDay = dayOfMonth;
        GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDay);
        edDateTu.setText(sdf.format(calendar.getTime()));
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = (view, year, month, dayOfMonth) -> {
        mYear = year;
        mMonth = month;
        mDay = dayOfMonth;
        GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDay);
        edDateDen.setText(sdf.format(calendar.getTime()));
    };

    public ThongKeDoanhThuFragment() {
        // Required empty public constructor
    }

    public static ThongKeDoanhThuFragment newInstance() {
        ThongKeDoanhThuFragment fragment = new ThongKeDoanhThuFragment();

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
        return inflater.inflate(R.layout.fragment_thong_ke_doanh_thu, container, false);
    }
}