package com.android.developergroup.printerusb;

import android.content.Context;

public class PrintOrder {

    public PrintOrder(){
		
	}
	
	public void Print(Context context, String msg){
		USBAdapter usba = new USBAdapter();
			usba.createConn(context);
		try {
			usba.printMessage(context,msg);
			usba.closeConnection(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}