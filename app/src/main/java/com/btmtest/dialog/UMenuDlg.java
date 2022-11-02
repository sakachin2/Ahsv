//*CID://+1amcR~:                             update#=  234;       //+1amcR~
//*****************************************************************//~v101I~
//1amc 2022/10/30 widen menu dialog when landscape                 //+1amcI~
//1Ahk 2020/06/05 Connect button for all connection type           //~1AhkI~
//*****************************************************************//~v@@1I~
//multichoice option; dismiss control by selected option           //~v@@1I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.app.Dialog;
import android.content.DialogInterface;
import android.app.AlertDialog;                                    //~v@@@I~
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.Ahsv.AG;
import com.Ahsv.R;
import com.Ahsv.Utils;
import com.btmtest.gui.UButton;
import com.btmtest.utils.UView;

import jagoclient.Dump;


//public class UMenuDlg   extends DialogFragment                     //~v@@@R~//~1AhkR~
public class UMenuDlg                                              //~1AhkR~
{                                                                  //~2C29R~
	private static final String PARM_TITLE="title";                //~v@@@R~
	private static final String PARM_ITEMSID="items";              //~v@@@I~
	private static final String PARM_MULTICHOICE="multichoice";    //~v@@1R~
                                                                   //~v@@1I~
	public static final int MENUID_CONNECT=1;                     //~v@@@I~//~v@@1I~
	public static final int MENUID_SETTING=2;                      //~v@@@I~//~v@@1I~
	public static final int MENUID_INGAME=3;                       //~v@@@R~//~v@@1R~
                                                                   //~v@@1I~
	private String[] items;                                        //~v@@@I~
	private UMenuDlgI listener;                                    //~v@@@R~
    private AlertDialog.Builder builder;                           //~v@@@I~
    private int menuid;                                            //~v@@@I~
    private boolean[] selectedId;                                  //~v@@1I~
    private boolean[] selectedIdClone;                       //~v@@1I~
    private Dialog androidDialog;                                  //~v@@1I~
    private boolean swTheme=true;                                  //~v@@1R~
    private boolean swNoAutoDismiss;                               //~v@@1R~
    private boolean swNoAutoDismissSingleChoice=true;              //~9904I~
    private int layoutID;                                          //~v@@1I~
    private Bundle dialogParms;                                    //~1AhkI~
//**********************************                               //~v@@@I~
    public interface UMenuDlgI                                   //~v@@@R~
    {                                                              //~v@@@I~
		void menuItemSelected(int Pmenuid,int Pidx,String Pitem);  //~v@@@R~
		boolean menuItemSelectedMulti(int Pmenuid,boolean[] PselectedId);//~v@@1R~
		void onDestroy();                                          //~v@@1R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    public UMenuDlg()                                              //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	private static UMenuDlg newInstance(String Ptitle,int Pitemsid)//~v@@@R~
    {                                                              //~v@@@I~
		return newInstance(Ptitle,Pitemsid,false);                  //~v@@1I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@1I~
	private static UMenuDlg newInstance(String Ptitle,int Pitemsid,boolean Pmultichoice)//~v@@1I~
    {                                                              //~v@@1I~
    	if (Dump.Y) Dump.println("UMenuDlg.newInstance multichice="+Pmultichoice+",title="+Ptitle+",itemsid="+Integer.toHexString(Pitemsid));//~v@@1I~//~9B20R~
    	UMenuDlg dlg=new UMenuDlg();                               //~v@@1I~
    	Bundle b=new Bundle();                                     //~v@@1I~
        b.putString(PARM_TITLE,Ptitle);                            //~v@@1I~
        b.putInt(PARM_ITEMSID,Pitemsid);                           //~v@@1I~
        b.putBoolean(PARM_MULTICHOICE,Pmultichoice);                 //~v@@1I~
//      dlg.setArguments(b);                                       //~v@@1I~//~1AhkR~
        dlg.dialogParms=b;                                         //~1AhkI~
        return dlg;                                                //~v@@1I~
    }                                                              //~v@@1I~
//**********************************                               //~v@@@I~
	public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,int Ptitleid,int Pitemsid)//~v@@@I~//~v@@1R~
    {                                                              //~v@@@I~
//  	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,false/*multichoice*/);            //~v@@@I~//~v@@1R~
    	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,false/*multichoice*/,true);//~v@@1R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@1I~
	public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,int Ptitleid,int Pitemsid,int Playoutid)//~v@@1I~
    {                                                              //~v@@1I~
//  	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,false/*multichoice*/,true,Playoutid);//~v@@1R~
    	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,false/*multichoice*/,true,Playoutid,false/*swNoAutoDismiss*/);//~v@@1I~
    }                                                              //~v@@1I~
