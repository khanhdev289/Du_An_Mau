package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyThanhVien;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.R;


public class QuanLyThanhVienFragment extends Fragment {
    private ArrayList<ThanhVien> list;
    private ThanhVienAdapter adapter;
    public QuanLyThanhVienFragment() {
    }

    public static QuanLyThanhVienFragment newInstance() {
        QuanLyThanhVienFragment fragment = new QuanLyThanhVienFragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyThanhVien = view.findViewById(R.id.recy_thanhvien);
        FloatingActionButton floatAdd = view.findViewById(R.id.float_thanhvien_add);

        ThanhVienDAO dao = new ThanhVienDAO(getContext());
        list =dao.getAllData();
        adapter = new ThanhVienAdapter(list, getContext());

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                ThanhVien tv = new ThanhVien();
                dialog.setContentView(R.layout.dialog_thanhvien_add);

                EditText ed1, ed2;
                Button btnDialogAddCancel, btnDialogAddSubmit;

                ed1 = dialog.findViewById(R.id.edt_dialog_thanhvien_add_name);
                ed2 = dialog.findViewById(R.id.edt_dialog_thanhvien_add_namsinh);

                btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_thanhvien_add_cancel);
                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_thanhvien_add_add);

                btnDialogAddCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String ten = ed1.getText().toString();
                        String namsinh = ed2.getText().toString();

                        if(ten.trim().equals("") && namsinh.trim().equals("")){
                            Toast.makeText(getContext(), "ko dc de trong", Toast.LENGTH_SHORT).show();
                        }else {
                            tv.setHoTen(ed1.getText().toString());
                            tv.setNamSinh(ed2.getText().toString());
                        }
                        if (dao.insert(tv) >= 0) {
                            Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_LONG).show();
                            list = dao.getAllData();
                            adapter.setData(list);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "them that bai!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyThanhVien.setLayoutManager(layoutManager);
        recyThanhVien.setAdapter(adapter);

        super.onViewCreated(view, savedInstanceState);
    }
}