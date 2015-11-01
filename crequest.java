package com.example.karthika.connect;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class crequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crequest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        String[] arraySpinner = new String[]{
                "A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        Constants.addToActivityStack(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crequest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        RadioButton rb=(RadioButton)findViewById(R.id.rblood);
        RadioButton rb1;
        rb1 = (RadioButton)findViewById(R.id.rwrite);
        RadioButton rb2=(RadioButton)findViewById(R.id.rnursing);
        EditText e=(EditText)findViewById(R.id.date);
        EditText e1=(EditText)findViewById(R.id.city);
        EditText e2=(EditText)findViewById(R.id.desc1);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Constants.killAllExcept(null);
        }
        else if(id==R.id.action_done)
        {
            if(!(rb.isChecked()||rb1.isChecked()||rb2.isChecked()))
            {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), "Choose Your Requirement", duration);
                toast.show();
            }
            else if(!(isDateValid()))
            {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Date Format", duration);
                toast.show();
            }
            else if((e1.getText().toString()).trim().equals("")&&(e2.getText().toString()).trim().equals(""))
            {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), "Fill allthe fields", duration);
                toast.show();
            }
            else
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Request ID is ");
                dlgAlert.setTitle("Request Created");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                });
                dlgAlert.create().show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
    public  void bgroup(View view)
    {
        RadioButton rb=(RadioButton)findViewById(R.id.rblood);
        LinearLayout l=(LinearLayout)findViewById(R.id.bgl);
        if(rb.isChecked())
        {
            l.setVisibility(View.VISIBLE);
        }
        else
        {
            l.setVisibility(View.GONE);
        }
    }
    final static String DATE_FORMAT = "dd-MM-yyyy";
    public boolean isDateValid()
    {
        EditText date=(EditText)findViewById(R.id.date);
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            String d=date.getText().toString();
            df.parse(d);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
