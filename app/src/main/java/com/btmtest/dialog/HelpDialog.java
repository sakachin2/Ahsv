//*CID://+1AhmR~:                             update#=  161;       //~1AhmR~
//*****************************************************************//~v101I~
//1Ahm 2020/06/05 fromHtml deprecated at AndroidN(api24)           //~1AhmI~
//1Ahk 2020/06/05 Connect button for all connection type           //~1AhkI~
//*****************************************************************//~1AhkI~
package com.btmtest.dialog;                                         //~v@@@R~

import android.annotation.TargetApi;
import android.app.Dialog;
import android.text.Html;
import android.text.Spanned;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;                                    //~v@@@I~
import android.view.View;                                          //~v@@@I~

import com.Ahsv.AG;
import com.Ahsv.R;                                              //~v@@@I~
import com.Ahsv.Utils;
import com.btmtest.gui.UButton;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;

import jagoclient.Dump;

import static android.text.Html.*;

public class HelpDialog extends Dialog                               //~v@@@R~//~1AhkR~
    implements  UButton.UButtonI                                   //~1AhkI~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Help;    //~v@@@R~
    private static final int LAYOUTID=R.layout.helpdialoghtml;     //~1AhkI~
    private static final String DEFAULT_FILENAME="default.history";   //~v@@@R~
    private TextView tvMessage;                                    //~v@@@I~
    private String helpFilename;                                   //~v@@@I~
    private ViewGroup layoutView;                                  //~1AhkI~
    private String title;                                          //~1AhkI~
