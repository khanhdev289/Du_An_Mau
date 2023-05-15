package khanhnqph30151.fptpoly.duanmau.fragment.QuanLySach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khanhnqph30151.fptpoly.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLySachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLySachFragment extends Fragment {


    public QuanLySachFragment() {
        // Required empty public constructor
    }

    public static QuanLySachFragment newInstance() {
        QuanLySachFragment fragment = new QuanLySachFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }
}