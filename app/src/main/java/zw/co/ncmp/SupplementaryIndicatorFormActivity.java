package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import zw.co.ncmp.business.ARTForm;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.SupplementaryIndicatorForm;
import zw.co.ncmp.util.AppUtil;

public class SupplementaryIndicatorFormActivity extends MenuBar implements View.OnClickListener {

    Spinner facility;
    Spinner period;
    EditText dateCreated;
    EditText name;
    EditText estimatedFacilityCatchmentPopulation;
    EditText numberOfActivePreARTPatients;
    EditText inPatientNumberOfPatientsInPastMonth;
    EditText inPatientNumberOfPatientsTestedPositiveInPastMonth;
    EditText inPatientNumberOfPatientsTestedForHIVInPastMonth;
    EditText inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry;
    EditText opdNumberOfPatientsInPastMonth;
    EditText opdNumberOfPatientsTestedForHIVInPastMonth;
    EditText opdNumberOfPatientsTestedPositiveInPastMonth;
    EditText opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry;
    EditText stiNumberOfPatientsInPastMonth;
    EditText stiNumberOfPatientsTestedForHIVInPastMonth;
    EditText stiNumberOfPatientsTestedPositiveInPastMonth;
    EditText stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry;

    Button btn_completed;
    Button btn_submit;
    Button btn_save;

