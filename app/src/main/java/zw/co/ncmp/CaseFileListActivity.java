package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.CaseFileAdapter;
import zw.co.ncmp.adpater.FacilityAdapter;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.util.AppUtil;

public class CaseFileListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    Facility facility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        facility = Facility.get(id);

        CaseFileAdapter caseFileAdapter = (new CaseFileAdapter(this, new ArrayList<CaseFile>(CaseFile.getCaseFiles(facility.getId()))));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(caseFileAdapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("Site Support Reports - " + facility));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CaseFile caseFile = (CaseFile) parent.getAdapter().getItem(position);
        Intent intent = new Intent(CaseFileListActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent intent = null;
        intent = new Intent(CaseFileListActivity.this, FacilityViewActivity.class);
        intent.putExtra(AppUtil.ID, facility.getId());
        startActivity(intent);
        finish();
    }
}