//**********************************                               //~v@@1I~
	public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,int Ptitleid,int Pitemsid,int Playoutid,boolean PswNoAutoDismiss)//~v@@1I~
    {                                                              //~v@@1I~
    	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,false/*multichoice*/,true,Playoutid,PswNoAutoDismiss);//~v@@1I~
    }                                                              //~v@@1I~
//**********************************                               //~v@@1I~
	public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,int Ptitleid,int Pitemsid,boolean Pswmultichoice)//~v@@1R~
    {                                                              //~v@@1I~
//  	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,Pswmultichoice);//~v@@1R~
    	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,Pswmultichoice,true);//~v@@1R~
    }                                                              //~v@@1I~
//**********************************                               //~v@@1I~
	public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,int Ptitleid,int Pitemsid,boolean Pswmultichoice,boolean PswCustomTheme)//~v@@1I~
    {                                                              //~v@@1I~
    	return show(Plistener,Pmenuid,Utils.getStr(Ptitleid),Pitemsid,Pswmultichoice,PswCustomTheme);//~v@@1I~
    }                                                              //~v@@1I~
//**********************************                               //~v@@@I~
//  public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,String Ptitle,int Pitemsid,boolean Pswmultichoice)//~v@@@I~//~v@@1R~
    public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,String Ptitle,int Pitemsid,boolean Pswmultichoice,boolean PswCustomTheme)//~v@@1I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UMenuDlg.show");                 //~v@@1I~
        UMenuDlg dlg=newInstance(Ptitle,Pitemsid,Pswmultichoice);                 //~v@@@R~//~v@@1R~
    	dlg.listener=Plistener;                                    //~v@@@I~
    	dlg.menuid=Pmenuid;                                        //~v@@@I~
    	dlg.swTheme=PswCustomTheme;                                //~v@@1I~
//      String tag=UMenuDlg.class.getSimpleName();                //~v@@@I~//~1AhkR~
//      if (Dump.Y) Dump.println("UMenuDlg.show call showDF");     //~v@@1I~//~1AhkR~
//      UView.showDF((DialogFragment)dlg,tag);                     //~v@@@I~//~1AhkR~
        dlg.showDlg();                                             //~1AhkI~
        if (Dump.Y) Dump.println("UMenuDlg.show after call showDF androidDialog==null?="+(dlg.androidDialog==null));//~v@@1R~
//        setTitleDivider(dlg);                                      //~v@@1R~
//  	setWidth(dlg);                                             //~v@@1I~
        return dlg;                                                //~v@@1I~
     }                                                              //~v@@@I~
