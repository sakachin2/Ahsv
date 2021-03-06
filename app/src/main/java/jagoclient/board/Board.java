//*CID://+1Ah5R~:                                   update#=  275; //~1Ag9R~//+1Ah5R~
//*****************************************************************************//~@@@1I~
//1Ah5 2020/05/31 uncheckd type Vector.addElement                  //+1Ah5I~
//1Ag9 2016/10/11 2016/10/09 (Ajagoc)Bitmap OutOfMemory;JNI Global reference remains..java//~1Ag9I~
//                try to clear ref to bitmap from Image:fieldBitmap, Graphics:targetBitmap, android.Graphics.Canvas(<--Image:androidCanvas, Graphics:androidCanvas)//~1Ag9I~
//1Ad9 2015/07/21 additional to 1Ad6(OutOfMemory)                  //~1Ad9I~
//1Ad8 2015/07/21 (Asgts)//1A4h 2014/12/03 catch OutOfMemory(Ajagot1w)//1B0g//~1Ad8I~
//1Aab 2015/04/22 1Aa7 for local game                              //~1AabI~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//1A35 2013/04/19 show mark of last moved from position            //~1A35I~
//1031 2013/03/05 flicker by dup copy()(flick by thread timing)    //~1031I~
//1A01 2013/02/15 avoid duplicate repaint() req(screen flicker)    //~1A01I~
//101e 2013/02/08 findViewById to Container(super of Frame and Dialog)//~v101I~
//*@@@1 20110514                                                   //~@@@1I~
//*   to avoid outofmemory:not keeping static bitmap, recycle when copyed to board//~@@@1I~
//*   (ignore call from WoodPaint<--Go.java:Bitmap would be saved if same board size)//~@@@1I~
//*****************************************************************************//~@@@1I~
package jagoclient.board;                                //~1420R~

import jagoclient.Dump;
import jagoclient.partner.GameQuestion;

import java.io.BufferedReader;
import java.util.Date;
import java.util.Vector;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;                                                 //~1Ag9R~
import com.Ahsv.awt.Canvas;                                        //~@@@@R~
import com.Ahsv.awt.Color;                                         //~@@@@R~
import com.Ahsv.awt.Dimension;                                     //~@@@@R~
import com.Ahsv.awt.Font;                                          //~@@@@R~
import com.Ahsv.awt.FontMetrics;                                   //~@@@@R~
import com.Ahsv.awt.Graphics;                                      //~@@@@R~
import com.Ahsv.awt.Image;                                         //~@@@@R~
import com.Ahsv.awt.KeyEvent;                                      //~@@@@R~
import com.Ahsv.awt.KeyListener;                                   //~@@@@R~
import com.Ahsv.awt.MouseEvent;                                    //~@@@@R~
import com.Ahsv.awt.MouseListener;                                 //~@@@@R~
import com.Ahsv.awt.MouseMotionListener;                           //~@@@@R~

import rene.util.list.ListElement;

//import rene.util.xml.XmlReader;                                  //~@@@@R~
//import rene.util.xml.XmlReaderException;                         //~@@@@R~
//import rene.util.xml.XmlWriter;                                  //~@@@@R~


//******************* Board ***********************

/**
This is the main file for presenting a Go board.
<P>
Handles the complete display and storage of a Go board.
The display is kept on an offscreen image.
Stores a Go game in a node list (with variants).
Handles the display of the current node.
<P>
This class handles mouse input to set the next move.
It also has methods to move in the node tree from external sources.
<P>
A BoardInterface is used to encorporate the board
into an environment.
*/
public class Board extends Canvas                                //~1116R~//~1420R~
    implements MouseListener, MouseMotionListener, KeyListener
{                                                                  //~@@@@R~
    private static final int MAX_PIECETYPE=3;                      //~@@@@I~
    private static final int MAX_COLOR=4;                          //~@@@@I~
    public  static final int IDX_UP_BLACK=0;                       //~@@@@R~
    public  static final int IDX_UP_WHITE=1;                       //~@@@@R~
    public  static final int IDX_DOWN_BLACK=2;                     //~@@@@R~
    public  static final int IDX_DOWN_WHITE=3;                     //~@@@@R~
    private static final int[][] Sidtbl_cess={  //~@@@@I~
                                {//me                              //~@@@@M~
    								R.drawable.chess_pawn_b_up,    //~@@@@R~
    								R.drawable.chess_bishop_b_up,  //~@@@@R~
    								R.drawable.chess_knight_b_up,  //~@@@@R~
                                },                                 //~@@@@M~
                                {                                  //~@@@@M~
    								R.drawable.chess_pawn_w_up,    //~@@@@R~
    								R.drawable.chess_bishop_w_up,  //~@@@@R~
    								R.drawable.chess_knight_w_up,  //~@@@@R~
                                },                                 //~@@@@M~
                                {//partner                         //~@@@@M~
    								R.drawable.chess_pawn_b_down,  //~@@@@R~
    								R.drawable.chess_bishop_b_down,//~@@@@R~
    								R.drawable.chess_knight_b_down,//~@@@@R~
                                },                                 //~@@@@M~
                                {                                  //~@@@@M~
    								R.drawable.chess_pawn_w_down,  //~@@@@R~
    								R.drawable.chess_bishop_w_down,//~@@@@R~
    								R.drawable.chess_knight_w_down,//~@@@@R~
                                }                                  //~@@@@M~
    											};                 //~@@@@M~
    private static final int[][] Sidtbl_shogi={ //~@@@@I~
                                {//me                              //~@@@@M~
    								R.drawable.shogi_fu_b_up,      //~@@@@R~
    								R.drawable.shogi_kaku_b_up,    //~@@@@R~
    								R.drawable.shogi_keima_b_up,   //~@@@@R~
                                },                                 //~@@@@M~
                                {                                  //~@@@@M~
    								R.drawable.shogi_fu_w_up,      //~@@@@R~
    								R.drawable.shogi_kaku_w_up,    //~@@@@R~
    								R.drawable.shogi_keima_w_up,   //~@@@@R~
                                },                                 //~@@@@M~
                                {//partner                         //~@@@@M~
    								R.drawable.shogi_fu_b_down,    //~@@@@R~
    								R.drawable.shogi_kaku_b_down,  //~@@@@R~
    								R.drawable.shogi_keima_b_down, //~@@@@R~
                                },                                 //~@@@@M~
                                {                                  //~@@@@M~
    								R.drawable.shogi_fu_w_down,    //~@@@@R~
    								R.drawable.shogi_kaku_w_down,  //~@@@@R~
    								R.drawable.shogi_keima_w_down, //~@@@@R~
                                }                                  //~@@@@M~
    											};                 //~@@@@M~
    public static final int PIECETYPE_CHESS=0;                     //~@@@@R~
    public static final int PIECETYPE_SHOGI=1;                     //~@@@@R~
    public static int pieceType=PIECETYPE_SHOGI;                   //~@@@@R~
    public static final int PIECE_MARGIN=3;                        //~@@@@R~
    public Image[][] SpieceImage=new Image[MAX_COLOR][MAX_PIECETYPE];//black/white/up/down,pawn/bishop/knight//~@@@@R~
	int O,W,D,/*S,*/OT,OTU,OP; // pixel coordinates                //~@@@@R~
	public int S;                                                  //~@@@@R~
	// O=offset, W=total width, D=field width, S=board size (9,11,13,19)
	// OT=offset for coordinates to the right and below
	// OTU=offset above the board and below for coordinates
	int lasti=-1,lastj=0; // last move (used to highlight the move)
	int lastifrom=-1,lastjfrom=0; // last move from(used to highlight the move)//~1A35I~
	boolean showlast; // internal flag, if last move is to be highlighted
//  Image Empty,EmptyShadow,ActiveImage;                           //~@@@@R~
    public Image Empty,ActiveImage;                                //~@@@@R~
		// offscreen images of empty and current board
    SGFTree T; // the game tree                                    //~@@@@R~
//  Vector Trees; // the game trees (one of them is T)             //+1Ah5R~
    Vector<SGFTree> Trees; // the game trees (one of them is T)    //+1Ah5I~
	int CurrentTree; // the currently displayed tree
	public //@@@@ for Canvas to calc cursor position               //~1422I~
	Position P; // current board position
	int number; // number of the next move
	protected int moveNumber=0;    //number advance by 2, move to new pos and delete old pos//~@@@@R~
	TreeNode Pos; // the current board position in this presentation
	int State; // states: 1 is black, 2 is white, 3 is set black etc.
		// see GoFrame.setState(int)
	Font font; // Font for board letters and coordinates
	FontMetrics fontmetrics; // metrics of this font
	BoardInterface GF; // frame containing the board
	boolean Active;
	int MainColor=1;
	public int MyColor=0;
	protected int YourColor;                                       //~@@@@R~
	int sendi=-1,sendj;
		// board position which has been sended to server
	Dimension Dim; // Note size to check for resizeing at paint
    int SpecialMarker=Field.SQUARE;                                //~@@@@R~
	String TextMarker="A";
	public int Pw,Pb; // Prisoners (white and black)
	BufferedReader LaterLoad=null; // File to be loaded at repaint
	Image BlackStone,WhiteStone;
	int Range=-1; // Numbers display from this one
	boolean KeepRange=false;
	String NodeName="",LText="";
	boolean DisplayNodeName=false;
//    public boolean Removing=false;                               //~@@@@R~
	boolean Activated=false;
	public boolean Teaching=false; // enable teaching mode
	boolean VCurrent=false; // show variations to current move
	boolean VHide=false; // hide variation markers

    private boolean swUpdateBoard; //@@@@                          //~1422R~
    private boolean swEmpty;                                       //~@@@1R~
    protected int Bishop,Knight,GameOptions,Gameover,Gameover2;    //~@@@@R~
    protected boolean swSelected=false,swReselectable=false;       //~@@@@R~
    protected boolean swCursorMovedFrom=false;                     //~1A35I~
	protected int iSelected=-1,jSelected=-1;                         //~@@@@I~//~@@@2M~//~@@@@I~
    private int boardFontSize;                                         //~@@@@R~
    public boolean swChessBoard=false;                             //~@@@@I~
	private int SpieceImageSize;                                   //~@@@@I~
	private boolean swCopyWillDone;                                //~1031I~
	// ******************** initialize board *******************

	public Board (int size, BoardInterface gf)                     //~1420R~
	{                                                              //~v101R~
    	super(gf);	//Canvas                                       //~v101I~
		S=size; D=16; W=S*D;                                       //~v101I~
        if (Dump.Y) Dump.println("New Board gf="+gf.toString());     //~1506R~//~@@@@R~
		Empty=null;
//        EmptyShadow=null;                                        //~@@@@R~
    	showlast=true;                                             //~@@@@R~
		GF=gf;
		State=1;
		P=new Position(S);
		number=1;
        T=new SGFTree(new Node(number));                           //~@@@@R~
//  	Trees=new Vector();                                        //+1Ah5R~
    	Trees=new Vector<SGFTree>();                               //+1Ah5I~
		Trees.addElement(T);
		CurrentTree=0;
		Pos=T.top();
		Active=true;
		Dim=new Dimension();
		Dim.width=0; Dim.height=0;
		Pw=Pb=0;
		setfonts();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
//        VHide=GF.getParameter("vhide",false);                    //~@@@@R~
//        VCurrent=GF.getParameter("vcurrent",true);               //~@@@@R~
        if (S==AG.BOARDSIZE_CHESS)                                 //~@@@@R~
        {                                                          //~@@@@I~
        	swChessBoard=true;                                     //~@@@@I~
			MainColor=-1;	//white is 1st move for chess          //~@@@@R~
            State=2;    //setwhite                                 //~@@@@I~
            P.color(-1);    //setwhite                             //~@@@@I~
        }                                                          //~@@@@I~
	}

	void setfonts ()
	// get the font from the go frame
	{	font=GF.boardFont();
		fontmetrics=getFontMetrics(font);
        boardFontSize=font.getSize();                              //~@@@@I~
        if (Dump.Y) Dump.println("Board  font size="+boardFontSize);//~@@@@I~
	}
	void setfonts(int Psize)                                       //~@@@@I~
	{                                                              //~@@@@I~
        if (Dump.Y) Dump.println("Board  font req size="+Psize+",old="+boardFontSize);//~@@@@I~
        if (Psize==0)                                              //~@@@@I~
            setfonts();                                            //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
            if (Psize!=boardFontSize)                              //~@@@@I~
            {                                                      //~@@@@I~
				font=GF.boardFont(Psize);                          //~@@@@I~
				fontmetrics=getFontMetrics(font);                  //~@@@@I~
			    boardFontSize=font.getSize();                      //~@@@@I~
		        if (Dump.Y) Dump.println("Board  font new size="+boardFontSize);//~@@@@I~
        	}                                                      //~@@@@I~
        }                                                          //~@@@@I~
	}                                                              //~@@@@I~

	public Dimension getMinimumSize ()
	// for the layout menager of the containing component
	{	Dimension d=getSize();
		if (d.width==0) return d=Dim;
		d.width=d.height+5;
		return d;
	}

	public Dimension getPreferredSize ()
	// for the layout menager of the containing component
	{	return getMinimumSize();
	}

	// ************** paint ************************

//  public synchronized void makeimages ()                         //~1A01R~
    public synchronized void makeimages (boolean Prepaint)         //~1A01I~
	// create images and repaint, if ActiveImage is invalid.
	// uses parameters from the BoardInterface for coordinate layout.
	{	Dim=getSize();
//        boolean c=GF.getParameter("coordinates",true);           //~@@@@R~
//        boolean ulc=GF.getParameter("upperleftcoordinates",true);//~@@@@R~
//        boolean lrc=GF.getParameter("lowerrightcoordinates",false);//~@@@@R~
//@@@@  D=Dim.height/(S+1+(c?((ulc?1:0)+(lrc?1:0)):0));            //~1427R~
//@@@@  OP=D/4; O=D/2+OP; W=S*D+2*O;                               //~1427R~
//Ajust for Gravity=Center                                         //~@@@1R~
     try                                                           //~1B0gI~//~1Ad8I~
     {                                                             //~1B0gI~//~1Ad8I~
      {//Ajust for Gravity=Center @@@@                             //~1427I~
//        OP=0;                                            //@@@@ no margin//~1427M~//~@@@@R~
//        OP=D/4;                                            //@@@@ no margin//~@@@@R~
//      int coordspace=(c?((ulc?1:0)+(lrc?1:0)):0);                //~1427R~//~@@@@R~
        W=Dim.height;                                              //~1427M~
//      D=W/(S+coordspace);                                        //~1427R~//~@@@@R~
        D=(W*2)/(S*2+1);       //W/(S+1/2);1/2=(1/4)*2             //~@@@@R~
        int margin;
//      margin=(W-D*(S+coordspace))/2;                             //~1502R~//~@@@@R~
        margin=(W-D/2-D*S)/2;                                      //~@@@@R~
//      if (margin<D/2 && coordspace!=2)  //no space except coordinates//~1502R~//~@@@@R~
        if (margin<D/4)  //no space except coordinates             //~@@@@R~
        {                                                          //~1501R~
            margin=D/4;                                            //~@@@@I~
//          D=W/(S+coordspace+1);       //add "D" for margin to touch//~1502R~//~@@@@R~
            D=((W-margin)*2)/(S*2+1);       //W/(S+1/2);1/2=(1/4)*2//~@@@@I~
//          margin=(W-D*(S+coordspace))/2;                         //~1502R~//~@@@@R~
        	margin=(W-D/2-D*S)/2;                                  //~@@@@R~
        }                                                          //~1501R~
        O=margin;                                                  //~1502R~
        OP=D/4;                                            //@@@@ no margin//~@@@@I~
        if (Dump.Y) Dump.println("coord W="+W+",margin="+margin+",O="+O+",D="+D);//~1506R~//~@@@@R~
      }//@@@@                                                      //~1427I~
//        if (c)                                                   //~@@@@R~
//        {   if (lrc) OT=D;                                       //~@@@@R~
//            else OT=0;                                           //~@@@@R~
//            if (ulc) OTU=D;                                      //~@@@@R~
//            else OTU=0;                                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else OT=OTU=0;                                           //~@@@@R~
//      OT=OTU=0;                                                  //~@@@@R~
        OT=OTU=D/4;                                                //~@@@@R~
//  	W+=OTU+OT;      //@@@@                                     //~1427R~
		if (!GF.boardShowing()) return;
        if (Dump.Y) Dump.println("Board:makeimages W="+W);           //~1506R~//~@@@@R~
		// create image and paint empty board
        Image empty=Empty;	//recycle bitmap @@@@                  //~1421I~
//        Image emptyshadow=EmptyShadow;     //@@@@                  //~1421I~//~@@@@R~
		synchronized (this)
		{	Empty=createImage(W,W);
//            EmptyShadow=createImage(W,W);                        //~@@@@R~
		}
        recycle(empty);	//recycle bitmap @@@@                      //~1422R~
        empty=null;                                                //~1Ad9I~
//        recycle(emptyshadow);           //@@@@                     //~1422R~//~@@@@R~
		emptypaint();
        Image activeimage=ActiveImage;    //@@@@                   //~1421I~
		ActiveImage=createImage(W,W);
        if (Dump.Y) Dump.println("Board:makeimages recycle ActiveImage old="+(activeimage==null?"null":activeimage.toString())+",new="+ActiveImage.toString());//~@@@@R~
        if (Dump.Y) Dump.println("Activeimage androidcanvas="+ActiveImage.androidCanvas.toString());//~@@@@I~
        recycle(activeimage);          //@@@@                      //~1422R~
        activeimage=null;                                          //~1Ad9I~
		// update the emtpy board
		swEmpty=true;		//@@@@                                 //~1422R~
		updateall();
		swEmpty=false;		//@@@@                                 //~1422I~
      if (Prepaint)                                                //~1A01I~
		repaint();
        if (Dump.Y) Dump.println("Board:makeimages return");         //~1506R~//~@@@@R~
     }                                                             //~1B0gI~//~1Ad8I~
     catch(OutOfMemoryError me)                                    //~1B0gI~//~1Ad8I~
     {                                                             //~1B0gI~//~1Ad8I~
        Dump.println(me,"Board:makeimages");                       //~1B0gI~//~1Ad8I~
        AView.memoryShortage("Board:makeimage");                //~1B0gI~//~1Ad8I~
     }                                                             //~1B0gI~//~1Ad8I~
	}

	synchronized public void paint (Graphics g)
	// repaint the board (generate images at first call)
	{	Dimension d=getSize();
        if (Dump.Y) Dump.println("paint@ start Dim=("+Dim.width+","+Dim.height+"),d=("+d.width+","+d.height+")");//~1506R~//~@@@@R~
        if (Dump.Y) Dump.println("paint@ Graphics="+g.toString()+",this board="+this.toString());//~1506R~//~@@@@R~
		// test if ActiveImage is valid.
		if (Dim.width!=d.width || Dim.height!=d.height)
		{	Dim=d;
//  		makeimages();                                          //~1A01R~
    		makeimages(false/*skip repaint*/);                     //~1A01I~
			repaint();
			return;
		}
        else                                                       //~1420I~
        if (swUpdateBoard)   //@@@@                                //~1422R~
        {                    //@@@@                                //~1422R~
			doUpdateboard(); //@@@@                                //~1422R~
			return;          //@@@@                                //~1422R~
        }                    //@@@@                                //~1422R~
		else                 //@@@@                                //~1422R~
		{	if (ActiveImage!=null) g.drawImage(ActiveImage,0,0,this);
		}
		if (!Activated && GF.boardShowing())
		{	Activated=true;
			GF.activate();
		}
		g.setColor(GF.backgroundColor());
        if (Dump.Y) Dump.println("paint W="+W+",dim w="+d.width+",h="+d.height);//~1506R~//~@@@@R~
		if (d.width>W)
			g.fillRect(W,0,d.width-W,W);
		if (d.height>W)
			g.fillRect(0,W,d.width,d.height-W);
        if (Dump.Y) Dump.println("paint@ end");                      //~1506R~//~@@@@R~
	}
	public void update (Graphics g)
	{	paint(g);
	}

	// The following is for the thread, which tries to draw the
	// board on program start, to save time.

	public static WoodPaint woodpaint=null;

	// Now come the normal routine to draw a board.

	EmptyPaint EPThread=null;

	/**
	Try to paint the wooden board. If the size is correct, use
	the predraw board. Otherwise generate an EmptyPaint thread
	to paint a board.
	*/
	public synchronized boolean trywood (Graphics gr, Graphics grs, int w)
	{	if (EmptyPaint.haveImage(w,w,
				GF.getColor("boardcolor",170,120,70),OP+OP/2,OP-OP/2,D))
			// use predrawn image
		{	gr.drawImage(EmptyPaint.StaticImage,O+OTU-OP,O+OTU-OP,this);
        	recycle(EmptyPaint.StaticImage);                       //~@@@1I~
        	EmptyPaint.StaticImage=null;                              //~@@@1I~
			if (EmptyPaint.StaticShadowImage!=null && grs!=null)
            {                                                      //~@@@1I~
				grs.drawImage(EmptyPaint.StaticShadowImage,O+OTU-OP,O+OTU-OP,this);
        		recycle(EmptyPaint.StaticShadowImage);             //~@@@1I~
        		EmptyPaint.StaticShadowImage=null;                    //~@@@1I~
            }                                                      //~@@@1I~
			return true;
		}
		else
		{	if (EPThread!=null && EPThread.isAlive()) EPThread.stopit();
			EPThread=new EmptyPaint(this,w,w,
				GF.getColor("boardcolor",170,120,70),
//					true,OP+OP/2,OP-OP/2,D);                       //~@@@@R~
  					false,OP+OP/2,OP-OP/2,D);	//no shadow        //~@@@@I~
		}
		return false;
	}

	final double pixel=0.8,shadow=0.7;

