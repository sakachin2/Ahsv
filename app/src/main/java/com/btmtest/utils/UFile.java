//*CID://+va42R~:                             update#=   82;       //~va42R~//~va40R~//+va42R~
//************************************************************************//~v102I~
//2020/11/19 va42 of BTMJ5 At Android10, mkdir /sdcard/eMahjong fails//~va42I~
//2020/11/04 va40 of BTMJ5 Android10(api29) upgrade                //~va42I~
//1Ah1 2020/05/30 from BTMJ5                                       //~1AH1I~
//************************************************************************//~v102I~
package com.btmtest.utils;                                        //~1110I~//~v107R~//~1Ad2R~
                                                                   //~1110I~
                                                                   //~1110I~
//import com.btmtest.MainActivity;                                 //~1AH1R~
//import com.btmtest.MainView;                                     //~1AH1R~
//import com.btmtest.R;                                              //~v@@@I~//~1AH1R~
import com.Ahsv.AMain;
import com.Ahsv.R;                                                 //~1AH1I~

import static android.os.Environment.*;
//import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~//~1AH1R~
import com.Ahsv.AG;                                             //~1AH1I~
import com.Ahsv.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;                           //~v@@@I~
import android.os.Environment;

import jagoclient.Dump;

//~1110I~
public class UFile                                                 //~v@@@R~
{                                                                  //~1110I~
    public static final String GAMEFILE="GameFileFolder";          //~1402I~
                                                                   //~1402I~
	public static final String DATAFILE_PREFIX="save";             //~1309I~//~1313R~
	public static final String SDFILE_PREFIX="rename";             //~1423I~
	private static final int BUFFSZ=1024;                          //~1511R~
	private static final int BUFFSZ2=32760;                        //~1511I~
//    private static boolean swSDAvailable=true;                  //~1313I~//~v@@@R~
//    private static String dirSD;                              //~1425R~//~v@@@R~
//    private static int rawFileId;                                  //~1425R~//~v@@@R~
//********************************************************         //~1401I~
//*to intercept FileOuputStream of Global                          //~1401I~
//* return FileOutputStream for cfgfile on /data/data              //~1401I~
//********************************************************         //~1401I~
//    public static String getOutputFilenameData(String Pfilename)   //~1401I~//~v@@@R~
//    {                                                              //~1401I~//~v@@@R~
//        String path,filename;                                      //~1401R~//~v@@@R~
//                                           //~1401I~             //~v@@@R~
//    //***************************                                  //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("getFileOutputStreamData:"+Pfilename);//~1506R~//~v@@@R~
//        filename=getConfigFilename(Pfilename);                     //~1401R~//~v@@@R~
//        path=AG.context.getFilesDir()+"/"+filename;                //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("GetFileOutputStreamData path="+path);//~1506R~//~v@@@R~
//        return path;                                               //~1401R~//~v@@@R~
//    }                                                              //~1401I~//~v@@@R~
////********************************************************         //~1329I~//~v@@@R~
////*to intercept FileInputStream of Global                          //~1329I~//~v@@@R~
////* return filename for  cfgfile                                   //~1329I~//~v@@@R~
////*   save.xxx.cfg on /data/data                                   //~1329I~//~v@@@R~
////*   if not save copy from /res/raw to /data/data                 //~1329I~//~v@@@R~
////    for other than cfg file return /data/data/fnm                //~1329I~//~v@@@R~
////* return helpfilename on SDcard                                  //~1412I~//~v@@@R~
////********************************************************         //~1329I~//~v@@@R~
//    public static String getInputFilenameDataRaw(String Pfilename) //~1329R~//~v@@@R~
//        throws NotFoundException                                   //~1329I~//~v@@@R~
//    {                                                              //~1329I~//~v@@@R~
//        String path,filename;                 //~1329I~            //~1401R~//~v@@@R~
//    //***************************                                  //~1329I~//~v@@@R~
//        if (Dump.Y) Dump.println("getFileInputFilenameDataRaw:"+Pfilename);//~1506R~//~v@@@R~
//        if ((path=getHelpFilename(Pfilename))!=null)               //~1412I~//~v@@@R~
//            return path;                                           //~1412I~//~v@@@R~
//        filename=getConfigFilename(Pfilename);                     //~1401R~//~v@@@R~
//        if (Dump.Y) Dump.println("GetInputFilenameDataRaw filename="+filename);//~1329I~//~1506R~//~v@@@R~
//        path=AG.context.getFilesDir()+"/"+filename;                //~1401I~//~v@@@R~
//        if (rawFileId==0)   //not cfg file                                 //~1329I~//~1401R~//~v@@@R~
//            return path;                                       //~1329I~//~1401R~//~v@@@R~
//        if ((new File(path)).exists())  //file on /data/data   //~1329I~//~1423R~//~v@@@R~
//        {                                                          //~1423R~//~v@@@R~
//            if (Dump.Y) Dump.println("GetFileInputFilenameDataRaw exist path="+path);//~1329I~//~1506R~//~v@@@R~
//            return path;                                       //~1329I~//~1423R~//~v@@@R~
//        }                                                          //~1423R~//~v@@@R~
//        copyToDataDir(rawFileId,filename);                         //~1423R~//~v@@@R~
//        return path;                                           //~1329I~//~1401R~//~v@@@R~
//    }                                                              //~1329I~//~v@@@R~
////********************************************************         //~1419R~//~v@@@R~
////*to intercept FileInputStream of Global                          //~1419R~//~v@@@R~
////* return inputStream for cfg/help file                           //~1419R~//~v@@@R~
////*   save.xxx.cfg on /data/data                                   //~1419R~//~v@@@R~
////*   if not save copy from /res/raw to /data/data                 //~1419R~//~v@@@R~
////    for other than cfg file return    /data/data/fnm             //~1419R~//~v@@@R~
////* return helpfile InputSream on SDcardm,if not found of asset/   //~1419R~//~v@@@R~
////********************************************************         //~1419R~//~v@@@R~
//    public static InputStream getInputStreamDataRaw(String Pfilename)//~1419R~//~v@@@R~
//        throws FileNotFoundException                               //~1419R~//~v@@@R~
//    {                                                              //~1419R~//~v@@@R~
//        String path,filename;                                      //~1419R~//~v@@@R~
//        InputStream is;                                            //~1419R~//~v@@@R~
//    //***************************                                  //~1419R~//~v@@@R~
//        if (Dump.Y) Dump.println("getInputStreamDataRaw:"+Pfilename);//~1506R~//~v@@@R~
//        if ((is=getInputStreamHelpFile(Pfilename))!=null)          //~1419R~//~v@@@R~
//            return is;                                             //~1419R~//~v@@@R~
//        filename=getConfigFilename(Pfilename);                     //~1419R~//~v@@@R~
//        if (Dump.Y) Dump.println("getFileInputStreamDataRaw filename="+filename);//~1506R~//~v@@@R~
//        path=AG.context.getFilesDir()+"/"+filename;                //~1419R~//~v@@@R~
//        if (rawFileId==0)   //not cfg file                         //~1419R~//~v@@@R~
//            return new FileInputStream(path);                      //~1419R~//~v@@@R~
//        if (!chkConfigOnSD(filename))   //chk new file on SDcard   //~1423R~//~v@@@R~
//        {                                                          //~1423M~//~v@@@R~
//            if ((new File(path)).exists())  //file on /data/data   //~1423R~//~v@@@R~
//            {                                                      //~1423R~//~v@@@R~
//                if (Dump.Y) Dump.println("getInputStreamDataRaw exist path="+path);//~1506R~//~v@@@R~
//                return new FileInputStream(path);                  //~1423R~//~v@@@R~
//            }                                                      //~1423R~//~v@@@R~
//            copyToDataDir(rawFileId,filename);                     //~1423R~//~v@@@R~
//        }                                                          //~1423M~//~v@@@R~
//        return new FileInputStream(path);                          //~1419R~//~v@@@R~
//    }                                                              //~1419R~//~v@@@R~
////********************************************************         //~1401I~//~v@@@R~
////*get real filename                                               //~1401I~//~v@@@R~
////********************************************************         //~1401I~//~v@@@R~
//    public static String getConfigFilename(String Pfilename)       //~1401I~//~v@@@R~
//    {                                                              //~1401I~//~v@@@R~
//        String filename,home;            //~1401I~               //~v@@@R~
//        int id=0;                                                  //~1401I~//~v@@@R~
//    //***************************                                  //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("getConfigFilename:"+Pfilename);  //~1506R~//~v@@@R~
//        home=Global.home();                                        //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".go.cfg"))                      //~1401I~//~v@@@R~
//            id=R.raw.go;                                           //~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".filter.cfg"))                  //~1401I~//~v@@@R~
//            id=R.raw.filter;                                       //~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".server.cfg"))                  //~1401I~//~v@@@R~
//            id=R.raw.server;                                       //~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".partner.cfg"))                 //~1401I~//~v@@@R~
//            id=R.raw.partner;                                      //~1401I~//~v@@@R~
//        if (id!=0)                                                 //~1401I~//~v@@@R~
//            filename=DATAFILE_PREFIX+Pfilename.substring(home.length());//~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//            filename=Pfilename;                                    //~1401I~//~v@@@R~
//        rawFileId=id;                                              //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("getConfigFilename  filename="+filename+",id="+Integer.toHexString(id));//~1506R~//~v@@@R~
//        return filename;                                           //~1401R~//~v@@@R~
//    }                                                              //~1401I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
////*chk new cfg file on SDcard                                      //~1423I~//~v@@@R~
////* if exit cpy to data/data then rename                           //~1423I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
//    public static boolean chkConfigOnSD(String Pfilename)          //~1423R~//~v@@@R~
//    {                                                              //~1423I~//~v@@@R~
//    //***************************                                  //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("chkConfigOnSD:"+Pfilename);      //~1506R~//~v@@@R~
//        String filenameSD=Pfilename.substring(DATAFILE_PREFIX.length()+1); //last name//~1423R~//~v@@@R~
//        String cfgSDfile=getSDPath(filenameSD);   //path           //~1423R~//~v@@@R~
//        if (cfgSDfile==null)                                       //~v102I~//~v@@@R~
//            return false;                                          //~v102I~//~v@@@R~
//        if (!(new File(cfgSDfile)).exists())    //file on /data/data//~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("chkConfigOnSD exist path="+cfgSDfile);//~1506R~//~v@@@R~
//        if (!copyToDataDir(filenameSD,Pfilename))                  //~1423R~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        if (renameFile(cfgSDfile))                             //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        return true;                                               //~1423I~//~v@@@R~
//    }                                                              //~1423I~//~v@@@R~
//********************************************************         //~1412I~
//*return SDCARD/jagoclient/hexlptext/subjext.txt if exist         //~1418R~
//********************************************************         //~1412I~
////********************************************************         //~1419I~//~v@@@R~
////*return InputStream for helpfile; SDCARD or asset folder         //~1419R~//~v@@@R~
////********************************************************         //~1419I~//~v@@@R~
//    public static InputStream getInputStreamHelpFile(String Pfilename)//~v@@@R~
//        throws FileNotFoundException                             //~v@@@R~
//    {                                                              //~1419I~//~v@@@R~
//        InputStream is;                                            //~1419I~//~v@@@R~
//        String filename,home;                                           //~1419I~//~v@@@R~
//    //***************************                                  //~1419I~//~v@@@R~
//        if (Dump.Y) Dump.println("getHelpFilename:"+Pfilename);    //~1506R~//~v@@@R~
//        home=AG.home();//~1419I~                                 //~v@@@R~
//        if (!Pfilename.startsWith(home+"helptexts"))               //~1Ad2I~//~v@@@R~
//            return null;                                           //~1419I~//~v@@@R~
//        filename=Pfilename.substring(home.length());                //~1419I~//~v@@@R~
//        is=openInputSD(filename);   //check SD card                //~1419I~//~v@@@R~
//        if (is==null)                                              //~1419I~//~v@@@R~
//            try                                                  //~v@@@R~
//            {                                                    //~v@@@R~
//                is=AG.resource.getAssets().open(filename);             //~1419I~//~v@@@R~
//            }                                                    //~v@@@R~
//            catch(Exception e)                                   //~v@@@R~
//            {                                                    //~v@@@R~
//                throw new FileNotFoundException();               //~v@@@R~
//            }                                                    //~v@@@R~
//        return is;                                                 //~1419R~//~v@@@R~
//    }                                                              //~1419I~//~v@@@R~
//********************************************************         //~v@@@I~
//*get help file text                                              //~v@@@I~
//********************************************************         //~v@@@I~
	public static String getHelpFileText(String Pfilename)         //~v@@@I~
	{                                                              //~v@@@I~
        String txt;                                                //~v@@@I~
    	InputStream is;                                            //~v@@@I~
    //***************************                                  //~v@@@I~
        if (Dump.Y) Dump.println("getHelpFileText:"+Pfilename);    //~v@@@I~
    	String fnm="helptexts/"+Pfilename+AG.helpFileSuffix+".txt";//~v@@@R~
//  	String txt=loadAssetTextFile(fnm,null/*encoding=utf8*/);   //~v@@@R~
    	if (AG.helpFileSuffix.equals(""))                          //~v@@@I~
			txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,true); //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,false);//~v@@@I~
            if (txt==null)                                         //~v@@@I~
            {                                                      //~v@@@I~
		    	fnm="helptexts/"+Pfilename+".txt";                 //~v@@@I~
				txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,true);//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		return txt;                                                //~v@@@I~
	}                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*get help file text extension specified                          //~v@@@I~
