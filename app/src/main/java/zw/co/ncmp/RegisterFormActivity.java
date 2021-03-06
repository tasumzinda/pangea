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
import zw.co.ncmp.business.RegisterForm;
import zw.co.ncmp.util.AppUtil;

public class RegisterFormActivity extends MenuBar implements View.OnClickListener {

    private RegisterForm registerForm;

    Spinner facility;
    Spinner period;
    EditText dateCreated;
    EditText name;

    Button btn_save;
    Button btn_completed;
    Button btn_submit;

    Button btn_question_one;
    Button btn_question_two;
    Button btn_question_three;
    Button btn_question_four;
    Button btn_question_five;
    Button btn_question_six;
    Button btn_question_seven;
    Button btn_question_eight;
    Button btn_question_nine;
    Button btn_question_ten;
    Button btn_question_eleven;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    private DatePickerDialog datePickerDialog;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form_activity);

        Intent intent = getIntent();
        Long registerForm_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        period = (Spinner) findViewById(R.id.period);
        title = (TextView) findViewById(R.id.txt_name);
        name = (EditText) findViewById(R.id.name);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCreated = (EditText) findViewById(R.id.dateCreated);
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

        if (registerForm_id != 0) {
            registerForm = RegisterForm.get(registerForm_id);
            name.setText(registerForm.name);
            updateLabel(registerForm.dateCreated);

            int i = 0;
            for (Facility s : Facility.getAll()) {
                if (registerForm.facility.equals(facility.getItemAtPosition(i))) {
                    facility.setSelection(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (Period s : Period.getAll()) {
                if (registerForm.period.equals(period.getItemAtPosition(i))) {
                    period.setSelection(i);
                    break;
                }
                i++;
            }

            setSupportActionBar(createToolBar(this.getString(R.string.register_title)));
        } else {
            registerForm = new RegisterForm();
            setSupportActionBar(createToolBar(this.getString(R.string.register_title)));
        }

        title.setText(this.getString(R.string.register_title));

        btn_question_one = (Button) findViewById(R.id.btn_question_one);
        btn_question_one.setOnClickListener(this);

        btn_question_two = (Button) findViewById(R.id.btn_question_two);
        btn_question_two.setOnClickListener(this);

        btn_question_three = (Button) findViewById(R.id.btn_question_three);
        btn_question_three.setOnClickListener(this);

        btn_question_four = (Button) findViewById(R.id.btn_question_four);
        btn_question_four.setOnClickListener(this);

        btn_question_five = (Button) findViewById(R.id.btn_question_five);
        btn_question_five.setOnClickListener(this);

        btn_question_six = (Button) findViewById(R.id.btn_question_six);
        btn_question_six.setOnClickListener(this);

        btn_question_seven = (Button) findViewById(R.id.btn_question_seven);
        btn_question_seven.setOnClickListener(this);

        btn_question_eight = (Button) findViewById(R.id.btn_question_eight);
        btn_question_eight.setOnClickListener(this);

        btn_question_nine = (Button) findViewById(R.id.btn_question_nine);
        btn_question_nine.setOnClickListener(this);

        btn_question_ten = (Button) findViewById(R.id.btn_question_ten);
        btn_question_ten.setOnClickListener(this);

        btn_question_eleven = (Button) findViewById(R.id.btn_question_eleven);
        btn_question_eleven.setOnClickListener(this);

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

        if (v.getId() == btn_question_three.getId()) {
            questionThree();
        }

        if (v.getId() == btn_question_four.getId()) {
            questionFour();
        }

        if (v.getId() == btn_question_five.getId()) {
            questionFive();
        }

        if (v.getId() == btn_question_six.getId()) {
            questionSix();
        }

        if (v.getId() == btn_question_seven.getId()) {
            questionSeven();
        }

        if (v.getId() == btn_question_eight.getId()) {
            questionEight();
        }

        if (v.getId() == btn_question_nine.getId()) {
            questionNine();
        }

        if (v.getId() == btn_question_ten.getId()) {
            questionTen();
        }

        if (v.getId() == btn_question_eleven.getId()) {
            questionEleven();
        }

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                registerForm.name = name.getText().toString();
                registerForm.facility = (Facility) facility.getSelectedItem();
                registerForm.period = (Period) period.getSelectedItem();
                registerForm.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                registerForm.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(RegisterFormActivity.this, "Saved");
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
                                AppUtil.createLongNotification(RegisterFormActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(RegisterFormActivity.this, RegisterFormListActivity.class);
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

                        Intent intent = new Intent(RegisterFormActivity.this, RegisterFormListActivity.class);
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
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.register_question_one);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion1()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion1()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne1));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne1));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour1));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour1));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine1));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine1));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen1));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen1));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen1));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen1));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour1));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour1));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine1));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine1));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus1));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus1));
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

                        registerForm.maleLessThanOne1 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour1 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine1 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion1()));
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

                        registerForm.femaleLessThanOne1 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour1 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine1 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion1()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne1 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne1 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour1 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour1 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine1 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine1 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
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
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.register_question_two);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion2()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion2()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne2));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne2));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour2));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour2));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine2));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine2));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen2));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen2));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen2));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen2));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour2));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour2));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine2));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine2));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus2));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus2));
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

                        registerForm.maleLessThanOne2 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour2 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine2 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion2()));
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

                        registerForm.femaleLessThanOne2 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour2 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine2 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion2()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne2 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne2 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour2 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour2 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine2 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine2 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        txt_name.setText(R.string.register_question_three);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion3()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion3()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne3));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne3));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour3));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour3));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine3));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine3));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen3));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen3));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen3));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen3));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour3));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour3));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine3));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine3));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus3));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus3));
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

                        registerForm.maleLessThanOne3 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour3 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine3 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen3 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen3 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour3 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine3 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion3()));
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

                        registerForm.femaleLessThanOne3 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour3 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine3 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen3 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen3 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour3 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine3 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion3()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne3 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne3 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour3 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour3 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine3 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine3 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen3 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen3 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen3 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen3 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour3 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour3 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine3 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine3 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        txt_name.setText(R.string.register_question_four);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion4()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion4()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne4));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne4));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour4));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour4));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine4));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine4));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen4));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen4));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen4));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen4));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour4));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour4));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine4));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine4));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus4));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus4));
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

                        registerForm.maleLessThanOne4 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour4 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine4 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen4 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen4 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour4 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine4 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion4()));
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

                        registerForm.femaleLessThanOne4 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour4 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine4 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen4 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen4 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour4 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine4 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion4()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne4 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne4 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour4 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour4 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine4 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine4 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen4 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen4 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen4 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen4 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour4 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour4 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine4 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine4 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        txt_name.setText(R.string.register_question_five);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion5()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion5()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne5));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne5));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour5));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour5));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine5));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine5));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen5));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen5));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen5));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen5));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour5));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour5));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine5));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine5));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus5));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus5));
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

                        registerForm.maleLessThanOne5 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour5 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine5 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen5 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen5 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour5 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine5 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus5 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion5()));
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

                        registerForm.femaleLessThanOne5 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour5 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine5 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen5 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen5 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour5 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine5 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus5 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion5()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne5 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne5 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour5 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour5 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine5 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine5 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen5 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen5 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen5 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen5 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour5 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour5 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine5 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine5 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus5 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus5 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionSix() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.register_question_six);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion6()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion6()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne6));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne6));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour6));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour6));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine6));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine6));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen6));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen6));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen6));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen6));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour6));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour6));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine6));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine6));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus6));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus6));
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

                        registerForm.maleLessThanOne6 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour6 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine6 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen6 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen6 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour6 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine6 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus6 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion6()));
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

                        registerForm.femaleLessThanOne6 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour6 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine6 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen6 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen6 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour6 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine6 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus6 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion6()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne6 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne6 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour6 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour6 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine6 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine6 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen6 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen6 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen6 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen6 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour6 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour6 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine6 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine6 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus6 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus6 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionSeven() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.register_question_seven);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion7()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion7()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne7));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne7));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour7));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour7));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine7));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine7));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen7));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen7));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen7));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen7));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour7));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour7));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine7));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine7));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus7));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus7));
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

                        registerForm.maleLessThanOne7 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour7 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine7 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen7 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen7 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour7 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine7 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus7 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion7()));
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

                        registerForm.femaleLessThanOne7 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour7 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine7 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen7 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen7 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour7 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine7 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus7 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion7()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne7 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne7 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour7 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour7 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine7 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine7 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen7 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen7 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen7 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen7 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour7 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour7 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine7 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine7 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus7 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus7 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionEight() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dsd_question_activity);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.register_question_eight);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion8()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion8()));

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

        if (registerForm != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(registerForm.maleLessThanOne8));
            femaleLessThanOne.setText(AppUtil.getLongValue(registerForm.femaleLessThanOne8));
            maleOneToFour.setText(AppUtil.getLongValue(registerForm.maleOneToFour8));
            femaleOneToFour.setText(AppUtil.getLongValue(registerForm.femaleOneToFour8));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine8));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine8));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen8));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen8));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.maleFifteenToNineteen8));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(registerForm.femaleFifteenToNineteen8));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.maleTwentyToTwentyFour8));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(registerForm.femaleTwentyToTwentyFour8));
            maleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFortyNine8));
            femaleTwentyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFortyNine8));
            maleFiftyPlus.setText(AppUtil.getLongValue(registerForm.maleFiftyPlus8));
            femaleFiftyPlus.setText(AppUtil.getLongValue(registerForm.femaleFiftyPlus8));
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

                        registerForm.maleLessThanOne8 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour8 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine8 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen8 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen8 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour8 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToFortyNine8 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus8 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion8()));
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

                        registerForm.femaleLessThanOne8 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour8 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine8 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen8 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen8 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour8 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToFortyNine8 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());
                        registerForm.femaleFiftyPlus8 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion8()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.maleLessThanOne8 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                registerForm.femaleLessThanOne8 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                registerForm.maleOneToFour8 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                registerForm.femaleOneToFour8 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                registerForm.maleFiveToNine8 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.femaleFiveToNine8 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                registerForm.maleTenToFourteen8 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.femaleTenToFourteen8 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                registerForm.maleFifteenToNineteen8 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.femaleFifteenToNineteen8 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                registerForm.maleTwentyToTwentyFour8 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.femaleTwentyToTwentyFour8 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                registerForm.maleTwentyFiveToFortyNine8 = AppUtil.getLongValue(maleTwentyFiveToFortyNine.getText().toString());
                registerForm.femaleTwentyFiveToFortyNine8 = AppUtil.getLongValue(femaleTwentyFiveToFortyNine.getText().toString());

                registerForm.maleFiftyPlus8 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleFiftyPlus8 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionNine() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pmtct_eid);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.pmtct_eid);

        final EditText pmtctEIDP4 = (EditText) dialog.findViewById(R.id.pmtctEIDP4);
        final EditText pmtctEIDP5 = (EditText) dialog.findViewById(R.id.pmtctEIDP5);
        final EditText pmtctEIDP14 = (EditText) dialog.findViewById(R.id.pmtctEIDP14);
        final EditText pmtctEIDP17 = (EditText) dialog.findViewById(R.id.pmtctEIDP17);
        final EditText pmtctEIDP30 = (EditText) dialog.findViewById(R.id.pmtctEIDP30);
        final EditText pmtctEIDP31 = (EditText) dialog.findViewById(R.id.pmtctEIDP31);

        if (registerForm != null) {
            pmtctEIDP4.setText(AppUtil.getLongValue(registerForm.pmtctEIDP4));
            pmtctEIDP5.setText(AppUtil.getLongValue(registerForm.pmtctEIDP5));
            pmtctEIDP14.setText(AppUtil.getLongValue(registerForm.pmtctEIDP14));
            pmtctEIDP17.setText(AppUtil.getLongValue(registerForm.pmtctEIDP17));
            pmtctEIDP30.setText(AppUtil.getLongValue(registerForm.pmtctEIDP30));
            pmtctEIDP31.setText(AppUtil.getLongValue(registerForm.pmtctEIDP31));
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.pmtctEIDP4 = AppUtil.getLongValue(pmtctEIDP4.getText().toString());
                registerForm.pmtctEIDP5 = AppUtil.getLongValue(pmtctEIDP5.getText().toString());

                registerForm.pmtctEIDP14 = AppUtil.getLongValue(pmtctEIDP14.getText().toString());
                registerForm.pmtctEIDP17 = AppUtil.getLongValue(pmtctEIDP17.getText().toString());

                registerForm.pmtctEIDP30 = AppUtil.getLongValue(pmtctEIDP30.getText().toString());
                registerForm.pmtctEIDP31 = AppUtil.getLongValue(pmtctEIDP31.getText().toString());

                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionTen() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pmtct_stat);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.pmtct_stat);

        final EditText pmtctSTATP1 = (EditText) dialog.findViewById(R.id.pmtctSTATP1);
        final EditText pmtctSTATP2 = (EditText) dialog.findViewById(R.id.pmtctSTATP2);
        final EditText pmtctSTATP5 = (EditText) dialog.findViewById(R.id.pmtctSTATP5);
        final EditText pmtctSTATP12 = (EditText) dialog.findViewById(R.id.pmtctSTATP12);
        final EditText pmtctSTATP13 = (EditText) dialog.findViewById(R.id.pmtctSTATP13);
        final EditText pmtctSTATP12DisaggregationP4 = (EditText) dialog.findViewById(R.id.pmtctSTATP12DisaggregationP4);
        final EditText pmtctSTATP12DisaggregationP5 = (EditText) dialog.findViewById(R.id.pmtctSTATP12DisaggregationP5);
        final EditText pmtctSTATP12DisaggregationP14 = (EditText) dialog.findViewById(R.id.pmtctSTATP12DisaggregationP14);
        final EditText pmtctSTATP12DisaggregationP17 = (EditText) dialog.findViewById(R.id.pmtctSTATP12DisaggregationP17);

        if (registerForm != null) {
            pmtctSTATP1.setText(AppUtil.getLongValue(registerForm.pmtctSTATP1));
            pmtctSTATP2.setText(AppUtil.getLongValue(registerForm.pmtctSTATP2));
            pmtctSTATP5.setText(AppUtil.getLongValue(registerForm.pmtctSTATP5));
            pmtctSTATP12.setText(AppUtil.getLongValue(registerForm.pmtctSTATP12));
            pmtctSTATP13.setText(AppUtil.getLongValue(registerForm.pmtctSTATP13));
            pmtctSTATP12DisaggregationP4.setText(AppUtil.getLongValue(registerForm.pmtctSTATP12DisaggregationP4));
            pmtctSTATP12DisaggregationP5.setText(AppUtil.getLongValue(registerForm.pmtctSTATP12DisaggregationP5));
            pmtctSTATP12DisaggregationP14.setText(AppUtil.getLongValue(registerForm.pmtctSTATP12DisaggregationP14));
            pmtctSTATP12DisaggregationP17.setText(AppUtil.getLongValue(registerForm.pmtctSTATP12DisaggregationP17));
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.pmtctSTATP1 = AppUtil.getLongValue(pmtctSTATP1.getText().toString());
                registerForm.pmtctSTATP2 = AppUtil.getLongValue(pmtctSTATP2.getText().toString());
                registerForm.pmtctSTATP5 = AppUtil.getLongValue(pmtctSTATP5.getText().toString());
                registerForm.pmtctSTATP12 = AppUtil.getLongValue(pmtctSTATP12.getText().toString());
                registerForm.pmtctSTATP13 = AppUtil.getLongValue(pmtctSTATP13.getText().toString());
                registerForm.pmtctSTATP12DisaggregationP4 = AppUtil.getLongValue(pmtctSTATP12DisaggregationP4.getText().toString());
                registerForm.pmtctSTATP12DisaggregationP5 = AppUtil.getLongValue(pmtctSTATP12DisaggregationP5.getText().toString());
                registerForm.pmtctSTATP12DisaggregationP14 = AppUtil.getLongValue(pmtctSTATP12DisaggregationP14.getText().toString());
                registerForm.pmtctSTATP12DisaggregationP17 = AppUtil.getLongValue(pmtctSTATP12DisaggregationP17.getText().toString());

                dialog.dismiss();

            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void questionEleven() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.entry_point);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText(R.string.entry_point);

        final EditText testedOPD = (EditText) dialog.findViewById(R.id.testedOPD);
        final EditText positiveTestedOPD = (EditText) dialog.findViewById(R.id.positiveTestedOPD);
        final EditText testedOutreach = (EditText) dialog.findViewById(R.id.testedOutreach);
        final EditText positiveTestedOutreach = (EditText) dialog.findViewById(R.id.positiveTestedOutreach);
        final EditText testedANC = (EditText) dialog.findViewById(R.id.testedANC);
        final EditText positiveTestedANC = (EditText) dialog.findViewById(R.id.positiveTestedANC);

        final EditText testedInpatient = (EditText) dialog.findViewById(R.id.testedInpatient);
        final EditText positiveTestedInpatient = (EditText) dialog.findViewById(R.id.positiveTestedInpatient);
        final EditText testedPaediatric = (EditText) dialog.findViewById(R.id.testedPaediatric);
        final EditText positiveTestedPaediatric = (EditText) dialog.findViewById(R.id.positiveTestedPaediatric);
        final EditText testedPMTCT = (EditText) dialog.findViewById(R.id.testedPMTCT);
        final EditText positiveTestedPMTCT = (EditText) dialog.findViewById(R.id.positiveTestedPMTCT);
        final EditText testedSTI = (EditText) dialog.findViewById(R.id.testedSTI);
        final EditText positiveTestedSTI = (EditText) dialog.findViewById(R.id.positiveTestedSTI);
        final EditText positiveTestedVMMC = (EditText) dialog.findViewById(R.id.positiveTestedVMMC);
        final EditText testedVMMC = (EditText) dialog.findViewById(R.id.testedVMMC);
        final EditText testedVIAC = (EditText) dialog.findViewById(R.id.testedVIAC);
        final EditText positiveTestedVIAC = (EditText) dialog.findViewById(R.id.positiveTestedVIAC);
        final EditText testedTB = (EditText) dialog.findViewById(R.id.testedTB);
        final EditText positiveTestedTB = (EditText) dialog.findViewById(R.id.positiveTestedTB);

        if (registerForm != null) {
            testedOPD.setText(AppUtil.getLongValue(registerForm.testedOPD));
            positiveTestedOPD.setText(AppUtil.getLongValue(registerForm.positiveTestedOPD));
            testedOutreach.setText(AppUtil.getLongValue(registerForm.testedOutreach));
            positiveTestedOutreach.setText(AppUtil.getLongValue(registerForm.positiveTestedOutreach));
            testedANC.setText(AppUtil.getLongValue(registerForm.testedANC));
            positiveTestedANC.setText(AppUtil.getLongValue(registerForm.positiveTestedANC));

            testedInpatient.setText(AppUtil.getLongValue(registerForm.testedInpatient));
            positiveTestedInpatient.setText(AppUtil.getLongValue(registerForm.positiveTestedInpatient));
            testedPaediatric.setText(AppUtil.getLongValue(registerForm.testedPaediatric));
            positiveTestedPaediatric.setText(AppUtil.getLongValue(registerForm.positiveTestedPaediatric));
            testedPMTCT.setText(AppUtil.getLongValue(registerForm.testedPMTCT));
            positiveTestedPMTCT.setText(AppUtil.getLongValue(registerForm.positiveTestedPMTCT));
            testedSTI.setText(AppUtil.getLongValue(registerForm.testedSTI));
            positiveTestedSTI.setText(AppUtil.getLongValue(registerForm.positiveTestedSTI));
            positiveTestedVMMC.setText(AppUtil.getLongValue(registerForm.positiveTestedVMMC));
            testedVMMC.setText(AppUtil.getLongValue(registerForm.testedVMMC));
            testedVIAC.setText(AppUtil.getLongValue(registerForm.testedVIAC));
            positiveTestedVIAC.setText(AppUtil.getLongValue(registerForm.positiveTestedVIAC));
            testedTB.setText(AppUtil.getLongValue(registerForm.testedTB));
            positiveTestedTB.setText(AppUtil.getLongValue(registerForm.positiveTestedTB));

        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                registerForm.testedOPD = AppUtil.getLongValue(testedOPD.getText().toString());
                registerForm.positiveTestedOPD = AppUtil.getLongValue(positiveTestedOPD.getText().toString());
                registerForm.testedOutreach = AppUtil.getLongValue(testedOutreach.getText().toString());
                registerForm.positiveTestedOutreach = AppUtil.getLongValue(positiveTestedOutreach.getText().toString());
                registerForm.testedANC = AppUtil.getLongValue(testedANC.getText().toString());
                registerForm.positiveTestedANC = AppUtil.getLongValue(positiveTestedANC.getText().toString());

                registerForm.testedInpatient = AppUtil.getLongValue(testedInpatient.getText().toString());
                registerForm.positiveTestedInpatient = AppUtil.getLongValue(positiveTestedInpatient.getText().toString());
                registerForm.testedPaediatric = AppUtil.getLongValue(testedPaediatric.getText().toString());
                registerForm.positiveTestedPaediatric = AppUtil.getLongValue(positiveTestedPaediatric.getText().toString());
                registerForm.testedPMTCT = AppUtil.getLongValue(testedPMTCT.getText().toString());
                registerForm.positiveTestedPMTCT = AppUtil.getLongValue(positiveTestedPMTCT.getText().toString());
                registerForm.testedSTI = AppUtil.getLongValue(testedSTI.getText().toString());
                registerForm.positiveTestedSTI = AppUtil.getLongValue(positiveTestedSTI.getText().toString());
                registerForm.testedVMMC = AppUtil.getLongValue(testedVMMC.getText().toString());
                registerForm.positiveTestedVMMC = AppUtil.getLongValue(positiveTestedVMMC.getText().toString());
                registerForm.testedVIAC = AppUtil.getLongValue(testedVIAC.getText().toString());
                registerForm.positiveTestedVIAC = AppUtil.getLongValue(positiveTestedVIAC.getText().toString());
                registerForm.testedTB = AppUtil.getLongValue(testedTB.getText().toString());
                registerForm.positiveTestedTB = AppUtil.getLongValue(positiveTestedTB.getText().toString());

                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }

    public void upDateForm() {

        btn_question_one.setText(this.getString(R.string.register_question_one)
                + " [ " + (registerForm.maleQuestion1() + registerForm.femaleQuestion1()) + " ]");

        btn_question_two.setText(this.getString(R.string.register_question_two)
                + " [ " + (registerForm.maleQuestion2() + registerForm.femaleQuestion2()) + " ]");

        btn_question_three.setText(this.getString(R.string.register_question_three)
                + " [ " + (registerForm.maleQuestion3() + registerForm.femaleQuestion3()) + " ]");

        btn_question_four.setText(this.getString(R.string.register_question_four)
                + " [ " + (registerForm.maleQuestion4() + registerForm.femaleQuestion4()) + " ]");

        btn_question_five.setText(this.getString(R.string.register_question_five)
                + " [ " + (registerForm.maleQuestion5() + registerForm.femaleQuestion5()) + " ]");
        btn_question_six.setText(this.getString(R.string.register_question_six)
                + " [ " + (registerForm.maleQuestion6() + registerForm.femaleQuestion6()) + " ]");
        btn_question_seven.setText(this.getString(R.string.register_question_seven)
                + " [ " + (registerForm.maleQuestion7() + registerForm.femaleQuestion7()) + " ]");
        btn_question_eight.setText(this.getString(R.string.register_question_eight)
                + " [ " + (registerForm.maleQuestion8() + registerForm.femaleQuestion8()) + " ]");

        btn_question_nine.setText(this.getString(R.string.pmtct_eid));
        btn_question_ten.setText(this.getString(R.string.pmtct_stat));
    }
}