//    public void stonespaint ()                                   //~@@@@R~
//    // Create the (beauty) images of the stones (black and white)//~@@@@R~
//    {   int col=GF.boardColor().getRGB();                        //~@@@@R~
//        int blue=col&0x0000FF,green=(col&0x00FF00)>>8,red=(col&0xFF0000)>>16;//~@@@@R~
//        if (Dump.Y) Dump.println("StonesPrint started");           //~1506R~//~@@@@R~
//        boolean Alias=GF.getParameter("alias",true);             //~@@@@R~
//        if (BlackStone==null || BlackStone.getWidth(this)!=D+2)  //~@@@@R~
//        {   int d=D+2;                                           //~@@@@R~
//            int pb[]=new int[d*d];                               //~@@@@R~
//            int pw[]=new int[d*d];                               //~@@@@R~
//            int i,j,g,k;                                         //~@@@@R~
//            double di,dj,d2=(double)d/2.0-5e-1,r=d2-2e-1,f=Math.sqrt(3);//~@@@@R~
//            double x,y,z,xr,xg,hh;                               //~@@@@R~
//            k=0;                                                 //~@@@@R~
//            if (GF.getParameter("smallerstones",false)) r-=1;    //~@@@@R~
//            for (i=0; i<d; i++)                                  //~@@@@R~
//                for (j=0; j<d; j++)                              //~@@@@R~
//                {   di=i-d2; dj=j-d2;                            //~@@@@R~
//                    hh=r-Math.sqrt(di*di+dj*dj);                 //~@@@@R~
//                    if (hh>=0)                                   //~@@@@R~
//                    {   z=r*r-di*di-dj*dj;                       //~@@@@R~
//                        if (z>0) z=Math.sqrt(z)*f;               //~@@@@R~
//                        else z=0;                                //~@@@@R~
//                        x=di; y=dj;                              //~@@@@R~
//                        xr=Math.sqrt(6*(x*x+y*y+z*z));           //~@@@@R~
//                        xr=(2*z-x+y)/xr;                         //~@@@@R~
//                        if (xr>0.9) xg=(xr-0.9)*10;              //~@@@@R~
//                        else xg=0;                               //~@@@@R~
//                        if (hh>pixel || !Alias)                  //~@@@@R~
//                        {   g=(int)(10+10*xr+xg*140);            //~@@@@R~
//                            pb[k]=(255<<24)|(g<<16)|(g<<8)|g;    //~@@@@R~
//                            g=(int)(200+10*xr+xg*45);            //~@@@@R~
//                            pw[k]=(255<<24)|(g<<16)|(g<<8)|g;    //~@@@@R~
//                        }                                        //~@@@@R~
//                        else                                     //~@@@@R~
//                        {   hh=(pixel-hh)/pixel;                 //~@@@@R~
//                            g=(int)(10+10*xr+xg*140);            //~@@@@R~
//                            double shade;                        //~@@@@R~
//                            if (di-dj<r/3) shade=1;              //~@@@@R~
//                            else shade=shadow;                   //~@@@@R~
//                            pb[k]=((255<<24)                     //~@@@@R~
//                                |(((int)((1-hh)*g+hh*shade*red))<<16)//~@@@@R~
//                                |(((int)((1-hh)*g+hh*shade*green))<<8)//~@@@@R~
//                                |((int)((1-hh)*g+hh*shade*blue)));//~@@@@R~
//                            g=(int)(200+10*xr+xg*45);            //~@@@@R~
//                            pw[k]=((255<<24)                     //~@@@@R~
//                                |(((int)((1-hh)*g+hh*shade*red))<<16)//~@@@@R~
//                                |(((int)((1-hh)*g+hh*shade*green))<<8)//~@@@@R~
//                                |((int)((1-hh)*g+hh*shade*blue)));//~@@@@R~
//                        }                                        //~@@@@R~
//                    }                                            //~@@@@R~
//                    else pb[k]=pw[k]=0;                          //~@@@@R~
//                    k++;                                         //~@@@@R~
//                }                                                //~@@@@R~
//            Image blackstone=BlackStone;    //@@@@                 //~1421I~//~@@@@R~
//            Image whitestone=WhiteStone;    //@@@@                 //~1421I~//~@@@@R~
//            BlackStone=createImage(                              //~@@@@R~
//                new MemoryImageSource(d,d,ColorModel.getRGBdefault(),//~@@@@R~
//                            pb,0,d));                            //~@@@@R~
//            WhiteStone=createImage(                              //~@@@@R~
//                new MemoryImageSource(d,d,ColorModel.getRGBdefault(),//~@@@@R~
//                            pw,0,d));                            //~@@@@R~
//            recycle(blackstone);          //@@@@                   //~1422R~//~@@@@R~
//            recycle(whitestone);          //@@@@                   //~1422R~//~@@@@R~
//        }                                                        //~@@@@R~
//        if (Dump.Y) Dump.println("StonesPrint End");               //~1506R~//~@@@@R~
//    }                                                            //~@@@@R~
//***********************************************************      //~@@@@I~
//*setupPieceImage                                                 //~@@@@I~
//***********************************************************      //~@@@@I~
    public void setupPieceImage()                                  //~@@@@R~
    {                                                              //~@@@@I~
    	int[][] idtbl;                                             //~@@@@I~
    //*******************                                          //~@@@@I~
    	if (D!=SpieceImageSize && SpieceImageSize!=0)              //~@@@@R~
        {                                                          //~@@@@I~
        	freePieceImage();                                      //~@@@@I~
            SpieceImageSize=D;                                     //~@@@@R~
        }                                                          //~@@@@I~
        if (pieceType==PIECETYPE_SHOGI)                            //~@@@@R~
        	idtbl=Sidtbl_shogi;                                     //~@@@@I~
        else                                                       //~@@@@I~
        	idtbl=Sidtbl_cess;                                     //~@@@@I~
    	for (int ii=0;ii<MAX_COLOR;ii++)                           //~@@@@I~
        	for (int jj=0;jj<MAX_PIECETYPE;jj++)                   //~@@@@I~
            {                                                      //~@@@@I~
		        int id=idtbl[ii][jj];                              //~@@@@I~
            	SpieceImage[ii][jj]=Image.loadPieceImage(id,D-PIECE_MARGIN/*for griid line pixcep*/,D-PIECE_MARGIN);//~@@@@R~
            }                                                      //~@@@@I~
        if (YourColor>0)	//black                                //~@@@@I~
        {                                                          //~@@@@I~
        	BlackStone=SpieceImage[IDX_UP_BLACK][0/*pawn*/];       //~@@@@R~
        	WhiteStone=SpieceImage[IDX_DOWN_WHITE][0/*pawn*/];     //~@@@@R~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
        	BlackStone=SpieceImage[IDX_DOWN_BLACK][0/*pawn*/];     //~@@@@R~
        	WhiteStone=SpieceImage[IDX_UP_WHITE][0/*pawn*/];       //~@@@@R~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
//**************************************************************** //~@@@@I~
	public void changePiece(int Ppiecetype)                       //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("changePiece start");             //~@@@@I~
        pieceType=Ppiecetype;                                 //~@@@@I~
        freePieceImage();                                          //~@@@@I~
	    setupPieceImage();                                          //~@@@@I~
//        putInitialPiece(YourColor,Bishop,Knight,-1/*gameover*/,-1/*gameover2*/,-1/*gameoptions*/);  //initialy put pieaces//~@@@@R~
        updateall();                                               //~@@@@R~
        repaint();	//invalidate,callback paint() with boardImageCopy:graphics//~@@@@I~
        if (Dump.Y) Dump.println("changePiece end");               //~@@@@I~
    }                                                              //~@@@@I~
//***********************************************************      //~@@@@I~
//*setupPieceImage                                                 //~@@@@I~
//***********************************************************      //~@@@@I~
    public void freePieceImage()                                   //~@@@@I~
    {                                                              //~@@@@I~
    //*******************                                          //~@@@@I~
    	for (int ii=0;ii<MAX_COLOR;ii++)                           //~@@@@I~
        	for (int jj=0;jj<MAX_PIECETYPE;jj++)                   //~@@@@I~
            {                                                      //~@@@@I~
            	Image image=SpieceImage[ii][jj];                   //~@@@@R~
                if (image!=null)                                   //~@@@@I~
                {                                                  //~@@@@I~
            		image.recycle();                               //~@@@@R~
                    SpieceImage[ii][jj]=null;                          //~@@@@I~
            	}                                                  //~@@@@I~
            }                                                      //~@@@@I~
    }                                                              //~@@@@I~
//***********************************************************      //~@@@@I~

	public synchronized void emptypaint ()
	// Draw an empty board onto the graphics context g.
	// Including lines, coordinates and markers.
	{	if (woodpaint!=null && woodpaint.isAlive()) woodpaint.stopit();
		synchronized (this)
//        {   if (Empty==null || EmptyShadow==null) return;        //~@@@@R~
        {                                                          //~@@@@I~
            if (Empty==null) return;                               //~@@@@I~
//  		Graphics g=Empty.getGraphics(),gs=EmptyShadow.getGraphics();//~@@@@R~
    		Graphics g=Empty.getGraphics();                        //~@@@@I~
			g.setColor(GF.backgroundColor());
    		g.fillRect(0,0,S*D+2*OP+100,S*D+2*OP+100);             //~@@@@R~
//          if (S==AG.BOARDSIZE_CHESS)                             //~@@@@R~
            if (swChessBoard)                                      //~@@@@R~
            {                                                      //~@@@@I~
			 	g.setColor(GF.boardColor());                       //~@@@@I~
                g.fillRect(O+OTU-OP,O+OTU-OP,S*D+2*OP,S*D+2*OP);   //~@@@@I~
                int xx,yy,white;                                   //~@@@@I~
                yy=O+OTU;                                          //~@@@@R~
                white=1;                                           //~@@@@R~
                for (int jj=0; jj<S; jj++)                        //~@@@@I~
                {                                                  //~@@@@I~
                    xx=O+OTU;                                      //~@@@@R~
                    for (int ii=0;ii<S;ii++)                       //~@@@@R~
                    {                                              //~@@@@I~
                    	if (white>0)                               //~@@@@I~
					 		g.setColor(AG.chessBoardWhite);        //~@@@@I~
                        else                                       //~@@@@I~
			 				g.setColor(AG.chessBoardBlack);        //~@@@@I~
			            g.fillRect(xx+1,yy+1,D-1,D-1);             //~@@@@R~
                    	xx+=D;                                     //~@@@@R~
		                white^=1;                                  //~@@@@I~
                    }                                              //~@@@@I~
                    yy+=D;                                         //~@@@@I~
                    white^=1;                                      //~@@@@I~
                }                                                  //~@@@@I~
            }                                                      //~@@@@I~
            else                                                   //~@@@@I~
//          if (!GF.getParameter("beauty",true)                    //~@@@@R~
            if (!true                                            //~@@@@I~
//    			|| !trywood(g,gs,S*D+2*OP)) // beauty board not available//~@@@@R~
     			|| !trywood(g,null,S*D+2*OP)) // beauty board not available//~@@@@I~
			{	g.setColor(GF.boardColor());
                g.fillRect(O+OTU-OP,O+OTU-OP,S*D+2*OP,S*D+2*OP);   //~@@@@R~
//                gs.setColor(GF.boardColor());                    //~@@@@R~
//                gs.fillRect(O+OTU-OP,O+OTU-OP,S*D+2*OP,S*D+2*OP);//~@@@@R~
			}
//            if (GF.getParameter("beautystones",true)) stonespaint();//~@@@@R~
            setupPieceImage();                                     //~@@@@I~//~1A01R~
			g.setColor(Color.black);
//  		gs.setColor(Color.black);                              //~@@@@R~
			int i,/*j,*/x,y1,y2;
			// Draw lines
			x=O+OTU+D/2; y1=O+OTU+D/2; y2=O+D/2+OTU+(S-1)*D;
            setGeometry(S,D,x);   //@@@@ set to Cavas for cursor positioning//~1317I~
            x-=D/2;y1-=D/2;y2+=D/2;                                //~@@@@R~
//  		for (i=0; i<S; i++)                                    //~@@@@R~
    		for (i=0; i<=S; i++)                                   //~@@@@I~
			{	g.drawLine(x,y1,x,y2);
				g.drawLine(y1,x,y2,x);
                if (Dump.Y) Dump.println("drawgrid ("+x+","+y1+")-("+x+","+y2+")");//~@@@@I~
                if (Dump.Y) Dump.println("drawgrid ("+y1+","+x+")-("+y2+","+x+")");//~@@@@I~
//                gs.drawLine(x,y1,x,y2);                          //~@@@@R~
//                gs.drawLine(y1,x,y2,x);                          //~@@@@R~
				x+=D;
			}
//            if (S==19) // handicap markers                       //~@@@@R~
//            {   for (i=0; i<3; i++)                              //~@@@@R~
//                    for (j=0; j<3; j++)                          //~@@@@R~
//                    {   hand(g,3+i*6,3+j*6);                     //~@@@@R~
//                        hand(gs,3+i*6,3+j*6);                    //~@@@@R~
//                    }                                            //~@@@@R~
//            }                                                    //~@@@@R~
//            else if (S>=11) // handicap markers                  //~@@@@R~
//            {   if (S>=15 && S%2==1)                             //~@@@@R~
//                {   int k=S/2-3;                                 //~@@@@R~
//                    for (i=0; i<3; i++)                          //~@@@@R~
//                        for (j=0; j<3; j++)                      //~@@@@R~
//                        {   hand(g,3+i*k,3+j*k);                 //~@@@@R~
//                            hand(gs,3+i*k,3+j*k);                //~@@@@R~
//                        }                                        //~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   hand(g,3,3);                                 //~@@@@R~
//                    hand(g,S-4,3);                               //~@@@@R~
//                    hand(g,3,S-4);                               //~@@@@R~
//                    hand(g,S-4,S-4);                             //~@@@@R~
//                    hand(gs,3,3);                                //~@@@@R~
//                    hand(gs,S-4,3);                              //~@@@@R~
//                    hand(gs,3,S-4);                              //~@@@@R~
//                    hand(gs,S-4,S-4);                            //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
			// coordinates below and to the right
//            if (OT>0)                                            //~@@@@R~
//            {   g.setFont(font);                                 //~@@@@R~
//                int y=O+OTU;                                     //~@@@@R~
//                int h=fontmetrics.getAscent()/2-1;               //~@@@@R~
//                for (i=0; i<S; i++)                              //~@@@@R~
//                {   String s=""+(S-i);                           //~@@@@R~
//                    int w=fontmetrics.stringWidth(s)/2;          //~@@@@R~
//                    g.drawString(s,O+OTU+S*D+D/2+OP-w,y+D/2+h);  //~@@@@R~
//                    y+=D;                                        //~@@@@R~
//                }                                                //~@@@@R~
//                x=O+OTU;                                         //~@@@@R~
//                char a[]=new char[1];                            //~@@@@R~
//                for (i=0; i<S; i++)                              //~@@@@R~
//                {   j=i;                                         //~@@@@R~
//                    if (j>7) j++;                                //~@@@@R~
//                    if (j>'Z'-'A') a[0]=(char)('a'+j-('Z'-'A')-1);//~@@@@R~
//                    else a[0]=(char)('A'+j);                     //~@@@@R~
//                    String s=new String(a);                      //~@@@@R~
//                    int w=fontmetrics.stringWidth(s)/2;          //~@@@@R~
//                    g.drawString(s,x+D/2-w,O+OTU+S*D+D/2+OP+h);  //~@@@@R~
//                    x+=D;                                        //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            // coordinates to the left and above                 //~@@@@R~
//            if (OTU>0)                                           //~@@@@R~
//            {   g.setFont(font);                                 //~@@@@R~
//                int y=O+OTU;                                     //~@@@@R~
//                int h=fontmetrics.getAscent()/2-1;               //~@@@@R~
//                for (i=0; i<S; i++)                              //~@@@@R~
//                {   String s=""+(S-i);                           //~@@@@R~
//                    int w=fontmetrics.stringWidth(s)/2;          //~@@@@R~
//                    g.drawString(s,O+D/2-OP-w,y+D/2+h);          //~@@@@R~
//                    y+=D;                                        //~@@@@R~
//                }                                                //~@@@@R~
//                x=O+OTU;                                         //~@@@@R~
//                char a[]=new char[1];                            //~@@@@R~
//                for (i=0; i<S; i++)                              //~@@@@R~
//                {   j=i;                                         //~@@@@R~
//                    if (j>7) j++;                                //~@@@@R~
//                    if (j>'Z'-'A') a[0]=(char)('a'+j-('Z'-'A')-1);//~@@@@R~
//                    else a[0]=(char)('A'+j);                     //~@@@@R~
//                    String s=new String(a);                      //~@@@@R~
//                    int w=fontmetrics.stringWidth(s)/2;          //~@@@@R~
//                    g.drawString(s,x+D/2-w,O+D/2-OP+h);          //~@@@@R~
//                    x+=D;                                        //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
			drawCoordinate(g);                                     //~@@@@I~
                                                                   //~@@@@I~
		}
	}

	public void hand (Graphics g, int i, int j)
	// help function for emptypaint (Handicap point)
	{	g.setColor(Color.black);
		int s=D/10;
		if (s<2) s=2;
		g.fillRect(O+OTU+D/2+i*D-s,O+OTU+D/2+j*D-s,2*s+1,2*s+1);
	}

	// *************** mouse events **********************

	public void mouseClicked (MouseEvent e) {}
	public void mouseDragged (MouseEvent e) {}

	boolean MouseDown=false; // mouse release only, if mouse pressed

	public synchronized void mousePressed (MouseEvent e)
	{	MouseDown=true;
	    requestFocus();
	}

	public synchronized void mouseReleased (MouseEvent e)
    {                                                              //~1031I~
        swCopyWillDone=true;                                       //~1031I~
		mouseReleasedSub(e);                             //~1031I~
        swCopyWillDone=false;                                      //~1031I~
    }                                                              //~1031I~
	private void mouseReleasedSub(MouseEvent e)                    //~1031I~
	// handle mouse input
	{	if (!MouseDown) return;
	    int x=e.getX(),y=e.getY();
		MouseDown=false;
		if (ActiveImage==null) return;
		if (!Active) return;
		getinformation();
		x-=O+OTU; y-=O+OTU;
		int i=x/D,j=y/D; // determine position
		if (x<0 || y<0 || i<0 || j<0 || i>=S || j>=S) return;
		switch (State)
		{	case 3 : // set a black stone
				if (GF.blocked() && Pos.isLastMain()) return;
				if (e.isShiftDown() && e.isControlDown()) setmousec(i,j,1);
				else setmouse(i,j,1);
				break;
			case 4 : // set a white stone
				if (GF.blocked() && Pos.isLastMain()) return;
				if (e.isShiftDown() && e.isControlDown()) setmousec(i,j,-1);
				else setmouse(i,j,-1);
				break;
//            case 5 : mark(i,j); break;                           //~@@@@R~
//            case 6 : letter(i,j); break;                         //~@@@@R~
			case 7 : // delete a stone
				if (e.isShiftDown() && e.isControlDown()) deletemousec(i,j);
				else deletemouse(i,j);
				break;
			case 8 : // remove a group
//                removemouse(i,j);                                //~@@@@R~
				break;
			case 9 : specialmark(i,j); break;
//            case 10 : textmark(i,j); break;                      //~@@@@R~
			case 1 :
			case 2 : // normal move mode
//                if (e.isShiftDown()) // create variation         //~@@@@R~
//                {   if (e.isControlDown())                       //~@@@@R~
//                    {   if (GF.blocked() && Pos.isLastMain()) return;//~@@@@R~
//                        changemove(i,j);                         //~@@@@R~
//                    }                                            //~@@@@R~
//                    else variation(i,j);                         //~@@@@R~
//                }                                                //~@@@@R~
//                else if (e.isControlDown())                      //~@@@@R~
//                    // goto variation                            //~@@@@R~
//                {   if (P.tree(i,j)!=null)                       //~@@@@R~
//                    {   gotovariation(i,j);                      //~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//                else if (e.isMetaDown()) // right click          //~@@@@R~
//                {   if (P.tree(i,j)!=null)                       //~@@@@R~
//                    {   gotovariation(i,j);                      //~@@@@R~
//                    }                                            //~@@@@R~
//                    else variation(i,j);                         //~@@@@R~
//                }                                                //~@@@@R~
//                else // place a W or B stone                     //~@@@@R~
				{	if (GF.blocked() && Pos.isLastMain()) return;
                  	int swselect=selectPiece(State,i,j);           //~@@@@I~
                    if (swselect<0)	//invalid selection            //~@@@@I~
                    	return;                                    //~@@@@I~
                    if (swselect>0) //selected                     //~@@@@I~
                    {                                              //~@@@@I~
                  		break;                                     //~@@@@I~
                    }                                              //~@@@@I~
					movemouse(i,j);
					if (P.color(i,j)!=0)	//color was set        //~@@@@I~
                    {                                              //~@@@@I~
					    pieceMoved(i,j);                           //~@@@@I~
                    }                                              //~@@@@I~
				}
				break;
		}
		showinformation();
        swCopyWillDone=false;   //do copy()                        //~1031I~
		copy(); // show position
	}
	// target rectangle things

	protected int iTarget=-1,jTarget=-1;

	public synchronized void mouseMoved (MouseEvent e)
	// show coordinates in the Lm Label
	{	if (!Active) return;
	    if (DisplayNodeName)
	    {   GF.setLabelM(LText);
	        DisplayNodeName=false;
	    }
	    int x=e.getX(),y=e.getY();
		x-=O+OTU; y-=O+OTU;
		int i=x/D,j=y/D; // determine position
		if (i<0 || j<0 || i>=S || j>=S)
		{	if (GF.showTarget()) { iTarget=jTarget=-1; copy(); }
			return;
		}
		if (GF.showTarget() && (iTarget!=i || jTarget!=j))
		{	drawTarget(i,j);
			iTarget=i; jTarget=j;
		}
//  	GF.setLabelM(Field.coordinate(i,j,S));                     //~@@@@R~
    	GF.setLabelM(Field.coordinate(i,j,S,YourColor));           //~@@@@I~
	}

	public void drawTarget (int i, int j)
	{	copy();
  		Graphics g=getGraphics();                                  //~@@@@R~
//		Graphics g=ActiveImage.getGraphics();	//@@@@test         //~@@@@R~
		if (g==null) return;
		i=O+OTU+i*D+D/2; j=O+OTU+j*D+D/2;
//        if (GF.bwColor()) g.setColor(Color.white);               //~@@@@R~
//        else g.setColor(Color.gray.brighter());                  //~@@@@R~
        g.setColor(Color.white);                                   //~@@@@I~
		g.drawRect(i-D/4,j-D/4,D/2,D/2);
		g.drawRect(i-D/4-1,j-D/4-1,D/2+2,D/2+2);                   //~@@@@R~
        if (Dump.Y) Dump.println("drawTarget g="+g.toString());    //~@@@@I~
		g.dispose();
	}

	public void mouseEntered (MouseEvent e)
	// start showing coordinates
	{	if (!Active) return;
	    if (DisplayNodeName)
	    {   GF.setLabel(LText);
	        DisplayNodeName=false;
	    }
	    int x=e.getX(),y=e.getY();
		x-=O+OTU; y-=O+OTU;
		int i=x/D,j=y/D; // determine position
		if (i<0 || j<0 || i>=S || j>=S) return;
		if (GF.showTarget())
		{	drawTarget(i,j);
			iTarget=i; jTarget=j;
		}
		GF.setLabelM(Field.coordinate(i,j,S));
	}

	public void mouseExited (MouseEvent e)
	// stop showing coordinates
	{	if (!Active) return;
	    GF.setLabelM("--");
	    if (!NodeName.equals(""))
	    {   GF.setLabel(NodeName);
	        DisplayNodeName=true;
	    }
	    if (GF.showTarget()) { iTarget=jTarget=-1; copy(); }
	}

	// *************** keyboard events ********************

    public synchronized void keyPressed (KeyEvent e)
//	{	if (e.isActionKey())                                       //~@@@@R~
  	{                                                              //~@@@@I~
//        if (e.isActionKey())                                     //~@@@@I~
//        {   switch (e.getKeyCode())                              //~@@@@R~
//            {   case KeyEvent.VK_DOWN : forward(); break;        //~@@@@R~
//                case KeyEvent.VK_UP : back(); break;             //~@@@@R~
//                case KeyEvent.VK_LEFT : varleft(); break;        //~@@@@R~
//                case KeyEvent.VK_RIGHT : varright(); break;      //~@@@@R~
//                case KeyEvent.VK_PAGE_DOWN : fastforward(); break;//~@@@@R~
//                case KeyEvent.VK_PAGE_UP : fastback(); break;    //~@@@@R~
//                case KeyEvent.VK_BACK_SPACE :                    //~@@@@R~
//                case KeyEvent.VK_DELETE : undo(); break;         //~@@@@R~
//                case KeyEvent.VK_HOME : varmain(); break;        //~@@@@R~
//                case KeyEvent.VK_END : varmaindown(); break;     //~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        else                                                     //~@@@@R~
//        {                                                          //~1116R~//~@@@@R~
//            if (Dump.Y) Dump.println("Board.keyPressed char="+Integer.toHexString((int)(e.getKeyChar())));//~1506R~//~@@@@R~
//            switch (e.getKeyChar())                                //~1116I~//~@@@@R~
//            {   case '*' : varmain(); break;                     //~@@@@R~
//                case '/' : varmaindown(); break;                 //~@@@@R~
//                case 'v' :                                       //~@@@@R~
//                case 'V' : varup(); break;                       //~@@@@R~
//                case 'm' :                                       //~@@@@R~
//                case 'M' : mark(); break;                        //~@@@@R~
//                case 'p' :                                       //~@@@@R~
//                case 'P' : resume(); break;                      //~@@@@R~
//                case 'c' :                                       //~@@@@R~
//                case 'C' : specialmark(Field.CIRCLE); break;     //~@@@@R~
//                case 's' :                                       //~@@@@R~
//                case 'S' : specialmark(Field.SQUARE); break;     //~@@@@R~
//                case 't' :                                       //~@@@@R~
//                case 'T' : specialmark(Field.TRIANGLE); break;   //~@@@@R~
//                case 'l' :                                       //~@@@@R~
//                case 'L' : letter(); break;                      //~@@@@R~
//                case 'r' :                                       //~@@@@R~
//                case 'R' : specialmark(Field.CROSS); break;      //~@@@@R~
//                case 'w' : setwhite(); break;                    //~@@@@R~
//                case 'b' : setblack(); break;                    //~@@@@R~
//                case 'W' : white(); break;                       //~@@@@R~
//                case 'B' : black(); break;                       //~@@@@R~
//                case '+' : gotonext(); break;                    //~@@@@R~
//                case '-' : gotoprevious(); break;                //~@@@@R~
//                // Bug (VK_DELETE not reported as ActionEvent)   //~@@@@R~
////              case KeyEvent.VK_BACK_SPACE :               //@@@@chked as isActionKey//~1428R~//~@@@@R~
////              case KeyEvent.VK_DELETE : undo(); break;    //@@@@by isActionKey()==true//~1428R~//~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
  	}

    public void keyReleased (KeyEvent e) {}
    public void keyTyped (KeyEvent e) {}

	// set things on the board

//    void gotovariation (int i, int j)                            //~@@@@R~
//    // goto the variation at (i,j)                               //~@@@@R~
//    {   TreeNode newpos=P.tree(i,j);                             //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        if (VCurrent &&  newpos.parentPos()==Pos.parentPos())    //~@@@@R~
//        {   goback();                                            //~@@@@R~
//            Pos=newpos;                                          //~@@@@R~
//            setnode();                                           //~@@@@R~
//            setlast();                                           //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (!VCurrent && newpos.parentPos()==Pos)           //~@@@@R~
//        {   Pos=newpos;                                          //~@@@@R~
//            setnode();                                           //~@@@@R~
//            setlast();                                           //~@@@@R~
//        }                                                        //~@@@@R~
//        copy();                                                  //~@@@@R~
//        showinformation();                                       //~@@@@R~
//    }                                                            //~@@@@R~

	public void set (int i, int j)
	// set a new move, if the board position is empty
	{	Action a;
		synchronized (Pos)
		{	if (P.color(i,j)==0) // empty?
			{	if (Pos.node().actions()!=null || Pos.parentPos()==null)
				// create a new node, if there the current node is not
				// empty, else use the current node. exception is the first
				// move, where we always create a new move.
				{	Node n=new Node(++number);
					if (P.color()>0)
					{	a=new Action("B",Field.string(i,j));
					}
					else
					{	a=new Action("W",Field.string(i,j));
					}
					n.addaction(a); // note the move action
					setaction(n,a,P.color()); // display the action
					// !!! We alow for suicide moves
					TreeNode newpos=new TreeNode(n);
					Pos.addchild(newpos); // note the move
					n.main(Pos);
					Pos=newpos; // update current position pointer
				}
				else
				{	Node n=Pos.node();
					if (P.color()>0)
					{	a=new Action("B",Field.string(i,j));
					}
					else
					{	a=new Action("W",Field.string(i,j));
					}
					n.addaction(a); // note the move action
					setaction(n,a,P.color()); // display the action
					// !!! We alow for suicide moves
				}
			}
		}
	}

	public void delete (int i, int j)
	// delete the stone and note it
	{	if (P.color(i,j)==0) return;
		synchronized (Pos)
		{	Node n=Pos.node();
//            if (GF.getParameter("puresgf",true) &&               //~@@@@R~
//                (n.contains("B") || n.contains("W"))) n=newnode();//~@@@@R~
			String field=Field.string(i,j);
			if (n.contains("AB",field))
			{	undonode();
				n.toggleaction(new Action("AB",field));
				setnode();
			}
			else if (n.contains("AW",field))
			{	undonode();
				n.toggleaction(new Action("AW",field));
				setnode();
			}
			else if (n.contains("B",field))
			{	undonode();
				n.toggleaction(new Action("B",field));
				setnode();
			}
			else if (n.contains("W",field))
			{	undonode();
				n.toggleaction(new Action("W",field));
				setnode();
			}
			else
			{	Action a=new Action("AE",field);
				n.expandaction(a);
				n.addchange(new Change(i,j,P.color(i,j)));
				P.color(i,j,0); update(i,j);
			}
			showinformation(); copy();
		}
	}

	public void changemove (int i, int j)
	// change a move to a new field (dangerous!)
	{	if (P.color(i,j)!=0) return;
		synchronized (Pos)
		{	ListElement la=Pos.node().actions();
			while (la!=null)
			{	Action a=(Action)la.content();
				if (a.type().equals("B") || a.type().equals("W"))
				{	undonode();
					a.arguments().content(Field.string(i,j));
					setnode();
					break;
				}
			}
		}
	}

//    public void removegroup (int i0, int j0)                     //~@@@@R~
//    // completely remove a group (at end of game, before count)  //~@@@@R~
//    // note all removals                                         //~@@@@R~
//    {   if (Pos.haschildren()) return;                           //~@@@@R~
//        if (P.color(i0,j0)==0) return;                           //~@@@@R~
//        Action a;                                                //~@@@@R~
//        P.markgroup(i0,j0);                                      //~@@@@R~
//        int i,j;                                                 //~@@@@R~
//        int c=P.color(i0,j0);                                    //~@@@@R~
//        Node n=Pos.node();                                       //~@@@@R~
//        if (n.contains("B") || n.contains("W")) n=newnode();     //~@@@@R~
//        for (i=0; i<S; i++)                                      //~@@@@R~
//            for (j=0; j<S; j++)                                  //~@@@@R~
//            {   if (P.marked(i,j))                               //~@@@@R~
//                {   a=new Action("AE",Field.string(i,j));        //~@@@@R~
//                    n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));//~@@@@R~
//                    n.expandaction(a);                           //~@@@@R~
//                    if (P.color(i,j)>0) { n.Pb++; Pb++; }        //~@@@@R~
//                    else { n.Pw++; Pw++; }                       //~@@@@R~
//                    P.color(i,j,0); update(i,j);                 //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

	public void mark (int i, int j)
	// Emphasize the field at i,j
	{	Node n=Pos.node();
		Action a=new MarkAction(Field.string(i,j),GF);
		n.toggleaction(a);
		update(i,j);
	}

	public void specialmark (int i, int j)
	// Emphasize with the SpecialMarker
	{	Node n=Pos.node();
		String s;
		switch (SpecialMarker)
        {   case Field.SQUARE : s="SQ"; break;                     //~@@@@R~
            case Field.CIRCLE : s="CR"; break;                     //~@@@@R~
            case Field.TRIANGLE : s="TR"; break;                   //~@@@@R~
            default : s="MA"; break;                               //~@@@@R~
//        {                                                        //~@@@@R~
//            case Field.BISHOP : s="SQ"; break;                   //~@@@@R~
//            case Field.KNIGHT : s="TR"; break;                   //~@@@@R~
//            default :           s="CR"; break;//ROOK             //~@@@@R~
		}
		Action a=new Action(s,Field.string(i,j));
		n.toggleaction(a);
		update(i,j);
	}

	public void markterritory (int i, int j, int color)
	{	Action a;
		if (color>0) a=new Action("TB",Field.string(i,j));
		else a=new Action("TW",Field.string(i,j));
		Pos.node().expandaction(a);
		update(i,j);
	}

//    public void textmark (int i, int j)                          //~@@@@R~
//    {   Action a=new Action("LB",Field.string(i,j)+":"+TextMarker);//~@@@@R~
//        Pos.node().expandaction(a);                              //~@@@@R~
//        update(i,j);                                             //~@@@@R~
//        GF.advanceTextmark();                                    //~@@@@R~
//    }                                                            //~@@@@R~

//    public void letter (int i, int j)                            //~@@@@R~
//    // Write a character to the field at i,j                     //~@@@@R~
//    {   Action a=new LabelAction(Field.string(i,j),GF);          //~@@@@R~
//        Pos.node().toggleaction(a);                              //~@@@@R~
//        update(i,j);                                             //~@@@@R~
//    }                                                            //~@@@@R~

	public Node newnode ()
	{	Node n=new Node(++number);
		TreeNode newpos=new TreeNode(n);
		Pos.addchild(newpos); // note the move
		n.main(Pos);
		Pos=newpos; // update current position pointerAction a;
		setlast();
		return n;
	}

	public void set (int i, int j, int c)
	// set a new stone, if the board position is empty
	// and we are on the last node.
	{	if (Pos.haschildren()) return;
		setc(i,j,c);
	}
	
	public void setc (int i, int j, int c)
	{    synchronized (Pos)
		{	Action a;
			if (P.color(i,j)==0) // empty?
			{	Node n=Pos.node();
//                if (GF.getParameter("puresgf",true) &&           //~@@@@R~
//                    (n.contains("B") || n.contains("W"))) n=newnode();//~@@@@R~
				n.addchange(new Change(i,j,0));
				if (c>0)
				{	a=new Action("AB",Field.string(i,j));
				}
				else
				{	a=new Action("AW",Field.string(i,j));
				}
				n.expandaction(a); // note the move action
				P.color(i,j,c);
				update(i,j);
			}
		}
	}
	
//    int captured=0,capturei,capturej;                            //~@@@@R~
    protected int captured=0;                                      //~@@@@I~

//    public void capture (int i, int j, Node n)                   //~@@@@R~
//    // capture neighboring groups without liberties              //~@@@@R~
//    // capture own group on suicide                              //~@@@@R~
//    {   int c=-P.color(i,j);                                     //~@@@@I~
//        captured=0;                                              //~@@@@R~
//        if (i>0) capturegroup(i-1,j,c,n);                        //~@@@@R~
//        if (j>0) capturegroup(i,j-1,c,n);                        //~@@@@R~
//        if (i<S-1) capturegroup(i+1,j,c,n);                      //~@@@@R~
//        if (j<S-1) capturegroup(i,j+1,c,n);                      //~@@@@R~
//        if (P.color(i,j)==-c)                                    //~@@@@R~
//        {   capturegroup(i,j,-c,n);                              //~@@@@R~
//        }                                                        //~@@@@R~
//        if (captured==1 && P.count(i,j)!=1)                      //~@@@@R~
//            captured=0;                                          //~@@@@R~
//        if (!GF.getParameter("korule",true)) captured=0;         //~@@@@R~
//    }                                                            //~@@@@R~

//    public void capturegroup (int i, int j, int c, Node n)       //~@@@@R~
//    // Used by capture to determine the state of the groupt at (i,j)//~@@@@R~
//    // Remove it, if it has no liberties and note the removals   //~@@@@R~
//    // as actions in the current node.                           //~@@@@R~
//    {   int ii,jj;                                               //~@@@@R~
//        Action a;                                                //~@@@@R~
//        if (P.color(i,j)!=c) return;                             //~@@@@R~
//        if (!P.markgrouptest(i,j,0)) // liberties?               //~@@@@R~
//        {   for (ii=0; ii<S; ii++)                               //~@@@@R~
//                for (jj=0; jj<S; jj++)                           //~@@@@R~
//                {   if (P.marked(ii,jj))                         //~@@@@R~
//                    {   n.addchange(new Change(ii,jj,P.color(ii,jj),P.number(ii,jj)));//~@@@@R~
//                        if (P.color(ii,jj)>0) { Pb++; n.Pb++; }  //~@@@@R~
//                        else { Pw++; n.Pw++; }                   //~@@@@R~
//                        P.color(ii,jj,0);                        //~@@@@R~
//                        update(ii,jj); // redraw the field (offscreen)//~@@@@R~
//                        captured++;                              //~@@@@R~
//                        capturei=ii; capturej=jj;                //~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    public void variation (int i, int j)                         //~@@@@R~
//    {   if (Pos.parentPos()==null) return;                       //~@@@@R~
//        if (P.color(i,j)==0) // empty?                           //~@@@@R~
//        {   int c=P.color();                                     //~@@@@R~
//            goback();                                            //~@@@@R~
//            P.color(-c);                                         //~@@@@R~
//            set(i,j);                                            //~@@@@R~
//            if (!GF.getParameter("variationnumbers",false))      //~@@@@R~
//            {   P.number(i,j,1);                                 //~@@@@R~
//                number=2;                                        //~@@@@R~
//                Pos.node().number(2);                            //~@@@@R~
//            }                                                    //~@@@@R~
//            update(i,j);                                         //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

	public String formtime (int t)
	{	int s,m,h=t/3600;
		if (h>=1)
		{	t=t-3600*h;
			m=t/60;
			s=t-60*m;
			return ""+h+":"+twodigits(m)+":"+twodigits(s);
		}
		else
		{	m=t/60;
			s=t-60*m;
			return ""+m+":"+twodigits(s);
		}
	}

	public String twodigits (int n)
	{	if (n<10) return "0"+n;
		else return ""+n;
	}

	public String lookuptime (String type)
	{	int t;
		if (Pos.parentPos()!=null)
		{	String s=Pos.parentPos().node().getaction(type);
			if (!s.equals(""))
			{	try { t=Integer.parseInt(s); return formtime(t); }
				catch (Exception e) { return ""; }
			}
			else return "";
		}
		return "";
	}

	public void showinformation ()
	// update the label to display the next move and who's turn it is
	// and disable variation buttons
	// update the navigation buttons
	// update the comment
	{	Node n=Pos.node();
		number=n.number();
		NodeName=n.getaction("N");
		String ms="";
		if (n.main())
		{	if (!Pos.haschildren()) ms="** ";
			else ms="* ";
		}
		switch (State)
		{	case 3 : LText=ms+GF.resourceString("Set_black_stones"); break;
			case 4 : LText=ms+GF.resourceString("Set_white_stones"); break;
			case 5 : LText=ms+GF.resourceString("Mark_fields"); break;
			case 6 : LText=ms+GF.resourceString("Place_letters"); break;
			case 7 : LText=ms+GF.resourceString("Delete_stones"); break;
//            case 8 : LText=ms+GF.resourceString("Remove_prisoners"); break;//~@@@@R~
            case 8 :                                               //~@@@@I~
				LText=ms+AG.resource.getString(R.string.DoScore);  //~@@@@I~
				break;                                             //~@@@@I~
			case 9 : LText=ms+GF.resourceString("Set_special_marker"); break;
			case 10 : LText=ms+GF.resourceString("Text__")+TextMarker; break;
			default :
//                if (P.color()>0)                                 //~@@@@R~
//                {   String s=lookuptime("BL");                   //~@@@@R~
//                    if (!s.equals(""))                           //~@@@@R~
//                        LText=ms+GF.resourceString("Next_move__Black_")+number+" ("+s+")";//~@@@@R~
//                    else                                         //~@@@@R~
//                        LText=ms+GF.resourceString("Next_move__Black_")+number;//~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   String s=lookuptime("WL");                   //~@@@@R~
//                    if (!s.equals(""))                           //~@@@@R~
//                        LText=ms+GF.resourceString("Next_move__White_")+number+" ("+s+")";//~@@@@R~
//                    else                                         //~@@@@R~
//                        LText=ms+GF.resourceString("Next_move__White_")+number;//~@@@@R~
//                }                                                //~@@@@R~
                if (P.color()>0)                                   //~@@@@I~
//            		LText=ms+GF.resourceString("Next_move__Black_")+moveNumber;//~@@@@R~
              		LText=ms+AG.resource.getString(R.string.Next_move__Black)+(moveNumber+1);//~@@@@R~
                else                                               //~@@@@I~
//                  LText=ms+GF.resourceString("Next_move__White_")+moveNumber;//~@@@@R~
              		LText=ms+AG.resource.getString(R.string.Next_move__White)+(moveNumber+1);//~@@@@R~
		}
//        LText=LText+" ("+siblings()+" "+GF.resourceString("Siblings")+", "+//~@@@@R~
//            children()+" "+GF.resourceString("Children")+")";    //~@@@@R~
		if (NodeName.equals(""))
		{   GF.setLabel(LText);
		    DisplayNodeName=false;
		}
		else
		{   GF.setLabel(NodeName);
		    DisplayNodeName=true;
		}
        if (Dump.Y) Dump.println("Board:showinformation icon button update start");//~1506R~//~@@@@R~
		GF.setState(3,!n.main());
		GF.setState(4,!n.main());
		GF.setState(7,!n.main() || Pos.haschildren());
		if (State==1 || State==2)
		{	if (P.color()==1) State=1;
			else State=2;
		}
		GF.setState(1,Pos.parentPos()!=null &&
			Pos.parentPos().firstChild()!=Pos);
		GF.setState(2,Pos.parentPos()!=null &&
			Pos.parentPos().lastChild()!=Pos);
		GF.setState(5,Pos.haschildren());
		GF.setState(6,Pos.parentPos()!=null);
		if (State!=9) GF.setState(State);
		else GF.setMarkState(SpecialMarker);
        if (Dump.Y) Dump.println("Board:showinformation icon button update End");//~1506R~//~@@@@R~
		int i,j;
		// delete all marks and variations
		for (i=0; i<S; i++)
			for (j=0; j<S; j++)
			{	if (P.tree(i,j)!=null)
				{	P.tree(i,j,null);
					update(i,j);
				}
				if (P.marker(i,j)!=Field.NONE)
				{	P.marker(i,j,Field.NONE);
					update(i,j);
				}
//                if (P.letter(i,j)!=0)                            //~@@@@R~
//                {   P.letter(i,j,0);                             //~@@@@R~
//                    update(i,j);                                 //~@@@@R~
//                }                                                //~@@@@R~
				if (P.haslabel(i,j))
				{	P.clearlabel(i,j);
					update(i,j);
				}
			}
		ListElement la=Pos.node().actions();
		Action a;
		String s;
		String sc="";
//		int let=1;
		while (la!=null) // setup the marks and letters
		{	a=(Action)la.content();
			if (a.type().equals("C"))
			{	sc=(String)a.arguments().content();
			}
//          else if (a.type().equals("SQ") || a.type().equals("SL"))//~@@@@R~
            else if (a.type().equals("SQ"))                        //~@@@@I~
			{	ListElement larg=a.arguments();
				while (larg!=null)
				{	s=(String)larg.content();
					i=Field.i(s);
					j=Field.j(s);
					if (valid(i,j))
                    {   P.marker(i,j,Field.SQUARE);                //~@@@@R~
//                  {   P.marker(i,j,Field.BISHOP);                //~@@@@R~
						update(i,j);
					}
					larg=larg.next();
				}
			}
//*"M":markaction, "MA":spacialmark(CROSS), "TW"/"TB":markteritory //~@@@@I~
//            else if (a.type().equals("MA") || a.type().equals("M")//~@@@@R~
//                || a.type().equals("TW") || a.type().equals("TB"))//~@@@@R~
//            {   ListElement larg=a.arguments();                  //~@@@@R~
//                while (larg!=null)                               //~@@@@R~
//                {   s=(String)larg.content();                    //~@@@@R~
//                    i=Field.i(s);                                //~@@@@R~
//                    j=Field.j(s);                                //~@@@@R~
//                    if (valid(i,j))                              //~@@@@R~
//                    {   P.marker(i,j,Field.CROSS);               //~@@@@R~
//                        update(i,j);                             //~@@@@R~
//                    }                                            //~@@@@R~
//                    larg=larg.next();                            //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
			else if (a.type().equals("TR"))
			{	ListElement larg=a.arguments();
				while (larg!=null)
				{	s=(String)larg.content();
					i=Field.i(s);
					j=Field.j(s);
					if (valid(i,j))
                    {   P.marker(i,j,Field.TRIANGLE);              //~@@@@R~
//                    {   P.marker(i,j,Field.KNIGHT);              //~@@@@R~
						update(i,j);
					}
					larg=larg.next();
				}
			}
			else if (a.type().equals("CR"))
			{	ListElement larg=a.arguments();
				while (larg!=null)
				{	s=(String)larg.content();
					i=Field.i(s);
					j=Field.j(s);
					if (valid(i,j))
					{	P.marker(i,j,Field.CIRCLE);                //~@@@@R~
// 					{	P.marker(i,j,Field.ROOK);                  //~@@@@R~
						update(i,j);
					}
					larg=larg.next();
				}
			}
//* labelAction()                                                  //~@@@@I~
//            else if (a.type().equals("L"))                       //~@@@@R~
//            {   ListElement larg=a.arguments();                  //~@@@@R~
//                while (larg!=null)                               //~@@@@R~
//                {   s=(String)larg.content();                    //~@@@@R~
//                    i=Field.i(s);                                //~@@@@R~
//                    j=Field.j(s);                                //~@@@@R~
//                    if (valid(i,j))                              //~@@@@R~
//                    {   P.letter(i,j,let);                       //~@@@@R~
//                        let++;                                   //~@@@@R~
//                        update(i,j);                             //~@@@@R~
//                    }                                            //~@@@@R~
//                    larg=larg.next();                            //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            else if (a.type().equals("LB"))                      //~@@@@R~
//            {   ListElement larg=a.arguments();                  //~@@@@R~
//                while (larg!=null)                               //~@@@@R~
//                {   s=(String)larg.content();                    //~@@@@R~
//                    i=Field.i(s);                                //~@@@@R~
//                    j=Field.j(s);                                //~@@@@R~
//                    if (valid(i,j) && s.length()>=4 &&           //~@@@@R~
//                        s.charAt(2)==':')                        //~@@@@R~
//                    {   P.setlabel(i,j,s.substring(3));          //~@@@@R~
//                        update(i,j);                             //~@@@@R~
//                    }                                            //~@@@@R~
//                    larg=larg.next();                            //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
			la=la.next();
		}
		TreeNode p;
		ListElement l=null;
		if (VCurrent)
		{	p=Pos.parentPos();
			if (p!=null) l=p.firstChild().listelement();
		}
		else if (Pos.haschildren() && Pos.firstChild()!=Pos.lastChild())
		{	l=Pos.firstChild().listelement();
		}
		while (l!=null)
		{	p=(TreeNode)l.content();
			if (p!=Pos)
			{	la=p.node().actions();
				while (la!=null)
				{	a=(Action)la.content();
					if (a.type().equals("W") || a.type().equals("B"))
					{	s=(String)a.arguments().content();
						i=Field.i(s);
						j=Field.j(s);
						if (valid(i,j))
						{	P.tree(i,j,p);
							update(i,j);
						}
						break;
					}
					la=la.next();
				}
			}
			l=l.next();
		}
//        if (!GF.getComment().equals(sc))                         //~@@@@R~
//        {   GF.setComment(sc);                                   //~@@@@R~
//        }                                                        //~@@@@R~
		if (Range>=0 && !KeepRange) clearrange();
	}

	public int siblings ()
	{	ListElement l=Pos.listelement();
		if (l==null) return 0;
		while (l.previous()!=null) l=l.previous();
		int count=0;
		while (l.next()!=null)
		{	l=l.next();
			count++;
		}
		return count;
	}
	
	public int children ()
	{	if (!Pos.haschildren()) return 0;
		TreeNode p=Pos.firstChild();
		if (p==null) return 0;
		ListElement l=p.listelement();
		if (l==null) return 0;
		while (l.previous()!=null) l=l.previous();
		int count=1;
		while (l.next()!=null)
		{	l=l.next();
			count++;
		}
		return count;
	}

	public void clearsend ()
	{	if (sendi>=0)
		{	int i=sendi; sendi=-1; update(i,sendj);
		}
	}

	public void getinformation ()
	// update the comment, when leaving the position
	{	ListElement la=Pos.node().actions();
		Action a;
		clearsend();
		while (la!=null)
		{	a=(Action)la.content();
			if (a.type().equals("C"))
//  		{	if (GF.getComment().equals(""))                    //~@@@@R~
			{                                                      //~@@@@I~
//                if (GF.getComment().equals(""))                  //~@@@@I~
					Pos.node().removeaction(la);
//                else                                             //~@@@@R~
//                    a.arguments().content((Object)GF.getComment());//~@@@@R~
				return;
			}
			la=la.next();
		}
//        String s=GF.getComment();                                //~@@@@R~
//        if (!s.equals(""))                                       //~@@@@R~
//        {   Pos.addaction(new Action("C",s));                    //~@@@@R~
//        }                                                        //~@@@@R~
	}

	public void update (int i, int j)
	// update the field (i,j) in the offscreen image Active
	// in dependance of the board position P.
	// display the last move mark, if applicable.
	{	if (ActiveImage==null) return;
		if (i<0 || j<0) return;
		Graphics g=ActiveImage.getGraphics();
        if (Dump.Y) Dump.println("update i="+i+",j="+j+",activeImage="+ActiveImage.toString());//~@@@@I~
		int xi=O+OTU+i*D;
		int xj=O+OTU+j*D;
		synchronized (this)
		{	g.drawImage(Empty,xi,xj,xi+D,xj+D,xi,xj,xi+D,xj+D,this);
//            if (GF.getParameter("shadows",true) && GF.getParameter("beauty",true)//~@@@@R~
//                && GF.getParameter("beautystones",true))         //~@@@@R~
//            {   if (P.color(i,j)!=0)                             //~@@@@R~
//                {   g.drawImage(EmptyShadow,xi-OP/2,xj+OP/2,xi+D-OP/2,xj+D+OP/2,//~@@@@R~
//                        xi-OP/2,xj+OP/2,xi+D-OP/2,xj+D+OP/2,this);//~@@@@R~
//                }                                                //~@@@@R~
//                else                                             //~@@@@R~
//                {   g.drawImage(Empty,xi-OP/2,xj+OP/2,xi+D-OP/2,xj+D+OP/2,//~@@@@R~
//                        xi-OP/2,xj+OP/2,xi+D-OP/2,xj+D+OP/2,this);//~@@@@R~
//                }                                                //~@@@@R~
//                g.setClip(xi-OP/2,xj+OP/2,D,D);	//no shadow    //~@@@@R~
//                update1(g,i-1,j);                                //~@@@@R~
//                update1(g,i,j+1);                                //~@@@@R~
//                update1(g,i-1,j+1);                              //~@@@@R~
//                g.setClip(xi,xj,D,D);                            //~@@@@R~
//                if (i<S-1 && P.color(i+1,j)!=0)                  //~@@@@R~
//                {   g.drawImage(EmptyShadow,xi+D-OP/2,xj+OP/2,xi+D,xj+D,//~@@@@R~
//                        xi+D-OP/2,xj+OP/2,xi+D,xj+D,this);       //~@@@@R~
//                }                                                //~@@@@R~
//                if (j>0 && P.color(i,j-1)!=0)                    //~@@@@R~
//                {   g.drawImage(EmptyShadow,xi,xj,xi+D-OP/2,xj+OP/2,//~@@@@R~
//                        xi,xj,xi+D-OP/2,xj+OP/2,this);           //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
		}
		g.setClip(xi,xj,D,D);
		update1(g,i,j);
		g.dispose();
	}
	
	void update1 (Graphics g, int i, int j)
	{	if (i<0 || i>=S || j<0 || j>=S) return;
//		char c[]=new char[1];
//		int n;
		int xi=O+OTU+i*D;
		int xj=O+OTU+j*D;
		if (P.color(i,j)>0 || (P.color(i,j)<0 && GF.blackOnly()))
		{	if (BlackStone!=null)
//  		{	g.drawImage(BlackStone,xi-1,xj-1,this);            //~@@@@R~
			{                                                      //~@@@@R~
//              if (P.marker(i,j)!=Field.BISHOP                    //~@@@@R~
//              &&  P.marker(i,j)!=Field.KNIGHT)                   //~@@@@R~
//                g.drawImage(BlackStone,xi+PIECE_MARGIN+1,xj+PIECE_MARGIN+1,this);//~@@@@R~
				drawPiece(g,1,P.piece(i,j),i,j);                   //~@@@@R~
			}
//            else                                                 //~@@@@R~
//            {   g.setColor(GF.blackColor());                     //~@@@@R~
//                g.fillOval(xi+1,xj+1,D-2,D-2);                   //~@@@@R~
//                g.setColor(GF.blackSparkleColor());              //~@@@@R~
//                g.drawArc(xi+D/2,xj+D/4,D/4,D/4,40,50);          //~@@@@R~
//            }                                                    //~@@@@R~
		}
		else if (P.color(i,j)<0)
		{	if (WhiteStone!=null)
//  		{	g.drawImage(WhiteStone,xi-1,xj-1,this); //Go stone image size=D+2//~@@@@R~
			{                                                      //~@@@@R~
//              if (P.marker(i,j)!=Field.BISHOP                    //~@@@@R~
//              &&  P.marker(i,j)!=Field.KNIGHT)                   //~@@@@R~
//                g.drawImage(WhiteStone,xi+PIECE_MARGIN+1,xj+PIECE_MARGIN+1,this); //line pixel=1//~@@@@R~
				drawPiece(g,-1,P.piece(i,j),i,j);                  //~@@@@R~
			}
//            else                                                 //~@@@@R~
//            {   g.setColor(GF.whiteColor());                     //~@@@@R~
//                g.fillOval(xi+1,xj+1,D-2,D-2);                   //~@@@@R~
//                g.setColor(GF.whiteSparkleColor());              //~@@@@R~
//                g.drawArc(xi+D/2,xj+D/4,D/4,D/4,40,50);          //~@@@@R~
//            }                                                    //~@@@@R~
		}
        else                                                       //~@@@@I~
        if (P.captured(i,j))	//after captured deleted           //~@@@@I~
        {                                                          //~@@@@I~
			drawCapturedPieceMark(g,i,j);                          //~@@@@I~
        }                                                          //~@@@@I~
		if (P.marker(i,j)!=Field.NONE)
		{	if (GF.bwColor())
			{	if (P.color(i,j)>=0) g.setColor(Color.white);
				else g.setColor(Color.black);
			}
			else g.setColor(GF.markerColor(P.color(i,j)));
			int h=D/4;
			switch (P.marker(i,j))
            {   case Field.CIRCLE :                                //~@@@@R~
                    g.drawOval(xi+D/2-h,xj+D/2-h,2*h,2*h);         //~@@@@R~
                    break;                                         //~@@@@R~
//            {                                                    //~@@@@R~
//                case Field.BISHOP :                              //~@@@@R~
//                    drawPiece(g,P.color(i,j),Field.BISHOP,i,j);  //~@@@@R~
//                    break;                                       //~@@@@R~
                case Field.CROSS :                                 //~@@@@R~
                      g.drawLine(xi+D/2-h,xj+D/2-h,xi+D/2+h,xj+D/2+h);//~@@@@R~
                      g.drawLine(xi+D/2+h,xj+D/2-h,xi+D/2-h,xj+D/2+h);//~@@@@R~
                      break;                                       //~@@@@R~
                case Field.TRIANGLE :                              //~@@@@R~
					g.drawLine(xi+D/2,xj+D/2-h,xi+D/2-h,xj+D/2+h); //~@@@@I~
					g.drawLine(xi+D/2,xj+D/2-h,xi+D/2+h,xj+D/2+h); //~@@@@I~
					g.drawLine(xi+D/2-h,xj+D/2+h,xi+D/2+h,xj+D/2+h);//~@@@@I~
//                case Field.KNIGHT:                               //~@@@@R~
//                    drawPiece(g,P.color(i,j),Field.KNIGHT,i,j);  //~@@@@R~
					break;
                default : g.drawRect(xi+D/2-h,xj+D/2-h,2*h,2*h);   //~@@@@R~
			}
		}
//        if (P.letter(i,j)!=0)                                    //~@@@@R~
//        {   if (GF.bwColor())                                    //~@@@@R~
//            {   if (P.color(i,j)>=0) g.setColor(Color.white);    //m~@@@@R~//~@@@@R~
//                else g.setColor(Color.black);                    //~@@@@R~
//            }                                                    //~@@@@R~
//            else g.setColor(GF.labelColor(P.color(i,j)));        //~@@@@R~
//            c[0]=(char)('a'+P.letter(i,j)-1);                    //~@@@@R~
//            String hs=new String(c);                             //~@@@@R~
//            int w=fontmetrics.stringWidth(hs)/2;                 //~@@@@R~
//            int h=fontmetrics.getAscent()/2-1;                   //~@@@@R~
//            g.setFont(font);                                     //~@@@@R~
//            g.drawString(hs,xi+D/2-w,xj+D/2+h);                  //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (P.haslabel(i,j))                                //~@@@@R~
//        {   if (GF.bwColor())                                    //~@@@@R~
//            {   if (P.color(i,j)>=0) g.setColor(Color.white);    //~@@@@R~
//                else g.setColor(Color.black);                    //~@@@@R~
//            }                                                    //~@@@@R~
//            else g.setColor(GF.labelColor(P.color(i,j)));        //~@@@@R~
//            String hs=P.label(i,j);                              //~@@@@R~
//            int w=fontmetrics.stringWidth(hs)/2;                 //~@@@@R~
//            int h=fontmetrics.getAscent()/2-1;                   //~@@@@R~
//            g.setFont(font);                                     //~@@@@R~
//            g.drawString(hs,xi+D/2-w,xj+D/2+h);                  //~@@@@R~
//        }                                                        //~@@@@R~
                                                        //~@@@@I~
		if (P.tree(i,j)!=null && !VHide)
		{	if (GF.bwColor()) g.setColor(Color.white);
			else g.setColor(Color.green);
			g.drawLine(xi+D/2-D/6,xj+D/2,xi+D/2+D/6,xj+D/2);
			g.drawLine(xi+D/2,xj+D/2-D/6,xi+D/2,xj+D/2+D/6);
		}
		if (sendi==i && sendj==j)
		{	if (GF.bwColor())
			{	if (P.color(i,j)>0) g.setColor(Color.white);
				else g.setColor(Color.black);
			}
			else g.setColor(Color.gray);
			g.drawLine(xi+D/2-1,xj+D/2,xi+D/2+1,xj+D/2);
			g.drawLine(xi+D/2,xj+D/2-1,xi+D/2,xj+D/2+1);
		}
		if (swSelected && iSelected==i && jSelected==j)            //~@@@@I~
        {                                                          //~@@@@I~
        	drawSelected(g,i,j);                                   //~@@@@I~
		}                                                          //~@@@@I~
        else                                                       //~@@@@I~
//  	if (lasti==i && lastj==j && showlast)                      //~@@@@R~
    	if (lasti==i && lastj==j && showlast && (AG.Options & AG.OPTIONS_SHOW_LAST)!=0)//~@@@@R~
        {                                                          //~@@@@I~
//            if (GF.lastNumber() || (Range>=0 && P.number(i,j)>Range))//~@@@@I~
//            {   if (P.color(i,j)>0) g.setColor(Color.white);     //~@@@@R~
//                else g.setColor(Color.black);                    //~@@@@R~
//                String hs=""+(P.number(i,j)%100);                //~@@@@R~
//                int w=fontmetrics.stringWidth(hs)/2;             //~@@@@R~
//                int h=fontmetrics.getAscent()/2-1;               //~@@@@R~
//                g.setFont(font);                                 //~@@@@R~
//                g.drawString(hs,xi+D/2-w,xj+D/2+h);              //~@@@@R~
//            }                                                    //~@@@@R~
//            else                                                 //~@@@@R~
//            {   if (GF.bwColor())                                //~@@@@R~
//                {   if (P.color(i,j)>0) g.setColor(Color.white); //~@@@@R~
//                    else g.setColor(Color.black);                //~@@@@R~
//                }                                                //~@@@@R~
//                else g.setColor(Color.red);                      //~@@@@R~
//                g.drawLine(xi+D/2-D/6,xj+D/2,xi+D/2+D/6,xj+D/2); //~@@@@R~
//                g.drawLine(xi+D/2,xj+D/2-D/6,xi+D/2,xj+D/2+D/6); //~@@@@R~
//            }                                                    //~@@@@R~
            g.drawRect(AG.lastPosColor,3/*line Width*/,xi+6,xj+6,D-12/*width*/,D-12/*height*/);//~@@@@R~//~v101R~
		}
        else                                                       //~1A35I~
    	if (swCursorMovedFrom && lastifrom==i && lastjfrom==j && showlast && (AG.Options & AG.OPTIONS_SHOW_LAST)!=0)//~1A35I~
        {                                                          //~1A35I~
        	drawLastFrom(g,i,j);	//blue square                  //~1A35I~
        }                                                          //~1A35I~
		else if (P.color(i,j)!=0 && Range>=0 && P.number(i,j)>Range)
		{	if (P.color(i,j)>0) g.setColor(Color.white);
			else g.setColor(Color.black);
			String hs=""+(P.number(i,j)%100);
			int w=fontmetrics.stringWidth(hs)/2;
			int h=fontmetrics.getAscent()/2-1;
			g.setFont(font);
			g.drawString(hs,xi+D/2-w,xj+D/2+h);
		}
	}

	public void copy ()
	// copy the offscreen board to the screen
	{	if (ActiveImage==null) return;
        if (swCopyWillDone)                                        //~1031I~
        {                                                          //~1031I~
        	if (Dump.Y) Dump.println("Board copy() nop by swCopyWillDone");//~1031I~
        	return;                                                //~1031I~
        }                                                          //~1031I~
        if (Dump.Y) Dump.println("Board copy start board="+this.toString()+",Activeimage="+ActiveImage.toString());//~1506R~
//        System.out.println("Board copy start board="+this.toString()+",Activeimage="+ActiveImage.toString());//~1A01I~//~1031R~
		try
		{   getGraphics().drawImage(ActiveImage,0,0,this);
		}
		catch (Exception e) {}
        if (Dump.Y) Dump.println("Board copy end graphics="+getGraphics().toString());//~1506R~
//        System.out.println("Board copy end graphics="+getGraphics().toString());//~1A01I~//~1031R~
	}

	public void undonode ()
	// Undo everything that has been changed in the node.
	// (This will not correct the last move marker!)
	{	Node n=Pos.node();
		clearrange();
		ListElement p=n.lastchange();
		while (p!=null)
		{	Change c=(Change)p.content();
			P.color(c.I,c.J,c.C);
			P.number(c.I,c.J,c.N);
			update(c.I,c.J);
			p=p.previous();
		}
		n.clearchanges();
		Pw-=n.Pw; Pb-=n.Pb;
	}

	public void setaction (Node n, Action a, int c)
	// interpret a set move action, update the last move marker,
	// c being the color of the move.
	{	String s=(String)(a.arguments().content());
		int i=Field.i(s);
		int j=Field.j(s);
		if (!valid(i,j)) return;
		n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));
		P.color(i,j,c);
		P.number(i,j,n.number()-1);
		showlast=false;
		update(lasti,lastj);
        showlast=true;                                             //~@@@@R~
		lasti=i; lastj=j;
		update(i,j);
		P.color(-c);
