//*CID://+1A3aR~:                             update#=  121;       //+1A3aR~
//****************************************************************************//~v101I~
//1A3a 2013/04/25 screen flicker by duplicate copy() by 1A37       //~103aI~
//1A38 2013/04/22 (BUG)When response(@@!move) delayed,accept next select.//~1038I~
//                Then move confuse to move to old dest from new selected.//~1038I~
//1A35 2013/04/19 show mark of last moved from position            //~1035I~
//1033 2013/03/07 gameover2 msg                                    //~1033I~
//1030 2013/03/05 (BUG)captured piece coordinate                   //~1030I~
//1024 2013/03/02 (BUG)moved even if errNoPath                     //~1024I~
//101g 2013/02/09 captured mark remains at after partner move after I captured//~v101I~
//****************************************************************************//~v101I~
package jagoclient.board;


import java.util.Arrays;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;
import com.Ahsv.awt.Color;
import com.Ahsv.awt.Graphics;
import jagoclient.Dump;
import jagoclient.partner.GameQuestion;
import jagoclient.sound.JagoSound;

/**
This board overrides some methods from the Board class
for connected boards. It knows a ConnectedGoFrame to
ask for permission of operations and to report moves
to it.
*/

public class ConnectedBoard extends Board
{                                                                  //~@@@2R~
    private static int markCaptured[]=new int[AG.BOARDSIZE_SHOGI*AG.BOARDSIZE_SHOGI];//~@@@2R~
                                                                   //~@@@2I~
	ConnectedGoFrame CGF;                                          //~@@@2I~
    boolean swGameover2;                                           //~@@@2R~
    int iref,jref;                                                 //~@@@2I~
    boolean swCaptured;                                            //~@@@2M~
    private boolean swDeletedCaptured;                             //~@@@2I~
	public ConnectedBoard (int size, ConnectedGoFrame gf)
	{	super(size,gf);
		CGF=gf;
	}

	public synchronized void setmouse (int i, int j, int c)	
	{	if (Pos.isMain() && CGF.wantsmove()) return;
		super.setmouse(i,j,c);
	}

	/**
	In a ConnectedBoard you cannot fix the game tree this way.
	*/
	public synchronized void setmousec (int i, int j, int c) {}
    //*override Board:movemouse()******************************    //~@@@2R~
	public synchronized void movemouse (int i, int j)
	{	if (Pos.haschildren()) return;
		if (P.color(i,j)!=0) return;
//        if (captured==1 && capturei==i && capturej==j &&         //~@@@2R~
//            GF.getParameter("preventko",true)) return;           //~@@@2R~
		if (Pos.isMain() && CGF.wantsmove())
//  	{	if (CGF.moveset(i,j))                                  //~@@@2R~
    	{                                                          //~@@@2I~
    		if (CGF.moveset(i,j,P.piece(i,j)))                     //~@@@2I~
			{	sendi=i; sendj=j;
				update(i,j); copy();
				MyColor=P.color();
			}
//            JagoSound.play("click","",false);                    //~@@@2R~
		}
		else set(i,j); // try to set a new move
	}

	/**
	Completely remove a group (at end of game, before count), and
	note all removals. This is only allowed in end nodes and if
	the GoFrame wants the removal, it gets it.
	*/
//    public synchronized void removegroup (int i0, int j0)        //~@@@2R~
//    {   if (Pos.haschildren()) return;                           //~@@@2R~
//        if (P.color(i0,j0)==0) return;                           //~@@@2R~
//        if (CGF.wantsmove() && ((Node)Pos.content()).main())     //~@@@2R~
//        {   CGF.moveset(i0,j0);                                  //~@@@2R~
//        }                                                        //~@@@2R~
//        super.removegroup(i0,j0);                                //~@@@2R~
//    }                                                            //~@@@2R~

	/**
	Take back the last move.
	*/
//    public synchronized void undo ()                             //~2C26R~
//    {   if (Pos.isMain()                                         //~2C26R~
//            && CGF.wantsmove())                                  //~2C26R~
//        {   if (!Pos.haschildren())                              //~2C26R~
//            {   if (State!=1 && State!=2) clearremovals();       //~2C26R~
//                CGF.undo();                                      //~2C26R~
//            }                                                    //~2C26R~
//            return;                                              //~2C26R~
//        }                                                        //~2C26R~
//        super.undo();                                            //~2C26R~
//    }                                                            //~2C26R~

	/**
	Pass (report to the GoFrame if necessary.
	*/
//    public synchronized void pass ()                             //~2C26R~
//    {   if (Pos.haschildren()) return;                           //~2C26R~
//        if (GF.blocked() && Pos.isMain()) return;                //~2C26R~
//        if (Pos.isMain() && CGF.wantsmove())                     //~2C26R~
//        {   CGF.movepass(); return;                              //~2C26R~
//        }                                                        //~2C26R~
//        super.pass();                                            //~2C26R~
//    }                                                            //~2C26R~

	/**
	This is used to fix the game tree (after confirmation).
	Will not be possible, if the GoFrame wants moves.
	*/
//    public synchronized void insertnode ()                       //~2C26R~
//    {   if (Pos.isLastMain() && CGF.wantsmove()) return;         //~2C26R~
//        super.insertnode();                                      //~2C26R~
//    }                                                            //~2C26R~
	
