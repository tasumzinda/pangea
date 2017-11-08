package zw.co.ncmp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import zw.co.ncmp.adpater.FacilityAdapter;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.Mentor;
import zw.co.ncmp.util.AppUtil;

public class MainActivity extends MenuBar implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView facility_list_view;
    private TextView txt_user_name;
    private TextView txt_mentor;
    private Mentor mentor;
    private Context context = this;
    FacilityAdapter facilityAdapterAdapter;
    Button btn_option_one;
    Button btn_option_two;
    Button btn_option_three;
    LinearLayout tbl_national;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WVersionManager versionManager = new WVersionManager(this);
        versionManager.setVersionContentUrl("http://update.pzat.org/update/version.txt"); // your update content url, see the response format below
        versionManager.setUpdateUrl("http://update.pzat.org/update/app-release.apk");
        versionManager.setIgnoreThisVersionLabel(" ");
        versionManager.setRemindMeLaterLabel("");
        versionManager.checkVersion();
        if (savedInstanceState == null) {
            syncAppData();
        }

        setContentView(R.layout.activity_main);

        mentor = Mentor.getMentor(AppUtil.getWebUserId(context));

        setSupportActionBar(createToolBar("Facilities"));

        tbl_national = (LinearLayout) findViewById(R.id.tbl_national);
        tbl_national.setVisibility(View.GONE);

        if ( ! AppUtil.getUserRole(context).equals("MENTOR")) {
            tbl_national.setVisibility(View.VISIBLE);
        }

        btn_option_one = (Button) findViewById(R.id.btn_option_one);
        btn_option_one.setOnClickListener(this);
        btn_option_one.setBackgroundResource(R.drawable.finish_background);

        btn_option_two = (Button) findViewById(R.id.btn_option_two);
        btn_option_two.setOnClickListener(this);
        btn_option_two.setBackgroundResource(R.drawable.finish_background);

        btn_option_three = (Button) findViewById(R.id.btn_option_three);
        btn_option_three.setOnClickListener(this);
        btn_option_three.setBackgroundResource(R.drawable.finish_background);

        facility_list_view = (ListView) findViewById(R.id.facility_list_view);
        facility_list_view.setEmptyView(findViewById(R.id.empty));

        facilityAdapterAdapter = (new FacilityAdapter(this, new ArrayList<>(Facility.getAll(mentor.getId()))));
        facility_list_view.setAdapter(facilityAdapterAdapter);
        facility_list_view.setOnItemClickListener(this);

        txt_mentor = (TextView) findViewById(R.id.txt_more);
        txt_mentor.setText("Member ID: " + AppUtil.getWebUserId(context) + " - Facilities(" + Facility.getCount() + ")");

        txt_user_name = (TextView) findViewById(R.id.txt_name);
        txt_user_name.setText(AppUtil.getUsername(MainActivity.this).toLowerCase());
        txt_user_name.setOnClickListener(this);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            AppUtil.createShortNotification(MainActivity.this, "Enable GPS Location for App to continue");
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == txt_user_name.getId()) {
            intent = new Intent(context, MentorActivity.class);
        }

        if (v.getId() == btn_option_one.getId()) {
            intent = new Intent(this, TBSelectionActivity.class);
        }

        if (v.getId() == btn_option_two.getId()) {
            intent = new Intent(this, DSDSelectionActivity.class);
        }

        if (v.getId() == btn_option_three.getId()) {
            intent = new Intent(this, ODSVSelectionActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Facility facility = (Facility) parent.getAdapter().getItem(position);
        Intent intent = new Intent(MainActivity.this, FacilityViewActivity.class);
        intent.putExtra(AppUtil.ID, facility.getId());
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(context)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void updateView() {
        facilityAdapterAdapter.clear();
        facilityAdapterAdapter.addAll(Facility.getAll(mentor.getId()));
        facilityAdapterAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item_down = menu.findItem(R.id.action_home);
        item_down.setVisible(false);
        return true;
    }


}
