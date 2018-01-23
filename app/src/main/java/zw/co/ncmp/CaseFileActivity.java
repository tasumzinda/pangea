package zw.co.ncmp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Date;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.network.LoginWebService;
import zw.co.ncmp.util.AppUtil;

public class CaseFileActivity extends MenuBar implements View.OnClickListener, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Button btn_save;
    Button btn_load;
    private Facility facility;

    public TextView facility_name;
    public EditText date_created;
    public EditText date_submitted;
    public EditText latitude_created;
    public EditText longitude_created;
    public EditText latitude_submitted;
    public EditText longitude_submitted;

    private CaseFile caseFile;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private static final String[] PERMISSIONS_LOCATION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
    PermissionsChecker checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_file_activity);

        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        checker = new PermissionsChecker(this);

        buildGoogleApiClient();
        location = getLocation();
        loadGooglePlayLocation();

        facility_name = (TextView) findViewById(R.id.txt_name);
        date_created = (EditText) findViewById(R.id.date_created);
        date_submitted = (EditText) findViewById(R.id.date_submitted);
        latitude_created = (EditText) findViewById(R.id.latitude_created);
        longitude_created = (EditText) findViewById(R.id.longitude_created);
        latitude_submitted = (EditText) findViewById(R.id.latitude_submitted);
        longitude_submitted = (EditText) findViewById(R.id.longitude_submitted);

        longitude_created.setEnabled(false);
        latitude_created.setEnabled(false);
        date_created.setEnabled(false);
        date_submitted.setEnabled(false);
        latitude_submitted.setEnabled(false);
        longitude_submitted.setEnabled(false);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);

        onLocationChanged(mLastLocation);
        onLocationChanged(location);
        if (case_file_id != 0) {
            caseFile = CaseFile.get(case_file_id);
            facility = Facility.get(caseFile.facility.getId());
            updateDateCreatedLabel(caseFile.dateCreated);
            if (caseFile.checkOutDate != null) {
                updateDateSubmittedLabel(caseFile.checkOutDate);
            }

            longitude_created.setText(String.valueOf(caseFile.longitudeCreated));
            latitude_created.setText(String.valueOf(caseFile.latitudeCreated));
            if (caseFile.dateSubmitted != null) {
                longitude_submitted.setText(String.valueOf(caseFile.longitudeSubmitted));
                latitude_submitted.setText(String.valueOf(caseFile.latitudeSubmitted));
            }
            facility_name.setText("SITE PROFILE : " + AppUtil.getStringDate(caseFile.dateCreated) + " " + facility);
            setSupportActionBar(createToolBar("View Case Report"));
            btn_save.setVisibility(View.GONE);
            btn_load.setVisibility(View.GONE);
        } else {
            facility = Facility.get(id);
            caseFile = new CaseFile();
            caseFile.dateCreated = new Date();
            updateDateCreatedLabel(caseFile.dateCreated);
            caseFile.facility = facility;
            facility_name.setText("SITE PROFILE : " + AppUtil.getStringDate(caseFile.dateCreated) + " " + facility);
            setSupportActionBar(createToolBar("Check In Case File"));
        }


        if (caseFile.serverId != null) {
            btn_load.setVisibility(View.GONE);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == btn_save.getId()) {
            if (caseFile.getId() == null) {
                try {
                    caseFile.longitudeCreated = Double.valueOf(longitude_created.getText().toString());
                    caseFile.latitudeCreated = Double.valueOf(latitude_created.getText().toString());
                    caseFile.save();
                    intent = new Intent(this, FacilityViewActivity.class);
                    intent.putExtra(AppUtil.ID, facility.getId());
                    AppUtil.createShortNotification(CaseFileActivity.this, "Check in successful");
                } catch (Exception e) {
                    AppUtil.createShortNotification(CaseFileActivity.this, "Check In Failed - location Unavailable");
                    return;
                }
            }
        }

        if (v.getId() == btn_load.getId()) {
            if(! isGPSEnabled()){
                AppUtil.createShortNotification(getApplicationContext(), "Please enable GPS first");
                Intent callGPSSettingIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }
            onLocationChanged(mLastLocation);
            onLocationChanged(location);
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }

    private void updateDateCreatedLabel(Date date) {
        date_created.setText(AppUtil.getStringDate(date));
    }

    private void updateDateSubmittedLabel(Date date) {
        date_submitted.setText(AppUtil.getStringDate(date));
    }


    @Override
    public void onBackPressed() {
        if (caseFile.getId() == null) {
            Intent intent = new Intent(CaseFileActivity.this, FacilityViewActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(CaseFileActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onLocationChanged(Location m) {
        Log.d("Test", String.valueOf(m == null));
        if (m != null) {
            longitude_created.setText(String.valueOf(m.getLongitude()));
            latitude_created.setText(String.valueOf(m.getLatitude()));
        }
    }

    public void loadGooglePlayLocation() {
        if(checker.lacksPermissions(PERMISSIONS_LOCATION)){
            PermissionsActivity.startActivityForResult(this, 0, PERMISSIONS_LOCATION);
        }else{
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

    }

    private Location getLocation() {
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null && !provider.equals("")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    location = locationManager.getLastKnownLocation(provider);
                }
            } else {
                location = locationManager.getLastKnownLocation(provider);
            }
        }
        return location;
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(provider, 400, 1, this);
            }
        } else {
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }*/
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(this);
            }
        } else {
            locationManager.removeUpdates(this);
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {


    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(CaseFileActivity.this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    public boolean isGPSEnabled(){
        LocationManager locationManager = (LocationManager)
                getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}

