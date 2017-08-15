//*CID://+1AabR~:                             update#=  102;       //~1AabR~
//**************************************************************************//~1022I~
//1Aab 2015/04/22 1Aa7 for local game                              //~1AabI~
//1Aa9 2015/04/21 (BUG)Button of GameQuestion except Help dose not work after ChangeBK button//~1Aa9I~
//                GameQuestion(ModalDialog) run on subthread at connection complete.//~1Aa9I~
//                Modal latch is posted when ChangeBK button pushed,but dose not dismiss dialog//~1Aa9I~
//                Push button except help do latch.countdown which is already posted(subthread was already posted)//~1Aa9I~
//                -->GameQuestionDialog is not requred as modal.   //~1Aa9I~
//                (LocalGameQuestion is not subthread but reset modal also)//~1Aa9I~
//                !! Modal option is assuming Button click follows dismiss dialog.//~1Aa9I~
//                !! Check class extended CloseDialog(modal=true)  //~1Aa9I~
//1Aa8 2015/04/20 put in layout the gamequestion for mdpi         //~1Aa8I~//~1Aa7R~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//1A55 2014/11/01 opponent name for local match(for savefile when asgts)//~1A55I~
//1022 2013/03/02 Accept gameover count=0 as board size            //~1022I~
//**************************************************************************//~1022I~
package jagoclient.partner;

import android.view.View;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.Prop;
import com.Ahsv.R;
import com.Ahsv.URunnable;
import com.Ahsv.URunnableI;
import com.Ahsv.awt.Checkbox;
import com.Ahsv.awt.Color;
import com.Ahsv.awt.Component;
import com.Ahsv.awt.Frame;

import jagoclient.Dump;
import jagoclient.dialogs.HelpDialog;
//import jagoclient.gui.ButtonAction;                              //~2C26R~
import jagoclient.gui.ButtonAction;
import jagoclient.gui.CloseDialog;
import jagoclient.gui.FormTextField;                             //~2C26R~//~@@@@R~
//import jagoclient.gui.MyLabel;                                   //~2C26R~
//import jagoclient.gui.MyPanel;                                   //~2C26R~
//import jagoclient.gui.Panel3D;                                   //~2C26R~

//import java.awt.Choice;                                          //~@@@@R~
//import java.awt.GridLayout;
//import java.awt.Panel;

/**
Question to start a game with user definable paramters (handicap etc.)
*/

public class GameQuestion extends CloseDialog
	implements URunnableI                                          //~@@@2I~
