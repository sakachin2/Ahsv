//*CID://+v101R~:                             update#=    4;       //~v101I~
//********************************************************************//~v101I~
//101e 2013/02/08 findViewById to Container(super of Frame and Dialog)//~v101I~
//********************************************************************//~v101I~
package com.Ahsv.awt;                                            //~1112I~//~2C26R~

import jagoclient.Dump;
import android.view.View;

                                                             //~1112I~
public class Container extends Component                           //~1116R~
{                                                                  //~1112I~
	public Color backgroundColor=null;                                     //~1216I~
    public View containerLayoutView;                               //~v101R~
    public Container()                                             //~1118R~
    {                                                              //~1118I~
    	super();                                                   //~1118R~
    }                                                              //~1118I~
    public void doLayout()                                              //~1216I~
    {                                                              //~1216I~
    }                                                              //~1216I~
//****************                                                 //+v101I~
    public void setContainerLayoutView(View Pview)                 //+v101I~
    {                                                              //+v101I~
        if(Dump.Y) Dump.println("Container setContainerLayoutView v="+Pview.toString());//+v101I~
		containerLayoutView=Pview;                                 //+v101I~
    }                                                              //+v101I~
//****************                                                 //~v101I~
	public View findViewById(int Presid)                    //~v101I~
    {                                                              //~v101I~
        View v=containerLayoutView.findViewById(Presid);           //+v101R~
        if(Dump.Y) Dump.println("Container findViewById layoutview="+containerLayoutView.toString()+",res="+Integer.toHexString(Presid)+",v="+(v==null?"null":v.toString()));//+v101I~
        return v;                                                  //+v101I~
    }                                                              //~v101I~
}//class                                                           //~1112I~
