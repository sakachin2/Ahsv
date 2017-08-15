//*CID://+1AabR~:                             update#=   44;       //~1Aa7R~//+1AabR~
//**************************************************************************//~1Aa7I~
//1Aab 2015/04/22 1Aa7 for local game                              //+1AabI~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//**************************************************************************//~1Aa7I~
package jagoclient;                                                 //~@@@@R~

import com.Ahsv.AG;
import com.Ahsv.R;
import com.Ahsv.awt.Checkbox;
import com.Ahsv.awt.Frame;
import jagoclient.Global;
import jagoclient.board.GoFrame;
import jagoclient.partner.GameQuestion;                            //~@@@@I~
import jagoclient.gui.ButtonAction;
import jagoclient.gui.FormTextField;                             //~2C26R~//~@@@@R~


public class LocalGameQuestion extends GameQuestion                //~@@@@R~
{                                                                  //~@@@@I~
    FormTextField Bishop,Knight,TotalTime,ExtraTime,Gameover,Gameover2;//~@@@@R~
    Checkbox Reflectable,Reselectable,MoveFirst;                   //~@@@@R~
	MainFrame MF;                                                  //~@@@@R~
	private boolean swStartGame;                                   //~@@@@I~
	public LocalGameQuestion(MainFrame Pframe)                     //~@@@@R~
	{                                                              //~@@@@R~
        super(Pframe,AG.resource.getString(R.string.Title_GameQuestion));//~@@@@R~
        MF=Pframe;                                                 //~@@@@I~
        setGameData();                                               //~@@@@I~
        new ButtonAction(this,R.string.StartGame,R.id.Request);  //Request//~@@@@I~
        new ButtonAction(this,0,R.id.Cancel);  //Cancel       //~@@@@I~
        new ButtonAction(this,0,R.id.Help);  //Help           //~@@@@I~
        new ButtonAction(this,0,R.id.ChangeBKButton);              //~1Aa7I~
        setDismissActionListener(this/*DoActionListener*/);        //~@@@@I~
		validate();
		show();
	}
//**************************************************************	//~@@@@R~
	public void doAction (String o)
    {                                                              //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.StartGame)))   //~@@@@R~
		{                                                          //~@@@@R~
        	if (!getGameData())                                    //~@@@@I~
            	return;                                            //~@@@@I~
            if (!chkLocalBK()) //chk piece count of local 1st/2nd player//+1AabI~
            	return;                                            //+1AabI~
			setVisible(false); dispose();
			swStartGame=true;                                      //~@@@@R~
		}
        else                                                       //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.ActionDismissDialog)))	//modal but no inputWait//~@@@@R~
		{   			//callback from Dialog after currentLaypout restored//~@@@@I~
        	if (swStartGame)                                       //~@@@@I~
				new LocalFrame(this);                              //~@@@@I~
		}                                                          //~@@@@I~
		else super.doAction(o);
	}
}

