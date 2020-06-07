//*CID://+1AhjR~: update#= 414;                                    //~1AHjR~//~1AhjR~
//**********************************************************************//~v101I~
//1Ahj 2020/06/05 IP io on mainthread fails by android.os.NetworkOnMainThreadException//~1AHjI~
//v@11 2019/02/02 TakeOne by touch                                 //~v@11I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package wifidirect;                                                //~1AHjR~

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.io.PrintWriter;

import com.Ahsv.AG;
import com.btmtest.utils.UHandler;
import jagoclient.Dump;


public class IPSubThread extends UHandler                      //~v@@@R~//~v@11R~
{                                                                  //~0914I~
//  private static final String IPST_IDXTHREAD="idxThread";        //~v@11I~//~1AhjR~
    private static final String IPST_MSGDATA="msgData";            //~v@11I~
    private static final int GCM_IPOUT=1;                          //~1AhjI~
    private PrintWriter Out;                                        //~1AhjI~
                                                                   //~v@@@I~
    private Looper looper;                                         //~v@11I~
//  private int msg_idxThread;                                     //~v@11I~//~1AhjR~
    private String msg_msgData;                                    //~v@11I~
	//*************************                                        //~v@@@I~//~v@11R~
    public IPSubThread(Looper Plooper)                             //~v@11I~
    {                                                              //~v@11I~
        super(Plooper);                                             //~v@11I~
        AG.aIPSubThread=this;                                      //~v@11I~
        if (Dump.Y) Dump.println("IPSubThread Constructor");       //~v@11I~
    }                                                              //~v@11I~
    //****************************************                     //~v@11I~
    public static void newInstance()                               //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("IPSubThread.newInstance");       //~v@11I~
//      HandlerThread handlerThread=new HandlerThread(Utils.getClassName(AG.aIPMulti));//~v@11I~//+1AhjR~
        HandlerThread handlerThread=new HandlerThread("IPSubThread");//+1AhjI~
        handlerThread.start();        //do run()                   //~v@11I~
	    Looper looper=handlerThread.getLooper();                          //~v@11I~
        IPSubThread st=new IPSubThread(looper);                //~v@11I~
	    st.onResume();                                             //~v@11I~
    }                                                              //~v@11I~
    //****************************************                 //~@003I~//~v@@@I~
    @Override	//UHandler                                                  //~@003I~//~v@@@I~
    public boolean storeMsg(Message Pmsg)                             //~@003I~//~v@@@I~
    {                                                          //~@003I~//~v@@@I~
        return true; //allow store when paused                 //~@003I~//~v@@@I~
    }                                                          //~@003I~//~v@@@I~
    //****************************************                     //~v@@@I~
    public void onResume()                                         //~v@@@I~
    {                                                              //~v@@@I~
    	super.onResume(AG.activity);	//UHandler                             //~v@@@R~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	super.onDestroy();	//UHandler                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************                                        //~1109I~//~1111I~//~1122M~//~v@@@R~
    @Override   //UHandler                                         //~v@@@R~
	public void handleMsg(Message Pmsg)                            //~v@@@R~
    {                                                              //~1120I~//~1122M~
        try                                                        //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
	    	handleMsgIP(Pmsg);                                     //~v@@@R~//~v@11R~
        }                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
        catch(Exception e)                                         //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
    		Dump.println(e,"IPSubThread.handleMessage");       //~v@@@R~//~v@11R~
        }                                                          //~1109I~//~1120M~//~1122M~
    }                                                              //~v@@@I~
