package com.example.chapter04.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.chapter04.R;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private DatePicker datePicker;
    private TextView tv;
    private TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        Button btn = findViewById(R.id.idet);
        btn.setOnClickListener(this);
        datePicker = findViewById(R.id.idpicker);
        tv = findViewById(R.id.dateidtxt);
        findViewById(R.id.choose).setOnClickListener(this);
        timePicker = findViewById(R.id.idtime);
        findViewById(R.id.timebutton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idet:
                String desc = String.format("你选择的日期是：%s-%s-%s", datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                tv.setText(desc);
                break;
            case R.id.choose:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2090, 4,10);
                datePickerDialog.show();
                break;
            case R.id.timebutton:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 0, 0, true);
                timePickerDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("你选择的日期是：%s-%s-%s", year, month + 1, dayOfMonth);
        tv.setText(desc);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc = String.format("你选择的时间是：%s : %s", hourOfDay, minute);
        tv.setText(desc);
    }
}

