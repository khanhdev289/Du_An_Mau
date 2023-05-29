package khanhnqph30151.fptpoly.duanmau.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.model.LoaiSach;
import khanhnqph30151.fptpoly.duanmau.model.PhieuMuon;
import khanhnqph30151.fptpoly.duanmau.data.PhieuMuonDAO;
import khanhnqph30151.fptpoly.duanmau.model.Sach;
import khanhnqph30151.fptpoly.duanmau.data.SachDAO;
import khanhnqph30151.fptpoly.duanmau.model.ThanhVien;
import khanhnqph30151.fptpoly.duanmau.data.ThanhVienDAO;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private ArrayList<PhieuMuon> list;
    private Context context;

    private ArrayList<LoaiSach> llist;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setData(ArrayList<PhieuMuon> lst) {
        this.list = lst;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenThanhVien.setText(list.get(position).getTenThanhVien());
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvNgayMuon.setText(list.get(position).getNgayMuon());
        holder.tvTraSach.setText(list.get(position).getTrangThai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showdata(context, list.get(position));
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder builder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_popup_edot_delete, builder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionmenu = new MenuPopupHelper(context, builder, v);
                builder.setCallback(new MenuBuilder.Callback() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.option_edit) {
                            updateDia(list.get(position), position);
                            return true;
                        } else if (item.getItemId() == R.id.option_delete) {
                            showDele(list.get(position).getMaPhieuMuon());
                            return true;
                        } else {
                            return false;
                        }
                    }

                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                optionmenu.show();
                return true;
            }
        });
    }
    public void showDele(int id){
        AlertDialog.Builder dialogDL = new AlertDialog.Builder(context);
        dialogDL.setMessage("Bạn có muốn xóa không?");
        dialogDL.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDL.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhieuMuonDAO pmDao = new PhieuMuonDAO(context);
                if (pmDao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = pmDao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }
    private void updateDia(PhieuMuon pm, int id) {
        Dialog dialog = new Dialog(context);
        SachDAO sachDao = new SachDAO(context);
        ThanhVienDAO tvDao = new ThanhVienDAO(context);
        dialog.setContentView(R.layout.dialog_phieumuon_edit);
        EditText ed1;
        TextView tv1;
        Spinner spinerSach, spinnerTv;
        CheckBox checkBox;
        Button btnDialogAddCancel, btnDialogAddSubmit;

        ed1 = dialog.findViewById(R.id.edt_dialog_phieumuon_edit_ngay);
        tv1 = dialog.findViewById(R.id.tv_dialog_phieumuon_edit_giathue);
        checkBox = dialog.findViewById(R.id.ckb_dialog_phieumuon_edit_check);

        spinerSach = dialog.findViewById(R.id.spn_dialog_phieumuon_edit_tensach);
        spinnerTv = dialog.findViewById(R.id.spn_dialog_phieumuon_edit_tenthanhvien);

        btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_phieumuon_edit_cancel);
        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_phieumuon_edit_add);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        ed1.setText(list.get(id).getNgayMuon());
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        ed1.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        ArrayList<Sach> sList = sachDao.getAllData();
        ArrayList<ThanhVien> tvList = tvDao.getAllData();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, tvDao.name());
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, sachDao.name());
        spinnerTv.setAdapter(adapter1);
        spinerSach.setAdapter(adapter2);

        int spIndex = 0;
        for (LoaiSach ls : llist) {
            if (ls.getTenLoaiSach().equals(pm.getTenSach())) {
                spinerSach.setSelection(spIndex);
                break;
            }
            spIndex++;
        }

        spinnerTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String i = tvList.get(position).getHoTen();
                pm.setTenThanhVien(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinerSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String i = sList.get(position).getTenSach();
                pm.setTenSach(i);
                tv1.setText(sList.get(position).getGiaThue());

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
                PhieuMuonDAO pmDao = new PhieuMuonDAO(context);
                String ten = ed1.getText().toString();
                String trangthai = "" ;
                if(checkBox.isChecked() == true) {
                    trangthai = "Đã Trả Sách";
                }
                else if(checkBox.isChecked() == false) {
                    trangthai = "Chưa Trả Sách";
                }


                if (ten.trim().equals("")) {
                    Toast.makeText(context, "ko dc de trong", Toast.LENGTH_SHORT).show();
                }
                else {
                    pm.setNgayMuon(ed1.getText().toString());
                    pm.setTrangThai(trangthai);
                    pm.setGiaThue(tv1.getText().toString());

                }
                if (pmDao.update(pm) > 0) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    list = pmDao.getAllData();
                    setData(list);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();

    }
    public void Showdata(Context context, PhieuMuon phieuMuon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông tin Phiếu Mượn");
        builder.setMessage("Tên Thành Viên:" + phieuMuon.getTenThanhVien() + "\nTên Sách:" + phieuMuon.getTenSach()
                + "\nGiá Sách:" + phieuMuon.getGiaThue() + "\nNgày Thuê:" + phieuMuon.getNgayMuon()+ "\nTrang Thái:" + phieuMuon.getTrangThai());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenThanhVien,tvTenSach, tvNgayMuon,tvGiaThue, tvTraSach;
         ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenThanhVien = itemView.findViewById(R.id.tv_item_phieumuon_tenThanhVien);
            tvTenSach = itemView.findViewById(R.id.tv_item_phieumuon_tensach);
            tvNgayMuon = itemView.findViewById(R.id.tv_item_phieumuon_ngayMuon);
            tvTraSach = itemView.findViewById(R.id.tv_PhieuMuon_traSach);
        }
    }
}
