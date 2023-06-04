package khanhnqph30151.fptpoly.duanmau.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.model.LoaiSach;
import khanhnqph30151.fptpoly.duanmau.data.LoaiSachDAO;
import khanhnqph30151.fptpoly.duanmau.model.Sach;
import khanhnqph30151.fptpoly.duanmau.data.SachDAO;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private ArrayList<Sach> list;
    private Context context;
    private ArrayList<LoaiSach> llist;
    private LoaiSachDAO loaiDao;
    ArrayAdapter<String> listspiner;




    public SachAdapter(ArrayList<Sach> list, Context context){
        this.list = list;
        this.context = context;
    }
    public void setData(ArrayList<Sach> lst){
        this.list = lst;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvGiaTien.setText(list.get(position).getGiaThue());
        holder.tvLoaiSach.setText(String.valueOf(list.get(position).getTenLoai()));
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
                            showDele(list.get(position).getIdSach());
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
                SachDAO dao = new SachDAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }
    private void updateDia(Sach s, int id) {
        Dialog dialog = new Dialog(context);
        LoaiSachDAO loaiDao = new LoaiSachDAO(context);
        dialog.setContentView(R.layout.dialog_sach_edit);
        EditText ed1, ed2;
        Spinner spinerSach;
        Button btnDialogAddCancel, btnDialogAddSubmit;
        ed1 = dialog.findViewById(R.id.edt_dialog_sach_edit_name);
        ed2 = dialog.findViewById(R.id.edt_dialog_sach_edit_giatien);
        spinerSach = dialog.findViewById(R.id.spn_dialog_sach_edit_loaisach);


        ed1.setText(list.get(id).getTenSach());
        ed2.setText(list.get(id).getGiaThue());

        btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_sach_edit_cancel);
        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_sach_edit_add);

        ArrayList<LoaiSach> lsList = loaiDao.getAllData();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, loaiDao.name());
        spinerSach.setAdapter(adapter1);
        int spIndex = 0;
        for (LoaiSach ls : lsList) {
            if (ls.getTenLoaiSach().equals(s.getTenLoai())) {
                spinerSach.setSelection(spIndex);
                break;
            }
            spIndex++;
        }
        spinerSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String i = lsList.get(position).getTenLoaiSach();
                s.setTenLoai(i);
//                llist = loaiDao.getAllData();
//                s.setTenLoai(String.valueOf(llist.get(position).getTenLoaiSach()));
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
                SachDAO sachDao = new SachDAO(context);
                String ten = ed1.getText().toString();
                String giatien = ed2.getText().toString();


                if (ten.trim().equals("") && giatien.trim().equals("")) {
                    Toast.makeText(context, "ko dc de trong", Toast.LENGTH_SHORT).show();
                }
                else {
                    s.setTenSach(ed1.getText().toString());
                    s.setGiaThue(ed2.getText().toString());
                }
                if (sachDao.update(s) > 0) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    list = sachDao.getAllData();
                    setData(list);
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();

    }


    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSach, tvGiaTien, tvLoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSach = itemView.findViewById(R.id.tv_item_sach_tensach);
            tvGiaTien = itemView.findViewById(R.id.tv_item_sach_giatien);
            tvLoaiSach = itemView.findViewById(R.id.tv_item_sach_loaisach);
        }
    }
}
