//*CID://+1AmsR~:                             update#=  224;       //+1AmsR~
//******************************************************************************************************************//~v101R~
//1ams 2022/11/01 control request permission to avoid 1amh:"null permission result".(W Activity: Can request only one set of permissions at a time)//+1AmsI~
//1am9 2022/10/30 android12(api31) Bluetooth permission is runtime permission//~1Am9I~
//1am3 2022/10/29 a0droid12(api31) Display.getRealSize, getRealMetrics//~1Am3I~
//1ak3 2021/09/10 picker(ACTION_PICK) for API30                    //~1Ak3I~
//1ak2 2021/09/04 access external audio file                       //~1Ak2I~
//1ak0 2021/08/26 androd11:externalStorage:ScopedStorage           //~1Ak0I~
//1Ahj 2020/06/05 IP io on mainthread fails by android.os.NetworkOnMainThreadException//~1AhjI~
//1Ah2 2020/05/31 for Android9(Pie)-api28(PlayStore requires),deprected. DialogFragment,Fragmentmanager//~1Ah2I~
//1Ah1 2020/05/30 from BTMJ5                                       //~1Ah1I~
//1Ad7 2015/07/20 Canvas/UiThread TraceOption was not effective if OptionDialog is opened//~1Ad7I~
//1Ad6 2015/07/20 OutOfMemory investigation                        //~1Ad6I~
//1Ad2 2015/07/17 HelpDialog by helptext                           //~1Ad2I~
//1Abg 2015/06/15 NFCBT:transfer to NFCBT or NFCWD if active session exist//~1AbgI~
//1Ab7 2015/05/03 NFC Bluetooth handover v2                        //~1Ab7I~
//1A8g 2015/03/05 chk only one session alive(Ip,Direct,BT)         //~1A8gI~
//1A8fk2015/03/01 display remote IP address                        //~1A8fI~
//1A8ck2015/03/01 extends PartnerFrame/PartnerThread to wifidirect //~1A8cI~
//1A85 2015/02/25 close each time partnerframe for IP Connection   //~1A85I~
//1A81 2015/02/24 ANFC is not used now                             //~1A81I~
//1A6A 2015/02/20 Another Trace option if (Dump.C) for canvas drawing, (Dump.T) for UiThread//~1A6AI~
//1A6s 2015/02/17 move NFC starter from WifiDirect dialog to MainFrame//~1A6sI~
//1A6p 2015/02/16 display.getWidth()/getHeight() was deprecated at api13,use getSize(Point)//~1A6pI~
//1A6k 2015/02/15 re-open IPConnection/BTConnection dialog when diconnected when dislog is opened.//~1A6kI~
//1A6c 2015/02/13 Bluetooth;identify paired device and discovered device//~1A6cI~
//1A6a 2015/02/10 NFC+Wifi support                                 //~1A6aI~
//1A61 2015/01/27 avoid to fill screen when listview has few entry.(Motorolla is dencity=1.0(mdpi)//~1A61I~
//1A50 2014/10/27 mdpi & tablet support                            //~1A50I~
//1A35 2013/04/19 show mark of last moved from position            //~1A35I~
//1A13 2013/03/10 1touch option                                    //~1A13I~
//1023 2013/03/02 use not japanese numeral but digit if not JP     //~1023I~
//1021 2013/02/15 (BUG) isLangJP set err                           //~v102I~
//1078:121208 add "menu" to option menu if landscape               //~v107I~
//1077:121208 control greeting by app start counter                //~v107I~
//1075:121207 control dumptrace by manifest debuggable option      //~v107I~
//1071:121204 partner connection using Bluetooth SPP               //~v107I~
//1067:121128 GMP connection NPE(currentLayout is intercepted by showing dialog:GMPWait)//~v106I~
//            doAction("play")-->gotOK(new GMPGoFrame) & new GMPWait()(MainThread)//~v106I~
//v101:120514 (Axe)android3(honeycomb) tablet has System bar at bottom that hide xe button line with 48pix height//~v101I~
//******************************************************************************************************************//~v101I~
//*Ajago Globals *****                                             //~1107I~
//********************                                             //~1107I~
package com.Ahsv;                                                    //~1108R~//~1109R~//~v107R~

import java.util.Locale;
import java.util.Properties;

//import android.app.FragmentManager;                              //~1Ah2R~
//import android.support.v4.app.FragmentManager;                   //~1Ah2R~
//import android.support.v4.app.Fragment;                          //~1Ah2R~
import android.os.Build;                                           //~vab0R~//~v101I~
                                                                   import com.Ahsv.awt.Color;
import com.Ahsv.awt.Component;
import com.Ahsv.awt.Dialog;                                        //~v107R~
import com.Ahsv.awt.Font;
import com.Ahsv.awt.Frame;                                         //~v107R~
import com.Ahsv.awt.Window;                                        //~v107R~

import jagoclient.partner.BluetoothConnection;
import jagoclient.partner.IPConnection;
import jagoclient.partner.partner.MsgThread;
//import com.Ahsv.jagoclient.partner.PartnerFrame;                    //~@@@@I~//~1A8gR~
import com.Ahsv.R;                                               //~1120I~//~v107R~
import com.btmtest.utils.UMediaStore;
import com.btmtest.utils.UScoped;
import com.btmtest.utils.UPermission;                              //+1AmsI~

import wifidirect.WDANFC;
import wifidirect.IPSubThread;                               //~@@01I~//~1AhjR~

import jagoclient.Dump;
import jagoclient.MainFrameOptions;                                //~1Ad7I~
import jagoclient.Go;
import jagoclient.dialogs.SayDialog;
//import jagoclient.igs.ConnectionFrame;                           //~@@@@R~
//import jagoclient.igs.games.GamesFrame;                          //~@@@@R~
import android.app.Activity;                                       //~1Ah2R~
//import android.support.v7.app.AppCompatActivity;                 //~1Ah2R~
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
//import android.support.v4.app.FragmentActivity;                  //~1Ah2R~
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import androidx.core.content.ContextCompat;                        //~1Ak3I~


