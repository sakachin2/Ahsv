//*CID://+1A6AR~:                             update#=   17;       //+1A6AR~
//*********************************************************************//~v101I~
//1A6A 2015/02/20 Another Trace option if (Dump.C) for canvas drawing//+1A6AI~
//1A07 2013/03/02 distinguish color for extratime                  //~1A07I~
//101e 2013/02/08 findViewById to Container(super of Frame and Dialog)//~v101I~
//*********************************************************************//~v101I~
package jagoclient.board;

import com.Ahsv.AG;
import com.Ahsv.awt.Color;                                         //~@@@@R~
import com.Ahsv.awt.Container;
import com.Ahsv.awt.FontMetrics;                                   //~@@@@R~
import com.Ahsv.awt.Graphics;                                      //~@@@@R~

import jagoclient.Dump;
import jagoclient.Global;
import jagoclient.gui.BigLabel;

//import java.awt.Color;
//import java.awt.FontMetrics;
//import java.awt.Graphics;

public class BigTimerLabel extends BigLabel
{	int White=0,Black=0,Col=0,MWhite=-1,MBlack=-1;
	boolean extraB,extraW;                                         //~1A07I~
    private static final Color COLOR_WARNING_SHORT=Color.red;      //~1A07I~
    private static final Color COLOR_WARNING_LONG=new Color(255,128,0);//orange//~1A07I~
//    public BigTimerLabel ()                                      //~v101R~
//    {   super(Global.BigMonospaced);                             //~v101R~
//    }                                                            //~v101R~
	public BigTimerLabel(Container Pcontainer)                     //~v101I~
	{	super(Pcontainer,Global.BigMonospaced);                    //~v101I~
	}                                                              //~v101I~
	static char a[]=new char[32];
	public void drawString (Graphics g, int x, int y, FontMetrics fm)
//  {	int delta=fm.charWidth('m')/4;                             //~1A07R~
    {                                                              //~1A07I~
    	int delta=fm.charWidth('m');                               //~1A07I~
		if (Global.BigMonospaced!=null) g.setFont(Global.BigMonospaced);
    //*WhightSign                                                  //~1A07I~
        int n;                                                     //~1A07I~
        String strW;                                               //~1A07I~
        strW=AG.WhiteSign+":";                                     //~1A07I~
        char[] aSign=strW.toCharArray();                           //~1A07I~
        n=aSign.length;                                            //~1A07I~
		int ww=fm.charsWidth(aSign,0,n);                           //~1A07I~
        setSignBG(g,Col<0,x,ww);                                   //~1A07I~
		g.setColor(Color.black);                                   //~1A07I~
		g.drawChars(aSign,0,n,x,y); //Color.black                  //~1A07I~
		x+=ww;                                                     //~1A07I~
    //*WhiteTime                                                   //~1A07I~
//        if (White<0) g.setColor(Color.blue);                     //~1A07R~
//        else if (White<30 && Col<0) g.setColor(Color.red);       //~1A07R~
//        else if (White<60 && Col<0) g.setColor(Color.red.darker());//~1A07R~
//        else if (Col<0) g.setColor(Color.green.darker());        //~1A07R~
//        else g.setColor(Color.black);                            //~1A07R~
        if (White<0) g.setColor(Color.blue);                       //~1A07I~
        else if (White<30) g.setColor(COLOR_WARNING_SHORT);        //~1A07I~
        else if (White<60) g.setColor(COLOR_WARNING_LONG);         //~1A07I~
        else g.setColor(Color.black);                              //~1A07I~
//  	int n=OutputFormatter.formtime(a,White);                   //~1A07R~
    	n=OutputFormatter.formtime(a,White);                       //~1A07I~
//        String strW;                                               //~@@@@I~//~1A07R~
//        strW=AG.WhiteSign+": ";                                    //~@@@@R~//~1A07R~
//        a=(strW+new String(a,0,n)).toCharArray();                  //~@@@@R~//~1A07R~
//        n=a.length;                                              //~@@@@R~//~1A07R~
//        g.drawChars(a,0,n,x,y);                                  //~1A07R~
//        if (Dump.C) Dump.println("BigTimer "+new String(a));       //~@@@@M~//~1A07R~//+1A6AR~
//        x+=fm.charsWidth(a,0,n)+delta;                           //~1A07R~
        char[] aTime=((extraW?"+":" ")+new String(a,0,n)+" ").toCharArray();//~1A07I~
        n=aTime.length;                                            //~1A07I~
		g.drawChars(aTime,0,n,x,y);                                //~1A07I~
    	x+=fm.charsWidth(aTime,0,n)+delta;                         //~1A07I~
		g.setFont(Global.Monospaced);
//        if (MWhite>=0)                                           //~@@@@R~
//        {   a[0]=(char)('0'+(MWhite%100)/10);                    //~@@@@R~
//            a[1]=(char)('0'+MWhite%10);                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else a[0]=a[1]=' ';                                      //~@@@@R~
//      a[0]=a[1]=' ';	//no need to print White remaining move in the time span//~@@@@I~//~1A07R~
		g.setColor(Color.black);
//  	g.drawChars(a,0,2,x,y);                                    //~1A07R~
//  	x+=fm.charsWidth(a,0,2)+delta;                             //~1A07R~
    //*BlackSign                                                   //~1A07I~
        strW=AG.BlackSign+":";                                     //~1A07I~
        aSign=strW.toCharArray();                                  //~1A07I~
        n=aSign.length;                                            //~1A07I~
		ww=fm.charsWidth(aSign,0,n);                               //~1A07I~
        setSignBG(g,Col>0,x,ww);                                   //~1A07I~
		g.setColor(Color.black);                                   //~1A07I~
		g.drawChars(aSign,0,n,x,y); //Color.black                  //~1A07I~
		x+=ww;                                                     //~1A07I~
		if (Global.BigMonospaced!=null) g.setFont(Global.BigMonospaced);
//        if (Black<0) g.setColor(Color.blue);                     //~1A07R~
//        else if (Black<30 && Col>0) g.setColor(Color.red);       //~1A07R~
//        else if (Black<60 && Col>0) g.setColor(Color.red.darker());//~1A07R~
//        else if (Col>0) g.setColor(Color.green.darker());        //~1A07R~
//        else g.setColor(Color.black);                            //~1A07R~
        if (Black<0) g.setColor(Color.blue);                       //~1A07I~
        else if (Black<30) g.setColor(COLOR_WARNING_SHORT);        //~1A07I~
        else if (Black<60) g.setColor(COLOR_WARNING_LONG);         //~1A07I~
        else g.setColor(Color.black);                              //~1A07I~
		n=OutputFormatter.formtime(a,Black);
//        a=(AG.BlackSign+": "+new String(a,0,n)).toCharArray();     //~@@@@R~//~1A07R~
//        n=a.length;                                              //~@@@@I~//~1A07R~
//        g.drawChars(a,0,n,x,y);                                  //~1A07R~
//        if (Dump.C) Dump.println("BigTimer "+new String(a));       //~@@@@I~//~1A07R~//+1A6AR~
//        x+=fm.charsWidth(a,0,n)+delta;                           //~1A07R~
        aTime=((extraB?"+":" ")+new String(a,0,n)).toCharArray();  //~1A07I~
        n=aTime.length;                                            //~1A07I~
        g.drawChars(aTime,0,n,x,y);                                //~1A07I~
        x+=fm.charsWidth(aTime,0,n);                               //~1A07I~
		g.setFont(Global.Monospaced);
//        if (MBlack>=0)                                           //~@@@@R~
//        {   a[0]=(char)('0'+(MBlack%100)/10);                    //~@@@@R~
//            a[1]=(char)('0'+MBlack%10);                          //~@@@@R~
//        }                                                        //~@@@@R~
//        else a[0]=a[1]=' ';                                      //~@@@@R~
//      a[0]=a[1]=' ';	//no need to print White remaining move in the time span//~@@@@I~//~1A07R~
//  	g.setColor(Color.black);                                   //~1A07R~
//  	g.drawChars(a,0,2,x,y);                                    //~1A07R~
		if (Global.BigMonospaced!=null) g.setFont(Global.BigMonospaced);
	}
//  public void setTime (int w, int b, int mw, int mb, int col)    //~1A07R~
    public void setTime (int w, int b, int mw, int mb, int col,boolean PextraB,boolean PextraW)//~1A07I~
	{	White=w; Black=b; MWhite=mw; MBlack=mb; Col=col;
    	extraB=PextraB; extraW=PextraW;                            //~1A07I~
        if (Dump.C) Dump.println("BigTimer settime w="+w+",b="+b+",mw="+mw+",mb="+mb+",color="+col+",extraB="+PextraB+",extraW="+PextraW);//~1A07I~//+1A6AR~
	}
    //********************************************************************//~1A07I~
    private void setSignBG(Graphics Pg,boolean Pcurrentcolor,int Px,int Pwidth)//~1A07I~
    {                                                              //~1A07I~
		Pg.setBackground(Pcurrentcolor?AG.cursorColor:Global.gray);//~1A07I~
        int hh=H;	//BigLabel                                     //~1A07I~
		Pg.clearRect(Px,0/*y*/,Pwidth,hh);                         //~1A07I~
    }                                                              //~1A07I~
}
