//*CID://+1ambR~:                             update#=    7;       //~1ambR~
//*****************************************************************//~1Ah7I~
//1amb 2022/10/30 deprecated; Java9 new Integer,Boolean,Double-->valueOf//~1ambI~
//1Ah7 2020/05/31 Properties.save deprecated at api15, use store.  //~1Ah7I~
//*****************************************************************//~1Ah7I~
package rene.gui;

//import java.awt.Color;                                           //~1108R~
//import java.awt.Font;                                            //~1108R~
//import java.awt.Frame;                                           //~1108R~
//import java.awt.SystemColor;                                     //~1108R~
import com.Ahsv.awt.Color;                                           //~1108R~//~@@@@R~
import com.Ahsv.awt.Font;                                            //~1108R~//~@@@@R~
//import com.Ahsv.awt.Frame;                                           //~1108R~//~@@@@R~
import com.Ahsv.awt.SystemColor;                                     //~1108R~//~@@@@R~
                                                                   //~1108I~
import jagoclient.Dump;

import java.io.FileInputStream;
//import java.io.FileOutputStream;	@@@@                           //~1401R~
import com.Ahsv.java.FileOutputStream;                           //~1401I~//~@@@@R~
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

//import rene.dialogs.Warning;                                     //~@@@@R~
//import rene.util.FileName;                                       //~@@@@R~
import rene.util.parser.StringParser;

/**
The Global class.
<p>
This class will load a resource bundle with local support. It will
set various things from this resource file.
*/

public class Global
{   
	// Fonts:
	static public Font NormalFont=null,FixedFont=null,BoldFont=null;

	static public void makeFonts ()
	{	NormalFont=createfont(
			"normalfont","SansSerif",12,false);
		FixedFont=createfont(
			"fixedfont","Monospaced",12,false);
		BoldFont=createfont(
			"fixedfont","Monospaced",12,true);
	}	
	
	static
	{	makeFonts();
	}
	
	static public Font createfont (String name, String def, int defsize,
		boolean bold)
	{	String fontname=getParameter(name+".name",def);
		String mode=getParameter(name+".mode","plain");
	    if (bold || mode.equals("bold"))
	    {   return new Font(fontname,
	    		Font.BOLD,getParameter(name+".size",defsize));
	    }
	    else if (mode.equals("italic"))
	    {   return new Font(fontname,
	    		Font.ITALIC,getParameter(name+".size",defsize));
	    }
	    else
	    {   return new Font(fontname,
	    		Font.PLAIN,Global.getParameter(name+".size",defsize));
	    }	   
	}
	
	static public Color Background=null,ControlBackground=null;
	
	static
	{	makeColors();
	}
	
	static public void makeColors ()
	{	if (haveParameter("color.background"))
			Background=getParameter("color.background",Color.gray.brighter());
		else Background=SystemColor.window;
		if (haveParameter("color.control"))
			ControlBackground=getParameter("color.control",Color.gray.brighter());
		else ControlBackground=SystemColor.control;
	}
	
	// Resources:
	static protected ResourceBundle B;
	
	public static Enumeration names ()
	{ 	if (B!=null) return B.getKeys(); 
		else return null;
	}
	public static String name (String tag, String def)
	{	String s;
		if (B==null) return def;
		try
		{	s=B.getString(tag);
		}
		catch (Exception e)
		{	s=def;
		}
		return s;
	}
	public static String name (String tag)
	{	return name(tag,tag.substring(tag.lastIndexOf(".")+1));
	}
	public static void initBundle (String file, boolean language)
	{	try
		{	B=ResourceBundle.getBundle(file);
//            String lang=getParameter("language","");             //~@@@@R~
//            if (language && !lang.equals("") && !lang.equals("default"))//~@@@@R~
//            {   String langsec="";                               //~@@@@R~
//                if (lang.length()>3 && lang.charAt(2)=='_')      //~@@@@R~
//                {   langsec=lang.substring(3);                   //~@@@@R~
//                    lang=lang.substring(0,2);                    //~@@@@R~
//                }                                                //~@@@@R~
//                Locale.setDefault(new Locale(lang,langsec));     //~@@@@R~
//                initBundle(file,false);                          //~@@@@R~
//            }                                                    //~@@@@R~
		}
		catch (RuntimeException e)
		{	B=null;
		}
	}
	public static void initBundle(String file)
	{	initBundle(file,false);
	}
	
	// Properties:
	protected //@@@@ for Ahsv.rene.Global                          //~@@@@R~
	static Properties P=new Properties();
	static String ConfigName;
	
