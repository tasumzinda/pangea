package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DSDSelectionActivity extends MenuBar implements View.OnClickListener {

    Button btn_option_one;
    Button btn_option_three;
    Button btn_option_two;
    Button btn_option_four;
    Button btn_option_five;
    Button btn_option_six;
    Button btn_option_seven;
    Button btn_option_eight;
    Button btn_option_nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_activity);

        btn_option_one = (Button) findViewById(R.id.btn_option_one);
        btn_option_one.setOnClickListener(this);
        btn_option_one.setBackgroundResource(R.drawable.finish_background);
        btn_option_one.setText("COP 16");

        btn_option_two = (Button) findViewById(R.id.btn_option_two);
        btn_option_two.setOnClickListener(this);
        btn_option_two.setBackgroundResource(R.drawable.finish_background);
        btn_option_two.setText("COP 17");

        btn_option_three = (Button) findViewById(R.id.btn_option_three);
        btn_option_three.setOnClickListener(this);
        btn_option_three.setBackgroundResource(R.drawable.finish_background);
        btn_option_three.setText("Flow Page");
        btn_option_three.setVisibility(View.GONE);

        setSupportActionBar(createToolBar("DSD Selection"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == btn_option_one.getId()) {
            intent = new Intent(this, COP16SelectionActivity.class);
        }

        if (v.getId() == btn_option_two.getId()) {
            intent = new Intent(this, COP17SelectionActivity.class);
        }

        if (v.getId() == btn_option_three.getId()) {
            intent = new Intent(DSDSelectionActivity.this, DSDFlowPager.class);
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
