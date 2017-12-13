package zw.co.ncmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by tasu
 */
public class Esta3SelectionActivity extends MenuBar implements View.OnClickListener{

    Button btn_option_one;
    Button btn_option_two;
    Button btn_option_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.esta3_selection);

        btn_option_one = (Button) findViewById(R.id.btn_option_one);
        btn_option_one.setOnClickListener(this);
        btn_option_one.setBackgroundResource(R.drawable.finish_background);
        btn_option_one.setText("HTS Register Form");

        btn_option_two = (Button) findViewById(R.id.btn_option_two);
        btn_option_two.setOnClickListener(this);
        btn_option_two.setBackgroundResource(R.drawable.finish_background);
        btn_option_two.setText("PreART Form");

        btn_option_three = (Button) findViewById(R.id.btn_option_three);
        btn_option_three.setOnClickListener(this);
        btn_option_three.setBackgroundResource(R.drawable.finish_background);
        btn_option_three.setText("Tx New");

        setSupportActionBar(createToolBar("ESTA3 Selection"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        if (v.getId() == btn_option_one.getId()) {
            intent = new Intent(this, HTSREgisterFormActivity.class);
        }

        if (v.getId() == btn_option_two.getId()) {
            intent = new Intent(this, PreARTFormActivity.class);
        }

        if(v.getId() == btn_option_three.getId()){
            intent = new Intent(this, Esta3TxNewActivity.class);
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
