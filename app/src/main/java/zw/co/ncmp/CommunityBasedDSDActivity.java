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
import zw.co.ncmp.business.CommunityBasedDSD;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.RegisterForm;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 3/21/2017.
 */
public class CommunityBasedDSDActivity extends MenuBar implements View.OnClickListener{

    private CommunityBasedDSD registerForm;

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
    Button btn_question_three;
    Button btn_question_four;
    Button btn_question_five;
    Button btn_question_six;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_dsd);

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
            registerForm = CommunityBasedDSD.get(registerForm_id);
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

            setSupportActionBar(createToolBar("Community Based DSD HIV Testing - Update"));
        } else {
            registerForm = new CommunityBasedDSD();
            setSupportActionBar(createToolBar("Community Based DSD HIV Testing"));
        }

        title.setText("Community Based DSD");

        btn_question_one = (Button) findViewById(R.id.btn_question_one);
        btn_question_one.setOnClickListener(this);
        btn_question_one.setText("Community Self Testing Disaggregated By Age/Sex - Positive");

        btn_question_two = (Button) findViewById(R.id.btn_question_two);
        btn_question_two.setOnClickListener(this);
        btn_question_two.setText("Community Self Testing Disaggregated By Age/Sex - Negative");

        btn_question_three = (Button) findViewById(R.id.btn_question_three);
        btn_question_three.setOnClickListener(this);
        btn_question_three.setText("Community Index Testing Disaggregated By Age/Sex - Positive");

        btn_question_four = (Button) findViewById(R.id.btn_question_four);
        btn_question_four.setOnClickListener(this);
        btn_question_four.setText("Community Index Testing Disaggregated By Age/Sex - Negative");

        btn_question_five = (Button) findViewById(R.id.btn_question_five);
        btn_question_five.setOnClickListener(this);
        btn_question_five.setText("Community Targeted Mobile Testing Disaggregated By Age/Sex - Positive");

        btn_question_six = (Button) findViewById(R.id.btn_question_six);
        btn_question_six.setOnClickListener(this);
        btn_question_six.setText("Community Targeted Mobile Testing Disaggregated By Age/Sex - Negative");

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

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                registerForm.name = name.getText().toString();
                registerForm.facility = (Facility) facility.getSelectedItem();
                registerForm.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                registerForm.startDate = AppUtil.getDate(startDate.getText().toString());
                registerForm.endDate = AppUtil.getDate(endDate.getText().toString());
                registerForm.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(CommunityBasedDSDActivity.this, "Saved");
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
                                AppUtil.createLongNotification(CommunityBasedDSDActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(CommunityBasedDSDActivity.this, CommunityBasedDSDListActivity.class);
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

                        Intent intent = new Intent(CommunityBasedDSDActivity.this, CommunityBasedDSDListActivity.class);
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
        txt_name.setText("Community Self Testing Disaggregated By Age/Sex - Positive");

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
        txt_name.setText("Community Self Testing Disaggregated By Age/Sex - Negative");

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
        txt_name.setText("Community Index Testing Disaggregated By Age/Sex - Positive");

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
        txt_name.setText("Community Index Testing Disaggregated By Age/Sex - Negative");

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
        txt_name.setText("Community Targeted Mobile Testing Disaggregated By Age/Sex - Positive");

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
        txt_name.setText("Community Targeted Mobile Testing Disaggregated By Age/Sex - Positive");

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


    public void upDateForm() {

        btn_question_one.setText("Community Self Testing Disaggregated By Age/Sex - Positive [ " + (registerForm.maleQuestion1() + registerForm.femaleQuestion1()) + " ]");

        btn_question_two.setText("Community Self Testing Disaggregated By Age/Sex - Negative [ " + (registerForm.maleQuestion2() + registerForm.femaleQuestion2()) + " ]");

        btn_question_three.setText("Community Index Testing Disaggregated By Age/Sex - Positive [ " + (registerForm.maleQuestion3() + registerForm.femaleQuestion3()) + " ]");

        btn_question_four.setText( "Community Index Testing Disaggregated By Age/Sex - Negative [ " + (registerForm.maleQuestion4() + registerForm.femaleQuestion4()) + " ]");

        btn_question_five.setText("Community Targeted Mobile Testing Disaggregated By Age/Sex - Positive [ " + (registerForm.maleQuestion5() + registerForm.femaleQuestion5()) + " ]");
        btn_question_six.setText("Community Targeted Mobile Testing Disaggregated By Age/Sex - Negative [ " + (registerForm.maleQuestion6() + registerForm.femaleQuestion6()) + " ]");
    }
}
