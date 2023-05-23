package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyThanhVien;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import khanhnqph30151.fptpoly.duanmau.R;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenThanhVien.setText(list.get(position).getHoTen());
        holder.tvNamSinh.setText(list.get(position).getNamSinh());
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
