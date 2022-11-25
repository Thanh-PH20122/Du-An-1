package com.example.nguoidung.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguoidung.Dao.ThanhVienDao;
import com.example.nguoidung.Object.ThanhVien;
import com.example.nguoidung.R;
import com.example.nguoidung.SQLsever.SQLsever;

import java.sql.Connection;
import java.util.List;

public class Login extends AppCompatActivity {
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int EDITTEXT_DELAY = 300;
    public static final int BUTTON_DELAY = 500;
    public static final int VIEW_DELAY = 400;
    public static final int CHECKBOK_DELAY = 600;
    public static final int TEXTVIEW_DELAY = 600;

    Button btnDangNhap,btnDangKy;
    EditText edtTenTK, edtMatKhau;
    CheckBox checkBox;
    Intent intent;
    Context context = this;
    ThanhVienDao dao = new ThanhVienDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        ///////////////////////
        edtTenTK = findViewById(R.id.login_edit_name);
        edtMatKhau = findViewById(R.id.login_edit_password);
        checkBox = findViewById(R.id.login_checkBok);
        btnDangNhap = findViewById(R.id.login_btn_login);
        btnDangKy = findViewById(R.id.login_btn_signUp);
        ImageView logoImageView = findViewById(R.id.logo);
        ViewGroup container = findViewById(R.id.container);


        //Code for app logo animation
        ViewCompat.animate(logoImageView)
                .translationY(-250)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                        new DecelerateInterpolator(1.0f)).start();

        //Here we are setting animation on displaying content
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (v instanceof EditText) {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((EDITTEXT_DELAY * i) + 500)
                        .setDuration(500);
            } else if (v instanceof Button) {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((BUTTON_DELAY * i) + 500)
                        .setDuration(500);
            }else if (v instanceof CheckBox) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((CHECKBOK_DELAY * i) + 500)
                        .setDuration(500);
            }else if (v instanceof TextView) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((TEXTVIEW_DELAY * i) + 500)
                        .setDuration(500);
            } else {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((VIEW_DELAY * i) + 500)
                        .setDuration(1000);
            }
            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtTenTK.getText().toString();
                String passWord = edtMatKhau.getText().toString();
                Boolean checked = checkBox.isChecked();
                List<ThanhVien> ls = dao.getAll();
                for (ThanhVien tv: ls) {
                    if (userName.equals(tv.getUserName()) && passWord.equals(tv.getPassWord())){
                        rememberUser(userName,passWord,checked);
                        startActivity(new Intent(context,Home.class));
                    }
                }
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });

    }
    public void login(){
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            String userName = intent.getStringExtra("UserName");
            String passWord = intent.getStringExtra("PassWord");
            edtTenTK.setText(userName);
            edtMatKhau.setText(passWord);
        }
    }
    public void rememberUser(String u, String p, Boolean b){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(!b){
            editor.clear();
        }else{
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",b);
        }
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String muser = pref.getString("USERNAME", String.valueOf(false));
        String mpass = pref.getString("PASSWORD", String.valueOf(false));
        Boolean mb = pref.getBoolean("REMEMBER", false);
        if (mb){
            edtTenTK.setText(muser);
            edtMatKhau.setText(mpass);
            checkBox.setChecked(mb);
        }
        login();
    }
}