package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.MenteeAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.CaseFileMentee;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Mentee;
import zw.co.ncmp.util.AppUtil;

public class MenteeListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    Facility facility;
    CaseFile caseFile;
    MenteeAdapter menteeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);
        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        if (id !=0) {
            facility = Facility.get(id);
            menteeAdapter = (new MenteeAdapter(this, new ArrayList<Mentee>(Mentee.getMentees(facility.getId()))));
            setSupportActionBar(createToolBar(facility + " - Facility Staff"));
            listView.setOnItemClickListener(this);
        } else if (case_file_id != 0) {
            caseFile = CaseFile.get(case_file_id);
            menteeAdapter = (new MenteeAdapter(this, new ArrayList<Mentee>(CaseFileMentee.getMentees(caseFile.getId()))));
            setSupportActionBar(createToolBar("SITE SUPPORT - Facility Staff : " + AppUtil.getStringDate(caseFile.dateCreated) + " " + caseFile.facility));
        } else {
            finish();
        }
        listView.setAdapter(menteeAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Mentee item = (Mentee) parent.getAdapter().getItem(position);
        Intent intent = new Intent(MenteeListActivity.this, MenteeActivity.class);
        intent.putExtra(AppUtil.MENTEE_ID, item.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;
        if (facility != null) {
            intent = new Intent(MenteeListActivity.this, FacilityViewActivity.class);
            intent.putExtra(AppUtil.ID, facility.getId());
        }else if(caseFile != null) {
            intent = new Intent(MenteeListActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }else{
            finish();
        }
        startActivity(intent);
        finish();
    }
}
