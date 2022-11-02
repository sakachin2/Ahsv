//*CID://+1amnR~:                             update#=  103;       //+1amnR~
//************************************************************************//~v102I~
//1amn 2022/10/31 (BUG when >=API30)not use UPicker for chane BGM, but use system picker//+1amnI~
//1amj 2022/10/31 explicitly say READ_PERMISSION when missing for change BGM//~1amjI~
//1ak2 2021/09/04 access external audio file                       //~1904I~
//************************************************************************//~v102I~
package com.btmtest.utils;                                        //~1110I~//~v107R~//~1Ad2R~
                                                                   //~1110I~
                                                                   //~1110I~
import com.Ahsv.AG;                                             //~1AH1I~
import com.Ahsv.Prop;
import com.Ahsv.R;
import com.Ahsv.Utils;
import com.btmtest.dialog.UPicker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import jagoclient.Dump;
import jagoclient.MainFrameOptions;

import static android.app.Activity.*;

//~1110I~
@TargetApi(30)                                                     //~1ak0I~
public class UMediaStore
        implements UPicker.UPickerI//~v@@@R~//~1ak0R~//~1aK2R~
{                                                                  //~1110I~
    private static final Uri uriAudioMedia=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI; //sdcard/Music or /SDcard/Download from API-1//~1aK2R~
//  private static final Uri uriDownloadMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29//~1aK2R~
    private   Uri uriDownloadMedia;                     //~1aK2I~
	private static final String CN="UMediaStore.";                 //~1aK2I~
    private static final int COL_ID=0;                             //~1aK2I~
    private static final int COL_TITLE=1;                          //~1aK2I~
    private static final int COL_ARTIST=2;                         //~1aK2I~
    private static final int COL_ISMUSIC=3;                        //~1aK2I~
    private static final int COL_MIMETYPE=4;                       //~1aK2I~
    private static final int COL_SIZE=5;                           //~1aK2R~
    private static final int COL_DURATION=6;                       //~1aK2I~
    private static final String[] COLUMNS_AUDIO=                   //~1aK2R~
					{MediaStore.Audio.Media._ID,                   //~1aK2I~
                     MediaStore.Audio.Media.TITLE,                 //~1aK2I~
                     MediaStore.Audio.Media.ARTIST,                //~1aK2I~
                     MediaStore.Audio.Media.IS_MUSIC,              //~1aK2I~
                     MediaStore.Audio.Media.MIME_TYPE,             //~1aK2I~
                     MediaStore.Audio.Media.SIZE,                  //~1aK2I~
                     MediaStore.Audio.Media.DURATION,              //~1aK2I~
                    };                                             //~1aK2I~
    private static final int COL_ID_DOWNLOAD=0;                    //~1aK2I~
    private static final int COL_TITLE_DOWNLOAD=1;                 //~1aK2I~
    private static final int COL_MIMETYPE_DOWNLOAD=2;              //~1aK2I~
    private static final int COL_SIZE_DOWNLOAD=3;                  //~1aK2I~
    private static final String[] COLUMNS_DOWNLOAD=                //~1aK2I~
					{MediaStore.MediaColumns._ID,                  //~1aK2I~
					 MediaStore.MediaColumns.TITLE,                //~1aK2I~
					 MediaStore.MediaColumns.MIME_TYPE,            //~1aK2I~
					 MediaStore.MediaColumns.SIZE,                 //~1aK2I~
                    };                                             //~1aK2I~
                                                                   //~1aK2I~
    private String[] colsAudio;                                    //~1aK2I~
	private ContentResolver CR;                                    //~1ak0I~//~1aK2M~
//  private boolean swTest=false;	//TODO test                        //~1ak0I~//~1aK2R~
    private boolean swPaused;                                      //~1aK2I~
    private ArrayList<AudioFile> audioFiles;                       //~1aK2I~
    private Uri uriPicked,uriStarted,uriClickedUPicker;            //~1aK2R~
    private AudioFile afPicked;                                    //~1aK2I~
    private MediaPlayer MP;                                        //~1aK2I~
    private MainFrameOptions MFO;                                  //~1aK2I~
//********************************************************         //~1ak0I~//~1aK2M~
    public class AudioFile                                        //~1ak0R~//~1aK2I~
    {                                                              //~1ak0I~//~1aK2M~
        public String title,artist,mimeType;                       //~1aK2R~
        public long id;                                            //~1aK2R~
        public boolean isMusic;                                    //~1aK2R~
        public long size;                                          //~1aK2R~
        public int min,sec,duration;                               //~1aK2I~
        public AudioFile(String Pid,String Ptitle,String Partist,String PisMusic,String PmimeType,Long Psize,String Pduration)//~1aK2R~
        {                                                          //~1ak0I~//~1aK2M~
	        title=Ptitle; artist=Partist; mimeType=PmimeType;      //~1aK2R~
            isMusic=Utils.parseInt(PisMusic,0)!=0;                 //~1aK2I~
            id=Utils.parseLong(Pid,0L);                            //~1aK2I~
            size=Psize;                                            //~1aK2I~
            duration=Utils.parseInt(Pduration,0); //millisec         //~1aK2I~
            int s=duration/1000;	//sec                          //~1aK2I~
            min=s/60;                                              //~1aK2I~
            sec=s%60;                                              //~1aK2I~
        }                                                          //~1ak0I~//~1aK2M~
        public String toString()                                          //~1ak0I~//~1aK2M~
        {                                                          //~1ak0I~//~1aK2M~
	        return "id="+id+",title="+title+",artist="+artist+",isMusic="+isMusic+",mimeType="+mimeType+",size="+size+",duration="+min+"."+sec;//~1aK2R~
        }                                                          //~1ak0I~//~1aK2M~
    }                                                              //~1ak0I~//~1aK2M~
//********************************************************         //~1ak0I~
    public UMediaStore()                                               //~1ak0I~//~1aK2R~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"constructor osVersion="+AG.osVersion);//~1ak0R~//~1aK2R~
        AG.aUMediaStore=this;                                           //~1ak0I~//~1aK2R~
//      if (swTest                                           //~1ak0R~//~1aK2R~
//      ||  AG.osVersion>= Build.VERSION_CODES.R) //>=Android-11 api30//~1ak0R~//~1aK2R~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
	        init();                                                //~1ak0R~
        }                                                          //~1aK2I~
        catch(NoClassDefFoundError e)                              //~1aK2I~
        {                                                          //~1aK2I~
            Dump.println(e,CN+"constructor NoClassDefFoundError"); //~1aK2R~
        }                                                          //~1aK2I~
    }                                                              //~1ak0I~
