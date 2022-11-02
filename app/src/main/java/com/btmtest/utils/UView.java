//*CID://+1amxR~: update#= 337;                                    //~1amxR~
//**********************************************************************//~v101I~
//1amx 2022/11/01 mdpi,hdpi,... by dencity                         //~1amxI~
//1ams 2022/11/01 control request permission to avoid 1amh:"null permission result".(W Activity: Can request only one set of permissions at a time)//~1amsI~
//1am8 2022/10/30 for A11(API39) gesturemode                       //~1am8I~
//1am7 2022/10/30 consider when Landscape(diff from BTMJ5, navigation bar will be shown)//~1am7I~
//1am3 2022/10/29 a0droid12(api31) Display.getRealSize, getRealMetrics//~1am3I~
//1ak3 2021/09/10 picker(ACTION_PICK) for API30                    //~1ak3I~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//1aj0 2021/08/14 androd11(api30) getDefaultDisplay deprecated at api30//~1aj0R~
//1Ah2 2020/05/31 for Android9(Pie)-api28(PlayStore requires),deprected. DialogFragment,Fragmentmanager//~1Ah2I~
//1Ah1 2020/05/30 from BTMJ5                                       //~1Ah1I~
//**********************************************************************//~1Ah1I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;                                    //~0913I~
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
//import android.support.design.widget.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import static android.util.DisplayMetrics.*;

import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
//import android.app.DialogFragment;                                 //~v@@@I~//~1Ah2R~
//import android.support.v4.app.DialogFragment;                    //~1Ah2R~
import android.view.WindowMetrics;
import android.widget.LinearLayout;

