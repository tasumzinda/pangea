package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.CaseFileAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.Challenge;
import zw.co.ncmp.business.ChallengeStatus;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.business.Mentee;
import zw.co.ncmp.util.AppUtil;

public class FacilityViewActivity extends MenuBar implements View.OnClickListener {

    TextView txt_facility_name;
    private Facility facility = null;
    private ListView case_file_list_View;
    Button btn_add_case_file;
    Button btn_view_mentees;
    Button btn_load;
    Button btn_view_case_files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        facility = Facility.get(id);

        setContentView(R.layout.facility_view_activity);
        txt_facility_name = (TextView) findViewById(R.id.txt_name);
        txt_facility_name.setText(facility.name);
        txt_facility_name.setOnClickListener(this);

        case_file_list_View = (ListView) findViewById(R.id.case_file_list_View);
        CaseFileAdapter caseFileAdapter = (new CaseFileAdapter(this, new ArrayList<>(CaseFile.getOpenCaseFiles(facility.getId()))));

        case_file_list_View.setAdapter(caseFileAdapter);
        case_file_list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CaseFile item = (CaseFile) parent.getAdapter().getItem(position);
                Intent intent = new Intent(FacilityViewActivity.this, CaseFileViewActivity.class);
                intent.putExtra(AppUtil.CASE_ID, item.getId());
                startActivity(intent);
                finish();
            }
        });

        btn_add_case_file = (Button) findViewById(R.id.btn_add_case_file);
        btn_add_case_file.setOnClickListener(this);

        btn_view_case_files = (Button) findViewById(R.id.btn_view_case_files);
        btn_view_case_files.setOnClickListener(this);
        btn_view_case_files.setText("SITE SUPPORT REPORTS (" + CaseFile.getTotalCaseFilesForFacility(facility.getId()) + ")");

        if (CaseFile.caseFileOpen(facility.getId())) {
            btn_add_case_file.setVisibility(View.GONE);
        }

        btn_view_mentees = (Button) findViewById(R.id.btn_view_mentees);
        btn_view_mentees.setOnClickListener(this);
        btn_view_mentees.setText("Facility Staff (" + Mentee.getMenteeCount(facility.getId()) + ")");


        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        ChallengeStatus challengeStatus = ChallengeStatus.getPendingChallengeStatus();
        if(challengeStatus!=null) {
            btn_load.setText("FACILITY CHALLENGES (" + FacilityChallenge.getCount(facility.getId(), challengeStatus.getId()) + ")");
        }else{
            btn_load.setText("FACILITY CHALLENGES (0))");
        }
        setSupportActionBar(createToolBar("View : " + facility.name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == txt_facility_name.getId()) {
            intent = new Intent(this, FacilityActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }

        if (v.getId() == btn_add_case_file.getId()) {
            intent = new Intent(this, CaseFileActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }

        if (v.getId() == btn_view_mentees.getId()) {
            intent = new Intent(this, MenteeListActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }

        if (v.getId() == btn_load.getId()) {
            intent = new Intent(this, FacilityChallengeListActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }

        if (v.getId() == btn_view_case_files.getId()) {
            intent = new Intent(this, CaseFileListActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item_down = menu.findItem(R.id.action_refresh);
        item_down.setVisible(true);
        return true;
    }

}
