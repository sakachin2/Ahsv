//*CID://+1AheR~: update#= 145;                                    //~1Ah0R~//~1AheR~
//**********************************************************************//~1107I~
//1Ahe 2020/06/03 WD.stopDiscover send msg PEER_CHANGED with device ctr=0. StopDiscover by only button(Close or Back)//~1AheI~
//1Ah0 2020/05/30 for Android9-api28(PlayStore requires),deprected. ProgressDialog at Android8-api26//~1Ah0I~
//1A2j 2015/02/16 from Asgts 2013/04/03 (Bug)sendsuspend(not main thread) call ProgDialog//~1A2jI~
//                that cause RunTimeException not looper           //~1A2jI~
//**********************************************************************//~1107I~
package com.ForDeprecated;                                         //~1Ah0I~

import jagoclient.Dump;

import java.util.ArrayList;


import com.Ahsv.AG;                                                //~@@@@R~
import com.Ahsv.ProgDlgI;
import com.Ahsv.R;                                                 //~@@@@R~//~1A2jR~
import com.Ahsv.URunnable;
//import com.Ahsv.URunnableI;
import com.Ahsv.URunnableData;
import com.btmtest.gui.UButton;

import android.app.Dialog;
//import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//**********************************************************************//~1107I~
public class ProgDlg                                            //~1107R~//~1211R~//~@@@@R~//~@@@2R~
	extends Dialog                                                 //~1Ah0I~
