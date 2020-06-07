//*CID://+1AabR~:                                   update#=   71; //~1AabR~
//****************************************************************************//~@@@1I~
//1Aab 2015/04/22 1Aa7 for local game                              //~1AabI~
//****************************************************************************//~@@@1I~
package jagoclient;                                                //~@@@2R~

import jagoclient.CloseConnection;
import jagoclient.Dump;
import jagoclient.Global;
import jagoclient.dialogs.Message;
import jagoclient.dialogs.Question;
import jagoclient.gui.CloseFrame;
import jagoclient.gui.MenuItemAction;
import jagoclient.partner.EndGameQuestion;
import jagoclient.partner.GameQuestion;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;                                                 //+1AabR~
import com.Ahsv.awt.Color;                                         //~v108R~
import com.Ahsv.awt.KeyEvent;                                      //~v108R~
import com.Ahsv.awt.KeyListener;                                   //~v108R~
import com.Ahsv.awt.MenuBar;                                       //~v108R~
import com.Ahsv.awt.Panel;                                         //~v108R~
import com.Ahsv.awt.Menu;                                          //~v108R~

import rene.util.list.ListClass;
import rene.util.list.ListElement;
import rene.util.parser.StringParser;


public class LocalFrame extends CloseFrame                              //~@@@2R~
{	                                                               //~v107I~
    public LocalGoFrame PGF;                                       //~@@@2I~
	String Dir;
	boolean Block;                                                 //~@@@2I~

    public LocalFrame (LocalGameQuestion Plgq)                     //~@@@2R~
	{                                                              //~@@@2R~
//		super("LocalFrame");                                       //~@@@2R~
  		super(0/*layout resid,having no frame*/,"LocalFrame");     //~@@@2I~
		int gameover=Plgq.gameover;                                     //~@@@2I~
		int gameover2=Plgq.gameover2;                              //~@@@2I~
		int totaltime=Plgq.totaltime;                              //~@@@2R~
		int extratime=Plgq.extratime;                              //~@@@2I~
		int bishop=Plgq.bishop;                                    //~@@@2I~
		int knight=Plgq.knight;                                    //~@@@2I~
		int bishopLocal=Plgq.bishopLocal;                          //~1AabI~
		int knightLocal=Plgq.knightLocal;                          //~1AabI~
		int gameoptions=Plgq.gameoptions;                          //~@@@2I~
        int sz;                                                    //~@@@2I~
		Block=false;
		Dir="";
		int color=((gameoptions & GameQuestion.GAMEOPT_MOVEFIRST)!=0? 1:-1);//~@@@2I~
        sz=AG.propBoardSize;                                        //~@@@2R~
        if (AG.isChessBoard())                                     //~@@@2R~
        	color=-color;                                          //~@@@2I~
        if (bishopLocal==0)                                        //~1AabI~
            bishopLocal=bishop;                                    //~1AabI~
        if (knightLocal==0)                                        //~1AabI~
            knightLocal=knight;                                    //~1AabI~
		PGF=new LocalGoFrame(this,                                //~@@@2R~
				color,sz,                                          //~@@@2R~
//  			gameover,gameover2,totaltime*60,extratime,bishop,knight,gameoptions);//~@@@2R~//~1AabR~
    			gameover,gameover2,totaltime*60,extratime,bishop,knight,gameoptions,bishopLocal,knightLocal);//~1AabI~
        PGF.start();                                               //~@@@2I~
	}



	public void doAction (String o)
	{                                                              //~v108I~
        if (false);                                                //~v108I~
		else super.doAction(o);
	}

	public void doclose ()
    {                                                              //~v108I~
		super.doclose();
	}

	public boolean close ()
    {                                                              //~@@@2I~
    	return true;                                               //~@@@2I~
	}
	public boolean moveset (String c, int i, int j, int bt, int bm,
		int wt, int wm)
	{	if (Block) return false;
		return true;
	}


	public void set (int i, int j)
	{
	}


	public void endgame ()
	{	if (Block) return;
		Block=true;
		new EndGameQuestion(this);                                 //~@@@2I~
	}

	public void doendgame ()
	{                                                              //~@@@2I~
//  	PGF.doscore();                                             //~@@@2R~
    	PGF.doscore(-PGF.B.currentColor());	//winner is opponent of current color//~@@@2I~
		Block=false;
//        AView.endGameConfirmed();                                //~@@@2R~
	}

	public void declineendgame ()
	{                                                              //~@@@2I~
		Block=false;
	}

	public void doresult (int b, int w)
	{                                                              //~@@@2I~
		Block=false;
	}

	public void declineresult ()
	{                                                              //~@@@2I~
		Block=false;
	}


//********************************************************         //~@@@@I~
//*from PartnerGoFrame:doclose                                     //~@@@@I~
//********************************************************         //~@@@@I~
	public void boardclosed (LocalGoFrame pgf)		
	{                                                              //~@@@2R~
	}

}