//********************************************************         //~v@@@I~
	public static String getHelpFileExt(String Pfilename,String Pextension,boolean PswNFMsg)//~v@@@I~
	{                                                              //~v@@@I~
    	InputStream is;                                            //~v@@@I~
        String txt;                                                //~v@@@I~
    //***************************                                  //~v@@@I~
        if (Dump.Y) Dump.println("getHelpFileText:"+Pfilename);    //~v@@@I~
    	String fnm="helptexts/"+Pfilename+AG.helpFileSuffix+Pextension;//~v@@@I~
//  	String txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,PswNFMsg);//~v@@@I~
    	if (AG.helpFileSuffix.equals(""))                          //~v@@@I~
			txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,PswNFMsg);//~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
	    	txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,false);//~v@@@I~
            if (txt==null)                                         //~v@@@I~
            {                                                      //~v@@@I~
		    	fnm="helptexts/"+Pfilename+Pextension;             //~v@@@I~
	    		txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,PswNFMsg);//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		return txt;                                                //~v@@@I~
	}                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*get asset file text                                             //~v@@@I~
//********************************************************         //~v@@@I~
	public static String loadAssetTextFile(String Pfnm,String Pencoding)//~v@@@I~
	{                                                              //~v@@@I~
		return loadAssetTextFile(Pfnm,Pencoding,true);             //~v@@@I~
    }                                                              //~v@@@I~
	public static String loadAssetTextFile(String Pfnm,String Pencoding,boolean PswNFMsg)//~v@@@I~
	{                                                              //~v@@@I~
    	String txt=null;                                           //~v@@@R~
    //***************************                                  //~v@@@I~
    	String encoding=Pencoding;                                  //~v@@@I~
        if (encoding==null)                                        //~v@@@I~
        	encoding="UTF-8";                                      //~v@@@I~
        if (Dump.Y) Dump.println("loadAssetTextFile:"+Pfnm);       //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	final AssetManager mgr=AG.context.getAssets();          //~v@@@I~
            InputStream is=mgr.open(Pfnm);                         //~v@@@I~
            txt=loadText(is,encoding);                             //~v@@@I~
        }                                                          //~v@@@I~
		catch(FileNotFoundException e)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (PswNFMsg)                                          //~v@@@I~
	        	UView.showToastLong(R.string.ErrAssetFileNotFound,Pfnm);//~v@@@R~
        }                                                          //~v@@@I~
		catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	UView.showToastLong(R.string.ErrAssetFileRead,Pfnm);   //~v@@@R~
        }                                                          //~v@@@I~
		return txt;                                                //~v@@@I~
	}                                                              //~v@@@I~
    private static String loadText(InputStream Pis,String Pencoding) throws IOException//~v@@@I~
    {                                                              //~v@@@I~
    	return new String(readStream(Pis,32768),Pencoding);       //~v@@@I~
    }                                                              //~v@@@I~
    private static byte[] readStream(InputStream Pis,int Pbuffsz) throws IOException//~v@@@I~
    {                                                              //~v@@@I~
    	ByteArrayOutputStream bs= new ByteArrayOutputStream(Pbuffsz);//~v@@@I~
        byte[] bytes=new byte[Pbuffsz];                            //~v@@@I~
        BufferedInputStream bis=new BufferedInputStream(Pis,Pbuffsz);//~v@@@I~
        int len=0;                                                 //~v@@@I~
        while ((len=bis.read(bytes,0,Pbuffsz))>0)                  //~v@@@I~
        {                                                          //~v@@@I~
            bs.write(bytes,0,len);                                 //~v@@@I~
        }                                                          //~v@@@I~
        byte[] ba=bs.toByteArray();                                //~v@@@I~
        bs.reset();                                                //~v@@@I~
        bis.close();                                               //~v@@@I~
        return ba;                                                 //~v@@@R~
    }                                                              //~v@@@I~