    private SupplementaryIndicatorForm form;
    private DatePickerDialog datePickerDialog;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplement_indicator_form_activity);

        Intent intent = getIntent();
        Long form_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        name = (EditText) findViewById(R.id.name);
        period = (Spinner) findViewById(R.id.period);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);

        estimatedFacilityCatchmentPopulation = (EditText) findViewById(R.id.estimatedFacilityCatchmentPopulation);
        numberOfActivePreARTPatients = (EditText) findViewById(R.id.numberOfActivePreARTPatients);
        inPatientNumberOfPatientsInPastMonth = (EditText) findViewById(R.id.inPatientNumberOfPatientsInPastMonth);
        inPatientNumberOfPatientsTestedPositiveInPastMonth = (EditText) findViewById(R.id.inPatientNumberOfPatientsTestedPositiveInPastMonth);
        inPatientNumberOfPatientsTestedForHIVInPastMonth = (EditText) findViewById(R.id.inPatientNumberOfPatientsTestedForHIVInPastMonth);
        inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry = (EditText) findViewById(R.id.inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry);
        opdNumberOfPatientsInPastMonth = (EditText) findViewById(R.id.opdNumberOfPatientsInPastMonth);
        opdNumberOfPatientsTestedForHIVInPastMonth = (EditText) findViewById(R.id.opdNumberOfPatientsTestedForHIVInPastMonth);
        opdNumberOfPatientsTestedPositiveInPastMonth = (EditText) findViewById(R.id.opdNumberOfPatientsTestedPositiveInPastMonth);
        opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry = (EditText) findViewById(R.id.opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry);
        stiNumberOfPatientsInPastMonth = (EditText) findViewById(R.id.stiNumberOfPatientsInPastMonth);
        stiNumberOfPatientsTestedForHIVInPastMonth = (EditText) findViewById(R.id.stiNumberOfPatientsTestedForHIVInPastMonth);
        stiNumberOfPatientsTestedPositiveInPastMonth = (EditText) findViewById(R.id.stiNumberOfPatientsTestedPositiveInPastMonth);
        stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry = (EditText) findViewById(R.id.stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry);

        dateCreated = (EditText) findViewById(R.id.dateCreated);


        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCreated.setOnClickListener(this);

        facility.setClickable(false);
        facility.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                facilityArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_item, Facility.getAll());
                facilityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                facility.setAdapter(facilityArrayAdapter);
                facility_label.setVisibility(View.GONE);
                return false;
            }
        });

        ArrayAdapter<Period> periodArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, Period.getAll());
        periodArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        period.setAdapter(periodArrayAdapter);

        if (form_id != 0) {
            form = SupplementaryIndicatorForm.get(form_id);
            name.setText(form.name);
            estimatedFacilityCatchmentPopulation.setText(AppUtil.getLongValue(form.estimatedFacilityCatchmentPopulation));
            numberOfActivePreARTPatients.setText(AppUtil.getLongValue(form.numberOfActivePreARTPatients));

            inPatientNumberOfPatientsInPastMonth.setText(AppUtil.getLongValue(form.inPatientNumberOfPatientsInPastMonth));
            inPatientNumberOfPatientsTestedPositiveInPastMonth.setText(AppUtil.getLongValue(form.inPatientNumberOfPatientsTestedPositiveInPastMonth));
            inPatientNumberOfPatientsTestedForHIVInPastMonth.setText(AppUtil.getLongValue(form.inPatientNumberOfPatientsTestedForHIVInPastMonth));
            inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry.setText(AppUtil.getLongValue(form.inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry));

            opdNumberOfPatientsInPastMonth.setText(AppUtil.getLongValue(form.opdNumberOfPatientsInPastMonth));
            opdNumberOfPatientsTestedForHIVInPastMonth.setText(AppUtil.getLongValue(form.opdNumberOfPatientsTestedForHIVInPastMonth));
            opdNumberOfPatientsTestedPositiveInPastMonth.setText(AppUtil.getLongValue(form.opdNumberOfPatientsTestedPositiveInPastMonth));
            opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry.setText(AppUtil.getLongValue(form.opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry));

            stiNumberOfPatientsInPastMonth.setText(AppUtil.getLongValue(form.stiNumberOfPatientsInPastMonth));
            stiNumberOfPatientsTestedForHIVInPastMonth.setText(AppUtil.getLongValue(form.stiNumberOfPatientsTestedForHIVInPastMonth));
            stiNumberOfPatientsTestedPositiveInPastMonth.setText(AppUtil.getLongValue(form.stiNumberOfPatientsTestedPositiveInPastMonth));
            stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry.setText(AppUtil.getLongValue(form.stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry));

            updateLabel(form.dateCreated);

            int i = 0;
            for (Facility s : Facility.getAll()) {
                if (form.facility.equals(facility.getItemAtPosition(i))) {
                    facility.setSelection(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (Period s : Period.getAll()) {
                if (form.period.equals(period.getItemAtPosition(i))) {
                    period.setSelection(i);
                    break;
                }
                i++;
            }

            setSupportActionBar(createToolBar("Suppliment Indicator Update"));
        } else {
            form = new SupplementaryIndicatorForm();
            setSupportActionBar(createToolBar("Suppliment Indicator"));
        }

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_save.setBackgroundResource(R.drawable.finish_background);

        btn_completed = (Button) findViewById(R.id.btn_completed);
        btn_completed.setVisibility(View.GONE);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);

        if (form.dateCreated != null) {
            btn_submit.setVisibility(View.VISIBLE);
        }

        if (form.dateSubmitted != null) {
            btn_submit.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            btn_completed.setVisibility(View.VISIBLE);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                form.facility = (Facility) facility.getSelectedItem();
                form.name = name.getText().toString();
                form.period = (Period) period.getSelectedItem();

                form.estimatedFacilityCatchmentPopulation = AppUtil.getLongValue(estimatedFacilityCatchmentPopulation.getText().toString());
                form.numberOfActivePreARTPatients = AppUtil.getLongValue(numberOfActivePreARTPatients.getText().toString());
                form.inPatientNumberOfPatientsInPastMonth = AppUtil.getLongValue(inPatientNumberOfPatientsInPastMonth.getText().toString());
                form.inPatientNumberOfPatientsTestedPositiveInPastMonth = AppUtil.getLongValue(inPatientNumberOfPatientsTestedPositiveInPastMonth.getText().toString());
                form.inPatientNumberOfPatientsTestedForHIVInPastMonth = AppUtil.getLongValue(inPatientNumberOfPatientsTestedForHIVInPastMonth.getText().toString());
                form.inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry = AppUtil.getLongValue(inPatientNumberOfPatientsWithKnownHIVPositiveStatusOnEntry.getText().toString());
                form.opdNumberOfPatientsInPastMonth = AppUtil.getLongValue(opdNumberOfPatientsInPastMonth.getText().toString());
                form.opdNumberOfPatientsTestedForHIVInPastMonth = AppUtil.getLongValue(opdNumberOfPatientsTestedForHIVInPastMonth.getText().toString());
                form.opdNumberOfPatientsTestedPositiveInPastMonth = AppUtil.getLongValue(opdNumberOfPatientsTestedPositiveInPastMonth.getText().toString());
                form.opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry = AppUtil.getLongValue(opdNumberOfPatientsWithKnownHIVPositiveStatusOnEntry.getText().toString());
                form.stiNumberOfPatientsInPastMonth = AppUtil.getLongValue(stiNumberOfPatientsInPastMonth.getText().toString());
                form.stiNumberOfPatientsTestedForHIVInPastMonth = AppUtil.getLongValue(stiNumberOfPatientsTestedForHIVInPastMonth.getText().toString());
                form.stiNumberOfPatientsTestedPositiveInPastMonth = AppUtil.getLongValue(stiNumberOfPatientsTestedPositiveInPastMonth.getText().toString());
                form.stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry = AppUtil.getLongValue(stiNumberOfPatientsWithKnownHIVPositiveStatusOnEntry.getText().toString());

                form.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                form.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(SupplementaryIndicatorFormActivity.this, "Saved");

            } else {
                return;
            }
        }

        if (v.getId() == btn_submit.getId()) {
            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to submit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (validate()) {
                                form.dateSubmitted = new Date();
                                form.save();
                                AppUtil.createLongNotification(SupplementaryIndicatorFormActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(SupplementaryIndicatorFormActivity.this, SupplementaryIndicatorFormListActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        if (v.getId() == dateCreated.getId()) {
            datePickerDialog.show();

        }
    }

    private void updateLabel(Date date) {
        dateCreated.setText(AppUtil.getStringDate(date));
    }

    public boolean validate() {
        boolean valid = true;

        String name = dateCreated.getText().toString().toString();

        if (name.isEmpty()) {
            dateCreated.setError("Required");
            valid = false;
        } else {
            dateCreated.setError(null);
        }

        if( ! name.isEmpty()){
            if( ! checkDateFormat(name)){
                dateCreated.setError(getResources().getString(R.string.date_format_error));
                valid = false;
            }else{
                dateCreated.setError(null);
            }
        }

        if (facilityArrayAdapter == null) {
            facility_label.setVisibility(View.VISIBLE);
            facility_label.setError("Please select a facility");
            valid = false;
        }
        else {
            facility_label.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(context)
                .setMessage("Exit Form?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(SupplementaryIndicatorFormActivity.this, SupplementaryIndicatorFormListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}

