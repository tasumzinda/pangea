package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @uthor Tasu Muzinda
 */
public class EstaSelectionActivity extends MenuBar implements View.OnClickListener{

    Button btn_option_one;
    Button btn_option_three;
    Button btn_option_two;
    Button btn_option_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.esta_selection_activity);

        btn_option_one = (Button) findViewById(R.id.btn_option_one);
        btn_option_one.setOnClickListener(this);
        btn_option_one.setBackgroundResource(R.drawable.finish_background);
        btn_option_one.setText("ESTA3");

        btn_option_two = (Button) findViewById(R.id.btn_option_two);
        btn_option_two.setOnClickListener(this);
        btn_option_two.setBackgroundResource(R.drawable.finish_background);
        btn_option_two.setText("ESTA4");

        btn_option_three = (Button) findViewById(R.id.btn_option_three);
        btn_option_three.setOnClickListener(this);
        btn_option_three.setBackgroundResource(R.drawable.finish_background);
        btn_option_three.setText("ESTA5");


        btn_option_four = (Button) findViewById(R.id.btn_option_four);
        btn_option_four.setOnClickListener(this);
        btn_option_four.setBackgroundResource(R.drawable.finish_background);
        btn_option_four.setText("HTS_TST");

        setSupportActionBar(createToolBar("ESTA Selection"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == btn_option_one.getId()) {
            intent = new Intent(this, Esta3SelectionActivity.class);
        }

        if (v.getId() == btn_option_two.getId()) {
            intent = new Intent(this, IndexCaseTestingFormActivity.class);
        }

        if(v.getId() == btn_option_three.getId()){
            intent = new Intent(this, DefaulterTrackingFormActivity.class);
        }
        if(v.getId() == btn_option_four.getId()){
            intent = new Intent(this, HTSTSTSelectionActivity.class);
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
