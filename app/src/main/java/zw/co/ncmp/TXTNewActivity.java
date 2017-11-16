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

import org.w3c.dom.Text;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.TXTNew;
import zw.co.ncmp.util.AppUtil;

public class TXTNewActivity extends MenuBar implements View.OnClickListener {

    private TXTNew txtNew;

    Spinner facility;
    EditText dateCreated;
    EditText startDate;
    EditText endDate;

    Button btn_save;
    Button btn_completed;
    Button btn_submit;

    Button btn_question_one;
    Button btn_question_two;
    Button btn_question_three;
    Button btn_question_four;

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txt_new_form_activity);

        Intent intent = getIntent();
        Long txtNew_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);

        dateCreated = (EditText) findViewById(R.id.dateCreated);
        dateCreated.setOnClickListener(this);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateCreated, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));


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


        if (txtNew_id != 0) {
            txtNew = TXTNew.get(txtNew_id);
            updateLabel(dateCreated, txtNew.dateCreated);

            int i = 0;
            for (Facility s : Facility.getAll()) {
                if (txtNew.facility.equals(facility.getItemAtPosition(i))) {
                    facility.setSelection(i);
                    break;
                }
                i++;
            }
            setSupportActionBar(createToolBar("TX_NEW: DSD"));
        } else {
            txtNew = new TXTNew();
            setSupportActionBar(createToolBar("TX_NEW: DSD"));
        }

        btn_question_one = (Button) findViewById(R.id.btn_question_one);
        btn_question_one.setOnClickListener(this);
        btn_question_one.setText(R.string.txt_new_question_one);

        btn_question_two = (Button) findViewById(R.id.btn_question_two);
        btn_question_two.setOnClickListener(this);
        btn_question_two.setText(R.string.txt_new_question_two);

        btn_question_three = (Button) findViewById(R.id.btn_question_three);
        btn_question_three.setOnClickListener(this);
        btn_question_three.setText(R.string.txt_new_question_three);

        btn_question_four = (Button) findViewById(R.id.btn_question_four);
        btn_question_four.setOnClickListener(this);
        btn_question_four.setText(R.string.txt_new_question_four);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_save.setBackgroundResource(R.drawable.finish_background);

        btn_completed = (Button) findViewById(R.id.btn_completed);
        btn_completed.setVisibility(View.GONE);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);

        if (txtNew.dateCreated != null) {
            btn_submit.setVisibility(View.VISIBLE);
        }

        if (txtNew.dateSubmitted != null) {
            btn_submit.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            btn_completed.setVisibility(View.VISIBLE);
        }

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

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                txtNew.facility = (Facility) facility.getSelectedItem();
                txtNew.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                txtNew.startDate = AppUtil.getDate(startDate.getText().toString());
                txtNew.endDate = AppUtil.getDate(endDate.getText().toString());

                txtNew.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(TXTNewActivity.this, "Saved");
            } else {
                return;
            }
        }

        if (v.getId() == startDate.getId()) {
            datePickerDialog1.show();
        }

        if (v.getId() == endDate.getId()) {
            datePickerDialog2.show();
        }

        if (v.getId() == btn_submit.getId()) {
            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to submit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (validate()) {
                                txtNew.dateSubmitted = new Date();
                                txtNew.save();
                                AppUtil.createLongNotification(TXTNewActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(TXTNewActivity.this, TXTNewListActivity.class);
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


        name = startDate.getText().toString().toString();

        if (name.isEmpty()) {
            startDate.setError("Required");
            valid = false;
        } else {
            startDate.setError(null);
        }

        if( ! name.isEmpty()){
            if( ! checkDateFormat(name)){
                startDate.setError(getResources().getString(R.string.date_format_error));
                valid = false;
            }else{
                startDate.setError(null);
            }
        }


        name = endDate.getText().toString().toString();

        if (name.isEmpty()) {
            endDate.setError("Required");
            valid = false;
        } else {
            endDate.setError(null);
        }

        if( ! name.isEmpty()){
            if( ! checkDateFormat(name)){
                endDate.setError(getResources().getString(R.string.date_format_error));
                valid = false;
            }else{
                endDate.setError(null);
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
                        Intent intent = new Intent(TXTNewActivity.this, TXTNewListActivity.class);
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
        txt_name.setText(R.string.txt_new_question_one);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion1()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion1()));

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

        if (txtNew != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(txtNew.maleLessThanOne1));
            femaleLessThanOne.setText(AppUtil.getLongValue(txtNew.femaleLessThanOne1));
            maleOneToFour.setText(AppUtil.getLongValue(txtNew.maleOneToFour1));
            femaleOneToFour.setText(AppUtil.getLongValue(txtNew.femaleOneToFour1));
            maleFiveToNine.setText(AppUtil.getLongValue(txtNew.maleFiveToNine1));
            femaleFiveToNine.setText(AppUtil.getLongValue(txtNew.femaleFiveToNine1));
            maleTenToFourteen.setText(AppUtil.getLongValue(txtNew.maleTenToFourteen1));
            femaleTenToFourteen.setText(AppUtil.getLongValue(txtNew.femaleTenToFourteen1));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.maleFifteenToNineteen1));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.femaleFifteenToNineteen1));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.maleTwentyToTwentyFour1));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.femaleTwentyToTwentyFour1));
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.maleTwentyFiveToTwentyNine1));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.femaleTwentyFiveToTwentyNine1));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.maleThirtyToThirtyFour1));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.femaleThirtyToThirtyFour1));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.maleThirtyFiveToThirtyNine1));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.femaleThirtyFiveToThirtyNine1));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.maleFortyToFortyFour1));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.femaleFortyToFortyFour1));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.maleFortyFiveToFortyNine1));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.femaleFortyFiveToFortyNine1));
            maleFiftyPlus.setText(AppUtil.getLongValue(txtNew.maleFiftyPlus1));
            femaleFiftyPlus.setText(AppUtil.getLongValue(txtNew.femaleFiftyPlus1));
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

                        txtNew.maleLessThanOne1 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        txtNew.maleOneToFour1 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        txtNew.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        txtNew.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        txtNew.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        txtNew.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        txtNew.maleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.maleThirtyToThirtyFour1 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        txtNew.maleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.maleFortyToFortyFour1 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        txtNew.maleFortyFiveToFortyNine1 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        txtNew.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion1()));
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

                        txtNew.femaleLessThanOne1 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        txtNew.femaleOneToFour1 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        txtNew.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        txtNew.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        txtNew.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        txtNew.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        txtNew.femaleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.femaleThirtyToThirtyFour1 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        txtNew.femaleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.femaleFortyToFortyFour1 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        txtNew.femaleFortyFiveToFortyNine1 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        txtNew.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion1()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                txtNew.maleLessThanOne1 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                txtNew.femaleLessThanOne1 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                txtNew.maleOneToFour1 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                txtNew.femaleOneToFour1 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                txtNew.maleFiveToNine1 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                txtNew.femaleFiveToNine1 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                txtNew.maleTenToFourteen1 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                txtNew.femaleTenToFourteen1 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                txtNew.maleFifteenToNineteen1 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                txtNew.femaleFifteenToNineteen1 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                txtNew.maleTwentyToTwentyFour1 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                txtNew.femaleTwentyToTwentyFour1 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                txtNew.maleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                txtNew.femaleTwentyFiveToTwentyNine1 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                txtNew.maleThirtyToThirtyFour1 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                txtNew.femaleThirtyToThirtyFour1 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                txtNew.maleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                txtNew.femaleThirtyFiveToThirtyNine1 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                txtNew.maleFortyToFortyFour1 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                txtNew.femaleFortyToFortyFour1 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                txtNew.maleFortyFiveToFortyNine1 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                txtNew.femaleFortyFiveToFortyNine1 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                txtNew.maleFiftyPlus1 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                txtNew.femaleFiftyPlus1 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        txt_name.setText(R.string.txt_new_question_two);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion2()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion2()));

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

        if (txtNew != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(txtNew.maleLessThanOne2));
            femaleLessThanOne.setText(AppUtil.getLongValue(txtNew.femaleLessThanOne2));
            maleOneToFour.setText(AppUtil.getLongValue(txtNew.maleOneToFour2));
            femaleOneToFour.setText(AppUtil.getLongValue(txtNew.femaleOneToFour2));
            maleFiveToNine.setText(AppUtil.getLongValue(txtNew.maleFiveToNine2));
            femaleFiveToNine.setText(AppUtil.getLongValue(txtNew.femaleFiveToNine2));
            maleTenToFourteen.setText(AppUtil.getLongValue(txtNew.maleTenToFourteen2));
            femaleTenToFourteen.setText(AppUtil.getLongValue(txtNew.femaleTenToFourteen2));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.maleFifteenToNineteen2));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.femaleFifteenToNineteen2));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.maleTwentyToTwentyFour2));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.femaleTwentyToTwentyFour2));
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.maleTwentyFiveToTwentyNine2));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.femaleTwentyFiveToTwentyNine2));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.maleThirtyToThirtyFour2));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.femaleThirtyToThirtyFour2));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.maleThirtyFiveToThirtyNine2));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.femaleThirtyFiveToThirtyNine2));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.maleFortyToFortyFour2));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.femaleFortyToFortyFour2));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.maleFortyFiveToFortyNine2));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.femaleFortyFiveToFortyNine2));
            maleFiftyPlus.setText(AppUtil.getLongValue(txtNew.maleFiftyPlus2));
            femaleFiftyPlus.setText(AppUtil.getLongValue(txtNew.femaleFiftyPlus2));
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

                        txtNew.maleLessThanOne2 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        txtNew.maleOneToFour2 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        txtNew.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        txtNew.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        txtNew.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        txtNew.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        txtNew.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.maleThirtyToThirtyFour2 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        txtNew.maleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.maleFortyToFortyFour2 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        txtNew.maleFortyFiveToFortyNine2 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        txtNew.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion2()));
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

                        txtNew.femaleLessThanOne2 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        txtNew.femaleOneToFour2 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        txtNew.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        txtNew.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        txtNew.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        txtNew.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        txtNew.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.femaleThirtyToThirtyFour2 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        txtNew.femaleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.femaleFortyToFortyFour2 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        txtNew.femaleFortyFiveToFortyNine2 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        txtNew.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion2()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                txtNew.maleLessThanOne2 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                txtNew.femaleLessThanOne2 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                txtNew.maleOneToFour2 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                txtNew.femaleOneToFour2 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                txtNew.maleFiveToNine2 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                txtNew.femaleFiveToNine2 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                txtNew.maleTenToFourteen2 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                txtNew.femaleTenToFourteen2 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                txtNew.maleFifteenToNineteen2 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                txtNew.femaleFifteenToNineteen2 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                txtNew.maleTwentyToTwentyFour2 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                txtNew.femaleTwentyToTwentyFour2 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                txtNew.maleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                txtNew.femaleTwentyFiveToTwentyNine2 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                txtNew.maleThirtyToThirtyFour2 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                txtNew.femaleThirtyToThirtyFour2 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                txtNew.maleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                txtNew.femaleThirtyFiveToThirtyNine2 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                txtNew.maleFortyToFortyFour2 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                txtNew.femaleFortyToFortyFour2 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                txtNew.maleFortyFiveToFortyNine2 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                txtNew.femaleFortyFiveToFortyNine2 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                txtNew.maleFiftyPlus2 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                txtNew.femaleFiftyPlus2 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        txt_name.setText(R.string.txt_new_question_three);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion3()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion3()));

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

        if (txtNew != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(txtNew.maleLessThanOne3));
            femaleLessThanOne.setText(AppUtil.getLongValue(txtNew.femaleLessThanOne3));
            maleOneToFour.setText(AppUtil.getLongValue(txtNew.maleOneToFour3));
            femaleOneToFour.setText(AppUtil.getLongValue(txtNew.femaleOneToFour3));
            maleFiveToNine.setText(AppUtil.getLongValue(txtNew.maleFiveToNine3));
            femaleFiveToNine.setText(AppUtil.getLongValue(txtNew.femaleFiveToNine3));
            maleTenToFourteen.setText(AppUtil.getLongValue(txtNew.maleTenToFourteen3));
            femaleTenToFourteen.setText(AppUtil.getLongValue(txtNew.femaleTenToFourteen3));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.maleFifteenToNineteen3));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.femaleFifteenToNineteen3));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.maleTwentyToTwentyFour3));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.femaleTwentyToTwentyFour3));
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.maleTwentyFiveToTwentyNine3));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.femaleTwentyFiveToTwentyNine3));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.maleThirtyToThirtyFour3));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.femaleThirtyToThirtyFour3));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.maleThirtyFiveToThirtyNine3));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.femaleThirtyFiveToThirtyNine3));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.maleFortyToFortyFour3));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.femaleFortyToFortyFour3));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.maleFortyFiveToFortyNine3));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.femaleFortyFiveToFortyNine3));
            maleFiftyPlus.setText(AppUtil.getLongValue(txtNew.maleFiftyPlus3));
            femaleFiftyPlus.setText(AppUtil.getLongValue(txtNew.femaleFiftyPlus3));
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

                        txtNew.maleLessThanOne3 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        txtNew.maleOneToFour3 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        txtNew.maleFiveToNine3 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        txtNew.maleTenToFourteen3 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        txtNew.maleFifteenToNineteen3 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        txtNew.maleTwentyToTwentyFour3 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        txtNew.maleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.maleThirtyToThirtyFour3 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        txtNew.maleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.maleFortyToFortyFour3 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        txtNew.maleFortyFiveToFortyNine3 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        txtNew.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion3()));
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

                        txtNew.femaleLessThanOne3 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        txtNew.femaleOneToFour3 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        txtNew.femaleFiveToNine3 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        txtNew.femaleTenToFourteen3 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        txtNew.femaleFifteenToNineteen3 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        txtNew.femaleTwentyToTwentyFour3 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        txtNew.femaleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.femaleThirtyToThirtyFour3 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        txtNew.femaleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.femaleFortyToFortyFour3 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        txtNew.femaleFortyFiveToFortyNine3 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        txtNew.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion3()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                txtNew.maleLessThanOne3 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                txtNew.femaleLessThanOne3 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                txtNew.maleOneToFour3 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                txtNew.femaleOneToFour3 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                txtNew.maleFiveToNine3 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                txtNew.femaleFiveToNine3 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                txtNew.maleTenToFourteen3 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                txtNew.femaleTenToFourteen3 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                txtNew.maleFifteenToNineteen3 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                txtNew.femaleFifteenToNineteen3 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                txtNew.maleTwentyToTwentyFour3 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                txtNew.femaleTwentyToTwentyFour3 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                txtNew.maleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                txtNew.femaleTwentyFiveToTwentyNine3 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                txtNew.maleThirtyToThirtyFour3 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                txtNew.femaleThirtyToThirtyFour3 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                txtNew.maleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                txtNew.femaleThirtyFiveToThirtyNine3 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                txtNew.maleFortyToFortyFour3 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                txtNew.femaleFortyToFortyFour3 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                txtNew.maleFortyFiveToFortyNine3 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                txtNew.femaleFortyFiveToFortyNine3 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                txtNew.maleFiftyPlus3 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                txtNew.femaleFiftyPlus3 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

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
        txt_name.setText(R.string.txt_new_question_four);

        final TextView maleTotal = (TextView) dialog.findViewById(R.id.maleTotal);
        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion4()));

        final TextView femaleTotal = (TextView) dialog.findViewById(R.id.femaleTotal);
        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion4()));

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

        if (txtNew != null) {
            maleLessThanOne.setText(AppUtil.getLongValue(txtNew.maleLessThanOne4));
            femaleLessThanOne.setText(AppUtil.getLongValue(txtNew.femaleLessThanOne4));
            maleOneToFour.setText(AppUtil.getLongValue(txtNew.maleOneToFour4));
            femaleOneToFour.setText(AppUtil.getLongValue(txtNew.femaleOneToFour4));
            maleFiveToNine.setText(AppUtil.getLongValue(txtNew.maleFiveToNine4));
            femaleFiveToNine.setText(AppUtil.getLongValue(txtNew.femaleFiveToNine4));
            maleTenToFourteen.setText(AppUtil.getLongValue(txtNew.maleTenToFourteen4));
            femaleTenToFourteen.setText(AppUtil.getLongValue(txtNew.femaleTenToFourteen4));
            maleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.maleFifteenToNineteen4));
            femaleFifteenToNineteen.setText(AppUtil.getLongValue(txtNew.femaleFifteenToNineteen4));
            maleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.maleTwentyToTwentyFour4));
            femaleTwentyToTwentyFour.setText(AppUtil.getLongValue(txtNew.femaleTwentyToTwentyFour4));
            maleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.maleTwentyFiveToTwentyNine4));
            femaleTwentyFiveToTwentyNine.setText(AppUtil.getLongValue(txtNew.femaleTwentyFiveToTwentyNine4));
            maleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.maleThirtyToThirtyFour4));
            femaleThirtyToThirtyFour.setText(AppUtil.getLongValue(txtNew.femaleThirtyToThirtyFour4));
            maleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.maleThirtyFiveToThirtyNine4));
            femaleThirtyFiveToThirtyNine.setText(AppUtil.getLongValue(txtNew.femaleThirtyFiveToThirtyNine4));
            maleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.maleFortyToFortyFour4));
            femaleFortyToFortyFour.setText(AppUtil.getLongValue(txtNew.femaleFortyToFortyFour4));
            maleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.maleFortyFiveToFortyNine4));
            femaleFortyFiveToFortyNine.setText(AppUtil.getLongValue(txtNew.femaleFortyFiveToFortyNine4));
            maleFiftyPlus.setText(AppUtil.getLongValue(txtNew.maleFiftyPlus4));
            femaleFiftyPlus.setText(AppUtil.getLongValue(txtNew.femaleFiftyPlus4));
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

                        txtNew.maleLessThanOne4 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                        txtNew.maleOneToFour4 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                        txtNew.maleFiveToNine4 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                        txtNew.maleTenToFourteen4 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                        txtNew.maleFifteenToNineteen4 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                        txtNew.maleTwentyToTwentyFour4 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                        txtNew.maleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.maleThirtyToThirtyFour4 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                        txtNew.maleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.maleFortyToFortyFour4 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                        txtNew.maleFortyFiveToFortyNine4 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                        txtNew.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());

                        maleTotal.setText(AppUtil.getLongValue(txtNew.maleQuestion4()));
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

                        txtNew.femaleLessThanOne4 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());
                        txtNew.femaleOneToFour4 = AppUtil.getLongValue(femaleOneToFour.getText().toString());
                        txtNew.femaleFiveToNine4 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());
                        txtNew.femaleTenToFourteen4 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());
                        txtNew.femaleFifteenToNineteen4 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());
                        txtNew.femaleTwentyToTwentyFour4 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());
                        txtNew.femaleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());
                        txtNew.femaleThirtyToThirtyFour4 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());
                        txtNew.femaleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());
                        txtNew.femaleFortyToFortyFour4 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());
                        txtNew.femaleFortyFiveToFortyNine4 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());
                        txtNew.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                        femaleTotal.setText(AppUtil.getLongValue(txtNew.femaleQuestion4()));
                    }

                }
            });
        }

        Button saveButton = (Button) dialog.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                txtNew.maleLessThanOne4 = AppUtil.getLongValue(maleLessThanOne.getText().toString());
                txtNew.femaleLessThanOne4 = AppUtil.getLongValue(femaleLessThanOne.getText().toString());

                txtNew.maleOneToFour4 = AppUtil.getLongValue(maleOneToFour.getText().toString());
                txtNew.femaleOneToFour4 = AppUtil.getLongValue(femaleOneToFour.getText().toString());

                txtNew.maleFiveToNine4 = AppUtil.getLongValue(maleFiveToNine.getText().toString());
                txtNew.femaleFiveToNine4 = AppUtil.getLongValue(femaleFiveToNine.getText().toString());

                txtNew.maleTenToFourteen4 = AppUtil.getLongValue(maleTenToFourteen.getText().toString());
                txtNew.femaleTenToFourteen4 = AppUtil.getLongValue(femaleTenToFourteen.getText().toString());

                txtNew.maleFifteenToNineteen4 = AppUtil.getLongValue(maleFifteenToNineteen.getText().toString());
                txtNew.femaleFifteenToNineteen4 = AppUtil.getLongValue(femaleFifteenToNineteen.getText().toString());

                txtNew.maleTwentyToTwentyFour4 = AppUtil.getLongValue(maleTwentyToTwentyFour.getText().toString());
                txtNew.femaleTwentyToTwentyFour4 = AppUtil.getLongValue(femaleTwentyToTwentyFour.getText().toString());

                txtNew.maleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(maleTwentyFiveToTwentyNine.getText().toString());
                txtNew.femaleTwentyFiveToTwentyNine4 = AppUtil.getLongValue(femaleTwentyFiveToTwentyNine.getText().toString());

                txtNew.maleThirtyToThirtyFour4 = AppUtil.getLongValue(maleThirtyToThirtyFour.getText().toString());
                txtNew.femaleThirtyToThirtyFour4 = AppUtil.getLongValue(femaleThirtyToThirtyFour.getText().toString());

                txtNew.maleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(maleThirtyFiveToThirtyNine.getText().toString());
                txtNew.femaleThirtyFiveToThirtyNine4 = AppUtil.getLongValue(femaleThirtyFiveToThirtyNine.getText().toString());

                txtNew.maleFortyToFortyFour4 = AppUtil.getLongValue(maleFortyToFortyFour.getText().toString());
                txtNew.femaleFortyToFortyFour4 = AppUtil.getLongValue(femaleFortyToFortyFour.getText().toString());

                txtNew.maleFortyFiveToFortyNine4 = AppUtil.getLongValue(maleFortyFiveToFortyNine.getText().toString());
                txtNew.femaleFortyFiveToFortyNine4 = AppUtil.getLongValue(femaleFortyFiveToFortyNine.getText().toString());

                txtNew.maleFiftyPlus4 = AppUtil.getLongValue(maleFiftyPlus.getText().toString());
                txtNew.femaleFiftyPlus4 = AppUtil.getLongValue(femaleFiftyPlus.getText().toString());

                upDateForm();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }


    public void upDateForm() {

        btn_question_one.setText(this.getString(R.string.txt_new_question_one)
                + " [ " + (txtNew.maleQuestion1() + txtNew.femaleQuestion1()) + " ]");

        btn_question_two.setText(this.getString(R.string.txt_new_question_two)
                + " [ " + (txtNew.maleQuestion2() + txtNew.femaleQuestion2()) + " ]");

        btn_question_three.setText(this.getString(R.string.txt_new_question_three)
                + " [ " + (txtNew.maleQuestion3() + txtNew.femaleQuestion3()) + " ]");

        btn_question_four.setText(this.getString(R.string.txt_new_question_four)
                + " [ " + (txtNew.maleQuestion4() + txtNew.femaleQuestion4()) + " ]");

    }

}

