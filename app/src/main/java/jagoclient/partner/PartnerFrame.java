//*CID://+1AecR~:                                   update#=  177; //~1AecR~
//****************************************************************************//~@@@1I~
//1Aec 2015/07/26 set connection type for Server                   //~1AecI~
//1AbM 2015/07/03 BT:short sleep for BT disconnet fo exchange @@@@end and @@@@!end//~1AbMI~
//1Abv 2015/06/19 BT:dismiss dialog at boardQuestion for also BT like as 1Abj//~1AbvI~
//1Abj 2015/06/15 NFCBT:dismiss dialog at boardQuestion            //~1AbjR~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//1A8i 2015/03/05 set connection type to PartnerFrame title        //~1A8iI~
//1A8fk2015/03/01 display remote IP address                        //~1A8fI~//~1A8iM~
//1A85 2015/02/25 close each time partnerframe for IP Connection   //~1A85I~
//1A76 2015/02/23 modal dialog(BoardQuestion) could not be posted if requested Game when IPConnection dialog is showen//~1A76I~
//                Dismiss IPConnection dialog se Current is Frame after BoardQuestion dialog started.//~1A76I~
//                By isCurrentDialog() is off,ActionEvent did not post(countdown latch).//~1A76I~
//                insert sleep.                                    //~1A76I~
//1A6B 2015/02/21 IP game title;identify IP and WifiDirect(WD)     //~1A6BI~
//1A6z 2015/02/20 PartnerFrame NPE at adjourn before board created by tcp delay//~1A6zI~
//1A6y 2015/02/20 dismiss ipconnection dialog when boardquestiondialog up by opponent game requested//~1A6yI~
//1A6s 2015/02/17 move NFC starter from WifiDirect dialog to MainFrame//~1A6sI~
//1A6k 2015/02/15 re-open IPConnection/BTConnection dialog when diconnected when dislog is opened.//~1A53I~
//1A53 2014/11/01 SayDialig title;missing partnername for game requester//~1A53I~
//1A39 2013/04/22 Over 1A37,out dummy response for !move           //~1A39I~
//                @@move b-->,<--@@!move b,@@!!move b-->,<--@@move w,...//~1A39I~
//1A38 2013/04/22 (BUG)When response(@@!move) delayed,accept next select.//~1A38I~
//                Then move confuse to move to old dest from new selected.//~1A38I~
//1A37 2013/04/20 avoid TCP delay bynagle algolism(witre-write-read is bad)//~1A37I~
//                So no send @@select                              //~1A37I~
//                PartnerFrame                                     //~1A37I~
//1A2k 2013/04/03 (Bug)when partner closed after resign+close adjourn NPE by PGF=null//~1A2eI~//~1A2kI~
//1A25 2013/03/25 StringParser:consideration quotation             //~1A25I~
//1A09 2013/03/03 use not stone but piecedown at yourMove()        //~1A09I~//~1A06I~
//                yourMove is not called for LGF, nop for PGF      //~1A09I~//~1A06I~
//                pieceMoved call piecedown for LGF,no sound for PGF//~1A09I~//~1A06I~
//1A06 2013/03/02 remaining ExtraTime was accounted.               //~1A06I~
//101h 2013/02/09 TcpNoDelay for socket                            //~v101I~
//1081:121213 partner-match:when server requested endgame,Block=true remains and//~v108I~
//            on next match on same connection(same PartnerFrame),client setstone is blocked//~v108I~
//            (                                                    //~v108I~
//            endgame by interrupt @@endgame set Block=true after call EndGameQuestion//~v108I~
//            EndGameQuestion call doendgame/declineendgame(set Block=false)//~v108I~
//            modal dialog is protected to return before reply,so results Block=true)//~v108I~
//            )                                                    //~v108I~
//1071:121204 partner connection using Bluetooth SPP               //~v107I~
//*@@@1 20110430 FunctionKey support                               //~@@@1I~
//****************************************************************************//~@@@1I~
package jagoclient.partner;

import jagoclient.CloseConnection;
import jagoclient.Dump;
import jagoclient.Global;
import jagoclient.board.ConnectedBoard;
import jagoclient.dialogs.Message;
import jagoclient.gui.CloseFrame;
import jagoclient.sound.JagoSound;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.Ahsv.AG;
import com.Ahsv.ProgDlg;
import com.Ahsv.R;
import com.Ahsv.Utils;

import rene.util.parser.StringParser;
import wifidirect.DialogNFC;
import wifidirect.DialogNFCBT;

//class PartnerMove                                                //~@@@2R~
//{   public String Type;                                          //~@@@2R~
//    public int P1,P2,P3,P4,P5,P6;                                //~@@@2R~
//    public PartnerMove (String type, int p1, int p2, int p3, int p4, int p5, int p6)//~@@@2R~
//    {   Type=type; P1=p1; P2=p2; P3=p3; P4=p4; P5=p5; P6=p6;     //~@@@2R~
//    }                                                            //~@@@2R~
//    public PartnerMove (String type, int p1, int p2, int p3, int p4)//~@@@2R~
//    {   Type=type; P1=p1; P2=p2; P3=p3; P4=p4;                   //~@@@2R~
//    }                                                            //~@@@2R~
//}                                                                //~@@@2R~

/**
The partner frame contains a simple chat dialog and a button
to start a game or restore an old game. This class contains an
interpreters for the partner commands.
*/

