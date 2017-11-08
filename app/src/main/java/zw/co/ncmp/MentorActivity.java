package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Mentor;
import zw.co.ncmp.util.AppUtil;

public class MentorActivity extends MenuBar {

    Mentor mentor;
    TextView first_name;
    TextView last_name;
    TextView middle_name;
    TextView id_number;
    TextView email;
    TextView mobile_number;

     private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentor_activity);
        mentor = Mentor.getMentor(AppUtil.getWebUserId(this));
        first_name = (TextView) findViewById(R.id.first_name);
        last_name = (TextView) findViewById(R.id.last_name);
        middle_name = (TextView) findViewById(R.id.last_name);
        id_number = (TextView) findViewById(R.id.id_number);
        email = (TextView) findViewById(R.id.email);
        mobile_number = (TextView) findViewById(R.id.mobile_number);

        first_name.setText(mentor.firstName);
        last_name.setText(mentor.lastName);
        middle_name.setText(mentor.middleName);
        id_number.setText(mentor.nationalId);
        email.setText(mentor.email);
        mobile_number.setText(mentor.mobileNumber);
        setSupportActionBar(createToolBar("Mentor Details"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MentorActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