//        capture(i,j,n);                                          //~@@@@R~
	}

	public void placeaction (Node n, Action a, int c)
	// interpret a set move action, update the last move marker,
	// c being the color of the move.
	{	int i,j;
		ListElement larg=a.arguments();
		while (larg!=null)
		{	String s=(String)larg.content();
			i=Field.i(s);
			j=Field.j(s);
			if (valid(i,j))
			{	n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));
				P.color(i,j,c);
				update(i,j);
			}
			larg=larg.next();
		}
	}

	public void emptyaction (Node n, Action a)
	// interpret a remove stone action
	{	int i,j/*,r=1*/;
		ListElement larg=a.arguments();
		while (larg!=null)
		{	String s=(String)larg.content();
			i=Field.i(s);
			j=Field.j(s);
			if (valid(i,j))
			{	n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));
				if (P.color(i,j)<0) { n.Pw++; Pw++; }
				else if (P.color(i,j)>0) { n.Pb++; Pb++; }
				P.color(i,j,0);
				update(i,j);
			}
			larg=larg.next();
		}
	}

	public void setnode ()
	// interpret all actions of a node
	{	Node n=Pos.node();
		ListElement p=n.actions();
		if (p==null) return;
		Action a;
//		String s;
//		int i,j;
		while (p!=null)
		{	a=(Action)(p.content());
			if (a.type().equals("SZ"))
			{	if (Pos.parentPos()==null)
				// only at first node
				{	try
					{	int ss=Integer.parseInt(a.argument().trim());
						if (ss!=S)
						{	S=ss;
							P=new Position(S);
//  						makeimages();                          //~1A01R~
    						makeimages(true/*repaint*/);           //~1A01I~
							updateall();
							copy();
						}
					}
					catch (NumberFormatException e) {}
				}
			}
			p=p.next();
		}
		n.clearchanges();
		n.Pw=n.Pb=0;
		p=n.actions();
		while (p!=null)
		{	a=(Action)(p.content());
			if (a.type().equals("B"))
			{	setaction(n,a,1);
			}
			else if (a.type().equals("W"))
			{	setaction(n,a,-1);
			}
			if (a.type().equals("AB"))
			{	placeaction(n,a,1);
			}
			if (a.type().equals("AW"))
			{	placeaction(n,a,-1);
			}
			else if (a.type().equals("AE"))
			{	emptyaction(n,a);
			}
			p=p.next();
		}
	}

	public void setlast ()
	// update the last move marker applying all
	// set move actions in the node
	{	Node n=Pos.node();
		ListElement l=n.actions();
		Action a;
		String s;
		int i=lasti,j=lastj;
		lasti=-1; lastj=-1;
		update(i,j);
		while (l!=null)
		{	a=(Action)(l.content());
			if (a.type().equals("B") || a.type().equals("W"))
			{	s=(String)a.arguments().content();
				i=Field.i(s);
				j=Field.j(s);
				if (valid(i,j))
				{	lasti=i; lastj=j; update(lasti,lastj);
					P.color(-P.color(i,j));
				}
			}
			l=l.next();
		}
		number=n.number();
	}

