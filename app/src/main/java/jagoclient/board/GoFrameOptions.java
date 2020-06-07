package jagoclient.board;
import com.Ahsv.AG;
import com.Ahsv.Prop;
import com.Ahsv.R;                                                 //+0530R~
import com.Ahsv.awt.Checkbox;
import com.Ahsv.awt.Frame;

import jagoclient.dialogs.HelpDialog;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.CloseDialog;

//*CID://+@@@@R~:                             update#=    5;       //~@@@@I~

class GoFrameOptions extends CloseDialog                           //~@@@@I~
{                                                                  //~@@@@R~
	Checkbox NoSound,BeepOnly,TimerWarning,ShowLast;               //~@@@@I~//~3114R~
	private ConnectedGoFrame CGF;
	public GoFrameOptions(ConnectedGoFrame f)                                 //~@@@@I~
	{                                                              //~@@@@I~
		super(f,AG.resource.getString(R.string.GoFrameOptions),R.layout.goframeoptions,false/*no modal*/,false/*no reshedule at close*/);//~@@@@I~//~3115R~
        CGF=f;
//        NoSound=new Checkbox(R.id.NoSound);                        //~@@@@I~//~3208R~
//        BeepOnly=new Checkbox(R.id.BeepOnly);                      //~@@@@I~//~3208R~
//        TimerWarning=new Checkbox(R.id.TimerWarning);              //~@@@@I~//~3208R~
//        ShowLast=new Checkbox(R.id.ShowLast);                      //~@@@@I~//~3208R~
//        ButtonAction.init(this,0,R.id.OK);                         //~@@@@I~//~3208R~
//        ButtonAction.init(this,0,R.id.Cancel);                     //~@@@@I~//~3208R~
//        ButtonAction.init(this,0,R.id.Help);                       //~@@@@I~//~3208R~
        NoSound=new Checkbox(this,R.id.NoSound);                   //~3208I~
        BeepOnly=new Checkbox(this,R.id.BeepOnly);                 //~3208I~
        TimerWarning=new Checkbox(this,R.id.TimerWarning);         //~3208I~
        ShowLast=new Checkbox(this,R.id.ShowLast);                 //~3208I~
        new ButtonAction(this,0,R.id.OK);                     //~3208I~
        new ButtonAction(this,0,R.id.Cancel);                 //~3208I~
        new ButtonAction(this,0,R.id.Help);                   //~3208I~
        NoSound.setState((AG.Options & AG.OPTIONS_NOSOUND)!=0);       //~@@@@I~
        BeepOnly.setState((AG.Options & AG.OPTIONS_BEEP_ONLY)!=0);     //~@@@@I~
        TimerWarning.setState((AG.Options & AG.OPTIONS_TIMER_WARNING)!=0);//~@@@@I~
        ShowLast.setState((AG.Options & AG.OPTIONS_SHOW_LAST)!=0);     //~@@@@I~
		show();                                                    //~@@@@I~
	}                                                              //~@@@@I~
//*****************************************                        //~@@@@R~
    private void getOptions()                                      //~@@@@I~
    {                                                              //~@@@@I~
        if (NoSound.getState())	    	AG.Options|=AG.OPTIONS_NOSOUND;    		else AG.Options&=~AG.OPTIONS_NOSOUND;//~@@@@I~//~3114R~
        if (BeepOnly.getState())		AG.Options|=AG.OPTIONS_BEEP_ONLY;  		else AG.Options&=~AG.OPTIONS_BEEP_ONLY;//~@@@@I~//~3114R~
        if (TimerWarning.getState())	AG.Options|=AG.OPTIONS_TIMER_WARNING;  	else AG.Options&=~AG.OPTIONS_TIMER_WARNING;//~@@@@I~//~3114R~
        if (ShowLast.getState())		AG.Options|=AG.OPTIONS_SHOW_LAST;  		else AG.Options&=~AG.OPTIONS_SHOW_LAST;//~@@@@I~//~3114R~
        Prop.putPreference(AG.PKEY_OPTIONS,AG.Options);            //~@@@@I~
    }                                                              //~@@@@I~
//*****************************************                        //~@@@@I~
	public void doAction (String o)                                //~@@@@I~
	{	if (o.equals(AG.resource.getString(R.string.OK)))          //~@@@@I~
		{                                                          //~@@@@I~
            getOptions();                                          //~@@@@I~
			setVisible(false); dispose();                          //~@@@@I~
		}                                                          //~@@@@I~
    	else if (o.equals(AG.resource.getString(R.string.Cancel))) //~@@@2I~//~@@@@I~
		{                                                          //~@@@@I~
			setVisible(false); dispose();                          //~@@@@I~
		}                                                          //~@@@@I~
    	else if (o.equals(AG.resource.getString(R.string.Help)))   //~@@@2I~//~@@@@I~
		{                                                          //~@@@@I~
        	new HelpDialog(null,R.string.Help_GoFrameOptions);          //~@@@@I~//~3208R~
		}                                                          //~@@@@I~
	}                                                              //~@@@@I~
}
