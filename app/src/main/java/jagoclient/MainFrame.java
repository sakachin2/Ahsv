//*CID://+1Ad3R~:                             update#=  135;       //~1Ad3R~
//***********************************************************************//~v101I~
//1Ad3 2015/07/19 Bypass NFCSelect, by NFS-WD and NFC-BT button directly.//~1Ad3I~
//1Abd 2015/06/15 use not Toast but Dialog for err of Alive Session exist//~1AbdI~
//1Ab8 2015/05/08 NFC Bluetooth handover v3(DialogNFCSelect distributes only)//~1Ab8I~
//1Ab7 2015/05/03 NFC Bluetooth handover v2                        //~1Ab7I~
//1Ab6 2015/05/03 NFC Bluetooth handover change(once delete 1Ab1)  //~1Ab6I~
//1Ab1 2015/05/03 NFC Bluetooth handover                           //~1Ab1I~
//1A91 2015/04/19 dislay session type on msg:only one alive session//~1A91I~
//1A90 2015/04/18 (like as 1A84)WiFiDirect Top panel               //~1A90I~
//1A8g 2015/03/05 chk only one session alive(Ip,Direct,BT)         //~1A8gI~
//1A6s 2015/02/17 move NFC starter from WifiDirect dialog to MainFrame//~1A6sI~
//1A66 2015/01/31 layout:mainframe both hdpi and mdpi in layout/    //~1A66I~//~1A65R~
//101e 2013/02/08 findViewById to Container(super of Frame and Dialog)//~v101I~
//101a 2013/01/30 IP connection                                    //~v101I~
//***********************************************************************//~v101I~
package jagoclient;

import wifidirect.DialogNFC;
import wifidirect.DialogNFCBT;
import wifidirect.DialogNFCSelect;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.Alert;
import com.Ahsv.Prop;
import com.Ahsv.R;
import com.Ahsv.Utils;                                             //~@@@@R~
import com.Ahsv.awt.CheckboxMenuItem;                              //~@@@@R~
import com.Ahsv.awt.MenuItem;                                      //~@@@@R~

import jagoclient.board.Board;
import jagoclient.board.GoFrame;
import jagoclient.dialogs.*;
import jagoclient.gui.*;
import jagoclient.partner.BluetoothConnection;
import jagoclient.partner.IPConnection;

public class MainFrame extends GoFrame                             //~@@@2I~
{	CheckboxMenuItem 
		StartPublicServer,TimerInTitle,BigTimer,ExtraInformation,ExtraSendField,
		DoSound,SimpleSound,BeepOnly,Warning,RelayCheck,Automatic,
		EveryMove,FineBoard,Navigation;
	MenuItem StartServer;
//    public Server S=null;                                        //~v101R~
//  private GoFrame GF;                                            //~@@@@I~//~@@@2R~
    private ButtonAction btnNFC;                                   //~1A6sI~
    private ButtonAction btnNFCBT;                                 //~1Ad3I~
    private ButtonAction btnWiFiDirect;                            //~1A90I~

