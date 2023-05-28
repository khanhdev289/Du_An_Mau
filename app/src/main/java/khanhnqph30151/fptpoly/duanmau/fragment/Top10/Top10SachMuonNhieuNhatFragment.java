package khanhnqph30151.fptpoly.duanmau.fragment.Top10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khanhnqph30151.fptpoly.duanmau.R;


public class Top10SachMuonNhieuNhatFragment extends Fragment {

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
}