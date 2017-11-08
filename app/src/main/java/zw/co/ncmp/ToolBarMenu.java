package zw.co.ncmp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by tdhlakama on 3/31/15.
 */
public class ToolBarMenu extends AppCompatActivity {

    public Context context = this;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.action_infor) {
            Dialog dialog = new Dialog(this);
            dialog.setTitle("App Information");
            dialog.setContentView(R.layout.item);

            TextView txt_name = (TextView)dialog.findViewById(R.id.txt_name);
            txt_name.setText("NCMP MOBILE");

            TextView txt_more = (TextView)dialog.findViewById(R.id.txt_more);
            txt_more.setText("App Version 1.0");

            dialog.show();
            return true;
        }

        if (id == R.id.action_refresh) {
            intent = new Intent(context, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Toolbar createToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        return toolbar;
    }

    public void updateTitle(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }



}
