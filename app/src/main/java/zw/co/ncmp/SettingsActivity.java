package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.util.AppUtil;

/**
 * Created by tdhlakama on 1/19/2016.
 */
public class SettingsActivity extends MenuBar implements View.OnClickListener{

    Button logout;
    Context context =this;
    TextView txt_username;
    TextView server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(SettingsActivity.this);

        server = (TextView) findViewById(R.id.txt_more);
        server.setText("URL : " + AppUtil.getWebService(context));

        txt_username = (TextView) findViewById(R.id.txt_name);
        txt_username.setText(AppUtil.getUsername(context));

        if(!AppUtil.isUserLoggedIn(SettingsActivity.this)){
            logout.setVisibility(View.GONE);
        }
        setSupportActionBar(createToolBar("Settings"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(final View view) {

        if (view.getId() == logout.getId()) {

            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AppUtil.loginReset(context);
                            Intent intent = new Intent(context, StartActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            AppUtil.loginReset(context);
                       finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
