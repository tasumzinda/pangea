package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import zw.co.ncmp.adpater.CommunityBasedDSDAdapter;
import zw.co.ncmp.adpater.PMTCTStatAdapter;
import zw.co.ncmp.business.CommunityBasedDSD;
import zw.co.ncmp.business.PMTCTStat;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;

/**
 * Created by User on 3/21/2017.
 */
public class CommunityBasedDSDListActivity extends MenuBar implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_view);
        CommunityBasedDSDAdapter adapter = (new CommunityBasedDSDAdapter(this, new ArrayList<CommunityBasedDSD>(CommunityBasedDSD.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("Community Based DSD HIV Testing Reported"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommunityBasedDSDListActivity.this, CommunityBasedDSDActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CommunityBasedDSD item = (CommunityBasedDSD) parent.getAdapter().getItem(position);
        Intent intent = new Intent(CommunityBasedDSDListActivity.this, CommunityBasedDSDActivity.class);
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
