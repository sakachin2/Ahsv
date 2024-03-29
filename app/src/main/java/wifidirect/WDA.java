//*CID://+1amwR~:                             update#=   97;       //+1amwR~
//*************************************************************************//~1A65I~
//1amw 2022/11/01 widen bluetooth/wifidirect dialog for landscape  //+1amwI~
//2020/11/04 va40 of BTMJ5 Android10(api29) upgrade                //~va40I~
//1Ahd 2020/06/03 WifiDirect required PERMISSION_LOCATION          //~1AhdI~
//1Ahc 2020/06/03 expand WDA dialog portrait width of Huawei7(Android6)//~1AhcI~
//1Ad2 2015/07/17 HelpDialog by helptext                           //~1Ad2I~
//1Ac4 2015/07/06 WD:try disable wifi direct at unpair             //~1Ac4I~
//1Ac3 2015/07/06 WD:Unpare after active session was closed        //~1Ac3I~
//1Ac2 2015/07/06 WD:Unpare process ignore push of "Cancel" button of alert dialog active session exist//~1Ac2I~
//1A90 2015/04/18 (like as 1A84)WiFiDirect from Top panel          //~1A90I~
//1A87 2015/02/26 WifiDirect:disconnect when Unpair; disconnect reuired before unpair,no ioexception when closed if upaired before  disconnect//~1A90I~
//1A81 2015/02/24 ANFC is not used now                             //~1A81I~
//1A89k2015/03/01 Ajagoc:2015/02/28 confirm session disconnect when unpair//~1A89I~
//1A6C 2015/02/22 after WiFiDirect,standard IP Connection is too late. try WiFi Off before std IPConnection.//~1A6CI~
//1A6a 2015/02/10 NFC+Wifi support                                 //~1A6aI~
//1A67 2015/02/05 (kan)                                            //~1A67I~
//1A65 2015/01/29 implement Wifi-Direct function(>=Api14:android4.0)//~1A65I~
//*************************************************************************//~1A65I~
package wifidirect;                                                //~1A67R~

import java.lang.reflect.Method;

import jagoclient.Dump;
import jagoclient.Global;                                          //~1Ad2I~
import jagoclient.dialogs.HelpDialog;
//import jagoclient.partner.IPConnection;                          //~1A90R~
import android.annotation.TargetApi;                               //~1A65I~
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.p2p.WifiP2pDevice;                       //~1A65R~
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
//import android.net.wifi.WifiConfiguration;                       //~va40R~
import android.net.wifi.WifiManager;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
                                                                   
//~1A65I~
import com.Ahsv.AG;                                                //~1A65I~
import com.Ahsv.AMain;
import com.Ahsv.AView;
import com.Ahsv.Alert;
import com.Ahsv.AlertI;
import com.Ahsv.R;                                                 //~1Ad2R~
import com.Ahsv.URunnable;
import com.Ahsv.URunnableI;
import com.Ahsv.awt.UButton;                                     //~1A90I~
import com.axe.AxeDialog;
import com.btmtest.utils.UView;

