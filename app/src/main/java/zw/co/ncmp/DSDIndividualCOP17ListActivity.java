package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import zw.co.ncmp.adpater.DSDIndividualAdapter;
import zw.co.ncmp.adpater.DSDIndividualCOP17Adapter;
import zw.co.ncmp.business.DSDIndividual;
import zw.co.ncmp.business.DSDIndividualCOP17;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;

/**
 * Created by User on 3/22/2017.
 */
public class DSDIndividualCOP17ListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_view);
        DSDIndividualCOP17Adapter adapter = (new DSDIndividualCOP17Adapter(this, new ArrayList<DSDIndividualCOP17>(DSDIndividualCOP17.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("FACILITY-BASED DSD"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DSDIndividualCOP17ListActivity.this, DSDIndividualCOP17Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DSDIndividualCOP17 item = (DSDIndividualCOP17) parent.getAdapter().getItem(position);
        Intent intent = new Intent(DSDIndividualCOP17ListActivity.this, DSDIndividualCOP17Activity.class);
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
