//*CID://+1AhgR~:                             update#=   29;       //~1AhgR~
//*********************************************************************//~v101I~
//1Ahg 2020/06/03 help text;string to helptext\
//1Ad1 2015/07/17 for 1Ab1,helpdialog with parm titleid and helpfile//~1Ad1I~
//1Aa6 2015/04/20 show youtube movie                               //~1Aa6I~
//1A73 2015/02/23 apply 1A68 to Help dialog(fill screen width)     //~1A73I~
//1A72 2015/02/23 use smaller help dialog font size for mdpi       //~1A72I~
//101e 2013/02/08 findViewById to Container(super of Frame and Dialog)//~v101I~
//*********************************************************************//~v101I~
package jagoclient.dialogs;

//import java.awt.*;                                               //~1418R~
import java.io.*;

import com.Ahsv.AG;
import com.Ahsv.AView;
import com.Ahsv.R;                                                 //~1Ad1R~
import com.Ahsv.Utils;
import com.Ahsv.awt.Font;
import com.Ahsv.awt.Frame;                                         //~@@@@R~
import com.Ahsv.awt.Panel;                                         //~@@@@R~
//import com.Ahsv.rene.viewer.SystemViewer;                        //~1327I~//~@@@@R~
import com.Ahsv.rene.viewer.Viewer;                              //~1327I~//~@@@@R~

import jagoclient.gui.*;
import jagoclient.Dump;
import jagoclient.Global;

//import rene.viewer.*;                                            //~1327R~

/**
The same as Help.java but as a dialog. This is for giving help in
modal dialogs. 
see jagoclient.dialogs.Help                                        //~1AhgR~
*/

