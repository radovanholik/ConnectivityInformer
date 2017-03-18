package cz.radovanholik.connectivityinformer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cz.radovanholik.library.ConnectivityInformer;
import cz.radovanholik.library.listeners.ConnectivityChangeListener;

public class MainActivity extends AppCompatActivity implements ConnectivityChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectivityInformer.getInstance().addConnectivityChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ConnectivityInformer.getInstance().removeConnectivityChangeListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Toast.makeText(this, "Moje knihovna funguje!", Toast.LENGTH_SHORT).show();
    }
}
