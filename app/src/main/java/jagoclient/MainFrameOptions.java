//*CID://+1Ak2R~:                             update#=   19;       //~1Ak2R~
//*****************************************************************************//~1A13I~
//1ak2 2021/09/04 access external audio file                       //~1Ak2I~
//1Aho 2020/06/06 HelpDialog by html for MainFrameOptions          //~1AhoI~
//1Ad7 2015/07/20 Canvas/UiThread TraceOption was not effective if OptionDialog is opened//~1Ad7I~
//1A6A 2015/02/20 Another Trace option if (Dump.C) for canvas drawing, (Dump.T) for UiThread//~1A6AI~
//1A13 2013/03/10 1touch option                                    //~1A13I~
//*****************************************************************************//~1A13I~
package jagoclient;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.Ahsv.AG;
import com.Ahsv.Prop;
import com.Ahsv.R;                                                 //~1Ad7R~
import com.Ahsv.awt.Checkbox;
import com.Ahsv.awt.Frame;

import com.btmtest.dialog.HelpDialog;                              //~1AhoR~
import com.btmtest.utils.UMediaStore;

import jagoclient.gui.ButtonAction;
import jagoclient.gui.CloseDialog;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.FormTextField;

//*CID://+@@@@R~:                             update#=    5;       //~@@@@I~