public class HelpDialog extends CloseDialog
{	Viewer V; // The viewer
	Frame F;
	/**
	Display the help from subject.txt,Global.url()/subject.txt
	or from the ressource /subject.txt.
	*/
//  public HelpDialog (Frame f, String subject)                    //~1Ad1R~
    public HelpDialog (Frame f,int Ptitleid,String subject)        //~v101I~//~1Ad1I~
//    {   super(f,Global.resourceString("Help"),true);             //~@@@@R~
    {                                                              //~@@@@I~
//      super(f,AG.resource.getString(R.string.Help),false/*not modal*/);//~@@@@R~
//      super(f,AG.resource.getString(R.string.Help),R.layout.helpdialog,false/*not modal*/,false/*no input wait*/);//~@@@@I~//~1Ad1R~
        super((f==null?AG.currentFrame:f),                         //~v101I~//~1Ad1I~
				AG.resource.getString(R.string.Help)+":"+AG.resource.getString(Ptitleid),//~v101I~//~1Ad1I~
				R.layout.helpdialog,false/*not modal*/,false/*no input wait*/);//~v101I~//~1Ad1I~
        setWindowSize(90/*W:90%*/,0/*H=wrap content*/,!AG.layoutMdpi/*for landscape,use ScrHeight for width limit if not mdpi*/);//~1A73I~
		F=f;
//  	V=Global.getParameter("systemviewer",false)?new SystemViewer():new Viewer();//~@@@@R~
//        V=new Viewer();                                          //~@@@@R~
        V=new Viewer(this,R.id.Viewer);                            //~@@@@I~
//      if (Ptitleid==R.string.HelpTitle_Mainframe)                //+1AhgR~
//      	new ButtonAction(this,0,R.id.ButtonYouTube);           //+1AhgR~
      if (AG.layoutMdpi)                                           //~1A72I~
      {                                                            //~1A72I~
        int fontsz=12;                                             //~1A72I~
        Font helpfont=Global.createfont("monospaced","Monospaced",fontsz);//~1A72I~
		V.setFont(helpfont);                                       //~1A72I~
      }                                                            //~1A72I~
      else                                                         //~1A72I~
		V.setFont(Global.Monospaced);
		V.setBackground(Global.gray);
        String fnms="";                                            //~1A4tI~//~1Ad1I~
        String fnm;                                                //~1A4tI~//~1Ad1I~
		try
		{	BufferedReader in;
			String s;
            String help_suffix="_"+AG.language;                    //~1A1jI~//~1Ad1I~
			AG.tryHelpFileOpen=true;                               //~1A41R~//~1Ad1I~
    		fnm="helptexts/"+subject+help_suffix+".txt";           //~1A4tI~//~1Ad1I~
            fnms+="\n"+fnm;                                        //~1A4tR~//~1Ad1I~
			try
//@@@@  	{	in=Global.getStream(                               //~1327R~
        	{	in=Global.getEncodedStream(        //like as Help.java;required to display locale language//~1327I~
//  				"jagoclient/helptexts/"+subject+Global.resourceString("HELP_SUFFIX")+".txt");//~1Ad1R~
    				fnm);                                           //~1A4tI~//~1Ad1I~
				s=in.readLine();
			}
			catch (Exception e)
//  		{	try                                                //~1Ad1R~
    		{                                                      //~1Ad1I~
                fnm=subject+help_suffix+".txt";                    //~1Ad1I~
	            fnms+="\n"+fnm;                                    //~1Ad1I~
    			try                                                //~1Ad1I~
//@@@@ 			{	in=Global.getStream(                           //~1327R~
    			{	in=Global.getEncodedStream(                    //~1327I~
//  					subject+Global.resourceString("HELP_SUFFIX")+".txt");//~1Ad1R~
    					fnm);                                      //~1Ad1I~
					s=in.readLine();
				}
				catch (Exception ex)
//@@@@  		{	in=Global.getStream("jagoclient/helptexts/"+subject+".txt");//~1327R~
//  			{	in=Global.getEncodedStream("jagoclient/helptexts/"+subject+".txt");//~1327I~//~1Ad1R~
    			{                                                  //~1Ad1I~
					AG.tryHelpFileOpen=false;                      //~1Ad1I~
    			 	fnm="helptexts/"+subject+".txt";               //~1Ad1I~
		            fnms+="\n"+fnm;                                //~1Ad1I~
    			 	in=Global.getEncodedStream(fnm);               //~1Ad1I~
					s=in.readLine();
				}
			}
			while (s!=null)
			{	V.appendLine(s);
				s=in.readLine();
			}
			in.close();
		}
		catch (Exception e)
		{	new Message(Global.frame(),
//  			Global.resourceString("Could_not_find_the_help_file_"));//~1Ad1R~
    			Global.resourceString("Could_not_find_the_help_file_")+fnms);//~1Ad1I~
			doclose();
			return;
		}
		AG.tryHelpFileOpen=false;                                  //~1Ad1I~
		display();
	}
	public void doclose ()
	{	setVisible(false); dispose();
	}
	
