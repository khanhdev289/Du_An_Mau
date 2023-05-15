package khanhnqph30151.fptpoly.duanmau.fragment.QuanLyLoaiSach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khanhnqph30151.fptpoly.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyLoaiSachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyLoaiSachFragment extends Fragment {



    public QuanLyLoaiSachFragment() {
        // Required empty public constructor
    }

    public static QuanLyLoaiSachFragment newInstance() {
        QuanLyLoaiSachFragment fragment = new QuanLyLoaiSachFragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
    }
}