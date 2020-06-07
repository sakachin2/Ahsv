//*CID://+1Aa7R~:                                   update#=  128; //~1Aa7R~
//****************************************************************************//~@@@1I~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//1A50 2014/10/27 mdpi & tablet support                            //~1A50I~
//1033 2013/03/07 gameover2 msg                                    //~1033I~
//1022 2013/02/18 set small button should be portrait only         //~1022I~
//101f 2013/02/09 drawPiece NPE                                    //~v101I~
//*@@@1 20110430 add "resign"/"pass" to Finish_Game menu(Pass is duplicated to "Set")//~@@@1R~
//****************************************************************************//~@@@1I~
package jagoclient.board;

//import com.Ahsv.awt.BorderLayout;                                //~@@@1R~
//import com.Ahsv.awt.CardLayout;                                  //~@@@1R~
//import com.Ahsv.awt.GridLayout;                                  //~@@@1R~
import android.view.View;
import android.widget.ImageView;

import com.Ahsv.AG;
import com.Ahsv.Alert;
import com.Ahsv.R;                                                 //+1Aa7R~
import com.Ahsv.awt.Component;
import com.Ahsv.awt.Label;                                         //~@@@1R~
import com.Ahsv.awt.Menu;                                          //~@@@1R~
import com.Ahsv.awt.Panel;                                         //~@@@1R~
import com.Ahsv.awt.TextArea;                                    //~@@@1R~
import com.Ahsv.awt.Color;
import com.Ahsv.awt.Image;
import com.Ahsv.awt.TextField;
import com.Ahsv.awt.WindowEvent;                                   //~@@@1R~
import jagoclient.Dump;
import jagoclient.Global;
//import jagoclient.gui.CheckboxMenuItemAction;
//import jagoclient.gui.HistoryTextField;                          //~@@@1R~
//import jagoclient.gui.MenuItemAction;
//import jagoclient.gui.MyLabel;                                   //~@@@1R~
//import jagoclient.gui.MyMenu;
import jagoclient.dialogs.Message;
import jagoclient.gui.ButtonAction;
//import jagoclient.gui.MyMenu;                                    //~@@@1R~
//import jagoclient.gui.MyPanel;                                   //~@@@1R~
import jagoclient.gui.MyTextArea;                                                           //~1116I~//~1117M~//~@@@1R~
//import jagoclient.gui.Panel3D;                                   //~@@@1R~
//import jagoclient.gui.SimplePanel;                               //~@@@1R~
//import java.awt.BorderLayout;                                    //~1416R~
//import java.awt.CardLayout;                                      //~1416R~
//import java.awt.CheckboxMenuItem;                                //~1416R~
//import java.awt.Component;                                       //~1416R~
//import java.awt.GridLayout;                                      //~1416R~
//import java.awt.Label;                                           //~1416R~
//import java.awt.Menu;                                            //~1416R~
//import java.awt.MenuBar;                                         //~1416R~
//import java.awt.Panel;                                           //~1416R~
//import java.awt.TextArea;                                        //~1416R~
//import java.awt.event.WindowEvent;                               //~1416R~
//import rene.gui.IconBar;                                         //~1221R~
import jagoclient.gui.MyLabel;
import jagoclient.partner.GameQuestion;
import jagoclient.sound.JagoSound;

//import com.Ahsv.rene.gui.IconBar;                                //~1221I~//~@@@1R~

/**
A subclass of GoFrame, which has a different menu layout.
Moreover, it contains a method to add a comment from an
external source (a distributor).
*/