//**********************************                               //~v@@@I~
	public HelpDialog()                                              //~v@@@I~
    {                                                              //~v@@@I~
        super(AG.context);                                         //~1AhkI~
    	if (Dump.Y) Dump.println("btmtest.HelpDialog.constructor");//+1AhmI~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance(String Ptitle,String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
    	HelpDialog hdlg=new HelpDialog();                          //~v@@@I~
        hdlg.helpFilename=Pfnm;                                    //~v@@@I~
        hdlg.title=Ptitle;                                         //~1AhkI~
    	if (Dump.Y) Dump.println("HelpDialog.newInstance title="+Ptitle+",fnm="+Pfnm);//~1AhmI~
    	return hdlg;                                               //~v@@@I~
     }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance(int Ptitleid,String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
    	return newInstance(Utils.getStr(Ptitleid),Pfnm);                  //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance()                         //~v@@@I~
    {                                                              //~v@@@I~
    	return newInstance(TITLEID,DEFAULT_FILENAME);              //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~1AhkI~
	public void showDlg()                                          //~1AhkI~
    {                                                              //~1AhkI~
    	layoutView=(ViewGroup)(AG.inflater.inflate(LAYOUTID,null));//~1AhkM~
        setContentView(layoutView);                                //~1AhkM~
        initLayout(layoutView);                                    //~1AhkM~
        show();                                                    //~1AhkI~
    }                                                              //~1AhkI~
    //******************************************                   //~v@@@M~
    public void initLayout(View Playoutview)                       //~v@@@R~
	{                                                              //~v@@@M~
		String txt,htmltxt;                                        //~v@@@I~
    //******************************                               //~v@@@I~
		UButton.bind(Playoutview,R.id.Close,this);                     //~1AhkI~
        tvMessage=(TextView)UView.findViewById(Playoutview,R.id.Message); //~v@@@I~
        htmltxt=UFile.getHelpFileExt(helpFilename,".htm",false);   //~v@@@R~
	    setTitle(title);                                           //~1AhkI~
        if (htmltxt!=null)                                         //~v@@@I~
        {                                                          //~v@@@I~
//      	txt=adjustHtml(htmltxt);                               //~v@@@R~
        	txt=htmltxt;                                           //~v@@@I~
			Spanned s;                                             //~v@@@I~
        	if (AG.osVersion>=24) // Nougat:android 7.0            //~v@@@I~
			    s=getHtmlSpanned(txt);                             //~v@@@R~
            else                                                   //~v@@@I~
//      		s=Html.fromHtml(txt);                              //~v@@@R~//~1AhmR~
			    s=getHtmlSpanned_underN(txt);                      //~1AhmI~
            tvMessage.setText(s);                                  //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
		    txt=UFile.getHelpFileText(helpFilename);               //~v@@@R~
	    	tvMessage.setText(txt);                                //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
    private String adjustHtml(String Ptxt)                                        //~v@@@I~
    {                                                              //~v@@@I~
        String txt;                                                //~v@@@I~
        txt=Ptxt.replaceAll(" ","&nbsp;");                      //~v@@@I~
//      Pattern p=Pattern.compile("([^>])(\n)");                   //~v@@@R~
  //    Pattern p=Pattern.compile("([^>])$",Pattern.MULTILINE);    //~v@@@R~
  //    Matcher m=p.matcher(txt);                                  //~v@@@R~
//      txt=m.replaceAll("$1<BR>$2");                              //~v@@@R~
  //    txt=m.replaceAll("$0<BR>");                                //~v@@@R~
//      txt=txt.replaceAll("^\n","<BR>\n");                        //~v@@@I~
    	if (Dump.Y) Dump.println("HelpDialog adjustHtml inp="+Ptxt+",out="+txt);//~v@@@I~
        return txt;
    }                                                              //~v@@@I~
//**********************************                               //~1AhmI~
	@SuppressWarnings("deprecation")                               //~1AhmI~
    public Spanned getHtmlSpanned_underN(String Ptxt)              //~1AhmI~
    {                                                              //~1AhmI~
    	if (Dump.Y) Dump.println("HelpDialog getHtmlSpanned api<24(Nogaut 7.0) string="+Ptxt);//~1AhmI~
        Spanned s=Html.fromHtml(Ptxt);                             //~1AhmI~
        return s;                                                  //~1AhmI~
	}                                                              //~1AhmI~
//**********************************                               //~v@@@I~
	@TargetApi(24) //android7 Nougat                               //~v@@@R~
    public Spanned getHtmlSpanned(String Ptxt)                     //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("HelpDialog getHtmlSpanned api>=24(Nogaut 7.0) string="+Ptxt);//~v@@@I~
//      int flag=TO_HTML_PARAGRAPH_LINES_INDIVIDUAL;               //~v@@@R~
        int flag=FROM_HTML_MODE_LEGACY;                            //~v@@@I~
        Spanned s=Html.fromHtml(Ptxt,flag);                     //~v@@@I~
        return s;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
	public static void showDlg(String Ptitle,String Pfnm)                  //~v@@@I~//~1AhkR~
	{                                                              //~v@@@I~
        newInstance(Ptitle,Pfnm).showDlg();                           //~v@@@I~//~1AhkR~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
	public static void showDlg(int Ptitleid,String Pfnm)                   //~v@@@I~//~1AhkR~
	{                                                              //~v@@@I~
        newInstance(Ptitleid,Pfnm).showDlg();                         //~v@@@I~//~1AhkR~
    }                                                              //~v@@@I~
    //******************************************                   //~1AhkI~
    @Override //UButtonI                                           //~1AhkI~
    public void onClickButton(Button Pbutton)                      //~1AhkI~
	{                                                              //~1AhkI~
        if (Dump.Y) Dump.println("ProgDlg:onClickButton"+Pbutton.getText());//~1AhkI~
    	try                                                        //~1AhkI~
        {                                                          //~1AhkI~
        	int id=Pbutton.getId();                                //~1AhkI~
        	switch(id)                                             //~1AhkI~
            {                                                      //~1AhkI~
            case R.id.Close:                                       //~1AhkI~
    	    	dismiss();                                         //~1AhkI~
                break;                                             //~1AhkI~
            }                                                      //~1AhkI~
        }                                                          //~1AhkI~
        catch(Exception e)                                         //~1AhkI~
        {                                                          //~1AhkI~
            Dump.println(e,"ProgDlg:onClick:"+Pbutton.getText());  //~1AhkI~
        }                                                          //~1AhkI~
    }                                                              //~1AhkI~
}//class                                                           //~v@@@R~