////********************************************************         //~1511I~//~v@@@R~
////*open Assetfile for Agnugo copy                                  //~1511I~//~v@@@R~
////********************************************************         //~1511I~//~v@@@R~
//    public static AssetFileDescriptor openAssetFileFD(String Pfilename)//~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        AssetFileDescriptor fd;                                    //~1511I~//~v@@@R~
//    //***************************                                  //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("openAssetFileFD:"+Pfilename);    //~1511I~//~v@@@R~
//        try                                                        //~1511I~//~v@@@R~
//        {                                                          //~1511I~//~v@@@R~
//            fd=AG.resource.getAssets().openFd(Pfilename);          //~1511I~//~v@@@R~
//        }                                                          //~1511I~//~v@@@R~
//        catch(Exception e)                                         //~1511I~//~v@@@R~
//        {                                                          //~1511I~//~v@@@R~
//            if (Dump.Y) Dump.println("openAssetFileFD failed:"+Pfilename);//~1511I~//~v@@@R~
//            fd=null;                                               //~1511I~//~v@@@R~
//        }                                                          //~1511I~//~v@@@R~
//        return fd;                                                 //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
//********************************************************         //~1511I~//~v@@@R~
//*open Assetfile for Agnugo copy                                  //~1511I~//~v@@@R~
//********************************************************         //~1511I~//~v@@@R~
    public static InputStream openAssetFile(String Pfilename,boolean Pshowexception)      //~1511I~//~v@@@R~
    {                                                              //~1511I~//~v@@@R~
        InputStream is;                                            //~1511I~//~v@@@R~
    //***************************                                  //~1511I~//~v@@@R~
        if (Dump.Y) Dump.println("UFile.openAssetFile:"+Pfilename);      //~1511I~//~v@@@R~
        try                                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            is=AG.resource.getAssets().open(Pfilename);            //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        catch(Exception e)                                         //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            if (Dump.Y) Dump.println("openAssetFile failed:"+Pfilename);//~1511I~//~v@@@I~
        	if (Pshowexception)                                    //~v@@@I~
	            Dump.println(e,"openAssetFile:"+Pfilename);        //~v@@@I~
            is=null;                                               //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        return is;                                                 //~1511I~//~v@@@R~
    }                                                              //~1511I~//~v@@@R~