//    public void undo ()                                          //~@@@@R~
//    // take back the last move, ask if necessary                 //~@@@@R~
//    {   // System.out.println("undo");                           //~@@@@R~
//        if (Pos.haschildren() ||                                 //~@@@@R~
//            (Pos.parent()!=null &&                               //~@@@@R~
//                Pos.parent().lastchild()!=Pos.parent().firstchild() &&//~@@@@R~
//                Pos==Pos.parent().firstchild()))                 //~@@@@R~
//        {   if (GF.askUndo()) doundo(Pos);                       //~@@@@R~
//        }                                                        //~@@@@R~
//        else doundo(Pos);                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    public void doundo (TreeNode pos1)                           //~@@@@R~
//    {   if (pos1!=Pos) return;                                   //~@@@@R~
//        if (Pos.parentPos()==null)                               //~@@@@R~
//        {   undonode();                                          //~@@@@R~
//            Pos.removeall();                                     //~@@@@R~
//            Pos.node().removeactions();                          //~@@@@R~
//            showinformation();                                   //~@@@@R~
//            copy();                                              //~@@@@R~
//            return;                                              //~@@@@R~
//        }                                                        //~@@@@R~
//        TreeNode pos=Pos;                                        //~@@@@R~
//        goback();                                                //~@@@@R~
//        if (pos==Pos.firstchild()) Pos.removeall();              //~@@@@R~
//        else Pos.remove(pos);                                    //~@@@@R~
//        goforward();                                             //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public void goback ()                                        //~@@@@R~
//    // go one move back                                          //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        if (Pos.parentPos()==null) return;                       //~@@@@R~
//        undonode();                                              //~@@@@R~
//        Pos=Pos.parentPos();                                     //~@@@@R~
//        setlast();                                               //~@@@@R~
//    }                                                            //~@@@@R~

	public void goforward ()
	// go one move forward
	{	if (!Pos.haschildren()) return;
		Pos=Pos.firstChild();
		setnode();
		setlast();
	}
	
