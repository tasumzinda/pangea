package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.FacilityChallengeAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.ChallengeStatus;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.util.AppUtil;

public class FacilityChallengeListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    CaseFile caseFile;
    Facility facility;
    FacilityChallengeAdapter facilityChallengeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);

        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        if (case_file_id != 0) {
            caseFile = CaseFile.get(case_file_id);
        }
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        if (id != 0) {
            facility = Facility.get(id);
        }
        if (facility != null) {
            ChallengeStatus challengeStatus = ChallengeStatus.getPendingChallengeStatus();
            if (challengeStatus != null) {
                facilityChallengeAdapter = (new FacilityChallengeAdapter(this, new ArrayList<>(FacilityChallenge.getChallenges(facility.getId(), challengeStatus.getId()))));
                setSupportActionBar(createToolBar("Facility Challenges at " + facility));
            } else {
                facilityChallengeAdapter = (new FacilityChallengeAdapter(this, new ArrayList<FacilityChallenge>()));
                setSupportActionBar(createToolBar("Facility Challenges at " + facility));
            }
        }

        if (caseFile != null) {
            facilityChallengeAdapter = (new FacilityChallengeAdapter(this, new ArrayList<>(FacilityChallenge.getChallengesByCaseFile(caseFile.getId()))));
            setSupportActionBar(createToolBar("Facility Challenges on Case File - " + AppUtil.getStringDate(caseFile.dateCreated)));
        }

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(facilityChallengeAdapter);
        listView.setOnItemClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FacilityChallenge item = (FacilityChallenge) parent.getAdapter().getItem(position);
        Intent intent = new Intent(FacilityChallengeListActivity.this, FacilityChallengeActivity.class);
        intent.putExtra(AppUtil.ID, item.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;
        if (caseFile != null) {
            intent = new Intent(FacilityChallengeListActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (facility != null) {
            intent = new Intent(FacilityChallengeListActivity.this, FacilityViewActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }
        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }
}
