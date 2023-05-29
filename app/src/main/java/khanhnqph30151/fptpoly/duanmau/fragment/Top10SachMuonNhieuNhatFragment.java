package khanhnqph30151.fptpoly.duanmau.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.duanmau.R;
import khanhnqph30151.fptpoly.duanmau.adapter.AdapterTop10;
import khanhnqph30151.fptpoly.duanmau.data.ThongKeDAO;
import khanhnqph30151.fptpoly.duanmau.model.Sach;


public class Top10SachMuonNhieuNhatFragment extends Fragment {
    private AdapterTop10 adapte;
    private ArrayList<Sach> toplist;
    private RecyclerView recyTop;
    private ThongKeDAO tkDao;
    public Top10SachMuonNhieuNhatFragment() {
    }

    public static Top10SachMuonNhieuNhatFragment newInstance() {
        Top10SachMuonNhieuNhatFragment fragment = new Top10SachMuonNhieuNhatFragment();
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
        return inflater.inflate(R.layout.fragment_top10_sach_muon_nhieu_nhat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyTop = view.findViewById(R.id.recyTop10);
        ThongKeDAO tkDao = new ThongKeDAO(getContext());

        toplist = tkDao.getTop10MuonSach();
        adapte = new AdapterTop10(toplist, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyTop.setLayoutManager(layoutManager);
        recyTop.setAdapter(adapte);
        super.onViewCreated(view, savedInstanceState);
    }
}