//**********************************                               //~v@@1I~
//  public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,String Ptitle,int Pitemsid,boolean Pswmultichoice,boolean PswCustomTheme,int Playoutid)//~v@@1R~
    public static UMenuDlg show(UMenuDlgI Plistener,int Pmenuid,String Ptitle,int Pitemsid,boolean Pswmultichoice,boolean PswCustomTheme,int Playoutid,boolean PswNoAutoDismiss)//~v@@1I~
    {                                                              //~v@@1I~
        if (Dump.Y) Dump.println("UMenuDlg.show with layoutid");   //~v@@1I~
        UMenuDlg dlg=newInstance(Ptitle,Pitemsid,Pswmultichoice);  //~v@@1I~
    	dlg.listener=Plistener;                                    //~v@@1I~
    	dlg.menuid=Pmenuid;                                        //~v@@1I~
    	dlg.swTheme=PswCustomTheme;                                //~v@@1I~
    	dlg.layoutID=Playoutid;                                    //~v@@1I~
    	dlg.swNoAutoDismiss=PswNoAutoDismiss;                        //~v@@1I~
//      String tag=UMenuDlg.class.getSimpleName();                 //~v@@1I~//~1AhkR~
        if (Dump.Y) Dump.println("UMenuDlg.show call showDF");     //~v@@1I~
//      UView.showDF((DialogFragment)dlg,tag);                     //~v@@1I~//~1AhkR~
        dlg.showDlg();                                             //~1AhkI~
        if (Dump.Y) Dump.println("UMenuDlg.show after call showDF androidDialog==null?="+(dlg.androidDialog==null));//~v@@1I~
        return dlg;                                                //~v@@1I~
     }                                                             //~v@@1I~
//**********************************                               //~1AhkI~
    private void showDlg()                                         //~1AhkI~
    {                                                              //~1AhkI~
	    Dialog dlg=initDialog();                                   //~1AhkR~
        dlg.show();                                                 //~1AhkR~
    }                                                              //~1AhkI~
//**********************************                               //~v@@1I~
//  @Override                                                      //~v@@@I~//~1AhkR~
//  public Dialog onCreateDialog(Bundle Pbundle)                   //~v@@@I~//~1AhkR~
    private Dialog initDialog()                                      //~1AhkI~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UMenuDlg.onCreateDialog entry"); //~v@@1R~
//      Bundle b=getArguments();                                   //~v@@@I~//~1AhkR~
        Bundle b=dialogParms;                                      //~1AhkI~
        String title=b.getString(PARM_TITLE,null);                 //~v@@@I~
        int  itemsid=b.getInt(PARM_ITEMSID,0);                //~v@@@I~
        boolean swMultiChoice=b.getBoolean(PARM_MULTICHOICE,false); //~v@@1I~
     	AlertDialog.Builder builder;                               //~v@@1I~
        if (Dump.Y) Dump.println("UMenuDlg:onCreateDialog swTheme="+swTheme);//~@003I~//~v@@1R~
        if (swTheme)                                               //~@003I~//~v@@1I~
    		builder=new AlertDialog.Builder(new ContextThemeWrapper(AG.context,R.style.AlertDialogThemeCustom));//~@003I~//~v@@1I~
        else                                                       //~@003I~//~v@@1I~
     		builder=new AlertDialog.Builder(AG.context);//~v@@@I~  //~v@@1R~
	    builder.setTitle(title);                                   //~v@@@I~
        setLayout(builder);                                        //~v@@1I~
        if (swMultiChoice)                                         //~v@@1I~
        	setMenuMultiChoice(builder,itemsid);                   //~v@@1I~
        else                                                       //~v@@1I~
        if (swNoAutoDismiss)                                       //~v@@1I~
        	setMenuItemNoAutoDismiss(builder,itemsid);             //~v@@1I~
        else                                                       //~v@@1I~
        	setMenu(builder,itemsid);                                  //~v@@@I~//~v@@1R~
    	Dialog dlg=builder.create();                               //~v@@@I~
        if (swMultiChoice)                                         //~v@@1I~
        	setOnShowListenerMulti((AlertDialog)dlg);                           //~v@@1I~
        else                                                       //~v@@1I~
        if (swNoAutoDismiss)                                       //~v@@1I~
        	setMenuItemListenerNoAutoDismiss((AlertDialog)dlg);                 //~v@@1I~
        dlg.setCanceledOnTouchOutside(false);                      //~v@@@I~
        androidDialog=dlg;                                         //~v@@1I~
//      setTitleDivider(dlg);                                      //~v@@1I~
        setDivider();                                              //~v@@1I~
