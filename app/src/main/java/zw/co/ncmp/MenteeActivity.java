package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.Designation;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Mentee;
import zw.co.ncmp.business.Qualification;
import zw.co.ncmp.util.AppUtil;

public class MenteeActivity extends MenuBar implements View.OnClickListener {

    TextView txt_facility_name;
    EditText first_name;
    EditText last_name;
    EditText middle_name;
    EditText id_number;
    EditText mobile_number;
    EditText date_of_birth;
    Spinner qualification;
    Spinner designation;

    Button btn_save;
    private Mentee mentee;
    private CaseFile caseFile;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentee_activity);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        Long mentee_id = intent.getLongExtra(AppUtil.MENTEE_ID, 0);
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);

        txt_facility_name = (TextView) findViewById(R.id.txt_name);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        middle_name = (EditText) findViewById(R.id.middle_name);
        id_number = (EditText) findViewById(R.id.id_number);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        date_of_birth = (EditText) findViewById(R.id.date_of_birth);
        date_of_birth.setInputType(InputType.TYPE_NULL);
        qualification = (Spinner) findViewById(R.id.qualification);
        designation = (Spinner) findViewById(R.id.designation);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        date_of_birth.setOnClickListener(this);

        ArrayAdapter<Qualification> qualificationArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, Qualification.getAll());
        qualificationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification.setAdapter(qualificationArrayAdapter);

        ArrayAdapter<Designation> designationArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, Designation.getAll());
        designationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designation.setAdapter(designationArrayAdapter);

        if (mentee_id != 0) {
            mentee = Mentee.get(mentee_id);
            first_name.setText(mentee.firstName);
            last_name.setText(mentee.lastName);
            middle_name.setText(mentee.middleName);
            id_number.setText(mentee.nationalId);
            mobile_number.setText(mentee.mobileNumber);
            updateLabel(mentee.dateOfBirth);

            int i = 0;
            for (Qualification s : Qualification.getAll()) {
                if (mentee.qualification != null && mentee.qualification.equals(qualification.getItemAtPosition(i))) {
                    qualification.setSelection(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (Designation s : Designation.getAll()) {
                if (mentee.designation != null && mentee.designation.equals(designation.getItemAtPosition(i))) {
                    designation.setSelection(i);
                    break;
                }
                i++;
            }

            txt_facility_name.setText("Facility : " + mentee.facility.name);
            setSupportActionBar(createToolBar("Update Detail"));
        } else {
            mentee = new Mentee();
            if (id != 0) {
                mentee.facility = Facility.get(id);
            }
            if (case_file_id != 0) {
                caseFile = CaseFile.get(case_file_id);
                mentee.facility = caseFile.facility;
            }
            txt_facility_name.setText("Facility : " + mentee.facility.name);
            setSupportActionBar(createToolBar("Add Mentee"));
        }

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                mentee.firstName = first_name.getText().toString();
                mentee.lastName = last_name.getText().toString();
                mentee.middleName = middle_name.getText().toString();
                mentee.nationalId = id_number.getText().toString();
                mentee.mobileNumber = mobile_number.getText().toString();
                mentee.dateOfBirth = AppUtil.getDate(date_of_birth.getText().toString());
                mentee.qualification = (Qualification) qualification.getSelectedItem();
                mentee.designation = (Designation) designation.getSelectedItem();
                mentee.save();
                if (caseFile != null) {
                    intent = new Intent(this, CaseMenteeListActivity.class);
                    intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
                } else {
                    intent = new Intent(this, FacilityViewActivity.class);
                    intent.putExtra(AppUtil.ID, mentee.facility.getId());
                }
                startActivity(intent);
                finish();
            } else {
                return;
            }
        }

        if (v.getId() == date_of_birth.getId()) {
            datePickerDialog.show();
        }
    }

    private void updateLabel(Date date) {
        date_of_birth.setText(AppUtil.getStringDate(date));
    }

    public boolean validate() {
        boolean valid = true;

        String name = first_name.getText().toString().toString();

        if (name.isEmpty()) {
            first_name.setError("Required");
            valid = false;
        } else {
            first_name.setError(null);
        }

        name = last_name.getText().toString().toString();

        if (name.isEmpty()) {
            last_name.setError("Required");
            valid = false;
        } else {
            last_name.setError(null);
        }

        name = date_of_birth.getText().toString().toString();

        if (name.isEmpty()) {
            date_of_birth.setError("Required");
            valid = false;
        } else {
            date_of_birth.setError(null);
        }

        if( ! name.isEmpty()){
            if( ! checkDateFormat(name)){
                date_of_birth.setError(getResources().getString(R.string.date_format_error));
                valid = false;
            }else{
                date_of_birth.setError(null);
            }
        }

        name = mobile_number.getText().toString().toString();

        if (name.isEmpty()) {
            mobile_number.setError("Required");
            valid = false;
        } else {
            mobile_number.setError(null);
        }

        name = id_number.getText().toString().toString();

        if (name.isEmpty()) {
            id_number.setError("Required");
            valid = false;
        } else {
            id_number.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;
        if (caseFile != null) {
            intent = new Intent(this, CaseMenteeListActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        } else {
            intent = new Intent(this, FacilityViewActivity.class);
            intent.putExtra(AppUtil.ID, mentee.facility.getId());
        }
        startActivity(intent);
        finish();
    }
}
