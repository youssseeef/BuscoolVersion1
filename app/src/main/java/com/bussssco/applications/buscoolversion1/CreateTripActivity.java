package com.bussssco.applications.buscoolversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTripActivity extends AppCompatActivity {
    public static final String CLIENT_TRIP= "ClientTrip";
    String mTripTitle;
    Button mCreateTripButton;
    EditText mTripName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        mCreateTripButton = (Button) findViewById(R.id.CreateTripNextButton);
        mCreateTripButton.setEnabled(false);
        mTripName = (EditText) findViewById(R.id.TripNameTextEdit);
        mTripName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence!=null && !charSequence.toString().equals("")){
                    mCreateTripButton.setEnabled(true);
                    mTripTitle = charSequence.toString();
                }else{
                    mCreateTripButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mCreateTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateTripActivity.this,AddClientsActivity.class);
                intent.putExtra(CLIENT_TRIP, mTripTitle);
                startActivity(intent);


            }
        });
    }
}