//    public void gotoMove (int move)                              //~@@@@R~
//    {   while (number<=move && Pos.firstChild()!=null)           //~@@@@R~
//        {   goforward();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    public void tovarleft ()                                     //~@@@@R~
//    {   ListElement l=Pos.listelement();                         //~@@@@R~
//        if (l==null) return;                                     //~@@@@R~
//        if (l.previous()==null) return;                          //~@@@@R~
//        TreeNode newpos=(TreeNode)l.previous().content();        //~@@@@R~
//        goback();                                                //~@@@@R~
//        Pos=newpos;                                              //~@@@@R~
//        setnode();                                               //~@@@@R~
//    }                                                            //~@@@@R~

//    public void tovarright ()                                    //~@@@@R~
//    {   ListElement l=Pos.listelement();                         //~@@@@R~
//        if (l==null) return;                                     //~@@@@R~
//        if (l.next()==null) return;                              //~@@@@R~
//        TreeNode newpos=(TreeNode)l.next().content();            //~@@@@R~
//        goback();                                                //~@@@@R~
//        Pos=newpos;                                              //~@@@@R~
//        setnode();                                               //~@@@@R~
//    }                                                            //~@@@@R~

//    public boolean hasvariation ()                               //~@@@@R~
//    {   ListElement l=Pos.listelement();                         //~@@@@R~
//        if (l==null) return false;                               //~@@@@R~
//        if (l.next()==null) return false;                        //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~
	
	static public TreeNode getNext (TreeNode p)
	{	ListElement l=p.listelement();
		if (l==null) return null;
		if (l.next()==null) return null;
		return (TreeNode)l.next().content();
	}

	public void territory (int i, int j)
	{	mark(i,j);
		copy();
	}

//    public void setpass ()                                       //~@@@@R~
//    {   TreeNode p=T.top();                                      //~@@@@R~
//        while (p.haschildren()) p=p.firstChild();                //~@@@@R~
//        Node n=new Node(number);                                 //~@@@@R~
//        p.addchild(new TreeNode(n));                             //~@@@@R~
//        n.main(p);                                               //~@@@@R~
//        GF.yourMove(Pos!=p);                                     //~@@@@R~
//        if (Pos==p)                                              //~@@@@R~
//        {   getinformation();                                    //~@@@@R~
//            int c=P.color();                                     //~@@@@R~
//            goforward();                                         //~@@@@R~
//            P.color(-c);                                         //~@@@@R~
//            showinformation();                                   //~@@@@R~
//            GF.addComment(GF.resourceString("Pass"));            //~@@@@R~
//        }                                                        //~@@@@R~
//        MainColor=-MainColor;                                    //~@@@@R~
//        captured=0;                                              //~@@@@R~
//    }                                                            //~@@@@R~
    public void setpass ()                                         //~@@@@I~
    {                                                              //~@@@@I~
        moveNumber++;                                              //~@@@@I~
        GF.yourMove(true);                                       //~@@@@I~
        getinformation();                                          //~@@@@I~
        int c=P.color();                                           //~@@@@I~
        goforward();                                               //~@@@@I~
        P.color(-c);                                               //~@@@@I~
        swSelected=false;                                          //~@@@@M~
        showinformation();                                         //~@@@@I~
        MainColor=-MainColor;                                      //~@@@@I~
    }                                                              //~@@@@I~

//    public void resume ()                                        //~@@@@R~
//    // Resume playing after marking                              //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        State=1;                                                 //~@@@@R~
//        showinformation();                                       //~@@@@R~
//    }                                                            //~@@@@R~

//    Node newtree ()                                              //~@@@@R~
//    {   number=1; Pw=Pb=0;                                       //~@@@@R~
//        Node n=new Node(number);                                 //~@@@@R~
//        T=new SGFTree(n);                                        //~@@@@R~
//        Trees.setElementAt(T,CurrentTree);                       //~@@@@R~
//        resettree();                                             //~@@@@R~
//        return n;                                                //~@@@@R~
//    }                                                            //~@@@@R~
	
//    void resettree ()                                            //~@@@@R~
//    {   Pos=T.top();                                             //~@@@@R~
//        P=new Position(S);                                       //~@@@@R~
//        lasti=lastj=-1;                                          //~@@@@R~
//        Pb=Pw=0;                                                 //~@@@@R~
//        updateall();                                             //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public boolean deltree ()                                    //~@@@@R~
//    {   newtree();                                               //~@@@@R~
//        return true;                                             //~@@@@R~
//    }                                                            //~@@@@R~

	public void active (boolean f) { Active=f; }

	public int getboardsize ()
	{	return S;
	}

	public boolean canfinish ()
	{	return Pos.isLastMain();
	}

	public int maincolor ()
	{	return MainColor;
	}
	
	public boolean ismain ()
	{	return Pos.isLastMain();
	}

	Node firstnode ()
	{	return T.top().node();
	}

	boolean valid (int i, int j)
	{	return (i>=0 && i<S && j>=0 && j<S);
	}

	public void clearrange ()
	{	if (Range==-1) return;
		Range=-1; updateall(); copy();
	}

	// *****************************************
	// Methods to be called from outside sources
	// *****************************************

	// ****** navigational things **************

	// methods to move in the game tree, including
	// update of the visible board:

//    public synchronized void back ()                             //~@@@@R~
//    // one move up                                               //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        goback();                                                //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

    public synchronized void forward ()                            //~@@@@R~
    // one move down                                               //~@@@@R~
    {   State=1;                                                   //~@@@@R~
        getinformation();                                          //~@@@@R~
        goforward();                                               //~@@@@R~
        showinformation();                                         //~@@@@R~
        copy();                                                    //~@@@@R~
    }                                                              //~@@@@R~