//class MainFrameOptions extends CloseDialog                           //~@@@@I~//~3114R~//~1Ad7R~
public class MainFrameOptions extends CloseDialog                  //~1Ad7I~
{                                                                  //~@@@@R~
    private static final int HELP_TITLEID=R.string.MainFrameOptions;//~1AhoI~
    private static final String HELPFILE="MainOptions";            //~1AhoI~
                                                                   //~1AhoI~
	private Checkbox Coordinate,BigTimer,Trace;               //~@@@@I~    //~3114R~//~3119R~
	private Checkbox cbBGM;                                        //~1Ak2I~
	private TextView tvBGM;                                        //~1Ak2I~
	private Checkbox TraceC,TraceT;                                //~1A6AI~
	private Checkbox OneTouch;                                     //~1A13I~
	private FormTextField YourName;                                             //~3114I~//~3119R~
	private DoActionListener dal;                                  //~3119R~
    private boolean changeBoard;
    private String strUriBGM,titleBGM;                             //~1Ak2R~
    MainFrame MF;//~3119I~
	public MainFrameOptions(MainFrame f)                                 //~@@@@I~//~3114R~//~3119R~
	{                                                              //~@@@@I~
//  	super(f,AG.resource.getString(R.string.MainFrameOptions),R.layout.mainframeoptions,false/*modal*/,false/*modalInput*/);//~@@@@I~//~3114R~//~3115R~//~1A6AR~
    	super(f,AG.resource.getString(R.string.MainFrameOptions),  //~1A6AI~
				((AG.layoutMdpi/*mdpi and height or width <=320*/ && !AG.portrait)//~1A6AR~
                  ? R.layout.mainframeoptions_land_mdpi : R.layout.mainframeoptions),//~1A6AI~
				false/*modal*/,false/*modalInput*/);               //~1A6AI~
        MF=f;
		dal=(DoActionListener)f;                                                           //~3114I~//~3119R~
        BigTimer=new Checkbox(this,R.id.BigTimer);                 //~3208I~
        cbBGM=new Checkbox(this,R.id.cbBGM);                       //~1Ak2I~
        Coordinate=new Checkbox(this,R.id.Coordinate);             //~3208I~
        OneTouch=new Checkbox(this,R.id.OneTouch);                 //~1A13I~
        Trace=new Checkbox(this,R.id.Trace);                       //~3208I~
        TraceT=new Checkbox(this,R.id.TraceT);                     //~1A6AI~
        TraceC=new Checkbox(this,R.id.TraceC);                     //~1A6AI~
        BigTimer.setState((AG.Options & AG.OPTIONS_BIG_TIMER)!=0); //~3114I~
        cbBGM.setState((AG.Options & AG.OPTIONS_BGM)!=0);          //~1Ak2I~
        Coordinate.setState((AG.Options & AG.OPTIONS_COORDINATE)!=0);//~3114I~
        OneTouch.setState((AG.Options & AG.OPTIONS_1TOUCH)!=0);    //~1A13I~
        YourName=new FormTextField(this,R.id.YourName,AG.YourName);//~3208I~
        tvBGM=(TextView)AG.findViewById(R.id.tvBGM);                          //~1Ak2I~
	    strUriBGM=Prop.getPreference(AG.PKEY_BGM_STRURI,"");       //~1Ak2I~
	    titleBGM=Prop.getPreference(AG.PKEY_BGM_TITLE,"");         //~1Ak2I~
        if (titleBGM.compareTo("")==0)                             //~1Ak2I~
			tvBGM.setText(R.string.tvBGMNotSet);                   //~1Ak2I~
        else                                                       //~1Ak2I~
			tvBGM.setText(titleBGM);                               //~1Ak2I~
                                                                   //~3114I~
	    if (AG.isDebuggable)                                          //~v107R~//~3114I~
        {                                                          //~3114I~
        	Trace.setState((AG.Options & AG.OPTIONS_TRACE)!=0);    //~3114R~
        	Trace.setVisibility(Trace.androidCheckBox,View.VISIBLE);//~3114R~
        	TraceC.setState((AG.Options & AG.OPTIONS_TRACE_CANVAS)!=0);//~1A6AI~
        	TraceC.setVisibility(TraceC.androidCheckBox,View.VISIBLE);//~1A6AI~
        	TraceT.setState((AG.Options & AG.OPTIONS_TRACE_UITHREAD)!=0);//~1A6AI~
        	TraceT.setVisibility(TraceT.androidCheckBox,View.VISIBLE);//~1A6AI~
        }                                                          //~3114I~
        new ButtonAction(this,0,R.id.btnBGM);                      //~1Ak2I~
        new ButtonAction(this,0,R.id.OK);                     //~3208I~
        new ButtonAction(this,0,R.id.Cancel);                 //~3208I~
        new ButtonAction(this,0,R.id.Help);                   //~3208I~
        setDismissActionListener(this/*DoActionListener*/);        //~3119I~
		show();                                                    //~@@@@I~
	}                                                              //~@@@@I~
//*****************************************                        //~@@@@R~
    private void getOptions()                                      //~@@@@I~
    {                                                              //~@@@@I~
        AG.YourName=YourName.getText().toString();                            //~3114I~
        if (BigTimer.getState())                                   //~3118R~
			AG.Options|=AG.OPTIONS_BIG_TIMER;                      //~3118I~
  		else                                                       //~3118I~
            AG.Options&=~AG.OPTIONS_BIG_TIMER;                     //~3118I~
        if (cbBGM.getState())                                      //~1Ak2I~
			AG.Options|=AG.OPTIONS_BGM;                            //~1Ak2I~
  		else                                                       //~1Ak2I~
            AG.Options&=~AG.OPTIONS_BGM;                           //~1Ak2I~
        if (Coordinate.getState())                                 //~3118R~
			AG.Options|=AG.OPTIONS_COORDINATE;                     //~3118I~
		else                                                       //~3118I~
			AG.Options&=~AG.OPTIONS_COORDINATE;                    //~3118I~
        if (OneTouch.getState())                                   //~1A13I~
			AG.Options|=AG.OPTIONS_1TOUCH;                         //~1A13I~
		else                                                       //~1A13I~
			AG.Options&=~AG.OPTIONS_1TOUCH;                        //~1A13I~
	    if (AG.isDebuggable)                                       //~3114I~
        {                                                          //~3114I~
        	int old=AG.Options;                                    //~3119I~
	        if (Trace.getState())                                  //~3118R~
				AG.Options|=AG.OPTIONS_TRACE;                      //~3118I~
  			else                                                   //~3118I~
				AG.Options&=~AG.OPTIONS_TRACE;                     //~3118I~
            if ((old & AG.OPTIONS_TRACE)!=(AG.Options & AG.OPTIONS_TRACE))	//redraw required//~3119I~
	            Dump.setOption((AG.Options & AG.OPTIONS_TRACE)!=0);     //~3114I~//~3119R~
	        if (TraceC.getState())                                 //~1A6AI~
				AG.Options|=AG.OPTIONS_TRACE_CANVAS;               //~1A6AI~
  			else                                                   //~1A6AI~
				AG.Options&=~AG.OPTIONS_TRACE_CANVAS;              //~1A6AI~
            if ((old & AG.OPTIONS_TRACE_CANVAS)!=(AG.Options & AG.OPTIONS_TRACE_CANVAS))	//redraw required//~1A6AI~
	            Dump.C=(AG.Options & AG.OPTIONS_TRACE_CANVAS)!=0; //~1A6AI~
	        if (TraceT.getState())                                 //~1A6AI~
				AG.Options|=AG.OPTIONS_TRACE_UITHREAD;             //~1A6AI~
  			else                                                   //~1A6AI~
				AG.Options&=~AG.OPTIONS_TRACE_UITHREAD;            //~1A6AI~
            if ((old & AG.OPTIONS_TRACE_UITHREAD)!=(AG.Options & AG.OPTIONS_TRACE_UITHREAD))	//redraw required//~1A6AI~
	            Dump.T=(AG.Options & AG.OPTIONS_TRACE_UITHREAD)!=0;//~1A6AI~
        }                                                          //~3114I~
        Prop.putPreference(AG.PKEY_OPTIONS,AG.Options);            //~@@@@I~
        Prop.putPreference(AG.PKEY_YOURNAME,AG.YourName);          //~3114I~
    }                                                              //~@@@@I~
//*****************************************                        //~@@@@I~
	public void doAction (String o)                                //~@@@@I~
	{	if (o.equals(AG.resource.getString(R.string.OK)))          //~@@@@I~
		{                                                          //~@@@@I~
        	int old=AG.Options;                                    //~3119I~
            getOptions();                                          //~@@@@I~
			setVisible(false); dispose();                          //~@@@@I~
            if ((old & AG.OPTIONS_COORDINATE)!=(AG.Options & AG.OPTIONS_COORDINATE))	//redraw required//~3119I~
            	changeBoard=true;                                  //~3119R~
	        Prop.putPreference(AG.PKEY_BGM_STRURI,strUriBGM);            //~@@@@I~//~1Ak2I~
	        Prop.putPreference(AG.PKEY_BGM_TITLE,titleBGM);        //~1Ak2I~
            setBGM(old,AG.Options);                                //~1Ak2R~
		}                                                          //~@@@@I~
    	else if (o.equals(AG.resource.getString(R.string.Cancel))) //~@@@2I~//~@@@@I~
		{                                                          //~@@@@I~
			setVisible(false); dispose();                          //~@@@@I~
		}                                                          //~@@@@I~
    	else if (o.equals(AG.resource.getString(R.string.Help)))   //~@@@2I~//~@@@@I~
		{                                                          //~@@@@I~
//        	new HelpDialog(null,R.string.Help_MainFrameOptions);          //~@@@@I~//~3114R~//~3208R~//~1AhoR~
	    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE).showDlg();//~1AhoI~
		}                                                          //~@@@@I~
    	else if (o.equals(AG.resource.getString(R.string.btnBGM))) //~1Ak2I~
		{                                                          //~1Ak2I~
	    	UMediaStore.changeBGM(this);                           //~1Ak2R~
		}                                                          //~1Ak2I~
        else if (o.equals(AG.resource.getString(R.string.ActionDismissDialog)))	//modal but no inputWait//~3119R~
		{                                                          //~3119I~
        //*after framelayout restored                              //~3119I~
        	if (Dump.Y) Dump.println("MainFrameOptions dismissDoAction");//~3119I~
            if (changeBoard)                                       //~3119I~
	        	dal.doAction(AG.resource.getString(R.string.ActionChangeCoordinate));//~3119R~
		}                                                          //~3119I~
	}                                                              //~@@@@I~