//********************************************************         //~1aK2I~
    private static UMediaStore getInstance()                           //~1ak0R~//~1aK2M~
    {                                                              //~1ak0I~//~1aK2M~
        UMediaStore ms=AG.aUMediaStore;                                  //~1ak0I~//~1aK2I~
	    if (Dump.Y) Dump.println(CN+"getInstance AG.aUMediaStore="+Utils.toString(ms));//~1ak0R~//~1aK2I~
        if (ms==null)                                              //~1ak0I~//~1aK2I~
        	ms=new UMediaStore();                                      //~1ak0I~//~1aK2I~
        return ms;                                                 //~1ak0I~//~1aK2I~
    }                                                              //~1ak0I~//~1aK2M~
//********************************************************         //~1401I~
    private void init()                                                         //~1ak0I~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"init");                   //~1ak0I~//~1aK2R~
        if (AG.osVersion>= 29) //Android10                         //~1aK2I~
			uriDownloadMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29//~1aK2R~
	    CR=AG.context.getContentResolver();                        //~1ak0I~
        colsAudio=COLUMNS_AUDIO;                                   //~1aK2R~
	    String strUri= Prop.getPreference(AG.PKEY_BGM_STRURI,"");   //~1aK2I~
        if (strUri.compareTo("")!=0)                                //~1aK2I~
        	uriPicked=Uri.parse(strUri);                           //~1aK2I~
    }                                                              //~1ak0I~
//********************************************************         //~1aK2I~
    public void testTest()                                         //~1aK2R~
    {                                                              //~1aK2I~
        if (Dump.Y) Dump.println(CN+"test");                       //~1aK2I~
	    audioFiles=listAudioFile(uriAudioMedia);                   //~1aK2R~
        openAudioFiles(audioFiles);                                //~1aK2R~
        if (audioFiles.size()!=0)                                  //~1aK2I~
        {                                                          //~1aK2I~
        	int pos=audioFiles.size()-1;                           //~1aK2I~
        	Uri uri= ContentUris.withAppendedId(uriAudioMedia,audioFiles.get(pos).id);//~1aK2R~
//          play(uri);                                             //~1aK2R~
        }                                                          //~1aK2I~
//      requestPickup();                                           //~1aK2R~
    }                                                              //~1aK2I~
