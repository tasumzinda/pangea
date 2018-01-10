package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import zw.co.ncmp.adpater.PMTCTEIDP36Adapter;
import zw.co.ncmp.adpater.PMTCTFOFormAdapter;
import zw.co.ncmp.business.PMTCTEIDP36;
import zw.co.ncmp.business.PMTCTFOForm;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;

/**
 * @uthor Tasu Muzinda
 */
public class PMTCTEIDP36ListActivity extends MenuBar implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_view);
        PMTCTEIDP36Adapter adapter = (new PMTCTEIDP36Adapter(this, new ArrayList<>(PMTCTEIDP36.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("PMTCT_EID"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PMTCTEIDP36ListActivity.this, PMTCTEIDP36Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PMTCTEIDP36 item = (PMTCTEIDP36) parent.getAdapter().getItem(position);
        Intent intent = new Intent(PMTCTEIDP36ListActivity.this, PMTCTEIDP36Activity.class);
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
