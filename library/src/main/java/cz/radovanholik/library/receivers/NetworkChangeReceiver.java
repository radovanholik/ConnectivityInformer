package cz.radovanholik.library.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.util.HashSet;

import cz.radovanholik.library.listeners.ConnectivityChangeListener;
import cz.radovanholik.library.utils.Utils;

/**
 * Created by Radovan on 2017-03-16.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    private boolean mLastConnectionValue = false;
    private long mLastKnownTimeStamp = 0;

    public HashSet<ConnectivityChangeListener> mConnectivityChangeListeners;

    public NetworkChangeReceiver(Context context) {
        mConnectivityChangeListeners = new HashSet<>();
        mLastConnectionValue = Utils.isInternetAvailable(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = Utils.isInternetAvailable(context);

        for(ConnectivityChangeListener listener : mConnectivityChangeListeners){
            if(listener != null){
                listener.onNetworkConnectionChanged(isConnected);
            }
        }
    }

    public HashSet<ConnectivityChangeListener> getConnectivityChangeListeners(){
        return mConnectivityChangeListeners;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };

    /**
     * This method takes care about fast changing the network connection.
     * e.g. when the user loses the wifi signal but is still connected to the mobile connection
     * then there is a small time period in which the connection status is false and then
     * switched back to true. The purpose of this method is to avoid calling connectivity change listener.
     * @return
     */
    private void checkIfConnectionChanged(final Context context){

    }
}
