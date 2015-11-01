package com.example.karthika.connect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class signup extends AppCompatActivity {
private Toolbar toolbar;
    private String[] arraySpinner;
    private String[] arraySpinner1;
    EditText name,pwd,phno,email,address1,DOB,city1;
    RadioButton g1,g2,g3,r,r1,r2;
    CheckBox c1,c2,c3;
    Spinner s,eqs;
    SQLiteDatabase db;
    int id1;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        this.arraySpinner = new String[] {
                "A+", "B+", "AB+", "O+", "A-","B-","AB-","O-"
        };
        s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        this.arraySpinner1 = new String[] {"None","Below SSLC",
                "SSLC", "HSC", "UG", "PG", "others"
        };
        eqs=(Spinner) findViewById(R.id.eqspin);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner1);
        eqs.setAdapter(adapter1);
        name=(EditText)findViewById(R.id.uname);
        pwd=(EditText)findViewById(R.id.pwd);
        g1=(RadioButton)findViewById(R.id.rmale);
        g2=(RadioButton)findViewById(R.id.rfemale);
        g3=(RadioButton)findViewById(R.id.rtransgender);
        c1=(CheckBox)findViewById(R.id.cblood);
        c2=(CheckBox)findViewById(R.id.cwrite);
        c3=(CheckBox)findViewById(R.id.cnursing);
        DOB=(EditText)findViewById(R.id.dob);
        phno=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.mail);
        address1=(EditText)findViewById(R.id.address);
        city1=(EditText)findViewById(R.id.city);
        r=(RadioButton)findViewById(R.id.roffer);
        r1=(RadioButton)findViewById(R.id.rseek);
        r2=(RadioButton)findViewById(R.id.rboth);
        db=openOrCreateDatabase("DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS register(uid INT,uname VARCHAR,utype VARCHAR,Gender VARCHAR,DOB VARCHAR,phone VARCHAR,email VARCHAR,address VARCHAR,city VARCHAR,qualification VARCHAR,bloodgroup VARCHAR);");
        Cursor c=db.rawQuery("SELECT * FROM register",null);
        if(c.getCount()==0)
        {
            db.execSQL("INSERT INTO register VALUES(0,'-','-','-','-','-','-','-','-','-','-');");
            Toast toast = Toast.makeText(getApplicationContext(), "App started",Toast.LENGTH_SHORT);
            toast.show();
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS login(uid INT,password VARCHAR,type VARCHAR);");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        }
        else if(id==R.id.action_login)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.action_done)
        {
            int duration = Toast.LENGTH_SHORT;
            EditText editText2=(EditText)findViewById(R.id.cpwd);
            String uname = name.getText().toString();
            String pwd1=pwd.getText().toString();
            String cpwd=editText2.getText().toString();
            LinearLayout l=(LinearLayout)findViewById(R.id.layoffer);
            if(!(r.isChecked()||r1.isChecked()||r2.isChecked()))
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Choose a Type", duration);
                toast.show();
            }
            else if(uname.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Enter Name", duration);
                toast.show();
            }
            else if(pwd1.equals("")||cpwd.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Enter Password", duration);
                toast.show();
            }
            else if(!pwd1.equals(cpwd)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Password mismatch", duration);
                toast.show();
            }
            else if(!isDateValid())
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid Date Format", duration);
                toast.show();
            }
            else if(email.getText().toString().trim().length()==0)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Enter mail ID", duration);
                toast.show();
            }
            else if(phno.length()!=10)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid MobileNumber", duration);
                toast.show();
            }
            else if(email.getText().toString().trim().length()==0||address1.getText().toString().trim().length()==0||city1.getText().toString().trim().length()==0)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Fill all the fields", duration);
                toast.show();
            }
            else if((l.getVisibility()==View.VISIBLE)&&(!(c1.isChecked()||c2.isChecked()||c3.isChecked())))
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Choose what You offer", duration);
                toast.show();
            }
            else
            {
                Cursor c1=db.rawQuery("SELECT MAX(uid) FROM register", null);
                c1.moveToFirst();
                id1=c1.getInt(0);
                id1=id1+1;String g="f";
                    if (r.isChecked()) {
                        type = "V";
                    }
                    else if (r1.isChecked()) {
                        type = "S";
                    }
                    else if (r2.isChecked()) {
                        type = "B";
                    }
                if(g1.isChecked()){
                     g= "m";}else if(g2.isChecked()){g="f";}else if(g3.isChecked()){g="t";}
                    db.execSQL("INSERT INTO register VALUES("+id1+",'"+name.getText()+"','"+type+"','"+g+"','"+DOB.getText()+"','"+ phno.getText()+"','"+email.getText()+"','"+address1.getText()+"','"+city1.getText()+"','"+eqs.getSelectedItem().toString()+"','"+s.getSelectedItem().toString()+"');");
                    db.execSQL("INSERT INTO login VALUES("+id1+",'"+pwd.getText()+"','"+type+"');");
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("User ID is " + type + Integer.toString(id1));
                    dlgAlert.setTitle("Registered Successfully");
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
    public void visib(View view)
    {
        LinearLayout l=(LinearLayout)findViewById(R.id.layoffer);
        RadioButton rb = (RadioButton) findViewById(R.id.roffer);
        RadioButton rb1 = (RadioButton) findViewById(R.id.rboth);
        if(rb.isChecked()||rb1.isChecked())
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
        EditText date=(EditText)findViewById(R.id.dob);
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
    public void bgroup(View view)
    {
     LinearLayout l=(LinearLayout)findViewById(R.id.bgl);
        if(c1.isChecked())
        {
            l.setVisibility(View.VISIBLE);
        }
        else {
            l.setVisibility(View.GONE);
        }
    }
}
