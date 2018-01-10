package zw.co.ncmp.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import zw.co.ncmp.R;
import zw.co.ncmp.WVersionManager;
import zw.co.ncmp.business.CaseFile;
import zw.co.ncmp.BuildConfig;
import zw.co.ncmp.business.CaseFileMentee;
import zw.co.ncmp.business.CaseFileMentor;
import zw.co.ncmp.business.Challenge;
import zw.co.ncmp.business.ChallengeStatus;
import zw.co.ncmp.business.DSDIndividual;
import zw.co.ncmp.business.Designation;
import zw.co.ncmp.business.Facility;
import zw.co.ncmp.business.FacilityChallenge;
import zw.co.ncmp.business.Mentee;
import zw.co.ncmp.business.Mentor;
import zw.co.ncmp.business.MentorShipFocusArea;
import zw.co.ncmp.business.MentorVisitReport;
import zw.co.ncmp.business.MonthReportForm;
import zw.co.ncmp.business.PMTCTARTForm;
import zw.co.ncmp.business.PMTCTFOForm;
import zw.co.ncmp.business.Period;
import zw.co.ncmp.business.Qualification;
import zw.co.ncmp.business.RegisterForm;
import zw.co.ncmp.business.StatForm;
import zw.co.ncmp.business.SupplementaryIndicatorForm;
import zw.co.ncmp.business.TXCURRForm;
import zw.co.ncmp.business.TXPVLSForm;
import zw.co.ncmp.business.TXRETForm;
import zw.co.ncmp.business.TXTNew;

/**
 * Created by tdhlakama on 1/19/2016.
 */
public class AppUtil {

    public static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static String ID = "ID";
    public static String MENTEE_ID = "MENTEE_ID";
    public static String DSD_ID = "DSD_ID";
    public static String CASE_ID = "CASE_ID";
    public static String MENTOR_ID = "MENTOR_ID";
    public static String DATE_FORMAT = "dd/MM/yyyy";
    public static String SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String OLD_SQL_DATE_FORMAT = "yyyy-MM-dd";
    public static String APP_URL = "http://192.168.1.172:8084/itech-mobile/rest/mobile/"; //UAT
    //public static String APP_URL = "http://tracker.pzat.org:8080/itech-mobile/rest/mobile/"; //UAT
    public static String LOGGED_IN = "LOGGED_IN";
    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String WEB_USER_ID = "WEB_USER_ID";
    public static String WEB_SERVICE_URL = "WEB_SERVICE_URL";
    public static String PENDING = "Pending";
    public static String RESOLVED = "Resolved";
    public static String MENTOR_ROLE = "NATIONAL";
    private static Gson gson;