	void display ()
//  {  	Global.setwindow(this,"help",500,400);                     //~@@@@R~
	{                                                              //~@@@@I~
//  	Global.setwindow(this,"help",500,400);                     //~@@@@I~
//        add("Center",V);                                         //~@@@@R~
//        Panel p=new MyPanel();                                   //~@@@@R~
//        p.add(new ButtonAction(this,Global.resourceString("Close")));//~@@@@R~
//        add("South",new Panel3D(p));                             //~@@@@R~
//        ButtonAction.init(this,R.string.Close);                  //~@@@@R~
        new ButtonAction(this,0,R.id.Close);                  //~@@@@R~
	  	setVisible(true);                                          //~@@@@R~
	}
	public void doAction (String o)
//  {  	Global.notewindow(this,"help");                            //~@@@@R~
	{                                                              //~@@@@I~
//  	Global.notewindow(this,"help");                            //~@@@@I~
      if (o.equals(AG.resource.getString(R.string.ButtonYouTube))) //~1Aa6I~
	  {                                                            //~1Ad1I~
        showMovie();                                           //~v1E7R~//~1Aa6I~
      }                                                            //~1Ad1I~
      else                                                         //~1Aa6I~
		super.doAction(o);
	}
//**********************************                               //~@@@@I~
	public HelpDialog (Frame Pframe,int Presid)                    //~@@@@R~
	{                                                              //~@@@@I~
//  	super(AG.currentFrame,AG.resource.getString(R.string.Help),true);//~@@@@R~
//      super(AG.currentFrame,AG.resource.getString(R.string.Help),false);//~@@@@R~
        super((Pframe==null?AG.currentFrame:Pframe),AG.resource.getString(R.string.Help),R.layout.helpdialog,false/*not modal*/,false/*no input wait*/);//~@@@@R~
//        V=new Viewer();                                          //~@@@@R~
        setWindowSize(90/*W:90%*/,0/*H=wrap content*/,!AG.layoutMdpi/*for landscape,use ScrHeight for width limit if not mdpi*/);//~1A73I~
        F=Pframe==null?AG.currentFrame:Pframe;                     //~@@@@I~
        V=new Viewer(this,R.id.Viewer);                            //~@@@@I~
      if (AG.layoutMdpi)                                           //~1A72I~
      {                                                            //~1A72I~
        int fontsz=12;                                             //~1A72I~
        Font helpfont=Global.createfont("monospaced","Monospaced",fontsz);//~1A72I~
		V.setFont(helpfont);                                       //~1A72I~
      }                                                            //~1A72I~
      else                                                         //~1A72I~
		V.setFont(Global.Monospaced);                              //~@@@@I~
//      if (Presid==R.string.Help_MainFrame)                       //~1Aa6I~//~1AhgR~
//      	new ButtonAction(this,0,R.id.ButtonYouTube);           //~1Aa6I~//~1AhgR~
		V.setBackground(Global.gray);                              //~@@@@I~
		String s=AG.resource.getString(Presid);                    //~@@@@I~
        String[] sa=s.split("\n");                                 //~@@@@I~
        for (int ii=0;ii<sa.length;ii++)                           //~@@@@I~
        {                                                          //~@@@@I~
        	s=sa[ii];                                              //~@@@@I~
            if (s.endsWith("\n"))                                  //~@@@@I~
            	s=s.substring(0,s.length()-1);                       //~@@@@I~
        	V.appendLine(s);                                       //~@@@@I~
        }                                                          //~@@@@I~
		display();                                                 //~@@@@I~
    }                                                              //~@@@@I~
    private static final String URL_MOVIELIST_J="https://www.youtube.com/playlist?list=PL2clNB_BpXSWBjoGF6g7jgNLMiL7Sgw_6";//~1Aa6I~
    private static final String URL_MOVIELIST_E="https://www.youtube.com/playlist?list=PL2clNB_BpXSXUk1zyFXdnmM3wvFBYyCWw";//~1Aa6I~
	public void showMovie()                                        //~v1E7I~//~1Aa6I~
	{                                                              //~v1E7I~//~1Aa6I~
        String url="";                                             //~v1E7I~//~1Aa6I~
      	try                                                        //~v1E7I~//~1Aa6I~
      	{                                                          //~v1E7I~//~1Aa6I~
			boolean jp=Global.resourceString("HELP_SUFFIX").equals("_ja");//~v1E7R~//~1Aa6I~
        	if (jp)                                                //~v1E7R~//~1Aa6I~
        		url=URL_MOVIELIST_J;                               //~v1E7I~//~1Aa6I~
            else                                                   //~v1E7I~//~1Aa6I~
        		url=URL_MOVIELIST_E;                               //~v1E7I~//~1Aa6I~
        	Utils.showWebSite(url);                                //~v1E7I~//~1Aa6I~
      	}                                                          //~v1E7I~//~1Aa6I~
      	catch (Exception e)                                        //~v1E7I~//~1Aa6I~
      	{                                                          //~v1E7I~//~1Aa6I~
      		Dump.println(e,"HelpDialog:showMovie Exception url="+url);//~v1E7I~//~1Aa6I~
       		AView.showToast(R.string.Exception);                   //~v1E7I~//~1Aa6I~
	    }                                                          //~v1E7I~//~1Aa6I~
    }                                                              //~v1E7I~//~1Aa6I~
}