//{   FormTextField BoardSize,Handicap,TotalTime,ExtraTime,ExtraMoves;//~@@@@R~
{                                                                  //~@@@@I~
    public  final static String PKEY_GAMEOVER="Gameover";          //~@@@@R~
    public  final static String PKEY_GAMEOVER2="Gameover2";        //~@@@2I~
    public  final static String PKEY_BISHOP="Bishop";              //~@@@@R~
    public  final static String PKEY_KNIGHT="Knight";              //~@@@@R~
    public  final static String PKEY_BISHOP_LOCAL="BishopLocal";   //~1AabI~
    public  final static String PKEY_KNIGHT_LOCAL="KnightLocal";   //~1AabI~
    public  final static String PKEY_GAMEOPTIONS="GameOptions";//~@@@@R~//~@@@2R~
    public  final static String PKEY_TOTAL_TIME="TotalTime";       //~@@@@R~
    public  final static String PKEY_EXTRA_TIME="ExtraTime";       //~@@@@R~
    private static final int FUNC_SETUPBK=100;                     //~1Aa7R~
    private static final int FUNC_SETUPBK_LOCAL=101;               //~1AabI~
                                                                   //~1Aa7R~
    private final static String PKEY_LOCAL_OPPONENT_NAME="LocalOpponentName";//~1A55I~
    public  final static Color  COLOR_YOURNAME=new Color(0x80,0xc0,0x80);//~@@@2I~
    public  final static int    MAX_GAMEOVER=9;                //~@@@@R~//~@@@2R~
    public  final static int    DEFAULT_GAMEOVER=5;                //~@@@2I~
    public  final static int    DEFAULT_GAMEOVER2=3;               //~@@@2I~
    public  final static int    DEFAULT_EXTRATIME=10; //sec        //~@@@@R~
    public  final static int    GAMEOPT_MOVEFIRST=0x01;            //~@@@2I~
    public  final static int    GAMEOPT_REFLECTABLE=0x02;          //~@@@2I~
    public  final static int    GAMEOPT_RESELECTABLE=0x04;         //~@@@2I~
    public  final static int    GAMEOPT_NOTREMOVABLE=0x08;         //~@@@2R~
    public  final static int    GAMEOPT_POSNOTFIX=0x10;            //~1Aa7I~
    public  final static int    GAMEOPT_GAMEREQUESTER=0x20; //not game requester//~1Aa7R~
    public  final static int    GAMEOPT_LOCALGAME=0x40;            //~1AabI~
    protected FormTextField Bishop,Knight,TotalTime,ExtraTime,Gameover,Gameover2;      //~@@@@R~//~@@@2R~
    protected FormTextField BishopLocal,KnightLocal;               //~1AabI~
    protected FormTextField OpponentName;                          //~1A55I~
    protected FormTextField YourName,BoardType;                    //~@@@2R~
//  Choice ColorChoice;                                            //~@@@@R~
    Checkbox Reflectable,MoveFirst,Reselectable,NotRemovable;                              //~@@@@I~//~@@@2R~
    Checkbox cbPosNotFix;                                          //~1Aa7R~
	PartnerFrame PF;
    public int bishop,knight,totaltime,extratime,gameover,gameover2,boardsz;//~@@@2R~
    public int bishopLocal,knightLocal;                            //~1AabI~
    public int reflectable,reselectable,notremovable;              //~@@@2R~
	public int movefirst,gameoptions;                              //~@@@2R~
	public int posnotfix;                                          //~1Aa7I~
	private boolean swLocalGame;                                   //~1A55I~
	public boolean swLocalBK;                                      //~1AabI~
//******************************                                   //~@@@2I~
//*for LocalGameQuestion                                           //~@@@2I~
//******************************                                   //~@@@2I~
	public GameQuestion(Frame Pframe,String Ptitle)                //~@@@2R~
	{                                                              //~@@@2I~
//      super(Pframe,Ptitle,R.layout.gamequestion,true,false);     //~@@@2R~//~1Aa8R~
        super(Pframe,Ptitle,                                       //~1Aa8I~
			(AG.layoutMdpi ? R.layout.gamequestion_mdpi : R.layout.gamequestion),//~1Aa8I~
//  		true,false);                                           //~1Aa8I~//~1Aa9R~
    		false,false);                                          //~1Aa9I~
        swLocalGame=true;                                          //~1A55I~
        new ButtonAction(this,0,R.id.ChangeBKButtonLocal);         //~1AabI~
    }                                                              //~@@@2I~
//******************************                                   //~@@@2I~
	public GameQuestion (PartnerFrame pf)
//  {   super(pf,Global.resourceString("Game_Setup),true);         //~@@@2R~
    {                                                              //~@@@2R~
//        super(pf,AG.resource.getString(R.string.Title_GameQuestion),R.layout.gamequestion,true,false);//~@@@2R~//~1Aa8R~
          super(pf,AG.resource.getString(R.string.Title_GameQuestion),//~1Aa8I~
				(AG.layoutMdpi ? R.layout.gamequestion_mdpi : R.layout.gamequestion),//~1Aa8I~
//  			true,false);                                       //~1Aa8I~//~1Aa9R~
    			false,false);                                      //~1Aa9I~
		PF=pf;
//        Panel pa=new MyPanel();                                  //~2C26R~
//        add("Center",new Panel3D(pa));                           //~2C26R~
//        pa.setLayout(new GridLayout(0,2));                       //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Board_size")));//~2C26R~
//        pa.add(BoardSize=new FormTextField("19"));               //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Your_color")));//~2C26R~
//        pa.add(ColorChoice=new Choice());                        //~2C26R~
//        ColorChoice.setFont(Global.SansSerif);                   //~2C26R~
//        ColorChoice.add(Global.resourceString("Black"));         //~2C26R~
//        ColorChoice.add(Global.resourceString("White"));         //~2C26R~
//        ColorChoice.select(0);                                   //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Handicap")));  //~2C26R~
//        pa.add(Handicap=new FormTextField("0"));                 //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Total_Time__min_")));//~2C26R~
//        pa.add(TotalTime=new FormTextField("10"));               //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Extra_Time__min_")));//~2C26R~
//        pa.add(ExtraTime=new FormTextField("10"));               //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Moves_per_Extra_Time")));//~2C26R~
//        pa.add(ExtraMoves=new FormTextField("24"));              //~2C26R~
//        Panel pb=new MyPanel();                                  //~2C26R~
//        pb.add(new ButtonAction(this,Global.resourceString("Request")));//~2C26R~
//        pb.add(new ButtonAction(this,Global.resourceString("Cancel")));//~2C26R~
//        add("South",new Panel3D(pb));                            //~2C26R~
//        Global.setpacked(this,"gamequestion",300,400,pf);        //~2C26R~
    	setGameData();                                             //~@@@2I~
                                                                   //~@@@@I~
//        ButtonAction.init(this,0,R.id.Request);  //Request         //~@@@@R~//~@@@2R~
//        ButtonAction.init(this,0,R.id.Cancel);  //Cancel           //~@@@@R~//~@@@2R~
//        ButtonAction.init(this,0,R.id.Help);  //Help               //~@@@@R~//~@@@2R~
        new ButtonAction(this,0,R.id.Request);  //Request     //~@@@2I~
        new ButtonAction(this,0,R.id.Cancel);  //Cancel       //~@@@2I~
        new ButtonAction(this,0,R.id.Help);  //Help           //~@@@2I~
        new ButtonAction(this,0,R.id.ChangeBKButton);              //~1Aa7R~
		validate();
		show();
	}
    public void setGameData()                                      //~@@@2R~
    {                                                              //~@@@2I~
		String oldstr,board;int oldint;          //~@@@@I~//~@@@2R~
        YourName=new FormTextField(this,R.id.YourName,AG.YourName);//~@@@2R~
        YourName.setBackground(COLOR_YOURNAME);                //~@@@2I~
        if (AG.isChessBoard())                                     //~@@@2I~
        	board=AG.resource.getString(R.string.BoardType_Chess); //~@@@2I~
        else                                                       //~@@@2I~
        	board=AG.resource.getString(R.string.BoardType_Shogi); //~@@@2I~
        BoardType=new FormTextField(this,R.id.BoardType,board);    //~@@@2R~
        BoardType.setBackground(COLOR_YOURNAME);                   //~@@@2I~
		oldint=Prop.getPreference(PKEY_TOTAL_TIME,0);              //~@@@@M~//~@@@2M~
        TotalTime=new FormTextField(this,R.id.TotalTime,Integer.toString(oldint));//~@@@@R~//~@@@2R~
		oldint=Prop.getPreference(PKEY_EXTRA_TIME,DEFAULT_EXTRATIME);//~@@@@I~//~@@@2M~
        ExtraTime=new FormTextField(this,R.id.ExtraTime,Integer.toString(oldint));//~@@@@R~//~@@@2R~
		oldint=Prop.getPreference(PKEY_GAMEOVER,DEFAULT_GAMEOVER); //~@@@@R~//~@@@2M~
        Gameover=new FormTextField(this,R.id.Gameover,Integer.toString(oldint));//~@@@@R~//~@@@2R~
		oldint=Prop.getPreference(PKEY_GAMEOVER2,DEFAULT_GAMEOVER2);//~@@@2R~
        Gameover2=new FormTextField(this,R.id.Gameover2,Integer.toString(oldint));//~@@@2R~
		oldint=Prop.getPreference(PKEY_BISHOP,0);                  //~@@@@I~//~@@@2M~
        oldstr=putPieces(oldint);                     //~@@@@I~//~@@@2M~
        Bishop=new FormTextField(this,R.id.Bishop,oldstr);              //~@@@@R~//~@@@2R~
		oldint=Prop.getPreference(PKEY_KNIGHT,0);                  //~@@@@I~//~@@@2M~
        oldstr=putPieces(oldint);                     //~@@@@I~//~@@@2M~
        Knight=new FormTextField(this,R.id.Knight,oldstr);              //~@@@@R~//~@@@2R~
        Reflectable=new Checkbox(this,R.id.MoveOption);                              //~@@@@I~//~@@@2R~
        Reselectable=new Checkbox(this,R.id.Reselectable);         //~@@@2R~
        NotRemovable=new Checkbox(this,R.id.NotRemovable);         //~@@@2R~
        MoveFirst=new Checkbox(this,R.id.MoveFirst);                                  //~@@@@I~//~@@@2R~
		oldint=Prop.getPreference(PKEY_GAMEOPTIONS,GAMEOPT_MOVEFIRST);//~@@@2R~
        Reflectable.setState((oldint & GAMEOPT_REFLECTABLE)!=0);                        //~@@@@I~//~@@@2R~
        Reselectable.setState((oldint & GAMEOPT_RESELECTABLE)!=0); //~@@@2I~
        NotRemovable.setState((oldint & GAMEOPT_NOTREMOVABLE)!=0); //~@@@2R~
        MoveFirst.setState((oldint & GAMEOPT_MOVEFIRST)!=0);       //~@@@2R~
                                                                   //~1Aa7I~
        cbPosNotFix=new Checkbox(this,R.id.CBPosNotFix);           //~1Aa7M~
        cbPosNotFix.setState((oldint & GAMEOPT_POSNOTFIX)!=0);     //~1Aa7I~
                                                                   //~1Aa7R~
        if (swLocalGame)                                           //~1A55I~
            setGameDataLocal();                                     //~1A55I~
        else                                                       //~1A55I~
            setGameDataNonLocal();                                 //~1A55I~
	}                                                              //~@@@2R~
    private void setGameDataLocal()                                //~1A55I~
    {                                                              //~1A55I~
		String oldstr;                                             //~1A55I~
        int oldint;                                                //~1AabI~
        View v;                                                    //~1A55I~
		oldstr=Prop.getPreference(PKEY_LOCAL_OPPONENT_NAME,AG.resource.getString(R.string.DefaultOpponentName));//~1A55I~
        OpponentName=new FormTextField(this,R.id.OpponentName,oldstr);//~1A55I~
        v=findViewById(R.id.LocalOpponentNameLine);                //~1A55I~
        Component dmy=new Component(); //no callback(dialog has callback then cause invalidcast exception at Dialog:runOnUithread)//~1A55I~
        dmy.setVisibility(v,View.VISIBLE);                         //~1A55R~
//*BK position for localGame                                       //~1AabI~
		oldint=Prop.getPreference(PKEY_BISHOP_LOCAL,0);            //~1AabI~
        oldstr=putPieces(oldint);                                  //~1AabI~
        BishopLocal=new FormTextField(this,R.id.BishopLocal,oldstr);//~1AabI~
		oldint=Prop.getPreference(PKEY_KNIGHT_LOCAL,0);            //~1AabI~
        oldstr=putPieces(oldint);                                  //~1AabI~
        KnightLocal=new FormTextField(this,R.id.KnightLocal,oldstr);//~1AabI~
        v=findViewById(R.id.LocalBK);                              //~1AabI~
        dmy.setVisibility(v,View.VISIBLE);                         //~1AabI~
        v=findViewById(R.id.CBPosNotFix);                          //~1AabR~
        dmy.setVisibility(v,View.GONE);                            //~1AabI~
	}                                                              //~1A55I~
    private void setGameDataNonLocal()                             //~1A55I~
    {                                                              //~1A55I~
//        View v;                                                  //~1A55R~
//        v=findViewById(R.id.LocalOpponentNameLine);              //~1A55R~
//        setVisibility(v,View.GONE);                              //~1A55R~
	}                                                              //~1A55I~
	public void doAction (String o)
//  {	Global.notewindow(this,"gamequestion");	                   //~@@@@R~
    {                                                              //~@@@@I~
//  	if (Global.resourceString("Request").equals(o))            //~@@@@R~
        if (o.equals(AG.resource.getString(R.string.Request)))     //~@@@@I~
		{                                                          //~@@@@R~
        	if (!getGameData())                                    //~@@@2I~
            	return;                                            //~@@@2I~
//			PF.dorequest(s,col,h,tt,et,em);                        //~@@@@R~
			String c;                                              //~@@@2I~
        	if (boardsz==AG.BOARDSIZE_CHESS)                       //~@@@2I~
  				c=movefirst!=0?"w":"b";                            //~@@@2I~
        	else                                                   //~@@@2I~
  				c=movefirst!=0?"b":"w";                            //~@@@2I~
		    URunnable.setRunFuncSubthread(this,0/*deley*/,c/*parmobj*/,0/*parmint*/);//~@@@2I~
			setVisible(false); dispose();
		}
//		else if (Global.resourceString("Cancel").equals(o))        //~@@@@R~
        else if (o.equals(AG.resource.getString(R.string.Cancel))) //~@@@@I~
		{	setVisible(false); dispose();
		}
        else if (o.equals(AG.resource.getString(R.string.Help)))   //~2C30I~//~@@@@I~
		{                                                          //~2C30I~//~@@@@I~
			new HelpDialog(null,R.string.Help_GameQuestion);           //~2C30I~//~@@@@I~
		}                                                          //~2C30I~//~@@@@I~
        else if (o.equals(AG.resource.getString(R.string.ChangeBKButton)))//~1Aa7R~
		{                                                          //~1Aa7R~
			setupBK();                                             //~1Aa7R~
		}                                                          //~1Aa7R~
        else if (o.equals(AG.resource.getString(R.string.ChangeBKButtonLocal)))//~1AabI~
		{                                                          //~1AabI~
			setupBKLocal();                                        //~1AabI~
		}                                                          //~1AabI~
		else super.doAction(o);
	}
    @Override	//URunnableI                                       //~1Aa7R~
    //*********************************************************    //~@@@2I~
    public void runFunc(Object Pparmobj,int Pparmint)              //~@@@2I~
    {                                                              //~@@@2I~
    	if (Pparmint==FUNC_SETUPBK)                                //~1Aa7R~
        {                                                          //~1Aa7R~
	    	if (Dump.Y) Dump.println("GameQuestion runFunc:setupBK");//~1Aa7R~
            swLocalBK=false;                                       //~1AabI~
        	SetupBK.showDialog((GameQuestion)Pparmobj);            //~1Aa7R~
            return;                                                //~1Aa7R~
        }                                                          //~1Aa7R~
    	if (Pparmint==FUNC_SETUPBK_LOCAL)                          //~1AabI~
        {                                                          //~1AabI~
	    	if (Dump.Y) Dump.println("GameQuestion runFunc:setupBKLocal");//~1AabI~
            swLocalBK=true;                                        //~1AabI~
        	SetupBK.showDialog((GameQuestion)Pparmobj);            //~1AabI~
            return;                                                //~1AabI~
        }                                                          //~1AabI~
    	String c=(String)Pparmobj;
    	if (Dump.Y) Dump.println("GAmeQuestion runFunc call dorequest");//~@@@2I~
  		PF.dorequest(boardsz,c,gameover,gameover2,totaltime,extratime,bishop,knight,gameoptions);//~@@@2I~
    	if (Dump.Y) Dump.println("GameQuestion runFunc call dorequest end");//~@@@2I~//~1Aa7R~
    }                                                              //~@@@2I~
    //*********************************************************    //~@@@2I~
    public boolean getGameData()                                   //~@@@2I~
    {                                                              //~@@@2I~
//  		int s,h,tt,et,em;                                      //~@@@2I~
		    boardsz=AG.propBoardSize;                              //~@@@2I~
            String s="";                                           //~@@@2I~
			try                                                    //~@@@2I~
            {                                                      //~@@@2I~
//                s=Integer.parseInt(BoardSize.getText());         //~@@@2I~
//                h=Integer.parseInt(Handicap.getText());          //~@@@2I~
//                tt=Integer.parseInt(TotalTime.getText());        //~@@@2I~
//                et=Integer.parseInt(ExtraTime.getText());        //~@@@2I~
//                em=Integer.parseInt(ExtraMoves.getText());       //~@@@2I~
                s=Gameover.getText();                              //~@@@2I~
                if (s.trim().equals(""))                           //~@@@2I~
	                gameover=0;                                    //~@@@2I~
                else                                               //~@@@2I~
	                gameover=Integer.parseInt(s);                  //~@@@2I~
            	if (gameover==0)                                   //~1022I~
                	gameover=DEFAULT_GAMEOVER;                    //~1022I~
                s=Gameover2.getText();                             //~@@@2I~
                if (s.trim().equals(""))                           //~@@@2I~
	                gameover2=0;                                   //~@@@2I~
                else                                               //~@@@2I~
	                gameover2=Integer.parseInt(s);                 //~@@@2I~
            	if (gameover2==0)                                  //~1022I~
                	gameover2=DEFAULT_GAMEOVER2;                    //~1022I~
                bishop=getPieces(Bishop.getText());                //~@@@2R~
                knight=getPieces(Knight.getText());                //~@@@2R~
                if (swLocalGame)                                   //~1AabI~
                {                                                  //~1AabI~
	                bishopLocal=getPieces(BishopLocal.getText());  //~1AabI~
    	            knightLocal=getPieces(KnightLocal.getText());  //~1AabI~
                }                                                  //~1AabI~
                s=TotalTime.getText();                             //~@@@2I~
                if (s.trim().equals(""))                           //~@@@2I~
	                totaltime=0;                                   //~@@@2I~
                else                                               //~@@@2I~
                	totaltime=Integer.parseInt(s);                 //~@@@2I~
                s=ExtraTime.getText();                             //~@@@2I~
                if (s.trim().equals(""))                           //~@@@2I~
	                extratime=0;                                          //~@@@2I~
                else                                               //~@@@2I~
                	extratime=Integer.parseInt(s);                        //~@@@2I~
                reflectable=Reflectable.getState()?GAMEOPT_REFLECTABLE:0;//~@@@2R~
                reselectable=Reselectable.getState()?GAMEOPT_RESELECTABLE:0;//~@@@2I~
                notremovable=NotRemovable.getState()?GAMEOPT_NOTREMOVABLE:0;//~@@@2R~
                movefirst=MoveFirst.getState()?GAMEOPT_MOVEFIRST:0;//~@@@2R~
                gameoptions=reflectable+reselectable+movefirst+notremovable;//~@@@2R~
                                                                   //~1Aa7R~
                posnotfix=cbPosNotFix.getState()?GAMEOPT_POSNOTFIX:0;//~1Aa7I~
                gameoptions|=posnotfix;                            //~1Aa7I~
                                                                   //~1Aa7R~
				Prop.putPreference(PKEY_GAMEOVER,gameover);        //~@@@2I~
				Prop.putPreference(PKEY_GAMEOVER2,gameover2);      //~@@@2I~
				Prop.putPreference(PKEY_BISHOP,bishop);            //~@@@2I~
				Prop.putPreference(PKEY_KNIGHT,knight);            //~@@@2I~
                if (swLocalGame)                                   //~1AabI~
                {                                                  //~1AabI~
					Prop.putPreference(PKEY_BISHOP_LOCAL,bishopLocal);//~1AabI~
					Prop.putPreference(PKEY_KNIGHT_LOCAL,knightLocal);//~1AabI~
                }                                                  //~1AabI~
				Prop.putPreference(PKEY_TOTAL_TIME,totaltime);     //~@@@2I~
				Prop.putPreference(PKEY_EXTRA_TIME,extratime);            //~@@@2I~
				Prop.putPreference(PKEY_GAMEOPTIONS,gameoptions);  //~@@@2R~
			}                                                      //~@@@2I~
			catch (NumberFormatException ex)                       //~@@@2I~
//  		{	new Message(PF,Global.resourceString("Illegal_Number_Format_"));//~@@@2I~
            {                                                      //~@@@2I~
            	AView.showToastLong(R.string.ParmErr,s);           //~@@@2I~
				return false;                                      //~@@@2R~
			}                                                      //~@@@2I~
        	if (swLocalGame)                                       //~1A55I~
            	getGameDataLocal();                                 //~1A55I~
//            if (s<5) s=5;                                        //~@@@2I~
//            if (s>29) s=29;                                      //~@@@2I~
//            if (h>9) h=9;                                        //~@@@2I~
//            if (h<0) h=0;                                        //~@@@2I~
//            if (tt<1) tt=1;                                      //~@@@2I~
//            if (et<0) et=0;                                      //~@@@2I~
//            if (em<1) em=1;                                      //~@@@2I~
//            String col="b";                                      //~@@@2I~
//            if (ColorChoice.getSelectedIndex()!=0) col="w";      //~@@@2I~
			if (gameover>boardsz                                   //~@@@2I~
			||  gameover<=0                                        //~@@@2I~
			||  gameover2<=0                                       //~@@@2I~
			||  gameover2>(gameover==0?boardsz:gameover)           //~@@@2I~
            ||  (bishop & 255)>boardsz                             //~@@@2I~
            ||  (knight & 255)>boardsz                             //~@@@2I~
            ||  (bishop & 255)+(knight & 255)>boardsz              //~@@@2I~
            ||  ((bishop>>8) & (knight >>8))!=0	//duplicated       //~@@@2I~
            )                                                      //~@@@2I~
            {                                                      //~@@@2I~
				AView.showToastLong(R.string.ParmErrPieceCount);   //~@@@2I~
				return false;                                      //~@@@2R~
            }                                                      //~@@@2I~
            return true;                                           //~@@@2I~
    }                                                              //~@@@2I~
    //*********************************************************    //~1A55I~
    private boolean getGameDataLocal()                             //~1A55I~
    {                                                              //~1A55I~
        String s=OpponentName.getText();                                  //~1A55I~
        if (s==null || s.trim().equals(""))                        //~1A55I~
        	s=AG.resource.getString(R.string.DefaultOpponentName); //~1A55I~
        Prop.putPreference(PKEY_LOCAL_OPPONENT_NAME,s);            //~1A55I~
        AG.LocalOpponentName=s;                                    //~1A55R~
        return true;                                               //~1A55I~
    }                                                              //~1A55I~
//************************************************************     //~v108I~//~@@@2M~
//*get bishop/knight arrangement                                   //~@@@@I~//~@@@2M~
//*position bit and count                                          //~v108I~//~@@@2M~
//************************************************************     //~v108I~//~@@@2M~
	public static int getPieces(String Ppieces)                    //~v108I~//~@@@2M~
    {                                                              //~1AabI~
		return getPieces(Ppieces,AG.propBoardSize);                //~1AabI~
    }                                                              //~1AabI~
	public static int getPieces(String Ppieces,int Pboardsz)       //~1AabI~
	{                                                              //~v108I~//~@@@2M~
        String[] sa=Ppieces.split(",");                            //~v108I~//~@@@2M~
        int jj=0,pos=0;                                            //~v108I~//~@@@2M~
        for (int ii=0;ii<sa.length;ii++)                           //~v108I~//~@@@2M~
        {                                                          //~v108I~//~@@@2M~
        	String s=sa[ii].trim();                                //~v108I~//~@@@2M~
            if (s.equals(""))                                      //~v108I~//~@@@2M~
            	continue;                                          //~v108I~//~@@@2M~
			try                                                    //~v108I~//~@@@2M~
            {                                                      //~v108I~//~@@@2M~
                int val=Integer.parseInt(s);                           //~v108I~//~@@@2M~
//              if (val>0 && val<=AG.propBoardSize)                    //~v108I~//~@@@@R~//~@@@2M~//~1AabR~
                if (val>0 && val<=Pboardsz)                        //~1AabI~
                {                                                  //~v108I~//~@@@2M~
                	if ((pos & (1<<(val-1)))==0)                   //~v108R~//~@@@2M~
                    {                                              //~v108I~//~@@@2M~
                		pos|=(1<<(val-1));                         //~v108I~//~@@@2M~
	                	jj++;                                      //~v108R~//~@@@2M~
                    }                                              //~v108I~//~@@@2M~
                }                                                  //~v108I~//~@@@2M~
			}                                                      //~v108I~//~@@@2M~
			catch (NumberFormatException ex)                       //~v108I~//~@@@2M~
			{                                                      //~v108I~//~@@@2M~
            	AView.showToastLong(R.string.ParmErr,s);           //~v108I~//~@@@2M~
			}                                                      //~v108I~//~@@@2M~
        }                                                          //~v108I~//~@@@2M~
        return (pos<<8)+jj;                                        //~v108I~//~@@@2M~
	}                                                              //~v108I~//~@@@2M~
//************************************************************     //~v108I~//~@@@2M~
	public static String putPieces(int Ppieces)                    //~v108I~//~@@@2M~
    {                                                              //+1AabI~
		return putPieces2(Ppieces,AG.propBoardSize);               //+1AabI~
                                                                   //+1AabI~
    }                                                              //+1AabI~
	public static String putPieces2(int Ppieces,int Pboardsz)      //+1AabI~
	{                                                              //~v108I~//~@@@2M~
//      int boardsz=AG.propBoardSize;                                  //~v108I~//~@@@2M~//+1AabR~
        int boardsz=Pboardsz;                                      //+1AabI~
        int pos=Ppieces >> 8;                                      //~v108I~//~@@@2M~
                                                                   //~v108I~//~@@@2M~
        String pieces="";                                          //~v108I~//~@@@2M~
        for (int ii=0;ii<boardsz;ii++)                             //~v108R~//~@@@2M~
        {                                                          //~v108I~//~@@@2M~
        	if ((pos & (1<<ii))!=0)                                //~v108R~//~@@@2M~
            {                                                      //~v108I~//~@@@2M~
                if (pieces.equals(""))                             //~v108I~//~@@@2M~
                	pieces=Integer.toString(ii+1);                 //~v108R~//~@@@2M~
                else                                               //~v108I~//~@@@2M~
                	pieces+=","+Integer.toString(ii+1);            //~v108R~//~@@@2M~
            }                                                      //~v108I~//~@@@2M~
        }                                                          //~v108I~//~@@@2M~
        return pieces;                                             //~v108I~//~@@@2M~
	}                                                              //~v108I~//~@@@2M~
	public static String putPieces(int Ppieces,int Pzero)          //~@@@2I~
    {                                                              //~@@@2I~
		String s=putPieces(Ppieces);                               //~@@@2I~
        if (s.equals(""))                                          //~@@@2I~
        	s=Integer.toString(Pzero);
        return s;
    }                                                              //~@@@2I~
//************************************************************     //~1Aa7R~
	public void setupBK()                                          //~1Aa7R~
	{                                                              //~1Aa7R~
	    getGameData();
		URunnable.setRunFuncDirect(this,this/*parmobj*/,FUNC_SETUPBK);//~1Aa7R~
    }                                                              //~1Aa7R~
//************************************************************     //~1AabI~
	public void setupBKLocal()                                     //~1AabI~
	{                                                              //~1AabI~
	    getGameData();                                             //~1AabI~
		URunnable.setRunFuncDirect(this,this/*parmobj*/,FUNC_SETUPBK_LOCAL);//~1AabI~
    }                                                              //~1AabI~
//************************************************************     //~1Aa7R~
	public int getBoardSize()                                      //~1Aa7R~
	{                                                              //~1Aa7R~
        return boardsz;                                            //~1Aa7R~
    }                                                              //~1Aa7R~
	public int getPosB()                                           //~1Aa7R~
	{                                                              //~1Aa7R~
	    if (swLocalBK)                                             //~1AabI~
        	return bishopLocal;                                    //~1AabI~
        return bishop;                                             //~1Aa7R~
    }                                                              //~1Aa7R~
	public int getPosK()	//get Knight position                  //~1Aa7R~
	{                                                              //~1Aa7R~
	    if (swLocalBK)                                             //~1AabI~
        	return knightLocal;                                    //~1AabI~
        return knight;                                             //~1Aa7R~
    }                                                              //~1Aa7R~
	public int getPosB1()                                          //~1AabI~
	{                                                              //~1AabI~
        return bishop;                                             //~1AabI~
    }                                                              //~1AabI~
	public int getPosK1()	//get Knight position                  //~1AabI~
	{                                                              //~1AabI~
        return knight;                                             //~1AabI~
    }                                                              //~1AabI~
//************************************************************     //~1Aa7R~
	public void changedBK(int PposB,int PposK)                     //~1Aa7R~//~1AabR~
	{                                                              //~1Aa7R~
    	String pos;                                                //~1Aa7R~
      if (swLocalBK)  //2nd player of local                        //~1AabR~
      {                                                            //~1AabM~
        bishopLocal=PposB;                                         //~1AabI~
        knightLocal=PposK;                                         //~1AabI~
		pos=putPieces(bishopLocal);                                //~1AabR~
        BishopLocal.setText(pos);                                  //~1AabR~
		pos=putPieces(knightLocal);                                //~1AabR~
        KnightLocal.setText(pos);                                  //~1AabR~
      }                                                            //~1AabM~
      else                                                         //~1AabM~
      {                                                            //~1AabM~
        bishop=PposB;                                              //~1Aa7R~
        knight=PposK;                                              //~1Aa7R~
		pos=putPieces(bishop);                                     //~1Aa7R~
        Bishop.setText(pos);                                       //~1Aa7R~
		pos=putPieces(knight);                                     //~1Aa7R~
        Knight.setText(pos);                                       //~1Aa7R~
      }                                                            //~1AabI~
    }                                                              //~1Aa7R~
//************************************************************     //~1Aa7R~//~1AabI~
	public boolean chkLocalBK()                     //~1Aa7R~      //~1AabI~
	{                                                              //~1Aa7R~//~1AabI~
    	boolean rc=true;                                           //~1AabI~
        if (bishopLocal!=0)                                        //~1AabI~
        {                                                          //~1AabI~
        	if ((bishopLocal & 255)!=(bishop & 255))               //~1AabI~
            	rc=false;                                          //~1AabI~
        }                                                          //~1AabI~
        if (knightLocal!=0)                                        //~1AabI~
        {                                                          //~1AabI~
        	if ((knightLocal & 255)!=(knight & 255))               //~1AabI~
            	rc=false;                                          //~1AabI~
        }                                                          //~1AabI~
        if (!rc)                                                   //~1AabI~
        {                                                          //~1AabI~
            AView.showToast(R.string.ErrBKCountLocal);             //~1AabI~
        }                                                          //~1AabI~
        return rc;                                                 //~1AabI~
    }                                                              //~1Aa7R~//~1AabI~
}

