package com.example.karthika.connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class rating extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        Constants.addToActivityStack(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rating, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Constants.killAllExcept(null);
        }

        return super.onOptionsItemSelected(item);
    }
    public void done(View view)
    {
        int duration = Toast.LENGTH_SHORT;
        float i;
//        if(cid exist)
        EditText e1=(EditText)findViewById(R.id.cid);
        RatingBar rb= (RatingBar)findViewById(R.id.ratingBar);
        i=rb.getRating();
        Toast toast = Toast.makeText(getApplicationContext(),Float.toString(i), duration);
        toast.show();
        finish();
//        elseToast toast = Toast.makeText(getApplicationContext(), "Check your connectionID", duration);
//        toast.show();
    }
}
