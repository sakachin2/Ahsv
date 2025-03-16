//*CID://+1ap1R~:                             update#=  181;       //+1ap1R~
//*****************************************************************//~v101I~
//1ap1 2025/03/13 NFC;setNdefPushMessageCallback is undefined(NFC is deprecated at api29, and api34 removed this api)//+1ap1I~
//1Ahk 2020/06/05 Connect button for all connection type           //~1AhkI~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import com.Ahsv.AG;
import com.Ahsv.R;
import com.Ahsv.Utils;
import com.btmtest.utils.UView;

import jagoclient.Dump;
import jagoclient.MainFrame;

import static com.btmtest.dialog.UMenuDlg.*;

public class MenuDlgConnect                                        //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Connect;             //~v@@@I~
    private static final int HELP_TITLEID=TITLEID;                 //~v@@@I~
    private static final String HELPFILE="MenuDlgConnect";         //~v@@@R~
                                                                   //~v@@@I~
    private static final int MENU_ITEMS=R.array.MenuDialog_Connect;//~v@@@I~
    private static String[] itemNames;                             //~1AhkI~
//    private static final int MENU_BLUETOOTH=0;                    //~v@@@I~//~1AhkR~
//    private static final int MENU_WIFIDIRECT=1;                   //~v@@@I~//~1AhkR~
//    private static final int MENU_HELP=2;                         //~v@@@I~//~1AhkR~
//    private static final int MENU_CANCEL=3;                       //~v@@@I~//~1AhkR~
    private static final int MENU_LOCAL=0;                         //~1AhkI~
    private static final int MENU_BT=1;                            //~1AhkI~
    private static final int MENU_IP=2;                            //~1AhkI~
    private static final int MENU_WD=3;                            //~1AhkI~
//  private static final int MENU_NFCBT=4;                         //~1AhkR~//+1ap1R~
//  private static final int MENU_NFCWF=5;                         //~1AhkR~//+1ap1R~
//  private static final int MENU_HELP=6;                          //~1AhkI~//+1ap1R~
//  private static final int MENU_CANCEL=7;                        //~1AhkR~//+1ap1R~
    private static final int MENU_HELP=4;                          //+1ap1I~
    private static final int MENU_CANCEL=5;                        //+1ap1I~
                                                                   //~v@@@I~
	private UMenuDlg umdlg;                                        //~v@@@I~
	private UMenuDlg.UMenuDlgI listener;                                    //~v@@@I~
	private int menuid,titleid,itemsid;                                            //~v@@@I~
	private MainFrame MF;                                          //~1AhkI~
//**********************************                               //~v@@@I~
    public MenuDlgConnect()                                        //~v@@@R~
    {                                                              //~v@@@I~
	    itemNames= Utils.getStrArray(MENU_ITEMS);                   //~1AhkI~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static MenuDlgConnect newInstance(int Ptitleid,int Pitemsid)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.newInstance strarrayid="+Integer.toHexString(Pitemsid));//~v@@@R~
    	MenuDlgConnect menuDialog=new MenuDlgConnect();            //~v@@@R~
        menuDialog.titleid=Ptitleid;                               //~v@@@I~
        menuDialog.itemsid=Pitemsid;                               //~v@@@I~
		return menuDialog;
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show(UMenuDlg.UMenuDlgI Plistener,int Pmenuid)               //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.show menuid="+Pmenuid);//~v@@@R~
        listener=Plistener;                                        //~v@@@I~
        umdlg=UMenuDlg.show(this,Pmenuid,titleid,itemsid);         //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
//*from Main                                                       //~v@@@I~
//**********************************                               //~v@@@I~
	public static void showMenu()                                  //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.showMenu");       //~v@@@R~
        MenuDlgConnect dlg=MenuDlgConnect.newInstance(TITLEID,MENU_ITEMS);//~v@@@R~
        dlg.show(null/*listener*/,MENUID_CONNECT);                 //~v@@@R~
    }                                                              //~v@@@I~
	public static void showMenu(MainFrame Pparent)                 //~1AhkI~
    {                                                              //~1AhkI~
    	if (Dump.Y) Dump.println("MenuDlgConnect.showMenu from MainFrame");//~1AhkI~
        MenuDlgConnect dlg=MenuDlgConnect.newInstance(TITLEID,MENU_ITEMS);//~1AhkI~
        dlg.MF=Pparent;                                            //~1AhkI~
        dlg.show(null/*listener*/,MENUID_CONNECT);                 //~1AhkI~
    }                                                              //~1AhkI~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.onDestroy");      //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@M~
	@Override                                                      //~v@@@I~
    public boolean menuItemSelectedMulti(int Pmenuid,boolean[] PselectedId)//~v@@@R~
    {                                                              //~v@@@I~
    //*not called if singleselectitem                              //~v@@@I~
    	return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void menuItemSelected(int Pmenuid,int Pidx,String Pitem)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.menuItemSelected menuid="+Pmenuid+",item="+Pitem+",listener!=null="+(listener!=null));//~v@@@R~
        if (listener!=null)                                        //~v@@@I~
        {                                                          //~v@@@I~
        	listener.menuItemSelected(Pmenuid,Pidx,Pitem);         //~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	switch(Pmenuid)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case MENUID_CONNECT:                                       //~v@@@R~
        	menuConnect(Pidx);                                     //~v@@@R~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
    private void menuConnect(int Pidx)                             //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.menuConnect idx="+Pidx+",itemNames="+Utils.toString(itemNames));//~v@@@R~//~1AhkR~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            switch(Pidx)                                           //~v@@@R~//~1AhkR~
            {                                                      //~v@@@R~//~1AhkR~
//          case MENU_BLUETOOTH:                                   //~v@@@R~//~1AhkR~
//              connectBluetooth();                                //~v@@@R~//~1AhkR~
//              break;                                             //~v@@@R~//~1AhkR~
//          case MENU_WIFIDIRECT:                                  //~v@@@I~//~1AhkR~
//              connectWifiDirect();                               //~v@@@I~//~1AhkR~
//              break;                                             //~v@@@I~//~1AhkR~
            case MENU_HELP:                                        //~v@@@R~//~1AhkR~
                help();                                            //~v@@@I~//~1AhkR~
                break;                                             //~v@@@M~//~1AhkR~
            case MENU_CANCEL:                                      //~1AhkI~
                break;                                             //~1AhkI~
            default:    //Cancel                                   //~v@@@I~//~1AhkR~
//              umdlg.dismiss();                                   //~v@@@I~//~1AhkR~
				MF.doAction(itemNames[Pidx]);                      //~1AhkI~
            }                                                      //~v@@@R~//~1AhkR~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"MenuDlgConnect:menuConnect idx="+Pidx);//~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void help()                                            //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.help");           //~v@@@I~
    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE).showDlg();      //~v@@@R~//~1AhkR~
    }                                                              //~v@@@I~