//    //***********************************************************  //~v@11I~//~1AhjR~
//    public void outOnSubThread(int Pmsgid,IPIOThread Pthread,String PmsgData)//~v@11R~//~1AhjR~
//    {                                                              //~v@11I~//~1AhjR~
//        if (Dump.Y) Dump.println("IPSubThread.outOnSubthread msgid=" + Pmsgid +",idxServer="+Pthread.idxServer+ ",idxThread=" + Pthread.idxMember + ",msgData=" + PmsgData);//~v@11R~//~1AhjR~
//        if (Dump.Y) Dump.println("IPSubThread.outOnSubthread Pthread="+Utils.toString(Pthread));//~v@11I~//~1AhjR~
//        if (!AG.isMainThread())     //on subthred, out to stream direct//~v@11R~//~1AhjR~
//        {                                                          //~v@11I~//~1AhjR~
//            if (Dump.Y) Dump.println("IPSubThread.outRequest req on subthread");//~v@11R~//~1AhjR~
//            Pthread.outOnSubThread(PmsgData);                      //~v@11R~//~1AhjR~
//            return;                                                //~v@11I~//~1AhjR~
//        }                                                          //~v@11I~//~1AhjR~
//        Message msg = obtainMessage(Pmsgid);                             //~v@11I~//~1AhjR~
//        Bundle bundle = msg.getData();                               //~v@11I~//~1AhjR~
//        if (AG.aIPMulti.swServer)                                  //~v@11R~//~1AhjR~
//            bundle.putInt(IPST_IDXTHREAD,Pthread.idxMember);       //~v@11R~//~1AhjR~
//        else                                                       //~v@11I~//~1AhjR~
//            bundle.putInt(IPST_IDXTHREAD,Pthread.idxServer);       //~v@11I~//~1AhjR~
//        bundle.putString(IPST_MSGDATA, PmsgData);                   //~v@11I~//~1AhjR~
//        sendMessage(msg);                                        //~1AhjR~
//    }//~v@11I~                                                   //~1AhjR~
    //***********************************************************  //~1AhjI~
    public void println(PrintWriter Ppw,String PmsgData)           //~1AhjR~
    {                                                              //~1AhjI~
        if (Dump.Y) Dump.println("IPSubThread.println msg="+PmsgData);//~1AhjR~
        if (!AG.isMainThread())     //on subthred, out to stream direct//~1AhjI~
        {                                                          //~1AhjI~
	        if (Dump.Y) Dump.println("IPSubThread.println req on subthread");//~1AhjR~
            Ppw.println(PmsgData);                                     //~1AhjI~
            return;                                                //~1AhjI~
        }                                                          //~1AhjI~
        Out=Ppw;                                                   //~1AhjI~
        Message msg = obtainMessage(GCM_IPOUT);                    //~1AhjI~
        Bundle bundle = msg.getData();                             //~1AhjI~
        bundle.putString(IPST_MSGDATA, PmsgData);                  //~1AhjI~
        sendMessage(msg);                                          //~1AhjI~
    }                                                              //~1AhjI~
    //***********************************************************  //~v@21I~
    private void getMsgData(Message Pmsg)             //~v@21I~    //~v@11R~
    {                                                              //~v@21I~
        Bundle bundle=Pmsg.getData();                              //~v@21I~
//      msg_idxThread=bundle.getInt(IPST_IDXTHREAD,-1);                 //~v@21I~//~v@11R~//~1AhjR~
        msg_msgData=bundle.getString(IPST_MSGDATA,"");                 //~v@21I~//~v@11R~
        if (Dump.Y) Dump.println("IPSubThread.getMsgData msgid="+Pmsg.what+",msgdata="+msg_msgData);//~v@11R~//~1AhjR~
    }                                                              //~v@21I~
	//*************************                                    //~v@@@I~
	private void handleMsgIP(Message Pmsg)                          //~v@@@R~//~v@11R~
    {                                                              //~v@@@I~
    	boolean rc=false;   //need call draw                       //~v@@@I~
        if (Dump.Y) Dump.println("IPSubThread.handleMsgIP what="+Pmsg.what);//~v@@@R~//~v@11R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        switch(Pmsg.what)                                      //~v@@@R~
    	    {                                                      //~v@@@I~
        	case GCM_IPOUT:                                        //~v@@@I~//~v@11R~
            	out(Pmsg);                               //~v@@@I~//~v@11R~
            	break;                                             //~v@@@I~
        	default:                                               //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	Dump.println(e,"IPSubThread.handleMsgIP");         //~v@@@I~//~v@11R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
	private void out(Message Pmsg)                         //~v@@@I~    //~v@11R~
    {                                                              //~v@@@I~
	    getMsgData(Pmsg);                                          //~v@11I~
//      if (msg_idxThread<0)                                       //~v@11I~//~1AhjR~
//      	return;                                                //~v@11I~//~1AhjR~
//      IPIOThread t=(IPIOThread)AG.aBTMulti.BTGroup.getThread(msg_idxThread);//~v@11I~//~1AhjR~
//      if (t!=null)                                               //~v@11I~//~1AhjR~
//          t.outOnSubThread(msg_msgData);                         //~v@11R~//~1AhjR~
        if (Dump.Y) Dump.println("IPSubThread.out Pmsg="+msg_msgData);//~v@11R~//~1AhjI~
        Out.println(msg_msgData);                                      //~1AhjI~
    }                                                              //~v@@@I~
	//**************************************************************//~v@21I~
}//class IPSubThread                                                 //~dataR~//~@@@@R~//~v@@@R~//~v@11R~