////********************************************************         //~1511I~//~v@@@R~
////*open Assetfile for Agnugo copy                                  //~1511I~//~v@@@R~
////********************************************************         //~1511I~//~v@@@R~
//    public static long getAssetFileSize(String Pfilename)          //~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        AssetFileDescriptor fd;                                    //~1511I~//~v@@@R~
//        long sz;                                                   //~1511I~//~v@@@R~
//    //***************************                                  //~1511I~//~v@@@R~
//        fd=openAssetFileFD(Pfilename);                             //~1511I~//~v@@@R~
//        if (fd==null)                                              //~1511I~//~v@@@R~
//            sz=-1;                                                 //~1511I~//~v@@@R~
//        else                                                       //~1511I~//~v@@@R~
//            sz=fd.getLength();                                      //~1511I~//~v@@@R~
//        try                                                      //~v@@@R~
//         {                                                       //~v@@@R~
//            fd.close();                                          //~v@@@R~
//         }                                                       //~v@@@R~
//        catch (IOException e)                                    //~v@@@R~
//         {                                                       //~v@@@R~
//            Dump.println(e,"xception Asset openFD"+Pfilename);   //~v@@@R~
//            e.printStackTrace();                                 //~v@@@R~
//        }                                                //~1511I~//~v@@@R~
//        return sz;                                                 //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
////********************************************************         //~1327I~//~v@@@R~
////*copy /res/raw file to /data/data private dir                    //~1329I~//~v@@@R~
////*retur success/false                                             //~1329I~//~v@@@R~
////********************************************************         //~1329I~//~v@@@R~
//    public static boolean copyToDataDir(int Prawresid,String Pfname)//~1327I~//~v@@@R~
//    {                                                              //~1327I~//~v@@@R~
//        InputStream is;                                       //~1327I~//~v@@@R~
//        FileOutputStream fos;                                      //~1327I~//~v@@@R~
//        int len;                                                   //~1327I~//~v@@@R~
//        boolean success=true;                                      //~1327I~//~v@@@R~
//        byte [] buff;                                              //~1327I~//~v@@@R~
//    //*********************                                        //~1327I~//~v@@@R~
//        if (Dump.Y) Dump.println("copyToDataDir:"+Pfname);         //~1506R~//~v@@@R~
//        is=AG.resource.openRawResource(Prawresid);  //read from res/raw//~1327I~//~v@@@R~
//        if (is==null)  //1st time    no save.xxx on data/data     //~1327I~//~v@@@R~
//            return false;                                          //~1327I~//~v@@@R~
//        fos=openOutputData(Pfname); ///data/data (no search SD)    //~1329R~//~v@@@R~
//        if (fos==null)                                             //~1327I~//~v@@@R~
//            return false;                                          //~1327I~//~v@@@R~
//        buff=new byte[BUFFSZ];                                     //~1327I~//~v@@@R~
//        try                                                        //~1327I~//~v@@@R~
//        {                                                          //~1327I~//~v@@@R~
//            for (;;)                                               //~1327I~//~v@@@R~
//            {                                                      //~1327I~//~v@@@R~
//                len=is.read(buff);                                 //~1327I~//~v@@@R~
//                if (len<0)                                         //~1327I~//~v@@@R~
//                    break;                                         //~1327I~//~v@@@R~
//                fos.write(buff,0,len);                             //~1327I~//~v@@@R~
//            }                                                      //~1327I~//~v@@@R~
//        }                                                          //~1327I~//~v@@@R~
//        catch (Exception e)                                        //~1327I~//~v@@@R~
//        {                                                          //~1327I~//~v@@@R~
//            success=false;                                         //~1327I~//~v@@@R~
//            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1327I~//~v@@@R~
//        }//catch                                                   //~1327I~//~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            is.close();                                               //~1327I~//~v@@@R~
//            fos.close();                                         //~v@@@R~
//            if (Dump.Y) Dump.println("close copy to SDfailed"+Pfname);//~1506R~//~v@@@R~
//        }                                                        //~v@@@R~
//        catch(IOException e)                                     //~v@@@R~
//        {                                                        //~v@@@R~
//            success=false;                                       //~v@@@R~
//            Dump.println(e,"close failed"+Pfname);//~1327I~      //~v@@@R~
//        }                                                        //~v@@@R~
//        return success;                                            //~1327I~//~v@@@R~
//    }                                                              //~1327I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
////*copy /sdcard/Ajagoc/cfg file to /data/data private dir          //~1423I~//~v@@@R~
////*retur success/false                                             //~1423I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
//    public static boolean copyToDataDir(String Pfname,String PfnameData)//~1423I~//~v@@@R~
//    {                                                              //~1423I~//~v@@@R~
//        InputStream is;                                            //~1423I~//~v@@@R~
//        FileOutputStream fos;                                      //~1423I~//~v@@@R~
//        int len;                                                   //~1423I~//~v@@@R~
//        boolean success=true;                                      //~1423I~//~v@@@R~
//        byte [] buff;                                              //~1423I~//~v@@@R~
//    //*********************                                        //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("copyToDataDir:SD="+Pfname+",data="+PfnameData);//~1506R~//~v@@@R~
//        is=openInputSD(Pfname);                  //~1423I~       //~v@@@R~
//        if (is==null)                                             //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        fos=openOutputData(PfnameData); ///data/data (no search SD)//~1423R~//~v@@@R~
//        if (fos==null)                                             //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        buff=new byte[BUFFSZ];                                     //~1423I~//~v@@@R~
//        try                                                        //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            for (;;)                                               //~1423I~//~v@@@R~
//            {                                                      //~1423I~//~v@@@R~
//                len=is.read(buff);                                //~1423I~//~v@@@R~
//                if (len<0)                                         //~1423I~//~v@@@R~
//                    break;                                         //~1423I~//~v@@@R~
//                fos.write(buff,0,len);                             //~1423I~//~v@@@R~
//            }                                                      //~1423I~//~v@@@R~
//        }                                                          //~1423I~//~v@@@R~
//        catch (Exception e)                                        //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            success=false;                                         //~1423I~//~v@@@R~
//            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1423I~//~v@@@R~
//        }//catch                                                   //~1423I~//~v@@@R~
//        try                                                        //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            is.close();                                           //~1423I~//~v@@@R~
//            fos.close();                                           //~1423I~//~v@@@R~
//            if (Dump.Y) Dump.println("close copy to Data failed"+Pfname);//~1506R~//~v@@@R~
//        }                                                          //~1423I~//~v@@@R~
//        catch(IOException e)                                       //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            success=false;                                         //~1423I~//~v@@@R~
//            Dump.println(e,"close failed"+Pfname);                 //~1423I~//~v@@@R~
//        }                                                          //~1423I~//~v@@@R~
//        return success;                                            //~1423I~//~v@@@R~
//    }                                                              //~1423I~//~v@@@R~
//********************************************************         //~v@@@I~
    public static FileInputStream newFileInputStream(String Pfnm)  //~v@@@I~
    {                                                              //~v@@@I~
		FileInputStream fis=null;                                  //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
			fis=new FileInputStream(Pfnm);                         //~v@@@I~
        }                                                          //~v@@@I~
	    catch (Exception e) //throw FileNotFoundException          //~v@@@I~
    	{                                                          //~v@@@I~
        	Dump.println(false/*toast*/,e,"new FileInputStream name="+Pfnm);//~v@@@I~
        }                                                          //~v@@@I~
		return fis;                                                //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~v@@@I~
    public static FileOutputStream newFileOutputStream(String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
		FileOutputStream fos=null;                                 //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
			fos=new FileOutputStream(Pfnm);                        //~v@@@I~
        }                                                          //~v@@@I~
	    catch (Exception e)  //throw FileNotFoundException         //~v@@@I~
    	{                                                          //~v@@@I~
        	Dump.println(false/*toast*/,e,"new FileOutputStream name="+Pfnm);//~v@@@I~
        }                                                          //~v@@@I~
		return fos;                                                //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*copy file /sdcard <-->/data/data/<pkg>                          //~v@@@I~
