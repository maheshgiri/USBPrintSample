package com.android.developergroup.printerusb;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

public class ConfigActivity extends AppCompatActivity {

    TextView deviceInfo;
    Spinner spinner_devicelist;
    UsbDevice device;
    UsbManager manager;
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                     device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if(device != null){
                            //call method to set up device communication
                        }
                    }
                    else {
                        Log.d("DEBUG", "permission denied for device " + device);
                    }
                }
            }
        }
    };
    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";
    private PendingIntent mPermissionIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        spinner_devicelist=(Spinner) findViewById(R.id.spinner_devicelist);
        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(mUsbReceiver, filter);
         manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        device=(UsbDevice) getIntent().getParcelableExtra(UsbManager.EXTRA_DEVICE);
        if (manager != null) {
          //  manager.requestPermission(device, mPermissionIntent);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Arrays.asList(deviceList.keySet()));
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_devicelist.setAdapter(adapter);
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()){
            UsbDevice device = deviceIterator.next();
            //your code


        }

    }
}
