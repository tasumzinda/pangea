package zw.co.ncmp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.CaseFileMentee;
import zw.co.ncmp.business.CaseFileMentor;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.business.MentorVisitReport;
import zw.co.ncmp.util.AppUtil;

public class CaseFileViewActivity extends MenuBar implements View.OnClickListener, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private Facility facility;

    TextView case_file;
    Button btn_add_visit_report;
    Button btn_add_facility_challenge;
    Button btn_save;
    Button btn_add_mentee;
    Button btn_add_mentor;
    Button btn_load_challenges;
    Button btn_completed;
    Button btn_submit;

    TextView txt_view_mentees;
    TextView txt_view_mentors;
    TextView txt_visit;
    TextView txt_challenge;

    private CaseFile caseFile;
    private LinearLayout optionsLayout;
    private LinearLayout optionsLayout1;
    private LinearLayout optionsLayout2;

    private LocationManager locationManager;
    private String provider;
    private Location location;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_file_view_activity);

        optionsLayout = (LinearLayout) findViewById(R.id.optionsLayout);
        optionsLayout1 = (LinearLayout) findViewById(R.id.optionsLayout1);
        optionsLayout2 = (LinearLayout) findViewById(R.id.optionsLayout2);

        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);

        buildGoogleApiClient();
        location = getLocation();
        loadGooglePlayLocation();

        caseFile = CaseFile.get(case_file_id);
        facility = Facility.get(caseFile.facility.getId());

        btn_add_visit_report = (Button) findViewById(R.id.btn_add_visit_report);
        btn_add_visit_report.setOnClickListener(this);

        btn_add_facility_challenge = (Button) findViewById(R.id.btn_add_facility_challenge);
        btn_add_facility_challenge.setOnClickListener(this);

        btn_add_mentee = (Button) findViewById(R.id.btn_add_mentee);
        btn_add_mentee.setOnClickListener(this);

        txt_view_mentees = (TextView) findViewById(R.id.txt_view_mentees);
        txt_view_mentees.setOnClickListener(this);
        txt_view_mentees.setText("Facility Staff (" + CaseFileMentee.getCount(caseFile.getId()) + ")");

        btn_add_mentor = (Button) findViewById(R.id.btn_add_mentor);
        btn_add_mentor.setOnClickListener(this);

        txt_view_mentors = (TextView) findViewById(R.id.txt_view_mentors);
        txt_view_mentors.setOnClickListener(this);
        txt_view_mentors.setText("Team Members (" + CaseFileMentor.getCount(caseFile.getId()) + ")");

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_load_challenges = (Button) findViewById(R.id.btn_load);
        btn_load_challenges.setOnClickListener(this);

        btn_completed = (Button) findViewById(R.id.btn_completed);
        btn_completed.setVisibility(View.GONE);

        if (CaseFileMentee.getCount(caseFile.getId()) == 0) {
            optionsLayout.setVisibility(View.GONE);
            optionsLayout2.setVisibility(View.GONE);
        }

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);

        if (caseFile.checkOutDate != null) {
            btn_submit.setVisibility(View.VISIBLE);
            btn_save.setVisibility(View.GONE);
        }

        if (caseFile.dateSubmitted != null) {
            optionsLayout.setVisibility(View.GONE);
            optionsLayout1.setVisibility(View.GONE);
            optionsLayout2.setVisibility(View.GONE);
            btn_completed.setVisibility(View.VISIBLE);
        }

        case_file = (TextView) findViewById(R.id.txt_name);
        case_file.setText("Site Profile : " + AppUtil.getStringDate(caseFile.dateCreated) + " - " + facility.name);
        case_file.setOnClickListener(this);

        txt_visit = (TextView) findViewById(R.id.txt_visit);
        txt_visit.setText("Site Support Report - (" + MentorVisitReport.getCount(caseFile.getId()) + ")");
        txt_visit.setOnClickListener(this);

        txt_challenge = (TextView) findViewById(R.id.txt_challenge);
        txt_challenge.setText("Facility Challenges - (" + FacilityChallenge.getCountChallengesByCaseFile(caseFile.getId())+ ")");
        txt_challenge.setOnClickListener(this);

        setSupportActionBar(createToolBar("Site Profile - " + AppUtil.getStringDate(caseFile.dateCreated) + " " + caseFile.facility));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        onLocationChanged(mLastLocation);
        onLocationChanged(location);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == case_file.getId()) {
            intent = new Intent(this, CaseFileActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == txt_visit.getId()) {
            intent = new Intent(this, VisitReportListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == txt_challenge.getId()) {
            intent = new Intent(this, FacilityChallengeListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == btn_add_visit_report.getId()) {
            intent = new Intent(this, VisitReportActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == btn_add_facility_challenge.getId()) {
            intent = new Intent(this, FacilityChallengeActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == txt_view_mentees.getId()) {
            intent = new Intent(this, MenteeListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == btn_add_mentee.getId()) {
            intent = new Intent(this, CaseMenteeListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == txt_view_mentors.getId()) {
            intent = new Intent(this, MentorListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == btn_add_mentor.getId()) {
            intent = new Intent(this, CaseMentorListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == btn_load_challenges.getId()) {
            intent = new Intent(this, FacilityChallengeReviewListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (v.getId() == btn_save.getId()) {
            if(! isGPSEnabled()){
                AppUtil.createShortNotification(getApplicationContext(), "Please enable GPS first");
                Intent callGPSSettingIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }else{
                new AlertDialog.Builder(context)
                        .setMessage("Checking out, Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (validate()) {
                                    onLocationChanged(mLastLocation);
                                    onLocationChanged(location);
                                    if(caseFile.latitudeSubmitted != null && caseFile.longitudeSubmitted != null){
                                        caseFile.checkOutDate = new Date();
                                        caseFile.save();
                                        btn_save.setVisibility(View.GONE);
                                        Log.d("Latitude", caseFile.latitudeSubmitted.toString());
                                        Log.d("Longitude", caseFile.longitudeSubmitted.toString());
                                        btn_submit.setVisibility(View.VISIBLE);
                                        AppUtil.createLongNotification(CaseFileViewActivity.this, "Check Out Successfull");
                                    }else{
                                        AppUtil.createShortNotification(CaseFileViewActivity.this, "Please wait for GPS coordinates to load");
                                    }

                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }


        }

        if (v.getId() == btn_submit.getId()) {
            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to submit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (validate()) {
                                caseFile.dateSubmitted = new Date();
                                caseFile.save();
                                optionsLayout.setVisibility(View.GONE);
                                optionsLayout1.setVisibility(View.GONE);
                                optionsLayout2.setVisibility(View.GONE);
                                optionsLayout2.setVisibility(View.GONE);
                                btn_completed.setVisibility(View.VISIBLE);
                                AppUtil.createLongNotification(CaseFileViewActivity.this, "Submitted for Upload to Server");
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }

    }

    public boolean validate() {
        boolean valid = true;

        if (MentorVisitReport.getCount(caseFile.getId()) == 0) {
            AppUtil.createShortNotification(CaseFileViewActivity.this, "Cannot Check out Visit Report not Created");
            valid = false;
        }
        if (CaseFileMentee.getCount(caseFile.getId()) == 0) {
            AppUtil.createShortNotification(CaseFileViewActivity.this, "Cannot Check out, No Meentees Assigned");
            valid = false;
        }
        if(CaseFileMentor.getCount(caseFile.getId()) == 0){
            AppUtil.createShortNotification(this, "Cannot Check out, No Mentors Assigned");
            valid = false;
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CaseFileViewActivity.this, FacilityViewActivity.class);
        intent.putExtra(AppUtil.ID, facility.getId());
        startActivity(intent);
        finish();
    }


    @Override
    public void onLocationChanged(Location m) {
        if (m != null) {
            caseFile.longitudeSubmitted = m.getLongitude();
            caseFile.latitudeSubmitted = m.getLatitude();
        }
    }

    public void loadGooglePlayLocation() {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(provider, 400, 1, this);
            }
        } else {
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }


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
                .addConnectionCallbacks(CaseFileViewActivity.this)
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