class IdTblEntry                                                   //~1120I~
{                                                                  //~1120I~
    String name; int id;                                           //~1120I~
    public IdTblEntry(String Pname,int Pid)                        //~1120I~
    {                                                              //~1120I~
        name=Pname; id=Pid;                                        //~1120I~
    }                                                              //~1120I~
}                                                                  //~1120I~
//**************************                                       //~1120I~
public class AG                                                    //~1107R~
{                                                                  //~1109R~
    public static final int ACTIVITY_REQUEST_ENABLE_BT = 2;       //~1A6aI~
    public static final int ACTIVITY_REQUEST_NFCBEAM   = 3;       //~1A6aI~
    public static final int ACTIVITY_REQUEST_PICKUP_AUDIO   = 10;  //~1Ak2I~
    public static final int ACTIVITY_REQUEST_SCOPED    = 100;      //~1Ak0I~
    public static final int ACTIVITY_REQUEST_SCOPED_OPEN_TREE = ACTIVITY_REQUEST_SCOPED+1;//~1Ak0I~
    public static final int ACTIVITY_REQUEST_SCOPED_LAST=110;      //~1Ak0I~
                                                                   //~1A6aI~
    public final static String PKEY_PIECE_TYPE="PieceType";        //~@@@@R~
    public final static String PKEY_BOARD_SIZE="BoardSize";       //~@@@@I~
    public static final String PKEY_OPTIONS="Options";             //~@@@@I~
    public static final String PKEY_YOURNAME="YourName";           //~@@@@I~
    public static final String PKEY_BGM_STRURI="BGMStrUri";        //~1Ak2I~
    public static final String PKEY_BGM_TITLE="BGMTitle";          //~1Ak2I~
    public static final Color cursorColor=new Color(0x00,0xff,0x20);//~@@@2I~//~@@@@R~
	public static final Color selectedColor=new Color(0x00,0x20,0xff);//~@@@2R~//~@@@@R~
    public static final Color capturedColor=new Color(0xff,0x00,0x00);//~@@@@I~//~@@@2I~//~@@@@I~
    public static final Color lastPosColor=new Color(0xff,0x00,0x80);//~@@@@R~
	public static final Color lastFromColor=new Color(0x60,0x60,0x00);//~1A35I~
                                                                   //~@@@@I~
//    public static final String DEBUGTRACE_CFGKEY  ="debugtrace"; //~@@@@R~
                                                                   //~@@@@I~
//  public final static String SshogiV="\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d";//~@@@@I~//~1023R~
    public final static String SshogiVJ="\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d";//~1023I~
	public final static String SshogiVE="abcdefghi";               //~1023I~
	public       static String SshogiV;                            //~1023I~
	public final static String SshogiH="987654321";                //~@@@@I~
	public final static String SchessV="87654321";                 //~@@@@I~
	public final static String SchessH="abcdefgh";                 //~@@@@R~
    public static final String COMMON_BUTTON_NAME="Button"; //Butonnxx//~@@@@I~
    public static final int BOARDSIZE_SHOGI=9;                     //~@@@@R~
    public static final int BOARDSIZE_CHESS=8;                     //~@@@@I~
    public static final int EXTRA_MOVE=1;      //move per extratime//~@@@@I~
    public static       int propBoardSize;                         //~@@@@R~
    public static       Color chessBoardBlack=new Color(0x90,0x60,0x40);//~@@@@R~
    public static       Color chessBoardWhite=new Color(0xE6,0xD4,0xAE);//~@@@@I~
                                                                   //~@@@@I~
    public static int Options;                                     //~@@@@I~
    public static final int OPTIONS_TIMER_IN_TITLE      	=0x0001;//~@@@@R~
    public static final int OPTIONS_BIG_TIMER           	=0x0002;//~@@@@R~
    public static final int OPTIONS_NOSOUND             	=0x0004;//~@@@@R~
    public static final int OPTIONS_BEEP_ONLY           	=0x0008;//~@@@@R~
    public static final int OPTIONS_TIMER_WARNING       	=0x0010;//~@@@@R~
    public static final int OPTIONS_SHOW_LAST           	=0x0020;//~@@@@R~
    public static final int OPTIONS_COORDINATE          	=0x0040;//~@@@@R~
    public static final int OPTIONS_TRACE               	=0x0080;//~@@@@R~
    public static final int OPTIONS_GOFRAME_CLOSE_CONFIRM 	=0x0100;//~@@@@I~
    public static final int OPTIONS_1TOUCH                	=0x2000;  //1touch on freeboard//~1A13I~
    public static final int OPTIONS_TRACE_CANVAS           	=0x4000;//~1A6AI~
    public static final int OPTIONS_TRACE_UITHREAD         	=0x8000;//~1A6AI~
    public static final int OPTIONS_BGM                   =0x010000;//~1Ak2I~
    public static String YourName,LocalOpponentName;                                                               //~@@@@I~
    public static String language;                                 //~1531I~//~@@@@I~
    public static String Glocale;                                  //~@@@@I~
    public static boolean isLangJP;                                //~@@@@I~
    public static String helpFileSuffix;                           //~1Ah1R~
    public static String dirSep;                                   //~1Ah1R~
 	public static boolean isDebuggable;                            //~v107I~
 	public static int testdump=0;	//@@@@test                     //~1301I~
   	public static Activity activity;                                 //~1109I~//~1111R~//~1Ah2R~
// 	public static AppCompatActivity activity;                      //~1Ah2R~
 	public static Context context;                                 //~1111I~
//    public static FragmentManager fragmentManager;                 //~1Ah1R~//~1Ah2R~
// 	public static PartnerFrame aPartnerFrame;                      //~@@@@I~//~1A8gR~
 	public static IPConnection aIPConnection;                      //~1A6kI~
 	public static BluetoothConnection aBTConnection;               //~1A6kI~
//  public static jagoclient.partner.PartnerFrame aPartnerFrameIP; //~@@@@I~//~1A85R~
    public static jagoclient.partner.PartnerFrame aPartnerFrame;   //~1A8gI~
    public static wifidirect.PartnerFrame aPartnerFrameIP;         //~1A8cR~
    public static int RemoteStatus;                                //~@@@@R~
    public static int RemoteStatusAccept;                          //~@@@@I~
    public static final int RS_IP=1;                               //~@@@@R~
    public static final int RS_BT=2;                               //~@@@@I~
    public static final int RS_IPLISTENING=RS_IP+4;                //~@@@@R~
    public static final int RS_BTLISTENING_SECURE=RS_BT+4;         //~@@@@R~
    public static final int RS_IPCONNECTED=RS_IP+8;                 //~@@@@R~
    public static final int RS_BTCONNECTED=RS_BT+8;                //~@@@@R~
    public static final int RS_BTLISTENING_INSECURE=RS_BT+16;      //~@@@@I~
    public static final int DEFAULT_SERVER_PORT=6973;              //~@@@@I~
    public static String RemoteInetAddress;                        //~@@@@I~
    public static String LocalInetAddress;                         //~1A6sI~
    public static String RemoteInetAddressLAN;                     //~1A8fI~
    public static String LocalInetAddressLAN;                      //~1A8fI~
    public static String PartnerName;                              //~@@@@I~
    public static String RemoteDeviceName;                         //~@@@@I~
    public static String LocalDeviceName;                          //~@@@@I~
	public static AMain aMain;                             //~1107R~//~@@@@R~
	public static AMenu aMenu;                             //~1107I~//~@@@@R~
	public static AView aView;                                //~1111I~//~@@@@R~
	public static Resources  resource;                              //~1109I~
	public static int       component;                             //~1109I~
	public static Go        go;                                    //~1109I~
//  public static int scrW,scrH;                                   //~1428R~//~v107R~
	public static float dip2pix;                                   //~1428I~
	public static float sp2pix;                                    //~@@@@I~
//  public static boolean landscape;                               //~1428R~//~v107R~
    public static final String SD_go_cfg="go.cfg.save";             //~1308R~
    public static Frame mainframe;                                 //~1111I~
    public static LayoutInflater inflater;                          //~1113I~
    public static Canvas    androidCanvasMain;                     //~1428R~
//    public static boolean   appStart;                              //~1428R~//~@@@@R~
    public static boolean   portrait;                              //~1428R~
    public static String    appName;                               //~1428R~
    public static String    appNameE;	//by alphabet              //~1Ah1R~
    public static String    pkgName;                               //~1A6aI~
    public static String    appVersion;                            //~1506I~
    public static int       scrWidth,scrHeight;                    //~1428R~
    public static int       scrWidthReal,scrHeightReal;            //~1Ah1I~
    public static int       scrNavigationbarRightWidth;            //~1Ah1I~
    public static boolean   swSmallDevice;      //portrait screen width<800pixel//~1Ah1I~
    public static double    scaleSmallDevice;   //portrait screen width/800pixel//~1Ah1I~
    private static View      currentLayout;//~1120I~               //~1428R~
    public static int       currentLayoutId;                       //~1428R~
    private static int       currentLayoutLabelSeqNo;              //~1428R~
    private static int       currentLayoutTextFieldSeqNo;          //~1428R~
    private static int       currentLayoutTextAreaSeqNo;           //~1428R~
    private static int       currentLayoutButtonSeqNo;             //~1428R~
    private static int       currentLayoutCheckBoxSeqNo;           //~1428R~
    private static int       currentLayoutSpinnerSeqNo;            //~1428R~
    private static int       currentLayoutSeekBarSeqNo;            //~1428R~
    public static Dialog    currentDialog;                    //~1215I~//~1428R~
    public static Frame     currentFrame;                          //~1428R~
    public static int       mainframeTag;                          //~1428R~
    public static boolean currentIsDialog;                         //~1428R~
                                                                   //~1211I~
    public static int       listViewRowId=R.layout.textrowlist;       //~1211I~//~1219R~
    public static int       viewerRowId  =R.layout.textrowviewer;  //~1219R~
    public static int       listViewRowIdMultipleChoice=android.R.layout.simple_list_item_multiple_choice;//~1211I~
    public static final int TIME_LONGPRESS=1000;//milliSeconds     //~1412I~
    public static int currentTabLayoutId;                          //~1428R~
    public static int titleBarTop;                                 //~1428R~
    public static int titleBarBottom;                              //~1428R~
    public static ABT aBT;                                 //~v107R~//~@@@@R~
//  public static WDANFC aWDANFC;                                  //~1A6aI~//~1A81R~
//  public static ANFC aANFC;                                      //~1A6aI~//~1A81R~
    public static IPSubThread aIPSubThread;                               //~@@01I~//~1AhjR~
    public static final String PKEY_STARTUPCTR="startupctr";       //~v107I~
    public static int startupCtr;                                  //~v107I~
    public static int activeSessionType;                           //~1A8gI~
    public static final int AST_IP=1;                              //~1A8gI~
    public static final int AST_WD=2;                              //~1A8gI~
    public static final int AST_BT=3;                              //~1A8gI~
    public static String BlackSign,WhiteSign,Move1stSign,Move2ndSign;//~@@@@I~
    public static String BlackName,WhiteName;                      //~@@@@I~
    public static MsgThread msgThread;                             //~@@@@R~
    public static SayDialog sayDialog;                             //~@@@@I~
    public static boolean smallButton;                             //~@@@@R~
    public static int smallViewHeight;                         //~@@@@I~
    public static int smallImageHeight;	//for captured List        //~@@@@I~
    public static int smallTextSize;                               //~@@@@I~
    public static Font smallFont;                                  //~@@@@I~
//  public static ProgDlg progDlg;                                       //~@@@@M~//~1Ah1R~
    public static com.ForDeprecated.ProgDlg progDlg26;                             //~1Ad7I~
    public static boolean screenDencityMdpi;                       //~1A50I~
    public static boolean screenDencityMdpiSmallV;                 //~1A61I~
    public static boolean screenDencityMdpiSmallH;                 //~1A61I~
    public static boolean layoutMdpi;                              //~1A6cI~
	public static boolean tryHelpFileOpen;                         //~1A41R~//~1Ad2I~
    public static  boolean swNFCBT=true;   //support NFC Bluetooth handover//~1Ab7I~
    public static  boolean swSecureNFCBT;  //current active NFCBT session is secure//~1AbgI~
    public static  boolean isNFCBT; 		//BT is by NFC         //~1AbgI~
//*UFile                                                           //~1Ah1I~
	public  static boolean swSDAvailable=true;                     //~1Ah1R~
	public  static String dirSD;                                   //~1Ah1R~
                                                                   //~1Ak0I~
	public  static UScoped aUScoped;                               //~1Ak0I~
	public  static boolean swScoped;                               //~1Ak0I~
	public  static UMediaStore aUMediaStore;                       //~1Ak2I~
	public  static boolean swGrantedExternalStorageRead,swGrantedExternalStorageWrite;//~1Ak2R~
	public  static boolean swSmallFont;                            //~1Ak3I~
    public  static int dialogPaddingHorizontal; //by UFDlg                 //~@@01I~//~1Ak3R~
                                                                   //~1120I~
//****************                                                 //~1109I~
	public  static final String ListServer ="ListView_Server";     //~1120I~
	public  static final String ListPartner="ListView_Partner";    //~1120I~
//****************                                                 //~1122I~
//    public  static final String tabName_ServerConnections   ="Server Connections";//~1122R~//~@@@@R~
//    public  static final String tabName_PartnerConnections  ="Partner Connections";//~1122R~//~@@@@R~
//    public  static final int    TabLayoutID_Servers     =R.id.MainFrameTabLayout_ServerConnections;//~1122I~//~@@@@R~
//    public  static final int    TabLayoutID_Partners    =R.id.MainFrameTabLayout_PartnerConnections;//~1122R~//~@@@@R~
//****************                                                 //~1120I~
//    public  static final String layout_ServerConnections   ="ServerConnections";//~1120I~//~@@@@R~
//    public  static final String layout_PartnerConnections  ="PartnerConnections";//~1120I~//~@@@@R~
//    public  static final String layout_SingleServer        ="SingleServer";//~1120I~//~@@@@R~
//    public  static final String layout_GamesFrame          ="GamesFrame";//~1120I~//~@@@@R~
//    public  static final String layout_ConnectionFrame     ="ConnectionFrame";//~1120I~//~@@@@R~
//    public  static final String layout_ConnectedGoFrame    ="ConnectedGoFrame";//~1121I~//~@@@@R~
	public  static final String layout_MainFrame           ="MainFrame";//~1125I~
                                                                   //~1121I~
    public  static final int frameId_MainFrame             =R.layout.mainframe; //initial//~1125R~
//    public  static final int frameId_ServerConnections     =R.layout.mainsl;//~1121I~//~@@@@R~
//    public  static final int frameId_PartnerConnections    =R.layout.mainpl;//~1121I~//~@@@@R~
//    public  static final int frameId_SingleServer          =R.layout.mainsv;//~1121I~//~@@@@R~
//    public  static final int frameId_GamesFrame            =R.layout.gamesframe;//~1121I~//~@@@@R~
//    public  static final int frameId_ConnectionFrame       =R.layout.connectionframe;//~1121I~//~@@@@R~
    public  static final int frameId_ConnectedGoFrame      =R.layout.connectedgoframe;//~1121I~
//    public  static final int frameId_WhoFrame              =R.layout.whoframe;//~1306I~//~@@@@R~
    public  static final int frameId_MessageDialog         =R.layout.messagedialog;//~1310I~
    public  static final int frameId_SayDialog             =R.layout.saydialog;//~1311I~
    public  static final int frameId_PartnerFrame          =R.layout.partnerframe;//~1318I~
//    public  static final int frameId_LocalViewer           =R.layout.localviewer;//~1323I~//~@@@@R~
//    public  static final int frameId_Help                  =R.layout.help;//~1326I~
//    public  static final int frameId_MessageFilter         =R.layout.messagefilter;//~1331I~//~@@@@R~
//    public  static final int frameId_GMPConnection         =R.layout.gmpconnection;//~1404I~//~@@@@R~
//    public  static final int frameId_OpenPartners          =R.layout.openpartners;//~1405I~//~@@@@R~
    public  static final int frameId_PartnerSendQuestion   =R.layout.partnersendquestion;//~1405I~
                                                                   //~1216I~
                                                                   //~1214I~
    public  static final int dialogId_Message              =R.layout.message;//~1214I~//~1310R~
    public  static final int dialogId_GetParameter         =R.layout.getparameter;//~1215R~//~1310R~
//    public  static final int dialogId_Password             =R.layout.password;//~1125I~//~1215R~//~1310R~//~@@@@R~
    public  static final int dialogId_Information          =R.layout.information;//~1307I~//~1310R~
    public  static final int dialogId_BluetoothConnection  =R.layout.bluetooth;//~@@@@I~
    public  static final int dialogId_MessageDialog        =R.layout.messagedialog;//~1310R~
    public  static final int dialogId_MatchQuestion        =R.layout.matchquestion;//~1310R~
    public  static final int dialogId_MatchDialog          =R.layout.matchdialog;//~1310I~
    public  static final int dialogId_TellQuestion         =R.layout.tellquestion;//~1311I~
//    public  static final int dialogId_EditConnection       =R.layout.editconnection;//~1314I~//~@@@@R~
//    public  static final int dialogId_EditPartner          =R.layout.editpartner;//~1318I~//~@@@@R~
//    public  static final int dialogId_FileDialog           =R.layout.filedialog;//~1326I~//~@@@@R~
    public  static final int dialogId_HelpDialog           =R.layout.helpdialog;//~1327I~
//    public  static final int dialogId_AdvancedOptionEdit   =R.layout.advancedoptionedit;                                                              //~1125I~//~@@@@R~
    public  static final int dialogId_Question             =R.layout.question;//~1329I~
//    public  static final int dialogId_FunctionKeys         =R.layout.functionkeys;//~1330I~//~@@@@R~
//    public  static final int dialogId_EditFilter           =R.layout.editfilter;//~1331I~//~@@@@R~
//    public  static final int dialogId_EditButtons          =R.layout.editbuttons;//~1331I~//~@@@@R~
//    public  static final int dialogId_RelayServer          =R.layout.relayserver;//~1331I~//~@@@@R~
    public  static final int dialogId_FontSize             =R.layout.fontsize;//~1331I~
    public  static final int dialogId_GlobalGray           =R.layout.globalgray;//~1331I~
//    public  static final int dialogId_ColorEdit            =R.layout.editcolor;//~1331I~//~@@@@R~
//    public  static final int dialogId_MailDialog           =R.layout.maildialog;//~1404I~//~@@@@R~
//    public  static final int dialogId_GMPWait              =R.layout.gmpwait;//~1404I~//~@@@@R~
    public  static final int dialogId_Warning              =R.layout.warning;//~1404I~
//    public  static final int dialogId_GameQuestion         =R.layout.gamequestion;//~1405R~//~@@@@R~
//    public  static final int dialogId_BoardQuestion        =R.layout.boardquestion;//~1405I~//~@@@@R~
    public  static final int dialogId_GameInformation      =R.layout.gameinformation;//~1405I~
//    public  static final int dialogId_TextMark             =R.layout.textmark;//~1405I~//~@@@@R~
    public  static final int dialogId_SendQuestion         =R.layout.sendquestion;//~1405I~
    public  static final int dialogId_PartnerSendQuestion  =R.layout.partnersendquestion;//~1405I~
                                                                   //~1331I~
//    public  static final int menuLayoutId_WhoPopup           =R.menu.whopopup;//~1307I~//~@@@@R~
//    public  static final int menuLayoutId_GamesPopup         =R.menu.gamespopup;//~1307I~//~@@@@R~
                                                                   //~1121I~
	private static final IdTblEntry[] layouttbl={
//                     new IdTblEntry(layout_MainFrame,             frameId_MainFrame),//~1125I~//~@@@@R~
//                     new IdTblEntry(layout_ServerConnections,     frameId_ServerConnections),//~1121R~//~@@@@R~
//                     new IdTblEntry(layout_PartnerConnections,    frameId_PartnerConnections),//~1121R~//~@@@@R~
//                     new IdTblEntry(layout_SingleServer,          frameId_SingleServer),//~1121R~//~@@@@R~
//  				 new IdTblEntry(layout_GamesFrame,            frameId_GamesFrame),//~1121R~//~1217R~
//  				 new IdTblEntry(layout_ConnectionFrame,       frameId_ConnectionFrame),//~1121R~//~1217R~
//                     new IdTblEntry(layout_ConnectedGoFrame,      frameId_ConnectedGoFrame),//~1121R~//~1217R~//~@@@@R~
    				 new IdTblEntry("dummy",-1)//~1120I~
                     };                                             //~1120I~
//*ViewID                                                          //~1121I~
                                                                   //~1216I~
	public  static final int    viewId_BigTimerLabel  =R.id.BigTimer;//~1216R~
	public  static final int    viewId_BoardPanel     =R.id.BoardPanel;//~1121I~//~1217I~
	public  static final int    viewId_ExampleCanvas  =R.id.ExampleCanvas;//~1331I~
	public  static final int    viewId_ListView       =R.id.ListView;//~1219I~
    public  static final int    viewId_Lister         =R.id.Lister;                                                               //~1216I~
//    public  static final int    viewId_IconBar        =R.id.IconBar;//~1322I~
//    public  static final int    viewId_IconBar1       =R.id.IconBar1;//~1324I~
//    public  static final int    viewId_IconBar2       =R.id.IconBar2;//~1324I~
//    public  static final int    viewId_NavigationPanel=R.id.NavigationPanel;//~1415I~
    public  static final int    viewId_SeekBarRed     =R.id.SeekBar1;//~1401I~
    public  static final int    viewId_SeekBarGreen   =R.id.SeekBar2;//~1401I~
    public  static final int    viewId_SeekBarBlue    =R.id.SeekBar3;//~1401I~
    public  static final int    viewId_ContainerRed   =R.id.ContainerRed;//~1401I~
    public  static final int    viewId_ContainerGreen =R.id.ContainerGreen;//~1401I~
    public  static final int    viewId_ContainerBlue  =R.id.ContainerBlue;//~1401I~
    public  static final int    viewId_ContainerFontSizeField=R.id.ContainerFontSizeField;//~1401I~
	public  static final int    viewId_DialogBackground=R.id.DialogBackground;//~1404I~
//	public  static final int    viewId_Comment        =R.id.Comment;//~1416I~
//	public  static final int    viewId_AllComments    =R.id.AllComments;//~1416I~//~@@@@R~
	public  static final int    viewId_SetStone       =R.id.SetStone;//~1424I~//~@@@@R~

