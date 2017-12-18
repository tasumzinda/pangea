package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.AdapterView;
import it.sephiroth.android.library.widget.HListView;
import zw.co.ncmp.business.IndexCaseTestingForm;
import zw.co.ncmp.business.util.Gender;
import zw.co.ncmp.business.util.HIVResult;
import zw.co.ncmp.business.util.Relationship;
import zw.co.ncmp.business.util.YesNo;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class IndexCaseTestingFormActivity extends MenuBar implements View.OnClickListener {

    @BindView(R.id.first_name)
    EditText firstNameOfIndex;
    @BindView(R.id.last_name)
    EditText lastNameOfIndex;
    @BindView(R.id.index_oi_art_number)
    EditText indexOIARTNumber;
    ArrayAdapter<YesNo> yesNoArrayAdapter;
    HListView initiatedOnART;
    @BindView(R.id.reasonForNotBeingInitiated)
    EditText reasonForNotBeingInitiated;
    HListView consentForListedContact;
    @BindView(R.id.preferredPlace)
    EditText preferredPlaceForContactsToBeTested;
    @BindView(R.id.appointmentDateForContact)
    EditText appointmentDateForContact;
    DatePickerDialog datePickerDialog;
    @BindView(R.id.nameOfContact)
    EditText nameOfContact;
    ListView relationshipToIndex;
    ArrayAdapter<Relationship> relationshipArrayAdapter;
    @BindView(R.id.other)
    EditText other;
    @BindView(R.id.age)
    EditText age;
    HListView gender;
    ArrayAdapter<Gender> genderArrayAdapter;
    @BindView(R.id.contactAddress)
    EditText contactAddress;
    @BindView(R.id.indexContactNumber)
    EditText indexContactNumber;
    @BindView(R.id.dateCalled)
    EditText dateCalled;
    DatePickerDialog datePickerDialog1;
    @BindView(R.id.callOutcome)
    EditText callOutcome;
    @BindView(R.id.dateVisited)
    EditText dateVisited;
    DatePickerDialog datePickerDialog2;
    @BindView(R.id.visitOutcome)
    EditText visitOutcome;
    @BindView(R.id.dateContactTested)
    EditText dateContactTested;
    DatePickerDialog datePickerDialog3;
    @BindView(R.id.locationOfTest)
    EditText locationOfTest;
    HListView hivResult;
    ArrayAdapter<HIVResult> hivResultArrayAdapter;
    HListView enrolledIntoCare;
    @BindView(R.id.btn_save)
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_case_testing_form);
        ButterKnife.bind(this);
        initiatedOnART = (HListView) findViewById(R.id.initiated_on_art);
        consentForListedContact = (HListView) findViewById(R.id.consentForListedContacts);
        relationshipToIndex = (ListView) findViewById(R.id.relationshipToIndex);
        gender = (HListView) findViewById(R.id.gender);
        hivResult = (HListView) findViewById(R.id.hivResult);
        enrolledIntoCare = (HListView) findViewById(R.id.enrolledIntoCare);
        yesNoArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, YesNo.values());
        initiatedOnART.setAdapter(yesNoArrayAdapter);
        initiatedOnART.setItemsCanFocus(false);
        initiatedOnART.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        consentForListedContact.setAdapter(yesNoArrayAdapter);
        consentForListedContact.setItemsCanFocus(false);
        consentForListedContact.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        enrolledIntoCare.setAdapter(yesNoArrayAdapter);
        enrolledIntoCare.setItemsCanFocus(false);
        enrolledIntoCare.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        relationshipArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, Relationship.values());
        relationshipToIndex.setAdapter(relationshipArrayAdapter);
        relationshipToIndex.setItemsCanFocus(false);
        relationshipToIndex.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        genderArrayAdapter = new ArrayAdapter<>(this,R.layout.check_box_item, Gender.values());
        gender.setAdapter(genderArrayAdapter);
        gender.setItemsCanFocus(false);
        gender.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        hivResultArrayAdapter = new ArrayAdapter<>(this, R.layout.check_box_item, HIVResult.values());
        hivResult.setAdapter(hivResultArrayAdapter);
        hivResult.setItemsCanFocus(false);
        hivResult.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(appointmentDateForContact, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateCalled, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateVisited, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog3 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateContactTested, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        appointmentDateForContact.setOnClickListener(this);
        dateCalled.setOnClickListener(this);
        dateVisited.setOnClickListener(this);
        dateContactTested.setOnClickListener(this);
        save.setOnClickListener(this);
        initiatedOnART.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                YesNo item = yesNoArrayAdapter.getItem(position);
                if(item.equals(YesNo.NO)){
                    reasonForNotBeingInitiated.setVisibility(View.VISIBLE);
                }else{
                    reasonForNotBeingInitiated.setVisibility(View.GONE);
                }
            }
        });
        relationshipToIndex.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> adapterView, View view, int i, long l) {
                Relationship item = relationshipArrayAdapter.getItem(i);
                if(item.equals(Relationship.OTHER)){
                    other.setVisibility(View.VISIBLE);
                }else{
                    other.setVisibility(View.GONE);
                }
            }
        });
        setSupportActionBar(createToolBar("Index Case Testing"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateLabel(EditText editText, Date date) {
        editText.setText(AppUtil.getStringDate(date));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == appointmentDateForContact.getId()){
            datePickerDialog.show();
        }

        if(view.getId() == dateCalled.getId()){
            datePickerDialog1.show();
        }

        if(view.getId() == dateVisited.getId()){
            datePickerDialog2.show();
        }

        if(view.getId() == dateContactTested.getId()){
            datePickerDialog3.show();
        }

        if(view.getId() == save.getId()){
            save();
        }
    }

    public void save(){
        if(validate()){
            IndexCaseTestingForm item = new IndexCaseTestingForm();
            item.firstNameOfIndex = firstNameOfIndex.getText().toString();
            item.lastNameOfIndex = lastNameOfIndex.getText().toString();
            item.indexOIARTNumber = Integer.parseInt(indexOIARTNumber.getText().toString());
            item.initiatedOnART = getInitiatedOnART();
            if(reasonForNotBeingInitiated.getVisibility() == View.VISIBLE){
                item.reasonForNotBeingInitiated = reasonForNotBeingInitiated.getText().toString();
            }
            item.consentForListedContacts = getConsentForListedContacts();
            item.preferredPlaceForContactsToBeTested = preferredPlaceForContactsToBeTested.getText().toString();
            item.appointmentDateForContact = DateUtil.getDateFromString(appointmentDateForContact.getText().toString());
            item.nameOfContact = nameOfContact.getText().toString();
            if(other.getVisibility() == View.VISIBLE){
                item.relationShipToIndex = other.getText().toString();
            }else{
                item.relationShipToIndex = getRelationshipToIndex().getName();
            }
            item.age = Integer.parseInt(age.getText().toString());
            item.gender = getGender();
            item.contactAddress = contactAddress.getText().toString();
            item.indexContactNumber = Long.parseLong(indexContactNumber.getText().toString());
            item.dateCalled = DateUtil.getDateFromString(dateCalled.getText().toString());
            item.callOutcome = callOutcome.getText().toString();
            item.dateVisited = DateUtil.getDateFromString(dateVisited.getText().toString());
            item.visitOutcome = visitOutcome.getText().toString();
            item.contactTestedDate = DateUtil.getDateFromString(dateContactTested.getText().toString());
            item.locationOfTest = locationOfTest.getText().toString();
            item.hivResult = getHivResult();
            item.enrolledIntoCare = getEnrolledIntoCare();
            item.save();
            for(IndexCaseTestingForm m : IndexCaseTestingForm.getAll()){
                Log.d("Index", AppUtil.createGson().toJson(m));
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public boolean validate(){
        boolean isValid = true;
        if(firstNameOfIndex.getText().toString().isEmpty()){
            firstNameOfIndex.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            firstNameOfIndex.setError(null);
        }

        if(lastNameOfIndex.getText().toString().isEmpty()){
            lastNameOfIndex.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            lastNameOfIndex.setError(null);
        }

        if(indexOIARTNumber.getText().toString().isEmpty()){
            indexOIARTNumber.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            indexOIARTNumber.setError(null);
        }

        if(getInitiatedOnART() == null){
            AppUtil.createShortNotification(this, "Please select initiated on ART");
            isValid = false;
        }

        if(getInitiatedOnART() != null && getInitiatedOnART() == YesNo.NO){
            if(reasonForNotBeingInitiated.getText().toString().isEmpty()){
                reasonForNotBeingInitiated.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                reasonForNotBeingInitiated.setError(null);
            }
        }

        if(getConsentForListedContacts() == null){
            AppUtil.createShortNotification(this, "Please select consent for list contacts");
            isValid = false;
        }

        if(preferredPlaceForContactsToBeTested.getText().toString().isEmpty()){
            preferredPlaceForContactsToBeTested.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            preferredPlaceForContactsToBeTested.setError(null);
        }

        if(appointmentDateForContact.getText().toString().isEmpty()){
            appointmentDateForContact.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            appointmentDateForContact.setError(null);
        }

        if(nameOfContact.getText().toString().isEmpty()){
            nameOfContact.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            nameOfContact.setError(null);
        }

        if(getRelationshipToIndex() == null){
            AppUtil.createShortNotification(this, "Please select relationship to index");
            isValid = false;
        }

        if(getRelationshipToIndex() != null && getRelationshipToIndex().equals(Relationship.OTHER)){
            if(other.getText().toString().isEmpty()){
                other.setError(getResources().getString(R.string.required_field_error));
                isValid = false;
            }else{
                other.setError(null);
            }
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

        if(contactAddress.getText().toString().isEmpty()){
            contactAddress.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            contactAddress.setError(null);
        }

        if(indexContactNumber.getText().toString().isEmpty()){
            indexContactNumber.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            indexContactNumber.setError(null);
        }

        if(dateCalled.getText().toString().isEmpty()){
            dateCalled.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateCalled.setError(null);
        }

        if(callOutcome.getText().toString().isEmpty()){
            callOutcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            callOutcome.setError(null);
        }

        if(dateVisited.getText().toString().isEmpty()){
            dateVisited.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateVisited.setError(null);
        }

        if(visitOutcome.getText().toString().isEmpty()){
            visitOutcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            visitOutcome.setError(null);
        }

        if(dateContactTested.getText().toString().isEmpty()){
            dateContactTested.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateContactTested.setError(null);
        }

        if(locationOfTest.getText().toString().isEmpty()){
            locationOfTest.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            locationOfTest.setError(null);
        }

        if(getHivResult() == null){
            AppUtil.createShortNotification(this, "Please select HIV Result");
            isValid = false;
        }

        if(getEnrolledIntoCare() == null){
            AppUtil.createShortNotification(this, "Please select enrolled into care");
            isValid = false;
        }
        return isValid;
    }

    public YesNo getInitiatedOnART(){
        YesNo item = null;
        for(int i = 0; i < initiatedOnART.getCount(); i++){
            if(initiatedOnART.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public YesNo getConsentForListedContacts(){
        YesNo item = null;
        for(int i = 0; i < consentForListedContact.getCount(); i++){
            if(consentForListedContact.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public Relationship getRelationshipToIndex(){
        Relationship item = null;
        for(int i = 0; i < relationshipToIndex.getCount(); i++){
            if(relationshipToIndex.isItemChecked(i)){
                item = relationshipArrayAdapter.getItem(i);
            }
        }
        return item;
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

    public HIVResult getHivResult(){
        HIVResult item = null;
        for(int i = 0; i < hivResult.getCount(); i++){
            if(hivResult.isItemChecked(i)){
                item = hivResultArrayAdapter.getItem(i);
            }
        }
        return item;
    }

    public YesNo getEnrolledIntoCare(){
        YesNo item = null;
        for(int i = 0; i < enrolledIntoCare.getCount(); i++){
            if(enrolledIntoCare.isItemChecked(i)){
                item = yesNoArrayAdapter.getItem(i);
            }
        }
        return item;
    }
}
