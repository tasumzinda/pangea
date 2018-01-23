package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import zw.co.ncmp.adpater.Esta3TxNewAdapter;
import zw.co.ncmp.adpater.HTSEligibilityScreeningFormAdapter;
import zw.co.ncmp.business.Esta3TxNew;
import zw.co.ncmp.business.HTSEligibilityScreeningForm;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;

/**
 * @uthor Tasu Muzinda
 */
public class HTSEligibilityScreeningFormListActivity extends MenuBar implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_view);
        HTSEligibilityScreeningFormAdapter adapter = (new HTSEligibilityScreeningFormAdapter(this, new ArrayList<>(HTSEligibilityScreeningForm.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("HTS Eligibility Screening Form List"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HTSEligibilityScreeningFormListActivity.this, HTSEligibilityScreeningFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HTSEligibilityScreeningForm item = (HTSEligibilityScreeningForm) parent.getAdapter().getItem(position);
        Intent intent = new Intent(HTSEligibilityScreeningFormListActivity.this, HTSEligibilityScreeningFormActivity.class);
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
