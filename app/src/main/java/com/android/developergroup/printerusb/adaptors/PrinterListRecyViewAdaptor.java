package com.android.developergroup.printerusb.adaptors;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.developergroup.printerusb.PrintOrder;
import com.android.developergroup.printerusb.R;
import com.android.developergroup.printerusb.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 30-01-2018.
 */

public class PrinterListRecyViewAdaptor extends RecyclerView.Adapter<PrinterListRecyViewAdaptor.PrinterItemViewHolder> {

    List<UsbDevice> usbDeviceList=new ArrayList<>();
    Context context;

    public PrinterListRecyViewAdaptor(List<UsbDevice> usbDevices,Context context){
        usbDeviceList=usbDevices;
        this.context=context;
    }


    @Override
    public PrinterItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new PrinterItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(PrinterItemViewHolder holder, int position) {

        final UsbDevice device=usbDeviceList.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.deviceInfo.setText("Product Name: " +device.getProductName()+"Manufacturer Name :"+device.getManufacturerName());
        }else {
            holder.deviceInfo.setText(  "DeviceName: " + device.getDeviceName() + "\n" +
                    "DeviceClass: " + device.getDeviceClass());
        }

        holder.deviceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.selectedDevice=device;
                String msg = "This is a test message";
        PrintOrder printer = new PrintOrder();
        printer.Print(context,msg);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usbDeviceList.size();
    }

    public static class PrinterItemViewHolder extends RecyclerView.ViewHolder  {

        TextView deviceInfo;

        public PrinterItemViewHolder(View itemView) {
            super(itemView);
            deviceInfo=(TextView) itemView.findViewById(R.id.list_deviceinfo);
        }



    }




}
