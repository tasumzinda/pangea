package zw.co.ncmp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.Date;

import it.sephiroth.android.library.widget.HListView;
import zw.co.ncmp.business.ARTForm;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.SupplementaryIndicatorForm;
import zw.co.ncmp.business.util.YesNo;
import zw.co.ncmp.util.AppUtil;

public class SupplementaryIndicatorFormActivity extends MenuBar implements View.OnClickListener {

    Spinner facility;
    Spinner period;
    EditText dateCreated;
    EditText name;
    EditText estimatedCatchmentPopulation;
    EditText numberOfPreARTPatients;
    EditText numberOfCLFsDeployed;
    EditText dateCLFsDeployed;
    EditText numberOfActiveCARGs;
    EditText numberOfActiveCARGMembers;
    EditText numberOfCARGsFormedThisMonth;
    EditText numberOfCARGsFormedToDate;
    EditText numberOfCATSSupportersDeployed;
    EditText dateCATSDeployed;
    EditText numberOfActiveAdolescentSupportGroups;
    EditText numberOfActiveAdolescentSupportGroupMembers;
    HListView areYouImplementingDefaulterTracking;
    EditText dateDefaulterTrackingImplemented;
    HListView areYouImplementingIndexTesting;
    EditText dateIndexTestingImplemented;
    HListView areYouImplementingRetestPriorToARTInitiation;
    EditText dateRetestPriorToARTInitiationImplemented;
    HListView doesFacilityHaveStaticHTSHRH;
    EditText dateStaticHTSHRSDeployed;
    HListView doesFacilityHaveStaticTXNEWHRH;
    EditText dateStaticTXNEWHRHDeployed;
    HListView doesFacilityProvideMultiMonthDrugDispensing;
    EditText dateMultiMonthDrugDispensingStarted;
    HListView doesFacilityHaveFunctionalHealthCentreCommittee;
    HListView doesFacilityHaveFunctionalQualityImprovementCommittee;
    HListView doesFacilityHaveQualityImprovementProject;
    EditText numberOfOPDPatientsSeenInLastMonth;
    EditText numberOfOPDPatientsWithKnownHIVStatusOnEntry;
    EditText numberOfOPDPatientsTestedForHIVInLastMonth;
    EditText numberOfOPDPatientsTestedHIVPositiveInLastMonth;
    EditText numberOfSTIPatientsSeenInLastMonth;
    EditText numberOfSTIPatientsWithKnownHIVStatusOnEntry;
    EditText numberOfSTIPatientsTestedForHIVInLastMonth;
    EditText numberOfSTIPatientsTestedHIVPositiveInLastMonth;
    EditText numberOfInpatientPatientsSeenInLastMonth;
    EditText numberOfInpatientPatientsWithKnownHIVStatusOnEntry;
    EditText numberOfInpatientPatientsTestedForHIVInLastMonth;
    EditText numberOfInpatientPatientsTestedHIVPositiveInLastMonth;
    EditText numberOfClientsWithDocumentedCompletedReferralCycle;
    EditText numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity;
    EditText numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility;
    EditText numberOfClientsWhoDefaultedART;
    EditText numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome;
    EditText numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers;
    EditText numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare;
    EditText numberOfVillageHealthWorkersWorkingWithITECH;

    Button btn_completed;
    Button btn_submit;
    Button btn_save;