//      dlg.show();                       //setlayout effective when after show()//~v@@1I~
    	setWidth();                                                //~v@@1I~
        if (Dump.Y) Dump.println("UMenuDlg.onCreateDialog exit");  //~v@@1R~
    	return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@1I~
	private static void setWidth(UMenuDlg Pmdlg)                   //~v@@1I~
    {                                                              //~v@@1I~
        if (Dump.Y) Dump.println("UMenuDlg.setWidth static androidDialog="+(Pmdlg.androidDialog==null?"null":"not null"));//~v@@1I~
    	Pmdlg.setWidth();                                          //~v@@1I~
    }                                                              //~v@@1I~
//**********************************                               //~v@@@I~//~v@@1M~
	private void setWidth()                                 //~v@@1I~
    {                                                              //~v@@1M~
        if (Dump.Y) Dump.println("UMenuDlg.setWidth");             //~v@@1M~
    	AlertDialog ad=(AlertDialog)androidDialog;                 //~v@@1I~
        ad.show();                                                 //~v@@1I~
        int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@@1M~
//      int minww=(AG.portrait ? AG.scrWidth : AG.scrHeight)/2;     //~v@@1I~//+1amcR~
        int minww=(AG.portrait ? AG.scrWidth/2 : AG.scrWidth/3);   ////+1amcR~
        ad.getWindow().setLayout(minww,wc);                        //~v@@1R~
    }                                                              //~v@@1M~
//**********************************                               //~v@@1I~
	private void setLayout(AlertDialog.Builder Pbuilder)           //~v@@1I~
    {                                                              //~v@@1I~
        if (Dump.Y) Dump.println("UMenuDlg.setLayout");            //~v@@1I~
        if (layoutID==0)                                           //~v@@1I~
        	return;                                                //~v@@1I~
    	View v=AG.inflater.inflate(layoutID,null);                 //~v@@1R~
    	Pbuilder.setView(v);                                       //~v@@1I~
        UButton.UButtonI ubtni=(UButton.UButtonI)listener;                             //~v@@1I~
        Button btn=(Button)UView.findViewById(v,R.id.Cancel);//~v@@1M~
        if (btn!=null)                                             //~v@@1I~
	        UButton.bind(v,R.id.Cancel,ubtni);                     //~v@@1I~
        btn=(Button)UView.findViewById(v,R.id.Help); //~v@@1I~
        if (btn!=null)                                             //~v@@1I~
	        UButton.bind(v,R.id.Help,ubtni);                       //~v@@1I~
    }                                                              //~v@@1I~
////**********************************                             //~v@@1R~
//    public View onCreateView(LayoutInflater Pinflater, ViewGroup Pcontainer, Bundle Pbundle)//~v@@1R~
//    {                                                            //~v@@1R~
//        if (Dump.Y) Dump.println("UMenu.onCreateView androidDialog="+androidDialog.toString());//~v@@1R~
//        View v=super.onCreateView(Pinflater,Pcontainer,Pbundle); //~v@@1R~
//        setTitleDivider(androidDialog);                          //~v@@1R~
//        return v;                                                //~v@@1R~
//    }                                                            //~v@@1R~
//**********************************                               //~v@@1I~
    private void setDivider()                                      //~v@@1I~
    {                                                              //~v@@1I~
    	AlertDialog ad=(AlertDialog)androidDialog;               //~v@@1R~
        ListView lv=ad.getListView();                              //~v@@1I~
//      lv.setDivider(new ColorDrawable(Color.CYAN));  //light blue//~v@@1R~
        lv.setDivider(new ColorDrawable(Color.BLUE));  //light blue//~v@@1I~
        lv.setDividerHeight(1);                                    //~v@@1I~
    }                                                              //~v@@1I~
