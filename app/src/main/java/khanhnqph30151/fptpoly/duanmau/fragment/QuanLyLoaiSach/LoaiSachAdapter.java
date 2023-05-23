package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.R;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tenLoaiSach.setText(list.get(position).getTenLoaiSach());
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