	public MainFrame (String c)
//  {   super(c+" "+Global.resourceString("Version"));             //~1524R~//~@@@@R~
    {                                                              //~@@@@I~
//     	super(AG.frameId_MainFrame,c+" "+AG.appVersion);                                //~@@@@I~//~@@@2R~//~1A66R~
       	super( (AG.screenDencityMdpiSmallV || AG.screenDencityMdpiSmallH/*mdpi and height or width <=320*/ ? R.layout.mainframe_mdpi : R.layout.mainframe),//~1A66R~
				c+" "+AG.appVersion);                              //~1A66I~
		int boardsz,piecetype;                                     //~@@@@R~
		if (AG.isLangJP)                                           //~@@@@I~
        {                                                          //~@@@@I~
			boardsz=AG.BOARDSIZE_SHOGI;  //default for JP                          //~@@@@I~//~@@@2R~
        	piecetype=Board.PIECETYPE_SHOGI;  //default for JP                     //~@@@@R~//~@@@2R~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~@@@@I~
			boardsz=AG.BOARDSIZE_CHESS;  //default for US                         //~@@@@I~//~@@@2R~
        	piecetype=Board.PIECETYPE_CHESS;   //default for US                    //~@@@@R~//~@@@2R~
		}                                                          //~@@@@I~
		AG.propBoardSize=Prop.getPreference(AG.PKEY_BOARD_SIZE,boardsz);//~@@@@R~
		Board.pieceType=Prop.getPreference(AG.PKEY_PIECE_TYPE,piecetype);//~@@@@R~
        B=new Board(AG.propBoardSize,this);                        //~@@@2I~
        int ic=AG.isChessBoard()?-1/*white*/:1/*Black*/;            //~@@@2I~
        B.putInitialPiece(ic,0/*bishop*/,0/*knight*/,0/*gameover*/,0/*gameover2*/,0/*gameoptions*/);//~@@@2R~
        boolean swSmall=AG.portrait;	//se small button if portrait and small pixel device//~@@@2I~
        new ButtonAction(this,0,R.id.ChangeBoard,swSmall);         //~v101R~
        new ButtonAction(this,0,R.id.ChangePiece,swSmall);         //~v101R~
        new ButtonAction(this,0,R.id.Options,swSmall);             //~v101R~
        new ButtonAction(this,0,R.id.LocalGame,swSmall);           //~v101R~
        new ButtonAction(this,0,R.id.RemoteGame,swSmall);          //~v101R~
        new ButtonAction(this,0,R.id.RemoteIP,swSmall);            //~v101R~
        new ButtonAction(this,0,R.id.Help,swSmall);                //~v101R~
        btnNFC=new ButtonAction(this,0,R.id.WiFiNFCButton,swSmall);//~1A6sI~
        btnNFCBT=new ButtonAction(this,0,R.id.BTNFCButton,swSmall);//~1Ad3I~
        btnWiFiDirect=new ButtonAction(this,0,R.id.WiFiDirectButton,swSmall);//~1A90I~
        if (AG.osVersion<AG.ICE_CREAM_SANDWICH)  //android4        //~1A6sI~
        {                                                          //~1A90I~
	        btnNFC.setEnabled(false);                              //~1A6sI~
	        btnNFCBT.setEnabled(false);                            //~1Ad3I~
	        btnWiFiDirect.setEnabled(false);                       //~1A90I~
        }                                                          //~1A90I~
        Show=true;                                                 //~@@@2I~
		setVisible(true);                                          //~@@@2I~
		repaint();                                                 //~@@@2I~
	}

	public boolean close ()
	{	                                                           //~@@@@R~
//        if (Global.getParameter("confirmations",true))           //~@@@@I~
//        {                                                        //~@@@@R~
//            CloseMainQuestion CMQ=new CloseMainQuestion(this);   //~@@@@I~
//            if (CMQ.Result) doclose();                           //~@@@@R~
//            return false;                                        //~@@@@R~
//        }                                                        //~@@@@R~
//        else                                                     //~@@@@R~
		{	doclose();
			return false;
		}
	}
	
	public void doclose ()
    {                                                              //~@@@@I~
		super.doclose();
//        Global.writeparameter(".go.cfg");                        //~@@@@R~
//        if (S!=null) S.close();                                  //~v101R~
//  	if (!Global.isApplet()) System.exit(0);		               //~1309R~
    	if (!Global.isApplet()) Utils.exit(0);	//@@@@ redirect to finish()//~1309I~//~@@@@R~
	}