////**********************************                             //~v@@1R~
//    public static void setTitleDivider(Dialog Pdlg)              //~v@@1R~
//    {                                                            //~v@@1R~
//        if (Dump.Y) Dump.println("UMenuDlg.setTitleDivider Pdlg="+Pdlg.toString());//~v@@1R~
//        Alert.setDivider(Pdlg,0/*default color*/);               //~v@@1R~
//    }                                                            //~v@@1R~
////**************************************************             //~v@@1I~
//    @Override                                                    //~v@@1I~
//    public void onStart()                                        //~v@@1I~
//    {                                                            //~v@@1I~
//        if (Dump.Y) Dump.println("UMenuDlg.onStart");            //~v@@1I~
//        setTitleDivider(androidDialog);                          //~v@@1I~
//        super.onStart();                                         //~v@@1I~
//    }                                                            //~v@@1I~
////**************************************************               //~v@@1R~//~1AhkR~
////*return to onCreteView if added to backstack                     //~v@@1I~//~1AhkR~
////***************************************************              //~v@@1I~//~1AhkR~
//    @Override                                                      //~v@@1I~//~1AhkR~
//    public void onDestroyView()                                    //~v@@1I~//~1AhkR~
//    {                                                              //~v@@1I~//~1AhkR~
//        super.onDestroyView();                                     //~v@@1I~//~1AhkR~
//        if (Dump.Y) Dump.println("UMenuDlg.onDestroyView");        //~v@@1I~//~1AhkR~
//    }                                                              //~v@@1I~//~1AhkR~
////**********************************                               //~v@@1I~//~1AhkR~
//    @Override                                                      //~v@@1I~//~1AhkR~
//    public void onDismiss(DialogInterface Pdlg)                    //~v@@1I~//~1AhkR~
//    {                                                              //~v@@1I~//~1AhkR~
//        if (Dump.Y) Dump.println("UMenuDlg.onDismiss");  //~v@@1R~ //~9904R~//~1AhkR~
////      super.onDismiss(Pdlg);                                         //~v@@1I~//~9904R~//~1AhkR~
//        remove();                                                  //~v@@1I~//~1AhkR~
////TODO  listener.onDismiss();                                      //~v@@1R~//~1AhkR~
//    }                                                              //~v@@1I~//~1AhkR~
////**********************************                               //~v@@1I~//~1AhkR~
////*after onDestryView                                              //~v@@1I~//~1AhkR~
////**********************************                               //~v@@1I~//~1AhkR~
//    @Override                                                      //~v@@1I~//~1AhkR~
//    public void onDestroy()                                        //~v@@1I~//~1AhkR~
//    {                                                              //~v@@1I~//~1AhkR~
//        if (Dump.Y) Dump.println("UMenuDlg.onDestroy");            //~v@@1I~//~1AhkR~
//        super.onDestroy();                                         //~v@@1I~//~1AhkR~
//        listener.onDestroy();   //TODO                             //~v@@1I~//~1AhkR~
//    }                                                              //~v@@1I~//~1AhkR~
////**********************************                               //~v@@1I~//~1AhkR~
//    public void remove()                                           //~v@@1I~//~1AhkR~
//    {                                                              //~v@@1I~//~1AhkR~
//        if (Dump.Y) Dump.println("UMenuDlg.remove");               //~v@@1I~//~1AhkR~
////        FragmentManager fm=getFragmentManager();                 //~v@@1R~//~1AhkR~
////        FragmentTransaction ft=fm.beginTransaction();            //~v@@1R~//~1AhkR~
////        ft.remove(this);                                         //~v@@1R~//~1AhkR~
////        ft.commit();                                             //~v@@1R~//~1AhkR~
////        fm.popBackStack();                                       //~v@@1R~//~1AhkR~
//        UFDlg.remove(this);                                        //~v@@1I~//~1AhkR~
//    }                                                              //~v@@1I~//~1AhkR~
//**********************************                               //~v@@@I~
	private void setMenu(AlertDialog.Builder Pbuilder,int Pitemsid)//~v@@@R~
    {                                                              //~v@@@I~
    	items=AG.resource.getStringArray(Pitemsid);                //~v@@@R~
    	if (Dump.Y) Dump.println("UMenuDlg.setMenuitem items="+Utils.joinStr(",",items));//~v@@@R~
    	Pbuilder.setItems(Pitemsid,                                //~v@@@R~//~v@@1R~
//  	Pbuilder.setSingleChoiceItems(items,-1,                    //~v@@1R~
			new DialogInterface.OnClickListener()                  //~v@@@I~
			{                                                      //~v@@@I~
            	@Override                                          //~v@@@I~
				public void onClick(DialogInterface Pdlg,int Pidx) //~v@@@R~
                {                                                  //~v@@@I~
			    	if (Dump.Y) Dump.println("UMenuDlg.onClick idx="+Pidx);//~v@@@R~
                    itemSelected(menuid,Pidx);	  //setup data required after dismiss     //~v@@@R~//~v@@1R~
//  		    	if (Dump.Y) Dump.println("UMenuDlg.setMenu onClick dismiss()");//~v@@1R~
//              	Pdlg.dismiss();                                //~v@@1R~
                }                                                  //~v@@@I~
            });
     }                                                              //~v@@@I~