//  implements URunnableI                                          //~1A2jI~//~1Ah0R~
    implements  UButton.UButtonI              //~v@@@I~            //~1Ah0I~
{                                                                  //~0914I~
	private static final int LAYOUTID=R.layout.progdlg;            //~1Ah0I~
	private static final int RUNUI_NOCALLBACK=1;                   //~1A2jI~
	private static final int RUNUI_CALLBACK=2;                     //~1A2jI~
	private static final int RUNUI_SIMPLE=3;                       //~1Ah0I~
	private ProgDlgI callback;                                  //~1425R~//~@@@@R~//~@@@2R~
//  ProgressDialog androidDialog;                                  //~@@@2I~//~1Ah0R~
    ProgDlg androidDialog;                                         //~1Ah0I~
    private boolean dismissCallback=true;                                  //~@@@2I~
    private boolean showing;                                       //~@@@2I~
//  private int mtTitleid;                                         //~1A2jI~//~1Ah0R~
//  private int mtMsgid;                                           //~1A2jI~//~1Ah0R~
//  private boolean mtCancelable;                                  //~1A2jI~//~1Ah0R~
//  private boolean mtAGPtr;                                       //~1A2jI~//~1Ah0R~
//  private boolean mtCancelCB;                                    //~1A2jI~//~1Ah0R~
//  private String mtMsg;                                          //~1A2jI~//~1Ah0R~
//  private ProgDlgI mtDlgI;                                       //~1A2jI~//~1Ah0R~
	private ViewGroup layoutView;                                  //~1818R~//~1Ah0I~
	public ProgDlgData progDlgData;                                //~1Ah0R~
    public static int PDUA_CANCEL=1;                                   //~1AheI~
    public static int PDUA_CLOSE=2;                                    //~1AheI~
    public static int PDUA_DISMISSDLG=0;                               //~1AheI~
	private int userAction=PDUA_DISMISSDLG;                        //+1AheR~
//**********************************                               //~1211I~
//*                                                                //~1211R~
//**********************************                               //~1211I~
	public ProgDlg()                                              //~1A2jI~
    {                                                              //~1A2jI~
    	super(AG.context);
        androidDialog=this;                                        //~1Ah0I~
    }                                                              //~1A2jI~
	private void initAndroid()                   //~1211I~ //~@@@@R~     //~@@@2R~//~1A2jR~
    {                                                              //~1211I~
//  	androidDialog=new ProgressDialog(AG.activity);             //~@@@2I~//~1Ah0R~
//      androidDialog.setOnDismissListener((OnDismissListener) new dismissListener());//~1Ah0I~
    	layoutView=(ViewGroup)(AG.inflater.inflate(LAYOUTID,null));//~1Ah0I~
        setContentView(layoutView);                                //~1Ah0I~
        androidDialog.setOnDismissListener((OnDismissListener) new dismissListener()); //~1326I~//~@@@@R~//~@@@2I~
    }                                                              //~1211I~
//**********************************                               //~@@@2I~
//* spinner type                                                   //~@@@2I~
//**********************************                               //~@@@2I~
//  public ProgDlg(int Ptitle,int Pmsg,boolean Pcancelable)        //~@@@2R~//~1A2jR~
    private ProgDlg(int Ptitle,int Pmsg,boolean Pcancelable)       //~1A2jI~
    {                                                              //~@@@2I~
    	this(Ptitle!=0?AG.resource.getString(Ptitle):"",Pmsg!=0?AG.resource.getString(Pmsg):"",Pcancelable);//~@@@2R~
    }                                                              //~@@@2I~
//**********************************                               //~@@@2I~
//  public ProgDlg(int Ptitle,String Pmsg,boolean Pcancelable)     //~@@@2I~//~1A2jR~
    private ProgDlg(int Ptitle,String Pmsg,boolean Pcancelable)    //~1A2jI~
    {                                                              //~@@@2I~
    	this(Ptitle!=0?AG.resource.getString(Ptitle):"",Pmsg,Pcancelable);//~@@@2I~
    }                                                              //~@@@2I~
//**********************************                               //~@@@2I~
//  public ProgDlg(String Ptitle,String Pmsg,boolean Pcancelable)  //~@@@2I~//~1A2jR~
    private ProgDlg(String Ptitle,String Pmsg,boolean Pcancelable) //~1A2jI~
    {                                                              //~@@@2I~
    	this();                                                    //~@@@2I~
        initAndroid();                                             //~1A2jI~
        if (Ptitle!=null)                                          //~@@@2I~
	        androidDialog.setTitle(Ptitle);                        //~@@@2I~
        if (Pmsg!=null)                                            //~@@@2I~
        {                                                          //~1Ah0I~
//          androidDialog.setMessage(Pmsg);                        //~@@@2I~//~1Ah0R~
			TextView tvMsg=(TextView)layoutView.findViewById(R.id.progMsg);//~1Ah0I~
            tvMsg.setText(Pmsg);                                   //~1Ah0I~
        }                                                          //~1Ah0I~
//      androidDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);    //~@@@2I~//~1Ah0R~
//      androidDialog.setIndeterminate(true);                      //~@@@2I~//~1Ah0R~
	    androidDialog.setCancelable(Pcancelable);                  //~@@@2I~
        setButton();                                               //~@@@2I~
    }                                                              //~@@@2I~
//**********************************                               //~@@@2I~
//  public void setCallback(ProgDlgI Pcallback,boolean Pcancelcallback,boolean Pdismisscallback)//~@@@2R~//~1A2jR~
    private void setCallback(ProgDlgI Pcallback, boolean Pcancelcallback, boolean Pdismisscallback)//~1A2jI~
    {                                                              //~@@@2I~
    	callback=Pcallback;                                        //~@@@2I~
        dismissCallback=Pdismisscallback;                           //~@@@2I~
        if (Pcancelcallback)                                       //~@@@2I~
        {                                                          //~@@@2I~
            androidDialog.setOnCancelListener                      //~@@@2R~
            (                                                      //~@@@2R~
                new DialogInterface.OnCancelListener()             //~@@@2R~
                {                                                  //~@@@2R~
                    @Override                                      //~@@@2R~
                    public void onCancel(DialogInterface Pdlg)      //~@@@2R~
                    {                                              //~@@@2R~
                        if (Dump.Y) Dump.println("ProgressDialog onCancel");//~@@@2R~
					    showing=false;                             //~@@@2I~
//                      callback(0);                               //~@@@2R~//~1AheR~
						userAction=PDUA_CANCEL;                    //~1AheR~
                        androidDialog.dismiss();                                 //~@@@2I~
                    }                                              //~@@@2R~
                }                                                  //~@@@2R~
            );                                                     //~@@@2R~
        }                                                          //~@@@2I~
        if (Dump.Y) Dump.println("ProgDlg setcallback");           //~@@@2I~
    }                                                              //~@@@2I~
//*******************************                                  //~@@@2I~
	private void setButton()                                       //~@@@2I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("ProgressDialog setButton");      //~@@@2I~
		Button btnClose=UButton.bind(layoutView,R.id.Close,this);  //~1Ah0R~
//        androidDialog.setButton(                                   //~@@@2I~//~1Ah0R~
//                DialogInterface.BUTTON_NEGATIVE,                   //~@@@2I~//~1Ah0R~
//                AG.resource.getString(R.string.Cancel),            //~@@@2I~//~1A2jR~//~1Ah0R~
//                new OnClickListener()              //~@@@2I~     //~1Ah0R~
//                    {                                              //~@@@2I~//~1Ah0R~
//                        @Override                                  //~@@@2I~//~1Ah0R~
//                        public void onClick(DialogInterface Pdlg,int Pid)//~@@@2I~//~1Ah0R~
//                        {                                          //~@@@2I~//~1Ah0R~
//                            if (Dump.Y) Dump.println("ProgressDialog CancelButton Click");//~@@@2I~//~1Ah0R~
//                            androidDialog.cancel();                 //~@@@2I~//~1Ah0R~
//                        }                                          //~@@@2I~//~1Ah0R~
//                    }                                            //~1Ah0R~
//                );//~@@@2I~                                      //~1Ah0R~
    }                                                              //~@@@2I~
    //******************************************                   //~1Ah0I~
    public void onClickCancel()                                    //~1Ah0I~
    {                                                              //~1Ah0I~
		if (Dump.Y) Dump.println("ProgDlg.onClickCancel");         //~1Ah0I~
		userAction=PDUA_CLOSE;                                     //~1AheI~
//  	androidDialog.cancel();                                    //~1Ah0R~
    	dismiss();                                                 //~1Ah0I~
    }                                                              //~1Ah0I~
    //******************************************                   //~v@@@I~//~1Ah0I~
    @Override //UButtonI                                           //~v@@@I~//~1Ah0I~
    public void onClickButton(Button Pbutton)                   //~v@@@R~//~1Ah0I~
	{                                                              //~v@@@I~//~1Ah0I~
    	boolean rc=true;                                           //~v@@@I~//~1Ah0I~
        if (Dump.Y) Dump.println("ProgDlg:onClickButton"+Pbutton.getText());//~v@@@I~//~1Ah0I~
    	try                                                        //~v@@@I~//~1Ah0I~
        {                                                          //~v@@@I~//~1Ah0I~
        	int id=Pbutton.getId();                                //~v@@@I~//~1Ah0I~
        	switch(id)                                             //~v@@@I~//~1Ah0I~
            {                                                      //~v@@@I~//~1Ah0I~
            case R.id.Close:                                      //~v@@@I~//~1Ah0R~
                onClickCancel();                                    //~v@@@I~//~1Ah0I~
                break;                                             //~v@@@I~//~1Ah0I~
            }                                                      //~v@@@I~//~1Ah0I~
        }                                                          //~v@@@I~//~1Ah0I~
        catch(Exception e)                                         //~v@@@I~//~1Ah0I~
        {                                                          //~v@@@I~//~1Ah0I~
            Dump.println(e,"ProgDlg:onClick:"+Pbutton.getText());    //~v@@@I~//~1Ah0I~
        }                                                          //~v@@@I~//~1Ah0I~
    }                                                              //~v@@@I~//~1Ah0I~
