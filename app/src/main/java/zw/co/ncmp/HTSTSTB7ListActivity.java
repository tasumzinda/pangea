package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import zw.co.ncmp.adpater.HTSTSTB6Adapter;
import zw.co.ncmp.adpater.HTSTSTB7Adapter;
import zw.co.ncmp.business.DSDIndividual;
import zw.co.ncmp.business.HTSTSTB6;
import zw.co.ncmp.business.HTSTSTB7;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;

/**
 * @uthor Tasu Muzinda
 */
public class HTSTSTB7ListActivity extends MenuBar implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_view);
        HTSTSTB7Adapter adapter = (new HTSTSTB7Adapter(this, new ArrayList<>(HTSTSTB7.getAll())));
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setSupportActionBar(createToolBar("HTS_TST B7"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HTSTSTB7ListActivity.this, HTSTSTB7Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DSDIndividual item = (DSDIndividual) parent.getAdapter().getItem(position);
        Intent intent = new Intent(HTSTSTB7ListActivity.this, HTSTSTB7Activity.class);
        intent.putExtra(AppUtil.ID, item.getId());
        startActivity(intent);
        finish();
    }
}
