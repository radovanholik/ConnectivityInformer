package cz.radovanholik.library;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import cz.radovanholik.library.listeners.ConnectivityChangeListener;
import cz.radovanholik.library.receivers.NetworkChangeReceiver;

/**
 * Created by radovanholik on 18.03.17.
 */

public class ConnectivityInformer {

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
        System.out.println("init");
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(context);
    }

    public boolean addConnectivityChangeListener(ConnectivityChangeListener listener) {
        return mNetworkChangeReceiver.getConnectivityChangeListeners().add(listener);
    }

    public boolean removeConnectivityChangeListener(ConnectivityChangeListener listener) {
        return mNetworkChangeReceiver.getConnectivityChangeListeners().remove(listener);
    }

    private void registerReceiver(Context context) {
        //LocalBroadcastManager.getInstance(context).registerReceiver(mNetworkChangeReceiver, mFilter);
        context.registerReceiver(mNetworkChangeReceiver, mFilter);
    }
}