//*******************************                                  //~@@@2I~
//  public void dismiss(boolean Pdismisscallback)                  //~@@@2R~//~1Ah0R~
    public void dismissDlg(boolean Pdismisscallback)               //~1Ah0I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("ProgressDialog dismiss");        //~@@@2I~
        dismissCallback=Pdismisscallback;                          //~@@@2I~
        androidDialog.dismiss();                                   //~@@@2I~
        showing=false;                                             //~@@@2I~
    }                                                              //~@@@2I~
//*******************************                                  //~@@@2I~
//*callback                                                        //~@@@2I~
//*parm 0:user cancel,1:dismiss                                         //~@@@2I~//~1AheR~
//*******************************                                  //~@@@2I~
	private void callback(int Preason)                             //~@@@2I~
    {                                                              //~@@@2I~
    	try                                                        //~@@@2I~
        {                                                          //~@@@2I~
        	if (callback!=null)                                    //~@@@2I~
	        	callback.onCancelProgDlg(Preason);          //~@@@2I~
        }                                                          //~@@@2I~
        catch(Exception e)                                         //~@@@2I~
        {                                                          //~@@@2I~
    		Dump.println(e,"ProgressDialog:callback="+Preason);    //~@@@2I~
        }                                                          //~@@@2I~
    }                                                              //~@@@2I~
