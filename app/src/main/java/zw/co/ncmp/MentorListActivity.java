package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.MentorAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.CaseFileMentor;
import zw.co.ncmp.business.Mentor;
import zw.co.ncmp.util.AppUtil;

public class MentorListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    CaseFile caseFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);
        Intent intent = getIntent();
        MentorAdapter mentorAdapter;
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        caseFile = CaseFile.get(case_file_id);
        if (caseFile != null) {
            mentorAdapter = new MentorAdapter(this, new ArrayList<>(CaseFileMentor.getMentors(case_file_id)));
            setSupportActionBar(createToolBar(AppUtil.getStringDate(caseFile.dateCreated) + " - Team Members"));
        } else {
            mentorAdapter = new MentorAdapter(this, new ArrayList<>(Mentor.getAll()));
            listView.setOnItemClickListener(this);
            setSupportActionBar(createToolBar("Team Members"));
        }
        listView.setAdapter(mentorAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Mentor mentor = (Mentor) parent.getAdapter().getItem(position);
        Intent intent = new Intent(MentorListActivity.this, MentorActivity.class);
        intent.putExtra(AppUtil.MENTOR_ID, mentor.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;
       if(caseFile != null) {
            intent = new Intent(MentorListActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }else{
           intent = new Intent(MentorListActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