	public void doAction (String o)
	{                                                              //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.ChangeBoard))) //~@@@@I~
        {                                                          //~@@@@I~
            changeBoard(true/*change board size*/);  //go to GoFrame//~@@@2R~
			Prop.putPreference(AG.PKEY_BOARD_SIZE,AG.propBoardSize);//~@@@@R~
    	}                                                          //~@@@@I~
        else                                                       //~@@@2I~
        if (o.equals(AG.resource.getString(R.string.ActionChangeCoordinate))) //from MainFrameOptions//~@@@2R~
        {                                                          //~@@@2I~
            changeBoard(false/*coordinate draw*/);  //go to GoFrame//~@@@2R~
    	}                                                          //~@@@2I~
        else                                                       //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.ChangePiece))) //~@@@@I~
        {                                                          //~@@@@I~
            changePiece();                                         //~@@@2I~
			Prop.putPreference(AG.PKEY_PIECE_TYPE,Board.pieceType);//~@@@@R~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.Options)))     //~@@@2I~
        {                                                          //~@@@2I~
            new MainFrameOptions(this);                            //~@@@2I~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
        if (o.equals(AG.resource.getString(R.string.LocalGame)))   //~@@@2R~
        {                                                          //~@@@2I~
            startLocalGame();                                      //~@@@2I~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
        if (o.equals(AG.resource.getString(R.string.RemoteGame)))  //~@@@2R~
        {                                                          //~@@@2I~
            startRemoteGame();                                     //~@@@2R~
        }                                                          //~@@@2I~
        else                                                       //~@@@2I~
        if (o.equals(AG.resource.getString(R.string.RemoteIP)))    //~v101I~
        {                                                          //~v101I~
            startRemoteIP();                                       //~v101I~
        }                                                          //~v101I~
        else                                                       //~v101I~
        if (o.equals(AG.resource.getString(R.string.Help)))        //~@@@@I~
        {                                                          //~@@@@I~
			new HelpDialog(this,R.string.Help_MainFrame);               //~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        if (o.equals(AG.resource.getString(R.string.ActionRestoreFrame)))//~@@@@I~//~@@@2R~
        {                                                          //~@@@@I~
			restoreFrame();                                        //~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~1A6sI~
        if (o.equals(AG.resource.getString(R.string.WiFiNFCButton)))//~1A6sI~
        {                                                          //~1A6sI~
//        if (AG.swNFCBT)                                             //~1Ab1I~//~1Ab6R~//~1Ab7R~//~1Ad3R~
    		selectNFCHandover();                                   //~1Ab1I~//~1Ab6R~//~1Ab7R~
//        else                                                     //~1Ab1I~//~1Ab6R~//~1Ab7R~//~1Ad3R~
//  		prepareNFC();                                          //~1A6sI~//~1Ad3R~
        }                                                          //~1A6sI~
        else                                                       //~1Ad3I~
        if (o.equals(AG.resource.getString(R.string.BTNFCButton))) //~1Ad3I~
        {                                                          //~1Ad3I~
    		selectNFCHandoverBT();                                 //~1Ad3I~
        }                                                          //~1Ad3I~
        else                                                       //~1A90I~
        if (o.equals(AG.resource.getString(R.string.WiFiDirectButton)))//~1A90I~
        {                                                          //~1A90I~
            startRemoteIP(true/*WiFiDirect*/);	                   //~1A90I~
        }                                                          //~1A90I~
		else super.doAction(o);
  	}
  	public void itemAction (String o, boolean flag)
  	{	if (Global.resourceString("Public").equals(o))
  		{	Global.setParameter("publicserver",flag);
  		}
  		else if (Global.resourceString("Timer_in_Title").equals(o))
  		{	Global.setParameter("timerintitle",flag);
  		}
  		else if (Global.resourceString("Beep_only").equals(o))
  		{	Global.setParameter("beep",flag);
  		}
  		else if (Global.resourceString("Timeout_warning").equals(o))
  		{	Global.setParameter("warning",flag);
  		}
  	}
    //***********************************************************************//~@@@@I~
    //*from Frame                                                  //~@@@@I~
    //***********************************************************************//~@@@@I~
    private void restoreFrame()                                    //~@@@@I~
    {                                                              //~@@@@I~
    }                                                              //~@@@@I~
    //***********************************************************************//~@@@2I~
    private void startLocalGame()                                  //~@@@2I~
    {                                                              //~@@@2I~
    	new LocalGameQuestion(this);                               //~@@@2I~
    }                                                              //~@@@2I~
    //***********************************************************************//~@@@2I~
    private void startRemoteGame()                                 //~@@@2R~
    {                                                              //~@@@2I~
        if ((AG.RemoteStatus & AG.RS_IP)!=0)                          //~v101I~
        {                                                          //~v101I~
        	new Message(this,R.string.ErrNowIPConnected);          //~v101I~
        	return;                                                //~v101I~
        }                                                          //~v101I~
		new BluetoothConnection(this);	//open dialog              //~@@@2R~
    }                                                              //~@@@2I~
    //***********************************************************************//~v101I~
    private void startRemoteIP()                                   //~v101I~
    {                                                              //~v101I~
        if ((AG.RemoteStatus & AG.RS_BT)!=0)                          //~v101I~
        {                                                          //~v101I~
            new Message(this,R.string.ErrNowBTConnected);          //~v101I~
            return;                                                //~v101I~
        }                                                          //~v101I~
    	if (isAliveOtherSession(AG.AST_IP,false/*dupok*/))         //~1A8gI~
            return;                                                //~1A8gI~
//  	isAliveOtherSession(AG.AST_IP,true/*duperr*/);	//issue toast if dup IP//~1A8gR~//~1AbdR~
		new IPConnection(this);	//open dialog                      //~v101I~
    }                                                              //~v101I~
    //***********************************************************************//~1A90I~
    private void startRemoteIP(boolean Pdirect)                    //~1A90I~
    {                                                              //~1A90I~
        if ((AG.RemoteStatus & AG.RS_BT)!=0)                    
        {                                                       
            new Message(this,R.string.ErrNowBTConnected);       
            return;                                             
        }                                                       
    	if (isAliveOtherSession(AG.AST_WD,false/*dupok*/))         //~1A8gR~
            return;                                                //~1A8gR~
		new wifidirect.IPConnection();	//open dialog              //~1A90I~
    }                                                              //~1A90I~
    //***********************************************************************//~1A6sI~
    private void prepareNFC()                                      //~1A6sI~
    {                                                              //~1A6sI~
        if ((AG.RemoteStatus & AG.RS_BT)!=0)                       //~1A6sI~
        {                                                          //~1A6sI~
            new Message(this,R.string.ErrNowBTConnected);          //~1A6sI~
            return;                                                //~1A6sI~
        }                                                          //~1A6sI~
    	if (isAliveOtherSession(AG.AST_WD,false/*dupok*/))         //~1A8gR~
            return;                                                //~1A8gR~
    	DialogNFC.showDialog();                                           //~1A6sR~
    }                                                              //~1A6sI~
//*****************************************************************************//~1A8gI~
//*sessiontype:1:LAN,2:WifiDirect,3:BT                             //~1A8gI~
//*****************************************************************************//~1A8gI~
    public static boolean isAliveOtherSession(int Psessiontype,boolean Pduperr)//~1A8gI~
    {                                                              //~1A8gI~
    	int active=AG.activeSessionType;                           //~1A8gI~
        if (active!=0)                                             //~1A8gI~
            if (active!=Psessiontype||Pduperr)                     //~1A8gI~
            {                                                      //~1A8gI~
//              String msg=AG.resource.getString(R.string.ErrOtherActiveSession);//~1A8gR~
//              new com.Ajagoc.gtp.MessageDialogs().showError(null/*frame*/,msg,""/*optionalmsg*/,false/*critical*/);//~1A8gR~
		        String session="";                                 //~1A91R~
                switch(active)                                     //~1A91R~
                {                                                  //~1A91R~
                case AG.AST_IP:                                    //~1A91R~
                    session=AG.resource.getString(R.string.RemoteIP);//~1A91R~
                	break;                                         //~1A91R~
                case AG.AST_WD:                                    //~1A91R~
                    session=AG.resource.getString(R.string.WiFiDirectButton)+"("+AG.resource.getString(R.string.WiFiNFCButton)+")";//~1A91R~
                	break;                                         //~1A91R~
                case AG.AST_BT:                                    //~1A91R~
                    session=AG.resource.getString(R.string.RemoteGame);//~1A91R~
                	break;                                         //~1A91R~
                }                                                  //~1A91R~
                String msg=AG.resource.getString(R.string.ErrOtherActiveSession,session);//~1A91R~
//              AView.showToast(msg);                              //~1A91R~//~1AbdR~
		    	int flag=Alert.BUTTON_POSITIVE;                    //~1AbdI~
				Alert.simpleAlertDialog(null/*callback*/,null/*title*/,msg,flag);//~1314I~//~@@@@R~//~1AbdI~
                return true;                                       //~1A8gI~
            }                                                      //~1A8gI~
                                                                   //~1A8gI~
        return false;                                              //~1A8gI~
  	}                                                              //~1A8gI~
//*****************************************************************************//~1Ab1I~//~1Ab6R~//~1Ab7R~
//*select WiFiDirect or Bluetooth for NFC handover                 //~1Ab1I~//~1Ab6R~//~1Ab7R~
//*****************************************************************************//~1Ab1I~//~1Ab6R~//~1Ab7R~
    private void selectNFCHandover()                               //~1Ab1I~//~1Ab6R~//~1Ab7R~
    {                                                              //~1Ab1I~//~1Ab6R~//~1Ab7R~
//      DialogNFCSelect.showDialog(this);                          //~1Ab1R~//~1Ab6R~//~1Ab7R~//~1Ad3R~
    	prepareNFCAhsv();                                          //+1Ad3I~
    }                                                              //~1Ab1I~//~1Ab6R~//~1Ab7R~
    private void selectNFCHandoverBT()                             //~1Ad3I~
    {                                                              //~1Ad3I~
    	prepareNFCBTAhsv();                                        //+1Ad3I~
    }                                                              //~1Ad3I~
    public void selectedNFCHandover(int PhandoverType)           //~1Ab1I~//~1Ab6R~//~1Ab7R~//~1Ab8R~
    {                                                              //~1Ab1I~//~1Ab6R~//~1Ab7R~
        if (PhandoverType==DialogNFCSelect.NFCTYPE_IP)                                           //~1Ab1I~//~1Ab6R~//~1Ab7R~
            prepareNFC();                                          //~1Ab1I~//~1Ab6R~//~1Ab7R~
        else                                                       //~1Ab1I~//~1Ab6R~//~1Ab7R~
            prepareNFCBT(PhandoverType);                                        //~1Ab1I~//~1Ab6R~//~1Ab7R~//~1Ab8R~
    }                                                              //~1Ab1I~//~1Ab6R~//~1Ab7R~
    //***********************************************************************//~1Ab1I~//~1Ab6R~//~1Ab7R~
    private void prepareNFCBT(int PhandoverType)                                    //~1Ab1I~//~1Ab6R~//~1Ab7R~//~1Ab8R~
    {                                                              //~1Ab1I~//~1Ab6R~//~1Ab7R~
        if ((AG.RemoteStatus & AG.RS_IP)!=0)                       //~1Ab1I~//~1Ab6R~//~1Ab7R~
        {                                                          //~1Ab1I~//~1Ab6R~//~1Ab7R~
            new Message(this,R.string.ErrNowIPConnected);          //~1Ab1I~//~1Ab6R~//~1Ab7R~
            return;                                                //~1Ab1I~//~1Ab6R~//~1Ab7R~
        }                                                          //~1Ab1I~//~1Ab6R~//~1Ab7R~
//      if (isAliveOtherSession(AG.AST_WD,false/*dupok*/))         //~1Ab1I~//~1Ab6R~//~1Ab7R~
//          return;                                                //~1Ab1I~//~1Ab6R~//~1Ab7R~
        DialogNFCBT.showDialog(this,PhandoverType);                                  //~1Ab1I~//~1Ab6R~//~1Ab7R~//~1Ab8R~
    }                                                              //~1Ab1I~//~1Ab6R~//~1Ab7R~
    //***********************************************************************//+1Ad3I~
    private void prepareNFCAhsv()                                  //+1Ad3I~
    {                                                              //+1Ad3I~
        if ((AG.RemoteStatus & AG.RS_BT)!=0)                       //+1Ad3I~
        {                                                          //+1Ad3I~
            new Message(this,R.string.ErrNowBTConnected);          //+1Ad3I~
            return;                                                //+1Ad3I~
        }                                                          //+1Ad3I~
    	if (isAliveOtherSession(AG.AST_WD,false/*dupok*/))         //+1Ad3I~
            return;                                                //+1Ad3I~
        DialogNFCSelect.showDialogNFCWD(this);                     //+1Ad3I~
    }                                                              //+1Ad3I~
    //***********************************************************************//+1Ad3I~
    private void prepareNFCBTAhsv()                                //+1Ad3I~
    {                                                              //+1Ad3I~
        if ((AG.RemoteStatus & AG.RS_IP)!=0)                       //+1Ad3I~
        {                                                          //+1Ad3I~
            new Message(this,R.string.ErrNowIPConnected);          //+1Ad3I~
            return;                                                //+1Ad3I~
        }                                                          //+1Ad3I~
        DialogNFCSelect.showDialogNFCBT(this);                     //+1Ad3M~
    }                                                              //+1Ad3I~
}

