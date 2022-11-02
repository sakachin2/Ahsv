//*CID://+1Ak3R~: update#= 251;                                    //~1Ak3R~
//**********************************************************************//~v105I~
//1ak3 2021/09/10 picker(ACTION_PICK) for API30                    //~1Ak3I~
//**********************************************************************//~1Ak3I~
//*My ListView Adapter                                                     //~1107I~//~1109R~
//**********************************************************************//~1107I~
package com.btmtest.gui;                                   //~1107R~  //~1108R~//~1109R~//~1114R~//~v107R~//~1AbSR~//~v@@@R~

import java.util.ArrayList;

import com.Ahsv.R;                                                 //~1Ak3I~
import com.Ahsv.AG;                                                //~1Ak3I~
import com.Ahsv.Utils;                                             //~1Ak3I~
import jagoclient.Dump;                                            //~1Ak3I~
import com.btmtest.utils.UView;

import android.view.MotionEvent;
import android.view.View;                                          //~1109I~
import android.view.View.OnTouchListener;
import android.view.ViewGroup;                                     //~1109I~
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ListAdapter;                                 //~@@@@I~//~v@@@I~
import android.graphics.Color;                                     //~v@@@M~
                                                                   //~1109I~
public class UListView                                                  //~1114           //~1220I~//~v@@@R~
{                                                                  //~1110I~
	public static final int CHOICEMODE_NONE=1;                     //~v@@@R~
	public static final int CHOICEMODE_SINGLE=2;                   //~v@@@I~
	public static final int CHOICEMODE_MULTIPLE=3;                 //~v@@@I~
                                                                   //~v@@@I~
	public static final int DEFAULT_ROWID=R.layout.textrowlist;    //~v@@@I~
                                                                   //~v@@@I~
	public ListView listview;                                      //~v@@@R~
	private ArrayAdapter<UListViewData> adapter;                        //~1425R~//~v@@@R~
    public ArrayList<UListViewData> arrayData;                          //~1A6fR~//~v@@@R~
    public int selectedpos=AdapterView.INVALID_POSITION;        //~1A6fI~//~v@@@R~
    public int rowId;                                              //~v@@@R~
	public int bgColor=Color.WHITE;                                      //~1112I~//~1219R~//~v@@@R~
	public int bgColorSelected=Utils.darkerColor(Utils.darkerColor(Color.BLUE));  //~1219R~//~v@@@R~
	private int bgColorChoosed=Color.YELLOW;                       //~v@@@R~
	private int choiceMode;                                        //~v@@@R~
    private UListViewI listener;                                   //~v@@@I~
                                                                   //~v107I~
//**************************************************************** //~v@@@I~
	public interface UListViewI                                    //~v@@@I~
	{                                                              //~v@@@I~
    	void onListItemClicked(int Ppos,int Pselectedpos);  //callback if SINGLE and Multiple//~v@@@R~
    	int  onListItemClickedMultiple(int Ppos); //callback if MULTIPLE before callbackSINGLE return 0 if selectable//~v@@@R~
		View getViewCustom(int Ppos, View Pview,ViewGroup Pparent);//~v@@@I~
	}                                                              //~v@@@I~
//**************************************************************** //~v@@@I~
    public class UListViewData                                     //~v@@@I~
    {                                                              //~v@@@I~
        public String itemtext;                                    //~v@@@I~
        public int    itemcolor;                                   //~v@@@I~
        public boolean choosed;                                    //~v@@@I~
        public String itemtext2;                                   //~v@@@I~
        public int    itemint;                                     //~v@@@I~
        public UListViewData(String Pitem,int Pcolor)              //~v@@@I~
        {                                                          //~v@@@I~
            itemtext=Pitem;                                        //~v@@@I~
            itemcolor=Pcolor;                                      //~v@@@I~
        }                                                          //~v@@@I~
        public UListViewData(String Pitem,int Pcolor,String Pitem2,int Pint)//~v@@@I~
        {                                                          //~v@@@I~
            itemtext=Pitem;                                        //~v@@@I~
            itemcolor=Pcolor;                                      //~v@@@I~
            itemtext2=Pitem2;                                      //~v@@@I~
            itemint=Pint;                                          //~v@@@I~
        }                                                          //~v@@@I~
                                                                   //~v@@@I~
    }//class                                                       //~v@@@I~
//*****************                                                //~1112I~
    public UListView(View Playout,int Pid,UListViewI Plistener)                //~v101I~//~v@@@R~
    {                                                              //~1112I~
	    this(Playout,Pid,Plistener,CHOICEMODE_SINGLE);             //~v@@@I~
    }                                                              //~1112I~
//*****************                                                //~v@@@I~
    public UListView(View Playout,int Pid,UListViewI Plistener,int Pchoicemode)//~v@@@I~
    {                                                              //~v@@@I~
	    this(Playout,Pid,DEFAULT_ROWID,Plistener,Pchoicemode);     //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~v@@@I~
    public UListView(View Playout,int Pid,int Prowid,UListViewI Plistener,int Pchoicemode)//~v@@@I~
    {                                                              //~v@@@I~
    	listview=(ListView)UView.findViewById(Playout,Pid);        //~v@@@I~
    	rowId=Prowid;                                              //~v@@@I~
        listener=Plistener;                                        //~v@@@I~
        choiceMode=Pchoicemode;                                    //~v@@@I~
    	init();                                                    //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~1220I~
    private void init()                                   //~1220I~
    {   
    	if (choiceMode==CHOICEMODE_NONE)                           //~v@@@I~
        	listview.setChoiceMode(ListView.CHOICE_MODE_NONE);      //~v@@@I~
        else                                                       //~v@@@I~
    	if (choiceMode==CHOICEMODE_SINGLE)                         //~v@@@I~
        	listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);    //~v@@@I~
        else                                                       //~v@@@I~
    	if (choiceMode==CHOICEMODE_MULTIPLE)                       //~v@@@I~
        	listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);  //~v@@@I~
    	arrayData=new ArrayList<UListViewData>();                       //~1220R~//~v@@@R~
    	setAdapter();                                              //~1220I~
    }                                                              //~1220I~
//*****************                                                //~1112I~
    public void add(String Pitem)                                   //~1112I~//~1220R~
    {                                                              //~1112I~
    	add(Pitem,Color.BLACK);                                        //~1112I~//~1403R~
    }                                                              //~1112I~
//*****************                                                //~1A6fI~
    public void add(String Pitem,String Pitem2,int Pint)           //~1A6fR~
    {                                                              //~1A6fI~
        if (Dump.Y) Dump.println("UListView.add item="+Pitem+",item2="+Pitem2+",int="+Pint);//~v@@@R~
    	UListViewData ld=add(Pitem,Color.BLACK);                        //~1A6fI~//~v@@@R~
        ld.itemtext2=Pitem2;                                       //~1A6fR~
        ld.itemint=Pint;                                           //~1A6fI~
    }                                                              //~1A6fI~
//*****************                                                //~v@@@I~
    public void add(String Pitem,String Pitem2,int Pcolor,int Pint)//~v@@@I~
    {                                                              //~v@@@I~
    	UListViewData ld=add(Pitem,Pcolor);                        //~v@@@I~
        ld.itemtext2=Pitem2;                                       //~v@@@I~
        ld.itemint=Pint;                                           //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~1220I~
    public UListViewData add(String Pitem,int Pcolor)                 //~1A6fI~//~v@@@R~
    {                                                              //~1220I~
    	UListViewData ld=new UListViewData(Pitem,Pcolor);                    //~1A6fI~//~v@@@R~
    	arrayData.add(ld);                                         //~1A6fI~
        if (Dump.Y) Dump.println("UListView.add isFocusable="+listview.isFocusable());//~v@@@I~
        if (choiceMode!=CHOICEMODE_NONE)                           //~v@@@I~
        {                                                          //~v@@@I~
            if (!(listview.isFocusable()))  //setFocusable is effective when not empty)//~1403R~//~v@@@R~
            {                                                          //~1403I~//~v@@@R~
                if (Dump.Y) Dump.println("List add selected position="+listview.getSelectedItemPosition());//~1506R~//~v@@@R~
                listview.setSelection(0);   //#### setSelection() is ignored if isInTouchMode()//~1403I~//~v@@@R~
                listview.setItemChecked(0,true); //call getView();setSelection() may move cursor//~1403I~//~v@@@R~
                if (Dump.Y) Dump.println("List add isFocusable="+listview.isFocusable());//~1506R~//~v@@@R~
                if (Dump.Y) Dump.println("List add isFocusableInTouchMode="+listview.isFocusableInTouchMode());//~1506R~//~v@@@R~
                listview.setFocusableInTouchMode(true);   //##### set desiredfocusableInTouchMode state if empty(set also focusable setting)//~1403R~//~v@@@R~
                                                          //#### setFocusableInToyuchMode() means also setFocusable()//~1403R~//~v@@@R~
                if (Dump.Y) Dump.println("List add selected position="+listview.getSelectedItemPosition());//~1506R~//~v@@@R~
                if (Dump.Y) Dump.println("List add isFocusable="+listview.isFocusable());//~1506R~//~v@@@R~
                if (Dump.Y) Dump.println("List add isFocusableInTouchMode="+listview.isFocusableInTouchMode());//~1506R~//~v@@@R~
                listview.requestFocus();                               //~1403I~//~v@@@R~
                listview.setItemChecked(selectedpos,true);             //~1403I~//~v@@@R~
            }                                                          //~1403I~//~v@@@R~
        }                                                          //~v@@@I~
        return ld;                                                 //~1A6fI~
    }                                                              //~1220I~
//*****************                                                //~1A6fI~
    public UListViewData update(String Pitem,String Pitem2)             //~1A6fI~//~v@@@R~
    {                                                              //~1A6fI~
    	int sz=arrayData.size();                                   //~1A6fI~
        for (int ii=0;ii<sz;ii++)                                  //~1A6fI~
        {                                                          //~1A6fI~
    		UListViewData ld=arrayData.get(ii);                         //~1A6fI~//~v@@@R~
            if (Pitem.equals(ld.itemtext))                         //~1A6fR~
            {                                                      //~1A6fI~
            	ld.itemtext2=Pitem2;                               //~1A6fI~
                return ld;                                         //~1A6fI~
            }                                                      //~1A6fI~
        }                                                          //~1A6fI~
        return null;                                               //~1A6fI~
    }                                                              //~1A6fI~
//*****************                                                //~1AbSI~
    public void remove(int Pindex)                                 //~1AbSI~
    {                                                              //~1AbSI~
    	ArrayList<UListViewData> newlist=new ArrayList<UListViewData>();     //~1AbSI~//~v@@@R~
    	int sz=arrayData.size();                                   //~1AbSI~
        for (int ii=0;ii<sz;ii++)                                  //~1AbSI~
        {                                                          //~1AbSI~
        	if (ii==Pindex)                                        //~1AbSI~
            	continue;                                          //~1AbSI~
    		UListViewData ld=arrayData.get(ii);                         //~1AbSI~//~v@@@R~
	    	newlist.add(ld);                                       //~1AbSI~
        }                                                          //~1AbSI~
    	setAdapter(newlist);                                       //~1AbSI~
    }                                                              //~1AbSI~
//*****************                                                //~1220I~
    public void setText(String Ptext)                              //~1220I~
    {                                                              //~1220I~
        removeAll(); //removeRange is protected                    //~1220R~
    	add(Ptext);                                                //~1220I~
    }                                                              //~1220I~
//*****************                                                //~1112I~
    public void setBackground(int Pcolor)                        //~1112R~//~v@@@R~
    {                                                              //~1112I~
    	bgColor=Pcolor;                                              //~1112I~//~1219R~
    }                                                              //~1112I~
//*****************                                                //~1112I~
    private void setAdapter()            //~1112I~//~1114R~     //~1219R~//~1220R~
    {                                                              //~1112I~
        adapter=new ListArrayAdapter(arrayData);//~1112I~//~1114R~  //~1219R~//~1220R~
        listview.setAdapter(adapter);                                    //~1112I~//~1114R~
    	setMode(listview);                                         //~1219I~
    }                                                              //~1112I~
//*****************                                                //~1AbSI~
    private void setAdapter(ArrayList<UListViewData> Plistdata)         //~1AbSI~//~v@@@R~
    {                                                              //~1AbSI~
    	arrayData=Plistdata;                                       //~1AbSI~
        adapter=new ListArrayAdapter(arrayData);                   //~1AbSI~
        listview.setAdapter(adapter);                              //~1AbSI~
    }                                                              //~1AbSI~
//*****************                                                //~1112I~
    private void setMode(ListView Plv)                             //~1112I~
    {                                                              //~1112I~
        if (Dump.Y) Dump.println("List setMode isFocusable="+Plv.isFocusable());//~1506R~
        if (Dump.Y) Dump.println("List setmode isFocusableInTouchMode="+Plv.isFocusableInTouchMode());//~1506R~
        if (choiceMode!=CHOICEMODE_NONE)                           //~v@@@I~
        {                                                          //~v@@@I~
        	Plv.setFocusableInTouchMode(true); //fail if Array is empty//~1403R~//~v@@@R~
        	if (Dump.Y) Dump.println("List setMode isFocusable="+Plv.isFocusable());//~1506R~//~v@@@R~
        	if (Dump.Y) Dump.println("List setmode isFocusableInTouchMode="+Plv.isFocusableInTouchMode());//~1506R~//~v@@@R~
			Plv.setItemsCanFocus(true);                                 //~1115I~//~v@@@R~
        	OnItemClickListener licl=new ListItemClickListener();          //~1115I~//~v@@@R~
        	Plv.setOnItemClickListener(licl);                            //~1115I~//~v@@@R~
        	OnItemLongClickListener lilcl=new ListItemLongClickListener();//~1307I~//~v@@@R~
        	Plv.setOnItemLongClickListener(lilcl);                     //~1307I~//~v@@@R~
        	OnItemSelectedListener lisl=new ListItemSelectedListener();//~1118I~//~v@@@R~
        	Plv.setOnItemSelectedListener(lisl);                       //~1118I~//~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	Plv.setFocusableInTouchMode(false); //fail if Array is empty//~v@@@I~
			Plv.setItemsCanFocus(false);                           //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("List setMode isFocusable="+Plv.isFocusable());//~1506R~
        if (Dump.Y) Dump.println("List setmode isFocusableInTouchMode="+Plv.isFocusableInTouchMode());//~1506R~
    }                                                              //~1112I~
//*****************                                                //~1112I~
    public int getSelectedPos()                                    //~1403R~
    {                                                              //~1403I~
    	return selectedpos;                                        //~1403I~
    }                                                              //~1403I~
//*****************                                                //~v101I~
    public int getValidSelectedPos()                               //~v101I~
    {                                                              //~v101I~
    	int pos=selectedpos;                                       //~v101I~
        if (pos==AdapterView.INVALID_POSITION||pos>=arrayData.size())//~v101I~
        	return -1;                                             //~v101I~
        return pos;                                                //~v101I~
    }                                                              //~v101I~
    public String getSelectedItem()                                //~1112I~
    {                                                              //~1112I~
    	int pos;                                                   //~1118R~
    	pos=listview.getCheckedItemPosition();                     //~1118I~
        if (Dump.Y) Dump.println("Listview getCheckedItem pos="+pos);//~1506R~
    	pos=listview.getSelectedItemPosition();	//trackball selection//~1115I~//~1118R~
        if (Dump.Y) Dump.println("Listview getSelectedItem pos="+pos);//~1506R~
        if (Dump.Y) Dump.println("selected item="+listview.getSelectedItem());//~1506R~
        pos=selectedpos;                                            //~1118I~
        if (Dump.Y) Dump.println("Listview selectedpos="+pos);     //~1506R~
        if (pos==AdapterView.INVALID_POSITION||pos>=arrayData.size())                       //~1114I~//~1118R~//~1220R~
        	return "";                                             //~1114I~
        String item=arrayData.get(pos).itemtext;                             //~1118I~//~1220R~
        if (Dump.Y) Dump.println("Listview selectedpos="+item);    //~1506R~
        return item;                                               //~1118R~
    }                                                              //~1112I~
//*****************                                                //~1112I~
    public int getItemCount()                                      //~1112I~
    {                                                              //~1112I~
        return arrayData.size();                                  //~1112I~//~1220R~
    }                                                              //~1112I~
//*****************                                                //~1112I~
    public void select(int Ppos)                                 //~1112I~
    {                                                              //~1112I~
    	selectedpos=Ppos;                                          //~1118M~
        if (Dump.Y) Dump.println("Listview select(setSlectedItem) req="+Ppos+",slected="+listview.getSelectedItemPosition());//~1506R~
        if (Dump.Y) Dump.println("Listview select(setItemChecked) pos="+listview.getCheckedItemPosition());//~1506R~
        listview.requestFocus();                                   //~1118I~
        setItemChecked(Ppos,true);                                 //~v105I~
        if (Dump.Y) Dump.println("Listview after requestfocus id="+Integer.toString(listview.getId(),16)+",isfocused="+listview.isFocused());//~1118I~//~1506R~
    }                                                              //~1112I~
//*****************                                                //~v105I~
    private void setItemChecked(int Ppos,boolean Pstate)            //~v105I~//~v@@@R~
    {                                                              //~v105I~
        setItemChecked(listview,Ppos,Pstate);            //~v105I~ //~v@@@R~
    }                                                              //~v@@@I~
//*****************                                                //~v@@@I~
    private void setItemChecked(ListView Plistview,int Ppos,boolean Pstate)//~v105I~//~@@@@R~//~v@@@I~
    {                                                              //~v105I~//~@@@@R~//~v@@@I~
        if (Dump.Y) Dump.println("UListView:setItemChecked pos="+Ppos);//~v105I~//~@@@@R~//~v@@@R~
        listview.setItemChecked(Ppos,Pstate);                //~v105I~//~@@@@R~//~v@@@I~
    }                                                              //~v105I~
//*****************                                                //~v@@@I~
    public void notifyChanged()                                    //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("UListView notifyChaged");        //~v@@@I~
        if (adapter!=null)                                         //~v@@@I~
        {                                                          //~v@@@I~
    		if (AG.isMainThread())                                 //~v@@@I~
            {                                                      //~v@@@I~
		        if (Dump.Y) Dump.println("UListView notifyChaged adapter="+adapter.toString());//~v@@@R~
				adapter.notifyDataSetChanged(); //delete "deleted entry" from list shown//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~1215I~
    public void removeAll(Boolean Pnotify)	//for messageFilter                    //~1215I~//~v@@@R~
    {                                                              //~1215I~
        arrayData.clear();  //removeRange is protected             //~1220I~
		selectedpos=AdapterView.INVALID_POSITION;                  //~1411I~
        if (Pnotify)                                               //~v@@@I~
		    notifyChanged();                                       //~v@@@R~
    }                                                              //~1215I~
//*****************                                                //~v@@@I~
    public void removeAll()	//for messageFilter                    //~v@@@I~
    {                                                              //~v@@@I~
    	removeAll(true/*notify*/);                                 //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~1220I~
    public void showBottom()                                       //~1220I~
    {                                                              //~1220I~
        showList(arrayData.size()-1);             //~1221I~        //~v@@@R~
    }                                                              //~1220I~
//*****************                                                //~1220I~
    public void showList()                                         //~1220I~//~1221R~
    {                                                              //~1220I~
        showList(-1/*keep currentpos*/);          //~1221I~        //~v@@@R~
    }                                                              //~1220I~
//*****************                                                //~1221I~//~v@@@I~
    private void showList(int Ppos)            //~1221R~//~@@@@R~   //~v@@@I~
    {                                                              //~1221I~//~@@@@R~//~v@@@I~
        if (Dump.Y) Dump.println("showList pos="+Ppos); //~1506R~//~@@@@R~//~v@@@I~
        ListAdapter adapter=listview.getAdapter();                 //~1221I~//~@@@@R~//~v@@@I~
        ((BaseAdapter)adapter).notifyDataSetChanged();             //~@@@@R~//~v@@@I~
        int pos=Ppos;                                                  //~1221I~//~@@@@R~//~v@@@I~
        if (pos<0) //keep currenttop                               //~@@@@R~//~v@@@I~
            pos=listview.getFirstVisiblePosition();//~1221R~       //~@@@@R~//~v@@@I~
        listview.setSelectionFromTop(pos,0);                       //~1221I~//~@@@@R~//~v@@@I~
    }                                                              //~1221I~//~@@@@R~//~v@@@I~
//**********************************************************************//~1111I~
//*ArrayAdapter class                                              //~1111I~
//**********************************************************************//~1111I~
    class ListArrayAdapter extends ArrayAdapter<UListViewData>           //~1111I~//~1112R~//~1114R~//~v@@@R~
    {                                                              //~1111I~
        public ListArrayAdapter(ArrayList<UListViewData> ParrayData)//~1111I~//~1112R~//~1114R~//~1219R~//~v@@@R~
        {                                                          //~1111I~
            super(AG.context,rowId,ParrayData);         //~1111I~//~1211R~//~1219R~
        }                                                          //~1111I~
        @Override                                                  //~1111I~
        public View getView(int Ppos, View Pview,ViewGroup Pparent)//~1111I~
        {                                                          //~1111I~
	        TextView tv;                                           //~1115I~
                                         //~1219I~
        //*******************                                      //~1115I~
            if (Dump.Y) Dump.println("UListView:ListArrayAdapter getview Ppos="+Ppos+",CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());//~1506R~//~v107R~//~v@@@R~
            View customView=getViewCustom(Ppos,Pview,Pparent);     //~1A6fI~
            if (customView!=null) 	//overidden by ListCustom      //~1A6fI~
            	return customView;                                 //~1A6fI~
            tv=(TextView)super.getView(Ppos,Pview,Pparent);
            UListViewData ld=arrayData.get(Ppos);//~1111I~//~1115M~//~1219R~//~1220R~//~v@@@R~
            tv.setText(ld.itemtext);
          	if (choiceMode==CHOICEMODE_MULTIPLE)                   //~v@@@R~
            {                                                      //~v@@@I~
          		if (ld.choosed)                                    //~v@@@I~
            	{                                                  //~v@@@I~
                	tv.setBackgroundColor(bgColorChoosed);         //~v@@@I~
            	}                                                  //~v@@@I~
            	else                                               //~v@@@I~
            	{                                                  //~v@@@I~
                	tv.setBackgroundColor(bgColor);                //~v@@@I~
            	}                                                  //~v@@@I~
            	tv.setTextColor(ld.itemcolor);                     //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
          	{                                                      //~v@@@I~
                if (Ppos==selectedpos)                             //~1219I~//~1220R~//~v@@@R~
                {                                                  //~1219I~//~1220R~//~v@@@R~
                    tv.setBackgroundColor(bgColorSelected);//~1219R~//~1220R~//~v@@@R~
                    tv.setTextColor(bgColor);            //~1219R~//~1220R~//~v@@@R~
                }                                                  //~1219I~//~1220R~//~v@@@R~
                else                                               //~1219I~//~1220R~//~v@@@R~
                {                                                  //~1219I~//~1220R~//~v@@@R~
                    tv.setBackgroundColor(bgColor);       //~1219I~//~1220R~//~v@@@R~
                    tv.setTextColor(ld.itemcolor);           //~1219I~//~1220R~//~v@@@R~
                }                                                  //~1219I~//~1220R~//~v@@@R~
            }                                                      //~v@@@I~
			getViewAdjust(Ppos,tv,Pparent,ld,selectedpos);         //~1A6fR~
            return tv;                                             //~1111I~
        }                                                          //~1111I~
    }//inner class                                                 //~1111I~
//**********************************************************************//~1115I~
//*itemclicklistener                                               //~1115I~
//**********************************************************************//~1115I~
    class ListItemClickListener implements OnItemClickListener     //~1115I~
    {                                                              //~1115I~
    	@Override                                                  //~1115I~
        public void onItemClick(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1115R~
        {                                                              //~v@@@I~//~1115I~
          try                                                      //~v107I~
          {                                                        //~v107I~
            if (Dump.Y) Dump.println("List OnItemClick pos="+Ppos+",choiceMode="+choiceMode);//~v@@@R~
            if (choiceMode==CHOICEMODE_MULTIPLE)                   //~v@@@R~
            	updateChoice(Ppos);                                //~v@@@I~
            if (Dump.Y) Dump.println("Listview OnItemClick getCheckedItemPos="+Plistview.getSelectedItemPosition());//~1506R~
            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusable="+Plistview.isFocusable());//~1506R~
            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusableInTouchMode="+Plistview.isFocusableInTouchMode());//~1506R~
            if (Dump.Y) Dump.println("Listview OnItemClick textview isInTouchMode="+Ptextview.isInTouchMode());//~1506R~
            Plistview.requestFocusFromTouch();                     //~1118R~
			((BaseAdapter)Plistview.getAdapter()).notifyDataSetChanged(); //invalidate is not effective to call getView()//~v107I~
            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusable="+Plistview.isFocusable());//~1506R~
            if (Dump.Y) Dump.println("Listview OnItemClick listview isFocusableInTouchMode="+Plistview.isFocusableInTouchMode());//~1506R~
            if (Dump.Y) Dump.println("Listview OnItemClick textview isInTouchMode="+Ptextview.isInTouchMode());//~1506R~
            if (listener!=null)                                    //~v@@@I~
	            listener.onListItemClicked(Ppos,selectedpos);      //~v@@@I~
    		onItemClicked(Ppos,selectedpos);                       //+1Ak3I~
          }                                                        //~v107I~
          catch(Exception e)                                       //~v107I~
          {                                                        //~v107I~
          	Dump.println(e,"List:onItemClick");                    //~v107I~
          }                                                        //~v107I~
            selectedpos=Ppos;                                      //~1118I~
        }                                                          //~1115I~

    }//inner class                                                 //~1115I~
//**********************************************************************//~1Ak3I~
//*for extender to override without thru interface                 //~1Ak3I~
//**********************************************************************//~1Ak3I~
    public void onItemClicked(int Ppos,int PoldSelected)           //~1Ak3R~
    {                                                              //~1Ak3I~
    	if (Dump.Y) Dump.println("UListView.onItemClicked pos="+Ppos+",posSelected="+PoldSelected);//~1Ak3R~
    }                                                              //~1Ak3I~
//**********************************************************************//~1307I~
//*itemclicklistener  LONG                                         //~1307R~
//**********************************************************************//~1307I~
    class ListItemLongClickListener implements OnItemLongClickListener//~1307I~
    {                                                              //~1307I~
    	@Override                                                  //~1307I~
        public boolean onItemLongClick(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1307I~
        {                                                          //~1307I~
            if (Dump.Y) Dump.println("List OnItemClick pos="+Ppos);//~1506R~
                                                                   //~1307I~
            if (Dump.Y) Dump.println("Listview OnItemLongClick getCheckedItemPos="+Plistview.getSelectedItemPosition());//~1506R~
            Plistview.requestFocusFromTouch();                     //~1307I~
            if (Dump.Y) Dump.println("Listview OnItemLongClick listview requestfocusfromtouch="+Plistview.requestFocusFromTouch());//~1506R~
            selectedpos=Ppos;   //setSelction() cause scroll       //~1307I~
			((BaseAdapter) Plistview.getAdapter()).notifyDataSetChanged(); //invalidate is not effective to call getView()//~1307I~
            return false;	//continue to ContextMenu processing   //~1307I~
        }                                                          //~1307I~
                                                                   //~1307I~
    }//inner class                                                 //~1307I~
//**********************************************************************//~1118I~
//*itemcselectedlistener  for keyboard up/down                     //~1403R~
//**********************************************************************//~1118I~
    class ListItemSelectedListener implements OnItemSelectedListener//~1118I~
    {                                                              //~1118I~
    	@Override                                                  //~1118I~
        public void onItemSelected(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1118I~
        {                                                          //~1118I~
            if (Dump.Y) Dump.println("List OnItemSelected pos="+Ppos);//~1506R~
            selectedpos=Ppos;                                      //~1118I~
            listview.setItemChecked(Ppos,true); //call getView();setSelection() may move cursor//~1403R~
        }                                                          //~1118I~
    	@Override
    	public void onNothingSelected(AdapterView<?> arg0) {
    		if (Dump.Y) Dump.println("List OnItemSelected Nothing");//~1506R~
    	}
    }//inner class                                                 //~1118I~
//**********************************************************************//~1115I~
//*itemclicklistener                                               //~1115I~
//**********************************************************************//~1115I~
    class ListTouchListener implements OnTouchListener             //~1115I~
    {                                                              //~1115I~
    	@Override                                                  //~1115I~
        public boolean onTouch(View view,MotionEvent event)        //~1115I~
        {                                                          //~1115I~
            if (Dump.Y) Dump.println("List OnTouch");              //~1506R~
            return false;                                              //~@@@@I~//~1115I~
        }                                                              //~0914I~//~1115I~
    }//ListTouchListener                                           //~1115I~
//**********************************************************************//~v@@@I~
    public void setChoiceMode(int Pchoicemode)                     //~v@@@I~
    {                                                              //~v@@@I~
    	int cm;                                                    //~v@@@I~
    	if (Pchoicemode==CHOICEMODE_MULTIPLE)                      //~v@@@R~
        {                                                          //~v@@@I~
        	cm=ListView.CHOICE_MODE_MULTIPLE;                      //~v@@@R~
	        int pos=selectedpos;                                   //~v@@@I~
    	    listview.setItemChecked(pos,false); //call getView();setSelection() may move cursor//~v@@@I~
    		updateChoice(pos);	//keep selected one                //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	clearChoosed();                                        //~v@@@I~
            if (Pchoicemode==CHOICEMODE_SINGLE)                    //~v@@@R~
                cm=ListView.CHOICE_MODE_SINGLE;                    //~v@@@R~
            else                                                   //~v@@@I~
                cm=ListView.CHOICE_MODE_NONE;                      //~v@@@R~
        }                                                          //~v@@@I~
        choiceMode=Pchoicemode;                                     //~v@@@I~
	    selectedpos=AdapterView.INVALID_POSITION;                  //~v@@@I~
        listview.setChoiceMode(cm);                                //~v@@@R~
		adapter.notifyDataSetChanged(); //delete "deleted entry" from list shown//~v@@@I~
        if (Dump.Y) Dump.println("List:setChoiceMode: notifydataSetChanged sw="+Pchoicemode+",mode="+choiceMode);//~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    private void clearChoosed()                                    //~v@@@I~
    {                                                              //~v@@@I~
        int ctr=arrayData.size();                                  //~v@@@I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
            UListViewData ld=arrayData.get(ii);                         //~v@@@I~
            ld.choosed=false;                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~v@@@I~
    public int[] getCheckedItemPositions()                         //~v@@@I~
    {                                                              //~v@@@I~
        int ctr=arrayData.size();                                  //~v@@@I~
        int selctr=0;                                              //~v@@@I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
            UListViewData ld=arrayData.get(ii);                         //~v@@@I~
            if (ld.choosed)                                        //~v@@@I~
            	selctr++;                                          //~v@@@I~
        }                                                          //~v@@@I~
        int[] rc=new int[selctr];                                  //~v@@@I~
        selctr=0;                                                  //~v@@@I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
            UListViewData ld=arrayData.get(ii);                         //~v@@@I~
            if (ld.choosed)                                        //~v@@@I~
            	rc[selctr++]=ii;                                   //~v@@@I~
        }                                                          //~v@@@I~
		return  rc;                                                //~v@@@I~
    }                                                              //~v@@@I~
//*****************                                                //~v@@@I~
    private int updateChoice(int Ppos)                             //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=-1;                                                 //~v@@@I~
        if (Ppos>=0 && Ppos<arrayData.size())                      //~v@@@I~
        {                                                          //~v@@@I~
        	int rc2=4;                                             //~v@@@I~
            if (listener!=null)                                    //~v@@@I~
	            rc2=listener.onListItemClickedMultiple(Ppos);//chk selectable//~v@@@I~
            if (rc2==0)                                            //~v@@@I~
            {                                                      //~v@@@I~
        		UListViewData ld=arrayData.get(Ppos);                   //~v@@@I~
            	boolean c=ld.choosed;                              //~v@@@I~
            	ld.choosed=!c;                                     //~v@@@I~
            	if (Dump.Y) Dump.println("ListView.updateChoice MultipleChoice pos="+Ppos+",choiced="+ld.choosed);//~v@@@R~
            	rc=ld.choosed?1:0;                                 //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~1A6fI~
//*overridden by ListCustom                                        //~1A6fI~
//**********************************************************************//~1A6fI~
	protected View getViewCustom(int Ppos, View Pview,ViewGroup Pparent)//~1A6fI~
    {                                                              //~1A6fI~
        if (listener!=null)                                        //~v@@@I~
			return listener.getViewCustom(Ppos,Pview,Pparent);              //~v@@@I~
    	return null;                                               //~1A6fI~
    }                                                              //~1A6fI~
//**********************************************************************//~1A6fI~
	protected void getViewAdjust(int Ppos, TextView Pview, ViewGroup Pparent,UListViewData Plistdata,int Pselectedpos)//~1A6fR~//~v@@@R~
	{                                                              //~1A6fI~
	}
//**********************************************************************//~v@@@I~
	public void selectAll(int Ppos,String Ppostfix)                //~v@@@R~
	{                                                              //~v@@@I~
        int sz=arrayData.size();                                   //~v@@@I~
        if (Dump.Y) Dump.println("List:selectAll pos="+Ppos+",posfix="+Ppostfix+",size="+sz);//~v@@@R~
        if (Ppos<sz)                                               //~v@@@I~
        {                                                          //~v@@@I~
            for (int ii=Ppos;ii<arrayData.size();ii++)             //~v@@@I~
            {                                                      //~v@@@I~
                UListViewData ld=arrayData.get(ii);                     //~v@@@I~
                if (Ppostfix==null || !ld.itemtext.endsWith(Ppostfix))    //skip diirectory//~v@@@R~
                {                                                  //~v@@@I~
                    ld.choosed=true;                               //~v@@@I~
                    if (Dump.Y) Dump.println("List:selectAll selected pos="+ii+",itemtext="+ld.itemtext);//~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
//          setChoiceMode(2/*multiple*/);      //do dataChanged    //~v@@@R~
    		notifyChanged();                                       //~v@@@I~
        }                                                          //~v@@@I~
	}                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
	public void clearChoice(Boolean Pswchoosed)                    //~v@@@R~
	{                                                              //~v@@@I~
        int sz=arrayData.size();                                   //~v@@@I~
        if (Dump.Y) Dump.println("List:clearChoice size="+sz);     //~v@@@I~
        for (int ii=0;ii<arrayData.size();ii++)                 //~v@@@I~
        {                                                          //~v@@@I~
            UListViewData ld=arrayData.get(ii);                    //~v@@@I~
            ld.choosed=Pswchoosed;                                 //~v@@@R~
        }                                                          //~v@@@I~
	}                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
	public void selectAll()                                        //~v@@@M~
	{                                                              //~v@@@M~
        if (Dump.Y) Dump.println("List:selectAll");                //~v@@@M~
		clearChoice(true/*choosed*/);                              //~v@@@I~
    	notifyChanged();                                           //~v@@@I~
    }                                                              //~v@@@M~
//**********************************************************************//~v@@@M~
	public void deselectAll()                                      //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("List:deselectAll");              //~v@@@I~
		clearChoice(false/*not choosed*/);                         //~v@@@R~
	    selectedpos=AdapterView.INVALID_POSITION;                  //~v@@@I~
    	notifyChanged();                                           //~v@@@I~
	}                                                              //~v@@@R~
//*****************                                                //~1220I~//~v@@@I~
    public void appendLine(String Pline)                           //~1403I~//~v@@@I~
    {                                                              //~1403I~//~v@@@I~
		appendLine(Pline,Color.BLACK);                       //~1403I~//~v@@@I~
    }                                                              //~1403I~//~v@@@I~
    public void appendLine(String Pline,int Pcolor)              //~1220I~//~v@@@I~
    {                                                              //~1220I~//~v@@@I~
        add(Pline,Pcolor);                                    //~1221I~//~v@@@I~
    }                                                              //~1220I~//~v@@@I~
//*****************                                                //~v@@@I~
    public void  setSelection(int Ppos)                            //~1411I~//~v@@@I~
    {                                                              //~1411I~//~v@@@I~
        if (Dump.Y) Dump.println("UListView.setSelection pos="+Ppos);//~v@@@I~
    	if (choiceMode==CHOICEMODE_MULTIPLE)                       //~v@@@I~
        {                                                          //~v@@@I~
            UListViewData ld=arrayData.get(Ppos);                  //~v@@@I~
            ld.choosed=true;                                       //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        	select(Ppos);                                  //~1411I~//~v@@@R~
    }                                                              //~1411I~//~v@@@I~
//*****************                                                //~v@@@I~
    public void clearList()                                        //~1403I~//~v@@@I~
    {                                                              //~1403I~//~v@@@I~
        removeAll();                                          //~1403I~//~v@@@I~
    }                                                              //~1403I~//~v@@@I~
////*****************                                              //~v@@@R~
//    public int[] getCheckedItem()                                //~v@@@R~
//    {                                                            //~v@@@R~
//        SharedBooleanArray aba=getCheckedItemPositions();        //~v@@@R~
//        ctr=sba.size();                                          //~v@@@R~
//        int[] ia=new int[ctr];                                   //~v@@@R~
//        for (int ii=0;ii<sba.size();ii++)                        //~v@@@R~
//        {                                                        //~v@@@R~
//            ia[ii]=sba.keyAt[ii];                                //~v@@@R~
//        }                                                        //~v@@@R~
//        return ia;                                               //~v@@@R~
//    }                                                            //~v@@@R~
}//class                                                           //~1109I~
