//*CID://+1AhkR~: update#= 178;                                    //~1Ah1R~//~1AhkR~
//**********************************************************************//~1107I~
//1Ahk 2020/06/05 Connect button for all connection type           //~1AhkI~
//1Ah1 2020/05/30 from BTMJ5                                       //~1Ah1I~
//1Ac0 2015/07/06 for mutual exclusive problem of IP and wifidirect;try to use connectivityManager API//~1Ac0I~
//1Aa6 2015/04/20 show youtube movie                               //~1Aa6I~
//1A8bk2015/02/28 (BUG)old pertner communication fail by multiple IP address//~1A8bI~
//1A86 2015/02/26 get IPAddr by MacAddr                            //~1A86I~
//1A05 2015/02/16 Asgts:2013/03/02 reject Accept/Connect if IP addr not avail//~1A05I~
//1A6a 2015/02/10 NFC+Wifi support                                 //~1A67I~
//1A67 2015/02/05 (kan)                                            //~1A67I~
//101a 2013/01/30 IP connection                                    //~v101I~
//1075:121207 control dumptrace by manifest debuggable option      //~v105I~
//1063:121124 menu to display ip address for pertner connection    //~v106I~
//**********************************************************************//~1107I~//~v106M~
package com.Ahsv;                                         //~1107R~  //~1108R~//~1109R~//~v107R~


import java.net.Inet6Address;
import java.net.InetAddress;                                       //~v106R~
import java.net.NetworkInterface;                                  //~v106R~
import java.net.Socket;
import java.util.Arrays;
import java.util.Enumeration;                                      //~v106I~
import java.text.SimpleDateFormat;
import java.util.Date;

import jagoclient.Dump;

import android.annotation.TargetApi;
import android.content.Context;                                    //~v107R~
import android.content.Intent;
import android.content.pm.ApplicationInfo;                         //~v107R~
import android.content.pm.PackageManager;                          //~v107R~
import android.content.pm.PackageManager.NameNotFoundException;    //~v107R~
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;

import com.Ahsv.AG;                                                //~v107R~

