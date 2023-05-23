package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach;

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

public class QuanLyLoaiSachFragment extends Fragment {
    private LoaiSachDAO dao;
    private ArrayList<LoaiSach> list;
    private LoaiSachAdapter adapter;

    public QuanLyLoaiSachFragment() {

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyLoaiSach = view.findViewById(R.id.recy_loaisach);
        FloatingActionButton floatAdd = view.findViewById(R.id.float_loaisach_add);

        LoaiSachDAO dao = new LoaiSachDAO(getContext());
        list = dao.getAllData();
        adapter = new LoaiSachAdapter(list, getContext());

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                LoaiSach ls = new LoaiSach();
                dialog.setContentView(R.layout.dialog_loaisach_add);

                EditText ed1;
                Button btnDialogAddCancel, btnDialogAddSubmit;
                ed1 = dialog.findViewById(R.id.edt_dialog_loaisach_add_name);
                btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_loaisach_add_cancel);
                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_loaisach_add_add);

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
                        if (ten.trim().equals("")) {
                            Toast.makeText(getContext(), "ko dc de trong", Toast.LENGTH_SHORT).show();
                        } else {
                            ls.setTenLoaiSach(ed1.getText().toString());
                        }
                        if (dao.insert(ls) >= 0) {
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
        recyLoaiSach.setLayoutManager(layoutManager);
        recyLoaiSach.setAdapter(adapter);

        super.onViewCreated(view, savedInstanceState);
    }
}