package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.CaseFileMentor;
import zw.co.ncmp.business.Mentor;
import zw.co.ncmp.util.AppUtil;

public class CaseMentorListActivity extends MenuBar implements View.OnClickListener {

    CaseFile caseFile;
    ListView listView;
    Button btn_save;
    Button btn_load;
    ArrayAdapter<Mentor> mentorAdapter;
    Mentor mentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_list_activity);
        listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        mentor = Mentor.getMentor(AppUtil.getWebUserId(CaseMentorListActivity.this));
        List<Mentor> mentorList = Mentor.getAll();
        mentorList.remove(mentor);

        mentorAdapter
                = new ArrayAdapter<Mentor>(this,
                android.R.layout.simple_list_item_multiple_choice, mentorList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(mentorAdapter);

        Intent intent = getIntent();
        Long case_file_id = intent.getLongExtra(AppUtil.CASE_ID, 0);
        caseFile = CaseFile.get(case_file_id);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);
        btn_load.setText("ADD ALL");
        btn_load.setVisibility(View.GONE);

        setSupportActionBar(createToolBar("Select Team Members"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View v) {

        Intent intent = null;

        if (v.getId() == btn_save.getId()) {
            CaseFileMentor.deleteAll(caseFile.getId());

            CaseFileMentor caseFileMentor = new CaseFileMentor();
            caseFileMentor.caseFile = caseFile;
            caseFileMentor.mentor = mentor;
            caseFileMentor.save();

            SparseBooleanArray checked = listView.getCheckedItemPositions();
            ArrayList<Mentor> selectedItems = new ArrayList<Mentor>();
            for (int i = 0; i < checked.size(); i++) {
                // Item position in adapter
                int position = checked.keyAt(i);
                // Add sport if it is checked i.e.) == TRUE!
                if (checked.valueAt(i))
                    selectedItems.add(mentorAdapter.getItem(position));
            }


            long[] outputStrArr = new long[selectedItems.size()];

            for (int i = 0; i < selectedItems.size(); i++) {
                outputStrArr[i] = selectedItems.get(i).getId();
                caseFileMentor = new CaseFileMentor();
                CaseFileMentor fileMentor = CaseFileMentor.get(caseFile.getId(), selectedItems.get(i).getId());
                if (fileMentor == null) {
                    caseFileMentor.caseFile = caseFile;
                    caseFileMentor.mentor = selectedItems.get(i);
                    caseFileMentor.save();
                }
            }

            intent = new Intent(CaseMentorListActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
            startActivity(intent);
            finish();
        }

        if (v.getId() == btn_load.getId()) {
            CaseFileMentor.deleteAll(caseFile.getId());
            for (Mentor mentor : Mentor.getAll()) {
                CaseFileMentor caseFileMentor = new CaseFileMentor();
                caseFileMentor.caseFile = caseFile;
                caseFileMentor.mentor = mentor;
                caseFileMentor.save();
            }
            intent = new Intent(CaseMentorListActivity.this, CaseFileViewActivity.class);
            intent.putExtra(AppUtil.CASE_ID, caseFile.getId());

        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CaseMentorListActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }

}
