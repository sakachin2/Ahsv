//*CID://+1AhgR~:                                   update#=  113; //+1AhgR~
//****************************************************************************//~@@@1I~
//1Ahg 2020/06/03 help text;string to helptext\
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//1A74 2015/02/23 toast for delayed response(Game not started,already end)//~1A74I~
//1A6B 2015/02/21 IP game title;identify IP and WifiDirect(WD)     //~1A6BI~
//1A06 2013/03/02 remaining ExtraTime was accounted.               //~1A06I~
//107b:121209 (OriginalBug)PartnerFrame did not stop GoTimer       //~v107I~
//1065:121124 PartnerConnection;FinishGame-->EndGame:send EndGEm req and if responsed, allog RemoveGroup.//~v106I~
//            Igs and GMP is FinishGame-->Remove groupe/prisoner   //~v106I~
//            change Menu Item Text for partner connection(send End game request)//~v106I~
//            Isue reply msg and notify "Remove Prisoner" avalable //~v106I~
//v106:1064:121124 beep also on partner connection(no beep even when beeponly for partnerconnection)//~v106I~
//*@@@1 20110430 change GoTimer interval 100-->1000ms              //~@@@1I~
//*@@@1 20110430 FunctionKey support                               //~@@@1I~
//*@@@1 20110430 add "resign" to FinishGame menu                   //~@@@1I~
//****************************************************************************//~@@@1I~
//****************************************************************************//~@@@1I~
package jagoclient.partner;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;                                                 //~1Aa7R~
import com.Ahsv.awt.Frame;
import com.Ahsv.awt.KeyEvent;                                      //~v107R~
import com.Ahsv.awt.KeyListener;                                   //~v107R~

import jagoclient.Dump;
import jagoclient.board.ConnectedBoard;
import jagoclient.board.TimedGoFrame;
import jagoclient.dialogs.HelpDialog;
import jagoclient.sound.JagoSound;


