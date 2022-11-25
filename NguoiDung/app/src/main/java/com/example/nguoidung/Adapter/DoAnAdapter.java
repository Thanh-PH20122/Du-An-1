package com.example.nguoidung.Adapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguoidung.Object.DoAn;
import com.example.nguoidung.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class DoAnAdapter extends RecyclerView.Adapter<DoAnAdapter.ViewHolder>{
    List<DoAn> ls;
    Context context;

    public DoAnAdapter(List<DoAn> ls, Context context) {
        this.ls = ls;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_view_mon_an,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(CovertBitmap(ls.get(position).getImage()));
        holder.txtTenDoAn.setText(ls.get(position).getTenDoAn());
        holder.txtGiaDoAn.setText(String.valueOf(ls.get(position).getGia()));
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTenDoAn;
        TextView txtGiaDoAn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_view_mon_an_img);
            txtTenDoAn = itemView.findViewById(R.id.item_view_mon_an_ten_mon_an);
            txtGiaDoAn = itemView.findViewById(R.id.item_view_mon_an_gia_mon_an);
        }
    }
    public Bitmap CovertBitmap(String path) {
        Bitmap mbitmap = null;
        try {
            URL url = new URL(path);
            InputStream inputStream = url.openConnection().getInputStream();

            mbitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mbitmap;
    }
}
