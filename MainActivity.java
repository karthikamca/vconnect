package com.example.karthika.connect;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.*;

import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends AppCompatActivity{
private Toolbar toolbar;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
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
         if(id==R.id.action_exit)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void loginm(View view)
    {
        EditText editText = (EditText) findViewById(R.id.uid);
        EditText editText1 = (EditText) findViewById(R.id.pwd);
        String uid = editText.getText().toString();
        String pwd = editText1.getText().toString();
        int duration = Toast.LENGTH_SHORT;
        if(!uid.equals(""))
        {
            if(pwd.equals(""))
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Enter Password", duration);
                toast.show();
            }
            else
            {   char type=uid.charAt(0);
                    if (type == 'S') {

                        Intent intent = new Intent(this, welcome.class);
                        startActivity(intent);
                    } else if (type == 'V') {
                        Intent intent = new Intent(this, welcome_volunteer.class);
                        startActivity(intent);
                    } else if (type == 'B') {
                        Intent intent = new Intent(this, welcomeboth.class);
                        startActivity(intent);
                    }
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter User ID", duration);
            toast.show();
        }
    }
    public void register(View view)
    {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }
}
