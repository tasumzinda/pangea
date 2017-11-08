package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import zw.co.ncmp.adpater.PMTCTEIDFormAdapter;
import zw.co.ncmp.adpater.PMTCTStatAdapter;
import zw.co.ncmp.business.PMTCTEIDForm;
import zw.co.ncmp.business.PMTCTStat;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;

/**
 * Created by User on 3/18/2017.
 */
public class PMTCTEIDFormListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_view);
        PMTCTEIDFormAdapter adapter = (new PMTCTEIDFormAdapter(this, new ArrayList<PMTCTEIDForm>(PMTCTEIDForm.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("Eid Reported"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PMTCTEIDFormListActivity.this, PMTCTEIDFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PMTCTEIDForm item = (PMTCTEIDForm) parent.getAdapter().getItem(position);
        Intent intent = new Intent(PMTCTEIDFormListActivity.this, PMTCTEIDFormActivity.class);
        intent.putExtra(AppUtil.ID, item.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item_down = menu.findItem(R.id.action_refresh);
        item_down.setVisible(false);
        return true;
    }
}
