package com.ellisallmond.payrolltracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class ExistingPayWeekActivity extends AppCompatActivity {

    PayWeek PW;

    EditText totalHoursEditTxt;

    EditText totalDollarsEditTxt;

    EditText monHoursEditTxt;

    EditText tueHoursEditTxt;

    EditText wedHoursEditTxt;

    EditText thuHoursEditTxt;

    EditText friHoursEditTxt;

    EditText satHoursEditTxt;

    EditText sunHoursEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_pay_week);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PW = ((PayWeek)getApplicationContext());

        totalHoursEditTxt = (EditText) findViewById(R.id.totalHoursEditTxt);
        totalHoursEditTxt.setText(PW.getHours());

        totalDollarsEditTxt = (EditText) findViewById(R.id.totalDollarsEditTxt);
        totalDollarsEditTxt.setText(PW.getDollars());


        monHoursEditTxt = (EditText) findViewById(R.id.monHoursEditTxt);
        monHoursEditTxt.setText(PW.getMon());

        tueHoursEditTxt = (EditText) findViewById(R.id.tueHoursEditTxt);
        tueHoursEditTxt.setText(PW.getTue());

        //final EditText wedHoursEditTxt = (EditText) findViewById(R.id.wedHoursEditTxt);
        wedHoursEditTxt = (EditText) findViewById(R.id.wedHoursEditTxt);
        wedHoursEditTxt.setText(PW.getWed());

        thuHoursEditTxt = (EditText) findViewById(R.id.thuHoursEditTxt);
        thuHoursEditTxt.setText(PW.getThu());

        friHoursEditTxt = (EditText) findViewById(R.id.friHoursEditTxt);
        friHoursEditTxt.setText(PW.getFri());

        satHoursEditTxt = (EditText) findViewById(R.id.satHoursEditTxt);
        satHoursEditTxt.setText(PW.getSat());

        sunHoursEditTxt = (EditText) findViewById(R.id.sunHoursEditTxt);
        sunHoursEditTxt.setText(PW.getSun());

    }

}