//********************************************************         //~1ak0I~//~1aK2M~
	private ArrayList<AudioFile> listAudioFile(Uri PuirDir)//~1ak0R~//~1aK2R~
    {                                                              //~1ak0I~//~1aK2M~
    	int ctrLine=0;                                             //~1ak0I~//~1aK2M~
	    if (Dump.Y) Dump.println(CN+"listAudioFile");//~1ak0I~//~1aK2I~
        int[] idxCol=new int[colsAudio.length];                      //~1ak0I~//~1aK2M~
//      String selection="${MediaStore.Audio.Media.IS_MUSIC} > 0}";//~1aK2R~
        String selection=null;                                     //~1aK2R~
        Cursor cursor=CR.query(uriAudioMedia,colsAudio,selection,null,null);   //~1ak0I~//~1aK2R~
        if (Dump.Y) Dump.println(CN+"listAudioFile uriAudioMedia="+uriAudioMedia+",cursor="+Utils.toString(cursor));//~1ak0I~//~1aK2R~
        ArrayList<AudioFile> members=new ArrayList<AudioFile>(0/*initial ctr*/);//~1ak0I~//~1aK2I~
        if (cursor!=null && cursor.moveToFirst())                  //~1ak0I~//~1aK2M~
        {                                                          //~1ak0I~//~1aK2M~
//  	    String path=cursor.getString(0);                       //~1aK2I~
//  	    if (Dump.Y) Dump.println(CN+"listAudioFile path="+Utils.toString(path));//~1aK2I~
//          File f=new File(path);                                 //~1aK2I~
//  	    if (Dump.Y) Dump.println(CN+"listAudioFile File="+f);  //~1aK2I~
        	for (int ii=0;ii<colsAudio.length;ii++)                  //~1ak0I~//~1aK2M~
            {                                                      //~1ak0I~//~1aK2M~
            	idxCol[ii]=cursor.getColumnIndex(colsAudio[ii]);     //~1ak0I~//~1aK2M~
		        if (Dump.Y) Dump.println(CN+"listAudioFile colidx ii="+ii+",col="+colsAudio[ii]+",idx="+idxCol[ii]);//~1ak0I~//~1aK2I~
            }                                                      //~1ak0I~//~1aK2M~
            ctrLine=cursor.getCount();                             //~1ak0I~//~1aK2M~
        	members=new ArrayList<AudioFile>(ctrLine);            //~1ak0I~//~1aK2I~
		    if (Dump.Y) Dump.println(CN+"listAudioFile ctrLine="+ctrLine);//~1ak0I~//~1aK2R~
            for (int ii=0;ii<ctrLine;ii++)                         //~1ak0I~//~1aK2M~
            {                                                      //~1ak0I~//~1aK2M~
                if (Dump.Y) Dump.println(CN+"listAudioFile ii="+ii+",title="+cursor.getString(idxCol[COL_TITLE])+",isMusic="+cursor.getString(idxCol[COL_ISMUSIC]));//~1ak0I~//~1aK2R~
                boolean isMusic=Utils.parseInt(cursor.getString(idxCol[COL_ISMUSIC]),0)!=0;//~1aK2I~
                if (isMusic)                                       //~1aK2I~
                {                                                  //~1aK2I~
                	AudioFile af=newAudioFile(cursor,idxCol);      //~1aK2R~
	                members.add(af);                               //~1aK2R~
                }                                                  //~1aK2I~
                cursor.moveToNext();                               //~1ak0I~//~1aK2M~
            }                                                      //~1ak0I~//~1aK2M~
        }                                                          //~1ak0I~//~1aK2M~
        if (cursor!=null)                                          //~1aK2I~
            cursor.close();                                        //~1aK2I~
//        if (members.size()>0)   //TODO test                      //~1aK2R~
//        {                                                        //~1aK2R~
//            uriPicked=ContentUris.withAppendedId(uriAudioMedia,members.get(0).id);//~1aK2R~
//            if ((AG.Options & AG.OPTIONS_BGM)!=0)                //~1aK2R~
//                startBGM();                                      //~1aK2R~
//        }                                                        //~1aK2R~
		if (Dump.Y) Dump.println(CN+"listAudioFile exit members size="+members.size());//~1ak0R~//~1aK2R~
        return members;                                            //~1ak0I~//~1aK2M~
    }                                                              //~1ak0I~//~1aK2M~
