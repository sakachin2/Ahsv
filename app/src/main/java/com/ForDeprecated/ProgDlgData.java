//*CID://+1Ah0R~: update#= 134;                                    //~1Ah0I~
//**********************************************************************//~1Ah0I~
//1Ah0 2020/05/30 for Android9-api28(PlayStore requires),deprected. ProgressDialog at Android8-api26//~1Ah0I~
//**********************************************************************//~1Ah0I~
package com.ForDeprecated;                                         //~1Ah0I~

import com.Ahsv.ProgDlgI;
import com.Ahsv.URunnableI;

//+1Ah0I~
public class ProgDlgData                                           //~1Ah0R~
		implements URunnableI                                      //~1Ah0I~
    {                                                              //~1Ah0I~
		public int mtTitleid;                                      //~1Ah0R~
		public String mtMsg;                                       //~1Ah0R~
		public boolean mtCancelable;                               //~1Ah0R~
		public boolean mtAGPtr;                                    //~1Ah0R~
		public ProgDlgI mtDlgI;                                    //~1Ah0R~
		public ProgDlg  progDlg;                                   //+1Ah0R~
		public boolean mtCancelCB;                                 //~1Ah0R~
		public int mtMsgid;                                        //~1Ah0R~
        //**********************                                   //~1Ah0I~
        public ProgDlgData(boolean Pagptr,int Ptitleid,int Pmsgid,boolean Pcancelable)//~1Ah0I~
        {                                                          //~1Ah0I~
	        mtTitleid=Ptitleid;                                    //~1Ah0I~
    	    mtMsgid=Pmsgid;                                        //~1Ah0I~
        	mtCancelable=Pcancelable;                              //~1Ah0I~
        	mtAGPtr=Pagptr;                                        //~1Ah0I~
        }                                                          //~1Ah0I~
        //**********************                                   //~1Ah0I~
    	public ProgDlgData(ProgDlgI Ppdi,boolean Pcancelcb,int Ptitleid,String Pmsg,boolean Pcancelable)//~1Ah0I~
        {                                                          //~1Ah0I~
            mtTitleid=Ptitleid;                                    //~1Ah0I~
            mtMsg=Pmsg;                                            //~1Ah0I~
            mtCancelable=Pcancelable;                              //~1Ah0I~
            mtCancelCB=Pcancelcb;                                  //~1Ah0I~
            mtDlgI=Ppdi;                                           //~1Ah0I~
		}                                                          //~1Ah0I~
        //**********************                                   //~1Ah0I~
        @Override                                                  //~1Ah0I~
		public void runFunc(Object Pobj,int Pint)                  //~1Ah0I~
        {                                                          //~1Ah0I~
			ProgDlg.runFunc(Pobj,Pint);                            //~1Ah0I~
        }                                                          //~1Ah0I~
}//class                                                           //~1Ah0R~
