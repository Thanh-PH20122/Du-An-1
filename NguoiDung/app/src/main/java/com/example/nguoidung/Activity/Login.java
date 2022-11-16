package com.example.nguoidung.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguoidung.R;

public class Login extends AppCompatActivity {
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int EDITTEXT_DELAY = 300;
    public static final int BUTTON_DELAY = 500;
    public static final int VIEW_DELAY = 400;
    public static final int CHECKBOK_DELAY = 600;
    public static final int TEXTVIEW_DELAY = 600;

    Button btnDangNhap,btnDangKy;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ///////////////////////
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

        btnDangKy = findViewById(R.id.login_btn_signUp);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });

    }
}