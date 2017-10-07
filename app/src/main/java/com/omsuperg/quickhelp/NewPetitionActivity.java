package com.omsuperg.quickhelp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.omsuperg.quickhelp.models.PeticionModel;
import com.omsuperg.quickhelp.models.StatusModel;

import java.util.Calendar;
import java.util.Date;

public class NewPetitionActivity extends AppCompatActivity {

    private Button btnCalendar;
    private Calendar calendar;
    private Button btnHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_petition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Peticion creada", Toast.LENGTH_LONG).show();
                PeticionModel petition = new PeticionModel(0, "472567553116840", ((TextView) findViewById(R.id.textTitulo)).getText().toString(), "",
                        ((TextView) findViewById(R.id.textDetalle)).getText().toString(), StatusModel.VISIBLE, Integer.valueOf(((TextView) findViewById(R.id.textPersonas)).getText().toString()), new Date());
                setResult(RESULT_OK, new Intent().putExtra(Constants.PETITION, petition));
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnHour = (Button) findViewById(R.id.btnHour);

        calendar = Calendar.getInstance();

        btnCalendar.setText(calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR));
        btnHour.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewPetitionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        btnCalendar.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(NewPetitionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btnHour.setText(hourOfDay + ":" + minute);
                    }
                }, calendar.get(Calendar.HOUR), Calendar.MINUTE, false).show();
            }
        });
    }
}