//    public synchronized void fastback ()                         //~@@@@R~
//    // 10 moves up                                               //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        for (int i=0; i<10; i++) goback();                       //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void fastforward ()                      //~@@@@R~
//    // 10 moves down                                             //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        for (int i=0; i<10; i++) goforward();                    //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void allback ()                          //~@@@@R~
//    // to top of tree                                            //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        while (Pos.parentPos()!=null) goback();                  //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void allforward ()                       //~@@@@R~
//    // to end of variation                                       //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        while (Pos.haschildren()) goforward();                   //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void varleft ()                          //~@@@@R~
//    // one variation to the left                                 //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        ListElement l=Pos.listelement();                         //~@@@@R~
//        if (l==null) return;                                     //~@@@@R~
//        if (l.previous()==null) return;                          //~@@@@R~
//        TreeNode newpos=(TreeNode)l.previous().content();        //~@@@@R~
//        goback();                                                //~@@@@R~
//        Pos=newpos;                                              //~@@@@R~
//        setnode();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void varright ()                         //~@@@@R~
//    // one variation to the right                                //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        ListElement l=Pos.listelement();                         //~@@@@R~
//        if (l==null) return;                                     //~@@@@R~
//        if (l.next()==null) return;                              //~@@@@R~
//        TreeNode newpos=(TreeNode)l.next().content();            //~@@@@R~
//        goback();                                                //~@@@@R~
//        Pos=newpos;                                              //~@@@@R~
//        setnode();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void varmain ()                          //~@@@@R~
//    // to the main variation                                     //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        while (Pos.parentPos()!=null &&                          //~@@@@R~
//            !Pos.node().main())                                  //~@@@@R~
//        {   goback();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        if (Pos.haschildren()) goforward();                      //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void varmaindown ()                      //~@@@@R~
//    // to end of main variation                                  //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        while (Pos.parentPos()!=null &&                          //~@@@@R~
//            !Pos.node().main())                                  //~@@@@R~
//        {   goback();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        while (Pos.haschildren())                                //~@@@@R~
//        {   goforward();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void varup ()                            //~@@@@R~
//    // to the start of the variation                             //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        if (Pos.parentPos()!=null) goback();                     //~@@@@R~
//        while (Pos.parentPos()!=null &&                          //~@@@@R~
//            Pos.parentPos().firstChild()==Pos.parentPos().lastChild()//~@@@@R~
//            && !Pos.node().main())                               //~@@@@R~
//        {   goback();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void gotonext ()                         //~@@@@R~
//    // goto next named node                                      //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        goforward();                                             //~@@@@R~
//        while (Pos.node().getaction("N").equals(""))             //~@@@@R~
//        {   if (!Pos.haschildren()) break;                       //~@@@@R~
//            goforward();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void gotoprevious ()                     //~@@@@R~
//    // gotoprevious named node                                   //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        goback();                                                //~@@@@R~
//        while (Pos.node().getaction("N").equals(""))             //~@@@@R~
//        {   if (Pos.parentPos()==null) break;                    //~@@@@R~
//            goback();                                            //~@@@@R~
//        }                                                        //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void gotonextmain ()                     //~@@@@R~
//    // goto next game tree                                       //~@@@@R~
//    {   if (CurrentTree+1>=Trees.size()) return;                 //~@@@@R~
//        State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        T.top().setaction("AP","Jago:"+GF.version(),true);       //~@@@@R~
//        T.top().setaction("SZ",""+S,true);                       //~@@@@R~
//        T.top().setaction("GM","1",true);                        //~@@@@R~
//        T.top().setaction("FF",                                  //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        CurrentTree++;                                           //~@@@@R~
//        T=(SGFTree)Trees.elementAt(CurrentTree);                 //~@@@@R~
//        resettree();                                             //~@@@@R~
//        setnode();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void gotopreviousmain ()                 //~@@@@R~
//    // goto previous game tree                                   //~@@@@R~
//    {   if (CurrentTree==0) return;                              //~@@@@R~
//        State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        T.top().setaction("AP","Jago:"+GF.version(),true);       //~@@@@R~
//        T.top().setaction("SZ",""+S,true);                       //~@@@@R~
//        T.top().setaction("GM","1",true);                        //~@@@@R~
//        T.top().setaction("FF",                                  //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        CurrentTree--;                                           //~@@@@R~
//        T=(SGFTree)Trees.elementAt(CurrentTree);                 //~@@@@R~
//        resettree();                                             //~@@@@R~
//        setnode();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
    
//    public synchronized void addnewgame ()                       //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        T.top().setaction("AP","Jago:"+GF.version(),true);       //~@@@@R~
//        T.top().setaction("SZ",""+S,true);                       //~@@@@R~
//        T.top().setaction("GM","1",true);                        //~@@@@R~
//        T.top().setaction("FF",                                  //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        Node n=new Node(number);                                 //~@@@@R~
//        T=new SGFTree(n);                                        //~@@@@R~
//        CurrentTree++;                                           //~@@@@R~
//        if (CurrentTree>=Trees.size()) Trees.addElement(T);      //~@@@@R~
//        else Trees.insertElementAt(T,CurrentTree);               //~@@@@R~
//        resettree();                                             //~@@@@R~
//        setnode();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~
    
//    public synchronized void removegame ()                       //~@@@@R~
//    {   if (Trees.size()==1) return;                             //~@@@@R~
//        Trees.removeElementAt(CurrentTree);                      //~@@@@R~
//        if (CurrentTree>=Trees.size()) CurrentTree--;            //~@@@@R~
//        T=(SGFTree)Trees.elementAt(CurrentTree);                 //~@@@@R~
//        resettree();                                             //~@@@@R~
//        setnode();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

    // ***** change the node at end of main tree ********
    // usually called by another board or server

	public synchronized void black (int i, int j)
	// white move at i,j at the end of the main tree
	{	if (i<0 || j<0 || i>=S || j>=S) return;
		TreeNode p=T.top();
		while (p.haschildren()) p=p.firstChild();
		Action a=new Action("B",Field.string(i,j));
		Node n=new Node(p.node().number()+1);
		n.addaction(a);
		p.addchild(new TreeNode(n));
		n.main(p);
		GF.yourMove(Pos!=p);
        swCopyWillDone=true;	//copy() will be called by pieceMoved()//~1A35I~
		if (Pos==p) forward();
        swCopyWillDone=false;                                      //~1A35I~
        pieceMoved(i,j);	//msg from partner,chk captured        //~@@@@I~
		MainColor=-1;
	}

	public synchronized void white (int i, int j)
	// black move at i,j at the end of the main tree
	{	if (i<0 || j<0 || i>=S || j>=S) return;
		TreeNode p=T.top();
		while (p.haschildren()) p=p.firstChild();
		Action a=new Action("W",Field.string(i,j));
		Node n=new Node(p.node().number()+1);
		n.addaction(a);
		p.addchild(new TreeNode(n));
		n.main(p);
		GF.yourMove(Pos!=p);
        swCopyWillDone=true;	//copy() will be called by pieceMoved()//~1A35I~
		if (Pos==p) forward();
        swCopyWillDone=false;                                      //~1A35I~
        pieceMoved(i,j);	//msg from partner,chk captured        //~@@@@I~
		MainColor=1;
	}

	public synchronized void setblack (int i, int j)
	// set a white stone at i,j at the end of the main tree
	{	if (i<0 || j<0 || i>=S || j>=S) return;
		TreeNode p=T.top();
		while (p.haschildren()) p=(TreeNode)p.firstChild();
		Action a=new Action("AB",Field.string(i,j));
		Node n;
		if (p==T.top())
		{	TreeNode newpos;
			p.addchild(newpos=new TreeNode(1));
			if (Pos==p) Pos=newpos;
			p=newpos;
			p.main(true);
		}
		n=p.node();
		n.expandaction(a);
		if (Pos==p)
		{	n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));
			P.color(i,j,1); update(i,j);
			copy();
		}
		MainColor=-1;
	}

	public synchronized void setwhite (int i, int j)
	// set a white stone at i,j at the end of the main tree
	{	if (i<0 || j<0 || i>=S || j>=S) return;
		TreeNode p=T.top();
		while (p.haschildren()) p=(TreeNode)p.firstChild();
		Action a=new Action("AW",Field.string(i,j));
		Node n;
		if (p==T.top())
		{	TreeNode newpos;
			p.addchild(newpos=new TreeNode(1));
			if (Pos==p) Pos=newpos;
			p=newpos;
			p.main(true);
		}
		n=p.node();
		n.expandaction(a);
		if (Pos==p)
		{	n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));
			P.color(i,j,-1); update(i,j);
			copy();
		}
		MainColor=1;
	}
    //********************************************************     //~@@@@I~
    //*set piece type to field                                     //~@@@@I~
    //********************************************************     //~@@@@I~
	public synchronized void setblack (int i, int j,int Ppiece)    //~@@@@I~
	{                                                              //~@@@@I~
		if (P.color(i,j)==0)	//was set                          //~@@@@I~
        {                                                          //~@@@@I~
			P.setPiece(i,j,Ppiece);	//set to Field(i,j)            //~@@@@I~
			setblack(i,j);                                         //~@@@@I~
        }                                                          //~@@@@I~
	}                                                              //~@@@@I~
    //********************************************************     //~@@@@I~
	public synchronized void setwhite (int i, int j,int Ppiece)    //~@@@@I~
	{                                                              //~@@@@I~
		if (P.color(i,j)==0)	//was set                          //~@@@@I~
        {                                                          //~@@@@I~
			P.setPiece(i,j,Ppiece);	//set to Field(i,j)            //~@@@@M~
			setwhite(i,j);                                         //~@@@@R~
        }                                                          //~@@@@I~
	}                                                              //~@@@@I~

//    public synchronized void pass ()                             //~@@@@R~
//    // pass at current node                                      //~@@@@R~
//    // notify BoardInterface                                     //~@@@@R~
//    {   if (Pos.haschildren()) return;                           //~@@@@R~
//        if (GF.blocked() && Pos.node().main()) return;           //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        P.color(-P.color());                                     //~@@@@R~
//        Node n=new Node(number);                                 //~@@@@R~
//        Pos.addchild(new TreeNode(n));                           //~@@@@R~
//        n.main(Pos);                                             //~@@@@R~
//        goforward();                                             //~@@@@R~
//        setlast();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//        GF.addComment(GF.resourceString("Pass"));                //~@@@@R~
//        captured=0;                                              //~@@@@R~
//    }                                                            //~@@@@R~

//    public synchronized void remove (int i0, int j0)             //~@@@@R~
//    // completely remove a group there                           //~@@@@R~
//    {   int s=State;                                             //~@@@@R~
//        varmaindown();                                           //~@@@@R~
//        State=s;                                                 //~@@@@R~
//        if (P.color(i0,j0)==0) return;                           //~@@@@R~
//        Action a;                                                //~@@@@R~
//        P.markgroup(i0,j0);                                      //~@@@@R~
//        int i,j;                                                 //~@@@@R~
//        int c=P.color(i0,j0);                                    //~@@@@R~
//        Node n=Pos.node();                                       //~@@@@R~
//        if (GF.getParameter("puresgf",true) &&                   //~@@@@R~
//            (n.contains("B") || n.contains("W"))) n=newnode();   //~@@@@R~
//        for (i=0; i<S; i++)                                      //~@@@@R~
//            for (j=0; j<S; j++)                                  //~@@@@R~
//            {   if (P.marked(i,j))                               //~@@@@R~
//                {   a=new Action("AE",Field.string(i,j));        //~@@@@R~
//                    n.addchange(new Change(i,j,P.color(i,j),P.number(i,j)));//~@@@@R~
//                    n.expandaction(a);                           //~@@@@R~
//                    if (P.color(i,j)>0) { n.Pb++; Pb++; }        //~@@@@R~
//                    else { n.Pw++; Pw++; }                       //~@@@@R~
//                    P.color(i,j,0); update(i,j);                 //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

	// ************ change the current node *****************

	public void clearmarks ()
	// clear all marks in the current node
	{	getinformation();
		undonode();
		ListElement la=Pos.node().actions(),lan;
		Action a;
		while (la!=null)
		{	a=(Action)la.content();
			lan=la.next();
//* "M" marrkaction(), "L": labelaction(), "MA":CROSS              //~@@@@I~
//			if (a.type().equals("M") || a.type().equals("L")       //~@@@@R~
 			if (false                                              //~@@@@I~
//				|| a.type().equals("MA") || a.type().equals("SQ")  //~@@@@R~
  				                         || a.type().equals("SQ")  //~@@@@I~
//				|| a.type().equals("SL") || a.type().equals("CR")  //~@@@@R~
  				                         || a.type().equals("CR")  //~@@@@I~
//  			|| a.type().equals("TR") || a.type().equals("LB")) //~@@@@R~
				|| a.type().equals("TR")                         )  //~@@@@I~
			{	Pos.node().removeaction(la);
			}
			la=lan;
		}
		setnode();
		showinformation();
		copy();
	}

	public void clearremovals ()
	// undo all removals in the current node
	{	if (Pos.haschildren()) return;
		getinformation();
		undonode();
		ListElement la=Pos.node().actions(),lan;
		Action a;
		while (la!=null)
		{	a=(Action)la.content();
			lan=la.next();
			if (a.type().equals("AB") || a.type().equals("AW")
				|| a.type().equals("AE"))
			{	Pos.node().removeaction(la);
			}
			la=lan;
		}
		setnode();
		showinformation();
		copy();
	}

	// *************** change the game tree ***********

//    public void insertnode ()                                    //~@@@@R~
//    // insert an empty node                                      //~@@@@R~
//    {   if (Pos.haschildren() && !GF.askInsert()) return;        //~@@@@R~
//        Node n=new Node(Pos.node().number());                    //~@@@@R~
//        Pos.insertchild(new TreeNode(n));                        //~@@@@R~
//        n.main(Pos);                                             //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        Pos=Pos.lastChild();                                     //~@@@@R~
//        setlast();                                               //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public void insertvariation ()                               //~@@@@R~
//    // insert an empty variation to the current                  //~@@@@R~
//    {   if (Pos.parentPos()==null) return;                       //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        int c=P.color();                                         //~@@@@R~
//        back();                                                  //~@@@@R~
//        Node n=new Node(2);                                      //~@@@@R~
//        Pos.addchild(new TreeNode(n));                           //~@@@@R~
//        n.main(Pos);                                             //~@@@@R~
//        Pos=Pos.lastChild();                                     //~@@@@R~
//        setlast();                                               //~@@@@R~
//        P.color(-c);                                             //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

//    public void undo (int n)                                     //~@@@@R~
//    // undo the n last moves, notify BoardInterface              //~@@@@R~
//    {   varmaindown();                                           //~@@@@R~
//        for (int i=0; i<n; i++)                                  //~@@@@R~
//        {   goback();                                            //~@@@@R~
//            Pos.removeall();                                     //~@@@@R~
//            showinformation();                                   //~@@@@R~
//            copy();                                              //~@@@@R~
//        }                                                        //~@@@@R~
//        GF.addComment(GF.resourceString("Undo"));                //~@@@@R~
//    }                                                            //~@@@@R~

	// ********** set board state ******************

	public void setblack ()
	// set black mode
	{	getinformation();
		State=3;
		showinformation();
	}

	public void setwhite ()
	// set white mode
	{	getinformation();
		State=4;
		showinformation();
	}

	public void black ()
	// black to play
	{	getinformation();
		State=1;
		P.color(1);
		showinformation();
	}

	public void white ()
	// white to play
	{	getinformation();
		State=2;
		P.color(-1);
		showinformation();
	}

	public void mark ()
	// marking
	{	getinformation();
		State=5;
		showinformation();
	}

	public void specialmark (int i)
	// marking
	{	getinformation();
		State=9;
		SpecialMarker=i;
		showinformation();
	}

	public void textmark (String s)
	// marking
	{	getinformation();
		State=10;
		TextMarker=s;
		showinformation();
	}

	public void letter ()
	// letter
	{	getinformation();
		State=6;
		showinformation();
	}

	public void deletestones ()
	// hide stones
	{	getinformation();
		State=7;
		showinformation();
	}

	public boolean score ()
	// board state is removing groups
	{	if (Pos.haschildren()) return false;
		getinformation();
//        State=8; Removing=true;                                  //~@@@@R~
        State=8;                                                   //~@@@@I~
		showinformation();
		if (Pos.node().main()) return true;
		else return false;
	}

//    synchronized public void setsize (int s)                     //~@@@@R~
//    // set the board size                                        //~@@@@R~
//    // clears the board !!!                                      //~@@@@R~
//    {   if (s<5 || s>59) return;                                 //~@@@@R~
//        S=s;                                                     //~@@@@R~
//        P=new Position(S);                                       //~@@@@R~
//        number=1;                                                //~@@@@R~
//        Node n=new Node(number);                                 //~@@@@R~
//        n.main(true);                                            //~@@@@R~
//        lasti=lastj=-1;                                          //~@@@@R~
//        T=new SGFTree(n);                                        //~@@@@R~
//        Trees.setElementAt(T,CurrentTree);                       //~@@@@R~
//        Pos=T.top();                                             //~@@@@R~
//        makeimages();                                            //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//    }                                                            //~@@@@R~

	// ******** set board information **********

	void setname (String s)
	// set the name of the node
    {   Pos.setaction("N",s,true);
        showinformation();
    }

    public void setinformation (String black, String blackrank,
		String white, String whiterank,
		String komi, String handicap)
	// set various things like names, rank etc.
	{	T.top().setaction("PB",black,true);
		T.top().setaction("BR",blackrank,true);
		T.top().setaction("PW",white,true);
		T.top().setaction("WR",whiterank,true);
		T.top().setaction("KM",komi,true);
		T.top().setaction("HA",handicap,true);
		T.top().setaction("GN",white+"-"+black,true);
		T.top().setaction("DT",new Date().toString());
	}

	// ************ get board information ******

	String getname ()
	// get node name
    {   return T.top().getaction("N");
    }

    public String getKomi ()
    // get Komi string
	{	return T.top().getaction("KM");
	}

	public String extraInformation ()
	// get a mixture from handicap, komi and prisoners
	{	StringBuffer b=new StringBuffer(GF.resourceString("_("));
		Node n=T.top().node();
		if (n.contains("HA"))
		{	b.append(GF.resourceString("Ha_")); b.append(n.getaction("HA"));
		}
		if (n.contains("KM"))
		{	b.append(GF.resourceString("__Ko")); b.append(n.getaction("KM"));
		}
		b.append(GF.resourceString("__B")); b.append(""+Pw);
		b.append(GF.resourceString("__W")); b.append(""+Pb);
		b.append(GF.resourceString("_)"));
		return b.toString();
	}

	// ***************** several other things ******

//    public void print (Frame f)                                  //~@@@@R~
//    // print the board                                           //~@@@@R~
//    {   Position p=new Position(P);                              //~@@@@R~
//        PrintBoard PB=new PrintBoard(p,Range,f);                 //~@@@@R~
//    }                                                            //~@@@@R~

	public void lastrange (int n)
	// set the range for stone numbers
	{	int l=(Pos.node().number())-2;
		Range=(l/n)*n;
		if (Range<0) Range=0;
		KeepRange=true; updateall(); copy(); KeepRange=false;
	}

