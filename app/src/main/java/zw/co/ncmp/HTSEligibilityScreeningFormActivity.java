package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import zw.co.ncmp.business.District;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.HTSEligibilityScreeningForm;
import zw.co.ncmp.business.Province;
import zw.co.ncmp.business.util.*;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class HTSEligibilityScreeningFormActivity extends MenuBar implements View.OnClickListener {

    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.surname)
    EditText surname;
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
    HListView eligibleForHIVTest;
    ArrayAdapter<YesNo> yesNoArrayAdapter;
    HListView willingToBeTestedToday;
    ListView reasonForUnwillingnessToBeTested;
    ArrayAdapter<ReasonForUnwillingnessToBeTested> reasonForUnwillingnessToBeTestedArrayAdapter;
    ListView reasonForIneligibilityForTesting;
    ArrayAdapter<ReasonForIneligibilityForTesting> reasonForIneligibilityForTestingArrayAdapter;
    ListView clientServices;
    ArrayAdapter<ClientServices> clientServicesArrayAdapter;
    @BindView(R.id.other)
    EditText other;
    @BindView(R.id.other1)
    EditText other1;
    @BindView(R.id.other2)
    EditText other2;
    @BindView(R.id.btn_completed)
    Button btn_completed;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.unwillingLabel)
    TextView unwillingLabel;
    @BindView(R.id.ineligibleLabel)
    TextView ineligibleLabel;
    @BindView(R.id.willingLayout)
    LinearLayout willingLayout;
    HTSEligibilityScreeningForm item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htseligibility_screening_form);
        ButterKnife.bind(this);
        setSupportActionBar(createToolBar("HTS Eligibility Screening Form"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0L);
        gender = (HListView) findViewById(R.id.gender);
        eligibleForHIVTest = (HListView) findViewById(R.id.eligibleForHIVTest);
        willingToBeTestedToday = (HListView) findViewById(R.id.willingToBeTestedToday);
        reasonForUnwillingnessToBeTested = (ListView) findViewById(R.id.reasonForUnwillingnessToBeTested);
        reasonForIneligibilityForTesting = (ListView) findViewById(R.id.reasonForIneligibilityForTesting);
        clientServices = (ListView) findViewById(R.id.clientServices);
        genderArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, Gender.values());
        gender.setAdapter(genderArrayAdapter);
        gender.setItemsCanFocus(false);
        gender.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        yesNoArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, YesNo.values());
        eligibleForHIVTest.setAdapter(yesNoArrayAdapter);
        eligibleForHIVTest.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        eligibleForHIVTest.setItemsCanFocus(false);
        willingToBeTestedToday.setAdapter(yesNoArrayAdapter);
        willingToBeTestedToday.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        willingToBeTestedToday.setItemsCanFocus(false);
        reasonForUnwillingnessToBeTestedArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, ReasonForUnwillingnessToBeTested.values());
        reasonForUnwillingnessToBeTested.setAdapter(reasonForUnwillingnessToBeTestedArrayAdapter);
        reasonForUnwillingnessToBeTested.setItemsCanFocus(false);
        reasonForUnwillingnessToBeTested.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        reasonForIneligibilityForTestingArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, ReasonForIneligibilityForTesting.values());
        reasonForIneligibilityForTesting.setAdapter(reasonForIneligibilityForTestingArrayAdapter);
        reasonForIneligibilityForTesting.setItemsCanFocus(false);
        reasonForIneligibilityForTesting.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        clientServicesArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, ClientServices.values());
        clientServices.setAdapter(clientServicesArrayAdapter);
        clientServices.setItemsCanFocus(false);
        clientServices.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(date, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        save.setOnClickListener(this);
        date.setOnClickListener(this);
        willingToBeTestedToday.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                YesNo item = yesNoArrayAdapter.getItem(position);
                if(item.equals(YesNo.NO)){
                    reasonForUnwillingnessToBeTested.setVisibility(View.VISIBLE);
                    unwillingLabel.setVisibility(View.VISIBLE);
                }else{
                    reasonForUnwillingnessToBeTested.setVisibility(View.GONE);
                    unwillingLabel.setVisibility(View.GONE);
                }
            }
        });

        eligibleForHIVTest.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
                YesNo item = yesNoArrayAdapter.getItem(position);
                if(item.equals(YesNo.NO)){
                    reasonForIneligibilityForTesting.setVisibility(View.VISIBLE);
                    ineligibleLabel.setVisibility(View.VISIBLE);
                    willingLayout.setVisibility(View.GONE);

                }else{
                    reasonForIneligibilityForTesting.setVisibility(View.GONE);
                    ineligibleLabel.setVisibility(View.GONE);
                    willingLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        reasonForUnwillingnessToBeTested.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReasonForUnwillingnessToBeTested item = reasonForUnwillingnessToBeTestedArrayAdapter.getItem(i);
                if(item.getName().equals("Other")){
                    other.setVisibility(View.VISIBLE);
                }else{
                    other.setVisibility(View.GONE);
                }
            }
        });

        reasonForIneligibilityForTesting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReasonForIneligibilityForTesting item = reasonForIneligibilityForTestingArrayAdapter.getItem(i);
                if(item.getName().equals("Other")){
                    other1.setVisibility(View.VISIBLE);
                }else{
                    other1.setVisibility(View.GONE);
                }
            }
        });

        clientServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClientServices item = clientServicesArrayAdapter.getItem(i);
                if(item.getName().equals("Other")){
                    other2.setVisibility(View.VISIBLE);
                }else{
                    other2.setVisibility(View.GONE);
                }
            }
        });

        facilityArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, Facility.getAll());
        facilityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facility.setAdapter(facilityArrayAdapter);
        btn_completed.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);
        btn_submit.setOnClickListener(this);
        if(id != 0){
            item = HTSEligibilityScreeningForm.findById(id);
            firstName.setText(item.firstName);
            surname.setText(item.lastName);
            cardNumber.setText(String.valueOf(item.cardNumber));
            updateLabel(date, item.date);
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
            age.setText(String.valueOf(item.age));
            YesNo n =  item.eligibleForHIVTest;
            count = yesNoArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                YesNo current = yesNoArrayAdapter.getItem(i);
                if(current.equals(n)){
                    eligibleForHIVTest.setItemChecked(i, true);
                }
            }

            n =  item.willingToBeTestedToday;
            count = yesNoArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                YesNo current = yesNoArrayAdapter.getItem(i);
                if(current.equals(n)){
                    willingToBeTestedToday.setItemChecked(i, true);
                }
            }

            String result = item.reasonForUnwillingnessToBeTested;
            count = reasonForUnwillingnessToBeTestedArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                ReasonForUnwillingnessToBeTested current = reasonForUnwillingnessToBeTestedArrayAdapter.getItem(i);
                if(current.getName().equals(result)){
                    reasonForUnwillingnessToBeTested.setItemChecked(i, true);
                }
            }
            if(getReasonForUnwillingnessToBeTested() == null){
                other.setVisibility(View.VISIBLE);
                other.setText(result);
                int k = reasonForUnwillingnessToBeTestedArrayAdapter.getPosition(ReasonForUnwillingnessToBeTested.OTHER);
                reasonForUnwillingnessToBeTested.setItemChecked(k, true);
            }

            result = item.reasonForIneligibilityForTesting;
            count = reasonForIneligibilityForTestingArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                ReasonForIneligibilityForTesting current = reasonForIneligibilityForTestingArrayAdapter.getItem(i);
                if(current.getName().equals(result)){
                    reasonForIneligibilityForTesting.setItemChecked(i, true);
                }
            }
            if(getReasonForIneligibilityForTesting() == null){
                int k = reasonForIneligibilityForTestingArrayAdapter.getPosition(ReasonForIneligibilityForTesting.OTHER);
                other1.setVisibility(View.VISIBLE);
                other1.setText(result);
                reasonForIneligibilityForTesting.setItemChecked(k, true);
            }

            result = item.servicesBeingSought;
            count = clientServicesArrayAdapter.getCount();
            for(i = 0; i < count; i++){
                ClientServices current = clientServicesArrayAdapter.getItem(i);
                if(current.getName().equals(result)){
                    reasonForIneligibilityForTesting.setItemChecked(i, true);
                }
            }
            if(getClientServices() == null){
                int k = clientServicesArrayAdapter.getPosition(ClientServices.OTHER);
                other2.setVisibility(View.VISIBLE);
                other2.setText(result);
                reasonForIneligibilityForTesting.setItemChecked(k, true);
            }
        }else{
            item = new HTSEligibilityScreeningForm();
        }
        if (item.date != null) {
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
                            item.dateSubmitted = new Date();
                            item.save();
                            AppUtil.createLongNotification(HTSEligibilityScreeningFormActivity.this, "Submitted for Upload to Server");
                            Intent intent = new Intent(HTSEligibilityScreeningFormActivity.this, HTSEligibilityScreeningFormListActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    private void updateLabel(EditText editText, Date date) {
        editText.setText(AppUtil.getStringDate(date));
    }

    public void save(){
        if(validate()){
            item.firstName = firstName.getText().toString();
            item.lastName = surname.getText().toString();
            item.cardNumber = Integer.parseInt(cardNumber.getText().toString());
            item.date = DateUtil.getDateFromString(date.getText().toString());
            item.facility = (Facility) facility.getSelectedItem();
            item.mTime = time.getCurrentHour() + ":" + time.getCurrentMinute();
            item.gender = getGender();
            item.age = Integer.parseInt(age.getText().toString());
            item.eligibleForHIVTest = getEligibleForTesting();
            item.willingToBeTestedToday = getWillingToBeTested();
            if(getReasonForIneligibilityForTesting() != null){
                if(getReasonForIneligibilityForTesting().getName().equals("Other")){
                    item.reasonForIneligibilityForTesting = other1.getText().toString();
                }else{
                    item.reasonForIneligibilityForTesting = getReasonForIneligibilityForTesting().getName();
                }
            }

            if(getReasonForUnwillingnessToBeTested() != null){
                if(getReasonForUnwillingnessToBeTested().getName().equals("Other")){
                    item.reasonForUnwillingnessToBeTested = other.getText().toString();
                }else{
                    item.reasonForUnwillingnessToBeTested = getReasonForUnwillingnessToBeTested().getName();
                }
            }

            if(getClientServices() != null){
                if(getClientServices().getName().equals("Other")){
                    item.servicesBeingSought = other2.getText().toString();
                }else{
                    item.servicesBeingSought = getClientServices().getName();
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

    public YesNo getEligibleForTesting(){
        YesNo item = null;
        for(int i = 0; i < eligibleForHIVTest.getCount(); i++){
            if(eligibleForHIVTest.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public YesNo getWillingToBeTested(){
        YesNo item = null;
        for(int i = 0; i < willingToBeTestedToday.getCount(); i++){
            if(willingToBeTestedToday.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public ReasonForIneligibilityForTesting getReasonForIneligibilityForTesting(){
        ReasonForIneligibilityForTesting item = null;
        for(int i = 0; i < reasonForIneligibilityForTesting.getCount(); i++){
            if(reasonForIneligibilityForTesting.isItemChecked(i)){
                item = reasonForIneligibilityForTestingArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public ReasonForUnwillingnessToBeTested getReasonForUnwillingnessToBeTested(){
        ReasonForUnwillingnessToBeTested item = null;
        for(int i = 0; i < reasonForUnwillingnessToBeTested.getCount(); i++){
            if(reasonForUnwillingnessToBeTested.isItemChecked(i)){
                item = reasonForUnwillingnessToBeTestedArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public ClientServices getClientServices(){
        ClientServices item = null;
        for(int i = 0; i < clientServices.getCount(); i++){
            if(clientServices.isItemChecked(i)){
                item = clientServicesArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public boolean validate(){
        boolean isValid = true;
        if(firstName.getText().toString().isEmpty()){
            firstName.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            firstName.setError(null);
        }

        if(surname.getText().toString().isEmpty()){
            surname.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            surname.setError(null);
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
        if(facility.getSelectedItem() == null){
            AppUtil.createShortNotification(this, "Please select facility");
            isValid = false;
        }

        if(getGender() == null){
            AppUtil.createShortNotification(this, "Please select gender");
            isValid = false;
        }

        if(getEligibleForTesting() == null){
            AppUtil.createShortNotification(this, "Please select eligible for HIV test");
            isValid = false;
        }

        if(getWillingToBeTested() == null){
            AppUtil.createShortNotification(this, "Please select willing to be tested");
            isValid = false;
        }

        if(getWillingToBeTested() != null && getWillingToBeTested().equals(YesNo.NO)){
            if(getReasonForUnwillingnessToBeTested() == null){
                AppUtil.createShortNotification(this, "Please select reason for unwillingness to be tested");
                isValid = false;
            }

        }

        if(getEligibleForTesting() != null && getEligibleForTesting().equals(YesNo.NO)){
            if(getReasonForIneligibilityForTesting() == null){
                AppUtil.createShortNotification(this, "Please select reason for ineligibility for testing");
                isValid = false;
            }

        }

        if(getReasonForUnwillingnessToBeTested() != null && getReasonForUnwillingnessToBeTested().equals(ReasonForUnwillingnessToBeTested.OTHER)){
            if(other.getText().toString().isEmpty()){
                other.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                other.setError(null);
            }
        }

        if(getReasonForIneligibilityForTesting() != null && getReasonForIneligibilityForTesting().equals(ReasonForIneligibilityForTesting.OTHER)){
            if(other1.getText().toString().isEmpty()){
                other1.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                other1.setError(null);
            }
        }

        if(getClientServices() == null){
            AppUtil.createShortNotification(this, "Please select client services");
            isValid = false;
        }

        if(getClientServices() != null && getClientServices().equals(ClientServices.OTHER)){
            if(other2.getText().toString().isEmpty()){
                other2.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                other2.setError(null);
            }
        }
        return isValid;
    }
}
