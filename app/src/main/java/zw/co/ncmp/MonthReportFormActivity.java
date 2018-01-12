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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zw.co.ncmp.business.*;
import zw.co.ncmp.util.AppUtil;

public class MonthReportFormActivity extends MenuBar implements View.OnClickListener {

    private MonthReportForm registerForm;

    Spinner facility;
    Spinner province;
    Spinner district;
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
    ArrayAdapter<Province> provinceArrayAdapter;
    ArrayAdapter<District> districtArrayAdapter;
    EditText province_label;

    private DatePickerDialog datePickerDialog;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form_activity);

        Intent intent = getIntent();
        Long registerForm_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        province = (Spinner) findViewById(R.id.province);
        district = (Spinner) findViewById(R.id.district);
        period = (Spinner) findViewById(R.id.period);
        title = (TextView) findViewById(R.id.txt_name);
        name = (EditText) findViewById(R.id.name);
        province_label = (EditText) findViewById(R.id.province_label);
        province_label.setVisibility(View.GONE);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCreated = (EditText) findViewById(R.id.dateCreated);
        dateCreated.setOnClickListener(this);
        province.setClickable(false);
        province.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                provinceArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, Province.getAll());
                province.setAdapter(provinceArrayAdapter);
                provinceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                provinceArrayAdapter.notifyDataSetChanged();
                province_label.setVisibility(View.GONE);
                return false;
            }
        });

        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Province item = provinceArrayAdapter.getItem(i);
                districtArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, District.findByProvince(item));
                districtArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                district.setAdapter(districtArrayAdapter);
                districtArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                District item = districtArrayAdapter.getItem(i);
                facilityArrayAdapter = new ArrayAdapter<>(getApplication(), R.layout.spinner_item, Facility.findByDistrict(item));
                facilityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                facility.setAdapter(facilityArrayAdapter);
                facilityArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<Period> periodArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, Period.getAll());
        periodArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        period.setAdapter(periodArrayAdapter);

        if (registerForm_id != 0) {
            registerForm = MonthReportForm.get(registerForm_id);
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
            registerForm = new MonthReportForm();
            setSupportActionBar(createToolBar(this.getString(R.string.register_title)));
        }

        title.setText(this.getString(R.string.month_report_form_title));

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
                AppUtil.createShortNotification(MonthReportFormActivity.this, "Saved");
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
                                AppUtil.createLongNotification(MonthReportFormActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(MonthReportFormActivity.this, MonthReportFormListActivity.class);
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

        if (provinceArrayAdapter == null) {
            province_label.setVisibility(View.VISIBLE);
            province_label.setError("Please select a province");
            valid = false;
        }
        else {
            province_label.setError(null);
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

                        Intent intent = new Intent(MonthReportFormActivity.this, MonthReportFormListActivity.class);
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
        txt_name.setText("HTS_TST B6-HIV Tested And Received Results Disaggregated By Age/Sex");

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion1()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion1()));

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

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion1()));
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

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion1()));
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
        dialog.setContentView(R.layout.htstst_individuals_dialog);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("HTS_TST B7-HIV Tested And Received Results Disaggregated By Age/Sex");

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion2()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion2()));

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
            maleLessThanTwoMonths.setText(AppUtil.getLongValue(registerForm.maleLessThanTwoMonths2));
            femaleLessThanTwoMonths.setText(AppUtil.getLongValue(registerForm.femaleLessThanTwoMonths2));
            maleTwoToTwelveMonths.setText(AppUtil.getLongValue(registerForm.maleTwoToTwelveMonths2));
            femaleTwoToTwelveMonths.setText(AppUtil.getLongValue(registerForm.femaleTwoToTwelveMonths2));
            maleThirteenToTwentyFourMonths.setText(AppUtil.getLongValue(registerForm.maleThirteenToTwentyFourMonths2));
            femaleThirteenToTwentyFourMonths.setText(AppUtil.getLongValue(registerForm.femaleThirteenToTwentyFourMonths2));
            maleTwentyFiveToFiftyNineMonths.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToFiftyNineMonths2));
            femaleTwentyFiveToFiftyNineMonths.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToFiftyNineMonths2));
            maleFiveToNine.setText(AppUtil.getLongValue(registerForm.maleFiveToNine2));
            femaleFiveToNine.setText(AppUtil.getLongValue(registerForm.femaleFiveToNine2));
            maleTenToFourteen.setText(AppUtil.getLongValue(registerForm.maleTenToFourteen2));
            femaleTenToFourteen.setText(AppUtil.getLongValue(registerForm.femaleTenToFourteen2));
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

                        registerForm.maleLessThanTwoMonths2 = AppUtil.getLongValue(maleLessThanTwoMonths.getText().toString());
                        registerForm.maleTwoToTwelveMonths2 = AppUtil.getLongValue(maleTwoToTwelveMonths.getText().toString());
                        registerForm.maleThirteenToTwentyFourMonths2 = AppUtil.getLongValue(maleThirteenToTwentyFourMonths.getText().toString());
                        registerForm.maleTwentyFiveToFiftyNineMonths2 = AppUtil.getLongValue(maleTwentyFiveToFiftyNineMonths.getText().toString());
                        registerForm.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToFortyNine2 = AppUtil.getLongValue(maleThirtyToFortyNine.getText().toString());
                        registerForm.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion2()));
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

                        registerForm.femaleLessThanTwoMonths2 = AppUtil.getLongValue(femaleLessThanTwoMonths.getText().toString());
                        registerForm.femaleTwoToTwelveMonths2 = AppUtil.getLongValue(femaleTwoToTwelveMonths.getText().toString());
                        registerForm.femaleThirteenToTwentyFourMonths2 = AppUtil.getLongValue(femaleThirteenToTwentyFourMonths.getText().toString());
                        registerForm.femaleTwentyFiveToFiftyNineMonths2 = AppUtil.getLongValue(femaleTwentyFiveToFiftyNineMonths.getText().toString());
                        registerForm.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToFortyNine2 = AppUtil.getLongValue(femaleThirtyToFortyNine.getText().toString());
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

                registerForm.maleLessThanTwoMonths2 = AppUtil.getLongValue(maleLessThanTwoMonths.getText().toString());
                registerForm.maleTwoToTwelveMonths2 = AppUtil.getLongValue(maleTwoToTwelveMonths.getText().toString());
                registerForm.maleThirteenToTwentyFourMonths2 = AppUtil.getLongValue(maleThirteenToTwentyFourMonths.getText().toString());
                registerForm.maleTwentyFiveToFiftyNineMonths2 = AppUtil.getLongValue(maleTwentyFiveToFiftyNineMonths.getText().toString());
                registerForm.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                registerForm.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                registerForm.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                registerForm.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                registerForm.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.maleThirtyToFortyNine2 = AppUtil.getLongValue(maleThirtyToFortyNine.getText().toString());
                registerForm.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                registerForm.femaleLessThanTwoMonths = AppUtil.getLongValue(femaleLessThanTwoMonths.getText().toString());
                registerForm.femaleTwoToTwelveMonths2 = AppUtil.getLongValue(femaleTwoToTwelveMonths.getText().toString());
                registerForm.femaleThirteenToTwentyFourMonths2 = AppUtil.getLongValue(femaleThirteenToTwentyFourMonths.getText().toString());
                registerForm.femaleTwentyFiveToFiftyNineMonths2 = AppUtil.getLongValue(femaleTwentyFiveToFiftyNineMonths.getText().toString());
                registerForm.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                registerForm.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
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

    public void questionThree() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.htstst_couples_dialog);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("HTS_TST B6-HIV Tested HIV Positive Disaggregated By Age/Sex");

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion3()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion3()));

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

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion3()));
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

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion3()));
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

    public void questionFour() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.htstst_couples_dialog);

        TextView txt_name = (TextView) dialog.findViewById(R.id.txt_name);
        txt_name.setText("HTS_TST B7-HIV Tested HIV Positive Disaggregated By Age/Sex");

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion4()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion4()));

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

                        maleTotal.setText(AppUtil.getLongValue(registerForm.maleQuestion4()));
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

                        femaleTotal.setText(AppUtil.getLongValue(registerForm.femaleQuestion4()));
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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToTwentyNine5));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToTwentyNine5));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.maleThirtyToThirtyFour5));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.femaleThirtyToThirtyFour5));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.maleThirtyFiveToThirtyNine5));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.femaleThirtyFiveToThirtyNine5));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.maleFortyToFortyFour5));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.femaleFortyToFortyFour5));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleFortyFiveToFortyNine5));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleFortyFiveToFortyNine5));
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

                        registerForm.maleLessThanOne5 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour5 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine5 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen5 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen5 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour5 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine5 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToThirtyFour5 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        registerForm.maleThirtyFiveToThirtyNine5 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.maleFortyToFortyFour5 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        registerForm.maleFortyFiveToFortyNine5 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
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

                        registerForm.femaleLessThanOne5 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour5 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine5 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen5 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen5 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour5 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine5 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToThirtyFour5 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        registerForm.femaleThirtyFiveToThirtyNine5 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.femaleFortyToFortyFour5 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        registerForm.femaleFortyFiveToFortyNine5 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
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

                registerForm.maleTwentyFiveToTwentyNine5 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.femaleTwentyFiveToTwentyNine5 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                registerForm.maleThirtyToThirtyFour5 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                registerForm.femaleThirtyToThirtyFour5 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                registerForm.maleThirtyFiveToThirtyNine5 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                registerForm.femaleThirtyFiveToThirtyNine5 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                registerForm.maleFortyToFortyFour5 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                registerForm.femaleFortyToFortyFour5 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                registerForm.maleFortyFiveToFortyNine5 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                registerForm.femaleFortyFiveToFortyNine5 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToTwentyNine6));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToTwentyNine6));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.maleThirtyToThirtyFour6));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.femaleThirtyToThirtyFour6));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.maleThirtyFiveToThirtyNine6));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.femaleThirtyFiveToThirtyNine6));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.maleFortyToFortyFour6));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.femaleFortyToFortyFour6));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleFortyFiveToFortyNine6));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleFortyFiveToFortyNine6));
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

                        registerForm.maleLessThanOne6 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour6 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine6 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen6 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen6 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour6 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine6 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToThirtyFour6 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        registerForm.maleThirtyFiveToThirtyNine6 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.maleFortyToFortyFour6 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        registerForm.maleFortyFiveToFortyNine6 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
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

                        registerForm.femaleLessThanOne6 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour6 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine6 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen6 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen6 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour6 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine6 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToThirtyFour6 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        registerForm.femaleThirtyFiveToThirtyNine6 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.femaleFortyToFortyFour6 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        registerForm.femaleFortyFiveToFortyNine6 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
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

                registerForm.maleTwentyFiveToTwentyNine6 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.femaleTwentyFiveToTwentyNine6 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                registerForm.maleThirtyToThirtyFour6 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                registerForm.femaleThirtyToThirtyFour6 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                registerForm.maleThirtyFiveToThirtyNine6 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                registerForm.femaleThirtyFiveToThirtyNine6 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                registerForm.maleFortyToFortyFour6 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                registerForm.femaleFortyToFortyFour6 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                registerForm.maleFortyFiveToFortyNine6 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                registerForm.femaleFortyFiveToFortyNine6 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToTwentyNine7));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToTwentyNine7));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.maleThirtyToThirtyFour7));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.femaleThirtyToThirtyFour7));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.maleThirtyFiveToThirtyNine7));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.femaleThirtyFiveToThirtyNine7));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.maleFortyToFortyFour7));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.femaleFortyToFortyFour7));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleFortyFiveToFortyNine7));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleFortyFiveToFortyNine7));
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

                        registerForm.maleLessThanOne7 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour7 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine7 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen7 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen7 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour7 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine7 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToThirtyFour7 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        registerForm.maleThirtyFiveToThirtyNine7 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.maleFortyToFortyFour7 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        registerForm.maleFortyFiveToFortyNine7 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
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

                        registerForm.femaleLessThanOne7 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour7 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine7 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen7 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen7 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour7 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine7 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToThirtyFour7 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        registerForm.femaleThirtyFiveToThirtyNine7 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.femaleFortyToFortyFour7 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        registerForm.femaleFortyFiveToFortyNine7 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
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

                registerForm.maleTwentyFiveToTwentyNine7 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.femaleTwentyFiveToTwentyNine7 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                registerForm.maleThirtyToThirtyFour7 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                registerForm.femaleThirtyToThirtyFour7 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                registerForm.maleThirtyFiveToThirtyNine7 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                registerForm.femaleThirtyFiveToThirtyNine7 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                registerForm.maleFortyToFortyFour7 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                registerForm.femaleFortyToFortyFour7 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                registerForm.maleFortyFiveToFortyNine7 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                registerForm.femaleFortyFiveToFortyNine7 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

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
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.maleTwentyFiveToTwentyNine8));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(registerForm.femaleTwentyFiveToTwentyNine8));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.maleThirtyToThirtyFour8));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(registerForm.femaleThirtyToThirtyFour8));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.maleThirtyFiveToThirtyNine8));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(registerForm.femaleThirtyFiveToThirtyNine8));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.maleFortyToFortyFour8));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(registerForm.femaleFortyToFortyFour8));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.maleFortyFiveToFortyNine8));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(registerForm.femaleFortyFiveToFortyNine8));
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

                        registerForm.maleLessThanOne8 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        registerForm.maleOneToFour8 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        registerForm.maleFiveToNine8 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        registerForm.maleTenToFourteen8 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        registerForm.maleFifteenToNineteen8 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        registerForm.maleTwentyToTwentyFour8 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        registerForm.maleTwentyFiveToTwentyNine8 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.maleThirtyToThirtyFour8 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        registerForm.maleThirtyFiveToThirtyNine8 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.maleFortyToFortyFour8 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        registerForm.maleFortyFiveToFortyNine8 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
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

                        registerForm.femaleLessThanOne8 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        registerForm.femaleOneToFour8 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        registerForm.femaleFiveToNine8 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        registerForm.femaleTenToFourteen8 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        registerForm.femaleFifteenToNineteen8 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        registerForm.femaleTwentyToTwentyFour8 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        registerForm.femaleTwentyFiveToTwentyNine8 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        registerForm.femaleThirtyToThirtyFour8 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        registerForm.femaleThirtyFiveToThirtyNine8 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        registerForm.femaleFortyToFortyFour8 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        registerForm.femaleFortyFiveToFortyNine8 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
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

                registerForm.maleTwentyFiveToTwentyNine8 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                registerForm.femaleTwentyFiveToTwentyNine8 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                registerForm.maleThirtyToThirtyFour8 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                registerForm.femaleThirtyToThirtyFour8 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                registerForm.maleThirtyFiveToThirtyNine8 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                registerForm.femaleThirtyFiveToThirtyNine8 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                registerForm.maleFortyToFortyFour8 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                registerForm.femaleFortyToFortyFour8 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                registerForm.maleFortyFiveToFortyNine8 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                registerForm.femaleFortyFiveToFortyNine8 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

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
        final EditText p36lessThanOrEqualToTwoMonths = (EditText) dialog.findViewById(R.id.p36lessThanOrEqualToTwoMonths);
        final EditText p36threeToTwelveMonths = (EditText) dialog.findViewById(R.id.p36threeToTwelveMonths);
        final EditText p36thirteenToTwentyFour = (EditText) dialog.findViewById(R.id.p36thirteenToTwentyFour);
        final EditText p37lessThanOrEqualToTwoMonths = (EditText) dialog.findViewById(R.id.p37lessThanOrEqualToTwoMonths);
        final EditText p37threeToTwelveMonths = (EditText) dialog.findViewById(R.id.p37threeToTwelveMonths);
        final EditText p37thirteenToTwentyFour = (EditText) dialog.findViewById(R.id.p37thirteenToTwentyFour);
        final EditText p38lessThanOrEqualToTwoMonths = (EditText) dialog.findViewById(R.id.p38lessThanOrEqualToTwoMonths);
        final EditText p38threeToTwelveMonths = (EditText) dialog.findViewById(R.id.p38threeToTwelveMonths);
        final EditText p38thirteenToTwentyFour = (EditText) dialog.findViewById(R.id.p38thirteenToTwentyFour);

        if (registerForm != null) {
            pmtctEIDP4.setText(AppUtil.getLongValue(registerForm.pmtctEIDP4));
            pmtctEIDP5.setText(AppUtil.getLongValue(registerForm.pmtctEIDP5));
            pmtctEIDP14.setText(AppUtil.getLongValue(registerForm.pmtctEIDP14));
            pmtctEIDP17.setText(AppUtil.getLongValue(registerForm.pmtctEIDP17));
            pmtctEIDP30.setText(AppUtil.getLongValue(registerForm.pmtctEIDP30));
            pmtctEIDP31.setText(AppUtil.getLongValue(registerForm.pmtctEIDP31));
            p36lessThanOrEqualToTwoMonths.setText(AppUtil.getLongValue(registerForm.lessThanTwo));
            p36thirteenToTwentyFour.setText(AppUtil.getLongValue(registerForm.thirteenToTwentyFour));
            p36threeToTwelveMonths.setText(AppUtil.getLongValue(registerForm.threeToTwelve));
            p37lessThanOrEqualToTwoMonths.setText(AppUtil.getLongValue(registerForm.lessThanTwo1));
            p37thirteenToTwentyFour.setText(AppUtil.getLongValue(registerForm.thirteenToTwentyFour1));
            p37threeToTwelveMonths.setText(AppUtil.getLongValue(registerForm.threeToTwelve1));
            p38lessThanOrEqualToTwoMonths.setText(AppUtil.getLongValue(registerForm.lessThanTwo2));
            p38thirteenToTwentyFour.setText(AppUtil.getLongValue(registerForm.thirteenToTwentyFour2));
            p38threeToTwelveMonths.setText(AppUtil.getLongValue(registerForm.threeToTwelve2));
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

                registerForm.lessThanTwo = AppUtil.getLongValue(p36lessThanOrEqualToTwoMonths.getText().toString());
                registerForm.threeToTwelve = AppUtil.getLongValue(p36threeToTwelveMonths.getText().toString());
                registerForm.thirteenToTwentyFour = AppUtil.getLongValue(p36thirteenToTwentyFour.getText().toString());

                registerForm.lessThanTwo1 = AppUtil.getLongValue(p37lessThanOrEqualToTwoMonths.getText().toString());
                registerForm.threeToTwelve1 = AppUtil.getLongValue(p37threeToTwelveMonths.getText().toString());
                registerForm.thirteenToTwentyFour1 = AppUtil.getLongValue(p37thirteenToTwentyFour.getText().toString());

                registerForm.lessThanTwo2 = AppUtil.getLongValue(p38lessThanOrEqualToTwoMonths.getText().toString());
                registerForm.threeToTwelve2 = AppUtil.getLongValue(p38threeToTwelveMonths.getText().toString());
                registerForm.thirteenToTwentyFour2 = AppUtil.getLongValue(p38thirteenToTwentyFour.getText().toString());


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

