package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 3/21/2017.
 */
public class COP17SelectionActivity extends MenuBar implements View.OnClickListener {

    Button btn_option_one;
    Button btn_option_three;
    Button btn_option_two;
    Button btn_option_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cop17_selection_activity);

        btn_option_one = (Button) findViewById(R.id.btn_option_one);
        btn_option_one.setOnClickListener(this);
        btn_option_one.setBackgroundResource(R.drawable.finish_background);
        btn_option_one.setText("Community-Based DSD");

        btn_option_two = (Button) findViewById(R.id.btn_option_two);
        btn_option_two.setOnClickListener(this);
        btn_option_two.setBackgroundResource(R.drawable.finish_background);
        btn_option_two.setText("Facility Based DSD");

        btn_option_three = (Button) findViewById(R.id.btn_option_three);
        btn_option_three.setOnClickListener(this);
        btn_option_three.setBackgroundResource(R.drawable.finish_background);
        btn_option_three.setText("TX New");

        btn_option_four = (Button) findViewById(R.id.btn_option_four);
        btn_option_four.setOnClickListener(this);
        btn_option_four.setBackgroundResource(R.drawable.finish_background);
        btn_option_four.setText("HTS Eligibility Screening");

        setSupportActionBar(createToolBar("COP17 Selection"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == btn_option_one.getId()) {
            intent = new Intent(this, CommunityBasedDSDListActivity.class);
        }

        if (v.getId() == btn_option_two.getId()) {
            intent = new Intent(this, DSDIndividualCOP17ListActivity.class);
        }

        if (v.getId() == btn_option_three.getId()) {
            intent = new Intent(this, COP17TXNewListActivity.class);
        }

        if(v.getId() == btn_option_four.getId()){
            intent = new Intent(this, HTSEligibilityScreeningFormActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
