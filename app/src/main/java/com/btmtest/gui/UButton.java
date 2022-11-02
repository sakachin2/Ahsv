//*CID://+1ak3R~:                             update#=   86;       //~1ak3R~
//**********************************************************************//~v105I~
//1ak3 2021/09/10 picker(ACTION_PICK) for API30                    //~1ak3I~
//get View & set Button OnClickListener                            //~v@@@R~
//**********************************************************************//~v105I~
package com.btmtest.gui;                                           //~v@@@R~
                                                                   //~v@@@I~
import com.Ahsv.AG;                                                //+1ak3I~
import android.view.View;
import android.view.ViewGroup;                                     //~1ak3I~
import android.widget.CheckBox;                                    //~1ak3I~
import android.widget.Button;
//~v@@@I~
import jagoclient.Dump;                                              //~v@@@R~
import com.btmtest.utils.UView;
//~1112I~
public class UButton implements View.OnClickListener               //~v@@@R~
{                                                              //~1528I~//~v@@@I~
//**********************************                               //~v@@@I~
    public interface UButtonI                                      //~v@@@I~
    {                                                              //~v@@@I~
        void onClickButton(Button Pbutton);                        //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private UButtonI listener;                                             //~v@@@R~
//**********************************                               //~v@@@I~
    public UButton(UButtonI Plistener)                //~1528I~    //~v@@@R~
    {                                                          //~1528I~//~v@@@I~
        listener=Plistener;                                        //~v@@@I~
    }                                                          //~1528I~//~v@@@I~
    @Override                                                  //~1831I~//~v@@@I~
    public void onClick(View Pv)                               //~1528I~//~v@@@I~
    {                                                          //~1528I~//~v@@@I~
        if (Dump.Y) Dump.println("UButton:onClick listener="+listener.getClass().getSimpleName()+",id="+Pv.getId());//~v@@@R~
        try                                                    //~1831I~//~v@@@I~
        {                                                      //~1831I~//~v@@@I~
            listener.onClickButton((Button)Pv);              //~1831R~     //~v@@@R~
        }                                                      //~1831I~//~v@@@I~
        catch(Exception e)                                     //~1831I~//~v@@@I~
        {                                                      //~1831I~//~v@@@I~
            Dump.println(e,"UButton:OnClick("+listener.getClass().getSimpleName()+")");               //~1831I~//~v@@@R~
        }                                                      //~1831I~//~v@@@I~
    }                                                              //~1528I~//~v@@@I~
//*******************************************************************                                            //~1528I~//~v@@@I~
	public static void setButtonListener(Button Pbutton,UButtonI Plistener)                 //~1919R~//~v@@@R~
    {                                                              //~1528I~//~v@@@I~
        if (Dump.Y) Dump.println("UButton:setButtonListener listener="+Plistener.getClass().getSimpleName()+",id="+Pbutton.getId());//~v@@@R~
        UButton ubtn=new UButton(Plistener);           //~1528I~   //~v@@@R~
        Pbutton.setOnClickListener(ubtn);                            //~1528I~//~v@@@R~
    }                                                              //~1528I~//~v@@@I~
//*******************************************************************//~v@@@I~
	public static Button bind(View Playout,int Pid,UButtonI Plistener)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UButton:bind id="+Integer.toHexString(Pid));//~v@@@R~
		Button btn=(Button)UView.findViewById(Playout,Pid);        //~v@@@I~
        btn.setAllCaps(false);                                     //~v@@@I~
        if (Plistener!=null)                                       //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("UButton:bind listener="+Plistener.getClass().getSimpleName());//~v@@@R~
	        setButtonListener(btn,Plistener);                      //~v@@@R~
        }                                                          //~v@@@I~
        return btn;                                                //~v@@@I~
    }                                                              //~v@@@I~
//*******************************************************************//~v@@@I~
	public static void bind(Button Pbtn,UButtonI Plistener)        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UButton:getView id="+Integer.toHexString(Pbtn.getId()));//~v@@@I~
	    setButtonListener(Pbtn,Plistener);                         //~v@@@I~
    }                                                              //~v@@@I~
//*******************************************************************//~vac5I~//~1ak3I~
//*set size by dpi                                                 //~vac5I~//~1ak3I~
//*-1:fill_parent, -2:wrap_content                                 //~vac5I~//~1ak3I~
//*******************************************************************//~vac5I~//~1ak3I~
	public static void setHeight(Button Pbtn,int Pwidth,int Pheight,boolean PswDPI)//~vac5R~//~1ak3I~
    {                                                              //~vac5I~//~1ak3I~
        ViewGroup.LayoutParams p=Pbtn.getLayoutParams();           //~vac5R~//~1ak3I~
        if (Dump.Y) Dump.println("UButton:setSize swDPI="+PswDPI+",Button="+Pbtn.toString()+",width="+Pwidth+",height="+Pheight+",dip2pix="+AG.dip2pix+",getLayoutParams=("+p.width+","+p.height+")");//~vac5R~//~1ak3I~
        if (Pwidth!=0)                                             //~vac5I~//~1ak3I~
	        p.width=Pwidth<0 ? Pwidth : (PswDPI ? (int)(Pwidth*AG.dip2pix) : Pwidth);//~vac5R~//~1ak3I~
        if (Pheight!=0)                                            //~vac5I~//~1ak3I~
	        p.height=Pheight<0 ? Pheight : (PswDPI ? (int)(Pheight*AG.dip2pix) : Pheight);//~vac5R~//~1ak3I~
        if (Dump.Y) Dump.println("UButton:setSize layoutParam=("+p.width+","+p.height+")");//~vac5R~//~1ak3I~
        Pbtn.setLayoutParams(p);                                   //~vac5I~//~1ak3I~
    }                                                              //~vac5I~//~1ak3I~
