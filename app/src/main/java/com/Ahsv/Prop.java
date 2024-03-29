//*CID://+1ak0R~:                             update#=   17;       //~va40R~//~1ak0R~
//************************************************************************//~v102I~
//1ak0 2021/08/26 androd11:externalStorage:ScopedStorage           //~1ak0I~
//2020/11/04 va40 of BTMJ5 Android10(api29) upgrade                //~va40I~
//1Ad2 2015/07/17 HelpDialog by helptext(//1A1j 2013/03/19 change Help file encoding to utf8 (path change drop jagoclient from jagoclient/helptexts)//~1Ad2I~
//1077:121208 control greeting by app start counter                //~v107I~
//1075:121207 control dumptrace by manifest debuggable option      //~v107I~
//1074:121207 no detail exception info for SDcard/resources        //~v107I~
//v102:120718 (Axe)miss serverlist when SDcard missing             //~v102I~
//************************************************************************//~v102I~
package com.Ahsv;                                                 //~1110I~//~v107R~
                                                                   //~1110I~
import com.Ahsv.R;                                                 //~1Ad2R~
import com.btmtest.utils.UFile;
import com.btmtest.utils.UScoped;
//~1110I~
import jagoclient.Dump;
import jagoclient.Global;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources.NotFoundException;
import android.os.Environment;
                                                                   //~1110I~
