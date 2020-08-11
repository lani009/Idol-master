package com.example.idolmastercalendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<TextView> bottomButtonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PlanFragment.newInstance()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page_1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, PlanFragment.newInstance()).commit();
                    return true;

                case R.id.page_2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, ReviewFragment.newInstance()).commit();
                    return true;

                case R.id.page_3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, ReviewFragment.newInstance()).commit();
                    return true;

                case R.id.page_4:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, MyPageFragment.newInstance()).commit();
                    return true;

                default:
                    return false;
            }
        });
        /*BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when(item.itemId) {
            R.id.item1 -> {
                // Respond to navigation item 1 click
                true
            }
            R.id.item2 -> {
                // Respond to navigation item 2 click
                true
            }
        else -> false
        }
        }*/
    }

    private void changeColor(int position) {
        for(TextView textView : bottomButtonList) {
            textView.setTextColor(Color.parseColor("#000000"));
        }
        bottomButtonList.get(position).setTextColor(Color.parseColor("#FF5F2D"));
    }
}