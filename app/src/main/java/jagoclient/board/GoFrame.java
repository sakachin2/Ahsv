//*CID://+1Ad9R~:                             update#=   97;       //~1Ad9R~
//**************************************************************   //~1A38I~
//1Ad9 2015/07/21 additional to 1Ad6(OutOfMemory)                  //~1Ad9I~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//1A38 2013/04/22 (BUG)When response(@@!move) delayed,accept next select.//~1A38I~
//                Then move confuse to move to old dest from new selected.//~1A38I~
//**************************************************************   //~1A38I~
package jagoclient.board;

//import jagoclient.BMPFile;                                       //~@@@@R~
import jagoclient.Dump;
import jagoclient.Global;
import jagoclient.dialogs.*;
import jagoclient.gui.*;

import java.io.*;

//import com.Ahsv.awt.BorderLayout;                                //~@@@@R~
import com.Ahsv.AG;
import com.Ahsv.R;
import com.Ahsv.URunnable;
import com.Ahsv.URunnableI;
import com.Ahsv.Utils;
import com.Ahsv.awt.CheckboxMenuItem;                              //~@@@@R~
//import com.Ahsv.awt.Clipboard;                                   //~@@@@R~
//import com.Ahsv.awt.ClipboardOwner;                              //~@@@@R~
import com.Ahsv.awt.Color;                                         //~@@@@R~
//import com.Ahsv.awt.FileDialog;                                  //~@@@@R~
import com.Ahsv.awt.Font;                                          //~@@@@R~
import com.Ahsv.awt.Frame;                                         //~@@@@R~
//import com.Ahsv.awt.GridLayout;                                  //~@@@@R~
import com.Ahsv.awt.KeyEvent;                                      //~@@@@R~
import com.Ahsv.awt.KeyListener;                                   //~@@@@R~
import com.Ahsv.awt.Label;                                         //~@@@@R~
import com.Ahsv.awt.MenuBar;                                       //~@@@@R~
import com.Ahsv.awt.Panel;                                         //~@@@@R~
//import com.Ahsv.awt.StringSelection;                             //~@@@@R~
import com.Ahsv.awt.TextArea;                                    //~@@@@R~
import com.Ahsv.awt.TextField;                                     //~@@@@R~

//import rene.gui.IconBar;                                         //~1221R~
//import com.Ahsv.rene.gui.IconBar;                                //~1221I~//~@@@@R~
//import rene.gui.IconBarListener;                                 //~@@@@R~
//import rene.util.FileName;                                       //~@@@@R~
                                                                   //~1116I~
                                                            //~1116I~
//import rene.util.xml.XmlReader;                                  //~@@@@R~
//import rene.util.xml.XmlReaderException;                         //~@@@@R~
                                                            //~1116I~

/**
Display a dialog to edit game information.
*/

class EditInformation extends CloseDialog
{	Node N;
	TextField Black,White,BlackRank,WhiteRank,Date,Time,
		Komi,Result,Handicap,GameName;
	GoFrame F;
	public EditInformation (GoFrame f, Node n)
	{	super(f,Global.resourceString("Game_Information"),false);
		N=n; F=f;
//        Panel p=new MyPanel();                                   //~@@@@R~
//        p.setLayout(new GridLayout(0,2));                        //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Game_Name")));  //~@@@@R~
//        p.add(GameName=new FormTextField(n.getaction("GN")));    //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Date")));       //~@@@@R~
//        p.add(Date=new FormTextField(n.getaction("DT")));        //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Black")));      //~@@@@R~
//        p.add(Black=new FormTextField(n.getaction("PB")));       //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Black_Rank"))); //~@@@@R~
//        p.add(BlackRank=new FormTextField(n.getaction("BR")));   //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("White")));      //~@@@@R~
//        p.add(White=new FormTextField(n.getaction("PW")));       //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("White_Rank"))); //~@@@@R~
//        p.add(WhiteRank=new FormTextField(n.getaction("WR")));   //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Result")));     //~@@@@R~
//        p.add(Result=new FormTextField(n.getaction("RE")));      //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Time")));       //~@@@@R~
//        p.add(Time=new FormTextField(n.getaction("TM")));        //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Komi")));       //~@@@@R~
//        p.add(Komi=new FormTextField(n.getaction("KM")));        //~@@@@R~
//        p.add(new MyLabel(Global.resourceString("Handicap")));   //~@@@@R~
//        p.add(Handicap=new FormTextField(n.getaction("HA")));    //~@@@@R~
//        add("Center",p);                                         //~@@@@R~
//        Panel pb=new MyPanel();                                  //~@@@@R~
//        pb.add(new ButtonAction(this,Global.resourceString("OK")));//~@@@@R~
//        pb.add(new ButtonAction(this,Global.resourceString("Cancel")));//~@@@@R~
//        add("South",pb);                                         //~@@@@R~
//        Global.setpacked(this,"editinformation",350,450);        //~@@@@R~
		show();
	}
	public void doAction (String o)
//  {	Global.notewindow(this,"editinformation");                 //~@@@@R~
    {                                                              //~@@@@I~
//  	Global.notewindow(this,"editinformation");                 //~@@@@I~
		if (Global.resourceString("OK").equals(o))
		{	N.setaction("GN",GameName.getText());
			N.setaction("PB",Black.getText());
			N.setaction("PW",White.getText());
			N.setaction("BR",BlackRank.getText());
			N.setaction("WR",WhiteRank.getText());
			N.setaction("DT",Date.getText());
			N.setaction("TM",Time.getText());
			N.setaction("KM",Komi.getText());
			N.setaction("RE",Result.getText());
			N.setaction("HA",Handicap.getText());
			if (!GameName.getText().equals(""))
				F.setTitle(GameName.getText());
		}
		setVisible(false); dispose();
	}
}

/**
A dialog to get the present encoding.
*/

//class GetEncoding extends GetParameter                           //~@@@@R~
//{   GoFrame GCF;                                                 //~@@@@R~
//    public GetEncoding (GoFrame gcf)                             //~@@@@R~
//    {   super(gcf,Global.resourceString("Encoding__empty__default_"),//~@@@@R~
//        Global.resourceString("Encoding"),gcf,true,"encoding");  //~@@@@R~
//        if (!Global.isApplet())                                  //~@@@@R~
//            set(Global.getParameter("encoding",                  //~@@@@R~
//            System.getProperty("file.encoding")));               //~@@@@R~
//        GCF=gcf;                                                 //~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public boolean tell (Object o, String S)                     //~@@@@R~
//    {   if (S.equals("")) Global.removeParameter("encoding");    //~@@@@R~
//        else Global.setParameter("encoding",S);                  //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

//class GetSearchString extends CloseDialog                        //~@@@@R~
//{   GoFrame GF;                                                  //~@@@@R~
//    TextFieldAction T;                                           //~@@@@R~
//    static boolean Active=false;                                 //~@@@@R~
//    public GetSearchString (GoFrame gf)                          //~@@@@R~
//    {   super(gf,Global.resourceString("Search"),false);         //~@@@@R~
//        if (Active) return;                                      //~@@@@R~
//        add("North",new MyLabel(Global.resourceString("Search_String")));//~@@@@R~
//        add("Center",T=new TextFieldAction(this,"Input",25));    //~@@@@R~
//        Panel p=new MyPanel();                                   //~@@@@R~
//        p.add(new ButtonAction(this,Global.resourceString("Search")));//~@@@@R~
//        p.add(new ButtonAction(this,Global.resourceString("Cancel")));//~@@@@R~
//        add("South",p);                                          //~@@@@R~
//        Global.setpacked(this,"getparameter",300,150);           //~@@@@R~
//        validate();                                              //~@@@@R~
//        T.addKeyListener(this);                                  //~@@@@R~
//        T.setText(Global.getParameter("searchstring","++"));     //~@@@@R~
//        GF=gf;                                                   //~@@@@R~
//        show();                                                  //~@@@@R~
//        Active=true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
//    public void doAction (String s)                              //~@@@@R~
//    {   if (s.equals(Global.resourceString("Search"))            //~@@@@R~
//        || s.equals("Input"))                                    //~@@@@R~
//        {   Global.setParameter("searchstring",T.getText());     //~@@@@R~
//            GF.search();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (s.equals(Global.resourceString("Cancel")))      //~@@@@R~
//        {   setVisible(false); dispose(); Active=false;          //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//    public void dispose ()                                       //~@@@@R~
//    {   Active=false;                                            //~@@@@R~
//        super.dispose();                                         //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Ask the user for permission to close the board frame.
*/

class CloseQuestion extends Question
{	GoFrame GF;
	boolean Result=false;
	public CloseQuestion (GoFrame g)
//  {	super(g,Global.resourceString("Really_trash_this_board_"), //~@@@2R~
    {                                                              //~@@@2I~
    	super(g,AG.resource.getString(R.string.Really_trash_this_board),//~@@@2I~
		AG.resource.getString(R.string.Title_Close_Board),g,true,false/*no reshedsule but callback doResultCloseQuestion()*/);//~@@@2R~
		GF=g;
		show();
	}
	public void tell (Question q, Object o, boolean f)
	{	q.setVisible(false); q.dispose();
		Result=f;
        GF.doResultCloseQuestion(Result);                          //~@@@2I~
	}
}

//class SizeQuestion extends GetParameter                          //~@@@@R~
//// Ask the board size.                                           //~@@@@R~
//{   public SizeQuestion (GoFrame g)                              //~@@@@R~
//    {   super(g,Global.resourceString("Size_between_5_and_29"),  //~@@@@R~
//            Global.resourceString("Board_size"),g,true);         //~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public boolean tell (Object  o, String s)                    //~@@@@R~
//    {   int n;                                                   //~@@@@R~
//        try                                                      //~@@@@R~
//        {   n=Integer.parseInt(s);                               //~@@@@R~
//            if (n<5 || n>59) return false;                       //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (NumberFormatException e)                          //~@@@@R~
//        {   return false;                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        ((GoFrame)o).doboardsize(n);                             //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Ask the user for permission to close the board frame.
*/

//class TextMarkQuestion extends CloseDialog                       //~@@@@R~
//{   GoFrame G;                                                   //~@@@@R~
//    TextField T;                                                 //~@@@@R~
//    Checkbox C;                                                  //~@@@@R~
//    public TextMarkQuestion (GoFrame g, String t)                //~@@@@R~
//    {   super(g,Global.resourceString("Text_Mark"),false);       //~@@@@R~
//        G=g;                                                     //~@@@@R~
//        setLayout(new BorderLayout());                           //~@@@@R~
//        add("Center",new SimplePanel(                            //~@@@@R~
//            new MyLabel(Global.resourceString("String")),1,      //~@@@@R~
//            T=new TextFieldAction(this,t),2));                   //~@@@@R~
//        T.setText(t);                                            //~@@@@R~
//        Panel ps=new MyPanel();                                  //~@@@@R~
//        ps.add(C=new CheckboxAction(this,Global.resourceString("Auto_Advance")));//~@@@@R~
//        C.setState(Global.getParameter("autoadvance",true));     //~@@@@R~
//        ps.add(new ButtonAction(this,Global.resourceString("Set")));//~@@@@R~
//        ps.add(new ButtonAction(this,Global.resourceString("Close")));//~@@@@R~
//        add("South",ps);                                         //~@@@@R~
//        Global.setpacked(this,"gettextmarkquestion",300,150);    //~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public void doAction (String o)                              //~@@@@R~
//    {   Global.notewindow(this,"gettextmarkquestion");           //~@@@@R~
//        Global.setParameter("autoadvance",C.getState());         //~@@@@R~
//        if (o.equals(Global.resourceString("Set")))              //~@@@@R~
//        {   G.setTextmark(T.getText());                          //~@@@@R~
//            Global.setParameter("textmark",T.getText());         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (o.equals(Global.resourceString("Close")))       //~@@@@R~
//        {   close(); setVisible(false); dispose();               //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//    public boolean close ()                                      //~@@@@R~
//    {   G.TMQ=null;                                              //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
//    public void advance ()                                       //~@@@@R~
//    {   if (!C.getState()) return;                               //~@@@@R~
//        String s=T.getText();                                    //~@@@@R~
//        if (s.length()==1)                                       //~@@@@R~
//        {   char c=s.charAt(0);                                  //~@@@@R~
//            c++;                                                 //~@@@@R~
//            T.setText(""+c);                                     //~@@@@R~
//            G.setTextmark(T.getText());                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else                                                     //~@@@@R~
//        {   try                                                  //~@@@@R~
//            {   int n=Integer.parseInt(s);                       //~@@@@R~
//                n=n+1;                                           //~@@@@R~
//                T.setText(""+n);                                 //~@@@@R~
//                G.setTextmark(T.getText());                      //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (Exception e) {   }                            //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
// Get/Set the name of the current node
*/

