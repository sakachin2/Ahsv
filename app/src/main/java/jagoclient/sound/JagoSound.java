//*CID://+1032R~:                             update#=    8;       //~@@@@I~//+1032R~
//*************************************************************************//+1032I~
//1032 2013/03/07 gameover sound                                   //+1032I~
//*************************************************************************//+1032I~
package jagoclient.sound;

import jagoclient.Global;
//import rene.util.sound.SoundList;                                //~1327R~
import com.Ahsv.AG;
import com.Ahsv.rene.util.sound.SoundList;                       //~1327I~//~@@@@R~

public class JagoSound
{	static SoundList SL=new SoundList();
//*always use simple sound(2nd parameter)***************************//~@@@@I~
	static synchronized public void play 
		(String file, String simplefile, boolean beep)
//  {   if (Global.getParameter("nosound",true)) return;           //~@@@@R~
    {                                                              //~@@@@R~
        if ((AG.Options & AG.OPTIONS_NOSOUND)!=0) return;          //~@@@@I~
//  	if (Global.getParameter("beep",true))                      //~@@@@R~
        if ((AG.Options & AG.OPTIONS_BEEP_ONLY)!=0)                //~@@@@I~
		{	if (beep) SoundList.beep();
			return;
		}
//        if (Global.getParameter("simplesound",true)) file=simplefile;//~@@@@R~
        file=simplefile;                                           //~@@@@I~
        if (file.equals("")) return;                               //~@@@@R~
//      if (SL.busy()) return;                                     //~@@@@R~//+1032R~
        if (SL.busy(file)) return;                                 //+1032I~
//      if (Global.getJavaVersion()>=1.3)                          //~@@@@R~
            SL.play("/jagoclient/au/"+file+".wav");                //~@@@@R~
//      else                                                       //~@@@@R~
//          SL.play("/jagoclient/au/"+file+".au");                 //~@@@@R~
	}
    static public void play (String file)                          //~@@@@R~
//  {   if (SL.busy()) return;                                     //~@@@@R~//+1032R~
    {                                                              //+1032I~
        if (SL.busy(file)) return;                                 //+1032I~
        play(file,"wip",false);                                    //~@@@@R~
    }                                                              //~@@@@R~
//********************************************************         //~@@@@I~
    static public void play (String file,boolean beep)             //~@@@@I~
    {                                                              //~@@@@I~
        play(file,file,beep);                                      //~@@@@I~
    }                                                              //~@@@@I~
}

