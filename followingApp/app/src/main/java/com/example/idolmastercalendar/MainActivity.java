package com.example.idolmastercalendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<TextView> bottomButtonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView bottomButton01 = findViewById(R.id.main);
        TextView bottomButton02 = findViewById(R.id.map);
        TextView bottomButton03 = findViewById(R.id.review);
        TextView bottomButton04 = findViewById(R.id.my);

        bottomButton01.setOnClickListener(this);
        bottomButton02.setOnClickListener(this);
        bottomButton03.setOnClickListener(this);
        bottomButton04.setOnClickListener(this);

        bottomButtonList.add(bottomButton01);
        bottomButtonList.add(bottomButton02);
        bottomButtonList.add(bottomButton03);
        bottomButtonList.add(bottomButton04);


        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PlanFragment.newInstance()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, PlanFragment.newInstance()).commit();
                changeColor(0);
                break;

            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ReviewFragment.newInstance()).commit();
                changeColor(1);
                break;

            case R.id.review:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ReviewFragment.newInstance()).commit();
                changeColor(2);
                break;

            case R.id.my:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, MyPageFragment.newInstance()).commit();
                changeColor(3);
                break;
        }
    }

    private void changeColor(int position) {
        for(TextView textView : bottomButtonList) {
            textView.setTextColor(Color.parseColor("#000000"));
        }
        bottomButtonList.get(position).setTextColor(Color.parseColor("#FF5F2D"));
    }
}