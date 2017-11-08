package zw.co.ncmp.network;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Proxy;
import java.net.SocketTimeoutException;

import zw.co.ncmp.MainActivity;
import zw.co.ncmp.business.Mentor;
import zw.co.ncmp.util.AppUtil;
import zw.co.ncmp.business.NCMP;

/**
 * Created by tdhlakama on 10/15/2015.
 */
public class LoginWebService extends AsyncTask<String, Integer, String> {

    private OkHttpClient client = new OkHttpClient();
    private Context context;

    public LoginWebService(Context context) {
         this.context = context;
    }

    public HttpUrl URL() {
        return AppUtil.getLoginUrl(context).newBuilder()
                .setQueryParameter("email", AppUtil.getUsername(context))
        .build();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            result = run();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String msg ="";
        try {

            JSONObject jsonObject = new JSONObject(result);
            Mentor mentor = Mentor.fromJson(jsonObject);
            if (mentor.serverId != null) {
                AppUtil.savePrefs(AppUtil.LOGGED_IN, Boolean.TRUE, context);
                AppUtil.savePrefs(AppUtil.WEB_USER_ID, mentor.serverId, context);
                AppUtil.savePrefs(AppUtil.MENTOR_ROLE, mentor.mentorRole, context);
                Mentor checkDuplicate = Mentor.getMentor(mentor.serverId);
                if (checkDuplicate == null) {
                    mentor.save();
                } else {
                    checkDuplicate.firstName = mentor.firstName;
                    checkDuplicate.lastName = mentor.lastName;
                    checkDuplicate.middleName = mentor.middleName;
                    checkDuplicate.nationalId = mentor.nationalId;
                    checkDuplicate.mobileNumber = mentor.mobileNumber;
                }
            } else {
                msg = "User Name or Password Incorrect";
            }
        } catch (Exception e) {
            msg = "Login Failed Try Again";
        }
        NCMP.getInstance().post(new AsyncTaskResultEvent(msg));
    }

    private String run() throws IOException {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        Response response=null;
        try {
            Request request = new Request.Builder()
                    .url(URL())
                    .build();
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }catch (SocketTimeoutException e) {
            return "Server Unavailable - Try Again Later";
        }
        return response.body().string();



    }




}