//*******************************                                  //~1326I~//~@@@2I~
//* show in-modal dialog                                           //~1330I~//~@@@2I~
//*******************************                                  //~1330I~//~@@@2I~
//  public void show()	                                          //~1214M~//~1330R~//~@@@2I~//~1A2jR~
//  private void show()                                            //~1A2jI~//~1Ah0R~
    private void showDlg()                                         //~1Ah0I~
    {                                                              //~1214M~//~@@@2I~
    	androidDialog.show();                                      //~1309R~//~1325R~//~@@@2I~
	    showing=true;                                              //~@@@2I~
        if (Dump.Y) Dump.println("ProgDlg show");                  //~@@@2I~
    }                                                              //~1214M~//~@@@2I~
    public boolean isShowing()                                     //~@@@2I~
    {                                                              //~@@@2I~
	    return showing;                                            //~@@@2I~
    }                                                              //~@@@2I~
//****************************************                                  //~1326I~//~1330R~//~@@@2I~
//*dismiss listener for inmodal dialog                                                //~1326I~//~1330R~//~@@@2I~
//****************************************                         //~1326I~//~1330R~//~@@@2I~
    public class dismissListener                                   //~1326I~//~@@@2I~
    		implements OnDismissListener                           //~1326I~//~@@@2I~
	{                                                              //~1326I~//~@@@2I~
        @Override                                                  //~1326I~//~@@@2I~
        public void onDismiss(DialogInterface Pdialog)             //~1326I~//~@@@2I~
        {                                                          //~1326I~//~@@@2I~
			if (Dump.Y) Dump.println("ProgDialog dialog dismiss listener userAction="+userAction+",dismisscallback="+dismissCallback); //~1326I~//~1506R~//~@@@2I~//~1AheR~
//          if (dismissCallback)                                   //~@@@2I~//~1AheR~
//          	callback(1);                                       //~@@@2I~//~1AheR~
            callback(userAction);                                  //~1AheR~
		    showing=false;                                          //~@@@2I~
//      	AG.progDlg=null;                                       //~@@@2M~//~1Ah0R~
        	AG.progDlg26=null;                                     //~1Ah0I~
        	progDlgData=null;                                      //~1Ah0I~
        }                                                          //~@@@2I~
    }                                                              //~1326I~//~@@@2I~
//****************************************                         //~@@@2I~
//  public static boolean dismiss()                                //~@@@2I~//~1Ah0R~
    public static boolean dismissDlg()                             //~1Ah0I~
    {                                                              //~@@@2I~
    	boolean rc=false;                                          //~@@@2I~
//  	if (AG.progDlg!=null && AG.progDlg.isShowing())            //~@@@2R~//~1Ah0R~
    	if (AG.progDlg26!=null && AG.progDlg26.isShowing())        //~1Ah0I~
    	{                                                          //~@@@2I~
//      	AG.progDlg.dismiss(false);                             //~@@@2I~//~1Ah0R~
        	AG.progDlg26.dismissDlg(false);                        //~1Ah0R~
            rc=true;                                               //~@@@2I~
        }                                                          //~@@@2I~
    	if (Dump.Y) Dump.println("ProgDlg static dismiss rc="+rc); //~@@@2I~
        return rc;                                                 //~@@@2I~
    }                                                              //~@@@2I~
//****************************************                         //~1A2jI~
//*force to execute on MainThread                                  //~1A2jI~
//****************************************                         //~1A2jI~
//from GameQuestion                                                //~1A2jI~
//****************************************                         //~1A2jI~
    public static void showProgDlg(boolean Pagptr,int Ptitleid,int Pmsgid,boolean Pcancelable)//~1A2jI~
    {                                                              //~1A2jI~
//        ProgDlg pd=new ProgDlg();   //empty                        //~1A2jI~//~1Ah0R~
//        pd.mtTitleid=Ptitleid;                                     //~1A2jI~//~1Ah0R~
//        pd.mtMsgid=Pmsgid;                                         //~1A2jI~//~1Ah0R~
//        pd.mtCancelable=Pcancelable;                               //~1A2jI~//~1Ah0R~
//        pd.mtAGPtr=Pagptr;                                         //~1A2jI~//~1Ah0R~
		ProgDlgData pd=new ProgDlgData(Pagptr,Ptitleid,Pmsgid,Pcancelable);//~1Ah0I~
        URunnable.setRunFuncDirect(pd/*Runnable*/,pd/*objparm*/,RUNUI_NOCALLBACK/*int parm*/);//~1A2jI~
    }                                                              //~1A2jI~