    public static SimpleDateFormat getFormatter() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public static HttpUrl getLoginUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("mentor"));
    }

    public static HttpUrl getChangePasswordUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("change-password")).newBuilder()
                .build();
    }

    public static HttpUrl getMentorFacilitiesUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("mentor-facilities")).newBuilder()
                .setQueryParameter("id", String.valueOf(AppUtil.getWebUserId(context)))
                .build();
    }

    public static HttpUrl getMentorsUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("district-mentors")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .setQueryParameter("email", AppUtil.getUsername(context))
                .build();
    }

    public static HttpUrl getMenteesPushUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("mentee-new")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getFacilityMenteesUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("facility-mentees")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .setQueryParameter("email", AppUtil.getUsername(context))
                .build();
    }

    public static HttpUrl getPushCaseFileUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("case-file")).newBuilder()
                .setQueryParameter("id", String.valueOf(AppUtil.getWebUserId(context)))
                .build();
    }

    public static HttpUrl getPushMentorVisitReportUrl(Context context, Long id) {
        final Mentor mentor = Mentor.getMentor(AppUtil.getWebUserId(context));
        return HttpUrl.parse(getWebService(context).concat("visit-report")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .setQueryParameter("email", mentor.email)
                .build();
    }

    public static HttpUrl getPushFacilityChallengeUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("facility-challenge")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPullFacilityChallengeUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("facility-challenges")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushARTFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tb-art")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }


    public static HttpUrl getPushScreenFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tb-screen-dx")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }


    public static HttpUrl getPushStatFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tb-stat")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushDSDIndividualUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/dsd-individual")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushTXTNewUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/dsd-new")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushCOP17TXTNewUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/cop17-tx-new")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushMonthFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/month-report-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushRegisterFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/register-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushPMTCTARTFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/pmtct-art-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushPMTCTFOFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/pmtct-fo-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushSupplementaryIndicatorFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/supplementary-indicator-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushTXCURRFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tx-curr-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushTXPVLSFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tx-pvls-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushTXRETFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tx-ret-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushHTCTSTReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/hts-tst")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushPMTCTStatReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/pmtct-stat")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushPMTCTEIDFormReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/pmtct-eid-form")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushTXNewReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("form/tx-new")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushCommBasedDSDReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/comm-based-dsd")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }

    public static HttpUrl getPushDSDCOP17ReportUrl(Context context, Long id) {
        return HttpUrl.parse(getWebService(context).concat("dsd/dsd-cop17")).newBuilder()
                .setQueryParameter("id", String.valueOf(id))
                .build();
    }


    public static HttpUrl getQualificationsUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/qualifications"));
    }

    public static HttpUrl getChallengeUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/challenge"));
    }

    public static HttpUrl getActionTakenCategoryUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/action-category"));
    }

    public static HttpUrl getChallengeStatusUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/challenge-status"));
    }

    public static HttpUrl getFocusAreaUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/focus-area"));
    }

    public static HttpUrl getDesignationUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/designation"));
    }

    public static HttpUrl getPeriodUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/period"));
    }

    public static HttpUrl getDistrictUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/district"));
    }

    public static HttpUrl getProvinceUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/province"));
    }

    public static HttpUrl getFacilityUrl(Context context) {
        return HttpUrl.parse(getWebService(context).concat("static/facility"));
    }

    public static Date getDate(String dateInString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = sdf.parse(dateInString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date getSQLDate(String dateInString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(SQL_DATE_FORMAT);
            Date date = sdf.parse(dateInString);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }


    public static Date getOIDSQLDate(String dateInString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_SQL_DATE_FORMAT);
            Date date = sdf.parse(dateInString);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getStringDate(Date date) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(AppUtil.DATE_FORMAT);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getStringTime(Date date) {
        if (date == null) {
            return "";

        }
        try {
            return getFormatter().format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getWebService(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(WEB_SERVICE_URL, APP_URL);
    }

    public static String getUsername(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(USERNAME, "Anonymous");
    }

    public static long getWebUserId(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getLong(WEB_USER_ID, 9000000);
    }

    public static String getPassword(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PASSWORD, "Anonymous");
    }

    public static Boolean isUserLoggedIn(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(LOGGED_IN, Boolean.FALSE);
    }

    public static String getUserRole(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(MENTOR_ROLE, "Role");
    }

    public static void removePrefs(String key, Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void savePrefs(String key, Boolean value, Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void savePrefs(String key, Long value, Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void savePrefs(String key, String value, Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void createSnackBarShort(View view, String mgs) {
        Snackbar.make(view, mgs, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    public static void createSnackBarLong(View view, String mgs) {
        Snackbar.make(view, mgs, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public static boolean isInternetPresent(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static OkHttpClient createAuthenticationData(OkHttpClient client, final Context context) {
        client.setAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Proxy proxy, Response response) {
                String credential = Credentials.basic(AppUtil.getUsername(context), AppUtil.getPassword(context));
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) {
                return null; // Null indicates no attempt to authenticate.
            }
        });
        return client;
    }

    public static OkHttpClient getUnsafeOkHttpClient(OkHttpClient client) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            client.setSslSocketFactory(sslSocketFactory);
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static OkHttpClient connectionSettings(OkHttpClient client) {
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        return client;
    }

    public static String run(HttpUrl httpUrl, Context context) throws IOException {
        String result = "";
        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.createAuthenticationData(client, context);
        client = AppUtil.getUnsafeOkHttpClient(client);
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(httpUrl)
                    .build();

            response = client.newCall(request).execute();

            if (AppUtil.responseCount(response) >= 3) {
                return "authentication_error";
            }

            result = response.body().string();
        } catch (SocketTimeoutException e) {
            result = "Server Unavailable - Try Again Later";
        }
        return result;

    }

    public static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    public static void createLongNotification(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void createShortNotification(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void loginReset(Context context) {
        clearAppData(context);
    }

    public static void clearAppData(Context context) {
        AppUtil.savePrefs(AppUtil.LOGGED_IN, Boolean.FALSE, context);
        AppUtil.removePrefs(AppUtil.USERNAME, context);
        AppUtil.removePrefs(AppUtil.PASSWORD, context);
        AppUtil.removePrefs(AppUtil.WEB_USER_ID, context);
        /*CaseFileMentor.deleteAll();
        CaseFileMentee.deleteAll();
        FacilityChallenge.deleteAll();
        MentorVisitReport.deleteAll();
        Mentee.deleteAll();
        CaseFile.deleteAll();
        Qualification.deleteAll();
        Designation.deleteAll();
        MentorShipFocusArea.deleteAll();
        DSDIndividual.deleteAll();
        Challenge.deleteAll();
        ChallengeStatus.deleteAll();
        StatForm.deleteAll();
        TXTNew.deleteAll();
        RegisterForm.deleteAll();
        MonthReportForm.deleteAll();
        PMTCTARTForm.deleteAll();
        PMTCTFOForm.deleteAll();
        TXCURRForm.deleteAll();
        TXRETForm.deleteAll();
        TXPVLSForm.deleteAll();
        SupplementaryIndicatorForm.deleteAll();
        Period.deleteAll();
        Facility.deleteAll();
        Mentor.deleteAll();*/

    }

    public static Boolean isNational(String value) {
        if (value == null || value.equals("")) {
            return Boolean.FALSE;
        } else {
            if (value.equals(AppUtil.MENTOR_ROLE)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }


    public static String getValue(String value) {
        if (value.isEmpty() || value == null) {
            return "";
        } else {
            return value;
        }
    }

    public static Integer getInputValue(String value) {
        try {
            if (value.isEmpty() || value == null) {
                return 0;
            } else {
                return Integer.valueOf(value);
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public static Long getLongValue(String value) {
        try {
            if (value.isEmpty() || value == null) {
                return 0L;
            } else {
                return Long.valueOf(value);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Long getLong(Long value) {
        try {
            if (value == null) {
                return 0L;
            } else {
                return value;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getLongValue(Long value) {
        try {
            if (value == null) {
                return "";
            } else {
                return String.valueOf(value);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getResponeBody(OkHttpClient client, HttpUrl httpUrl) {
        Response response = null;
        String result = "";
        try {
            Request request = new Request.Builder()
                    .url(httpUrl)
                    .build();
            response = client.newCall(request).execute();
            if (AppUtil.responseCount(response) >= 3) {
                return "authentication_error";
            }
            result = response.body().string();
        } catch (SocketTimeoutException e) {
            result = "Server Unavailable - Try Again Later";
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }

    public static String getResponeBody(OkHttpClient client, HttpUrl httpUrl, String json) {
        Response response = null;
        String result = "";
        Log.d("Json", json);
        try {
            Request request = new Request.Builder()
                    .url(httpUrl)
                    .post(AppUtil.getPostBody(json))
                    .build();

            response = client.newCall(request).execute();

            if (AppUtil.responseCount(response) >= 3) {
                return "authentication_error";
            }
            result = response.body().string();
        } catch (SocketTimeoutException e) {
            result = "Server Unavailable - Try Again Later";
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    public static RequestBody getPostBody(String json) {
        RequestBody body = RequestBody.create(JSON, json);
        return body;
    }

    public static Dialog getAppDialog(final Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setTitle("Clinical Site Support v." + BuildConfig.VERSION_NAME);
        dialog.setContentView(R.layout.info);
        String msg = "Total Number of Case Files to Upload - (" + CaseFile.getNumberOfFilesToUpload() + ").";
        TextView txt_more = (TextView) dialog.findViewById(R.id.txt_more);
        txt_more.setText(msg);
        dialog.show();
        return dialog;
    }

    public static List<String> getYesNoCombo = Arrays.asList("Yes", "No");

    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setDateFormat("dd/MM/yyyy").create();
        return gson;
    }
}