//*retur success/false                                             //~v@@@I~
//********************************************************         //~v@@@I~
    public static boolean copyFile(String Psrc,String Ptgt)        //~v@@@I~
    {                                                              //~v@@@I~
        int len;                                                   //~v@@@I~
        boolean success=true;                                      //~v@@@I~
        byte [] buff;                                              //~v@@@I~
    //*********************                                        //~v@@@I~
        if (Dump.Y) Dump.println("copyFile:src="+Psrc+",tgt="+Ptgt);//~v@@@I~
                                                                   //~v@@@I~
		FileInputStream fis=newFileInputStream(Psrc);              //~v@@@I~
        if (fis==null)                                             //~v@@@I~
            return false;                                          //~v@@@I~
        FileOutputStream fos=newFileOutputStream(Ptgt);            //~v@@@I~
        if (fos==null)                                             //~v@@@I~
            return false;                                          //~v@@@I~
        buff=new byte[BUFFSZ];                                     //~v@@@I~
        for (;;)                                                   //~v@@@I~
        {                                                          //~v@@@I~
        	try                                                    //~v@@@I~
        	{                                                      //~v@@@I~
                len=fis.read(buff);                                //~v@@@I~
            }                                                      //~v@@@I~
	        catch (Exception e) //IOException                      //~v@@@I~
    	    {                                                      //~v@@@I~
            	Dump.println(false,e,"copyFile read name="+Psrc);  //~v@@@I~
            	success=false;                                     //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
            if (len<0)                                             //~v@@@I~
            	break;                                             //~v@@@I~
        	try                                                    //~v@@@I~
        	{                                                      //~v@@@I~
                fos.write(buff,0,len);                             //~v@@@I~
            }                                                      //~v@@@I~
	        catch (Exception e)                                    //~v@@@I~
    	    {                                                      //~v@@@I~
            	Dump.println(false,e,"copyFile write name="+Ptgt); //~v@@@I~
            	success=false;                                     //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            fis.close();                                           //~v@@@I~
	        if (Dump.Y) Dump.println("closed src="+Psrc);          //~v@@@I~
        }                                                          //~v@@@I~
        catch(IOException e)                                       //~v@@@I~
        {                                                          //~v@@@I~
            success=false;                                         //~v@@@I~
            Dump.println(false,e,"close failed:"+Psrc);            //~v@@@I~
        }                                                          //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            fos.close();                                           //~v@@@I~
	        if (Dump.Y) Dump.println("closed tgt="+Ptgt);          //~v@@@I~
        }                                                          //~v@@@I~
        catch(IOException e)                                       //~v@@@I~
        {                                                          //~v@@@I~
            success=false;                                         //~v@@@I~
            Dump.println(false,e,"close failed:"+Ptgt);            //~v@@@I~
        }                                                          //~v@@@I~
        if (success)                                               //~v@@@I~
        	copyFileTimestamp(Psrc,Ptgt);                          //~v@@@I~
        return success;                                            //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*copy lastModified                                               //~v@@@I~
//********************************************************         //~v@@@I~
    public static boolean copyFileTimestamp(String Psrc,String Ptgt)//~v@@@I~
    {                                                              //~v@@@I~
    	boolean success;                                           //~v@@@I~
        if (Dump.Y) Dump.println("copyFileTimestamp:src="+Psrc+",tgt="+Ptgt);//~v@@@I~
		File src=new File(Psrc);                                   //~v@@@I~
		File tgt=new File(Ptgt);                                   //~v@@@I~
        if (Dump.Y) Dump.println("timestamp before src="+src.lastModified()+",tgt="+tgt.lastModified());//~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        success=tgt.setLastModified(src.lastModified());         //~v@@@I~
        }                                                          //~v@@@I~
	    catch (Exception e) //IOException                          //~v@@@I~
    	{                                                          //~v@@@I~
            Dump.println(false,e,"copyFileTimestamp src="+Psrc+",tgt="+Ptgt);//~v@@@I~
            success=false;                                         //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("copyFileTimestamp rc="+success+",src="+Psrc+",tgt="+Ptgt);//~v@@@I~
        if (Dump.Y) Dump.println("timestamp after src="+src.lastModified()+",tgt="+tgt.lastModified());//~v@@@I~
        return success;                                            //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~1511I~//~v@@@R~
//*copy asset file to /data/data/files                             //~v@@@R~
//*retur success/false                                             //~1511I~//~v@@@R~
//********************************************************         //~1511I~//~v@@@R~
    public static boolean copyAssetToDataDir(String Pfname,String PfnameData)//~1511I~//~v@@@R~
    {                                                              //~1511I~//~v@@@R~
        InputStream is;                                            //~1511I~//~v@@@R~
        FileOutputStream fos;                                      //~1511I~//~v@@@R~
        int len;                                                   //~1511I~//~v@@@R~
        boolean success=true;                                      //~1511I~//~v@@@R~
        byte [] buff;                                              //~1511I~//~v@@@R~
    //*********************                                        //~1511I~//~v@@@R~
        if (Dump.Y) Dump.println("copyToDataDir:SD="+Pfname+",data="+PfnameData);//~1511I~//~v@@@R~
        is=openAssetFile(Pfname,true);                                  //~1511I~//~v@@@R~
        if (is==null)                                              //~1511I~//~v@@@R~
            return false;                                          //~1511I~//~v@@@R~
        fos=openOutputData(PfnameData); // to /data/data (no search SD)//~1511I~//~v@@@R~
        if (fos==null)                                             //~1511I~//~v@@@R~
            return false;                                          //~1511I~//~v@@@R~
        buff=new byte[BUFFSZ2];                                    //~1511I~//~v@@@R~
        try                                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            for (;;)                                               //~1511I~//~v@@@R~
            {                                                      //~1511I~//~v@@@R~
                len=is.read(buff);                                 //~1511I~//~v@@@R~
                if (len<0)                                         //~1511I~//~v@@@R~
                    break;                                         //~1511I~//~v@@@R~
                fos.write(buff,0,len);                             //~1511I~//~v@@@R~
            }                                                      //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        catch (Exception e)                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            success=false;                                         //~1511I~//~v@@@R~
            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1511I~//~v@@@R~
        }//catch                                                   //~1511I~//~v@@@R~
        try                                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            is.close();                                            //~1511I~//~v@@@R~
            fos.close();                                           //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        catch(IOException e)                                       //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            success=false;                                         //~1511I~//~v@@@R~
            Dump.println(e,"close failed"+Pfname);                 //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        return success;                                            //~1511I~//~v@@@R~
    }                                                              //~1511I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
////*rename cfg on sdcard after copyed to data/data                  //~1423I~//~v@@@R~
////*retur success/false                                             //~1423I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
//    public static boolean renameFile(String Pfname)                //~1423R~//~v@@@R~
//    {                                                              //~1423I~//~v@@@R~
//        boolean success=true;                                      //~1423I~                                            //~1423I~//~v@@@R~
//    //*********************                                        //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("renameFile="+Pfname);            //~1506R~//~v@@@R~
//        File oldfile=new File(Pfname);                             //~1423R~//~v@@@R~
//        if (!oldfile.exists())  //file on /data/data               //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        String ts=Utils.getTimeStamp(Utils.TS_DATE_TIME);            //~1425R~//~@@@@R~//~v@@@R~
//        String newname=Pfname+"."+SDFILE_PREFIX+"."+ts;            //~1423R~//~v@@@R~
//        File newfile=new File(newname);                            //~1423I~//~v@@@R~
//        success=oldfile.renameTo(newfile);                              //~1423I~//~v@@@R~
//        return success;                                            //~1423I~//~v@@@R~
//    }                                                              //~1423I~//~v@@@R~
////*******************************************                      //~1423R~//~v@@@R~
//    public static FileInputStream openInputData(String Pfname)        //~1309I~//~1312R~//~1327R~//~v@@@R~
//    {                                                              //~1309I~//~v@@@R~
//        FileInputStream in=null;                                       //~1309I~//~1327R~//~v@@@R~
//        if (Dump.Y) Dump.println("openInputStream:"+Pfname);       //~1506R~//~v@@@R~
//        try                                                        //~1309I~//~v@@@R~
//        {                                                          //~1309I~//~v@@@R~
//            in=AG.context.openFileInput(Pfname);                   //~1309I~//~v@@@R~
//            File f=new File(Pfname);                               //~1309I~//~v@@@R~
//            if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpath="+f.getAbsolutePath());//~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpathname="+f.getAbsoluteFile());//~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("Test isiexst by File()="+f.exists());             //~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("Test tiimestamp="+Long.toHexString(f.lastModified()));//~1309I~//~1506R~//~v@@@R~
//            f=Environment.getDataDirectory();                      //~1309I~//~v@@@R~
//            if (Dump.Y) Dump.println("DataDir Path="+f.getAbsolutePath());//~1309I~//~1506R~//~v@@@R~
//            f=new File(AG.context.getFilesDir(),Pfname);    //android.app.contextImple//~1309I~//~v@@@R~
//            if (Dump.Y) Dump.println(f.toString()+":isiexst="+f.exists());//~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("tiimestamp="+Long.toHexString(f.lastModified()));//~1309I~//~1506R~//~v@@@R~
//        }                                                          //~1309I~//~v@@@R~
//        catch(FileNotFoundException e)                             //~1309I~//~v@@@R~
//        {                                                          //~1309I~//~v@@@R~
////          Dump.println(e,"openInputData failed "+Pfname);  //~1309I~//~1312R~//~1329R~//~v107R~//~v@@@R~
//            Dump.println("openInputData NotFound:"+Pfname);        //~v107I~//~v@@@R~
//        }                                                          //~1309I~//~v@@@R~
//        catch (Exception e)                                        //~1309I~//~v@@@R~
//        {                                                          //~1309I~//~v@@@R~
//            Dump.println(e,"openInputData exception "+Pfname);              //~1309I~//~1312R~//~1329R~//~v@@@R~
//        }//catch                                                   //~1309I~//~v@@@R~
//        return in;                                                 //~1309I~//~v@@@R~
//    }                                                              //~1309I~//~v@@@R~
////*********************************************                                                       //~1309I~//~1329R~//~v@@@R~
////*output to SD if avale else private *********                    //~1329I~//~v@@@R~
////*********************************************                    //~1329I~//~v@@@R~
    public static FileOutputStream openOutputSD(String Pdir,String Pfname)//~1313I~//~v@@@R~
    {                                                              //~1313I~//~v@@@R~
        String fnm,path;                                           //~1313I~//~v@@@R~
        if (Dump.Y) Dump.println("openOutputSD dir="+Pdir+",file="+Pfname);//~1313I~//~1506R~//~v@@@R~
        path=getSDPath(Pdir);                                      //~1313R~//~v@@@R~
        if (path==null) //no SDCard available                          //~1313I~//~v@@@R~
            return openOutputData(Pfname);    //to /data/data/<pkg>/files                     //~1313I~//~v@@@R~
        fnm=path+AG.dirSep+Pfname;      //~1313R~                  //~v@@@R~
        if (Dump.Y) Dump.println("openoutputDataSD on SDcard fnm="+fnm);                       //~1313I~//~1506R~//~v@@@R~
        FileOutputStream out=null;                                 //~v@@@R~
        try {                                                      //~v@@@R~
            out = new FileOutputStream(fnm);                       //~v@@@R~
        } catch (Exception e)                                      //~1329R~//~v@@@R~
        {                                                          //~v@@@R~
            Dump.println(e,"openOutputSD:"+fnm);       //~1329R~   //~v@@@R~
        }                //~1313I~                                 //~v@@@R~
        return out;                                                //~1313I~//~v@@@R~
    }                                                              //~1313I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
////*get private data file path                                      //~1511I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
//    public static String getDataFileFullpath(String Pfname)        //~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileFullpath:"+Pfname);   //~1511I~//~v@@@R~
//        String path=AG.context.getFilesDir()+"/"+Pfname;                      //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileFullpath:"+path);     //~1511I~//~v@@@R~
//        return path;                                               //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
////*get private data file size                                      //~1511I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
//    public static long getDataFileSize(String Pfname)              //~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileSize:"+Pfname);       //~1511I~//~v@@@R~
//        String path=AG.context.getFilesDir()+"/"+Pfname;                      //~1511I~//~v@@@R~
//        File f=new File(path);                                     //~1511I~//~v@@@R~
//        long sz;                                                   //~1511I~//~v@@@R~
//        if (f.exists())                                            //~1511I~//~v@@@R~
//            sz=f.length();                                         //~1511I~//~v@@@R~
//        else                                                       //~1511I~//~v@@@R~
//            sz=-1;                                                 //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileSize:"+path+",sz="+sz);//~1511I~//~v@@@R~
//        return sz;                                                 //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
//************************************************************************                                                       //~1401I~//~v@@@R~
    public static InputStream openInputSD(String Pfname,boolean Pshowexception)           //~1327I~//~v@@@R~
    {                                                              //~1327I~//~v@@@R~
        String fnm;                                           //~1327I~//~v@@@R~
        if (Dump.Y) Dump.println("open Input SD="+Pfname);         //~1506R~//~v@@@R~
        fnm=getSDPath(Pfname);                                      //~1327I~//~v@@@R~
        if (fnm==null)  //no SDCard available                      //~1327I~//~v@@@R~
            return null;                                           //~1327I~//~v@@@R~
                                        //~1327I~                  //~v@@@R~
        if (Dump.Y) Dump.println("openInputSD fnm="+fnm);                       //~1327I~//~1506R~//~v@@@R~
        FileInputStream is=null;                                   //~1327I~//~v@@@R~
        try                                                        //~1327I~//~v@@@R~
        {                                                          //~1327I~//~v@@@R~
            is=new FileInputStream(fnm);                           //~1327I~//~v@@@R~
        }                                                          //~1327I~//~v@@@R~
        catch (Exception e)                            //~1327I~   //~v@@@R~
        {                                                          //~1327I~//~v@@@R~
            if (Dump.Y) Dump.println("openInputSD:"+fnm);//~v107I~ //~v@@@I~
        	if (Pshowexception)                                    //~v@@@I~
	            Dump.println(e,"openInputSD:"+fnm);                //~v@@@R~
        }                                                          //~1327I~//~v@@@R~
        return (InputStream)is;                                    //~1327I~//~v@@@R~
    }                                                              //~1327I~//~v@@@R~
//************************************************************************//~v@@@I~
//* file in data/data/<pkg>/files                                  //~v@@@I~
//************************************************************************//~v@@@I~
//    public static InputStream openInputOnFiles(String Pfname,boolean Pshowexception)//~v@@@R~
//    {                                                            //~v@@@R~
//        String fnm=getFilesPath(Pfname);                         //~v@@@R~
//        if (Dump.Y) Dump.println("openInputOnFiles fnm="+fnm);   //~v@@@R~
//        FileInputStream is=null;                                 //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            is=new FileInputStream(fnm);                         //~v@@@R~
//        }                                                        //~v@@@R~
//        catch (Exception e)                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("openInputOnFiles:"+fnm);   //~v@@@R~
//            if (Pshowexception)                                  //~v@@@R~
//                Dump.println(e,"openInputOnFiles:"+fnm);         //~v@@@R~
//        }                                                        //~v@@@R~
//        return (InputStream)is;                                  //~v@@@R~
//    }                                                            //~v@@@R~
//*********************************************                    //~1329I~
//*output to private data/data        *********                    //~1329I~//~1401R~
//*********************************************                    //~1329I~
//***********************************                              //~1401I~
//*from FileOutputStream                                           //~v@@@R~
//***********************************                              //~1401I~
//    public static FileOutputStream openOutputDataCfg(String Pfname)//~1401I~//~v@@@R~
//    {                                                              //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("open Output private file from FileOutputStream="+Pfname);//~1506R~//~v@@@R~
//        String home=AG.home();                                 //~1401I~//~v@@@R~
//        String filename=DATAFILE_PREFIX+Pfname.substring(home.length());//~1401I~//~v@@@R~
//        return openOutputData(filename);                           //~1401I~//~v@@@R~
//    }                                                              //~1401I~//~v@@@R~
//*****************************************************************//~1401I~//~v@@@R~
//*output in /data/data/<pkg>/files                                //~v@@@I~
//*****************************************************************//~v@@@I~
    public static FileOutputStream openOutputData(String Pfname)      //~1309I~//~v@@@R~
    {                                                          //~1309I~//~v@@@R~
	    return openOutputData(Pfname,Context.MODE_PRIVATE);        //~v@@@R~
    }                                                              //~1309I~
//***********************************                              //~v@@@I~
    public static FileOutputStream openOutputData(String Pfname,int Pmode)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("UFile.openOutputData file="+Pfname+",mode="+Pmode);//~v@@@R~
	    FileOutputStream out=null;	//FileOutputStream extend OutputStream//~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            out=AG.context.openFileOutput(Pfname,Pmode);           //~v@@@I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFile.openOutputData:"+Pfname+",mode="+Pmode);//~v@@@R~
        }//catch                                                   //~v@@@I~
    	return out;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********                                                       //~1312I~
    public static boolean writeOutputFiles(String Pfname,byte[] Pbytedata)//~1511R~//~v@@@R~
    {                                                              //~1312I~
    	boolean rc=false;                                          //~1511I~
    	if (Dump.Y) Dump.println("writeOutputFiles file="+Pfname);//~1506R~//~v@@@R~
		FileOutputStream os=openOutputData(Pfname);                    //~1312I~//~v@@@R~
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
            Dump.println(e,"writeOutputFiles:"+Pfname);      //~1312I~//~v@@@R~
        }//catch                                                   //~1312I~
        return rc;                                                 //~1511I~
    }                                                              //~1312I~
