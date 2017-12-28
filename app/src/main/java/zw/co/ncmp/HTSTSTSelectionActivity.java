package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @uthor Tasu Muzinda
 */
public class HTSTSTSelectionActivity extends MenuBar implements View.OnClickListener{

    Button btn_option_one;
    Button btn_option_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htstst_selection);

        btn_option_one = (Button) findViewById(R.id.btn_option_one);
        btn_option_one.setOnClickListener(this);
        btn_option_one.setBackgroundResource(R.drawable.finish_background);
        btn_option_one.setText("HTS_TST B6");

        btn_option_two = (Button) findViewById(R.id.btn_option_two);
        btn_option_two.setOnClickListener(this);
        btn_option_two.setBackgroundResource(R.drawable.finish_background);
        btn_option_two.setText("HTS_TST B7");

        setSupportActionBar(createToolBar("HTS_TST Selection"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == btn_option_one.getId()) {
            intent = new Intent(this, HTSTSTB6ListActivity.class);
        }

        if (v.getId() == btn_option_two.getId()) {
            intent = new Intent(this, HTSTSTB7ListActivity.class);
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
