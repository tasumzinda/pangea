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

import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.TXPVLSForm;
import zw.co.ncmp.util.AppUtil;

public class TXPLSVFormActivity extends MenuBar implements View.OnClickListener {

    Spinner facility;
    Spinner period;
    EditText dateCreated;
    EditText name;

    EditText targetNumeratorPregnant;
    EditText targetDenominatorPregnant;
    EditText targetNumeratorBreastFeeding;
    EditText targetDenominatorBreastFeeding;
    EditText routineNumeratorPregnant;
    EditText routineDenominatorPregnant;
    EditText routineNumeratorBreastFeeding;
    EditText routineDenominatorBreastFeeding;

    Button btn_question_one;
    Button btn_question_two;
    Button btn_question_three;
    Button btn_question_four;

    Button btn_completed;
    Button btn_submit;
    Button btn_save;

    private TXPVLSForm form;
    private DatePickerDialog datePickerDialog;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tx_pvls_form_activity);

        Intent intent = getIntent();
        Long form_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        name = (EditText) findViewById(R.id.name);
        period = (Spinner) findViewById(R.id.period);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);

        routineNumeratorPregnant = (EditText) findViewById(R.id.routineNumeratorPregnant);
        routineDenominatorPregnant = (EditText) findViewById(R.id.routineDenominatorPregnant);
        routineNumeratorBreastFeeding = (EditText) findViewById(R.id.routineNumeratorBreastFeeding);
        routineDenominatorBreastFeeding = (EditText) findViewById(R.id.routineDenominatorBreastFeeding);
        targetNumeratorPregnant = (EditText) findViewById(R.id.targetNumeratorPregnant);
        targetDenominatorPregnant = (EditText) findViewById(R.id.targetDenominatorPregnant);
        targetNumeratorBreastFeeding = (EditText) findViewById(R.id.targetNumeratorBreastFeeding);
        targetDenominatorBreastFeeding = (EditText) findViewById(R.id.targetDenominatorBreastFeeding);

        dateCreated = (EditText) findViewById(R.id.dateCreated);

        btn_question_one = (Button) findViewById(R.id.btn_question_one);
        btn_question_one.setOnClickListener(this);

        btn_question_two = (Button) findViewById(R.id.btn_question_two);
        btn_question_two.setOnClickListener(this);

        btn_question_three = (Button) findViewById(R.id.btn_question_three);
        btn_question_three.setOnClickListener(this);

        btn_question_four = (Button) findViewById(R.id.btn_question_four);
        btn_question_four.setOnClickListener(this);


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
            form = TXPVLSForm.get(form_id);
            name.setText(form.name);

            routineNumeratorPregnant.setText(AppUtil.getLongValue(form.routineNumeratorPregnant));
            routineDenominatorPregnant.setText(AppUtil.getLongValue(form.routineDenominatorPregnant));
            routineNumeratorBreastFeeding.setText(AppUtil.getLongValue(form.routineNumeratorBreastFeeding));
            routineDenominatorBreastFeeding.setText(AppUtil.getLongValue(form.routineDenominatorBreastFeeding));
            targetNumeratorPregnant.setText(AppUtil.getLongValue(form.targetNumeratorPregnant));
            targetDenominatorPregnant.setText(AppUtil.getLongValue(form.targetDenominatorPregnant));
            targetNumeratorBreastFeeding.setText(AppUtil.getLongValue(form.targetNumeratorBreastFeeding));
            targetDenominatorBreastFeeding.setText(AppUtil.getLongValue(form.targetDenominatorBreastFeeding));


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

            setSupportActionBar(createToolBar("TX_PVLS Update"));
        } else {
            form = new TXPVLSForm();
            setSupportActionBar(createToolBar("TX_PVLS"));
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

        upDateForm();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        /*if (v.getId() == btn_question_one.getId()) {
            questionOne();
        }

        if (v.getId() == btn_question_two.getId()) {
            questionTwo();
        }

        if (v.getId() == btn_question_three.getId()) {
            questionThree();
        }

        if (v.getId() == btn_question_four.getId()) {
            questionFour();
        }


        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                form.facility = (Facility) facility.getSelectedItem();
                form.name = name.getText().toString();
                form.period = (Period) period.getSelectedItem();
                form.routineNumeratorPregnant = AppUtil.getLongValue(routineNumeratorPregnant.getText().toString());
                form.routineDenominatorPregnant = AppUtil.getLongValue(routineDenominatorPregnant.getText().toString());
                form.routineNumeratorBreastFeeding = AppUtil.getLongValue(routineNumeratorBreastFeeding.getText().toString());
                form.routineDenominatorBreastFeeding = AppUtil.getLongValue(routineDenominatorBreastFeeding.getText().toString());

                form.targetNumeratorPregnant = AppUtil.getLongValue(targetNumeratorPregnant.getText().toString());
                form.targetDenominatorPregnant = AppUtil.getLongValue(targetDenominatorPregnant.getText().toString());
                form.targetNumeratorBreastFeeding = AppUtil.getLongValue(targetNumeratorBreastFeeding.getText().toString());
                form.targetDenominatorBreastFeeding = AppUtil.getLongValue(targetDenominatorBreastFeeding.getText().toString());

                form.dateCreated = AppUtil.getDate(dateCreated.getText().toString());


                form.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(TXPLSVFormActivity.this, "Saved");

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
                                AppUtil.createLongNotification(TXPLSVFormActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(TXPLSVFormActivity.this, TXPLSVFormListActivity.class);
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

    /*public void questionOne() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.tx_pvls_question_one);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion1()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion1()));

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

                        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion1()));
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

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion1()));
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

    public void questionTwo() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.tx_pvls_question_two);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion2()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion2()));

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

                        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion2()));
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

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion2()));
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

    public void questionThree() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.tx_pvls_question_three);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion3()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion3()));

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

                        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion3()));
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

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion3()));
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

    public void questionFour() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.tx_pvls_question_four);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion4()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion4()));

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

                        maleTotal.setText(AppUtil.getLongValue(form.maleQuestion4()));
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

                        femaleTotal.setText(AppUtil.getLongValue(form.femaleQuestion4()));
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

    }*/

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

                        Intent intent = new Intent(TXPLSVFormActivity.this, TXPLSVFormListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void upDateForm() {

        btn_question_one.setText(this.getString(R.string.tx_pvls_question_one)
                + " [ " + (form.maleQuestion1() + form.femaleQuestion1()) + " ]");

        btn_question_two.setText(this.getString(R.string.tx_pvls_question_two)
                + " [ " + (form.maleQuestion2() + form.femaleQuestion2()) + " ]");

        btn_question_three.setText(this.getString(R.string.tx_pvls_question_three)
                + " [ " + (form.maleQuestion3() + form.femaleQuestion3()) + " ]");

        btn_question_four.setText(this.getString(R.string.tx_pvls_question_four)
                + " [ " + (form.maleQuestion4() + form.femaleQuestion4()) + " ]");

    }


}

