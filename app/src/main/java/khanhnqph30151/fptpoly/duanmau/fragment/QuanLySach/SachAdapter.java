package khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach.LoaiSach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private ArrayList<Sach> list;
    private Context context;


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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvGiaTien.setText(list.get(position).getGiaTien());
        holder.tvLoaiSach.setText(String.valueOf(list.get(position).getIdLoai()));
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
