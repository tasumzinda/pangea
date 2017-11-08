package zw.co.ncmp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.business.MentorShipFocusArea;
import zw.co.ncmp.business.MentorVisitReport;
import zw.co.ncmp.business.VisitReportFocusArea;
import zw.co.ncmp.util.AppUtil;

public class VisitReportActivity extends MenuBar implements View.OnClickListener {

    TextView txt_caseFile_name;
    EditText hours;
    EditText minutes;
    EditText comments;

    TextView txt_other;
    EditText others;
    EditText action_taken;
    EditText observation;
    EditText recommendations;

    Button btn_save;
    Button btn_load;
    CaseFile caseFile;
    private MentorVisitReport mentorVisitReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit_report_activity);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(AppUtil.ID, 0);
        Long caseFile_id = intent.getLongExtra(AppUtil.CASE_ID, 0);

        txt_caseFile_name = (TextView) findViewById(R.id.txt_name);

        hours = (EditText) findViewById(R.id.hours);
        minutes = (EditText) findViewById(R.id.minutes);
        comments = (EditText) findViewById(R.id.comments);
        txt_other = (TextView) findViewById(R.id.txt_other);
        others = (EditText) findViewById(R.id.others);
        action_taken = (EditText) findViewById(R.id.action_taken);
        recommendations = (EditText) findViewById(R.id.recommendations);
        observation = (EditText) findViewById(R.id.observation);

        others.setVisibility(View.GONE);
        txt_other.setVisibility(View.GONE);

        if (id != 0) {
            mentorVisitReport = MentorVisitReport.get(id);
            mentorVisitReport.focusAreas = VisitReportFocusArea.getMentorShipFocusAreas(mentorVisitReport.getId());

            caseFile = CaseFile.get(mentorVisitReport.caseFile.getId());
            hours.setText(String.valueOf(mentorVisitReport.hours));
            minutes.setText(String.valueOf(mentorVisitReport.minutes));
            comments.setText(mentorVisitReport.comments);
            others.setText(mentorVisitReport.others);
            action_taken.setText(mentorVisitReport.action_taken);
            recommendations.setText(mentorVisitReport.recommendations);
            observation.setText(mentorVisitReport.observation);

            for (MentorShipFocusArea s : mentorVisitReport.focusAreas) {
                if (s.name == "Other") {
                    others.setVisibility(View.VISIBLE);
                    txt_other.setVisibility(View.VISIBLE);
                    break;
                }
            }

            txt_caseFile_name.setText("SITE SUPPORT REPORT : " + AppUtil.getStringDate(caseFile.dateCreated) + " - " + caseFile.facility.name);
            setSupportActionBar(createToolBar("Update Activity"));
        } else {
            caseFile = CaseFile.get(caseFile_id);
            mentorVisitReport = new MentorVisitReport();
            mentorVisitReport.caseFile = caseFile;
            txt_caseFile_name.setText("SITE SUPPORT REPORT : " + AppUtil.getStringDate(caseFile.dateCreated) + " - " + caseFile.facility.name);
            setSupportActionBar(createToolBar("Add New Activity"));
        }

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(this);

        btn_load.setText("Focus Areas (" + mentorVisitReport.focusAreas + ")");

        if (caseFile.dateSubmitted != null) {
            btn_save.setVisibility(View.GONE);
        } else {
            btn_save.setVisibility(View.VISIBLE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_save.getId()) {
            if (validate()) {
                mentorVisitReport.hours = AppUtil.getInputValue(hours.getText().toString());
                mentorVisitReport.minutes = AppUtil.getInputValue(minutes.getText().toString());
                mentorVisitReport.comments = comments.getText().toString();
                mentorVisitReport.others = others.getText().toString();
                mentorVisitReport.action_taken = action_taken.getText().toString();
                mentorVisitReport.recommendations = recommendations.getText().toString();
                mentorVisitReport.observation = observation.getText().toString();
                mentorVisitReport.save();

                if (mentorVisitReport.getId() != null) {
                    VisitReportFocusArea.deleteAll(mentorVisitReport.getId());
                }

                for (MentorShipFocusArea s : mentorVisitReport.focusAreas) {
                    VisitReportFocusArea focusArea = new VisitReportFocusArea();
                    focusArea.mentorShipFocusArea = s;
                    focusArea.mentorVisitReport = mentorVisitReport;
                    focusArea.save();
                }

                Intent intent = new Intent(this, CaseFileViewActivity.class);
                intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
                startActivity(intent);
                finish();
                return;
            } else {
                return;
            }
        }

        if (v.getId() == btn_load.getId()) {
            selectFocusAreas();
        }
    }

    public boolean validate() {
        boolean valid = true;

        String name = comments.toString().toString();

        if (name.isEmpty()) {
            comments.setError("Required");
            valid = false;
        } else {
            comments.setError(null);
        }
        return valid;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VisitReportActivity.this, CaseFileViewActivity.class);
        intent.putExtra(AppUtil.CASE_ID, caseFile.getId());
        startActivity(intent);
        finish();
    }

    public void selectFocusAreas() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.select_list_activity);
        dialog.setTitle("Select Focus Areas");

        final ListView focusAreas = (ListView) dialog.findViewById(R.id.list);
        focusAreas.setEmptyView(findViewById(R.id.empty));
        final ArrayAdapter<MentorShipFocusArea> mentorShipFocusAreaArrayAdapter
                = new ArrayAdapter<MentorShipFocusArea>(this,
                android.R.layout.simple_list_item_multiple_choice, MentorShipFocusArea.getAll());
        focusAreas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        focusAreas.setAdapter(mentorShipFocusAreaArrayAdapter);

        Button button = (Button) dialog.findViewById(R.id.btn_save);
        button.setVisibility(View.GONE);

        Button btn_select = (Button) dialog.findViewById(R.id.btn_load);
        btn_select.setOnClickListener(this);
        btn_select.setText("Load");

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SparseBooleanArray checked = focusAreas.getCheckedItemPositions();
                ArrayList<MentorShipFocusArea> selectedItems = new ArrayList<MentorShipFocusArea>();
                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);
                    // Add sport if it is checked i.e.) == TRUE!
                    if (checked.valueAt(i))
                        selectedItems.add(mentorShipFocusAreaArrayAdapter.getItem(position));
                }

                long[] outputStrArr = new long[selectedItems.size()];

                for (int i = 0; i < selectedItems.size(); i++) {
                    outputStrArr[i] = selectedItems.get(i).getId();
                    mentorVisitReport.focusAreas.add(selectedItems.get(i));
                }
                btn_load.setText("Focus Areas (" + mentorVisitReport.focusAreas + ")");

                for (MentorShipFocusArea s : mentorVisitReport.focusAreas) {
                    if (s.name == "Other") {
                        others.setVisibility(View.VISIBLE);
                        txt_other.setVisibility(View.VISIBLE);
                        break;
                    }
                }

                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.show();

    }
}