public class ConnectedGoFrame extends GoFrame
//{   protected boolean TimerInTitle,ExtraSendField,BigTimer;      //~@@@2R~
{                                                                  //~@@@2I~
//    protected HistoryTextField SendField;                        //~@@@1R~
//	private static final int WIN_MSG_TEXTSIZE=80;                  //~@@@2I~
	protected String MatchTitle;                                   //~@@@2I~
    protected Label TL;                                          //~@@@1R~//~@@@2R~
	protected BigTimerLabel BL;
	protected Menu FileMenu,Options;
	protected Panel CommentPanel;
    public int Col;                                                    //~@@@2R~
    protected int Bishop,Knight,GameOptions,Gameover,Gameover2;    //~@@@2I~
    protected int accepterBishop,accepterKnight;                   //~1Aa7I~
    protected	TextField tfU,tfL;                                 //~@@@2I~
    protected	ButtonAction ButtonResign;                         //~@@@2I~
    protected boolean swLocalGame;                                 //~@@@2I~
//    protected TextArea AllComments;                              //~@@@1R~
//    CardLayout CommentPanelLayout;                               //~@@@1R~
//  protected CheckboxMenuItem KibitzWindow,Teaching,Silent;       //~@@@1R~
	private static Image SimageGameover,SimageGameover2;           //~@@@@R~//~@@@2M~
    private static int maxgo=GameQuestion.MAX_GAMEOVER;            //~@@@@I~//~@@@2M~
	private int[] capturedUL=new int[2];                           //~@@@@R~//~@@@2M~
	static final int[] Scapturedlistidtb={                          //~@@@@M~//~@@@2R~
    	R.id.Captured11,R.id.Captured12,R.id.Captured13,R.id.Captured14,R.id.Captured15,R.id.Captured16,R.id.Captured17,R.id.Captured18,R.id.Captured19,//~@@@@M~//~@@@2M~
    	R.id.Captured21,R.id.Captured22,R.id.Captured23,R.id.Captured24,R.id.Captured25,R.id.Captured26,R.id.Captured27,R.id.Captured28,R.id.Captured29,//~@@@@M~//~@@@2M~
    };                                                             //~@@@@M~//~@@@2M~
	private ImageView[] capturedlistViewtb=new ImageView[AG.BOARDSIZE_SHOGI*2];//~v101I~
	/**
	Will modify the menues of the GoFrame.
	"endgame" is used for for the menu entry to end a game
	(e.g. "remove groups").
	"count" is the string to count a game (e.g. "send done").
	<P>
	Optionally, the comment uses a card layout and a second
	text area to hold the Kibitz window.
	*/
//**************************************************************** //~@@@1I~
	public ConnectedGoFrame
//        (String s, int si, String endgame, String count,         //~@@@2R~
        (int Presid,String s, int si,                              //~@@@2R~
//  	boolean kibitzwindow, boolean canteach)                    //~@@@1R~
    	int Pgameover,int Pgameover2,                              //~@@@1R~
//  	int Pbishop,int Pknight,int Pgameoptions,int col)          //~@@@1R~//~1Aa7R~
    	int Pbishop,int Pknight,int Pgameoptions,int col,int PaccepterBishop,int PaccepterKnight)//~1Aa7I~
	{	super(Presid,s);                                           //~@@@2R~
//        setLayout(new BorderLayout());                           //~@@@1R~
        if (Dump.Y) Dump.println("@@@@ConnectedGoFrame@@@@");      //~@@@1I~
    	Bishop=Pbishop;                                            //~@@@1M~
        Knight=Pknight;                                            //~@@@1M~
    	accepterBishop=PaccepterBishop;                            //~1Aa7I~
        accepterKnight=PaccepterKnight;                            //~1Aa7I~
        GameOptions=Pgameoptions;                                  //~@@@1R~
    	Gameover=Pgameover==0?Math.min(si,GameQuestion.MAX_GAMEOVER):Pgameover;//~@@@1I~
        Gameover2=Pgameover2;                                      //~@@@1I~
        Col=col;                                                   //~@@@1I~
//        TimerInTitle=Global.getParameter("timerintitle",true);   //~@@@2R~
//        ExtraSendField=Global.getParameter("extrasendfield",true);//~@@@2R~
//        BigTimer=Global.getParameter("bigtimer",true);           //~@@@2R~
		// Menu
//        MenuBar M=new MenuBar();                                 //~@@@1R~
//        setMenuBar(M);                                           //~@@@1R~
//        Menu file=new MyMenu(Global.resourceString("File"));     //~@@@1R~
//        FileMenu=file;                                           //~@@@1R~
//        M.add(file);                                             //~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Save")));//~@@@1R~
//        file.addSeparator();                                     //~@@@1R~
//        file.add(UseXML=                                         //~@@@1R~
//            new CheckboxMenuItemAction(this,Global.resourceString("Use_XML")));//~@@@1R~
//        UseXML.setState(Global.getParameter("xml",false));       //~@@@1R~
//        file.add(UseSGF=                                         //~@@@1R~
//            new CheckboxMenuItemAction(this,Global.resourceString("Use_SGF")));//~@@@1R~
//        UseSGF.setState(!Global.getParameter("xml",false));      //~@@@1R~
//        file.addSeparator();                                     //~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Copy_to_Clipboard")));//~@@@1R~
//        file.addSeparator();                                     //~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Mail")));//~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Ascii_Mail")));//~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Print")));//~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Save_Bitmap")));//~@@@1R~
//        file.addSeparator();                                     //~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Refresh")));//~@@@1R~
//        file.addSeparator();                                     //~@@@1R~
//        file.add(                                                //~@@@1R~
//            ShowButtons=new CheckboxMenuItemAction(this,         //~@@@1R~
//                Global.resourceString("Show_Buttons")));         //~@@@1R~
//        ShowButtons.setState(Global.getParameter("showbuttonsconnected",true));//~@@@1R~
//        /*                                                       //~@@@1R~
//        if (canteach)                                            //~@@@1R~
//        {   file.addSeparator();                                 //~@@@1R~
//            Menu teaching=new MyMenu(Global.resourceString("Teaching"));//~@@@1R~
//            teaching.add(Teaching=new CheckboxMenuItemAction(this,Global.resourceString("Teaching_On")));//~@@@1R~
//            Teaching.setState(false);                            //~@@@1R~
//            teaching.add(new MenuItemAction(this,Global.resourceString("Load_Teaching_Game")));//~@@@1R~
//            file.add(teaching);                                  //~@@@1R~
//        }                                                        //~@@@1R~
//        */                                                       //~@@@1R~
//        file.addSeparator();                                     //~@@@1R~
//        file.add(new MenuItemAction(this,Global.resourceString("Close")));//~@@@1R~
//        Menu set=new MyMenu(Global.resourceString("Set"));       //~@@@1R~
//        M.add(set);                                              //~@@@1R~
//        set.add(Mark=new CheckboxMenuItemAction(this,Global.resourceString("Mark")));//~@@@1R~
//        set.add(Letter=new CheckboxMenuItemAction(this,Global.resourceString("Letter")));//~@@@1R~
//        set.add(Hide=new CheckboxMenuItemAction(this,Global.resourceString("Delete")));//~@@@1R~
//        Menu mark=new MyMenu(Global.resourceString("Special_Mark"));//~@@@1R~
//        mark.add(Square=new CheckboxMenuItemAction(this,Global.resourceString("Square")));//~@@@1R~
//        mark.add(Circle=new CheckboxMenuItemAction(this,Global.resourceString("Circle")));//~@@@1R~
//        mark.add(Triangle=new CheckboxMenuItemAction(this,Global.resourceString("Triangle")));//~@@@1R~
//        mark.add(Cross=new CheckboxMenuItemAction(this,Global.resourceString("Cross")));//~@@@1R~
//        mark.addSeparator();                                     //~@@@1R~
//        mark.add(TextMark=new CheckboxMenuItemAction(this,Global.resourceString("Text")));//~@@@1R~
//        set.add(mark);                                           //~@@@1R~
//        set.addSeparator();                                      //~@@@1R~
//        set.add(new MenuItemAction(this,Global.resourceString("Resume_playing")));//~@@@1R~
//        set.addSeparator();                                      //~@@@1R~
//        set.add(new MenuItemAction(this,Global.resourceString("Pass")));//~@@@1R~
//        set.addSeparator();                                      //~@@@1R~
//        set.add(SetBlack=new CheckboxMenuItemAction(this,Global.resourceString("Set_Black")));//~@@@1R~
//        set.add(SetWhite=new CheckboxMenuItemAction(this,Global.resourceString("Set_White")));//~@@@1R~
//        set.addSeparator();                                      //~@@@1R~
//        set.add(Black=new CheckboxMenuItemAction(this,Global.resourceString("Black_to_play")));//~@@@1R~
//        set.add(White=new CheckboxMenuItemAction(this,Global.resourceString("White_to_play")));//~@@@1R~
//        set.addSeparator();                                      //~@@@1R~
//        set.add(new MenuItemAction(this,Global.resourceString("Undo_Adding_Removing")));//~@@@1R~
//        set.add(new MenuItemAction(this,Global.resourceString("Clear_all_marks")));//~@@@1R~
//        Menu var=new MyMenu(Global.resourceString("Variations"));//~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Insert_Node")));//~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Insert_Variation")));//~@@@1R~
//        var.addSeparator();                                      //~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Next_Game")));//~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Previous_Game")));//~@@@1R~
//        var.addSeparator();                                      //~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Set_Search_String")));//~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Search_Comment")));//~@@@1R~
//        var.addSeparator();                                      //~@@@1R~
//        var.add(new MenuItemAction(this,Global.resourceString("Node_Name")));//~@@@1R~
//        M.add(var);                                              //~@@@1R~
//        Menu score=new MyMenu(Global.resourceString("Finish_Game"));//~@@@1R~
//        M.add(score);                                            //~@@@1R~
//        score.add(new MenuItemAction(this,Global.resourceString("Resign")));//~@@@1R~
//        score.add(new MenuItemAction(this,Global.resourceString("Pass")));//~@@@1R~
//        if (!endgame.equals("")) score.add(new MenuItemAction(this,endgame));//~@@@1R~
//        score.add(new MenuItemAction(this,Global.resourceString("Local_Count")));//~@@@1R~
//        score.addSeparator();                                    //~@@@1R~
//        score.add(new MenuItemAction(this,Global.resourceString("Prisoner_Count")));//~@@@1R~
//        if (!count.equals("")) score.add(new MenuItemAction(this,count));//~@@@1R~
//        score.addSeparator();                                    //~@@@1R~
//        score.add(new MenuItemAction(this,Global.resourceString("Game_Information")));//~@@@1R~
//        score.add(new MenuItemAction(this,Global.resourceString("Game_Copyright")));//~@@@1R~
//        Menu options=new MyMenu(Global.resourceString("Options"));//~@@@1R~
//        Options=options;                                         //~@@@1R~
//        options.add(Silent=new CheckboxMenuItemAction(this,Global.resourceString("Silent")));//~@@@1R~
//        Silent.setState(Global.getParameter("boardsilent",false));//~@@@1R~
//        if (Silent.getState()) Global.Silent++;                  //~@@@1R~
//        Menu mc=new MyMenu(Global.resourceString("Coordinates"));//~@@@1R~
//        mc.add(Coordinates=new CheckboxMenuItemAction(this,Global.resourceString("On")));//~@@@1R~
//        Coordinates.setState(Global.getParameter("coordinates",true));//~@@@1R~
//        mc.add(UpperLeftCoordinates=new CheckboxMenuItemAction(this,Global.resourceString("Upper_Left")));//~@@@1R~
//        UpperLeftCoordinates.setState(Global.getParameter("upperleftcoordinates",true));//~@@@1R~
//        mc.add(LowerRightCoordinates=new CheckboxMenuItemAction(this,Global.resourceString("Lower_Right")));//~@@@1R~
//        LowerRightCoordinates.setState(                          //~@@@1R~
//            Global.getParameter("lowerrightcoordinates",false)); //~@@@1R~
//        options.add(mc);                                         //~@@@1R~
//        options.addSeparator();                                  //~@@@1R~
//        Menu colors=new MyMenu(Global.resourceString("Colors")); //~@@@1R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Board_Color")));//~@@@1R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Black_Color")));//~@@@1R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Black_Sparkle_Color")));//~@@@1R~
//        colors.add(new MenuItemAction(this,Global.resourceString("White_Color")));//~@@@1R~
//        colors.add(new MenuItemAction(this,Global.resourceString("White_Sparkle_Color")));//~@@@1R~
//        options.add(colors);                                     //~@@@1R~
//        options.add(MenuBWColor=new CheckboxMenuItemAction(this,Global.resourceString("Use_B_W_marks")));//~@@@1R~
//        MenuBWColor.setState(Global.getParameter("bwcolor",false));//~@@@1R~
//        BWColor=MenuBWColor.getState();                          //~@@@1R~
//        options.add(PureSGF=new CheckboxMenuItemAction(this,Global.resourceString("Save_pure_SGF")));//~@@@1R~
//        PureSGF.setState(Global.getParameter("puresgf",false));  //~@@@1R~
//        options.add(CommentSGF=new CheckboxMenuItemAction(this,Global.resourceString("Use_SGF_Comments")));//~@@@1R~
//        CommentSGF.setState(Global.getParameter("sgfcomments",false));//~@@@1R~
//        options.addSeparator();                                  //~@@@1R~
//        Menu fonts=new MyMenu(Global.resourceString("Fonts"));   //~@@@1R~
//        fonts.add(new MenuItemAction(this,Global.resourceString("Fixed_Font")));//~@@@1R~
//        fonts.add(new MenuItemAction(this,Global.resourceString("Board_Font")));//~@@@1R~
//        fonts.add(new MenuItemAction(this,Global.resourceString("Normal_Font")));//~@@@1R~
//        options.add(fonts);                                      //~@@@1R~
//        Menu variations=new MyMenu(Global.resourceString("Variation_Display"));//~@@@1R~
//        variations.add(VHide=new CheckboxMenuItemAction(this,    //~@@@1R~
//            Global.resourceString("Hide")));                     //~@@@1R~
//        VHide.setState(Global.getParameter("vhide",false));      //~@@@1R~
//        variations.add(VCurrent=new CheckboxMenuItemAction(this, //~@@@1R~
//            Global.resourceString("To_Current")));               //~@@@1R~
//        VCurrent.setState(Global.getParameter("vcurrent",true)); //~@@@1R~
//        variations.add(VChild=new CheckboxMenuItemAction(this,   //~@@@1R~
//            Global.resourceString("To_Child")));                 //~@@@1R~
//        VChild.setState(!Global.getParameter("vcurrent",true));  //~@@@1R~
//        variations.addSeparator();                               //~@@@1R~
//        variations.add(VNumbers=new CheckboxMenuItemAction(this, //~@@@1R~
//            Global.resourceString("Continue_Numbers")));         //~@@@1R~
//        VNumbers.setState(Global.getParameter("variationnumbers",false));//~@@@1R~
//        options.add(variations);                                 //~@@@1R~
//        options.addSeparator();                                  //~@@@1R~
//        options.add(MenuTarget=new CheckboxMenuItemAction(this,Global.resourceString("Show_Target")));//~@@@1R~
//        MenuTarget.setState(Global.getParameter("showtarget",true));//~@@@1R~
//        ShowTarget=MenuTarget.getState();                        //~@@@1R~
//        options.add(MenuLastNumber=new CheckboxMenuItemAction(this,Global.resourceString("Last_Number")));//~@@@1R~
//        MenuLastNumber.setState(Global.getParameter("lastnumber",false));//~@@@1R~
//        LastNumber=MenuLastNumber.getState();                    //~@@@1R~
//        options.add(new MenuItemAction(this,Global.resourceString("Last_50")));//~@@@1R~
//        options.add(new MenuItemAction(this,Global.resourceString("Last_100")));//~@@@1R~
//        options.addSeparator();                                  //~@@@1R~
//        options.add(TrueColor=new CheckboxMenuItemAction(this,Global.resourceString("True_Color_Board")));//~@@@1R~
//        TrueColor.setState(Global.getParameter("beauty",true));  //~@@@1R~
//        options.add(TrueColorStones=new CheckboxMenuItemAction(this,Global.resourceString("True_Color_Stones")));//~@@@1R~
//        TrueColorStones.setState(Global.getParameter("beautystones",true));//~@@@1R~
//        options.add(Alias=new CheckboxMenuItemAction(this,Global.resourceString("Anti_alias_Stones")));//~@@@1R~
//        Alias.setState(Global.getParameter("alias",true));       //~@@@1R~
//        options.add(Shadows=new CheckboxMenuItemAction(this,Global.resourceString("Shadows")));//~@@@1R~
//        Shadows.setState(Global.getParameter("shadows",true));   //~@@@1R~
//        options.add(SmallerStones=new CheckboxMenuItemAction(this,Global.resourceString("Smaller_Stones")));//~@@@1R~
//        SmallerStones.setState(Global.getParameter("smallerstones",false));//~@@@1R~
//        options.add(BlackOnly=new CheckboxMenuItemAction(this,Global.resourceString("Black_Only")));//~@@@1R~
//        BlackOnly.setState(Global.getParameter("blackonly",false));//~@@@1R~
//        if (kibitzwindow)                                        //~@@@1R~
//        {   options.addSeparator();                              //~@@@1R~
//            options.add(KibitzWindow=new CheckboxMenuItemAction(this,Global.resourceString("Kibitz_Window")));//~@@@1R~
//            KibitzWindow.setState(Global.getParameter("kibitzwindow",true));//~@@@1R~
//            options.add(new MenuItemAction(this,Global.resourceString("Clear_Kibitz_Window")));//~@@@1R~
//        }                                                        //~@@@1R~
//        M.add(options);                                          //~@@@1R~
//        Menu help=new MyMenu(Global.resourceString("Help"));     //~@@@1R~
//        help.add(new MenuItemAction(this,Global.resourceString("Board_Window")));//~@@@1R~
//        help.add(new MenuItemAction(this,Global.resourceString("Making_Moves")));//~@@@1R~
//        help.add(new MenuItemAction(this,Global.resourceString("Keyboard_Shortcuts")));//~@@@1R~
//        help.add(new MenuItemAction(this,Global.resourceString("About_Variations")));//~@@@1R~
//        help.add(new MenuItemAction(this,Global.resourceString("Playing_Games")));//~@@@1R~
//        help.add(new MenuItemAction(this,Global.resourceString("Mailing_Games")));//~@@@1R~
//        M.setHelpMenu(help);                                     //~@@@1R~
//        // Board                                                 //~@@@1R~
//        L=new MyLabel(Global.resourceString("New_Game"));          //~1118R~//~@@@1R~
//        L=new MyLabel(R.id.GameInfo);                              //~@@@2I~//~@@@1I~//~@@@2R~
        L=new MyLabel(this,R.id.GameInfo);                         //~@@@2I~
//        Lm=new MyLabel("--");                                      //~1118R~//~@@@1R~
//        Lm=new MyLabel(R.id.SetStone);                             //~@@@2I~//~@@@1I~//~@@@2R~
        Lm=new MyLabel(this,R.id.SetStone);                        //~@@@2I~
//        Comment=new MyTextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@1R~//~@@@2R~
//        Comment=new MyTextArea("",R.id.Comment,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@2R~
        Comment=new MyTextArea(this,"",R.id.Comment,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@2I~
        Comment.setFont(Global.SansSerif);                       //~@@@1R~//~@@@2R~
//        CommentPanel=new MyPanel();                              //~@@@1R~//~@@@2R~
//        if (kibitzwindow)                                        //~@@@1R~
//        {   AllComments=new MyTextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@1R~
//            AllComments.setEditable(false);                      //~@@@1R~
//            CommentPanel.setLayout(CommentPanelLayout=new CardLayout());//~@@@1R~
//            CommentPanel.add("Comment",Comment);                 //~@@@1R~
//            CommentPanel.add("AllComments",AllComments);         //~@@@1R~
//        }                                                        //~@@@1R~
//        else                                                     //~@@@1R~
//        {   CommentPanel.setLayout(new BorderLayout());          //~@@@1R~
//            CommentPanel.add("Center",Comment);                  //~@@@1R~
//        }                                                        //~@@@1R~
          B=new ConnectedBoard(si,this);                           //~@@@1R~
//        B.putInitialPiece(Col,Bishop,Knight,Gameover,Gameover2,GameOptions);//~@@@1R~//~1Aa7R~
          B.putInitialPiece(Col,Bishop,Knight,Gameover,Gameover2,GameOptions,accepterBishop,accepterKnight);//~1Aa7I~
//        Panel BP=new MyPanel();                                  //~@@@1R~
//        BP.setLayout(new BorderLayout());                        //~@@@1R~
//        BP.add("Center",B);                                      //~@@@1R~
//        // Add the label                                         //~@@@1R~
//        Panel bpp=new MyPanel();                                 //~@@@1R~
//        bpp.setLayout(new GridLayout(0,1));                      //~@@@1R~
//        SimplePanel sp=                                          //~@@@1R~
//            new SimplePanel((Component)L,80,(Component)Lm,20);   //~@@@1R~
//        bpp.add(sp);                                             //~@@@1R~
//        sp.setBackground(Global.gray);                           //~@@@1R~
//        // Add the timer label                                   //~@@@1R~
//        if (!TimerInTitle)                                       //~@@@1R~
//        {   SimplePanel btl=new SimplePanel(                     //~@@@1R~
//                TL=new MyLabel(Global.resourceString("Start"));,80,new MyLabel(""),20);//~@@@1R~//~@@@2R~
//            bpp.add(btl);                                        //~@@@1R~
//            btl.setBackground(Global.gray);                      //~@@@1R~
//        }                                                        //~@@@1R~
//        BP.add("South",bpp);                                     //~@@@1R~
//        // Text Area                                             //~@@@1R~
//        Panel cp=new MyPanel();                                  //~@@@1R~
//        cp.setLayout(new BorderLayout());                        //~@@@1R~
        Comment.setBackground(Global.gray);                      //~@@@1R~//~@@@2R~
//        cp.add("Center",CommentPanel);                           //~@@@1R~
//        if (kibitzwindow && KibitzWindow.getState())             //~@@@1R~
//            CommentPanelLayout.show(CommentPanel,"AllComments"); //~@@@1R~
//        // Add the extra send field                              //~@@@1R~
//        if (ExtraSendField)                                      //~@@@1R~
//        {   SendField=new HistoryTextField(this,"SendField");    //~@@@1R~
//            cp.add("South",SendField);                           //~@@@1R~
//        }                                                        //~@@@1R~
//        Panel bcp=new BoardCommentPanel(BP,cp,B);                //~@@@1R~
//        add("Center",bcp);                                       //~@@@1R~
//        // Add the big timer label                               //~@@@1R~
//        if (BigTimer)                                            //~@@@1R~
//        {   BL=new BigTimerLabel();                              //~@@@1R~
//            cp.add("North",BL);                                  //~@@@1R~
//        }                                                        //~@@@1R~
//        // Navigation panel                                      //~@@@1R~
//        IB=createIconBar();                                      //~@@@1R~
//        ButtonP=new Panel3D(IB);                                 //~@@@1R~
//        if (Global.getParameter("showbuttonsconnected",true))    //~@@@1R~
//            add("South",ButtonP);                                //~@@@1R~
		// Directory for FileDialog
//        Dir=new String("");                                      //~@@@1R~
//        Global.setwindow(this,"board",500,450,false);            //~@@@1R~
//        TL=new MyLabel(R.id.TimerLabel);                         //~@@@2R~
//        BL=new BigTimerLabel();                                  //~@@@2R~
//        tfU=new TextField(0,R.id.CapturedU,"");                  //~@@@2R~
//        tfL=new TextField(0,R.id.CapturedL,"");                  //~@@@2R~
        TL=new MyLabel(this,R.id.TimerLabel);                      //~@@@2I~
        BL=new BigTimerLabel(this);                                //~@@@2I~
        tfU=new TextField(this,0,R.id.CapturedU,"");               //~@@@2I~
        tfL=new TextField(this,0,R.id.CapturedL,"");               //~@@@2I~
        if (AG.screenDencityMdpi)                                  //~1A50I~
			;   //apply layout xml                                 //~1A50I~
        else                                                       //~1A50I~
		if (AG.smallButton)    //both portrait and landscale       //~@@@2R~
			setSmallHeight();                                      //~@@@2R~
        boolean swSmall=AG.portrait;	//se small button if portrait and small pixel device//~@@@2I~
//        ButtonResign=ButtonAction.init(this,R.string.Resign,R.id.Resign,swSmall/*smallbutton*/);       //~@@@1R~//~@@@2R~
//        ButtonAction.init(this,0,R.id.Options,swSmall);          //~@@@2R~
//        ButtonAction.init(this,0,R.id.Help,swSmall);           //~@@@1R~//~@@@2R~
        ButtonResign=new ButtonAction(this,R.string.Resign,R.id.Resign,swSmall/*smallbutton*/);//~@@@2I~
        new ButtonAction(this,0,R.id.Options,swSmall);        //~@@@2I~
        new ButtonAction(this,0,R.id.Help,swSmall);           //~@@@2I~
 //       validate();                                                //~@@@1R~
		Show=true;
		B.addKeyListener(this);
	}

//    public IconBar createIconBar ()                              //~@@@1R~
//    {   IconBar I=new IconBar(this);                             //~@@@1R~
//        I.Resource="/jagoclient/icons/";                         //~@@@1R~
//        I.addLeft("undo");                                       //~@@@1R~
//        addSendForward(I);                                       //~@@@1R~
//        I.addSeparatorLeft();                                    //~@@@1R~
//        I.addLeft("allback");                                    //~@@@1R~
//        I.addLeft("fastback");                                   //~@@@1R~
//        I.addLeft("back");                                       //~@@@1R~
//        I.addLeft("forward");                                    //~@@@1R~
//        I.addLeft("fastforward");                                //~@@@1R~
//        I.addLeft("allforward");                                 //~@@@1R~
//        I.addSeparatorLeft();                                    //~@@@1R~
//        I.addLeft("variationback");                              //~@@@1R~
//        I.addLeft("variationstart");                             //~@@@1R~
//        I.addLeft("variationforward");                           //~@@@1R~
//        I.addLeft("main");                                       //~@@@1R~
//        I.addLeft("mainend");                                    //~@@@1R~
//        I.addSeparatorLeft();                                    //~@@@1R~
//        I.addLeft("send");                                       //~@@@1R~
//        I.setIconBarListener(this);                              //~@@@1R~
//        return I;                                                //~@@@1R~
//    }                                                            //~@@@1R~
	
//    public void addSendForward(IconBar I)                        //~@@@1R~
//    {                                                            //~@@@1R~
//    }                                                            //~@@@1R~
	
//    public void iconPressed (String s)                           //~@@@1R~
//    {   if (s.equals("send")) doAction(Global.resourceString("Send"));//~@@@1R~
//        else super.iconPressed(s);                               //~@@@1R~
//    }                                                            //~@@@1R~
	
//    public void addComment (String s)                            //~@@@1R~
//    // add a comment to the board (called from external sources) //~@@@1R~
//    {   B.addcomment(s);                                         //~@@@1R~
//        if (AllComments!=null) AllComments.append(s+"\n");       //~@@@1R~
//    }                                                            //~@@@1R~

//    public void addtoallcomments (String s)                      //~@@@1R~
//    // add something to the allcomments window                   //~@@@1R~
//    {   if (AllComments!=null) AllComments.append(s+"\n");       //~@@@1R~
//    }                                                            //~@@@1R~

	public void doAction (String o)
//  {	if (o.equals(Global.resourceString("Clear_Kibitz_Window")))//~@@@1R~
	{                                                              //~@@@1I~
//        if (o.equals(Global.resourceString("Clear_Kibitz_Window")))//~@@@1I~
//        {   AllComments.setText("");                             //~@@@1R~
//        }                                                        //~@@@1R~
        if (o.equals(AG.resource.getString(R.string.Options)))   //~@@@@I~//~@@@2I~
        {                                                          //~@@@2I~
            new GoFrameOptions(this);                              //~@@@2R~
        }                                                          //~@@@2I~
		else super.doAction(o);
	}

	public void itemAction (String o, boolean flag)
//  {	if (Global.resourceString("Kibitz_Window").equals(o))      //~@@@1R~
	{                                                              //~@@@1I~
//        if (Global.resourceString("Kibitz_Window").equals(o))    //~@@@1I~
//        {   if (KibitzWindow.getState())                         //~@@@1R~
//                CommentPanelLayout.show(CommentPanel,"AllComments");//~@@@1R~
//            else                                                 //~@@@1R~
//                CommentPanelLayout.show(CommentPanel,"Comment"); //~@@@1R~
//            Global.setParameter("kibitzwindow",KibitzWindow.getState());//~@@@1R~
//        }                                                        //~@@@1R~
//        else if (Global.resourceString("Silent").equals(o))      //~@@@1R~
//        {   if (flag) Global.Silent++;                           //~@@@1R~
//            else Global.Silent--;                                //~@@@1R~
//            Global.setParameter("boardsilent",flag);             //~@@@1R~
//        }                                                        //~@@@1R~
                                              //~@@@1I~
		/*else*/ super.itemAction(o,flag);
	}
	
	public void windowOpened (WindowEvent e)
//  {	if (ExtraSendField) SendField.requestFocus();              //~@@@1R~
    {                                                              //~@@@1I~
//  	if (ExtraSendField) SendField.requestFocus();              //~@@@1I~
	}
	
	public void activate ()
	{
	}
	
	public boolean wantsmove ()
	{	return false;
	}
	
	public void doclose ()
	{	Global.Silent--;
		super.doclose();
	}
//********************************************************         //~@@@@I~//~@@@2M~
//********************************************************         //~@@@2I~
	public void setSmallHeight()                                   //~@@@2R~
    {                                                              //~@@@2I~
        String labelU,labelL;                                      //~@@@2I~
        Component c=new Component();                               //~@@@2I~
        int hw;                                                    //~@@@2I~
    //*******************                                          //~@@@2I~
        if (Dump.Y) Dump.println("setSmallCapturedListHeight");    //~@@@2I~
        hw=AG.smallImageHeight;                                //~@@@2I~
    	for (int ii=0;ii<AG.BOARDSIZE_SHOGI*2;ii++)                     //~@@@2I~
        {                                                          //~@@@2I~
//            View v=(View)AG.findViewById(Scapturedlistidtb[ii]);   //~@@@2I~//~v101R~
            View v=(View)findViewById(Scapturedlistidtb[ii]); //by container//~v101I~
            capturedlistViewtb[ii]=(ImageView)v;                   //~v101I~
            c.setLayoutWidthHeight(v,hw,hw,false/*no setTextSize*/);//~@@@2R~
        }                                                          //~@@@2I~
//        View v=(View)AG.findViewById(R.id.SetStone);               //~@@@2I~//~v101R~
      if (AG.portrait)                                             //~1022I~
      {                                                            //~1022I~
        View v=(View)findViewById(R.id.SetStone);                  //~v101I~
        c.setLayoutHeight(v,hw,false/*setTextSize*/);              //~@@@2R~
      }                                                            //~1022I~
        c.setLayoutWidthHeight(tfL.textView,hw,hw,false/*setTextSize*/);//~@@@2R~
        c.setLayoutWidthHeight(tfU.textView,hw,hw,false/*setTextSize*/);//~@@@2R~
    }                                                              //~@@@2I~
//********************************************************         //~@@@@I~//~@@@2M~
	public void drawInitialCaptured()                             //~@@@@R~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~                                                 //~@@@2I~
        String labelU,labelL;                                      //~@@@2I~
    //*******************                                          //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("drawInitialcaptured start");     //~@@@2I~
        int hh=tfU.textView.getHeight();                                      //~@@@@I~//~@@@2R~
        if (hh==0)                                                 //~@@@2I~
        	return;	//cause exception by invalid bitmapsize        //~@@@2I~
        if (SimageGameover==null)                                  //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
	        SimageGameover=Image.loadPieceImage(R.drawable.gameover,hh,hh);//~@@@@R~//~@@@2M~
    	    SimageGameover2=Image.loadPieceImage(R.drawable.gameover2,hh,hh);//~@@@@R~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        drawGameover2(0,Gameover2);                                  //~@@@@R~//~@@@2R~
        drawGameover2(maxgo,Gameover2);                        //~@@@@R~//~@@@2R~
        drawGameover(Gameover2,Gameover);                          //~@@@2I~
        drawGameover(maxgo+Gameover2,Gameover);                    //~@@@2I~
//        if (B.pieceType==Board.PIECETYPE_SHOGI)                          //~@@@@R~//~@@@2R~
//        {                                                        //~@@@2R~
//            if (Col>0)  //black                                  //~@@@2R~
//            {   labelL="B";labelU="R";}                          //~@@@2R~
//            else                                                 //~@@@2R~
//            {   labelL="R";labelU="B";}                          //~@@@2R~
//        }                                                        //~@@@2R~
//        else                                                     //~@@@2R~
//        {                                                        //~@@@2R~
            if (Col>0)  //black                                    //~@@@2R~
            {   labelL=AG.BlackSign;labelU=AG.WhiteSign;}          //~@@@2R~
            else                                                   //~@@@2R~
            {   labelL=AG.WhiteSign;labelU=AG.BlackSign;}          //~@@@2R~
//        }                                                        //~@@@2R~
        tfU.setText(labelU+":");                                   //~@@@2R~
        tfL.setText(labelL+":");                                   //~@@@2R~
        if (Dump.Y) Dump.println("DrawInitialCaptured end");       //~@@@2I~
    }                                                              //~@@@@I~//~@@@2M~
//********************************************************         //~@@@@I~//~@@@2M~
//* draw gameover limit over diff count                            //~@@@2I~
//********************************************************         //~@@@2I~
	private void drawGameover(int Pstart,int Pctr)                    //~@@@@I~//~@@@2R~
    {                                                              //~@@@@I~//~@@@2M~
    	int mx;                                                    //~@@@@I~//~@@@2M~
    	if (Pstart<maxgo)	//upper                                    //~@@@@I~//~@@@2R~
        	mx=Math.min(Gameover,Pstart+Pctr);                                           //~@@@@R~//~@@@2R~
        else                                                       //~@@@@I~//~@@@2M~
        	mx=Math.min(maxgo+Gameover,Pstart+Pctr);                                     //~@@@@R~//~@@@2R~
    	for (int ii=Pstart;ii<mx;ii++)                      //~@@@@R~//~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	drawPiece(ii,SimageGameover);                         //~@@@@R~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//********************************************************         //~@@@@I~//~@@@2M~
//* draw difference gameover warning                               //~@@@2I~
//********************************************************         //~@@@2I~
	private void drawGameover2(int Pstart,int Pctr)                   //~@@@@I~//~@@@2R~
    {                                                              //~@@@@I~//~@@@2M~
    	int mx;                                                    //~@@@@I~//~@@@2M~
    	if (Pstart<maxgo)	//upper                                //~@@@2I~
        	mx=Math.min(Gameover,Pstart+Pctr);                     //~@@@2R~
        else                                                       //~@@@2I~
        	mx=Math.min(maxgo+Gameover,Pstart+Pctr);               //~@@@2R~
    	for (int ii=Pstart;ii<mx;ii++)                             //~@@@2I~
        {                                                          //~@@@@I~//~@@@2M~
        	drawPiece(ii,SimageGameover2);                        //~@@@@R~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//********************************************************         //~@@@@I~//~@@@2M~
	private void drawGameoverDiff(int Plistidx)                   //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    	int capturedU=capturedUL[0];                               //~@@@@I~//~@@@2M~
    	int capturedL=capturedUL[1];                               //~@@@@I~//~@@@2M~
    	int diff=capturedU-capturedL;                              //~@@@@I~//~@@@2M~
    	if (diff<0)	//advantage of you                             //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	drawGameover(capturedL,Gameover);                //~@@@@R~//~@@@2I~
        	drawGameover2(maxgo+capturedL,Gameover2+diff);                       //~@@@@R~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
    	if (diff>0)	//advantage of opponent                        //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	drawGameover2(capturedU,Gameover2-diff);                   //~@@@@R~//~@@@2R~
        	drawGameover(maxgo+capturedL,Gameover);                     //~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	drawGameover(capturedU,Gameover);                     //~@@@@I~//~@@@2M~
        	drawGameover(maxgo+capturedL,Gameover);                //~@@@@R~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
//      ((ConnectedBoard)B).reachedGameover2(diff>=Gameover2||-diff>=Gameover2);      //~@@@@I~//~@@@2R~//~1033R~
        ((ConnectedBoard)B).reachedGameover2(diff>=Gameover2||-diff>=Gameover2,diff);//~1033I~
    }                                                              //~@@@@I~//~@@@2M~
//********************************************************         //~@@@@I~//~@@@2M~
	private void drawPiece(int Pididx,Image Pimage)                //~@@@@R~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
//      ImageView v=(ImageView)AG.findViewById(Scapturedlistidtb[Pididx]);//~@@@@I~//~@@@2R~//~v101I~
        ImageView v;                                               //~v101I~
        v=capturedlistViewtb[Pididx];                              //~v101I~
      	if (v==null)                                               //~v101I~
        {                                                          //~v101I~
        	v=(ImageView)findViewById(Scapturedlistidtb[Pididx]);  //~v101I~
	        capturedlistViewtb[Pididx]=v;                          //~v101I~
        }                                                          //~v101I~
		setImageBitmap(v,Pimage.bitmap,View.VISIBLE);              //~@@@@R~//~@@@2M~
        if (Dump.Y) Dump.println("drawCaptuedPiece idxx="+Pididx); //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//********************************************************         //~@@@2I~
	private void drawPiece(Color Pbgcolor,int Pididx,Image Pimage) //~@@@2I~
    {                                                              //~@@@2I~
//      ImageView v=(ImageView)AG.findViewById(Scapturedlistidtb[Pididx]);//~v101I~
        ImageView v;                                               //~v101I~
        v=capturedlistViewtb[Pididx];                              //~v101I~
        if (v==null)                                               //~v101I~
        {                                                          //~v101I~
        	v=(ImageView)findViewById(Scapturedlistidtb[Pididx]);//~@@@2R~//~v101R~
	        capturedlistViewtb[Pididx]=v;                          //~v101I~
        }                                                          //~v101I~
		setImageBitmap(v,Pimage.bitmap,View.VISIBLE,Pbgcolor);//by component//~@@@2R~
        if (Dump.Y) Dump.println("drawCaptuedPiece with bgcolor idxx="+Pididx);//~@@@2I~
    }                                                              //~@@@2I~
//********************************************************         //~@@@@I~//~@@@2M~
    @Override //GoFrame                                            //~@@@@I~//~@@@2M~
    public void updateCapturedList(int Pcolor,int Ppiece)                             //~@@@2I~//~@@@@I~//~@@@2R~
    {                                                              //~@@@2I~//~@@@@I~//~@@@2M~
    	int imageidx,listidx;                                      //~@@@@I~//~@@@2M~
        Color bgcolor=Color.black;                                 //~@@@2R~
    //**********************	                                                           //~@@@2I~//~@@@@I~//~@@@2M~
    	if (Pcolor>0)	//black captured                           //~@@@@I~//~@@@2M~
        {                                                          //~@@@2I~
            imageidx=(Col>0)?Board.IDX_DOWN_BLACK:Board.IDX_UP_BLACK;   //black bitmap                           //~@@@@I~//~@@@2R~
			if (Board.pieceType==Board.PIECETYPE_CHESS)               //~@@@2I~
	            bgcolor=Color.white;                               //~@@@2R~
        }                                                          //~@@@2I~
        else             //white capture                                            //~@@@@I~//~@@@2R~
        {                                                          //~@@@2I~
            imageidx=(Col<0)?Board.IDX_DOWN_WHITE:Board.IDX_UP_WHITE;   //white bitmap//~@@@2R~
        }                                                          //~@@@2I~
        Image image=B.SpieceImage[imageidx][Ppiece];                //~@@@@I~//~@@@2M~
         if (Col!=Pcolor)   //oponent captured                                        //~@@@@I~//~@@@2R~
        	listidx=1;	//captured list lower                      //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        	listidx=0;	//captured list upper                      //~@@@@I~//~@@@2M~
        drawPiece(bgcolor,capturedUL[listidx]+listidx*GameQuestion.MAX_GAMEOVER,image);//~@@@@R~//~@@@2R~
        capturedUL[listidx]++;                                     //~@@@@I~//~@@@2M~
        if (Gameover2>0)                                           //~@@@@I~//~@@@2M~
	        drawGameoverDiff(listidx);                             //~@@@@I~//~@@@2M~
    }                                                              //~@@@2I~//~@@@@I~//~@@@2M~
//********************************************************         //~@@@@I~//~@@@2M~
    @Override //GoFrame                                            //~@@@@I~//~@@@2M~
    public void gameover(int Prequest,int Pcolor)                  //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
    	switch(Prequest)                                           //~@@@2M~
    	{//~@@@@I~                                                 //~@@@2M~
        case 0: //chk                                              //~@@@@I~//~@@@2R~
        	if (capturedUL[0]>=Gameover)                          //~@@@@I~//~@@@2R~
        		gameover(2,-Col);                                  //~@@@@R~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
        	if (capturedUL[1]>=Gameover)                          //~@@@@I~//~@@@2R~
        		gameover(2,Col);                                   //~@@@@R~//~@@@2M~
            break;                                                 //~@@@@I~//~@@@2M~
        case 1:		//by diff                                      //~@@@@I~//~@@@2M~
	    	gameoverMessage(Pcolor,1);                             //~@@@@I~//~@@@2M~
            break;                                                 //~@@@@I~//~@@@2M~
        case 2:		//gameover2                                    //~@@@@I~//~@@@2M~
	    	gameoverMessage(Pcolor,2);                             //~@@@@I~//~@@@2M~
            break;                                                 //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//********************************************************         //~@@@@I~//~@@@2M~
    public void gameoverMessage(int Pcolor,int Ptype)                  //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    	int id;                                                    //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
    	if (Pcolor>0)                                              //~@@@@I~//~@@@2M~
        	id=R.string.WinnerBlack;                               //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        	id=R.string.WinnerWhite;                              //~@@@@I~//~@@@2R~
        String winner=AG.resource.getString(id);                   //~@@@@I~//~@@@2M~
    	switch(Ptype)                                              //~@@@@I~//~@@@2R~
        {                                                          //~@@@2I~
        case 1:                                                    //~@@@2I~
        	id=R.string.GameoverByDiff;                            //~@@@@I~//~@@@2M~
            break;                                                 //~@@@2I~
        case 2:                                                    //~@@@2R~
        	id=R.string.GameoverByCaptured;                        //~@@@@I~//~@@@2M~
            break;                                                 //~@@@2I~
        case 3:                                                    //~@@@2I~
        	id=R.string.GameoverByTimeout;                         //~@@@2I~
            break;                                                 //~@@@2I~
        case 4:                                                    //~@@@2I~
        	id=R.string.GameoverByResign;                          //~@@@2I~
            break;                                                 //~@@@2I~
        default:                                                   //~@@@2I~
        	return;                                                //~@@@2I~
        }                                                          //~@@@2I~
        String msg=winner+AG.resource.getString(id);                     //~@@@@I~//~@@@2R~
        ((ConnectedBoard) B).infoComment(msg);                                        //~@@@2I~
//        new Message(this,msg,WIN_MSG_TEXTSIZE);                  //~@@@2R~
		Alert.simpleAlertDialog(null,MatchTitle,msg,Alert.BUTTON_POSITIVE);//~@@@2R~
        ButtonResign.setAction(R.string.Close);                    //~@@@2M~
		JagoSound.play("ahsvgameover",false/*not change to beep when beeponly option is on*/);//~1022I~
		gameovered();                                              //~@@@2R~
    }                                                              //~@@@@I~//~@@@2M~
//*****************************************************************************//~@@@2R~
//*from com.partnerFrame when PartnerThread got exception(Connection failed etc)//~@@@2I~
//******************************************************************************//~@@@2I~
    public void gameInterrupted(int Pmsgid)                        //~@@@2I~
    {                                                              //~@@@2I~
        String type=AG.resource.getString(Pmsgid);                     //~@@@2I~
		new Message(this,type);                                    //~@@@2I~
        ButtonResign.setAction(R.string.Close);                    //~@@@2I~
		gameovered();                                              //~@@@2I~
    }                                                              //~@@@2I~
//********************************************************         //~@@@2I~
    protected void pieceMoved(int Pcolor){}	//LocalGoFrame will override//~@@@2R~
    protected void pieceSelected(int Pi,int Pj){}	//LocalGoFrame will override//~@@@2R~
    protected boolean moveset(int Pi,int Pj,int Ppiece){return true;}//~@@@2I~
    protected void errPass(){}                                     //~@@@2I~
	protected void gameovered(){}                                  //~@@@2R~
}