                                                                   //~1413I~
                                                                   //~1404I~
	private static final IdTblEntry[] viewtbl={  //by actionName   //~1124I~
                                                                   //~1124I~
					new IdTblEntry("Observe"       ,              R.id.Observe    ),//~1124I~
					new IdTblEntry("Peek"          ,              R.id.Peek       ),//~1124I~
					new IdTblEntry("Status"        ,              R.id.Status     ),//~1124I~
					new IdTblEntry("Information"   ,              R.id.Information),//~1125I~//~1216R~//~1307R~

					new IdTblEntry("Viewer"        ,              R.id.Viewer     ),//~1215I~
					new IdTblEntry("Lister"        ,              R.id.Lister     ),//~1220I~
					new IdTblEntry("SetStone"        ,            AG.viewId_SetStone),//~1424I~

                                                                   //~1328I~
    //Iconbar                                                      //~1322I~
//                    new IdTblEntry("undo"                ,        R.id.undo       ),//~1322R~//~@@@@R~
//					new IdTblEntry("sendforward"         ,        R.id.sendforward),//~1322R~
//                    new IdTblEntry("allback"             ,        R.id.allback    ),//~1322R~//~@@@@R~
//                    new IdTblEntry("fastback"            ,        R.id.fastback   ),//~1322R~//~@@@@R~
//                    new IdTblEntry("back"                ,        R.id.back       ),//~1322R~//~@@@@R~
//                    new IdTblEntry("forward"             ,        R.id.forward    ),//~1322R~//~@@@@R~
//                    new IdTblEntry("fastforward"         ,        R.id.fastforward),//~1322R~//~@@@@R~
//                    new IdTblEntry("allforward"          ,        R.id.allforward ),//~1322R~//~@@@@R~
//                    new IdTblEntry("variationback"       ,        R.id.variationback),//~1322R~//~@@@@R~
//                    new IdTblEntry("variationstart"      ,        R.id.variationstart),//~1322R~//~@@@@R~
//                    new IdTblEntry("variationforward"    ,        R.id.variationforward),//~1322R~//~@@@@R~
//                    new IdTblEntry("main"                ,        R.id.main       ),//~1322R~//~@@@@R~
//                    new IdTblEntry("mainend"             ,        R.id.mainend    ),//~1322R~//~@@@@R~
//					new IdTblEntry("send"                ,        R.id.send       ),//~1322R~
//                    new IdTblEntry("mark"                ,        R.id.mark       ),//~1322R~//~@@@@R~
//                    new IdTblEntry("square"              ,        R.id.square     ),//~1322R~//~@@@@R~
//                    new IdTblEntry("triangle"            ,        R.id.triangle   ),//~1322R~//~@@@@R~
//                    new IdTblEntry("circle"              ,        R.id.circle     ),//~1322R~//~@@@@R~
//                    new IdTblEntry("letter"              ,        R.id.letter     ),//~1322R~//~@@@@R~
//                    new IdTblEntry("text"                ,        R.id.text       ),//~1322R~//~@@@@R~
//                    new IdTblEntry("black"               ,        R.id.black      ),//~1322R~//~@@@@R~
//                    new IdTblEntry("white"               ,        R.id.white      ),//~1322R~//~@@@@R~
//                    new IdTblEntry("setblack"            ,        R.id.setblack   ),//~1322R~//~@@@@R~
//                    new IdTblEntry("setwhite"            ,        R.id.setwhite   ),//~1323R~//~@@@@R~
//                    new IdTblEntry("delete"              ,        R.id.delete     ),//~1322R~//~@@@@R~
//                    new IdTblEntry("deletemarks"         ,        R.id.deletemarks),//~1323I~//~@@@@R~
//                    new IdTblEntry("play"                ,        R.id.play),//~1323I~//~@@@@R~
                                                                   //~1322I~
    				new IdTblEntry("dummy",-1)                     //~1124I~
                     };                                            //~1124I~
//*                                                                //~v101I~
    public static int bottomSpaceHeight;                           //~v101I~
    public static final int SYSTEMBAR_HEIGHT=48;                   //~v101I~
	public static String PREFKEY_BOTTOMSPACE_HIGHT="BottomSpaceHeight";//~v101I~
    public static int osVersion;                                   //~vab0I~//~v101I~
    public static final int HONEYCOMB=11; //android3.0 (GINGERBREAD=9)//~vab0I~//~v101I~
    public static final int HONEYCOMB_MR1=12; //android3.1         //~1A4hI~//~1Ad6I~
    public static final int HONEYCOMB_MR2=13; //android3.2         //~1A6pI~
    public static final int ICE_CREAM_SANDWICH=14; //android4.0    //~vab0I~//~v101I~
//************************************                                                 //~1402I~//~@@@@R~
//*static to be creaded at create                                  //~@@@@I~
//************************************                             //~@@@@I~
    public static int       status=0;                              //~1212I~//~@@@@M~
    public static final int STATUS_MAINFRAME_OPEN=1;               //~1212I~//~@@@@M~
    public static final int STATUS_STOPFINISH=9;                   //~@@@@I~
//    public static boolean   Utils_stopFinish;   //Utils                                 //~1309I~//~@@@@R~
    public static int Board_SpieceImageSize;                       //~@@@@I~
//************************************                             //~@@@@I~
    public  static int       scrStatusBarHeight;	//API30, by insets     //~vaj0I~//~1Am3I~
//  public  static int       scrNavigationbarRightWidth;                  //~@@01I~//~1Am3I~
//  public  static int       scrNavigationbarBottomHeight;                //~vaeeI~//~1Am3I~
    public  static int       scrNavigationbarBottomHeightA11;             //~vaefR~//~1Am3I~
    public  static int       scrNavigationbarLeftWidthA11;                //~vaefI~//~1Am3R~
    public  static int       scrNavigationbarRightWidthA11;               //~vaefI~//~1Am3I~
    public  static boolean   swNavigationbarGestureMode;                  //~vaefR~//~1Am3I~
	public  static boolean swGrantedBluetooth,swGrantBluetoothFailed;                         //~vas6I~//~1Am9R~
    public  static boolean swFailedGrantBluetoothAdvertize;                //~vas6I~//~1Am9I~
    public  static UPermission aUPermission;                       //+1AmsI~
                                                                   //+1AmsI~
	public static void init(AMain Pmain)                        //~1402I~//~v107R~//~@@@@R~
    {                                                              //~1402I~
//******************************************************************************//~@@@@R~
//*recreate case;need clear static                                 //~@@@@R~
//* static field is cleared when class is re-loaded at restart after finish()//~@@@@I~
//* else it should be initialized                                  //~@@@@I~
//******************************************************************************//~@@@@R~
    	status=0;	//for create() after finsh() even process active//~@@@@I~
    	currentIsDialog=false;                                     //~@@@@I~
//        Utils_stopFinish=false;                                  //~@@@@R~
//******************************************************           //~@@@@I~
    	osVersion=Build.VERSION.SDK_INT;                //~vab0I~  //~v101I~
        aMain=Pmain;                                            //~1109I~//~1329R~//~1402I~//~@@@@R~
        activity=(Activity)Pmain;                                //~1402I~//~@@@@R~//~1Ah2R~
//      activity=(AppCompatActivity)Pmain;                         //~1Ah2R~
        context=(Context)Pmain;                                  //~1402I~//~@@@@R~
        isDebuggable=Utils.isDebuggable(context);             //~v107I~//~@@@@R~
        if (isDebuggable)                                          //~1Ah1I~
        	Dump.open("");	//write all to Terminal log,not exception only//~1Ah1I~
		Properties p=System.getProperties();                       //~1Ah1I~
		dirSep=p.getProperty("file.separator");                    //~1Ah1I~
        startupCtr=Prop.getPreference(PKEY_STARTUPCTR,0);    //~v107I~//~@@@@R~
        Prop.putPreference(PKEY_STARTUPCTR,startupCtr+1);    //~v107I~//~@@@@R~
        resource=Pmain.getResources();                                //~1109I~//~1329R~//~1402I~//~@@@@R~
        inflater=Pmain.getLayoutInflater();                           //~1113I~//~1329R~//~1402I~//~@@@@R~
		appName=context.getText(R.string.app_name).toString();     //~1402I~
        appNameE=Utils.getStr(R.string.app_nameE);                 //~1Ah1I~
		pkgName=context.getPackageName();                          //~1A6aI~
//      fragmentManager=aMain.getFragmentManager();        //~1Ah1I~//~1Ah2R~
//        fragmentManager=activity.getSupportFragmentManager();    //~1Ah2R~
		appVersion=context.getText(R.string.Version).toString();   //~1506I~
                                                                   //~v101I~
        if (osVersion>=HONEYCOMB && osVersion<ICE_CREAM_SANDWICH)  //android3 api11-13//~vab0R~//~v101I~
        	bottomSpaceHeight=SYSTEMBAR_HEIGHT;                    //~vab0I~//~v101I~
    	mainThread=Thread.currentThread();                         //~@@@@I~
        Locale locale=Locale.getDefault();                         //~@@@@I~
        Glocale=locale.toString();	//ja_JP                        //~1820R~//~@@@@I~
        language=locale.getLanguage();   //ja(Locale.JAPANESE) or ja_JP(Locale.JAPAN)//~1531R~//~@@@@I~
//      isLangJP=language.substring(0,2).equals(Locale.JAPANESE);  //~@@@@I~//~v102R~
        isLangJP=language.substring(0,2).equals(Locale.JAPANESE.getLanguage());  //~@@@@I~//~v102I~
        helpFileSuffix=isLangJP ? "_ja" : "";                      //~1Ah1I~
	    Options=Prop.getPreference(PKEY_OPTIONS,                   //~@@@@R~
                0                                                  //~@@@@I~
    			|OPTIONS_BIG_TIMER                                 //~@@@@I~
    			|OPTIONS_SHOW_LAST                                 //~@@@@I~
    			|OPTIONS_COORDINATE                                //~@@@@I~
                );                                                 //~@@@@R~
        MainFrameOptions.initialOptions();                         //~1Ad7I~
	    YourName=Prop.getPreference(PKEY_YOURNAME,resource.getString(R.string.DefaultYourName));//~@@@@I~
	    LocalOpponentName=resource.getString(R.string.LocalOpponentName);//~@@@@I~
        BlackSign=resource.getString(R.string.BlackSign);          //~@@@@R~
        WhiteSign=resource.getString(R.string.WhiteSign);          //~@@@@R~
        BlackName=resource.getString(R.string.Black);              //~@@@@I~
        WhiteName=resource.getString(R.string.White);              //~@@@@I~
        Move1stSign=resource.getString(R.string.Move1st);          //~@@@@I~
        Move2ndSign=resource.getString(R.string.Move2nd);          //~@@@@I~
        if (isLangJP)                                              //~1023I~
			SshogiV=SshogiVJ; //japanese rank                      //~1023I~
        else                                                       //~1023I~
			SshogiV=SshogiVE; //letter rank                        //~1023I~
        screenDencityMdpi=resource.getDisplayMetrics().density==1.0;//~1A50I~
    }                                                              //~1402I~
//****************                                                 //~1402I~
	public static int findLayoutIdByName(String Pname)               //~1120I~//~1125R~
    {                                                              //~1120I~
    	for (int ii=0;ii<layouttbl.length;ii++)                      //~1120I~
    		if (Pname.equals(layouttbl[ii].name))                   //~1120I~
            {                                                      //~1120I~
    			if (Dump.Y) Dump.println(Pname+" id="+Integer.toString(layouttbl[ii].id,16));//~1126R~//~1506R~
    			return layouttbl[ii].id;                             //~1120I~
            }                                                      //~1120I~
        if (Dump.Y) Dump.println(Pname+" not found(LayoutID)");    //~1506R~
    	return -1;
        //out of bound                                 //~1120I~
    }                                                              //~1120I~
//****************                                                 //~1213I~
	private static final IdTblEntry[] icontbl={                    //~1213I~//~1328M~
					new IdTblEntry("ijago.gif"     ,              R.drawable.ijago),//~1213I~//~1328M~
					new IdTblEntry("iboard.gif"    ,              R.drawable.iboard),//~1213I~//~1328M~
					new IdTblEntry("ihelp.gif"     ,              R.drawable.ihelp),//~1213I~//~1328M~
					new IdTblEntry("iconn.gif"     ,              R.drawable.iconn),//~1213I~//~1328M~
					new IdTblEntry("iwho.gif"      ,              R.drawable.iwho),//~1213I~//~1328M~
					new IdTblEntry("igames.gif"    ,              R.drawable.igames) //~1213I~//~1327R~//~1328M~
                    };                                              //~1213I~//~1328M~
	public static int findIconId(String Pname)                     //~1213I~
    {                                                              //~1213I~
		int id=findViewIdByName(icontbl,Pname);                        //~1328I~
    	if (Dump.Y) Dump.println("icon search "+Pname+" id="+Integer.toHexString(id));//~1213I~//~1506R~
    	return id;                                                 //~1213I~//~1328R~
    }                                                              //~1213I~
//****************                                                 //~1327I~
	private static final IdTblEntry[] soundtbl={                   //~1327I~//~1328M~
					new IdTblEntry("high"          ,              R.raw.high),//~1327I~//~1328M~
					new IdTblEntry("message"       ,              R.raw.message),//~1327I~//~1328M~
					new IdTblEntry("click"         ,              R.raw.click),//~1327I~//~1328M~
					new IdTblEntry("stone"         ,              R.raw.stone),//~1327I~//~1328M~
					new IdTblEntry("wip"           ,              R.raw.wip),//~1327I~//~1328M~
					new IdTblEntry("yourmove"      ,              R.raw.yourmove),//~1327I~//~1328M~
					new IdTblEntry("game"          ,              R.raw.game),//~1327I~//~1328M~
					new IdTblEntry("pieceup"       ,              R.raw.pieceup),//~@@@@I~
					new IdTblEntry("piecedown"     ,              R.raw.piecedown),//~@@@@I~
					new IdTblEntry("piececaptured" ,              R.raw.piececaptured),//~@@@@I~
					new IdTblEntry("ahsvgameover" ,               R.raw.ahsvgameover),//~1023R~
                    };                                             //~1327I~//~1328M~
	public static int findSoundId(String Pname)                    //~1327I~
    {                                                              //~1327I~
		int id=findViewIdByName(soundtbl,Pname);                       //~1328I~
        if (Dump.Y) Dump.println("Sound "+Pname+",id="+Integer.toHexString(id));//~1506R~
    	return id;                                                 //~1327I~//~1328R~
    }                                                              //~1327I~
//****************                                                 //~1125I~
//    public static String findFrameNameByInstance(Object PframeObject) //~1125I~//~@@@@R~
//    {                                                              //~1125I~//~@@@@R~
//        String framename;                                          //~1125I~//~@@@@R~
//    //****************                                             //~1125I~//~@@@@R~
//        if (PframeObject instanceof MainFrame)                     //~1125I~//~@@@@R~
//            framename=layout_MainFrame;                            //~1125I~//~@@@@R~
////        else                                                       //~1125I~//~@@@@R~
////        if (PframeObject instanceof Go)                            //~1125I~//~@@@@R~
////            framename=layout_SingleServer;                         //~1125I~//~@@@@R~
//        else                                                       //~1125I~//~@@@@R~
//        if (PframeObject instanceof ConnectedGoFrame)             //~1125I~//~@@@@R~
//            framename=layout_ConnectedGoFrame;                     //~1125I~//~@@@@R~
////        else                                                       //~1125I~//~@@@@R~
////        if (PframeObject instanceof GamesFrame)                   //~1125I~//~@@@@R~
////            framename=layout_GamesFrame;                           //~1125I~//~@@@@R~
////        else                                                       //~1125I~//~@@@@R~
////        if (PframeObject instanceof ConnectionFrame)              //~1125I~//~@@@@R~
////            framename=layout_ConnectionFrame;                      //~1125I~//~@@@@R~
//        else                                                       //~1125I~//~@@@@R~
//            framename=null;                                        //~1125I~//~@@@@R~
//        return framename;                                          //~1125I~//~@@@@R~
//    }                                                              //~1125I~//~@@@@R~
//****************                                                 //~1120I~
	public static int findViewIdByName(String Pname)               //~1120R~
    {                                                              //~1120I~
    	return findViewIdByName(viewtbl,Pname);                    //~1328I~
    }                                                              //~1120I~
//****************                                                 //~1328I~
	public static int findViewIdByName(IdTblEntry[] Pviewtbl,String Pname)//~1328R~
    {                                                              //~1328I~
    	int id=-1,sz=Pviewtbl.length;                              //~1328R~
    	for (int ii=0;ii<sz;ii++)                                  //~1328I~
    		if (Pname.equals(Pviewtbl[ii].name))                   //~1328I~
            {                                                      //~1328R~
    			id=Pviewtbl[ii].id;                                //~1328R~
                break;                                             //~1328I~
            }                                                      //~1328I~
    	if (Dump.Y) Dump.println("FindViewByName "+Pname+" id="+Integer.toHexString(id));//~1506R~
    	return id;                                                 //~1328R~
    }                                                              //~1328I~
//****************                                                 //~1328I~
	public static View findViewByName(IdTblEntry[] Pviewtbl,String Pname)//~1328I~
    {                                                              //~1328I~
		int id=findViewIdByName(Pviewtbl,Pname);                   //~1328I~
        if (id<0)                                                  //~1328I~
        	return null;                                           //~1328I~
    	return findViewById(id);                                   //~1328I~
    }                                                              //~1328I~
//****************                                                 //~1120I~
	public static View findViewByName(String Pname)                //~1120I~
    {                                                              //~1120I~
        return findViewByName(AG.currentLayout,Pname);             //~1121R~
    }                                                              //~1120I~
//****************                                                 //~1216I~
	public static View findViewById(int Presid)                   //~1216I~
    {                                                              //~1216I~
        return AG.currentLayout.findViewById(Presid);              //~1216I~
    }                                                              //~1216I~
//****************                                                 //~1416I~
	public static View findViewById(View Playout,int Pid)          //~1416I~
    {                                                              //~1416I~
		return Playout.findViewById(Pid);                          //~1416I~
    }                                                              //~1416I~
//****************                                                 //~1121I~//~1216M~
	public static View findViewByName(View Playout,String Pname)   //~1121I~//~1216M~
    {                                                              //~1121I~//~1216M~
        int id=findViewIdByName(Pname);                            //~1216M~
		return Playout.findViewById(id);                             //~1121I~//~1216M~
    }                                                              //~1121I~//~1216M~
//****************                                                 //~1216I~
	public static void setCurrentView(int Presourceid,View Pview)         //~1216I~
    {                                                              //~1216I~
        if (Pview==null)                                           //~v106I~
        {                                                          //~v106I~
        	if (Dump.Y) Dump.println("setCurrentLayout null resid="+Integer.toHexString(Presourceid));//~v106I~
            return;                                                //~v106I~
        }                                                          //~v106I~
        currentLayout=Pview;                                       //~1216I~
        currentLayoutId=Presourceid;                               //~1216I~
        currentLayoutLabelSeqNo=0;                                 //~1216R~
        currentLayoutTextFieldSeqNo=0;                             //~1216I~
        currentLayoutTextAreaSeqNo=0;                               //~1416I~
        currentLayoutButtonSeqNo=0;                                //~1306I~
        currentLayoutCheckBoxSeqNo=0;                               //~1328I~
        currentLayoutSpinnerSeqNo=0;                               //~1331I~
        currentLayoutSeekBarSeqNo=0;                               //~1331I~
        if (Dump.Y) Dump.println("setCurrentLayout resid="+Integer.toHexString(Presourceid)+",Viewid="+(Pview==null?"null":Integer.toHexString(Pview.getId())));//~v106I~
                                                                   //~v106I~
    }
//****************                                                 //~1216I~
	public static View getCurrentLayout()
	{
		return currentLayout;//~1216I~
	}
//****************                                                 //~1216I~
	public static void setCurrentDialog(Dialog Pdialog)            //~1216I~
	{                                                              //~1216I~
    	currentIsDialog=true;                                      //~1311I~
		currentDialog=Pdialog;                                     //~1216I~
	}                                                              //~1216I~
//****************                                                 //~1311I~
	public static void setCurrentFrame(Frame Pframe)               //~1311I~
	{                                                              //~1311I~
    	currentIsDialog=false;                                     //~1311I~
		currentFrame=Pframe;                                       //~1311I~
        if (Dump.Y) Dump.println("AG:setCurrentFrame name="+Pframe.framename+"="+Pframe.toString());//~@@@@R~
    	setCurrentView(Pframe.framelayoutresourceid,Pframe.framelayoutview);//for wrap operation//~1504I~
	}                                                              //~1311I~
//****************                                                 //~1216I~
//    public static void setDialogClosed()                           //~1325I~//~@@@@R~
    public static void setDialogClosed(Dialog Pdialog)             //~@@@@I~
	{                                                              //~1325I~
        if (Dump.Y) Dump.println("AG:setDialogClosed dialogname="+Pdialog.dialogname);//~@@@@I~
      if (Pdialog.parentDialog!=null)	//dialog on dialog         //~@@@@R~
      {                                                            //~@@@@I~
        currentDialog=Pdialog.parentDialog;                        //~@@@@I~
        if (Dump.Y) Dump.println("AG:setDialogClosed parent is dialog");//~@@@@I~
      }                                                            //~@@@@I~
      else                                                         //~@@@@I~
      {                                                            //~@@@@I~
    	currentIsDialog=false;                                     //~1325I~
//      Frame f=Pdialog.parentFrame;                               //~@@@@R~
        Frame f=Pdialog.layoutFrame;                               //~@@@@I~
        if (Dump.Y) Dump.println("AG:setDialogClosed parent is frame="+(f==null?"null":f.framename));//~@@@@I~
        if (f!=null && f.framelayoutresourceid!=0)	//should recover framelayoutview//~@@@@R~
        {                                                          //~@@@@I~
        	if (Dump.Y) Dump.println("AG:setDialogClosed parent is frame="+f.framename);//~@@@@I~
//            AG.setCurrentFrame(f);  //restore currentLayout for new Canvas for changeBoard//~@@@@R~
			setCurrentView(f.framelayoutresourceid,f.framelayoutview);//~@@@@I~
        }                                                          //~@@@@I~
      }                                                            //~@@@@I~
      	if (Pdialog.dismissListener!=null)                         //~@@@@M~
      	{                                                          //~@@@@M~
        	if (Dump.Y) Dump.println("AG:setDialogClosed dismisslistenercall");//~@@@@I~
            Pdialog.dismissListener.doAction(AG.resource.getString(R.string.ActionDismissDialog));	//notify to Dialog callet to new frame  will be opened//~@@@@R~
      	}                                                          //~@@@@M~
	}                                                              //~1325I~
//****************                                                 //~1325I~
	public static Dialog getCurrentDialog()                          //~1216I~
	{                                                              //~1216I~
        if (currentIsDialog)                                       //~1401I~
			return currentDialog;                                      //~1216I~//~1401R~
        return null;                                               //~1401I~
	}                                                              //~1216I~
//****************                                                 //~1324I~
	public static boolean currentIsDialog()                        //~1427R~
	{                                                              //~1324I~
		return currentIsDialog;                                    //~1427R~
	}                                                              //~1324I~
	public static Frame getCurrentFrame()                          //~1427I~
	{                                                              //~1427I~
		return currentFrame;                                       //~1427I~
	}                                                              //~1427I~
//****************                                                 //~1216I~
	private static final int[] labelviewtbl={               //~1328I~
					R.id.Label1     ,//~1307I~//~1328M~           //~1331R~
					R.id.Label2     ,//~1216I~//~1328M~           //~1331R~
					R.id.Label3     ,//~1216I~//~1328M~           //~1331R~
					R.id.Label4     ,//~1310I~//~1328M~           //~1331R~
					R.id.Label5     ,//~1310I~//~1328M~           //~1331R~
					R.id.Label6     ,//~1314I~//~1328M~           //~1331R~
					R.id.Label7     ,//~1314I~//~1328M~           //~1331R~
					R.id.Label8     ,//~1330I~                    //~1331R~
					R.id.Label9     ,//~1330I~                    //~1331R~
					R.id.Label10    ,//~1330I~                    //~1331R~
                    };                                             //~1328I~
	public static View findLabelView()                             //~1216I~
    {                                                              //~1216I~
    	int id=labelviewtbl[currentLayoutLabelSeqNo++];            //~1331I~
        if (Dump.Y) Dump.println("findLabelView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                               //~1216I~//~1328R~//~1331R~
    }                                                              //~1216I~
//****************                                                 //~1216I~
	private static final int[] textfieldviewtbl={           //~1328I~
					R.id.TextField1 ,//~1216I~//~1328M~           //~1331R~
					R.id.TextField2 ,//~1216I~//~1328M~           //~1331R~
					R.id.TextField3 ,//~1216I~//~1328M~           //~1331R~
					R.id.TextField4 ,//~1310I~//~1328M~           //~1331R~
					R.id.TextField5 ,//~1314I~//~1328M~           //~1331R~
					R.id.TextField6 ,//~1314I~//~1328M~           //~1331R~
					R.id.TextField7 ,//~1330I~                    //~1331R~
					R.id.TextField8 ,//~1330I~                    //~1331R~
					R.id.TextField9 ,//~1330I~                    //~1331R~
					R.id.TextField10,//~1330I~                    //~1331R~
                    };                                             //~1328I~
	public static View findTextFieldView()                         //~1216I~
    {                                                              //~1216I~
    	int id=textfieldviewtbl[currentLayoutTextFieldSeqNo++];    //~1331I~
        if (Dump.Y) Dump.println("findTextFieldView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                               //~1216I~//~1328R~//~1331R~
    }                                                              //~1216I~
//****************                                                 //~1416I~
	private static final int[] textareaviewtbl={                   //~1416I~
					R.id.TextArea1 ,                               //~1416I~
                    };                                             //~1416I~
	public static View findTextAreaView()                          //~1416I~
    {                                                              //~1416I~
    	int id=textareaviewtbl[currentLayoutTextAreaSeqNo++];      //~1416I~
        if (Dump.Y) Dump.println("findTextAreaView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                                   //~1416I~
    }                                                              //~1416I~
//****************                                                 //~1306I~
	private static final int[] buttonviewtbl={              //~1328I~
					R.id.Button1    ,//~1306I~//~1328M~           //~1331R~
					R.id.Button2    ,//~1306I~//~1328M~           //~1331R~
					R.id.Button3    ,//~1306I~//~1328M~           //~1331R~
					R.id.Button4    ,//~1306I~//~1328M~           //~1331R~
					R.id.Button5    ,//~1310I~//~1328M~           //~1331R~
                    };                                             //~1328I~
	public static View findButtonView()                            //~1306I~
    {                                                              //~1306I~
    	int id=buttonviewtbl[currentLayoutButtonSeqNo++];          //~1331I~
        if (Dump.Y) Dump.println("findButtonView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                               //~1306I~//~1328R~//~1331R~
    }                                                              //~1306I~
	public static int disableButton()                              //~@@@@R~
    {                                                              //~@@@@I~
    	int ctr=0;                                                 //~@@@@I~
    //**************************                                   //~@@@@I~
    	for (int ii=currentLayoutButtonSeqNo;ii<buttonviewtbl.length;ii++,ctr++)//~@@@@R~
        {                                                          //~@@@@I~
    		int id=buttonviewtbl[ii];                              //~@@@@I~
			Button v=(Button)findViewById(id);                     //~@@@@I~
            Component dmy=new Component();                         //~@@@@I~
            if (v!=null)                                           //~@@@@I~
            {                                                      //~@@@@I~
            	dmy.setVisibility((View)v,View.GONE);              //~@@@@R~
            }                                                      //~@@@@I~
    	}                                                          //~@@@@I~
    	currentLayoutButtonSeqNo=0; //for next frame               //~@@@@I~
    	return ctr;                                                //~@@@@I~
    }                                                              //~@@@@I~
//****************                                                 //~@@@@I~
	public static boolean disableButton(int Presid)                    //~@@@@I~
    {                                                              //~@@@@I~
    //**************************                                   //~@@@@I~
		Button v=(Button)findViewById(Presid);                     //~@@@@I~
        if (v==null)                                               //~@@@@I~
        	return false;                                                    //~@@@@I~
        Component dmy=new Component();                             //~@@@@I~
                                           //~@@@@I~
            dmy.setVisibility((View)v,View.GONE);                  //~@@@@I~
        return true;//~@@@@I~
    }                                                              //~@@@@I~
//****************                                                 //~1328I~
	private static final int[] chkboxviewtbl={              //~1328I~//~1331R~
//                     R.id.CheckBox1  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox2  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox3  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox4  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox5  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox6  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox7  ,//~1328M~                   //~1331R~//~@@@@R~
//                     R.id.CheckBox8  ,//~1328M~                   //~1331R~//~@@@@R~
                    };                                             //~1328I~
	public static View findCheckBoxView()                          //~1328I~
    {                                                              //~1328I~
    	int id=chkboxviewtbl[currentLayoutCheckBoxSeqNo++];        //~1331I~
        if (Dump.Y) Dump.println("findCheckBoxView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                 //~1328R~         //~1331R~
    }                                                              //~1328I~
//****************                                                 //~1331I~
	private static final int[] spinnerviewtbl={                    //~1331R~
					R.id.Spinner1  ,                              //~1331R~
					R.id.Spinner2  ,                              //~1331R~
                    };                                             //~1331I~
	public static View findSpinnerView()                           //~1331I~
    {                                                              //~1331I~
    	int id=spinnerviewtbl[currentLayoutSpinnerSeqNo++];        //~1331R~
        if (Dump.Y) Dump.println("findSpinnerView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                                   //~1331I~
    }                                                              //~1331I~
//****************                                                 //~1331I~
	private static final int[] seekbarviewtbl={                    //~1331I~
					R.id.SeekBar1  ,                               //~1331I~
					R.id.SeekBar2  ,                               //~1331I~
					R.id.SeekBar3  ,                               //~1331I~
                    };                                             //~1331I~
	public static View findSeekBarView()                           //~1331I~
    {                                                              //~1331I~
    	int id=seekbarviewtbl[currentLayoutSeekBarSeqNo++];        //~1331I~
        if (Dump.Y) Dump.println("findSeekBarView id="+Integer.toHexString(id));//~1506R~
		return findViewById(id);                                   //~1331I~
    }                                                              //~1331I~
//****************                                                 //~1326I~
	public static boolean isTopFrame()                             //~1326I~
    {                                                              //~1326I~
        Frame frame=Window.getCurrentFrame();                      //~1326I~
        if (Dump.Y) Dump.println("isTopFrame current frame:"+frame.framename);//~1506R~
        return frame==AG.mainframe;                                //~1326I~
    }                                                              //~1326I~
	public static boolean isMainFrame(Frame Pframe)                //~1331I~
    {                                                              //~1331I~
	    return (Pframe.framelayoutresourceid==AG.frameId_MainFrame);//~1331I~
    }                                                              //~1331I~
//****************                                                 //~1126I~
//*for Modal Dialog                                                //~1126I~
//****************                                                 //~1126I~
	public static Thread mainThread;                               //~1126I~
    public static boolean isMainThread()                           //~1126M~
    {                                                              //~1126M~
    	return (Thread.currentThread()==AG.mainThread);              //~1126M~
    }                                                              //~1126M~
//**************************************************************   //~@@@@I~
    public static boolean isChessBoard()                           //~@@@@I~
    {                                                              //~@@@@I~
    	return (propBoardSize==BOARDSIZE_CHESS);                    //~@@@@I~
    }                                                              //~@@@@I~
//*************************************************************    //~va40I~//~1Ak3I~
    public static int getColor(int Pcolor)                         //~va40R~//~1Ak3I~
    {                                                              //~va40I~//~1Ak3I~
    	int rc;                                                    //~va40I~//~1Ak3I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) //api23 Marshmallow android6//~va40I~//~1Ak3I~
        	rc= ContextCompat.getColor(AG.context,Pcolor);           //~va40I~//~1Ak3I~
        else                                                       //~va40I~//~1Ak3I~
		    rc=getColor_Under23(Pcolor);                        //~va40I~//~1Ak3I~
        return rc;                                                 //~va40I~//~1Ak3I~
    }                                                              //~va40I~//~1Ak3I~
//*************************************************************    //~va40I~//~1Ak3I~
	@SuppressWarnings("deprecation")                                //~va40I~//~1Ak3I~
    private static int getColor_Under23(int Pcolor)                 //~va40R~//~1Ak3I~
    {                                                              //~va40I~//~1Ak3I~
        return AG.resource.getColor(Pcolor);                    //~va40I~//~1Ak3I~
    }                                                              //~va40I~//~1Ak3I~
//*******************                                              //~1120I~
}//class AG                                                        //~1107R~