	/**
	In a ConnectedBoard you cannot delete stones.
	*/
	public synchronized void deletemousec (int i, int j) {}
//**************************************************************** //~@@@@I~//~@@@2M~
//*Board.java is too large;shift to ConnectedBoard                 //~@@@2I~
//**************************************************************** //~@@@2I~
////****************************************************************//~@@@@R~//~@@@2M~
////*draw piece:Bishop and knight                                  //~@@@@R~//~@@@2M~
////****************************************************************//~@@@@R~//~@@@2M~
//    private void drawPiece(Graphics Pg,int Pcolor,int Pmark,int Pi,int Pj)//~@@@@R~//~@@@2M~
//    {                                                            //~@@@@R~//~@@@2M~
//        int colidx;                                              //~@@@@R~//~@@@2M~
//    //*******************                                        //~@@@@R~//~@@@2M~
//        if (Dump.Y) Dump.println("drawPiece i="+Pi+",j="+Pj+",Grafics="+Pg.toString());//~@@@@R~//~@@@2M~
//        if (YourColor>0)    //black                              //~@@@@R~//~@@@2M~
//            colidx=Pcolor>0?0:3;    //black or white-down        //~@@@@R~//~@@@2M~
//        else                                                     //~@@@@R~//~@@@2M~
//            colidx=Pcolor>0?2:1;    //black-down or white        //~@@@@R~//~@@@2M~
//        Image piece=SpieceImage[colidx][getPieceIndex(Pmark)];   //~@@@@R~//~@@@2M~
//        int xi=O+OTU+Pi*D+PIECE_MARGIN+1;                        //~@@@@R~//~@@@2M~
//        int xj=O+OTU+Pj*D+PIECE_MARGIN+1;;                       //~@@@@R~//~@@@2M~
//        Pg.drawImage(piece,xi,xj);                               //~@@@@R~//~@@@2M~
//        if (Dump.Y) Dump.println("drawPiece end i="+Pi+",j="+Pj);//~@@@@R~//~@@@2M~
//    }                                                            //~@@@@R~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
//*piece selection chk                                             //~@@@@I~//~@@@2M~
//*rc:-1:err,1:selected,0:do move                                  //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	@Override //Board                                              //~@@@2I~
	protected int selectPiece(int Pstate,int i,int j)                //~@@@@I~//~@@@2R~
    {                                                              //~@@@@I~//~@@@2M~
    	int col,rc=-1;                                             //~@@@@I~//~@@@2M~
    //*******************                                          //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("selectPiece state="+Pstate+",swSelected="+swSelected+",i="+i+",j="+j);//~@@@@I~//~@@@2M~
//        if (moveNumber==1)  //before 1st move                    //~@@@2R~
//            CGF.drawInitialCaptured();  //height is not determined at constructor//~@@@2R~
    	if (Pstate==1)                                             //~@@@@I~//~@@@2M~
        	col=1;   //black                                       //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        	col=-1;  //white                                       //~@@@@I~//~@@@2M~
		if (!CGF.swLocalGame && col!=YourColor)	//your turn in partner match//~@@@2I~
        {                                                          //~@@@2I~
			errNotYourTurn(-YourColor);                            //~@@@2R~
            return rc;    	                                       //~@@@2I~
        }                                                          //~@@@2I~
        if (CGF.swWaitingPartnerResponse)                          //~1038I~
        {                                                          //~1038I~
			errWaitingPartnerResponse();                           //~1038I~
            return rc;                                             //~1038I~
        }                                                          //~1038I~
    	if (swSelected)                                            //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
			if (col==P.color(i,j))	//your piece                   //~@@@@I~//~@@@2R~
            {                                                      //~@@@@I~//~@@@2M~
            	if ((GameOptions & GameQuestion.GAMEOPT_RESELECTABLE)==0)//~@@@@R~//~@@@2M~
                {                                                  //~@@@@I~//~@@@2M~
            		if (iSelected==i && jSelected==j)              //~@@@2I~
						rc=1;	//ignore if duplicated             //~@@@2I~
                    else                                           //~@@@2I~
                    {                                              //~@@@2I~
                		errMissTouch(iSelected,jSelected);                                 //~@@@@I~//~@@@2R~
                		rc=-2;                                         //~@@@@I~//~@@@2R~
                    }                                              //~@@@2I~
                }                                                  //~@@@@I~//~@@@2M~
                else                                               //~@@@@I~//~@@@2M~
                {                                                  //~@@@@I~//~@@@2M~
            		if (iSelected==i && jSelected==j)	//resetreq //~@@@@I~//~@@@2M~
                	{	                                           //~@@@@I~//~@@@2M~
                    	swSelected=false;                          //~@@@@I~//~@@@2M~
						update(iSelected,jSelected);	//clear mark on old square//~@@@@I~//~@@@2M~
                        rc=-1;                                     //~@@@@I~//~@@@2M~
                	}                                              //~@@@@I~//~@@@2M~
                    else                                           //~@@@@I~//~@@@2M~
                    {                                              //~@@@@I~//~@@@2M~
				    	updateSelected(i,j);                       //~@@@2I~
						rc=1;	//selected                         //~@@@@I~//~@@@2M~
                    }                                              //~@@@@I~//~@@@2M~
                }                                                  //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
            {                                                      //~v101I~
                rc=isOnPiecePath(i,j);                               //~@@@@I~//~@@@2M~//~v101R~
                if (rc==1)                                         //~v101I~
                {                                                      //~@@@@I~//~@@@2M~//~v101R~
    //              swSelected=false; set after color was set          //~@@@@R~//~@@@2M~//~v101R~
                    P.setPiece(i,j,P.piece(iSelected,jSelected));         //~@@@@I~//~@@@2M~//~v101R~
                    rc=0;                                              //~@@@@I~//~@@@2M~//~v101R~
                }                                                      //~@@@@I~//~@@@2M~//~v101R~
                else                                                   //~@@@@I~//~@@@2M~//~v101R~
                if (rc==0)  //-1:dup ignore                        //~v101I~
                {                                                  //~1024I~
                    errNoPath(iSelected,jSelected,P.piece(iSelected,jSelected),P.color(iSelected,jSelected),i,j);                                       //~@@@@I~//~@@@2R~//~v101R~
                    rc=-1;                                         //~1024I~
                }                                                  //~1024I~
            }                                                      //~v101I~
        }                                                          //~@@@@I~//~@@@2M~
        else              //do select                              //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
			if (col==P.color(i,j)) 	//your piece                   //~@@@2R~
            {                                                      //~@@@@I~//~@@@2M~
            	swSelected=true;            	//clear mark on old square//~@@@@I~//~@@@2M~                       //~@@@@I~//~@@@2M~
                iSelected=i; jSelected=j;                          //~@@@@I~//~@@@2M~
				update(iSelected,jSelected);
				rc=1;	//selected                                 //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@2M~
            if (P.color(i,j)!=0)                                   //~@@@2M~
				errNotYourTurn(col);                               //~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("selectPiece rc="+rc+",swSelected="+swSelected+",i="+iSelected+",j="+jSelected);//~@@@@I~//~@@@2M~
        if (rc==1)	//selected                                     //~@@@2I~
        {                                                          //~@@@2I~
            if (swDeletedCaptured)                 //~@@@@I~       //~@@@2I~
            {                                          //~@@@@I~   //~@@@2I~
	        	swDeletedCaptured=false;           //~@@@@I~       //~@@@2I~
                clearCapturedMark();                   //~@@@@I~   //~@@@2I~
            }                                          //~@@@@I~   //~@@@2I~
        	CGF.pieceSelected(iSelected,jSelected);                //~@@@2R~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
        if (rc==0)	//will be moved                                //~@@@2I~
        {                                                          //~@@@2I~
        	CGF.pieceMoved(P.color(iSelected,jSelected));          //~@@@2I~
        }                                                          //~@@@2I~
        return rc;                                                 //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	@Override //Board                                              //~@@@2I~
	protected void deleteMovedPiece(int i,int j)                     //~@@@@R~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    //*******************                                          //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("deleteMoved +i="+iSelected+",j="+jSelected);//~@@@@I~//~@@@2M~
       	swSelected=false;                                          //~@@@@I~//~@@@2M~
	    swCursorMovedFrom=true;                                    //~1035I~
        lastifrom=iSelected; lastjfrom=jSelected;	//draw mark of last moved from//~1035I~
		deletemousecPiece(iSelected,jSelected);                    //~@@@@R~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	@Override //Board                                              //~@@@2I~
	protected void drawSelected (Graphics g,int i, int j)            //~@@@@R~//~@@@2M~
	{                                                              //~@@@@I~//~@@@2M~
        int xx,yy;                                                 //~@@@@I~//~@@@2M~
		if (g==null) return;                                       //~@@@@I~//~@@@2M~
		xx=O+OTU+i*D; yy=O+OTU+j*D;                                //~@@@@R~//~@@@2M~
//        g.setColor(selectedColor);                                   //~@@@@R~//~@@@2R~
//        g.drawRect(xx+1,yy+1,D-2,D-2);                             //~@@@@R~//~@@@2R~
//        g.drawRect(xx+2,yy+2,D-4,D-4);                             //~@@@@I~//~@@@2R~
          g.drawRect(AG.selectedColor,2/*line Width*/,xx+5,yy+5,D-9/*width*/,D-9/*height*/);//~@@@2R~
        if (Dump.Y) Dump.println("drawSelected i="+i+",j="+j+",x="+xx+",y="+yy+",D="+D+",g="+g.toString());//~@@@@R~//~@@@2M~
	}                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~1035I~
	@Override //Board                                              //~1035I~
	protected void drawLastFrom (Graphics g,int i, int j)          //~1035I~
	{                                                              //~1035I~
        int xx,yy;
        Color markcolor;                                       //~1035R~
    //*****************************                                //~1035I~
		if (g==null) return;                                       //~1035I~
		xx=O+OTU+i*D; yy=O+OTU+j*D;                                //~1035I~
        if (swChessBoard)                                          //~1035I~
        {                                                          //~1035I~
        	if (i%2==j%2)                                       //~1035I~
				markcolor=AG.chessBoardBlack; //black on white                      //~1035I~
            else                                                   //~1035I~
				markcolor=AG.chessBoardWhite;                      //~1035I~
        }                                                          //~1035I~
        else                                                       //~1035I~
        	markcolor=AG.lastFromColor;                            //~1035I~
        g.drawRect(markcolor,2/*line Width*/,xx+5,yy+5,D-9/*width*/,D-9/*height*/);//~1035R~
        if (Dump.Y) Dump.println("drawSelected i="+i+",j="+j+",x="+xx+",y="+yy+",D="+D+",g="+g.toString());//~1035I~
	}                                                              //~1035I~
//**************************************************************** //~@@@@I~//~@@@2M~
//chk path is available from (iSelected,jSelected) to (i,j)        //~@@@@I~//~@@@2M~
//rc:0 no path,1:path ok,-1:dup ignore                             //~v101I~
//**************************************************************** //~@@@@I~//~@@@2M~
	private int isOnPiecePath(int i,int j)                     //~@@@@I~//~@@@2M~//~v101R~
    {                                                              //~@@@@I~//~@@@2M~
    	int rc=0;                                              //~@@@@I~//~@@@2M~//~v101R~
        if (Dump.Y) Dump.println("isOnPiecePath to ("+i+","+j+"),color="+P.color(i,j)+",piece="+P.piece(i,j));//~v101R~
        if (P.color(i,j)!=0)	//filled                           //~@@@@I~//~@@@2M~
        	return 0;                                          //~@@@@I~//~@@@2M~//~v101R~
        if (P.isSetPiece(i,j))	//not yet set color but move duplicate check//~v101R~
        	return -1;                                             //~v101R~
    	switch(P.piece(iSelected,jSelected))                       //~@@@@R~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        case Field.BISHOP:                                         //~@@@@I~//~@@@2M~
        	rc=isOnPiecePathBishop(i,j)?1:0;                           //~@@@@I~//~@@@2M~//~v101R~
            break;                                                 //~@@@@I~//~@@@2M~
        case Field.KNIGHT:                                         //~@@@@I~//~@@@2M~
        	rc=isOnPiecePathKnight(i,j)?1:0;                           //~@@@@I~//~@@@2M~//~v101R~
            break;                                                 //~@@@@I~//~@@@2M~
        default:                                                   //~@@@@I~//~@@@2M~
        	rc=isOnPiecePathPawn(i,j)?1:0;                              //~@@@@I~//~@@@2M~//~v101R~
            break;                                                 //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("isOnPiecePath from=("+iSelected+","+jSelected+") to ("+i+","+j+") rc="+rc);//~@@@@I~//~@@@2M~
        return rc;                                                 //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private boolean isOnPiecePathPawn(int Pi,int Pj)               //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    	boolean rc=false;                                          //~@@@@I~//~@@@2M~
        int ii,jj;                                                 //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
        if (Pi==iSelected)  //vertical                             //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (Pj>jSelected)               //up                   //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
		        for (jj=jSelected+1;jj<Pj;jj++)                    //~@@@@I~//~@@@2M~
		        	if (P.color(Pi,jj)!=0)	//filled               //~@@@@I~//~@@@2M~
                    	break;                                     //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            else      //Pj<jSelected        //down                 //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
		        for (jj=jSelected-1;jj>Pj;jj--)                    //~@@@@I~//~@@@2M~
		        	if (P.color(Pi,jj)!=0)	//filled               //~@@@@I~//~@@@2M~
                    	break;                                     //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            rc=(jj==Pj);                                           //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@R~//~@@@2M~
        if (Pj==jSelected)  //horizontal                           //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (Pi>iSelected)               //to right             //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
		        for (ii=iSelected+1;ii<Pi;ii++)                    //~@@@@I~//~@@@2M~
		        	if (P.color(ii,Pj)!=0)	//filled               //~@@@@I~//~@@@2M~
                    	break;                                     //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            else      //Pi<iSelected                               //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
		        for (ii=iSelected-1;ii>Pi;ii--)                    //~@@@@I~//~@@@2M~
		        	if (P.color(ii,Pj)!=0)	//filled               //~@@@@I~//~@@@2M~
                    	break;                                     //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            rc=(ii==Pi);                                           //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        return rc;                                                 //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private boolean isOnPiecePathBishop(int Pi,int Pj)             //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    	boolean reflectable;                                       //~@@@@I~//~@@@2M~
        int /*ii,jj,*/rc;                                             //~@@@@I~//~@@@2R~
    //**********************                                       //~@@@@I~//~@@@2M~
    	reflectable=(GameOptions & GameQuestion.GAMEOPT_REFLECTABLE)!=0;//~@@@@R~//~@@@2M~
//*Down Right                                                      //~@@@@I~//~@@@2M~
		rc=isOnPiecePathBishopDR(iSelected+1,jSelected+1,Pi,Pj); //down right//~@@@@I~//~@@@2R~
        if (rc==1)	//bottom edge                                  //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)                                       //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopUR(iref,S-2,Pi,Pj);  //up right//~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        if (rc==2)	//right                                        //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)	//try one reflection               //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopDL(S-2,jref,Pi,Pj); //down left//~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        if (rc==0)                                                //~@@@@I~//~@@@2R~
        	return true;                                           //~@@@@I~//~@@@2M~
//*Down Left                                                       //~@@@@I~//~@@@2M~
		rc=isOnPiecePathBishopDL(iSelected-1,jSelected+1,Pi,Pj);//down left//~@@@@I~//~@@@2R~
        if (rc==1)	//bootom                                       //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)                                       //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopUL(iref,S-2,Pi,Pj);  //up left//~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        if (rc==2)	//left                                         //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)	//try one reflection               //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopDR(1,jref,Pi,Pj);           //~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        if (rc==0)                                                //~@@@@I~//~@@@2R~
        	return true;                                           //~@@@@I~//~@@@2M~
//*Up Right                                                        //~@@@@I~//~@@@2M~
		rc=isOnPiecePathBishopUR(iSelected+1,jSelected-1,Pi,Pj);//up right//~@@@@I~//~@@@2R~
        if (rc==1)	//top                                          //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)                                       //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopDR(iref,1,Pi,Pj);  //down right//~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        if (rc==2)	//right                                        //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)	//try one reflection               //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopUL(S-2,jref,Pi,Pj);         //~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        if (rc==0)                                                //~@@@@I~//~@@@2M~
        	return true;                                           //~@@@@I~//~@@@2M~
//*Up Left                                                         //~@@@@I~//~@@@2M~
		rc=isOnPiecePathBishopUL(iSelected-1,jSelected-1,Pi,Pj);//up right//~@@@@I~//~@@@2M~
        if (rc==1)	//top                                          //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)                                       //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopDL(iref,1,Pi,Pj);  //down left//~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        else                                                       //~@@@@I~//~@@@2M~
        if (rc==2)	//left                                         //~@@@2R~
        {                                                          //~@@@@I~//~@@@2M~
        	if (reflectable)	//try one reflection               //~@@@@I~//~@@@2M~
            	rc=isOnPiecePathBishopUR(1,jref,Pi,Pj);           //~@@@@I~//~@@@2R~
        }                                                          //~@@@@I~//~@@@2M~
        if (rc==0)                                                //~@@@@I~//~@@@2M~
        	return true;                                           //~@@@@I~//~@@@2M~
        return false;                                              //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
//Bishop path up-right                                             //~@@@@I~//~@@@2M~
//rc=0:reached,(ii)>0:reached horizontal edge,(-jj)<0:reached vertical edge,=S:unreachable//~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private int isOnPiecePathBishopDR(int Pi1,int Pj1,int Pi2,int Pj2)//~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
//    	int rc;                                                    //~@@@@I~//~@@@2M~
        int ii,jj;                                                 //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("PathBishop-DR from=("+Pi1+","+Pj1+"),to=("+Pi2+","+Pj2);//~@@@@I~//~@@@2M~
    	for (ii=Pi1,jj=Pj1;ii!=Pi2||jj!=Pj2;ii++,jj++)	//down-right//~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (ii==S)	//beyond right edge                        //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
            	if (jj==S)                                         //~@@@@I~//~@@@2M~
                	return S;                                      //~@@@@I~//~@@@2M~
                else                                               //~@@@@I~//~@@@2M~
                {                                                  //~@@@2I~
                	jref=jj;                                       //~@@@2I~
                	return 2;                                    //~@@@@I~//~@@@2R~
                }                                                  //~@@@2I~
            }                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
        	if (jj==S)	//beyond bottom edge                       //~@@@@I~//~@@@2M~
            {                                                      //~@@@2I~
            	iref=ii;                                           //~@@@2I~
            	return 1;                                         //~@@@@I~//~@@@2R~
            }                                                      //~@@@2I~
            if (P.color(ii,jj)!=0)          //blocked              //~@@@@I~//~@@@2M~
                return S;  //unreachable                           //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        return 0;	//reached Pi2,Pj2                              //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private int isOnPiecePathBishopDL(int Pi1,int Pj1,int Pi2,int Pj2)//~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
//    	int rc;                                                    //~@@@@I~//~@@@2M~
        int ii,jj;                                                 //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("PathBishop-DL from=("+Pi1+","+Pj1+"),to=("+Pi2+","+Pj2);//~@@@@I~//~@@@2M~
    	for (ii=Pi1,jj=Pj1;ii!=Pi2||jj!=Pj2;ii--,jj++)	//down-left//~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (ii==-1)  //beyond left edge                        //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
            	if (jj==S)                                         //~@@@@I~//~@@@2M~
                	return S;                                      //~@@@@I~//~@@@2M~
                else                                               //~@@@@I~//~@@@2M~
                {                                                  //~@@@2I~
                	jref=jj;                                       //~@@@2I~
                	return 2;                                    //~@@@@I~//~@@@2R~
                }                                                  //~@@@2I~
            }                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
        	if (jj==S)	//beyond bottom edge                       //~@@@@I~//~@@@2M~
            {                                                      //~@@@2I~
                iref=ii;                                           //~@@@2I~
                return 1;                                          //~@@@2R~
            }                                                      //~@@@2I~
            if (P.color(ii,jj)!=0)          //blocked              //~@@@@I~//~@@@2M~
                return S;  //unreachable                           //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        return 0;	//reached Pi2,Pj2                              //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private int isOnPiecePathBishopUR(int Pi1,int Pj1,int Pi2,int Pj2)//~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
//    	int rc;                                                    //~@@@@I~//~@@@2M~
        int ii,jj;                                                 //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("PathBishop-UR from=("+Pi1+","+Pj1+"),to=("+Pi2+","+Pj2);//~@@@@I~//~@@@2M~
    	for (ii=Pi1,jj=Pj1;ii!=Pi2||jj!=Pj2;ii++,jj--)	//up-right //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (ii==S)	//beyond right edge                        //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
            	if (jj==-1)	//corner                               //~@@@@I~//~@@@2M~
                	return S;                                      //~@@@@I~//~@@@2M~
                else                                               //~@@@@I~//~@@@2M~
                {                                                  //~@@@2I~
                	jref=jj;                                       //~@@@2I~
                	return 2;                                      //~@@@2R~
                }                                                  //~@@@2I~
            }                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
        	if (jj==-1)	//beyond upper edge                        //~@@@@I~//~@@@2M~
            {                                                      //~@@@2I~
                iref=ii;                                           //~@@@2I~
                return 1;                                          //~@@@2R~
            }                                                      //~@@@2I~
            if (P.color(ii,jj)!=0)          //blocked              //~@@@@I~//~@@@2M~
                return S;  //unreachable                           //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        return 0;	//reached Pi2,Pj2                              //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private int isOnPiecePathBishopUL(int Pi1,int Pj1,int Pi2,int Pj2)//~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
//    	int rc;                                                    //~@@@@I~//~@@@2M~
        int ii,jj;                                                 //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("PathBishop-UL from=("+Pi1+","+Pj1+"),to=("+Pi2+","+Pj2);//~@@@@I~//~@@@2M~
    	for (ii=Pi1,jj=Pj1;ii!=Pi2||jj!=Pj2;ii--,jj--)	//up-left  //~@@@@I~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (ii==-1)  //beyond left edge                        //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
            	if (jj==-1)                                        //~@@@@I~//~@@@2M~
                	return S;                                      //~@@@@I~//~@@@2M~
                else                                               //~@@@@I~//~@@@2M~
                {                                                  //~@@@2I~
                	jref=jj;                                       //~@@@2I~
                	return 2;                                      //~@@@2R~
                }                                                  //~@@@2I~
            }                                                      //~@@@@I~//~@@@2M~
            else                                                   //~@@@@I~//~@@@2M~
        	if (jj==-1)	//beyond bottom edge                       //~@@@@I~//~@@@2M~
            {                                                      //~@@@2I~
                iref=ii;                                           //~@@@2I~
                return 1;                                          //~@@@2R~
            }                                                      //~@@@2I~
            if (P.color(ii,jj)!=0)          //blocked              //~@@@@I~//~@@@2M~
                return S;  //unreachable                           //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        return 0;	//reached Pi2,Pj2                              //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private boolean isOnPiecePathKnight(int Pi,int Pj)             //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    	boolean rc=true;                                           //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
//*Down Right                                                      //~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,1,2)) //down right//~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,2,1)) //down right//~@@@@I~//~@@@2M~
//*Down Left                                                       //~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,-1,2)) //down right//~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,-2,1)) //down right//~@@@@I~//~@@@2M~
//*Up Right                                                        //~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,1,-2)) //down right//~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,2,-1)) //down right//~@@@@I~//~@@@2M~
//*Up Left                                                         //~@@@@I~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,-1,-2)) //down right//~@@@@R~//~@@@2M~
		if (!isOnPiecePathKnightSub(iSelected,jSelected,Pi,Pj,-2,-1)) //down right//~@@@@R~//~@@@2M~
        	rc=false;                                              //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("PathKnight rc="+rc+",from=("+iSelected+","+jSelected+"),to=("+Pi+","+Pj+")");//~@@@@I~//~@@@2M~
        return rc;                                                 //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@@I~//~@@@2M~
	private boolean isOnPiecePathKnightSub(int Pi1,int Pj1,int Pi2,int Pj2,int Pistep,int Pjstep)//~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
    	boolean rc=true;                                               //~@@@@I~//~@@@2M~
        int ii,jj;                                                 //~@@@@I~//~@@@2M~
    //**********************                                       //~@@@@I~//~@@@2M~
        if (Dump.Y) Dump.println("PathKnightSub from=("+Pi1+","+Pj1+"),to=("+Pi2+","+Pj2+",step=("+Pistep+","+Pjstep);//~@@@@I~//~@@@2M~
    	for (ii=Pi1+Pistep,jj=Pj1+Pjstep;ii!=Pi2||jj!=Pj2;ii+=Pistep,jj+=Pjstep)	//down-right//~@@@@R~//~@@@2M~
        {                                                          //~@@@@I~//~@@@2M~
        	if (ii>=S||ii<0||jj>=S||jj<0)	//beyond right edge    //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
            	rc=false;                                          //~@@@@I~//~@@@2M~
            	break;                                             //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
            if (P.color(ii,jj)!=0)          //blocked              //~@@@@I~//~@@@2M~
            {                                                      //~@@@@I~//~@@@2M~
            	rc=false;                                          //~@@@@I~//~@@@2M~
                break;                                             //~@@@@I~//~@@@2M~
            }                                                      //~@@@@I~//~@@@2M~
        }                                                          //~@@@@I~//~@@@2M~
        return rc;	//reached Pi2,Pj2                              //~@@@@I~//~@@@2M~
    }                                                              //~@@@@I~//+@@@2M~                                                                //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@2I~
	@Override                                                      //~@@@2I~
	protected void capturePiece(int Pi,int Pj)                     //~@@@2R~
    {                                                              //~@@@2I~
    //**********************                                       //~@@@2M~
        Arrays.fill(markCaptured,0);                               //~@@@2M~
        swCaptured=false;                                          //~@@@2I~
        int c=-P.color(Pi,Pj);                                     //~@@@2I~
        if (Pi>0)                                                  //~@@@2I~
	        capturePieceHV(c,Pi-1,Pj,-1,0);	//to Left;             //~@@@2I~
        if (Pi<S)                                                  //~@@@2I~
	        capturePieceHV(c,Pi+1,Pj,1,0);	//to Right             //~@@@2I~
        if (Pj>0)                                                  //~@@@2I~
	        capturePieceHV(c,Pi,Pj-1,0,-1);	//to Top               //~@@@2I~
        if (Pj<S)                                                  //~@@@2I~
	        capturePieceHV(c,Pi,Pj+1,0,1);	//to Bottom            //~@@@2I~
        capturePieceCorner(c,Pi,Pj);                               //~@@@2I~
        if (swCaptured)                                            //~@@@2I~
        	for (int ii=0; ii<S; ii++)                             //~@@@2M~
        		for (int jj=0; jj<S; jj++)                         //~@@@2M~
        			if (markCaptured[ii*S+jj]==1)                  //~@@@2M~
        				markPieceCaptured(ii,jj);                  //~@@@2M~
        if (captured!=0)                                           //~@@@2I~
        {                                                          //~@@@2I~
        	GF.setBlock(true);                                     //~@@@2R~
			requestDeleteCapturedPiece(captured/*firsttime*/);	//draw 1 by 1//~@@@2R~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
        	if (swGameover2)                                       //~@@@2R~
            	GF.gameover(1/*diff*/,c/*winner*/);               //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*hrizontal or vertical sandwitch chk                             //~@@@2I~
//**************************************************************** //~@@@2I~
	protected void capturePieceHV(int Pcolor,int Pi,int Pj,int Pistep,int Pjstep)//~@@@2I~
    {                                                              //~@@@2I~
    	int ii,jj,ii2,jj2,c;                                       //~@@@2I~
    //**********************                                       //~@@@2I~
		for (ii=Pi,jj=Pj;ii>=0 && ii<S && jj>=0 && jj<S;ii+=Pistep,jj+=Pjstep)//~@@@2I~
        {                                                          //~@@@2I~
			c=P.color(ii,jj);                                      //~@@@2I~
			if (c==0)                                              //~@@@2I~
            	break;                                             //~@@@2I~
            if (c!=Pcolor)	//sandwicthed                          //~@@@2I~
            {                                                      //~@@@2I~
            	for (ii2=Pi,jj2=Pj;!(ii2==ii && jj2==jj);ii2+=Pistep,jj2+=Pjstep)//~@@@2R~
                {                                                  //~@@@2I~
//                    markPieceCaptured(ii2,jj2);    //mark captured//~@@@2R~
                      markCaptured[ii2*S+jj2]=1;                   //~@@@2I~
                      swCaptured=true;                             //~@@@2I~
                }                                                  //~@@@2I~
            	break;                                             //~@@@2I~
            }                                                      //~@@@2I~
        }                                                          //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*4 corner/border capture chk                                     //~@@@2R~
//**************************************************************** //~@@@2I~
//    protected void capturePieceCorner(int Pcolor,int Pi,int Pj)  //~@@@2R~
//    {                                                            //~@@@2R~
////      int yourcolor;                                           //~@@@2R~
//    //**********************                                     //~@@@2R~
//        Arrays.fill(markCaptured,0);                             //~@@@2R~
////      yourcolor=-Pcolor;                                       //~@@@2R~
////        if (P.color(0,0)==Pcolor)   //left-top is opponent     //~@@@2R~
////            if ((Pi==0 && Pj==1 && P.color(1,0)==yourcolor)    //~@@@2R~
////            ||  (Pi==1 && Pj==0 && P.color(0,1)==yourcolor))   //~@@@2R~
////                markPieceCaptured(0,0);    //mark captured     //~@@@2R~
////        if (P.color(0,S-1)==Pcolor) //left-bottom is opponent  //~@@@2R~
////            if ((Pi==0 && Pj==S-2 && P.color(1,S-1)==yourcolor)//~@@@2R~
////            ||  (Pi==1 && Pj==S-1 && P.color(0,S-2)==yourcolor))//~@@@2R~
////                markPieceCaptured(0,S-1);    //mark captured   //~@@@2R~
////        if (P.color(S-1,0)==Pcolor) //right-top is opponent    //~@@@2R~
////            if ((Pi==S-1 && Pj==1 && P.color(S-2,0)==yourcolor)//~@@@2R~
////            ||  (Pi==S-2 && Pj==0 && P.color(S-1,1)==yourcolor))//~@@@2R~
////                markPieceCaptured(S-1,0);    //mark captured   //~@@@2R~
////        if (P.color(S-1,S-1)==Pcolor)   //right-bottom is opponent//~@@@2R~
////            if ((Pi==S-1 && Pj==S-2 && P.color(S-2,S-1)==yourcolor)//~@@@2R~
////            ||  (Pi==S-2 && Pj==S-1 && P.color(S-1,S-2)==yourcolor))//~@@@2R~
////                markPieceCaptured(S-1,S-1);    //mark captured //~@@@2R~
//        if (P.color(0,0)==Pcolor)   //left-top is opponent       //~@@@2R~
//            setCaptured(false,Pcolor,0,0,1/*istep*/,1/*jstep*/,Pi,Pj);      //chk to right//~@@@2R~
//        if (P.color(0,S-1)==Pcolor)   //left-bottom is opponent  //~@@@2R~
//            setCaptured(false,Pcolor,0,S-1,1/*istep*/,-1/*jstep*/,Pi,Pj);//~@@@2R~
//        if (P.color(S-1,0)==Pcolor)   //right-top is opponent    //~@@@2R~
//            setCaptured(false,Pcolor,S-1,0,-1/*istep*/,1/*jstep*/,Pi,Pj);       //chk to right//~@@@2R~
//        if (P.color(S-1,S-1)==Pcolor)   //right-bottom is opponent//~@@@2R~
//            setCaptured(false,Pcolor,S-1,S-1,-1/*istep*/,-1/*jstep*/,Pi,Pj);        //chk to right//~@@@2R~
//    }                                                            //~@@@2R~
    private void capturePieceCorner(int Pcolor/*opponent*/,int i,int j)                   //~@@@2I~
    {                                                              //~@@@2I~
    // capture neighboring groups without liberties              //~@@@@R~//~@@@2I~
    // capture own group on suicide                              //~@@@@R~//~@@@2I~
        int c=Pcolor; //-P.color(i,j);    //opponent color                                 //~@@@@I~//~@@@2I~
        if (i>0) capturegroup(i-1,j,c);                        //~@@@@R~//~@@@2I~
        if (j>0) capturegroup(i,j-1,c);                        //~@@@@R~//~@@@2I~
        if (i<S-1) capturegroup(i+1,j,c);                      //~@@@@R~//~@@@2I~
        if (j<S-1) capturegroup(i,j+1,c);                     //~@@@@R~//~@@@2I~
//        if (P.color(i,j)==-c)                                    //~@@@@R~//~@@@2R~
//        {   capturegroup(i,j,-c);                              //~@@@@R~//~@@@2R~
//        }                                                        //~@@@@R~//~@@@2R~
//        if (captured==1 && P.count(i,j)!=1)                      //~@@@@R~//~@@@2I~
//            captured=0;                                          //~@@@@R~//~@@@2I~
//        if (!GF.getParameter("korule",true)) captured=0;         //~@@@@R~//~@@@2I~
    }                                                            //~@@@@R~//~@@@2I~
    private void capturegroup (int i, int j, int c/*opponent*/)       //~@@@@R~//~@@@2I~
    // Used by capture to determine the state of the groupt at (i,j)//~@@@@R~//~@@@2I~
    // Remove it, if it has no liberties and note the removals   //~@@@@R~//~@@@2I~
    // as actions in the current node.                           //~@@@@R~//~@@@2I~
    {   int ii,jj;                                               //~@@@@R~//~@@@2I~
//        Action a;                                                //~@@@@R~//~@@@2I~
        if (Dump.Y)Dump.println("capturegroup Opponent color="+c+",i="+i+",j="+j+",color="+P.color(i,j));//~@@@2I~
        if (P.color(i,j)!=c) return;                             //~@@@@R~//~@@@2I~
        if (!P.markgrouptest(i,j,0)) // liberties?               //~@@@@R~//~@@@2I~
        {                                                          //~@@@2R~
        	if (Dump.Y)Dump.println("capturegroup liberty=false"); //~@@@2I~
        	for (ii=0; ii<S; ii++)                                 //~@@@2I~
                for (jj=0; jj<S; jj++)                           //~@@@@R~//~@@@2I~
                {   if (P.marked(ii,jj))                         //~@@@@R~//~@@@2I~
//                    {   n.addchange(new Change(ii,jj,P.color(ii,jj),P.number(ii,jj)));//~@@@@R~//~@@@2I~
                    {                                              //~@@@2I~
//                        if (P.color(ii,jj)>0) { Pb++; n.Pb++; }  //~@@@@R~//~@@@2I~
//                        else { Pw++; n.Pw++; }                   //~@@@@R~//~@@@2I~
//                        P.color(ii,jj,0);                        //~@@@@R~//~@@@2I~
//                        update(ii,jj); // redraw the field (offscreen)//~@@@@R~//~@@@2I~
//                        captured++;                              //~@@@@R~//~@@@2R~
//                        capturei=ii; capturej=jj;                //~@@@@R~//~@@@2I~
                        markCaptured[ii*S+jj]=1;
                        swCaptured=true;                          //~@@@2R~
			        	if (Dump.Y)Dump.println("capturegroup marked");//~@@@2I~
                    }                                            //~@@@@R~//~@@@2I~
                }                                                //~@@@@R~//~@@@2I~
        }                                                        //~@@@@R~//~@@@2I~
    }                                                            //~@@@@R~//~@@@2I~
////****************************************************************//~@@@2R~
//    private void setCaptured(boolean Pset,int Pcolor/*opponent*/,int Pi,int Pj,int Pdesti,int Pdestj,int Pci,int Pcj)//~@@@2R~
//    {                                                            //~@@@2R~
//        int ii,jj,kk,color,posi,posj,posii,posjj;                //~@@@2R~
//        boolean free,captured=true,now=false;                    //~@@@2R~
//    //********************************                           //~@@@2R~
//        if (Dump.Y)Dump.println("setCaptured set="+Pset+",color="+Pcolor+",i="+Pi+",j="+Pj+",cposi="+Pci+",cposj="+Pcj);//~@@@2R~
//        for (kk=0,posi=Pi,posj=Pj;kk<S;kk++,posi+=Pdesti,posj+=Pdestj)//~@@@2R~
//        {                                                        //~@@@2R~
//            color=P.color(posi,posj);                            //~@@@2R~
//            if (color!=Pcolor)                                   //~@@@2R~
//                break;                                           //~@@@2R~
//            if (isFree(posi,posj))                               //~@@@2R~
//            {                                                    //~@@@2R~
//                captured=false;                                  //~@@@2R~
//                break;                                           //~@@@2R~
//            }                                                    //~@@@2R~
//            if (Pset)                                            //~@@@2R~
//                markPieceCaptured(posi,posj);                    //~@@@2R~
//            free=false;                                          //~@@@2R~
//            for (ii=kk+1,posii=posi+Pdesti,posjj=posj;ii<S;ii++,posii+=Pdesti)  //horijontal//~@@@2R~
//            {                                                    //~@@@2R~
//                color=P.color(posii,posjj);                      //~@@@2R~
//                if (color==-Pcolor)                              //~@@@2R~
//                {                                                //~@@@2R~
//                    if (posii==Pci && posjj==Pcj)                //~@@@2R~
//                        now=true;                                //~@@@2R~
//                    break;                                       //~@@@2R~
//                }                                                //~@@@2R~
//                if (Pset)                                        //~@@@2R~
//                    markPieceCaptured(posii,posjj);              //~@@@2R~
//                else                                             //~@@@2R~
//                if (isFree(posii,posjj))                         //~@@@2R~
//                {                                                //~@@@2R~
//                    free=true;                                   //~@@@2R~
//                    break;                                       //~@@@2R~
//                }                                                //~@@@2R~
//            }                                                    //~@@@2R~
//            if (free)                                            //~@@@2R~
//            {                                                    //~@@@2R~
//                captured=false;                                  //~@@@2R~
//                break;                                           //~@@@2R~
//            }                                                    //~@@@2R~
//            for (jj=kk+1,posii=posi,posjj=posj+Pdestj;jj<S;ii++,posjj+=Pdestj)  //horijontal//~@@@2R~
//            {                                                    //~@@@2R~
//                color=P.color(posii,posjj);                      //~@@@2R~
//                if (color==-Pcolor)                              //~@@@2R~
//                {                                                //~@@@2R~
//                    if (posii==Pci && posjj==Pcj)                //~@@@2R~
//                        now=true;                                //~@@@2R~
//                    break;                                       //~@@@2R~
//                }                                                //~@@@2R~
//                if (Pset)                                        //~@@@2R~
//                    markPieceCaptured(posii,posjj);              //~@@@2R~
//                else                                             //~@@@2R~
//                if (isFree(posii,posjj))                         //~@@@2R~
//                {                                                //~@@@2R~
//                    free=true;                                   //~@@@2R~
//                    break;                                       //~@@@2R~
//                }                                                //~@@@2R~
//            }                                                    //~@@@2R~
//            if (free)                                            //~@@@2R~
//            {                                                    //~@@@2R~
//                captured=false;                                  //~@@@2R~
//                break;                                           //~@@@2R~
//            }                                                    //~@@@2R~
//        }                                                        //~@@@2R~
//        if (Dump.Y)Dump.println("setCaptured captured="+captured+",now="+now);//~@@@2R~
//        if (captured)                                            //~@@@2R~
//            if (!Pset && now)                                    //~@@@2R~
//                setCaptured(true,Pcolor/*opponent*/,Pi,Pj,Pdesti,Pdestj,Pci,Pcj);//~@@@2R~
//    }                                                            //~@@@2R~
////****************************************************************//~@@@2R~
//    private boolean isFree(int Pi,int Pj)                        //~@@@2R~
//    {                                                            //~@@@2R~
//        boolean free=false;                                      //~@@@2R~
//    //******************************                             //~@@@2R~
//        if (Pi!=0 && P.color(Pi-1,Pj)==0)                        //~@@@2R~
//            free=true;                                           //~@@@2R~
//        else                                                     //~@@@2R~
//        if (Pi!=S-1 && P.color(Pi+1,Pj)==0)                      //~@@@2R~
//            free=true;                                           //~@@@2R~
//        else                                                     //~@@@2R~
//        if (Pj!=0 && P.color(Pi,Pj-1)==0)                        //~@@@2R~
//            free=true;                                           //~@@@2R~
//        else                                                     //~@@@2R~
//        if (Pj!=S-1 && P.color(Pi,Pj+1)==0)                      //~@@@2R~
//            free=true;                                           //~@@@2R~
//        if (Dump.Y)Dump.println("isFree="+free+",i="+Pi+",j="+Pj);//~@@@2R~
//        return free;                                             //~@@@2R~
//    }                                                            //~@@@2R~
////****************************************************************//~@@@2R~
//    private void setCaptured(int Pcolor/*opponent*/,int Pi,int Pj,int Pstepi,int Pstepj,int Pcposi,int Pcposj)//~@@@2R~
//    {                                                            //~@@@2R~
//        int ii,jj,posi,posj;                                     //~@@@2R~
//    //********************************                           //~@@@2R~
//        for (ii=0,posi=Pi; ii<S; ii++,posi+=Pstepi)              //~@@@2R~
//        {                                                        //~@@@2R~
//            for (jj=0,posj=Pj; jj<S; jj++,posj+=Pstepj)          //~@@@2R~
//            {                                                    //~@@@2R~
//                if (P.color(posi,posj)==-Pcolor)//my color       //~@@@2R~
//                    continue;                                    //~@@@2R~
//                if (notCaptured(Pcolor,posi,posj,Pstepi,Pstepj)) //~@@@2R~
//                    break;                                       //~@@@2R~
////                    markPieceCaptured(posi,posj);              //~@@@2R~
//                    if (Dump.Y) Dump.println("@@@@ captured i="+posi+",j="+posj);//~@@@2R~
//                                                                 //~@@@2R~
//            }                                                    //~@@@2R~
//            if (jj<S)                                            //~@@@2R~
//                break;                                           //~@@@2R~
//        }                                                        //~@@@2R~
//    }                                                            //~@@@2R~
////****************************************************************//~@@@2R~
//    private boolean notCaptured(int Pcolor/*opponent*/,int Pi,int Pj,int Pstepi,int Pstepj)//~@@@2R~
//    {                                                            //~@@@2R~
//        int ii,jj,col;                                           //~@@@2R~
//    //******************************                             //~@@@2R~
//        col=P.color(Pi,Pj);                                      //~@@@2R~
//        if (Dump.Y)Dump.println("notCaptured col="+col+",Pcolor="+Pcolor+",i="+Pi+",j="+Pj+",stepi="+Pstepi+",stepj="+Pstepj);//~@@@2R~
//        if (col==0)                                              //~@@@2R~
//            return true;    //free                               //~@@@2R~
//        if (col!=Pcolor)        //mycolor                        //~@@@2R~
//            return false;                                        //~@@@2R~
//        ii=Pi+Pstepi;                                            //~@@@2R~
//        if (ii>=0 && ii<S)                                       //~@@@2R~
//            if (notCaptured(Pcolor,ii,Pj,Pstepi,Pstepj))         //~@@@2R~
//                return true;                                     //~@@@2R~
//        jj=Pj+Pstepj;                                            //~@@@2R~
//        if (jj>=0 && jj<S)                                       //~@@@2R~
//            if (notCaptured(Pcolor,Pi,jj,Pstepi,Pstepj))         //~@@@2R~
//                return true;                                     //~@@@2R~
//        if (Dump.Y)Dump.println("notCaptured return false");     //~@@@2R~
//        return false;                                            //~@@@2R~
//    }                                                            //~@@@2R~
//**************************************************************** //~@@@2I~
    private void markPieceCaptured(int i,int j)                   //~@@@2R~
    {                                                              //~@@@2I~
    	if (Dump.Y) Dump.println("markPieceCaptured i="+i+",j="+j);//~@@@2I~
        P.setCaptured(i,j,true);    //mark captured                //~@@@2R~
        captured++;                                                //~@@@2R~
		update(i,j);                                               //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
    @Override //Canvas
    protected void deleteCapturedPiece(int Premains)               //~@@@2R~
    {                                                              //~@@@2I~
        for (int ii=0; ii<S; ii++)                                     //~@@@2I~
            for (int jj=0; jj<S; jj++)                                 //~@@@2I~
            {                                                      //~@@@2I~
//          	if (P.captured(ii,jj))                             //~@@@2R~
            	if (P.captured(ii,jj) && P.color(ii,jj)!=0)//not yet deleted//~@@@2I~
                {                                                  //~@@@2I~
                    captured--;                                    //~@@@2I~
//                  P.setCaptured(ii,jj,false);    //clear later,at next select//~@@@2R~
		            swDeletedCaptured=true;                        //~@@@2I~
                    deleteCapturedPiece(ii,jj);	                   //~@@@2R~
                    if (captured>0)                                //~@@@2I~
	                    requestDeleteCapturedPiece(captured/*remaining*/);//~@@@2R~
                    else                                           //~@@@2I~
                    {                                              //~@@@2I~
        				GF.setBlock(false);                        //~@@@2I~
        				GF.gameover(0/*chk*/,0);                     //~@@@2I~
                    }                                              //~@@@2I~
                }                                                  //~@@@2I~
            }                                            //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*clear captured mark at next selec                               //~@@@2I~
//**************************************************************** //~@@@2I~
    protected void clearCapturedMark()                             //~@@@2I~
    {                                                              //~@@@2I~
        for (int ii=0; ii<S; ii++)                                 //~@@@2I~
            for (int jj=0; jj<S; jj++)                             //~@@@2I~
            {                                                      //~@@@2I~
            	if (P.captured(ii,jj) && P.color(ii,jj)==0)// deleted//~@@@2I~
                {                                                  //~@@@2I~
	            	P.setCaptured(ii,jj,false);                           //~@@@2I~
					update(ii,jj);                                   //~@@@2I~
                }                                                  //~@@@2I~
            }                                                      //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//* callbak drawCapturedPiece after litte sleep time               //~@@@2I~
//**************************************************************** //~@@@2I~
    public void requestDeleteCapturedPiece(int Premains)           //~@@@2R~
    {                                                              //~@@@2I~
    	drawCaptured(Premains);	//to Canvas through GoFrame blocking next input//~@@@2R~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*draw 1 by 1 captured piece                                      //~@@@2I~
//**************************************************************** //~@@@2I~
    public void deleteCapturedPiece(int Pi,int Pj)                 //~@@@2R~
    {                                                              //~@@@2I~
    	int color=P.color(Pi,Pj);                                  //~@@@2I~
    	int piece=P.piece(Pi,Pj);                                  //~@@@2I~
		deletemousecPiece(Pi,Pj);	//delete piece from board      //~@@@2I~
		infoCaptured(Pi,Pj,piece,color);                           //~@@@2R~
    	GF.updateCapturedList(color,piece);	//add to captured List //~@@@2R~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*gameover2 warning                                               //~1033R~
//*diff>0 opponent advantage                                       //~1033I~
//**************************************************************** //~@@@2I~
//  public void reachedGameover2(boolean Preached)                 //~@@@2R~//~1033R~
    public void reachedGameover2(boolean Preached,int Pdiff)       //~1033I~
    {                                                              //~@@@2I~
    	String msg;                                                //~1033I~
    	swGameover2=Preached;                                      //~@@@2I~
        if (Preached)                                              //~1033I~
        {                                                          //~1033I~
	        msg=AG.resource.getString(R.string.WarnGameover2);	   //~1033I~
	        CGF.appendComment(msg+"\n");                           //~1033I~
	        msg=AG.resource.getString(R.string.WarnGameover2Toast);//~1033I~
    		AView.showToast(msg);                                  //~1033I~
        }                                                          //~1033I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
	@Override//Board                                               //~@@@2M~
	protected void drawCapturedPieceMark(Graphics Pg,int i,int j)//~@@@2I~
	{                                                              //~@@@2I~
		int xi=O+OTU+i*D;                                          //~@@@2I~
		int xj=O+OTU+j*D;
		int h=D/4;//~@@@2I~
    	Pg.drawLine(AG.capturedColor,2,xi+D/2-h,xj+D/2-h,xi+D/2+h,xj+D/2+h);//~@@@2R~
        Pg.drawLine(AG.capturedColor,2,xi+D/2+h,xj+D/2-h,xi+D/2-h,xj+D/2+h);//~@@@2R~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*partner send piece selected                                   * //~@@@2I~
//**************************************************************** //~@@@2I~
	public void receiveSelected(int Pi,int Pj)                     //~@@@2R~
    {                                                              //~@@@2I~
        if (swDeletedCaptured)                                     //~v101I~
        {                                                          //~v101I~
            swDeletedCaptured=false;                               //~v101I~
            clearCapturedMark();    //for the case captured partner piece,next event is partner's move//~v101I~
        }                                                          //~v101I~
    	updateSelected(Pi,Pj);                                      //~@@@2I~
		showinformation();                                         //~@@@2I~
//  	copy(); // show position                                   //~@@@2I~//+1A3aR~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*draw selected piece                                             //~@@@2I~
//**************************************************************** //~@@@2I~
    void updateSelected(int Pi,int Pj)                             //~@@@2I~
    {                                                              //~@@@2I~
        swSelected=false;                                          //~@@@2I~
        update(iSelected,jSelected);    //clear mark on old square //~@@@2I~
        iSelected=Pi; jSelected=Pj;                                //~@@@2I~
        swSelected=true;                                           //~@@@2I~
        update(iSelected,jSelected);    //clear mark on old square //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~1035I~
//*clear last moved from                                           //~1035I~
//**************************************************************** //~1035I~
	@Override //Board                                              //~1035I~
    public void resetMovedFrom()                                   //~1035I~
    {                                                              //~1035I~
    	if (Dump.Y) Dump.println("CB:resetMovedFrom sw="+swCursorMovedFrom+",lastifrom="+lastifrom+",j="+lastjfrom);//~1035I~
    	if (swCursorMovedFrom)                                     //~1035I~
        {                                                          //~1035I~
            swCursorMovedFrom=false;                               //~1035I~
        	update(lastifrom,lastjfrom);                           //~1035I~
        }                                                          //~1035I~
    }                                                              //~1035I~
//**************************************************************** //~@@@2M~
	private void errNoPath(int Pi1,int Pj1,int Ppiece,int Pcolor,int Pi2,int Pj2)                                       //~@@@@I~//~@@@2R~
    {                                                              //~@@@@I~//~@@@2M~
        if ((GameOptions & GameQuestion.GAMEOPT_NOTREMOVABLE)!=0)  //~@@@2I~
        {                                                          //~@@@2I~
        	errPass(Pi1,Pj1,Ppiece,Pcolor,Pi2,Pj2);                //~@@@2R~
            return;                                                //~@@@2I~
        }                                                          //~@@@2I~
        String msg=AG.resource.getString(R.string.ErrNoPathPiece); //~@@@2M~
    	AView.showToast(msg);                  //~@@@@I~           //~@@@2M~
        String pos1=Field.coordinate(Pi1,Pj1,S,YourColor);         //~@@@2M~
        String pos2=Field.coordinate(Pi2,Pj2,S,YourColor);         //~@@@2M~
        String piece=CGF.pieceName(Ppiece);                        //~@@@2M~
        msg=msg+": "+piece+" "+pos1+" --> "+pos2;                  //~@@@2M~
        CGF.appendComment(msg+"\n");                               //~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
//**************************************************************** //~@@@2I~
	private void errPass(int Pi1,int Pj1,int Ppiece,int Pcolor,int Pi2,int Pj2)//~@@@2R~
    {                                                              //~@@@2I~
        String msg=AG.resource.getString(R.string.ErrPass);        //~@@@2I~
    	AView.showToast(msg);                                      //~@@@2I~
        String pos1=Field.coordinate(Pi1,Pj1,S,YourColor);         //~@@@2I~
        String pos2=Field.coordinate(Pi2,Pj2,S,YourColor);         //~@@@2I~
        String piece=CGF.pieceName(Ppiece);                        //~@@@2I~
        String color=Pcolor>0 ? AG.BlackSign : AG.WhiteSign;       //~@@@2I~
        msg="["+Integer.toString(moveNumber+1)+"] "+color+" ("+msg+") "+pos1+" --> "+pos2+ " ("+piece+")";//~@@@2R~
        CGF.appendComment(msg+"\n");                               //~@@@2I~
        CGF.errPass();                                             //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
//*pass notified from partner (@@pass)                             //~@@@2I~
//**************************************************************** //~@@@2I~
	public void notifiedPass()                                    //~@@@2I~
    {                                                              //~@@@2I~
        String msg=AG.resource.getString(R.string.ErrPass);        //~@@@2I~
    	AView.showToast(msg);                                      //~@@@2I~
        String color=maincolor()>0 ? AG.BlackSign : AG.WhiteSign;  //~@@@2I~
        msg="["+Integer.toString(moveNumber+1)+"] "+color+" ("+msg+")";//~@@@2I~
        CGF.appendComment(msg+"\n");                               //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2M~
	@Override //Board                                                  //~@@@2I~
	protected void infoMoved(int Pi1,int Pj1,int Ppiece,int Pcolor,int Pi2,int Pj2)//~@@@2R~
    {                                                              //~@@@2M~
        String pos1=Field.coordinate(Pi1,Pj1,S,YourColor);         //~@@@2M~
        String pos2=Field.coordinate(Pi2,Pj2,S,YourColor);         //~@@@2M~
        String piece=CGF.pieceName(Ppiece);                         //~@@@2M~
        String color=Pcolor>0 ? AG.BlackSign : AG.WhiteSign;       //~@@@2R~
        String msg="["+Integer.toString(moveNumber)+"] "+color+" "+pos1+" --> "+pos2+ " ("+piece+")";//~@@@2R~
        CGF.appendComment(msg+"\n");                               //~@@@2M~
    }                                                              //~@@@2M~
//**************************************************************** //~@@@2I~
	private void infoCaptured(int Pi,int Pj,int Ppiece,int Pcolor) //~@@@2R~
    {                                                              //~@@@2I~
        String msg=AG.resource.getString(R.string.InfoCaptured);   //~@@@2R~
//      String pos=Field.coordinate(Pi,Pj,S,Pcolor);               //~@@@2I~//~1030R~
        String pos=Field.coordinate(Pi,Pj,S,YourColor);            //~1030I~
        String piece=CGF.pieceName(Ppiece);                        //~@@@2I~
        String color=Pcolor>0 ? AG.BlackSign : AG.WhiteSign;       //~@@@2I~
        msg=color+" "+msg+" "+pos+" ("+piece+")";                  //~@@@2R~
    	JagoSound.play("piececaptured",false/*not change to beep when beeponly option is on*/);//~@@@@I~//~@@@2I~
        CGF.appendComment(msg+"\n");                               //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@@I~//~@@@2M~
	private void errMissTouch(int i,int j)                                    //~@@@@I~//~@@@2M~
    {                                                              //~@@@@I~//~@@@2M~
        String msg=AG.resource.getString(R.string.ErrNotReselectable)//~@@@2M~
    				+Field.coordinate(i,j,S,YourColor);            //~@@@2M~
    	AView.showToast(msg);                                      //~@@@2M~
        CGF.appendComment(msg+"\n");                               //~@@@2M~
    }                                                              //~@@@@I~//~@@@2M~
////****************************************************************//~@@@2R~
//    private void errNotYourColor(int Pcolor/*next Color*/)       //~@@@2R~
//    {                                                            //~@@@2R~
//        String color=Pcolor>0 ? AG.BlackSign : AG.WhiteSign;     //~@@@2R~
//        String msg=AG.resource.getString(R.string.ErrNotYourColor)+color;//~@@@2R~
//        AView.showToast(msg);                                    //~@@@2R~
//        CGF.appendComment(msg+"\n");                             //~@@@2R~
//    }                                                            //~@@@2R~
//**************************************************************** //~@@@2I~
	private void errNotYourTurn(int Pcolor/*your color*/)          //~@@@2I~
    {                                                              //~@@@2I~
        String color=Pcolor>0 ? AG.BlackName : AG.WhiteName;       //~@@@2R~
        String msg=AG.resource.getString(R.string.ErrNotYourTurn,color);//~@@@2R~
    	AView.showToast(msg);                                      //~@@@2I~
        CGF.appendComment(msg+"\n");                               //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~1038I~
	private void errWaitingPartnerResponse()                       //~1038I~
    {                                                              //~1038I~
    //*nothing to do(protect new seelction before get @@!move response)//~1038I~
    }                                                              //~1038I~
//**************************************************************** //~@@@2I~
	public void infoMsg(String Pmsg)                               //~@@@2R~
    {                                                              //~@@@2I~
    	AView.showToast(Pmsg);                                     //~@@@2I~
        CGF.appendComment(Pmsg+"\n");                              //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
	public void infoComment(String Pmsg)                           //~@@@2I~
    {                                                              //~@@@2I~
        CGF.appendComment(Pmsg+"\n");                              //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
	public void infoMsg(int Presid)                                //~@@@2R~
    {                                                              //~@@@2I~
    	String s=AG.resource.getString(Presid);                    //~@@@2I~
        infoMsg(s);                                                //~@@@2I~
    }                                                              //~@@@2I~
//**************************************************************** //~@@@2I~
	public void infoMsg(String Ps,int Presid)                      //~@@@2I~
    {                                                              //~@@@2I~
    	String s=Ps+AG.resource.getString(Presid);                 //~@@@2I~
        infoMsg(s);                                                //~@@@2I~
    }                                                              //~@@@2I~
}
