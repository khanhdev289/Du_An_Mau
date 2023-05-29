package khanhnqph30151.fptpoly.duanmau.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.View.LoginActivity;
import khanhnqph30151.fptpoly.duanmau.data.ThuThuDAO;

public class DoiMatKhauFragment extends Fragment {

    public DoiMatKhauFragment() {

    }

    public static DoiMatKhauFragment newInstance() {
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText edOldPass = view.findViewById(R.id.ed_Doimk_matkhaucu);
        EditText edNewPass1 = view.findViewById(R.id.ed_Doimk_matkhaumoi);
        EditText edNewPass2 = view.findViewById(R.id.ed_Doimk_matkhau2);
        Button doiMk = view.findViewById(R.id.btn_Doimk_Doimk);

        doiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edOldPass.getText().toString();
                String newPass1 = edNewPass1.getText().toString();
                String newPass2 = edNewPass2.getText().toString();

                if (newPass1.equals(newPass2)){
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    String thuThu_id = sharedPreferences.getString("thuThu_id", "");
                    ThuThuDAO ttDao = new ThuThuDAO(getContext());
                    boolean check = ttDao.updatePass(thuThu_id, oldPass, newPass1);
                    if (check){
                        Toast.makeText(getContext(), "Cap nhat mat khau thanh cong", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }else{
                        Toast.makeText(getContext(), "Cap nhat mat khau that bai", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Nhap mat khau khong duoc trung nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}