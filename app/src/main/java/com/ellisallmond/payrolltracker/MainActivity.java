package com.ellisallmond.payrolltracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Integer numEntries = new Integer(0);

    Boolean entryCreated = false;
    Boolean creating = true;

    Integer i = new Integer(0);

    Integer week, entries, location;

    PayWeek PW;

    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    RelativeLayout relativeLayout;
    ViewGroup container;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        relativeLayout = (RelativeLayout) findViewById(R.id.mainRele);

        PW = (PayWeek) getApplicationContext();
        //PW.setHours("");

        CheckFileExists();

        Button btnSetRates = (Button) findViewById(R.id.btnSetRates);
        btnSetRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent setRatesIntent = new Intent(MainActivity.this, SetRates.class);
//                startActivity(setRatesIntent);
                popupWindow();
            }
        });

        Button btnDeleteFile = (Button) findViewById(R.id.btnDeleteFile);
        btnDeleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "WorkWeeks";
                String tempStr = "";
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    try {
                        outputStream.write(tempStr.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();

                    NewPayWeek();
                    addButton();
                }
            });
        }


        Button fpb = (Button) findViewById(R.id.btn1);

        fpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "First check clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void NewPayWeek() {
//        Intent newPayWeekIntent = new Intent(MainActivity.this, NewPayActivity.class);
//        startActivity(newPayWeekIntent);
    }


    public void addButton() {
        entryCreated = true;
        creating = true;
        final Intent newPayWeekIntent = new Intent(MainActivity.this, NewPayActivity.class);
        startActivity(newPayWeekIntent);
    }


    public void ReadFromFile(){
        String filename = "WorkWeeks";
        byte[] data = new byte[128];
        String finalString;
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);

            inputStream.read(data);
            inputStream.close();

            finalString = new String(data);

            String[] parts = finalString.split(System.getProperty("line.separator"));
            String part1 = parts[0 + location]; // Hours
            String part2 = parts[1 + location]; // Dollars
            String part3 = parts[2 + location]; // Monday
            String part4 = parts[3 + location]; // Tuesday
            String part5 = parts[4 + location]; // Wednesday
            String part6 = parts[5 + location]; // Thursday
            String part7 = parts[6 + location]; // Friday
            String part8 = parts[7 + location]; // Saturday
            String part9 = parts[8 + location]; // Sunday

            PW.setHours(part1);
            PW.setDollars(part2);
            PW.setMon(part3);
            PW.setTue(part4);
            PW.setWed(part5);
            PW.setThu(part6);
            PW.setFri(part7);
            PW.setSat(part8);
            PW.setSun(part9);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void popupWindow() {
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        container = (ViewGroup) layoutInflater.inflate(R.layout.set_rates, null);

        popupWindow = new PopupWindow(container, relativeLayout.getWidth(), relativeLayout.getHeight() + toolbar.getHeight(), true);
        popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, 0, 0);

        final EditText taxRate = (EditText) container.findViewById(R.id.taxRateInput);
        final EditText payRate = (EditText) container.findViewById(R.id.payRateInput);

        container.findViewById(R.id.btnSaveRates).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "Rates";
                FileOutputStream outputStream;

                final String strPayRate = payRate.getText() + System.getProperty("line.separator");
                final String strTaxRate = taxRate.getText() + System.getProperty("line.separator");

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(strPayRate.getBytes());
                    outputStream.write(strTaxRate.getBytes());
                    outputStream.close();

                    Snackbar.make(v, "Saved", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        container.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void addOneBtn() {
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        final Button btnNew = new Button(this);

        btnNew.setId(i);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(40, 40, 40, 40);

        week = btnNew.getId();
        entries = 9;
        if(week == 0) {
            location = 0;
        } else if(week > 0) {
            location = entries * week;
        }

        ReadFromFile();

        btnNew.setText(PW.getHours() + "HOURS | $" + PW.getDollars());

        btnNew.setBackgroundColor(Color.WHITE);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "The number " + btnNew.getId() + " entry was clicked!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                week = btnNew.getId();
                entries = 9;
                if(week == 0) {
                    location = 0;
                } else if(week > 0) {
                    location = entries * week;
                }


                ReadFromFile();

                final Intent existingPayWeekIntent = new Intent(MainActivity.this, ExistingPayWeekActivity.class);
                startActivity(existingPayWeekIntent);
            }
        });

        tableLayout.addView(btnNew, 0, lp);
        i++;
    }

    public void addInitialWkBtns() {
        String filename = "WorkWeeks";
        byte[] data = new byte[128];
        String finalString;
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
            inputStream.read(data);
            inputStream.close();
            finalString = new String(data);
            Integer numBtns;

            String[] parts = finalString.split(System.getProperty("line.separator"));
            numBtns = parts.length/9;

            for(int j = 0; j < numBtns; j++){
                addOneBtn();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CheckFileExists(){
        String filename = "WorkWeeks";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
            inputStream.close();
//            Toast.makeText(getApplication(), "File Exist!",
//                    Toast.LENGTH_LONG).show();

            addInitialWkBtns();

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(getApplication(), "File doesn't exist!",
//                    Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

        if(entryCreated && creating){
            final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

            final Button btnNew = new Button(this);

            btnNew.setId(i);
            TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(40, 40, 40, 40);

            week = btnNew.getId();
            entries = 9;
            if(week == 0) {
                location = 0;
            } else if(week > 0) {
                location = entries * week;
            }

            ReadFromFile();

            btnNew.setText(PW.getHours() + "HOURS | $" + PW.getDollars());
            btnNew.setTextColor(Color.BLACK);

            btnNew.setBackgroundColor(Color.WHITE);

            btnNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "The number " + btnNew.getId() + " entry was clicked!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    week = btnNew.getId();
                    entries = 9;
                    if(week == 0) {
                        location = 0;
                    } else if(week > 0) {
                        location = entries * week;
                    }


                    ReadFromFile();

                    final Intent existingPayWeekIntent = new Intent(MainActivity.this, ExistingPayWeekActivity.class);
                    startActivity(existingPayWeekIntent);
                }
            });

            tableLayout.addView(btnNew, 0, lp);
            i++;
            creating = false;
        }


    }
}