//    public void addcomment (String s)                            //~@@@@R~
//    // add a string to the comments, notifies comment area       //~@@@@R~
//    {   TreeNode p=T.top();                                      //~@@@@R~
//        while (p.haschildren()) p=p.firstChild();                //~@@@@R~
//        if (Pos==p) getinformation();                            //~@@@@R~
//        ListElement la=p.node().actions();                       //~@@@@R~
//        Action a;                                                //~@@@@R~
//        String Added="";                                         //~@@@@R~
//        ListElement larg;                                        //~@@@@R~
//        outer: while (true)                                      //~@@@@R~
//        {   while (la!=null)                                     //~@@@@R~
//            {   a=(Action)la.content();                          //~@@@@R~
//                if (a.type().equals("C"))                        //~@@@@R~
//                {   larg=a.arguments();                          //~@@@@R~
//                    if (((String)larg.content()).equals(""))     //~@@@@R~
//                    {   larg.content(s);                         //~@@@@R~
//                        Added=s;                                 //~@@@@R~
//                    }                                            //~@@@@R~
//                    else                                         //~@@@@R~
//                    {   larg.content((String)larg.content()+"\n"+s);//~@@@@R~
//                        Added="\n"+s;                            //~@@@@R~
//                    }                                            //~@@@@R~
//                    break outer;                                 //~@@@@R~
//                }                                                //~@@@@R~
//                la=la.next();                                    //~@@@@R~
//            }                                                    //~@@@@R~
//            p.addaction(new Action("C",s));                      //~@@@@R~
//            break;                                               //~@@@@R~
//        }                                                        //~@@@@R~
//        if (Pos==p)                                              //~@@@@R~
//        {   GF.appendComment(Added);                             //~@@@@R~
//            showinformation();                                   //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

	public String done ()
	// count territory and return result string
	// notifies BoardInterface
	{	if (Pos.haschildren()) return null;
		clearmarks();
		getinformation();
		int i,j;
		int tb=0,tw=0,sb=0,sw=0;
		P.getterritory();
		for (i=0; i<S; i++)
			for (j=0; j<S; j++)
			{	if (P.territory(i,j)==1 || P.territory(i,j)==-1)
				{	markterritory(i,j,P.territory(i,j));
					if (P.territory(i,j)>0) tb++;
					else tw++;
				}
				else
				{	if (P.color(i,j)>0) sb++;
					else if (P.color(i,j)<0) sw++;
				}
			}
		String s=GF.resourceString("Chinese_count_")+"\n"+
			GF.resourceString("Black__")+(sb+tb)+
			GF.resourceString("__White__")+(sw+tw)+"\n"+
			GF.resourceString("Japanese_count_")+"\n"+
			GF.resourceString("Black__")+(Pw+tb)+
			GF.resourceString("__White__")+(Pb+tw);
		showinformation();
		copy();
		if (Pos.node().main())
		{	GF.result(tb,tw);
		}
		return s;
	}

	public String docount ()
	// maka a local count and return result string
	{	clearmarks();
		getinformation();
		int i,j;
		int tb=0,tw=0,sb=0,sw=0;
		P.getterritory();
		for (i=0; i<S; i++)
			for (j=0; j<S; j++)
			{	if (P.territory(i,j)==1 || P.territory(i,j)==-1)
				{	markterritory(i,j,P.territory(i,j));
					if (P.territory(i,j)>0) tb++;
					else tw++;
				}
				else
				{	if (P.color(i,j)>0) sb++;
					else if (P.color(i,j)<0) sw++;
				}
			}
		showinformation();
		copy();
		return GF.resourceString("Chinese_count_")+"\n"+
			GF.resourceString("Black__")+(sb+tb)+
			GF.resourceString("__White__")+(sw+tw)+"\n"+
			GF.resourceString("Japanese_count_")+"\n"+
			GF.resourceString("Black__")+(Pw+tb)+
			GF.resourceString("__White__")+(Pb+tw);
	}

//    public void load (BufferedReader in) throws IOException      //~@@@@R~
//    // load a game from the stream                               //~@@@@R~
//    {   Vector v=SGFTree.load(in,GF);                            //~@@@@R~
//        synchronized (this)                                      //~@@@@R~
//        {   if (v.size()==0) return;                             //~@@@@R~
//            showlast=false;                                      //~@@@@R~
//            update(lasti,lastj);                                 //~@@@@R~
//            showlast=true;                                       //~@@@@R~
//            lasti=lastj=-1;                                      //~@@@@R~
//            newtree();                                           //~@@@@R~
//            Trees=v;                                             //~@@@@R~
//            T=(SGFTree)v.elementAt(0);                           //~@@@@R~
//            CurrentTree=0;                                       //~@@@@R~
//            resettree();                                         //~@@@@R~
//            setnode();                                           //~@@@@R~
//            showinformation();                                   //~@@@@R~
//            copy();                                              //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    public void loadXml (XmlReader xml)                          //~@@@@R~
//        throws XmlReaderException                                //~@@@@R~
//    // load a game from the stream                               //~@@@@R~
//    {   Vector v=SGFTree.load(xml,GF);                           //~@@@@R~
//        synchronized (this)                                      //~@@@@R~
//        {   if (v.size()==0) return;                             //~@@@@R~
//            showlast=false;                                      //~@@@@R~
//            update(lasti,lastj);                                 //~@@@@R~
//            showlast=true;                                       //~@@@@R~
//            lasti=lastj=-1;                                      //~@@@@R~
//            newtree();                                           //~@@@@R~
//            Trees=v;                                             //~@@@@R~
//            T=(SGFTree)v.elementAt(0);                           //~@@@@R~
//            CurrentTree=0;                                       //~@@@@R~
//            resettree();                                         //~@@@@R~
//            setnode();                                           //~@@@@R~
//            showinformation();                                   //~@@@@R~
//            copy();                                              //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    public void save (PrintWriter o)                             //~@@@@R~
//    // saves the file to the specified print stream              //~@@@@R~
//    // in SGF                                                    //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        T.top().setaction("AP","Jago:"+GF.version(),true);       //~@@@@R~
//        T.top().setaction("SZ",""+S,true);                       //~@@@@R~
//        T.top().setaction("GM","1",true);                        //~@@@@R~
//        T.top().setaction("FF",                                  //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        for (int i=0; i<Trees.size(); i++)                       //~@@@@R~
//            ((SGFTree)Trees.elementAt(i)).print(o);              //~@@@@R~
//    }                                                            //~@@@@R~
	
//    public void savePos (PrintWriter o)                          //~@@@@R~
//    // saves the file to the specified print stream              //~@@@@R~
//    // in SGF                                                    //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        Node n=new Node(0);                                      //~@@@@R~
//        positionToNode(n);                                       //~@@@@R~
//        o.println("(");                                          //~@@@@R~
//        n.print(o);                                              //~@@@@R~
//        o.println(")");                                          //~@@@@R~
//    }                                                            //~@@@@R~

//    public void saveXML (PrintWriter o, String encoding)         //~@@@@R~
//    // save the file in Jago's XML format                        //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        T.top().setaction("AP","Jago:"+GF.version(),true);       //~@@@@R~
//        T.top().setaction("SZ",""+S,true);                       //~@@@@R~
//        T.top().setaction("GM","1",true);                        //~@@@@R~
//        T.top().setaction("FF",                                  //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        XmlWriter xml=new XmlWriter(o);                          //~@@@@R~
//        xml.printEncoding(encoding);                             //~@@@@R~
//        xml.printXls("go.xsl");                                  //~@@@@R~
//        xml.printDoctype("Go","go.dtd");                         //~@@@@R~
//        xml.startTagNewLine("Go");                               //~@@@@R~
//        for (int i=0; i<Trees.size(); i++)                       //~@@@@R~
//        {   ((SGFTree)Trees.elementAt(i)).printXML(xml);         //~@@@@R~
//        }                                                        //~@@@@R~
//        xml.endTagNewLine("Go");                                 //~@@@@R~
//    }                                                            //~@@@@R~

//    public void saveXMLPos (PrintWriter o, String encoding)      //~@@@@R~
//    // save the file in Jago's XML format                        //~@@@@R~
//    {   getinformation();                                        //~@@@@R~
//        T.top().setaction("AP","Jago:"+GF.version(),true);       //~@@@@R~
//        T.top().setaction("SZ",""+S,true);                       //~@@@@R~
//        T.top().setaction("GM","1",true);                        //~@@@@R~
//        T.top().setaction("FF",                                  //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        XmlWriter xml=new XmlWriter(o);                          //~@@@@R~
//        xml.printEncoding(encoding);                             //~@@@@R~
//        xml.printXls("go.xsl");                                  //~@@@@R~
//        xml.printDoctype("Go","go.dtd");                         //~@@@@R~
//        xml.startTagNewLine("Go");                               //~@@@@R~
//        Node n=new Node(0);                                      //~@@@@R~
//        positionToNode(n);                                       //~@@@@R~
//        SGFTree t=new SGFTree(n);                                //~@@@@R~
//        t.printXML(xml);                                         //~@@@@R~
//        xml.endTagNewLine("Go");                                 //~@@@@R~
//    }                                                            //~@@@@R~

//    public void asciisave (PrintWriter o)                        //~@@@@R~
//    // an ASCII image of the board.                              //~@@@@R~
//    {   int i,j;                                                 //~@@@@R~
//        o.println(T.top().getaction("GN"));                      //~@@@@R~
//        o.print("      ");                                       //~@@@@R~
//        for (i=0; i<S; i++)                                      //~@@@@R~
//        {   char a;                                              //~@@@@R~
//            if (i<=7) a=(char)('A'+i);                           //~@@@@R~
//            else a=(char)('A'+i+1);                              //~@@@@R~
//            o.print(" "+a);                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        o.println();                                             //~@@@@R~
//        o.print("      ");                                       //~@@@@R~
//        for (i=0; i<S; i++) o.print("--");                       //~@@@@R~
//        o.println("-");                                          //~@@@@R~
//        for (i=0; i<S; i++)                                      //~@@@@R~
//        {   o.print("  ");                                       //~@@@@R~
//            if (S-i<10) o.print(" "+(S-i));                      //~@@@@R~
//            else o.print(S-i);                                   //~@@@@R~
//            o.print(" |");                                       //~@@@@R~
//            for (j=0; j<S; j++)                                  //~@@@@R~
//            {   switch (P.color(j,i))                            //~@@@@R~
//                {   case 1 : o.print(" #"); break;               //~@@@@R~
//                    case -1 : o.print(" O"); break;              //~@@@@R~
//                    case 0 :                                     //~@@@@R~
//                        if (P.haslabel(j,i)) o.print(" "+P.label(j,i));//~@@@@R~
//                        else if (P.letter(j,i)>0) o.print(" "+(char)(P.letter(j,i)+'a'-1));//~@@@@R~
//                        else if (P.marker(j,i)>0) o.print(" X"); //~@@@@R~
//                        else if (ishand(i) && ishand(j)) o.print(" ,");//~@@@@R~
//                        else o.print(" .");                      //~@@@@R~
//                        break;                                   //~@@@@R~
//                }                                                //~@@@@R~
//            }                                                    //~@@@@R~
//            o.print(" | ");                                      //~@@@@R~
//            if (S-i<10) o.print(" "+(S-i));                      //~@@@@R~
//            else o.print(S-i);                                   //~@@@@R~
//            o.println();                                         //~@@@@R~
//        }                                                        //~@@@@R~
//        o.print("      ");                                       //~@@@@R~
//        for (i=0; i<S; i++) o.print("--");                       //~@@@@R~
//        o.println("-");                                          //~@@@@R~
//        o.print("      ");                                       //~@@@@R~
//        for (i=0; i<S; i++)                                      //~@@@@R~
//        {   char a;                                              //~@@@@R~
//            if (i<=7) a=(char)('A'+i);                           //~@@@@R~
//            else a=(char)('A'+i+1);                              //~@@@@R~
//            o.print(" "+a);                                      //~@@@@R~
//        }                                                        //~@@@@R~
//        o.println();                                             //~@@@@R~
//    }                                                            //~@@@@R~

//    public void positionToNode (Node n)                          //~@@@@R~
//    // copy the current position to a node.                      //~@@@@R~
//    {   n.setaction("AP","Jago:"+GF.version(),true);             //~@@@@R~
//        n.setaction("SZ",""+S,true);                             //~@@@@R~
//        n.setaction("GM","1",true);                              //~@@@@R~
//        n.setaction("FF",                                        //~@@@@R~
//            GF.getParameter("puresgf",false)?"4":"1",true);      //~@@@@R~
//        n.copyAction(T.top().node(),"GN");                       //~@@@@R~
//        n.copyAction(T.top().node(),"DT");                       //~@@@@R~
//        n.copyAction(T.top().node(),"PB");                       //~@@@@R~
//        n.copyAction(T.top().node(),"BR");                       //~@@@@R~
//        n.copyAction(T.top().node(),"PW");                       //~@@@@R~
//        n.copyAction(T.top().node(),"WR");                       //~@@@@R~
//        n.copyAction(T.top().node(),"PW");                       //~@@@@R~
//        n.copyAction(T.top().node(),"US");                       //~@@@@R~
//        n.copyAction(T.top().node(),"CP");                       //~@@@@R~
//        int i,j;                                                 //~@@@@R~
//        for (i=0; i<S; i++)                                      //~@@@@R~
//        {   for (j=0; j<S; j++)                                  //~@@@@R~
//            {   String field=Field.string(i,j);                  //~@@@@R~
//                switch (P.color(i,j))                            //~@@@@R~
//                {   case 1 : n.expandaction(new Action("AB",field)); break;//~@@@@R~
//                    case -1 : n.expandaction(new Action("AW",field)); break;//~@@@@R~
//                }                                                //~@@@@R~
//                if (P.marker(i,j)>0)                             //~@@@@R~
//                {   switch (P.marker(i,j))                       //~@@@@R~
//                    {   case Field.SQUARE :                      //~@@@@R~
//                            n.expandaction(new Action("SQ",field));//~@@@@R~
//                            break;                               //~@@@@R~
//                        case Field.TRIANGLE :                    //~@@@@R~
//                            n.expandaction(new Action("TR",field));//~@@@@R~
//                            break;                               //~@@@@R~
//                        case Field.CIRCLE :                      //~@@@@R~
//                            n.expandaction(new Action("CR",field));//~@@@@R~
//                            break;                               //~@@@@R~
//                        default:                                 //~@@@@R~
//                            n.expandaction(new MarkAction(field,GF));//~@@@@R~
//                    }                                            //~@@@@R~
//                }                                                //~@@@@R~
//                else if (P.haslabel(i,j))                        //~@@@@R~
//                    n.expandaction(new Action("LB",field+":"+P.label(i,j)));//~@@@@R~
//                else if (P.letter(i,j)>0)                        //~@@@@R~
//                    n.expandaction(new Action("LB",field+":"+P.letter(i,j)));//~@@@@R~
//            }                                                    //~@@@@R~
//        }                                                        //~@@@@R~
//    }                                                            //~@@@@R~

//    boolean ishand (int i)                                       //~@@@@R~
//    {   if (S>13)                                                //~@@@@R~
//        {   return (i==3 || i==S-4 || i==S/2);                   //~@@@@R~
//        }                                                        //~@@@@R~
//        else if (S>9)                                            //~@@@@R~
//        {   return (i==3 || i==S-4);                             //~@@@@R~
//        }                                                        //~@@@@R~
//        else return false;                                       //~@@@@R~
//    }                                                            //~@@@@R~

	public void handicap (int n)
	// set number of handicap points
	{	int h=(S<13)?3:4;
		if (n>5)
		{	setblack(h-1,S/2); setblack(S-h,S/2);
		}
		if (n>7)
		{	setblack(S/2,h-1); setblack(S/2,S-h);
		}
		switch (n)
		{	case 9 :
			case 7 :
			case 5 :
				setblack(S/2,S/2);
			case 8 :
			case 6 :
			case 4 :
				setblack(S-h,S-h);
			case 3 :
				setblack(h-1,h-1);
			case 2 :
				setblack(h-1,S-h);
			case 1 :
				setblack(S-h,h-1);
		}
		MainColor=-1;
	}
