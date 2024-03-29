//*CID://+1amrR~:                             update#=  141;       //+1amrR~
//**********************************************************************//~v107I~
//1amr 2022/10/31 unkown permission on ndroid6(api23); BLUETOOTH_CONNECT is from API31//+1amrI~
//1am9 2022/10/30 android12(api31) Bluetooth permission is runtime permission//~1am9I~
//1am1 2022/10/29 android12 API31; bluetooth.getDefaultAdapter deprecated//~1am1I~
//1Aed 2015/07/30 show secure/insecure option to waiting msg       //~1AedI~
//1Ac5 2015/07/09 NFCBT:confirmation dialog is not show and fails to pairig//~1Ac5I~
//                (LocalBluetoothPreference:"Found no reason to show dialog",requires discovaring status in the 60 sec before)//~1Ac5I~
//                Issue waring whne NFCBT-Secure                   //~1Ac5I~
//1AbR 2015/07/03 BT:additional to paired, add device not paired but connected to devlice list//~1AbRI~
//1Abt 2015/06/18 BT:Accept not both of Secure/Insecure but one of two.//~1AbtI~
//1Abm 2015/06/16 NFCBT:chk secure level of requester and intent receiver//~1AbmI~
//1Abg 2015/06/15 NFCBT:transfer to NFCBT or NFCWD if active session exist//~1AbgR~
//1Abb 2015/06/15 NFCBT:try insecure only(if accepting both,connect insecure request is accepted by secure socket)//~1AbbR~
//1Aa0 2015/04/18 (BUG)AjagoBT coding miss                         //~1Aa0I~
//1A8g 2015/03/05 chk only one session alive(Ip,Direct,BT)         //~1A8gI~
//1A6m 2015/02/15 BT;distingush msg for CONN_LOST and CONN_FAILURE //~1A6mI~
//1A6i 2015/02/14 Bluetooth;display current bonded(paired) device list not initial status.//~1A6iI~
//1A6b 2015/02/13 (BUG) BTDiscovery intent receiver was not unregister.(Following msg on logcat)//~1A6bI~
//1A63 2015/01/27 msg "Already trying connect" by not MessageDialog but Toast.(message diialog will be overrided by connection fail")//~1A63I~
//1A60 2015/01/25 also support Bluetooth secure SPP(Bluetooth 2.1) //~v101I~
//101i 2013/02/09 for the case Bluetoothe disabled at initial      //~v101I~
//101a 2013/01/30 IP connection                                    //~@@@2I~
//1071:121204 partner connection using Bluetooth SPP               //~v107I~
//**********************************************************************//~v107I~
package com.Ahsv;                                                  //~v107R~

import wifidirect.DialogNFCBT;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.bluetooth.BluetoothSocket;                          //~v107I~

import com.Ahsv.BT.BTControl;                                      //~v107R~
import com.Ahsv.BT.BTDiscover;
import com.Ahsv.BT.BTService;
import com.Ahsv.R;                                                 //~1AedR~
//import com.Ahsv.BT.BTList;                                       //~@@@2R~
import com.Ahsv.jagoclient.partner.PartnerFrame;                   //~v107R~
import com.Ahsv.jagoclient.partner.Server;                         //~v107R~
import com.btmtest.utils.UView;

import java.util.ArrayList;

import jagoclient.Dump;                                            //~v107I~
import jagoclient.Global;
import jagoclient.MainFrame;
import jagoclient.dialogs.Message;
import jagoclient.gui.DoActionListener;
import jagoclient.partner.BluetoothConnection;

