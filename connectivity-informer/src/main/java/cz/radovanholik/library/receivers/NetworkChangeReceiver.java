package cz.radovanholik.library.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.util.Calendar;
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

        if (mLastConnectionValue != isConnected || mLastKnownTimeStamp + 1000 < Calendar.getInstance().getTimeInMillis()) {
            mLastConnectionValue = isConnected;
            mLastKnownTimeStamp = Calendar.getInstance().getTimeInMillis();

            for (ConnectivityChangeListener listener : mConnectivityChangeListeners) {
                if (listener != null) {
                    listener.onNetworkConnectionChanged(isConnected);
                }
            }
        }
    }

    public HashSet<ConnectivityChangeListener> getConnectivityChangeListeners(){
        return mConnectivityChangeListeners;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO
        }
    };

    /**
     * This method takes care about fast changing network connection.
     * e.g. when the device loses the wifi signal but it is still connected to the mobile connection
     * then there might be a small time period in which the connection status is false and then
     * switched back to true (behaves differently on different android versions).
     * The purpose of this method is to avoid calling connectivity change listener.
     * @return
     */
    private void checkIfConnectionChanged(final Context context){
        // TODO
    }
}
