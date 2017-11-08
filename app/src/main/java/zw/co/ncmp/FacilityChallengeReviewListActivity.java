package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zw.co.ncmp.adpater.FacilityChallengeAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.ChallengeStatus;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.util.AppUtil;

public class FacilityChallengeReviewListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    CaseFile caseFile;
    FacilityChallengeAdapter facilityChallengeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);

        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        caseFile = CaseFile.get(case_file_id);

        ChallengeStatus challengeStatus = ChallengeStatus.getPendingChallengeStatus();
        List<FacilityChallenge> list = new ArrayList<>();
        if (challengeStatus != null) {
            list.addAll(FacilityChallenge.getChallenges(caseFile.facility.getId(), challengeStatus.getId()));
            List<FacilityChallenge> facilityChallengeList = new ArrayList<>(FacilityChallenge.getChallengesByCaseFile(caseFile.getId()));
            list.removeAll(facilityChallengeList);
            for (FacilityChallenge facilityChallenge : facilityChallengeList) {
                if (facilityChallenge.previousChallenge != null && facilityChallenge.previousChallenge.serverId != null) {
                    list.remove(FacilityChallenge.getFacilityChallenge(facilityChallenge.previousChallenge.serverId));
                }
            }
        }

        facilityChallengeAdapter = (new FacilityChallengeAdapter(this, new ArrayList<>(list)));
        setSupportActionBar(createToolBar("Review Facility Challenges"));

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(facilityChallengeAdapter);
        listView.setOnItemClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FacilityChallenge item = (FacilityChallenge) parent.getAdapter().getItem(position);
        Intent intent = new Intent(FacilityChallengeReviewListActivity.this, FacilityChallengeReviewActivity.class);
        intent.putExtra(AppUtil.ID, item.getId());
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;
        intent = new Intent(FacilityChallengeReviewListActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }
}
