package cz.radovanholik.library;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import cz.radovanholik.library.listeners.ConnectivityChangeListener;
import cz.radovanholik.library.receivers.NetworkChangeReceiver;

/**
 * Created by radovanholik on 18.03.17.
 */

public class ConnectivityInformer {

    /**
     * Constants
     */
    public static final String NOT_INITIALIZED = "Not initialized.";

    /**
     * Logic vars
     */
    private boolean mIsInitialized = false;

    /**
     * Other vars
     */
    private static final ConnectivityInformer sInstance = new ConnectivityInformer();
    private IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private NetworkChangeReceiver mNetworkChangeReceiver;

    private ConnectivityInformer() {
    }

    public static ConnectivityInformer getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mNetworkChangeReceiver = new NetworkChangeReceiver(context);
        registerReceiver(context);
        mIsInitialized = true;
    }

    public void addConnectivityChangeListener(ConnectivityChangeListener listener) {
        if(mIsInitialized) mNetworkChangeReceiver.getConnectivityChangeListeners().add(listener);
        else Log.e(getClass().getSimpleName(), NOT_INITIALIZED);
    }

    public void removeConnectivityChangeListener(ConnectivityChangeListener listener) {
        if(mIsInitialized) mNetworkChangeReceiver.getConnectivityChangeListeners().remove(listener);
        else Log.e(getClass().getSimpleName(), NOT_INITIALIZED);
    }

    public boolean isInitialized(){
        return mIsInitialized;
    }

    private void registerReceiver(Context context) {
        context.registerReceiver(mNetworkChangeReceiver, mFilter);
    }
}