//********************************************************         //~1aK2I~
	public static Uri getMemberUri(Uri PuriDir,long Pid)           //~1aK2R~
    {                                                              //~1aK2I~
    	Uri uri=ContentUris.withAppendedId(PuriDir,Pid);           //~1aK2I~
		if (Dump.Y) Dump.println(CN+"getMemberUri uriDir="+PuriDir+",id="+Pid+",memberUri="+uri);//~1aK2I~
        return uri;                                                //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
//*TODO test                                                       //~1aK2I~
//********************************************************         //~1aK2I~
	private void listDownloadFile()                                //~1aK2R~
    {                                                              //~1aK2I~
    	int ctrLine=0;                                             //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"listDownloadFile uriDownload="+uriDownloadMedia);//~1aK2R~
        if (uriDownloadMedia==null)                                //~1aK2I~
        	return;                                                //~1aK2I~
        String[] cols=COLUMNS_DOWNLOAD;                            //~1aK2R~
        int[] idxCol=new int[cols.length];                 //~1aK2R~
        String selection=null;                                            //~1aK2I~
        Cursor cursor=CR.query(uriDownloadMedia,cols,selection,null,null);//~1aK2R~
        if (Dump.Y) Dump.println(CN+"listDownloadFile uriDownloadMedia="+uriDownloadMedia+",cursor="+Utils.toString(cursor));//~1aK2I~
        ArrayList<AudioFile> members=new ArrayList<AudioFile>(0/*initial ctr*/);//~1aK2I~
        if (cursor!=null && cursor.moveToFirst())                  //~1aK2I~
        {                                                          //~1aK2I~
        	for (int ii=0;ii<colsAudio.length;ii++)                //~1aK2I~
            {                                                      //~1aK2I~
            	idxCol[ii]=cursor.getColumnIndex(cols[ii]);        //~1aK2R~
		        if (Dump.Y) Dump.println(CN+"listDownloadFile colidx ii="+ii+",col="+cols[ii]+",idx="+idxCol[ii]);//~1aK2R~
            }                                                      //~1aK2I~
            ctrLine=cursor.getCount();                             //~1aK2I~
        	members=new ArrayList<AudioFile>(ctrLine);             //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"listDownloadFile ctrLine="+ctrLine);//~1aK2I~
            for (int ii=0;ii<ctrLine;ii++)                         //~1aK2I~
            {                                                      //~1aK2I~
                if (Dump.Y) Dump.println(CN+"listDownloadFile ii="+ii+",title="+cursor.getString(idxCol[COL_TITLE_DOWNLOAD])+",mimeType="+cursor.getString(idxCol[COL_MIMETYPE_DOWNLOAD]));//~1aK2R~
                cursor.moveToNext();                               //~1aK2I~
            }                                                      //~1aK2I~
        }                                                          //~1aK2I~
        if (cursor!=null)                                          //~1aK2I~
            cursor.close();                                        //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private AudioFile newAudioFile(Cursor Pcursor,int[] PidxCol)
    {                                                              //~1aK2I~
        if (Dump.Y) Dump.println(CN+"newAudioFile id="+Pcursor.getString(PidxCol[COL_ID])+//~1aK2I~
					",title="+Pcursor.getString(PidxCol[COL_TITLE])+	//title//~1aK2I~
					",artist="+Pcursor.getString(PidxCol[COL_ARTIST])+	//artist//~1aK2I~
					",isMusic="+Pcursor.getString(PidxCol[COL_ISMUSIC])+	//isMusic//~1aK2I~
					",mimetype="+Pcursor.getString(PidxCol[COL_MIMETYPE])+   //mime_type//~1aK2R~
					",size="+Pcursor.getLong(PidxCol[COL_SIZE])+   //mime_type//~1aK2R~
					",duration="+Pcursor.getString(PidxCol[COL_DURATION]));   //mime_type//~1aK2R~
        AudioFile af=new AudioFile(                                //~1aK2I~
					Pcursor.getString(PidxCol[COL_ID]),	//id       //~1aK2R~
					Pcursor.getString(PidxCol[COL_TITLE]),	//title//~1aK2R~
					Pcursor.getString(PidxCol[COL_ARTIST]),	//artist//~1aK2R~
					Pcursor.getString(PidxCol[COL_ISMUSIC]),	//isMusic//~1aK2R~
					Pcursor.getString(PidxCol[COL_MIMETYPE]),   //mime_type//~1aK2R~
					Pcursor.getLong(PidxCol[COL_SIZE]),   //mime_type//~1aK2R~
					Pcursor.getString(PidxCol[COL_DURATION])   //mime_type//~1aK2R~
					);                                             //~1aK2I~
        if (Dump.Y) Dump.println(CN+"newAudioFile AudioFile="+af.toString());//~1aK2I~
        return af;                                                 //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private AudioFile getAudioInfo(Uri Puri)                       //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"getAudioInfo Puri="+Puri);    //~1aK2I~
        int[] idxCol=new int[colsAudio.length];                    //~1aK2I~
        Cursor cursor=CR.query(Puri,colsAudio,null,null,null);     //~1aK2I~
        AudioFile af=null;                                         //~1aK2I~
        if (Dump.Y) Dump.println(CN+"getAudioInfo cursor="+Utils.toString(cursor));//~1aK2R~
        if (cursor!=null && cursor.moveToFirst())                  //~1aK2I~
        {                                                          //~1aK2I~
        	for (int ii=0;ii<colsAudio.length;ii++)                //~1aK2I~
            {                                                      //~1aK2I~
            	idxCol[ii]=cursor.getColumnIndex(colsAudio[ii]);   //~1aK2I~
		        if (Dump.Y) Dump.println(CN+"listAudioInfo colidx ii="+ii+",col="+colsAudio[ii]+",idx="+idxCol[ii]);//~1aK2R~
            }                                                      //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"getAudioInfo ctrLine="+cursor.getCount());//~1aK2R~
            af=newAudioFile(cursor,idxCol);                        //~1aK2R~
        }                                                          //~1aK2I~
        if (cursor!=null)                                          //~1aK2I~
            cursor.close();                                        //~1aK2I~
        return af;                                                 //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private void openAudioFiles(ArrayList<AudioFile> Plist)        //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"openAudioFiles");             //~1aK2I~
        for (AudioFile af:Plist)                                   //~1aK2I~
        {                                                          //~1aK2I~
            if (Dump.Y) Dump.println(CN+"openAudioFiles AudioFile="+af.toString());//~1aK2I~
        	if (!af.isMusic)                                       //~1aK2I~
            	continue;                                          //~1aK2I~
        	Uri uri= ContentUris.withAppendedId(uriAudioMedia,af.id);//~1aK2I~
            openAudioFile(uri);                                    //~1aK2I~
        }                                                          //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private void openAudioFile(Uri Puri)                           //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"openAudioFile uri="+Puri);    //~1aK2I~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
        	InputStream is=CR.openInputStream(Puri);               //~1aK2I~
        	InputStreamReader isr=new InputStreamReader(is);       //~1aK2I~
        	BufferedReader br=new BufferedReader(isr);             //~1aK2I~
            br.close();                                            //~1aK2I~
        }                                                          //~1aK2I~
        catch(FileNotFoundException e)                             //~1aK2I~
        {                                                          //~1aK2I~
        	Dump.println(e,CN+"openAudioFile FileNotFound:"+Puri); //~1aK2R~
        }                                                          //~1aK2I~
        catch(IOException e)                                       //~1aK2I~
        {                                                          //~1aK2I~
        	Dump.println(e,CN+"openAudioFile IOException:"+Puri);  //~1aK2R~
        }                                                          //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"openAudioFile exit");         //~1aK2R~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private void play(Uri Puri)                                    //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"play uri="+Puri);             //~1aK2I~
        MediaPlayer mp=new MediaPlayer();                          //~1aK2I~
        MP=mp;
        final Uri uriPlay=Puri;//~1aK2I~
//      mp.setAudioStreamType(AudioManager.STREAM_MUSIC);          //~1aK2R~
        setStreamType(mp);                                         //~1aK2R~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
            mp.setDataSource(AG.context,Puri); //IOException if FileNotFound//~1aK2R~
//         	mp.setDataSource(AG.aMain.getApplicationContext(),Puri); //IOException if FileNotFound//~1aK2R~
//        	mp.prepare();                                          //~1aK2R~
          	mp.setLooping(true);                                   //~1aK2I~
        	mp.setOnPreparedListener(                               //~1aK2I~
					new MediaPlayer.OnPreparedListener()                        //~1aK2I~
                    {                                              //~1aK2I~
                    	@Override                                  //~1aK2I~
                        public void onPrepared(MediaPlayer Pmp)    //~1aK2I~
                        {                                          //~1aK2I~
	    					if (Dump.Y) Dump.println(CN+"play.onPrepared mp.start()");//~1aK2R~
        					Pmp.seekTo(0);                                      //~1327I~//~1aK2I~
                        	Pmp.start();                           //~1aK2I~
                            uriStarted=uriPlay;                       //~1aK2I~
                        }                                          //~1aK2I~
                    });                                            //~1aK2I~
        	mp.setOnErrorListener(                                 //~1aK2I~
					new MediaPlayer.OnErrorListener()              //~1aK2I~
                    {                                              //~1aK2I~
                    	@Override                                  //~1aK2I~
                        public boolean onError(MediaPlayer Pmp,int Pwhat,int Pextra)//~1aK2I~
                        {                                          //~1aK2I~
	    					if (Dump.Y) Dump.println(CN+"play.onError@@@@ what="+Pwhat+",extra="+Pextra);//~1aK2R~
                            resetMP(Pmp,true/*swStop*/);                           //~1aK2I~
                            boolean rc=true;	//app processed    //~1aK2R~
                            return rc;
                        }                                          //~1aK2I~
                    });                                            //~1aK2I~
        	mp.setOnCompletionListener(                            //~1aK2I~
					new MediaPlayer.OnCompletionListener()         //~1aK2I~
                    {                                              //~1aK2I~
                    	@Override                                  //~1aK2I~
                        public void onCompletion(MediaPlayer Pmp)//~1aK2I~
                        {                                          //~1aK2I~
	    					if (Dump.Y) Dump.println(CN+"play.onCompletion");//~1aK2R~
                            resetMP(Pmp,false/*swStop*/);                           //~1aK2I~
                        }                                          //~1aK2I~
                    });                                            //~1aK2I~
        	mp.prepareAsync();                                     //~1aK2I~
        }                                                          //~1aK2I~
        catch(IllegalArgumentException e)                         //~1aK2R~
        {                                                          //~1aK2I~
        	Dump.println(e,CN+"play IlleagalArgumentException:"+Puri);//~1aK2R~
        }                                                          //~1aK2I~
        catch(IllegalStateException e)                             //~1aK2I~
        {                                                          //~1aK2I~
        	Dump.println(e,CN+"play IlleagalStateException:"+Puri);//~1aK2I~
        }                                                          //~1aK2I~
        catch(IOException e)                                       //~1aK2I~
        {                                                          //~1aK2I~
        	Dump.println(e,CN+"play IOException:"+Puri);           //~1aK2I~
        }                                                          //~1aK2I~
//      mp.start();                                                //~1aK2R~
	    if (Dump.Y) Dump.println(CN+"play exit");                  //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
    private void resetMP(MediaPlayer Pmp,boolean PswStop)          //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"resetMP uriStarted="+Utils.toString(uriStarted)+",MP="+Pmp);//~1aK2R~
        if (Pmp==null)                                             //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"resetMP return by MP=null");//~1aK2I~
        	return;                                                //~1aK2I~
        }                                                          //~1aK2I~
        Uri uri=uriStarted;                                        //~1aK2I~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"resetMP stop");           //~1aK2I~
        	if (PswStop)                                           //~1aK2I~
        		Pmp.stop();                                        //~1aK2I~
        }                                                          //~1aK2I~
        catch(IllegalArgumentException e)                          //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalArgumentException:"+Utils.toString(uri));//~1aK2R~
        }                                                          //~1aK2I~
        catch(IllegalStateException e)                             //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalStateException:"+Utils.toString(uri));//~1aK2R~
        }                                                          //~1aK2I~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"resetMP reset");          //~1aK2I~
        	Pmp.reset();                                           //~1aK2I~
        }                                                          //~1aK2I~
        catch(IllegalArgumentException e)                          //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalArgumentException:"+Utils.toString(uri));//~1aK2R~
        }                                                          //~1aK2I~
        catch(IllegalStateException e)                             //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalStateException:"+Utils.toString(uri));//~1aK2R~
        }                                                          //~1aK2I~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"resetMP release");        //~1aK2I~
        	Pmp.release();                                         //~1aK2I~
        }                                                          //~1aK2I~
        catch(IllegalArgumentException e)                          //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalArgumentException:"+Utils.toString(uri));//~1aK2R~
        }                                                          //~1aK2I~
        catch(IllegalStateException e)                             //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalStateException:"+Utils.toString(uri));//~1aK2R~
        }                                                          //~1aK2I~
        MP=null;                                                   //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private void setStreamType(MediaPlayer Pplayer)                //~1aK2I~
    {                                                              //~1aK2I~
        if (AG.osVersion>= 26) //Android8.0 Oreo=Api26             //~1aK2I~
        	setStreamType_26(Pplayer);                              //~1aK2I~
        else                                                       //~1aK2I~
        	setStreamType_25(Pplayer);                              //~1aK2I~
    }                                                              //~1aK2I~
    @SuppressWarnings("deprecation")                               //~1ak1I~//~1aK2I~
	private void setStreamType_25(MediaPlayer Pplayer)             //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"setStreamPlayer_25");         //~1aK2R~
        Pplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);     //~1aK2I~
    }                                                              //~1aK2I~
	@TargetApi(26)     //KitKat                                    //~v@@@I~//~1aK2I~
	private void setStreamType_26(MediaPlayer Pplayer)             //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"setStreamPlayer_26");         //~1aK2R~
        Pplayer.setAudioAttributes(                                //~1aK2R~
            new AudioAttributes                                    //~1aK2R~
                .Builder()                                         //~1aK2R~
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)//~1aK2R~
                .setUsage(AudioAttributes.USAGE_MEDIA)             //~1aK2R~
                .build());                                         //~1aK2R~