////**********************************                               //~v@@@I~//~1AhkR~
//    private void connectBluetooth()                                //~v@@@I~//~1AhkR~
//    {                                                              //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDlgConnect.connectBluetooth");//~v@@@I~//~1AhkR~
//        if (chkMixModeErr(CM_BT/*BT*/))                            //~v@@@R~//~1AhkR~
//            return;                                                //~v@@@I~//~1AhkR~
//        AG.aBTMulti.onConnect();                                   //~v@@@I~//~1AhkR~
//    }                                                              //~v@@@I~//~1AhkR~
////**********************************                               //~v@@@I~//~1AhkR~
//    private void connectWifiDirect()                               //~v@@@I~//~1AhkR~
//    {                                                              //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDlgConnect.connectWifiDirect");//~v@@@I~//~1AhkR~
//        if (!chkGranted())                                         //~v@@@I~//~1AhkR~
//            return;                                                //~v@@@I~//~1AhkR~
//        if (chkMixModeErr(CM_WD/*WD*/))                            //~v@@@R~//~1AhkR~
//            return;                                                //~v@@@I~//~1AhkR~
////      new IPConnectionWD();                                      //~v@@@R~//~1AhkR~
//        WDI.startRemoteIP();                                       //~v@@@I~//~1AhkR~
//    }                                                              //~v@@@I~//~1AhkR~
////***************************************************************  //~v@@@I~//~1AhkR~
////*from MainActivity onRequestPermissionResult                     //~v@@@I~//~1AhkR~
////***************************************************************  //~v@@@I~//~1AhkR~
//    private static void connectWifiDirectGranted()                 //~v@@@I~//~1AhkR~
//    {                                                              //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDlgConnect.connectWifiDirectGranted");//~v@@@I~//~1AhkR~
//        if (chkMixModeErr(CM_WD/*WD*/))                            //~v@@@I~//~1AhkR~
//            return;                                                //~v@@@I~//~1AhkR~
//        WDI.startRemoteIP();                                       //~v@@@I~//~1AhkR~
//    }                                                              //~v@@@I~//~1AhkR~
////**********************************                               //~v@@@I~//~1AhkR~
//    private static boolean chkMixModeErr(int Pmode)                //~v@@@R~//~1AhkR~
//    {                                                              //~v@@@I~//~1AhkR~
//        boolean rc=false;                                          //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDlgConnect.chkMixMode mode="+Pmode);//~v@@@I~//~1AhkR~
//        Members m=AG.aBTMulti.BTGroup;                              //~v@@@I~//~1AhkR~
//        if (m!=null)                                               //~v@@@I~//~1AhkR~
//        {                                                          //~v@@@I~//~1AhkR~
//            int[] ctrs=new int[4];                                 //~v@@@I~//~1AhkR~
//            m.getConnectionModeCtr(ctrs);                          //~v@@@I~//~1AhkR~
//            if (ctrs[CM_WD]!=0)                                    //~v@@@I~//~1AhkR~
//                AG.activeSessionType=AST_WD;                           //~1A8gI~//~9719I~//~v@@@I~//~1AhkR~
//            else                                                   //~v@@@I~//~1AhkR~
//            if (ctrs[CM_BT]!=0)                                    //~v@@@I~//~1AhkR~
//                AG.activeSessionType=AST_BT;                       //~v@@@I~//~1AhkR~
//            else                                                   //~v@@@I~//~1AhkR~
//                AG.activeSessionType=AST_NONE;                     //~v@@@I~//~1AhkR~
//            switch(Pmode)                                          //~v@@@I~//~1AhkR~
//            {                                                      //~v@@@I~//~1AhkR~
//            case CM_BT: //bt                                       //~v@@@R~//~1AhkR~
////              if (ctrs[CM_WD]!=0)                                //~v@@@R~//~1AhkR~
//                if (AG.activeSessionType==AST_WD)                  //~v@@@I~//~1AhkR~
//                {                                                  //~v@@@I~//~1AhkR~
//                    UView.showToast(R.string.ErrMixedModeRemainWD); //~v@@@I~//~1AhkR~
//                    rc=true;                                       //~v@@@I~//~1AhkR~
//                }                                                  //~v@@@I~//~1AhkR~
//                AG.activeSessionType=AST_BT;                      //~v@@@I~//~1AhkR~
//                break;                                             //~v@@@I~//~1AhkR~
//            case CM_WD: //wifidirect                               //~v@@@R~//~1AhkR~
////              if (ctrs[CM_BT]!=0)                                //~v@@@R~//~1AhkR~
//                if (AG.activeSessionType==AST_BT)                  //~v@@@I~//~1AhkR~
//                {                                                  //~v@@@I~//~1AhkR~
//                    UView.showToast(R.string.ErrMixedModeRemainBT); //~v@@@I~//~1AhkR~
//                    rc=true;                                       //~v@@@I~//~1AhkR~
//                }                                                  //~v@@@I~//~1AhkR~
//                AG.activeSessionType=AST_WD;                      //~v@@@I~//~1AhkR~
//                break;                                             //~v@@@I~//~1AhkR~
//            }                                                      //~v@@@I~//~1AhkR~
//        }                                                          //~v@@@I~//~1AhkR~
//        return rc;                                               //~1AhkR~
//    }                                                              //~v@@@I~//~1AhkR~
////**********************************                               //~v@@@I~//~1AhkR~
//    private boolean chkGranted()                                   //~v@@@I~//~1AhkR~
//    {                                                              //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDialogConnect.chkGranted");  //~v@@@I~//~1AhkR~
////      boolean showToast=false;                                   //~v@@@R~//~1AhkR~
//        boolean rc=UView.isPermissionGrantedLocation();                    //~v@@@I~//~1AhkR~
//        if (!rc)                                                   //~v@@@I~//~1AhkR~
//        {                                                           //~v@@@I~//~1AhkR~
////          if (UView.isPermissionDeniedLocation()) //user replayed No//~v@@@R~//~1AhkR~
////              showToast=true;                                    //~v@@@R~//~1AhkR~
//            UView.requestPermissionLocation(MainActivity.PERMISSION_LOCATION);//~v@@@I~//~1AhkR~
////          if (showToast)                                         //~v@@@R~//~1AhkR~
////              UView.showToastLong(R.string.WifiDirectRequiresGranted);//~v@@@R~//~1AhkR~
//        }                                                          //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDialogConnect rc="+rc);      //~v@@@I~//~1AhkR~
//        return rc;                                                 //~v@@@I~//~1AhkR~
//    }                                                              //~v@@@I~//~1AhkR~
////**********************************                               //~v@@@I~//~1AhkR~
//    public static void grantedWifi(boolean PswGranted)             //~v@@@I~//~1AhkR~
//    {                                                              //~v@@@I~//~1AhkR~
//        boolean rc;                                                //~v@@@I~//~1AhkR~
//        if (Dump.Y) Dump.println("MenuDialogConnect PswGranted="+PswGranted);//~v@@@I~//~1AhkR~
//        if (!PswGranted)                                           //~v@@@I~//~1AhkR~
//        {                                                          //~v@@@I~//~1AhkR~
////          MainView.drawMsg(R.string.WifiDirectNotGranted);       //~v@@@R~//~1AhkR~
////          UView.showToastLong(R.string.WifiDirectRequiresGranted);//~v@@@R~//~1AhkR~
//            MainView.drawMsg(R.string.WifiDirectRequiresGranted);  //~v@@@I~//~1AhkR~
//            return;                                                //~v@@@I~//~1AhkR~
//        }                                                          //~v@@@I~//~1AhkR~
//        UView.showToast(R.string.WifiDirectGranted);               //~v@@@I~//~1AhkR~
//        connectWifiDirectGranted();                                //~v@@@I~//~1AhkR~
//    }                                                              //~v@@@I~//~1AhkR~
}//class                                                           //~v@@@R~