//class NodeNameEdit extends GetParameter                          //~@@@@R~
//{   public NodeNameEdit (GoFrame g, String s)                    //~@@@@R~
//    {   super(g,Global.resourceString("Name"),Global.resourceString("Node_Name"),g,true);//~@@@@R~
//        set(s);                                                  //~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public boolean tell (Object  o, String s)                    //~@@@@R~
//    {   ((GoFrame)o).setname(s);                                 //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Let the user edit the board colors (stones, shines and board).
Redraw the board, when done with OK.
*/

//class BoardColorEdit extends ColorEdit                           //~@@@@R~
//{   GoFrame GF;                                                  //~@@@@R~
//    public BoardColorEdit (GoFrame F, String s, int red, int green, int blue)//~@@@@R~
//    {   super(F,s,red,green,blue,true);                          //~@@@@R~
//        GF=F;                                                    //~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public BoardColorEdit (GoFrame F, String s, Color c)         //~@@@@R~
//    {   super(F,s,c.getRed(),c.getGreen(),c.getBlue(),true);     //~@@@@R~
//        GF=F;                                                    //~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public void doAction (String o)                              //~@@@@R~
//    {   super.doAction(o);                                       //~@@@@R~
//        if (Global.resourceString("OK").equals(o))               //~@@@@R~
//        {   GF.updateall();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Let the user edit the board fong
Redraw the board, when done with OK.
*/

//class BoardGetFontSize extends GetFontSize                       //~@@@@R~
//{   GoFrame GF;                                                  //~@@@@R~
//    public BoardGetFontSize (GoFrame F, String fontname, String deffontname,//~@@@@R~
//        String fontsize, int deffontsize, boolean flag)          //~@@@@R~
//    {   super(fontname,deffontname,fontsize,deffontsize,flag);   //~@@@@R~
//        GF=F;                                                    //~@@@@R~
//    }                                                            //~@@@@R~
//    public void doAction (String o)                              //~@@@@R~
//    {   super.doAction(o);                                       //~@@@@R~
//        if (Global.resourceString("OK").equals(o))               //~@@@@R~
//        {   Global.createfonts();                                //~@@@@R~
//            GF.updateall();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Display a dialog to edit game copyright and user.
*/

//class EditCopyright extends CloseDialog                          //~@@@@R~
//{   TextArea Copyright;                                          //~@@@@R~
//    TextField User;                                              //~@@@@R~
//    Node N;                                                      //~@@@@R~
//    public EditCopyright (GoFrame f, Node n)                     //~@@@@R~
//    {   super(f,Global.resourceString("Copyright_of_Game"),false);//~@@@@R~
//        Panel p1=new MyPanel();                                  //~@@@@R~
//        N=n;                                                     //~@@@@R~
//        p1.setLayout(new GridLayout(0,2));                       //~@@@@R~
//        p1.add(new MyLabel(Global.resourceString("User")));      //~@@@@R~
//        p1.add(User=new GrayTextField(n.getaction("US")));       //~@@@@R~
//        add("North",p1);                                         //~@@@@R~
//        Panel p2=new MyPanel();                                  //~@@@@R~
//        p2.setLayout(new BorderLayout());                        //~@@@@R~
//        p2.add("North",new MyLabel(Global.resourceString("Copyright")));//~@@@@R~
//        p2.add("Center",Copyright=new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY));//~@@@@R~
//        Copyright.setBackground(Global.gray);                    //~@@@@R~
//        add("Center",p2);                                        //~@@@@R~
//        Panel pb=new MyPanel();                                  //~@@@@R~
//        pb.add(new ButtonAction(this,Global.resourceString("OK")));//~@@@@R~
//        pb.add(new ButtonAction(this,Global.resourceString("Cancel")));//~@@@@R~
//        add("South",pb);                                         //~@@@@R~
//        Global.setwindow(this,"editcopyright",350,400);          //~@@@@R~
//        show();                                                  //~@@@@R~
//        Copyright.setText(n.getaction("CP"));                    //~@@@@R~
//    }                                                            //~@@@@R~
//    public void doAction (String o)                              //~@@@@R~
//    {   Global.notewindow(this,"editcopyright");                 //~@@@@R~
//        if (Global.resourceString("OK").equals(o))               //~@@@@R~
//        {   N.setaction("US",User.getText());                    //~@@@@R~
//            N.setaction("CP",Copyright.getText());               //~@@@@R~
//        }                                                        //~@@@@R~
//        setVisible(false); dispose();                            //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Ask, if a complete subtree is to be deleted.
*/

//class AskUndoQuestion extends Question                           //~@@@@R~
//{   public boolean Result=false;                                 //~@@@@R~
//    public AskUndoQuestion (Frame f)                             //~@@@@R~
//    {   super(f,Global.resourceString("Delete_all_subsequent_moves_"),Global.resourceString("Delete_Tree"),f,true);//~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public void tell (Question q, Object o, boolean f)           //~@@@@R~
//    {   q.setVisible(false); q.dispose();                        //~@@@@R~
//        Result=f;                                                //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
Ask, if a node is to be inserted and the tree thus changed.
*/

//class AskInsertQuestion extends Question                         //~@@@@R~
//{   public boolean Result=false;                                 //~@@@@R~
//    public AskInsertQuestion (Frame f)                           //~@@@@R~
//    {   super(f,Global.resourceString("Change_Game_Tree_"),Global.resourceString("Change_Game_Tree"),f,true);//~@@@@R~
//        show();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
//    public void tell (Question q, Object o, boolean f)           //~@@@@R~
//    {   q.setVisible(false); q.dispose();                        //~@@@@R~
//        Result=f;                                                //~@@@@R~
//    }                                                            //~@@@@R~
//}                                                                //~@@@@R~

/**
The GoFrame class is a frame, which contains the board,
the comment window and the navigation buttons (at least).
<P>
This class implements BoardInterface. This is done to make clear
what routines are called from the board and to give the board a
beans appearance.
<P>
The layout is a  panel of class BoardCommentPanel, containing two panels
for the board (BoardPanel) and for the comments (plus the ExtraSendField
in ConnectedGoFrame). Below is a 3D panel for the buttons. The BoardCommentPanel
takes care of the layout for its components.
<P>
This class handles all actions in it, besides the mouse actions on the
board, which are handled by Board.
<P>
Note that the Board class modifies the appearance of buttons and
takes care of the comment window, the next move label and the board
position label.
<P>
Several private classes in GoFrame.java contain dialogs to enter game
information, copyright, text marks, etc.
@see jagoclient.board.Board
*/

// The parent class for a frame containing the board, navigation buttons
// and menus.
// This board has a constructor, which initiates menus to be used as a local
// board. For Partner of IGS games there is the ConnectedGoFrame child, which
// uses another menu structure.
// Furthermore, it has methods to handle lots of user actions.
public class GoFrame extends CloseFrame
//  implements FilenameFilter, KeyListener, BoardInterface, ClipboardOwner,//~@@@@R~
	implements  KeyListener, BoardInterface ,URunnableI                       //~@@@@R~