import jagoclient.Dump;

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
//  	Display display=((WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1122M~//~1aj0R~
    	Display display=getDefaultDisplay();                       //~1aj0R~
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
    //*******************************************************      //~1aj0R~
	public static Display getDefaultDisplay()                      //~1aj0R~
    {                                                              //~1aj0R~
	    if (Dump.Y) Dump.println("UView:getDefaultDisplay");       //~1aj0R~
    	Display d;                                                 //~1aj0R~
		if (Build.VERSION.SDK_INT>=30)   //android30(R)            //~1aj0R~
			d=getDefaultDisplay30();                               //~1aj0R~
        else                                                       //~1aj0R~
			d=getDefaultDisplay29();                               //~1aj0R~
        return d;                                                  //~1aj0R~
    }                                                              //~1aj0R~
    //*******************************************************      //~1aj0R~
    @SuppressWarnings("deprecation")                               //~1aj0R~
	public static Display getDefaultDisplay29()                    //~1aj0R~
    {                                                              //~1aj0R~
	    if (Dump.Y) Dump.println("UView:getDefaultDisplay29");     //~1aj0R~
		Display display=((WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1aj0R~
        return display;                                            //~1aj0R~
    }                                                              //~1aj0R~
    //*******************************************************      //~1aj0R~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0R~
	public static Display getDefaultDisplay30()                    //~1aj0R~
    {                                                              //~1aj0R~
	    if (Dump.Y) Dump.println("UView:getDefaultDisplay30");     //~1aj0R~
		Display display=AG.context.getDisplay();                   //~1aj0R~
        return display;                                            //~1aj0R~
    }                                                              //~1aj0R~
    //*******************************************************      //~v@@@I~
	public static void getScreenRealSize(Display Pdisplay)         //~v@@@I~
    {                                                              //~v@@@I~
		if (Build.VERSION.SDK_INT>=19)   //Navigationbar can be hidden//~v@@@R~
        {                                                          //~v@@@I~
	        Point p=new Point();                                   //~v@@@I~
//        	Pdisplay.getSize(p);                                   //~v@@@I~
//      	Pdisplay.getRealSize(p); //api17:4.2.2 JELLY bean mr1  //~v@@@R~//~1am3R~
        	getRealSize(Pdisplay,p); //api17:4.2.2 JELLY bean mr1  //~vam6I~//~1am3I~
        	AG.scrWidthReal=p.x;                                   //~v@@@I~
        	AG.scrHeightReal=p.y;                                  //~v@@@I~
	        if (Dump.Y) Dump.println("UView:getScreemRealSize getRealSize() w="+AG.scrWidthReal+",h="+AG.scrHeightReal);//~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			DisplayMetrics m=new DisplayMetrics();                 //~v@@@R~
//  		Pdisplay.getMetrics(m);                                //~v@@@R~//~1aj0R~
    		displayGetMetrics(Pdisplay,m);                         //~1aj0R~
        	AG.scrWidthReal=m.widthPixels;                         //~v@@@R~
        	AG.scrHeightReal=m.heightPixels;                       //~v@@@R~
	        if (Dump.Y) Dump.println("UView:getScreenRealSize Displaymetrics w="+AG.scrWidthReal+",h="+AG.scrHeightReal);//~v@@@I~
        }                                                          //~v@@@I~
        int ww=Math.min(AG.scrWidthReal,AG.scrHeightReal);         //~9809R~
        AG.swSmallDevice=ww<BASE_NEXUS7;                           //~9809I~
        AG.scaleSmallDevice=(double)ww/BASE_NEXUS7;               //~9809I~
    }                                                              //~v@@@I~
    //*******************************************************      //~1aj0R~
    @SuppressWarnings("deprecation")                               //~1aj0R~
    private static void displayGetMetrics(Display Pdisplay,DisplayMetrics Pmetrics)//~1aj0R~
    {                                                              //~1aj0R~
	    if (Dump.Y) Dump.println("UView:displayGetMetrics");       //~1aj0R~
		Pdisplay.getMetrics(Pmetrics);                             //~1aj0R~
    }                                                              //~1aj0R~
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
//      Pdisplay.getSize(Ppoint);                                    //~1A6pI~//~v@@@M~//~1aj0R~
	    if (Dump.Y) Dump.println("UView:getDisplaySize");          //~1aj0R~
		if (Build.VERSION.SDK_INT>=31)                             //~vam6I~//~1am3I~
			getDisplaySize31(Pdisplay,Ppoint);                     //~vam6I~//~1am3I~
        else                                                       //~vam6I~//~1am3I~
		if (Build.VERSION.SDK_INT>=30)   //android30(R)            //~1aj0R~
			getDisplaySize30(Pdisplay,Ppoint);                     //~1aj0R~
        else                                                       //~1aj0R~
			getDisplaySize29(Pdisplay,Ppoint);                     //~1aj0R~
   }                                                               //~1aj0R~
    //*******************************************************      //~1aj0R~
    @SuppressWarnings("deprecation")                               //~1aj0R~
	public static void getDisplaySize29(Display Pdisplay,Point Ppoint)//~1aj0R~
    {                                                              //~1aj0R~
        Pdisplay.getSize(Ppoint);                                  //~1aj0R~
	    if (Dump.Y) Dump.println("UView:getDisplaySize29 point="+Ppoint.toString());//~1aj0R~
    }                                                              //~1aj0R~
    //*******************************************************      //~1aj0R~
    @SuppressWarnings("deprecation")                               //~vam6I~//~1am3I~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0R~
    public static void getDisplaySize30(Display Pdisplay,Point Ppoint)//~1aj0R~
    {                                                              //~1aj0R~
//        DisplayMetrics m=new DisplayMetrics();                     //~1aj0R~//~1am8R~
//        Pdisplay.getRealMetrics(m);                                //~1aj0R~//~1am8R~
//        Ppoint.x=m.widthPixels;                                    //~1aj0R~//~1am8R~
//        Ppoint.y=m.heightPixels;                                   //~1aj0R~//~1am8R~
//        if (Dump.Y) Dump.println("UView:getDisplaySize30 point="+Ppoint.toString());//~1aj0R~//~1am8R~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30");        //~1aj0R~//~1am8I~
        WindowMetrics wm=AG.activity.getWindowManager().getCurrentWindowMetrics();//~1aj0R~//~1am8I~
	    int ww0=wm.getBounds().width();                             //~1aj0R~//~1am8I~
	    int hh0=wm.getBounds().height();                              //~1aj0I~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 windowMetrics ww="+ww0+",hh="+hh0);//~1aj0I~//~1am8I~
        Rect rectDecor=getDecorViewRect();                         //~vaegI~//~1am8I~
        Insets insetnavi=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//TODO test//~vaefI~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 insetnavi="+insetnavi.toString());//~vaefI~//~1am8I~
        Insets insetstatus=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//TODO test//~vaefI~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 insetstatus="+insetstatus.toString());//~vaefI~//~1am8I~
        Insets insetnaviv=wm.getWindowInsets().getInsets(WindowInsets.Type.navigationBars());//TODO test//~vaefI~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 insetnaviv="+Utils.toString(insetnaviv));//~vaefI~//~1am8I~
        Insets insetstatusv=wm.getWindowInsets().getInsets(WindowInsets.Type.statusBars());//TODO test//~vaefI~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 insetstatus visible="+Utils.toString(insetstatusv));//~vaefI~//~1am8I~
        Insets insetsys=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());//TODO test//~1aj0R~//~vaefR~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 insetsys="+insetsys.toString());//~1aj0R~//~vaefR~//~1am8I~
                                                                   //~vaefI~//~1am8I~
        Insets inset=wm.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vaefR~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset systemGesture="+Utils.toString(inset));//~vaefR~//~1am8I~
                                                                   //~vaefI~//~1am8I~
        int ww,hh;                                                 //~vaefI~//~1am8I~
        AG.swNavigationbarGestureMode=inset.left!=0 && inset.right !=0 && inset.top!=0 && inset.bottom!=0;//~vaefM~//~1am8I~
        ww=ww0-inset.left-inset.right;                             //~vaefI~//~1am8I~
        hh=hh0-inset.bottom;  //fullscreen(no title) mode,bottom is 3button/gesture navigationbar//~vaefR~//~1am8I~
        if (ww0>hh0)	//landscape                                //~vaefI~//~1am8I~
        {                                                          //~vaefI~//~1am8I~
// for AHSV 	hh=hh0;	//hide navigationbar at MainActivity       //~vaefR~//~1am8I~
                ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vaegR~//~1am8I~
        }                                                          //~vaefI~//~1am8I~
        else                                                       //~vaefI~//~1am8I~
	        ww=ww0;                                                //~vaefI~//~1am8I~
        AG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vaefI~//~1am8I~
        int marginLR;                                              //~vaegI~//~1am8I~
        if (AG.swNavigationbarGestureMode)                         //~vaegI~//~1am8I~
        {                                                          //~vaegI~//~1am8I~
            int left=rectDecor.left;                               //~vaegI~//~1am8I~
            int right=ww0-rectDecor.right;                         //~vaegI~//~1am8I~
        	marginLR=Math.max(left,right);                         //~vaegI~//~1am8I~
		    if (Dump.Y) Dump.println("UView:getDisplaySize30 gesture mode marginLR="+marginLR);//~vaegI~//~1am8I~
        }                                                          //~vaegI~//~1am8I~
        else  //3button mode                                       //~vaegI~//~1am8I~
        {                                                          //~vaegI~//~1am8I~
        	marginLR=ww0-(rectDecor.right-rectDecor.left);         //~vaegI~//~1am8I~
			if (Dump.Y) Dump.println("UView:getDisplaySize30 3 button mode marginLR="+marginLR);//~vaegI~//~vataR~//~1am8I~
        }                                                          //~vaegI~//~vataR~//~1am8I~
        AG.scrNavigationbarRightWidthA11=marginLR;              //~vaefR~//~vaegI~//~1am8I~
        Ppoint.x=ww; Ppoint.y=hh;                                  //~1aj0R~//~1am8I~
        AG.scrStatusBarHeight=inset.top;                           //~1aj0I~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 navigationbar bottomHA11="+AG.scrNavigationbarBottomHeightA11+",leftWA11="+AG.scrNavigationbarLeftWidthA11+",rightWA11="+AG.scrNavigationbarRightWidthA11+",swgesturemode="+AG.swNavigationbarGestureMode);//~vaefR~//~vaegR~//~1am8I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 point="+Ppoint.toString()+",statusBarHeight="+AG.scrStatusBarHeight);//~vaefI~//~1am8I~
    }                                                              //~1A6pI~
    //*******************************************************      //~vam6M~//~1am3I~
    @TargetApi(31)   //>=31                                        //~vam6M~//~1am3I~
    public static void getDisplaySize31(Display Pdisplay,Point Ppoint)//~vam6M~//~1am3I~
    {                                                              //~vam6M~//~1am3I~
        WindowMetrics metrics=getRealMetrics_from31(Pdisplay);     //~vam6M~//~1am3I~
        Insets insetGesture=metrics.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vam6I~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 insetGesture="+insetGesture);//~vam6I~//~1am3I~
        AG.swNavigationbarGestureMode=insetGesture.left!=0 && insetGesture.right !=0 && insetGesture.top!=0 && insetGesture.bottom!=0;//~vam6I~//~1am3I~
                                                                   //~vam6I~//~1am3I~
		Rect bounds=metrics.getBounds();                           //~vam6M~//~1am3I~
	    int ww0=bounds.width();                                    //~vam6M~//~1am3I~
	    int hh0=bounds.height();                                   //~vam6M~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 bounds="+bounds);//~vam6I~//~1am3I~
        WindowInsets windowInsets=metrics.getWindowInsets();       //~vam6M~//~1am3I~
        Insets inset=windowInsets.getInsetsIgnoringVisibility      //~vam6M~//~1am3I~
						(WindowInsets.Type.navigationBars()|WindowInsets.Type.displayCutout());//~vam6M~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset="+inset);//~vataI~//~1am3I~
        int insetWW=inset.right+inset.left;                        //~vam6M~//~1am3I~
        int insetHH=inset.top+inset.bottom;                        //~vam6M~//~1am3I~
        Rect rectDecor=getDecorViewRect();                         //~vam6M~//~vataM~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 rectRecor="+rectDecor.toString());//~vataI~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 insetWW="+insetWW+",insetHH="+insetHH+",insets="+inset);//~vam6M~//~1am3I~
                                                                   //~vam6M~//~1am3I~
        int ww=ww0-insetWW;                                        //~vam6I~//~1am3I~
        int hh=hh0-insetHH;                                        //~vam6I~//~1am3I~
        if (ww0>hh0)	//landscape                                //~vam6I~//~1am3I~
        {                                                          //~vam6I~//~1am3I~
//          hh=hh0;	//hide navigationbar at MainActivity ;FOR AHSV navigationbar will not be hidden          //~vam6I~//~1am3I~//~1am7R~
            ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vam6I~//~1am3I~
        }                                                          //~vam6I~//~1am3I~
        else                                                       //~vam6I~//~1am3I~
	        ww=ww0;                                                //~vam6I~//~1am3I~
        AG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vam6M~//~1am3I~
        int marginLR;                                              //~vam6M~//~1am3I~
        int left=inset.left;                                       //~vateI~//~1am3I~
        int right=inset.right;                                     //~vateI~//~1am3I~
        marginLR=Math.max(left,right);                             //~vateI~//~1am3I~
		if (Dump.Y) Dump.println("UView:getDisplaySize33 swPortrait="+AG.portrait+",marginLR="+marginLR+",left="+left+",right="+right);//~vateI~//~1am3I~
        AG.scrNavigationbarRightWidthA11=marginLR;                 //~vam6M~//~1am3I~
        AG.scrStatusBarHeight=inset.top;                           //~vam6M~//~1am3I~
                                                                   //~vam6M~//~1am3I~
        Ppoint.x=ww;                                               //~vam6R~//~1am3I~
        Ppoint.y=hh;                                               //~vam6R~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 navigationbar bottomHA11="+AG.scrNavigationbarBottomHeightA11+",leftWA11="+AG.scrNavigationbarLeftWidthA11+",rightWA11="+AG.scrNavigationbarRightWidthA11+",swgesturemode="+AG.swNavigationbarGestureMode);//~vam6M~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 point="+Ppoint.toString()+",statusBarHeight="+AG.scrStatusBarHeight);//~vam6M~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 point="+Ppoint.toString()+",bounds="+bounds+",insets="+inset);//~vam6M~//~1am3I~
    }                                                              //~vam6M~//~1am3I~
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
    //******************************************************************************//~1ak2I~
    public static boolean isPermissionGrantedExternalStorageRead() //~1ak2I~
    {                                                              //~1ak2I~
        String type= Manifest.permission.READ_EXTERNAL_STORAGE;    //~1ak2I~
        boolean rc=isPermissionGranted(type);                      //~1ak2I~
        if (Dump.Y) Dump.println("Uview.isPermissionGrantedExternalStorageRead rc="+rc);//~1ak2I~
        return rc;                                                 //~1ak2I~
    }                                                              //~1ak2I~
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
        if (!rc)                                                   //~1ak2I~
        {                                                          //~1ak2I~
            if (Dump.Y) Dump.println("UView.isPermissionGranted shouldShowRequestPermissionRationale="+ActivityCompat.shouldShowRequestPermissionRationale(AG.activity,Ptype));//~1ak2I~
        }                                                          //~1ak2I~
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
    //*Read and write                                              //~1ak2I~
    //******************************************************************************//~1ak2I~
    public static void requestPermissionExternalStorage(int PrequestID)//~9B09I~
    {                                                              //~9B09I~
        if (Dump.Y) Dump.println("Uview.requestPermissionExternalStorage requestid="+PrequestID);//~9B09I~//~1ak2R~
//      String type=Manifest.permission.WRITE_EXTERNAL_STORAGE;    //~9B09I~//~1ak2R~
        String[] type={Manifest.permission.WRITE_EXTERNAL_STORAGE, //~1ak2I~
                       Manifest.permission.READ_EXTERNAL_STORAGE}; //Required for Mediastore query//~1ak2R~
	    requestPermission(type,PrequestID);                        //~9B09I~
    }                                                              //~9B09I~
    //******************************************************************************//~1ak2I~
    public static void requestPermissionExternalStorageRead(int PrequestID)//~1ak2I~
    {                                                              //~1ak2I~
        if (Dump.Y) Dump.println("Uview.requestPermissionExternalStorageRead requestid="+PrequestID);//~1ak2R~
        String type=Manifest.permission.READ_EXTERNAL_STORAGE;     //~1ak2I~
	    requestPermission(type,PrequestID);                        //~1ak2I~
    }                                                              //~1ak2I~
    //******************************************************************************//~9930I~
    public static void requestPermission(String Ptype,int PrequestID)//~9930I~
    {                                                              //~9930I~
        if (Dump.Y) Dump.println("Uview.requestPermission type="+Ptype+",requestID="+PrequestID);//~9930I~
        String[] types=new String[]{Ptype};                        //~9930I~
//      ActivityCompat.requestPermissions(AG.activity,types,PrequestID);//~9930I~//~1amsR~
        UPermission.requestPermissions(types,PrequestID);          //~1amsI~
    }                                                              //~9930I~
    //******************************************************************************//~1ak2I~
    public static void requestPermission(String[] Ptypes,int PrequestID)//~1ak2I~
    {                                                              //~1ak2I~
        if (Dump.Y) Dump.println("Uview.requestPermission types="+Utils.toString(Ptypes)+",requestID="+PrequestID);//~1ak2I~
//      ActivityCompat.requestPermissions(AG.activity,Ptypes,PrequestID);//~1ak2I~//~1amsR~
        UPermission.requestPermissions(Ptypes,PrequestID);         //~1amsI~
    }                                                              //~1ak2I~
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
    //*******************************************************************//~vam6I~//~1am3I~
    //*for APi>=19                                                 //~vam6I~//~1am3I~
    //*******************************************************************//~vam6I~//~1am3I~
    private static void getRealSize(Display Pdisplay,Point Ppoint) //~vam6I~//~1am3I~
    {                                                              //~vam6I~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealSize apiLevel="+Build.VERSION.SDK_INT);//~vam6I~//~1am3I~
		if (Build.VERSION.SDK_INT>=31)   //Navigationbar can be hidden//~vam6I~//~1am3I~
		    getRealSize_from31(Pdisplay,Ppoint);                   //~vam6I~//~1am3I~
        else                                                       //~vam6I~//~1am3I~
		    getRealSize_19To30(Pdisplay,Ppoint);                   //~vam6I~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealSize exit point="+Ppoint);//~vam6I~//~1am3I~
    }                                                              //~vam6I~//~1am3I~
    //*******************************************************************//~vam6I~//~1am3I~
    @SuppressWarnings("deprecation")                               //~vam6R~//~1am3I~
    @TargetApi(19)                                                 //~vam6I~//~1am3I~
    private static void getRealSize_19To30(Display Pdisplay,Point Ppoint)//~vam6I~//~1am3I~
    {                                                              //~vam6I~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealSize_upto30 display="+Pdisplay);//~vam6I~//~1am3I~
		Pdisplay.getRealSize(Ppoint);                              //~vam6I~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealSize_upto30 exit point="+Ppoint);//~vam6I~//~1am3I~
    }                                                              //~vam6I~//~1am3I~
    //*******************************************************************//~vam6I~//~1am3I~
    @TargetApi(31)                                                 //~vam6I~//~1am3I~
    private static void getRealSize_from31(Display Pdisplay,Point Ppoint)//~vam6I~//~1am3I~
    {                                                              //~vam6I~//~1am3I~
        WindowMetrics metrics=getRealMetrics_from31(Pdisplay);     //~vam6R~//~1am3I~
        Rect rect=metrics.getBounds();                             //~vam6R~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealSize_from31 display="+Pdisplay+",metrics="+metrics+",getBounds="+rect);//~vam6R~//~1am3I~
        Ppoint.x=rect.right-rect.left;                              //~vam6R~//~1am3I~
        Ppoint.y=rect.bottom-rect.top;                              //~vam6R~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealSize_from31 exit point="+Ppoint);//~vam6I~//~1am3I~
    }                                                              //~vam6I~//~1am3I~
    //*******************************************************************//~vam6I~//~1am3I~
    @TargetApi(31)                                                 //~vam6I~//~1am3I~
    public static WindowMetrics getRealMetrics_from31(Display Pdisplay)//~vam6I~//~1am3I~
    {                                                              //~vam6I~//~1am3I~
		WindowManager mgr=getWindowManager();                      //~vam6I~//~1am3I~
        WindowMetrics metrics=mgr.getCurrentWindowMetrics();       //~vam6I~//~1am3I~
		if (Dump.Y) Dump.println("UView.getRealMetrics_31 metrics="+metrics);//~vam6R~//~1am3I~
        return metrics;                                            //~vam6I~//~1am3I~
    }                                                              //~vam6I~//~1am3I~
    //*******************************************************      //~vam6I~//~1am3I~
	public static WindowManager getWindowManager()                 //~vam6I~//~1am3I~
    {                                                              //~vam6I~//~1am3I~
		WindowManager wm=(WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE));//~vam6I~//~1am3I~
	    if (Dump.Y) Dump.println("UView:getWindowManager mgr="+wm);//~vam6I~//~1am3I~
        return wm;                                                 //~1am3I~
    }                                                              //~vam6I~//~1am3I~
    //*******************************************************      //~vaegI~//~1am3I~
    public static Rect getDecorViewRect()                          //~vaegI~//~1am3I~
    {                                                              //~vaegI~//~1am3I~
        Rect rect=new Rect();                                      //~vaegI~//~1am3I~
        android.view.Window w=AG.activity.getWindow();             //~vaegI~//~1am3I~
        View v=w.getDecorView();                                   //~vaegI~//~1am3I~
        v.getWindowVisibleDisplayFrame(rect);                      //~vaegI~//~1am3I~
        if (Dump.Y) Dump.println("UView.getViewRect DecorView rect="+rect.toString());//~vaegI~//~1am3I~
        return rect;                                               //~vaegI~//~1am3I~
    }                                                              //~vaegI~//~1am3I~
    //*******************************************************      //~1amxI~
    public static int getDensityDpiType()                          //~1amxR~
    {                                                              //~1amxI~
		int dencityDpi=AG.resource.getDisplayMetrics().densityDpi; //~1amxI~
	    int rc=getDensityDpiType(dencityDpi);                      //~1amxR~
        if (Dump.Y) Dump.println("UView.getDensityDpiType rc="+rc);//~1amxR~
        return rc;                                               //~1amxI~
    }                                                              //~1amxI~
    //*******************************************************      //~1amxI~
    public static int getDensityDpiType(int PdensityDpi)           //~1amxR~
    {                                                              //~1amxI~
    	int type=0;                                                //~1amxI~
		if (PdensityDpi>0 && PdensityDpi< DENSITY_LOW) //120(78)DPI//~1amxR~
        	type=DisplayMetrics.DENSITY_LOW;                       //~1amxR~
        else                                                       //~1amxI~
		if (PdensityDpi<DisplayMetrics.DENSITY_MEDIUM)            //160(a0)//~1amxR~
        	type=DisplayMetrics.DENSITY_MEDIUM;                    //~1amxR~
        else                                                       //~1amxI~
		if (PdensityDpi<DisplayMetrics.DENSITY_HIGH)              //240(f0)//~1amxR~
        	type=DisplayMetrics.DENSITY_HIGH;                      //~1amxR~
        else                                                       //~1amxI~
		if (PdensityDpi<DisplayMetrics.DENSITY_XHIGH)             //320(140)//~1amxR~
        	type=DisplayMetrics.DENSITY_XHIGH;                     //~1amxR~
        else                                                       //~1amxI~
		if (PdensityDpi<DisplayMetrics.DENSITY_XXHIGH)            //480(1e0)//~1amxR~
        	type=DisplayMetrics.DENSITY_XXHIGH;                    //~1amxR~
        else                                                       //~1amxI~
		if (PdensityDpi<DisplayMetrics.DENSITY_XXXHIGH)           //640(280)//~1amxR~
        	type=DisplayMetrics.DENSITY_XXXHIGH;                   //~1amxR~
        if (Dump.Y) Dump.println("UView.getDensityDpiType type="+type+",dencityDpi="+PdensityDpi);//~1amxR~
        return type;                                               //~1amxI~
    }                                                              //~1amxI~
}//class UView                                                     //~9410I~