//**************************************************************** //~@@@@I~
//*put initial piece Arangement                                    //~@@@@I~
//**************************************************************** //~@@@@I~
    public void putInitialPiece(int Pcolor,int Pbishop,int Pknight,int Pgameover,int Pgameover2,int Pgameoptions)	//initialy put pieaces//~@@@@R~
    {                                                              //~@@@@I~
    	int piece;                                                 //~@@@@I~
        int maincolor;                                             //~@@@@I~
    //***********************                                      //~@@@@I~
        maincolor=MainColor;//setblack/setwhite changes MainColor  //~@@@@I~
    	YourColor=Pcolor;                                          //~@@@@I~
    	Bishop=Pbishop; Knight=Pknight;                            //~@@@@R~
        if (Pgameover>=0)                                          //~@@@@I~
        	Gameover=Pgameover;                                    //~@@@@I~
        if (Pgameover2>=0)                                         //~@@@@I~
        	Gameover2=Pgameover2;                                  //~@@@@I~
        if (Pgameoptions>=0)                                       //~@@@@I~
        	GameOptions=Pgameoptions;                              //~@@@@I~
        int boardsz=S;                                             //~@@@@R~
        int posbishop=Bishop>>8;                                   //~@@@@I~
        int posknight=Knight>>8;                                   //~@@@@I~
        for (int ii=0;ii<boardsz;ii++)                             //~@@@@I~
        {                                                          //~@@@@I~
        	if ((posbishop & (1<<ii))!=0)                          //~@@@@M~
            	piece=Field.BISHOP;                                //~@@@@I~
            else                                                   //~@@@@M~
        	if ((posknight & (1<<ii))!=0)                          //~@@@@M~
            	piece=Field.KNIGHT;                                //~@@@@I~
            else                                                   //~@@@@I~
            	piece=Field.PAWN;                                  //~@@@@I~
            if (Pcolor>0)	//YourColor:black                      //~@@@@R~
            {                                                      //~@@@@I~
				setwhite(boardsz-ii-1,0,piece);  //upper white     //~@@@@I~
    			setblack(ii,boardsz-1,piece);    //lower black     //~@@@@R~
            }                                                      //~@@@@I~
            else                                                   //~@@@@I~
            {                                                      //~@@@@I~
				setblack(boardsz-ii-1,0,piece);  //upper black     //~@@@@R~
    			setwhite(ii,boardsz-1,piece);    //lower white     //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        MainColor=maincolor;                                       //~@@@@I~
        return;                                             //~@@@@I~
    }                                                              //~@@@@R~
//**************************************************************** //~1Aa7I~
//*put initial piece Arangement for pos changed case               //~1Aa7R~
//**************************************************************** //~1Aa7I~
//  public void putInitialPiece(int Pcolor,int Pbishop,int Pknight,int Pgameover,int Pgameover2,int Pgameoptions)	//initialy put pieaces//~1Aa7I~
    public void putInitialPiece(int Pcolor,int Pbishop,int Pknight,int Pgameover,int Pgameover2,int Pgameoptions,	//initialy put pieaces//~1Aa7I~
    							int PaccepterBishop,int PaccepterKnight)//~1Aa7I~
    {                                                              //~1Aa7I~
    	int piece;                                                 //~1Aa7I~
        int maincolor;                                             //~1Aa7I~
    //***********************                                      //~1Aa7I~
        maincolor=MainColor;//setblack/setwhite changes MainColor  //~1Aa7I~
    	YourColor=Pcolor;                                          //~1Aa7I~
    	Bishop=Pbishop; Knight=Pknight;                            //~1Aa7I~
        if (Pgameover>=0)                                          //~1Aa7I~
        	Gameover=Pgameover;                                    //~1Aa7I~
        if (Pgameover2>=0)                                         //~1Aa7I~
        	Gameover2=Pgameover2;                                  //~1Aa7I~
        if (Pgameoptions>=0)                                       //~1Aa7I~
        	GameOptions=Pgameoptions;                              //~1Aa7I~
        int boardsz=S;                                             //~1Aa7I~
//      int posbishop=Bishop>>8;                                   //~1Aa7I~
//      int posknight=Knight>>8;                                   //~1Aa7I~
        int posbishop,posknight;                                   //~1Aa7I~
//set my piece(lower row)                                          //~1Aa7I~
        if ((Pgameoptions & GameQuestion.GAMEOPT_LOCALGAME)!=0)    //~1AabI~
        {                                                          //~1AabI~
        	posbishop=Bishop>>8;                                   //~1AabI~
        	posknight=Knight>>8;                                   //~1AabI~
        }                                                          //~1AabI~
        else                                                       //~1AabI~
        if ((Pgameoptions & GameQuestion.GAMEOPT_GAMEREQUESTER)!=0)//~1Aa7I~
        {                                                          //~1Aa7I~
        	posbishop=Bishop>>8;                                   //~1Aa7I~
        	posknight=Knight>>8;                                   //~1Aa7I~
        }                                                          //~1Aa7I~
        else                                                       //~1Aa7I~
        {                                                          //~1Aa7I~
        	posbishop=PaccepterBishop>>8;                          //~1Aa7I~
        	posknight=PaccepterKnight>>8;                          //~1Aa7I~
        }                                                          //~1Aa7I~
        for (int ii=0;ii<boardsz;ii++)                             //~1Aa7I~
        {                                                          //~1Aa7I~
        	if ((posbishop & (1<<ii))!=0)                          //~1Aa7I~
            	piece=Field.BISHOP;                                //~1Aa7I~
            else                                                   //~1Aa7I~
        	if ((posknight & (1<<ii))!=0)                          //~1Aa7I~
            	piece=Field.KNIGHT;                                //~1Aa7I~
            else                                                   //~1Aa7I~
            	piece=Field.PAWN;                                  //~1Aa7I~
            if (Pcolor>0)	//YourColor:black                      //~1Aa7I~
            {                                                      //~1Aa7I~
//  			setwhite(boardsz-ii-1,0,piece);  //upper white     //~1AabI~
    			setblack(ii,boardsz-1,piece);    //lower black     //~1AabI~
            }                                                      //~1Aa7I~
            else                                                   //~1Aa7I~
            {                                                      //~1Aa7I~
//  			setblack(boardsz-ii-1,0,piece);  //upper black     //~1Aa7I~//~1AabR~
    			setwhite(ii,boardsz-1,piece);    //lower white     //~1Aa7I~//~1AabR~
            }                                                      //~1Aa7I~
        }                                                          //~1Aa7I~
//set opponent piece(upper row)                                    //~1Aa7I~
        if ((Pgameoptions & GameQuestion.GAMEOPT_LOCALGAME)!=0)    //~1AabI~
        {                                                          //~1AabI~
        	posbishop=PaccepterBishop>>8;                          //~1AabI~
        	posknight=PaccepterKnight>>8;                          //~1AabI~
        }                                                          //~1AabI~
        else                                                       //~1AabI~
        if ((Pgameoptions & GameQuestion.GAMEOPT_GAMEREQUESTER)==0)//~1Aa7I~
        {                                                          //~1Aa7I~
        	posbishop=Bishop>>8;                                   //~1Aa7I~
        	posknight=Knight>>8;                                   //~1Aa7I~
        }                                                          //~1Aa7I~
        else                                                       //~1Aa7I~
        {                                                          //~1Aa7I~
        	posbishop=PaccepterBishop>>8;                          //~1Aa7I~
        	posknight=PaccepterKnight>>8;                          //~1Aa7I~
        }                                                          //~1Aa7I~
        for (int ii=0;ii<boardsz;ii++)                             //~1Aa7I~
        {                                                          //~1Aa7I~
        	if ((posbishop & (1<<ii))!=0)                          //~1Aa7I~
            	piece=Field.BISHOP;                                //~1Aa7I~
            else                                                   //~1Aa7I~
        	if ((posknight & (1<<ii))!=0)                          //~1Aa7I~
            	piece=Field.KNIGHT;                                //~1Aa7I~
            else                                                   //~1Aa7I~
            	piece=Field.PAWN;                                  //~1Aa7I~
            if (Pcolor>0)	//YourColor:black                      //~1Aa7I~
            {                                                      //~1Aa7I~
    			setwhite(boardsz-ii-1,0,piece);  //upper white     //~1AabI~
//  			setblack(ii,boardsz-1,piece);    //lower black     //~1AabI~
            }                                                      //~1Aa7I~
            else                                                   //~1Aa7I~
            {                                                      //~1Aa7I~
    			setblack(boardsz-ii-1,0,piece);  //upper black     //~1AabI~
//  			setwhite(ii,boardsz-1,piece);    //lower white     //~1AabI~
            }                                                      //~1Aa7I~
        }                                                          //~1Aa7I~
        MainColor=maincolor;                                       //~1Aa7I~
        return;                                                    //~1Aa7I~
    }                                                              //~1Aa7I~
//**************************************************************** //~@@@@I~
	public void updateall ()
	// update all of the board
	{	if (ActiveImage==null) return;
		synchronized (this)
		{	ActiveImage.getGraphics().drawImage(Empty,0,0,this);
		}
      if (!swEmpty || !isEmpty())	//@@@@ performance tuning      //~1422R~
      {                                                            //~1313I~
		int i,j;
		for (i=0; i<S; i++)
			for (j=0; j<S; j++)
				update(i,j);
      }                                                            //~1313I~
		showinformation();
	}

	public void updateboard ()
	// redraw the board and its background
	{                                                              //~1302R~
        if (Dump.Y) Dump.println("updateboard entry");             //~1506R~
        swUpdateBoard=true;                                        //~1420I~
        repaint();  //call paint() on SubThread through Canvas     //~1422R~
    }                                                              //~1420I~
	public void doUpdateboard()                                    //~1420R~
    {                                                              //~1420I~
        if (Dump.Y) Dump.println("doUpdateboard entry");           //~1506R~
//@@@@  BlackStone=WhiteStone=null; //live until makeimage set new to avoid NPE//~1420R~
//@@@@  EmptyShadow=null;           //make image set new           //~1420R~
		setfonts();
//      makeimages();               //executed at paint()          //~1420R~//~1A01R~
        makeimages(true/*repaint()*/);               //executed at paint()//~1A01I~
//@@@@  updateall();                //will be done at makeimage    //~1422R~
        copy();						//callback paint() through Canvas//~1422R~
        swUpdateBoard=false;                                       //~1420I~
        if (Dump.Y) Dump.println("doUpdateboard end");             //~1506R~
	}
//****************************************************             //~1306I~
	/**
	Search the string as substring of a comment,
	go to that node and report success. On failure
	this routine will go up to the root node.
	*/
//    public boolean search (String s)                             //~@@@@R~
//    {   State=1;                                                 //~@@@@R~
//        getinformation();                                        //~@@@@R~
//        TreeNode pos=Pos;                                        //~@@@@R~
//        boolean found=true;                                      //~@@@@R~
//        outer: while (Pos.node().getaction("C").indexOf(s)<0 || Pos==pos)//~@@@@R~
//        {   if (!Pos.haschildren())                              //~@@@@R~
//            {   while (!hasvariation())                          //~@@@@R~
//                {   if (Pos.parent()==null) { found=false; break outer; }//~@@@@R~
//                    else goback();                               //~@@@@R~
//                }                                                //~@@@@R~
//                tovarright();                                    //~@@@@R~
//            }                                                    //~@@@@R~
//            else goforward();                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        showinformation();                                       //~@@@@R~
//        copy();                                                  //~@@@@R~
//        return found;                                            //~@@@@R~
//    }                                                            //~@@@@R~
	
	Image getBoardImage ()
	{	return ActiveImage;
	}
	
	Dimension getBoardImageSize ()
	{	return new Dimension(ActiveImage.getWidth(this),ActiveImage.getHeight(this));
	}

	//*****************************************************
	// procedures that might be overloaded for more control
	// (Callback to server etc.)
	//*****************************************************

	void movemouse (int i, int j)
	// set a move at i,j
	{	if (Pos.haschildren()) return;
//        if (captured==1 && capturei==i && capturej==j &&         //~@@@@R~
//            GF.getParameter("preventko",true)) return;           //~@@@@R~
		set(i,j); //try to set a new move
	}

	void setmouse (int i, int j, int c)
	// set a stone at i,j with specified color
	{	set(i,j,c);
		undonode(); setnode();
		showinformation();
	}

	void setmousec (int i, int j, int c)
	// set a stone at i,j with specified color
	{	setc(i,j,c);
		undonode(); setnode();
		showinformation();
	}

	void deletemouse (int i, int j)
	// delete a stone at i,j
	{	if (Pos.haschildren()) return;
		deletemousec(i,j);
	}

	void deletemousec (int i, int j)
	// delete a stone at i,j
	{	delete(i,j);
		undonode(); setnode();
		showinformation();
	}
//***********************************************************      //~@@@@I~
//*avoid duplicated showinformation                                //~@@@@I~
//***********************************************************      //~@@@@I~
	void deletemousecPiece (int i, int j)                          //~@@@@I~
	// delete a stone at i,j                                       //~@@@@I~
	{	delete(i,j);                                               //~@@@@I~
		undonode(); setnode();                                     //~@@@@I~
	}                                                              //~@@@@I~

//    void removemouse (int i, int j)                              //~@@@@R~
//    // remove a group at i,j                                     //~@@@@R~
//    {   if (Pos.haschildren()) return;                           //~@@@@R~
//        removegroup(i,j);                                        //~@@@@R~
//        undonode(); setnode();                                   //~@@@@R~
//        showinformation();                                       //~@@@@R~
//    }                                                            //~@@@@R~
	
	void setVariationStyle (boolean hide, boolean current)
	{	undonode();
		VHide=hide; VCurrent=current;
		setnode();
		updateall();
		copy();
	}
//**************************************************************** //~1422I~
//*From Board:for performance;avoid update if initial status       //~1422I~
//**************************************************************** //~1422I~
	private boolean isEmpty()                                      //~1422I~
	{                                                              //~1422I~
		int i,j;                                                   //~1422I~
		for (i=0; i<S; i++)                                        //~1422I~
        {                                                          //~1422I~
			for (j=0; j<S; j++)                                    //~1422I~
				if (P.color(i,j)!=0)                         //~1422I~
                	return false;                                  //~1422I~
        }                                                          //~1422I~
        return true;                                               //~1422I~
	}                                                              //~1422I~
//**************************************************************** //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@I~
//*draw piece(remains Board.java for MainFrame)                                                      //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@I~
//*piece:0:pawn,1:bishop,2:knight                                  //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
//**************************************************************** //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
	protected void drawPiece(Graphics Pg,int Pcolor,int Ppiece,int i,int j)//~@@@@I~//~@@@2M~//~@@@@I~//~@@@2I~//~@@@@M~
	{                                                              //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
    	int colidx;                                                //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2I~//~@@@@M~
    //*******************                                          //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
        if (Dump.Y) Dump.println("drawPiece i="+i+",j="+j+",Grafics="+Pg.toString());//~@@@@I~//~@@@2M~//~@@@@I~//~@@@2I~//~@@@@M~
		int xi=O+OTU+i*D;                                          //~@@@2I~//~@@@@M~
		int xj=O+OTU+j*D;                                          //~@@@2I~//~@@@@M~
        if (YourColor>0)	//black                                //~@@@@R~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
	        colidx=Pcolor>0?0:3;    //black or white-down          //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
        else                                                       //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
	        colidx=Pcolor>0?2:1;    //black-down or white          //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
        Image piece=SpieceImage[colidx][Ppiece];                   //~@@@@R~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
        Pg.drawImage(piece,xi+PIECE_MARGIN+1,xj+PIECE_MARGIN+1,this);//~@@@@R~//~@@@2M~//~@@@@I~//~@@@2I~//~@@@@M~
        if (Dump.Y) Dump.println("drawPiece end xi="+xi+",xj="+xj);  //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2I~//~@@@@M~
        if (P.captured(i,j))                                       //~@@@2I~//~@@@@M~
        	drawCapturedPieceMark(Pg,i,j);                         //~@@@2I~//~@@@@M~
	}                                                              //~@@@@I~//~@@@2M~//~@@@@I~//~@@@2M~//~@@@@M~
//**************************************************************** //~@@@@I~
	private void drawCoordinate(Graphics Pg)                       //~@@@@I~
    {                                                              //~@@@@I~
        int hx=O+OTU,hy=O+OTU,vx=O+OTU-OP,vy=O+OTU,reverse,asc,desc,fontsz;//~@@@@R~
        String labelH,labelV;                                      //~@@@@I~
    //*******************************************:                 //~@@@@I~
    	if ((AG.Options & AG.OPTIONS_COORDINATE)==0)               //~@@@@I~
        	return;                                                //~@@@@I~
        asc=fontmetrics.getAscent();//=fontsize                    //~@@@@R~
        desc=fontmetrics.getDescent();                             //~@@@@I~
        fontsz=(OP*asc)/(asc+desc);                                //~@@@@R~
		setfonts(fontsz);	//change font size                     //~@@@@I~
        asc=fontmetrics.getAscent();	//new font	               //~@@@@I~
        desc=fontmetrics.getDescent();                             //~@@@@I~
        boolean reversecoord=false;                                //~@@@@I~
        if (!swChessBoard)                                         //~@@@@R~
        {                                                          //~@@@@I~
            labelH=AG.SshogiH;                                     //~@@@@I~
            labelV=AG.SshogiV;                                     //~@@@@I~
            if (YourColor>0)    //Black:upper(9-1) and right(kanji-1-9)//~@@@@I~
            {                                                      //~@@@@I~
                vx+=D*S+OP;                                        //~@@@@R~
            }                                                      //~@@@@I~
            else                //White lower(1-9) and left(kanji-9-1)//~@@@@I~
            {                                                      //~@@@@I~
                hy+=D*S+OP;                                        //~@@@@R~
                reversecoord=true;                                 //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
            labelH=AG.SchessH;                                     //~@@@@I~
            labelV=AG.SchessV;                                     //~@@@@I~
            if (YourColor>0)    //Black:upper(h-a) and right(1-8)  //~@@@@I~
            {                                                      //~@@@@I~
                vx+=D*S+OP;                                        //~@@@@R~
                reversecoord=true;                                 //~@@@@M~
            }                                                      //~@@@@I~
            else                //White lower(a-h) and left(8-1)   //~@@@@I~
            {                                                      //~@@@@I~
                hy+=D*S+OP;                                        //~@@@@R~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
            Pg.setFont(font);                                       //~@@@@I~
            int h=asc;                                             //~@@@@R~
            String s;                                              //~@@@@I~
        //*vertical                                                //~@@@@I~
        	h=(asc-desc)/2;                                        //~@@@@I~
            for (int i=0; i<S; i++)                                    //~@@@@I~
            {                                                      //~@@@@I~
                if (reversecoord)                                  //~@@@@I~
                    s=labelV.substring(S-i-1,S-i);                 //~@@@@R~
                else                                               //~@@@@I~
                    s=labelV.substring(i,i+1);                     //~@@@@R~
                int w=fontmetrics.stringWidth(s)/2;                //~@@@@I~
                Pg.drawString(s,vx+OP/2-w,vy+D/2+h);               //~@@@@R~
            	if (Dump.Y) Dump.println("coordinate V s="+s+",w="+w+",x="+(vx+OP-w)+",y="+(vy+D/2+h));//~@@@@I~
                vy+=D;                                             //~@@@@I~
            }                                                      //~@@@@I~
        //*horizontal                                              //~@@@@I~
        	h=-OP/2+(asc-desc)/2;                                  //~@@@@R~
            int hg=h-desc/2;                                       //~@@@@R~
//            int hb=h+desc/2;                                     //~@@@@R~
            for (int i=0; i<S; i++)                                    //~@@@@I~
            {                                                      //~@@@@I~
                if (reversecoord)                                  //~@@@@I~
                    s=labelH.substring(S-i-1,S-i);                 //~@@@@R~
                else                                               //~@@@@I~
                    s=labelH.substring(i,i+1);                     //~@@@@R~
                int w=fontmetrics.stringWidth(s)/2;                //~@@@@I~
                if (s.equals("g"))                                 //~@@@@I~
	                Pg.drawString(s,hx+D/2-w,hy+hg);               //~@@@@I~
//                else                                             //~@@@@R~
//                if (s.equals("b")||                              //~@@@@R~
//                    s.equals("d")||                              //~@@@@R~
//                    s.equals("f")||                              //~@@@@R~
//                    s.equals("h"))                               //~@@@@R~
//                    Pg.drawString(s,hx+D/2-w,hy+hb);             //~@@@@R~
                else                                               //~@@@@I~
    	            Pg.drawString(s,hx+D/2-w,hy+h);                //~@@@@R~
            	if (Dump.Y) Dump.println("coordinate H s="+s+",w="+w+",x="+(hx+D/2-w)+",y="+(hy+h));//~@@@@I~
                hx+=D;                                             //~@@@@I~
            }                                                      //~@@@@I~
    }                                                              //~@@@@I~
//**************************************************************** //~@@@@I~
	public int currentColor()//for TimedGoFrame                                  //~@@@@I~
	{                                                              //~@@@@I~
    	return P.color();                                          //~@@@@I~
	}                                                              //~@@@@I~
//**************************************************************** //~@@@@I~
    void pieceMoved(int Pi,int Pj)                                 //~@@@@I~
    {                                                              //~@@@@I~
        moveNumber++;                                              //~@@@@M~
  		resetMovedFrom();	//clear mark of last moved from        //~1A35I~
        infoMoved(iSelected,jSelected,P.piece(iSelected,jSelected),P.color(iSelected,jSelected),Pi,Pj);//~@@@@I~
		capturePiece(Pi,Pj);                                       //~@@@@I~
        deleteMovedPiece(Pi,Pj);                                   //~@@@@I~
	}                                                              //~@@@@I~
//**************************************************************** //~@@@@I~
//*overridden by ConnectedGoBoard                                  //~@@@@I~
//**************************************************************** //~@@@@I~
	protected int selectPiece(int Pstate,int i,int j){return 0;}   //~@@@@R~
	protected void deleteMovedPiece(int i,int j){}                 //~@@@@I~
    public void resetMovedFrom(){}                                 //~1A35I~
	protected void drawSelected (Graphics g,int i, int j){}
	protected void drawLastFrom (Graphics g,int i, int j){}        //~1A35I~
	protected void capturePiece(int i,int j){};                    //~@@@@I~
	protected void drawCapturedPieceMark(Graphics Pg,int i,int j){};//~@@@@I~
	protected void infoMoved(int Pi1,int Pj1,int Ppiece,int Pcolor,int Pi2,int Pj2){};//~@@@@I~
    //*************************************************************************//~1Ag9I~
    //*from Canvas at stopThread                                   //~1Ag9I~
    //*************************************************************************//~1Ag9I~
    public void onDestroy()                                        //~1Ag9I~
    {                                                              //~1Ag9I~
		if (Empty!=null)                                           //~1Ag9I~
        {                                                          //~1Ag9I~
        	if (Dump.Y) Dump.println("Board:onDestroy Empty="+Empty.toString());//~1Ag9I~
//      	Empty.recycle(true);                                   //~1Ag9I~
            Empty=null;                                            //~1Ag9I~
        }                                                          //~1Ag9I~
		if (ActiveImage!=null)                                     //~1Ag9I~
        {                                                          //~1Ag9I~
        	if (Dump.Y) Dump.println("Board:onDestroy ActiveImage="+ActiveImage.toString());//~1Ag9I~
//      	ActiveImage.recycle(true);                             //~1Ag9I~
            ActiveImage=null;                                      //~1Ag9I~
        }                                                          //~1Ag9I~
    }//onDestroy()                                                 //~1Ag9I~
}//~@@@@I~