//**********************************                               //~9904I~
	private void setMenuSingleChoice(AlertDialog.Builder Pbuilder,int Pitemsid)//~9904I~
    {                                                              //~9904I~
    	items=AG.resource.getStringArray(Pitemsid);                //~9904I~
    	if (Dump.Y) Dump.println("UMenuDlg.setMenuitem items="+Utils.joinStr(",",items));//~9904I~
    	Pbuilder.setSingleChoiceItems(items,-1,                    //~9904I~
			new DialogInterface.OnClickListener()                  //~9904I~
			{                                                      //~9904I~
            	@Override                                          //~9904I~
				public void onClick(DialogInterface Pdlg,int Pidx) //~9904I~
                {                                                  //~9904I~
			    	if (Dump.Y) Dump.println("UMenuDlg.onClick idx="+Pidx);//~9904I~
                    itemSelected(menuid,Pidx);	  //setup data required after dismiss//~9904I~
                }                                                  //~9904I~
            });                                                    //~9904I~
     }                                                             //~9904I~
//**********************************                               //~v@@1I~
	private void setMenuItemNoAutoDismiss(AlertDialog.Builder Pbuilder,int Pitemsid)//~v@@1I~
    {                                                              //~v@@1I~
    	if (swNoAutoDismissSingleChoice)                            //~9904I~
        {                                                          //~9904I~
        	setMenuSingleChoice(Pbuilder,Pitemsid);                 //~9904I~
            return;                                                //~9904I~
        }                                                          //~9904I~
    	items=AG.resource.getStringArray(Pitemsid);                //~v@@1I~
    	if (Dump.Y) Dump.println("UMenuDlg.setMenuitemNoAutoDismiss items="+Utils.joinStr(",",items));//~v@@1I~
    	Pbuilder.setItems(Pitemsid,null);	//no onclickListener   //~v@@1I~
     }                                                             //~v@@1I~
//**********************************                               //~v@@1I~
	private void setMenuItemListenerNoAutoDismiss(AlertDialog Pdlg)//~v@@1I~
    {                                                              //~v@@1I~
    	if (Dump.Y) Dump.println("UMenuDlg.setMenuitemListenerNoAutoDismiss swNoAutoDismissSingleChoice="+swNoAutoDismissSingleChoice);//~9904I~
    	if (swNoAutoDismissSingleChoice)                            //~9904I~
            return;                                                //~9904I~
    	ListView lv=Pdlg.getListView();                            //~v@@1I~
    	if (Dump.Y) Dump.println("UMenuDlg.setMenuitemListenerNoAutoDismiss listview="+Utils.toString(lv));//~v@@1I~
    	lv.setOnItemClickListener(                                 //~v@@1I~
			new AdapterView.OnItemClickListener()                  //~v@@1I~
			{                                                      //~v@@1I~
            	@Override                                          //~v@@1I~
				public void onItemClick(AdapterView<?> parent,View Pview,int Pidx,long Pid)//~v@@1I~
                {                                                  //~v@@1I~
			    	if (Dump.Y) Dump.println("UMenuDlg.OnItemClickListener idx="+Pidx);//~v@@1I~
                    itemSelected(menuid,Pidx);	  //setup data required after dismiss//~v@@1I~
                }                                                  //~v@@1I~
            });                                                    //~v@@1I~
     }                                                             //~v@@1I~