    private SupplementaryIndicatorForm form;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    private DatePickerDialog datePickerDialog3;
    private DatePickerDialog datePickerDialog4;
    private DatePickerDialog datePickerDialog5;
    private DatePickerDialog datePickerDialog6;
    private DatePickerDialog datePickerDialog7;
    private DatePickerDialog datePickerDialog8;
    private DatePickerDialog datePickerDialog;
    ArrayAdapter<Facility> facilityArrayAdapter;
    EditText facility_label;
    ArrayAdapter<YesNo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplement_indicator_form_activity);

        Intent intent = getIntent();
        Long form_id = intent.getLongExtra(AppUtil.ID, 0);

        facility = (Spinner) findViewById(R.id.facility);
        name = (EditText) findViewById(R.id.name);
        period = (Spinner) findViewById(R.id.period);
        facility_label = (EditText) findViewById(R.id.facility_label);
        facility_label.setVisibility(View.GONE);

        estimatedCatchmentPopulation = (EditText) findViewById(R.id.estimatedCatchmentPopulation);
        numberOfPreARTPatients = (EditText) findViewById(R.id.numberOfPreARTPatients);
        numberOfCLFsDeployed = (EditText) findViewById(R.id.numberOfCLFsDeployed);
        dateCLFsDeployed = (EditText) findViewById(R.id.dateCLFsDeployed);
        numberOfActiveCARGs = (EditText) findViewById(R.id.numberOfActiveCARGs);
        numberOfActiveCARGMembers = (EditText) findViewById(R.id.numberOfActiveCARGMembers);
        numberOfCARGsFormedThisMonth = (EditText) findViewById(R.id.numberOfCARGsFormedThisMonth);
        numberOfCARGsFormedToDate = (EditText) findViewById(R.id.numberOfCARGsFormedToDate);
        numberOfCATSSupportersDeployed = (EditText) findViewById(R.id.numberOfCATSSupportersDeployed);
        dateCATSDeployed = (EditText) findViewById(R.id.dateCATSDeployed);
        numberOfActiveAdolescentSupportGroups = (EditText) findViewById(R.id.numberOfActiveAdolescentSupportGroups);
        numberOfActiveAdolescentSupportGroupMembers = (EditText) findViewById(R.id.numberOfActiveAdolescentSupportGroupMembers);
        areYouImplementingDefaulterTracking = (HListView) findViewById(R.id.areYouImplementingDefaulterTracking);
        dateDefaulterTrackingImplemented = (EditText) findViewById(R.id.dateDefaulterTrackingImplemented);
        areYouImplementingIndexTesting = (HListView) findViewById(R.id.areYouImplementingIndexTesting);
        dateIndexTestingImplemented = (EditText) findViewById(R.id.dateIndexTestingImplemented);
        areYouImplementingRetestPriorToARTInitiation = (HListView) findViewById(R.id.areYouImplementingRetestPriorToARTInitiation);
        dateRetestPriorToARTInitiationImplemented = (EditText) findViewById(R.id.dateRetestPriorToARTInitiationImplemented);
        doesFacilityHaveStaticHTSHRH = (HListView) findViewById(R.id.doesFacilityHaveStaticHTSHRH);
        dateStaticHTSHRSDeployed = (EditText) findViewById(R.id.dateStaticHTSHRSDeployed);
        doesFacilityHaveStaticTXNEWHRH = (HListView) findViewById(R.id.doesFacilityHaveStaticTXNEWHRH);
        dateStaticTXNEWHRHDeployed = (EditText) findViewById(R.id.dateStaticTXNEWHRHDeployed);
        doesFacilityProvideMultiMonthDrugDispensing = (HListView) findViewById(R.id.doesFacilityProvideMultiMonthDrugDispensing);
        dateMultiMonthDrugDispensingStarted = (EditText) findViewById(R.id.dateMultiMonthDrugDispensingStarted);
        doesFacilityHaveFunctionalHealthCentreCommittee = (HListView) findViewById(R.id.doesFacilityHaveFunctionalHealthCentreCommittee);
        doesFacilityHaveFunctionalQualityImprovementCommittee = (HListView) findViewById(R.id.doesFacilityHaveFunctionalQualityImprovementCommittee);
        doesFacilityHaveQualityImprovementProject = (HListView) findViewById(R.id.doesFacilityHaveQualityImprovementProject);
        numberOfOPDPatientsSeenInLastMonth = (EditText) findViewById(R.id.numberOfOPDPatientsSeenInLastMonth);
        numberOfOPDPatientsWithKnownHIVStatusOnEntry = (EditText) findViewById(R.id.numberOfOPDPatientsWithKnownHIVStatusOnEntry);
        numberOfOPDPatientsTestedForHIVInLastMonth = (EditText) findViewById(R.id.numberOfOPDPatientsTestedForHIVInLastMonth);
        numberOfOPDPatientsTestedHIVPositiveInLastMonth = (EditText) findViewById(R.id.numberOfOPDPatientsTestedHIVPositiveInLastMonth);
        numberOfSTIPatientsSeenInLastMonth = (EditText) findViewById(R.id.numberOfSTIPatientsSeenInLastMonth);
        numberOfSTIPatientsWithKnownHIVStatusOnEntry = (EditText) findViewById(R.id.numberOfSTIPatientsWithKnownHIVStatusOnEntry);
        numberOfSTIPatientsTestedForHIVInLastMonth = (EditText) findViewById(R.id.numberOfSTIPatientsTestedForHIVInLastMonth);
        numberOfSTIPatientsTestedHIVPositiveInLastMonth = (EditText) findViewById(R.id.numberOfSTIPatientsTestedHIVPositiveInLastMonth);
        numberOfInpatientPatientsSeenInLastMonth = (EditText) findViewById(R.id.numberOfInpatientPatientsSeenInLastMonth);
        numberOfInpatientPatientsWithKnownHIVStatusOnEntry = (EditText) findViewById(R.id.numberOfInpatientPatientsWithKnownHIVStatusOnEntry);
        numberOfInpatientPatientsTestedForHIVInLastMonth = (EditText) findViewById(R.id.numberOfInpatientPatientsTestedForHIVInLastMonth);
        numberOfInpatientPatientsTestedHIVPositiveInLastMonth = (EditText) findViewById(R.id.numberOfInpatientPatientsTestedHIVPositiveInLastMonth);
        numberOfClientsWithDocumentedCompletedReferralCycle = (EditText) findViewById(R.id.numberOfClientsWithDocumentedCompletedReferralCycle);
        numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity = (EditText) findViewById(R.id.numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity);
        numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility = (EditText) findViewById(R.id.numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility);
        numberOfClientsWhoDefaultedART = (EditText) findViewById(R.id.numberOfClientsWhoDefaultedART);


        numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome = (EditText) findViewById(R.id.numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome);
        numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers = (EditText) findViewById(R.id.numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers);
        numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare = (EditText) findViewById(R.id.numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare);
        numberOfVillageHealthWorkersWorkingWithITECH = (EditText) findViewById(R.id.numberOfVillageHealthWorkersWorkingWithITECH);
        adapter = new ArrayAdapter<>(this, R.layout.check_box_item, YesNo.values());
        areYouImplementingDefaulterTracking.setAdapter(adapter);
        areYouImplementingDefaulterTracking.setItemsCanFocus(false);
        areYouImplementingDefaulterTracking.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        areYouImplementingIndexTesting.setAdapter(adapter);
        areYouImplementingIndexTesting.setItemsCanFocus(false);
        areYouImplementingIndexTesting.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        areYouImplementingRetestPriorToARTInitiation.setAdapter(adapter);
        areYouImplementingRetestPriorToARTInitiation.setItemsCanFocus(false);
        areYouImplementingRetestPriorToARTInitiation.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doesFacilityHaveStaticHTSHRH.setAdapter(adapter);
        doesFacilityHaveStaticHTSHRH.setItemsCanFocus(false);
        doesFacilityHaveStaticHTSHRH.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doesFacilityHaveStaticTXNEWHRH.setAdapter(adapter);
        doesFacilityHaveStaticTXNEWHRH.setItemsCanFocus(false);
        doesFacilityHaveStaticTXNEWHRH.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doesFacilityProvideMultiMonthDrugDispensing.setAdapter(adapter);
        doesFacilityProvideMultiMonthDrugDispensing.setItemsCanFocus(false);
        doesFacilityProvideMultiMonthDrugDispensing.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doesFacilityHaveFunctionalHealthCentreCommittee.setAdapter(adapter);
        doesFacilityHaveFunctionalHealthCentreCommittee.setItemsCanFocus(false);
        doesFacilityHaveFunctionalHealthCentreCommittee.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doesFacilityHaveFunctionalQualityImprovementCommittee.setAdapter(adapter);
        doesFacilityHaveFunctionalQualityImprovementCommittee.setItemsCanFocus(false);
        doesFacilityHaveFunctionalQualityImprovementCommittee.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doesFacilityHaveQualityImprovementProject.setAdapter(adapter);
        doesFacilityHaveQualityImprovementProject.setItemsCanFocus(false);
        doesFacilityHaveQualityImprovementProject.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        dateCreated = (EditText) findViewById(R.id.dateCreated);


        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateCreated);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCreated.setOnClickListener(this);

        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateCLFsDeployed);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCLFsDeployed.setOnClickListener(this);

        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateCATSDeployed);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateCATSDeployed.setOnClickListener(this);

        datePickerDialog3 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateDefaulterTrackingImplemented);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateDefaulterTrackingImplemented.setOnClickListener(this);

        datePickerDialog4 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateIndexTestingImplemented);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateIndexTestingImplemented.setOnClickListener(this);

        datePickerDialog5 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateRetestPriorToARTInitiationImplemented);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateRetestPriorToARTInitiationImplemented.setOnClickListener(this);

        datePickerDialog6 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateStaticHTSHRSDeployed);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateStaticHTSHRSDeployed.setOnClickListener(this);

        datePickerDialog7 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateStaticTXNEWHRHDeployed);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateStaticTXNEWHRHDeployed.setOnClickListener(this);

        datePickerDialog8 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                updateLabel(newDate.getTime(), dateMultiMonthDrugDispensingStarted);
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        dateMultiMonthDrugDispensingStarted.setOnClickListener(this);

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

        if (form_id != 0) {
            form = SupplementaryIndicatorForm.get(form_id);
            name.setText(form.name);
            estimatedCatchmentPopulation.setText(AppUtil.getLongValue(form.estimatedCatchmentPopulation));
            numberOfPreARTPatients.setText(AppUtil.getLongValue(form.numberOfPreARTPatients));

            numberOfCLFsDeployed.setText(AppUtil.getLongValue(form.numberOfCLFsDeployed));
            numberOfActiveCARGs.setText(AppUtil.getLongValue(form.numberOfActiveCARGs));
            numberOfActiveCARGMembers.setText(AppUtil.getLongValue(form.numberOfActiveCARGMembers));
            numberOfCARGsFormedThisMonth.setText(AppUtil.getLongValue(form.numberOfCARGsFormedThisMonth));

            numberOfCARGsFormedToDate.setText(AppUtil.getLongValue(form.numberOfCARGsFormedToDate));
            numberOfCATSSupportersDeployed.setText(AppUtil.getLongValue(form.numberOfCATSSupportersDeployed));
            numberOfActiveAdolescentSupportGroups.setText(AppUtil.getLongValue(form.numberOfActiveAdolescentSupportGroups));
            numberOfActiveAdolescentSupportGroupMembers.setText(AppUtil.getLongValue(form.numberOfActiveAdolescentSupportGroupMembers));

            numberOfOPDPatientsSeenInLastMonth.setText(AppUtil.getLongValue(form.numberOfOPDPatientsSeenInLastMonth));
            numberOfOPDPatientsWithKnownHIVStatusOnEntry.setText(AppUtil.getLongValue(form.numberOfOPDPatientsWithKnownHIVStatusOnEntry));
            numberOfOPDPatientsTestedForHIVInLastMonth.setText(AppUtil.getLongValue(form.numberOfOPDPatientsTestedForHIVInLastMonth));
            numberOfOPDPatientsTestedHIVPositiveInLastMonth.setText(AppUtil.getLongValue(form.numberOfOPDPatientsTestedHIVPositiveInLastMonth));

            numberOfSTIPatientsSeenInLastMonth.setText(AppUtil.getLongValue(form.numberOfSTIPatientsSeenInLastMonth));
            numberOfSTIPatientsWithKnownHIVStatusOnEntry.setText(AppUtil.getLongValue(form.numberOfSTIPatientsWithKnownHIVStatusOnEntry));
            numberOfSTIPatientsTestedForHIVInLastMonth.setText(AppUtil.getLongValue(form.numberOfSTIPatientsTestedForHIVInLastMonth));
            numberOfSTIPatientsTestedHIVPositiveInLastMonth.setText(AppUtil.getLongValue(form.numberOfSTIPatientsTestedHIVPositiveInLastMonth));
            numberOfInpatientPatientsSeenInLastMonth.setText(AppUtil.getLongValue(form.numberOfInpatientPatientsSeenInLastMonth));
            numberOfInpatientPatientsWithKnownHIVStatusOnEntry.setText(AppUtil.getLongValue(form.numberOfInpatientPatientsWithKnownHIVStatusOnEntry));
            numberOfInpatientPatientsTestedForHIVInLastMonth.setText(AppUtil.getLongValue(form.numberOfInpatientPatientsTestedForHIVInLastMonth));
            numberOfInpatientPatientsTestedHIVPositiveInLastMonth.setText(AppUtil.getLongValue(form.numberOfInpatientPatientsTestedHIVPositiveInLastMonth));


            numberOfClientsWithDocumentedCompletedReferralCycle.setText(AppUtil.getLongValue(form.numberOfClientsWithDocumentedCompletedReferralCycle));
            numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity.setText(AppUtil.getLongValue(form.numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity));
            numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility.setText(AppUtil.getLongValue(form.numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility));
            numberOfClientsWhoDefaultedART.setText(AppUtil.getLongValue(form.numberOfClientsWhoDefaultedART));
            numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome.setText(AppUtil.getLongValue(form.numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome));
            numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers.setText(AppUtil.getLongValue(form.numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers));
            numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare.setText(AppUtil.getLongValue(form.numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare));
            numberOfVillageHealthWorkersWorkingWithITECH.setText(AppUtil.getLongValue(form.numberOfVillageHealthWorkersWorkingWithITECH));


            updateLabel(form.dateCreated, dateCreated);

            int i = 0;
            for (Facility s : Facility.getAll()) {
                if (form.facility.equals(facility.getItemAtPosition(i))) {
                    facility.setSelection(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (Period s : Period.getAll()) {
                if (form.period.equals(period.getItemAtPosition(i))) {
                    period.setSelection(i);
                    break;
                }
                i++;
            }

            setSupportActionBar(createToolBar("Supplimentary Indicator Update"));
        } else {
            form = new SupplementaryIndicatorForm();
            setSupportActionBar(createToolBar("Supplimentary Indicator"));
        }

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_save.setBackgroundResource(R.drawable.finish_background);

        btn_completed = (Button) findViewById(R.id.btn_completed);
        btn_completed.setVisibility(View.GONE);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_submit.setVisibility(View.GONE);
        btn_submit.setBackgroundResource(R.drawable.finish_background);

        if (form.dateCreated != null) {
            btn_submit.setVisibility(View.VISIBLE);
        }

        if (form.dateSubmitted != null) {
            btn_submit.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            btn_completed.setVisibility(View.VISIBLE);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateLabel(Date date, EditText editText) {
        editText.setText(AppUtil.getStringDate(date));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                form.facility = (Facility) facility.getSelectedItem();
                form.name = name.getText().toString();
                form.period = (Period) period.getSelectedItem();

                form.estimatedCatchmentPopulation = AppUtil.getLongValue(estimatedCatchmentPopulation.getText().toString());
                form.numberOfPreARTPatients = AppUtil.getLongValue(numberOfPreARTPatients.getText().toString());
                form.numberOfCLFsDeployed = AppUtil.getLongValue(numberOfCLFsDeployed.getText().toString());
                form.numberOfActiveCARGs = AppUtil.getLongValue(numberOfActiveCARGs.getText().toString());
                form.numberOfActiveCARGMembers = AppUtil.getLongValue(numberOfActiveCARGMembers.getText().toString());
                form.numberOfCARGsFormedThisMonth = AppUtil.getLongValue(numberOfCARGsFormedThisMonth.getText().toString());
                form.numberOfCARGsFormedToDate = AppUtil.getLongValue(numberOfCARGsFormedToDate.getText().toString());
                form.numberOfCATSSupportersDeployed = AppUtil.getLongValue(numberOfCATSSupportersDeployed.getText().toString());
                form.numberOfActiveAdolescentSupportGroups = AppUtil.getLongValue(numberOfActiveAdolescentSupportGroups.getText().toString());
                form.numberOfActiveAdolescentSupportGroupMembers = AppUtil.getLongValue(numberOfActiveAdolescentSupportGroupMembers.getText().toString());
                form.numberOfOPDPatientsSeenInLastMonth = AppUtil.getLongValue(numberOfOPDPatientsSeenInLastMonth.getText().toString());
                form.numberOfOPDPatientsWithKnownHIVStatusOnEntry = AppUtil.getLongValue(numberOfOPDPatientsWithKnownHIVStatusOnEntry.getText().toString());
                form.numberOfOPDPatientsTestedForHIVInLastMonth = AppUtil.getLongValue(numberOfOPDPatientsTestedForHIVInLastMonth.getText().toString());
                form.numberOfOPDPatientsTestedHIVPositiveInLastMonth = AppUtil.getLongValue(numberOfOPDPatientsTestedHIVPositiveInLastMonth.getText().toString());


                form.numberOfSTIPatientsSeenInLastMonth = AppUtil.getLongValue(numberOfSTIPatientsSeenInLastMonth.getText().toString());
                form.numberOfSTIPatientsWithKnownHIVStatusOnEntry = AppUtil.getLongValue(numberOfSTIPatientsWithKnownHIVStatusOnEntry.getText().toString());
                form.numberOfSTIPatientsTestedForHIVInLastMonth = AppUtil.getLongValue(numberOfSTIPatientsTestedForHIVInLastMonth.getText().toString());
                form.numberOfSTIPatientsTestedHIVPositiveInLastMonth = AppUtil.getLongValue(numberOfSTIPatientsTestedHIVPositiveInLastMonth.getText().toString());
                form.numberOfInpatientPatientsSeenInLastMonth = AppUtil.getLongValue(numberOfInpatientPatientsSeenInLastMonth.getText().toString());
                form.numberOfInpatientPatientsWithKnownHIVStatusOnEntry = AppUtil.getLongValue(numberOfInpatientPatientsWithKnownHIVStatusOnEntry.getText().toString());
                form.numberOfInpatientPatientsTestedForHIVInLastMonth = AppUtil.getLongValue(numberOfInpatientPatientsTestedForHIVInLastMonth.getText().toString());
                form.numberOfInpatientPatientsTestedHIVPositiveInLastMonth = AppUtil.getLongValue(numberOfInpatientPatientsTestedHIVPositiveInLastMonth.getText().toString());
                form.numberOfClientsWithDocumentedCompletedReferralCycle = AppUtil.getLongValue(numberOfClientsWithDocumentedCompletedReferralCycle.getText().toString());
                form.numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity = AppUtil.getLongValue(numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity.getText().toString());
                form.numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility = AppUtil.getLongValue(numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility.getText().toString());
                form.numberOfClientsWhoDefaultedART = AppUtil.getLongValue(numberOfClientsWhoDefaultedART.getText().toString());
                form.numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome = AppUtil.getLongValue(numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome.getText().toString());
                form.numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers = AppUtil.getLongValue(numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers.getText().toString());
                form.numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare = AppUtil.getLongValue(numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare.getText().toString());
                form.numberOfVillageHealthWorkersWorkingWithITECH = AppUtil.getLongValue(numberOfVillageHealthWorkersWorkingWithITECH.getText().toString());
                form.areYouImplementingDefaulterTracking = get(areYouImplementingDefaulterTracking);
                form.areYouImplementingIndexTesting = get(areYouImplementingIndexTesting);
                form.areYouImplementingRetestPriorToARTInitiation = get(areYouImplementingRetestPriorToARTInitiation);
                form.doesFacilityHaveStaticHTSHRH = get(doesFacilityHaveStaticHTSHRH);
                form.doesFacilityHaveStaticTXNEWHRH = get(doesFacilityHaveStaticTXNEWHRH);
                form.doesFacilityProvideMultiMonthDrugDispensing = get(doesFacilityProvideMultiMonthDrugDispensing);
                form.doesFacilityHaveFunctionalHealthCentreCommittee = get(doesFacilityHaveFunctionalHealthCentreCommittee);
                form.doesFacilityHaveFunctionalQualityImprovementCommittee = get(doesFacilityHaveFunctionalQualityImprovementCommittee);
                form.doesFacilityHaveQualityImprovementProject = get(doesFacilityHaveQualityImprovementProject);


                form.dateCreated = AppUtil.getDate(dateCreated.getText().toString());
                form.save();
                btn_submit.setVisibility(View.VISIBLE);
                AppUtil.createShortNotification(SupplementaryIndicatorFormActivity.this, "Saved");

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
                                form.dateSubmitted = new Date();
                                form.save();
                                AppUtil.createLongNotification(SupplementaryIndicatorFormActivity.this, "Submitted for Upload to Server");
                                Intent intent = new Intent(SupplementaryIndicatorFormActivity.this, SupplementaryIndicatorFormListActivity.class);
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

        if (v.getId() == dateCLFsDeployed.getId()) {
            datePickerDialog1.show();

        }

        if (v.getId() == dateCATSDeployed.getId()) {
            datePickerDialog2.show();

        }

        if (v.getId() == dateDefaulterTrackingImplemented.getId()) {
            datePickerDialog3.show();

        }

        if (v.getId() == dateIndexTestingImplemented.getId()) {
            datePickerDialog4.show();

        }

        if (v.getId() == dateRetestPriorToARTInitiationImplemented.getId()) {
            datePickerDialog5.show();

        }

        if (v.getId() == dateStaticHTSHRSDeployed.getId()) {
            datePickerDialog6.show();

        }

        if (v.getId() == dateStaticTXNEWHRHDeployed.getId()) {
            datePickerDialog7.show();

        }

        if (v.getId() == dateMultiMonthDrugDispensingStarted.getId()) {
            datePickerDialog8.show();

        }
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

                        Intent intent = new Intent(SupplementaryIndicatorFormActivity.this, SupplementaryIndicatorFormListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private YesNo get(HListView view){
        YesNo item = null;
        for(int i = 0; i < adapter.getCount(); i++){
            if(view.isItemChecked(i))
                item = adapter.getItem(i);
        }
        return item;
    }

}

