//*CID://+1Ah2R~: update#= 307;                                    //~v@@@R~//~9410R~//~1Ah1R~//~1Ah2R~
//**********************************************************************//~v101I~
//1Ah2 2020/05/31 for Android9(Pie)-api28(PlayStore requires),deprected. DialogFragment,Fragmentmanager//~1Ah2I~
//1Ah1 2020/05/30 from BTMJ5                                       //~1Ah1I~
//**********************************************************************//~1Ah1I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;                                    //~0913I~
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
//import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
//import android.app.DialogFragment;                                 //~v@@@I~//~1Ah2R~
//import android.support.v4.app.DialogFragment;                    //~1Ah2R~
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.EmptyStackException;
import de.greenrobot.event.EventBus;
import jagoclient.Dump;

import com.Ahsv.Alert;
import com.Ahsv.R;                                                 //~1Ah1R~
import com.Ahsv.AView;                                             //~1Ah1I~
                                                                   //~v@@@I~
//import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~//~0530R~//~1Ah1R~
import com.Ahsv.AG;                                         //~0530I~//~1Ah1R~
import com.Ahsv.Utils;
//~v@@@I~


public class UView                                                 //~v@@@I~
{                                                                  //~v@@@I~
    private static final int HIGHT_DPI_TV=72;   //?                //~v@@@I~
    private static final int HIGHT_DPI_XHIGH=50;//not tested       //~v@@@I~
    private static final int HIGHT_DPI_HIGH=38;                    //~v@@@I~
    private static final int HIGHT_DPI_MED=25;                     //~v@@@I~
    private static final int HIGHT_DPI_LOW=19;                     //~v@@@I~
    private static final int BASE_NEXUS7=800;                      //~9808I~
    private static final int MULTIWINDOW_SHIFT=50;                 //~0113I~
//    private static Stack<View> stackSnackbarLayout=new Stack<View>();//~v@@@R~
//*************************                                        //~v@@@I~
	public UView()                                                 //~v@@@I~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~@@@@I~
	public static void fixOrientation(boolean Pfix)                      //~@@@@I~//~v@@@R~
    {                                                              //~@@@@I~
        int ori2=ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;      //~@@@@I~
    	if (Pfix)                                                  //~@@@@I~
        {                                                          //~@@@@I~
            int ori=AG.resource.getConfiguration().orientation;    //~@@@@I~
//            if (ori==Configuration.ORIENTATION_LANDSCAPE)          //~1A54I~//~v@@@R~
//                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;//~1A54I~//~v@@@R~
//            else                                                   //~1A54I~//~v@@@R~
//                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//~1A54I~//~v@@@R~
            if (ori==Configuration.ORIENTATION_LANDSCAPE || ori==ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)//~v@@@I~
				ori2=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;    //~v@@@I~
            else                                                   //~v@@@I~
                ori2=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;     //~v@@@I~
        }                                                          //~@@@@I~
        AG.activity.setRequestedOrientation(ori2);                 //~@@@@I~
    }                                                              //~@@@@I~
//*************************                                        //~1A6hI~
	public static void fixOrientation(Activity Pactivity,boolean Pfix)    //~1A6hI~//~v@@@R~
    {                                                              //~1A6hI~
        int ori2=ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;      //~1A6hI~
    	if (Pfix)                                                  //~1A6hI~
        {                                                          //~1A6hI~
            int ori=AG.resource.getConfiguration().orientation;    //~1A6hI~
            if (ori==Configuration.ORIENTATION_LANDSCAPE)          //~1A6hI~
                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;//~1A6hI~
            else                                                   //~1A6hI~
                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//~1A6hI~
        }                                                          //~1A6hI~
        Pactivity.setRequestedOrientation(ori2);                   //~1A6hI~
    }                                                              //~1A6hI~
//*************************                                        //~1122M~
	public static void getScreenSize()                                    //~1122M~//~v@@@R~
    {                                                              //~1122M~
		Display display=((WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1122M~
        Point p=new Point();                                       //~1A6pI~
        getDisplaySize(display,p);                                         //~1A6pR~
        AG.scrWidth=p.x;	//by pixel                             //~1A6pI~
        AG.scrHeight=p.y;   //                                     //~1A6pI~
        if (Dump.Y) Dump.println("UView: getScreenSize w="+p.x+",h="+p.y);//~1506R~//~@@@@R~//~1A6pR~//~v@@@R~
        AG.dip2pix=AG.resource.getDisplayMetrics().density;        //~1428I~
        AG.sp2pix=AG.resource.getDisplayMetrics().scaledDensity;   //~@@@@I~
        if (Dump.Y) Dump.println("UView:getScreenSize dp2pix="+AG.dip2pix+",sp2pix="+AG.sp2pix); //~1506R~//~@@@@R~//~v@@@R~//~9717R~
        AG.portrait=(AG.scrWidth<AG.scrHeight);                    //~1223R~
        getTitleBarHeight();                                       //~1413M~
        getScreenRealSize(display);                                //~v@@@I~
        AG.scrNavigationbarRightWidth=0;                           //~9807I~
        if (!AG.portrait)                                          //~9807I~
            if (AG.scrWidthReal>AG.scrWidth)    //navigationBar on the right//~9807I~
                AG.scrNavigationbarRightWidth=AG.scrWidthReal-AG.scrWidth;    //navigationBar on the right//~9807I~
    }                                                              //~1122M~
    //*******************************************************      //~v@@@I~
	public static void getScreenRealSize(Display Pdisplay)         //~v@@@I~
    {                                                              //~v@@@I~
		if (Build.VERSION.SDK_INT>=19)   //Navigationbar can be hidden//~v@@@R~
        {                                                          //~v@@@I~
	        Point p=new Point();                                   //~v@@@I~
//        	Pdisplay.getSize(p);                                   //~v@@@I~
        	Pdisplay.getRealSize(p); //api17:4.2.2 JELLY bean mr1  //~v@@@R~
        	AG.scrWidthReal=p.x;                                   //~v@@@I~
        	AG.scrHeightReal=p.y;                                  //~v@@@I~
	        if (Dump.Y) Dump.println("UView:getScreemRealSize getRealSize() w="+AG.scrWidthReal+",h="+AG.scrHeightReal);//~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			DisplayMetrics m=new DisplayMetrics();                 //~v@@@R~
			Pdisplay.getMetrics(m);                                //~v@@@R~
        	AG.scrWidthReal=m.widthPixels;                         //~v@@@R~
        	AG.scrHeightReal=m.heightPixels;                       //~v@@@R~
	        if (Dump.Y) Dump.println("UView:getScreenRealSize Displaymetrics w="+AG.scrWidthReal+",h="+AG.scrHeightReal);//~v@@@I~
        }                                                          //~v@@@I~
        int ww=Math.min(AG.scrWidthReal,AG.scrHeightReal);         //~9809R~
        AG.swSmallDevice=ww<BASE_NEXUS7;                           //~9809I~
        AG.scaleSmallDevice=(double)ww/BASE_NEXUS7;               //~9809I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    public static void getTitleBarHeight()                         //~1413R~
    {                                                              //~1413M~
        Rect rect=new Rect();                                      //~1413M~
        android.view.Window w=AG.activity.getWindow();                                 //~1413M~
        View v=w.getDecorView();                                   //~1413M~
        v.getWindowVisibleDisplayFrame(rect);                      //~1413M~
        if (Dump.Y) Dump.println("UView:getTitleBarHeight  DecorView rect="+rect.toString());//~1506R~//~v106R~//~v@@@R~
        v=w.findViewById(android.view.Window.ID_ANDROID_CONTENT);               //~1413M~
        AG.titleBarTop=rect.top;                                   //~1413M~
        AG.titleBarBottom=v.getTop();                              //~1413M~
        if (Dump.Y) Dump.println("UView TitleBar top="+AG.titleBarTop+",bottom="+AG.titleBarBottom);//~1506R~//~v106R~//~v@@@R~
    }                                                              //~1413M~
    public static Point getTitleBarPosition()                      //~1413I~
    {                                                              //~1413I~
    	if (AG.titleBarBottom==0)                                  //~1413I~
        	getTitleBarHeight();                                   //~1413I~
        return new Point(AG.titleBarTop,AG.titleBarBottom);        //~1413I~
    }                                                              //~1413I~
    public static int getFramePosition()                         //~1413I~
    {                                                              //~1413I~
    	if (AG.titleBarBottom==0)                                  //~1413I~
        {                                                          //~@@@@I~
        	getTitleBarHeight();                                   //~1413I~
			if (AG.titleBarBottom==0) //not yet drawn once(in onCreate())//~@@@@I~
            {                                                      //~@@@@I~
            	return getDefaultTitlebarHeight();                 //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        return AG.titleBarBottom;                                  //~1413I~
    }                                                              //~1413I~
//******************                                               //~@@@@I~
    public static int getDefaultTitlebarHeight()                   //~@@@@I~
    {                                                              //~@@@@I~
        int	h=HIGHT_DPI_TV;                                        //~@@@@R~
        int density=AG.resource.getDisplayMetrics().densityDpi;    //~@@@@I~
        if (Dump.Y) Dump.println("UView:getDefaultDencity density="+density);//~9717I~
        switch(density)                                            //~@@@@I~
        {                                                          //~@@@@I~
        case DisplayMetrics.DENSITY_MEDIUM:                        //~@@@@I~
        	h=HIGHT_DPI_MED;                                      //~@@@@I~
            break;                                                 //~@@@@I~
        case DisplayMetrics.DENSITY_LOW:                           //~@@@@I~
        	h=HIGHT_DPI_LOW;                                      //~@@@@I~
            break;                                                 //~@@@@I~
        case DisplayMetrics.DENSITY_HIGH:                          //~@@@@I~
	        h=HIGHT_DPI_HIGH;                                      //~@@@@I~
            break;                                                 //~@@@@I~
        case DisplayMetrics.DENSITY_XHIGH:                         //~@@@@I~
	        h=HIGHT_DPI_XHIGH;                                     //~@@@@I~
            break;                                                 //~@@@@I~
        }                                                          //~@@@@I~
        return h;                                           //~@@@@I~
    }                                                              //~@@@@I~
//*************************                                        //~1128I~
	static public View inflateView(int Presid)                     //~1128I~
    {                                                              //~1128I~
		View layoutview=inflateLayout(Presid);                     //~1128I~
        return layoutview;                                         //~1128I~
    }                                                              //~1128I~
//******************                                               //~1124I~//~1216M~
	static private View inflateLayout(int Presid)                   //~1122I~//~1216I~
    {                                                              //~1122I~//~1216M~
    	View layoutView=AG.inflater.inflate(Presid,null);          //~1122I~//~1216M~
        if (Dump.Y) Dump.println("UView:inflateLayout res="+Integer.toHexString(Presid)+",view="+layoutView.toString());//~@@@@R~//~v@@@R~
        return layoutView;                                         //~1122I~//~1216M~
    }                                                              //~1122I~//~1216M~
//**********************************                               //~v106I~
    public static void lockContention(String Ptext)                //~v106I~
    {                                                              //~v106I~
    	showToastLong(R.string.lockContention,Ptext);              //~v106I~//~1Ah1R~
	}                                                              //~v106I~
//**********************************                               //~1B0gI~//~1Ad8I~
    public static void memoryShortage(String Ptext)                //~1B0gI~//~1Ad8I~
    {                                                              //~1B0gI~//~1Ad8I~
    	showToastLong(R.string.ErrOutOfMemory,Ptext);                //~1B0gI~//~1Ad8I~//~1Ah1R~
	}                                                              //~1B0gI~//~1Ad8I~
//**********************************                               //~1A6pI~
    public static void getDisplaySize(Display Pdisplay,Point Ppoint)//~1A6pI~
    {                                                              //~1A6pI~
        Pdisplay.getSize(Ppoint);                                    //~1A6pI~//~v@@@M~
    }                                                              //~1A6pI~
////**********************************                               //~v@@@I~//~1Ah1R~
//    public static void showSnackbar(View Pview,String Pmsg,int Pperiod)//~v@@@R~//~1Ah1R~
//    {                                                              //~v@@@I~//~1Ah1R~
//        View v=Pview;                                              //~v@@@I~//~1Ah1R~
//        if (v==null)                                               //~v@@@I~//~1Ah1R~
//            v=popSnackbarParent();                                 //~v@@@I~//~1Ah1R~
//        if (Pperiod==Snackbar.LENGTH_INDEFINITE)                     //~v@@@I~//~1Ah1R~
//        {                                                          //~v@@@I~//~1Ah1R~
//            final Snackbar sb=Snackbar.make(v,Pmsg,Pperiod);       //~v@@@R~//~1Ah1R~
//            sb.setAction("Ok",new View.OnClickListener()            //~v@@@R~//~1Ah1R~
//                                {                                  //~v@@@I~//~1Ah1R~
//                                    @Override                      //~v@@@I~//~1Ah1R~
//                                    public void onClick(final View v)//~v@@@I~//~1Ah1R~
//                                    {                              //~v@@@I~//~1Ah1R~
//                                        sb.dismiss();              //~v@@@R~//~1Ah1R~
//                                    }                              //~v@@@I~//~1Ah1R~
//                                });                                //~v@@@R~//~1Ah1R~
//            sb.show();                                             //~v@@@R~//~1Ah1R~
//        }                                                          //~v@@@I~//~1Ah1R~
//        else                                                       //~v@@@I~//~1Ah1R~
//            Snackbar.make(AG.parentSnackbar,Pmsg,Pperiod).setAction("Action", null).show();//~v@@@R~//~1Ah1R~
//    }                                                              //~v@@@I~//~1Ah1R~
////**********************************************************       //~v@@@M~//~1Ah1R~
//    public static void showSnackbar(View Pview,int Presid,int Pperiod)//~v@@@I~//~1Ah1R~
//    {                                                              //~v@@@M~//~1Ah1R~
//        String msg=Utils.getStr(Presid);                           //~v@@@M~//~1Ah1R~
//        showSnackbar(Pview,msg,Pperiod);                           //~v@@@I~//~1Ah1R~
//    }                                                              //~v@@@M~//~1Ah1R~
////**********************************************************       //~v@@@I~//~1Ah1R~
//    public static View popSnackbarParent()                         //~v@@@I~//~1Ah1R~
//    {                                                              //~v@@@I~//~1Ah1R~
//        View v;                                                    //~v@@@I~//~1Ah1R~
//        try                                                        //~v@@@I~//~1Ah1R~
//        {                                                          //~v@@@I~//~1Ah1R~
//            v=AG.stackSnackbarLayout.pop();                        //~v@@@R~//~1Ah1R~
//        }                                                          //~v@@@I~//~1Ah1R~
//        catch (EmptyStackException e)                              //~v@@@I~//~1Ah1R~
//        {                                                          //~v@@@I~//~1Ah1R~
//            v=(View)AG.parentSnackbar;                             //~v@@@I~//~1Ah1R~
//        }                                                          //~v@@@I~//~1Ah1R~
//        return v;                                                  //~v@@@I~//~1Ah1R~
//    }                                                              //~v@@@I~//~1Ah1R~
////**********************************************************       //~v@@@I~//~1Ah1R~
//    public static void pushSnackbarParent(View Pview)              //~v@@@I~//~1Ah1R~
//    {                                                              //~v@@@I~//~1Ah1R~
//        AG.stackSnackbarLayout.push(Pview);                        //~v@@@R~//~1Ah1R~
//    }                                                              //~v@@@I~//~1Ah1R~
////**********************************                               //~v@@@I~//~1Ah2R~
//    public static void showDF(DialogFragment Pdf, String tag)      //~v@@@R~//~1Ah2R~
//    {                                                              //~v@@@I~//~1Ah2R~
////        FragmentTransaction ft = AG.activity.getFragmentManager().beginTransaction();//~v@@@R~//~1Ah2R~
////        ft.add(Pdf,tag);                                         //~v@@@R~//~1Ah2R~
////        ft.commitAllowingStateLoss();                            //~v@@@R~//~1Ah2R~
//        Pdf.show(AG.fragmentManager,tag);                          //~v@@@R~//~1Ah2R~
//    }                                                              //~v@@@I~//~1Ah2R~
//**********************************************************       //~v@@@M~
    public static void showToast(int Presid)                       //~v@@@M~
    {                                                              //~v@@@M~
		showToastShort(Presid);                                 //~v@@@R~
    }                                                              //~v@@@M~
//**********************************************************       //~v@@@I~
    public static void showToastShort(int Presid)                  //~v@@@I~
    {                                                              //~v@@@I~
		showToast(Presid,"");                                 //~v@@@R~//~1Ah1R~
    }                                                              //~v@@@I~
//**********************************************************       //~v@@@M~
    public static void showToastLong(int Presid)                   //~v@@@M~
    {                                                              //~v@@@M~
		showToastLong(Presid,"");                                  //~v@@@M~//~1Ah1R~
    }                                                              //~v@@@M~
//**********************************************************       //~v@@@M~//~1Ah1R~
    public static void showToast(int Presid,String Ptext)          //~v@@@M~//~1Ah1R~
    {                                                              //~v@@@M~//~1Ah1R~
        String msg= Utils.getStr(Presid)+Ptext;                     //~v@@@M~//~1Ah1R~
        if (Dump.Y) Dump.println("showToast msg="+msg);            //~v@@@M~//~1Ah1R~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@M~//~1Ah1R~
            return;                                                //~v@@@M~//~1Ah1R~
//      EventBus.getDefault().post(new EventToast(msg,false));     //~v@@@M~//~1Ah1R~
        showToastShort(msg);                                       //~1Ah1I~
    }                                                              //~v@@@M~//~1Ah1R~
//**********************************************************       //~v@@@M~//~1Ah1R~
    public static void showToastLong(int Presid,String Ptext)      //~v@@@M~//~1Ah1R~
    {                                                              //~v@@@M~//~1Ah1R~
        String msg= Utils.getStr(Presid)+Ptext;                     //~v@@@M~//~1Ah1R~
        if (Dump.Y) Dump.println("showToastLong msg="+msg);        //~v@@@M~//~1Ah1R~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@M~//~1Ah1R~
            return;                                                //~v@@@M~//~1Ah1R~
//      EventBus.getDefault().post(new EventToast(msg,true));      //~v@@@M~//~1Ah1R~
        showToastLong(msg);                                        //~1Ah1I~
    }                                                              //~v@@@M~//~1Ah1R~
//**********************************************************       //~v@@@M~//~1Ah1R~
    public static void showToast(String Ptext)                     //~v@@@M~//~1Ah1R~
    {                                                              //~v@@@M~//~1Ah1R~
        showToastShort(Ptext);                                     //~v@@@I~//~1Ah1R~
    }                                                              //~v@@@M~//~1Ah1R~
//**********************************************************       //~v@@@I~//~1Ah1R~
    public static void showToastShort(String Ptext)                //~v@@@I~//~1Ah1R~
    {                                                              //~v@@@I~//~1Ah1R~
        if (Dump.Y) Dump.println("showToast msg="+Ptext);          //~v@@@I~//~1Ah1R~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@I~//~1Ah1R~
            return;                                                //~v@@@I~//~1Ah1R~
//      EventBus.getDefault().post(new EventToast(Ptext,false));   //~v@@@I~//~1Ah1R~
//  	Toast.makeText(AG.context,Ptext,Toast.LENGTH_SHORT).show();//~1Ah1R~
        AView.showToast(Ptext);                                    //~1Ah1I~
    }                                                              //~v@@@I~//~1Ah1R~
//**********************************************************       //~v@@@M~//~1Ah1R~
    public static void showToastLong(String Ptext)                 //~v@@@M~//~1Ah1R~
    {                                                              //~v@@@M~//~1Ah1R~
        if (Dump.Y) Dump.println("showToastLong msg="+Ptext);      //~v@@@M~//~1Ah1R~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@M~//~1Ah1R~
            return;                                                //~v@@@M~//~1Ah1R~
//      EventBus.getDefault().post(new EventToast(Ptext,true));    //~v@@@M~//~1Ah1R~
//  	Toast.makeText(AG.context,Ptext,Toast.LENGTH_LONG).show(); //~1Ah1R~
        AView.showToastLong(Ptext);                                //~1Ah1I~
    }                                                              //~v@@@M~//~1Ah1R~
//****************                                                 //~1416I~//~1Ad7R~//~v@@@I~
    public static View findViewById(View Playout,int Pid)          //~1416I~//~1Ad7R~//~v@@@I~
    {                                                              //~1416I~//~1Ad7R~//~v@@@I~
        View v=Playout.findViewById(Pid);                          //~1416I~//~1Ad7R~//~v@@@I~//~9416R~
        if (Dump.Y) Dump.println("UView.findViewById rc==null?="+(v==null?"true":"false")+",id="+Integer.toHexString(Pid));//~9416I~
        return v;                                                  //~9416I~
    }                                                              //~1416I~//~1Ad7R~//~v@@@I~
//****************                                                 //~v@@@I~
    public static void recycle(Bitmap Pbitmap)                     //~v@@@I~
    {                                                              //~v@@@I~
    	if (Pbitmap==null)      //in case bm[] point same bitmap   //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("UView.recycle bitmap bitmap=null");//~v@@@I~//~0216R~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UView.recycle bitmap isRecycled="+Pbitmap.isRecycled());//~0216I~
        if (!Pbitmap.isRecycled())                                 //~v@@@I~
        {                                                          //~0216I~
        	if (Dump.Y) Dump.println("UView.recycle bitmap isRecycled=false byteCount="+Pbitmap.getByteCount()+",bitmap="+Pbitmap.toString());//~0216I~//~0217R~
	        Pbitmap.recycle();                                     //~v@@@I~
        }                                                          //~0216I~
    }                                                              //~v@@@I~
//****************                                                 //~v@@@I~
    public static void setWillNotDraw(View Pview,boolean PswNotDraw)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UView.setWillNotDraw swNotDraw="+PswNotDraw+",view="+Pview.toString());//~v@@@I~
        Pview.setWillNotDraw(false);	//enable onDraw() callback //~v@@@I~
    }                                                              //~v@@@I~
//****************                                                 //~v@@@I~
    public static void setAttachStateChangeListener(View Pview)    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UView.setAttachStateChangeListener view="+Pview.toString());//~v@@@I~
        View.OnAttachStateChangeListener l=                        //~v@@@I~
        	new View.OnAttachStateChangeListener()                 //~v@@@I~
            	{                                                  //~v@@@I~
                	@Override                                      //~v@@@I~
                    public void onViewAttachedToWindow(View Pview)             //~v@@@I~
                    {                                              //~v@@@I~
                    	if (Dump.Y) Dump.println("UView.onViewAttachedToWindow view="+Pview.toString());//~v@@@R~
                    }                                              //~v@@@I~
                	@Override                                      //~v@@@I~
                    public void onViewDetachedFromWindow(View Pview)           //~v@@@I~
                    {                                              //~v@@@I~
                    	if (Dump.Y) Dump.println("UView.onViewDetachedFromWindow view="+Pview.toString());//~v@@@R~
                    }                                              //~v@@@I~
                };                                                 //~v@@@I~
    	Pview.addOnAttachStateChangeListener(l);                   //~v@@@I~
    }                                                              //~v@@@I~
//****************                                                 //~v@@@I~
    public static Point getMeasuredSize(View Pview)                //~v@@@I~
    {                                                              //~v@@@I~
        int ww=Pview.getMeasuredWidth();                           //~v@@@I~
        int hh=Pview.getMeasuredHeight();                          //~v@@@I~
        if (Dump.Y) Dump.println("UView.getMeasuredSize ww="+ww+",hh="+hh+",view="+Pview.toString());//~v@@@I~
        return new Point(ww,hh);                                   //~v@@@I~
    }                                                              //~v@@@I~
//****************                                                 //~9928I~
    public static Point getMeasuredSize(View Pview,int Psize,int Pmode)//~9928I~
    {                                                              //~9928I~
        if (Dump.Y) Dump.println("UView.getMeasuredSize width mode Psize="+Psize+",mode="+Pmode);//~9928I~
        int msw= View.MeasureSpec.makeMeasureSpec(Psize,Pmode);    //~9928I~
        int msh= View.MeasureSpec.makeMeasureSpec(Psize,Pmode);    //~9928I~
        Pview.measure(msw,msh);                                    //~9928I~
        Point p=getMeasuredSize(Pview);                            //~9928I~
        return p;                                                  //~9928I~
    }                                                              //~9928I~
    //******************************************************************************//~9410I~
    //*!! call from onStart                                        //~9410I~
    //******************************************************************************//~9410I~
    public static void setDialogWidth(Dialog Pdlg, double Prate)   //~9410R~
    {                                                              //~9410I~
	    int ww;                                                    //~9410I~
	    ww=(int)(AG.scrWidth*Prate);                               //~9410R~
        int hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9410I~
        if (Dump.Y) Dump.println("Uview.setDialogWidth:ww="+ww+",hh="+hh+",rate="+Prate+",scrWidth="+AG.scrWidth+",portrait="+AG.portrait);//~9410R~//~9925R~
        Pdlg.getWindow().setLayout(ww,hh);                         //~9410I~
    }                                                              //~9410I~
    //******************************************************************************//~9811I~
    public static void setDialogWidth(Dialog Pdlg, int Pww)        //~9810I~
    {                                                              //~9810I~
	    int ww=Math.min(AG.scrWidth,Pww);                              //~9810I~
        int hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9810I~
        if (Dump.Y) Dump.println("Uview.setDialogWidth:Pww="+Pww+",ww="+ww+",scrWidth="+AG.scrWidth+",hh="+hh);//~9810R~//~9925R~
//        if (true) //TODO test                                    //~9925I~
//        {                                                        //~9925I~
//            WindowManager.LayoutParams lp=Pdlg.getWindow().getAttributes();//~9925I~
//            lp.width=ww;                                         //~9925I~
//            lp.height=ViewGroup.LayoutParams.WRAP_CONTENT;       //~9925I~
//            Pdlg.getWindow().setAttributes(lp);                  //~9925I~
//            if (Dump.Y) Dump.println("Uview.setDialogWidth setattribute");//~9925I~
//        }                                                        //~9925I~
//        else                                                     //~9925I~
        Pdlg.getWindow().setLayout(ww,hh);                         //~9810I~
    }                                                              //~9810I~
    //******************************************************************************//~9811I~
    public static void setDialogWidthMatchParent(Dialog Pdlg)      //~9811I~
    {                                                              //~9811I~
        if (Dump.Y) Dump.println("Uview.setDialogWidthMatchParent");//~9811I~//~9925R~
    	Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);//~9811I~
    }                                                              //~9811I~
    //******************************************************************************//~9925I~
    public static void setDialogWidthMatchParentPortrait(Dialog Pdlg)//~9925I~
    {                                                              //~9925I~
        if (Dump.Y) Dump.println("Uview.setDialogWidthMatchParentPortrait swPortrait="+AG.portrait);//~9925I~
        if (AG.portrait)                                           //~9925I~
    		Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);//~9925I~
        else                                                       //~9925I~
        {                                                          //~9925I~
        	int ww=Math.min(AG.scrWidthReal,AG.scrHeightReal);     //~9925I~
	        if (Dump.Y) Dump.println("Uview.setDialogWidthMatchParentPortrait ww="+ww+",realW="+AG.scrWidthReal+",realH="+AG.scrHeightReal);//~9925I~
//  		Pdlg.getWindow().setLayout(ww,LinearLayout.LayoutParams.WRAP_CONTENT);//~9925R~//~9927R~
    		Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT); //TODO test//~9927I~
        }                                                          //~9925I~
    }                                                              //~9925I~
    //******************************************************************************//~9812I~
    public static void setDialogWidthWrapContent(Dialog Pdlg)      //~9812I~
    {                                                              //~9812I~
        if (Dump.Y) Dump.println("Uview.setDialogWidthWrapContent");//~9812I~
    	Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);//~9812I~
    }                                                              //~9812I~
    //******************************************************************************//~9927I~
    public static int getDialogPaddingHorizontal(Dialog Pdlg)      //~9927I~
    {                                                              //~9927I~
        View decor=Pdlg.getWindow().getDecorView();                //~9927I~
        int p=decor.getPaddingRight();                             //~9927I~
        p+=decor.getPaddingLeft();                                 //~9927I~
        if (Dump.Y) Dump.println("Uview.getDialogPaddingHorizontal padding="+p);//~9927I~
        return p;
    }                                                              //~9927I~
