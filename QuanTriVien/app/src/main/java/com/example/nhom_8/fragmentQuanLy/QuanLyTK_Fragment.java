package com.example.nhom_8.fragmentQuanLy;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Adapter.HoaDonAdapter;
import com.example.nhom_8.Adapter.ThanhVienAdapter;
import com.example.nhom_8.Dao.HoaDonDao;
import com.example.nhom_8.Dao.ThanhVienDao;
import com.example.nhom_8.Object.HoaDon;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.R;

import java.util.List;

public class QuanLyTK_Fragment extends Fragment {
    View view;
    Context context;
    RecyclerView recyclerView;
    ThanhVienDao dao;
    ThanhVienAdapter adapter;
    List<ThanhVien> ls;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quan_ly_tk, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.fragment_quan_ly_tk_rcyView);
        loadData();

        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayout =new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        dao = new ThanhVienDao();
        ls = dao.getAll();
        if (ls.isEmpty()){

        }else{
            adapter = new ThanhVienAdapter(ls,context);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
