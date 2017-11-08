package zw.co.ncmp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import zw.co.ncmp.business.NCMP;
import zw.co.ncmp.network.AsyncTaskResultEvent;
import zw.co.ncmp.network.LoginWebService;
import zw.co.ncmp.util.AppUtil;


public class LoginActivity extends Activity implements View.OnClickListener{

    Context context = this;
    ProgressDialog progressDialog;
    private boolean isTaskRunning = false;
    private LoginWebService asyncTask;
    private String username, password;
    EditText server_url;
    TextView server;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        if (AppUtil.isUserLoggedIn(LoginActivity.this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        server = (TextView) findViewById(R.id.txt_more);
        server.setText(AppUtil.getWebService(context));
        server.setOnClickListener(this);

        server_url = (EditText) findViewById(R.id.txt_name);
        server_url.setText(AppUtil.getWebService(context));

        login = ((Button) findViewById(R.id.login));
        login.setOnClickListener(this);
        login.setBackgroundResource(R.drawable.finish_background);

        server_url.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == login.getId()){
            username = ((EditText) findViewById(R.id.username)).getText()
                    .toString().trim();
            password = ((EditText) findViewById(R.id.password)).getText()
                    .toString().trim();
            checkValidations(v);
        }

        if(v.getId() == server.getId()){
            server.setVisibility(View.GONE);
            server_url.setVisibility(View.VISIBLE);
        }

    }

    protected void checkValidations(View view) {

        if (username.equals("")) {
            AppUtil.createSnackBarShort(view, "Please enter username/email.");
            return;
        }

        if (password.equals("")) {
            AppUtil.createSnackBarShort(view, "Please enter password.");
            return;
        }

        String url = server_url.getText().toString();
        if (url.equals("")) {
            AppUtil.createSnackBarShort(view, "Please Server URL.");
            return;
        }

        if (AppUtil.isInternetPresent(context)) {
            AppUtil.savePrefs(AppUtil.WEB_SERVICE_URL, url, this);
            loginAsyncTask(username, password);
        } else {
            AppUtil.createShortNotification(this, "No Internet, Check Connectivity!");
        }

    }

    private void loginAsyncTask(String username, String password) {
        lockScreenOrientation();
        progressDialog = ProgressDialog.show(this, "Please wait", "Signing In...", true);
        progressDialog.setCancelable(true);
        AppUtil.savePrefs(AppUtil.USERNAME, username, this);
        AppUtil.savePrefs(AppUtil.PASSWORD, password, this);
        new LoginWebService(context).execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        NCMP.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        NCMP.getInstance().unregister(this);
    }

    @Subscribe
    public void onAsyncTaskResult(AsyncTaskResultEvent event) {
        progressDialog.hide();
        unlockScreenOrientation();
        String msg = event.getResult();
        if (msg.isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            AppUtil.createShortNotification(this, msg);
        }
    }

    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void unlockScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

}


