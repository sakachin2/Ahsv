//*CID://+1ak3R~:                             update#=   29;       //~1ak3I~
//*****************************************************************//~v101I~
//*Audio picker                                                    //~vac5R~
//*****************************************************************//~v101I~
//1ak3 2021/09/10 picker(ACTION_PICK) for API30                    //~vac5I~
//*****************************************************************//~v@@@I~
package com.btmtest.dialog;                                        //~v@@@R~
import com.Ahsv.AG;                                                //~1ak3I~
import com.Ahsv.R;                                                 //~1ak3I~
import com.Ahsv.Utils;                                             //~1ak3I~
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map; //~v@@@I~
import jagoclient.Dump;                                            //~1ak3I~
import android.net.Uri;                                            //~1ak3I~

import android.content.Intent;                                     //~v@@@I~
import android.bluetooth.BluetoothDevice;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Dialog;//~v@@@I~
import android.graphics.Color;                                     //~v@@@I~
import android.view.WindowManager;//~v@@@I~
import android.os.Bundle;

import com.axe.AxeDialog;
import com.btmtest.utils.UMediaStore;                              //~1ak3R~
import com.btmtest.utils.UMediaStore.AudioFile;                    //~1ak3I~
import com.btmtest.utils.UView;                                         //~v@@@R~
import com.btmtest.gui.UListView;                                //~v@@@I~
import com.btmtest.gui.UButton;                                    //~v@@@R~

import static com.btmtest.game.GConst.*;                                  //~9723I~