	public static synchronized Enumeration properties () { return P.keys(); }
	public static synchronized void loadProperties (InputStream in)
	{	try
		{	P=new Properties();
			P.load(in);
			in.close();
		}
		catch (Exception e)
		{	P=new Properties();
		}
	}
	public static synchronized boolean loadPropertiesFromResource (String filename)
	{	try
		{	Object G=new Object();
			InputStream in=G.getClass().getResourceAsStream(filename);
			P=new Properties();
			P.load(in);
			in.close();
		}
		catch (Exception e)
		{	P=new Properties();
			return false;
		}
		ConfigName=filename;
		return true;
	}
	public static synchronized boolean loadProperties (String filename)
	{	ConfigName=filename;
		try
		{	FileInputStream in=new FileInputStream(filename);
			P=new Properties();
			P.load(in);
			in.close();
		}
		catch (Exception e)
		{	P=new Properties();
			return false;
		}
		return true;
	}
	public static synchronized void loadProperties (String dir, String filename)
	{	try
		{	Properties p=System.getProperties();
			ConfigName=dir+
				p.getProperty("file.separator")+filename;
			loadProperties(ConfigName);
		}
		catch (Exception e)
		{	P=new Properties();
		}
	}
	public static synchronized void loadPropertiesInHome (String filename)
	{	try
		{	Properties p=System.getProperties();
			loadProperties(p.getProperty("user.home"),filename);
		}
		catch (Exception e)
		{	P=new Properties();
		}
	}
	public static synchronized void clearProperties ()
	{	P=new Properties();
	}
	public static synchronized void saveProperties (String text)
	{	try
		{	FileOutputStream out=new FileOutputStream(ConfigName);
//  		P.save(out,text);                                      //~1Ah7R~
    		P.store(out,text);                                     //~1Ah7I~
			out.close();
		}
		catch (Exception e)                                        //~1401R~
		{                                                          //~1401I~
            Dump.println(e,"Property:"+ConfigName+ "save failed");//~1308I~//~1401I~
		}                                                          //~1308I~//~1401I~
        if (Dump.Y) Dump.println("Property "+text+" saved to "+ConfigName);        //~1308I~//~1506R~
	}
	public static void saveProperties (String text, String filename)
	{	ConfigName=filename;
		saveProperties(text);
	}
	public static synchronized void setParameter (String key, boolean value)
	{	if (P==null) return;
		if (value) P.put(key,"true");
		else P.put(key,"false");
	}
	public static synchronized boolean getParameter (String key, boolean def)
	{	try
		{	String s=P.getProperty(key);
			if (s.equals("true")) return true;
			else if (s.equals("false")) return false;
			return def;
		}
		catch (Exception e)
		{	return def;
		}
	}
	public static synchronized String getParameter (String key, String def)
	{	String res=def;
		try
		{	res=P.getProperty(key);
		}
		catch (Exception e) {}
		if (res!=null)
		{	if (res.startsWith("$")) res=res.substring(1);
			return res;
		}
		else return def;
	}
//    public static double getVersion ()                           //~@@@@R~
//    {   String s=getParameter("program.version","0");            //~@@@@R~
//        int pos=s.indexOf(" ");                                  //~@@@@R~
//        if (pos>0) s=s.substring(0,pos);                         //~@@@@R~
//        try                                                      //~@@@@R~
//        {   return new Double(s).doubleValue();                  //~@@@@R~
//        }                                                        //~@@@@R~
//        catch (Exception e) {}                                   //~@@@@R~
//        return 0;                                                //~@@@@R~
//    }                                                            //~@@@@R~
	public static synchronized void setParameter (String key, String value)
	{	if (P==null) return;
		if (value.length()>0 && Character.isSpaceChar(value.charAt(0)))
			value="$"+value;
		P.put(key,value);
	}
	public static synchronized int getParameter (String key, int def)
	{	try
		{	return Integer.parseInt(getParameter(key,""));
		}
		catch (Exception e)
		{	try
//  		{	double x=new Double(getParameter(key,"")).doubleValue();//~1ambR~
            {                                                      //~1ambI~
    		 	double x=Double.valueOf(getParameter(key,"")).doubleValue();//~1ambI~
				return (int)x;
			}
			catch (Exception ex) {}
			return def;
		}
	}
	public static synchronized void setParameter (String key, int value)
	{	setParameter(key,""+value);
	}
	public static synchronized double getParameter (String key, double def)
	{	try
//  	{	return new Double(getParameter(key,"")).doubleValue(); //+1ambR~
    	{                                                          //+1ambI~
    	 	return Double.valueOf(getParameter(key,"")).doubleValue();//+1ambI~
		}
		catch (Exception e)
		{	return def;
		}
	}
	public static synchronized void setParameter (String key, double value)
	{	setParameter(key,""+value);
	}
	static public synchronized Color getParameter (String key, Color c)
	{	String s=getParameter(key,"");
		if (s.equals("")) return c;
		int red=0,green=0,blue=0;
		if (s.startsWith("#") && s.length()==7)
		{	red=Integer.parseInt(s.substring(1,3),16);
			green=Integer.parseInt(s.substring(3,5),16);
			blue=Integer.parseInt(s.substring(5,7),16);
		}
		else
		{	StringParser p=new StringParser(s);
			p.replace(',',' ');
			red=p.parseint(); green=p.parseint(); blue=p.parseint();
		}
		try
		{	return new Color(red,green,blue);
		}
		catch (RuntimeException e)
		{	return c;
		}
	}
	static public synchronized Color getParameter (String key, int red, int green, int blue)
	{	String s=getParameter(key,"");
		if (s.equals("")) return new Color(red,green,blue);
		StringParser p=new StringParser(s);
		p.replace(',',' ');
		red=p.parseint(); green=p.parseint(); blue=p.parseint();
		try
		{	return new Color(red,green,blue);
		}
		catch (RuntimeException e)
		{	return Color.black;
		}
	}
	public static synchronized void setParameter (String key, Color c)
	{	setParameter(key,""+c.getRed()+","+c.getGreen()+","+c.getBlue());
	}
	/**
	Remove a specific Paramater.
	*/
	public static synchronized void removeParameter (String key)
	{	P.remove((Object)key);
	}
	/**
	Remove all Parameters that start with the string.
	*/
	public static synchronized void removeAllParameters (String start)
	{	Enumeration e=P.keys();
		while (e.hasMoreElements())
		{	String key=(String)e.nextElement();
			if (key.startsWith(start))
			{	P.remove((Object)key);
			}
		}	
	}
	/**
	 * Set default values for parameters
	 * resetDefaults("default.") is the same as:
	 * setParameter("xxx",getParameter("default.xxx",""));
	 * if "default.xxx" has a value.
	 * @param defaults
	 */
	public static synchronized void resetDefaults (String defaults)
	{	Enumeration e=P.keys();
		while (e.hasMoreElements())
		{	String key=(String)e.nextElement();
			if (key.startsWith(defaults))
			{	setParameter(key.substring(defaults.length()),
					getParameter(key,""));
			}
		}	
	}
	public static void resetDefaults ()
	{	resetDefaults("default.");
	}
	/**
	@return if I have such a parameter.
	*/
	public static synchronized boolean haveParameter (String key)
	{	try
		{	String res=P.getProperty(key);
			if (res==null) return false;
		}
		catch (Exception e) { return false; }
		return true;
	}
	
