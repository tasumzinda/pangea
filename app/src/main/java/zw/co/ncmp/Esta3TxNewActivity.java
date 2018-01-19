package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import zw.co.ncmp.business.Esta3TxNew;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.HTSRegisterForm;
import zw.co.ncmp.business.PreARTForm;
import zw.co.ncmp.business.util.*;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class Esta3TxNewActivity extends MenuBar implements View.OnClickListener {

    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.btn_save)
    Button save;
    @BindView(R.id.cardNumber)
    EditText cardNumber;
    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.time)
    TimePicker time;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.facility)
    Spinner facility;
    HListView gender;
    ArrayAdapter<Facility> facilityArrayAdapter;
    ArrayAdapter<Gender> genderArrayAdapter;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ListView reasonForHIVTesting;
    ArrayAdapter<ReasonForHIVTest> reasonForHIVTestArrayAdapter;
    ArrayAdapter<YesNo> yesNoArrayAdapter;
    HListView inPreArt;
    ListView finalResult;
    ArrayAdapter<ReasonForIneligibilityForTesting> reasonForIneligibilityForTestingArrayAdapter;
    ListView entryStream;
    ArrayAdapter<ClientServices> clientServicesArrayAdapter;
    @BindView(R.id.other)
    EditText other;
    @BindView(R.id.other1)
    EditText other1;
    @BindView(R.id.date_registered_in_pre_art)
    EditText dateRegisteredInPreArt;
    DatePickerDialog datePickerDialog1;
    HListView initiatedOnArt;
    @BindView(R.id.date_of_initiation)
    EditText dateOfInitiation;
    DatePickerDialog datePickerDialog2;
    @BindView(R.id.oi_art_number)
    EditText oiArtNumber;
    @BindView(R.id.btn_completed)
    Button btn_completed;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    private RadioGroup test;
    private RadioButton selected;
    Esta3TxNew item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esta3);
        ButterKnife.bind(this);
        setSupportActionBar(createToolBar("ESTA3 Tx-New"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0L);
        gender = (HListView) findViewById(R.id.gender);
        reasonForHIVTesting = (ListView) findViewById(R.id.reasonForHIVTest);
        inPreArt = (HListView) findViewById(R.id.in_pre_art);
        initiatedOnArt = (HListView) findViewById(R.id.initiated_on_art);
        finalResult = (ListView) findViewById(R.id.final_result);
        entryStream = (ListView) findViewById(R.id.entry_stream);
        test = (RadioGroup) findViewById(R.id.test);
        facilityArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, Facility.getAll());
        facilityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facility.setAdapter(facilityArrayAdapter);
        genderArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, Gender.values());
        gender.setAdapter(genderArrayAdapter);
        gender.setItemsCanFocus(false);
        gender.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        reasonForHIVTestArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, ReasonForHIVTest.values());
        reasonForHIVTesting.setAdapter(reasonForHIVTestArrayAdapter);
        reasonForHIVTesting.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        reasonForHIVTesting.setItemsCanFocus(false);
        yesNoArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, YesNo.values());
        inPreArt.setAdapter(yesNoArrayAdapter);
        inPreArt.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        inPreArt.setItemsCanFocus(false);
        initiatedOnArt.setAdapter(yesNoArrayAdapter);
        initiatedOnArt.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        initiatedOnArt.setItemsCanFocus(false);
        reasonForIneligibilityForTestingArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, ReasonForIneligibilityForTesting.values());
        finalResult.setAdapter(reasonForIneligibilityForTestingArrayAdapter);
        finalResult.setItemsCanFocus(false);
        finalResult.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        clientServicesArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, ClientServices.values());
        entryStream.setAdapter(clientServicesArrayAdapter);
        entryStream.setItemsCanFocus(false);
        entryStream.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(date, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateRegisteredInPreArt, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateOfInitiation, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        save.setOnClickListener(this);
        date.setOnClickListener(this);
        dateRegisteredInPreArt.setOnClickListener(this);
        finalResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReasonForIneligibilityForTesting item = reasonForIneligibilityForTestingArrayAdapter.getItem(i);
                if(item.getName().equals("Other")){
                    other.setVisibility(View.VISIBLE);
                }else{
                    other.setVisibility(View.GONE);
                }
            }
        });

        entryStream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClientServices item = clientServicesArrayAdapter.getItem(i);
                if(item.getName().equals("Other")){
                    other1.setVisibility(View.VISIBLE);
                }else{
                    other1.setVisibility(View.GONE);
                }
            }
        });

        inPreArt.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                YesNo item = yesNoArrayAdapter.getItem(position);
                if(item.equals(YesNo.YES)){
                    dateRegisteredInPreArt.setVisibility(View.VISIBLE);
                }else{
                    dateRegisteredInPreArt.setVisibility(View.GONE);
                }
            }
        });

        initiatedOnArt.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                YesNo item = yesNoArrayAdapter.getItem(position);
                if(item.equals(YesNo.YES)){
                    dateOfInitiation.setVisibility(View.VISIBLE);
                }else{
                    dateOfInitiation.setVisibility(View.GONE);
                }
            }
        });
        dateOfInitiation.setOnClickListener(this);
        btn_completed.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);
        btn_submit.setOnClickListener(this);
        if(id != 0){
            item = Esta3TxNew.findById(id);
            firstName.setText(item.firstName);
            lastName.setText(item.lastName);
            cardNumber.setText(String.valueOf(item.cardNumber));
            age.setText(String.valueOf(item.age));
            updateLabel(date, item.mDate);
            int i = 0;
            for (Facility s : Facility.getAll()) {
                if (item.facility.equals(facility.getItemAtPosition(i))) {
                    facility.setSelection(i);
                    break;
                }
                i++;
            }
            Gender m =  item.gender;
            int count = genderArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                Gender current = genderArrayAdapter.getItem(i);
                if(current.equals(m)){
                    gender.setItemChecked(i, true);
                }
            }
            ReasonForHIVTest r = item.reasonForHIVTest;
            count = reasonForHIVTestArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                ReasonForHIVTest current = reasonForHIVTestArrayAdapter.getItem(i);
                if(current.equals(r)){
                    reasonForHIVTesting.setItemChecked(i, true);
                }
            }
            String result = item.finalResult;
            count = reasonForIneligibilityForTestingArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                ReasonForIneligibilityForTesting current = reasonForIneligibilityForTestingArrayAdapter.getItem(i);
                if(current.getName().equals(result)){
                    finalResult.setItemChecked(i, true);
                }
            }
            if(getFinalResult() == null){
                other.setVisibility(View.VISIBLE);
                other.setText(result);
                int k = reasonForIneligibilityForTestingArrayAdapter.getPosition(ReasonForIneligibilityForTesting.OTHER);
                finalResult.setItemChecked(k, true);
            }
            result = item.entryStream;
            count = clientServicesArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                ClientServices current = clientServicesArrayAdapter.getItem(i);
                if(current.getName().equals(result)){
                    entryStream.setItemChecked(i, true);
                }
            }
            if(getEntryStream() == null){
                int k = clientServicesArrayAdapter.getPosition(ClientServices.OTHER);
                other1.setVisibility(View.VISIBLE);
                other1.setText(result);
                entryStream.setItemChecked(k, true);
            }
            YesNo preArt =  item.inPreArt;
            count = yesNoArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                YesNo current = yesNoArrayAdapter.getItem(i);
                if(current.equals(preArt)){
                    inPreArt.setItemChecked(i, true);
                }
            }
            if(item.registeredInPreArt != null){
                updateLabel(dateRegisteredInPreArt, item.registeredInPreArt);
            }

            YesNo init =  item.initiatedOnArt;
            count = yesNoArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                YesNo current = yesNoArrayAdapter.getItem(i);
                if(current.equals(init)){
                    initiatedOnArt.setItemChecked(i, true);
                }
            }
            if(item.initiatedOnArt != null){
                updateLabel(dateOfInitiation, item.dateOfInitiation);
            }
            oiArtNumber.setText(String.valueOf(item.oiArtNumber));
        }else{
            item = new Esta3TxNew();
        }

        if (item.mDate != null) {
            btn_submit.setVisibility(View.VISIBLE);
        }

        if (item.dateSubmitted != null) {
            btn_submit.setVisibility(View.GONE);
            save.setVisibility(View.GONE);
            btn_completed.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == date.getId()){
            datePickerDialog.show();
        }

        if(view.getId() == save.getId()){
            save();
        }

        if(view.getId() == btn_submit.getId()){

            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to submit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (validate()) {
                                item.dateSubmitted = new Date();
                                item.save();
                                AppUtil.createLongNotification(Esta3TxNewActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(Esta3TxNewActivity.this, Esta3TxNewListActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        if(view.getId() == dateRegisteredInPreArt.getId()){
            datePickerDialog1.show();
        }

        if(view.getId() == dateOfInitiation.getId()){
            datePickerDialog2.show();
        }
    }

    private void updateLabel(EditText editText, Date date) {
        editText.setText(AppUtil.getStringDate(date));
    }

    public void save(){
        if(validate()){
            item.firstName = firstName.getText().toString();
            item.lastName = lastName.getText().toString();
            item.cardNumber = Integer.parseInt(cardNumber.getText().toString());
            item.mDate = DateUtil.getDateFromString(date.getText().toString());
            item.facility = (Facility) facility.getSelectedItem();
            item.mTime = time.getCurrentHour() + ":" + time.getCurrentMinute();
            item.gender = getGender();
            item.age = Integer.parseInt(age.getText().toString());
            item.reasonForHIVTest = getReasonForHIVTest();
            item.inPreArt = getInPreArt();
            if(getFinalResult().getName().equals("Other")){
                item.finalResult = other.getText().toString();
            }else{
                item.finalResult = getFinalResult().getName();
            }

            if(getEntryStream().getName().equals("Other")){
                item.entryStream = other1.getText().toString();
            }else{
                item.entryStream = getEntryStream().getName();
            }
            String date = dateRegisteredInPreArt.getText().toString();
            if( ! date.isEmpty()){
                item.registeredInPreArt = DateUtil.getDateFromString(date);
            }
            item.initiatedOnArt = getInitiatedOnArt();
            date = dateOfInitiation.getText().toString();
            if( ! date.isEmpty()){
                item.dateOfInitiation = DateUtil.getDateFromString(date);
            }
            item.oiArtNumber = Integer.parseInt(oiArtNumber.getText().toString());
            int selectedItem = test.getCheckedRadioButtonId();
            selected = (RadioButton) findViewById(selectedItem);
            if(selected != null){
                if(selected.getText().equals("F")){
                    item.test = "First Test";
                }else{
                    item.test = "Retest";
                }
            }
            item.save();
            btn_submit.setVisibility(View.VISIBLE);
            AppUtil.createShortNotification(this, "Saved");
        }
    }

    public Gender getGender(){
        Gender item = null;
        for(int i = 0; i < gender.getCount(); i++){
            if(gender.isItemChecked(i)){
                item = genderArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public YesNo getInitiatedOnArt(){
        YesNo item = null;
        for(int i = 0; i < initiatedOnArt.getCount(); i++){
            if(initiatedOnArt.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public YesNo getInPreArt(){
        YesNo item = null;
        for(int i = 0; i < inPreArt.getCount(); i++){
            if(inPreArt.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public ReasonForIneligibilityForTesting getFinalResult(){
        ReasonForIneligibilityForTesting item = null;
        for(int i = 0; i < finalResult.getCount(); i++){
            if(finalResult.isItemChecked(i)){
                item = reasonForIneligibilityForTestingArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public ReasonForHIVTest getReasonForHIVTest(){
        ReasonForHIVTest item = null;
        for(int i = 0; i < reasonForHIVTesting.getCount(); i++){
            if(reasonForHIVTesting.isItemChecked(i)){
                item = reasonForHIVTestArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public ClientServices getEntryStream(){
        ClientServices item = null;
        for(int i = 0; i < entryStream.getCount(); i++){
            if(entryStream.isItemChecked(i)){
                item = clientServicesArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public boolean validate(){
        boolean isValid = true;
        if(firstName.getText().toString().isEmpty()){
            firstName.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            firstName.setError(null);
        }

        if(lastName.getText().toString().isEmpty()){
            lastName.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            lastName.setError(null);
        }

        if(cardNumber.getText().toString().isEmpty()){
            cardNumber.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            cardNumber.setError(null);
        }

        if(date.getText().toString().isEmpty()){
            date.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            date.setError(null);
        }

        if(age.getText().toString().isEmpty()){
            age.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            age.setError(null);
        }

        if(getGender() == null){
            AppUtil.createShortNotification(this, "Please select gender");
            isValid = false;
        }

        if(getReasonForHIVTest() == null){
            AppUtil.createShortNotification(this, "Please select reason for hiv testing");
            isValid = false;
        }

        if(getFinalResult() == null){
            AppUtil.createShortNotification(this, "Please select final result");
            isValid = false;
        }

        if(getFinalResult() != null && getFinalResult().equals(ReasonForIneligibilityForTesting.OTHER)){
            if(other.getText().toString().isEmpty()){
                other.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                other.setError(null);
            }
        }

        if(getEntryStream() == null){
            AppUtil.createShortNotification(this, "Please select entry stream");
            isValid = false;
        }

        if(getEntryStream() != null && getEntryStream().equals(ClientServices.OTHER)){
            if(other1.getText().toString().isEmpty()){
                other1.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                other1.setError(null);
            }
        }

        if(getInPreArt() == null){
            AppUtil.createShortNotification(this, "Please select In Pre-ART");
            isValid = false;
        }

        if(getInPreArt() != null && getInPreArt().equals(YesNo.YES)){
            if(dateRegisteredInPreArt.getText().toString().isEmpty()){
                dateRegisteredInPreArt.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                dateRegisteredInPreArt.setError(null);
            }
        }

        if(getInitiatedOnArt() == null){
            AppUtil.createShortNotification(this, "Please select Initiated On ART");
            isValid = false;
        }

        if(getInitiatedOnArt() != null && getInitiatedOnArt().equals(YesNo.YES)){
            if(dateOfInitiation.getText().toString().isEmpty()){
                dateOfInitiation.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                dateOfInitiation.setError(null);
            }
        }

        if(oiArtNumber.getText().toString().isEmpty()){
            oiArtNumber.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            oiArtNumber.setError(null);
        }
        return isValid;
    }
}
