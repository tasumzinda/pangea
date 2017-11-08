package zw.co.ncmp.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;

import zw.co.ncmp.business.NCMP;
import zw.co.ncmp.util.AppUtil;

/**
 * Created by tdhlakama on 10/15/2015.
 */
public class ChangePasswordWebService extends AsyncTask<String, Integer, String> {

    private OkHttpClient client = new OkHttpClient();
    private Context context;
    private String password;

    public ChangePasswordWebService(Context context, String password) {
        this.context = context;
        this.password = password;
    }

    public HttpUrl URL() {
        return AppUtil.getChangePasswordUrl(context).newBuilder()
                .setQueryParameter("email", AppUtil.getUsername(context))
                .setQueryParameter("txmp", password)
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
        String msg = "";
        try {
            if (Boolean.valueOf(result)) {
                AppUtil.loginReset(context);
                msg = "Password Changed Successfully";
            }
        } catch (Exception e) {
            msg = "Change Password Failed";
        }
        NCMP.getInstance().post(new AsyncTaskResultEvent(msg));
    }

    private String run() throws IOException {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);

        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(URL())
                    .build();
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (SocketTimeoutException e) {
            return "Server Unavailable - Try Again Later";
        }
        return response.body().string();


    }


}