//*******************************                                  //~1313I~
//*SD card                      *                                  //~1313I~
//*Manifest setting                                                //~1313I~
//* <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>//~1313I~
//*******************************                                  //~1313I~
    public static boolean isSDMounted()                            //~1313I~
    {                                                              //~1313I~
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//~1313I~
    }                                                              //~1313I~
//*******************************                                  //~v@@@I~
	@SuppressWarnings("deprecation")                               //~va42I~
	@TargetApi(19)     //KitKat                                    //~v@@@I~
//  public static String getPublicPath19()                         //~v@@@I~//~va42R~
    private static String getPublicPath19()                        //~va42I~
    {                                                              //~v@@@I~
        String path=Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS).getPath();//~v@@@I~
        if (Dump.Y) Dump.println("Ufile.getPublicPath19 ="+path);  //~v@@@I~
        return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
	@SuppressWarnings("deprecation")                               //~va42I~
//  public static String getPublicPath()                           //~v@@@I~//~va42R~
    private static String getPublicPath()                          //~va42I~
    {                                                              //~v@@@I~
        String path=Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM).getPath();//traditional picture and video//~v@@@I~
        if (Dump.Y) Dump.println("Ufile.getPublicPath ="+path);    //~v@@@I~
        return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
	@SuppressWarnings("deprecation")                               //+va42I~
    private static String getExternalPath()                        //+va42I~
    {                                                              //+va42I~
        String path=Environment.getExternalStorageDirectory().getPath();//+va42I~
        if (Dump.Y) Dump.println("Ufile.getExternbalPath="+path);  //+va42I~
        return path;                                               //+va42I~
    }                                                              //+va42I~
