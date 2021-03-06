package com.bussssco.applications.buscoolversion1;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bussssco.applications.buscoolversion1.model.Client;
import com.bussssco.applications.buscoolversion1.model.ClientLab;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class CreateNewClientActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private LocationManager loc;
    private Button mGetLocationButton;
    private Button mSaveUser;
    private EditText mClientNameEditText;
    private EditText mClientPhoneEditText;
    private Location mLastLocation;
    public GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_client);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        mClientNameEditText = (EditText) findViewById(R.id.get_client_name);
        mClientPhoneEditText = (EditText) findViewById(R.id.get_phone_text_view);
        mGetLocationButton = (Button) findViewById(R.id.get_location_Button);
        mGetLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetLocationButton.setText(getLocation());
            }
        });
        mSaveUser = (Button) findViewById(R.id.submit_client_button);
        mSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientLab cl = ClientLab.get(getApplicationContext());
                int tripId = Integer.parseInt(getIntent().getStringExtra(AddClientsActivity.ADD_CLIENT_TO_CREATE_NEW));
                Client client = Client.newClient(
                        tripId,
                        mClientNameEditText.getText().toString(),
                        mClientPhoneEditText.getText().toString(),
                        mLastLocation.getLatitude(),
                        mLastLocation.getLongitude());
                cl.addClient(client);
                finish();
            }
        });

    }

    private String getLocation() {
        if(mLastLocation!=null) {
            return mLastLocation.getLatitude() + ";" + mLastLocation.getLongitude();
        }
        return "No Location";
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();


    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            /*if(mLastLocation!=null){
                getLocation();
            }*/
            Toast.makeText(CreateNewClientActivity.this, "You need to grant the permission!", Toast.LENGTH_SHORT).show();
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