public class PartnerFrame extends CloseFrame
//    implements KeyListener                                         //~@@@1I~//~@@@2R~
//{	BufferedReader In;                                             //~v107R~
{	                                                               //~v107I~
    private static final String CONTYPE_PREFIX=";";                //~1A6BI~
	public static final String CONN_TITLE_IP="IP ";                //~1A8iI~
	public static final String CONN_TITLE_WD="WD ";                //~1A8iI~
	public static final String CONN_TITLE_BT="BT ";                //~1A8iI~
	public static final String CONN_TITLE_NFC="NFC ";              //~1A8iI~
	public static final String CONN_TITLE_NFC_WD="NFC-WD ";        //~1AecI~
	public static final String CONN_TITLE_NFC_BT="NFC-BT ";        //~1AecI~
//    public static int test=2;                                      //~@@@9R~//~1A6BR~
	protected BufferedReader In;                                   //~v107I~
  	protected PrintWriter Out;                                      //~v107I~
	Socket Server;
	public PartnerThread PT;                                    //~v107I~//~@@@2R~
	public PartnerGoFrame PGF;
	boolean Serving;
	boolean Block;
	String Dir;
  public                                                           //~1A8iI~
    String PartnerName;                                            //~@@@2I~
    boolean GameRequester;                                         //~@@@2R~
    private int sendiSelected,sendjSelected;                       //~1A37I~
//    private OutputStream OS;                                       //~@@@9R~//~1A6BR~
//    public static InputStream IS;                                   //~@@@9I~//~1A6BR~
	public String myName="";                        //~v1D3R~      //~1A8iI~
    public int connectionType;                                     //~1A6BI~
    private String titleName;                                      //~1A8iI~

	public PartnerFrame (String name, boolean serving)
//  {   super(name);                                               //~@@@2R~
    {                                                              //~@@@2I~
       super(0/*layouid,have no view*/,name);                      //~@@@2I~
		if (Dump.Y) Dump.println("jagoclient:@@PartnerFrame@@");   //~@@@2I~
		Serving=serving;
		PGF=null;
		Block=false;
		Dir="";
//        Moves=new ListClass();                                   //~@@@2R~
//        seticon("iconn.gif");                                    //~@@@2R~
//        addKeyListener(this);                                      //~@@@1I~//~@@@2R~
//      AG.aPartnerFrameIP=this;                                   //~@@@2I~//~1A85R~
        AG.aPartnerFrame=this;                                     //~1A85I~
        AG.aPartnerFrameIP=null;                                   //~1A85I~
        titleName=name;                                            //~1A8iI~
	}
	public PartnerFrame (String name, boolean serving,int Pconnectiontype)//~1A6BI~
    {                                                              //~1A6BI~
        this(name,serving);                                        //~1A6BI~
		if (Dump.Y) Dump.println("jagoclient:@@PartnerFrame@@");   //~1A6BI~
		connectionType=Pconnectiontype;                            //~1A6BI~
	}                                                              //~1A6BI~
	public PartnerFrame (String name, boolean serving,String Ptitleprefix)//~1A8iI~
	{                                                              //~1A8iI~
		this(name,serving);                                        //~1A8iI~
        setTitle(Ptitleprefix+titleName);                          //~1A8iI~
	}                                                              //~1A8iI~

	public boolean connect (String s, int p)
	{	if (Dump.Y) Dump.println("Starting partner connection="+s+",port="+p);//~v101R~
		try
		{	Server=new Socket(s,p);
            Server.setTcpNoDelay(true);                            //~v101R~
//         if (test==1)                                              //~@@@9I~//~1A6BR~
//         {                                                         //~@@@9I~//~1A6BR~
//            Out=new PrintWriter(                                   //~@@@9I~//~1A6BR~
//                Server.getOutputStream(),true);                    //~@@@9I~//~1A6BR~
//            In=new BufferedReader(new InputStreamReader(           //~@@@9I~//~1A6BR~
//                Server.getInputStream()));                         //~@@@9I~//~1A6BR~
//            OS=Server.getOutputStream();                           //~@@@9I~//~1A6BR~
//            IS=Server.getInputStream();                           //~@@@9I~//~1A6BR~
//         }                                                         //~@@@9I~//~1A6BR~
//         else                                                      //~@@@9I~//~1A6BR~
//         {                                                         //~@@@9I~//~1A6BR~
			Out=new PrintWriter(
				new DataOutputStream(Server.getOutputStream()),true);
			In=new BufferedReader(new InputStreamReader(
			    new DataInputStream(Server.getInputStream())));
//         }                                                         //~@@@9I~//~1A6BR~
		}
		catch (Exception e)
		{                                                          //~v101R~
            Dump.println(e,"PartnerFrame connect");                //~v101I~
			return false;                                          //~v101I~
		}
//  	PT=new PartnerThread(In,Out,Input,Output,this);            //~v108R~
    	PT=new PartnerThread(In,Out,null,null,this);               //~v108I~
		PT.start();
		out("@@name "+                                             //~@@@2R~
//  		Global.getParameter("yourname","No Name"));            //~@@@2R~
//  		AG.YourName);                                          //~@@@2I~//~1A6BR~
    		AG.YourName+CONTYPE_PREFIX+connectionType);            //~1A6BR~
//        show();                                                  //~@@@2R~
        AG.RemoteStatus=AG.RS_IPCONNECTED;                                 //~@@@2I~
		getHostAddr(Server);                                       //~@@@2I~
        doAction(AG.resource.getString(R.string.Game));	//popup GameQuestion//~@@@2I~
		return true;
	}


	public void open (Socket server)
	{	if (Dump.Y) Dump.println("Starting partner server");       //~@@@1R~
		Server=server;
		try
		{                                                          //~v101R~
            Server.setTcpNoDelay(true);                            //~v101R~
//         if (test==1)                                              //~@@@9I~//~1A6BR~
//         {                                                         //~@@@9I~//~1A6BR~
//            Out=new PrintWriter(                                   //~@@@9I~//~1A6BR~
//                Server.getOutputStream(),true);                    //~@@@9I~//~1A6BR~
//            In=new BufferedReader(new InputStreamReader(           //~@@@9I~//~1A6BR~
//                Server.getInputStream()));                         //~@@@9I~//~1A6BR~
//            OS=Server.getOutputStream();                           //~@@@9I~//~1A6BR~
//            IS=Server.getInputStream();                           //~@@@9I~//~1A6BR~
//         }                                                         //~@@@9I~//~1A6BR~
//         else                                                      //~@@@9I~//~1A6BR~
//         {                                                         //~@@@9I~//~1A6BR~
		 	Out=new PrintWriter(                                   //~v101I~
				new DataOutputStream(Server.getOutputStream()),true);
			In=new BufferedReader(new InputStreamReader(
			    new DataInputStream(Server.getInputStream())));
//         }                                                         //~@@@9I~//~1A6BR~
		}
		catch (Exception e)
		{	if (Dump.Y) Dump.println("---> no connection");        //~@@@1R~
			new Message(this,Global.resourceString("Got_no_Connection_"));
			return;
		}
        if (Dump.Y) Dump.println("PartnetFrame server after open");//~1AbMI~
//  	PT=new PartnerThread(In,Out,Input,Output,this);            //~v108R~
 	  	PT=new PartnerThread(In,Out,null,null,this);               //~v108I~//~@@@2R~
		PT.start();
        AG.RemoteStatus=AG.RS_IPCONNECTED;                                 //~@@@2I~
		getHostAddr(Server);                                       //~@@@2I~
	}

	public void doAction (String o)
	{                                                              //~v108I~
        if (o.equals(AG.resource.getString(R.string.Game))) //~v108I~//~@@@2R~
		{                                                          //~@@@2R~
        	if (Dump.Y) Dump.println("PartnerFrame doAction Game");//~v101I~
            dismissWaitingDialog();                                //~@@@2I~
            if (Dump.Y) Dump.println("PartnerFrame new GameQuestion");//~1AbMI~
			new GameQuestion(this);                                //~@@@2I~
		}
		else super.doAction(o);
	}

	public void doclose ()
    {                                                              //~v108I~
        if (Dump.Y) Dump.println("jagoclient.PartnerFrame doclose");//~1AbMI~
		out("@@@@end");                                            //~@@@2R~
		Out.close();
		new CloseConnection(Server,In);
		super.doclose();
	}
	public void doclose2()                                         //~1AbMI~
    {                                                              //~1AbMI~
        if (Dump.Y) Dump.println("jagoclient.PartnerFrame doclose2");//~1AbMI~
//  	out("@@@@end");                                            //~1AbMI~
//  	Out.close();                                               //~1AbMI~
		new CloseConnection(Server,In); //Close Serve and In       //~1AbMI~
		super.doclose();                                           //~1AbMI~
	}                                                              //~1AbMI~

	public boolean close ()
	{	if (PT.isAlive())
		{	new ClosePartnerQuestion(this);
			return false;
		}
		else return true;
	}

	/**
	The interpreter for the partner commands (all start with @@).
	*/
	public void interpret (String s)
//  {	if (s.startsWith("@@name"))                                //~v107R~
	{                                                              //~v107I~
        if (Dump.Y) Dump.println("PartnetFrame interpret:"+s);     //~v107I~
		if (s.startsWith("@@name"))                                //~v107I~
		{	StringParser p=new StringParser(s);
			p.skip("@@name");
//  		setTitle(Global.resourceString("Connection_to_")+p.upto('!'));//~1A6BR~
            String name=p.upto(CONTYPE_PREFIX.charAt(0)).trim();   //~1A6BR~
            PartnerName=name;                                      //~1A8iI~
//  		setTitle(Global.resourceString("Connection_to_")+name); //~1A6BI~//~1A85R~
//  		setTitle(CONN_TITLE_IP+Global.resourceString("Connection_to_")+name);//~1A8iR~//~1A85I~//~1AecR~
    		setTitle((AG.isNFCBT ? CONN_TITLE_NFC_BT :CONN_TITLE_BT)//~1AecI~
						+Global.resourceString("Connection_to_")+PartnerName);//+1AecR~
            p.advance();                                           //~1A6BI~
            int ct=p.parseint();                                   //~1A6BR~
            setConnectionType(ct);                                 //~1A6BI~
        	if (Dump.Y) Dump.println("PartnetFrame setTitle:"+Global.resourceString("Connection_to_")+name);//~v107I~//~1A6BR~
        	if (Dump.Y) Dump.println("PartnetFrame connectiontype="+connectionType);//~1A6BI~
			out("@@!name "+AG.YourName);                           //~1A37I~
		}
        else                                                       //~1A37I~
		if (s.startsWith("@@!name"))                               //~1A37I~
		{                                                          //~1A37I~
			StringParser p=new StringParser(s);                    //~1A37I~
			p.skip("@@!name");                                     //~1A37I~
        	if (Dump.Y) Dump.println("PartnetFrame partnername="+p.upto('!'));//~1A37I~
		}                                                          //~1A37I~
		else if (s.startsWith("@@board"))
		{	if (PGF!=null) return;
			StringParser p=new StringParser(s);
			p.skip("@@board");
			String color=p.parseword();
//*out:"b"/"w", boardsize,gameover,gameover2,tiotaltime,extratime,bishop knight//~@@@2R~
//			int C;
//			if (color.equals("b")) C=1;
//			else C=-1;
			int Size=p.parseint();
//            int TotalTime=p.parseint();                          //~v108R~
//            int ExtraTime=p.parseint();                          //~v108R~
//            int ExtraMoves=p.parseint();                         //~v108R~
//            int Handicap=p.parseint();                           //~v108R~
//            new BoardQuestion(this,Size,color,Handicap,TotalTime,//~v108I~
//                ExtraTime,ExtraMoves);                           //~v108I~
            int Gameover=p.parseint();                             //~@@@2I~
            int Gameover2=p.parseint();                            //~@@@2I~
            int TotalTime=p.parseint();                            //~v108I~
            int ExtraTime=p.parseint();                            //~@@@@I~//~@@@2M~
            int Bishop=p.parseint();                               //~v108I~
            int Knight=p.parseint();                               //~v108M~
            int GameOptions=p.parseint();                          //~@@@2R~
            PartnerName=p.parsewordDQ();                             //~@@@2R~//~1A25R~
//            if (PartnerName.length()>2)                            //~@@@2I~//~1A25R~
//                PartnerName=PartnerName.substring(1,PartnerName.length()-1);//drop embedding dquote//~@@@2I~//~1A25R~
//            else                                                   //~@@@2I~//~1A25R~
//                PartnerName="?";                                   //~@@@2I~//~1A25R~
            AG.PartnerName=PartnerName;                            //~@@@2I~
            dismissWaitingDialog();                                //~@@@2I~
			try { Thread.sleep(200); } catch (Exception e) {}      //~1A76I~
            if (Dump.Y) Dump.println("PartnerFrame new BoardQuestion");//~1AbMI~
			new BoardQuestion(this,Size,color,Gameover,Gameover2,TotalTime,ExtraTime,//~v108R~//~@@@2R~
				Bishop,Knight,GameOptions,PartnerName);                                    //~v108R~//~@@@@R~//~@@@2R~
		}
		else if (s.startsWith("@@!board"))
//*parm: b/w, size, totaltime, bishop, knight, gameoptions      //~v108I~//~@@@2R~
		{	if (PGF!=null) return;
			StringParser p=new StringParser(s);
			p.skip("@@!board");
			String color=p.parseword();
			int C;
			if (color.equals("b")) C=1;
			else C=-1;
			int Size=p.parseint();
  			int Gameover=p.parseint();                             //~@@@2I~
  			int Gameover2=p.parseint();                            //~@@@2I~
			int TotalTime=p.parseint();
  			int ExtraTime=p.parseint();                            //~@@@@I~//~@@@2M~
  			int Bishop=p.parseint();                               //~v108I~
  			int Knight=p.parseint();                               //~v108I~
  			int GameOptions=p.parseint();                          //~@@@2R~
  			PartnerName=p.parsewordDQ();                             //~@@@2I~//~1A25R~
//            if (PartnerName.length()>2)                            //~@@@2I~//~1A25R~
//                PartnerName=PartnerName.substring(1,PartnerName.length()-1);//drop embedding dquote//~@@@2I~//~1A25R~
//            else                                                   //~@@@2I~//~1A25R~
//                PartnerName="?";                                   //~@@@2I~//~1A25R~
  			int accepterBishop=p.parseint();                       //~1Aa7I~
  			int accepterKnight=p.parseint();                       //~1Aa7I~
            AG.PartnerName=PartnerName;                            //~1A53I~
            GameRequester=true;                                    //~@@@2I~
//  		PGF=new PartnerGoFrame(this,Global.resourceString("Partner_Game"),//~@@@2R~
    		PGF=new PartnerGoFrame(this,                           //~@@@2I~
//  			C,Size,TotalTime*60,ExtraTime*60,ExtraMoves,Handicap);//~v108R~
//  			C,Size,Gameover,Gameover2,TotalTime*60,ExtraTime,Bishop,Knight,GameOptions);  //~v108I~//~@@@@R~//~@@@2R~//~1Aa7R~
    			C,Size,Gameover,Gameover2,TotalTime*60,ExtraTime,Bishop,Knight,//~1Aa7R~
				GameOptions|GameQuestion.GAMEOPT_GAMEREQUESTER,accepterBishop,accepterKnight);//~1Aa7I~
			out("@@start");                                        //~@@@2R~
			Block=false;
//            Moves=new ListClass();                               //~@@@2R~
//            Moves.append(new ListElement(                        //~@@@2R~
//                new PartnerMove("board",C,Size,                  //~@@@2R~
//  				TotalTime,ExtraTime,ExtraMoves,Handicap)));    //~v108R~
		}
		else if (s.startsWith("@@-board"))
		{	new Message(this,Global.resourceString("Partner_declines_the_game_"));
			Block=false;
		}		
		else if (s.startsWith("@@start"))
		{	if (PGF==null) return;
			PGF.start();
			out("@@!start");                                       //~@@@2R~
		}
		else if (s.startsWith("@@!start"))
		{	if (PGF==null) return;
			PGF.start();
		}
		else if (s.startsWith("@@move"))
		{	if (PGF==null) return;
			yourMove();	//sound                                    //~v101I~
			StringParser p=new StringParser(s);
			p.skip("@@move");
			String color=p.parseword();
			int piece=p.parseint();     //0:pawn,1:bishop,2:knight //~@@@2I~
			int i=p.parseint(),j=p.parseint();
    		int ifrom=p.parseint();                                //~1A37I~
    		int jfrom=p.parseint();                                //~1A37I~
			int bt=p.parseint(),bm=p.parseint();
			int wt=p.parseint(),wm=p.parseint();
			int enteredExtraTime=p.parseint();                     //~1A06I~
			if (Dump.Y) Dump.println("Move of "+color+" at "+i+","+j+",ifrom="+ifrom+",jfrom="+jfrom);//~1A37I~
            receiveSelected(ifrom,jfrom);                          //~1A37I~
			if (color.equals("b"))
			{	if (PGF.maincolor()<0) return;
//				PGF.black(i,j);                                    //~@@@2R~
  				PGF.black(i,j,true/*reverse coordinate*/,piece);   //~@@@2R~
//                Moves.append(new ListElement                     //~@@@2R~
//                    (new PartnerMove("b",i,j,bt,bm,wt,wm)));     //~@@@2R~
				if (enteredExtraTime!=0)                           //~1A06I~
					PGF.resetExtraTime(1/*black*/);                //~1A06I~
			}
			else
			{	if (PGF.maincolor()>0) return;
//                PGF.white(i,j);                                  //~@@@2R~
                PGF.white(i,j,true/*reverse coordinate*/,piece);   //~@@@2R~
//                Moves.append(new ListElement                     //~@@@2R~
//                    (new PartnerMove("w",i,j,bt,bm,wt,wm)));     //~@@@2R~
				if (enteredExtraTime!=0)                           //~1A06I~
					PGF.resetExtraTime(-1/*white*/);               //~1A06I~
			}
			PGF.settimes(bt,bm,wt,wm);
//            out("@@!move "+color+" "+i+" "+j+" "+                //~@@@2R~
            out("@@!move "+color+" "+piece+" "+i+" "+j+" "+        //~@@@2I~
				bt+" "+bm+" "+wt+" "+wm);
		}
		else if (s.startsWith("@@!move"))
		{	if (PGF==null) return;
//         if (test==1)                                              //~@@@9R~//~1A6BR~
//            ;                                                      //~@@@9I~//~1A6BR~
//         else                                                      //~@@@9I~//~1A6BR~
//         if (test==2)                                              //~@@@9I~//~1A6BR~
//            ;                                                      //~@@@9I~//~1A6BR~
//         else                                                      //~@@@9I~//~1A6BR~
          out("@@!!move");	//to keep up/down sequense             //~1A39I~
        //********move piece at partner responsed time             //~@@@2I~
			StringParser p=new StringParser(s);
			p.skip("@@!move");
			String color=p.parseword();
			int piece=p.parseint();     //0:pawn,1:bishop,2:knight //~@@@2I~
			int i=p.parseint(),j=p.parseint();
			int bt=p.parseint(),bm=p.parseint();
			int wt=p.parseint(),wm=p.parseint();
			if (Dump.Y) Dump.println("Move of "+color+" piece:"+piece+" at "+i+","+j);//~@@@1R~//~@@@2R~
            if (color.equals("b"))                                 //~@@@2R~
            {   if (PGF.maincolor()<0) return;                     //~@@@2R~
                PGF.black(i,j);                                    //~@@@2R~
//                Moves.append(new ListElement                     //~@@@2R~
//                    (new PartnerMove("b",i,j,bt,bm,wt,wm)));     //~@@@2R~
            }                                                      //~@@@2R~
            else                                                   //~@@@2R~
            {   if (PGF.maincolor()>0) return;                     //~@@@2R~
                PGF.white(i,j);                                    //~@@@2R~
//                Moves.append(new ListElement                     //~@@@2R~
//                    (new PartnerMove("w",i,j,bt,bm,wt,wm)));     //~@@@2R~
            }                                                      //~@@@2R~
            PGF.settimes(bt,bm,wt,wm);                             //~@@@2R~
	        PGF.swWaitingPartnerResponse=false;                    //~1A38I~
		}
//        else if (s.startsWith("@@selected"))                       //~@@@2I~//~1A37R~
//        {   if (PGF==null) return;                                 //~@@@2I~//~1A37R~
//            StringParser p=new StringParser(s);                    //~@@@2I~//~1A37R~
//            p.skip("@@selected");                                  //~@@@2I~//~1A37R~
//            int i=p.parseint(),j=p.parseint();                     //~@@@2I~//~1A37R~
//            if (Dump.Y) Dump.println("@@selected at "+i+","+j);    //~@@@2I~//~1A37R~
//            PGF.receiveSelected(i,j);                              //~@@@2I~//~1A37R~
//            out("@@!selected "+i+" "+j);                           //~@@@2R~//~1A37R~
//        }                                                          //~@@@2I~//~1A37R~
//        else if (s.startsWith("@@!selected"))                      //~@@@2I~//~1A37R~
//        {   if (PGF==null) return;                                 //~@@@2I~//~1A37R~
//            StringParser p=new StringParser(s);                    //~@@@2I~//~1A37R~
//            p.skip("@@!selected");                                 //~@@@2I~//~1A37R~
//            int i=p.parseint(),j=p.parseint();                     //~@@@2I~//~1A37R~
//            if (Dump.Y) Dump.println("@@!selected at "+i+","+j);   //~@@@2I~//~1A37R~
//        }                                                          //~@@@2I~//~1A37R~
        else if (s.startsWith("@@pass"))                         //~v108R~//~@@@2R~
        {   if (PGF==null) return;                               //~v108R~//~@@@2R~
            StringParser p=new StringParser(s);                  //~v108R~//~@@@2R~
            p.skip("@@pass");                                    //~v108R~//~@@@2R~
            int bt=p.parseint(),bm=p.parseint();                 //~v108R~//~@@@2R~
            int wt=p.parseint(),wm=p.parseint();                 //~v108R~//~@@@2R~
            if (Dump.Y) Dump.println("Pass");                      //~@@@1R~//~v108R~//~@@@2R~
            PGF.notifiedPass();	//received pass                    //~@@@2R~
            PGF.dopass();                                        //~v108R~//~@@@2R~
            PGF.settimes(bt,bm,wt,wm);                           //~v108R~//~@@@2R~
//            Moves.append(new ListElement                         //~v108R~//~@@@2R~
//                (new PartnerMove("pass",bt,bm,wt,wm)));          //~v108R~//~@@@2R~
            out("@@!pass "+bt+" "+bm+" "+wt+" "+wm);     //~v108R~ //~@@@2R~
        }                                                        //~v108R~//~@@@2R~
        else if (s.startsWith("@@!pass"))                        //~v108R~//~@@@2R~
        {   if (PGF==null) return;                               //~v108R~//~@@@2R~
            StringParser p=new StringParser(s);                  //~v108R~//~@@@2R~
            p.skip("@@!pass");                                   //~v108R~//~@@@2R~
            int bt=p.parseint(),bm=p.parseint();                 //~v108R~//~@@@2R~
            int wt=p.parseint(),wm=p.parseint();                 //~v108R~//~@@@2R~
            if (Dump.Y) Dump.println("Pass");                      //~@@@1R~//~v108R~//~@@@2R~
            PGF.dopass();                                        //~v108R~//~@@@2R~
//            Moves.append(new ListElement                         //~v108R~//~@@@2R~
//                (new PartnerMove("pass",bt,bm,wt,wm)));          //~v108R~//~@@@2R~
            PGF.settimes(bt,bm,wt,wm);                           //~v108R~//~@@@2R~
        }                                                        //~v108R~//~@@@2R~
		else if (s.startsWith("@@resign "))                        //~@@@2R~
		{                                                          //~@@@2I~
  			resignrequested(s.substring(9));                       //~@@@2R~
		}                                                          //~@@@2I~
		else if (s.startsWith("@@endgame"))
		{	if (PGF==null) return;
  			Block=true;                                            //~v108I~
			new EndGameQuestion(this);
//			Block=true;                                            //~v108R~
		}
		else if (s.startsWith("@@!endgame"))
		{	if (PGF==null) return;
//  		PGF.doscore();                                         //~@@@2R~
    		PGF.doscore(-PGF.Col/*winner is partner*/);  //partner accepted resign on EndGameQuestion//~@@@2R~
			Block=false;
		}
		else if (s.startsWith("@@-endgame"))
		{	if (PGF==null) return;
			new Message(this,"Partner declines game end!");
			Block=false;
		}
		else if (s.startsWith("@@result"))
		{	if (PGF==null) return;
			StringParser p=new StringParser(s);
			p.skip("@@result");
			int b=p.parseint();
			int w=p.parseint();
			if (Dump.Y) Dump.println("Result "+b+" "+w);           //~@@@1R~
			new ResultQuestion(this,Global.resourceString("Accept_result__B_")+b+Global.resourceString("__W_")+w+"?",b,w);
			Block=true;
		}
		else if (s.startsWith("@@!result"))
		{	if (PGF==null) return;
			StringParser p=new StringParser(s);
			p.skip("@@!result");
			int b=p.parseint();
			int w=p.parseint();
			if (Dump.Y) Dump.println("Result "+b+" "+w);           //~@@@1R~
//            Output.append(Global.resourceString("Game_Result__B_")+b+Global.resourceString("__W_")+w+"\n",Color.green.darker());//~v108R~
			new Message(this,Global.resourceString("Result__B_")+b+Global.resourceString("__W_")+w+" was accepted!");
			Block=false;
		}
		else if (s.startsWith("@@-result"))
		{	if (PGF==null) return;
			new Message(this,Global.resourceString("Partner_declines_result_"));
			Block=false;
		}
		else if (s.startsWith("@@adjourn"))
		{	adjourn();
		}
	}
//from PartnerGoFrame                                              //~v108R~
//parm: color, pos1, pos2, Black remaining Time , Black Moves remaining count in the time, White time, White moves//~v108I~
//  public boolean moveset (String c, int i, int j, int bt, int bm,//~@@@2R~
    public boolean moveset (String c, int Ppiece,int i, int j, int bt, int bm,//~@@@2I~
//  	int wt, int wm)                                            //~1A06R~
    	int wt, int wm,int PenteredExtraTime)                      //~1A06I~
	{	if (Block) return false;
        PGF.swWaitingPartnerResponse=true;                         //~1A38I~
//  	out("@@move "+c+" "+i+" "+j+" "+bt+" "+bm+" "+wt+" "+wm);  //~@@@2R~
//  	out("@@move "+c+" "+Ppiece+" "+i+" "+j+" "+bt+" "+bm+" "+wt+" "+wm);//~@@@2I~//~1A06R~
//  	out("@@move "+c+" "+Ppiece+" "+i+" "+j+" "+bt+" "+bm+" "+wt+" "+wm+" "+PenteredExtraTime);//~1A06I~//~1A37R~
  		out("@@move "+c+" "+Ppiece+" "+i+" "+j+" "+sendiSelected+" "+sendjSelected+" "+bt+" "+bm+" "+wt+" "+wm+" "+PenteredExtraTime);//~1A37I~
        yourMove();	//sound stone                                  //~v101I~
		if (Dump.Y) Dump.println("PF:moveset send @@move c="+c+",piece="+Ppiece+",i="+i+",j="+j+",ifrom="+sendiSelected+",jfrom="+sendjSelected+",bt="+bt+",wt="+wt);//~1A37I~
		return true;
	}
//*******************************************************************//~@@@2I~
//from PartnerGoFrame                                              //~@@@2I~
//send piece selected                                              //~@@@2I~
//*******************************************************************//~@@@2I~
	public boolean sendSelected (int Pi, int Pj)                    //~@@@2R~
	{	if (Block) return false;                                   //~@@@2I~
//        Out.println("@@selected "+Pi+" "+Pj);                    //~@@@2R~
//      out("@@selected "+Pi+" "+Pj);                              //~@@@2I~//~1A37R~
		sendiSelected=Pi; sendjSelected=Pj;                        //~1A37I~
    	JagoSound.play("pieceup",false/*not change to beep when beeponly option is on*/);//~@@@2I~
		return true;                                               //~@@@2I~
	}                                                              //~@@@2I~
//*******************************************************************//~1A37I~
    private void receiveSelected(int Pi, int Pj)                   //~1A37I~
    {                                                              //~1A37I~
		if (Dump.Y) Dump.println("receiveselected at "+Pi+","+Pj); //~1A37I~
        PGF.receiveSelected(Pi,Pj);                                //~1A37I~
    }                                                              //~1A37I~
	public void out (String s)
	{                                                              //~@@@2R~
    	try                                                        //~@@@2I~
        {                                                          //~@@@2I~
//          if (test==1)                                             //~@@@9R~//~1A6BR~
//          {                                                        //~@@@9I~//~1A6BR~
//        if (Dump.Y) Dump.println("PartnerFrame OS="+s);            //~@@@9I~//~1A6BR~
//            String sl="                                                  ";     //50//~@@@9R~//~1A6BR~
//          if (test==1)                                             //~@@@9R~//~1A6BR~
//          {                                                        //~@@@9I~//~1A6BR~
//            sl=sl+sl;   //100                                      //~@@@9I~//~1A6BR~
//            sl=sl+sl+sl+sl+sl;    //500                            //~@@@9I~//~1A6BR~
//            sl=sl+sl;  //1000                                      //~@@@9I~//~1A6BR~
//            sl=s+sl;  //1000                                       //~@@@9I~//~1A6BR~
//          }                                                        //~@@@9I~//~1A6BR~
//          else                                                     //~@@@9I~//~1A6BR~
//            sl=s+"\n";                                             //~@@@9I~//~1A6BR~
//            if (Dump.Y) Dump.println("PartnerFrame OS="+sl);       //~@@@9R~//~1A6BR~
//            byte[] b=sl.getBytes("UTF-8");                         //~@@@9R~//~1A6BR~
//            OS.write(b);                                           //~@@@9I~//~1A6BR~
//            OS.flush();                                            //~@@@9I~//~1A6BR~
//          }                                                        //~@@@9I~//~1A6BR~
//          else                                                     //~@@@9I~//~1A6BR~
//          if (test==2)                                             //~@@@9I~//~1A6BR~
//          {                                                        //~@@@9I~//~1A6BR~
//            String s2=s+"\n";                                      //~@@@9R~//~1A6BR~
//        if (Dump.Y) Dump.println("PartnerFrame test2 out="+s2+";");//~@@@9I~//~1A6BR~
//            Out.println(s2);                                       //~@@@9R~//~1A6BR~
//          }                                                        //~@@@9I~//~1A6BR~
//          else                                                     //~@@@9I~//~1A6BR~
//          {                                                        //~@@@9I~//~1A6BR~
    	if (Dump.Y) Dump.println("PartnerFrame out="+s+",Out="+Out.toString());//~1A37I~
			Out.println(s);                                        //~@@@2I~
//            Out.flush();                                         //~v101R~
//    	if (Dump.Y) Dump.println("PartnerFrame out flushed");      //~v101R~//~@@@9R~
//          }                                                        //~@@@9I~//~1A6BR~
        }                                                          //~@@@2I~
        catch(Exception e)                                         //~@@@2I~
        {                                                          //~@@@2I~
        	Dump.println(e,"PatrnerFrame out():"+s);               //~@@@2I~
        }                                                          //~@@@2I~
	}

	public void refresh ()
	{
	}

	public void set (int i, int j)
	{
	}
//**from PGF.movepass()                                            //~@@@2R~
	public void pass (int bt, int bm, int wt, int wm)
	{                                                              //~@@@2R~
//		Out.println("@@pass "+bt+" "+bm+" "+wt+" "+wm);            //~@@@2I~
		out("@@pass "+bt+" "+bm+" "+wt+" "+wm);                    //~@@@2I~
	}

	public void endgame ()
	{	if (Block) return;
		Block=true;
//      Out.println("@@endgame");                                  //~@@@2R~
        out("@@endgame");                                          //~@@@2I~
	}
//*resign button pushed ********************                       //~@@@2R~
	public void resign ()	//from PartnerGoFrame                  //~@@@2R~
	{                                                              //~@@@2I~
		new EndGameQuestion(this,AG.resource.getString(R.string.EndgameResign),true);//~@@@2I~
	}                                                              //~@@@2I~
//***Ok for EndgameQuestion(Resign)(requester) **************      //~@@@2I~
	public void doresign ()                                        //+@@@2I~                                                              //~@@@2I~
	{	if (Block) return;                                         //~@@@2I~
		Block=true;                                                //~@@@2I~
		if (Dump.Y) Dump.println("doresign send @@resign");        //~@@@2I~
		out("@@resign \""+AG.YourName+"\"");                       //~@@@2R~
        ((ConnectedBoard)(PGF.B)).infoMsg(R.string.Info_I_Resign);               //~@@@@I~//~@@@2I~
    }                                                              //~@@@2R~
//***received @@resign **************                              //~@@@2I~
//***doendgame() will be called if replyed OK **************       //~@@@2I~
	public void resignrequested(String Ppartnername)               //~@@@2R~
	{                                                              //~@@@2M~
//        ((ConnectedBoard)(PGF.B)).infoMsg(Ppartnername+" ",R.string.Info_Opponent_Resign);//~@@@2R~
        ((ConnectedBoard)(PGF.B)).infoMsg(AG.resource.getString(R.string.Info_Opponent_Resign,Ppartnername));//~@@@2I~
//        new EndGameQuestion(this,AG.resource.getString(R.string.DoYouAcceptResign),false);//~@@@2R~
        doendgame();    //no question but msg only then            //~@@@2R~
	 	new Message(this,Ppartnername+" "+AG.resource.getString(R.string.Msg_Partner_Resigned));//~@@@2R~
	}                                                              //~@@@2M~
//****************************************************             //~@@@2R~
//*EndGameQuetion replyed OK send @@!endgame	                   //~@@@2R~
	public void doendgame ()
	{                                                              //~@@@2R~
		out("@@!endgame");                                         //~@@@2I~
//  	PGF.doscore();                                             //~@@@2R~
    	PGF.doscore(PGF.Col/*winner is me*/); //accepted endgame   //~@@@2R~
		Block=false;
	}
//*EndGameQuetion replyed NO                                       //~@@@2I~
	public void declineendgame ()
	{                                                              //~@@@2R~
		out("@@-endgame");                                         //~@@@2I~
		Block=false;
	}

	public void doresult (int b, int w)
	{                                                              //~@@@2R~
		out("@@!result "+b+" "+w);                                 //~@@@2I~
//        Output.append(Global.resourceString("Game_Result__B_")+b+Global.resourceString("__W_")+w+"\n",Color.green.darker());//~v108R~
		Block=false;
	}

	public void declineresult ()
	{                                                              //~@@@2R~
		out("@@-result");                                          //~@@@2I~
		Block=false;
	}

//*parm:boardsize, b/w,gameover,gameover2,totaltime,extratime,bishop,knight,gameoptions//~@@@2R~
//  public void dorequest (int s, String c, int h, int tt, int et, int em)//~@@@@R~
//  {	Out.println("@@board "+c+" "+s+" "+tt+" "+et+" "+em+" "+h);//~@@@@R~
    public void dorequest (int s, String c, int gameover,int gameover2,int tt, int et, int bishop, int knight, int gameoptions)//~@@@@I~//~@@@2R~
    {                                                              //~@@@@I~
//  	Out.println("@@board "+c+" "+s+" "+gameover+" "+gameover2+" "+tt+" "+et+" "+bishop+" "+knight+" "+gameoptions+" \""+AG.YourName+"\"");//~@@@2R~
  		out("@@board "+c+" "+s+" "+gameover+" "+gameover2+" "+tt+" "+et+" "+bishop+" "+knight+" "+gameoptions+" \""+AG.YourName+"\"");//~@@@2I~
		Block=true;
	}

//*****************************************************************************//~@@@2I~
//*from BoardQuestion                                              //~@@@2I~
//*Accepted/Declined                                               //~@@@2I~
//*****************************************************************************//~@@@2I~
//*parm:boardsize, b/w,gameover,gameover2, totaltime,extratime, bishop, knight,gameoptions//~@@@2R~
//	public void doboard (int Size, String C, int Handicap,         //~v108R~
//			int TotalTime, int ExtraTime, int ExtraMoves)          //~v108R~
  	public void doboard (int Size, String C, int Gameover,int Gameover2,//~@@@2R~
//  		int TotalTime,int ExtraTime, int Bishop, int Knight, int GameOptions)                 //~v108I~//~@@@@R~//~@@@2R~//~1Aa7R~
    		int TotalTime,int ExtraTime, int Bishop, int Knight, int GameOptions,//~1Aa7I~
    		int accepterBishop,int accepterKnight)                 //~1Aa7I~
//  {	PGF=new PartnerGoFrame(this,"Partner Game",                //~@@@2R~
    {                                                              //~@@@2R~
        GameRequester=false;                                       //~@@@2I~
    	PGF=new PartnerGoFrame(this,                               //~@@@2I~
//  		C.equals("b")?-1:1,Size,TotalTime*60,ExtraTime*60,ExtraMoves,Handicap);//~v108R~
//  		C.equals("b")?-1:1,Size,Gameover,Gameover2,TotalTime*60,ExtraTime,Bishop,Knight,GameOptions);//~v108I~//~@@@@R~//~@@@2R~//~1Aa7R~
    		C.equals("b")?-1:1,Size,Gameover,Gameover2,TotalTime*60,ExtraTime,Bishop,Knight,GameOptions,accepterBishop,accepterKnight);//~1Aa7I~
		if (C.equals("b"))                                         //~@@@2R~
			out("@@!board b"+                                      //~@@@2I~
//  		" "+Size+" "+TotalTime+" "+ExtraTime+" "+ExtraMoves+" "+Handicap);//~v108R~
//  		" "+Size+" "+Gameover+" "+Gameover2+" "+TotalTime+" "+ExtraTime+" "+Bishop+" "+Knight+" "+GameOptions+" \""+AG.YourName+"\"");//~v108I~//~@@@@R~//~@@@2R~//~1Aa7R~
    		" "+Size+" "+Gameover+" "+Gameover2+" "+TotalTime+" "+ExtraTime+" "+Bishop+" "+Knight+" "+GameOptions+" \""+AG.YourName+"\""+//~1Aa7I~
            " "+accepterBishop+" "+accepterKnight);                //~1Aa7I~
		else                                                       //~@@@2R~
			out("@@!board w"+                                      //~@@@2I~
//  		" "+Size+" "+TotalTime+" "+ExtraTime+" "+ExtraMoves+" "+Handicap);//~v108R~
//  		" "+Size+" "+Gameover+" "+Gameover2+" "+TotalTime+" "+ExtraTime+" "+Bishop+" "+Knight+" "+GameOptions+" \""+AG.YourName+"\"");//~@@@2R~//~1Aa7R~
    		" "+Size+" "+Gameover+" "+Gameover2+" "+TotalTime+" "+ExtraTime+" "+Bishop+" "+Knight+" "+GameOptions+" \""+AG.YourName+"\""+//~1Aa7I~
            " "+accepterBishop+" "+accepterKnight);                //~1Aa7I~
//        Moves=new ListClass();                                   //~@@@2R~
//        Moves.append(new ListElement(                            //~@@@2R~
//            new PartnerMove("board",C.equals("b")?-1:1,          //~@@@2R~
//  			Size,TotalTime,ExtraTime,ExtraMoves,Handicap)));   //~v108R~
	}

	public void declineboard ()
	{                                                              //~@@@2R~
		out("@@-board");                                           //~@@@2I~
	}

//********************************************************         //~@@@@I~
//*from PartnerGoFrame:doclose                                     //~@@@@I~
//********************************************************         //~@@@@I~
	public void boardclosed (PartnerGoFrame pgf)
	{	if (PGF==pgf)
		{                                                          //~@@@2R~
			out("@@adjourn");                                      //~@@@2I~
//            savemoves();                                         //~v108R~
		}
	}

	public void adjourn ()
//  {	new Message(this,Global.resourceString("Your_Partner_closed_the_board_"));//~1A2kR~
    {                                                              //~1A2kI~
    	if (Dump.Y) Dump.println("PF:adjourn");                    //~1A2eI~//~1A2kI~
		if (PGF==null) return;                                     //~1A6zI~
    	new Message(this,Global.resourceString("Your_Partner_closed_the_board_"));//~1A2kI~
//        savemoves();                                             //~v108R~
		PGF.acceptClosed();		//close frame                      //~@@@@I~
		PGF=null;
	}


	void acceptrestore ()
	{                                                              //~@@@2R~
		out("@@!restore");                                         //~@@@2I~
	}

	void declinerestore ()
	{                                                              //~@@@2R~
		out("@@-restore");                                         //~@@@2I~
	}

//***************************************************************  //~@@@2I~
	public void disconnect()	//from IPConnection                //~@@@2I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("PartnerFrame Disconnect");       //~@@@2I~
        if (PGF==null)	//not game started                         //~@@@2I~
        {                                                          //~@@@2I~
			doclose();	//CloseConnection                          //~@@@2I~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
    	if (PT!=null && PT.isAlive())                              //~@@@2I~
			resign();                                              //~@@@2I~
        else                                                       //~@@@2I~
			doclose();	//CloseConnection	                       //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************  //~@@@2I~
	public static void dismissWaitingDialog()                      //~@@@2R~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("PartnerFrame DismissWaitiingDialog");//~@@@2R~
		ProgDlg.dismiss();                                         //~@@@2I~
        IPConnection.closeDialog();                                 //~1A6yI~
        BluetoothConnection.closeDialog();                         //~1AbvI~
        DialogNFC.closeDialog();	//close DialogNFC if showing   //~1A6sI~
        DialogNFCBT.closeDialog();	//close DialogNFCBT if showing //~1AbjI~//~1AbjR~
    }                                                              //~@@@2I~
//***************************************************************  //~@@@2M~
//*from PartnerThread when connection failed                       //~@@@2M~
//*change Resign button to Close                                   //~@@@2M~
//***************************************************************  //~@@@2M~
	public void interrupted(int Pstrid)                            //~@@@2M~
    {                                                              //~@@@2M~
      	if (Dump.Y) Dump.println("PartnerFrame interrupted pgf="+(PGF==null?"null":PGF.toString()));//~@@@2I~
      try                                                          //~1A6kR~
      {                                                            //~1A53I~
        if (PGF!=null)                                             //~@@@2M~
	        PGF.gameInterrupted(Pstrid);                           //~@@@2M~
        else                                                       //~@@@2I~
        {                                                          //~1A6kR~
        	if (AG.aIPConnection!=null)                            //~1A6kR~
            	AG.aIPConnection.updateViewDisconnected();         //~1A6kR~
        	if (AG.aBTConnection!=null)                            //~1A6kI~
            	AG.aBTConnection.updateViewDisconnected();         //~1A6kI~
            new Message(this,R.string.ErrDisconnected);              //~@@@2I~
        }                                                          //~1A6kR~
      }                                                            //~1A6kR~
      catch(Exception e)                                           //~1A6kR~
      {                                                            //~1A6kR~
        Dump.println(e,"PartnerFrame:interrupted");                //~1A6kR~
      }                                                            //~1A6kR~
    }                                                              //~@@@2M~
//***************************************************************  //~@@@2I~
    private void getHostAddr(Socket Psocket)                       //~@@@2I~//~1A8iR~
    {                                                              //~@@@2I~
//        InetAddress ia=Psocket.getInetAddress();                   //~@@@2I~//~1A8fR~
//        if (ia!=null)                                              //~@@@2M~//~1A8fR~
//        {                                                          //~@@@2M~//~1A8fR~
//            if (Dump.Y) Dump.println("server inet address="+ia.getHostAddress()+",name="+ia.getHostName());//~@@@2I~//~1A8fR~
//            if (Dump.Y) Dump.println("server inet address="+ia.toString());//~@@@2I~//~1A8fR~
//            AG.RemoteInetAddress=ia.getHostAddress();              //~@@@2R~//~1A8fR~
//        }                                                          //~@@@2M~//~1A8fR~
//        ia=Psocket.getLocalAddress();                              //~1A6sI~//~1A8fR~
//        if (ia!=null)                                              //~1A6sI~//~1A8fR~
//        {                                                          //~1A6sI~//~1A8fR~
//            AG.LocalInetAddress=ia.getHostAddress();               //~1A6sI~//~1A8fR~
//            if (Dump.Y) Dump.println("server inet localaddress="+AG.LocalInetAddress);//~1A6sI~//~1A8fR~
//        }                                                          //~1A6sI~//~1A8fR~
        AG.RemoteInetAddressLAN=Utils.getRemoteIPAddr(Psocket,null);//~1A8fI~
        AG.LocalInetAddressLAN=Utils.getLocalIPAddr(Psocket,null);//~1A8fI~
	    if (Dump.Y) Dump.println("PartnerFrame:getHostAddr remote="+AG.RemoteInetAddressLAN+",local="+AG.LocalInetAddressLAN);//~1A8fI~
    }                                                              //~@@@2I~
	private void yourMove()                                        //~v101I~
    {                                                              //~v101I~
    	if (Dump.Y) Dump.println("PF:yourMove sound stone start"); //~v101I~
//  	JagoSound.play("stone","click",true);                    //~@@@@R~//~v101I~//~1A06R~
    	JagoSound.play("piecedown",false/*not change to beep when beeponly option is on*/);//~1A09I~//~1A06I~
    	if (Dump.Y) Dump.println("PF:yourMove sound stone end");   //~v101I~
	}                                                              //~v101I~
//***************************************************************  //~1A6BI~
//  private void setConnectionType(int Pcontype)                   //~1A6BI~//~1A8iR~
    public void setConnectionType(int Pcontype)                    //~1A8iI~
    {                                                              //~1A6BI~
    	int ct=Pcontype;                                           //~1A6BI~
    	if (Pcontype==IPConnection.NFC_CLIENT)                     //~1A6BI~
        	ct=IPConnection.NFC_SERVER;                            //~1A6BI~
        else                                                       //~1A6BI~
        if (ct==IPConnection.WD_CLIENT)                            //~1A6BI~
            ct=IPConnection.WD_SERVER;                             //~1A6BI~
        connectionType=ct;                                         //~1A6BI~
    }                                                              //~1A6BI~
//**************************************************************************//~v110I~//~1A85I~
//*from AMain at appStop,send @@adjourn and cose out to notify termination//~v110I~//~1A85I~
//**************************************************************************//~v110I~//~1A85I~
    public void closeStream()                                      //~v110I~//~1A85I~
    {                                                              //~v110I~//~1A85I~
	    if (Dump.Y) Dump.println("PartnerFrame close Stream");     //~v110I~//~1A85I~
//      stopTimer();                                               //~v110M~//~1A85I~
        if (Out!=null)                                             //~v110I~//~1A85I~
        {                                                          //~v110I~//~1A85I~
            if (Out!=null)                                         //~v110I~//~1A85I~
            {                                                      //~v110I~//~1A85I~
			    if (Dump.Y) Dump.println("out @@adjourn");         //~v110I~//~1A85I~
				out("@@adjourn");                          //~v110I~//~1A8cR~//~1A85I~
			    if (Dump.Y) Dump.println("out close");             //~v110I~//~1A85I~
                Out.close();                                       //~v110I~//~1A85I~
            }                                                      //~v110I~//~1A85I~
        }                                                          //~v110I~//~1A85I~
    }                                                              //~v110I~//~1A85I~
}

