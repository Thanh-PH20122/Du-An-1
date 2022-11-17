package com.example.nguoidung.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.nguoidung.Fragment.fragmenCheQue;
import com.example.nguoidung.Fragment.fragmentFavorite;
import com.example.nguoidung.Fragment.fragmentHome;
import com.example.nguoidung.Fragment.fragmentUser;
import com.example.nguoidung.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.green);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.favorite, R.color.ping);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.cheque, R.color.yello);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.user, R.color.blue);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#CDDC39"));

        bottomNavigation.setColored(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.home_frame_layout, new fragmentHome());
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.home_frame_layout, new fragmentHome());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (position == 1) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.home_frame_layout, new fragmentFavorite());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (position == 2) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.home_frame_layout, new fragmenCheQue());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (position == 3) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.home_frame_layout, new fragmentUser());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                return true;
            }
        });

    }
}