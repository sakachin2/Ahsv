//*CID://+v@@@R~:                             update#=  178;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.app.Dialog;
import android.view.ViewGroup;
import android.widget.Button;

import com.Ahsv.AG;
import com.btmtest.gui.UButton;
import com.Ahsv.R;                                                     //~v@@@I~

import jagoclient.Dump;
import jagoclient.MainFrame;
import com.btmtest.dialog.HelpDialog;

public class MenuDlgConnectButton                                  //+v@@@R~
	extends Dialog                                                 //~1Ah0I~//~v@@@I~
    implements  UButton.UButtonI              //~v@@@I~            //~1Ah0I~//~v@@@I~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Connect;             //~v@@@I~
    private static final int HELP_TITLEID=TITLEID;                 //~v@@@I~
    private static final String HELPFILE="MenuDlgConnectButton";   //+v@@@R~
                                                                   //~v@@@I~
	private static final int LAYOUTID=R.layout.menuconnect;            //~1Ah0I~//~v@@@I~
	private MainFrame MF;                                          //~v@@@I~
//**********************************                               //~1211I~//~v@@@I~
//*                                                                //~1211R~//~v@@@I~
//**********************************                               //~1211I~//~v@@@I~
	public MenuDlgConnectButton()                                  //+v@@@R~
    {                                                              //~1A2jI~//~v@@@I~
    	super(AG.context);
        initAndroid();                                             //~v@@@I~
    }                                                              //~1A2jI~//~v@@@I~
	private void initAndroid()                   //~1211I~ //~@@@@R~     //~@@@2R~//~1A2jR~//~v@@@I~
    {                                                              //~1211I~//~v@@@I~
    	ViewGroup layoutView=(ViewGroup)(AG.inflater.inflate(LAYOUTID,null));//~1Ah0I~//~v@@@R~
        setButtons(layoutView);                                    //~v@@@I~
        setContentView(layoutView);                                //~1Ah0I~//~v@@@I~
    }                                                              //~1211I~//~v@@@I~
//**********************************                               //~v@@@I~
	private void setButtons(ViewGroup Playout)                                       //~@@@2I~//~v@@@I~
    {                                                              //~@@@2I~//~v@@@I~
        if (Dump.Y) Dump.println("MenuDlgConnectButton.setButtons");      //~@@@2I~//+v@@@R~
		UButton.bind(Playout,R.id.LocalGame,this);  //~1Ah0R~   //~v@@@I~
		UButton.bind(Playout,R.id.BTNFCButton,this);            //~v@@@I~
		UButton.bind(Playout,R.id.WiFiNFCButton,this);             //~v@@@R~
		UButton.bind(Playout,R.id.RemoteGame,this);                //~v@@@R~
		UButton.bind(Playout,R.id.RemoteIP,this);                  //~v@@@R~
		UButton.bind(Playout,R.id.WiFiDirectButton,this);          //~v@@@R~
		UButton.bind(Playout,R.id.Help,this);                      //~v@@@R~
		UButton.bind(Playout,R.id.Close,this);                     //~v@@@R~
    }                                                              //~@@@2I~//~v@@@I~
//**********************************                               //~v@@@I~
	public static void show(MainFrame Pparent)                     //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnectButton.show");     //+v@@@R~
        MenuDlgConnectButton dlg=new MenuDlgConnectButton();       //+v@@@R~
        dlg.MF=Pparent;                                                //~v@@@I~
        dlg.show();                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~//~1Ah0I~//~v@@@I~
    @Override //UButtonI                                           //~v@@@I~//~1Ah0I~//~v@@@I~
    public void onClickButton(Button Pbutton)                   //~v@@@R~//~1Ah0I~//~v@@@I~
	{                                                              //~v@@@I~//~1Ah0I~//~v@@@I~
    	boolean rc=true;	//close                                           //~v@@@I~//~1Ah0I~//~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg:onClickButton"+Pbutton.getText());//~v@@@I~//~1Ah0I~//~v@@@I~
    	try                                                        //~v@@@I~//~1Ah0I~//~v@@@I~
        {                                                          //~v@@@I~//~1Ah0I~//~v@@@I~
        	int id=Pbutton.getId();                                //~v@@@I~//~1Ah0I~//~v@@@I~
        	switch(id)                                             //~v@@@I~//~1Ah0I~//~v@@@I~
            {                                                      //~v@@@I~//~1Ah0I~//~v@@@I~
            case R.id.Close:                                      //~v@@@I~//~1Ah0R~//~v@@@I~
                break;                                             //~v@@@I~//~1Ah0I~//~v@@@I~
            case R.id.Help:                                        //~v@@@I~
                rc=false;                                          //~v@@@I~
                help();                                            //~v@@@I~
                break;                                             //~v@@@I~
            default:                                               //~v@@@I~
				String btnname=Pbutton.getText().toString();                  //~v@@@I~
                MF.doAction(btnname);                              //~v@@@I~
            }                                                      //~v@@@I~//~1Ah0I~//~v@@@I~
        }                                                          //~v@@@I~//~1Ah0I~//~v@@@I~
        catch(Exception e)                                         //~v@@@I~//~1Ah0I~//~v@@@I~
        {                                                          //~v@@@I~//~1Ah0I~//~v@@@I~
            Dump.println(e,"ProgDlg:onClick:"+Pbutton.getText());    //~v@@@I~//~1Ah0I~//~v@@@I~
        }                                                          //~v@@@I~//~1Ah0I~//~v@@@I~
        if (rc)                                                    //~v@@@I~
        	dismiss();                                             //~v@@@I~
    }                                                              //~v@@@I~//~1Ah0I~//~v@@@I~
//**********************************                               //~v@@@I~
    private void help()                                            //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnectButton.help");     //+v@@@R~
    	HelpDialog.showDlg(HELP_TITLEID,HELPFILE);      //~v@@@R~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
