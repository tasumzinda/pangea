package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import zw.co.ncmp.business.DefaulterTrackingForm;
import zw.co.ncmp.business.IndexCaseTestingForm;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class DefaulterTrackingFormActivity extends MenuBar implements View.OnClickListener{


    @BindView(R.id.first_name)
    EditText firstNameOfIndex;
    @BindView(R.id.last_name)
    EditText lastNameOfIndex;
    @BindView(R.id.btn_save)
    Button save;
    @BindView(R.id.physicalAddress)
    EditText physicalAddress;
    @BindView(R.id.contactDetails)
    EditText contactDetails;
    @BindView(R.id.index_oi_art_number)
    EditText indexOIARTNumber;
    @BindView(R.id.dateOIARTInitiation)
    EditText dateOfOIARTInitiation;
    DatePickerDialog datePickerDialog;
    @BindView(R.id.reviewDate)
    EditText reviewDate;
    DatePickerDialog datePickerDialog1;
    @BindView(R.id.dateAppointmentDeemedDefaulter)
    EditText dateAppointmentDeemedDefaulter;
    DatePickerDialog datePickerDialog2;
    @BindView(R.id.reasonForTracking)
    EditText reasonForTracking;
    @BindView(R.id.dateOfCall1)
    EditText dateOfCall1;
    DatePickerDialog datePickerDialog3;
    @BindView(R.id.callOutcome)
    EditText callOutcome;
    @BindView(R.id.appointmentDateIfLinkedToCare)
    EditText appointmentDateIfLinkedToCare;
    DatePickerDialog datePickerDialog4;
    @BindView(R.id.dateOfCall2)
    EditText dateOfCall2;
    DatePickerDialog datePickerDialog5;
    @BindView(R.id.call2Outcome)
    EditText call2Outcome;
    @BindView(R.id.dateOfCall3)
    EditText dateOfCall3;
    DatePickerDialog datePickerDialog6;
    @BindView(R.id.call3Outcome)
    EditText call3Outcome;
    @BindView(R.id.dateOfVisit)
    EditText dateOfVisit;
    DatePickerDialog datePickerDialog7;
    @BindView(R.id.visitOutcome)
    EditText visitOutcome;
    @BindView(R.id.appointmentDateIfLinkedToCare1)
    EditText appointmentDateIfLinkedToCare1;
    DatePickerDialog datePickerDialog8;
    @BindView(R.id.dateVisitDone)
    EditText dateVisitDone;
    DatePickerDialog datePickerDialog9;
    @BindView(R.id.visitDoneOutcome)
    EditText visitDoneOutcome;
    @BindView(R.id.appointmentDateIfLinkedBackToCare)
    EditText appointmentDateIfLinkedBackToCare;
    DatePickerDialog datePickerDialog10;
    @BindView(R.id.dateClientVisitedFacilityIfLinkedToCare)
    EditText dateClientVisitedFacilityIfLinkedToCare;
    DatePickerDialog datePickerDialog11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaulter_tracking_form);
        ButterKnife.bind(this);
        save.setOnClickListener(this);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateOfOIARTInitiation, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateOfOIARTInitiation.setOnClickListener(this);
        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(reviewDate, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        reviewDate.setOnClickListener(this);
        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateAppointmentDeemedDefaulter, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateAppointmentDeemedDefaulter.setOnClickListener(this);
        datePickerDialog3 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateOfCall1, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateOfCall1.setOnClickListener(this);
        datePickerDialog4 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(appointmentDateIfLinkedToCare, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        appointmentDateIfLinkedToCare.setOnClickListener(this);
        datePickerDialog5 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateOfCall2, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateOfCall2.setOnClickListener(this);
        datePickerDialog6 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateOfCall3, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateOfCall3.setOnClickListener(this);
        datePickerDialog7 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateOfVisit, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateOfVisit.setOnClickListener(this);
        datePickerDialog8 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(appointmentDateIfLinkedToCare1, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        appointmentDateIfLinkedToCare1.setOnClickListener(this);
        datePickerDialog9 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateVisitDone, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateVisitDone.setOnClickListener(this);
        datePickerDialog10 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(appointmentDateIfLinkedBackToCare, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        appointmentDateIfLinkedBackToCare.setOnClickListener(this);
        datePickerDialog11 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(dateClientVisitedFacilityIfLinkedToCare, newDate.getTime());
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dateClientVisitedFacilityIfLinkedToCare.setOnClickListener(this);
        setSupportActionBar(createToolBar("Defaulter Tracking Form"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateLabel(EditText editText, Date date) {
        editText.setText(AppUtil.getStringDate(date));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == dateOfOIARTInitiation.getId()){
            datePickerDialog.show();
        }

        if(view.getId() == reviewDate.getId()){
            datePickerDialog1.show();
        }

        if(view.getId() == dateAppointmentDeemedDefaulter.getId()){
            datePickerDialog2.show();
        }

        if(view.getId() == dateOfCall1.getId()){
            datePickerDialog3.show();
        }

        if(view.getId() == appointmentDateIfLinkedToCare.getId()){
            datePickerDialog4.show();
        }

        if(view.getId() == dateOfCall2.getId()){
            datePickerDialog5.show();
        }

        if(view.getId() == dateOfCall3.getId()){
            datePickerDialog6.show();
        }

        if(view.getId() == dateOfVisit.getId()){
            datePickerDialog7.show();
        }

        if(view.getId() == appointmentDateIfLinkedToCare1.getId()){
            datePickerDialog8.show();
        }

        if(view.getId() == dateVisitDone.getId()){
            datePickerDialog9.show();
        }

        if(view.getId() == appointmentDateIfLinkedBackToCare.getId()){
            datePickerDialog10.show();
        }

        if(view.getId() == dateClientVisitedFacilityIfLinkedToCare.getId()){
            datePickerDialog11.show();
        }

        if(view.getId() == save.getId()){
            save();
        }
    }

    public void save(){
        if(validate()){
            DefaulterTrackingForm item = new DefaulterTrackingForm();
            item.firstNameOfIndex = firstNameOfIndex.getText().toString();
            item.lastNameOfIndex = lastNameOfIndex.getText().toString();
            item.physicalAddress = physicalAddress.getText().toString();
            item.contactDetails = contactDetails.getText().toString();
            item.oIARTNumber = Integer.parseInt(indexOIARTNumber.getText().toString());
            item.dateArtInitiation = DateUtil.getDateFromString(dateOfOIARTInitiation.getText().toString());
            item.reviewDate = DateUtil.getDateFromString(reviewDate.getText().toString());
            item.appointmentDeemedDefaulter = DateUtil.getDateFromString(dateAppointmentDeemedDefaulter.getText().toString());
            item.reasonForTracking = reasonForTracking.getText().toString();
            item.dateOfCall1 = DateUtil.getDateFromString(dateOfCall1.getText().toString());
            item.call1Outcome = callOutcome.getText().toString();
            if( ! appointmentDateIfLinkedToCare.getText().toString().isEmpty()){
                item.appointmentDateIfLinkedToCare = DateUtil.getDateFromString(appointmentDateIfLinkedToCare.getText().toString());
            }
            item.dateOfCall2 = DateUtil.getDateFromString(dateOfCall2.getText().toString());
            item.call2Outcome = call2Outcome.getText().toString();
            item.dateOfCall3 = DateUtil.getDateFromString(dateOfCall3.getText().toString());
            item.call3Outcome = call3Outcome.getText().toString();
            item.dateOfVisit = DateUtil.getDateFromString(dateOfVisit.getText().toString());
            item.visitOutcome = visitOutcome.getText().toString();
            item.appointmentDateIfLinkedToCare1 = DateUtil.getDateFromString(appointmentDateIfLinkedToCare1.getText().toString());
            item.dateVisitDone = DateUtil.getDateFromString(dateVisitDone.getText().toString());
            item.visitDoneOutcome = visitDoneOutcome.getText().toString();
            if( ! appointmentDateIfLinkedBackToCare.getText().toString().isEmpty()){
                item.appointmentDateIfLinkedBackToCare = DateUtil.getDateFromString(appointmentDateIfLinkedBackToCare.getText().toString());
            }

            if( ! dateClientVisitedFacilityIfLinkedToCare.getText().toString().isEmpty()){
                item.dateClientVisitedFacilityIfLinkedToCare = DateUtil.getDateFromString(dateClientVisitedFacilityIfLinkedToCare.getText().toString());
            }
            item.save();
            for(DefaulterTrackingForm m : DefaulterTrackingForm.getAll()){
                Log.d("Defaulter", AppUtil.createGson().toJson(m));
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

        if(physicalAddress.getText().toString().isEmpty()){
            physicalAddress.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            physicalAddress.setError(null);
        }

        if(contactDetails.getText().toString().isEmpty()){
            contactDetails.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            contactDetails.setError(null);
        }

        if(indexOIARTNumber.getText().toString().isEmpty()){
            indexOIARTNumber.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            indexOIARTNumber.setError(null);
        }

        if(dateOfOIARTInitiation.getText().toString().isEmpty()){
            dateOfOIARTInitiation.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateOfOIARTInitiation.setError(null);
        }

        if(reviewDate.getText().toString().isEmpty()){
            reviewDate.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            reviewDate.setError(null);
        }

        if(dateAppointmentDeemedDefaulter.getText().toString().isEmpty()){
            dateAppointmentDeemedDefaulter.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateAppointmentDeemedDefaulter.setError(null);
        }

        if(reasonForTracking.getText().toString().isEmpty()){
            reasonForTracking.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            reasonForTracking.setError(null);
        }

        if(dateOfCall1.getText().toString().isEmpty()){
            dateOfCall1.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateOfCall1.setError(null);
        }

        if(callOutcome.getText().toString().isEmpty()){
            callOutcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            callOutcome.setError(null);
        }

        /*if(appointmentDateIfLinkedToCare.getText().toString().isEmpty()){
            appointmentDateIfLinkedToCare.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            appointmentDateIfLinkedToCare.setError(null);
        }*/

        if(dateOfCall2.getText().toString().isEmpty()){
            dateOfCall2.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateOfCall2.setError(null);
        }

        if(call2Outcome.getText().toString().isEmpty()){
            call2Outcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            call2Outcome.setError(null);
        }

        if(dateOfCall3.getText().toString().isEmpty()){
            dateOfCall3.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateOfCall3.setError(null);
        }

        if(call3Outcome.getText().toString().isEmpty()){
            call3Outcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            call3Outcome.setError(null);
        }

        if(dateOfVisit.getText().toString().isEmpty()){
            dateOfVisit.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateOfVisit.setError(null);
        }

        if(visitOutcome.getText().toString().isEmpty()){
            visitOutcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            visitOutcome.setError(null);
        }

        if(appointmentDateIfLinkedToCare1.getText().toString().isEmpty()){
            appointmentDateIfLinkedToCare1.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            appointmentDateIfLinkedToCare1.setError(null);
        }

        if(dateVisitDone.getText().toString().isEmpty()){
            dateVisitDone.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            dateVisitDone.setError(null);
        }

        if(visitDoneOutcome.getText().toString().isEmpty()){
            visitDoneOutcome.setError(getResources().getString(R.string.required_field_error));
            isValid = false;
        }else{
            visitDoneOutcome.setError(null);
        }

        return isValid;
    }
}