public class PartnerGoFrame extends TimedGoFrame                   //~@@@@I~
		implements KeyListener                                     //~@@@@I~
{                                                                   //~@@@@R~
	PartnerFrame PF;
	
//*********************************************************************//+@@@@I~                                                             //~@@@@I~
    public PartnerGoFrame (PartnerFrame pf,                        //~@@@@I~
//  	int col, int si,int Pgameover,int Pgameover2, int tt,int et, int Pbishop, int Pknight, int Pgameoptions)//~@@@@R~//~1Aa7R~
    	int col, int si,int Pgameover,int Pgameover2, int tt,int et, int Pbishop, int Pknight, int Pgameoptions,//~1Aa7I~
        int PaccepterBishop,int PaccepterKnight)                   //~1Aa7I~
    {                                                              //~@@@@I~
    	super((Frame)pf,AG.frameId_ConnectedGoFrame,AG.resource.getString(R.string.Title_PartnerMatch),//~@@@@R~
//  			col,si,Pgameover,Pgameover2,tt,et,Pbishop,Pknight,Pgameoptions);//~@@@@I~//~1Aa7R~
    			col,si,Pgameover,Pgameover2,tt,et,Pbishop,Pknight,Pgameoptions,PaccepterBishop,PaccepterKnight);//~1Aa7I~
        if (Dump.Y) Dump.println("@@@@PartnerGoFrame@@@@");        //~@@@@I~
		PF=pf;
//  	Col=col; TotalTime=tt; ExtraTime=et; ExtraMoves=em;        //~@@@@R~
//        Col=col; TotalTime=tt; ExtraTime=et; ExtraMoves=AG.EXTRA_MOVE;//~@@@@R~
//        Col=col;                                                 //~@@@@R~
//        BlackTime=TotalTime; WhiteTime=TotalTime;                //~@@@@R~
//  	Handicap=ha;                                               //~@@@@R~
//        BlackRun=0; WhiteRun=0;                                  //~@@@@R~
//        Started=false; Ended=false;                              //~@@@@R~
//        if (Col==1) BlackName=Global.resourceString("You");      //~@@@@R~
//        else BlackName=Global.resourceString("Opponent");        //~@@@@R~
//        if (Col==-1) WhiteName=Global.resourceString("You");     //~@@@@R~
//        else WhiteName=Global.resourceString("Opponent");        //~@@@@R~
        if (Col==1)                                                //~@@@@I~
        {                                                          //~@@@@I~
            BlackName=AG.YourName;                                 //~@@@@R~
            WhiteName=PF.PartnerName;                              //~@@@@R~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
            WhiteName=AG.YourName;                                 //~@@@@R~
            BlackName=PF.PartnerName;                              //~@@@@R~
        }                                                          //~@@@@I~
//        ShowTarget=true; //cursor is drawn by Canvas             //~@@@@R~
		addKeyListener(this);                                      //~@@@1I~
		setVisible(true);
      if (PF.connectionType==IPConnection.NFC_SERVER||PF.connectionType==IPConnection.NFC_CLIENT)//~1A6BI~
		setTitle("NFC"+": "+WhiteName+" vs "+BlackName);            //~1A6BI~
      else                                                         //~1A6BI~
      if (PF.connectionType==IPConnection.WD_SERVER||PF.connectionType==IPConnection.WD_CLIENT)//~1A6BI~
		setTitle("WD"+": "+WhiteName+" vs "+BlackName);             //~1A6BI~
      else                                                         //~1A6BI~
		setTitle(((AG.RemoteStatus & AG.RS_IP)!=0?"IP":"BT")+": "+WhiteName+" vs "+BlackName);//~@@@@I~
		repaint();
	}

	public void doAction (String o)
//  {	if (Global.resourceString("Send").equals(o) || (ExtraSendField && Global.resourceString("ExtraSendField").equals(o)))//~@@@@R~
    {                                                              //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.Resign)))      //~@@@@R~
		{                                                          //~@@@1I~
            PF.resign();   //confirm by dialog                     //~@@@@I~
			return;                                                //~@@@1I~
		}                                                          //~@@@1I~
        else if (o.equals(AG.resource.getString(R.string.Help)))   //~@@@@I~
		{                                                          //~@@@@I~
//      	new HelpDialog(this,R.string.Help_GoFrame);                 //~@@@@R~//+1AhgR~
        	new HelpDialog(this,R.string.Title_PartnerMatch,"GoFrame");//+1AhgI~
		}                                                          //~@@@@I~
		else super.doAction(o);
	}

	public boolean blocked ()
	{	return false;
	}

	public boolean wantsmove ()
	{	return true;
	}

	@Override //ConnectedGoFrame                                   //~@@@@I~
//  public boolean moveset (int i, int j)                          //~@@@@R~
    public boolean moveset (int i, int j,int Ppiece)               //~@@@@I~
//  {   if (!Started || Ended) return false;                       //~@@@@R~//~1A74R~
    {                                                              //~1A74I~
        if (!Started || Ended)                                     //~1A74I~
        {                                                          //~1A74I~
        	if (!Started)                                          //~1A74I~
	        	AView.showToast(R.string.InfoGameNotStarted);      //~1A74I~
            else                                                   //~1A74I~
	        	AView.showToast(R.string.InfoGameAlreadyEnded);    //~1A74I~
        	return false;                                          //~1A74I~
        }                                                          //~1A74I~
        String color;                                              //~@@@@R~
        int enteredExtraTime=0;                                    //~1A06I~
        if (B.maincolor()!=Col) return false;                      //~@@@@R~
        if (B.maincolor()>0) color="b";                            //~@@@@R~
        else color="w";                                            //~@@@@R~
        if (Timer!=null && Timer.isAlive()) alarm();               //~@@@@R~
        int bm=BlackMoves,wm=WhiteMoves;                           //~@@@@R~
        if (Col>0)                                                 //~1A06R~
        {                                                          //~1A06I~
        	if (BlackMoves>0) BlackMoves--;                        //~1A06I~
            enteredExtraTime=resetExtraTime(Col)?1:0;              //~1A06I~
		}                                                          //~1A06I~
        else                                                       //~1A06R~
		{                                                          //~1A06I~
			if (WhiteMoves>0) WhiteMoves--;                        //~1A06I~
            enteredExtraTime=resetExtraTime(Col)?1:0;              //~1A06I~
		}                                                          //~1A06I~
//        if (!PF.moveset(color,i,j,BlackTime-BlackRun,BlackMoves, //~@@@@R~
        if (!PF.moveset(color,Ppiece,i,j,BlackTime-BlackRun,BlackMoves,//~@@@@I~
//          WhiteTime-WhiteRun,WhiteMoves))                        //~@@@@R~//~1A06R~
            WhiteTime-WhiteRun,WhiteMoves,enteredExtraTime))       //~1A06I~
        {   BlackMoves=bm; WhiteMoves=wm;                          //~@@@@R~
            if (Timer.isAlive()) alarm();                          //~@@@@R~
            return false;                                          //~@@@@R~
        }                                                          //~@@@@R~
        return true;                                               //~@@@@R~
    }                                                              //~@@@@R~
//************************************************************************//~@@@@I~
	@Override  //ConnectedGoFrame from ConnectedBoard              //~@@@@I~
	protected void errPass()                                       //~@@@@I~
    {                                                              //~@@@@I~
		movepass();     //to PF.pass(),Out @@pass                  //~@@@@I~
    }                                                              //~@@@@I~
//************************************************************************//~@@@@I~
	public void movepass ()
	{	if (!Started || Ended) return;
		if (B.maincolor()!=Col) return;
      if (Timer!=null)                                             //~@@@@I~
		if (Timer.isAlive()) alarm();
		if (Col>0) { if (BlackMoves>0) BlackMoves--; }
		else { if (WhiteMoves>0) WhiteMoves--; }		
		PF.pass(BlackTime-BlackRun,BlackMoves,
			WhiteTime-WhiteRun,WhiteMoves);
	}
//************************************************************************//~@@@@I~
//*@@pass,@@!pass process                                          //~@@@@R~
    public void dopass ()                                          //~@@@@R~
    {   B.setpass();                                               //~@@@@R~
    }                                                              //~@@@@R~
//************************************************************************//~@@@@I~
    public void notifiedPass()	//notified pass from partner       //~@@@@R~
    {                                                              //~@@@@I~
    	((ConnectedBoard)B).notifiedPass();	//to ConnectedBoard                    //~@@@@R~
    }                                                              //~@@@@I~
                                                             //~@@@@I~


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
	protected void start ()                                        //~@@@@I~
    {                                                              //~@@@@I~
    	super.start();                                             //~@@@@I~
    }                                                              //~@@@@I~
	

	public void result (int b, int w)
	{	PF.out("@@result "+b+" "+w);
	}


	public void yourMove (boolean notinpos)
    {                                                              //~@@@@I~
//        JagoSound.play("stone","click",true);                    //~@@@@R~
    }                                                              //~@@@@I~
//*FunctionKey support                                             //~@@@1I~
	public void keyPressed (KeyEvent e) {}                         //~@@@1I~
	public void keyTyped (KeyEvent e) {}                           //~@@@1I~
	public void keyReleased (KeyEvent e)                           //~@@@1I~
	{                                                              //~@@@@I~
	}                                                              //~@@@1I~
//*****************************************************            //~@@@@I~
    protected void receiveSelected(int Pi,int Pj)                  //~@@@@I~
    {                                                              //~@@@@I~
    	int i=B.S-Pi-1; int j=B.S-Pj-1;                                //~@@@@I~
    	if (Dump.Y) Dump.println("receiveSelected my i="+i+",j="+j);//~@@@@I~
    	((ConnectedBoard)B).receiveSelected(i,j);                  //~@@@@R~
    	JagoSound.play("pieceup",false/*not change to beep when beeponly option is on*/);//~@@@@I~
    }                                                              //~@@@@I~
    public void black(int Pi,int Pj,boolean Preverse,int Ppiece)   //~@@@@R~
    {                                                              //~@@@@I~
    	int i,j;                                                   //~@@@@R~
    //*******************                                          //~@@@@I~
        if (Preverse)                                              //~@@@@I~
    	{	i=B.S-Pi-1;j=B.S-Pj-1;	}                              //~@@@@I~
        else                                                       //~@@@@I~
    	{	i=Pi;j=Pj;	}                                          //~@@@@I~
    	if (Dump.Y) Dump.println("received black my i="+i+",j="+j);//~@@@@I~
		B.P.setPiece(i,j,Ppiece);                                  //~@@@@M~
        B.black(i,j);                                              //~@@@@R~
    }                                                              //~@@@@I~
    public void white(int Pi,int Pj,boolean Preverse,int Ppiece)   //~@@@@R~
    {                                                              //~@@@@I~
    	int i,j;                                                   //~@@@@I~
    //*******************                                          //~@@@@I~
        if (Preverse)                                              //~@@@@I~
    	{	i=B.S-Pi-1;j=B.S-Pj-1;	}                              //~@@@@I~
        else                                                       //~@@@@I~
    	{	i=Pi;j=Pj;	}                                          //~@@@@I~
    	if (Dump.Y) Dump.println("received white my i="+i+",j="+j);//~@@@@I~
		B.P.setPiece(i,j,Ppiece);         //~@@@@I~//~@@@2M~       //~@@@@M~
        B.white(i,j);                                              //~@@@@R~
    }                                                              //~@@@@I~
//*****************************************************            //~@@@@I~
//*from ConnectedBoard:selectPiece                                 //~@@@@I~
//*****************************************************            //~@@@@I~
	@Override //ConnectedGoFrame                                   //~@@@@I~
    protected void pieceSelected(int Pi,int Pj)                    //~@@@@I~
    {                                                              //~@@@@I~
    	PF.sendSelected(Pi,Pj);                                    //~@@@@I~
    }                                                              //~@@@@I~
}