//**********************************************************************//~1107I~
public class Utils                                            //~1309R~//~@@@@R~
{                                                                  //~0914I~
    public static final String	IPA_NA="N/A";                      //~1A05I~
//**********************************                               //~@@@@I~
//*from Alert,replyed Yes                                          //~@@@@I~
//**********************************                               //~@@@@I~
	public static void stopFinish()		//from Alert by Stop:Yes   //~@@@2I~
    {                                                              //~@@@2I~
    	if (Dump.Y) Dump.println("AjagoUtils stopFinish");         //~@@@2I~
       try                                                        //~@@@2I~
        {                                                          //~@@@2I~
        	AG.aMain.destroyClose();                               //~@@@2R~
        }                                                          //~@@@2I~
        catch (Exception e)                                               //~@@@2I~
        {                                                          //~@@@2I~
        	Dump.println(e,"stopFinish");                          //~@@@2I~
            finish();                                              //~@@@2I~
        }                                                          //~@@@2I~
    }                                                              //~@@@2I~
//**********************************                               //~1211I~
	public static void exit(int Pexitcode)	//from Mainframe:doclose()                         //~1309I~//~@@@2R~
    {                                                              //~1309I~
    	if (Dump.Y) Dump.println("AjagoUtils exit()");//~1309I~//~1506R~//~@@@2R~
		finish();                                                  //~1309R~
    }                                                              //~1309I~
//*******************************************************          //~@@@@R~
//*from onDestroy,kill process                                                  //~@@@@I~//~@@@2R~
//*******************************************************          //~@@@@I~
	public static void exit(int Pexitcode,boolean Pkill)           //~1329I~
    {                                                              //~1329I~
    	if (Dump.Y) Dump.println("AjagoUtils kill exit() code="+Pexitcode+",kill="+Pkill);//~1506R~//~@@@2R~
        if (Pkill)                                                 //~1329I~
        {                                                          //~1503I~
//            System.runFinalizersOnExit(true);   //depricated unsafe//~@@@@R~
//            System.exit(Pexitcode);                                //~1329I~//~@@@@R~
    		if (Dump.Y) Dump.println("AjagoUtils kill exit() killProcess");//~@@@2I~
            Dump.close();                                          //~1503I~//~@@@2M~
            android.os.Process.killProcess(android.os.Process.myPid());                  //~@@@@I~//~@@@2R~
//**          finish only,check static is reused                   //~@@@2I~
        }                                                          //~1503I~
//        else                                                       //~@@@@I~//~@@@2R~
//        {                                                        //~@@@2R~
//            if (Dump.Y) Dump.println("AjagoUtils kill exit() exit()");//~@@@2R~
////            exit(Pexitcode);                                           //~1329I~//~@@@@R~//~@@@2R~
//        }                                                        //~@@@2R~
//        finish();                                                //~@@@2R~
    }                                                              //~1329I~
//**********************************                               //~@@@@I~
//*from Alert,replyed Yes                                          //~@@@@I~
//**********************************                               //~@@@@I~
//    public static void finish()                                         //~1309I~//~@@@@R~
//    {                                                              //~1309I~//~@@@@R~
//        if (Dump.Y) Dump.println("AjagoUtils finish requested "+finished);//~1506R~//~@@@@R~
//        if (finished)                                              //~1309M~//~@@@@R~
//            return ;                                               //~1309M~//~@@@@R~
//        AG.aMain.destroyClose();                                 //~@@@@R~
//        AG.aMain.finish();                                        //~1309I~//~@@@@R~
//        if (Dump.Y) Dump.println("AjagoUtils context finish request");//~1506R~//~@@@@R~
//        sleep(1200);//wait subtread termination  1.2sec            //~1503R~//~@@@@R~
//        if (Dump.Y) Dump.println("AjagoUtils context finish request after sleep 1200");//~1506R~//~@@@@R~
//        Dump.close();                                              //~1503I~//~@@@@R~
//        finished=true;                                             //~1309I~//~@@@@R~
//    }                                                              //~1309I~//~@@@@R~
//**********************************                               //~@@@2I~
//*Activity:finish()                                               //~@@@2I~
//**********************************                               //~@@@2I~
    public static void finish()                                    //~@@@@I~
    {                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("Utils finish");             //~@@@@I~//~@@@2R~
//        closeFinish();                                             //~@@@@I~//~@@@2R~
        AG.aMain.finish();	//schedule onDestroy                   //~@@@2I~
    }                                                              //~@@@@I~
//**********************************                               //~@@@2I~
//    public static void closeFinish()                               //~@@@@I~//~@@@2R~
//    {                                                              //~@@@@I~//~@@@2R~
//        if (Dump.Y) Dump.println("Utils closeFinish finished="+AG.Utils_finished);//~@@@@M~//~@@@2R~
//        if (AG.Utils_finished)                                              //~@@@@I~//~@@@2R~
//            return ;                                               //~@@@@I~//~@@@2R~
//        AG.aMain.destroyClose();    //close stream the finish      //~@@@@I~//~@@@2R~
//    }                                                              //~@@@@I~//~@@@2R~
//    public static void closedFinish()                              //~@@@@I~//~@@@2R~
//    {                                                              //~@@@@I~//~@@@2R~
//        if (Dump.Y) Dump.println("Utils closedFinish finished="+AG.Utils_finished);//~@@@@I~//~@@@2R~
//        if (AG.Utils_finished)                                              //~@@@@I~//~@@@2R~
//            return ;                                               //~@@@@I~//~@@@2R~
//        AG.aMain.finish();  //schedule onDestroy                   //~@@@@I~//~@@@2R~
//        if (Dump.Y) Dump.println("Utils context finish request");//~@@@@I~//~@@@2R~
//        sleep(200);//wait subtread termination  1.2sec             //~@@@@R~//~@@@2R~
//        if (Dump.Y) Dump.println("Utils context finish request after sleep 200");//~@@@@I~//~@@@2R~
//        Dump.close();                                              //~@@@@I~//~@@@2R~
//        AG.Utils_finished=true;                                             //~@@@@I~//~@@@2R~
//    }                                                              //~@@@@I~//~@@@2R~
//**********************************                               //~@@@@I~
	public static void sleep(long Pmilisec)                        //~1503I~
    {                                                              //~1503I~
        try                                                        //~1503I~
        {                                                          //~1503I~
        	Thread.sleep(Pmilisec);//wait subtread termination  1.2sec//~1503I~
        }                                                          //~1503I~
        catch(InterruptedException e)                              //~1503I~
		{                                                          //~1503I~
        	Dump.println(e,"sleep interrupted Exception");         //~1503I~
		}                                                          //~1503I~
    }                                                              //~1503I~
//**********************************                               //~1412I~
//*elapsed time calc                                               //~1412I~
//**********************************                               //~1412I~
	public static final int TSID_TITLE_TOUCH=0;                   //~1412I~
	private static final int TSID_MAX        =1;                   //~1412I~
	private static long[] Stimestamp=new long[TSID_MAX];                                 //~1412I~
	public static long setTimeStamp(int Pid)                       //~1412I~
    {                                                              //~1412I~
        if (Pid>=TSID_MAX)                                         //~1412I~
            return 0;                                              //~1412I~
        long t=System.currentTimeMillis();                         //~1412I~
        Stimestamp[Pid]=t;                                         //~1412I~
    	if (Dump.Y) Dump.println("AjagoUtils setTimeStamp id="+Pid+",ts="+Long.toHexString(t));//~1506R~
        return t;                                                  //~1412I~
    }                                                              //~1412I~
	public static int getElapsedTimeMillis(int Pid)                //~1412I~
    {                                                              //~1412I~
        if (Pid>=TSID_MAX)                                         //~1412I~
            return 0;                                              //~1412I~
        if (Stimestamp[Pid]==0)                                    //~1413I~
            return 0;                                              //~1413I~
        long t=System.currentTimeMillis();                         //~1412I~
    	if (Dump.Y) Dump.println("AjagoUtils getElapsed now id="+Pid+",ts="+Long.toHexString(t));//~1506R~
        int  elapsed=(int)(t-Stimestamp[Pid]);                     //~1412I~
    	if (Dump.Y) Dump.println("AjagoUtils getElapsetTimeMillis id="+Pid+",ts="+Integer.toHexString(elapsed));//~1506R~
        Stimestamp[Pid]=0;                                         //~1413I~
        return elapsed;                                            //~1412I~
    }                                                              //~1412I~
//**********************************                               //~1425I~
//*edit date/time                                                  //~1425I~
//**********************************                               //~1425I~
	public static final int TS_DATE_TIME=1;                        //~1425I~
	public static final int TS_MILI_TIME=2;                        //~1425I~
	public static final int TS_DATE_TIME2=3;                       //~1Ah1I~
	private static final SimpleDateFormat fmtdt=new SimpleDateFormat("yyyyMMdd-HHmmss");//~1425I~
	private static final SimpleDateFormat fmtdt2=new SimpleDateFormat("yyyyMMdd:HHmmss");//~1Ah1I~
	private static final SimpleDateFormat fmtms=new SimpleDateFormat("HHmmss.SSS");//~1425I~
//**********************************                               //~1Ah1I~
	public static String getTimeStamp(int Popt,Date Pdate)         //~1Ah1I~
    {                                                              //~1Ah1I~
        SimpleDateFormat f;                                        //~1Ah1I~
    //**********************:                                      //~1Ah1I~
    	switch(Popt)                                               //~1Ah1I~
        {                                                          //~1Ah1I~
        case TS_DATE_TIME:                                         //~1Ah1I~
        	f=fmtdt;                                               //~1Ah1I~
            break;                                                 //~1Ah1I~
        case TS_DATE_TIME2:                                        //~1Ah1I~
        	f=fmtdt2;                                              //~1Ah1I~
            break;                                                 //~1Ah1I~
        case TS_MILI_TIME:                                         //~1Ah1I~
        	f=fmtms;                                               //~1Ah1I~
            break;                                                 //~1Ah1I~
        default:                                                   //~1Ah1I~
        	return null;                                           //~1Ah1I~
        }                                                          //~1Ah1I~
        String s=f.format(Pdate);                                  //~1Ah1I~
//      if (Dump.Y) Dump.println("Utils.getTimeStamp opt="+Popt+",rc="+s);//~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************************                               //~1Ah1I~
//    public static String getTimeStamp(int Popt)                    //~1425I~//~1Ah1R~
//    {                                                              //~1425I~//~1Ah1R~
//        SimpleDateFormat f;                                        //~1425I~//~1Ah1R~
//    //**********************:                                      //~1425I~//~1Ah1R~
//        switch(Popt)                                               //~1425I~//~1Ah1R~
//        {                                                          //~1425I~//~1Ah1R~
//        case TS_DATE_TIME:                                         //~1425I~//~1Ah1R~
//            f=fmtdt;                                               //~1425I~//~1Ah1R~
//            break;                                                 //~1425I~//~1Ah1R~
//        case TS_DATE_TIME2:                                      //~1Ah1I~
//            f=fmtdt2;                                            //~1Ah1I~
//            break;                                               //~1Ah1I~
//        case TS_MILI_TIME:                                         //~1425I~//~1Ah1R~
//            f=fmtms;                                               //~1425I~//~1Ah1R~
//            break;                                                 //~1425I~//~1Ah1R~
//        default:                                                   //~1425I~//~1Ah1R~
//            return null;                                           //~1425I~//~1Ah1R~
//        }                                                          //~1425I~//~1Ah1R~
//        return f.format(new Date());                               //~1425I~//~1Ah1R~
//    }                                                              //~1425I~//~1Ah1R~
	public static String getTimeStamp(int Popt)                    //~1Ah1I~
    {                                                              //~1Ah1I~
        return getTimeStamp(Popt,new Date());                      //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************************                               //~1Ah1I~
	public static String getTimeStamp(int Popt,long Ptime)         //~1Ah1I~
    {                                                              //~1Ah1I~
        return getTimeStamp(Popt,new Date(Ptime));                 //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************************                               //~1Ah1I~
	public static String getTimeStamp(String Pfmt,long Ptime)      //~1Ah1I~
    {                                                              //~1Ah1I~
		SimpleDateFormat f=new SimpleDateFormat(Pfmt);             //~1Ah1I~
        return f.format(new Date(Ptime));                          //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************************                               //~1425I~
//* Digit Thread ID                                                //~1425I~
//**********************************                               //~1425I~
	public static String getThreadId()                             //~1425I~
    {                                                              //~1425I~
    //**********************:                                      //~1425I~
    	int tid=(int)Thread.currentThread().getId();               //~1425I~
        if (tid<10)                                                //~1425I~
        	return "0"+tid;                                        //~1425I~
        return Integer.toString(tid);                              //~1425I~
    }                                                              //~1425I~
//**********************************                               //~1425I~
	public static String getThreadTimeStamp()                      //~1425I~
    {                                                              //~1425I~
    //**********************:                                      //~1425I~
    	String tidts=getThreadId()+":"+getTimeStamp(TS_MILI_TIME);  //~1425I~
        return tidts;                                              //~1425I~
    }                                                              //~1425I~
//***************************************************************************//~1A8bI~
	private static int SswDirect;                                  //~1A8bI~
    private static final int MAC_LOCAL_ADDRESS=0x02;	//if off global address//~1A8bI~
//***********                                                      //~1A8bI~
    public static String getIPAddressDirect()                      //~1A8bI~
    {                                                              //~1A8bI~
    	SswDirect=1; //local only                                  //~1A8bI~
    	String ipa=getIPAddress(false);                            //~1A8bI~
    	SswDirect=0;                                               //~1A8bI~
        return ipa;                                                //~1A8bI~
    }                                                              //~1A8bI~
//***********                                                      //~1A8bI~
    public static String getIPAddressAll()                         //~1A8bI~
    {                                                              //~1A8bI~
    	SswDirect=2; //both global and local                       //~1A8bI~
    	String ipa=getIPAddress(false);                            //~1A8bI~
    	SswDirect=0;                                               //~1A8bI~
        return ipa;                                                //~1A8bI~
    }                                                              //~1A8bI~
//**********************************                               //~v106I~
    public static String getIPAddress(boolean Pipv6)                            //~v106I~//~v101R~
    {                                                              //~v106I~
//  	String ipa="N/A";                                          //~v106R~//~1A05R~
    	String ipa=IPA_NA;                                         //~1A05I~
    	String ipv6="";                                           //~v106I~
        int ctr=0;                                                 //~1A67I~
    //**********************:                                      //~v106I~
        try                                                        //~v106I~
        {                                                          //~v106I~
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();//~v106I~
            while(interfaces.hasMoreElements())                    //~v106I~
            {                                                      //~v106I~
                NetworkInterface network = interfaces.nextElement();//~v106I~
    		    if (Dump.Y) Dump.println("getIPAddress mac="+getMacString(network.getHardwareAddress()));//~1A67R~
    		    byte[] bmac=network.getHardwareAddress();          //~1A8bI~
                if (bmac!=null)                                    //~1A8bI~
                {                                                  //~1A8bI~
                    if ((bmac[0] & MAC_LOCAL_ADDRESS)!=0)          //~1A8bI~
                    {                                              //~1A8bI~
                        if (SswDirect==0) //global only            //~1A8bI~
                            continue;                              //~1A8bI~
                    }                                              //~1A8bI~
                    else    //global                               //~1A8bI~
                    {                                              //~1A8bI~
                        if (SswDirect==1)   //local only           //~1A8bI~
                            continue;                              //~1A8bI~
                    }                                              //~1A8bI~
                }                                                  //~1A8bI~
    		    if (Dump.Y) Dump.println("isPont2point="+network.isPointToPoint()+",isUp="+network.isUp());//~1A05I~
    		    if (Dump.Y) Dump.println("name="+network.getName()+",displayName="+network.getDisplayName());//~1A05R~
    		    if (Dump.Y) Dump.println("toString="+network.toString());//~1A05I~
                Enumeration<InetAddress> addresses = network.getInetAddresses();//~v106I~
                while(addresses.hasMoreElements())                 //~v106I~
                {                                                  //~v106I~
                    InetAddress na=addresses.nextElement();        //~v106R~
                    String ipa2=na.getHostAddress();               //~v106I~
                    if (na.isLoopbackAddress()                     //~v106R~
                    ||  na.isLinkLocalAddress()                    //~v106R~
//                  ||  na.isSiteLocalAddress()                    //~v106R~
                    )                                              //~v106I~
                    	continue;                                  //~v106I~
                    if (na instanceof Inet6Address)//ipv6          //~v106M~
                    {                                              //~v106I~
                    	ipv6=ipa2;                                 //~v106I~
                    	continue;                                  //~v106M~
                    }                                              //~v106I~
			        if (Dump.Y) Dump.println("getIPAddress:"+ipa2);//~v106R~
                  if (ctr++==0)                                    //~1A67I~
                    ipa=ipa2;                                      //~v106R~//~1A67R~
				  else                                             //~1A67I~
                    ipa+=";"+ipa2;                                 //~1A67R~
                    break;                                         //~v106R~
                }                                                  //~v106I~
            }                                                      //~v106I~
        }                                                          //~v106I~
        catch(Exception e)                                         //~v106I~
        {                                                          //~v106I~
        	Dump.println(e,"getIPAddress");                        //~v106I~
        }                                                          //~v106I~
//      if (!Pipv6)                                                //~v101I~//~1A8bR~
        if (!Pipv6 || ipv6.equals(""))                             //~1B10R~//~1A8bI~
            return ipa;                                             //~v101I~//~1A67R~
        return ipa+","+ipv6;                                       //~v106R~
    }                                                              //~v106I~
//**********************************                               //~1A86I~
    public static String getIPAddressFromMacAddr(String Pmacaddr)  //~1A86I~
    {                                                              //~1A86I~
    	String ipa=IPA_NA;                                         //~1A86I~
//  	String ipv6="";                                            //~1A86I~
        int ctr=0;                                                 //~1A86I~
    //**********************:                                      //~1A86I~
        try                                                        //~1A86I~
        {                                                          //~1A86I~
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();//~1A86I~
            while(interfaces.hasMoreElements())                    //~1A86I~
            {                                                      //~1A86I~
                NetworkInterface network = interfaces.nextElement();//~1A86I~
                String macaddr=getMacString(network.getHardwareAddress());//~1A86I~
                if (Dump.Y) Dump.println("getIPAddressFromMacaddr mac="+macaddr+",parm="+Pmacaddr);//~1A86I~
    		    if (Dump.Y) Dump.println("isPont2point="+network.isPointToPoint()+",isUp="+network.isUp());//~1A86I~
    		    if (Dump.Y) Dump.println("name="+network.getName()+",displayName="+network.getDisplayName());//~1A86I~
    		    if (Dump.Y) Dump.println("toString="+network.toString());//~1A86I~
                if (!macaddr.equals(Pmacaddr))                     //~1A86I~
                	continue;                                      //~1A86I~
                Enumeration<InetAddress> addresses = network.getInetAddresses();//~1A86I~
                while(addresses.hasMoreElements())                 //~1A86I~
                {                                                  //~1A86I~
                    InetAddress na=addresses.nextElement();        //~1A86I~
                    String ipa2=na.getHostAddress();               //~1A86I~
                    if (na.isLoopbackAddress()                     //~1A86I~
                    ||  na.isLinkLocalAddress()                    //~1A86I~
//                  ||  na.isSiteLocalAddress()                    //~1A86I~
                    )                                              //~1A86I~
                    	continue;                                  //~1A86I~
                    if (na instanceof Inet6Address)//ipv6          //~1A86I~
                    {                                              //~1A86I~
//                    	ipv6=ipa2;                                 //~1A86I~
                    	continue;                                  //~1A86I~
                    }                                              //~1A86I~
			        if (Dump.Y) Dump.println("getIPAddress:"+ipa2);//~1A86I~
                  if (ctr++==0)                                    //~1A86I~
                    ipa=ipa2;                                      //~1A86I~
				  else                                             //~1A86I~
                    ipa+=";"+ipa2;                                 //~1A86I~
                    break;                                         //~1A86I~
                }                                                  //~1A86I~
            }                                                      //~1A86I~
        }                                                          //~1A86I~
        catch(Exception e)                                         //~1A86I~
        {                                                          //~1A86I~
        	Dump.println(e,"getIPAddressFromMacAddr");             //~1A86I~
        }                                                          //~1A86I~
//      if (!Pipv6 || ipv6.equals(""))                             //~1A86I~
//          return ipa;                                            //~1A86I~
//      return ipa+","+ipv6;                                       //~1A86I~
        return ipa;                                                //~1A86I~
    }                                                              //~1A86I~
//***********************************************************************//~v107R~
    public static boolean isDebuggable(Context ctx)                //~v107R~
    {                                                              //~v107R~
        PackageManager manager = ctx.getPackageManager();          //~v107R~
        ApplicationInfo appInfo = null;                            //~v107R~
        try                                                        //~v107R~
        {                                                          //~v107R~
            appInfo = manager.getApplicationInfo(ctx.getPackageName(), 0);//~v107R~
        }                                                          //~v107R~
        catch (NameNotFoundException e)                            //~v107R~
        {                                                          //~v107R~
            return false;                                          //~v107R~
        }                                                          //~v107R~
        if ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE)//~v107R~
            return true;                                           //~v107R~
        return false;                                              //~v107R~
    }                                                              //~v107R~
//***********************************************************************//~1A67I~//~1A6aR~
    public static String getMacString(byte[] Pbytemacaddr)                //~1A67R~//~1A6aR~
    {                                                              //~1A67R~//~1A6aR~
        if (Pbytemacaddr==null)                                    //~1A6aR~
            return "";                                             //~1A6aR~
        StringBuilder sb=new StringBuilder("");                      //~1A67R~//~1A6aR~
        int sz=Pbytemacaddr.length;                                //~1A67R~//~1A6aR~
        for (int ii=0;ii<sz;ii++)                                  //~1A67R~//~1A6aR~
        {                                                          //~1A67R~//~1A6aR~
            sb.append(String.format("%s%02x",((ii==0) ? "" : ":"),Pbytemacaddr[ii]));//~1A67R~//~1A6aR~
        }                                                          //~1A67R~//~1A6aR~
        return new String(sb);                                     //~1A67R~//~1A6aR~
    }                                                              //~1A67R~//~1A6aR~
//***********************************************************************//~1A6aI~
	public static String getRemoteIPAddr(Socket Psocket,String Pnullopt)                       //~@@@2I~//~1A8bI~
    {                                                              //~@@@2I~//~1A8bI~
    	String ipa=null;                                           //~1A8bI~
        InetAddress ia=Psocket.getInetAddress();                   //~@@@2I~//~1A8bI~
        if (ia!=null)                                              //~@@@2M~//~1A8bI~
        {                                                          //~@@@2M~//~1A8bI~
	        ipa=ia.getHostAddress();              //~@@@2R~        //~1A8bI~
	        if (Dump.Y) Dump.println("AjagoUtils:getRemoteIPAddr="+ipa+",name="+ia.getHostName());//~@@@2I~//~1A8bI~
        }                                                          //~@@@2M~//~1A8bI~
        if (ipa==null)                                             //~1A8bI~
        {                                                          //~1A8bI~
        	ipa=Pnullopt;                                          //~1A8bI~
        }                                                          //~1A8bI~
        return ipa;                                                //~1A8bI~
    }                                                              //~@@@2I~//~1A8bI~
//***********************************************************************//~1A8bI~
	public static String getLocalIPAddr(Socket Psocket,String Pnullopt)//~1A8bI~
    {                                                              //~1A8bI~
    	String ipa=null;                                           //~1A8bI~
        InetAddress ia=Psocket.getLocalAddress();                  //~1A8bI~
        if (ia!=null)                                              //~1A6sI~//~1A8bI~
        {                                                          //~1A6sI~//~1A8bI~
	        ipa=ia.getHostAddress();               //~1A6sI~       //~1A8bI~
	        if (Dump.Y) Dump.println("AjagoUtils:getLocalIPAddr="+ipa+",name="+ia.getHostName());//~1A8bI~
        }                                                          //~1A6sI~//~1A8bI~
        if (ipa==null)                                             //~1A8bI~
        {                                                          //~1A8bI~
        	ipa=Pnullopt;                                          //~1A8bI~
        }                                                          //~1A8bI~
        return ipa;                                                //~1A8bI~
    }                                                              //~1A8bI~
//***********************************************************      //~v1E7I~//~1Aa6I~
	public static void showWebSite(String Purl)                    //~v1E7I~//~1Aa6I~
	{                                                              //~v1E7I~//~1Aa6I~
		if (Dump.Y) Dump.println("Utils:showWebSite url="+Purl);   //~v1E7I~//~1Aa6I~
        Intent intent=new Intent(Intent.ACTION_VIEW);              //~v1E7I~//~1Aa6I~
        intent.setData(Uri.parse(Purl));                           //~v1E7I~//~1Aa6I~
        AG.activity.startActivity(intent);                         //~v1E7I~//~1Aa6I~
	}                                                              //~v1E7I~//~1Aa6I~
//***********************************************************************//~1Ac0I~
    public static void chkNetwork()                                //~1Ac0I~
    {                                                              //~1Ac0I~
		if (AG.osVersion>= Build.VERSION_CODES.M)   //23             //~1Ah1I~
		    chkNetwork_M();                                         //~1Ah1I~
        else                                                       //~1Ah1I~
	    	chkNetwork_L();                                         //~1Ah1I~
    }                                                              //~1Ah1I~
    //*******************                                          //~1Ah1I~
	@SuppressWarnings("deprecation")                               //~1Ah1I~
    public static void chkNetwork_L()      //<23                   //~1Ah1R~
    {                                                              //~1Ah1I~
        ConnectivityManager cm=getCM();                            //~1Ac0I~
        NetworkInfo[] infos=cm.getAllNetworkInfo();                //~1Ac0I~
        if (Dump.Y) Dump.println("Utils:chkNetwork_L ctr="+infos.length);//~1Ac0I~//~1Ah1R~
        for (NetworkInfo ni:infos)                                 //~1Ac0I~
        {                                                          //~1Ac0I~
        	String typename=ni.getTypeName();                      //~1Ac0I~
        	String subtypename=ni.getSubtypeName();                //~1Ac0I~
        	boolean connected=ni.isConnected();                    //~1Ac0I~
            if (Dump.Y) Dump.println("Utils:chkNetwork_L type="+typename+",subtype="+subtypename+",connected="+connected+",tostring="+ni.toString());//~1Ac0I~//~1Ah1R~
        }                                                          //~1Ac0I~
    }                                                              //~1Ac0I~
//***********************************************************************//~1Ah1I~
	@TargetApi(Build.VERSION_CODES.M)   //>=23                     //~1Ah1R~
    public static void chkNetwork_M()                              //~1Ah1I~
    {                                                              //~1Ah1I~
        ConnectivityManager cm=getCM();                            //~1Ah1I~
        Network[] anw=cm.getAllNetworks();                         //~1Ah1I~
        if (Dump.Y) Dump.println("Utils:chkNetwork_M ctr="+anw.length);//~1Ah1I~
        NetworkInfo ni;                                            //~1Ah1I~
        for (Network nw:anw)                                       //~1Ah1I~
        {                                                          //~1Ah1I~
        	ni=cm.getNetworkInfo(nw);                           //~1Ah1I~
            if (ni!=null)                                          //~1Ah1I~
            {                                                      //~1Ah1I~
//      		String typename=ni.getTypeName();                  //~1Ah1R~
        		String subtypename=ni.getSubtypeName();            //~1Ah1I~
        		boolean connected=ni.isConnected();                //~1Ah1I~
            	if (Dump.Y) Dump.println("Utils:chkNetwork_M subtype="+subtypename+",connected="+connected+",tostring="+ni.toString());//~1Ah1R~
            }                                                      //~1Ah1I~
        }                                                          //~1Ah1I~
    }                                                              //~1Ah1I~
//***********************************************************************//~1Ac0I~
    public static ConnectivityManager getCM()                      //~1Ac0I~
    {                                                              //~1Ac0I~
        return (ConnectivityManager)AG.context.getSystemService(Context.CONNECTIVITY_SERVICE);//~1Ac0I~
    }                                                              //~1Ac0I~
    //*************************************************            //~1Ah1I~
    public static String toString(String[] Psa)                    //~1Ah1I~
    {                                                              //~1Ah1I~
    	String s;                                                  //~1Ah1I~
        if (Psa==null)                                             //~1Ah1I~
	        s="null";                                              //~1Ah1I~
        else                                                       //~1Ah1I~
            s=Arrays.toString(Psa);                                //~1Ah1I~
        if (Dump.Y) Dump.println("Utils.toString(String[]) out="+s);//~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(String[][] Psa2)                 //~1Ah1I~
    {                                                              //~1Ah1I~
        StringBuffer sb=new StringBuffer();                        //~1Ah1I~
        sb.append("[");                                            //~1Ah1I~
        if (Psa2==null)                                            //~1Ah1I~
	        sb.append("null");                                     //~1Ah1I~
        else                                                       //~1Ah1I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~1Ah1I~
        {                                                          //~1Ah1I~
        	if (ii!=0)                                             //~1Ah1I~
    	        sb.append(",");                                    //~1Ah1I~
            sb.append(Arrays.toString(Psa2[ii]));                  //~1Ah1I~
        }                                                          //~1Ah1I~
        sb.append("]");                                            //~1Ah1I~
        String s=sb.toString();                                    //~1Ah1I~
        if (Dump.Y) Dump.println("Utils.toString(String[][]) out="+s);//~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(int[][] Psa2)                    //~1Ah1I~
    {                                                              //~1Ah1I~
        StringBuffer sb=new StringBuffer();                        //~1Ah1I~
        sb.append("[");                                            //~1Ah1I~
        if (Psa2==null)                                            //~1Ah1I~
	        sb.append("null");                                     //~1Ah1I~
        else                                                       //~1Ah1I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~1Ah1I~
        {                                                          //~1Ah1I~
        	if (ii!=0)                                             //~1Ah1I~
    	        sb.append(",");                                    //~1Ah1I~
            sb.append(Arrays.toString(Psa2[ii]));                  //~1Ah1I~
        }                                                          //~1Ah1I~
        sb.append("]");                                            //~1Ah1I~
        String s=sb.toString();                                    //~1Ah1I~
        if (Dump.Y) Dump.println("Utils.toString(int[][]) out="+s);//~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(boolean[][] Psa2)                //~1Ah1I~
    {                                                              //~1Ah1I~
        StringBuffer sb=new StringBuffer();                        //~1Ah1I~
        sb.append("[");                                            //~1Ah1I~
        if (Psa2==null)                                            //~1Ah1I~
	        sb.append("null");                                     //~1Ah1I~
        else                                                       //~1Ah1I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~1Ah1I~
        {                                                          //~1Ah1I~
        	if (ii!=0)                                             //~1Ah1I~
    	        sb.append(",");                                    //~1Ah1I~
            sb.append(Arrays.toString(Psa2[ii]));                  //~1Ah1I~
        }                                                          //~1Ah1I~
        sb.append("]");                                            //~1Ah1I~
        String s=sb.toString();                                    //~1Ah1I~
        if (Dump.Y) Dump.println("Utils.toString(boolean[][]) out="+s);//~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(Rect[] Psa2)                     //~1Ah1I~
    {                                                              //~1Ah1I~
        StringBuffer sb=new StringBuffer();                        //~1Ah1I~
        sb.append("[");                                            //~1Ah1I~
        if (Psa2==null)                                            //~1Ah1I~
	        sb.append("null");                                     //~1Ah1I~
        else                                                       //~1Ah1I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~1Ah1I~
        {                                                          //~1Ah1I~
        	if (ii!=0)                                             //~1Ah1I~
    	        sb.append(",");                                    //~1Ah1I~
            sb.append(Psa2[ii]==null ? "null" : Psa2[ii].toString());//~1Ah1I~
        }                                                          //~1Ah1I~
        sb.append("]");                                            //~1Ah1I~
        String s=sb.toString();                                    //~1Ah1I~
        if (Dump.Y) Dump.println("Utils.toString(Rect[]) out="+s); //~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(Rect[][] Psa2)                   //~1Ah1I~
    {                                                              //~1Ah1I~
        StringBuffer sb=new StringBuffer();                        //~1Ah1I~
        sb.append("[");                                            //~1Ah1I~
        if (Psa2==null)                                            //~1Ah1I~
	        sb.append("null");                                     //~1Ah1I~
        else                                                       //~1Ah1I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~1Ah1I~
        {                                                          //~1Ah1I~
        	if (ii!=0)                                             //~1Ah1I~
    	        sb.append(",");                                    //~1Ah1I~
            sb.append(toString(Psa2[ii]));                         //~1Ah1I~
        }                                                          //~1Ah1I~
        sb.append("]");                                            //~1Ah1I~
        String s=sb.toString();                                    //~1Ah1I~
        if (Dump.Y) Dump.println("Utils.toString(Rect[][]) out="+s);//~1Ah1I~
        return s;                                                  //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(String Pstr)                     //~1Ah1I~
    {                                                              //~1Ah1I~
    	return Pstr==null ? "null" : Pstr;                         //~1Ah1I~
    }                                                              //~1Ah1I~
    //*************************************************            //~1Ah1I~
    public static String toString(Object Pobj)                     //~1Ah1I~
    {                                                              //~1Ah1I~
    	return Pobj==null ? "null" : Pobj.toString();              //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************                                           //~1Ah1I~
    public static String getStr(int Presid)                        //~1Ah1I~
	{                                                              //~1Ah1I~
    	if (Presid==0)                                             //~1Ah1I~
        	return "";                                             //~1Ah1I~
    	return AG.resource.getString(Presid);                      //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************                                           //+1AhkI~
    public static String[] getStrArray(int Presid)                   //+1AhkI~
	{                                                              //+1AhkI~
    	return AG.resource.getStringArray(Presid);                 //+1AhkI~
    }                                                              //+1AhkI~
//**********************                                           //~1Ah1I~
    public static String getStr(int Presid,String P1)              //~1Ah1I~
	{                                                              //~1Ah1I~
    	return AG.resource.getString(Presid,P1);                   //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************                                           //~1Ah1I~
    public static String getStr(int Presid,int P1)                 //~1Ah1I~
	{                                                              //~1Ah1I~
    	return AG.resource.getString(Presid,P1);                   //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************                                           //~1Ah1I~
    public static String getStr(int Presid,String P1,String P2)    //~1Ah1I~
	{                                                              //~1Ah1I~
    	return AG.resource.getString(Presid,P1,P2);                //~1Ah1I~
    }                                                              //~1Ah1I~
//**********************                                           //~1AhkI~
    public static String joinStr(String Pseparater,String[] Pstrarray)//~1AhkI~
	{                                                              //~1AhkI~
	 //  	StringJoiner sj=new StringJoiner(Pseparater);     //from api24//~1AhkI~
        StringBuffer sb=new StringBuffer();                        //~1AhkI~
        for (String s:Pstrarray)                                   //~1AhkI~
        {                                                          //~1AhkI~
    		sb.append(s+Pseparater);                               //~1AhkI~
    	}                                                          //~1AhkI~
        return sb.substring(0,sb.length()-1);                      //~1AhkI~
    }                                                              //~1AhkI~
//**********************                                           //~1AhkI~
    public static String joinStr(String[] Pstrarray)               //~1AhkI~
	{                                                              //~1AhkI~
        return joinStr(",",Pstrarray);                             //~1AhkI~
    }                                                              //~1AhkI~
}//class AjagoUtils                                                //~1309R~