public class UPicker extends AxeDialog                           //~v@@@R~//~1ak3R~
{                                                                  //~2C29R~
	private   static final String CN="UPicker.";                    //~1ak3R~
	private   static final String HELPFILE="UPicker";              //~1ak3I~
	private   static final int LAYOUTID=R.layout.upicker;          //~1ak3R~
	private   static final int LAYOUTID_LISTROW=R.layout.textrowlist_audio;//~1ak3I~
	private   static final int RID_LISTURL=R.id.UrlList;           //~1ak3R~
	private   static final int TITLEID=R.string.Title_Picker;      //~1ak3R~
	private   static final int ROWID_TITLE=R.id.Audio_Title;       //~1ak3I~
	private   static final int ROWID_ARTIST=R.id.Audio_Artist;     //~1ak3I~
	private   static final int ROWID_DURATION=R.id.Audio_Duration; //~1ak3R~
	private   static final int COLOR_BG_LIST=Color.rgb(0xc8,0xff,0xc8);  //orange//~1ak3I~
                                                                   //~v@@@I~
    private ListMedia LM;   	//listview media file              //~1ak3R~
    private View layoutView;                                       //~v@@@I~//~9A23R~//~1ak3R~
    private UPickerI callback;                                                               //~1A6fI]~//~1ak3R~
    private UMediaStore UMS;                                                               //~1A6fI]~//~1ak3I~
    private Uri uriDir,uriPicked;                                  //~1ak3R~
    private TextView tvUriDir;                                     //~1ak3I~
    private ArrayList<AudioFile> audioList;                        //~1ak3I~
    private boolean swOK;                                          //+1ak3I~
//**********************************                               //~v@@@I~//~1ak3I~
    public interface UPickerI                                   //~v@@@R~//~1ak3I~
    {                                                              //~v@@@I~//~1ak3I~
		void itemSelected(Uri PitemUri);  //~v@@@R~                //~1ak3R~
    }                                                              //~v@@@I~//~1ak3I~
    //******************************************                   //~v@@@M~
	public UPicker(int PlayoutID)                                             //~v@@@M~//~1ak3R~
	{                                                              //~3105R~//~v@@@M~
        super(PlayoutID);
	    if (Dump.Y) Dump.println(CN+"constructor exit");                //~1ak3R~
	}                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    public static UPicker newInstance(Uri PuriDir,UMediaStore PumediaStore)     //~v@@@R~//~1ak3R~
    {                                                              //~v@@@I~
		UPicker dlg=new UPicker(LAYOUTID);                                 //~1ak3R~
        dlg.UMS=PumediaStore;                                      //~1ak3R~
        dlg.callback=PumediaStore;                                  //~1ak3I~
        dlg.uriDir=PuriDir;                                        //~1ak3I~
        String title=Utils.getStr(TITLEID);                        //~1ak3I~
		dlg.showDialog();                                          //~1ak3R~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~1ak3I~
    public void showDialog()                                       //~1ak3I~
    {                                                              //~1ak3I~
        String title=Utils.getStr(TITLEID);                        //~1ak3I~
		showDialog(title);                                         //~1ak3I~
    }                                                              //~1ak3I~
	//***********************************************************************************//~1ak3I~
    @Override                                                      //~1ak3I~
	protected void setupDialogExtend(ViewGroup PlayoutView)        //~1ak3I~
    {                                                              //~1ak3I~
    	layoutView=PlayoutView;                                          //~v@@@I~
        tvUriDir=(TextView)    UView.findViewById(layoutView,R.id.tvUriDir);//~v@@@R~//~1ak3R~
        LM=new ListMedia(this,layoutView,RID_LISTURL,LAYOUTID_LISTROW);//~1ak3R~
        setDir();                                                  //~1ak3I~
        setListViewData();                                         //~1ak3I~
        LM.setBackground(COLOR_BG_LIST);                             //~3203I~//~3209R~//~@@@2R~//~v@@@R~//~1ak3R~
    }                                                              //~v@@@I~
    //*****************************************                    //~1ak3I~
    private void setDir()                                          //~1ak3I~
    {                                                              //~1ak3I~
        if (Dump.Y) Dump.println(CN+"setDir uriDir="+uriDir);      //~1ak3I~
        tvUriDir.setText(uriDir.toString());                                //~1ak3I~
    }                                                              //~1ak3I~
    //*****************************************                    //~1ak3I~
    private void setListViewData()                                 //~1ak3I~
    {                                                              //~1ak3I~
        if (Dump.Y) Dump.println(CN+"setListViewData uriDir="+uriDir);//~1ak3I~
        audioList=UMediaStore.getMemberList(uriDir);             //~1ak3R~
        for (AudioFile af:audioList)                               //~1ak3R~
        {                                                          //~1ak3I~
        	LM.add(af.title,af.artist,af.min*60+af.sec);           //~1ak3R~
        }                                                          //~1ak3I~
    }                                                              //~1ak3I~
    //******************************************                   //+1ak3I~
    @Override                                                      //+1ak3I~
    protected void onDismiss()                                     //+1ak3I~
	{                                                              //+1ak3I~
        if (Dump.Y) Dump.println(CN+"onDismiss swOK="+swOK);       //+1ak3I~
    	if (!swOK)                                                 //+1ak3I~
        	UMS.itemCanceled();                                     //+1ak3I~
    }                                                              //+1ak3I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~1ak3I~
    protected boolean onClickOK()                                  //~v@@@R~//~1ak3R~
	{                                                              //~v@@@I~
    	int selected=getSelected();                                //~1ak3R~
        boolean rc=false;                                          //~1ak3I~
        if (selected>=0)                                           //~1ak3I~
        {                                                          //~1ak3I~
    		uriPicked=UMediaStore.getMemberUri(uriDir,audioList.get(selected).id);//~1ak3R~
    		callback.itemSelected(uriPicked);                      //~1ak3R~
            swOK=true;                                             //+1ak3I~
            rc=true;                                               //~1ak3I~
        }                                                          //~1ak3I~
    	return rc;    //dismiss                                    //~1ak3R~
    }                                                              //~v@@@I~
	//**********************************                           //~1ak3I~
	@Override                                                      //~1ak3I~
    protected boolean onClickHelp()                                //~1ak3I~
    {                                                              //~1ak3I~
    	if (Dump.Y) Dump.println(CN+"onClickHelp");                //~1ak3I~
    	HelpDialog.newInstance(TITLEID,HELPFILE).showDlg();        //~1ak3I~
        return false;	//no dismiss                               //~1ak3I~
    }                                                              //~1ak3I~
    //******************************************                   //~3203I~
	private int getSelected()                                      //~3203I~
    {                                                              //~3203I~
	    int idx=LM.getValidSelectedPos();                           //~3203I~//~1ak3R~
        if (idx==-1)                                               //~3203I~
        {                                                          //~3203I~
    		UView.showToast(R.string.ErrNotSelected);                  //~v101I~//~v@@@R~
        }                                                          //~v@@@I~
        return idx;                                                //~3203I~
    }                                                              //~3203I~
    //**********************************************************************//~1ak3M~
    public void onItemClicked(int Ppos,int PoldSelected)           //~1ak3R~
    {                                                              //~1ak3I~
    	if (Dump.Y) Dump.println(CN+"onItemClicked Ppos="+Ppos+",PoldSelected="+PoldSelected);//~1ak3R~
    	Uri uriClicked=UMediaStore.getMemberUri(uriDir,audioList.get(Ppos).id);//~1ak3I~
        UMS.itemClicked(uriClicked);                               //~1ak3I~
    }                                                              //~1ak3I~
    //**********************************************************************//~1ak3I~
    //**********************************************************************//~1ak3I~
    //**********************************************************************//~1ak3I~
  	class ListMedia extends UListView                                 //~@002R~//~1ak3R~
	{                                                              //~v@@@I~
    	UPicker upicker;                                           //~1ak3I~
    //*****************                                            //~1A6fI~//~v@@@M~
        ListMedia(UPicker Pupicker,View Playout,int Plvid,int Prowresid)//~1A6fI~      //~v@@@R~//~1ak3R~
        {                                                          //~1A6fI~//~v@@@M~
            super(Playout,Plvid,Prowresid,null/*listener*/,UListView.CHOICEMODE_SINGLE);                     //~1A6fI~//~v@@@R~//~1ak3R~
            upicker=Pupicker;                                      //~1ak3I~
        }                                                          //~1A6fI~//~v@@@M~
    //**********************************************************************//~1ak3I~
        @Override                                                  //~1ak3I~
        public void onItemClicked(int Ppos,int PposSelected)         //~1ak3I~
        {                                                          //~1ak3I~
        	upicker.onItemClicked(Ppos,PposSelected);                //~1ak3I~
        }                                                          //~1ak3I~
    //**********************************************************************//~1A6fI~//~v@@@M~
        @Override                                                  //~1A6fI~//~v@@@M~
        public View getViewCustom(int Ppos, View Pview, ViewGroup Pparent)//~1A6fI~//~v@@@M~
        {                                                          //~1A6fI~//~v@@@M~
        //*******************                                      //~1A6fI~//~v@@@M~
            if (Dump.Y) Dump.println(CN+"ListMedia.getViewCustom Ppos="+Ppos+",CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());//~1A6fI~//~v@@@M~//~1ak3R~
            View v=Pview;                                          //~1A6fI~//~v@@@M~
            if (v == null)                                         //~1A6fI~//~v@@@M~
			{                                                      //~1A6fI~//~v@@@M~
                v=AG.inflater.inflate(rowId/*super*/,null);            //~1A65I~//~1A6fI~//~v@@@M~
            }                                                      //~1A6fI~//~v@@@M~
            TextView v1=(TextView)v.findViewById(ROWID_TITLE);      //~1A6fI~//~v@@@M~//~1ak3R~
            TextView v2=(TextView)v.findViewById(ROWID_ARTIST);    //~1A6fI~//~v@@@M~//~1ak3R~
            TextView v3=(TextView)v.findViewById(ROWID_DURATION);  //~1ak3R~
            UListViewData ld=arrayData.get(Ppos);                  //~v@@@I~
            v1.setText(ld.itemtext);                               //~1A6fI~//~v@@@M~
            if (Ppos==selectedpos)                                 //~1A6fI~//~v@@@M~
            {                                                      //~1A6fI~//~v@@@M~
                v1.setBackgroundColor(bgColorSelected);   //~1A6fI~//~v@@@M~
                v1.setTextColor(bgColor);                 //~1A6fI~//~v@@@M~
            }                                                      //~1A6fI~//~v@@@M~
            else                                                   //~1A6fI~//~v@@@M~
            {                                                      //~1A6fI~//~v@@@M~
                v1.setBackgroundColor(bgColor);           //~1A6fI~//~v@@@M~
                v1.setTextColor(ld.itemcolor);            //~1A6fI~//~v@@@M~
            }                                                      //~1A6fI~//~v@@@M~
            String artist;                                         //~1A6fI~//~v@@@M~//~1ak3R~
            if (ld.itemtext2==null)                                //~1A6fI~//~v@@@M~
            	artist="";                                         //~1A6fI~//~v@@@M~//~1ak3R~
            else                                                   //~1A6fI~//~v@@@M~
            	artist=ld.itemtext2;                               //~1A6fI~//~v@@@M~//~1ak3R~
            if (Dump.Y) Dump.println(CN+"ListMedia.getViewCustom itemtext="+ld.itemtext+",itemtext2="+ld.itemtext2);//~9731I~//~1ak3R~
            v2.setText(artist);                                    //~1A6fI~//~v@@@M~//~1ak3R~
            int sec=ld.itemint;                                    //~1ak3I~
            String duration=sec/60+"."+sec%60;                     //~1ak3R~
	        v3.setText(duration);//~v@@@I~                         //~1ak3R~
            return v;                                              //~1A6fI~//~v@@@M~
        }                                                          //~1A6fI~//~v@@@M~
	}//class                                                       //~1A6fI~//~v@@@I~
}//class                                                           //~v@@@R~
