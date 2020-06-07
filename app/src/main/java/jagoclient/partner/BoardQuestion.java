//*CID://+1AhgR~:                             update#=   20;       //+1AhgR~
//**********************************************************************//~v101I~
//1Ahg 2020/06/03 help text;string to helptext\
//1Aab 2015/04/22 1Aa7 for local game                              //~1AabI~
//1Aa9 2015/04/21 (BUG)Button of GameQuestion except Help dose not work after ChangeBK button//~1Aa8I~
//                GameQuestion(ModalDialog) run on subthread at connection complete.//~1Aa8I~
//                Modal latch is posted when ChangeBK button pushed,but dose not dismiss dialog//~1Aa8I~
//                Push button except help do latch.countdown which is already posted(subthread was already posted)//~1Aa8I~
//                -->GameQuestionDialog is not requred as modal.   //~1Aa8I~
//                (LocalGameQuestion is not subthread but reset modal also)//~1Aa8I~
//                !! Modal option is assuming Button click follows dismiss dialog.//~1Aa8I~
//                !! Check class extended CloseDialog(modal=true)  //~1Aa8I~
//1Aa8 2015/04/20 put in layout the gamequestion/boardquestion for mdpi//~1Aa8I~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa9I~
//101e 2013/02/08 findViewById to Container(super of Frame and Dialog)//~v101I~
//**********************************************************************//~v101I~
package jagoclient.partner;

//import com.Ahsv.awt.GridLayout;                                  //~2C26R~
import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;                                                 //~1AabR~
import com.Ahsv.awt.Checkbox;
//import jagoclient.gui.ButtonAction;                              //~2C26R~
import jagoclient.Dump;
import jagoclient.dialogs.HelpDialog;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.CloseDialog;
//import jagoclient.gui.FormTextField;                             //~2C26R~
//import jagoclient.gui.MyLabel;                                   //~2C26R~
//import jagoclient.gui.MyPanel;                                   //~2C26R~
//import jagoclient.gui.Panel3D;                                   //~2C26R~
import jagoclient.gui.FormTextField;

//import java.awt.GridLayout;
//import java.awt.Panel;
//import java.awt.TextField;

/**
Question to accept or decline a game with received parameters.
*/