//********************************************************************//~1212I~
//* Interface to BT                                                //~v107R~
//********************************************************************//~1212I~
public class ABT                                               //~1122R~//~v107R~
//         implements DoActionListener                             //~v107R~
{                                                                  //~1109I~
//	private static final String DISCOVERABLE="Make Discoverable";  //~v107M~
//	private static final String STARTSERVER="Open Server";         //~v107R~
//	private static final String CONNECT="Connect to Server";       //~v107R~
	public BTControl mBTC;                                        //~v107R~
	public  BTDiscover mBTD;                                       //~@@@2I~
	private BluetoothSocket mBTSocket;                             //~v107R~
  	private PartnerFrame partnerFrame;                             //~v107I~
    private String mDeviceName;                                    //~v107I~
    private String requestDeviceName="Unknown";                    //~@@@2I~
//    private boolean swServer;                                      //~v107I~//~@@@@R~
//  private boolean swConnect;                                     //~@@@@I~//~1Ac5R~
    public  boolean swConnect;                                     //~1AbcI~//~1Ac5I~
//    private boolean swBTavail;                                     //~v107I~//~@@@2R~
//    private static Menu bluetooth;                                 //~v107I~//~@@@2R~
	public boolean swDestroy;                                      //~@@@2R~
	private boolean swSecureConnect;                               //~1AedI~
    public boolean swNFC,swSecureNFC;                              //~1AbbI~
    public boolean swSecureNonNFCAccept;                           //~1AbtI~
    public ABT()                                                   //~v107R~
    {                                                              //~1329I~
        if (Dump.Y) Dump.println("===========ABT start============");//~@@@2I~
		swDestroy=false;               //static clear for after finish()//~@@@2I~
		AG.aBT=this;                                               //~1am1I~
      if (AG.swGrantedBluetooth                                    //~vam8I~//~1am9I~
      ||  AG.swGrantBluetoothFailed)                               //~vas6I~//~1am9I~
      {                                                            //~vam8I~//~1am9I~
	    mBTC=new BTControl();                                      //~v107R~
	    mBTD=new BTDiscover();                                     //~@@@2I~
      }                                                            //~1am9I~
    }
    public static ABT createABT()                                  //~v107R~
    {                                                              //~v107I~
		ABT inst=null;                                             //~v107R~
        try                                                        //~v107I~
        {                                                          //~v107I~
          if (AG.swGrantBluetoothFailed)                           //~vas6R~//~1am9I~
          {                                                        //~vas6I~//~1am9I~
		    inst=new ABT();                                        //~v107R~
          }                                                        //~vas6I~//~1am9I~
          else                                                     //~vas6I~//~1am9I~
          {                                                        //~vas6I~//~1am9I~
            chkGrantedPermission();                                //~vam8M~//~1am9I~
		    inst=new ABT();                                        //~v107R~//~v@@@R~//~1am9R~
		    if (!AG.swGrantedBluetooth)                            //~vam8I~//~1am9I~
            {                                                      //~vam8M~//~1am9I~
            	//*wait onRequestPermissionResult callback         //~vas6I~//~1am9I~
            	return null;                                       //~vam8M~//~1am9I~
            }                                                      //~vam8M~//~1am9I~
          }                                                        //~vas6I~//~1am9I~
            if (!inst.mBTC.BTCreate())	//default adapter chk      //~v107R~
            	inst.mBTC=null;                                    //~v107R~
            else                                                   //~@@@@I~//~@@@2R~
            {                                                      //~@@@2I~
//                inst.startServer();                                //~@@@@R~//~@@@2R~
//                inst.mBTC.BTEnable(false/*not resume*/);         //~v101R~
	    		AG.LocalDeviceName=inst.mBTC.getDeviceName();           //~@@@2I~
            }                                                      //~@@@2I~
        }                                                          //~v107I~
        catch(Exception e)                                         //~1am1I~
        {                                                          //~1am1I~
            Dump.println(e,"ABT.createABT");                       //~1am1I~
        }                                                          //~1am1I~
        catch(Throwable e)                                         //~v107R~
        {                                                          //~v107I~
            Dump.println(e.toString());                            //~v107R~
        }                                                          //~v107I~
        return inst;                                               //~v107I~
    }                                                              //~v107I~
//********************************************************************//~@@@2I~
    public static boolean startListen()                        //~@@@2R~
    {                                                              //~@@@2I~
    	boolean rc=false;                                          //~@@@2I~
		ABT inst=AG.aBT;                                           //~@@@2I~
        if (inst.mBTC==null)                                       //~@@@2I~
        {                                                          //~@@@2I~
//            AView.showToast(R.string.BTNotAvalable);               //~@@@2I~//~1AbRR~
//            return false;                                          //~@@@2I~//~1AbRR~
			return BTNotAvailable();                               //~1AbRI~
        }                                                          //~@@@2I~
        try                                                        //~@@@2I~
        {                                                          //~@@@2I~
        	rc=inst.startServer();                                    //~@@@2I~//~v101R~
        }                                                          //~@@@2I~
        catch(Throwable e)                                         //~@@@2I~
        {                                                          //~@@@2I~
            Dump.println(e.toString());                            //~@@@2I~
//          AView.showToast(R.string.BTNotAvalable);               //~@@@2I~//~1AbRR~
			BTNotAvailable();                                      //~1AbRI~
        }                                                          //~@@@2I~
        AG.isNFCBT=false;                                          //~1AbgR~
        return rc;                                                 //~@@@2I~
    }                                                              //~@@@2I~
//********************************************************************//~1AbbI~
//* NFCBT:startServer with secure option                           //~1AbbI~
//********************************************************************//~1AbbI~
    public static boolean startListen(boolean Psecure)             //~1AbbI~
    {                                                              //~1AbbI~
		ABT inst=AG.aBT;                                           //~1AbbI~
        inst.swNFC=true; //parm to BTstartServer()-->BTService.start()//~1AbbI~
    	inst.swSecureNFC=Psecure;                                  //~1AbbI~
    	AG.swSecureNFCBT=Psecure;                                  //~1AbgR~
	    boolean rc=startListen();                                  //~1AbbI~
        AG.isNFCBT=true;                                           //~1AbgR~
	    return rc;                                                 //~1AbbI~
    }                                                              //~1AbbI~
//********************************************************************//~1AbtI~
//* from Bluetooth                                                 //~1AbtI~
//* strtServer with secure option                                  //~1AbtI~
//********************************************************************//~1AbtI~
    public static boolean startListenNonNFC(boolean Psecure)       //~1AbtI~
    {                                                              //~1AbtI~
		ABT inst=AG.aBT;                                           //~1AbtI~
        inst.swNFC=false; //parm to BTstartServer()-->BTService.start()//~1AbtI~
    	inst.swSecureNonNFCAccept=Psecure;                         //~1AbtI~
	    boolean rc=startListen();                                  //~1AbtI~
	    return rc;                                                 //~1AbtI~
    }                                                              //~1AbtI~
//********************************************************************//~v107I~
    public void resume()                                           //~v107I~
    {                                                              //~v107I~
        try                                                        //~v107I~
        {                                                          //~v107I~
        	if (mBTC!=null)                                              //~v107I~
			    mBTC.BTResume();                                   //~v107R~
        }                                                          //~v107I~
        catch(Exception e)                                         //~v107R~
        {                                                          //~v107I~
            Dump.println(e,"ABT:resume");                          //~v107R~
            AView.showToast(R.string.failedBluetooth);             //~v107R~
        }                                                          //~v107I~
    }                                                              //~v107I~
//********************************************************************//~v107I~
    public void destroy()                                          //~v107I~
    {                                                              //~v107I~
		if (Dump.Y) Dump.println("ABT:destroy swGrantedBluetooth="+AG.swGrantedBluetooth);                   //~@@@2I~//~1am9R~
        if (!AG.swGrantedBluetooth)                                //~vam8I~//~1am9I~
        {                                                          //~vam8I~//~1am9I~
	        if (Dump.Y) Dump.println("ABT.destroy return NOP by swGrantedBluetooth");//~vam8I~//~1am9R~
        	return;                                                //~vam8I~//~1am9I~
        }                                                          //~vam8I~//~1am9I~
    	swDestroy=true;                                            //~@@@2I~
        try                                                        //~v107I~
        {                                                          //~v107I~
        	if (mBTC!=null)                                              //~v107I~
		    	mBTC.BTDestroy();                                  //~v107R~
            if (mBTSocket!=null)                                   //~@@@2I~
            {                                                      //~@@@2I~
				if (Dump.Y) Dump.println("ABT:destroy:close mBTSocket="+mBTSocket.toString());//~@@@2R~
            	mBTSocket.close();                                 //~@@@2I~
                mBTSocket=null;                                     //~@@@2I~
            }                                                      //~@@@2I~
        }                                                          //~v107I~
        catch(Exception e)                                         //~v107I~
        {                                                          //~v107I~
            Dump.println(e,"ABT:destroy");                         //~v107R~
            AView.showToast(R.string.failedBluetooth);             //~v107R~
        }                                                          //~v107I~
    }                                                              //~v107I~
//*************************************************************************//~v107I~
    public void activityResult(int requestCode, int resultCode, Intent data)//~v107I~
    {                                                              //~v107I~
        try                                                        //~v107I~
        {                                                          //~v107I~
	    	mBTC.BTActivityResult(requestCode,resultCode,data);    //~v107R~
        }                                                          //~v107I~
        catch(Exception e)                                         //~v107I~
        {                                                          //~v107I~
            Dump.println(e,"ABT:activityResult");                  //~v107R~
            AView.showToast(R.string.failedBluetooth);             //~v107R~
        }                                                          //~v107I~
    }                                                              //~v107I~
////********************************************************************//~v107R~
////*from MenuBar;add submenu to mainframe                         //~v107R~
////********************************************************************//~v107R~
//    public void addBTMenu(MenuBar Pmenubar)                      //~v107R~
//    {                                                            //~v107R~
//        if (bluetooth==null)                                     //~v107R~
//        {                                                        //~v107R~
//            bluetooth=new MyMenu(Global.resourceString("Bluetooth"));//~v107R~
//            bluetooth.add(new MenuItemAction(this,Global.resourceString(STARTSERVER)));//~v107R~
//            bluetooth.add(new MenuItemAction(this,Global.resourceString(CONNECT)));//~v107R~
//            bluetooth.add(new MenuItemAction(this,Global.resourceString(DISCOVERABLE)));//~v107R~
//        }                                                        //~v107R~
//        Pmenubar.menuList.add(bluetooth);                        //~v107R~
//    }                                                            //~v107R~
////********************************************************************//~v107R~
////*implement DoActionListener                                    //~v107R~
////********************************************************************//~v107R~
//    @Override                                                    //~v107R~
//    public void doAction(String o)                               //~v107R~
//    {                                                            //~v107R~
//        try                                                      //~v107R~
//        {                                                        //~v107R~
//            if (mBTC==null)                                      //~v107R~
//            {                                                    //~v107R~
//                AView.showToast(R.string.noBTadapter);           //~v107R~
//                return;                                          //~v107R~
//            }                                                    //~v107R~
//            if (Global.resourceString(DISCOVERABLE).equals(o))   //~v107R~
//                setDiscoverable();                               //~v107R~
//            else                                                 //~v107R~
//            if (Global.resourceString(STARTSERVER).equals(o))    //~v107R~
//                startServer();                                   //~v107R~
//            else                                                 //~v107R~
//            if (Global.resourceString(CONNECT).equals(o))        //~v107R~
//                connect();                                       //~v107R~
//        }                                                        //~v107R~
//        catch(Exception e)                                       //~v107R~
//        {                                                        //~v107R~
//            Dump.println(e,"ABT:doAction");                      //~v107R~
//            AView.showToast(R.string.failedBluetooth);           //~v107R~
//        }                                                        //~v107R~
//    }                                                            //~v107R~
//***************************************************************************//~v107I~
//    @Override                                                    //~v107R~
//    public void itemAction(String o, boolean flag) {             //~v107R~
//    }                                                            //~v107R~
//***************************************************************************//~v107I~
    public void setDiscoverable()                                  //~v107R~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("ABT:setDiscoverable");           //~v107R~
        if (mBTC==null)                                            //~v107I~
        {                                                          //~1AbRI~
			BTNotAvailable();                                      //~1AbRI~
            return;                                                //~v107I~
        }                                                          //~1AbRI~
        if (mBTC.BTensureDiscoverable(true/*request discoverable if not*/)==0)	//already discoverable//~v107R~
            AView.showToast(R.string.nowDiscoverable);             //~v107R~
    }                                                              //~v107I~