//    //******************************************************************************//~9410I~//~9810R~
//    //*!! call from onStart                                        //~9410I~//~9810R~
//    //*Pww:max for portrait,min for landscape                    //~9810I~
//    //******************************************************************************//~9410I~//~9810R~
//    public static void setDialogWidth(Dialog Pdlg, double Prate,int Pww)//~9410I~//~9810R~
//    {                                                              //~9410I~//~9810R~
//        int WW=AG.scrWidth;                                        //~9410I~//~9810R~
//        int ww=(int)(WW*Prate);                                    //~9410I~//~9810R~
//        if (AG.portrait)                                           //~9410I~//~9810R~
//        {                                                          //~9410I~//~9810R~
//            if (Pww<WW)                                            //~9410I~//~9810R~
//                ww=Math.max(ww,Pww);                               //~9410I~//~9810R~
//        }                                                          //~9410I~//~9810R~
//        else                                                       //~9410I~//~9810R~
//        {                                                          //~9410I~//~9810R~
//            if (Pww<WW)                                            //~9410I~//~9810R~
//                ww=Math.min(ww,Pww);                               //~9410I~//~9810R~
//        }                                                          //~9410I~//~9810R~
//        int hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9410I~//~9810R~
//        Pdlg.getWindow().setLayout(ww,hh);                         //~9410I~//~9810R~
//        if (Dump.Y) Dump.println("Uview.setDialogWidth:portrait="+AG.portrait+",scrWidth="+WW+",rate="+Prate+",Pww="+Pww+",setww="+ww);//~9410I~//~9810R~
//    }                                                              //~9410I~//~9810R~
    //******************************************************************************//~9930I~
    public static boolean isPermissionGrantedLocation()            //~9930I~
    {                                                              //~9930I~
        String type= Manifest.permission.ACCESS_FINE_LOCATION;      //~9930I~
        boolean rc=isPermissionGranted(type);                      //~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionGrantedLocation rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9B09I~
    public static boolean isPermissionGrantedExternalStorage()     //~9B09I~
    {                                                              //~9B09I~
        String type= Manifest.permission.WRITE_EXTERNAL_STORAGE;   //~9B09I~
        boolean rc=isPermissionGranted(type);                      //~9B09I~
        if (Dump.Y) Dump.println("Uview.isPermissionGrantedExternalStorage rc="+rc);//~9B09I~
        return rc;                                                 //~9B09I~
    }                                                              //~9B09I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionGranted(String Ptype)        //~9930I~
    {                                                              //~9930I~
	    if (Dump.Y) Dump.println("Uview.isPermissionGranted Build.VERSION.SDK_INIT="+Build.VERSION.SDK_INT);//~9A01I~
//      if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M) //M:Mashmallow=api23=Android6//~9A01R~
//      {                                                          //~9A01R~
//          if (Dump.Y) Dump.println("Uview.isPermissionGranted version < android6(api23): Build.VERSION.SDK_INIT="+Build.VERSION.SDK_INT);//~9A01R~
//      	return true;                                           //~9A01R~
//      }                                                          //~9A01R~
        boolean rc= ContextCompat.checkSelfPermission(AG.activity,Ptype)== PackageManager.PERMISSION_GRANTED;//~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionGranted type="+Ptype+",rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionGranted(int Presult)         //~9930I~
    {                                                              //~9930I~
        boolean rc=Presult==PackageManager.PERMISSION_GRANTED;       //~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionGranted Presult="+Presult+",rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionDeniedLocation()             //~9930I~
    {                                                              //~9930I~
        String type=Manifest.permission.ACCESS_FINE_LOCATION;      //~9930I~
        boolean rc=isPermissionDenied(type);                       //~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionDeniedLocation rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionDenied(String Ptype)         //~9930I~
    {                                                              //~9930I~
        boolean rc=ActivityCompat.shouldShowRequestPermissionRationale(AG.activity,Ptype);//~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionDenied type="+Ptype+",rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static void requestPermissionLocation(int PrequestID)   //~9930I~
    {                                                              //~9930I~
        if (Dump.Y) Dump.println("Uview.requestPermissionLocation requestid="+PrequestID);//~9930I~
        String type=Manifest.permission.ACCESS_FINE_LOCATION;      //~9930I~
	    requestPermission(type,PrequestID);                        //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9B09I~
    public static void requestPermissionExternalStorage(int PrequestID)//~9B09I~
    {                                                              //~9B09I~
        if (Dump.Y) Dump.println("Uview.requestPermissionLocation requestid="+PrequestID);//~9B09I~
        String type=Manifest.permission.WRITE_EXTERNAL_STORAGE;    //~9B09I~
	    requestPermission(type,PrequestID);                        //~9B09I~
    }                                                              //~9B09I~
    //******************************************************************************//~9930I~
    public static void requestPermission(String Ptype,int PrequestID)//~9930I~
    {                                                              //~9930I~
        if (Dump.Y) Dump.println("Uview.requestPermission type="+Ptype+",requestID="+PrequestID);//~9930I~
        String[] types=new String[]{Ptype};                        //~9930I~
        ActivityCompat.requestPermissions(AG.activity,types,PrequestID);//~9930I~
    }                                                              //~9930I~
//    //******************************************************************************//~9B25R~
//    public static void getBackgroundColor(Button Pbtn)           //~9B25R~
//    {                                                            //~9B25R~
//        if (Dump.Y) Dump.println("Uview.getBackgroundColor btn="+Pbtn.toString());//~9B25R~
//        Drawable d=Pbtn.getBackgrounbd();                        //~9B25R~
//        ColorDrawable c=(ColorDrawable)d;                        //~9B25R~
//        int color=c.getColor();                                  //~9B25R~
//        if (Dump.Y) Dump.println("Uview.getBackgroundColor color="+Integer.toHexString(color));//~9B25R~
//    }                                                            //~9B25R~
    //*******************************************************************//~0113I~
    //*call on onCreateView                                        //~0113I~
    //*******************************************************************//~0113I~
    public static void shiftDialog(Dialog PandroidDlg,int Pctr,int Pshift)//~0113I~
    {                                                              //~0113I~
		if (Dump.Y) Dump.println("UView.shiftDialog ctr="+Pctr+",shift="+Pshift+",dialog="+PandroidDlg.toString());//~0113I~
        if (Pctr!=0)                                               //~0113I~
        {                                                          //~0113I~
            WindowManager.LayoutParams lp=PandroidDlg.getWindow().getAttributes();//~0113I~
            lp.y=Pctr*(Pshift==0 ? MULTIWINDOW_SHIFT : Pshift);    //~0113I~
        }                                                          //~0113I~
    }                                                              //~0113I~
    //*******************************************************************//~0327I~
    public static String showParentPathWidth(View Pview)           //~0327I~
    {                                                              //~0327I~
        StringBuffer sb=new StringBuffer();                        //~0327I~
        for (View v=Pview;v!=null;)                                //~0327R~
        {                                                          //~0327I~
        	if (!(v instanceof View))                              //~0327I~
            	break;                                             //~0327I~
        	int id=v.getId();                                      //~0327I~
    		sb.append("id="+Integer.toHexString(v.getId())+",width="+v.getWidth()+"\t");//~0327R~
        	if (v.getParent() instanceof View)                     //~0327I~
            	v=(View)v.getParent();                             //~0327I~
            else                                                   //~0327I~
                break;                                             //~0327I~
        }                                                          //~0327I~
        String s=sb.toString();                                    //~0327I~
		if (Dump.Y) Dump.println("UView.showParentPathWidth rc="+s);//~0327I~
        return s;                                                  //~0327I~
    }                                                              //~0327I~
}//class UView                                                     //~9410I~
