//*CID://+1AabR~:                                   update#=  112; //+1AabR~
//****************************************************************************//~@@@1I~
//1Aab 2015/04/22 1Aa7 for local game                              //+1AabI~
//1A50 2014/10/27 mdpi & tablet support                            //~1A50I~
//****************************************************************************//~@@@1I~
package jagoclient;

import com.Ahsv.AG;
import com.Ahsv.AView;                                         //~v107R~//~@@@@R~
import com.Ahsv.R;
import com.Ahsv.awt.Frame;
import com.Ahsv.awt.KeyEvent;                                      //~v107R~
import com.Ahsv.awt.KeyListener;                                   //~v107R~
import jagoclient.Global;
import jagoclient.board.Board;
import jagoclient.board.TimedGoFrame;
import jagoclient.dialogs.HelpDialog;
import jagoclient.dialogs.Message;
import jagoclient.gui.ButtonAction;
import jagoclient.partner.GameQuestion;
import jagoclient.sound.JagoSound;

/**
The go frame for partner connections.
*/

//public class LocalGoFrame extends TimedGoFrame                   //~@@@@R~
public class LocalGoFrame extends TimedGoFrame                     //~@@@@I~
	implements KeyListener                                                  //~@@@1I~
{                                                                  //~@@@@R~
	LocalFrame PF;
//    int Handicap;                                                //~@@@@R~
	
//*********************************************************************//~@@@@I~
//from LocalGoFrame                                                //~@@@@I~
//*********************************************************************//~@@@@I~
//    public PartnerGoFrame (PartnerFrame pf, String s,            //~@@@@R~
//  	int col, int si, int tt, int et, int em, int ha)           //~@@@@R~
	public LocalGoFrame (LocalFrame pf,                     //~@@@@M~
//  	int col, int si,int Pgameover,int Pgameover2, int tt,int et, int Pbishop, int Pknight, int Pgameoptions)//~@@@@R~//+1AabR~
    	int col, int si,int Pgameover,int Pgameover2, int tt,int et, int Pbishop, int Pknight, int Pgameoptions,//+1AabI~
        int Pbishop2,int Pknight2)                                 //+1AabI~
//  {	super(s,si,Global.resourceString("End_Game"),Global.resourceString("Count"),false,false);//~@@@@R~
    {                                                              //~@@@@I~
//        super(s,si,Global.resourceString("End_Game"),Global.resourceString("Count"),Pgameover,Pgameover2,Pbishop,Pknight,Pgameoptions,col);//~@@@@R~
        super(pf,AG.frameId_ConnectedGoFrame,AG.resource.getString(R.string.Title_LocalViewer),//~@@@@R~
//  		col,si,Pgameover,Pgameover2,tt,et,Pbishop,Pknight,Pgameoptions);//~@@@@R~//+1AabR~
    		col,si,Pgameover,Pgameover2,tt,et,Pbishop,Pknight,Pgameoptions|GameQuestion.GAMEOPT_LOCALGAME,Pbishop2,Pknight2);//+1AabI~
		PF=pf;
		if (col==1)                                                //~@@@@I~
        {                                                          //~@@@@I~
        	BlackName=AG.YourName;                                 //~@@@@I~
        	WhiteName=AG.LocalOpponentName;                        //~@@@@R~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
        	WhiteName=AG.YourName;                                 //~@@@@I~
        	BlackName=AG.LocalOpponentName;                        //~@@@@R~
        }                                                          //~@@@@I~
		addKeyListener(this);                                      //~@@@1I~
		setVisible(true);
		setTitle(AG.resource.getString(R.string.Title_LocalViewer)+": "+WhiteName+" vs "+BlackName);//~1A50R~
		repaint();
	}

	public void doAction (String o)
    {                                                              //~@@@@I~
		if (false);                                                //~@@@@I~
        else if (o.equals(AG.resource.getString(R.string.Resign))) //~@@@@I~
		{                                                          //~@@@1I~
//			PF.out("resign");                                      //~@@@1I~
			PF.endgame();                                          //~@@@1I~
			return;                                                //~@@@1I~
		}                                                          //~@@@1I~
        else if (o.equals(AG.resource.getString(R.string.Help)))   //~@@@@I~
		{                                                          //~@@@@I~
        	new HelpDialog(this,R.string.Help_GoFrame);                 //~@@@@R~
		}                                                          //~@@@@I~
		else super.doAction(o);
	}

	public boolean blocked ()
	{	return false;
	}

//*****************************************************            //~@@@@I~
	public boolean wantsmove ()
    {                                                              //~@@@@R~
    	if (Ended)                                                 //~@@@@I~
        {                                                          //~@@@@I~
	    	if (Dump.Y) Dump.println("LocalGoFrame wantsmove return true after Ended");//~@@@@I~
        	return true;	//reques callback moveset to ignore movemouse()//~@@@@I~
        }                                                          //~@@@@I~
		return false;  //no request ConnectedBoard to call CGF.moveset(i,j) to get move event//~@@@@I~
	}
//*from ConnectedBoard**************************************       //~@@@@I~
	public boolean moveset(int i,int j)                            //~@@@@I~
    {                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("LocalGoFrame moveset return false");//~@@@@I~
    	return false;	//nop movemouse                            //~@@@@I~
    }                                                              //~@@@@I~
//*****************************************************            //~@@@@I~
//*from ConnectedBoard:selectPiece                                 //~@@@@I~
//*****************************************************            //~@@@@I~
	@Override //ConnectedGoFrame                                   //~@@@@I~
    protected void pieceMoved(int Pcolor/*last moved color*/)      //~@@@@R~
    {                                                              //~@@@@R~
    	settimesLocalColorSwitch(Pcolor);                          //~@@@@I~
    	JagoSound.play("piecedown",false/*not change to beep when beeponly option is on*/);//~@@@@I~
    }                                                              //~@@@@I~
	@Override //ConnectedGoFrame                                   //~@@@@I~
    protected void pieceSelected(int Pi,int Pj)         //~@@@@R~
    {                                                              //~@@@@I~
    	JagoSound.play("pieceup",false/*not change to beep when beeponly option is on*/);//~@@@@I~
    }                                                              //~@@@@I~
//*****************************************************            //~@@@@I~
	public void doclose ()
	{	setVisible(false); dispose();
		if (Timer!=null && Timer.isAlive()) Timer.stopit();        //~v107I~
		PF.toFront();
		PF.boardclosed(this);
		PF.PGF=null;
	}
//********************************************************         //~@@@@I~
//*from PartnerFrame:adjourn                                       //~@@@@I~
//********************************************************         //~@@@@I~
	public void acceptClosed()                                     //~@@@@I~
	{	setVisible(false); dispose();                              //~@@@@I~
		if (Timer!=null && Timer.isAlive()) Timer.stopit();        //~@@@@I~
		PF.toFront();                                              //~@@@@I~
		PF.PGF=null;                                               //~@@@@I~
	}                                                              //~@@@@I~

	int maincolor ()
	{	return B.maincolor();
	}


	public void yourMove (boolean notinpos)
    {                                                              //~@@@@I~
        JagoSound.play("stone","click",true);                      //~@@@@I~
    }                                                              //~@@@@I~
//*FunctionKey support                                             //~@@@1I~
	public void keyPressed (KeyEvent e) {}                         //~@@@1I~
	public void keyTyped (KeyEvent e) {}                           //~@@@1I~
	public void keyReleased (KeyEvent e)                           //~@@@1I~
	{                                                              //~@@@@I~
	}                                                              //~@@@1I~
//************************************************************************//~@@@@I~
	protected void start ()                                        //~@@@@I~
    {                                                              //~@@@@I~
    	super.start();                                             //~@@@@I~
    }                                                              //~@@@@I~
//************************************************************************//~@@@@I~
	@Override                                                      //~@@@@I~
	protected void errPass()                                       //~@@@@I~
    {                                                              //~@@@@I~
		B.setpass();                                               //~@@@@I~
    }                                                              //~@@@@I~
}

