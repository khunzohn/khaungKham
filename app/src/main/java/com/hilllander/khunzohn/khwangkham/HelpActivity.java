package com.hilllander.khunzohn.khwangkham;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hilllander.khunzohn.khwangkham.util.FontSupplier;


public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("How to use");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface zawgyi = FontSupplier.getZawgyiTypeface(this);
        TextView instruction_1 = (TextView) findViewById(R.id.instruction_one);
        instruction_1.setTypeface(zawgyi);
        TextView instruction_2 = (TextView) findViewById(R.id.instruction_two);
        instruction_2.setTypeface(zawgyi);
        TextView instruction_3 = (TextView) findViewById(R.id.instruction_three);
        instruction_3.setTypeface(zawgyi);
        TextView hostingMessage = (TextView) findViewById(R.id.hosting_message);
        hostingMessage.setTypeface(zawgyi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