	// Warnings
	
//    static Frame F=null;                                         //~@@@@R~
//    public static void warning (String s)                        //~@@@@R~
//    {   if (F==null)                                             //~@@@@R~
//        {   F=new Frame();                                       //~@@@@R~
//        }                                                        //~@@@@R~
//        Warning W=new Warning(F,s,name("warning"),false);        //~@@@@R~
//        W.center();                                              //~@@@@R~
//        W.setVisible(true);                                      //~@@@@R~
//    }                                                            //~@@@@R~
//    public static void warning (Frame f, String s)               //~@@@@R~
//    {   Warning W=new Warning(f,s,name("warning"),false);        //~@@@@R~
//        W.center(f);                                             //~@@@@R~
//        W.setVisible(true);                                      //~@@@@R~
//    }                                                            //~@@@@R~
	
	// Clipboard for applets
	static public String AppletClipboard=null;
	
	static public boolean IsApplet=false;
	static public void setApplet (boolean flag) { IsApplet=flag; }
	static public boolean isApplet () { return IsApplet; }
	
	// Java Version
	
	public static double getJavaVersion ()
	{	String version=System.getProperty("java.version");
		if (version==null) return 0.0;
		double v=0.0;
		StringTokenizer t=new StringTokenizer(version,".");
		if (t.hasMoreTokens())
		{	v=convert(t.nextToken());
		}
		else return v;
		if (t.hasMoreTokens())
		{	v=v+convert(t.nextToken())/10;
		}
		else return v;
		if (t.hasMoreTokens())
		{	v=v+convert(t.nextToken())/100;
		}
		return v;
	}
	
	public static double convert (String s)
	{	try
		{	return Integer.parseInt(s);
		}
		catch (Exception e)
		{	return 0;
		}
	}
	
//    public static synchronized String getUserDir ()              //~@@@@R~
//    {   String dir=System.getProperty("user.dir");               //~@@@@R~
//        return FileName.canonical(dir);                          //~@@@@R~
//    }                                                            //~@@@@R~
	
	public static Object ExitBlock=new Object();
	
	public static synchronized void exit (int i)
	{	synchronized (ExitBlock)
		{	System.exit(i);
		}
	}
	
	public static void main (String args[])
	{	try
		{	System.out.println(new Color(4,5,600));
		}
		catch (RuntimeException e) {}
	}
}
