package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import zw.co.ncmp.business.Facility;
import zw.co.ncmp.util.AppUtil;

public class FacilityActivity extends MenuBar implements View.OnClickListener{

    EditText facility_name;
    EditText facility_code;
    EditText contact_name;
    EditText contact_mobile_number;
    EditText contact_email;
    Button btn_save;
    Facility facility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_activity);

        facility_name = (EditText) findViewById(R.id.facility_name);
        facility_code = (EditText) findViewById(R.id.facility_code);
        contact_name = (EditText) findViewById(R.id.contact_name);
        contact_email = (EditText) findViewById(R.id.contact_email);
        contact_mobile_number = (EditText) findViewById(R.id.contact_mobile_number);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        if(id!=0) {
            facility = Facility.get(id);
            facility_name.setText(facility.name);
            facility_code.setText(facility.facilityCode);
            contact_mobile_number.setText(facility.contactMobileNumber);
            contact_name.setText(facility.contactName);
            contact_email.setText(facility.contactEmail);
            setSupportActionBar(createToolBar("Update Facility"));
        }else{
            facility = new Facility();
            setSupportActionBar(createToolBar("Add Facility"));
        }

        btn_save =  (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                facility.name = facility_name.getText().toString();
                facility.contactEmail = contact_email.getText().toString();
                facility.contactMobileNumber = contact_email.getText().toString();
                facility.contactName = contact_name.getText().toString();
                facility.save();
                Intent intent = new Intent(this, FacilityListActivity.class);
                startActivity(intent);
                finish();
            }else{
                return;
            }
        }
    }

    public boolean validate() {
        boolean valid = true;

        String name = facility_name.getText().toString().toString();

        if (name.isEmpty()) {
            facility_name.setError("Required");
            valid = false;
        } else {
            facility_name.setError(null);
        }

        return valid;
    }

}