//    IconBarListener                                                //~1221M~//~@@@@R~
//{	TextArea T; // For comments                                    //~@@@@R~
{                                                                  //~@@@@I~
//	TextArea T; // For comments                                    //~@@@@I~
	public Label L,Lm; // For board informations
    TextArea Comment; // For comments                            //~@@@@R~//~@@@2R~
//    String Dir; // FileDialog directory                          //~@@@@R~
	public Board B; // The board itself
	// menu check items:
//    CheckboxMenuItem SetBlack,SetWhite,Black,White,Mark,Letter,Hide,//~@@@@R~
//        Square,Cross,Circle,Triangle,TextMark;                   //~@@@@R~
	public Color BoardColor,BlackColor,BlackSparkleColor,
		WhiteColor,WhiteSparkleColor,MarkerColor,LabelColor;
	CheckboxMenuItem Coordinates,UpperLeftCoordinates,LowerRightCoordinates;
	CheckboxMenuItem PureSGF,CommentSGF,DoSound,BeepOnly,TrueColor,Alias,
		TrueColorStones,SmallerStones,MenuLastNumber,MenuTarget,Shadows,
		BlackOnly,UseXML,UseSGF;
	public boolean BWColor=false,LastNumber=false,ShowTarget=false;
	public boolean swWaitingPartnerResponse;                       //~1A38I~
	CheckboxMenuItem MenuBWColor,ShowButtons;
	CheckboxMenuItem VHide,VCurrent,VChild,VNumbers;
//    String Text=Global.getParameter("textmark","A");             //~@@@2R~
//    boolean Show;                                                //~@@@2R~
    protected boolean Show;                                        //~@@@2I~
//    TextMarkQuestion TMQ;                                        //~@@@@R~
//    IconBar IB;                                                  //~@@@@R~
	Panel ButtonP;
	String DefaultTitle="";
//    NavigationPanel Navigation;                                  //~@@@@R~

//    public GoFrame (String s)                                    //~@@@2R~
    public GoFrame (int Presid,String s)                           //~@@@2I~
		// For children, who set up their own menus
//  {   super(s);                                                  //~@@@2R~
    {                                                              //~@@@2I~
		super(Presid,s/*title*/);                                  //~@@@2I~
		DefaultTitle=s;
//        seticon("iboard.gif");                                   //~@@@2R~
		setcolors();
	}

	void setcolors ()
	{	// Take colors from Global parameters.
		BoardColor=Global.getColor("boardcolor",170,120,70);
		BlackColor=Global.getColor("blackcolor",30,30,30);
		BlackSparkleColor=Global.getColor("blacksparklecolor",120,120,120);
		WhiteColor=Global.getColor("whitecolor",210,210,210);
		WhiteSparkleColor=Global.getColor("whitesparklecolor",250,250,250);
		MarkerColor=Global.getColor("markercolor",Color.blue);
		LabelColor=Global.getColor("labelcolor",Color.pink.darker());
		Global.setColor("boardcolor",BoardColor);
		Global.setColor("blackcolor",BlackColor);
		Global.setColor("blacksparklecolor",BlackSparkleColor);
		Global.setColor("whitecolor",WhiteColor);
		Global.setColor("whitesparklecolor",WhiteSparkleColor);
		Global.setColor("markercolor",MarkerColor);
		Global.setColor("labelcolor",LabelColor);
	}
////************************************************************************//~@@@2R~
////*from LocalGameQuestion                                        //~@@@2R~
////************************************************************************//~@@@2R~
//    public GoFrame (Frame f, String s, LocalGameQuestion Plgq)   //~@@@2R~
//    {                                                            //~@@@2R~
//        super(s);                                                //~@@@2R~
//        DefaultTitle=s;                                          //~@@@2R~
//        // Colors                                                //~@@@2R~
//        setcolors();                                             //~@@@2R~
//        seticon("iboard.gif");                                   //~@@@2R~
////      L=new MyLabel(Global.resourceString("New_Game"));        //~@@@2R~
//        L=new MyLabel(R.id.GameInfo);                            //~@@@2R~
////      Lm=new MyLabel("--");                                    //~@@@2R~
//        Lm=new MyLabel(R.id.SetStone);                           //~@@@2R~
//        B=new Board(AG.propBoardSize,this);  //boardsize was set at MainFrame//~@@@2R~
//        B.putInitialPiece(Plgq.movefirst?1:-1,Plgq.bishop,Plgq.knight,Plgq.knightpattern,Plgq.gameover);//~@@@2R~
//        Show=true;                                               //~@@@2R~
//        B.addKeyListener(this);                                  //~@@@2R~
//        setVisible(true);                                        //~@@@2R~
//        ShowTarget=true;    //@@@@test                           //~@@@2R~
//        repaint();                                               //~@@@2R~
//    }                                                            //~@@@2R~
//************************************************************************//~@@@2I~
	public GoFrame (Frame f, String s)
    	                                     //~@@@2I~
		// Constructur for local board menus.
	{	super(s);
		DefaultTitle=s;
		// Colors
		setcolors();
//        seticon("iboard.gif");                                   //~@@@2R~
//        setLayout(new BorderLayout());                           //~@@@@R~
		// Menu
		MenuBar M=new MenuBar();
		setMenuBar(M);
//        Menu file=new MyMenu(Global.resourceString("File"));     //~@@@@R~
//        M.add(file);                                             //~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("New")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Load")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Save")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Save_Position")));//~@@@@R~
//        file.addSeparator();                                     //~@@@@R~
//        file.add(UseXML=                                         //~@@@@R~
//            new CheckboxMenuItemAction(this,Global.resourceString("Use_XML")));//~@@@@R~
//        UseXML.setState(Global.getParameter("xml",false));       //~@@@@R~
//        file.add(UseSGF=                                         //~@@@@R~
//            new CheckboxMenuItemAction(this,Global.resourceString("Use_SGF")));//~@@@@R~
//        UseSGF.setState(!Global.getParameter("xml",false));      //~@@@@R~
//        file.addSeparator();                                     //~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Load_from_Clipboard")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Copy_to_Clipboard")));//~@@@@R~
//        file.addSeparator();                                     //~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Mail")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Ascii_Mail")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Print")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Save_Bitmap")));//~@@@@R~
//        file.addSeparator();                                     //~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Board_size")));//~@@@@R~
//        file.addSeparator();                                     //~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Add_Game")));//~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Remove_Game")));//~@@@@R~
//        file.addSeparator();                                     //~@@@@R~
//        file.add(new MenuItemAction(this,Global.resourceString("Close")));//~@@@@R~
//        Menu set=new MyMenu(Global.resourceString("Set"));       //~@@@@R~
//        M.add(set);                                              //~@@@@R~
//        set.add(Mark=new CheckboxMenuItemAction(this,Global.resourceString("Mark")));//~@@@@R~
//        set.add(Letter=new CheckboxMenuItemAction(this,Global.resourceString("Letter")));//~@@@@R~
//        set.add(Hide=new CheckboxMenuItemAction(this,Global.resourceString("Delete")));//~@@@@R~
//        Menu mark=new MyMenu(Global.resourceString("Special_Mark"));//~@@@@R~
//        mark.add(Square=new CheckboxMenuItemAction(this,Global.resourceString("Square")));//~@@@@R~
//        mark.add(Circle=new CheckboxMenuItemAction(this,Global.resourceString("Circle")));//~@@@@R~
//        mark.add(Triangle=new CheckboxMenuItemAction(this,Global.resourceString("Triangle")));//~@@@@R~
//        mark.add(Cross=new CheckboxMenuItemAction(this,Global.resourceString("Cross")));//~@@@@R~
//        mark.addSeparator();                                     //~@@@@R~
//        mark.add(TextMark=new CheckboxMenuItemAction(this,Global.resourceString("Text")));//~@@@@R~
//        set.add(mark);                                           //~@@@@R~
//        set.addSeparator();                                      //~@@@@R~
//        set.add(new MenuItemAction(this,Global.resourceString("Resume_playing")));//~@@@@R~
//        set.addSeparator();                                      //~@@@@R~
//        set.add(new MenuItemAction(this,Global.resourceString("Pass")));//~@@@@R~
//        set.addSeparator();                                      //~@@@@R~
//        set.add(SetBlack=new CheckboxMenuItemAction(this,Global.resourceString("Set_Black")));//~@@@@R~
//        set.add(SetWhite=new CheckboxMenuItemAction(this,Global.resourceString("Set_White")));//~@@@@R~
//        set.addSeparator();                                      //~@@@@R~
//        set.add(Black=new CheckboxMenuItemAction(this,Global.resourceString("Black_to_play")));//~@@@@R~
//        set.add(White=new CheckboxMenuItemAction(this,Global.resourceString("White_to_play")));//~@@@@R~
//        set.addSeparator();                                      //~@@@@R~
//        set.add(new MenuItemAction(this,Global.resourceString("Undo_Adding_Removing")));//~@@@@R~
//        set.add(new MenuItemAction(this,Global.resourceString("Clear_all_marks")));//~@@@@R~
//        Menu var=new MyMenu(Global.resourceString("Nodes"));     //~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Insert_Node")));//~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Insert_Variation")));//~@@@@R~
//        var.addSeparator();                                      //~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Next_Game")));//~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Previous_Game")));//~@@@@R~
//        var.addSeparator();                                      //~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Search")));//~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Search_Again")));//~@@@@R~
//        var.addSeparator();                                      //~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Node_Name")));//~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Goto_Next_Name")));//~@@@@R~
//        var.add(new MenuItemAction(this,Global.resourceString("Goto_Previous_Name")));//~@@@@R~
//        M.add(var);                                              //~@@@@R~
//        Menu score=new MyMenu(Global.resourceString("Finish_Game"));//~@@@@R~
//        M.add(score);                                            //~@@@@R~
//        score.add(new MenuItemAction(this,Global.resourceString("Remove_groups")));//~@@@@R~
//        score.add(new MenuItemAction(this,Global.resourceString("Score")));//~@@@@R~
//        score.addSeparator();                                    //~@@@@R~
//        score.add(new MenuItemAction(this,Global.resourceString("Game_Information")));//~@@@@R~
//        score.add(new MenuItemAction(this,Global.resourceString("Game_Copyright")));//~@@@@R~
//        score.addSeparator();                                    //~@@@@R~
//        score.add(new MenuItemAction(this,Global.resourceString("Prisoner_Count")));//~@@@@R~
//        Menu options=new MyMenu(Global.resourceString("Options"));//~@@@@R~
//        Menu mc=new MyMenu(Global.resourceString("Coordinates"));//~@@@@R~
//        mc.add(Coordinates=new CheckboxMenuItemAction(this,Global.resourceString("On")));//~@@@@R~
//        Coordinates.setState(Global.getParameter("coordinates",true));//~@@@@R~
//        mc.add(UpperLeftCoordinates=new CheckboxMenuItemAction(this,Global.resourceString("Upper_Left")));//~@@@@R~
//        UpperLeftCoordinates.setState(Global.getParameter("upperleftcoordinates",true));//~@@@@R~
//        mc.add(LowerRightCoordinates=new CheckboxMenuItemAction(this,Global.resourceString("Lower_Right")));//~@@@@R~
//        LowerRightCoordinates.setState(                          //~@@@@R~
////@@@@      Global.getParameter("lowerrightcoordinates",true));    //~1430R~//~@@@@R~
//            Global.getParameter("lowerrightcoordinates",false)); //defalt unmatch with Board,it cause Menuitem status and board disply//~1430I~//~@@@@R~
//        options.add(mc);                                         //~@@@@R~
//        options.addSeparator();                                  //~@@@@R~
//        Menu colors=new MyMenu(Global.resourceString("Colors")); //~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Board_Color")));//~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Black_Color")));//~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Black_Sparkle_Color")));//~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("White_Color")));//~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("White_Sparkle_Color")));//~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Label_Color")));//~@@@@R~
//        colors.add(new MenuItemAction(this,Global.resourceString("Marker_Color")));//~@@@@R~
//        options.add(colors);                                     //~@@@@R~
//        options.add(MenuBWColor=new CheckboxMenuItemAction(this,Global.resourceString("Use_B_W_marks")));//~@@@@R~
//        MenuBWColor.setState(Global.getParameter("bwcolor",false));//~@@@@R~
//        BWColor=MenuBWColor.getState();                          //~@@@@R~
//        options.add(PureSGF=new CheckboxMenuItemAction(this,Global.resourceString("Save_pure_SGF")));//~@@@@R~
//        PureSGF.setState(Global.getParameter("puresgf",false));  //~@@@@R~
//        options.add(CommentSGF=new CheckboxMenuItemAction(this,Global.resourceString("Use_SGF_Comments")));//~@@@@R~
//        CommentSGF.setState(Global.getParameter("sgfcomments",false));//~@@@@R~
//        options.addSeparator();                                  //~@@@@R~
//        Menu fonts=new MyMenu(Global.resourceString("Fonts"));   //~@@@@R~
//        fonts.add(new MenuItemAction(this,Global.resourceString("Board_Font")));//~@@@@R~
//        fonts.add(new MenuItemAction(this,Global.resourceString("Fixed_Font")));//~@@@@R~
//        fonts.add(new MenuItemAction(this,Global.resourceString("Normal_Font")));//~@@@@R~
//        options.add(fonts);                                      //~@@@@R~
//        Menu variations=new MyMenu(Global.resourceString("Variation_Display"));//~@@@@R~
//        variations.add(VCurrent=new CheckboxMenuItemAction(this, //~@@@@R~
//            Global.resourceString("To_Current")));               //~@@@@R~
//        VCurrent.setState(Global.getParameter("vcurrent",true)); //~@@@@R~
//        variations.add(VChild=new CheckboxMenuItemAction(this,   //~@@@@R~
//            Global.resourceString("To_Child")));                 //~@@@@R~
//        VChild.setState(!Global.getParameter("vcurrent",true));  //~@@@@R~
//        variations.add(VHide=new CheckboxMenuItemAction(this,    //~@@@@R~
//            Global.resourceString("Hide")));                     //~@@@@R~
//        VHide.setState(Global.getParameter("vhide",false));      //~@@@@R~
//        variations.addSeparator();                               //~@@@@R~
//        variations.add(VNumbers=new CheckboxMenuItemAction(this, //~@@@@R~
//            Global.resourceString("Continue_Numbers")));         //~@@@@R~
//        VNumbers.setState(Global.getParameter("variationnumbers",false));//~@@@@R~
//        options.add(variations);                                 //~@@@@R~
//        options.addSeparator();                                  //~@@@@R~
//        options.add(MenuTarget=new CheckboxMenuItemAction(this,Global.resourceString("Show_Target")));//~@@@@R~
//        MenuTarget.setState(Global.getParameter("showtarget",true));//~@@@@R~
//        ShowTarget=MenuTarget.getState();                        //~@@@@R~
//        options.add(MenuLastNumber=new CheckboxMenuItemAction(this,Global.resourceString("Last_Number")));//~@@@@R~
//        MenuLastNumber.setState(Global.getParameter("lastnumber",false));//~@@@@R~
//        LastNumber=MenuLastNumber.getState();                    //~@@@@R~
//        options.add(new MenuItemAction(this,Global.resourceString("Last_50")));//~@@@@R~
//        options.add(new MenuItemAction(this,Global.resourceString("Last_100")));//~@@@@R~
//        options.addSeparator();                                  //~@@@@R~
//        options.add(TrueColor=new CheckboxMenuItemAction(this,Global.resourceString("True_Color_Board")));//~@@@@R~
//        TrueColor.setState(Global.getParameter("beauty",true));  //~@@@@R~
//        options.add(TrueColorStones=new CheckboxMenuItemAction(this,Global.resourceString("True_Color_Stones")));//~@@@@R~
//        TrueColorStones.setState(Global.getParameter("beautystones",true));//~@@@@R~
//        options.add(Alias=new CheckboxMenuItemAction(this,Global.resourceString("Anti_alias_Stones")));//~@@@@R~
//        Alias.setState(Global.getParameter("alias",true));       //~@@@@R~
//        options.add(Shadows=new CheckboxMenuItemAction(this,Global.resourceString("Shadows")));//~@@@@R~
//        Shadows.setState(Global.getParameter("shadows",true));   //~@@@@R~
//        options.add(SmallerStones=new CheckboxMenuItemAction(this,Global.resourceString("Smaller_Stones")));//~@@@@R~
//        SmallerStones.setState(Global.getParameter("smallerstones",false));//~@@@@R~
//        options.add(BlackOnly=new CheckboxMenuItemAction(this,Global.resourceString("Black_Only")));//~@@@@R~
//        BlackOnly.setState(Global.getParameter("blackonly",false));//~@@@@R~
//        options.addSeparator();                                  //~@@@@R~
//        options.add(new MenuItemAction(this,Global.resourceString("Set_Encoding")));//~@@@@R~
//        options.add(                                             //~@@@@R~
//            ShowButtons=new CheckboxMenuItemAction(this,         //~@@@@R~
//            Global.resourceString("Show_Buttons")));             //~@@@@R~
//        ShowButtons.setState(Global.getParameter("showbuttons",true));//~@@@@R~
//        Menu help=new MyMenu(Global.resourceString("Help"));     //~@@@@R~
//        help.add(new MenuItemAction(this,Global.resourceString("Board_Window")));//~@@@@R~
//        help.add(new MenuItemAction(this,Global.resourceString("Making_Moves")));//~@@@@R~
//        help.add(new MenuItemAction(this,Global.resourceString("Keyboard_Shortcuts")));//~@@@@R~
//        help.add(new MenuItemAction(this,Global.resourceString("About_Variations")));//~@@@@R~
//        help.add(new MenuItemAction(this,Global.resourceString("Playing_Games")));//~@@@@R~
//        help.add(new MenuItemAction(this,Global.resourceString("Mailing_Games")));//~@@@@R~
//        M.add(options);                                          //~@@@@R~
//        M.setHelpMenu(help);                                     //~@@@@R~
//        // Board                                                 //~@@@@R~
//          L=new MyLabel(Global.resourceString("New_Game"));        //~@@@@R~//~@@@2R~
//          Lm=new MyLabel("--");                                    //~@@@@R~//~@@@2R~
          L=new MyLabel(this,R.id.GameInfo);                       //~@@@2I~
          Lm=new MyLabel(this,R.id.SetStone);                      //~@@@2I~
//        B=new Board(19,this);                                    //~@@@@R~
          B=new Board(AG.propBoardSize,this);  //boardsize was set at MainFrame//~@@@@R~
//          B.putInitialPiece(1/*color=Black*/,0/*bishop*/,0/*knight*/,0/*gameover*/,0/*gameover2*/,0/*gameoptions*/);//~@@@@I~//~@@@2R~
//        Panel BP=new MyPanel();                                  //~@@@@R~
//        BP.setLayout(new BorderLayout());                        //~@@@@R~
//        BP.add("Center",B);                                      //~@@@@R~
//        // Add the label                                         //~@@@@R~
//        SimplePanel sp=                                          //~@@@@R~
//            new SimplePanel((Component)L,80,(Component)Lm,20);   //~@@@@R~
//        BP.add("South",sp);                                      //~@@@@R~
//        sp.setBackground(Global.gray);                           //~@@@@R~
//        // Text Area                                             //~@@@@R~
//        Comment=new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@@R~
//        Comment=new TextArea("",R.id.Comment,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@2R~
        Comment=new TextArea(this,"",R.id.Comment,0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);//~@@@2I~
        Comment.setFont(Global.SansSerif);                       //~@@@@R~//~@@@2R~
        Comment.setBackground(Global.gray);                      //~@@@@R~//~@@@2R~
//        Panel bcp;                                               //~@@@@R~
//        if (Global.getParameter("shownavigationtree",true))      //~@@@@R~
//        {   Navigation=new NavigationPanel(B);                   //~@@@@R~
//            bcp=new BoardCommentPanel(BP,                        //~@@@@R~
//                new CommentNavigationPanel(Comment,new Panel3D(Navigation)),B);//~@@@@R~
//        }                                                        //~@@@@R~
//        else bcp=new BoardCommentPanel(BP,Comment,B);            //~@@@@R~
//        add("Center",bcp);                                       //~@@@@R~
//        // Navigation panel                                      //~@@@@R~
//        IB=createIconBar();                                      //~@@@@R~
//        ButtonP=new Panel3D(IB);                                 //~@@@@R~
//        if (Global.getParameter("showbuttons",true))             //~@@@@R~
//            add("South",ButtonP);                                //~@@@@R~
//        // Directory for FileDialog                              //~@@@@R~
//        Dir=new String("");                                      //~@@@@R~
//        Global.setwindow(this,"board",500,450,false);            //~@@@@R~
//        validate();                                              //~@@@@R~
          Show=true;                                               //~@@@@R~
          B.addKeyListener(this);                                  //~@@@@R~
//        if (Navigation!=null) Navigation.addKeyListener(B);      //~@@@@R~
//        addmenuitems();                                          //~@@@@R~
//        ButtonAction.init(this,0,R.id.Resign);  //setup doAction //~@@@2R~
//        ButtonAction.init(this,0,R.id.Help);  //setup doAction   //~@@@2R~
        new ButtonAction(this,0,R.id.Resign);  //setup doAction//~@@@2I~
        new ButtonAction(this,0,R.id.Help);  //setup doAction //~@@@2I~
		setVisible(true);
		repaint();
	}
	
	public void addmenuitems() 
		// for children to add menu items (because of bug in Linux Java 1.5)
	{
	}
	
//    public IconBar createIconBar ()                              //~@@@@R~
//    {   IconBar I=new IconBar(this);                             //~@@@@R~
//        I.Resource="/jagoclient/icons/";                         //~@@@@R~
//        I.addLeft("undo");                                       //~@@@@R~
//        I.addSeparatorLeft();                                    //~@@@@R~
//        I.addLeft("allback");                                    //~@@@@R~
//        I.addLeft("fastback");                                   //~@@@@R~
//        I.addLeft("back");                                       //~@@@@R~
//        I.addLeft("forward");                                    //~@@@@R~
//        I.addLeft("fastforward");                                //~@@@@R~
//        I.addLeft("allforward");                                 //~@@@@R~
//        I.addSeparatorLeft();                                    //~@@@@R~
//        I.addLeft("variationback");                              //~@@@@R~
//        I.addLeft("variationforward");                           //~@@@@R~
//        I.addLeft("variationstart");                             //~@@@@R~
//        I.addLeft("main");                                       //~@@@@R~
//        I.addLeft("mainend");                                    //~@@@@R~
//        I.addSeparatorLeft();                                    //~@@@@R~
//        String icons[]={                                         //~@@@@R~
//            "mark","square","triangle","circle","letter","text","",//~@@@@R~
//            "black","white","","setblack","setwhite","delete"};  //~@@@@R~
//        I.addToggleGroupLeft(icons);                             //~@@@@R~
//        I.addSeparatorLeft();                                    //~@@@@R~
//        I.addLeft("deletemarks");                                //~@@@@R~
//        I.addLeft("play");                                       //~@@@@R~
//        I.setIconBarListener(this);                              //~@@@@R~
//        return I;                                                //~@@@@R~
//    }                                                            //~@@@@R~
	
//    public void iconPressed (String s)                           //~@@@@R~
//    {   if (s.equals("undo")) doAction(Global.resourceString("Undo"));//~@@@@R~
//        else if (s.equals("allback")) doAction("I<<");           //~@@@@R~
//        else if (s.equals("fastback")) doAction("<<");           //~@@@@R~
//        else if (s.equals("back")) doAction("<");                //~@@@@R~
//        else if (s.equals("forward")) doAction(">");             //~@@@@R~
//        else if (s.equals("fastforward")) doAction(">>");        //~@@@@R~
//        else if (s.equals("allforward")) doAction(">>I");        //~@@@@R~
//        else if (s.equals("variationback")) doAction("<V");      //~@@@@R~
//        else if (s.equals("variationstart")) doAction("V");      //~@@@@R~
//        else if (s.equals("variationforward")) doAction("V>");   //~@@@@R~
//        else if (s.equals("main")) doAction("*");                //~@@@@R~
//        else if (s.equals("mainend")) doAction("**");            //~@@@@R~
//        else if (s.equals("mark")) B.mark();                     //~@@@@R~
//        else if (s.equals("mark")) B.mark();                     //~@@@@R~
//        else if (s.equals("square")) B.specialmark(Field.SQUARE);//~@@@@R~
//        else if (s.equals("triangle")) B.specialmark(Field.TRIANGLE);//~@@@@R~
//        else if (s.equals("circle")) B.specialmark(Field.CIRCLE);//~@@@@R~
//        else if (s.equals("letter")) B.letter();                 //~@@@@R~
//        else if (s.equals("text"))                               //~@@@@R~
//        {   B.textmark(Text);                                    //~@@@@R~
//            if (TMQ==null) TMQ=new TextMarkQuestion(this,Text);  //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (s.equals("black")) B.black();                   //~@@@@R~
//        else if (s.equals("white")) B.white();                   //~@@@@R~
//        else if (s.equals("setblack")) B.setblack();             //~@@@@R~
//        else if (s.equals("setwhite")) B.setwhite();             //~@@@@R~
//        else if (s.equals("delete")) B.deletestones();           //~@@@@R~
//        else if (s.equals("deletemarks")) B.clearmarks();        //~@@@@R~
//        else if (s.equals("play")) B.resume();                   //~@@@@R~
//    }                                                            //~@@@@R~

	public void doAction (String o)
//  {	if (Global.resourceString("Undo").equals(o))               //~@@@@R~
	{                                                              //~@@@@I~
//        if (Global.resourceString("Undo").equals(o))             //~@@@@I~
//        {   B.undo();                                            //~@@@@R~
//        }                                                        //~@@@@R~
        if (o.equals(AG.resource.getString(R.string.Close)))       //~@@@2I~
		{                                                          //~@@@2I~
			close();                                               //~@@@2I~
		}                                                          //~@@@2I~
		else if (Global.resourceString("Close").equals(o))
		{	close();
		}
//        else if (Global.resourceString("Board_size").equals(o))  //~@@@@R~
//        {   boardsize();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("<".equals(o))                                  //~@@@@R~
//        {   B.back();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (">".equals(o))                                  //~@@@@R~
//        {   B.forward();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (">>".equals(o))                                 //~@@@@R~
//        {   B.fastforward();                                     //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("<<".equals(o))                                 //~@@@@R~
//        {   B.fastback();                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("I<<".equals(o))                                //~@@@@R~
//        {   B.allback();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (">>I".equals(o))                                //~@@@@R~
//        {   B.allforward();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("<V".equals(o))                                 //~@@@@R~
//        {   B.varleft();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("V>".equals(o))                                 //~@@@@R~
//        {   B.varright();                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("V".equals(o))                                  //~@@@@R~
//        {   B.varup();                                           //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("**".equals(o))                                 //~@@@@R~
//        {   B.varmaindown();                                     //~@@@@R~
//        }                                                        //~@@@@R~
//        else if ("*".equals(o))                                  //~@@@@R~
//        {   B.varmain();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Pass").equals(o))        //~@@@@R~
//        {   B.pass();                                            //~@@@@R~
//            notepass();                                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Resume_playing").equals(o))//~@@@@R~
//        {   B.resume();                                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Clear_all_marks").equals(o))//~@@@@R~
//        {   B.clearmarks();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Undo_Adding_Removing").equals(o))//~@@@@R~
//        {   B.clearremovals();                                   //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Remove_groups").equals(o))//~@@@@R~
//        {   B.score();                                           //~@@@@R~
//        }                                                        //~@@@@R~
		else if (Global.resourceString("Score").equals(o))
		{	String s=B.done();
			if (s!=null) new Message(this,s);
		}
		else if (Global.resourceString("Local_Count").equals(o))
		{	new Message(this,B.docount());
		}
//        else if (Global.resourceString("New").equals(o))         //~@@@@R~
//        {   B.deltree(); B.copy();                               //~@@@@R~
//            setTitle(DefaultTitle);                              //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Mail").equals(o)) // mail the game//~@@@@R~
//        {   ByteArrayOutputStream ba=new ByteArrayOutputStream(50000);//~@@@@R~
//            try                                                  //~@@@@R~
//            {   if (Global.getParameter("xml",false))            //~@@@@R~
//                {   PrintWriter po=new PrintWriter(              //~@@@@R~
//                    new OutputStreamWriter(ba,"UTF8"),true);     //~@@@@R~
//                    B.saveXML(po,"utf-8");                       //~@@@@R~
//                    po.close();                                  //~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   PrintWriter po=new PrintWriter(ba,true);     //~@@@@R~
//                    B.save(po);                                  //~@@@@R~
//                    po.close();                                  //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (Exception ex) {  }                            //~@@@@R~
//            new MailDialog(this,ba.toString());                  //~@@@@R~
//            return;                                              //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Ascii_Mail").equals(o))  //~@@@@R~
//            // ascii dump of the game                            //~@@@@R~
//        {   ByteArrayOutputStream ba=new ByteArrayOutputStream(10000);//~@@@@R~
//            PrintWriter po=new PrintWriter(ba,true);             //~@@@@R~
//            try                                                  //~@@@@R~
//            {   B.asciisave(po);                                 //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (Exception ex) {  }                            //~@@@@R~
//            new MailDialog(this,ba.toString());                  //~@@@@R~
//            return;                                              //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Print").equals(o)) // print the game//~@@@@R~
//        {   B.println(Global.frame());                             //~@@@@R~//~@@@2R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Save").equals(o)) // save the game//~@@@@R~
//        {   // File dialog handling                              //~@@@@R~
//            FileDialog fd=new FileDialog(this,Global.resourceString("Save"),//~@@@@R~
//                FileDialog.SAVE);                                //~@@@@R~
//            if (!Dir.equals("")) fd.setDirectory(Dir);           //~@@@@R~
//            String s=((Node)B.firstnode()).getaction("GN");      //~@@@@R~
//            if (s!=null && !s.equals(""))                        //~@@@@R~
//                fd.setFile(s+"."+Global.getParameter("extension",//~@@@@R~
//                Global.getParameter("xml",false)?"xml":"sgf"));  //~@@@@R~
//            else                                                 //~@@@@R~
//                fd.setFile("*."+Global.getParameter("extension", //~@@@@R~
//                Global.getParameter("xml",false)?"xml":"sgf"));  //~@@@@R~
//            fd.setFilenameFilter(this);                          //~@@@@R~
//            center(fd);                                          //~@@@@R~
//            fd.show();                                           //~@@@@R~
//            String fn=fd.getFile();                              //~@@@@R~
//            if (fn==null) return;                                //~@@@@R~
//            setGameTitle(FileName.purefilename(fn));             //~@@@@R~
//            Dir=fd.getDirectory();                               //~@@@@R~
//            try // print out using the board class               //~@@@@R~
//            {   PrintWriter fo;                                  //~@@@@R~
//                if (Global.getParameter("xml",false))            //~@@@@R~
//                {   if (Global.isApplet())                       //~@@@@R~
//                    {   fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                            new FileOutputStream(fd.getDirectory()+fn),"UTF8"));//~@@@@R~
//                        B.saveXML(fo,"utf-8");                   //~@@@@R~
//                    }                                            //~@@@@R~
//                    else                                         //~@@@@R~
//                    {   String Encoding=Global.getParameter("encoding",//~@@@@R~
//                            System.getProperty("file.encoding")).toUpperCase();//~@@@@R~
//                        if (Encoding.equals(""))                 //~@@@@R~
//                        {   fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                                new FileOutputStream(fd.getDirectory()+fn),//~@@@@R~
//                                "UTF8"));                        //~@@@@R~
//                            B.saveXML(fo,"utf-8");               //~@@@@R~
//                        }                                        //~@@@@R~
//                        else                                     //~@@@@R~
//                        {   String XMLEncoding="";               //~@@@@R~
//                            if (Encoding.equals("CP1252") ||     //~@@@@R~
//                                Encoding.equals("ISO8859_1"))    //~@@@@R~
//                            {   Encoding="ISO8859_1";            //~@@@@R~
//                                XMLEncoding="iso-8859-1";        //~@@@@R~
//                            }                                    //~@@@@R~
//                            else                                 //~@@@@R~
//                            {   Encoding="UTF8";                 //~@@@@R~
//                                XMLEncoding="utf-8";             //~@@@@R~
//                            }                                    //~@@@@R~
//                            FileOutputStream fos=                //~@@@@R~
//                                new FileOutputStream(fd.getDirectory()+fn);//~@@@@R~
//                            try                                  //~@@@@R~
//                            {   fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                                    fos,Encoding));              //~@@@@R~
//                            }                                    //~@@@@R~
//                            catch (Exception e)                  //~@@@@R~
//                            {   Encoding="UTF8";                 //~@@@@R~
//                                XMLEncoding="utf-8";             //~@@@@R~
//                                fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                                    fos,Encoding));              //~@@@@R~
//                            }                                    //~@@@@R~
//                            B.saveXML(fo,XMLEncoding);           //~@@@@R~
//                        }                                        //~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   if (Global.isApplet())                       //~@@@@R~
//                    fo=                                          //~@@@@R~
//                        new PrintWriter(new OutputStreamWriter(  //~@@@@R~
//                        new FileOutputStream(fd.getDirectory()+fn),//~@@@@R~
//                        Global.getParameter("encoding","ASCII")));//~@@@@R~
//                    else                                         //~@@@@R~
//                        fo=                                      //~@@@@R~
//                        new PrintWriter(new OutputStreamWriter(  //~@@@@R~
//                        new FileOutputStream(fd.getDirectory()+fn),//~@@@@R~
//                        Global.getParameter("encoding",          //~@@@@R~
//                        System.getProperty("file.encoding"))));  //~@@@@R~
//                    B.save(fo);                                  //~@@@@R~
//                }                                                //~@@@@R~
//                fo.close();                                      //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (IOException ex)                               //~@@@@R~
//            {   new Message(this,Global.resourceString("Write_error_")+"\n"+ex.toString());//~@@@@R~
//                return;                                          //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Save_Position").equals(o)) // save the position//~@@@@R~
//        {   // File dialog handling                              //~@@@@R~
//            FileDialog fd=new FileDialog(this,Global.resourceString("Save Position"),//~@@@@R~
//                FileDialog.SAVE);                                //~@@@@R~
//            if (!Dir.equals("")) fd.setDirectory(Dir);           //~@@@@R~
//            String s=((Node)B.firstnode()).getaction("GN");      //~@@@@R~
//            if (s!=null && !s.equals(""))                        //~@@@@R~
//                fd.setFile(s+"."+Global.getParameter("extension",//~@@@@R~
//                Global.getParameter("xml",false)?"xml":"sgf"));  //~@@@@R~
//            else                                                 //~@@@@R~
//                fd.setFile("*."+Global.getParameter("extension", //~@@@@R~
//                Global.getParameter("xml",false)?"xml":"sgf"));  //~@@@@R~
//            fd.setFilenameFilter(this);                          //~@@@@R~
//            center(fd);                                          //~@@@@R~
//            fd.show();                                           //~@@@@R~
//            String fn=fd.getFile();                              //~@@@@R~
//            if (fn==null) return;                                //~@@@@R~
//            Dir=fd.getDirectory();                               //~@@@@R~
//            try // print out using the board class               //~@@@@R~
//            {   PrintWriter fo;                                  //~@@@@R~
//                if (Global.getParameter("xml",false))            //~@@@@R~
//                {   if (Global.isApplet())                       //~@@@@R~
//                    {   fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                            new FileOutputStream(fd.getDirectory()+fn),"UTF8"));//~@@@@R~
//                        B.saveXML(fo,"utf-8");                   //~@@@@R~
//                    }                                            //~@@@@R~
//                    else                                         //~@@@@R~
//                    {   String Encoding=Global.getParameter("encoding",//~@@@@R~
//                            System.getProperty("file.encoding")).toUpperCase();//~@@@@R~
//                        if (Encoding.equals(""))                 //~@@@@R~
//                        {   fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                                new FileOutputStream(fd.getDirectory()+fn),//~@@@@R~
//                                "UTF8"));                        //~@@@@R~
//                            B.saveXMLPos(fo,"utf-8");            //~@@@@R~
//                        }                                        //~@@@@R~
//                        else                                     //~@@@@R~
//                        {   String XMLEncoding="";               //~@@@@R~
//                            if (Encoding.equals("CP1252") ||     //~@@@@R~
//                                Encoding.equals("ISO8859_1"))    //~@@@@R~
//                            {   Encoding="ISO8859_1";            //~@@@@R~
//                                XMLEncoding="iso-8859-1";        //~@@@@R~
//                            }                                    //~@@@@R~
//                            else                                 //~@@@@R~
//                            {   Encoding="UTF8";                 //~@@@@R~
//                                XMLEncoding="utf-8";             //~@@@@R~
//                            }                                    //~@@@@R~
//                            FileOutputStream fos=                //~@@@@R~
//                                new FileOutputStream(fd.getDirectory()+fn);//~@@@@R~
//                            try                                  //~@@@@R~
//                            {   fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                                    fos,Encoding));              //~@@@@R~
//                            }                                    //~@@@@R~
//                            catch (Exception e)                  //~@@@@R~
//                            {   Encoding="UTF8";                 //~@@@@R~
//                                XMLEncoding="utf-8";             //~@@@@R~
//                                fo=new PrintWriter(new OutputStreamWriter(//~@@@@R~
//                                    fos,Encoding));              //~@@@@R~
//                            }                                    //~@@@@R~
//                            B.saveXMLPos(fo,XMLEncoding);        //~@@@@R~
//                        }                                        //~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   if (Global.isApplet())                       //~@@@@R~
//                    fo=                                          //~@@@@R~
//                        new PrintWriter(new OutputStreamWriter(  //~@@@@R~
//                        new FileOutputStream(fd.getDirectory()+fn),//~@@@@R~
//                        Global.getParameter("encoding","ASCII")));//~@@@@R~
//                    else                                         //~@@@@R~
//                        fo=                                      //~@@@@R~
//                        new PrintWriter(new OutputStreamWriter(  //~@@@@R~
//                        new FileOutputStream(fd.getDirectory()+fn),//~@@@@R~
//                        Global.getParameter("encoding",          //~@@@@R~
//                        System.getProperty("file.encoding"))));  //~@@@@R~
//                    B.savePos(fo);                               //~@@@@R~
//                }                                                //~@@@@R~
//                fo.close();                                      //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (IOException ex)                               //~@@@@R~
//            {   new Message(this,Global.resourceString("Write_error_")+"\n"+ex.toString());//~@@@@R~
//                return;                                          //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Save_Bitmap").equals(o)) // save the game//~@@@@R~
//        {   // File dialog handling                              //~@@@@R~
//            FileDialog fd=new FileDialog(this,Global.resourceString("Save_Bitmap"),FileDialog.SAVE);//~@@@@R~
//            if (!Dir.equals("")) fd.setDirectory(Dir);           //~@@@@R~
//            String s=((Node)B.firstnode()).getaction("GN");      //~@@@@R~
//            if (s!=null && !s.equals(""))                        //~@@@@R~
//                fd.setFile(s+"."+Global.getParameter("extension","bmp"));//~@@@@R~
//            else                                                 //~@@@@R~
//                fd.setFile("*."+Global.getParameter("extension","bmp"));//~@@@@R~
//            fd.setFilenameFilter(this);                          //~@@@@R~
//            center(fd);                                          //~@@@@R~
//            fd.show();                                           //~@@@@R~
//            String fn=fd.getFile();                              //~@@@@R~
//            if (fn==null) return;                                //~@@@@R~
//            Dir=fd.getDirectory();                               //~@@@@R~
//            try // print out using the board class               //~@@@@R~
//            {   BMPFile F=new BMPFile();                         //~@@@@R~
//                Dimension d=B.getBoardImageSize();               //~@@@@R~
//                F.saveBitmap(fd.getDirectory()+fn,B.getBoardImage(),d.width,d.height);//~@@@@R~
//            }                                                    //~@@@@R~
//            catch (Exception ex)                                 //~@@@@R~
//            {   new Message(this,Global.resourceString("Write_error_")+"\n"+ex.toString());//~@@@@R~
//                return;                                          //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Load").equals(o)) // load a game//~@@@@R~
//        {   // File dialog handling                              //~@@@@R~
//            FileDialog fd=new FileDialog(this,Global.resourceString("Load_Game"),FileDialog.LOAD);//~1324R~//~@@@@R~
//            if (!Dir.equals("")) fd.setDirectory(Dir);           //~@@@@R~
//            fd.setFilenameFilter(this);                          //~@@@@R~
//            fd.setFile("*."+Global.getParameter("extension",     //~@@@@R~
//                Global.getParameter("xml",false)?"xml":"sgf"));  //~@@@@R~
//            center(fd);                                          //~@@@@R~
//            fd.show();                                           //~@@@@R~
//            String fn=fd.getFile();                              //~@@@@R~
//            if (fn==null) return;                                //~@@@@R~
//            Dir=fd.getDirectory();                               //~@@@@R~
//            try // print out using the board class               //~@@@@R~
//            {   if (Global.getParameter("xml",false))            //~@@@@R~
//                {   InputStream in=                              //~@@@@R~
//                        new FileInputStream(fd.getDirectory()+fn);//~@@@@R~
//                    try                                          //~@@@@R~
//                    {   B.loadXml(new XmlReader(in));            //~@@@@R~
//                    }                                            //~@@@@R~
//                    catch (XmlReaderException e)                 //~@@@@R~
//                    {   new Message(this,"Error in file!\n"+e.getText());//~@@@@R~
//                    }                                            //~@@@@R~
//                    in.close();                                  //~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   BufferedReader fi;                           //~@@@@R~
//                    if (Global.isApplet())                       //~@@@@R~
//                        fi=                                      //~@@@@R~
//                        new BufferedReader(                      //~@@@@R~
//                        new InputStreamReader(                   //~@@@@R~
//                        new FileInputStream(fd.getDirectory()+fn),//~@@@@R~
//                        Global.getParameter("encoding","")));    //~@@@@R~
//                    else                                         //~@@@@R~
//                        fi=                                      //~@@@@R~
//                        new BufferedReader(                      //~@@@@R~
//                        new InputStreamReader(                   //~@@@@R~
//                        new FileInputStream(fd.getDirectory()+fn),//~@@@@R~
//                        Global.getParameter("encoding",          //~@@@@R~
//                            System.getProperty("file.encoding"))));//~@@@@R~
//                    try                                          //~@@@@R~
//                    {   B.load(fi);                              //~@@@@R~
//                    }                                            //~@@@@R~
//                    catch (IOException e)                        //~@@@@R~
//                    {   new Message(this,"Error in file!");      //~@@@@R~
//                    }                                            //~@@@@R~
//                    fi.close();                                  //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (IOException ex)                               //~@@@@R~
//            {   new Message(this,Global.resourceString("Read_error_")+"\n"+ex.toString());//~@@@@R~
//                return;                                          //~@@@@R~
//            }                                                    //~@@@@R~
//            String s=((Node)B.firstnode()).getaction("GN");      //~@@@@R~
//            if (s!=null && !s.equals("")) setTitle(s);           //~@@@@R~
//            else                                                 //~@@@@R~
//            {   ((Node)B.firstnode()).setaction("GN",FileName.purefilename(fn));//~@@@@R~
//                setTitle(FileName.purefilename(fn));             //~@@@@R~
//            }                                                    //~@@@@R~
//            if (fn.toLowerCase().indexOf("kogo")>=0)             //~@@@@R~
//                B.setVariationStyle(false,false);                //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Load_from_Clipboard").equals(o))//~@@@@R~
//        {   loadClipboard();                                     //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Copy_to_Clipboard").equals(o))//~@@@@R~
//        {   saveClipboard();                                     //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Board_Window").equals(o))//~@@@2R~
//        {   new Help("board");                                   //~@@@2R~
//        }                                                        //~@@@2R~
//        else if (Global.resourceString("Making_Moves").equals(o))//~@@@2R~
//        {   new Help("moves");                                   //~@@@2R~
//        }                                                        //~@@@2R~
//        else if (Global.resourceString("Keyboard_Shortcuts").equals(o))//~@@@2R~
//        {   new Help("keyboard");                                //~@@@2R~
//        }                                                        //~@@@2R~
//        else if (Global.resourceString("Playing_Games").equals(o))//~@@@2R~
//        {   new Help("playing");                                 //~@@@2R~
//        }                                                        //~@@@2R~
//        else if (Global.resourceString("About_Variations").equals(o))//~@@@2R~
//        {   new Help("variations");                              //~@@@2R~
//        }                                                        //~@@@2R~
//        else if (Global.resourceString("Mailing_Games").equals(o))//~@@@@R~
//        {   new Help("mail");                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Insert_Node").equals(o)) //~@@@@R~
//        {   B.insertnode();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Insert_Variation").equals(o))//~@@@@R~
//        {   B.insertvariation();                                 //~@@@@R~
//        }                                                        //~@@@@R~
		else if (Global.resourceString("Game_Information").equals(o))
		{	new EditInformation(this,B.firstnode());
		}
//        else if (Global.resourceString("Game_Copyright").equals(o))//~@@@@R~
//        {   new EditCopyright(this,B.firstnode());               //~@@@@R~
//        }                                                        //~@@@@R~
		else if (Global.resourceString("Prisoner_Count").equals(o))
		{	String s=
			Global.resourceString("Black__")+B.Pw+
				Global.resourceString("__White__")+B.Pb+"\n"+
				Global.resourceString("Komi")+" "+B.getKomi();
			new Message(this,s);
		}
//        else if (Global.resourceString("Board_Color").equals(o)) //~@@@@R~
//        {   new BoardColorEdit(this,"boardcolor",BoardColor);    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Black_Color").equals(o)) //~@@@@R~
//        {   new BoardColorEdit(this,"blackcolor",BlackColor);    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Black_Sparkle_Color").equals(o))//~@@@@R~
//        {   new BoardColorEdit(this,"blacksparklecolor",BlackSparkleColor);//~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("White_Color").equals(o)) //~@@@@R~
//        {   new BoardColorEdit(this,"whitecolor",WhiteColor);    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("White_Sparkle_Color").equals(o))//~@@@@R~
//        {   new BoardColorEdit(this,"whitesparklecolor",WhiteSparkleColor);//~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Label_Color").equals(o)) //~@@@@R~
//        {   new BoardColorEdit(this,"labelcolor",LabelColor);    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Marker_Color").equals(o))//~@@@@R~
//        {   new BoardColorEdit(this,"markercolor",MarkerColor);  //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Board_Font").equals(o))  //~@@@@R~
//        {   (new BoardGetFontSize(this,                          //~@@@@R~
//            "boardfontname",Global.getParameter("boardfontname","SansSerif"),//~@@@@R~
//                "boardfontsize",Global.getParameter("boardfontsize",10),true)).show();//~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Normal_Font").equals(o)) //~@@@@R~
//        {   (new BoardGetFontSize(this,                          //~@@@@R~
//            "sansserif",Global.getParameter("sansserif","SansSerif"),//~@@@@R~
//                "ssfontsize",Global.getParameter("ssfontsize",11),true)).show();//~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Fixed_Font").equals(o))  //~@@@@R~
//        {   (new BoardGetFontSize(this,                          //~@@@@R~
//            "monospaced",Global.getParameter("monospaced","Monospaced"),//~@@@@R~
//                "msfontsize",Global.getParameter("msfontsize",11),true)).show();//~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Last_50").equals(o))     //~@@@@R~
//        {   B.lastrange(50);                                     //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Last_100").equals(o))    //~@@@@R~
//        {   B.lastrange(100);                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Node_Name").equals(o))   //~@@@@R~
//        {   callInsert();                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Goto_Next_Name").equals(o))//~@@@@R~
//        {   B.gotonext();                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Goto_Previous_Name").equals(o))//~@@@@R~
//        {   B.gotoprevious();                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Next_Game").equals(o))   //~@@@@R~
//        {   B.gotonextmain();                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Previous_Game").equals(o))//~@@@@R~
//        {   B.gotopreviousmain();                                //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Add_Game").equals(o))    //~@@@@R~
//        {   B.addnewgame();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Remove_Game").equals(o)) //~@@@@R~
//        {   B.removegame();                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Set_Encoding").equals(o))//~@@@@R~
//        {   new GetEncoding(this);                               //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Search_Again").equals(o))//~@@@@R~
//        {   search();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Search").equals(o))      //~@@@@R~
//        {   new GetSearchString(this);                           //~@@@@R~
//        }                                                        //~@@@@R~
		else super.doAction(o);
	}

//    public void center (FileDialog d)                            //~@@@@R~
//    {   Point lo=getLocation();                                  //~@@@@R~
//        Dimension di=getSize();                                  //~@@@@R~
//        d.setLocation(lo.x+di.width/2-100,                       //~@@@@R~
//            lo.y+di.height/2-100);                               //~@@@@R~
//    }                                                            //~@@@@R~

//    public void search ()                                        //~@@@@R~
//    {   B.search(Global.getParameter("searchstring","++"));      //~@@@@R~
//    }                                                            //~@@@@R~

	public void itemAction (String o, boolean flag)
//  {	if (Global.resourceString("Save_pure_SGF").equals(o))      //~@@@@R~
	{                                                              //~@@@@I~
//        if (Global.resourceString("Save_pure_SGF").equals(o))    //~@@@@I~
//        {   Global.setParameter("puresgf",flag);                 //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Use_SGF_Comments").equals(o))//~@@@@R~
//        {   Global.setParameter("sgfcomments",flag);             //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("On").equals(o))          //~@@@@R~
//        {   Global.setParameter("coordinates",flag);             //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Upper_Left").equals(o))  //~@@@@R~
//        {   Global.setParameter("upperleftcoordinates",flag);    //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Lower_Right").equals(o)) //~@@@@R~
//        {   Global.setParameter("lowerrightcoordinates",flag);   //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Set_Black").equals(o))   //~@@@@R~
//        {   B.setblack();                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Set_White").equals(o))   //~@@@@R~
//        {   B.setwhite();                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Black_to_play").equals(o))//~@@@@R~
//        {   B.black();                                           //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("White_to_play").equals(o))//~@@@@R~
//        {   B.white();                                           //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Mark").equals(o))        //~@@@@R~
//        {   B.mark();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Text").equals(o))        //~@@@@R~
//        {   B.textmark(Text);                                    //~@@@@R~
//            if (TMQ==null) TMQ=new TextMarkQuestion(this,Text);  //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Square").equals(o))      //~@@@@R~
//        {   B.specialmark(Field.SQUARE);                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Triangle").equals(o))    //~@@@@R~
//        {   B.specialmark(Field.TRIANGLE);                       //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Cross").equals(o))       //~@@@@R~
//        {   B.specialmark(Field.CROSS);                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Circle").equals(o))      //~@@@@R~
//        {   B.specialmark(Field.CIRCLE);                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Letter").equals(o))      //~@@@@R~
//        {   B.letter();                                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Delete").equals(o))      //~@@@@R~
//        {   B.deletestones();                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("True_Color_Board").equals(o))//~@@@@R~
//        {   Global.setParameter("beauty",flag);                  //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("True_Color_Stones").equals(o))//~@@@@R~
//        {   Global.setParameter("beautystones",flag);            //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Anti_alias_Stones").equals(o))//~@@@@R~
//        {   Global.setParameter("alias",flag);                   //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Shadows").equals(o))     //~@@@@R~
//        {   Global.setParameter("shadows",flag);                 //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Smaller_Stones").equals(o))//~@@@@R~
//        {   Global.setParameter("smallerstones",flag);           //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Black_Only").equals(o))  //~@@@@R~
//        {   Global.setParameter("blackonly",flag);               //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Use_B_W_marks").equals(o))//~@@@@R~
//        {   BWColor=flag;                                        //~@@@@R~
//            Global.setParameter("bwcolor",BWColor);              //~@@@@R~
//            updateall();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Last_Number").equals(o)) //~@@@@R~
//        {   LastNumber=flag;                                     //~@@@@R~
//            Global.setParameter("lastnumber",LastNumber);        //~@@@@R~
//            B.updateall();                                       //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Show_Target").equals(o)) //~@@@@R~
//        {   ShowTarget=flag;                                     //~@@@@R~
//            Global.setParameter("showtarget",ShowTarget);        //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Show_Buttons").equals(o))//~@@@@R~
//        {   if (flag) add("South",ButtonP);                      //~@@@@R~
//            else remove(ButtonP);                                //~@@@@R~
//            if (this instanceof ConnectedGoFrame)                //~@@@@R~
//                Global.setParameter("showbuttonsconnected",flag);//~@@@@R~
//            else                                                 //~@@@@R~
//                Global.setParameter("showbuttons",flag);         //~@@@@R~
//            setVisible(true);                                    //~@@@@R~
//            validate();                                          //~@@@@R~
//            doLayout();                                          //~@@@@R~
//            setVisible(true);                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Use_XML").equals(o))     //~@@@@R~
//        {   UseXML.setState(true); UseSGF.setState(false);       //~@@@@R~
//            Global.setParameter("xml",true);                     //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Use_SGF").equals(o))     //~@@@@R~
//        {   UseSGF.setState(true); UseXML.setState(false);       //~@@@@R~
//            Global.setParameter("xml",false);                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Hide").equals(o))        //~@@@@R~
//        {   B.setVariationStyle(flag,VCurrent.getState());       //~@@@@R~
//            Global.setParameter("vhide",flag);                   //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("To_Current").equals(o))  //~@@@@R~
//        {   VCurrent.setState(true); VChild.setState(false);     //~@@@@R~
//            B.setVariationStyle(VHide.getState(),true);          //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("To_Child").equals(o))    //~@@@@R~
//        {   VCurrent.setState(false); VChild.setState(true);     //~@@@@R~
//            B.setVariationStyle(VHide.getState(),false);         //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (Global.resourceString("Continue_Numbers").equals(o))//~@@@@R~
//        {   Global.setParameter("variationnumbers",flag);        //~@@@@R~
//        }                                                        //~@@@@R~
	}

	// This can be used to set a board position
	// The board is updated directly, if it is at the
	// last move.
	/** set a black move at i,j */
	public void black (int i, int j)
	{	B.black(i,j);
	}
	/** set a white move at i,j */
	public void white (int i, int j)
	{	B.white(i,j);
	}
	/** set a black stone at i,j */
	public void setblack (int i, int j)
	{	B.setblack(i,j);
	}
	/** set a black stone at i,j */
	public void setwhite (int i, int j)
	{	B.setwhite(i,j);
	}
	/** mark the field at i,j as territory */
	public void territory (int i, int j)
	{	B.territory(i,j);
	}
	/** Next to move */
	public void color (int c)
	{	if (c==-1) B.white();
		else B.black();
	}

	/**
	This called from the board to set the menu checks
	according to the current state.
	@param i the number of the state the Board is in.
	*/
	public void setState (int i)
	{                                                              //~1116R~
    	if (Dump.Y) Dump.println("GoFrame:setState init i="+i);      //~1506R~//~@@@2R~
//        Black.setState(false);                                     //~1116I~//~@@@@R~
//        White.setState(false);                                   //~@@@@R~
//        SetBlack.setState(false);                                //~@@@@R~
//        SetWhite.setState(false);                                //~@@@@R~
//        Mark.setState(false);                                    //~@@@@R~
//        Letter.setState(false);                                  //~@@@@R~
//        Hide.setState(false);                                    //~@@@@R~
//        Circle.setState(false);                                  //~@@@@R~
//        Cross.setState(false);                                   //~@@@@R~
//        Triangle.setState(false);                                //~@@@@R~
//        Square.setState(false);                                  //~@@@@R~
//        TextMark.setState(false);                                //~@@@@R~
//        switch (i)                                               //~@@@@R~
//        {   case 1 : Black.setState(true); break;                //~@@@@R~
//            case 2 : White.setState(true); break;                //~@@@@R~
//            case 3 : SetBlack.setState(true); break;             //~@@@@R~
//            case 4 : SetWhite.setState(true); break;             //~@@@@R~
//            case 5 : Mark.setState(true); break;                 //~@@@@R~
//            case 6 : Letter.setState(true); break;               //~@@@@R~
//            case 7 : Hide.setState(true); break;                 //~@@@@R~
//            case 10 : TextMark.setState(true); break;            //~@@@@R~
//        }                                                        //~@@@@R~
//        switch (i)                                               //~@@@@R~
//        {   case 1 : IB.setState("black",true); break;           //~@@@@R~
//            case 2 : IB.setState("white",true); break;           //~@@@@R~
//            case 3 : IB.setState("setblack",true); break;        //~@@@@R~
//            case 4 : IB.setState("setwhite",true); break;        //~@@@@R~
//            case 5 : IB.setState("mark",true); break;            //~@@@@R~
//            case 6 : IB.setState("letter",true); break;          //~@@@@R~
//            case 7 : IB.setState("delete",true); break;          //~@@@@R~
//            case 10 : IB.setState("text",true); break;           //~@@@@R~
//        }                                                        //~@@@@R~
	}

	/**
	Called from board to check the proper menu for markers.
	@param i the number of the marker type.
	*/
	public void setMarkState (int i)
	{                                                              //~1116R~
    	if (Dump.Y) Dump.println("GoFrame:setmarkState i="+i);       //~1506R~//~@@@2R~
		setState(0);                                               //~1116I~
//        switch (i)                                               //~@@@@R~
//        {   case Field.SQUARE : Square.setState(true); break;    //~@@@@R~
//            case Field.TRIANGLE : Triangle.setState(true); break;//~@@@@R~
//            case Field.CROSS : Cross.setState(true); break;      //~@@@@R~
//            case Field.CIRCLE : Circle.setState(true); break;    //~@@@@R~
//        }                                                        //~@@@@R~
//        switch (i)                                               //~@@@@R~
//        {   case Field.SQUARE : IB.setState("square",true); break;//~@@@@R~
//            case Field.TRIANGLE : IB.setState("triangle",true); break;//~@@@@R~
//            case Field.CROSS : IB.setState("mark",true); break;  //~@@@@R~
//            case Field.CIRCLE : IB.setState("circle",true); break;//~@@@@R~
//        }                                                        //~@@@@R~
	}

	/**
	Called from board to enable and disable navigation
	buttons.
	@param i the number of the button
	@param f enable/disable the button
	*/
	public void setState (int i, boolean f)
	{                                                              //~1116R~
    	if (Dump.Y) Dump.println("GoFrame:setState i="+i+"="+f);     //~1506R~//~@@@2R~
//        switch (i)                                                 //~1116I~//~@@@@R~
//        {   case 1 : IB.setEnabled("variationback",f); break;    //~@@@@R~
//            case 2 : IB.setEnabled("variationforward",f); break; //~@@@@R~
//            case 3 : IB.setEnabled("variationstart",f); break;   //~@@@@R~
//            case 4 : IB.setEnabled("main",f); break;             //~@@@@R~
//            case 5 :                                             //~@@@@R~
//                IB.setEnabled("fastforward",f);                  //~@@@@R~
//                IB.setEnabled("forward",f);                      //~@@@@R~
//                IB.setEnabled("allforward",f);                   //~@@@@R~
//                break;                                           //~@@@@R~
//            case 6 :                                             //~@@@@R~
//                IB.setEnabled("fastback",f);                     //~@@@@R~
//                IB.setEnabled("back",f);                         //~@@@@R~
//                IB.setEnabled("allback",f);                      //~@@@@R~
//                break;                                           //~@@@@R~
//            case 7 :                                             //~@@@@R~
//                IB.setEnabled("mainend",f);                      //~@@@@R~
//                IB.setEnabled("sendforward",!f);                 //~@@@@R~
//                break;                                           //~@@@@R~
//        }                                                        //~@@@@R~
	}

	/** tests, if a name is accepted as a SGF file name */
//    public boolean accept (File dir, String name)                //~@@@2R~
//    {   if (name.endsWith("."+Global.getParameter("extension","sgf"))) return true;//~@@@2R~
//        else return false;                                       //~@@@2R~
//    }                                                            //~@@@2R~

	/**
	Called from the edit marker label dialog, when its
	text has been entered by the user.
	@param s the marker to be used by the board
	*/
	void setTextmark (String s)
	{	B.textmark(s);
	}

	/** A blocked board cannot react to the user. */
	public boolean blocked ()
	{	return false;
	}

	// The following are used from external board
	// drivers to set stones, handicap etc. (like
	// distributors for IGS commands)
	/** see, if the board is already acrive */
	public void active (boolean f) {	B.active(f); }
	/** pass the Board */
//    public void pass () {   B.pass(); }                          //~@@@@R~
//    public void setpass () {    B.setpass(); }                   //~@@@@R~
	/** Notify about pass */
	public void notepass () {	}
	/**
	Set a handicap to the Board.
	@param n number of stones
	*/
	public void handicap (int n) {	B.handicap(n); }
	/** set a move at i,j (called from Board) */
	public boolean moveset (int i, int j) {	return true; }
	/** pass (only proceeded from ConnectedGoFrame) */
	public void movepass () {	}
	/**
	Undo moves on the board (called from a distributor e.g.)
	@param n numbers of moves to undo.
	*/
//    public void undo (int n) {  B.undo(n); }                     //~@@@@R~
	/** undo (only processed from ConnectedGoFrame) */
//    public void undo () {   }                                    //~@@@@R~

	public boolean close () // try to close
//  {	if (Global.getParameter("confirmations",true))             //~@@@2R~
	{                                                              //~@@@2R~
		if ((AG.Options & AG.OPTIONS_GOFRAME_CLOSE_CONFIRM)!=0)    //~@@@2I~
		{	/*CloseQuestion CQ=*/new CloseQuestion(this);
//            if (CQ.Result)                                       //~@@@2R~
////          {   Global.notewindow(this,"board");                   //~@@@@R~//~@@@2R~
//            {                                                      //~@@@@I~//~@@@2R~
////              Global.notewindow(this,"board");                   //~@@@@R~//~@@@2R~
//                doclose();                                       //~@@@2R~
//            }                                                    //~@@@2R~
			return false;
		}
		else
//  	{	Global.notewindow(this,"board");                       //~@@@@R~
    	{                                                          //~@@@@I~
//    		Global.notewindow(this,"board");                       //~@@@@R~
			doclose();
			return false;
		}
	}
//**************************************                           //~@@@2I~
	public void doResultCloseQuestion(boolean Presult)             //~@@@2I~
	{                                                              //~@@@2I~
		if (Presult)                                               //~@@@2I~
            doclose();                                             //~@@@2I~
	}                                                              //~@@@2I~

	/**
	Called from the BoardsizeQuestion dialog.
	@param s the size of the board.
	*/
//    public void doboardsize (int s)                              //~@@@@R~
//    {   B.setsize(s);                                            //~@@@@R~
//    }                                                            //~@@@@R~

	/** called by menu action, opens a SizeQuestion dialog */
//    public void boardsize ()                                     //~@@@@R~
//    {   new SizeQuestion(this);                                  //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Determine the board size (for external purpose)
	@return the board size
	*/
//    public int getboardsize ()                                   //~@@@@R~
//    {   return B.getboardsize();                                 //~@@@@R~
//    }                                                            //~@@@@R~

	/** add a comment to the board (called from external sources) */
//    public void addComment (String s)                            //~@@@@R~
//    {   B.addcomment(s);                                         //~@@@@R~
//    }                                                            //~@@@@R~

	public void result (int b, int w) {	}

    public void yourMove (boolean notinpos) {	}

	InputStreamReader LaterLoad=null;
	boolean LaterLoadXml;
	int LaterMove=0;
	String LaterFilename="";
	boolean Activated=false;

	/**
	Note that the board must only load a file, when it is ready.
	This is to interpret a command line argument SGF filename.
	*/
//    public synchronized void load (String file, int move)        //~@@@@R~
//    {   LaterFilename=FileName.purefilename(file);               //~@@@@R~
//        LaterMove=move;                                          //~@@@@R~
//        try                                                      //~@@@@R~
//        {   if (file.endsWith(".xml"))                           //~@@@@R~
//            {   LaterLoad=new InputStreamReader(new FileInputStream(file),//~@@@@R~
//                "UTF8");                                         //~@@@@R~
//                LaterLoadXml=true;                               //~@@@@R~
//            }                                                    //~@@@@R~
//            else                                                 //~@@@@R~
//            {   LaterLoad=new InputStreamReader(new FileInputStream(file));//~@@@@R~
//                LaterLoadXml=false;                              //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception e) {   LaterLoad=null; }                //~@@@@R~
//        if (LaterLoad!=null && Activated) activate();            //~@@@@R~
//    }                                                            //~@@@@R~
//    public void load (String file)                               //~@@@@R~
//    {   load(file,0);                                            //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Note that the board must load a file, when it is ready.
	This is to interpret a command line argument SGF filename.
	*/
//    public void load (URL file)                                  //~@@@@R~
//    {   LaterFilename=file.toString();                           //~@@@@R~
//        try                                                      //~@@@@R~
//        {   if (file.toExternalForm().endsWith(".xml"))          //~@@@@R~
//            {   LaterLoad=new InputStreamReader(file.openStream(),//~@@@@R~
//                "UTF8");                                         //~@@@@R~
//                LaterLoadXml=true;                               //~@@@@R~
//            }                                                    //~@@@@R~
//            else                                                 //~@@@@R~
//            {   LaterLoad=new InputStreamReader(file.openStream());//~@@@@R~
//                LaterLoadXml=false;                              //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception e) {   LaterLoad=null; }                //~@@@@R~
//    }                                                            //~@@@@R~

	/** Actually do the loading, when the board is ready. */
//    public void doload (Reader file)                             //~@@@@R~
//    {   validate();                                              //~@@@@R~
//        try                                                      //~@@@@R~
//        {   B.load(new BufferedReader(file));                    //~@@@@R~
//            file.close();                                        //~@@@@R~
//            setGameTitle(LaterFilename);                         //~@@@@R~
//            B.gotoMove(LaterMove);                               //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception ex)                                     //~@@@@R~
//        {   rene.dialogs.Warning w=                              //~@@@@R~
//            new rene.dialogs.Warning(this,ex.toString(),"Warning",true);//~@@@@R~
//            w.center(this);                                      //~@@@@R~
//            w.setVisible(true);                                  //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

	public void setGameTitle (String filename)
	{		String s=((Node)B.firstnode()).getaction("GN");
			if (s!=null && !s.equals("")) 
			{	setTitle(s);
			}
			else
			{	((Node)B.firstnode()).addaction(new Action("GN",filename));
				setTitle(filename);
			}
	}

	/** Actually do the loading, when the board is ready. */
//    public void doloadXml (Reader file)                          //~@@@@R~
//    {   validate();                                              //~@@@@R~
//        try                                                      //~@@@@R~
//        {   XmlReader xml=new XmlReader(new BufferedReader(file));//~@@@@R~
//            B.loadXml(xml);                                      //~@@@@R~
//            file.close();                                        //~@@@@R~
//            setGameTitle(LaterFilename);                         //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception ex)                                     //~@@@@R~
//        {   rene.dialogs.Warning w=                              //~@@@@R~
//            new rene.dialogs.Warning(this,ex.toString(),"Warning",true);//~@@@@R~
//            w.center(this);                                      //~@@@@R~
//            w.setVisible(true);                                  //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~
//    public void loadClipboard ()                                 //~@@@@R~
//    {   try                                                      //~@@@@R~
//        {   Clipboard clip=getToolkit().getSystemClipboard();    //~@@@@R~
//            Transferable t=clip.getContents(this);               //~@@@@R~
//            String S=(String)t.getTransferData(DataFlavor.stringFlavor);//~@@@@R~
//            LaterFilename="Clipboard Content";                   //~@@@@R~
//            if (XmlReader.testXml(S)) doloadXml(new StringReader(S));//~@@@@R~
//            else doload(new StringReader(S));                    //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception e)                                        //~1401R~//~@@@@R~
//        {                                                          //~1401I~//~@@@@R~
//            Dump.println(e,"loadClipboard ");                      //~1401I~//~@@@@R~
//        }                                                          //~1401I~//~@@@@R~
//    }                                                            //~@@@@R~

//    public void saveClipboard ()                                 //~@@@@R~
//    {   try                                                      //~@@@@R~
//        {   ByteArrayOutputStream ba=new ByteArrayOutputStream(50000);//~@@@@R~
//            try                                                  //~@@@@R~
//            {   if (Global.getParameter("xml",false))            //~@@@@R~
//                {   PrintWriter po=new PrintWriter(              //~@@@@R~
//                    new OutputStreamWriter(ba,"UTF8"),true);     //~@@@@R~
//                    B.saveXML(po,"utf-8");                       //~@@@@R~
//                    po.close();                                  //~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   PrintWriter po=new PrintWriter(ba,true);     //~@@@@R~
//                    B.save(po);                                  //~@@@@R~
//                    po.close();                                  //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            catch (Exception ex)                                   //~1401R~//~@@@@R~
//            {                                                      //~1401I~//~@@@@R~
//                Dump.println(ex,"saveClipboard PrintWriter");       //~1401I~//~@@@@R~
//            }                                                      //~1401I~//~@@@@R~
//            String S=ba.toString();                              //~@@@@R~
//            Clipboard clip=getToolkit().getSystemClipboard();    //~@@@@R~
//            StringSelection sel=new StringSelection(S);          //~@@@@R~
//            clip.setContents(sel,this);                          //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception e)                                        //~1401R~//~@@@@R~
//        {                                                          //~1401I~//~@@@@R~
//            Dump.println(e,"saveClipboard ");                      //~1401I~//~@@@@R~
//        }                                                          //~1401I~//~@@@@R~
//    }                                                            //~@@@@R~
//    public void lostOwnership (Clipboard b, Transferable s) {   }//~@@@@R~

	public synchronized void activate ()
	{	Activated=true;
//        if (LaterLoad!=null)                                     //~@@@@R~
//        {   if (LaterLoadXml) doloadXml(LaterLoad);              //~@@@@R~
//            else doload(LaterLoad);                              //~@@@@R~
//        }                                                        //~@@@@R~
//        LaterLoad=null;                                          //~@@@@R~
	}

	/** Repaint the board, when color or font changes. */
	public void updateall ()
	{	setcolors();
		B.updateboard();
	}

	/**
	Opens a dialog to ask for deleting of game trees. This is
	called from the Board, if the node has grandchildren.
	*/
//    public boolean askUndo ()                                    //~@@@@R~
//    {   return new AskUndoQuestion(this).Result;                 //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Called from the board, when a node is to be inserted.
	Opens a dialog asking for permission.
	*/
//    public boolean askInsert ()                                  //~@@@@R~
//    {   return new AskInsertQuestion(this).Result;               //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Sets the name of the Board (called from a Distributor)
	@see jagoclient.igs.Distributor
	*/
	public void setname (String s)
	{	B.setname(s);
	}

	/**
	Sets the name of the current board name in a dialog
	(called from the menu)
	*/
//    public void callInsert ()                                    //~@@@@R~
//    {   new NodeNameEdit(this,B.getname());                      //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Remove a group at i,j in the board.
	*/
//    public void remove (int i, int j)                            //~@@@@R~
//    {   B.remove(i,j);                                           //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Called from the board to advance the text mark.
	*/
//    public void advanceTextmark ()                               //~@@@@R~
//    {   if (TMQ!=null) TMQ.advance();                            //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Called from the board to set the comment of a board in the Comment
	text area.
	*/
//    public void setComment (String s)                            //~@@@@R~
//    {   Comment.setText(s);                                      //~@@@@R~
//        Comment.append("");                                      //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Called from the Board to read the comment text area.
	*/
//    public String getComment ()                                  //~@@@@R~
//    {   return Comment.getText();                                //~@@@@R~
//    }                                                            //~@@@@R~

	/**
	Called from outside to append something to the comment
	text area (e.g. from a Distributor).
	*/
    public void appendComment (String s)                         //~@@@@R~//~@@@2R~
    {   Comment.append(s);                                       //~@@@@R~//~@@@2R~
    }                                                            //~@@@@R~//~@@@2R~

	/**
	Called from the board to set the Label below the board.
	*/
	public void setLabel (String s)
//  {	L.setText(s);                                              //~@@@2R~
	{                                                              //~@@@2I~
      if (L!=null)                                                 //~@@@2I~
		L.setText(s);                                              //~@@@2I~
//        if (Navigation!=null) Navigation.repaint();              //~@@@@R~
	}

	/**
	Called from the board to set the label for the cursor position.
	*/
	public void setLabelM (String s)
//  {	Lm.setText(s);                                             //~@@@2R~
    {                                                              //~@@@2I~
      if (Lm!=null)                                                //~@@@2I~
    	Lm.setText(s);                                             //~@@@2I~
	}

	public boolean boardShowing () {	return Show; }
	public boolean lastNumber () {	return LastNumber; }
	public boolean showTarget () {	return ShowTarget; }
	public Color blackColor () {	return BlackColor; }
	public Color whiteColor () {	return WhiteColor; }
	public Color whiteSparkleColor () {	return WhiteSparkleColor; }
	public Color blackSparkleColor () {	return BlackSparkleColor; }
	public Color markerColor (int color)
	{	switch (color)
		{	case 1 : return MarkerColor.brighter().brighter();
			case -1 : return MarkerColor.darker().darker();
			default : return MarkerColor;
		} 
	}
	public Color boardColor () { return BoardColor; }
	public Color labelColor (int color)
	{	switch (color)
		{	case 1 : return LabelColor.brighter().brighter();
			case -1 : return LabelColor.darker().darker();
			default : return LabelColor.brighter();
		} 
	}
	public boolean bwColor () {	return BWColor; }

	/**
	Process the insert key, which set the node name by opening
	the correspinding dialog.
	*/
	public void keyPressed (KeyEvent e)
//  {	if (e.isActionKey())                                       //~@@@@R~
	{                                                              //~@@@@I~
//        if (e.isActionKey())                                     //~@@@@I~
//        {   switch (e.getKeyCode())                              //~@@@@R~
//            {   case KeyEvent.VK_INSERT : callInsert(); break;   //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else                                                     //~@@@@R~
//        {   switch (e.getKeyChar())                              //~@@@@R~
//            {   case 'f' :                                       //~@@@@R~
//                case 'F' :                                       //~@@@@R~
//                    B.search(Global.getParameter("searchstring","++")); break;//~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
	}
	public void keyReleased (KeyEvent e) {	}
	public void keyTyped (KeyEvent e) {	}

	// interface routines for the BoardInterface

	public Color getColor (String S, int r, int g, int b)
	{	return Global.getColor(S,r,g,b);
	}

	public String resourceString (String S)
	{	return Global.resourceString(S);
	}

	public String version ()
	{	return "Version "+resourceString("Version");
	}

	public boolean getParameter (String s, boolean f)
	{	return Global.getParameter(s,f);
	}

	public Font boardFont ()
	{	return Global.BoardFont;
	}
	public Font boardFont (int Psize)                              //~@@@2I~
	{                                                              //~@@@2I~
		return Global.createfont("Bold","SansSerif",Psize);        //~@@@2R~
	}                                                              //~@@@2I~

	public Frame frame ()
	{	return Global.frame();
	}

	public boolean blackOnly ()
	{	if (BlackOnly!=null)
		return BlackOnly.getState();
		return false;
	}
	
	public Color backgroundColor ()
	{	return Global.gray;
	}
//************************************************************     //~@@@@I~
//*on UiThread                                                     //~@@@@I~
//*   if used "implements UiThreadI" all other function was intercepted//~@@@@I~
//************************************************************     //~@@@@I~
	public void changeBoard(boolean Pchangeboard)                                     //~@@@@I~//~@@@2R~
	{                                                              //~@@@@I~
    	if (Pchangeboard)                                          //~@@@2I~
            if (AG.propBoardSize==AG.BOARDSIZE_SHOGI)                  //~@@@@R~//~@@@2R~
            {                                                      //~@@@2R~
                AG.propBoardSize=AG.BOARDSIZE_CHESS;                   //~@@@@R~//~@@@2R~
            }                                                      //~@@@2R~
            else                                                       //~@@@@I~//~@@@2R~
            {                                                      //~@@@2R~
                AG.propBoardSize=AG.BOARDSIZE_SHOGI;                   //~@@@@R~//~@@@2R~
            }                                                      //~@@@2R~
//        if (AG.isMainThread())                                     //~1214M~//~@@@@I~//~@@@2R~
//            changeBoardUi();                                       //~@@@@R~//~@@@2R~
//        else                                                       //~@@@@I~//~@@@2R~
//        {                                                          //~@@@@I~//~@@@2R~
//            AG.activity.runOnUiThread(                             //~@@@@I~//~@@@2R~
//                new Runnable()                                         //~1214I~//~@@@@I~//~@@@2R~
//                {                                                      //~1214I~//~@@@@I~//~@@@2R~
//                    @Override                                          //~1214I~//~@@@@I~//~@@@2R~
//                    public void run()                                  //~1214I~//~@@@@I~//~@@@2R~
//                    {                                                  //~1214I~//~@@@@I~//~@@@2R~
//                        try                                        //~@@@@I~//~@@@2R~
//                        {                                          //~@@@@I~//~@@@2R~
//                            changeBoardUi();                       //~@@@@I~//~@@@2R~
//                        }                                          //~@@@@I~//~@@@2R~
//                        catch (Exception e)             //~1311I~  //~@@@@I~//~@@@2R~
//                        {                                          //~1311I~//~@@@@I~//~@@@2R~
//                            Dump.println(e,"changeBoardUi");//~1311I~//~@@@@I~//~@@@2R~
//                        }                                          //~1311I~//~@@@@I~//~@@@2R~
//                    }                                                  //~1214I~//~@@@@I~//~@@@2R~
//                }                                                      //~1214I~//~@@@@I~//~@@@2R~
//                                      );                               //~1214I~//~@@@@I~//~@@@2R~
//        }                                                          //~@@@@I~//~@@@2R~
        URunnable.setRunFuncDirect(this,null/*objparm*/,0/*intparm*/);            //~@@@2I~
	}                                                              //~@@@@I~
//************************************************************     //~@@@2I~
	@Override //URunnableI                                         //~@@@2I~
	public void runFunc(Object Pobj,int Pint)                                          //~@@@2I~
	{                                                              //~@@@2I~
		changeBoardUi();                                           //~@@@2I~
	}                                                              //~@@@2I~
//************************************************************     //~@@@@I~
	public void changeBoardUi()                                    //~@@@@R~
	{                                                              //~@@@@I~
        if (Dump.Y) Dump.println("changeBoardUi entry");           //~@@@2R~
    	B.stopThread();   //stop BoardSync thread and recycle Board bitmap//~@@@2R~
        Utils.sleep(100);//give timing of recycle Bitmap at stopThread()//+1Ad9R~
        if (Dump.Y) Dump.println("changeBoardUi new Board");       //~@@@2I~
        B=new Board(AG.propBoardSize,this);  //boardsize was set at MainFrame//~@@@@R~
        if (Dump.Y) Dump.println("changeBoardUi new Board return");//~@@@2I~
        int ic=AG.isChessBoard()?-1/*white*/:1/*Black*/;           //~@@@2I~
        B.putInitialPiece(ic,0/*bishop*/,0/*knight*/,-1/*gameover*/,-1/*gameover2*/,-1/*ga,eoptions*/);//~@@@@I~//~@@@2R~
        if (Dump.Y) Dump.println("changeBoardUi return");          //~@@@2I~
	}                                                              //~@@@@I~
//************************************************************     //~@@@@I~
	public void changePiece()                                     //~@@@@I~
	{                                                              //~@@@@I~
    	int piecetype;                                             //~@@@@I~
    	if (Board.pieceType==Board.PIECETYPE_SHOGI)                   //~@@@@I~
        	piecetype=Board.PIECETYPE_CHESS;                       //~@@@@R~
        else                                                       //~@@@@I~
            piecetype=Board.PIECETYPE_SHOGI;                       //~@@@@R~
        B.changePiece(piecetype);                                  //~@@@@I~
	}                                                              //~@@@@I~
//************************************************************     //~@@@@I~
	public void repaint(boolean Pinital)                           //~@@@@I~
	{                                                              //~@@@@I~
    	B.repaint();                                               //~@@@@I~
	}                                                              //~@@@@I~
//************************************************************     //~@@@2I~
	public String pieceName(int Ppiece)                            //~@@@2I~
	{                                                              //~@@@2I~
    	int id;                                                 //~@@@2I~
    	if (Board.pieceType==Board.PIECETYPE_CHESS)                //~@@@2I~
        {                                                          //~@@@2I~
        	if (Ppiece==Field.BISHOP)                              //~@@@2I~
            	id=R.string.NameBishop;                            //~@@@2I~
            else                                                   //~@@@2I~
        	if (Ppiece==Field.KNIGHT)                              //~@@@2I~
            	id=R.string.NameKnight;                            //~@@@2I~
            else                                                   //~@@@2I~
            	id=R.string.NamePawn;                              //~@@@2I~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
        {                                                          //~@@@2I~
        	if (Ppiece==Field.BISHOP)                              //~@@@2I~
            	id=R.string.NameKaku;                              //~@@@2I~
            else                                                   //~@@@2I~
        	if (Ppiece==Field.KNIGHT)                              //~@@@2I~
            	id=R.string.NameKei;                               //~@@@2I~
            else                                                   //~@@@2I~
            	id=R.string.NameFu;                                //~@@@2I~
        }                                                          //~@@@2I~
        return AG.resource.getString(id);                          //~@@@2I~
	}                                                              //~@@@2I~
////************************************************************   //~@@@2R~
//    public String colorName(int Pcolor)                          //~@@@2R~
//    {                                                            //~@@@2R~
//        int id;                                                  //~@@@2R~
//        if (!AG.isChessBoard())                                  //~@@@2R~
//        {                                                        //~@@@2R~
//            if (Pcolor>0)                                        //~@@@2R~
//                id=R.string.ColorNameShogiBlack;                 //~@@@2R~
//            else                                                 //~@@@2R~
//                id=R.string.ColorNameShogiWhite;                 //~@@@2R~
//        }                                                        //~@@@2R~
//        else                                                     //~@@@2R~
//        {                                                        //~@@@2R~
//            if (Pcolor>0)                                        //~@@@2R~
//                id=R.string.ColorNameChessBlack;                 //~@@@2R~
//            else                                                 //~@@@2R~
//                id=R.string.ColorNameChessWhite;                 //~@@@2R~
//        }                                                        //~@@@2R~
//        return AG.resource.getString(id);                        //~@@@2R~
//    }                                                            //~@@@2R~
//************************************************************     //~@@@2I~
//*LocalGoFrame/PartnerGoFrame will override                       //~@@@2I~
//************************************************************     //~@@@2I~
	public void setBlock(boolean Pblock){}                         //~@@@2I~
	public void updateCapturedList(int Pcolor,int Ppiece){}              //~@@@2I~
	public void gameover(int request,int Pcolor){}                 //~@@@2I~
}