@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~
//public class WiFiDirectActivity extends Activity implements ChannelListener, DeviceActionListener {//~1A65R~
public class WDA extends AxeDialog                                 //~1A65R~
    implements URunnableI                                          //~1Ac3I~
{                                                                  //~1A65I~
	private static final int LAYOUTID=R.layout.device_wd;          //~1A65I~
	private static final int LAYOUTID_MDPI=R.layout.device_wd_mdpi;//~1A67I~
    private static final int TITLEID=R.string.DialogTitle_DeviceDetail;//~1A65R~
    private static final int BUTTONS_2ND=R.id.DialogButtonsDetail; //~1A65R~
    public static final int ID_ACCEPT=R.id.AcceptIPConnection;    //~1A84I~//~1A90I~
    public static final int ID_STOPACCEPT=R.id.StopAcceptIPConnection;//~1A84I~//~1A90I~
    public static final int ID_CONNECT=R.id.IPConnect;            //~1A84I~//~1A90I~
    public static final int ID_DISCONNECT=R.id.IPDisConnect;       //~1A84R~//~1A90I~
    public static final int ID_GAME=R.id.IPGame;                  //~1A84I~//~1A90I~
    public static final int ALERT_TITLE_DISCONNECT=R.string.Title_ConfirmDisconnect;//~1A89I~
    public static final int ALERT_MSG_DISCONNECT=R.string.Msg_ConfirmDisconnect;//~1A89I~
	public static WDA SWDA;                                        //~1A65I~
	public WiFiDirectActivity aWDActivity;                         //~1A65R~
	private IPConnection IPC;                                      //~1A65R~
	private DeviceDetailFragment deviceDetailFragment;             //~1A65I~
	private DeviceListFragment deviceListFragment;                 //~1A65I~
                                                                   //~1A65I~
    public static final String TAG = "wifidirectdemo";
    public boolean swWifiEnable;                                   //~1A65I~
    public String WDAipa,WDApeer;                                  //~1A65R~
	public boolean WDAowner;                                       //~1A65I~
	public boolean swOwner;                                        //~1A84I~//~1A90I~
    private WDANFC  NFCwaitingDiscover;                            //~1A6aI~
    private String  NFCwaitingDiscoverAddr;                        //~1A6aI~
    private UButton btnAccept,btnConnect,btnGame,btnDisconnect,btnStopAccept;//~1A84R~//~1A90I~
	//***********************************************************************************//~1A65I~
	public static WDA showDialog(IPConnection Pipc)                           //~1612R~//~1A65R~
    {                                                              //~1528I~//~1A65I~
    	WDA dlg=new WDA(Pipc);           //~1612I~                 //~1A65R~
    	if (SWDA==null)                                            //~1A65I~
        	return null;                                           //~1A65I~
        String title=dlg.getResources().getString(TITLEID);     //~1612I~//~1A65R~
        if (!dlg.swWifiEnable)                                         //~1A65I~
        	return null;                                           //~1A65I~
		dlg.showDialog(title);                               //~1612I~//~1A65I~                                           //~1A65I~
        return dlg;                                          //~1612I~//~1A65I~
    }                                                              //~1612I~//~1A65I~
	//***********************************************************************************//~1A65I~
    public WDA(IPConnection Pipc)                                  //~1A65R~
    {                                                              //~1A65I~
		super((AG.screenDencityMdpiSmallV || AG.screenDencityMdpiSmallH/*mdpi and height or width <=320*/ ? LAYOUTID_MDPI : LAYOUTID));//~1A67I~
        IPC=Pipc;                                                  //~1A65R~
        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4          //~1A65I~//~1A6aR~
        	init_ICE_CREAM_SANDWICH();                             //~1A65I~
        else                                                       //~1A65I~
            init_deprecated();                                     //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65M~
    private void init_deprecated()                                 //~1A65R~
    {                                                              //~1A65I~
        SWDA=null;                                                 //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
	@TargetApi(AG.ICE_CREAM_SANDWICH)                                 //~1A65I~
    private void init_ICE_CREAM_SANDWICH()                         //~1A65I~
    {                                                              //~1A65I~
        SWDA=this;                                                 //~1A65I~
    	aWDActivity=new WiFiDirectActivity();                      //~1A65R~
        deviceListFragment=new DeviceListFragment();               //~1A65I~
        deviceDetailFragment=new DeviceDetailFragment();           //~1A65I~
      swWifiEnable=                                                //~1A84I~//~1A90I~
        enableWiFi();                                              //~1A65I~
        aWDActivity.registerReceiver();                            //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
    public static boolean enableWiFi()                             //~1A6CI~
    {                                                              //~1A6CI~
        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4       //~1A6CI~
        	return enableWiFi_ICE_CREAM_SANDWICH();              //~1A6CI~
        return false;                                              //~1A6CI~
    }                                                              //~1A6CI~
	//***********************************************************************************//~1A6CI~
	@TargetApi(AG.ICE_CREAM_SANDWICH)                              //~1A65I~
//  private boolean enableWiFi()                                   //~1A65I~//~1A6CR~
    private static boolean enableWiFi_ICE_CREAM_SANDWICH()         //~1A6CI~
    {                                                              //~1A65I~
    	boolean rc=true;                                           //~1A65I~
    //**********************                                       //~1A65I~
    	WifiManager wm=(WifiManager) AG.context.getSystemService(Context.WIFI_SERVICE);//~1A65R~
        if (Dump.Y) Dump.println("WFA:enableWiFi_ICE_CREAM_SANDWITCH isWifiEnabled="+wm.isWifiEnabled());//~1Ac4I~
//        if (true)                      //@@@@test                //~1Ac4I~
//        {                                                        //~1Ac4I~
//            setWifiDirectStatus(true);   //@@@@test              //~1Ac4I~
////          setWifiP2pState(true);  //enableP2p @@@@test         //~1Ac4I~
//        }                                                        //~1Ac4I~
//        else                                                     //~1Ac4I~
        if (!wm.isWifiEnabled())                                   //~1A65I~
        {                                                          //~1A65I~
        	if (Dump.Y) Dump.println("WDA:enableWiFi_ICE_CREAM_SANDWITCH isWifiEnabled:false");//~va40I~
//      	if (wm.setWifiEnabled(true))//success                  //~1A65I~//~va40R~
        	if (setWifiEnabled(wm,true))                           //~va40I~
				AView.showToastLong(R.string.InfoWifiEnabled);            //~1A65I~
            else                                                   //~1A65I~
            {                                                      //~1A65I~
				AView.showToastLong(R.string.ErrWifiEnable);              //~1A65I~
                rc=false;                                          //~1A65I~
            }                                                      //~1A65I~
        }                                                          //~1A65I~
        if (Dump.Y) Dump.println("enableWiFi rc="+rc);             //~1A65I~
//      swWifiEnable=rc;                                           //~1A65I~//~1A6CR~
//        if (SWDA!=null)                                            //~1A6CI~//~1A90R~
//            SWDA.swWifiEnable=rc;                                  //~1A6CI~//~1A90R~
        return rc;                                                 //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~va40I~
    private static boolean setWifiEnabled(WifiManager Pwm,boolean Penable)//~va40I~
    {                                                              //~va40I~
        if (Dump.Y) Dump.println("WDA.setWifiEnabled Penable="+Penable);//~va40I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q) //api29 android10//~va40I~
			return setWifiEnabled_From29(Pwm,Penable);             //~va40I~
        else                                                       //~va40I~
			return setWifiEnabled_Bellow29(Pwm,Penable);           //~va40I~
    }                                                              //~va40I~
	//***********************************************************************************//~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
    private static boolean setWifiEnabled_Bellow29(WifiManager Pwm,boolean Penable)//~va40I~
    {                                                              //~va40I~
		boolean rc=Pwm.setWifiEnabled(Penable);                    //~va40I~
        if (Dump.Y) Dump.println("setWifiEnabled_Bellow29 Penable="+Penable+",rc="+rc);//~va40I~
        return rc;                                                 //~va40I~
    }                                                              //~va40I~
	//***********************************************************************************//~va40I~
    private static boolean setWifiEnabled_From29(WifiManager Pwm,boolean Penable)//~va40I~
    {                                                              //~va40I~
		boolean rc=false;	//app could not change                 //~va40I~
        if (Dump.Y) Dump.println("setWifiEnabled_From29 Penable="+Penable+",rc="+rc);//~va40I~
        return rc;                                                 //~va40I~
    }                                                              //~va40I~
	//***********************************************************************************//~1A6CI~
    public static boolean disableWiFi()                            //~1A6CI~
    {                                                              //~1A6CI~
        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4       //~1A6CI~
        	return disableWiFi_ICE_CREAM_SANDWICH();               //~1A6CI~
        return false;                                              //~1A6CI~
    }                                                              //~1A6CI~
	//***********************************************************************************//~1A6CI~
	@TargetApi(AG.ICE_CREAM_SANDWICH)                              //~1A6CI~
    private static boolean disableWiFi_ICE_CREAM_SANDWICH()        //~1A6CR~
    {                                                              //~1A6CI~
    	boolean rc=true;                                           //~1A6CI~
    //**********************                                       //~1A6CI~
    	WifiManager wm=(WifiManager) AG.context.getSystemService(Context.WIFI_SERVICE);//~1A6CI~
        if (Dump.Y) Dump.println("WDA:disableWiFi old="+wm.isWifiEnabled());//~1Ac4I~
        if (wm.isWifiEnabled())                                    //~1A6CI~
        {                                                          //~1A6CI~
//      	if (wm.setWifiEnabled(false))                          //~1A6CI~//~va40R~
        	if (setWifiEnabled(wm,false))                          //~va40I~
				AView.showToastLong(R.string.InfoWifiDisabled);    //~1A6CI~
            else                                                   //~1A6CI~
            {                                                      //~1A6CI~
				AView.showToastLong(R.string.ErrWifiDisable);      //~1A6CI~
                rc=false;                                          //~1A6CI~
            }                                                      //~1A6CI~
        }                                                          //~1A6CI~
        if (Dump.Y) Dump.println("disnableWiFi rc="+rc);           //~1A6CI~
		if (SWDA!=null)                                            //~1A6CI~
	        SWDA.swWifiEnable=!rc;                                 //~1A6CI~
        return rc;                                                 //~1A6CI~
    }                                                              //~1A6CI~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~1A65I~
    {                                                              //~1A65I~
    	if (SWDA==null)                                            //~1A65R~
        	return;                                                //~1A65I~
        deviceListFragment.initFragment(PlayoutView);              //~1A65I~
        deviceDetailFragment.initFragment(PlayoutView);            //~1A65I~
        setButtonListenerAll(BUTTONS_2ND);                         //~1A65R~
		btnAccept=new UButton(layoutView,ID_ACCEPT);               //~1A84I~//~1A90I~
		btnStopAccept=new UButton(layoutView,ID_STOPACCEPT);       //~1A84I~//~1A90I~
		btnConnect=new UButton(layoutView,ID_CONNECT);             //~1A84I~//~1A90I~
		btnDisconnect=new UButton(layoutView,ID_DISCONNECT);       //~1A84I~//~1A90I~
		btnGame=new UButton(layoutView,ID_GAME);                   //~1A84I~//~1A90I~
	    updateButtonView(false/*owner*/);                          //~1A84R~//~1A90I~
      if (AG.portrait)                                             //+1amwI~
		setAxeDialogWindowSize(95/*width rate*/,0/*hight=wrap_content*/,true/*apply for min(Width,Height)*/);//~1AhcI~
      else                                                         //+1amwI~
		setAxeDialogWindowSize(100*2/3/*width rate*/,0/*hight=wrap_content*/,false/*apply for min(Width,Height)*/);//+1amwI~
    }                                                              //~1A65I~
//    //**********************************                           //~1A65I~//~1A90R~
//    @Override                                                      //~1A65I~//~1A90R~
//    protected boolean onClickClose()                                    //~1A65I~//~1A90R~
//    {                                                              //~1A65I~//~1A90R~
//        boolean rc=false;                                           //~1A65I~//~1A90R~
//        String ipa;                                                //~1A65I~//~1A90R~
//        boolean owner;                                             //~1A65I~//~1A90R~
//    //*****************************                                //~1A65I~//~1A90R~
//        ipa=deviceDetailFragment.ownerIPAddress;                   //~1A65R~//~1A90R~
//        owner=deviceListFragment.thisOwner==1;                     //~1A65R~//~1A90R~
//        if (deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED)//~1A65I~//~1A90R~
//        {                                                          //~1A65I~//~1A90R~
//            if (Dump.Y) Dump.println("WDA:onClickClose connected ipa="+(ipa==null?"null":ipa)+",owner="+owner);//~1A65R~//~1A67R~//~1A90R~
//            if (!owner && ipa!=null)                               //~1A65R~//~1A90R~
//            {                                                      //~1A65I~//~1A90R~
//                AView.showToast(R.string.InfoWDUseOwnerIP,ipa);    //~1A65R~//~1A90R~
//                rc=true;                                           //~1A65I~//~1A90R~
//            }                                                      //~1A65I~//~1A90R~
//            else                                                   //~1A65I~//~1A90R~
//            if (owner)                                             //~1A65R~//~1A90R~
//            {                                                      //~1A65I~//~1A90R~
//                AView.showToast(R.string.InfoWDOpenPort);          //~1A65I~//~1A90R~
//                rc=true;                                           //~1A65I~//~1A90R~
//            }                                                      //~1A65I~//~1A90R~
//        }                                                          //~1A65I~//~1A90R~
//        if (!rc)                                                   //~1A65I~//~1A90R~
//            AView.showToast(R.string.WarningWDNotConnected);       //~1A65I~//~1A90R~
//        else                                                       //~1A65I~//~1A90R~
//        {                                                          //~1A65I~//~1A90R~
//            WDAipa=ipa;                                            //~1A65I~//~1A90R~
//            WDAowner=owner;                                        //~1A65I~//~1A90R~
//            WDApeer=deviceDetailFragment.peerDevice;                  //~1A65I~//~1A90R~
//            if (WDApeer==null)                                     //~1A65I~//~1A90R~
//                WDApeer=WDAipa;                                    //~1A65I~//~1A90R~
////          IPC.doAction(getResourceString(R.string.doAction_WDAOK));//~1A65R~//~1A67R~//~1A90R~
//            IPC.onCloseWDA();                                      //~1A67I~//~1A90R~
//        }                                                          //~1A65I~//~1A90R~
//        return rc;  //not dismiss                                  //~1A65I~//~1A90R~
//    }                                                              //~1A65I~//~1A90R~
    //**********************************                           //~1A84I~//~1A90I~
    protected boolean onClickClose()                               //~1A84R~//~1A90I~
    {                                                              //~1A84I~//~1A90I~
        boolean rc=false;                                          //~1A84I~//~1A90I~
        String ipa;                                                //~1A84I~//~1A90I~
        boolean owner;                                             //~1A84I~//~1A90I~
    //*****************************                                //~1A84I~//~1A90I~
        ipa=deviceDetailFragment.ownerIPAddress;                   //~1A84I~//~1A90I~
//      owner=deviceListFragment.thisOwner==1;                     //~1A84I~//~1A90I~
        owner=swOwner;                                             //~1A84I~//~1A90I~
        if (deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED)//~1A84I~//~1A90I~
        {                                                          //~1A84I~//~1A90I~
            if (Dump.Y) Dump.println("WDA:onClickClose connected ipa="+(ipa==null?"null":ipa)+",owner="+owner);//~1A84I~//~1A90I~
            if (!owner && ipa!=null)                               //~1A84I~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
                rc=true;                                           //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
            else                                                   //~1A84I~//~1A90I~
            if (owner)                                             //~1A84I~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
                rc=true;                                           //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
        }                                                          //~1A84I~//~1A90I~
        if (!rc)                                                   //~1A84I~//~1A90I~
            AView.showToast(R.string.WarningWDNotConnected);   //~1A84I~//~1A90I~
        else                                                       //~1A84I~//~1A90I~
        {                                                          //~1A84I~//~1A90I~
            WDAipa=ipa;                                            //~1A84I~//~1A90I~
            WDAowner=owner;                                        //~1A84I~//~1A90I~
            WDApeer=deviceDetailFragment.peerDevice;               //~1A84I~//~1A90I~
            if (WDApeer==null)                                     //~1A84I~//~1A90I~
                WDApeer=WDAipa;                                    //~1A84I~//~1A90I~
        }                                                          //~1A84I~//~1A90I~
        return rc;  //not dismiss                                  //~1A84I~//~1A90I~
    }                                                              //~1A84I~//~1A90I~
    //**********************************                           //~1A84I~//~1A90I~
    private void updateButtonView(boolean Powner)                  //~1A84R~//~1A90I~
    {                                                              //~1A84I~//~1A90I~
        String ipa;                                                //~1A84I~//~1A90I~
        boolean connected,listening,paired;                               //~1A84I~//~1A87R~//~1A90I~
    //*****************************                                //~1A84I~//~1A90I~
		connected=AG.RemoteStatus==AG.RS_IPCONNECTED;              //~1A84I~//~1A90I~
		listening=AG.RemoteStatusAccept==AG.RS_IPLISTENING;        //~1A84I~//~1A90I~
		paired=deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED;//~1A87I~//~1A90I~
    	if (Dump.Y) Dump.println("WDA:updateButtonView remotestatus="+AG.RemoteStatus+",remotestatusaccept="+AG.RemoteStatusAccept);//~1A84R~//~1A90I~
    	if (connected)                                             //~1A84R~//~1A90I~
        {                                                          //~1A84I~//~1A90I~
			if (listening)                                         //~1A84I~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
        		btnAccept.setVisibility(View.GONE);                //~1A84R~//~1A90I~
        		btnStopAccept.setVisibility(View.VISIBLE);         //~1A84I~//~1A90I~
        		btnConnect.setVisibility(View.GONE);               //~1A84I~//~1A90I~
        		btnDisconnect.setVisibility(View.GONE);            //~1A84I~//~1A90I~
        		btnGame.setEnabled(false);                         //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
            else                                                   //~1A84I~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
        		btnAccept.setVisibility(View.GONE);                //~1A84I~//~1A90I~
        		btnStopAccept.setVisibility(View.GONE);            //~1A84I~//~1A90I~
        		btnConnect.setVisibility(View.GONE);               //~1A84I~//~1A90I~
        		btnDisconnect.setVisibility(View.VISIBLE);          //~1A84I~//~1A90I~
	        	btnGame.setEnabled(true);                          //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
        }                                                          //~1A84I~//~1A90I~
        else                                                       //~1A84I~//~1A90I~
        if (paired)                                                //~1A87I~//~1A90I~
        {                                                          //~1A84I~//~1A90I~
            if (Powner)               //paired server              //~1A84R~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
                AView.showToast(R.string.InfoWDOpenPort);      //~1A84I~//~1A90I~
                btnAccept.setVisibility(View.VISIBLE);             //~1A84I~//~1A90I~
        		btnStopAccept.setVisibility(View.GONE);            //~1A84I~//~1A90I~
                btnConnect.setVisibility(View.GONE);               //~1A84I~//~1A90I~
        		btnDisconnect.setVisibility(View.GONE);            //~1A84I~//~1A90I~
        		btnGame.setEnabled(false);                         //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
            else                                                   //~1A87I~//~1A90I~
            {                                                      //~1A84I~//~1A87M~//~1A90I~
		        ipa=deviceDetailFragment.ownerIPAddress;               //~1A84I~//~1A87I~//~1A90I~
                AView.showToast(R.string.InfoWDUseOwnerIP,ipa);//~1A84I~//~1A87M~//~1A90I~
                btnAccept.setVisibility(View.GONE);                //~1A84R~//~1A87M~//~1A90I~
        		btnStopAccept.setVisibility(View.GONE);            //~1A84I~//~1A87M~//~1A90I~
                btnConnect.setVisibility(View.VISIBLE);            //~1A84I~//~1A87M~//~1A90I~
        		btnDisconnect.setVisibility(View.GONE);            //~1A84I~//~1A87M~//~1A90I~
        		btnGame.setEnabled(false);                         //~1A84I~//~1A87M~//~1A90I~
            }                                                      //~1A84I~//~1A87M~//~1A90I~
        }                                                          //~1A87I~//~1A90I~
        else                                                       //~1A87I~//~1A90I~
        {                                                          //~1A87I~//~1A90I~
                btnAccept.setVisibility(View.GONE);                //~1A87I~//~1A90I~
        		btnStopAccept.setVisibility(View.GONE);            //~1A87I~//~1A90I~
                btnConnect.setVisibility(View.GONE);               //~1A87I~//~1A90I~
        		btnDisconnect.setVisibility(View.GONE);            //~1A87I~//~1A90I~
        		btnGame.setEnabled(false);                         //~1A87I~//~1A90I~
        }                                                          //~1A84I~//~1A90I~
    }                                                              //~1A84I~//~1A90I~
	//**********************************                           //~1A65I~
	@Override                                                      //~1A65I~
    protected boolean onClickHelp()                                //~1A65I~
    {                                                              //~1A65I~
//  	new HelpDialog(null,R.string.Help_WDA);                    //~1A65I~//~1Ad2R~
  		new HelpDialog(Global.frame(),R.string.HelpTitle_WDA,"wifidirect");//~1A8rR~//~1Ad2I~
        return false;	//not dismiss                              //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
    protected boolean onClickOther(int PbuttonId)                  //~1A65I~
    {                                                              //~1A65I~
        boolean dismiss=false;                                     //~1A65R~
        int rc;                                                    //~1A65I~
    //********************                                         //~1A65I~
    	if (Dump.Y) Dump.println("WDA:onClickOther buttonid="+Integer.toHexString(PbuttonId));//~1A84I~//~1A90I~
    	if (SWDA==null)                                            //~1A65R~
        	return false;                                          //~1A65I~
        try                                                        //~1A65I~
        {                                                          //~1A65I~
          switch(PbuttonId)                                        //~1A84I~//~1A90I~
          {                                                        //~1A84I~//~1A90I~
          case ID_ACCEPT:                                          //~1A84I~//~1A90I~
          case ID_CONNECT:                                         //~1A84I~//~1A90I~
          case ID_GAME:                                            //~1A84I~//~1A90I~
          case ID_STOPACCEPT:                                      //~1A84I~//~1A90I~
          case ID_DISCONNECT:                                      //~1A84I~//~1A90I~
            dismiss=buttonAction(PbuttonId);                       //~1A84I~//~1A90I~
            break;                                                 //~1A84I~//~1A90I~
          default:                                                 //~1A84I~//~1A90I~
            rc=deviceDetailFragment.buttonAction(PbuttonId);       //~1A65R~
            if (rc<0)   //not button of List                       //~1A65R~
                aWDActivity.buttonAction(PbuttonId);               //~1A65R~
            else                                                   //~1A65R~
                dismiss=(rc==1);                                   //~1A65R~
          }                                                        //~1A84I~//~1A90I~
        }                                                          //~1A65I~
        catch(Exception e)                                         //~1A65I~
        {                                                          //~1A65I~
        	Dump.println(e,"WDA:onClickOther");                    //~1A65I~
        }                                                          //~1A65I~
        return dismiss;                                            //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
    protected void onDismiss()                                     //~1A65I~
    {                                                              //~1A65I~
        aWDActivity.unregisterReceiver();                          //~1A65I~
        IPC.dismissWDA();                                          //~1A67I~
    }                                                              //~1A65I~

    /** register the BroadcastReceiver with the intent values to be matched */
//  @Override                                                      //~1A65R~
    public void onResume() {
//      super.onResume();                                          //~1A65R~
//      receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);//~1A65R~
//      registerReceiver(receiver, intentFilter);                  //~1A65R~
        if (SWDA==null)                                            //~1A65R~
        	return;                                                //~1A65I~
        aWDActivity.onResume();                                     //~1A65R~
    }

//  @Override                                                      //~1A65R~
    public void onPause() {
//      super.onPause();                                           //~1A65R~
//      unregisterReceiver(receiver);                              //~1A65R~
        if (SWDA==null)                                            //~1A65R~
        	return;                                                //~1A65I~
        aWDActivity.onPause();                                      //~1A65R~
    }

	//*******************************************************************************************************//~1A65I~
	public void connected()                                        //~1A65R~
    {                                                              //~1A65I~
    	if (Dump.Y) Dump.println("WDA:connected");                 //~1A65R~
//        if (AG.aWDANFC!=null)   //NFC activity alive                       //~1A6aI~//~1A81R~
//            AG.aWDANFC.connected(WDANFC.CONNECTED/*connected*/);   //~1A6aI~//~1A81R~
	}                                                              //~1A65I~
	//*******************************************************************************************************//~1A84I~//~1A90I~
	public void connected(boolean Powner)                          //~1A84I~//~1A90I~
    {                                                              //~1A84I~//~1A90I~
    	if (Dump.Y) Dump.println("WDA:connected owner="+Powner);   //~1A84I~//~1A90I~
        updateButtonView(Powner);	       //~1A84I~               //~1A90I~
        swOwner=Powner;                                            //~1A84I~//~1A90I~
	}                                                              //~1A84I~//~1A90I~
	//*******************************************************************************************************//~1A6aI~
	public void connectError()                                     //~1A6aI~
    {                                                              //~1A6aI~
    	if (Dump.Y) Dump.println("WDA:connectError");              //~1A6aI~
//        if (AG.aWDANFC!=null)   //NFC activity alive                       //~1A6aI~//~1A81R~
//            AG.aWDANFC.connected(WDANFC.CONNECTERR);                //~1A6aI~//~1A81R~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A65I~
	public void disconnected()                                     //~1A65I~
    {                                                              //~1A65I~
    	if (Dump.Y) Dump.println("WDA:diconnected");               //~1A65I~
        IPC.unpaired();                                             //~1A87I~//~1A90I~
	}                                                              //~1A65I~
	//******************************************************************************//~1A65I~
	public static DeviceDetailFragment getDeviceDetailFragment()   //~1A65I~
    {                                                              //~1A65I~
        return SWDA.deviceDetailFragment;                          //~1A65I~
    }                                                              //~1A65I~
	//******************************************************************************//~1A65I~
	public static DeviceListFragment getDeviceListFragment()       //~1A65I~
    {                                                              //~1A65I~
        return SWDA.deviceListFragment;                            //~1A65R~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
	public static WiFiDirectActivity getWDActivity()            //~1A65R~//~1A6aR~
    {                                                              //~1A65M~
        return SWDA.aWDActivity;                                               //~1A65R~
    }                                                              //~1A65M~
	//*******************************************************************************************************//~1A65I~
    private Resources getResources()                               //~1A65R~
    {                                                              //~1A65M~
        return AG.resource;                                        //~1A65M~
    }                                                              //~1A65M~
	//*******************************************************************************************************//~1A65I~
	public static String getResourceString(int Pid)                //~1A65I~
    {                                                              //~1A65I~
		return AG.resource.getString(Pid);                         //~1A65I~
	}                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
	public void setButtonListener(Button Pbtn)                     //~1A65I~
    {                                                              //~1A65I~
		super.setButtonListener(Pbtn);                             //~1A65I~
	}                                                              //~1A65I~
	//*******************************************************************************************************//~1A6aI~
	public void connect(String Pmacaddr)                           //~1A6aI~
    {                                                              //~1A6aI~
        deviceDetailFragment.connect(Pmacaddr);                    //~1A6aI~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
	//*on macaddr received side                                    //~1A6aI~
    //*rc:-1:not found,1:paired,0:not paired(do connect)           //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
	public int discover(WDANFC Pwdanfc,String Pmacaddr)            //~1A6aR~
    {                                                              //~1A6aI~
        NFCwaitingDiscover=null;                                   //~1A6aI~
        int rc=aWDActivity.discover(Pmacaddr,true/*issue discover when not paired*/);//~1A6aR~
        if (rc==-1) //not found                                    //~1A6aR~
        {                                                          //~1A6aI~
	        NFCwaitingDiscover=Pwdanfc;	//expect callback at dicovery end//~1A6aI~
	        NFCwaitingDiscoverAddr=Pmacaddr;	//expect callback at dicovery end//~1A6aI~
        }                                                          //~1A6aI~
    	if (Dump.Y) Dump.println("WDA:discover rc="+rc);           //~1A6aI~
        return rc;                                                 //~1A6aI~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
	//*on macaddr send side                                        //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
	public void discover()                                         //~1A6aI~
    {                                                              //~1A6aI~
        aWDActivity.discover();                                    //~1A6aI~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
//	public void peerUpdated()                                      //~1A6aI~//~1A90R~
	public void peerUpdated(int Ppeerctr)                                      //~1A6aI~//~1A87R~//~1A90I~
    {                                                              //~1A6aI~
    	int rc;                                                    //~1A6aR~
    	if (Dump.Y) Dump.println("WDA:peerUpdated ctr="+Ppeerctr); //~1A87R~//~1A90I~
    	if (NFCwaitingDiscover!=null)                              //~1A6aI~
        {                                                          //~1A6aI~
        	rc=aWDActivity.discover(NFCwaitingDiscoverAddr,false/*not issue discover when not paired*/);//~1A6aR~
            NFCwaitingDiscover.discovered(NFCwaitingDiscoverAddr,rc);//~1A6aR~
	    	NFCwaitingDiscover=null;	//response gotten          //~1A6aM~
        }                                                          //~1A6aI~
        updateButtonView(deviceListFragment.thisOwner==1);         //~1A87I~//~1A90I~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A84I~//~1A90I~
	private boolean buttonAction(int PbuttonId)                    //~1A84I~//~1A90I~
    {                                                              //~1A84I~//~1A90I~
    	if (!onClickClose())	//set partner info parm            //~1A84R~//~1A90I~
        	return false;                                          //~1A84I~//~1A90I~
    	boolean rc=IPC.buttonAction(PbuttonId);                    //~1A84I~//~1A90I~
    	if (Dump.Y) Dump.println("WDA:buttonAction rc="+rc);       //~1A84I~//~1A90I~
        return rc;                                                 //~1A84I~//~1A90I~
    }                                                              //~1A84I~//~1A90I~
	//*******************************************************************************************************//~1A87I~//~1A89I~
	//*from DeviceDetailFragment; before request unpair            //~1A87I~//~1A89I~
	//*******************************************************************************************************//~1A87I~//~1A89I~
	public boolean unpairRequest()                                   //~1A87I~//~1A89I~
    {                                                              //~1A87I~//~1A89I~
    	boolean rc=true;	//execute unpair at return             //~1A89I~
    	if (Dump.Y) Dump.println("WDA:unpairRequest remotestatus="+AG.RemoteStatus);//~1A87I~//~1A89I~
        if (AG.RemoteStatus==AG.RS_IPCONNECTED) //connection remains//~1A87I~//~1A89I~
        {                                                          //~1A87I~//~1A89I~
//          IPC.disconnectPartner();                               //~1A87I~//~1A89I~
            AlertI ai=new AlertI()                                 //~1A89R~
                                {                                  //~1A89I~
                                    @Override                      //~1A89I~
                                    public int alertButtonAction(int Pbuttonid,int Pitempos)//~1A89I~
                                    {                              //~1A89I~
                                    	if (Dump.Y) Dump.println("WDA:unpairRequest buttonid="+Integer.toHexString(Pbuttonid));//~1A89I~
                                        if (Pbuttonid==Alert.BUTTON_POSITIVE)//~1A89R~
                                        {                          //~1Ac2I~
//  										IPC.disconnectPartner();//~1A89I~//~1Ac3R~
//                                      deviceDetailFragment.doUnpair();//~1A89I~//~1Ac3R~
    										IPC.disconnectPartner(true/*unpair after closed*/);//~1Ac3I~
                                        }                          //~1Ac2I~
                                        return 1;                  //~1A89I~
                                    }                              //~1A89I~
                                };                                 //~1A89I~
            Alert.simpleAlertDialog(ai,ALERT_TITLE_DISCONNECT,ALERT_MSG_DISCONNECT,//~1A89R~
                                Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE);//~1A89R~
	    	rc=false;	//execute unpair at alaer dialog response  //~1A89I~
        }                                                          //~1A87I~//~1A89I~
    	if (Dump.Y) Dump.println("WDA:unpairRequest rc="+rc);      //~1A89I~
        return rc;                                                 //~1A89I~
    }                                                              //~1A87I~//~1A89I~
	//*******************************************************************************************************//~1Ac3I~
	//*from PartnerThread by IPC.disconnectPartner(unpair=true) at thread terminate//~1Ac3I~
	//*******************************************************************************************************//~1Ac3I~
	public static void unpairFromPT()                              //~1Ac3I~
    {                                                              //~1Ac3I~
    	if (Dump.Y) Dump.println("WDA:unpairFromPT");              //~1Ac3I~
        if (SWDA!=null)                                            //~1Ac3I~
        {                                                          //~1Ac3I~
	        URunnable.setRunFunc(SWDA/*URunnableI*/,0/*delay*/,SWDA/*objparm*/,0/*int parm*/);//~1Ac3I~
        }                                                          //~1Ac3I~
    }                                                              //~1Ac3I~
//***************************************************************  //~1Ac3I~
	@Override                                                      //~1Ac3I~
	public void runFunc(Object parmObj,int parmInt/*0*/)           //~1Ac3I~
    {                                                              //~1Ac3I~
        if (Dump.Y) Dump.println("WDA:runfunc");                   //~1Ac3I~
      	try                                                        //~1Ac3I~
      	{                                                          //~1Ac3I~
			deviceDetailFragment.doUnpair();                       //~1Ac3I~
      	}                                                          //~1Ac3I~
      	catch(Exception e)                                         //~1Ac3I~
      	{                                                          //~1Ac3I~
      		Dump.println(e,"WDA:runfunc");                         //~1Ac3I~
      	}                                                          //~1Ac3I~
    }                                                              //~1Ac3I~
////***************************************************************  //~1Ac4I~//~va40R~
////*disable wifidirect                                              //~1Ac4I~//~va40R~
////***************************************************************  //~1Ac4I~//~va40R~
//    public static boolean setWifiDirectStatus(boolean Penable)     //~1Ac4I~//~va40R~
//    {                                                              //~1Ac4I~//~va40R~
//        if (Dump.Y) Dump.println("setWWifiDirectState parmenable="+Penable);//~1Ac4I~//~va40R~
//        wifiApManager mgr=SWDA.new wifiApManager();                //~1Ac4I~//~va40R~
//        if (mgr.swNoMethod)                                        //~1Ac4I~//~va40R~
//            return false;                                          //~1Ac4I~//~va40R~
//        return mgr.setState(Penable);                              //~1Ac4I~//~va40R~
//    }                                                              //~1Ac4I~//~va40R~
//    class wifiApManager                                            //~1Ac4I~//~va40R~
//    {                                                              //~1Ac4I~//~va40R~
//        private final WifiManager wifiManager;                     //~1Ac4I~//~va40R~
//        private Method ctlMethod,cfgMethod,statMethod;             //~1Ac4I~//~va40R~
//        public boolean swNoMethod;                                 //~1Ac4I~//~va40R~
//        public wifiApManager()                                     //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            wifiManager=(WifiManager)AG.context.getSystemService(Context.WIFI_SERVICE);//~1Ac4I~//~va40R~
//            try                                                    //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                ctlMethod=wifiManager.getClass().getMethod("setWifiApEnabled",WifiConfiguration.class,boolean.class);//~1Ac4I~//~va40R~
//                cfgMethod=wifiManager.getClass().getMethod("getWifiApConfiguration");//~1Ac4I~//~va40R~
//                statMethod=wifiManager.getClass().getMethod("getWifiApState");//~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            catch (NoSuchMethodException e)                        //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                Dump.println(e,"WDA.wifiApManager constructor");   //~1Ac4I~//~va40R~
//                e.printStackTrace();                               //~1Ac4I~//~va40R~
//                swNoMethod=true;                                   //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            if (Dump.Y) Dump.println("WDA.wifiApManager:wifiApManager ctlM="+ctlMethod+",cfgMethod="+cfgMethod+",staeMethod="+statMethod);//~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        private WifiConfiguration getCfg()                         //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            try                                                    //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                return (WifiConfiguration)cfgMethod.invoke(wifiManager);//~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            catch(Exception e)                                     //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                Dump.println(e,"WDA:wifiApManager.getCfg");        //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            return null;                                           //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        public boolean setState(boolean Penable)                   //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            WifiConfiguration cfg=getCfg();                        //~1Ac4I~//~va40R~
//            if (cfg!=null)                                         //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                return setState(cfg,Penable);                      //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            return false;                                          //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        public boolean setState(WifiConfiguration Pcfg,boolean Penable)//~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            try                                                    //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                int stat=(Integer)statMethod.invoke(wifiManager);  //~1Ac4I~//~va40R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState before parm enable="+Penable+",wifi isenable="+wifiManager.isWifiEnabled()+",getstate="+wifiManager.getWifiState()+",apstat="+stat);//~1Ac4I~//~va40R~
////              wifiManager.setWifiEnabled(!Penable);  //disable wifi case p2p disable//~1Ac4I~//~va40R~
//                boolean rc=(Boolean)ctlMethod.invoke(wifiManager,Pcfg,Penable);//~1Ac4I~//~va40R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState after parm enable="+Penable+",wifi isenable="+wifiManager.isWifiEnabled()+",getstate="+wifiManager.getWifiState()+",apstat="+stat);//~1Ac4I~//~va40R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState rc="+rc);//~1Ac4I~//~va40R~
//                return rc;                                         //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            catch(Exception e)                                     //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                Dump.println(e,"WDA:wifiApManager.getCfg");        //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            return false;                                          //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//    }                                                              //~1Ac4I~//~va40R~
////***************************************************************  //~1Ac4I~//~va40R~
////*disable wifidirect 2'nd way                                     //~1Ac4I~//~va40R~
////***************************************************************  //~1Ac4I~//~va40R~
//    public static boolean setWifiP2pState(boolean Penable)         //~1Ac4I~//~va40R~
//    {                                                              //~1Ac4I~//~va40R~
//        boolean rc=false;                                          //~1Ac4I~//~va40R~
//        if (Dump.Y) Dump.println("setWifiP2pState parmenable="+Penable);//~1Ac4I~//~va40R~
//        WifiP2pManager mgr=SWDA.aWDActivity.manager;               //~1Ac4I~//~va40R~
//        Channel channel=SWDA.aWDActivity.channel;                  //~1Ac4I~//~va40R~
//        Method method;                                             //~1Ac4I~//~va40R~
//        try                                                        //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            if (Penable)                                           //~1Ac4I~//~va40R~
//                method=mgr.getClass().getMethod("enableP2p",Channel.class);//~1Ac4I~//~va40R~
//            else                                                   //~1Ac4I~//~va40R~
//                method=mgr.getClass().getMethod("disableP2p",Channel.class);//~1Ac4I~//~va40R~
//            method.invoke(mgr,channel);                            //~1Ac4I~//~va40R~
//            rc=true;                                               //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        catch (Exception e)                                        //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            Dump.println(e,"WDA:setWifiP2pState");                 //~1Ac4I~//~va40R~
//            e.printStackTrace();                                   //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        return rc;                                                 //~1Ac4I~//~va40R~
//    }                                                              //~1Ac4I~//~va40R~
//**********************************                               //~v@@@I~//~1AhdI~
    public static boolean chkGranted()                                   //~v@@@I~//~1AhdI~
    {                                                              //~v@@@I~//~1AhdI~
        if (Dump.Y) Dump.println("WDA.chkGranted");  //~v@@@I~     //~1AhdI~
		boolean rc= UView.isPermissionGrantedLocation();                    //~v@@@I~//~1AhdI~
        if (!rc)                                                   //~v@@@I~//~1AhdI~
        {                                                           //~v@@@I~//~1AhdI~
		    UView.requestPermissionLocation(AMain.PERMISSION_LOCATION);//~v@@@I~//~1AhdI~
        }                                                          //~v@@@I~//~1AhdI~
        if (Dump.Y) Dump.println("WDA.chkGranted rc="+rc);      //~v@@@I~//~1AhdI~
        return rc;                                                 //~v@@@I~//~1AhdI~
    }                                                              //~v@@@I~//~1AhdI~
//**********************************                               //~v@@@I~//~1AhdI~
    public static void grantedWifi(boolean PswGranted)             //~v@@@I~//~1AhdI~
    {                                                              //~v@@@I~//~1AhdI~
    	boolean rc;                                                //~v@@@I~//~1AhdI~
        if (Dump.Y) Dump.println("WD.grantedWifi PswGranted="+PswGranted);//~v@@@I~//~1AhdI~
        if (!PswGranted)                                           //~v@@@I~//~1AhdI~
        {                                                          //~v@@@I~//~1AhdI~
//        	MainView.drawMsg(R.string.WifiDirectRequiresGranted);  //~v@@@I~//~1AhdI~
          	UView.showToastLong(R.string.WifiDirectRequiresGranted);//~1AhdI~
            return;                                                //~v@@@I~//~1AhdI~
        }	                                                       //~v@@@I~//~1AhdI~
		UView.showToast(R.string.WifiDirectGranted);               //~v@@@I~//~1AhdI~
//    	connectWifiDirectGranted();                                //~v@@@I~//~1AhdI~
    }                                                              //~v@@@I~//~1AhdI~
}
