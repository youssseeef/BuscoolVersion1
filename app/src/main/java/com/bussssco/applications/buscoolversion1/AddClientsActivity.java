package com.bussssco.applications.buscoolversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddClientsActivity extends AppCompatActivity {
    private TextView mTripTitleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clients);
        String text = getIntent().getStringExtra(CreateTripActivity.CLIENT_TRIP);
        mTripTitleView = (TextView) findViewById(R.id.textviewtitle);
        mTripTitleView.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item)
         ;return false;//For now
    }
}