//  public void showProgDlgUI(boolean Pagptr,int Ptitleid,int Pmsgid,boolean Pcancelable)//~1A2jI~//~1Ah0R~
    public static void showProgDlgUI(ProgDlgData Pdata,boolean Pagptr,int Ptitleid,int Pmsgid,boolean Pcancelable)//~1Ah0R~
    {                                                              //~1A2jI~
    	ProgDlg pd=new ProgDlg(Ptitleid,Pmsgid,Pcancelable);	//empty//~1A2jI~
        Pdata.progDlg=pd;                                          //~1Ah0I~
        pd.progDlgData=Pdata;                                      //~1Ah0I~
        if (Pagptr)                                                //~1A2jI~
//      	AG.progDlg=pd;                                         //~1A2jI~//~1Ah0R~
        	AG.progDlg26=pd;                                       //~1Ah0I~
//      pd.show();                                                 //~1A2jI~//~1Ah0R~
        pd.showDlg();                                              //~1Ah0I~
    }                                                              //~1A2jI~
//****************************************                         //~1A2jI~
//from IPConnection/BTConnection                                   //~1A2jI~
//****************************************                         //~1A2jI~
    public static void showProgDlg(ProgDlgI Ppdi,boolean Pcancelcb,int Ptitleid,String Pmsg,boolean Pcancelable)//~1A2jI~
    {                                                              //~1A2jI~
//        ProgDlg pd=new ProgDlg();   //empty                        //~1A2jI~//~1Ah0R~
//        pd.mtTitleid=Ptitleid;                                     //~1A2jI~//~1Ah0R~
//        pd.mtMsg=Pmsg;                                             //~1A2jI~//~1Ah0R~
//        pd.mtCancelable=Pcancelable;                               //~1A2jI~//~1Ah0R~
//        pd.mtCancelCB=Pcancelcb;                                   //~1A2jI~//~1Ah0R~
//        pd.mtDlgI=Ppdi;                                            //~1A2jI~//~1Ah0R~
		ProgDlgData pd=new ProgDlgData(Ppdi,Pcancelcb,Ptitleid,Pmsg,Pcancelable);//~1Ah0I~
        URunnable.setRunFuncDirect(pd/*Runnable*/,pd/*objparm*/,RUNUI_CALLBACK/*int parm*/);//~1A2jI~
    }                                                              //~1A2jI~
    public static void showProgDlg(ProgDlgI Ppdi,boolean PcancelCB,int Ptitleid,int Pmsgid,boolean Pcancelable)//~1A2jI~
    {                                                              //~1A2jI~
		showProgDlg(Ppdi,PcancelCB,Ptitleid,AG.resource.getString(Pmsgid),Pcancelable);//~1A2jI~
    }                                                              //~1A2jI~
//  public void showProgDlgUI(ProgDlgI Pdlgi,boolean Pcancelcb,int Ptitleid,String Pmsg,boolean Pcancelable)//~1A2jI~//~1Ah0R~
    public static void showProgDlgUI(ProgDlgData Pdata,ProgDlgI Pdlgi,boolean Pcancelcb,int Ptitleid,String Pmsg,boolean Pcancelable)//~1Ah0R~
    {                                                              //~1A2jI~
    	ProgDlg pd=new ProgDlg(Ptitleid,Pmsg,Pcancelable);	//empty//~1A2jI~
        pd.progDlgData=Pdata;                                      //~1Ah0I~
        Pdata.progDlg=pd;                                          //~1Ah0I~
        pd.setCallback(Pdlgi,Pcancelcb/*cancel CB*/,false/*dismisscallback*/);//~1A2jI~
//      AG.progDlg=pd;                                             //~1A2jI~//~1Ah0R~
        AG.progDlg26=pd;                                           //~1Ah0I~
//      pd.show();                                                 //~1A2jI~//~1Ah0R~
        pd.showDlg();                                              //~1Ah0I~
    }                                                              //~1A2jI~
