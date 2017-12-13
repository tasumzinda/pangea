package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.HTSEligibilityScreeningForm;
import zw.co.ncmp.business.util.*;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class HTSEligibilityScreeningFormActivity extends MenuBar implements View.OnClickListener {

    @BindView(R.id.name)
    EditText clientName;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htseligibility_screening_form);
        ButterKnife.bind(this);
        setSupportActionBar(createToolBar("HTS Eligibility Screening Form"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gender = (HListView) findViewById(R.id.gender);
        eligibleForHIVTest = (HListView) findViewById(R.id.eligibleForHIVTest);
        willingToBeTestedToday = (HListView) findViewById(R.id.willingToBeTestedToday);
        reasonForUnwillingnessToBeTested = (ListView) findViewById(R.id.reasonForUnwillingnessToBeTested);
        reasonForIneligibilityForTesting = (ListView) findViewById(R.id.reasonForIneligibilityForTesting);
        clientServices = (ListView) findViewById(R.id.clientServices);
        facilityArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, Facility.getAll());
        facilityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facility.setAdapter(facilityArrayAdapter);
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
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == date.getId()){
            datePickerDialog.show();
        }

        if(view.getId() == save.getId()){
            save();
        }
    }

    private void updateLabel(EditText editText, Date date) {
        editText.setText(AppUtil.getStringDate(date));
    }

    public void save(){
        HTSEligibilityScreeningForm item = new HTSEligibilityScreeningForm();
        item.clientName = clientName.getText().toString();
        item.cardNumber = Integer.parseInt(cardNumber.getText().toString());
        item.date = DateUtil.getDateFromString(date.getText().toString());
        item.facility = (Facility) facility.getSelectedItem();
        item.time = time.getCurrentHour() + ":" + time.getCurrentMinute();
        item.gender = getGender();
        item.age = Integer.parseInt(age.getText().toString());
        item.eligibleForHIVTest = getEligibleForTesting();
        item.willingToBeTestedToday = getWillingToBeTested();
        if(getReasonForIneligibilityForTesting().getName().equals("Other")){
            item.reasonForIneligibilityForTesting = other1.getText().toString();
        }else{
            item.reasonForIneligibilityForTesting = getReasonForIneligibilityForTesting().getName();
        }

        if(getReasonForUnwillingnessToBeTested().getName().equals("Other")){
            item.reasonForUnwillingnessToBeTested = other.getText().toString();
        }else{
            item.reasonForUnwillingnessToBeTested = getReasonForUnwillingnessToBeTested().getName();
        }

        if(getClientServices().getName().equals("Other")){
            item.servicesBeingSought = other2.getText().toString();
        }else{
            item.servicesBeingSought = getClientServices().getName();
        }
        item.save();
        for(HTSEligibilityScreeningForm m : HTSEligibilityScreeningForm.getAll()){
            Log.d("HTS", AppUtil.createGson().toJson(m));
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
}
