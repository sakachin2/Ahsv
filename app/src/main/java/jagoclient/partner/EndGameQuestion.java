//*CID://+1A1dR~:                             update#=   11;       //~1A1dR~
//*****************************************************************************//~1A1cI~
//1A1d 2013/03/17 popup at partner when decliened resign           //~1A1dI~
//*****************************************************************************//~1A1cI~
package jagoclient.partner;

import com.Ahsv.AG;
import com.Ahsv.R;                                                 //+1A1dR~

import jagoclient.Dump;
import jagoclient.Global;
import jagoclient.LocalFrame;
import jagoclient.dialogs.Question;

/**
Question to end the game, or decline that.
*/

public class EndGameQuestion extends Question
{	PartnerFrame G;
	LocalFrame LG;                                                    //~@@@@I~
    boolean resign;                                                //~@@@@I~
	public EndGameQuestion (PartnerFrame g)
//  {	super(g,Global.resourceString("End_the_game_"),Global.resourceString("End"),g,true); G=g;//~@@@@R~
    {                                                              //~@@@@R~
    	super(g,AG.resource.getString(R.string.End_the_game),AG.resource.getString(R.string.Title_EndGameQuestion),g,true,false); G=g;//~@@@@I~
		show();
	}
	public EndGameQuestion (PartnerFrame g,String Pmsg,boolean Presign)//~@@@@I~
	{	super(g,Pmsg,AG.resource.getString(R.string.Title_EndGameQuestion),g,true,false);//~@@@@R~
		G=g;                                                       //~@@@@I~
    	resign=Presign;                                            //~@@@@R~
		show();                                                    //~@@@@I~
	}                                                              //~@@@@I~
	public EndGameQuestion (LocalFrame g)                            //~@@@@I~
	{                                                              //~@@@@I~
		super(g,Global.resourceString("End_the_game_"),Global.resourceString("End"),g,true,false);//~@@@@I~
		LG=g;                                                     //~@@@@I~
		show();                                                    //~@@@@I~
	}                                                              //~@@@@I~
	public void tell (Question q, Object o, boolean f)
	{	q.setVisible(false); q.dispose();
    	if (Dump.Y) Dump.println("EndGameQuestion:tell() accept="+f+",resign="+resign);//~@@@@R~
      if (LG!=null)                                                //~@@@@I~
      {                                                            //~@@@@I~
	    if (f) LG.doendgame();                                     //~@@@@I~
		else LG.declineendgame();                                  //~@@@@I~
      }                                                            //~@@@@I~
      else                                                         //~@@@@I~
//      if (f) G.doendgame();                                      //~@@@@R~
        if (f)                                                     //~@@@@I~
        	if (resign)                                            //~@@@@I~
	 			G.doresign();                                      //~@@@@R~
            else                                                   //~@@@@I~
	 			G.doendgame();                                     //~@@@@I~
//  	else G.declineendgame();                                   //~1A1dR~
    	else                                                       //~1A1dR~
        {                                                          //~1A1dR~
        	if (!resign)                                           //~1A1dR~
    	     G.declineendgame();                                   //~1A1dR~
        }                                                          //~1A1dR~
	}
	public boolean close ()
	{	G.declineendgame();
		return true;
	}
}