//**********************************                               //~v@@@I~
	private void itemSelected(int Pmenuid,int Pidx)                //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("UMenuDlg.onClick menuid="+Pmenuid+",idx="+Pidx+"item="+items[Pidx]);//~v@@@M~//~v@@1R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	    	listener.menuItemSelected(Pmenuid,Pidx,items[Pidx]);   //~v@@@R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UMenuDlg:itemSelected menuid="+Pmenuid+",idx="+Pidx);//~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@1I~
	private void setMenuMultiChoice(AlertDialog.Builder Pbuilder,int Pitemsid)//~v@@1I~
    {                                                              //~v@@1I~
    	items=AG.resource.getStringArray(Pitemsid);                //~v@@1I~
    	if (Dump.Y) Dump.println("UMenuDlg.setMenuitem items="+Utils.joinStr(",",items));//~v@@1I~
        selectedId=new boolean[items.length];                      //~v@@1I~
        for (boolean b:selectedId)                                 //~v@@1I~
        	b=false;                                               //~v@@1I~
        selectedIdClone=selectedId.clone();                        //~v@@1R~
    	Pbuilder.setMultiChoiceItems(Pitemsid,                     //~v@@1I~
        	selectedId,                                            //~v@@1I~
			new DialogInterface.OnMultiChoiceClickListener()       //~v@@1I~
			{                                                      //~v@@1I~
            	@Override                                          //~v@@1I~
				public void onClick(DialogInterface Pdlg,int Pidx,boolean Pswchecked)//~v@@1I~
                {                                                  //~v@@1I~
			    	if (Dump.Y) Dump.println("UMenuDlg.onClick(multiChoice) idx="+Pidx);//~v@@1I~
			        selectedIdClone[Pidx]=Pswchecked;              //~v@@1I~
                }                                                  //~v@@1I~
            });                                                    //~v@@1I~
        Pbuilder.setNegativeButton(Utils.getStr(R.string.Cancel),   //~v@@1I~
			new DialogInterface.OnClickListener()                  //~v@@1I~
            {                                                      //~v@@1I~
            	@Override                                          //~v@@1I~
                public void onClick(DialogInterface Pdlg,int Pbuttonid)//~v@@1I~
                {                                                  //~v@@1I~
			    	if (Dump.Y) Dump.println("UMenuDlg.onClick Multiple Cancel buttonid"+Pbuttonid);//~v@@1I~
                	Pdlg.dismiss();                                //~v@@1I~
                }                                                  //~v@@1I~
            });                                                    //~v@@1I~
//        Pbuilder.setNeutralButton(Utils.getStr(R.string.OK),     //~v@@1I~
//            new DialogInterface.OnClickListener()                //~v@@1I~
//            {                                                    //~v@@1I~
//                @Override                                        //~v@@1I~
//                public void onClick(DialogInterface Pdlg,int Pbuttonid)//~v@@1I~
//                {                                                //~v@@1I~
//                    boolean rc=itemSelected(menuid,selectedIdClone);//~v@@1I~
//                    if (Dump.Y) Dump.println("UMenuDlg.onClick Multiple OK rc="+rc+",buttonid="+Pbuttonid);//~v@@1I~
//                    if (rc);                                     //~v@@1I~
//                        Pdlg.dismiss();                          //~v@@1I~
//                }                                                //~v@@1I~
//            });                                                  //~v@@1I~
          Pbuilder.setPositiveButton(Utils.getStr(R.string.OK),null);//~v@@1I~
     }                                                             //~v@@1I~