//        AudioAttributes.Builder ab=new AudioAttributes.Builder();//~1aK2R~
//        ab.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC);   //~1aK2R~
//        ab.setUsage(AudioAttributes.USAGE_MEDIA);                //~1aK2R~
//        AudioAttributes aa=ab.build();                           //~1aK2R~
//        Pplayer.setAudioAttributes(aa);                          //~1aK2R~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private void requestPickup()                                   //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"requestPickup osVersion="+AG.osVersion);//~1aK2R~
//      if (AG.osVersion>= 30) //Android8.0 Oreo=Api26             //~1aK2R~//+1amnR~
//      if (true) //TODO test                                      //~1aK2R~
//      {                                                          //~1aK2I~//+1amnR~
//      	UPicker.newInstance(uriAudioMedia,this).showDialog(); //~1aK2I~//+1amnR~
//          return;                                                //~1aK2I~//+1amnR~
//      }                                                          //~1aK2I~//+1amnR~
      if (false)//TODO test                                        //~1aK2R~
      {                                                            //~1aK2I~
        Intent intent2=new Intent(Intent.ACTION_OPEN_DOCUMENT);   //can not play on picker,invalid cusrsor data for GetString()//~1aK2I~
        intent2.addCategory(Intent.CATEGORY_OPENABLE);             //~1aK2I~
//      intent2.setType("*/*");                                    //~1aK2R~
        intent2.setType("audio/*");                                //~1aK2I~
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);//~1aK2I~
      }                                                            //~1aK2I~
      else                                                         //~1aK2I~
      if (false)//TODO test                                        //~1aK2R~
      {                                                            //~1aK2I~
        Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);    //can not play on picker,invalid cusrsor data for GetString()//~1aK2R~
        intent2.setType("*/*");                                    //~1aK2I~
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);//~1aK2I~
      }                                                            //~1aK2I~
      else                                                         //~1aK2I~
      if (false) //TODO test                                       //~1aK2R~
      {                                                            //~1aK2I~
        Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);    //can not play on picker,invalid cusrsor data//~1aK2R~
        intent2.setType("audio/*");                                //~1aK2I~
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);//~1aK2I~
      }                                                            //~1aK2I~
      else                                                         //~1aK2I~
      {                                                            //~1aK2I~
        Intent intent=new Intent(Intent.ACTION_PICK,uriAudioMedia);// can play on picker to select music//~1aK2R~
//      intent.setType("audio/*");                                 //~1aK2R~
        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);//~1aK2I~
      }                                                            //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"requestPickup exit");         //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
