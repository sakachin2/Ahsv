//*CID://+v@@@R~:                             update#=   82;       //~v@@@R~
//**********************************************************************//~v105I~
//get View & set Button OnClickListener                            //~v@@@R~
//**********************************************************************//~v105I~
package com.btmtest.gui;                                           //~v@@@R~
                                                                   //~v@@@I~
import android.view.View;
import android.widget.Button;
//~v@@@I~
import jagoclient.Dump;                                              //+v@@@R~
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
}//class                                                           //~1112I~
