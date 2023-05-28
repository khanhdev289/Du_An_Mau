package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyPhieuMuon;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import khanhnqph30151.fptpoly.duanmau.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private ArrayList<PhieuMuon> list;
    private Context context;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenThanhVien.setText(list.get(position).getTenThanhVien());
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvNgayMuon.setText(list.get(position).getNgayMuon());
        holder.tvTraSach.setText(list.get(position).getTrangThai());
//        holder.tvGiaThue.setText(list.get(position).getGiaThue());


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
//            tvGiaThue = itemView.findViewById(R.id.tv_hieumuon_add_giathue);
        }
    }
}