//*from AMain                                                      //~1aK2I~
//********************************************************         //~1aK2I~
	public static void onActivityResult(int Prequest,int Presult,Intent Pintent)//~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"onActivityResult request="+Prequest+",result="+Presult);//~1aK2I~
        UMediaStore ums=getInstance();                         //~1aK2R~
        if (Presult==RESULT_OK)                                    //~1aK2I~
        {                                                          //~1aK2I~
        	Uri uri=Pintent.getData();                             //~1aK2R~
    		AudioFile af=ums.getAudioInfo(uri);                    //~1aK2I~
            if (af==null)                                          //~1aK2I~
            {                                                      //~1aK2I~
            	invalidSelection(uri);                             //~1aK2I~
            	return;                                            //~1aK2I~
            }                                                      //~1aK2I~
            if (!af.isMusic)                                       //~1aK2I~
            {                                                      //~1aK2I~
		    	if (Dump.Y) Dump.println(CN+"onActivityResult OK but not Music="+af.toString());//~1aK2I~
            }                                                      //~1aK2I~
			ums.afPicked=af;                                       //~1aK2I~
            ums.uriPicked=uri;                                     //~1aK2R~
        	if ((AG.Options & AG.OPTIONS_BGM)!=0)                  //~1aK2M~
            {                                                      //~1aK2M~
            	ums.play(uri);                                     //~1aK2R~
            }                                                      //~1aK2I~
            if (ums.MFO!=null)                                     //~1aK2I~
            {                                                      //~1aK2I~
            	ums.MFO.setBGMTitle(ums.afPicked.title,uri.toString()/*save to preference by OK button*/);//~1aK2R~
            }                                                      //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"onActivityResult OK uri="+uri);//~1aK2I~
        }                                                          //~1aK2I~
        ums.MFO=null;                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"onActivityResult exit");      //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	@Override //UPickerI                                           //~1aK2I~
	public void itemSelected(Uri PitemUri)                         //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"itemSelected PitemUri="+PitemUri);//~1aK2R~
    	resetMP(MP,true/*PswStop*/);                               //~1aK2I~
		Uri uri=PitemUri;                                          //~1aK2I~
        uriPicked=uri;                                             //~1aK2R~
		afPicked=getAudioInfo(uri);                                //~1aK2I~
        if (afPicked==null)                                        //~1aK2I~
        {                                                          //~1aK2I~
            invalidSelection(uri);                                 //~1aK2I~
            return;                                                //~1aK2I~
        }                                                          //~1aK2I~
        if ((AG.Options & AG.OPTIONS_BGM)!=0)                      //~1aK2I~
        {                                                          //~1aK2I~
            play(uri);                                             //~1aK2I~
        }                                                          //~1aK2I~
        if (MFO!=null)                                             //~1aK2I~
        {                                                          //~1aK2I~
            MFO.setBGMTitle(afPicked.title,uri.toString()/*save to preference by OK button*/);//~1aK2I~
        }                                                          //~1aK2I~
        MFO=null;                                                  //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"itemSelected exit");          //~1aK2R~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
