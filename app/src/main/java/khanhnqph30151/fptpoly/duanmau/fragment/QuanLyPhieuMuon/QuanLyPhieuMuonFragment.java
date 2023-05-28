package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyPhieuMuon;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach.LoaiSach;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach.LoaiSachDAO;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach.Sach;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach.SachAdapter;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach.SachDAO;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyThanhVien.ThanhVien;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyThanhVien.ThanhVienDAO;


public class QuanLyPhieuMuonFragment extends Fragment {
    private ArrayList<PhieuMuon> pmList;
    private ArrayList<Sach> SachList;
    private ArrayList<ThanhVien> tvlist;
    private PhieuMuonAdapter pmAdapter;
    public QuanLyPhieuMuonFragment() {

    }

    public static QuanLyPhieuMuonFragment newInstance() {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyPhieuMuon = view.findViewById(R.id.recy_qlphieumuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.float_qlphieumuon_add);
        PhieuMuonDAO pmDao = new PhieuMuonDAO(getContext());
        SachDAO sachDao = new SachDAO(getContext());
        ThanhVienDAO tvDao = new ThanhVienDAO(getContext());
        pmList = pmDao.getAllData();
        pmAdapter = new PhieuMuonAdapter(pmList, getContext());
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                PhieuMuon pm = new PhieuMuon();
                dialog.setContentView(R.layout.dialog_phieumuon_add);

                EditText ed1;
                TextView tv1;
                CheckBox checkBox;
                Spinner spinerSach, spinnerThanhVien;
                Button btnDialogAddCancel, btnDialogAddSubmit;

                ed1 = dialog.findViewById(R.id.edt_dialog_phieumuon_add_ngay);
                tv1 = dialog.findViewById(R.id.tv_dialog_phieumuon_add_giathue);
                checkBox = dialog.findViewById(R.id.ckb_dialog_phieumuon_add_check);

                spinerSach = dialog.findViewById(R.id.spn_dialog_phieumuon_add_tensach);
                spinnerThanhVien = dialog.findViewById(R.id.spn_dialog_phieumuon_add_tenthanhvien);

                btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_phieumuon_add_cancel);
                btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_phieumuon_add_add);

                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, tvDao.name());
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sachDao.name());
                spinnerThanhVien.setAdapter(adapter1);
                spinerSach.setAdapter(adapter2);
                spinnerThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tvlist = tvDao.getAllData();
                        pm.setTenThanhVien(tvlist.get(position).getHoTen());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spinerSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SachList = sachDao.getAllData();
                        pm.setTenSach(SachList.get(position).getTenSach());
                        tv1.setText(SachList.get(position).getGiaThue());
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
                        String trangthai = "" ;
                        if(checkBox.isChecked() == true) {
                            trangthai = "Đã Trả Sách";
                        }
                        else if(checkBox.isChecked() == false) {
                            trangthai = "Chưa Trả Sách";
                        }
                        String ten = ed1.getText().toString();
                        if (ten.trim().equals("")) {
                            Toast.makeText(getContext(), "ko dc de trong", Toast.LENGTH_SHORT).show();
                        } else {
                            pm.setNgayMuon(ed1.getText().toString());
                            pm.setTrangThai(trangthai);

                        }
                        if (pmDao.insert(pm) >= 0) {
                            Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_LONG).show();
                            pmList = pmDao.getAllData();
                            pmAdapter.setData(pmList);
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
        recyPhieuMuon.setLayoutManager(layoutManager);
        recyPhieuMuon.setAdapter(pmAdapter);
        super.onViewCreated(view, savedInstanceState);
    }


}