//*****************************************                        //~1Ad7I~
//**from AG                                                        //~1Ad7I~
//*****************************************                        //~1Ad7I~
	public static void initialOptions()                            //~1Ad7R~
	{                                                              //~1Ad7I~
	    if (AG.isDebuggable)                                       //~1Ad7I~
        {                                                          //~1Ad7I~
	        Dump.C=(AG.Options & AG.OPTIONS_TRACE_CANVAS)!=0;      //~1Ad7I~
	        Dump.T=(AG.Options & AG.OPTIONS_TRACE_UITHREAD)!=0;    //~1Ad7I~
        }                                                          //~1Ad7I~
	}                                                              //~1Ad7I~
//*****************************************                        //~1Ak2I~
	private void setBGM(int Pold,int Pnew)                         //~1Ak2I~
	{                                                              //~1Ak2I~
        if (Dump.Y) Dump.println("MainFrameOptions.setBGM option old="+Integer.toHexString(Pold)+",new="+Integer.toHexString(Pnew));//~1Ak2I~
        UMediaStore.stopBGM(false/*swPause*/);                     //+1Ak2I~
	    if ((Pnew & AG.OPTIONS_BGM)!=0)                            //+1Ak2R~
        	UMediaStore.startBGM();                                //~1Ak2I~
	}                                                              //~1Ak2I~
//*****************************************                        //~1Ak2I~
	public void setBGMTitle(String Ptitle,String PstrUri)          //~1Ak2R~
	{                                                              //~1Ak2I~
        if (Dump.Y) Dump.println("MainFrameOptions.setBGMTitle title="+Ptitle+",strUri="+PstrUri);//~1Ak2R~
		tvBGM.setText(Ptitle);                                     //~1Ak2I~
        strUriBGM=PstrUri;                                         //~1Ak2I~
        titleBGM=Ptitle;                                           //~1Ak2I~
	}                                                              //~1Ak2I~
}
