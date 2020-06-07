//*CID://+@@02R~: update#= 156;
//**********************************************************************
//@@02 20181106 UHandler on subthread
//@003:20181103 dismiss aler dialog when interrupted by other app  //~@003I~
//**********************************************************************
package com.btmtest.utils;

import jagoclient.Dump;                                     //~@003R~
import android.app.Activity;
import android.os.Handler;                                         //~@003I~
import android.os.Looper;
import android.os.Message;                                         //~@003I~

import java.util.ArrayList;

//*****************************************                        //~@003I~
////usage                                                          //~@003I~
//    public Uhandler uHandler=new UHandler();                     //~@003I~
////onResume()                                                     //~@003I~
//    UHandler.setActivity(getActivity());                         //~@003I~
//    uHandler.onResume();                                         //~@003I~
////onPause()                                                      //~@003I~
//    uHandler.onPause();                                          //~@003I~
////onDestroy()                                                    //~@003I~
//    UHandler.setActivity(null);                                  //~@003I~
//**********************************************************************
public abstract class UHandler extends Handler                            //~@003R~
{                                                                  //~@003R~
    final ArrayList<Message> MQ=new ArrayList<Message>();          //~@003R~
    private boolean paused=false;                                        //~@003R~//~@@02R~
    private Activity activity;                                     //~@003I~
//**********************************                               //~@003I~
    protected abstract boolean storeMsg(Message msg);              //~@003I~
    protected abstract void handleMsg(Message msg);                //~@003R~
//**********************************                               //~@003R~
    public UHandler()                                              //~@@02R~
    {                                                              //~@@02I~
//      super();                                                   //~@@02R~
        if (Dump.Y) Dump.println("UHandler constructor main looper");//~@@02R~
    }                                                              //~@@02I~
//**********************************                               //~@@02I~
    public UHandler(Looper Plooper)                                //~@@02I~
    {                                                              //~@@02I~
        super(Plooper);                                            //~@@02I~
        if (Dump.Y) Dump.println("UHandler constructor looper="+Plooper.toString());//~@@02I~
    }                                                              //~@@02I~
//**********************************                               //~@@02I~
    public final void onResume(Activity Pactivity)                 //~@003R~
    {                                                              //~@003R~
        if (Dump.Y) Dump.println("UHandler.onResume Qctr="+MQ.size()+",activity="+Pactivity.toString());//~@003R~
        activity=Pactivity;                                        //~@003I~
        paused=false;                                              //~@003R~
        while (MQ.size()>0)                                        //~@003I~
        {                                                          //~@003I~
            final Message msg=MQ.get(0);                           //~@003I~
            MQ.remove(0);                                          //~@003I~
            if (Dump.Y) Dump.println("UHandler.onResume sendMessage what="+msg.what);//~@003R~
            sendMessage(msg);                                      //~@003I~
        }                                                          //~@003I~
    }                                                              //~@003R~
//**********************************                               //~@003I~
    public final void onPause()                                    //~@003I~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("UHandler.onPause");              //~@003R~
        paused=true;                                               //~@003I~
    }                                                              //~@003I~
//**********************************                               //~@003I~
    public void onDestroy()                                  //~@003I~//~@@02R~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("UHandler.onDestry");             //~@003R~
        activity=null;                                             //~@003I~
    }                                                              //~@003I~
//**********************************                               //~@003R~
    @Override                                                      //~@003I~
    final public void handleMessage(Message Pmsg)                  //~@003I~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("UHandler.handleMessage paused="+paused);//+@@02I~
        if (paused)                                                //~@003I~
        {                                                          //~@003I~
            if (storeMsg(Pmsg)) //save for restore                 //~@003I~//~@@02R~
            {                                                      //~@003I~
                Message msg=new Message();                        //~@003I~
                msg.copyFrom(Pmsg);                                //~@003I~
                MQ.add(msg);                                       //~@003I~
                if (Dump.Y) Dump.println("UHandler.handleMessage.stored ctr="+MQ.size()+",what="+Pmsg.what);//~@003R~//~@@02R~
            }                                                      //~@003I~
        }                                                          //~@003I~
        else                                                       //~@003I~
        {                                                          //~@003I~
        	if (activity!=null)                                     //~@003I~
	            handleMsg(Pmsg);                                   //~@003R~
        }                                                          //~@003I~
    }                                                              //~@003I~
}//class                                                           //~@003R~
