package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach;

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
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach.Sach;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach.SachDAO;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private ArrayList<LoaiSach> list;
    private Context context;

    public LoaiSachAdapter(ArrayList<LoaiSach> list, Context context){
        this.list = list;
        this.context = context;
    }
    public void setData(ArrayList<LoaiSach> lst){
        this.list = lst;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenLoaiSach.setText(list.get(position).getTenLoaiSach());
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
                            showDele(list.get(position).getMaLoaiSach());
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
                LoaiSachDAO dao = new LoaiSachDAO(context);
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
        public void showUpdate(LoaiSach ls, int id){
            Dialog dialog = new Dialog(context);
            LoaiSachDAO loaiDao = new LoaiSachDAO(context);
            dialog.setContentView(R.layout.dialog_loaisach_edit);
            EditText ed1;
            Button btnDialogAddCancel, btnDialogAddSubmit;
            ed1 = dialog.findViewById(R.id.edt_dialog_loaisach_edit_name);

            ed1.setText(list.get(id).getTenLoaiSach());

            btnDialogAddCancel = dialog.findViewById(R.id.btn_dialog_loaisach_edit_cancel);
            btnDialogAddSubmit = dialog.findViewById(R.id.btn_dialog_loaisach_edit_add);

            btnDialogAddCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnDialogAddSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoaiSachDAO loaiDao = new LoaiSachDAO(context);
                    String ten = ed1.getText().toString();
                    if (ten.trim().equals("")) {
                        Toast.makeText(context, "ko dc de trong", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ls.setTenLoaiSach(ed1.getText().toString());
                    }
                    if (loaiDao.update(ls) > 0) {
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                        list = loaiDao.getAllData();
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
        TextView tenLoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenLoaiSach = itemView.findViewById(R.id.tv_item_loaisach_tenloaisach);
        }
    }
}
