package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import zw.co.ncmp.business.ActionCategory;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.Challenge;
import zw.co.ncmp.business.ChallengeStatus;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.util.AppUtil;

public class FacilityChallengeActivity extends MenuBar implements View.OnClickListener {

    TextView txt_caseFile_name;
    TextView txt_follow_up_date;
    TextView txt_other;

    Spinner action_taken_category;
    Spinner challenge_status;
    Spinner challenge;
    Spinner achievable;
    Spinner realistic;
    Spinner specific;

    EditText action_taken;
    EditText expected_outcome;
    EditText follow_up_date;
    EditText detail;
    EditText measurementMethod;
    EditText others;
    EditText expected_completion_date;

    Button btn_save;
    Button btn_load;
    private CaseFile caseFile;
    private FacilityChallenge facilityChallenge;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_challenge_activity);
        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);

        txt_caseFile_name = (TextView) findViewById(R.id.txt_name);
        txt_follow_up_date = (TextView) findViewById(R.id.txt_follow_up_date);
        txt_other = (TextView) findViewById(R.id.txt_other);

        follow_up_date = (EditText) findViewById(R.id.follow_up_date);
        follow_up_date.setInputType(InputType.TYPE_NULL);
        expected_completion_date = (EditText) findViewById(R.id.expected_completion_date);
        expected_completion_date.setInputType(InputType.TYPE_NULL);

        action_taken = (EditText) findViewById(R.id.action_taken);
        others = (EditText) findViewById(R.id.others);
        detail = (EditText) findViewById(R.id.detail);
        measurementMethod = (EditText) findViewById(R.id.measurementMethod);
        expected_outcome = (EditText) findViewById(R.id.expected_outcome);

        action_taken_category = (Spinner) findViewById(R.id.action_taken_category);
        challenge_status = (Spinner) findViewById(R.id.challenge_status);
        challenge = (Spinner) findViewById(R.id.challenge);
        achievable = (Spinner) findViewById(R.id.achievable);
        realistic = (Spinner) findViewById(R.id.realistic);
        specific = (Spinner) findViewById(R.id.specific);

        others.setVisibility(View.GONE);
        txt_other.setVisibility(View.GONE);

        follow_up_date.setOnClickListener(this);
        expected_completion_date.setOnClickListener(this);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(follow_up_date, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(expected_completion_date, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        ArrayAdapter<ChallengeStatus> challengeStatusArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, ChallengeStatus.getAll());
        challengeStatusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        challenge_status.setAdapter(challengeStatusArrayAdapter);

        ArrayAdapter<Challenge> challengeArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, Challenge.getAll());
        challengeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        challenge.setAdapter(challengeArrayAdapter);

        ArrayAdapter<ActionCategory> actionTakenCategoryArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, ActionCategory.getAll());
        actionTakenCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        action_taken_category.setAdapter(actionTakenCategoryArrayAdapter);

        ArrayAdapter<String> yesNoAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, AppUtil.getYesNoCombo);
        yesNoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        achievable.setAdapter(yesNoAdapter);
        realistic.setAdapter(yesNoAdapter);
        specific.setAdapter(yesNoAdapter);

        challenge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Challenge c = (Challenge) challenge.getSelectedItem();
                if (c.name.equalsIgnoreCase("Other")) {
                    others.setVisibility(View.VISIBLE);
                    txt_other.setVisibility(View.VISIBLE);
                } else {
                    others.setVisibility(View.GONE);
                    txt_other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (id != 0) {
            facilityChallenge = FacilityChallenge.get(id);
            caseFile = CaseFile.get(facilityChallenge.caseFile.getId());
            detail.setText(facilityChallenge.detail);
            action_taken.setText(facilityChallenge.actionTaken);
            expected_outcome.setText(facilityChallenge.expectedOutcome);
            others.setText(facilityChallenge.others);
            measurementMethod.setText(facilityChallenge.measurementMethod);

            updateLabel(follow_up_date, facilityChallenge.follow_up_date);
            updateLabel(expected_completion_date, facilityChallenge.expected_completion_date);

            txt_caseFile_name.setText("SITE SUPPORT REPORT : " + AppUtil.getStringDate(caseFile.dateCreated) + " - " + caseFile.facility.name);
            int i = 0;
            for (Challenge s : Challenge.getAll()) {
                if (facilityChallenge.challenge.equals(challenge.getItemAtPosition(i))) {
                    challenge.setSelection(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (ActionCategory a : ActionCategory.getAll()) {
                if (facilityChallenge.actionCategory != null && facilityChallenge.actionCategory.equals(action_taken_category.getItemAtPosition(i))) {
                    action_taken_category.setSelection(i);
                    break;
                }
                i++;
            }

            for (Challenge s : Challenge.getAll()) {
                if (s.name == "Other") {
                    others.setVisibility(View.VISIBLE);
                    txt_other.setVisibility(View.VISIBLE);
                    break;
                }
            }

            i = 0;
            for (ChallengeStatus s : ChallengeStatus.getAll()) {
                if (facilityChallenge.challengeStatus.equals(challenge_status.getItemAtPosition(i))) {
                    challenge_status.setSelection(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (String s : AppUtil.getYesNoCombo) {
                if (s.equals(achievable.getItemAtPosition(i))) {
                    achievable.setSelection(i);
                }

                if (s.equals(realistic.getItemAtPosition(i))) {
                    realistic.setSelection(i);
                }

                if (s.equals(specific.getItemAtPosition(i))) {
                    specific.setSelection(i);
                }
                i++;
            }
            hideFollowUpComponets(AppUtil.getValue(facilityChallenge.challengeStatus.name));
            setSupportActionBar(createToolBar("Update Facility Challenge"));
        } else {
            caseFile = CaseFile.get(case_file_id);
            facilityChallenge = new FacilityChallenge();
            facilityChallenge.caseFile = caseFile;
            txt_caseFile_name.setText("SITE SUPPORT REPORT : " + AppUtil.getStringDate(caseFile.dateCreated) + " - " + caseFile.facility.name);
            setSupportActionBar(createToolBar("Add Facility Challenge"));
            challenge_status.setEnabled(false);
            int i = 0;
            for (ChallengeStatus s : ChallengeStatus.getAll()) {
                if (AppUtil.PENDING.equals(challenge_status.getItemAtPosition(i).toString())) {
                    challenge_status.setSelection(i);
                    break;
                }
                i++;
            }
        }

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setVisibility(View.GONE);

        if (caseFile.dateSubmitted != null) {
            btn_save.setVisibility(View.GONE);
        } else {
            btn_save.setVisibility(View.VISIBLE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                facilityChallenge.detail = detail.getText().toString();
                facilityChallenge.follow_up_date = AppUtil.getDate(follow_up_date.getText().toString());
                facilityChallenge.challenge = (Challenge) challenge.getSelectedItem();
                facilityChallenge.challengeStatus = (ChallengeStatus) challenge_status.getSelectedItem();
                facilityChallenge.actionCategory = (ActionCategory) action_taken_category.getSelectedItem();
                facilityChallenge.actionTaken = action_taken.getText().toString();
                facilityChallenge.expectedOutcome = expected_outcome.getText().toString();
                facilityChallenge.expected_completion_date = AppUtil.getDate(expected_completion_date.getText().toString());
                facilityChallenge.others = others.getText().toString();
                facilityChallenge.achievable = (String) achievable.getSelectedItem();
                facilityChallenge.realistic = (String) realistic.getSelectedItem();
                facilityChallenge.specific = (String) specific.getSelectedItem();
                facilityChallenge.measurementMethod = measurementMethod.getText().toString();
                facilityChallenge.save();
                Intent intent = new Intent(this, CaseFileViewActivity.class);
                intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
                startActivity(intent);
                finish();
            } else {
                return;
            }
        }

        if (v.getId() == follow_up_date.getId()) {
            datePickerDialog.show();
        }

        if (v.getId() == expected_completion_date.getId()) {
            datePickerDialog2.show();
        }


    }

    private void updateLabel(EditText item, Date date) {
        item.setText(AppUtil.getStringDate(date));
    }

    private void hideFollowUpComponets(String status) {
        if (AppUtil.RESOLVED.equals(status)) {
            follow_up_date.setVisibility(View.GONE);
            txt_follow_up_date.setVisibility(View.GONE);
        }
    }

    public boolean validate() {
        boolean valid = true;

        String name = detail.toString().toString();

        if (name.isEmpty()) {
            detail.setError("Required");
            valid = false;
        } else {
            detail.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FacilityChallengeActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }

}