//*******************************************************************//~vac5I~//~1ak3I~
	public static void setSize(View Pview,int PlayoutID,int Pwidth,int Pheight,boolean PswDPI)//~vac5R~//~1ak3I~
    {                                                              //~vac5I~//~1ak3I~
        if (Dump.Y) Dump.println("UButton:setSize View="+Pview.toString()+",layoutID="+Integer.toHexString(PlayoutID)+",width="+Pwidth+",height="+Pheight+",swDPI="+PswDPI);//~vac5R~//~1ak3I~
        ViewGroup vg=(ViewGroup)(Pview.findViewById(PlayoutID));   //~vac5I~//~1ak3I~
        if (vg==null)                                              //~vac5I~//~1ak3I~
        {                                                          //~vac5I~//~1ak3I~
	        if (Dump.Y) Dump.println("UButton:setSeize @@@@ buttons not found id="+Integer.toHexString(PlayoutID));//~vac5R~//~1ak3I~
        	return;                                                //~vac5I~//~1ak3I~
        }                                                          //~vac5I~//~1ak3I~
        int ctr=vg.getChildCount();                                //~vac5I~//~1ak3I~
	    if (Dump.Y) Dump.println("UButton:setSeize child ctr="+ctr);//~vac5I~//~1ak3I~
        for (int ii=0;ii<ctr;ii++)                                 //~vac5I~//~1ak3I~
        {                                                          //~vac5I~//~1ak3I~
        	View v=(View)(vg.getChildAt(ii));                      //~vac5I~//~1ak3I~
		    if (Dump.Y) Dump.println("UButton:setSeize child ii="+ii+",id="+Integer.toHexString(v.getId())+",view="+v.toString());//~vac5R~//~1ak3I~
            if (v instanceof CheckBox)                             //~vac5I~//~1ak3I~
            {                                                      //~vac5I~//~1ak3I~
			    if (Dump.Y) Dump.println("UButton:setSeize child is CheckBox");//~vac5I~//~1ak3I~
            }                                                      //~vac5I~//~1ak3I~
            else                                                   //~vac5I~//~1ak3I~
            if (v instanceof Button)                               //~vac5I~//~1ak3I~
            {                                                      //~vac5I~//~1ak3I~
        		Button btn=(Button)(v);                            //~vac5I~//~1ak3I~
				setHeight(btn,Pwidth,Pheight,PswDPI);              //~vac5R~//~1ak3I~
            }                                                      //~vac5I~//~1ak3I~
            else                                                   //~vac5I~//~1ak3I~
            if (v instanceof ViewGroup)                            //~vac5I~//~1ak3I~
            {                                                      //~vac5I~//~1ak3I~
			    if (Dump.Y) Dump.println("UButton:setSeize child is ViewGroup");//~vac5I~//~1ak3I~
				setSize(v,Pwidth,Pheight,PswDPI);                  //~vac5R~//~1ak3I~
            }                                                      //~vac5I~//~1ak3I~
        }                                                          //~vac5I~//~1ak3I~
    }                                                              //~vac5I~//~1ak3I~
//*******************************************************************//~vac5I~//~1ak3I~
	public static void setSize(View Pview,int Pwidth,int Pheight,boolean PswDPI)//~vac5I~//~1ak3I~
    {                                                              //~vac5I~//~1ak3I~
        if (Dump.Y) Dump.println("UButton:setSize for ViewGroup LayoutView=id="+Integer.toHexString(Pview.getId())+"="+Pview.toString()+",width="+Pwidth+",height="+Pheight+",swDPI="+PswDPI);//~vac5R~//~1ak3I~
        ViewGroup vg=(ViewGroup)Pview;                             //~vac5I~//~1ak3I~
        int ctr=vg.getChildCount();                                //~vac5I~//~1ak3I~
	    if (Dump.Y) Dump.println("UButton:setSeize child ctr="+ctr);//~vac5I~//~1ak3I~
        for (int ii=0;ii<ctr;ii++)                                 //~vac5I~//~1ak3I~
        {                                                          //~vac5I~//~1ak3I~
        	View v=(View)(vg.getChildAt(ii));                      //~vac5I~//~1ak3I~
		    if (Dump.Y) Dump.println("UButton:setSeize for ViewGroup child ii="+ii+"mid="+Integer.toHexString(v.getId())+",view="+v.toString());//~vac5R~//~1ak3I~
            if (v instanceof CheckBox)                             //~vac5I~//~1ak3I~
            {                                                      //~vac5I~//~1ak3I~
			    if (Dump.Y) Dump.println("UButton:setSeize for ViewGroup child is CheckBox");//~vac5R~//~1ak3I~
            }                                                      //~vac5I~//~1ak3I~
            else                                                   //~vac5I~//~1ak3I~
            if (v instanceof Button)                               //~vac5I~//~1ak3I~
            {                                                      //~vac5I~//~1ak3I~
        		Button btn=(Button)(v);                            //~vac5I~//~1ak3I~
				setHeight(btn,Pwidth,Pheight,PswDPI);              //~vac5I~//~1ak3I~
            }                                                      //~vac5I~//~1ak3I~
            else                                                   //~vac5I~//~1ak3I~
            if (v instanceof ViewGroup)                            //~vac5I~//~1ak3I~
            {                                                      //~vac5I~//~1ak3I~
			    if (Dump.Y) Dump.println("UButton:setSeize for ViewGroup child is ViewGroup");//~vac5R~//~1ak3I~
				setSize(v,Pwidth,Pheight,PswDPI);                  //~vac5R~//~1ak3I~
            }                                                      //~vac5I~//~1ak3I~
        }                                                          //~vac5I~//~1ak3I~
    }                                                              //~vac5I~//~1ak3I~
}//class                                                           //~1112I~