public class BoardQuestion extends CloseDialog
{                                                                  //~2C29R~
//	int BoardSize,Handicap,TotalTime,ExtraTime,ExtraMoves;         //~2C29I~
 	int BoardSize,Bishop,TotalTime,Knight,Reflaectable,Reselectable,Gameover,Gameover2,ExtraTime,GameOptions;           //~2C29I~//~2C30R~//~3107R~
    int NotRemovable;                                              //~3123I~
	String ColorChoice;
	PartnerFrame PF;
    FormTextField YourName,BoardType;                              //~3119R~
    private ButtonAction btnChangeBK;                              //~1Aa7R~
    private FormTextField ftfBishop,ftfKnight;                     //~1Aa7I~
    private int bishop,knight;                                     //~1Aa7I~
	public BoardQuestion (PartnerFrame pf,
//  	int s, String c, int h, int tt, int et, int em)            //~2C29R~
    	int s, String c, int Pgameover,int Pgameover2,int tt,int et,int Pbishop,int Pknight,int Pgameoptions,String Prequestername)//~2C29I~//~3104R~//~3107R~//~3119R~
//  {	super(pf,Global.resourceString("Game_Setup"),true);        //~3118R~
    {                                                              //~3118I~
//      super(pf,AG.resource.getString(R.string.Title_BoardQuestion),R.layout.boardquestion,true,false);//~@@@2I~//~3118R~//~3123R~//~1Aa8R~
        super(pf,AG.resource.getString(R.string.Title_BoardQuestion),//~1Aa8I~
				(AG.layoutMdpi ? R.layout.boardquestion_mdpi : R.layout.boardquestion),//~1Aa8I~
//  			true,false);                                       //~1Aa9R~
    			false/*non modal*/,false);                         //~1Aa9R~
		PF=pf;
//        BoardSize=s; Handicap=h; TotalTime=tt; ExtraTime=et;     //~2C29R~
//        ExtraMoves=em; ColorChoice=c;                            //~2C29R~
        BoardSize=s; TotalTime=tt; Bishop=Pbishop; ExtraTime=et;                //~2C30R~//~3104R~
        Knight=Pknight; ColorChoice=c;                             //~2C29I~
        Gameover=Pgameover;                                        //~3107R~
        Gameover2=Pgameover2;                                      //~3107I~
        GameOptions=Pgameoptions;                                  //~3107R~
//        Panel pa=new MyPanel();                                  //~2C26R~
//        add("Center",new Panel3D(pa));                           //~2C26R~
//        TextField t;                                             //~2C26R~
//        pa.setLayout(new GridLayout(0,2));                       //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Board_size")));//~2C26R~
//        pa.add(t=new FormTextField(""+s));                       //~2C26R~
//        t.setEditable(false);                                    //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Partners_color")));//~2C26R~
//        pa.add(t=new FormTextField(c));                          //~2C26R~
//        t.setEditable(false);                                    //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Handicap")));  //~2C26R~
//        pa.add(t=new FormTextField(""+h));                       //~2C26R~
//        t.setEditable(false);                                    //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Total_Time__min_")));//~2C26R~
//        pa.add(t=new FormTextField(""+tt));                      //~2C26R~
//        t.setEditable(false);                                    //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Extra_Time__min_")));//~2C26R~
//        pa.add(t=new FormTextField(""+et));                      //~2C26R~
//        t.setEditable(false);                                    //~2C26R~
//        pa.add(new MyLabel(Global.resourceString("Moves_per_Extra_Time")));//~2C26R~
//        pa.add(t=new FormTextField(""+em));                      //~2C26R~
//        t.setEditable(false);                                    //~2C26R~
//        Panel pb=new MyPanel();                                  //~2C26R~
//        pb.add(new ButtonAction(this,Global.resourceString("Accept")));//~2C26R~
//        pb.add(new ButtonAction(this,Global.resourceString("Decline")));//~2C26R~
//        add("South",new Panel3D(pb));                            //~2C26R~
//        Global.setpacked(this,"boardquestion",300,400,pf);       //~2C26R~
//        YourName=new FormTextField(R.id.YourName,Prequestername);  //~3119I~//~v101R~
        YourName=new FormTextField(this,R.id.YourName,Prequestername);//~v101I~
        YourName.setBackground(GameQuestion.COLOR_YOURNAME);       //~3119I~
        String board;                                              //~3119I~
        if (BoardSize==AG.BOARDSIZE_CHESS)                         //~3119I~
        	board=AG.resource.getString(R.string.BoardType_Chess); //~3119I~
        else                                                       //~3119I~
        	board=AG.resource.getString(R.string.BoardType_Shogi); //~3119I~
//        BoardType=new FormTextField(R.id.BoardType,board);          //~3119R~//~v101R~
        BoardType=new FormTextField(this,R.id.BoardType,board);    //~v101I~
        BoardType.setBackground(GameQuestion.COLOR_YOURNAME);      //~3119I~
//        new FormTextField(R.id.TotalTime,Integer.toString(TotalTime));                     //~2C29I~//~2C30M~//~3104R~//~v101R~
//        new FormTextField(R.id.ExtraTime,Integer.toString(ExtraTime));//~3104R~//~v101R~
//        new FormTextField(R.id.Gameover,Integer.toString(Gameover));               //~2C29I~//~2C30R~//~3104R~//~v101R~
//        new FormTextField(R.id.Gameover2,Integer.toString(Gameover2));//~3107I~//~v101R~
//        new FormTextField(R.id.Bishop,GameQuestion.putPieces(Bishop,0));         //~2C30R~//~3104R~//~3110R~//~3120R~//~v101R~
//        new FormTextField(R.id.Knight,GameQuestion.putPieces(Knight,0));               //~2C29I~//~2C30R~//~3104R~//~3110R~//~3120R~//~v101R~
        new FormTextField(this,R.id.TotalTime,Integer.toString(TotalTime));//~v101I~
        new FormTextField(this,R.id.ExtraTime,Integer.toString(ExtraTime));//~v101I~
        new FormTextField(this,R.id.Gameover,Integer.toString(Gameover));//~v101I~
        new FormTextField(this,R.id.Gameover2,Integer.toString(Gameover2));//~v101I~
      ftfBishop=                                                   //~1Aa7I~
//      new FormTextField(this,R.id.Bishop,GameQuestion.putPieces(Bishop,0));//~v101I~//~1AabR~
        new FormTextField(this,R.id.Bishop,GameQuestion.putPieces(Bishop));//~1AabI~
      ftfKnight=                                                   //~1Aa7I~
//      new FormTextField(this,R.id.Knight,GameQuestion.putPieces(Knight,0));//~v101I~//~1AabR~
        new FormTextField(this,R.id.Knight,GameQuestion.putPieces(Knight));//~1AabI~
//        new Checkbox(R.id.MoveOption).setState((GameOptions & GameQuestion.GAMEOPT_REFLECTABLE)!=0);                 //~2C29I~//~3104R~//~3107R~//~3118R~//~v101R~
//        new Checkbox(R.id.Reselectable).setState((GameOptions & GameQuestion.GAMEOPT_RESELECTABLE)!=0);//~3107I~//~3118R~//~v101R~
//        new Checkbox(R.id.NotRemovable).setState((GameOptions & GameQuestion.GAMEOPT_NOTREMOVABLE)!=0);//~3123I~//~v101R~
        new Checkbox(this,R.id.MoveOption).setState((GameOptions & GameQuestion.GAMEOPT_REFLECTABLE)!=0);//~v101I~
        new Checkbox(this,R.id.Reselectable).setState((GameOptions & GameQuestion.GAMEOPT_RESELECTABLE)!=0);//~v101I~
        new Checkbox(this,R.id.NotRemovable).setState((GameOptions & GameQuestion.GAMEOPT_NOTREMOVABLE)!=0);//~v101I~
        new Checkbox(this,R.id.CBPosNotFix).setState((GameOptions & GameQuestion.GAMEOPT_POSNOTFIX)!=0);//~1Aa7I~
        boolean movefirst;
        if (BoardSize==AG.BOARDSIZE_CHESS)                       //~@@@2I~//~3118I~
  			movefirst=ColorChoice.equals("w");                     //~3118I~
        else                                                   //~@@@2I~//~3118I~
  			movefirst=ColorChoice.equals("b");                     //~3118I~
//        new Checkbox(R.id.PartnerMoveFirst).setState(movefirst);         //~2C29I~//~3104R~//~3118R~//~v101R~
        new Checkbox(this,R.id.PartnerMoveFirst).setState(movefirst);//~v101I~
//        ButtonAction.init(this,0,R.id.Accept);                   //~2C30I~//~3118R~//~v101R~
//        ButtonAction.init(this,0,R.id.Decline);                  //~2C30I~//~3118R~//~v101R~
//        ButtonAction.init(this,0,R.id.Help);                     //~2C30I~//~3118R~//~v101R~
        new ButtonAction(this,0,R.id.Accept);                   //~2C30I~//~3118R~//~3208R~//~v101I~
        new ButtonAction(this,0,R.id.Decline);                  //~2C30I~//~3118R~//~3208R~//~v101I~
        new ButtonAction(this,0,R.id.Help);                     //~2C30I~//~3118R~//~3208R~//~v101I~
        btnChangeBK=new ButtonAction(this,0,R.id.ChangeBKButton);  //~1Aa7I~
        btnChangeBK.setEnabled((GameOptions & GameQuestion.GAMEOPT_POSNOTFIX)!=0);//~1Aa7I~
		validate();
		show();
	}
	
