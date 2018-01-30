package com.android.developergroup.printerusb;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.developergroup.printerusb.adaptors.PrinterListRecyViewAdaptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView textinfo;

    RecyclerView deviceInfoList;
    LinearLayoutManager linearLayoutManager;
    PrinterListRecyViewAdaptor printerListRecyViewAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textinfo=(TextView) findViewById(R.id.textinfo) ;

        deviceInfoList=(RecyclerView) findViewById(R.id.printerlist);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        deviceInfoList.setLayoutManager(linearLayoutManager);
        String msg = "This is a test message";
        /*PrintOrder printer = new PrintOrder();
        printer.Print(this,msg);*/

    }

    public void startConfig(View view) {


        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = null;
        if (manager != null) {
            deviceList = manager.getDeviceList();
            Iterator<UsbDevice> deviceIterator = null;

            deviceIterator = deviceList.values().iterator();

            List<UsbDevice> usbDevices = new ArrayList<>();
            String i = "";
            while (deviceIterator.hasNext()) {
                UsbDevice device = deviceIterator.next();
                usbDevices.add(device);/*
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    i += "\n" +
                            "DeviceID: " + device.getDeviceId() + "\n" +
                            "DeviceName: " + device.getDeviceName() + "\n" +
                            "DeviceClass: " + device.getDeviceClass() + " - "
                            + translateDeviceClass(device.getDeviceClass()) + "\n" +
                            "DeviceSubClass: " + device.getDeviceSubclass() + "\n" +
                            "VendorID: " + device.getVendorId() + "\n" +
                            "ProductID: " + device.getProductId() + "\n"
                            + "Product Name: " + device.getProductName() + "Manufacturer Name :" + device.getManufacturerName()
                            + "Device Protocol: " + device.getDeviceProtocol()
                    ;
                } else {
                    i += "\n" +
                            "DeviceID: " + device.getDeviceId() + "\n" +
                            "DeviceName: " + device.getDeviceName() + "\n" +
                            "DeviceClass: " + device.getDeviceClass() + " - "
                            + translateDeviceClass(device.getDeviceClass()) + "\n" +
                            "DeviceSubClass: " + device.getDeviceSubclass() + "\n" +
                            "VendorID: " + device.getVendorId() + "\n" +
                            "ProductID: " + device.getProductId() + "\n"

                    ;
                }*/


            }


            printerListRecyViewAdaptor = new PrinterListRecyViewAdaptor(usbDevices, this);
            deviceInfoList.setAdapter(printerListRecyViewAdaptor);
         //   textinfo.setText(i);
        }
    }

    private String translateDeviceClass(int deviceClass){
        switch(deviceClass){
            case UsbConstants.USB_CLASS_APP_SPEC:
                return "Application specific USB class";
            case UsbConstants.USB_CLASS_AUDIO:
                return "USB class for audio devices";
            case UsbConstants.USB_CLASS_CDC_DATA:
                return "USB class for CDC devices (communications device class)";
            case UsbConstants.USB_CLASS_COMM:
                return "USB class for communication devices";
            case UsbConstants.USB_CLASS_CONTENT_SEC:
                return "USB class for content security devices";
            case UsbConstants.USB_CLASS_CSCID:
                return "USB class for content smart card devices";
            case UsbConstants.USB_CLASS_HID:
                return "USB class for human interface devices (for example, mice and keyboards)";
            case UsbConstants.USB_CLASS_HUB:
                return "USB class for USB hubs";
            case UsbConstants.USB_CLASS_MASS_STORAGE:
                return "USB class for mass storage devices";
            case UsbConstants.USB_CLASS_MISC:
                return "USB class for wireless miscellaneous devices";
            case UsbConstants.USB_CLASS_PER_INTERFACE:
                return "USB class indicating that the class is determined on a per-interface basis";
            case UsbConstants.USB_CLASS_PHYSICA:
                return "USB class for physical devices";
            case UsbConstants.USB_CLASS_PRINTER:
                return "USB class for printers";
            case UsbConstants.USB_CLASS_STILL_IMAGE:
                return "USB class for still image devices (digital cameras)";
            case UsbConstants.USB_CLASS_VENDOR_SPEC:
                return "Vendor specific USB class";
            case UsbConstants.USB_CLASS_VIDEO:
                return "USB class for video devices";
            case UsbConstants.USB_CLASS_WIRELESS_CONTROLLER:
                return "USB class for wireless controller devices";
            default: return "Unknown USB class!";

        }
    }
}
