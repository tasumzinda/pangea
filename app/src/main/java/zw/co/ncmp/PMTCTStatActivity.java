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
import zw.co.ncmp.business.PMTCTStat;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.StatForm;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 3/17/2017.
 */
public class PMTCTStatActivity extends MenuBar implements View.OnClickListener{

    Spinner facility;
    EditText dateCreated;
    Spinner period;
    EditText name;
    EditText numerator;
    EditText denominator;

    Button btn_completed;
    Button btn_submit;
    Button knownHIVPositive;
    Button newHIVPositive;
    Button testedHIVNegative;
    Button numDisaggregation;
    Button denomDisaggregation;

    Button btn_save;
    private PMTCTStat form;
    private DatePickerDialog datePickerDialog;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmtct_stat);

        Intent intent = getIntent();
        Long form_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        name = (EditText) findViewById(R.id.name);
        period = (Spinner) findViewById(R.id.period);
        numerator = (EditText) findViewById(R.id.numerator);
        denominator = (EditText) findViewById(R.id.denominator);
        dateCreated = (EditText) findViewById(R.id.dateCreated);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);
        knownHIVPositive = (Button) findViewById(R.id.btn_known_hiv_positive);
        newHIVPositive = (Button) findViewById(R.id.btn_new_hiv_positive);
        testedHIVNegative = (Button) findViewById(R.id.btn_tested_hiv_negative);
        denomDisaggregation = (Button) findViewById(R.id.btn_denom_disagg);
        numDisaggregation = (Button) findViewById(R.id.btn_num_disagg);

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
            form = PMTCTStat.get(form_id);
            name.setText(form.name);

            numerator.setText(AppUtil.getLongValue(form.numerator));
            denominator.setText(AppUtil.getLongValue(form.denominator));

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

            setSupportActionBar(createToolBar("PMTCT_STAT Update"));
        } else {
            form = new PMTCTStat();
            setSupportActionBar(createToolBar("PMTCT_STAT"));
        }

        knownHIVPositive.setOnClickListener(this);
        knownHIVPositive.setText(getResources().getString(R.string.known_positives));
        newHIVPositive.setText(getResources().getString(R.string.new_positives));
        newHIVPositive.setOnClickListener(this);
        testedHIVNegative.setOnClickListener(this);
        testedHIVNegative.setText(getResources().getString(R.string.new_negatives));
        denomDisaggregation.setOnClickListener(this);
        denomDisaggregation.setText(getResources().getString(R.string.denominator_disaggregation));
        numDisaggregation.setOnClickListener(this);
        numDisaggregation.setText(getResources().getString(R.string.numerator_disaggregation));

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

        if (v.getId() == knownHIVPositive.getId()) {
            questionOne();
        }
        if(v.getId() == newHIVPositive.getId()){
            questionTwo();
        }
        if(v.getId() == testedHIVNegative.getId()){
            questionThree();
        }
        if(v.getId() == denomDisaggregation.getId()){
            questionFour();
        }

        if(v.getId() == numDisaggregation.getId()){
            questionFive();
        }

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                form.facility = (Facility) facility.getSelectedItem();
                form.name = name.getText().toString();
                form.period = (Period) period.getSelectedItem();
                form.numerator = AppUtil.getLongValue(numerator.getText().toString());
                form.denominator = AppUtil.getLongValue(denominator.getText().toString());
                form.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                form.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(PMTCTStatActivity.this, "Saved");
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
                                AppUtil.createLongNotification(PMTCTStatActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(PMTCTStatActivity.this, PMTCTStatListActivity.class);
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


    public void questionOne() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleKnownPositive()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleKnownPositive()));

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
        final EditText maleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToTwentyNine);
        final EditText femaleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToTwentyNine);
        final EditText maleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.maleThirtyToThirtyFour);
        final EditText femaleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.femaleThirtyToThirtyFour);
        final EditText maleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.maleThirtyFiveToThirtyNine);
        final EditText femaleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.femaleThirtyFiveToThirtyNine);
        final EditText maleFortyToFortyFour = (EditText) dialog.findViewById(R.id.maleFortyToFortyFour);
        final EditText femaleFortyToFortyFour = (EditText) dialog.findViewById(R.id.femaleFortyToFortyFour);
        final EditText maleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleFortyFiveToFortyNine);
        final EditText femaleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleFortyFiveToFortyNine);
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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToTwentyNine));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToTwentyNine));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.maleThirtyToThirtyFour));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.femaleThirtyToThirtyFour));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.maleThirtyFiveToThirtyNine));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.femaleThirtyFiveToThirtyNine));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(form.maleFortyToFortyFour));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(form.femaleFortyToFortyFour));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleFortyFiveToFortyNine));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleFortyFiveToFortyNine));
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
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToThirtyFour);
        list.add(maleThirtyFiveToThirtyNine);
        list.add(maleFortyToFortyFour);
        list.add(maleFortyFiveToFortyNine);
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
                        form.maleTwentyFiveToTwentyNine = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        form.maleThirtyToThirtyFour = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        form.maleThirtyFiveToThirtyNine = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        form.maleFortyToFortyFour = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        form.maleFortyFiveToFortyNine = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.maleKnownPositive()));
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
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToThirtyFour);
        list.add(femaleThirtyFiveToThirtyNine);
        list.add(femaleFortyToFortyFour);
        list.add(femaleFortyFiveToFortyNine);
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
                        form.femaleTwentyFiveToTwentyNine = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        form.femaleThirtyToThirtyFour = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        form.femaleThirtyFiveToThirtyNine = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        form.femaleFortyToFortyFour = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        form.femaleFortyFiveToFortyNine = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleKnownPositive()));
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

                form.maleTwentyFiveToTwentyNine = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                form.femaleTwentyFiveToTwentyNine = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                form.maleThirtyToThirtyFour = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                form.femaleThirtyToThirtyFour = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                form.maleThirtyFiveToThirtyNine = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                form.femaleThirtyFiveToThirtyNine = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                form.maleFortyToFortyFour = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                form.femaleFortyToFortyFour = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                form.maleFortyFiveToFortyNine = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                form.femaleFortyFiveToFortyNine = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleTestedPositive()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleTestedPositive()));

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
        final EditText maleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToTwentyNine);
        final EditText femaleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToTwentyNine);
        final EditText maleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.maleThirtyToThirtyFour);
        final EditText femaleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.femaleThirtyToThirtyFour);
        final EditText maleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.maleThirtyFiveToThirtyNine);
        final EditText femaleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.femaleThirtyFiveToThirtyNine);
        final EditText maleFortyToFortyFour = (EditText) dialog.findViewById(R.id.maleFortyToFortyFour);
        final EditText femaleFortyToFortyFour = (EditText) dialog.findViewById(R.id.femaleFortyToFortyFour);
        final EditText maleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleFortyFiveToFortyNine);
        final EditText femaleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleFortyFiveToFortyNine);
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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToTwentyNine1));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToTwentyNine1));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.maleThirtyToThirtyFour1));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.femaleThirtyToThirtyFour1));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.maleThirtyFiveToThirtyNine1));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.femaleThirtyFiveToThirtyNine1));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(form.maleFortyToFortyFour1));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(form.femaleFortyToFortyFour1));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleFortyFiveToFortyNine1));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleFortyFiveToFortyNine1));
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
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToThirtyFour);
        list.add(maleThirtyFiveToThirtyNine);
        list.add(maleFortyToFortyFour);
        list.add(maleFortyFiveToFortyNine);
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
                        form.maleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        form.maleThirtyToThirtyFour1 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        form.maleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        form.maleFortyToFortyFour1 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        form.maleFortyFiveToFortyNine1 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.maleTestedPositive()));
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
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToThirtyFour);
        list.add(femaleThirtyFiveToThirtyNine);
        list.add(femaleFortyToFortyFour);
        list.add(femaleFortyFiveToFortyNine);
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
                        form.femaleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        form.femaleThirtyToThirtyFour1 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        form.femaleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        form.femaleFortyToFortyFour1 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        form.femaleFortyFiveToFortyNine1 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleTestedPositive()));
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

                form.maleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                form.femaleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                form.maleThirtyToThirtyFour1 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                form.femaleThirtyToThirtyFour1 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                form.maleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                form.femaleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                form.maleFortyToFortyFour1 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                form.femaleFortyToFortyFour1 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                form.maleFortyFiveToFortyNine1 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                form.femaleFortyFiveToFortyNine1 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleTestedNegative()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleTestedNegative()));

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
        final EditText maleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToTwentyNine);
        final EditText femaleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToTwentyNine);
        final EditText maleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.maleThirtyToThirtyFour);
        final EditText femaleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.femaleThirtyToThirtyFour);
        final EditText maleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.maleThirtyFiveToThirtyNine);
        final EditText femaleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.femaleThirtyFiveToThirtyNine);
        final EditText maleFortyToFortyFour = (EditText) dialog.findViewById(R.id.maleFortyToFortyFour);
        final EditText femaleFortyToFortyFour = (EditText) dialog.findViewById(R.id.femaleFortyToFortyFour);
        final EditText maleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleFortyFiveToFortyNine);
        final EditText femaleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleFortyFiveToFortyNine);
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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToTwentyNine2));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToTwentyNine2));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.maleThirtyToThirtyFour2));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.femaleThirtyToThirtyFour2));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.maleThirtyFiveToThirtyNine2));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.femaleThirtyFiveToThirtyNine2));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(form.maleFortyToFortyFour2));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(form.femaleFortyToFortyFour2));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleFortyFiveToFortyNine2));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleFortyFiveToFortyNine2));
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
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToThirtyFour);
        list.add(maleThirtyFiveToThirtyNine);
        list.add(maleFortyToFortyFour);
        list.add(maleFortyFiveToFortyNine);
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
                        form.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        form.maleThirtyToThirtyFour2 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        form.maleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        form.maleFortyToFortyFour2 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        form.maleFortyFiveToFortyNine2 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.maleTestedNegative()));
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
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToThirtyFour);
        list.add(femaleThirtyFiveToThirtyNine);
        list.add(femaleFortyToFortyFour);
        list.add(femaleFortyFiveToFortyNine);
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
                        form.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        form.femaleThirtyToThirtyFour2 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        form.femaleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        form.femaleFortyToFortyFour2 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        form.femaleFortyFiveToFortyNine2 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleTestedNegative()));
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

                form.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                form.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                form.maleThirtyToThirtyFour2 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                form.femaleThirtyToThirtyFour2 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                form.maleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                form.femaleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                form.maleFortyToFortyFour2 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                form.femaleFortyToFortyFour2 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                form.maleFortyFiveToFortyNine2 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                form.femaleFortyFiveToFortyNine2 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionFour() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.disaggregation);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleDenominator()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleDenominator()));

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
        final EditText maleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToTwentyNine);
        final EditText femaleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToTwentyNine);
        final EditText maleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.maleThirtyToThirtyFour);
        final EditText femaleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.femaleThirtyToThirtyFour);
        final EditText maleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.maleThirtyFiveToThirtyNine);
        final EditText femaleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.femaleThirtyFiveToThirtyNine);
        final EditText maleFortyToFortyFour = (EditText) dialog.findViewById(R.id.maleFortyToFortyFour);
        final EditText femaleFortyToFortyFour = (EditText) dialog.findViewById(R.id.femaleFortyToFortyFour);
        final EditText maleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleFortyFiveToFortyNine);
        final EditText femaleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleFortyFiveToFortyNine);
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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToTwentyNine3));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToTwentyNine3));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.maleThirtyToThirtyFour3));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.femaleThirtyToThirtyFour3));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.maleThirtyFiveToThirtyNine3));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.femaleThirtyFiveToThirtyNine3));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(form.maleFortyToFortyFour3));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(form.femaleFortyToFortyFour3));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleFortyFiveToFortyNine3));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleFortyFiveToFortyNine3));
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
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToThirtyFour);
        list.add(maleThirtyFiveToThirtyNine);
        list.add(maleFortyToFortyFour);
        list.add(maleFortyFiveToFortyNine);
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
                        form.maleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        form.maleThirtyToThirtyFour3 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        form.maleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        form.maleFortyToFortyFour3 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        form.maleFortyFiveToFortyNine3 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.maleDenominator()));
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
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToThirtyFour);
        list.add(femaleThirtyFiveToThirtyNine);
        list.add(femaleFortyToFortyFour);
        list.add(femaleFortyFiveToFortyNine);
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
                        form.femaleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        form.femaleThirtyToThirtyFour3 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        form.femaleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        form.femaleFortyToFortyFour3 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        form.femaleFortyFiveToFortyNine3 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleDenominator()));
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

                form.maleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                form.femaleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                form.maleThirtyToThirtyFour3 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                form.femaleThirtyToThirtyFour3 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                form.maleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                form.femaleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                form.maleFortyToFortyFour3 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                form.femaleFortyToFortyFour3 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                form.maleFortyFiveToFortyNine3 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                form.femaleFortyFiveToFortyNine3 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionFive() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.register_question_four);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleNumerator()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleNumerator()));

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
        final EditText maleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToTwentyNine);
        final EditText femaleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToTwentyNine);
        final EditText maleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.maleThirtyToThirtyFour);
        final EditText femaleThirtyToThirtyFour = (EditText) dialog.findViewById(R.id.femaleThirtyToThirtyFour);
        final EditText maleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.maleThirtyFiveToThirtyNine);
        final EditText femaleThirtyFiveToThirtyNine = (EditText) dialog.findViewById(R.id.femaleThirtyFiveToThirtyNine);
        final EditText maleFortyToFortyFour = (EditText) dialog.findViewById(R.id.maleFortyToFortyFour);
        final EditText femaleFortyToFortyFour = (EditText) dialog.findViewById(R.id.femaleFortyToFortyFour);
        final EditText maleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.maleFortyFiveToFortyNine);
        final EditText femaleFortyFiveToFortyNine = (EditText) dialog.findViewById(R.id.femaleFortyFiveToFortyNine);
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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.maleTwentyFiveToTwentyNine4));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(form.femaleTwentyFiveToTwentyNine4));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.maleThirtyToThirtyFour4));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(form.femaleThirtyToThirtyFour4));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.maleThirtyFiveToThirtyNine4));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(form.femaleThirtyFiveToThirtyNine4));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(form.maleFortyToFortyFour4));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(form.femaleFortyToFortyFour4));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.maleFortyFiveToFortyNine4));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(form.femaleFortyFiveToFortyNine4));
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
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToThirtyFour);
        list.add(maleThirtyFiveToThirtyNine);
        list.add(maleFortyToFortyFour);
        list.add(maleFortyFiveToFortyNine);
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
                        form.maleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        form.maleThirtyToThirtyFour4 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        form.maleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        form.maleFortyToFortyFour4 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        form.maleFortyFiveToFortyNine4 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        form.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(form.maleNumerator()));
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
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToThirtyFour);
        list.add(femaleThirtyFiveToThirtyNine);
        list.add(femaleFortyToFortyFour);
        list.add(femaleFortyFiveToFortyNine);
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
                        form.femaleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        form.femaleThirtyToThirtyFour4 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        form.femaleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        form.femaleFortyToFortyFour4 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        form.femaleFortyFiveToFortyNine4 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        form.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleNumerator()));
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

                form.maleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                form.femaleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                form.maleThirtyToThirtyFour4 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                form.femaleThirtyToThirtyFour4 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                form.maleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                form.femaleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                form.maleFortyToFortyFour4 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                form.femaleFortyToFortyFour4 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                form.maleFortyFiveToFortyNine4 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                form.femaleFortyFiveToFortyNine4 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                form.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                form.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

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

        name = denominator.getText().toString().toString();

        if (name.isEmpty()) {
            denominator.setError("Required");
            valid = false;
        } else {
            denominator.setError(null);
        }

        name = dateCreated.getText().toString().toString();

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
                        Intent intent = new Intent(PMTCTStatActivity.this, PMTCTStatListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void upDateForm() {

        knownHIVPositive.setText(this.getString(R.string.known_positives)
                + " [ " + (form.maleKnownPositive() + form.femaleKnownPositive()) + " ]");
        newHIVPositive.setText(this.getString(R.string.new_positives)
                + " [ " + (form.maleTestedPositive() + form.femaleTestedPositive()) + " ]");
        testedHIVNegative.setText(this.getString(R.string.new_negatives)
                + " [ " + (form.maleTestedNegative() + form.femaleTestedNegative()) + " ]");
        denomDisaggregation.setText(this.getString(R.string.denominator_disaggregation)
                + " [ " + (form.maleDenominator() + form.femaleDenominator()) + " ]");
        numDisaggregation.setText(this.getString(R.string.numerator_disaggregation)
                + " [ " + (form.maleNumerator() + form.femaleNumerator()) + " ]");

    }
}