	public void doAction (String o)
	{                                                              //~2C26R~
//        Global.notewindow(this,"boardquestion");                 //~2C26I~
//  	if (Global.resourceString("Accept").equals(o))             //~2C30R~
        if (o.equals(AG.resource.getString(R.string.Accept)))     //~@@@@I~//~2C30I~
  		{                                                          //~2C30R~
//  		PF.doboard(BoardSize,ColorChoice,Handicap,             //~2C30I~
//  			TotalTime,ExtraTime,ExtraMoves);                   //~2C30R~
            if ((GameOptions & GameQuestion.GAMEOPT_POSNOTFIX)!=0) //~1Aa7M~
				getGameData();                                     //~1Aa7M~
            else                                                   //~1Aa7I~
            {                                                      //~1Aa7I~
            	bishop=Bishop;	//same as requester                //~1Aa7I~
            	knight=Knight;                                     //~1Aa7I~
            }                                                      //~1Aa7I~
  			PF.doboard(BoardSize,ColorChoice,Gameover,Gameover2,    //~3107R~
//			TotalTime,ExtraTime,Bishop,Knight,GameOptions);                              //~3103R~//~3104R~//~3107R~//~1Aa7R~
  			TotalTime,ExtraTime,Bishop,Knight,GameOptions,bishop,knight);//~1Aa7I~
			setVisible(false); dispose();
		}
//		else if (Global.resourceString("Decline").equals(o))       //~2C30R~
        else if (o.equals(AG.resource.getString(R.string.Decline)))//~2C30I~
		{	PF.declineboard();
			setVisible(false); dispose();
		}
        else if (o.equals(AG.resource.getString(R.string.Help)))   //~2C30I~
		{                                                          //~2C30I~
//			new HelpDialog(null,R.string.Help_BoardQuestion);           //~2C30I~//+1AhgR~
  			new HelpDialog(null,R.string.Title_BoardQuestion,"BoardQuestion");//+1AhgI~
		}                                                          //~2C30I~
        else if (o.equals(AG.resource.getString(R.string.ChangeBKButton)))//~1Aa7I~
		{                                                          //~1Aa7I~
			setupBK();                                             //~1Aa7I~
		}                                                          //~1Aa7I~
		else super.doAction(o);
	}
//************************************************************     //~1Aa7I~
	static private BoardQuestion tempBQ;                           //~1Aa7I~
	private void setupBK()                                         //~1Aa7I~
	{                                                              //~1Aa7I~
	    getGameData();                                             //~1Aa7I~
    	tempBQ=this;                                               //~1Aa7I~
    	if (AG.isMainThread())                                     //~1Aa7I~
        {                                                          //~1Aa7I~
        	showDialogBK();                                        //~1Aa7I~
        }                                                          //~1Aa7I~
        else                                                       //~1Aa7I~
        {                                                          //~1Aa7I~
    		Runnable r=new Runnable()                                       //~1Aa7I~
            		{                                              //~1Aa7I~
                    	@Override                                  //~1Aa7I~
                        public void run()                          //~1Aa7I~
                        {                                          //~1Aa7I~
				        	showDialogBK();                        //~1Aa7I~
                        }                                          //~1Aa7I~
                    };                                             //~1Aa7I~
            runOnUiThread(r);                                      //~1Aa7I~
        }                                                          //~1Aa7I~
    }                                                              //~1Aa7I~
//************************************************************     //~1Aa7I~
	private void getGameData()                                     //~1Aa7I~
	{                                                              //~1Aa7I~
    	String bk;                                                 //~1Aa7I~
      	bk=ftfBishop.getText();                                    //~1Aa7I~
        bishop=GameQuestion.getPieces(bk,BoardSize);                  //~1Aa7I~//~1AabR~
      	bk=ftfKnight.getText();                                    //~1Aa7I~
        knight=GameQuestion.getPieces(bk,BoardSize);                  //~1Aa7I~//~1AabR~
    }                                                              //~1Aa7I~
//************************************************************     //~1Aa7I~
	private void showDialogBK()                                    //~1Aa7I~
	{                                                              //~1Aa7I~
	    if (Dump.Y) Dump.println("BoardQuestion showBKDialog");    //~1Aa7I~
        SetupBK.showDialog(tempBQ);                                //~1Aa7I~
        tempBQ=null;	//for GC                                   //~1Aa7I~
    }                                                              //~1Aa7I~
//************************************************************     //~1Aa7I~
	public int getBoardSize()                                      //~1Aa7I~
	{                                                              //~1Aa7I~
        return BoardSize;                                          //~1Aa7I~
    }                                                              //~1Aa7I~
	public int getPosB()                                           //~1Aa7I~
	{                                                              //~1Aa7I~
        return bishop;                                             //~1Aa7I~
    }                                                              //~1Aa7I~
	public int getPosK()	//get Knight position                  //~1Aa7I~
	{                                                              //~1Aa7I~
        return knight;                                             //~1Aa7I~
    }                                                              //~1Aa7I~
	public int getPosB1()	//1st players position                 //~1AabI~
	{                                                              //~1AabI~
        return Bishop;                                             //~1AabI~
    }                                                              //~1AabI~
	public int getPosK1()	//get Knight position                  //~1AabI~
	{                                                              //~1AabI~
        return Knight;                                             //~1AabI~
    }                                                              //~1AabI~
//************************************************************     //~1Aa7I~
	public boolean changedBK(int PposB,int PposK)                  //~1Aa7R~
	{                                                              //~1Aa7I~
    	String pos;                                                //~1Aa7I~
        bishop=PposB;                                              //~1Aa7I~
        knight=PposK;                                              //~1Aa7I~
        if (Dump.Y) Dump.println("BoardQuestion changedBK init Bishop="+Integer.toHexString(Bishop)+",Knight="+Integer.toHexString(Knight)//~1Aa7I~
        		+"changed Bishop="+Integer.toHexString(bishop)+",Knight="+Integer.toHexString(knight));//~1Aa7I~
        if ((Bishop & 255)!=(bishop & 255)                         //~1Aa7I~
        ||  (Knight & 255)!=(knight & 255))                        //~1Aa7I~
        {                                                          //~1Aa7I~
            String msg=AG.resource.getString(R.string.ErrBKCountChanged,Bishop&255,Knight&255);//~1Aa7R~
            AView.showToast(msg);                                  //~1Aa7I~
			return false;                                          //~1Aa7I~
        }                                                          //~1Aa7I~
		pos=GameQuestion.putPieces2(bishop,BoardSize);                        //~1Aa7M~//~1AabR~
        ftfBishop.setText(pos);                                    //~1Aa7M~
		pos=GameQuestion.putPieces2(knight,BoardSize);                        //~1Aa7M~//~1AabR~
        ftfKnight.setText(pos);                                    //~1Aa7M~
		return true;                                               //~1Aa7I~
    }                                                              //~1Aa7I~
}

