package com.ellisallmond.payrolltracker;

import android.app.DatePickerDialog;
import android.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class NewPayActivity extends AppCompatActivity implements View.OnClickListener {

    PayWeek PW;

    Double total_dollars = new Double(0.00);
    Integer total_hours = new Integer(0);

    EditText totalHoursEditTxt;

    EditText totalDollarsEditTxt;

    EditText monHoursEditTxt;

    EditText tueHoursEditTxt;

    EditText wedHoursEditTxt;

    EditText thuHoursEditTxt;

    EditText friHoursEditTxt;

    EditText satHoursEditTxt;

    EditText sunHoursEditTxt;

    EditText taxRateInput;

    EditText startDateEditTxt;
    Button btnStartDateCalendar;

    EditText endDateEditTxt;
    Button btnEndDateCalendar;

    private int mYear, mMonth, mDay, mHour, mMinute;

    final Double payRate = new Double(12.55);

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aaPayRollTracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Pay Week");
        setSupportActionBar(toolbar);

        PW = ((PayWeek)getApplicationContext());

        File dir = new File(path);
        dir.mkdirs();

        totalHoursEditTxt = (EditText) findViewById(R.id.totalHoursEditTxt);
        totalHoursEditTxt.setText("0");

        totalDollarsEditTxt = (EditText) findViewById(R.id.totalDollarsEditTxt);
        totalDollarsEditTxt.setText("0.00");


        monHoursEditTxt = (EditText) findViewById(R.id.monHoursEditTxt);
        monHoursEditTxt.setText("0");

        tueHoursEditTxt = (EditText) findViewById(R.id.tueHoursEditTxt);
        tueHoursEditTxt.setText("0");

        //final EditText wedHoursEditTxt = (EditText) findViewById(R.id.wedHoursEditTxt);
        wedHoursEditTxt = (EditText) findViewById(R.id.wedHoursEditTxt);
        wedHoursEditTxt.setText("0");

        thuHoursEditTxt = (EditText) findViewById(R.id.thuHoursEditTxt);
        thuHoursEditTxt.setText("0");

        friHoursEditTxt = (EditText) findViewById(R.id.friHoursEditTxt);
        friHoursEditTxt.setText("0");

        satHoursEditTxt = (EditText) findViewById(R.id.satHoursEditTxt);
        satHoursEditTxt.setText("0");

        sunHoursEditTxt = (EditText) findViewById(R.id.sunHoursEditTxt);
        sunHoursEditTxt.setText("0");

        taxRateInput = (EditText) findViewById(R.id.taxRateInput);

        startDateEditTxt = (EditText) findViewById(R.id.startDateEditTxt);
        btnStartDateCalendar = (Button) findViewById(R.id.btnStartDateCalendar);
        btnStartDateCalendar.setOnClickListener(this);

        endDateEditTxt = (EditText) findViewById(R.id.endDateEditTxt);
        btnEndDateCalendar = (Button) findViewById(R.id.btnEndDateCalendar);
        btnEndDateCalendar.setOnClickListener(this);

        getTaxPayRates();
        insertTaxPayRates();



        Button btnCalcTotals = (Button) findViewById(R.id.btnCalcTotals);
        assert btnCalcTotals != null;
        btnCalcTotals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (monHoursEditTxt.getText().toString().trim().equals("")) {
                    monHoursEditTxt.setText("0");
                }

                if (tueHoursEditTxt.getText().toString().trim().equals("")) {
                    tueHoursEditTxt.setText("0");
                }

                if (wedHoursEditTxt.getText().toString().trim().equals("")) {
                    wedHoursEditTxt.setText("0");
                }

                if (thuHoursEditTxt.getText().toString().trim().equals("")) {
                    thuHoursEditTxt.setText("0");
                }

                if (friHoursEditTxt.getText().toString().trim().equals("")) {
                    friHoursEditTxt.setText("0");
                }

                if (satHoursEditTxt.getText().toString().trim().equals("")) {
                    satHoursEditTxt.setText("0");
                }

                if (sunHoursEditTxt.getText().toString().trim().equals("")) {
                    sunHoursEditTxt.setText("0");
                }

                //Double tempTotalDollars = new Double(0.00);
                total_dollars += Double.valueOf(monHoursEditTxt.getText().toString());
                total_dollars += Double.valueOf(tueHoursEditTxt.getText().toString());
                total_dollars += Double.valueOf(wedHoursEditTxt.getText().toString());
                total_dollars += Double.valueOf(thuHoursEditTxt.getText().toString());
                total_dollars += Double.valueOf(friHoursEditTxt.getText().toString());
                total_dollars += Double.valueOf(satHoursEditTxt.getText().toString());
                total_dollars += Double.valueOf(sunHoursEditTxt.getText().toString());
                total_dollars *= payRate;

                //Integer tempTotalHours = new Integer(0);
                total_hours += Integer.valueOf(monHoursEditTxt.getText().toString());
                total_hours += Integer.valueOf(tueHoursEditTxt.getText().toString());
                total_hours += Integer.valueOf(wedHoursEditTxt.getText().toString());
                total_hours += Integer.valueOf(thuHoursEditTxt.getText().toString());
                total_hours += Integer.valueOf(friHoursEditTxt.getText().toString());
                total_hours += Integer.valueOf(satHoursEditTxt.getText().toString());
                total_hours += Integer.valueOf(sunHoursEditTxt.getText().toString());


                totalDollarsEditTxt.setText(String.format("%.2f", total_dollars));

                totalHoursEditTxt.setText(total_hours.toString());


            }
        });


        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteToFile();

            }
        });


