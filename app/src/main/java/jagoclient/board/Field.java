//*CID://+@@@2R~:                             update#=   13;       //+@@@2R~
package jagoclient.board;

import com.Ahsv.AG;


// ************** Field **************

/**
A class to hold a single field in the game board.
Contains data for labels, numbers, marks etc. and
of course the color of the stone on the board.
<P>
It may contain a reference to a tree, which is a variation
starting at this (empty) board position.
<P>
The Mark field is used for several purposes, like marking
a group of stones or a territory.
*/

public class Field
{	int C; // the state of the field (-1,0,1), 1 is black
	boolean Mark; // for several purposes (see Position.markgroup)
	TreeNode T; // Tree that starts this variation
	int Letter; // Letter to be displayed
	String LabelLetter; // Strings from the LB tag.
	boolean HaveLabel; // flag to indicate there is a label
	int Territory; // For Territory counting
	int Marker; // emphasized field
	static final int NONE=0;
    static final int CROSS=1;                                    //~2C31R~//~@@@2R~
    static final int SQUARE=2;                                   //~2C31R~//~@@@2R~
    static final int TRIANGLE=3;                                 //~2C31R~//~@@@2R~
    static final int CIRCLE=4;                                   //~2C31R~//~@@@2R~
	int Piece;                                                     //~@@@2I~
    static final int PAWN=0;                                       //~@@@2R~
    static final int BISHOP=1;                                     //~@@@2R~
    static final int KNIGHT=2;                                     //~@@@2R~
	boolean Captured;                                              //~@@@2I~
	boolean PieceSet;                                              //~@@@2I~
	int Number;

	//** set the field to 0 initially */
	public Field ()
	{	C=0;
		T=null;
		Letter=0;
		HaveLabel=false;
		Number=0;
        Piece=0;                                                   //~@@@2R~
        Captured=false;                                            //~@@@2I~
        PieceSet=false;                                            //~@@@2I~
	}

	/** return its state */
	int color ()
	{	return C;
	}

	/** set its state */
	void color (int c)
	{	C=c;
		Number=0;
        if (C==0)                                                  //~@@@2I~
	        PieceSet=false;                                        //~@@@2I~
	}
//**********************************************                   //~@@@2I~
	public void piece(int Ppiece)                                  //~@@@2I~
	{                                                              //~@@@2I~
		Piece=Ppiece;                                              //~@@@2I~
        PieceSet=true;                                             //~@@@2I~
	}                                                              //~@@@2I~
//**********************************************                   //~@@@2I~
	public boolean isSetPiece()                                    //~@@@2I~
	{                                                              //~@@@2I~
        return PieceSet;                                           //~@@@2I~
	}                                                              //~@@@2I~
//**********************************************                   //~@@@2I~
	public int piece()                                             //~@@@2I~
	{                                                              //~@@@2I~
		return Piece;                                              //~@@@2I~
	}                                                              //~@@@2I~
//**********************************************                   //~@@@2I~
	public void captured(boolean Pcaptured)                        //~@@@2I~
	{                                                              //~@@@2I~
		Captured=Pcaptured;                                        //~@@@2I~
	}                                                              //~@@@2I~
//**********************************************                   //~@@@2I~
	public boolean captured()                                          //~@@@2I~
	{                                                              //~@@@2I~
		return Captured;                                           //~@@@2I~
	}                                                              //~@@@2I~
//**********************************************                   //~@@@2I~
	
	final static int az='z'-'a';

	/** return a string containing the coordinates in SGF */
	static String string (int i, int j)
	{	char[] a=new char[2];
		if (i>=az) a[0]=(char)('A'+i-az-1);
		else a[0]=(char)('a'+i); 
		if (j>=az) a[1]=(char)('A'+j-az-1);
		else a[1]=(char)('a'+j);
		return new String(a);
	}
//** Position string********************************************   //~@@@2I~
	static String coordinate (int i,int j,int Size,int Pcolor)     //~@@@2R~
	{                                                              //~@@@2I~
		char[] a=new char[2];                                      //~@@@2I~
		if (Size==AG.BOARDSIZE_SHOGI)                              //~@@@2I~
        {                                                          //~@@@2I~
        	if (Pcolor<0)	//white                                //~@@@2I~
            {	i=AG.BOARDSIZE_SHOGI-i-1; j=AG.BOARDSIZE_SHOGI-j-1;}//~@@@2I~
        	a[0]=AG.SshogiH.charAt(i);                             //~@@@2I~
        	a[1]=AG.SshogiV.charAt(j);                             //~@@@2I~
        }                                                          //~@@@2I~
		else                                                       //~@@@2R~
        {                                                          //~@@@2I~
        	if (Pcolor>0)	//black                                //~@@@2I~
            {	i=AG.BOARDSIZE_CHESS-i-1; j=AG.BOARDSIZE_CHESS-j-1;}//~@@@2I~
        	a[0]=AG.SchessH.charAt(i);                             //~@@@2I~
        	a[1]=AG.SchessV.charAt(j);                             //~@@@2I~
        }                                                          //~@@@2I~
		return new String(a);                                      //~@@@2I~
	}                                                              //~@@@2I~

	/** return a string containing the coordinates in SGF */
	static String coordinate (int i, int j, int s)
	{	if (s>25)
		{	return (i+1)+","+(s-j);
		}
		else
		{	if (i>=8) i++;
			return ""+(char)('A'+i)+(s-j);
		}
	}

	/** get the first coordinate from the SGF string */
	static int i (String s)
	{	if (s.length()<2) return -1;
		char c=s.charAt(0);
		if (c<'a')  return c-'A'+az+1;
		return c-'a';
	}

	/** get the second coordinate from the SGF string */
	static int j (String s)
	{	if (s.length()<2) return -1;
		char c=s.charAt(1);
		if (c<'a')  return c-'A'+az+1;
		return c-'a';
	}

	// modifiers:
	void mark (boolean f) { Mark=f; } // set Mark
	void tree (TreeNode t) { T=t; } // set Tree
	void marker (int f) { Marker=f; }
	void letter (int l) { Letter=l; }
	void territory (int c) { Territory=c; }
	void setlabel (String s) { HaveLabel=true; LabelLetter=s; }
	void clearlabel () { HaveLabel=false; }
	void number (int n) { Number=n; }

	// access functions:
	boolean mark () { return Mark; } // ask Mark
	int marker () { return Marker; }
	TreeNode tree () { return T; }
	int letter () { return Letter; }
	int territory () { return Territory; }
	boolean havelabel () { return HaveLabel; }
	String label () { return LabelLetter; }
	int number() { return Number; }
}
