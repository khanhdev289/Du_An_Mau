package khanhnqph30151.fptpoly.duanmau.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.data.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edUser =  findViewById(R.id.ed_Login_ten);
        EditText edPass = findViewById(R.id.ed_Login_matkhau);


        AppCompatButton btnLogin = findViewById(R.id.btn_Login_DangNhap);
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString();
                String pass =edPass.getText().toString();
                if(thuThuDAO.checkLogin(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("thuThu_id", user);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Tài Khoản hoặc Mật Khẩu không đúng !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}