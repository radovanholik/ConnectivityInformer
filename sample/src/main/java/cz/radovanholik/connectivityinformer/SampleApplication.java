package cz.radovanholik.connectivityinformer;

import android.app.Application;

import cz.radovanholik.library.ConnectivityInformer;

/**
 * Created by radovanholik on 18.03.17.
 */

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ConnectivityInformer.getInstance().init(this);
    }
}
