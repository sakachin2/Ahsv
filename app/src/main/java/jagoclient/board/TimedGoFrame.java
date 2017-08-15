//*CID://+1Aa7R~:                                   update#=  139; //+1Aa7R~
//****************************************************************************//~@@@1I~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //+1Aa7I~
//1A50 2014/10/27 mdpi & tablet support                            //~1A50I~
//1034 2013/03/07 time sign color did'nt change for remote game    //~1034I~
//1A07 2013/03/02 distinguish color for extratime                  //~1A07I~
//1A06 2013/03/02 remaining ExtraTime was accounted.               //~1A06I~
//*split from PartnerGoFrame for common to LocalGoFrame  *         //~@@@2I~
//****************************************************************************//~@@@1I~
package jagoclient.board;

import android.view.View;

import com.Ahsv.AG;
import com.Ahsv.URunnable;                                         //~@@@2R~
import com.Ahsv.URunnableI;                                        //~@@@2R~
import com.Ahsv.awt.Frame;
import jagoclient.Dump;
import jagoclient.LocalFrame;
import jagoclient.board.GoTimer;                                   //~@@@2I~


/**
A subclass of GoFrame, which has a different menu layout.
Moreover, it contains a method to add a comment from an
external source (a distributor).
*/

public class TimedGoFrame extends ConnectedGoFrame                 //~@@@2R~
	implements TimedBoard, URunnableI                              //~@@@2R~
{//~@@@2I~
 	protected String BlackName,WhiteName;                          //~@@@2R~
	protected int BlackTime,WhiteTime,BlackMoves,WhiteMoves;       //~@@@2M~
	protected int BlackRun,WhiteRun;                               //~@@@2R~
	protected int TotalTime,ExtraTime,ExtraMoves;                  //~@@@2R~
	protected GoTimer Timer;                                       //~@@@2M~
	protected long CurrentTime;                                    //~@@@2R~
	protected boolean Started,Ended;                               //~@@@2I~
	protected boolean TimerInTitle,BigTimer;                       //~@@@2I~
	private	int cc;	//current color                            //~@@@2I~
//	private	int prevColor;	//previous color                       //~@@@2I~
//    private LocalFrame LF;                                         //~@@@2R~
//    private PartnerFrame PF;                                       //~@@@2R~
    private boolean extraB,extraW;	//timeover of TotalTime        //~@@@2I~
//**************************************************************** //~@@@1I~
	public TimedGoFrame (Frame pf,int Presid,String s,             //~@@@2R~
//  	int col, int si,int Pgameover,int Pgameover2, int tt,int et, int Pbishop, int Pknight, int Pgameoptions)//~@@@2I~//+1Aa7R~
    	int col, int si,int Pgameover,int Pgameover2, int tt,int et, int Pbishop, int Pknight, int Pgameoptions,//+1Aa7I~
        int PaccepterBishop,int PaccepterKnight)                   //+1Aa7I~
	{                                                              //~@@@2I~
//  	super(Presid,s,si,Pgameover,Pgameover2,Pbishop,Pknight,Pgameoptions,col);//~@@@@R~//~@@@2R~//+1Aa7R~
    	super(Presid,s,si,Pgameover,Pgameover2,Pbishop,Pknight,Pgameoptions,col,PaccepterBishop,PaccepterKnight);//+1Aa7I~
        if (Dump.Y) Dump.println("@@@@TimedGoFrame@@@@");      //~@@@1I~//~@@@2R~
        if (pf instanceof LocalFrame)                            //~@@@2M~
        {                                                          //~@@@2I~2
//	        LF=(LocalFrame)pf;                                     //~@@@2R~
        	swLocalGame=true;                                      //~@@@2I~
        }                                                          //~@@@2I~
//        else                                                       //~@@@2I~
//	        PF=(PartnerFrame)pf;                                   //~@@@2R~
        TotalTime=tt; ExtraTime=et; ExtraMoves=AG.EXTRA_MOVE;      //~@@@2I~
        BlackTime=TotalTime;                                       //~@@@2M~
        WhiteTime=TotalTime;                                       //~@@@2M~
        BlackRun=0; WhiteRun=0;                                    //~@@@2I~
        TimerInTitle=(AG.Options & AG.OPTIONS_TIMER_IN_TITLE)!=0;     //~@@@2I~
        BigTimer=(AG.Options & AG.OPTIONS_BIG_TIMER)!=0;              //~@@@2I~
//        prevColor=col;                                             //~@@@2I~
	}
//************************************************                 //~@@@2I~
	protected void start ()                                        //~@@@2I~
	{	Started=true; Ended=false;                                 //~@@@2I~
		CurrentTime=System.currentTimeMillis();                    //~@@@2I~
		BlackRun=0; WhiteRun=0;                                    //~@@@2I~
		BlackMoves=-1; WhiteMoves=-1;                              //~@@@2I~
//      if ((AG.Options & AG.OPTIONS_BIG_TIMER)!=0)                //~@@@2R~
    	Timer=new GoTimer(this,1000);        //same as IgsGoFrame  //~@@@2I~
        URunnable.setRunFunc(this,0/*deley*/,null/*parmobj*/,0/*parmint*/);//~@@@2R~
	}                                                              //~@@@2I~
//************************************************                 //~@@@2I~
	public void runFunc(Object Pparmobj,int Pparmint)              //~@@@2R~
    {                                                              //~@@@2I~
    	drawInitialCaptured();                                     //~@@@2I~
    }                                                              //~@@@2I~
//************************************************                 //~@@@2I~
	public void settimes (int bt, int bm, int wt, int wm)          //~@@@2I~
	{	BlackTime=bt; BlackRun=0;                                  //~@@@2I~
		WhiteTime=wt; WhiteRun=0;                                  //~@@@2I~
		WhiteMoves=wm; BlackMoves=bm;                              //~@@@2I~
		CurrentTime=System.currentTimeMillis();                    //~@@@2I~
        if (Dump.Y)Dump.println("TGF:settimes by receive @@move bt="+bt+",wt="+wt);//~@@@2I~
		settitle();                                                //~@@@2I~
	}                                                              //~@@@2I~
//************************************************                 //~@@@2I~
	public void settimesLocalColorSwitch(int Pcolor/*last moved color*/)//~@@@2R~
	{                         
		long now=System.currentTimeMillis();//elapsed time after swithed//~@@@2I~
		if (Pcolor>0)                                              //~@@@2I~
			BlackMoves--;	//for extratime period max move is 1   //~@@@2I~
        else                                                       //~@@@2I~
			WhiteMoves--;                                          //~@@@2I~
		alarm();	//evaluate timeover                             //~@@@2I~
		if (Pcolor>0)         //black moved,next is white          //~@@@2R~
        {                                                          //~@@@2I~
            if (extraB)  //in extratime period                     //~@@@2I~
            {                                                      //~@@@2I~
                BlackTime=ExtraTime;                               //~@@@2I~
                BlackRun=0;	//for settitle                         //~@@@2I~
                BlackMoves=ExtraMoves;                             //~@@@2I~
            }                                                      //~@@@2I~
            CurrentTime=now-WhiteRun*1000;//elapsed time after swithed//~@@@2R~
        }                                                          //~@@@2I~
        else	//next is black                                    //~@@@2R~
        {                                                          //~@@@2I~
        	if (extraW)                                            //~@@@2R~
            {                                                      //~@@@2I~
                WhiteTime=ExtraTime;                               //~@@@2R~
                WhiteRun=0;	//for settitle                         //~@@@2R~
                WhiteMoves=ExtraMoves;                             //~@@@2R~
            }                                                      //~@@@2I~
			CurrentTime=now-BlackRun*1000;//elapsed time after swithed//~@@@2R~
        }                                                          //~@@@2I~
		lastbeep=0;		//for black and white                      //~1A07I~
		settitle();	//display full extratime                       //~@@@2I~
        if (Dump.Y) Dump.println("ColorSwitch old="+Pcolor+",extraW="+extraW+",extraB="+extraB+",blackRun="+BlackRun+",whiteRun="+WhiteRun);//~@@@2I~
	}                                                              //~@@@2I~
//************************************************************************//~@@@@I~//~@@@2M~
	public void alarm ()                                           //~@@@@I~//~@@@2M~
	{	long now=System.currentTimeMillis();                       //~@@@@I~//~@@@2M~
        if (swLocalGame)	                                       //~@@@2I~
			cc=B.currentColor();                                          //~@@@@I~//~@@@2I~
        else		//PartnerGoFrame                               //~@@@2I~
        	cc=B.maincolor();                                      //~@@@2R~
//      if (B.maincolor()>0) BlackRun=(int)((now-CurrentTime)/1000);//~@@@@R~//~@@@2M~
        if (cc>0) BlackRun=(int)((now-CurrentTime)/1000);          //~@@@@I~//~@@@2M~
		else WhiteRun=(int)((now-CurrentTime)/1000);               //~@@@@I~//~@@@2M~
//      if (Dump.Y) Dump.println("alarm color="+cc+",elapsedW="+WhiteRun+",elapsedB="+BlackRun);//~@@@2R~
      if (TotalTime!=0)	//timeover was set                         //~@@@@R~//~@@@2M~
      {                                                            //~@@@@I~//~@@@2M~
//  	if (Col>0 && BlackTime-BlackRun<0)                         //~@@@@I~//~@@@2M~
    	if (cc>0 && BlackTime-BlackRun<0)                          //~@@@@I~//~@@@2M~
		{	if (BlackMoves<0)                                      //~@@@@I~//~@@@2M~
            {   BlackMoves=ExtraMoves;                             //~@@@@I~//~@@@2M~
                BlackTime=ExtraTime; BlackRun=0;                   //~@@@@I~//~@@@2M~
                CurrentTime=now;                                   //~@@@@I~//~@@@2M~
                extraB=true;                                       //~@@@2I~
                beep();	//entered extratime                        //~1A07I~
            }                                                      //~@@@@I~//~@@@2M~
            else if (BlackMoves>0)                                 //~@@@@R~//~@@@2M~
//          {   new Message(this,Global.resourceString("Black_looses_by_time_"));//~@@@@I~//~@@@2R~
            {                                                      //~@@@2I~
            	super.gameoverMessage(-1/*white is winner*/,3);    //~@@@2I~
				Timer.stopit();                                    //~@@@@I~//~@@@2M~
			}                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
            {   BlackMoves=ExtraMoves;                             //~@@@@I~//~@@@2M~
                BlackTime=ExtraTime; BlackRun=0;                   //~@@@@I~//~@@@2M~
                CurrentTime=now;                                   //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
		}                                                          //~@@@@I~//~@@@2M~
//  	else if (Col<0 && WhiteTime-WhiteRun<0)                    //~@@@@I~//~@@@2M~
    	else if (cc<0 && WhiteTime-WhiteRun<0)                     //~@@@@I~//~@@@2M~
		{	if (WhiteMoves<0)                                      //~@@@@I~//~@@@2M~
            {   WhiteMoves=ExtraMoves;                             //~@@@@I~//~@@@2M~
                WhiteTime=ExtraTime; WhiteRun=0;                   //~@@@@I~//~@@@2M~
                CurrentTime=now;                                   //~@@@@I~//~@@@2M~
                extraW=true;                                       //~@@@2I~
                beep();	//entered extratime                        //~1A07I~
            }                                                      //~@@@@I~//~@@@2M~
            else if (WhiteMoves>0)                                 //~@@@@R~//~@@@2M~
//    		{	new Message(this,Global.resourceString("White_looses_by_time_"));//~@@@@I~//~@@@2R~
    		{                                                      //~@@@2R~
            	super.gameoverMessage(1/*black is winner*/,3);     //~@@@2I~
				Timer.stopit();                                    //~@@@@I~//~@@@2M~
			}                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
            {   WhiteMoves=ExtraMoves;                             //~@@@@I~//~@@@2M~
                WhiteTime=ExtraTime; WhiteRun=0;                   //~@@@@I~//~@@@2M~
                CurrentTime=now;                                   //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
		}                                                          //~@@@@I~//~@@@2M~
      }//TotalTime!=0                                              //~@@@@I~//~@@@2R~
//      if (Dump.Y) Dump.println("alarm color=extraW="+extraW+",extraB="+extraB+",blackTime="+BlackTime+",whiteTime="+WhiteTime);//~@@@2R~
		settitle();                                                //~@@@@I~//~@@@2M~
	}                                                              //~@@@@I~//~@@@2M~
//************************************************                 //~@@@2I~
	int lastbeep=0;                                                //~@@@2I~
	public void beep (int s)                                       //~@@@2I~
//  {	if (s<0 || !Global.getParameter("warning",true)) return;   //~@@@2R~
    {                                                              //~@@@2I~
    	if (s<0 || (AG.Options & AG.OPTIONS_TIMER_WARNING)==0) return;//~@@@2I~
        if (s==60)                                                 //~1A07I~
        {                                                          //~1A07I~
			getToolkit().beep();                                   //~1A07I~
        }                                                          //~1A07I~
		else if (s<31 && s!=lastbeep)                              //~@@@2I~
		{	if (s%10==0)                                           //~@@@2I~
			{	getToolkit().beep();                               //~@@@2I~
				lastbeep=s;                                        //~@@@2I~
			}                                                      //~@@@2I~
		}                                                          //~@@@2I~
	}                                                              //~@@@2I~
//************************************************                 //~1A07I~
	public void beep ()                                            //~1A07I~
    {                                                              //~1A07I~
    	if ((AG.Options & AG.OPTIONS_TIMER_WARNING)==0) return;    //~1A07I~
		getToolkit().beep();                                       //~1A07I~
	}                                                              //~1A07I~
	public void addtime (int s)                                    //~@@@2I~
//  {   if (Col>0) BlackTime+=s;                                   //~@@@2R~
    {                                                              //~@@@2I~
        if (swLocalGame)                                           //~@@@2I~
			cc=B.currentColor();                                   //~@@@2I~
        else		//PartnerGoFrame                               //~@@@2I~
        	cc=Col;                                                //~@@@2I~
        if (cc>0) BlackTime+=s;                                    //~@@@2I~
		else WhiteTime+=s;                                         //~@@@2I~
		settitle();                                                //~@@@2I~
	}                                                              //~@@@2I~
//************************************************                 //~@@@2M~
	String OldS="";                                                //~@@@2M~
	protected void settitle ()                                     //~@@@2M~
	{	String S;                                                  //~@@@2M~
      	if (swLocalGame)                                           //~@@@2M~
			cc=B.currentColor();                                   //~@@@2M~
        else                                                       //~@@@2M~
//      	cc=Col;                                                //~@@@2M~//~1034R~
			cc=B.maincolor();                                      //~1034I~
//        if (BigTimer)                                            //~@@@2R~
//            S=WhiteName+" "+formmoves(WhiteMoves)+" - "+         //~@@@2R~
//            BlackName+" "+formmoves(BlackMoves);                 //~@@@2R~
//        else                                                     //~@@@2R~
//            S=WhiteName+" "+formtime(WhiteTime-WhiteRun)+" "+formmoves(WhiteMoves)+" - "+//~@@@2R~
//            BlackName+" "+formtime(BlackTime-BlackRun)+" "+formmoves(BlackMoves);//~@@@2R~
        S="("+AG.WhiteSign+") "+WhiteName+" - ("+AG.BlackSign+") "+BlackName;//~@@@2I~
        MatchTitle=S;                                              //~@@@2R~
		if (!S.equals(OldS))                                       //~@@@2M~
//  	{	if (!TimerInTitle) TL.setText(S);                      //~@@@2M~
		{                                                          //~@@@2M~
//  		if ((AG.Options & AG.OPTIONS_TIMER_IN_TITLE)==0) TL.setText(S);//~1A50R~
//  		else setTitle(S);                                      //~1A50R~
			TL.setVisibility(TL.textView,View.GONE);               //~1A50I~
			OldS=S;                                                //~@@@2M~
		}                                                          //~@@@2M~
		if (BigTimer)                                              //~@@@2M~
//  	{	BL.setTime(WhiteTime-WhiteRun,BlackTime-BlackRun,WhiteMoves,BlackMoves,Col);//~@@@2M~
    	{                                                          //~@@@2M~
//  		BL.setTime(WhiteTime-WhiteRun,BlackTime-BlackRun,WhiteMoves,BlackMoves,cc);//~@@@2M~//~1A07R~
    		BL.setTime(WhiteTime-WhiteRun,BlackTime-BlackRun,WhiteMoves,BlackMoves,cc,extraB,extraW);//~1A07I~
			BL.repaint();                                          //~@@@2M~
		}                                                          //~@@@2M~
      if (swLocalGame)                                             //~@@@2M~
      {                                                            //~@@@2M~
		if (cc>0) beep(BlackTime-BlackRun);                        //~@@@2M~
		if (cc<0) beep(WhiteTime-WhiteRun);                        //~@@@2M~
      }                                                            //~@@@2M~
      else                                                         //~@@@2M~
      {                                                            //~@@@2M~
//  	if (Col>0 && B.maincolor()>0) beep(BlackTime-BlackRun);    //~@@@2M~//~1034R~
//  	if (Col<0 && B.maincolor()<0) beep(WhiteTime-WhiteRun);    //~@@@2M~//~1034R~
    	if (Col>0 && cc>0) beep(BlackTime-BlackRun);               //~1034I~
    	if (Col<0 && cc<0) beep(WhiteTime-WhiteRun);               //~1034I~
      }                                                            //~@@@2M~
	}                                                              //~@@@2M~
                                                                   //~@@@2M~
	char form[]=new char[32];                                      //~@@@2M~
                                                                   //~@@@2M~
//******************************************************************//~@@@2I~
	String formmoves (int m)                                       //~@@@2M~
	{	if (m<0) return "";                                        //~@@@2M~
		form[0]='(';                                               //~@@@2M~
		int n=OutputFormatter.formint(form,1,m);                   //~@@@2M~
		form[n++]=')';                                             //~@@@2M~
		return new String(form,0,n);                               //~@@@2M~
	}                                                              //~@@@2M~
                                                                   //~@@@2M~
//******************************************************************//~@@@2I~
	String formtime (int sec)                                      //~@@@2M~
	{	int n=OutputFormatter.formtime(form,sec);                  //~@@@2M~
		return new String(form,0,n);                               //~@@@2M~
	}                                                              //~@@@2M~
//******************************************************************//~@@@2I~
	public void addothertime (int s)                               //~@@@2I~
//  {	if (Col>0) WhiteTime+=s;                                   //~@@@2R~
    {                                                              //~@@@2I~
        if (swLocalGame)                                           //~@@@2I~
			cc=B.currentColor();                                   //~@@@2I~
        else		//PartnerGoFrame                               //~@@@2I~
        	cc=Col;                                                //~@@@2I~
    	if (cc>0) WhiteTime+=s;                                    //~@@@2I~
		else BlackTime+=s;                                         //~@@@2I~
		settitle();                                                //~@@@2I~
	}                                                              //~@@@2I~
//******************************************************************//~@@@2I~
	public void doscore (int Pcolor/*winner*/)                            //~@@@2I~
	{                                                              //~@@@2I~
    	int winner;                                                //~@@@2I~
		B.score();                                                 //~@@@2I~
        if (Timer!=null)                                           //~@@@2I~
			Timer.stopit();                                        //~@@@2R~
		Ended=true;                                                //~@@@2I~
        super.gameoverMessage(Pcolor,4);                           //~@@@2I~
	}                                                              //~@@@2I~
//********************************************************         //~@@@2M~
	@Override	//CGF                                              //~@@@2I~
    protected void gameovered()                                    //~@@@2M~
	{                                                              //~@@@2M~
    	B.score();                                                 //~@@@2M~
        stopBoard(false);	//false:stop thread at close           //~@@@2R~
	}                                                              //~@@@2M~
    public boolean stopBoard(Boolean Pstopthread)     //called from also AMain at destroy//~@@@2R~
	{                                                              //~@@@2M~
    	boolean rc=false;                                          //~@@@2I~
		if (Timer!=null && Timer.isAlive())                        //~@@@2R~
        {                                                          //~@@@2I~
			 Timer.stopit();     //stop BigTimer                   //~@@@2I~
             rc=true;	//need 1 sec wait                          //~@@@2I~
        }                                                          //~@@@2I~
        if (B!=null)	                                           //~@@@2M~
        {                                                          //~@@@2I~
        	if (Pstopthread)                                       //~@@@2I~
	        	B.stopThread();   	//from AMain when destroy      //~@@@2I~
            else                                                   //~@@@2I~
	        	B.inactivateCanvas();                              //~@@@2R~
        }                                                          //~@@@2I~
        return rc;//~@@@2M~
	}                                                              //~@@@2M~
////******************************************************************//~@@@2R~
////*PartnetrGoFrame will override to send to partner              //~@@@2R~
////******************************************************************//~@@@2R~
//    protected boolean moveset(String color,int i,int j,int bt,int bm,int wt,int wm){return true;}//~@@@2R~
//******************************************************************//~1A06I~
    public boolean resetExtraTime(int Pcolor/*your color*/)        //~1A06I~
    {                                                              //~1A06I~
    	boolean rc;                                                //~1A06I~
    //**********************                                       //~1A06I~
        if (Dump.Y) Dump.println("TimedGoFrame resetExtraTime color="+Pcolor);//~1A06I~
		long now=System.currentTimeMillis();//elapsed time after swithed//~1A06I~
        if (Pcolor>0)	//you are black                            //~1A06I~
        {                                                          //~1A06I~
        	rc=extraB;                                             //~1A06I~
            if (rc)                                                //~1A06I~
            {                                                      //~1A06I~
                BlackMoves=ExtraMoves;                             //~1A06I~
                BlackTime=ExtraTime; BlackRun=0;                   //~1A06I~
                CurrentTime=now;                                   //~1A06I~
            }                                                      //~1A06I~
        }                                                          //~1A06I~
        else                                                       //~1A06I~
        {                                                          //~1A06I~
        	rc=extraW;                                             //~1A06I~
            if (rc)                                                //~1A06I~
            {                                                      //~1A06I~
                WhiteMoves=ExtraMoves;                             //~1A06I~
                WhiteTime=ExtraTime; WhiteRun=0;                   //~1A06I~
                CurrentTime=now;                                   //~1A06I~
            }                                                      //~1A06I~
        }                                                          //~1A06I~
        if (Dump.Y) Dump.println("TimedGoFrame resetExtraTime rc="+rc+",bt="+BlackTime+",wt="+WhiteTime);//~1A06I~
        return rc;                                                 //~1A06I~
    }                                                              //~1A06I~
}
