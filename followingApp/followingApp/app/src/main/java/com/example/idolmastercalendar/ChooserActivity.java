package com.example.idolmastercalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        final ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        FloatingActionButton next = findViewById(R.id.next);
        FloatingActionButton before = findViewById(R.id.before);

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem() > 0)
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
                else
                    finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem() == 0)
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                else {
                    startActivity(new Intent(ChooserActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}