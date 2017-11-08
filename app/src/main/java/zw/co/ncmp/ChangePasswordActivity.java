package zw.co.ncmp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.renderscript.Long4;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import zw.co.ncmp.business.NCMP;
import zw.co.ncmp.network.AsyncTaskResultEvent;
import zw.co.ncmp.network.ChangePasswordWebService;
import zw.co.ncmp.network.LoginWebService;
import zw.co.ncmp.util.AppUtil;


public class ChangePasswordActivity extends MenuBar implements View.OnClickListener{

    Context context = this;
    ProgressDialog progressDialog;
    private String confirm_password, password;
    EditText server_password;
    EditText confirm_server_password;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.change_password);
        server_password = (EditText) findViewById(R.id.txt_more);
        confirm_server_password = (EditText) findViewById(R.id.txt_name);
        btn_submit = ((Button) findViewById(R.id.btn_submit));
        btn_submit.setOnClickListener(this);

        setSupportActionBar(createToolBar("Change Password"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == btn_submit.getId()){
            confirm_password = ((EditText) findViewById(R.id.txt_more)).getText()
                    .toString().trim();
            password = ((EditText) findViewById(R.id.txt_name)).getText()
                    .toString().trim();
            checkValidations();
        }
   }

    protected void checkValidations() {

        if (confirm_password.isEmpty()) {
            AppUtil.createShortNotification(context, "Please Confirm Password.");
            return;
        }

        if (password.isEmpty()) {
            AppUtil.createShortNotification(context, "Please new Enter password.");
            return;
        }

        if (!password.equals(confirm_password)) {
            AppUtil.createShortNotification(context, "Passwords do not match.");
            return;
        }

        if (AppUtil.isInternetPresent(context)) {
            changePasswordAsyncTask(password);
        } else {
            AppUtil.createShortNotification(this, "No Internet, Check Connectivity!");
        }

    }

    private void changePasswordAsyncTask(String password) {
        lockScreenOrientation();
        progressDialog = ProgressDialog.show(this, "Please wait", "Changing Password...", true);
        progressDialog.setCancelable(true);
        new ChangePasswordWebService(context, password).execute();
    }

    @Subscribe
    public void onAsyncTaskResult(AsyncTaskResultEvent event) {
        progressDialog.hide();
        unlockScreenOrientation();
        String msg = event.getResult();
        if (msg.equals("Password Changed Successfully")) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            AppUtil.createShortNotification(this, msg);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}