//**********************************                               //~v@@1I~
	private void setOnShowListenerMulti(AlertDialog Pdlg)               //~v@@1I~
    {                                                              //~v@@1I~
    	if (Dump.Y) Dump.println("UMenuDlg.setOnShowListener");    //~v@@1I~
        Pdlg.setOnShowListener(                                    //~v@@1I~
			new DialogInterface.OnShowListener()                              //~v@@1I~
			{                                                      //~v@@1I~
            	@Override                                          //~v@@1I~
				public void onShow(DialogInterface Pshowdlg)       //~v@@1I~
                {                                                  //~v@@1I~
			    	if (Dump.Y) Dump.println("UMenuDlg.setOnShowListener.onShow");//~v@@1I~
			        Button btnOK=((AlertDialog)Pshowdlg).getButton(DialogInterface.BUTTON_POSITIVE);//~v@@1I~
					setButtonListenerMulti(btnOK);                 //~v@@1I~
                }                                                  //~v@@1I~
            });                                                    //~v@@1I~
     }                                                             //~v@@1I~
//**********************************                               //~v@@1I~
	private void setButtonListenerMulti(Button Pbtn)               //~v@@1R~
    {                                                              //~v@@1I~
        Pbtn.setOnClickListener(                                   //~v@@1R~
			new View.OnClickListener()                             //~v@@1M~
			{                                                      //~v@@1M~
            	@Override                                          //~v@@1M~
				public void onClick(View Pview)//~v@@1M~
                {                                                  //~v@@1M~
			        boolean rc=itemSelected(menuid,selectedIdClone);//~v@@1M~
			    	if (Dump.Y) Dump.println("UMenuDlg.onClick Multiple OK rc="+rc);//~v@@1M~
			        if (rc)                                       //~v@@1M~
	                	androidDialog.dismiss();                            //~v@@1M~
                    else                                           //~v@@1M~
                    	Toast.makeText(AG.activity,"invalid selection",Toast.LENGTH_SHORT).show();//~v@@1M~
                }                                                  //~v@@1M~
            });                                                    //~v@@1M~
    }                                                              //~v@@1I~
////**********************************                             //~v@@1R~
//    private void setButtonListener(Button Pbtn)                  //~v@@1R~
//    {                                                            //~v@@1R~
//        Pbtn.setOnClickListener(                                 //~v@@1R~
//            new View.OnClickListener()                           //~v@@1R~
//            {                                                    //~v@@1R~
//                @Override                                        //~v@@1R~
//                public void onClick(View Pview)                  //~v@@1R~
//                {                                                //~v@@1R~
//                    int btnid=Pview.getID();                     //~v@@1R~
//                    if (Dump.Y) Dump.println("UMenuDlg.setButtonListener.onClick id="+Integer.toHexString(id));//~v@@1R~
//                    switch (id)                                  //~v@@1R~
//                    {                                            //~v@@1R~
//                    case R.id.Cancel:                            //~v@@1R~
//                        dismiss();                               //~v@@1R~
//                    case R.id.Help:                              //~v@@1R~
//                        dismiss();                               //~v@@1R~
//                    }                                            //~v@@1R~
//                }                                                //~v@@1R~
//            });                                                  //~v@@1R~
//    }                                                            //~v@@1R~
//**********************************                               //~v@@1I~
	private boolean itemSelected(int Pmenuid,boolean[] Pselected)     //~v@@1I~
    {                                                              //~v@@1I~
		boolean rc=false;
        if (Dump.Y) Dump.println("UMenuDlg.itemSeleced(multichoice) menuid="+Pmenuid);//~v@@1R~
        try                                                        //~v@@1I~
        {                                                          //~v@@1I~
	    	rc=listener.menuItemSelectedMulti(Pmenuid,selectedIdClone);//~v@@1I~
        }                                                          //~v@@1I~
        catch(Exception e)                                         //~v@@1I~
        {                                                          //~v@@1I~
            Dump.println(e,"UMenuDlg:itemSelected(multiChoice) menuid="+Pmenuid);//~v@@1I~
        }
        return rc;//~v@@1I~
    }                                                              //~v@@1I~
}//class                                                           //~v@@@R~