//****************************************                         //~1Ah0I~
//from wifidirect                                                  //~1Ah0I~
//****************************************                         //~1Ah0I~
    public static ProgDlgData showProgDlgSimple(int Ptitleid,String Pmsg,boolean Pcancelable)//~1Ah0I~
    {                                                              //~1Ah0I~
		ProgDlgData pd=new ProgDlgData(null,true,Ptitleid,Pmsg,Pcancelable);//~1Ah0I~
        URunnable.setRunFuncDirect(pd/*Runnable*/,pd/*objparm*/,RUNUI_SIMPLE/*int parm*/);//~1Ah0I~
        return pd;                                                 //~1Ah0I~
    }                                                              //~1Ah0I~
    public static ProgDlgData showProgDlgSimple(URunnableData PrunData, int Ptitleid, String Pmsg, boolean Pcancelable)//~1Ah0I~
    {                                                              //~1Ah0I~
//		ProgDlgData pd=new ProgDlgData(null,true,Ptitleid,Pmsg,Pcancelable);//~1Ah0I~
  		ProgDlgData pd=new ProgDlgData(PrunData.progDlgI,true,Ptitleid,Pmsg,Pcancelable);//~1Ah0I~
        URunnable.setRunFuncDirect(pd/*Runnable*/,pd/*objparm*/,RUNUI_SIMPLE/*int parm*/);    //callback:PrunData//~1Ah0I~
        return pd;                                                 //~1Ah0I~
    }                                                              //~1Ah0I~
    public static void showProgDlgSimpleUI(ProgDlgData Pdata,int Ptitleid,String Pmsg,boolean Pcancelable)//~1Ah0R~
    {                                                              //~1Ah0I~
    	ProgDlg pd=new ProgDlg(Ptitleid,Pmsg,Pcancelable);	//empty//~1Ah0I~
        if (Pdata.mtDlgI!=null)                                    //~1Ah0I~
	    	pd.setCallback(Pdata.mtDlgI,true/*Pcancelcallback*/,true/* boolean Pdismisscallback*/);//~1Ah0I~
        Pdata.progDlg=pd;                                          //~1Ah0I~
        pd.progDlgData=Pdata;                                      //~1Ah0I~
        AG.progDlg26=pd;                                           //~1Ah0I~
        pd.showDlg();                                              //~1Ah0I~
    }                                                              //~1Ah0I~
//===============================================================================//~@@@2I~//~1A2jI~
//=run on UIThread                                                 //~@@@2I~//~1A2jI~
//===============================================================================//~@@@2I~//~1A2jI~
//  public void runFunc(Object Pobj,int Pint)                           //~@@@2I~//~1A2jI~//~1Ah0R~
    public static void runFunc(Object Pobj,int Pint)               //~1Ah0I~
    {                                                              //~@@@2I~//~1A2jI~
//        ProgDlg pd=(ProgDlg)Pobj;                                  //~1A2jI~//~1Ah0R~
        ProgDlgData pd=(ProgDlgData)Pobj;                          //~1Ah0I~
        int caseno=Pint;                                           //~1A2jI~
        if (caseno==RUNUI_NOCALLBACK)                              //~1A2jI~
        {                                                          //~1A2jI~
//  		showProgDlgUI(pd.mtAGPtr,pd.mtTitleid,pd.mtMsgid,pd.mtCancelable);//~1A2jI~//~1Ah0R~
    		showProgDlgUI(pd,pd.mtAGPtr,pd.mtTitleid,pd.mtMsgid,pd.mtCancelable);//~1Ah0I~
        }                                                          //~1A2jI~
        else                                                       //~1A2jI~
        if (caseno==RUNUI_CALLBACK)                                //~1A2jI~
        {                                                          //~1A2jI~
//  		showProgDlgUI(pd.mtDlgI,pd.mtCancelCB,pd.mtTitleid,pd.mtMsg,pd.mtCancelable);//~1A2jI~//~1Ah0R~
    		showProgDlgUI(pd,pd.mtDlgI,pd.mtCancelCB,pd.mtTitleid,pd.mtMsg,pd.mtCancelable);//~1Ah0I~
        }                                                          //~1A2jI~
        else                                                       //~1Ah0I~
        if (caseno==RUNUI_SIMPLE)                                  //~1Ah0I~
        {                                                          //~1Ah0I~
			showProgDlgSimpleUI(pd,pd.mtTitleid,pd.mtMsg,pd.mtCancelable);//~1Ah0I~
        }                                                          //~1Ah0I~
    }                                                              //~@@@2I~//~1A2jI~
}//class ProgDlg                                                //~1211R~//~@@@@R~//~@@@2R~