//        Button btnLoad = (Button) findViewById(R.id.btnLoad);
//        btnLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//                public void onClick(View v) {
//                ReadFromFile();
//            }
//        });

    }

    public void insertTaxPayRates() {
        //taxRateInput.setText(PW.getTaxRate().toString());
    }

    public void getTaxPayRates() {
        String filename = "Rates";
        byte[] data = new byte[128];
        String finalString = "";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);

            inputStream.read(data);
            inputStream.close();

            finalString = new String(data);

            String[] parts = finalString.split(System.getProperty("line.separator"));
            String part1 = parts[0]; // Pay per hour
            String part2 = parts[1]; // Tax Rate

            PW.setPayRate(part1);
            PW.setTaxRate(part2);
            taxRateInput.setText(part1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadFromFile(){
        String filename = "WorkWeeks";
        byte[] data = new byte[128];
        String finalString = "";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);

            inputStream.read(data);
            inputStream.close();

            finalString = new String(data);

            String[] parts = finalString.split(System.getProperty("line.separator"));
            String part1 = parts[0]; // Hours
            String part2 = parts[1]; // Dollars
            String part3 = parts[2]; // Monday
            String part4 = parts[3]; // Tuesday
            String part5 = parts[4]; // Wednesday
            String part6 = parts[5]; // Thursday
            String part7 = parts[6]; // Friday
            String part8 = parts[7]; // Saturday
            String part9 = parts[8]; // Sunday

            PW.setHours(part1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WriteToFile(){

        String filename = "WorkWeeks";
        String strTotalHours = totalHoursEditTxt.getText() + System.getProperty("line.separator");
        String strTotalDollars = totalDollarsEditTxt.getText() + System.getProperty("line.separator");
        String strMon = monHoursEditTxt.getText() + System.getProperty("line.separator");
        String strTue = tueHoursEditTxt.getText() + System.getProperty("line.separator");
        String strWed = wedHoursEditTxt.getText() + System.getProperty("line.separator");
        String strThu = thuHoursEditTxt.getText() + System.getProperty("line.separator");
        String strFri = friHoursEditTxt.getText() + System.getProperty("line.separator");
        String strSat = satHoursEditTxt.getText() + System.getProperty("line.separator");
        String strSun = sunHoursEditTxt.getText() + System.getProperty("line.separator");
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(strTotalHours.getBytes());
            outputStream.write(strTotalDollars.getBytes());
            outputStream.write(strMon.getBytes());
            outputStream.write(strTue.getBytes());
            outputStream.write(strWed.getBytes());
            outputStream.write(strThu.getBytes());
            outputStream.write(strFri.getBytes());
            outputStream.write(strSat.getBytes());
            outputStream.write(strSun.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        if (v == btnStartDateCalendar) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            startDateEditTxt.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnEndDateCalendar) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            endDateEditTxt.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

}