//***************************************************************************//~@@@@I~//~v107M~
    public boolean startServer()                                      //~v107R~//~v101R~
    {                                                              //~v107I~
        boolean rc=false;                                          //~v101I~
    //*******************                                          //~v107I~
    	if (Dump.Y) Dump.println("ABT:startServer");               //~v107R~
        if (mBTC==null)                                            //~v107I~
            return false;                                                //~v107I~//~v101R~
    	if (MainFrame.isAliveOtherSession(AG.AST_BT,true/*duper*/))//~1A8gI~
            return false;                                          //~1Aa0R~
//        swServer=true;                                             //~v107I~//~@@@@R~
		if (mBTSocket==null)                                       //~@@@@R~
//            mBTSocket=mBTC.BTstartServer();                            //~v107R~//~@@@@R~//~v101R~
            rc=mBTC.BTstartServer();	//true if accept issued    //~v101I~
//        if (mBTSocket!=null)                                       //~v107I~//~v101R~
//            openServer(mBTSocket);                                 //~v107I~//~v101R~
//        else                                                     //~v101R~
//            return false;   //may be enabling                    //~v101R~
//        return true;                                             //~v101R~
        return rc;                                                 //~v101I~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
//    public void connect()                //~@@@@I~                 //~v107R~//~@@@2R~
//    {                                                              //~@@@@I~//~v107I~//~@@@2R~
//        int rc;                                                    //~v107I~//~@@@2R~
//    //**********************                                       //~v107I~//~@@@2R~
//        requestDeviceName="Unknown";                             //~@@@2R~
//        if (Dump.Y) Dump.println("ABT:connect");                   //~v107R~//~@@@2R~
//        if (mBTC==null)                                            //~v107I~//~@@@2R~
//            return;                                                //~v107I~//~@@@2R~
////        swServer=false;                                            //~v107I~//~@@@@R~//~@@@2R~
//        swConnect=true;                                            //~@@@@I~//~@@@2R~
//        rc=mBTC.BTconnect();                                       //~v107R~//~@@@2R~
//        if (rc==1)  //connecting                                   //~v107I~//~@@@2R~
//        {                                                          //~v107I~//~@@@2R~
//            new jagoclient.dialogs.Message(Global.frame(),Global.resourceString("Already_trying_this_connection"));//~v107I~//~@@@2R~
//            return;                                                //~v107I~//~@@@2R~
//        }                                                          //~v107I~//~@@@2R~
//    }                                                              //~@@@@I~//~v107I~//~@@@2R~
//***************************************************************************//~@@@2I~
//* device addr specified connect                                  //~@@@2I~
//***************************************************************************//~@@@2I~
//  public void connect(String Pname,String Paddr)                 //~@@@2R~//~1A60R~
    public void connect(String Pname,String Paddr,boolean Psecure) //~1A60I~
    {                                                              //~@@@2I~
    	int rc;                                                    //~@@@2I~
    //**********************                                       //~@@@2I~
    	swSecureConnect=Psecure;                                   //~1AedI~
	    requestDeviceName=Pname;                                   //~@@@2I~
    	if (Dump.Y) Dump.println("ABT:connect device="+Pname+",addr="+Paddr);//~@@@2R~
        if (mBTC==null)                                            //~@@@2I~
            return;                                                 //~@@@2I~
    	if (MainFrame.isAliveOtherSession(AG.AST_BT,true/*duper*/))//~1A8gI~//~1AedR~
            return;                                                //~1A8gI~
        swConnect=true;                                            //~@@@2I~
//      rc=mBTC.BTconnect(Paddr);                                  //~@@@2I~//~1A60R~
        rc=mBTC.BTconnect(Paddr,Psecure);                          //~1A60I~
        if (rc==1)	//connecting                                   //~@@@2I~
        {                                                          //~@@@2I~
//  		new jagoclient.dialogs.Message(Global.frame(),Global.resourceString("Already_trying_this_connection"));//~@@@2I~//~1A63R~
    		AView.showToastLong(Global.resourceString("Already_trying_this_connection"));//~1A63I~
//			return;                                                //~@@@2I~//~1AbgR~
  		}                                                          //~@@@2I~
	    AG.isNFCBT=false;                                          //~1AbgR~
    }                                                              //~@@@2I~
//***************************************************************************//~1AbgR~
//* connect for NFCBT                                              //~1AbgR~
//***************************************************************************//~1AbgR~
    public void connectNFC(String Pname,String Paddr,boolean Psecure)//~1AbgR~
    {                                                              //~1AbgR~
	    swNFC=true;                                                //~1AbgR~
		swSecureNFC=Psecure;                                       //~1AbgR~
    	AG.swSecureNFCBT=Psecure;                                  //~1AbgR~
	    connect(Pname,Paddr,Psecure);                              //~1AbgR~
	    AG.isNFCBT=true;                                           //~1AbgR~
    }                                                              //~1AbgR~
//***************************************************************************//~v107I~
//*from BTControl;after request enable completed                   //~v107R~
//***************************************************************************//~v107I~
    public void enabled()                                          //~v107R~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("ABT:enabled swServer="+swConnect);//~v107R~//~@@@@R~
//        if (swServer)                                              //~v107I~//~@@@@R~
//        {                                                          //~v107I~//~@@@@R~
//            startServer();                                         //~v107I~//~v101R~
//        }                                                          //~v107I~//~@@@@R~
		new jagoclient.dialogs.Message(Global.frame(),R.string.InfoBTEnabled);//~v101I~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
//*from BTControl;on MainThread                                    //~v107I~
//***************************************************************************//~v107I~
    public void connected(BluetoothSocket Psocket,String Pdevicename)//~v107I~
    {                                                              //~v107I~
        String addr=Psocket.getRemoteDevice().getAddress();        //~1AbRI~
    	if (Dump.Y) Dump.println("ABT:connected swConnect="+swConnect+",device="+Pdevicename+"="+addr);//~v107R~//~@@@@R~//~1AbRI~
    	mBTSocket=Psocket;                                         //~v107I~
    	mDeviceName=Pdevicename;                                   //~v107I~
        BluetoothConnection.addConnectedDevice(Pdevicename,addr);  //~1AbRI~
//        if (swServer)                                              //~v107I~//~@@@@R~
        if (!swConnect)                                            //~@@@@I~
        {                                                          //~v107I~
	    	openServer(mBTSocket);                                 //~v107I~
        }                                                          //~v107I~
        else                                                       //~v107I~
        {                                                          //~v107I~
	    	openPartner(mBTSocket,mDeviceName,mBTC.getDeviceName());//~v107R~
        }                                                          //~v107I~
        swConnect=false;                                           //~@@@2I~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
    private void openServer(BluetoothSocket Psocket)               //~v107I~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("ABT:openServer socket="+Psocket.toString());                //~v107R~//~@@@2R~
    	new Server(Psocket,mDeviceName,mBTC.getDeviceName());   //open PartnerFrame//~v107R~//~@@@@R~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
    private void openPartner(BluetoothSocket Psocket,String Pdevicename,String Plocaldevicename)//~v107R~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("ABT:openPartner socket="+Psocket.toString());//~@@@2I~
    	if (Dump.Y) Dump.println("device="+Pdevicename+",local="+Plocaldevicename);//~@@@2I~
//		partnerFrame=new PartnerFrame(Global.resourceString("Connection_to_"),Pdevicename,Plocaldevicename,false,Psocket);//~v107R~//~@@@@R~
		partnerFrame=new PartnerFrame(AG.resource.getString(R.string.BTClient),Pdevicename,Plocaldevicename,false,Psocket);//~@@@@I~
        if (partnerFrame.connect())	//connected                                    //~v107I~//~@@@2R~
        {                                                          //~1AbmI~
            partnerFrame.doAction(AG.resource.getString(R.string.Game));//~@@@2I~
            DialogNFCBT.chkSecureLevelMsg();	//after dismiss,issue alertDialog on GameQuetion dialog//~1AbmI~
        }                                                          //~1AbmI~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
    public void connectionLost()                           //~v107I~
    {                                                              //~v107I~
    	mBTC.connectionLost();                                     //~v107I~
        if (mBTSocket!=null)                                       //~@@@@I~
        {                                                          //~@@@@I~
        	try                                                    //~@@@@I~
            {                                                      //~@@@@I~
                if (Dump.Y) Dump.println("ABT:conectionLost:close btsocket="+mBTSocket.toString());//~@@@2R~
    			mBTSocket.close();                                  //~@@@@I~
            }                                                      //~@@@@I~
            catch(Exception e)                                     //~@@@@I~
            {                                                      //~@@@@I~
	            Dump.println(e,"ABT:connectionLost:close()");      //~@@@@I~
            }                                                      //~@@@@I~
            mBTSocket=null;                                        //~@@@@I~
        }                                                          //~@@@@I~
        swConnect=false;                                           //~@@@2I~
//        if (!swDestroy)                                          //~@@@2R~
//            startServer();      //issue Accept                         //~@@@@I~//~@@@2R~
    }                                                              //~v107I~
//***************************************************************************//~@@@@I~
    public boolean isConnectionAlive()                                //~@@@@I~
    {                                                              //~@@@@I~
    	return (mBTC!=null)&&mBTC.isConnectionAlive();             //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~@@@2I~
    public boolean stopListen()                                    //~@@@2I~
    {                                                              //~@@@2I~
    	return (mBTC!=null)&&mBTC.stopListen();                    //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
//*BTService-->BTControl-->                                        //~@@@2I~
//***************************************************************************//~@@@2I~
    public void connFailed(int flag)                               //~@@@2I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("ABT:connFailed "+flag);          //~@@@2I~
        swConnect=false;                                           //~@@@2I~
        PartnerFrame.dismissWaitingDialog();                       //~@@@2I~
      if (flag==BTService.CONN_FAILED)                             //~1A6mI~
      {                                                            //~1AedI~
    	String secureopt=AG.resource.getString(swSecureConnect ? R.string.BTSecure : R.string.BTInSecure);//~1AedI~//~1am9R~
		new Message(Global.frame(),                                //~@@@2I~
//  		Global.resourceString("No_connection_to_")+requestDeviceName);//~@@@2I~//~1A6iR~
//  		AG.resource.getString(R.string.Err_No_connection_to_BT,requestDeviceName));//~1A6iI~//~1AedR~
    		AG.resource.getString(R.string.Err_No_connection_to_BT,requestDeviceName,secureopt));//~1AedI~
      }                                                            //~1AedI~
      else                                                         //~1A6mI~
		new Message(Global.frame(),                                //~1A6mI~
    		Global.resourceString("No_connection_to_")+requestDeviceName);//~1A6mI~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public void acceptFailed(String Psecure)                       //~@@@2I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("ABT:acceptFailed "+Psecure);     //~@@@2I~
        PartnerFrame.dismissWaitingDialog();                       //~@@@2I~
		new Message(Global.frame(),AG.resource.getString(R.string.ErrAcceptfailed,Psecure));//~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public String[] getPairDeviceList()                            //~@@@2R~
    {                                                              //~@@@2I~
        if (mBTC==null)                                            //~@@@2I~
            return null;                                           //~@@@2I~
//        return BTList.getPairDeviceList();                       //~@@@2R~
        if (!mBTC.BTEnable(false/*not resume*/))                                      //~v101I~
            return null;                                           //~v101I~
        return BTDiscover.getPairDeviceList();                     //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public boolean discover(DoActionListener Pdal)                 //~@@@2R~
    {                                                              //~@@@2I~
        if (mBTC==null)                                            //~@@@2I~
//          return false;                                          //~@@@2R~//~1AbRR~
			return BTNotAvailable();                               //~1AbRI~
        return mBTC.discover(Pdal);                              //~@@@2R~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public boolean cancelDiscover()                                //~@@@2I~
    {                                                              //~@@@2I~
        if (mBTC==null)                                            //~@@@2I~
            return false;                                          //~@@@2I~
        return mBTC.cancelDiscover();                              //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public String[] getNewDevice()                                 //~@@@2I~
    {                                                              //~@@@2I~
    	if (Dump.Y) Dump.println("ABT.getNewDevice "+ Utils.toString(BTDiscover.newDevice));//~1AedI~
        return BTDiscover.newDevice;                             //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~1A6bI~
//*from AMain                                                      //~1A6bI~
//***************************************************************************//~1A6bI~
    public void onDestroy()                                        //~1A6bI~
    {                                                              //~1A6bI~
        if (Dump.Y) Dump.println("ABT onDestroy");                 //~1A6bI~
	    mBTD.unregister();                                          //~1A6bI~
    }                                                              //~1A6bI~
//***************************************************************************//~1AbRI~
	public static boolean BTNotAvailable()                         //~1AbRI~
    {                                                              //~1AbRI~
        AView.showToast(R.string.BTNotAvalable);                   //~1AbRI~
        return false;                                              //~1AbRI~
    }                                                              //~1AbRI~
//***************************************************************************//~1Ac5I~
	public static boolean BTisDiscoverable()                       //~1Ac5I~
    {                                                              //~1Ac5I~
        return AG.aBT.mBTC.BTisDiscoverable()==1;                  //~1Ac5I~
    }                                                              //~1Ac5I~
//********************************************************************//~vam8I~//~1am9I~
    private static boolean chkGrantedPermission()                  //~vam8R~//~1am9I~
    {                                                              //~vam8I~//~1am9I~
        if (Dump.Y) Dump.println("ABT.chkGrantedPermission osVerison="+AG.osVersion+",AG.swGrantedBluetooth="+AG.swGrantedBluetooth);//~vam8R~//~1am9R~
        if (AG.osVersion>=31)                                      //~vam8I~//~1am9I~
        {                                                          //~vam8I~//~1am9I~
			if (!AG.swGrantedBluetooth)                            //~vam8I~//~1am9I~
				AG.swGrantedBluetooth=chkGrantedPermission_from31();//~vam8R~//~1am9I~
        }                                                          //~vam8I~//~1am9I~
        else                                                       //~vam8I~//~1am9I~
        {                                                          //~vas6I~//~1am9I~
			if (!AG.swGrantedBluetooth)                            //~vas6I~//~1am9I~
				AG.swGrantedBluetooth=chkGrantedPermission_upto30();//~vas6I~//~1am9I~
        	AG.swGrantedBluetooth=true;                            //~vam8I~//~1am9I~
        }                                                          //~vas6I~//~1am9I~
        boolean rc=AG.swGrantedBluetooth;                          //~vam8R~//~1am9I~
        if (Dump.Y) Dump.println("ABT.chkGrantedPermission exit rc="+rc);//~vam8R~//~1am9R~
        return rc;                                                 //~vam8I~//~1am9I~
    }                                                              //~vam8I~//~1am9I~
//********************************************************************//~vam8I~//~1am9I~
    @TargetApi(31)                                                 //~vam8I~//~1am9I~
    private static boolean chkGrantedPermission_from31()           //~vam8R~//~1am9I~
    {                                                              //~vam8I~//~1am9I~
        if (Dump.Y) Dump.println("ABT.chkGrantedPermission_from31");//~vam8R~//~1am9R~
        ArrayList<String> requestList=new ArrayList<>();           //~vas6I~//~1am9I~
        boolean swGrantedConnect= UView.isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT);//~vam8I~//~1am9I~
        boolean swGrantedScan=UView.isPermissionGranted(Manifest.permission.BLUETOOTH_SCAN);//~vam8I~//~1am9I~
                                                                   //~vas6I~//~1am9I~
        if (!swGrantedConnect)                                     //~vas6I~//~1am9I~
        	requestList.add(Manifest.permission.BLUETOOTH_CONNECT);//~vas6I~//~1am9I~
        if (!swGrantedScan)                                        //~vas6I~//~1am9I~
        	requestList.add(Manifest.permission.BLUETOOTH_SCAN);   //~vas6I~//~1am9I~
        if (!UView.isPermissionGranted(Manifest.permission.BLUETOOTH_ADVERTISE))//~vas6I~//~1am9I~
        	requestList.add(Manifest.permission.BLUETOOTH_ADVERTISE);//~vas6I~//~1am9I~
//      if (!UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION))//~vatdR~//~1am9I~
//      	requestList.add(Manifest.permission.ACCESS_FINE_LOCATION);//~vatdR~//~1am9I~
        UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION);    //chk only//~vatdI~//~1am9I~
        if (!UView.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION))//~vatdR~//~1am9I~
        	requestList.add(Manifest.permission.ACCESS_COARSE_LOCATION);//~vatdR~//~1am9I~
//      if (Dump.Y) Dump.println("ABT.chkGrnatedPermission_from31 isGranted FINE_LOCATION="+UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION));//~vas6R~//~1am9R~
//      boolean rc=swGrantedConnect && swGrantedScan;              //~vam8I~//~vas6R~//~1am9I~
//      if (!rc)                                                   //~vam8I~//~vas6R~//~1am9I~
//      {                                                          //~vam8I~//~vas6R~//~1am9I~
//      	String[] types=new String[]{Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_SCAN};//~vam8I~//~vas6R~//~1am9I~
//      	UView.requestPermission(types, MainActivity.PERMISSION_BLUETOOTH);//~vam8I~//~vas6R~//~1am9I~
//      }                                                          //~vam8I~//~vas6R~//~1am9I~
        boolean rc=true;                                           //~vas6I~//~1am9I~
        if (!requestList.isEmpty())                                //~vas6I~//~1am9I~
        {                                                          //~vas6I~//~1am9I~
	      	String[] reqs=(String[])(requestList.toArray(new String[requestList.size()]));//~vas6R~//~1am9I~
	        if (Dump.Y) Dump.println("ABT.chkGrnatedPermission_from31 requestPermission list="+Utils.toString(reqs));//~vas6I~//~1am9R~
	      	UView.requestPermission(reqs,AMain.PERMISSION_BLUETOOTH);//~vas6I~//~1am9R~
            rc=false;                                              //~vas6I~//~1am9I~
        }                                                          //~vas6I~//~1am9I~
        if (Dump.Y) Dump.println("ABT.chkGrnatedPermission_from31 exit");//~vas6R~//~1am9R~
        return rc;                                                 //~1am9I~
    }                                                              //~vam8I~//~1am9I~
//********************************************************************//~vas6I~//~1am9I~
    private static boolean chkGrantedPermission_upto30()           //~vas6I~//~1am9I~
    {                                                              //~vas6I~//~1am9I~
        if (Dump.Y) Dump.println("ABT.chkGrantedPermission_upto30");//~vas6I~//~1am9R~
        ArrayList<String> requestList=new ArrayList<>();           //~vas6I~//~1am9I~
//      UView.isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT);   //for debug//~vas6R~//~1am9I~//+1amrR~
//      UView.isPermissionGranted(Manifest.permission.BLUETOOTH_SCAN);      //for debug//~vas6R~//~1am9I~//+1amrR~
//      UView.isPermissionGranted(Manifest.permission.BLUETOOTH_ADVERTISE);	//for debug//~vas6R~//~1am9I~//+1amrR~
        if (!UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION))//~vas6R~//~1am9I~
        {                                                          //~vas6I~//~vatdR~//~1am9I~
        	if (AG.osVersion>=29) 	//android10,11                 //~vas6R~//~vatdR~//~1am9I~
            	requestList.add(Manifest.permission.ACCESS_FINE_LOCATION);//~vas6R~//~1am9I~
        }                                                          //~vas6I~//~1am9I~
        if (!UView.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION))//~vatdI~//~1am9I~
        {                                                          //~vatdI~//~1am9I~
            requestList.add(Manifest.permission.ACCESS_COARSE_LOCATION);//~vatdR~//~1am9I~
        }                                                          //~vatdI~//~1am9I~
        boolean rc=true;                                           //~vas6I~//~1am9I~
        if (!requestList.isEmpty())                                //~vas6I~//~1am9I~
        {                                                          //~vas6I~//~1am9I~
	      	String[] reqs=(String[])(requestList.toArray(new String[requestList.size()]));//~vas6I~//~1am9I~
	        if (Dump.Y) Dump.println("ABT.chkGrnatedPermission_upto30 requestPermission list="+Utils.toString(reqs));//~vas6R~//~1am9R~
          	UView.requestPermission(reqs,AMain.PERMISSION_BLUETOOTH); //TODO test//~vas6R~//~1am9R~
            rc=false;                                              //~vas6I~//~1am9I~
        }                                                          //~vas6I~//~1am9I~
        if (Dump.Y) Dump.println("ABT.chkGrnatedPermission_upto30 exit");//~vas6I~//~1am9R~
        return rc;                                                 //~vas6I~//~1am9I~
    }                                                              //~vas6I~//~1am9I~
    public static void grantedPermission(String[] Pname,int[] Presults/*0:granted,denied*/)//~vas6R~//~1am9I~
    {                                                              //~vas6I~//~1am9I~
        if (Dump.Y) Dump.println("ABT.grantedPermission name="+ Utils.toString(Pname)+",result="+Utils.toString(Presults));//~vas6I~//~1am9I~
        boolean ok=true;                                           //~vas6I~//~1am9I~
        boolean swFailedAdvertize=false;                           //~vas6I~//~1am9I~
        for (int ii=0;ii<Pname.length;ii++)                        //~vas6I~//~1am9I~
        {                                                          //~vas6I~//~1am9I~
        	if (!UView.isPermissionGranted(Presults[ii]))           //~vas6I~//~1am9I~
            {                                                      //~vas6I~//~1am9I~
                if (Pname[ii].equals(Manifest.permission.BLUETOOTH_ADVERTISE))//~vas6I~//~1am9I~
                {                                                  //~vas6I~//~1am9I~
                    AG.swFailedGrantBluetoothAdvertize=true;       //~vas6R~//~1am9I~
//	            	without this permission, pairing available     //~vas6I~//~1am9I~
//	            	UView.showToastLong(Utils.getStr(R.string.failedBluetoothPermissionTypeAdvertize,Pname[ii]));//~vas6R~//~1am9I~
                }                                                  //~vas6I~//~1am9I~
                else                                               //~vas6I~//~1am9I~
                {                                                  //~vas6I~//~1am9I~
            		ok=false;                                      //~vas6R~//~1am9I~
	            	UView.showToastLong(Utils.getStr(R.string.failedBluetoothPermissionType,Pname[ii]));//~vas6R~//~1am9I~
                }                                                  //~vas6I~//~1am9I~
            }                                                      //~vas6I~//~1am9I~
        }                                                          //~vas6I~//~1am9I~
        if (ok)                                                    //~vas6I~//~1am9I~
        	AG.swGrantedBluetooth=true;                            //~vas6I~//~1am9I~
        else                                                       //~vas6I~//~1am9I~
        	AG.swGrantBluetoothFailed=true;                        //~vas6I~//~1am9I~
        createABT();                                               //~vas6I~//~1am9I~
        	                                                       //~vas6I~//~1am9I~
    }                                                              //~vas6I~//~1am9I~
}//class                                                           //~1109R~