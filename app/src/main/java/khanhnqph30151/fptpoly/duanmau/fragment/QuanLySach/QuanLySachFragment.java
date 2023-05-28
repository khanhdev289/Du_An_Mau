package khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach.LoaiSach;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach.LoaiSachAdapter;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach.LoaiSachDAO;


public class QuanLySachFragment extends Fragment {
    private SachDAO dao;
    private ArrayList<Sach> list;
    private ArrayList<LoaiSach> llist;
    private SachAdapter adapter;

    public QuanLySachFragment() {

    }

    public static QuanLySachFragment newInstance() {
        QuanLySachFragment fragment = new QuanLySachFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recySach = view.findViewById(R.id.recy_sach);
        FloatingActionButton floatAdd = view.findViewById(R.id.float_sach_add);
        SachDAO sachDao = new SachDAO(getContext());
        LoaiSachDAO loaiDao = new LoaiSachDAO(getContext());
        list = sachDao.getAllData();
        adapter = new SachAdapter(list, getContext());
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                Sach s = new Sach();
                dialog.setContentView(R.layout.dialog_sach_add);

                EditText ed1,ed2;
                Spinner spinerSach;
                Button btnDialogAddCancel, btnDialogAddSubmit;
                ed1 = dialog.findViewById(R.id.edt_dialog_sach_add_name);
                ed2 = dialog.findViewById(R.id.edt_dialog_sach_add_giatien);

                spinerSach = dialog.findViewById(R.id.spn_dialog_sach_add_loaisach);
                btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_sach_add_cancel);
                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_sach_add_add);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, loaiDao.name());
                spinerSach.setAdapter(adapter1);
                spinerSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        llist = loaiDao.getAllData();
                        s.setTenLoai((llist.get(position).getTenLoaiSach()));
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

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
                        String giatien = ed2.getText().toString();
                        if (ten.trim().equals("") && giatien.trim().equals("")) {
                            Toast.makeText(getContext(), "ko dc de trong", Toast.LENGTH_SHORT).show();
                        } else {
                            s.setTenSach(ed1.getText().toString());
                            s.setGiaThue(ed2.getText().toString());
                        }
                        if (sachDao.insert(s) >= 0) {
                            Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_LONG).show();
                            list = sachDao.getAllData();
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
        recySach.setLayoutManager(layoutManager);
        recySach.setAdapter(adapter);

        super.onViewCreated(view, savedInstanceState);

    }

}