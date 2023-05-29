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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.data.ThanhVienDAO;
import khanhnqph30151.fptpoly.duanmau.model.ThanhVien;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    ArrayList<ThanhVien> list;
    private Context context;

    public ThanhVienAdapter(ArrayList<ThanhVien> list, Context context){
        this.list  =list;
        this.context = context;
    }
    public void setData(ArrayList<ThanhVien> lst){
        this.list = lst;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTenThanhVien.setText(list.get(position).getHoTen());
        holder.tvNamSinh.setText(list.get(position).getNamSinh());
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
                            showUpdate(list.get(position), position);
                            return true;
                        } else if (item.getItemId() == R.id.option_delete) {
                            showDele(list.get(position).getMaThanhVien());
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
                ThanhVienDAO tvDao = new ThanhVienDAO(context);
                if (tvDao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = tvDao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }
    public void showUpdate(ThanhVien tv, int id){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanhvien_edit);
        EditText ed1, ed2;
        Button btnDialogAddCancel, btnDialogAddSubmit;
        ed1 = dialog.findViewById(R.id.edt_dialog_thanhvien_edit_name);
        ed2 = dialog.findViewById(R.id.edt_dialog_thanhvien_edit_namsinh);

        ed1.setText(list.get(id).getHoTen());
        ed2.setText(list.get(id).getNamSinh());


        btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_thanhvien_edit_cancel);
        btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_thanhvien_edit_add);

        btnDialogAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhVienDAO tvDao = new ThanhVienDAO(context);
                String ten = ed1.getText().toString();
                String namsinh = ed2.getText().toString();
                if (ten.trim().equals("") && namsinh.trim().equals(""))  {
                    Toast.makeText(context, "ko dc de trong", Toast.LENGTH_SHORT).show();
                }
                else {
                    tv.setHoTen(ed1.getText().toString());
                    tv.setNamSinh(ed2.getText().toString());
                }
                if (tvDao.update(tv) > 0) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    list = tvDao.getAllData();
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

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tvTenThanhVien, tvNamSinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenThanhVien = itemView.findViewById(R.id.tv_item_thanhvien_tenthanhvien);
            tvNamSinh = itemView.findViewById(R.id.tv_item_thanhvien_namsinh);
        }
    }
}
