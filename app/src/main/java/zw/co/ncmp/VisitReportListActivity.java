package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.VisitReportAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.MentorVisitReport;
import zw.co.ncmp.util.AppUtil;

public class VisitReportListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    private CaseFile caseFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);
        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        caseFile = CaseFile.get(case_file_id);
        VisitReportAdapter visitReportAdapter = (new VisitReportAdapter(this, new ArrayList<>(MentorVisitReport.getVisitsByCaseFile(caseFile.getId()))));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(visitReportAdapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("Activites - " + AppUtil.getStringDate(caseFile.dateCreated)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MentorVisitReport item = (MentorVisitReport) parent.getAdapter().getItem(position);
        Intent intent = new Intent(VisitReportListActivity.this, VisitReportActivity.class);
        intent.putExtra(AppUtil.ID, item.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VisitReportListActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }
}
