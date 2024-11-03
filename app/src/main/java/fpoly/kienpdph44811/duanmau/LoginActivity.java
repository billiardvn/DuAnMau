package fpoly.kienpdph44811.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import fpoly.kienpdph44811.duanmau.DAO.ThuThuDAO;
import fpoly.kienpdph44811.duanmau.Models.ThuThu;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputLayout edUsername, edPassword;
    ThuThuDAO dao;
    RadioButton chkRem;
    String strUser;
    String strPass;
    ImageButton btnQS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edPassword = findViewById(R.id.edPassword);
        edUsername = findViewById(R.id.edUsername);
        btnLogin = findViewById(R.id.btnLogin);
        chkRem = findViewById(R.id.chkRem);
        dao = new ThuThuDAO(this);
        btnQS = findViewById(R.id.btnQS);
        btnLogin.setOnClickListener(view ->{
            checkLogin();
        });
        btnQS.setOnClickListener(v -> {
            dao.insertThuThu(new ThuThu("ad" , "ad" ,"ad"));
            Toast.makeText(this, "Đã add admin default", Toast.LENGTH_SHORT).show();
        });
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE" , MODE_PRIVATE);
        String user = sharedPreferences.getString("USERNAME" ,"");
        String pass = sharedPreferences.getString("PASSWORD","");
        Boolean rem = sharedPreferences.getBoolean("REMEMBER" , false);
        edUsername.getEditText().setText(user);
        edPassword.getEditText().setText(pass);
        chkRem.setChecked(rem);
    }

    //Chức năng nhớ mật khẩu
    public void rememberUser(String u , String p , boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME" , u);
            editor.putString("PASSWORD" , p);
            editor.putBoolean("REMEMBER" , status);
        }
        editor.commit();
    }
    public void checkLogin(){
        strUser = edUsername.getEditText().getText().toString();
        strPass = edPassword.getEditText().getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập Tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dao.checkLogin(strUser,strPass) > 0){
                rememberUser(strUser,strPass,chkRem.isChecked());
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                intent.putExtra("USER" , strUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Tài Khoản Không tồn tại!!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}