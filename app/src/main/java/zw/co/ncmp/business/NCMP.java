package zw.co.ncmp.business;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import zw.co.ncmp.util.AppUtil;

/**
 * Created by tdhlakama on 1/19/2016.
 */
public class NCMP extends android.app.Application {

    public static Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        bus = new Bus(ThreadEnforcer.MAIN);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    @Override
    protected void attachBaseContext(Context context){
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    public static Bus getInstance() {
        return bus;
    }
}
