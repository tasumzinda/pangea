package zw.co.ncmp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import zw.co.ncmp.adpater.FacilityAdapter;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.util.AppUtil;

public class FacilityListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list_view);
        FacilityAdapter facilityAdapterAdapter = (new FacilityAdapter(this, new ArrayList<Facility>(Facility.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(facilityAdapterAdapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("Facilities"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Facility facility = (Facility) parent.getAdapter().getItem(position);
        Intent intent = new Intent(FacilityListActivity.this, FacilityViewActivity.class);
        intent.putExtra(AppUtil.ID, facility.getId());
        startActivity(intent);
        finish();
    }
}
