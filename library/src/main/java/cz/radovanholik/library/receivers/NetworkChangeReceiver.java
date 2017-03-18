package cz.radovanholik.library.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashSet;

import cz.radovanholik.library.listeners.ConnectivityChangeListener;
import cz.radovanholik.library.utils.Utils;

/**
 * Created by Radovan on 2017-03-16.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    public HashSet<ConnectivityChangeListener> mConnectivityChangeListeners;

    public NetworkChangeReceiver() {
        mConnectivityChangeListeners = new HashSet<>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = Utils.isInternetAvailable(context);
System.err.println("KUrva, zachytil jsem broadcast");
        for(ConnectivityChangeListener listener : mConnectivityChangeListeners){
            if(listener != null){
                listener.onNetworkConnectionChanged(isConnected);
            }
        }
    }

    public HashSet<ConnectivityChangeListener> getConnectivityChangeListeners(){
        return mConnectivityChangeListeners;
    }
}