//*from UPicker for API30                                          //~1aK2I~
//********************************************************         //~1aK2I~
	public void itemClicked(Uri PitemUri)                          //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"itemSelected PitemUri="+PitemUri);//~1aK2I~
		Uri uri=PitemUri;
        uriClickedUPicker=uri;                                       //~1aK2I~
		AudioFile af=getAudioInfo(uri);                     //~1aK2I~
        if (af==null)                                              //~1aK2I~
        {                                                          //~1aK2I~
            invalidSelection(uri);                          //~1aK2I~
            return;                                                //~1aK2I~
        }                                                          //~1aK2I~
    	resetMP(MP,true/*PswStop*/);                               //~1aK2I~
        play(uri);                                                 //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"itemClicked exit");           //~1aK2I~
    }                                                              //~1aK2I~
	public void itemCanceled()                                     //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"itemCanceled");               //~1aK2I~
    	resetMP(MP,true/*PswStop*/);                               //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"itemCanceled exit");          //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
//*from MainFrameOptions                                           //~1aK2I~
//********************************************************         //~1aK2I~
	public static void startBGM()                                  //~1aK2I~
    {                                                              //~1aK2I~
        UMediaStore ums=getInstance();                             //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"startBGM swpaused="+ums.swPaused+",uriPicked="+Utils.toString(ums.uriPicked)+",MP="+Utils.toString(ums.MP));//~1aK2R~
        if (ums.uriPicked!=null)                                   //~1aK2I~
        {                                                          //~1aK2I~
        	if (ums.MP==null)                                      //~1aK2I~
	        	ums.play(ums.uriPicked);                           //~1aK2I~
            else                                                   //~1aK2I~
            	if (ums.swPaused)                                  //~1aK2I~
        			ums.MP.start();                                //~1aK2I~
        }                                                          //~1aK2I~
        else                                                       //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"startBGM no uri picked yet");//~1aK2I~
        }                                                          //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	private void pauseBGM()                                        //~1aK2I~
    {                                                              //~1aK2I~
        try                                                        //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"pauseBGM MP="+Utils.toString(MP));//~1aK2I~
            if (MP!=null)                                          //~1aK2I~
            {                                                      //~1aK2I~
	        	MP.pause();                                        //~1aK2I~
                swPaused=true;                                     //~1aK2I~
            }                                                      //~1aK2I~
        }                                                          //~1aK2I~
        catch(Exception e)                                         //~1aK2I~
        {                                                          //~1aK2I~
        	if (Dump.Y) Dump.println(e,CN+"pause");                //~1aK2I~
        }                                                          //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	public static void stopBGM(boolean PswPause)                   //~1aK2R~
    {                                                              //~1aK2I~
        UMediaStore ums=getInstance();                             //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"stopBGM swPause="+PswPause+",uriPicked="+Utils.toString(ums.uriPicked)+",MP="+Utils.toString(ums.MP));//~1aK2R~
        if (ums.MP!=null)                                          //~1aK2I~
        {                                                          //~1aK2I~
        	if (PswPause)                                          //~1aK2I~
            	ums.pauseBGM();                                    //~1aK2I~
            else                                                   //~1aK2I~
		        ums.resetMP(ums.MP,true/*swStop*/);                //~1aK2R~
        }                                                          //~1aK2I~
        else                                                       //~1aK2I~
        {                                                          //~1aK2I~
		    if (Dump.Y) Dump.println(CN+"stopBGM MP=null");        //~1aK2R~
        }                                                          //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	public static void changeBGM(MainFrameOptions Pmfo)            //~1aK2R~
    {                                                              //~1aK2I~
        UMediaStore ums=getInstance();                             //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"changeBGM AG.swGrantedExternalStorageRead="+AG.swGrantedExternalStorageRead+",uriPicked="+Utils.toString(ums.uriPicked)+",MP="+Utils.toString(ums.MP));//~1aK2R~
        if (!AG.swGrantedExternalStorageRead)                      //~1aK2R~
        {                                                          //~1aK2I~
//          UView.showToast(R.string.ErrNoExternalStoragePermission);//~1aK2I~//~1amjR~
            UView.showToast(R.string.ErrNoExternalStorageReadPermission);//~1amjI~
        	return;                                                //~1aK2I~
        }                                                          //~1aK2I~
        stopBGM(false/*swPause*/);                                 //~1aK2R~
        ums.MFO=Pmfo;                                              //~1aK2M~
        ums.requestPickup();                                       //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	public static String getCurrentTitle()                         //~1aK2I~
    {                                                              //~1aK2I~
        UMediaStore ums=getInstance();                             //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"getCurrentTitle uriPicked="+Utils.toString(ums.uriPicked)+",MP="+Utils.toString(ums.MP)+",AudioFile="+Utils.toString(ums.afPicked));//~1aK2I~
        String rc="";                                              //~1aK2I~
        if (ums.afPicked!=null)                                     //~1aK2I~
	        rc=ums.afPicked.title;                                 //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"getCurrentTitle rc="+rc);     //~1aK2I~
        return rc;                                                 //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	public static ArrayList<AudioFile> getMemberList(Uri PuriDir)  //~1aK2I~
    {                                                              //~1aK2I~
        UMediaStore ums=getInstance();                             //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"getMemberList uriDir="+PuriDir);//~1aK2R~
		ArrayList<AudioFile> list=ums.listAudioFile(PuriDir);      //~1aK2I~
        return list;                                               //~1aK2I~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	public static void onPause()                                   //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"onPause");                    //~1aK2I~
    	stopBGM(true/*pase*/);                                     //~1aK2R~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
	public static void onResume()                                  //~1aK2I~
    {                                                              //~1aK2I~
	    if (Dump.Y) Dump.println(CN+"onResume AG.swGrantedExternalStorageRead="+AG.swGrantedExternalStorageRead+",AG.Options="+Integer.toHexString(AG.Options));//~1aK2R~
        if (!AG.swGrantedExternalStorageRead)                      //~1aK2R~
        	return;                                                //~1aK2I~
		UMediaStore ums=getInstance();                             //~1aK2R~
//  	ums.audioFiles=ums.listAudioFile(ums.uriAudioMedia);	//TODO test//~1aK2R~
//  	getInstance().listDownloadFile();		//TODO test        //~1aK2R~
        if ((AG.Options & AG.OPTIONS_BGM)!=0)                      //~1aK2R~
	    	startBGM();                                            //~1aK2R~
    }                                                              //~1aK2I~
//********************************************************         //~1aK2I~
    private static void invalidSelection(Uri Puri)                        //~1aK2I~
    {                                                              //~1aK2I~
		UView.showToast(Utils.getStr(R.string.ErrInvalidAudioSelection," "+Puri));//~1aK2I~
    }                                                              //~1aK2I~
}//class                                                           //~1110I~
