package com.example.idolmastercalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity<materialCalendarView> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCalendarView materialCalendarView;

        materialCalendarView = findViewById(R.id.calendarView);

    //////주말 색상 추가 ////
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

//        ///클릭한 날짜 표시하기/////
//        materialCalendarView.addDecorators(
//                new MySelectorDecorator(this)
//        );

        //오늘 날짜 표시하기
        OneDayDecorator oneDayDecorator = new OneDayDecorator();

        materialCalendarView.addDecorators(
                oneDayDecorator
        );
    }




}
