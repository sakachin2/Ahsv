//*CID://+1AabR~:                           update#=  225;         //~1AabR~
//*************************************************************************
//1Aab 2015/04/22 1Aa7 for local game                              //~1AabI~
//1Aa7 2015/04/20 dialog to setup bishop/Knight assignment         //~1Aa7I~
//*************************************************************************
package jagoclient.partner;                                        //~1Aa7R~

import jagoclient.Dump;
import jagoclient.Global;
import jagoclient.board.Board;
import jagoclient.dialogs.HelpDialog;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;                                                 //+1AabR~
import com.Ahsv.awt.Color;
import com.Ahsv.awt.Component;
import com.Ahsv.awt.Image;
import com.axe.AxeDialog;

public class SetupBK extends AxeDialog                             //~1Aa7R~
{
	private static final int LAYOUTID=R.layout.setupbk;            //~1Aa7R~
    private static final int TITLEID=R.string.Title_SetupBK;       //~1Aa7R~
    private static final int TEXTID_HELP=R.string.Help_SetupBK;    //~1Aa7R~
    private static final int PIECE_PAWN=0;                         //~1Aa7I~
    private static final int PIECE_BISHOP=1;                       //~1Aa7I~
    private static final int PIECE_KNIGHT=2;                       //~1Aa7I~
    private static final int MAX_PLIECECTR=2;                      //~1Aa7R~
    private static final int RATE_WIDTH=98;                        //~1Aa7I~
    private static final int GAP_PIECE=2; //pixel of divider       //~1Aa7R~
    private static final int MAX_PIECETYPE=3;                      //~1Aa7I~
    private static final int[] pieceShogi={R.drawable.shogi_fu_b_up,R.drawable.shogi_kaku_b_up,R.drawable.shogi_keima_b_up};//~1Aa7R~
    private static final int[] pieceChess={R.drawable.chess_pawn_b_up,R.drawable.chess_bishop_b_up,R.drawable.chess_knight_b_up};//~1Aa7I~
    private Image[] pieceImage=new Image[MAX_PIECETYPE];           //~1Aa7I~
	private Color bgShogi;                                         //~1Aa7R~
    private GameQuestion GQ;                                       //~1Aa7R~
    private BoardQuestion BQ;                                      //~1Aa7I~
                                                                   //~1Aa7I~
    private int posB,posK,boardSize;                               //~1Aa7R~
    private int ctrB,ctrK;                                         //~1Aa7R~
    private int[] pieceTbl;                                        //~1Aa7R~
    private ViewGroup vgPanel;                                     //~1Aa7R~
//    ViewGroup[] vgLL=new ViewGroup[AG.BOARDSIZE_SHOGI];          //~1Aa7R~
    private ImageButton[] btnPiece=new ImageButton[AG.BOARDSIZE_SHOGI];//~1Aa7R~
    private Color bgB,bgW;//~1Aa7I~                                //~1Aa7R~
    private int pieceType;                                         //~1Aa7I~
    private boolean sw2ndPlayer;                                   //~1AabI~
    private TextView tv2ndPlayer;                                  //~1AabI~
    private int posB1,posK1;                                       //~1AabI~
	//***********************************************************************************
	public static SetupBK showDialog(GameQuestion Pgq)             //~1Aa7R~
    {
    	SetupBK dlg=new SetupBK(Pgq);                              //~1Aa7R~
        String title=AG.resource.getString(TITLEID);
		dlg.showDialog(title);
        return dlg;
    }
	//***********************************************************************************//~1Aa7I~
	public static SetupBK showDialog(BoardQuestion Pbq)            //~1Aa7I~
    {                                                              //~1Aa7I~
    	SetupBK dlg=new SetupBK(Pbq);                              //~1Aa7I~
        String title=AG.resource.getString(TITLEID);               //~1Aa7I~
		dlg.showDialog(title);                                     //~1Aa7I~
        return dlg;                                                //~1Aa7I~
    }                                                              //~1Aa7I~
	//***********************************************************************************
    public SetupBK(GameQuestion Pgq)                               //~1Aa7R~
    {
    	super(AG.layoutMdpi ? LAYOUTID:LAYOUTID);             //~1A6BI~//~1Aa7R~
        GQ=Pgq;                                                    //~1Aa7I~
        sw2ndPlayer=GQ.swLocalBK;                                  //~1AabI~
		init();
    }
    public SetupBK(BoardQuestion Pbq)                              //~1Aa7I~
    {                                                              //~1Aa7I~
    	super(AG.layoutMdpi ? LAYOUTID:LAYOUTID);                  //~1Aa7I~
        BQ=Pbq;                                                    //~1Aa7I~
        sw2ndPlayer=true;                                          //~1AabI~
		init();                                                    //~1Aa7I~
    }                                                              //~1Aa7I~
	//***********************************************************************************//~1Aa7I~
    private void init()
    {
    	if (GQ!=null)                                              //~1Aa7I~
        {                                                          //~1Aa7I~
        	boardSize=GQ.getBoardSize();                           //~1Aa7R~
    		posB=GQ.getPosB();                                     //~1Aa7R~
    		posK=GQ.getPosK();                                     //~1Aa7R~
    		posB1=GQ.getPosB1();                                   //~1AabI~
    		posK1=GQ.getPosK1();                                   //~1AabI~
        }                                                          //~1Aa7I~
        else                                                       //~1Aa7I~
        {                                                          //~1Aa7I~
        	boardSize=BQ.getBoardSize();                           //~1Aa7I~
    		posB=BQ.getPosB();                                     //~1Aa7I~
    		posK=BQ.getPosK();                                     //~1Aa7I~
    		posB1=BQ.getPosB1();                                   //~1AabI~
    		posK1=BQ.getPosK1();                                   //~1AabI~
        }                                                          //~1Aa7I~
        pieceTbl=new int[AG.BOARDSIZE_SHOGI];                      //~1Aa7R~
        setPieceTbl(posB,posK);                                    //~1Aa7R~
        pieceType=Board.pieceType;                                 //~1Aa7I~
		bgShogi=Global.getColor("boardcolor",170,120,70);          //~1Aa7I~
        if (boardSize==AG.BOARDSIZE_SHOGI)                         //~1Aa7I~
        {                                                          //~1Aa7I~
        	bgB=bgShogi;                                           //~1Aa7I~
        	bgW=bgShogi;                                           //~1Aa7I~
        }                                                          //~1Aa7I~
        else                                                       //~1Aa7I~
        {                                                          //~1Aa7I~
        	bgB=AG.chessBoardBlack;                                //~1Aa7I~
        	bgW=AG.chessBoardWhite;                                //~1Aa7I~
        }                                                          //~1Aa7I~
        loadPieceImage();                                          //~1Aa7I~
    }
    //*******************************************************************//~1Aa7I~
    private void loadPieceImage()                                  //~1Aa7I~
    {                                                              //~1Aa7I~
        int ww=Math.min(AG.scrWidth,AG.scrHeight);                 //~1Aa7I~
        int dww=ww/boardSize-GAP_PIECE;;                        //~1Aa7I~
    	for (int ii=0;ii<MAX_PIECETYPE;ii++)                       //~1Aa7I~
        {                                                          //~1Aa7I~
	    	int resid;                                             //~1Aa7I~
            if (pieceType==Board.PIECETYPE_SHOGI)                  //~1Aa7I~
            	resid=pieceShogi[ii];                              //~1Aa7I~
            else                                                   //~1Aa7I~
            	resid=pieceChess[ii];                              //~1Aa7I~
            pieceImage[ii]=Image.loadPieceImage(resid,dww,dww);    //~1Aa7I~
        }                                                          //~1Aa7I~
    }                                                              //~1Aa7I~
	//***********************************************************************************
    @Override
	protected void setupDialogExtend(ViewGroup PlayoutView)
    {
		setAxeDialogWindowSize(RATE_WIDTH/*width rate*/,0/*hight=wrap_content*/,true/*apply for min(Width,Height)*/);//~1Aa7R~
    	vgPanel=(ViewGroup)PlayoutView.findViewById(R.id.BKPanel); //~1Aa7R~
        btnPieceInit(posB,posK);                                   //~1Aa7R~
        show1stPlayer();                                           //~1AabR~
        showPiece();                                               //~1Aa7R~
    }
    //*******************************************************************//~1AabI~
    private void show1stPlayer()                                   //~1AabI~
    {                                                              //~1AabI~
        String count1st;                                           //~1AabI~
    	tv2ndPlayer=(TextView)layoutView.findViewById(R.id.BK1stPlayer);//~1AabR~
        if (sw2ndPlayer)                                           //~1AabI~
        {                                                          //~1AabI~
        	String b=GameQuestion.putPieces2(posB1,boardSize);     //~1AabI~
        	String k=GameQuestion.putPieces2(posK1,boardSize);     //~1AabI~
        	count1st=AG.resource.getString(R.string.BKCount1stPlayer,b,k);//~1AabR~
        }                                                          //~1AabI~
        else                                                       //~1AabI~
        	count1st=AG.resource.getString(R.string.BKThisIs1stPlayer);//~1AabI~
	    tv2ndPlayer.setText(count1st);                             //~1AabI~
    }                                                              //~1AabI~
	//***********************************************************************************//~1Aa7I~
    @Override                                                      //~1Aa7I~
    protected void onDismiss()                                     //~1Aa7I~
    {                                                              //~1Aa7I~
    	recycleImage();                                            //~1Aa7I~
    	return;                                                    //~1Aa7I~
    }                                                              //~1Aa7I~
	//***********************************************************************************//~5219I~
	//*                                                            //~5219I~
	//***********************************************************************************//~5219I~
    public boolean onClickClose()                               //~5219I~
    {                                                              //~5219I~
        getPieceTbl();
        if (ctrK>MAX_PLIECECTR||ctrB>MAX_PLIECECTR)                //~1Aa7R~
        {                                                          //~1Aa7I~
            AView.showToast(R.string.ErrPieceCount);               //~1Aa7I~
            return false;                                          //~1Aa7I~
        }                                                          //~1Aa7I~
        if (GQ!=null)                                              //~1Aa7I~
        {                                                          //~1Aa7I~
			GQ.changedBK(posB,posK);                         //~1Aa7R~//~1AabR~
        }                                                          //~1Aa7I~
        else                                                       //~1Aa7I~
        {                                                          //~1Aa7I~
			if (!BQ.changedBK(posB,posK))	//count changed        //~1Aa7R~
	            return false;                                      //~1Aa7I~
        }                                                          //~1Aa7I~
        return true;//~1Aa7R~
    }                                                              //~5219I~
	//**********************************                           //~5218I~
	@Override                                                      //~5218I~
    protected boolean onClickHelp()                                //~5218I~
    {                                                              //~5218I~
	    new HelpDialog(Global.frame(),TEXTID_HELP);                                      //~5218I~
        return false;	//not dismiss                              //~5218I~
    }                                                              //~5218I~
	//**********************************                           //~1Aa7I~
	@Override                                                      //~1Aa7I~
    protected boolean onClickOther(int Pbuttonid)                  //~1Aa7I~
    {                                                              //~1Aa7I~
        int pos=0;                                                 //~1Aa7I~
	    switch(Pbuttonid)                                          //~1Aa7I~
        {                                                          //~1Aa7I~
        case R.id.BKIB1:                                           //~1Aa7I~
            pos=0;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB2:                                           //~1Aa7I~
            pos=1;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB3:                                           //~1Aa7I~
            pos=2;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB4:                                           //~1Aa7I~
            pos=3;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB5:                                           //~1Aa7I~
            pos=4;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB6:                                           //~1Aa7I~
            pos=5;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB7:                                           //~1Aa7I~
            pos=6;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB8:                                           //~1Aa7I~
            pos=7;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        case R.id.BKIB9:                                           //~1Aa7I~
            pos=8;                                                 //~1Aa7I~
        	break;                                                 //~1Aa7I~
        }                                                          //~1Aa7I~
        int piece=pieceTbl[pos];                                   //~1Aa7R~
        int newpiece;                                              //~1Aa7I~
        if (piece==PIECE_BISHOP)                                   //~1Aa7I~
        	newpiece=PIECE_KNIGHT;                                 //~1Aa7I~
        else                                                       //~1Aa7I~
        if (piece==PIECE_KNIGHT)                                  //~1Aa7I~
        	newpiece=PIECE_PAWN;                                   //~1Aa7I~
        else                                                       //~1Aa7I~
        	newpiece=PIECE_BISHOP;                                 //~1Aa7I~
        pieceTbl[pos]=newpiece;                                     //~1Aa7I~
		showPiece();                                                //~1Aa7I~
        return false;	//not dismiss                              //~1Aa7I~
    }                                                              //~1Aa7I~
    //******************************************                   //~5219I~
	private void setPieceTbl(int PposB,int PposK)                                     //~5219I~//~1Aa7R~
    {                                                              //~5219I~
    	int posb,posk;                                             //~1Aa7R~
    	posb=PposB>>8;                                         //~1Aa7I~
    	posk=PposK>>8;                                         //~1Aa7I~
        ctrK=0;                                                    //~1Aa7I~
        ctrB=0;                                                    //~1Aa7I~
        if (Dump.Y) Dump.println("setPieceTbl posB="+Integer.toHexString(posb)+" posK="+Integer.toHexString(posk));//~1Aa7R~
        for (int ii=0;ii<boardSize;ii++,posb>>=1,posk>>=1)         //~1Aa7R~
        {                                                          //~1Aa7I~
        	if ((posb & 0x01)!=0)                                  //~1Aa7I~
            {                                                      //~1Aa7I~
            	pieceTbl[ii]=PIECE_BISHOP;                         //~1Aa7R~
                ctrB++;                                            //~1Aa7I~
            }                                                      //~1Aa7I~
            else                                                   //~1Aa7I~
        	if ((posk & 0x01)!=0)                                  //~1Aa7I~
            {                                                      //~1Aa7I~
            	pieceTbl[ii]=PIECE_KNIGHT;                         //~1Aa7R~
                ctrK++;                                            //~1Aa7I~
            }                                                      //~1Aa7I~
            else                                                   //~1Aa7I~
            	pieceTbl[ii]=PIECE_PAWN;                           //~1Aa7R~
        }                                                          //~1Aa7I~
    }                                                              //~5219I~
    //******************************************                   //~1Aa7I~
	private void getPieceTbl()                                     //~1Aa7R~
    {                                                              //~1Aa7I~
    	int posb=0,posk=0;                                         //~1Aa7R~
        ctrB=0;                                                    //~1Aa7I~
        ctrK=0;                                                    //~1Aa7I~
        for (int ii=0;ii<boardSize;ii++)                           //~1Aa7R~
        {                                                          //~1Aa7I~
        	int piece=pieceTbl[ii];                                //~1Aa7R~
        	if (piece==PIECE_BISHOP)                               //~1Aa7R~
            {                                                      //~1Aa7I~
                posb|=(1<<ii);                                     //~1Aa7I~
                ctrB++;                                            //~1Aa7R~
            }                                                      //~1Aa7I~
            else                                                   //~1Aa7I~
        	if (piece==PIECE_KNIGHT)                              //~1Aa7R~
            {                                                      //~1Aa7I~
                posk|=(1<<ii);                                     //~1Aa7I~
                ctrK++;                                            //~1Aa7R~
            }                                                      //~1Aa7I~
        }                                                          //~1Aa7I~
        posB=(posb<<8)+ctrB;                                       //~1Aa7R~
        posK=(posk<<8)+ctrK;                                       //~1Aa7R~
        if (Dump.Y) Dump.println("getPieceTbl posB="+Integer.toHexString(posB)+" posK="+Integer.toHexString(posK));//~1Aa7R~
    }                                                              //~1Aa7I~
    //******************************************                   //~1Aa7I~
	private void btnPieceInit(int PposB,int PposK)                 //~1Aa7R~
    {                                                              //~1Aa7I~
        for (int ii=0;ii<AG.BOARDSIZE_SHOGI;ii++)                  //~1Aa7R~
        {                                                          //~1Aa7I~
        	ViewGroup vg=(ViewGroup)vgPanel.getChildAt(ii);	//LinearLayout//~1Aa7R~
//          vgLL[ii]=vg;                                           //~1Aa7R~
            if (ii==AG.BOARDSIZE_SHOGI-1)                          //~1Aa7I~
            {                                                      //~1Aa7I~
            	if (boardSize==AG.BOARDSIZE_SHOGI)                 //~1Aa7I~
			        new Component().setVisibility(vg,View.VISIBLE);//~1Aa7I~
                else                                               //~1Aa7I~
			        new Component().setVisibility(vg,View.GONE);   //~1Aa7I~
            }                                                      //~1Aa7I~
        	ImageButton ib=(ImageButton)vg.getChildAt(0);          //~1Aa7I~
            setButtonListener(ib);                                 //~1Aa7I~
            btnPiece[ii]=ib;                                       //~1Aa7I~
        }                                                          //~1Aa7I~
    }                                                              //~1Aa7I~
    //***********************************************************  //~1Aa7R~
	private void showPiece()                                       //~1Aa7R~
    {                                                              //~1Aa7I~
		for (int ii=0;ii<boardSize;ii++)                           //~1Aa7I~
        {
        	Color bg;
//        	int imgid;//~1Aa7I~
//      	ViewGroup vg=vgLL[ii];                                 //~1Aa7R~
            if (ii%2==0)                                           //~1Aa7I~
            	bg=bgB;                                            //~1Aa7I~
            else                                                   //~1Aa7I~
            	bg=bgW;                                            //~1Aa7I~
//          ((View)vg).setBackgroundColor(bg.getRGB());            //~1Aa7R~
        	ImageButton ib=btnPiece[ii];                           //~1Aa7I~
            ib.setBackgroundColor(bg.getRGB());                    //~1Aa7I~
//            if (pieceType==Board.PIECETYPE_SHOGI)                //~1Aa7R~
//                if (pieceTbl[ii]==PIECE_BISHOP)   //Bishop       //~1Aa7R~
//                    imgid=R.drawable.shogi_kaku_b_up;            //~1Aa7R~
//                else                                             //~1Aa7R~
//                if (pieceTbl[ii]==PIECE_KNIGHT)   //Knight       //~1Aa7R~
//                    imgid=R.drawable.shogi_keima_b_up;           //~1Aa7R~
//                else                                             //~1Aa7R~
//                    imgid=R.drawable.shogi_fu_b_up;              //~1Aa7R~
//            else                                                 //~1Aa7R~
//                if (pieceTbl[ii]==PIECE_BISHOP)   //Bishop       //~1Aa7R~
//                    imgid=R.drawable.chess_bishop_b_up;          //~1Aa7R~
//                else                                             //~1Aa7R~
//                if (pieceTbl[ii]==PIECE_KNIGHT)   //Knight       //~1Aa7R~
//                    imgid=R.drawable.chess_knight_b_up;          //~1Aa7R~
//                else                                             //~1Aa7R~
//                    imgid=R.drawable.chess_pawn_b_up;            //~1Aa7R~
//            ib.setImageResource(imgid);                          //~1Aa7R~
			int pid=pieceTbl[ii];                                  //~1Aa7I~
			Image image=pieceImage[pid];                           //~1Aa7I~
            Bitmap bm=image.bitmap;                                //~1Aa7I~
            ib.setImageBitmap(bm);                                 //~1Aa7I~
        }                                                          //~1Aa7I~
    }                                                              //~1Aa7I~
    //***********************************************************  //~1Aa7I~
	private void recycleImage()                                    //~1Aa7I~
    {                                                              //~1Aa7I~
		for (int ii=0;ii<MAX_PIECETYPE;ii++)                       //~1Aa7R~
        {                                                          //~1Aa7I~
			Image image=pieceImage[ii];                            //~1Aa7I~
            image.recycle();                                       //~1Aa7I~
        }                                                          //~1Aa7I~
    }                                                              //~1Aa7I~
}
