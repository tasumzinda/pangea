package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.CaseFileMentee;
import zw.co.ncmp.business.CaseFileMentor;
import zw.co.ncmp.business.Mentee;
import zw.co.ncmp.util.AppUtil;

public class CaseMenteeListActivity extends MenuBar implements View.OnClickListener {

    CaseFile caseFile;
    ListView listView;
    Button btn_save;
    Button btn_load;
    ArrayAdapter<Mentee> menteeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_list_activity);
        listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));

        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        caseFile = CaseFile.get(case_file_id);

        menteeAdapter
                = new ArrayAdapter<Mentee>(this,
                android.R.layout.simple_list_item_multiple_choice, Mentee.getMentees(caseFile.facility.getId()));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(menteeAdapter);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        btn_load.setText("ADD NEW FACILITY STAFF");

        setSupportActionBar(createToolBar("Select Facility Staff"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View v) {

        Intent intent = null;
        CaseFileMentee.deleteAll(caseFile.getId());

        if (v.getId() == btn_save.getId()) {
            SparseBooleanArray checked = listView.getCheckedItemPositions();
            ArrayList<Mentee> selectedItems = new ArrayList<Mentee>();
            for (int i = 0; i < checked.size(); i++) {
                // Item position in adapter
                int position = checked.keyAt(i);
                // Add sport if it is checked i.e.) == TRUE!
                if (checked.valueAt(i))
                    selectedItems.add(menteeAdapter.getItem(position));
            }

            long[] outputStrArr = new long[selectedItems.size()];

            for (int i = 0; i < selectedItems.size(); i++) {
                outputStrArr[i] = selectedItems.get(i).getId();
                CaseFileMentee caseFileMentee = new CaseFileMentee();
                CaseFileMentee fileMentee = CaseFileMentee.get(caseFile.getId(), selectedItems.get(i).getId());
                if (fileMentee == null) {
                    caseFileMentee.caseFile = caseFile;
                    caseFileMentee.mentee = selectedItems.get(i);
                    caseFileMentee.save();
                }
            }

            intent = new Intent(CaseMenteeListActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
            startActivity(intent);
            finish();
        }

        if (v.getId() == btn_load.getId()) {
            intent = new Intent(CaseMenteeListActivity.this, MenteeActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CaseMenteeListActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }

}
