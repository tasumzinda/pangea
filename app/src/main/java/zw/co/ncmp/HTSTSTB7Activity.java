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
import zw.co.ncmp.business.HTSTSTB6;
import zw.co.ncmp.business.HTSTSTB7;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @uthor Tasu Muzinda
 */
public class HTSTSTB7Activity extends MenuBar implements View.OnClickListener {

    HTSTSTB7 registerForm;
    Spinner facility;
    EditText dateCreated;
    EditText name;
    EditText startDate;
    EditText endDate;

    Button btn_save;
    Button btn_completed;
    Button btn_submit;

    Button btn_question_one;
    Button btn_question_two;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htststb6);

        Intent intent = getIntent();
        Long registerForm_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        title = (TextView) findViewById(R.id.txt_name);
        name = (EditText) findViewById(R.id.name);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateCreated, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCreated = (EditText) findViewById(R.id.dateCreated);
        dateCreated.setOnClickListener(this);

        startDate = (EditText) findViewById(R.id.startDate);
        startDate.setOnClickListener(this);
        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(startDate, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));


        endDate = (EditText) findViewById(R.id.endDate);
        endDate.setOnClickListener(this);
        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(endDate, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));


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


        if (registerForm_id != 0) {
            registerForm = HTSTSTB7.get(registerForm_id);
            name.setText(registerForm.name);
            updateLabel(dateCreated, registerForm.dateCreated);
            updateLabel(startDate, registerForm.startDate);
            updateLabel(endDate, registerForm.endDate);

            int i = 0;
            for (Facility s : Facility.getAll()) {
                if (registerForm.facility.equals(facility.getItemAtPosition(i))) {
                    facility.setSelection(i);
                    break;
                }
                i++;
            }

            setSupportActionBar(createToolBar("HTS_TST B7 - Update"));
        } else {
            registerForm = new HTSTSTB7();
            setSupportActionBar(createToolBar("HTS_TST B7"));
        }

        title.setText("HTSTST B7");

        btn_question_one = (Button) findViewById(R.id.btn_question_one);
        btn_question_one.setOnClickListener(this);
        btn_question_one.setText("HTS_TST B7-HIV Tested And Received Results Disaggregated By Age/Sex");

        btn_question_two = (Button) findViewById(R.id.btn_question_two);
        btn_question_two.setOnClickListener(this);
        btn_question_two.setText("HTS_TST B7-Tested HIV Positive Disaggregated By Age/Sex");

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_save.setBackgroundResource(R.drawable.finish_background);

        btn_completed = (Button) findViewById(R.id.btn_completed);
        btn_completed.setVisibility(View.GONE);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);

        if (registerForm.dateCreated != null) {
            btn_submit.setVisibility(View.VISIBLE);
        }

        if (registerForm.dateSubmitted != null) {
            btn_submit.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            btn_completed.setVisibility(View.VISIBLE);
        }

        upDateForm();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btn_question_one.getId()) {
            questionOne();
        }

        if (v.getId() == btn_question_two.getId()) {
            questionTwo();
        }

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                registerForm.name = name.getText().toString();
                registerForm.facility = (Facility) facility.getSelectedItem();
                registerForm.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                registerForm.startDate = AppUtil.getDate(startDate.getText().toString());
                registerForm.endDate = AppUtil.getDate(endDate.getText().toString());
                registerForm.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(HTSTSTB7Activity.this, "Saved");
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
                                registerForm.dateSubmitted = new Date();
                                registerForm.save();
                                AppUtil.createLongNotification(HTSTSTB7Activity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(HTSTSTB7Activity.this, HTSTSTB7ListActivity.class);
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

        if (v.getId() == startDate.getId()) {
            datePickerDialog1.show();
        }

        if (v.getId() == endDate.getId()) {
            datePickerDialog2.show();
        }
    }

    private void updateLabel(EditText editText, Date date) {
        editText.setText(AppUtil.getStringDate(date));
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

                        Intent intent = new Intent(HTSTSTB7Activity.this, HTSTSTB7ListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void questionOne() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.htstst_individuals_dialog);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("HTS_TST B7-HIV Tested And Received Results Disaggregated By Age/Sex");

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.getMaleIndividuals()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.getFemaleIndividuals()));

        final EditText maleLessThanTwoMonths = (EditText) dialog.findViewById(R.id.maleLessThanTwoMonths);
        final EditText femaleLessThanTwoMonths = (EditText) dialog.findViewById(R.id.femaleLessThanTwoMonths);
        final EditText maleTwoToTwelveMonths = (EditText) dialog.findViewById(R.id.maleTwoToTwelveMonths);
        final EditText femaleTwoToTwelveMonths = (EditText) dialog.findViewById(R.id.femaleTwoToTwelveMonths);
        final EditText maleThirteenToTwentyFourMonths = (EditText) dialog.findViewById(R.id.maleThirteenToTwentyFourMonths);
        final EditText femaleThirteenToTwentyFourMonths = (EditText) dialog.findViewById(R.id.femaleThirteenToTwentyFourMonths);
        final EditText maleTwentyFiveToFiftyNineMonths = (EditText) dialog.findViewById(R.id.maleTwentyFiveToFiftyNineMonths);
        final EditText femaleTwentyFiveToFiftyNineMonths = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToFiftyNineMonths);
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
        final EditText maleThirtyToFortyNine = (EditText) dialog.findViewById(R.id.maleThirtyToFortyNine);
        final EditText femaleThirtyToFortyNine = (EditText) dialog.findViewById(R.id.femaleThirtyToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (registerForm != null) {
            maleLessThanTwoMonths.setText(AppUtil.getLongValue(registerForm.maleLessThanTwoMonths));
            femaleLessThanTwoMonths.setText(AppUtil.getLongValue(registerForm.femaleLessThanTwoMonths));
            maleTwoToTwelveMonths.setText(AppUtil.getLongValue(registerForm.maleTwoToTwelveMonths));
            femaleTwoToTwelveMonths.setText(AppUtil.getLongValue(registerForm.femaleTwoToTwelveMonths));
            maleThirteenToTwentyFourMonths.setText(AppUtil.getLongValue(registerForm.maleThirteenToTwentyFourMonths));
            femaleThirteenToTwentyFourMonths.setText(AppUtil.getLongValue(registerForm.femaleThirteenToTwentyFourMonths));
            maleTwentyFiveToFiftyNineMonths.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFiftyNineMonths));
            femaleTwentyFiveToFiftyNineMonths.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFiftyNineMonths));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine1));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine1));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen1));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen1));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen1));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen1));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour1));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour1));
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToTwentyNine1));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToTwentyNine1));
            maleThirtyToFortyNine.setText(AppUtil.getLongValue(registerForm.maleThirtyToFortyNine1));
            femaleThirtyToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleThirtyToFortyNine1));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus1));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus1));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanTwoMonths);
        list.add(maleTwoToTwelveMonths);
        list.add(maleThirteenToTwentyFourMonths);
        list.add(maleTwentyFiveToFiftyNineMonths);
        list.add(maleFiveToNine);
        list.add(maleTenToFourteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        registerForm.maleLessThanTwoMonths = AppUtil.getLongValue(maleLessThanTwoMonths.getText().toString());
                        registerForm.maleTwoToTwelveMonths = AppUtil.getLongValue(maleTwoToTwelveMonths.getText().toString());
                        registerForm.maleThirteenToTwentyFourMonths = AppUtil.getLongValue(maleThirteenToTwentyFourMonths.getText().toString());
                        registerForm.maleTwentyFiveToFiftyNineMonths = AppUtil.getLongValue(maleTwentyFiveToFiftyNineMonths.getText().toString());
                        registerForm.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToFortyNine1 = AppUtil.getLongValue(maleThirtyToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.getMaleIndividuals()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanTwoMonths);
        list.add(femaleTwoToTwelveMonths);
        list.add(femaleThirteenToTwentyFourMonths);
        list.add(femaleTwentyFiveToFiftyNineMonths);
        list.add(femaleFiveToNine);
        list.add(femaleTenToFourteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        registerForm.femaleLessThanTwoMonths = AppUtil.getLongValue(femaleLessThanTwoMonths.getText().toString());
                        registerForm.femaleTwoToTwelveMonths = AppUtil.getLongValue(femaleTwoToTwelveMonths.getText().toString());
                        registerForm.femaleThirteenToTwentyFourMonths = AppUtil.getLongValue(femaleThirteenToTwentyFourMonths.getText().toString());
                        registerForm.femaleTwentyFiveToFiftyNineMonths = AppUtil.getLongValue(femaleTwentyFiveToFiftyNineMonths.getText().toString());
                        registerForm.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToFortyNine1 = AppUtil.getLongValue(femaleThirtyToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.getFemaleIndividuals()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanTwoMonths = AppUtil.getLongValue(maleLessThanTwoMonths.getText().toString());
                registerForm.maleTwoToTwelveMonths = AppUtil.getLongValue(maleTwoToTwelveMonths.getText().toString());
                registerForm.maleThirteenToTwentyFourMonths = AppUtil.getLongValue(maleThirteenToTwentyFourMonths.getText().toString());
                registerForm.maleTwentyFiveToFiftyNineMonths = AppUtil.getLongValue(maleTwentyFiveToFiftyNineMonths.getText().toString());
                registerForm.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.maleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.maleThirtyToFortyNine1 = AppUtil.getLongValue(maleThirtyToFortyNine.getText().toString());
                registerForm.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleLessThanTwoMonths = AppUtil.getLongValue(femaleLessThanTwoMonths.getText().toString());
                registerForm.femaleTwoToTwelveMonths = AppUtil.getLongValue(femaleTwoToTwelveMonths.getText().toString());
                registerForm.femaleThirteenToTwentyFourMonths = AppUtil.getLongValue(femaleThirteenToTwentyFourMonths.getText().toString());
                registerForm.femaleTwentyFiveToFiftyNineMonths = AppUtil.getLongValue(femaleTwentyFiveToFiftyNineMonths.getText().toString());
                registerForm.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                registerForm.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                registerForm.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                registerForm.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                registerForm.femaleThirtyToFortyNine1 = AppUtil.getLongValue(femaleThirtyToFortyNine.getText().toString());
                registerForm.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());


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
        dialog.setContentView(R.layout.htstst_couples_dialog);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("HTS_TST B7-HIV Tested HIV Positive Disaggregated By Age/Sex");

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.getMaleCouples()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.getFemaleCouples()));

        final EditText maleLessThanFifteen = (EditText) dialog.findViewById(R.id.maleLessThanFifteen);
        final EditText femaleLessThanFifteen = (EditText) dialog.findViewById(R.id.femaleLessThanFifteen);
        final EditText maleFifteenToNineteen = (EditText) dialog.findViewById(R.id.maleFifteenToNineteen);
        final EditText femaleFifteenToNineteen = (EditText) dialog.findViewById(R.id.femaleFifteenToNineteen);
        final EditText maleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.maleTwentyToTwentyFour);
        final EditText femaleTwentyToTwentyFour = (EditText) dialog.findViewById(R.id.femaleTwentyToTwentyFour);
        final EditText maleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.maleTwentyFiveToTwentyNine);
        final EditText femaleTwentyFiveToTwentyNine = (EditText) dialog.findViewById(R.id.femaleTwentyFiveToTwentyNine);
        final EditText maleThirtyToFortyNine = (EditText) dialog.findViewById(R.id.maleThirtyToFortyNine);
        final EditText femaleThirtyToFortyNine = (EditText) dialog.findViewById(R.id.femaleThirtyToFortyNine);
        final EditText maleFiftyPlus = (EditText) dialog.findViewById(R.id.maleFiftyPlus);
        final EditText femaleFiftyPlus = (EditText) dialog.findViewById(R.id.femaleFiftyPlus);

        if (registerForm != null) {
            maleLessThanFifteen.setText(AppUtil.getLongValue(registerForm.maleLessThanFifteen));
            femaleLessThanFifteen.setText(AppUtil.getLongValue(registerForm.femaleLessThanFifteen));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen2));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen2));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour2));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour2));
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToTwentyNine2));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToTwentyNine2));
            maleThirtyToFortyNine.setText(AppUtil.getLongValue(registerForm.maleThirtyToFortyNine2));
            femaleThirtyToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleThirtyToFortyNine2));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus2));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus2));
        }

        List<EditText> list = new ArrayList<>();
        list.add(maleLessThanFifteen);
        list.add(maleFifteenToNineteen);
        list.add(maleTwentyToTwentyFour);
        list.add(maleTwentyFiveToTwentyNine);
        list.add(maleThirtyToFortyNine);
        list.add(maleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        registerForm.maleLessThanFifteen = AppUtil.getLongValue(maleLessThanFifteen.getText().toString());
                        registerForm.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToFortyNine2 = AppUtil.getLongValue(maleThirtyToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.getMaleCouples()));
                    }

                }
            });
        }

        list = new ArrayList<>();
        list.add(femaleLessThanFifteen);
        list.add(femaleFifteenToNineteen);
        list.add(femaleTwentyToTwentyFour);
        list.add(femaleTwentyFiveToTwentyNine);
        list.add(femaleThirtyToFortyNine);
        list.add(femaleFiftyPlus);

        for (EditText editText : list) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {

                        registerForm.femaleLessThanFifteen = AppUtil.getLongValue(femaleLessThanFifteen.getText().toString());
                        registerForm.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToFortyNine2 = AppUtil.getLongValue(femaleThirtyToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.getFemaleCouples()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanFifteen = AppUtil.getLongValue(maleLessThanFifteen.getText().toString());
                registerForm.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.maleThirtyToFortyNine2 = AppUtil.getLongValue(maleThirtyToFortyNine.getText().toString());
                registerForm.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleLessThanFifteen = AppUtil.getLongValue(femaleLessThanFifteen.getText().toString());
                registerForm.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                registerForm.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                registerForm.femaleThirtyToFortyNine2 = AppUtil.getLongValue(femaleThirtyToFortyNine.getText().toString());
                registerForm.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }




    public void upDateForm() {
        btn_question_one.setText("HTS_TST B7-HIV Tested And Received Results Disaggregated By Age/Sex [ " + (registerForm.getMaleIndividuals() + registerForm.getFemaleIndividuals()) + " ]");
        btn_question_two.setText("HTS_TST B7-TestedHIV Positive Results Disaggregated By Age/Sex [ " + (registerForm.getMaleCouples() + registerForm.getFemaleCouples()) + " ]");
    }
}
