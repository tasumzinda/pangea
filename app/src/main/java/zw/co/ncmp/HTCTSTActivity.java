package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.*;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.HTCTST;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HTCTSTActivity extends MenuBar implements View.OnClickListener{


    Spinner facility;
    EditText dateCreated;
    Spinner period;
    EditText name;
    Button btn_save;
    Button btn_completed;
    Button btn_submit;
    Button btn_pitc_positive;
    Button btn_pitc_negative;
    EditText numerator;
    ArrayAdapter<Facility> facilityArrayAdapter;
    private DatePickerDialog datePickerDialog;
    EditText facility_label;
    EditText pitc_positive;
    EditText pitc_negative;
    EditText malnutritionPositive;
    EditText malnutritionNegative;
    Button tbClinicsPositive;
    Button tbClinicsNegative;
    Button ancClinicsPositive;
    Button ancClinicsNegative;
    private HTCTST form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htctst);
        Intent intent = getIntent();
        Long form_id = intent.getLongExtra(AppUtil.ID, 0);
        facility = (Spinner) findViewById(R.id.facility);
        name = (EditText) findViewById(R.id.name);
        period = (Spinner) findViewById(R.id.period);
        numerator = (EditText) findViewById(R.id.numerator);
        btn_pitc_negative = (Button) findViewById(R.id.btn_pitc_negative);
        btn_pitc_positive = (Button) findViewById(R.id.btn_pitc_positive);
        pitc_negative = (EditText) findViewById(R.id.pitc_negative);
        pitc_positive = (EditText) findViewById(R.id.pitc_positive);
        malnutritionNegative = (EditText) findViewById(R.id.malnutritionNegative);
        malnutritionPositive = (EditText) findViewById(R.id.malnutritionPositive);
        tbClinicsNegative = (Button) findViewById(R.id.btn_tb_clinics_negative);
        tbClinicsPositive = (Button) findViewById(R.id.btn_tb_clinics_positive);
        ancClinicsNegative = (Button) findViewById(R.id.btn_anc_clinics_negative);
        ancClinicsPositive = (Button) findViewById(R.id.btn_anc_clinics_positive);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);
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
            form = HTCTST.get(form_id);
            numerator.setText(String.valueOf(form.numerator));
            name.setText(String.valueOf(form.name));
            pitc_negative.setText(AppUtil.getLongValue(form.pitcNegativeLessThan5));
            pitc_positive.setText(AppUtil.getLongValue(form.pitcPositiveLessThan5));
            malnutritionNegative.setText(AppUtil.getLongValue(form.malnutritionNegativeLessThan5));
            malnutritionPositive.setText(AppUtil.getLongValue(form.malnutritionPositiveLessThan5));
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

            setSupportActionBar(createToolBar("HTC_TST Update"));
        } else {
            form = new HTCTST();
            setSupportActionBar(createToolBar("HTC_TST"));
        }
        btn_pitc_negative.setOnClickListener(this);
        btn_pitc_negative.setText(R.string.pitc_negative);
        btn_pitc_positive.setText(R.string.pitc_positive);
        btn_pitc_positive.setOnClickListener(this);

        tbClinicsNegative.setOnClickListener(this);
        tbClinicsNegative.setText(R.string.tb_clinics_negative);
        tbClinicsPositive.setText(R.string.tb_clinics_positive);
        tbClinicsPositive.setOnClickListener(this);

        ancClinicsNegative.setOnClickListener(this);
        ancClinicsNegative.setText(R.string.anc_clinics_negative);
        ancClinicsPositive.setText(R.string.anc_clinics_positive);
        ancClinicsPositive.setOnClickListener(this);

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
        upDateForm();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        /*if (v.getId() == btn_pitc_positive.getId()) {
            pitcPositive();
        }

        if(v.getId() == btn_pitc_negative.getId()){
            pitcNegative();
        }

        if(v.getId() == tbClinicsNegative.getId()){
            tbClinicsNegative();
        }

        if(v.getId() == tbClinicsPositive.getId()){
            tbClinicsPositive();
        }

        if(v.getId() == ancClinicsPositive.getId()){
            ancClinicsPositive();
        }

        if(v.getId() == ancClinicsNegative.getId()){
            ancClinicsNegative();
        }

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                form.facility = (Facility) facility.getSelectedItem();
                form.name = name.getText().toString();
                form.period = (Period) period.getSelectedItem();
                form.numerator = AppUtil.getLongValue(numerator.getText().toString());
                form.pitcNegativeLessThan5 = AppUtil.getLongValue(pitc_negative.getText().toString());
                form.pitcPositiveLessThan5 = AppUtil.getLongValue(pitc_positive.getText().toString());
                form.malnutritionNegativeLessThan5 = AppUtil.getLongValue(malnutritionNegative.getText().toString());
                form.malnutritionPositiveLessThan5 = AppUtil.getLongValue(malnutritionPositive.getText().toString());
                form.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                form.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(HTCTSTActivity.this, "Saved");
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
                                AppUtil.createLongNotification(HTCTSTActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(HTCTSTActivity.this, HTCTListActivity.class);
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
        }*/


    }


    /*public void pitcPositive() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.pitcMalePositive()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.pitcFemalePositive()));

        final EditText maleLessThanOne = (EditText) dialog.findViewById(R.id.maleLessThanOne);
        final EditText femaleLessThanOne = (EditText) dialog.findViewById(R.id.femaleLessThanOne);
        final EditText maleOneToFour = (EditText) dialog.findViewById(R.id.maleOneToFour);
        final EditText femaleOneToFour = (EditText) dialog.findViewById(R.id.femaleOneToFour);
        final EditText maleFiveToNine = (EditText) dialog.findViewById(R.id.maleFiveToNine);
        final EditText femaleFiveToNine = (EditText) dialog.findViewById(R.id.femaleFiveToNine);
        final EditText maleTenToFourteen = (EditText) dialog.findViewById(R.id.maleTenToFourteen);
        final EditText femaleTenToFourteen = (EditText) dialog.findViewById(R.id.femaleTenToFourteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFortyNine);
        final EditText femaleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (form != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(form.maleLessThanOne));
            femaleLessThanOne.setText(AppUtil.getLongValue(form.femaleLessThanOne));
            maleOneToFour.setText(AppUtil.getLongValue(form.maleOneToFour));
            femaleOneToFour.setText(AppUtil.getLongValue(form.femaleOneToFour));
            maleFiveToNine.setText(AppUtil.getLongValue(form.maleFiveToNine));
            femaleFiveToNine.setText(AppUtil.getLongValue(form.femaleFiveToNine));
            maleTenToFourteen.setText(AppUtil.getLongValue(form.maleTenToFourteen));
            femaleTenToFourteen.setText(AppUtil.getLongValue(form.femaleTenToFourteen));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(form.maleFifteenToNineteen));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(form.femaleFifteenToNineteen));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.maleTwentyToTwentyFour));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.femaleTwentyToTwentyFour));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToFortyNine));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToFortyNine));
            maleFiftyPlus.setText(AppUtil.getLongValue(form.maleFiftyPlus));
            femaleFiftyPlus.setText(AppUtil.getLongValue(form.femaleFiftyPlus));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanOne);
        list.add(maleOneToFour);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.maleLessThanOne = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        form.maleOneToFour = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        form.maleFiveToNine = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        form.maleTenToFourteen = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        form.maleFifteenToNineteen = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        form.maleTwentyToTwentyFour = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        form.maleTwentyFiveToFortyNine = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.pitcMalePositive()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanOne);
        list.add(femaleOneToFour);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.femaleLessThanOne = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        form.femaleOneToFour = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        form.femaleFiveToNine = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        form.femaleTenToFourteen = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        form.femaleFifteenToNineteen = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        form.femaleTwentyToTwentyFour = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        form.femaleTwentyFiveToFortyNine = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.pitcFemalePositive()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.maleLessThanOne = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                form.femaleLessThanOne = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                form.maleOneToFour = AppUtil.getLongValue(maleOneToFour.getText().toString());
                form.femaleOneToFour = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                form.maleFiveToNine = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                form.femaleFiveToNine = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                form.maleTenToFourteen = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                form.femaleTenToFourteen = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                form.maleFifteenToNineteen = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                form.femaleFifteenToNineteen = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                form.maleTwentyToTwentyFour = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                form.femaleTwentyToTwentyFour = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                form.maleTwentyFiveToFortyNine = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                form.femaleTwentyFiveToFortyNine = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void pitcNegative() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.pitcMaleNegative()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.pitcFemaleNegative()));

        final EditText maleLessThanOne = (EditText) dialog.findViewById(R.id.maleLessThanOne);
        final EditText femaleLessThanOne = (EditText) dialog.findViewById(R.id.femaleLessThanOne);
        final EditText maleOneToFour = (EditText) dialog.findViewById(R.id.maleOneToFour);
        final EditText femaleOneToFour = (EditText) dialog.findViewById(R.id.femaleOneToFour);
        final EditText maleFiveToNine = (EditText) dialog.findViewById(R.id.maleFiveToNine);
        final EditText femaleFiveToNine = (EditText) dialog.findViewById(R.id.femaleFiveToNine);
        final EditText maleTenToFourteen = (EditText) dialog.findViewById(R.id.maleTenToFourteen);
        final EditText femaleTenToFourteen = (EditText) dialog.findViewById(R.id.femaleTenToFourteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFortyNine);
        final EditText femaleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (form != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(form.maleLessThanOne1));
            femaleLessThanOne.setText(AppUtil.getLongValue(form.femaleLessThanOne1));
            maleOneToFour.setText(AppUtil.getLongValue(form.maleOneToFour1));
            femaleOneToFour.setText(AppUtil.getLongValue(form.femaleOneToFour1));
            maleFiveToNine.setText(AppUtil.getLongValue(form.maleFiveToNine1));
            femaleFiveToNine.setText(AppUtil.getLongValue(form.femaleFiveToNine1));
            maleTenToFourteen.setText(AppUtil.getLongValue(form.maleTenToFourteen1));
            femaleTenToFourteen.setText(AppUtil.getLongValue(form.femaleTenToFourteen1));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(form.maleFifteenToNineteen1));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(form.femaleFifteenToNineteen1));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.maleTwentyToTwentyFour1));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.femaleTwentyToTwentyFour1));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToFortyNine1));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToFortyNine1));
            maleFiftyPlus.setText(AppUtil.getLongValue(form.maleFiftyPlus1));
            femaleFiftyPlus.setText(AppUtil.getLongValue(form.femaleFiftyPlus1));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanOne);
        list.add(maleOneToFour);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.maleLessThanOne1 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        form.maleOneToFour1 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        form.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        form.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        form.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        form.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        form.maleTwentyFiveToFortyNine1 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.pitcMaleNegative()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanOne);
        list.add(femaleOneToFour);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.femaleLessThanOne1 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        form.femaleOneToFour1 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        form.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        form.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        form.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        form.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        form.femaleTwentyFiveToFortyNine1 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.pitcFemaleNegative()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.maleLessThanOne1 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                form.femaleLessThanOne1 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                form.maleOneToFour1 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                form.femaleOneToFour1 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                form.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                form.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                form.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                form.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                form.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                form.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                form.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                form.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                form.maleTwentyFiveToFortyNine1 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                form.femaleTwentyFiveToFortyNine1 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void tbClinicsNegative() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.tbClinicsMaleNegative()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.tbClinicsFemaleTestedNegative()));

        final EditText maleLessThanOne = (EditText) dialog.findViewById(R.id.maleLessThanOne);
        final EditText femaleLessThanOne = (EditText) dialog.findViewById(R.id.femaleLessThanOne);
        final EditText maleOneToFour = (EditText) dialog.findViewById(R.id.maleOneToFour);
        final EditText femaleOneToFour = (EditText) dialog.findViewById(R.id.femaleOneToFour);
        final EditText maleFiveToNine = (EditText) dialog.findViewById(R.id.maleFiveToNine);
        final EditText femaleFiveToNine = (EditText) dialog.findViewById(R.id.femaleFiveToNine);
        final EditText maleTenToFourteen = (EditText) dialog.findViewById(R.id.maleTenToFourteen);
        final EditText femaleTenToFourteen = (EditText) dialog.findViewById(R.id.femaleTenToFourteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFortyNine);
        final EditText femaleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (form != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(form.maleLessThanOne2));
            femaleLessThanOne.setText(AppUtil.getLongValue(form.femaleLessThanOne2));
            maleOneToFour.setText(AppUtil.getLongValue(form.maleOneToFour2));
            femaleOneToFour.setText(AppUtil.getLongValue(form.femaleOneToFour2));
            maleFiveToNine.setText(AppUtil.getLongValue(form.maleFiveToNine2));
            femaleFiveToNine.setText(AppUtil.getLongValue(form.femaleFiveToNine2));
            maleTenToFourteen.setText(AppUtil.getLongValue(form.maleTenToFourteen2));
            femaleTenToFourteen.setText(AppUtil.getLongValue(form.femaleTenToFourteen2));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(form.maleFifteenToNineteen2));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(form.femaleFifteenToNineteen2));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.maleTwentyToTwentyFour2));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.femaleTwentyToTwentyFour2));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToFortyNine2));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToFortyNine2));
            maleFiftyPlus.setText(AppUtil.getLongValue(form.maleFiftyPlus2));
            femaleFiftyPlus.setText(AppUtil.getLongValue(form.femaleFiftyPlus2));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanOne);
        list.add(maleOneToFour);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.maleLessThanOne2 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        form.maleOneToFour2 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        form.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        form.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        form.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        form.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        form.maleTwentyFiveToFortyNine2 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.tbClinicsMaleNegative()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanOne);
        list.add(femaleOneToFour);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.femaleLessThanOne2 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        form.femaleOneToFour2 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        form.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        form.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        form.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        form.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        form.femaleTwentyFiveToFortyNine2 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.tbClinicsFemaleTestedNegative()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.maleLessThanOne2 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                form.femaleLessThanOne2 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                form.maleOneToFour2 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                form.femaleOneToFour2 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                form.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                form.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                form.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                form.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                form.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                form.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                form.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                form.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                form.maleTwentyFiveToFortyNine2 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                form.femaleTwentyFiveToFortyNine2 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void tbClinicsPositive() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.tbClinicsMalePositive()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.tbClinicsFemaleTestedPositive()));

        final EditText maleLessThanOne = (EditText) dialog.findViewById(R.id.maleLessThanOne);
        final EditText femaleLessThanOne = (EditText) dialog.findViewById(R.id.femaleLessThanOne);
        final EditText maleOneToFour = (EditText) dialog.findViewById(R.id.maleOneToFour);
        final EditText femaleOneToFour = (EditText) dialog.findViewById(R.id.femaleOneToFour);
        final EditText maleFiveToNine = (EditText) dialog.findViewById(R.id.maleFiveToNine);
        final EditText femaleFiveToNine = (EditText) dialog.findViewById(R.id.femaleFiveToNine);
        final EditText maleTenToFourteen = (EditText) dialog.findViewById(R.id.maleTenToFourteen);
        final EditText femaleTenToFourteen = (EditText) dialog.findViewById(R.id.femaleTenToFourteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFortyNine);
        final EditText femaleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (form != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(form.maleLessThanOne3));
            femaleLessThanOne.setText(AppUtil.getLongValue(form.femaleLessThanOne3));
            maleOneToFour.setText(AppUtil.getLongValue(form.maleOneToFour3));
            femaleOneToFour.setText(AppUtil.getLongValue(form.femaleOneToFour3));
            maleFiveToNine.setText(AppUtil.getLongValue(form.maleFiveToNine3));
            femaleFiveToNine.setText(AppUtil.getLongValue(form.femaleFiveToNine3));
            maleTenToFourteen.setText(AppUtil.getLongValue(form.maleTenToFourteen3));
            femaleTenToFourteen.setText(AppUtil.getLongValue(form.femaleTenToFourteen3));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(form.maleFifteenToNineteen3));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(form.femaleFifteenToNineteen3));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.maleTwentyToTwentyFour3));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.femaleTwentyToTwentyFour3));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToFortyNine3));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToFortyNine3));
            maleFiftyPlus.setText(AppUtil.getLongValue(form.maleFiftyPlus3));
            femaleFiftyPlus.setText(AppUtil.getLongValue(form.femaleFiftyPlus3));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanOne);
        list.add(maleOneToFour);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.maleLessThanOne3 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        form.maleOneToFour3 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        form.maleFiveToNine3 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        form.maleTenToFourteen3 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        form.maleFifteenToNineteen3 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        form.maleTwentyToTwentyFour3 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        form.maleTwentyFiveToFortyNine3 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.tbClinicsMalePositive()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanOne);
        list.add(femaleOneToFour);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.femaleLessThanOne3 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        form.femaleOneToFour3 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        form.femaleFiveToNine3 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        form.femaleTenToFourteen3 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        form.femaleFifteenToNineteen3 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        form.femaleTwentyToTwentyFour3 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        form.femaleTwentyFiveToFortyNine3 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.tbClinicsFemaleTestedPositive()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.maleLessThanOne3 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                form.femaleLessThanOne3 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                form.maleOneToFour3 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                form.femaleOneToFour3 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                form.maleFiveToNine3 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                form.femaleFiveToNine3 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                form.maleTenToFourteen3 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                form.femaleTenToFourteen3 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                form.maleFifteenToNineteen3 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                form.femaleFifteenToNineteen3 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                form.maleTwentyToTwentyFour3 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                form.femaleTwentyToTwentyFour3 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                form.maleTwentyFiveToFortyNine3 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                form.femaleTwentyFiveToFortyNine3 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void ancClinicsPositive() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.ancMalePositive()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.ancFemalePositive()));

        final EditText maleLessThanOne = (EditText) dialog.findViewById(R.id.maleLessThanOne);
        final EditText femaleLessThanOne = (EditText) dialog.findViewById(R.id.femaleLessThanOne);
        final EditText maleOneToFour = (EditText) dialog.findViewById(R.id.maleOneToFour);
        final EditText femaleOneToFour = (EditText) dialog.findViewById(R.id.femaleOneToFour);
        final EditText maleFiveToNine = (EditText) dialog.findViewById(R.id.maleFiveToNine);
        final EditText femaleFiveToNine = (EditText) dialog.findViewById(R.id.femaleFiveToNine);
        final EditText maleTenToFourteen = (EditText) dialog.findViewById(R.id.maleTenToFourteen);
        final EditText femaleTenToFourteen = (EditText) dialog.findViewById(R.id.femaleTenToFourteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFortyNine);
        final EditText femaleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (form != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(form.maleLessThanOne4));
            femaleLessThanOne.setText(AppUtil.getLongValue(form.femaleLessThanOne4));
            maleOneToFour.setText(AppUtil.getLongValue(form.maleOneToFour4));
            femaleOneToFour.setText(AppUtil.getLongValue(form.femaleOneToFour4));
            maleFiveToNine.setText(AppUtil.getLongValue(form.maleFiveToNine4));
            femaleFiveToNine.setText(AppUtil.getLongValue(form.femaleFiveToNine4));
            maleTenToFourteen.setText(AppUtil.getLongValue(form.maleTenToFourteen4));
            femaleTenToFourteen.setText(AppUtil.getLongValue(form.femaleTenToFourteen4));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(form.maleFifteenToNineteen4));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(form.femaleFifteenToNineteen4));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.maleTwentyToTwentyFour4));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.femaleTwentyToTwentyFour4));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToFortyNine4));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToFortyNine4));
            maleFiftyPlus.setText(AppUtil.getLongValue(form.maleFiftyPlus4));
            femaleFiftyPlus.setText(AppUtil.getLongValue(form.femaleFiftyPlus4));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanOne);
        list.add(maleOneToFour);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.maleLessThanOne4 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        form.maleOneToFour4 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        form.maleFiveToNine4 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        form.maleTenToFourteen4 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        form.maleFifteenToNineteen4 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        form.maleTwentyToTwentyFour4 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        form.maleTwentyFiveToFortyNine4 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.ancMalePositive()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanOne);
        list.add(femaleOneToFour);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.femaleLessThanOne4 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        form.femaleOneToFour4 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        form.femaleFiveToNine4 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        form.femaleTenToFourteen4 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        form.femaleFifteenToNineteen4 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        form.femaleTwentyToTwentyFour4 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        form.femaleTwentyFiveToFortyNine4 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.ancFemalePositive()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.maleLessThanOne4 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                form.femaleLessThanOne4 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                form.maleOneToFour4 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                form.femaleOneToFour4 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                form.maleFiveToNine4 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                form.femaleFiveToNine4 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                form.maleTenToFourteen4 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                form.femaleTenToFourteen4 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                form.maleFifteenToNineteen4 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                form.femaleFifteenToNineteen4 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                form.maleTwentyToTwentyFour4 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                form.femaleTwentyToTwentyFour4 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                form.maleTwentyFiveToFortyNine4 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                form.femaleTwentyFiveToFortyNine4 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void ancClinicsNegative() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.ancMaleNegative()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.ancFemaleNegative()));

        final EditText maleLessThanOne = (EditText) dialog.findViewById(R.id.maleLessThanOne);
        final EditText femaleLessThanOne = (EditText) dialog.findViewById(R.id.femaleLessThanOne);
        final EditText maleOneToFour = (EditText) dialog.findViewById(R.id.maleOneToFour);
        final EditText femaleOneToFour = (EditText) dialog.findViewById(R.id.femaleOneToFour);
        final EditText maleFiveToNine = (EditText) dialog.findViewById(R.id.maleFiveToNine);
        final EditText femaleFiveToNine = (EditText) dialog.findViewById(R.id.femaleFiveToNine);
        final EditText maleTenToFourteen = (EditText) dialog.findViewById(R.id.maleTenToFourteen);
        final EditText femaleTenToFourteen = (EditText) dialog.findViewById(R.id.femaleTenToFourteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFortyNine);
        final EditText femaleTwentyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (form != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(form.maleLessThanOne5));
            femaleLessThanOne.setText(AppUtil.getLongValue(form.femaleLessThanOne5));
            maleOneToFour.setText(AppUtil.getLongValue(form.maleOneToFour5));
            femaleOneToFour.setText(AppUtil.getLongValue(form.femaleOneToFour5));
            maleFiveToNine.setText(AppUtil.getLongValue(form.maleFiveToNine5));
            femaleFiveToNine.setText(AppUtil.getLongValue(form.femaleFiveToNine5));
            maleTenToFourteen.setText(AppUtil.getLongValue(form.maleTenToFourteen5));
            femaleTenToFourteen.setText(AppUtil.getLongValue(form.femaleTenToFourteen5));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(form.maleFifteenToNineteen5));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(form.femaleFifteenToNineteen5));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.maleTwentyToTwentyFour5));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(form.femaleTwentyToTwentyFour5));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToFortyNine5));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToFortyNine5));
            maleFiftyPlus.setText(AppUtil.getLongValue(form.maleFiftyPlus5));
            femaleFiftyPlus.setText(AppUtil.getLongValue(form.femaleFiftyPlus5));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanOne);
        list.add(maleOneToFour);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.maleLessThanOne5 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        form.maleOneToFour5 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        form.maleFiveToNine5 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        form.maleTenToFourteen5 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        form.maleFifteenToNineteen5 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        form.maleTwentyToTwentyFour5 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        form.maleTwentyFiveToFortyNine5 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus5 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.ancMaleNegative()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanOne);
        list.add(femaleOneToFour);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        form.femaleLessThanOne5 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        form.femaleOneToFour5 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        form.femaleFiveToNine5 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        form.femaleTenToFourteen5 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        form.femaleFifteenToNineteen5 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        form.femaleTwentyToTwentyFour5 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        form.femaleTwentyFiveToFortyNine5 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus5 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.ancFemaleNegative()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                form.maleLessThanOne5 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                form.femaleLessThanOne5 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                form.maleOneToFour5 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                form.femaleOneToFour5 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                form.maleFiveToNine5 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                form.femaleFiveToNine5 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                form.maleTenToFourteen5 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                form.femaleTenToFourteen5 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                form.maleFifteenToNineteen5 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                form.femaleFifteenToNineteen5 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                form.maleTwentyToTwentyFour5 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                form.femaleTwentyToTwentyFour5 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                form.maleTwentyFiveToFortyNine5 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                form.femaleTwentyFiveToFortyNine5 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus5 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus5 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }*/

    private void updateLabel(Date date) {
        dateCreated.setText(AppUtil.getStringDate(date));
    }

    public boolean validate() {
        boolean valid = true;

        String name = numerator.getText().toString().toString();

        if (name.isEmpty()) {
            numerator.setError("Required");
            valid = false;
        } else {
            numerator.setError(null);
        }

        name = pitc_negative.getText().toString().toString();

        if (name.isEmpty()) {
            pitc_negative.setError("Required");
            valid = false;
        } else {
            pitc_negative.setError(null);
        }

        name = pitc_positive.getText().toString().toString();

        if (name.isEmpty()) {
            pitc_positive.setError("Required");
            valid = false;
        } else {
            pitc_positive.setError(null);
        }

        name = dateCreated.getText().toString();

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

        if(malnutritionNegative.getText().toString().isEmpty()){
            malnutritionNegative.setError("Required");
            valid = false;
        }else{
            malnutritionNegative.setError(null);
        }

        if(malnutritionPositive.getText().toString().isEmpty()){
            malnutritionPositive.setError("Required");
            valid = false;
        }else{
            malnutritionPositive.setError(null);
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

                        Intent intent = new Intent(HTCTSTActivity.this, HTCTListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void upDateForm() {

        btn_pitc_positive.setText(this.getString(R.string.pitc_positive)
                + " [ " + (form.pitcFemalePositive() + form.pitcMalePositive()) + " ]");
        btn_pitc_negative.setText(this.getString(R.string.pitc_negative)
                + " [ " + (form.pitcMaleNegative() + form.pitcFemaleNegative()) + " ]");
        tbClinicsPositive.setText(this.getString(R.string.tb_clinics_positive)
                + " [ " + (form.tbClinicsFemaleTestedPositive() + form.tbClinicsMalePositive()) + " ]");
        tbClinicsNegative.setText(this.getString(R.string.tb_clinics_negative)
                + " [ " + (form.tbClinicsMaleNegative() + form.tbClinicsFemaleTestedNegative()) + " ]");
        ancClinicsPositive.setText(this.getString(R.string.anc_clinics_positive)
                + " [ " + (form.ancMalePositive() + form.ancFemalePositive()) + " ]");
        ancClinicsNegative.setText(this.getString(R.string.anc_clinics_negative)
                + " [ " + (form.ancMaleNegative() + form.ancFemaleNegative()) + " ]");
    }
}
