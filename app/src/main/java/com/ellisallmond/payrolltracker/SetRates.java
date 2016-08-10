package com.ellisallmond.payrolltracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetRates extends AppCompatActivity {

    EditText taxRateInput;
    Button btnSave;
    PayWeek PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_rates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PW = ((PayWeek)getApplicationContext());

        taxRateInput = (EditText) findViewById(R.id.taxRateInput);
        btnSave = (Button) findViewById(R.id.btnSaveRates);
        taxRateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PW.setTaxRate(taxRateInput.toString());
            }
        });
    }

}