//**************************************************************** //~v@@@I~
    public static String[] getSDPaths()                            //~v@@@I~
    {                                                              //~v@@@I~
        String path;                                               //~v@@@I~
    	List<String> list=new ArrayList<>();                       //~v@@@I~
//        path=Environment.getExternalStorageDirectory().getPath();  //~v@@@I~//~va42R~
//        list.add(path);                                            //~v@@@I~//~va42R~
//        if (AG.osVersion>=19) // Kitkat android 4.4                //~v@@@I~//~va42R~
//            path=getPublicPath19();                                //~v@@@I~//~va42R~
//        else                                                       //~v@@@I~//~va42R~
//            path=getPublicPath();                                  //~v@@@I~//~va42R~
//        list.add(path);                                            //~v@@@I~//~va42R~
        list.add("/sdcard");                                       //~v@@@I~
        if (AG.osVersion<=29) //allow on 29:android10 by manifest: application-->requestLagacyExternalStorage="true" (ignored when target=androd11)//~va42I~
        {                                                          //~va42I~
        	path=getExternalPath();                                //~va42I~
        	list.add(path);                                        //~va42I~
	        if (Dump.Y) Dump.println("Ufile.getSDPaths getExternalStorageDirectory="+path);//~va42I~
        }                                                          //~va42I~
        if (AG.osVersion>=19) // Kitkat android 4.4                //~va42I~
            path=getPublicPath19();                                //~va42I~
        else                                                       //~va42I~
            path=getPublicPath();                                  //~va42I~
        if (Dump.Y) Dump.println("Ufile.getSDPaths getExternalStoragePublicDirectory="+path);//~va42I~
        list.add(path);                                            //~va42I~
                                                                   //~v@@@I~
        String[] ar=new String[list.size()];                       //~v@@@I~
        list.toArray(ar);                                          //~v@@@I~
        if (Dump.Y) Dump.println("Ufile.getSDPaths ="+ Arrays.toString(ar));//~v@@@I~
		return ar;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**************************************************************** //~v@@@I~
    public static String getSDPath(String Pfile)                   //~1313R~
    {                                                              //~1313I~
		String path;                                               //~1313I~
    //************                                                 //~1313I~
        if (!AG.swSDAvailable)                                     //~1313I~//~v@@@R~
        	return null;                                           //~1313I~
        path=AG.dirSD;                                           //~1313I~//~v@@@R~
        if (path==null)                                            //~1313I~
        {                                                          //~1313I~
            if (!isSDMounted())                                    //~1313I~
            {                                                      //~1313I~
                AG.swSDAvailable=false;                            //~1313I~//~v@@@R~
                return null;                                       //~1313I~
            }                                                      //~1313I~
//  		String approot=AG.appName;//~1323I~                    //~1402R~//~v@@@R~
    		String approot=AG.appNameE;                            //~v@@@I~
//      	path=Environment.getExternalStorageDirectory().getPath()+System.getProperty("file.separator")+approot;//~1323R~//~v@@@R~
            String[] paths=getSDPaths();                           //~v@@@I~
            boolean swOK=false;                                    //~v@@@R~
          for (int ii=0;ii<paths.length;ii++)                      //~v@@@I~
          {                                                        //~v@@@I~
//      	path=Environment.getExternalStorageDirectory().getPath()+System.getProperty("file.separator")+approot;//~v@@@I~
        	path=paths[ii]+System.getProperty("file.separator")+approot;//~v@@@I~
        	if (Dump.Y) Dump.println("Ufile.getSDPath path="+path);//~v@@@I~
            File f=new File(path);
        	if (!f.exists())	                                   //~1313I~
            {                                                      //~1313I~
	        	if (Dump.Y) Dump.println("Ufile.getSDPath not Exist="+path);//~v@@@I~
            	if (!f.mkdir())                                 //~1313I~
                {                                                  //~1313I~
//  	        	AG.swSDAvailable=false;                        //~1313I~//~v@@@R~
			        if (Dump.Y) Dump.println("getSDpath mkdir failed:"+path);//~1506R~
//      			return null;                                   //~1313I~//~v@@@R~
                    continue;                                      //~v@@@I~
                }                                                  //~1313I~
	            swOK=true;                                         //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~1313I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
	        	if (Dump.Y) Dump.println("Ufile.getSDPath Exist="+path);//~v@@@I~
	            swOK=true;                                         //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
          }                                                        //~v@@@I~
            if (!swOK)                                             //~v@@@I~
            {                                                      //~v@@@I~
				AG.swSDAvailable=false;                            //~v@@@I~
				return null;                                       //~v@@@I~
            }                                                      //~v@@@I~
        	AG.dirSD=path;                                       //~1313I~//~v@@@R~
            if (Dump.Y) Dump.println("SDcard dir="+path);                       //~1313I~//~1506R~
		}                                                          //~1313I~
        if (!Pfile.equals(""))                                     //~1313I~
        	if (Pfile.startsWith(AG.dirSep))                            //~1412I~//~v@@@R~
        		path+=Pfile;                                       //~1412I~
            else                                                   //~1412I~
        		path+=AG.dirSep+Pfile;                                   //~1412R~//~v@@@R~
        if (Dump.Y) Dump.println("GetSDpath:"+path);               //~1506R~
        return path;                                               //~1313R~
    }                                                              //~1313I~
//**********************************************************************//~1402I~
//*mkdir                                                           //~v@@@R~
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
//**********************************************************************//~v@@@I~
//*makePathName                                                    //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String makePathName(String Pfullpath,String Ptgtdir)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("makePathName fullpath="+Pfullpath+",tgtdir="+Ptgtdir);//~v@@@I~
        File f=new File(Pfullpath);                                //~v@@@I~
        String fnm=f.getName();                                    //~v@@@I~
        fnm=Ptgtdir+AG.dirSep+fnm;                                 //~v@@@I~
    	if (Dump.Y) Dump.println("makePathName fullpath="+Pfullpath+",tgtdir="+Ptgtdir+",out="+fnm);//~v@@@I~
        return fnm;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public static String makeFullpath(String Ppath,String Pfile,String Pext)//~v@@@I~
    {                                                              //~v@@@I~
    	String path=Ppath+AG.dirSep+Pfile+Pext;                   //~v@@@I~
	    if (Dump.Y) Dump.println("makeFullpath="+path+",parmDir="+Ppath+",file="+Pfile+",ext="+(Pext==null?"null":Pext));//~v@@@I~
    	return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*return path:/data/data/<pkg>/files                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String getDataDirPath(String Pfilename)          //~v@@@R~
    {                                                              //~v@@@I~
        String path=null;                                          //~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	path=AG.context.getFilesDir().getAbsolutePath();       //~v@@@I~
	        if (Pfilename!=null)                                   //~v@@@I~
    	    	path+=AG.dirSep+Pfilename;                         //~v@@@R~
	    	if (Dump.Y) Dump.println("getFilesPath:"+path);        //~v@@@I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println("getFilesPath"+Pfilename);                   //~v@@@I~
        }//catch                                                   //~v@@@I~
        return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*chk file in path:/data/data/<pkg>/files                         //~v@@@I~
//**********************************************************************//~v@@@I~
    public static boolean isExistInFiles(String Pfilename)         //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=false;                                           //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	FileInputStream fis=AG.context.openFileInput(Pfilename);//~v@@@I~
            fis.close();
            rc=true;//~v@@@I~
        }                                                          //~v@@@I~
        catch (FileNotFoundException e)                            //~v@@@I~
        {                                                          //~v@@@I~
        }//catch
        catch (Exception e)
        {
            Dump.println(e,"isExistInDataDir:"+Pfilename);// ~v@@@I~
        }
        if (Dump.Y) Dump.println("isExistInDataDir:"+Pfilename+"="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*open file in path:/data/data/<pkg>/files                        //~v@@@R~
//**********************************************************************//~v@@@I~
    public static FileInputStream openInputData(String Pfilename)  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("openInputData:"+Pfilename);      //~v@@@R~
        FileInputStream fis=null;                                  //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	fis=AG.context.openFileInput(Pfilename);               //~v@@@I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"openInputData:"+Pfilename);            //~v@@@R~
        }//catch                                                   //~v@@@I~
        return fis;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*get lastmodified                                                //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String getLastModified(File Pfile,String Pfmt)   //~v@@@R~
    {                                                              //~v@@@I~
	    long t=0;                                                  //~v@@@I~
        String ts="Unknown";                                       //~v@@@R~
        if (Dump.Y) Dump.println("getLastModified file="+Pfile.getAbsolutePath());//~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        t=Pfile.lastModified();                                //~v@@@R~
            ts= Utils.getTimeStamp(Pfmt,t);                         //~v@@@R~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFile.getLastModified:"+Pfile.getAbsolutePath());//~v@@@R~
        }//catch                                                   //~v@@@I~
        return ts;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*get lastmodified                                                //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String getBaseName(String Pfpath)                //~v@@@I~
    {                                                              //~v@@@I~
    	String bn;                                                 //~v@@@I~
    	String nm=new File(Pfpath).getName();                      //~v@@@I~
        int pos=nm.lastIndexOf(".");                               //~v@@@I~
        if (pos<0)                                                 //~v@@@I~
        	bn=nm;                                                 //~v@@@I~
        else                                                       //~v@@@I~
        	bn=nm.substring(0,pos);                                //~v@@@I~
        if (Dump.Y) Dump.println("UFile.getBaseName path="+Pfpath+",basename="+bn);//~v@@@R~
	    return bn;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*textfile to string                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String fileToString(String Pfpath)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.fileToString fpath="+Pfpath);//~v@@@R~
        StringBuffer sb=fileToStringBuffer(Pfpath);                //~v@@@I~
        String str=null;                                           //~v@@@I~
        if (sb!=null)                                              //~v@@@I~
        	str=sb.toString();                                     //~v@@@I~
        if (Dump.Y) Dump.println("UFile.fileToString fpath="+Pfpath+",out="+str);//~v@@@R~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static StringBuffer fileToStringBuffer(String Pfpath)         //~v@@@I~
    {                                                              //~v@@@I~
        StringBuffer sb=new StringBuffer();                        //~v@@@R~
        if (Dump.Y) Dump.println("UFile.fileToStringBuffer fpath="+Pfpath);//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	BufferedReader r=new BufferedReader(new FileReader(Pfpath));//~v@@@I~
	        String line;                                           //~v@@@I~
            while((line=r.readLine())!=null)                       //~v@@@I~
            {                                                      //~v@@@I~
            	sb.append(line);                                   //~v@@@I~
            	sb.append("\n");                                   //~v@@@I~
            }                                                      //~v@@@I~
            r.close();                                             //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
	        Dump.println(e,"UFile.fileToString fnm="+Pfpath);      //~v@@@R~
            sb=null;                                               //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UFile.fileToStringBuffer sb="+sb.toString());//~v@@@R~
        return sb;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static boolean setLastModified(String Pfpath,Long PtimeMillis)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.setLastModified fpath="+Pfpath+",time="+Long.toHexString(PtimeMillis));//~v@@@R~
        boolean rc;                                                //~v@@@R~
        File fh=new File(Pfpath);                                  //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	rc=fh.setLastModified((PtimeMillis/1000)*1000);        //~v@@@R~
	        if (Dump.Y) Dump.println("UFile.setLastModified rc="+rc+",result="+Long.toHexString(fh.lastModified()));//~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	rc=false;                                              //~v@@@I~
	        Dump.println(e,"UFile.setLastModified fnm="+Pfpath);   //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UFile.setLastModified rc="+rc);  //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static boolean chkWritableSD()                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.chkWritableSD");            //~v@@@I~
        if (!chkGrantedSD())                                       //~v@@@I~
        	return false;                                          //~v@@@I~
        AG.swSDAvailable=true;                                     //~v@@@I~
	    String path=getSDPath("");                                 //~v@@@I~
        boolean rc=path!=null;                                     //~v@@@I~
        if (Dump.Y) Dump.println("UFile.chkWritableSD rc="+rc+",path="+Utils.toString(path));//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private static boolean chkGrantedSD()                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.chkGrantedSD");            //~v@@@I~
		boolean rc=UView.isPermissionGrantedExternalStorage();     //~v@@@I~
        if (!rc)                                                   //~v@@@I~
        {                                                          //~v@@@I~
//  	    UView.requestPermissionExternalStorage(MainActivity.PERMISSION_EXTERNAL_STORAGE);//~v@@@I~//~1AH1R~
    	    UView.requestPermissionExternalStorage(AMain.PERMISSION_EXTERNAL_STORAGE);//~1AH1I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("MenuDialogConnect rc="+rc);      //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//*************************************************************************//~v@@@I~
//* from Main.onRequestPermissionResult                            //~v@@@I~
//*************************************************************************//~v@@@I~
    public static void grantedExternalStorage(boolean PswGranted)  //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc;                                                //~v@@@I~
        if (Dump.Y) Dump.println("UFile.grantedExternalStorage PswGranted="+PswGranted);//~v@@@I~
        if (!PswGranted)                                           //~v@@@I~
        {                                                          //~v@@@I~
//        	MainView.drawMsg(R.string.ExternalStorageForSDRequiresGranted);//~v@@@I~//~1AH1R~
          	UView.showToastLong(R.string.ExternalStorageForSDRequiresGranted);//~1AH1I~
            return;                                                //~v@@@I~
        }	                                                       //~v@@@I~
		UView.showToast(R.string.ExternalStorageForSDGranted);     //~v@@@I~
    	chkWritableSD();                                           //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~1110I~
