package com.bussssco.applications.buscoolversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddClientsActivity extends AppCompatActivity {
    public static final String ADD_CLIENT_TO_CREATE_NEW = "addclientactivity";
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

        switch(item.getItemId()){
            case R.id.create_client_in_a_trip:
                Intent intent = new Intent(AddClientsActivity.this,CreateNewClientActivity.class);
                intent.putExtra(ADD_CLIENT_TO_CREATE_NEW,mTripTitleView.getText());
                startActivity(intent);
                return true;
            default:
                super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }
}
