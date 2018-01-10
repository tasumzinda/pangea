package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.*;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.PMTCTEIDForm;
import zw.co.ncmp.business.PMTCTEIDP36;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @uthor Tasu Muzinda
 */
public class PMTCTEIDP36Activity extends MenuBar implements View.OnClickListener{

    Spinner facility;
    EditText dateCreated;
    Spinner period;
    EditText name;

    Button btn_completed;
    Button btn_submit;
    Button btn_question_one;
    Button btn_question_two;
    Button btn_question_three;

    Button btn_save;
    private PMTCTEIDP36 form;
    private DatePickerDialog datePickerDialog;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.pmtct_eid_p36);

        Intent intent = getIntent();
        Long form_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        name = (EditText) findViewById(R.id.name);
        period = (Spinner) findViewById(R.id.period);
        dateCreated = (EditText) findViewById(R.id.dateCreated);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);
        btn_question_one = (Button) findViewById(R.id.btn_question_one);
        btn_question_one.setText("P36 – HIV Exposed Infants with DNA PCR sample collected");
        btn_question_one.setOnClickListener(this);

        btn_question_two = (Button) findViewById(R.id.btn_question_two);
        btn_question_two.setText("P37 – HIV Exposed Infants DNA-PCR positive");
        btn_question_two.setOnClickListener(this);

        btn_question_three = (Button) findViewById(R.id.btn_question_three);
        btn_question_three.setText("P38 – Infants initiated on ART");
        btn_question_three.setOnClickListener(this);

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
            form = PMTCTEIDP36.get(form_id);
            name.setText(form.name);
            //numerator.setText(AppUtil.getLongValue(form.getNumerator()));
            upDateForm();
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

            setSupportActionBar(createToolBar("PMTCT_EID Update"));
        } else {
            form = new PMTCTEIDP36();
            setSupportActionBar(createToolBar("PMTCT_EID"));
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

    private void updateLabel(Date date) {
        dateCreated.setText(AppUtil.getStringDate(date));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btn_question_one.getId()){
            questionOne();
        }

        if(v.getId() == btn_question_two.getId()){
            questionTwo();
        }

        if(v.getId() == btn_question_three.getId()){
            questionThree();
        }

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                form.facility = (Facility) facility.getSelectedItem();
                form.name = name.getText().toString();
                form.period = (Period) period.getSelectedItem();
                form.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                form.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(PMTCTEIDP36Activity.this, "Saved");
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
                                AppUtil.createLongNotification(PMTCTEIDP36Activity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(PMTCTEIDP36Activity.this, PMTCTEIDP36ListActivity.class);
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

    public void questionOne() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pmtct_eid_p36_dialogue);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("P36 - HIV Exposed Infants with DNA PCR sample collected");

        final TextView total = (TextView) dialog.findViewById(R.id.total);
        total.setText(AppUtil.getLongValue(form.getP36Total()));

        final EditText lessThanOrEqualToTwoMonths = (EditText) dialog.findViewById(R.id.lessThanOrEqualToTwoMonths);
        final EditText threeToTwelveMonths = (EditText) dialog.findViewById(R.id.threeToTwelveMonths);
        final EditText thirteenToTwentyFour = (EditText) dialog.findViewById(R.id.thirteenToTwentyFour);

        if (form != null) {
            lessThanOrEqualToTwoMonths.setText(AppUtil.getLongValue(form.lessThanTwo));
            threeToTwelveMonths.setText(AppUtil.getLongValue(form.threeToTwelve));
            thirteenToTwentyFour.setText(AppUtil.getLongValue(form.thirteenToTwentyFour));
        }

        List<EditText> list = new ArrayList<>();
        list.add(lessThanOrEqualToTwoMonths);
        list.add(threeToTwelveMonths);
        list.add(thirteenToTwentyFour);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.lessThanTwo = AppUtil.getLongValue(lessThanOrEqualToTwoMonths.getText().toString());
                        form.threeToTwelve = AppUtil.getLongValue(threeToTwelveMonths.getText().toString());
                        form.thirteenToTwentyFour = AppUtil.getLongValue(thirteenToTwentyFour.getText().toString());
                        total.setText(AppUtil.getLongValue(form.getP36Total()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.lessThanTwo = AppUtil.getLongValue(lessThanOrEqualToTwoMonths.getText().toString());
                form.threeToTwelve = AppUtil.getLongValue(threeToTwelveMonths.getText().toString());
                form.thirteenToTwentyFour = AppUtil.getLongValue(thirteenToTwentyFour.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionTwo() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pmtct_eid_p36_dialogue);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("P37 – HIV Exposed Infants DNA-PCR positive");

        final TextView total = (TextView) dialog.findViewById(R.id.total);
        total.setText(AppUtil.getLongValue(form.getP37Total()));

        final EditText lessThanOrEqualToTwoMonths = (EditText) dialog.findViewById(R.id.lessThanOrEqualToTwoMonths);
        final EditText threeToTwelveMonths = (EditText) dialog.findViewById(R.id.threeToTwelveMonths);
        final EditText thirteenToTwentyFour = (EditText) dialog.findViewById(R.id.thirteenToTwentyFour);

        if (form != null) {
            lessThanOrEqualToTwoMonths.setText(AppUtil.getLongValue(form.lessThanTwo1));
            threeToTwelveMonths.setText(AppUtil.getLongValue(form.threeToTwelve1));
            thirteenToTwentyFour.setText(AppUtil.getLongValue(form.thirteenToTwentyFour1));
        }

        List<EditText> list = new ArrayList<>();
        list.add(lessThanOrEqualToTwoMonths);
        list.add(threeToTwelveMonths);
        list.add(thirteenToTwentyFour);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.lessThanTwo1 = AppUtil.getLongValue(lessThanOrEqualToTwoMonths.getText().toString());
                        form.threeToTwelve1 = AppUtil.getLongValue(threeToTwelveMonths.getText().toString());
                        form.thirteenToTwentyFour1 = AppUtil.getLongValue(thirteenToTwentyFour.getText().toString());
                        total.setText(AppUtil.getLongValue(form.getP37Total()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.lessThanTwo1 = AppUtil.getLongValue(lessThanOrEqualToTwoMonths.getText().toString());
                form.threeToTwelve1 = AppUtil.getLongValue(threeToTwelveMonths.getText().toString());
                form.thirteenToTwentyFour1 = AppUtil.getLongValue(thirteenToTwentyFour.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionThree() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pmtct_eid_p36_dialogue);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("P38 – Infants initiated on ART");

        final TextView total = (TextView) dialog.findViewById(R.id.total);
        total.setText(AppUtil.getLongValue(form.getP38Total()));

        final EditText lessThanOrEqualToTwoMonths = (EditText) dialog.findViewById(R.id.lessThanOrEqualToTwoMonths);
        final EditText threeToTwelveMonths = (EditText) dialog.findViewById(R.id.threeToTwelveMonths);
        final EditText thirteenToTwentyFour = (EditText) dialog.findViewById(R.id.thirteenToTwentyFour);

        if (form != null) {
            lessThanOrEqualToTwoMonths.setText(AppUtil.getLongValue(form.lessThanTwo2));
            threeToTwelveMonths.setText(AppUtil.getLongValue(form.threeToTwelve2));
            thirteenToTwentyFour.setText(AppUtil.getLongValue(form.thirteenToTwentyFour2));
        }

        List<EditText> list = new ArrayList<>();
        list.add(lessThanOrEqualToTwoMonths);
        list.add(threeToTwelveMonths);
        list.add(thirteenToTwentyFour);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.lessThanTwo2 = AppUtil.getLongValue(lessThanOrEqualToTwoMonths.getText().toString());
                        form.threeToTwelve2 = AppUtil.getLongValue(threeToTwelveMonths.getText().toString());
                        form.thirteenToTwentyFour2 = AppUtil.getLongValue(thirteenToTwentyFour.getText().toString());
                        total.setText(AppUtil.getLongValue(form.getP38Total()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.lessThanTwo2 = AppUtil.getLongValue(lessThanOrEqualToTwoMonths.getText().toString());
                form.threeToTwelve2 = AppUtil.getLongValue(threeToTwelveMonths.getText().toString());
                form.thirteenToTwentyFour2 = AppUtil.getLongValue(thirteenToTwentyFour.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void upDateForm() {

        btn_question_one.setText("P36 - HIV Exposed Infants with DNA PCR sample collected [ " + form.getP36Total()+ " ]");
        btn_question_two.setText("P37 – HIV Exposed Infants DNA-PCR positive [ " + form.getP37Total()+ " ]");
        btn_question_three.setText("P38 – Infants initiated on ART [ " + form.getP38Total()+ " ]");
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(context)
                .setMessage("Exit Form?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(PMTCTEIDP36Activity.this, PMTCTEIDP36ListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