public class Prop                                             //~1110I~//~v107R~
{                                                                  //~1110I~
    public static final String GAMEFILE="GameFileFolder";          //~1402I~
                                                                   //~1402I~
	public static final String DATAFILE_PREFIX="save";             //~1309I~//~1313R~
	public static final String SDFILE_PREFIX="rename";             //~1423I~
	private static final int BUFFSZ=1024;                          //~1511R~
	private static final int BUFFSZ2=32760;                        //~1511I~
	private static boolean availableAjagoSD=true;                  //~1313I~
	private static String dirAjagoSD;                              //~1425R~
	private static int rawFileId;                                  //~1425R~
//********************************************************         //~1401I~
//*to intercept FileOuputStream of Global                          //~1401I~
//* return FileOutputStream for cfgfile on /data/data              //~1401I~
//********************************************************         //~1401I~
	public static String getOutputFilenameData(String Pfilename)   //~1401I~
	{                                                              //~1401I~
        String path,filename;                                      //~1401R~
                                           //~1401I~
    //***************************                                  //~1401I~
        if (Dump.Y) Dump.println("getFileOutputStreamData:"+Pfilename);//~1506R~
		filename=getConfigFilename(Pfilename);                     //~1401R~
        path=AG.context.getFilesDir()+"/"+filename;                //~1401I~
        if (Dump.Y) Dump.println("GetFileOutputStreamData path="+path);//~1506R~
        return path;                                               //~1401R~
	}                                                              //~1401I~
//********************************************************         //~1329I~
//*to intercept FileInputStream of Global                          //~1329I~
//* return filename for  cfgfile                                   //~1329I~
//*   save.xxx.cfg on /data/data                                   //~1329I~
//*   if not save copy from /res/raw to /data/data                 //~1329I~
//    for other than cfg file return /data/data/fnm                //~1329I~
//* return helpfilename on SDcard                                  //~1412I~
//********************************************************         //~1329I~
	public static String getInputFilenameDataRaw(String Pfilename) //~1329R~
		throws NotFoundException                                   //~1329I~
	{                                                              //~1329I~
        String path,filename;                 //~1329I~            //~1401R~
    //***************************                                  //~1329I~
        if (Dump.Y) Dump.println("getFileInputFilenameDataRaw:"+Pfilename);//~1506R~
        if ((path=getHelpFilename(Pfilename))!=null)               //~1412I~
        	return path;                                           //~1412I~
		filename=getConfigFilename(Pfilename);                     //~1401R~
        if (Dump.Y) Dump.println("GetInputFilenameDataRaw filename="+filename);//~1329I~//~1506R~
        path=AG.context.getFilesDir()+"/"+filename;                //~1401I~
        if (rawFileId==0)	//not cfg file                                 //~1329I~//~1401R~
            return path;                                       //~1329I~//~1401R~
        if ((new File(path)).exists())  //file on /data/data   //~1329I~//~1423R~
        {                                                          //~1423R~
            if (Dump.Y) Dump.println("GetFileInputFilenameDataRaw exist path="+path);//~1329I~//~1506R~
            return path;                                       //~1329I~//~1423R~
        }                                                          //~1423R~
        copyToDataDir(rawFileId,filename);                         //~1423R~
        return path;                                           //~1329I~//~1401R~
	}                                                              //~1329I~
//********************************************************         //~1419R~
//*to intercept FileInputStream of Global                          //~1419R~
//* return inputStream for cfg/help file                           //~1419R~
//*   save.xxx.cfg on /data/data                                   //~1419R~
//*   if not save copy from /res/raw to /data/data                 //~1419R~
//    for other than cfg file return    /data/data/fnm             //~1419R~
//* return helpfile InputSream on SDcardm,if not found of asset/   //~1419R~
//********************************************************         //~1419R~
    public static InputStream getInputStreamDataRaw(String Pfilename)//~1419R~
        throws FileNotFoundException                               //~1419R~
    {                                                              //~1419R~
        String path,filename;                                      //~1419R~
        InputStream is;                                            //~1419R~
    //***************************                                  //~1419R~
        if (Dump.Y) Dump.println("getInputStreamDataRaw:"+Pfilename);//~1506R~
        if ((is=getInputStreamHelpFile(Pfilename))!=null)          //~1419R~
            return is;                                             //~1419R~
        filename=getConfigFilename(Pfilename);                     //~1419R~
        if (Dump.Y) Dump.println("getFileInputStreamDataRaw filename="+filename);//~1506R~
        path=AG.context.getFilesDir()+"/"+filename;                //~1419R~
        if (rawFileId==0)   //not cfg file                         //~1419R~
            return new FileInputStream(path);                      //~1419R~
        if (!chkConfigOnSD(filename))	//chk new file on SDcard   //~1423R~
        {                                                          //~1423M~
            if ((new File(path)).exists())  //file on /data/data   //~1423R~
            {                                                      //~1423R~
                if (Dump.Y) Dump.println("getInputStreamDataRaw exist path="+path);//~1506R~
                return new FileInputStream(path);                  //~1423R~
            }                                                      //~1423R~
            copyToDataDir(rawFileId,filename);                     //~1423R~
        }                                                          //~1423M~
        return new FileInputStream(path);                          //~1419R~
    }                                                              //~1419R~
//********************************************************         //~1401I~
//*get real filename                                               //~1401I~
//********************************************************         //~1401I~
	public static String getConfigFilename(String Pfilename)       //~1401I~
	{                                                              //~1401I~
        String filename,home;            //~1401I~
		int id=0;                                                  //~1401I~
    //***************************                                  //~1401I~
        if (Dump.Y) Dump.println("getConfigFilename:"+Pfilename);  //~1506R~
        home=Global.home();                                        //~1401I~
    	if (Pfilename.equals(home+".go.cfg"))                      //~1401I~
        	id=R.raw.go;                                           //~1401I~
        else                                                       //~1401I~
    	if (Pfilename.equals(home+".filter.cfg"))                  //~1401I~
        	id=R.raw.filter;                                       //~1401I~
        else                                                       //~1401I~
    	if (Pfilename.equals(home+".server.cfg"))                  //~1401I~
        	id=R.raw.server;                                       //~1401I~
        else                                                       //~1401I~
    	if (Pfilename.equals(home+".partner.cfg"))                 //~1401I~
        	id=R.raw.partner;                                      //~1401I~
        if (id!=0)                                                 //~1401I~
        	filename=DATAFILE_PREFIX+Pfilename.substring(home.length());//~1401I~
        else                                                       //~1401I~
        	filename=Pfilename;                                    //~1401I~
        rawFileId=id;                                              //~1401I~
        if (Dump.Y) Dump.println("getConfigFilename  filename="+filename+",id="+Integer.toHexString(id));//~1506R~
        return filename;                                           //~1401R~
	}                                                              //~1401I~
//********************************************************         //~1423I~
//*chk new cfg file on SDcard                                      //~1423I~
//*	if exit cpy to data/data then rename                           //~1423I~
//********************************************************         //~1423I~
	public static boolean chkConfigOnSD(String Pfilename)          //~1423R~
	{                                                              //~1423I~
    //***************************                                  //~1423I~
        if (Dump.Y) Dump.println("chkConfigOnSD:"+Pfilename);      //~1506R~
        String filenameSD=Pfilename.substring(DATAFILE_PREFIX.length()+1); //last name//~1423R~
        String cfgSDfile=getSDPath(filenameSD);   //path           //~1423R~
        if (cfgSDfile==null)                                       //~v102I~
        	return false;                                          //~v102I~
        if (!(new File(cfgSDfile)).exists())	//file on /data/data//~1423I~
        	return false;                                          //~1423I~
	    if (Dump.Y) Dump.println("chkConfigOnSD exist path="+cfgSDfile);//~1506R~
        if (!copyToDataDir(filenameSD,Pfilename))                  //~1423R~
        	return false;                                          //~1423I~
        if (renameFile(cfgSDfile))                             //~1423I~
        	return false;                                          //~1423I~
        return true;                                               //~1423I~
	}                                                              //~1423I~
//********************************************************         //~1412I~
//*return SDCARD/jagoclient/hexlptext/subjext.txt if exist         //~1418R~
//********************************************************         //~1412I~
	public static String getHelpFilename(String Pfilename)         //~1412I~
	{                                                              //~1412I~                                     //~1412I~                                               //~1412I~
    //***************************                                  //~1412I~
        if (Dump.Y) Dump.println("getHelpFilename:"+Pfilename);    //~1506R~
//  	if (!Pfilename.startsWith(Global.home()+"jagoclient/helptexts"))//~1412R~//~1Ad2R~
    	if (!Pfilename.startsWith(Global.home()+"helptexts"))      //~1Ad2I~
        	return null;                                           //~1412I~
        String helpfile=getSDPath(Pfilename);                             //~1412I~
      if (helpfile!=null)                                          //~v102I~
        if ((new File(helpfile)).exists())	//file on /data/data   //~1418I~
        {                                                          //~1418I~
	        if (Dump.Y) Dump.println("GetHelpFilename exist path="+helpfile);//~1506R~
            return helpfile;                                           //~1418I~
        }                                                          //~1418I~
        helpfile=AG.resource.getText(R.raw.go).toString();         //~1418R~
        if ((new File(helpfile)).exists())	//file on /data/data   //~1418I~
        {                                                          //~1418I~
        	if (Dump.Y) Dump.println("gethelpFilename raw/folder filename="+helpfile);//~1506R~
            return helpfile;                                       //~1418I~
        }                                                          //~1418I~
        if ((new File("/"+helpfile)).exists())	//file on /data/data//~1418I~
        {                                                          //~1418I~
        	if (Dump.Y) Dump.println("gethelpFilename raw/folder filename="+helpfile);//~1506R~
            return helpfile;                                       //~1418I~
        }                                                          //~1418I~
        if ((new File("Ajagot1/"+helpfile)).exists())	//file on /data/data//~1418I~
        {                                                          //~1418I~
        	if (Dump.Y) Dump.println("gethelpFilename raw/folder filename="+helpfile);//~1506R~
            return helpfile;                                       //~1418I~
        }                                                          //~1418I~
        AG.resource.openRawResource(R.raw.go);                     //~1418I~
        if (Dump.Y) Dump.println("gethelpFilename return filename="+Pfilename);//~1506R~
        return Pfilename;                                          //~1418R~
	}                                                              //~1412I~
//********************************************************         //~1419I~
//*return InputStream for helpfile; SDCARD or asset folder         //~1419R~
//********************************************************         //~1419I~
	public static InputStream getInputStreamHelpFile(String Pfilename)
		throws FileNotFoundException
	{                                                              //~1419I~
    	InputStream is;                                            //~1419I~
        String filename,home;                                           //~1419I~
    //***************************                                  //~1419I~
        if (Dump.Y) Dump.println("getHelpFilename:"+Pfilename);    //~1506R~
        home=Global.home();//~1419I~
//  	if (!Pfilename.startsWith(home+"jagoclient/helptexts"))//~1419I~//~1Ad2R~
    	if (!Pfilename.startsWith(home+"helptexts"))               //~1Ad2I~
        	return null;                                           //~1419I~
        filename=Pfilename.substring(home.length());                //~1419I~
		is=openInputSD(filename);   //check SD card                //~1419I~
		if (is==null)                                              //~1419I~
			try
			{	
				is=AG.resource.getAssets().open(filename);             //~1419I~
			}
			catch(Exception e)
			{
				throw new FileNotFoundException();
			}
		return is;                                                 //~1419R~
	}                                                              //~1419I~
//********************************************************         //~1511I~
//*open Assetfile for Agnugo copy                                  //~1511I~
//********************************************************         //~1511I~
	public static AssetFileDescriptor openAssetFileFD(String Pfilename)//~1511I~
	{                                                              //~1511I~
    	AssetFileDescriptor fd;                                    //~1511I~
    //***************************                                  //~1511I~
        if (Dump.Y) Dump.println("openAssetFileFD:"+Pfilename);    //~1511I~
        try                                                        //~1511I~
        {                                                          //~1511I~
            fd=AG.resource.getAssets().openFd(Pfilename);          //~1511I~
        }                                                          //~1511I~
        catch(Exception e)                                         //~1511I~
        {                                                          //~1511I~
	        if (Dump.Y) Dump.println("openAssetFileFD failed:"+Pfilename);//~1511I~
            fd=null;                                               //~1511I~
        }                                                          //~1511I~
		return fd;                                                 //~1511I~
	}                                                              //~1511I~
//********************************************************         //~1511I~
//*open Assetfile for Agnugo copy                                  //~1511I~
//********************************************************         //~1511I~
	public static InputStream openAssetFile(String Pfilename)      //~1511I~
	{                                                              //~1511I~
    	InputStream is;                                            //~1511I~
    //***************************                                  //~1511I~
        if (Dump.Y) Dump.println("openAssetFile:"+Pfilename);      //~1511I~
        try                                                        //~1511I~
        {                                                          //~1511I~
            is=AG.resource.getAssets().open(Pfilename);            //~1511I~
        }                                                          //~1511I~
        catch(Exception e)                                         //~1511I~
        {                                                          //~1511I~
	        if (Dump.Y) Dump.println("openAssetFile failed:"+Pfilename);//~1511I~
            is=null;                                               //~1511I~
        }                                                          //~1511I~
		return is;                                                 //~1511I~
	}                                                              //~1511I~
//********************************************************         //~1511I~
//*open Assetfile for Agnugo copy                                  //~1511I~
//********************************************************         //~1511I~
	public static long getAssetFileSize(String Pfilename)          //~1511I~
	{                                                              //~1511I~
    	AssetFileDescriptor fd;                                    //~1511I~
        long sz;                                                   //~1511I~
    //***************************                                  //~1511I~
        fd=openAssetFileFD(Pfilename);                             //~1511I~
        if (fd==null)                                              //~1511I~
        	sz=-1;                                                 //~1511I~
        else                                                       //~1511I~
        	sz=fd.getLength();                                      //~1511I~
        try
         {
			fd.close();
		 }
        catch (IOException e)
         {
			Dump.println(e,"xception Asset openFD"+Pfilename);
			e.printStackTrace();
		}                                                //~1511I~
        return sz;                                                 //~1511I~
	}                                                              //~1511I~
//********************************************************         //~1327I~
//*copy /res/raw file to /data/data private dir                    //~1329I~
//*retur success/false                                             //~1329I~
//********************************************************         //~1329I~
	public static boolean copyToDataDir(int Prawresid,String Pfname)//~1327I~
	{                                                              //~1327I~
        InputStream is;                                       //~1327I~
		FileOutputStream fos;                                      //~1327I~
        int len;                                                   //~1327I~
        boolean success=true;                                      //~1327I~
        byte [] buff;                                              //~1327I~
	//*********************                                        //~1327I~
    	if (Dump.Y) Dump.println("copyToDataDir:"+Pfname);         //~1506R~
		is=AG.resource.openRawResource(Prawresid);	//read from res/raw//~1327I~
        if (is==null)  //1st time    no save.xxx on data/data     //~1327I~
        	return false;                                          //~1327I~
		fos=openOutputData(Pfname);	///data/data (no search SD)    //~1329R~
        if (fos==null)                                             //~1327I~
        	return false;                                          //~1327I~
        buff=new byte[BUFFSZ];                                     //~1327I~
        try                                                        //~1327I~
        {                                                          //~1327I~
        	for (;;)                                               //~1327I~
            {                                                      //~1327I~
        		len=is.read(buff);	                               //~1327I~
            	if (len<0)                                         //~1327I~
            		break;                                         //~1327I~
            	fos.write(buff,0,len);                             //~1327I~
            }                                                      //~1327I~
        }                                                          //~1327I~
        catch (Exception e)                                        //~1327I~
        {                                                          //~1327I~
        	success=false;                                         //~1327I~
            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1327I~
        }//catch                                                   //~1327I~
        try
        {
        	is.close();                                               //~1327I~
        	fos.close();
        	if (Dump.Y) Dump.println("close copy to SDfailed"+Pfname);//~1506R~
        }
        catch(IOException e)
        {
        	success=false;
        	Dump.println(e,"close failed"+Pfname);//~1327I~
        }
        return success;                                            //~1327I~
	}                                                              //~1327I~
//********************************************************         //~1423I~
//*copy /sdcard/Ajagoc/cfg file to /data/data private dir          //~1423I~
//*retur success/false                                             //~1423I~
//********************************************************         //~1423I~
	public static boolean copyToDataDir(String Pfname,String PfnameData)//~1423I~
	{                                                              //~1423I~
        InputStream is;                                            //~1423I~
		FileOutputStream fos;                                      //~1423I~
        int len;                                                   //~1423I~
        boolean success=true;                                      //~1423I~
        byte [] buff;                                              //~1423I~
	//*********************                                        //~1423I~
    	if (Dump.Y) Dump.println("copyToDataDir:SD="+Pfname+",data="+PfnameData);//~1506R~
	    is=openInputSD(Pfname);                  //~1423I~
        if (is==null)                                             //~1423I~
        	return false;                                          //~1423I~
		fos=openOutputData(PfnameData);	///data/data (no search SD)//~1423R~
        if (fos==null)                                             //~1423I~
        	return false;                                          //~1423I~
        buff=new byte[BUFFSZ];                                     //~1423I~
        try                                                        //~1423I~
        {                                                          //~1423I~
        	for (;;)                                               //~1423I~
            {                                                      //~1423I~
        		len=is.read(buff);                                //~1423I~
            	if (len<0)                                         //~1423I~
            		break;                                         //~1423I~
            	fos.write(buff,0,len);                             //~1423I~
            }                                                      //~1423I~
        }                                                          //~1423I~
        catch (Exception e)                                        //~1423I~
        {                                                          //~1423I~
        	success=false;                                         //~1423I~
            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1423I~
        }//catch                                                   //~1423I~
        try                                                        //~1423I~
        {                                                          //~1423I~
        	is.close();                                           //~1423I~
        	fos.close();                                           //~1423I~
        	if (Dump.Y) Dump.println("close copy to Data failed"+Pfname);//~1506R~
        }                                                          //~1423I~
        catch(IOException e)                                       //~1423I~
        {                                                          //~1423I~
        	success=false;                                         //~1423I~
        	Dump.println(e,"close failed"+Pfname);                 //~1423I~
        }                                                          //~1423I~
        return success;                                            //~1423I~
	}                                                              //~1423I~
//********************************************************         //~1511I~
//*copy /sdcard/Ajagoc/cfg file to /data/data private dir          //~1511I~
//*retur success/false                                             //~1511I~
//********************************************************         //~1511I~
	public static boolean copyAssetToDataDir(String Pfname,String PfnameData)//~1511I~
	{                                                              //~1511I~
        InputStream is;                                            //~1511I~
		FileOutputStream fos;                                      //~1511I~
        int len;                                                   //~1511I~
        boolean success=true;                                      //~1511I~
        byte [] buff;                                              //~1511I~
	//*********************                                        //~1511I~
    	if (Dump.Y) Dump.println("copyToDataDir:SD="+Pfname+",data="+PfnameData);//~1511I~
	    is=openAssetFile(Pfname);                                  //~1511I~
        if (is==null)                                              //~1511I~
        	return false;                                          //~1511I~
		fos=openOutputData(PfnameData);	///data/data (no search SD)//~1511I~
        if (fos==null)                                             //~1511I~
        	return false;                                          //~1511I~
        buff=new byte[BUFFSZ2];                                    //~1511I~
        try                                                        //~1511I~
        {                                                          //~1511I~
        	for (;;)                                               //~1511I~
            {                                                      //~1511I~
        		len=is.read(buff);                                 //~1511I~
            	if (len<0)                                         //~1511I~
            		break;                                         //~1511I~
            	fos.write(buff,0,len);                             //~1511I~
            }                                                      //~1511I~
        }                                                          //~1511I~
        catch (Exception e)                                        //~1511I~
        {                                                          //~1511I~
        	success=false;                                         //~1511I~
            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1511I~
        }//catch                                                   //~1511I~
        try                                                        //~1511I~
        {                                                          //~1511I~
        	is.close();                                            //~1511I~
        	fos.close();                                           //~1511I~
        }                                                          //~1511I~
        catch(IOException e)                                       //~1511I~
        {                                                          //~1511I~
        	success=false;                                         //~1511I~
        	Dump.println(e,"close failed"+Pfname);                 //~1511I~
        }                                                          //~1511I~
        return success;                                            //~1511I~
	}                                                              //~1511I~
//********************************************************         //~1423I~
//*rename cfg on sdcard after copyed to data/data                  //~1423I~
//*retur success/false                                             //~1423I~
//********************************************************         //~1423I~
	public static boolean renameFile(String Pfname)                //~1423R~
	{                                                              //~1423I~
        boolean success=true;                                      //~1423I~                                            //~1423I~
	//*********************                                        //~1423I~
    	if (Dump.Y) Dump.println("renameFile="+Pfname);            //~1506R~
        File oldfile=new File(Pfname);                             //~1423R~
        if (!oldfile.exists())	//file on /data/data               //~1423I~
        	return false;                                          //~1423I~
        String ts=Utils.getTimeStamp(Utils.TS_DATE_TIME);            //~1425R~//~@@@@R~
        String newname=Pfname+"."+SDFILE_PREFIX+"."+ts;            //~1423R~
        File newfile=new File(newname);                            //~1423I~
        success=oldfile.renameTo(newfile);                              //~1423I~
        return success;                                            //~1423I~
	}                                                              //~1423I~
//*******************************************                      //~1423R~
    public static FileInputStream openInputData(String Pfname)        //~1309I~//~1312R~//~1327R~
    {                                                              //~1309I~
	    FileInputStream in=null;                                       //~1309I~//~1327R~
        if (Dump.Y) Dump.println("openInputStream:"+Pfname);       //~1506R~
        try                                                        //~1309I~
        {                                                          //~1309I~
            in=AG.context.openFileInput(Pfname);                   //~1309I~
	    	File f=new File(Pfname);                               //~1309I~
	    	if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpath="+f.getAbsolutePath());//~1309I~//~1506R~
	    	if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpathname="+f.getAbsoluteFile());//~1309I~//~1506R~
	    	if (Dump.Y) Dump.println("Test isiexst by File()="+f.exists());             //~1309I~//~1506R~
	    	if (Dump.Y) Dump.println("Test tiimestamp="+Long.toHexString(f.lastModified()));//~1309I~//~1506R~
            f=Environment.getDataDirectory();                      //~1309I~
	    	if (Dump.Y) Dump.println("DataDir Path="+f.getAbsolutePath());//~1309I~//~1506R~
	    	f=new File(AG.context.getFilesDir(),Pfname);	//android.app.contextImple//~1309I~
	    	if (Dump.Y) Dump.println(f.toString()+":isiexst="+f.exists());//~1309I~//~1506R~
	    	if (Dump.Y) Dump.println("tiimestamp="+Long.toHexString(f.lastModified()));//~1309I~//~1506R~
        }                                                          //~1309I~
        catch(FileNotFoundException e)                             //~1309I~
        {                                                          //~1309I~
//          Dump.println(e,"openInputData failed "+Pfname);  //~1309I~//~1312R~//~1329R~//~v107R~
            Dump.println("openInputData NotFound:"+Pfname);        //~v107I~
        }                                                          //~1309I~
        catch (Exception e)                                        //~1309I~
        {                                                          //~1309I~
            Dump.println(e,"openInputData exception "+Pfname);              //~1309I~//~1312R~//~1329R~
        }//catch                                                   //~1309I~
    	return in;                                                 //~1309I~
    }                                                              //~1309I~
//*********************************************                                                       //~1309I~//~1329R~
//*output to SD if avale else private *********                    //~1329I~
//*********************************************                    //~1329I~
    public static FileOutputStream openOutputData(String Pdir,String Pfname)//~1313I~
    {                                                              //~1313I~
        String fnm,path;                                           //~1313I~
    	if (Dump.Y) Dump.println("openOutputData dir="+Pdir+",file="+Pfname);//~1313I~//~1506R~
		path=getSDPath(Pdir);                                      //~1313R~
        if (path==null)	//no SDCard available                          //~1313I~
        	return openOutputData(Pfname);                         //~1313I~
        fnm=path+System.getProperty("file.separator")+Pfname;      //~1313R~
        if (Dump.Y) Dump.println("openoutputData on SDcard fnm="+fnm);                       //~1313I~//~1506R~
	    FileOutputStream out=null;
		try {
			out = new FileOutputStream(fnm);
		} catch (Exception e)                                      //~1329R~
		{
			Dump.println(e,"openOutputData Exception:"+fnm);       //~1329R~
		}                //~1313I~
    	return out;                                                //~1313I~
    }                                                              //~1313I~
//*********************************************                    //~1511I~
//*get private data file path                                      //~1511I~
//*********************************************                    //~1511I~
    public static String getDataFileFullpath(String Pfname)        //~1511I~
    {                                                              //~1511I~
    	if (Dump.Y) Dump.println("getDataFileFullpath:"+Pfname);   //~1511I~
		String path=AG.context.getFilesDir()+"/"+Pfname;                      //~1511I~
    	if (Dump.Y) Dump.println("getDataFileFullpath:"+path);     //~1511I~
        return path;                                               //~1511I~
    }                                                              //~1511I~
//*********************************************                    //~1511I~
//*get private data file size                                      //~1511I~
//*********************************************                    //~1511I~
    public static long getDataFileSize(String Pfname)              //~1511I~
    {                                                              //~1511I~
    	if (Dump.Y) Dump.println("getDataFileSize:"+Pfname);       //~1511I~
		String path=AG.context.getFilesDir()+"/"+Pfname;                      //~1511I~
        File f=new File(path);                                     //~1511I~
        long sz;                                                   //~1511I~
        if (f.exists())                                            //~1511I~
        	sz=f.length();                                         //~1511I~
        else                                                       //~1511I~
        	sz=-1;                                                 //~1511I~
        if (Dump.Y) Dump.println("getDataFileSize:"+path+",sz="+sz);//~1511I~
    	return sz;                                                 //~1511I~
    }                                                              //~1511I~
//**********                                                       //~1401I~
    public static InputStream openInputSD(String Pfname)           //~1327I~
    {                                                              //~1327I~
        String fnm;                                           //~1327I~
    	if (Dump.Y) Dump.println("open Input SD="+Pfname);         //~1506R~
        if (AG.swScoped) //android11 api30                         //~1ak0R~
        {                                                          //~1ak0I~
        	return UScoped.openInputSD(Pfname);                    //~1ak0I~
        }                                                          //~1ak0I~
		fnm=getSDPath(Pfname);                                      //~1327I~
        if (fnm==null)	//no SDCard available                      //~1327I~
        	return null;                                           //~1327I~
                                        //~1327I~
        if (Dump.Y) Dump.println("openInputSD fnm="+fnm);                       //~1327I~//~1506R~
	    FileInputStream is=null;                                   //~1327I~
		try                                                        //~1327I~
		{                                                          //~1327I~
			is=new FileInputStream(fnm);                           //~1327I~
		}                                                          //~1327I~
		catch (FileNotFoundException e)                            //~1327I~
		{                                                          //~1327I~
//  		Dump.println(e,"openInputSD Exception:"+fnm);                     //~1327I~//~1329R~//~v107R~
    		Dump.println("@@@@ Prop:openInputSD FileNotFoundException:"+fnm);//~v107I~//+1ak0R~
		}                                                          //~1327I~
    	return (InputStream)is;                                    //~1327I~
    }                                                              //~1327I~
//*********************************************                    //~1329I~
//*output to private data/data        *********                    //~1329I~//~1401R~
//*********************************************                    //~1329I~
//***********************************                              //~1401I~
//*from FileOutputStream(Ajagoc.java)                              //~1401I~
//***********************************                              //~1401I~
    public static FileOutputStream openOutputDataCfg(String Pfname)//~1401I~
    {                                                              //~1401I~
    	if (Dump.Y) Dump.println("open Output private file from FileOutputStream="+Pfname);//~1506R~
        String home=Global.home();                                 //~1401I~
        String filename=DATAFILE_PREFIX+Pfname.substring(home.length());//~1401I~
    	return openOutputData(filename);                           //~1401I~
    }                                                              //~1401I~
//***********************************                              //~1401I~
    public static FileOutputStream openOutputData(String Pfname)      //~1309I~
    {                                                              //~1309I~
    	if (Dump.Y) Dump.println("open Output private file="+Pfname);    //~1309I~//~1506R~
	    FileOutputStream out=null;	//FileOutputStream extend OutputStream//~1309I~
        try                                                        //~1309I~
        {                                                          //~1309I~
            out=AG.context.openFileOutput(Pfname,Context.MODE_PRIVATE);//~1309I~
        }                                                          //~1309I~
        catch (Exception e)                                        //~1309I~
        {                                                          //~1309I~
            Dump.println(e,"open output exception "+Pfname);       //~1309I~
        }//catch                                                   //~1309I~
    	return out;                                                //~1309I~
    }                                                              //~1309I~
//**********                                                       //~1312I~
    public static boolean writeOutputData(String Pfname,byte[] Pbytedata)//~1511R~
    {                                                              //~1312I~
    	boolean rc=false;                                          //~1511I~
    	if (Dump.Y) Dump.println("write Output private file="+Pfname);//~1506R~
		FileOutputStream os=openOutputData(Pfname);                    //~1312I~
        if (os==null)                                              //~1312I~
        	return rc;                                             //~1511R~
        try                                                        //~1312I~
        {                                                          //~1312I~
            os.write(Pbytedata,0,Pbytedata.length);              //~1312I~
            os.close();                                            //~1312I~
            rc=true;                                               //~1511I~
        }                                                          //~1312I~
        catch (Exception e)                                        //~1312I~
        {                                                          //~1312I~
            Dump.println(e,"write output exception "+Pfname);      //~1312I~
        }//catch                                                   //~1312I~
        return rc;                                                 //~1511I~
    }                                                              //~1312I~
//*******************************                                  //~1313I~
//*SD card                      *                                  //~1313I~
//*Manifest setting                                                //~1313I~
//* <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>//~1313I~
//*******************************                                  //~1313I~
//    public static boolean isSDMounted()    //Use UFile             //~1313I~//~1ak0R~
//    {                                                              //~1313I~//~1ak0R~
//        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//~1313I~//~1ak0R~
//    }                                                              //~1313I~//~1ak0R~
//    public static String getSDPath(String Pfile)                   //~1313R~//~va40R~
//    {                                                              //~1313I~//~va40R~
//        String path;                                               //~1313I~//~va40R~
//                                                                 //~va40R~
//    //************                                                 //~1313I~//~va40R~
//        if (!availableAjagoSD)                                     //~1313I~//~va40R~
//            return null;                                           //~1313I~//~va40R~
//        path=dirAjagoSD;                                           //~1313I~//~va40R~
//        if (path==null)                                            //~1313I~//~va40R~
//        {                                                          //~1313I~//~va40R~
//            String approot=AG.appName;//~1323I~                    //~1402R~//~va40R~
//            if (!isSDMounted())                                    //~1313I~//~va40R~
//            {                                                      //~1313I~//~va40R~
//                availableAjagoSD=false;                            //~1313I~//~va40R~
//                return null;                                       //~1313I~//~va40R~
//            }                                                      //~1313I~//~va40R~
//            path=Environment.getExternalStorageDirectory().getPath()+System.getProperty("file.separator")+approot;//~1323R~//~va40R~
//            File f=new File(path);                               //~va40R~
//            if (!f.exists())                                       //~1313I~//~va40R~
//            {                                                      //~1313I~//~va40R~
//                if (!f.mkdir())                                 //~1313I~//~va40R~
//                {                                                  //~1313I~//~va40R~
//                    availableAjagoSD=false;                        //~1313I~//~va40R~
//                    if (Dump.Y) Dump.println("getSDpath mkdir failed:"+path);//~1506R~//~va40R~
//                    return null;                                   //~1313I~//~va40R~
//                }                                                  //~1313I~//~va40R~
//            }                                                      //~1313I~//~va40R~
//            dirAjagoSD=path;                                       //~1313I~//~va40R~
//            if (Dump.Y) Dump.println("SDcard dir="+path);                       //~1313I~//~1506R~//~va40R~
//        }                                                          //~1313I~//~va40R~
//        if (!Pfile.equals(""))                                     //~1313I~//~va40R~
//            if (Pfile.startsWith("/"))                            //~1412I~//~va40R~
//                path+=Pfile;                                       //~1412I~//~va40R~
//            else                                                   //~1412I~//~va40R~
//                path+="/"+Pfile;                                   //~1412R~//~va40R~
//        if (Dump.Y) Dump.println("GetSDpath:"+path);               //~1506R~//~va40R~
//        return path;                                               //~1313R~//~va40R~
//    }                                                              //~1313I~//~va40R~
    public static String getSDPath(String Pfile)                   //~va40I~
    {                                                              //~va40I~
        return UFile.getSDPath(Pfile);                             //~va40I~
    }                                                              //~va40I~
//**********************************************************************//~1402I~
//*Preference read/write                                               *//~1402I~
//**********************************************************************//~1402I~
    public static String getPreference(String Pkey)                      //~1402I~
    {                                                              //~1402I~
	    return getPreference(Pkey,"");                             //~1402I~
    }                                                              //~1402I~
    //******************                                           //~v107I~
    public static boolean getPreference(String Pkey,boolean Pdefault)//~v107I~
    {                                                              //~v107I~
    	SharedPreferences pref=getPreferenceName();                //~v107I~
        boolean value=pref.getBoolean(Pkey,Pdefault);              //~v107I~
        return value;                                              //~v107I~
    }                                                              //~v107I~
    //******************                                           //~v107I~
    public static int getPreference(String Pkey,int Pdefault)      //~v107I~
    {                                                              //~v107I~
    	SharedPreferences pref=getPreferenceName();                //~v107I~
        int value=pref.getInt(Pkey,Pdefault);                      //~v107I~
        return value;                                              //~v107I~
    }                                                              //~v107I~
    //******************                                           //~v107I~
    public static String getPreference(String Pkey,String Pdefault)//~1402I~
    {                                                              //~1402I~
    	SharedPreferences pref=getPreferenceName();                 //~1402I~
        String value=pref.getString(Pkey,Pdefault/*default value*/);//~1402R~
        if (Dump.Y) Dump.println("getPreference:"+Pkey+"="+value); //~1506R~
        return value;                                              //~1402I~
    }//readwriteQNo                                                //~1402I~
    //******************                                           //~1402I~
    public static void putPreference(String Pkey,String Pvalue)        //~1402I~
    {                                                              //~1402I~
        if (Dump.Y) Dump.println("putPreference:"+Pkey+"="+Pvalue);//~1506R~
    	SharedPreferences pref=getPreferenceName();                 //~1402I~
        SharedPreferences.Editor editor=pref.edit();               //~1402I~
        editor.putString(Pkey,Pvalue);                             //~1402I~
        editor.commit();                                           //~1402I~
    }                                                              //~v107R~
    //******************                                           //~1402I~
    public static void putPreference(String Pkey,boolean Pvalue)   //~v107I~
    {                                                              //~v107I~
    	SharedPreferences pref=getPreferenceName();                //~v107I~
        SharedPreferences.Editor editor=pref.edit();               //~v107I~
        editor.putBoolean(Pkey,Pvalue);                            //~v107I~
        editor.commit();                                           //~v107I~
    }                                                              //~v107I~
    //******************                                           //~v107I~
    public static void putPreference(String Pkey,int Pvalue)       //~v107I~
    {                                                              //~v107I~
    	SharedPreferences pref=getPreferenceName();                //~v107I~
        SharedPreferences.Editor editor=pref.edit();               //~v107I~
        editor.putInt(Pkey,Pvalue);                                //~v107I~
        editor.commit();                                           //~v107I~
    }                                                              //~v107I~
    //******************                                           //~v107I~
    private static SharedPreferences getPreferenceName()                   //~1402I~
    {                                                              //~1402I~
        return AG.context.getSharedPreferences(AG.appName+"-PrivatePreference",Context.MODE_PRIVATE);//~1402I~
    }                                                              //~1402I~
//**********************************************************************//~1402I~
//*makePath                                                        //~1402I~
//**********************************************************************//~1402I~
    public static boolean makePath(String Ppath)                   //~1402I~
    {                                                              //~1402I~
        File f;                                                    //~1402I~
        boolean rc;                                                //~1402I~
    //**********************                                       //~1402I~
    	if (Dump.Y) Dump.println("makePath:"+Ppath);               //~1506R~
        f=new File(Ppath);                                             //~1402I~
        if (f.exists())                                            //~1402I~
        	if (f.isDirectory())                                   //~1402I~
        		rc=true;                                           //~1402R~
            else                                                   //~1402I~
            	rc=false;                                          //~1402R~
		else                                                       //~1402I~
        {                                                          //~1402I~
        	f.mkdirs();	//create also parent path                  //~1402R~
            rc=true;                                               //~1402I~
        }                                                          //~1402I~
    	if (Dump.Y) Dump.println("makePath:rc"+rc);                //~1506R~
        return rc;                                                 //~1402R~
    }                                                              //~1402I~
}//class                                                           //~1110I~
