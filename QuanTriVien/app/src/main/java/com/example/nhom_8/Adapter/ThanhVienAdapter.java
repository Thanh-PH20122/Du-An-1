package com.example.nhom_8.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Dao.ThanhVienDao;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.R;

import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    List<ThanhVien> ls;
    Context context;
    LayoutInflater inflater;
    ThanhVienDao dao;
    public ThanhVienAdapter(List<ThanhVien> ls, Context context) {
        this.ls = ls;
        this.context = context;
        dao = new ThanhVienDao(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quan_ly_tai_khoan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTaiKHoan.setText(ls.get(position).getUserName());
        holder.txtMatKhau.setText(ls.get(position).getPassWord());
        holder.txtTenNguoiDung.setText(ls.get(position).getTenNguoiDung());
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTaiKHoan,txtMatKhau,txtTenNguoiDung;
        Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTaiKHoan = itemView.findViewById(R.id.item_quan_ly_tk_TxtTaiKhoan);
            txtMatKhau = itemView.findViewById(R.id.item_quan_ly_tk_TxtMatKhau);
            txtTenNguoiDung = itemView.findViewById(R.id.item_quan_ly_tk_TxtTenNguoiDung);
            btnSua = itemView.findViewById(R.id.item_quan_ly_tk_btnSua);
            btnXoa = itemView.findViewById(R.id.item_quan_ly_tk_btnXoa);

        }
    }
    public void delete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa");
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deleteRow(ls.get(position));
                loadData();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void loadData() {
        ls.clear();
        ls = dao.getAll();
        notifyDataSetChanged();
    }
}
