//*CID://+1amgR~:                             update#=  219;       //~1amqR~//~1amgR~
//************************************************************************//~v102I~
//1amq 2022/10/31 Scoped storage is from android10(Api29) ;swScopedTEs should be false//~1amqI~
//1amm 2022/10/31 (BUG)duplicated alert for document folder specification//~1amgI~
//1amg 2022/10/30 (Bug) scoped record is written by testing        //~1amgR~
//1ak0 2021/08/26 androd11:externalStorage:ScopedStorage           //~1ak0I~
//************************************************************************//~v102I~
package com.btmtest.utils;                                        //~1110I~//~v107R~//~1Ad2R~
                                                                   //~1110I~
                                                                   //~1110I~
import com.Ahsv.AG;                                             //~1AH1I~
import com.Ahsv.Alert;
import com.Ahsv.AlertI;
import com.Ahsv.Prop;
import com.Ahsv.R;
import com.Ahsv.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.UriPermission;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;

import androidx.documentfile.provider.DocumentFile;                //~1ak0I~

import jagoclient.Dump;

//~1110I~
@TargetApi(30)                                                     //~1ak0I~
public class UScoped                                                 //~v@@@R~//~1ak0R~
		 implements AlertI                                         //~1ak0I~
{                                                                  //~1110I~
	private static final String PREFKEY_SAVE_DIR="SaveDir";        //~1ak0R~
	private static final String FILTER_HISTORY=".history";         //~1ak0I~
	private static final String SEP_MEMBER="%2F";                   //~1ak0I~
	private static final String CN="UScoped.";                      //~1ak0I~
	private ContentResolver CR;                                    //~1ak0I~
	private String saveDirTop;                                     //~1ak0M~
	private Uri uriSaveDir;                                        //~1ak0I~
    private DocumentFile docDir;                                   //~1ak0I~
//  private boolean swScopedTest=true;	//TODO test                //~1ak0R~//~1amqR~
    private boolean swScopedTest=false;                            //~1amqI~
    private boolean swInitialized;                                         //~1ak0I~
    private boolean swAlertShowing;                                //~1ammR~
//********************************************************         //~1ak0I~
    public UScoped()                                               //~1ak0I~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"constructor osVersion="+AG.osVersion+",swScopedTest="+swScopedTest);//~1ak0R~
        AG.aUScoped=this;                                           //~1ak0I~
        if (swScopedTest                                           //~1ak0R~
        ||  AG.osVersion>= Build.VERSION_CODES.R) //>=Android-11 api30//~1ak0R~
        try                                                        //~1ak0I~
        {                                                          //~1ak0I~
	        init();                                                //~1ak0R~
        }                                                          //~1ak0I~
        catch(NoClassDefFoundError e)                                 //~1ak0I~
        {                                                          //~1ak0I~
            Dump.println(e,CN+"constructor NoClassDefFoundError"); //~1ak0I~
        }                                                          //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1401I~
    private void init()                                                         //~1ak0I~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"init");                       //~1ak0R~
        saveDirTop=AG.appNameE;                                    //~1ak0I~
	    CR=AG.context.getContentResolver();                        //~1ak0I~
//TODO test start                                                  //~1ak0I~
    	File fd;                                                   //~1ak0I~
    	fd=AG.context.getCacheDir();                               //~1ak0I~
    	if (Dump.Y) Dump.println(CN+"init CacheDir="+fd.getAbsolutePath());//~1ak0R~
    	fd=AG.context.getExternalCacheDir();                       //~1ak0I~
    	if (Dump.Y) Dump.println(CN+"init ExternalCacheDir="+fd.getAbsolutePath());//~1ak0R~
    	fd=AG.context.getExternalFilesDir("cfgs");                 //~1ak0I~
    	if (Dump.Y) Dump.println(CN+"init context.getExternnalFilesDir="+fd.getAbsolutePath());//~1ak0R~
        if (AG.osVersion>=29) //allow on 29:android10 by manifest: application-->requestLagacyExternalStorage="true" (ignored when target=androd11)//~va42I~//~1ak0I~
	    	chkExternalDir();                                      //~1ak0R~
//TODO test end                                                    //~1ak0I~
        swInitialized=true;                                        //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
//*by ACTIVITY_RESULT                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void initComp(Uri PuriDir)                             //~1ak0I~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"initComp uriDir="+PuriDir);   //~1ak0R~
        uriSaveDir=PuriDir;                                         //~1ak0I~
		confirmPermission(uriSaveDir,true/*swAction*/);            //~1ak0R~
		docDir=DocumentFile.fromTreeUri(AG.context,uriSaveDir);    //~1ak0M~
        AG.swScoped=true;                                          //~1ak0M~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
//*by saved strUri at APP restart                                  //~1ak0I~
//********************************************************         //~1ak0I~
	private void initComp(String PstrUri)                          //~1ak0I~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"initComp strUri="+PstrUri);   //~1ak0R~
        uriSaveDir=Uri.parse(PstrUri);                             //~1ak0I~
		boolean rc=confirmPermission(uriSaveDir,false/*swAction*/);//~1ak0R~
        if (!rc)                                                   //~1ak0I~
        {                                                          //~1ak0I~
			requestDocumentTree(false/*swInstall*/);               //~1ak0I~
        	return;                                                //~1ak0I~
        }                                                          //~1ak0I~
		docDir=DocumentFile.fromTreeUri(AG.context,uriSaveDir);    //~1ak0M~
        AG.swScoped=true;                                          //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private boolean confirmPermission(Uri Puri,boolean PswAction)     //~1ak0R~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"confirmPermission swAction="+PswAction+",uri="+Puri);//~1ak0R~
        boolean rc=false;                                          //~1ak0I~
		rc=chkPermission(Puri);                                    //~1ak0R~
        if (PswAction)                                             //~1ak0I~
        {                                                          //~1ak0I~
	        int flags=Intent.FLAG_GRANT_READ_URI_PERMISSION        //~1ak0R~
    	    		| Intent.FLAG_GRANT_WRITE_URI_PERMISSION;      //~1ak0R~
        	CR.takePersistableUriPermission(Puri,flags);  //remember across device reboot//~1ak0R~
			rc=chkPermission(Puri);                                //~1ak0R~
        }                                                          //~1ak0I~
        if (Dump.Y) Dump.println(CN+"confirmPermission rc="+rc);   //~1ak0R~
        return rc;
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private boolean chkPermission(Uri Puri)                        //~1ak0R~
    {                                                              //~1ak0I~
    	boolean rc=false;                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"chkPermission uri="+Puri);    //~1ak0R~
//        List<UriPermission> list1=CR.getOutgoingPersistedUriPermissions();//~1ak0R~
//        if (Dump.Y) Dump.println(CN+"chkPermission outGoingPersisted list size="+list1.size());//~1ak0R~
//        for (UriPermission p:list1)                              //~1ak0R~
//        {                                                        //~1ak0R~
//            if (Dump.Y) Dump.println(CN+"chkPermission getOutgoingPerssistedUriPermission permision="+p.toString()+",uri="+p.getUri());//~1ak0R~
//        }                                                        //~1ak0R~
    	List<UriPermission> list2=CR.getPersistedUriPermissions(); //persisted by this app//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"chkPermission persisted list size="+list2.size());//~1ak0R~
        for (UriPermission p:list2)                                //~1ak0I~
        {                                                          //~1ak0I~
	        if (Dump.Y) Dump.println(CN+"chkPermission getPerssistedUriPermission permision="+p.toString()+",uri="+p.getUri());//~1ak0R~
            if ((Puri.toString()).compareTo(p.getUri().toString())==0)             //~1ak0I~
            	rc=true;                                           //~1ak0I~
        }                                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"chkPermission rc="+rc);       //~1ak0R~
        return rc;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	public static boolean isScoped()                               //~1ak0I~
    {                                                              //~1ak0I~
    	UScoped us=getInstance();                                   //~1ak0I~
        boolean rc=us!=null && us.swInitialized;                  //~1ak0R~
        if (Dump.Y) Dump.println(CN+"isScoped rc="+rc+",osVersion="+AG.osVersion);//~1ak0R~
        return rc;
    }                                                              //~1ak0I~
//********************************************************         //~1401I~
	private void chkExternalDir()                                  //~1ak0I~
    {                                                              //~1ak0I~
    	File[] fds;                                                //~1ak0I~
    	fds=AG.context.getExternalFilesDirs("Ahsv");               //~1ak0I~
        for (File f:fds)                                           //~1ak0I~
        {                                                          //~1ak0I~
        	if (f==null)                                           //~1ak0I~
            	continue;                                          //~1ak0I~
	    	if (Dump.Y) Dump.println(CN+"init context.getExternnalFilesDirs="+f.getAbsolutePath());//~1ak0R~
        }                                                          //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void requestDocumentTree(boolean PswInstall)           //~1ak0R~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"requestDocumentTree swAlertShowing="+swAlertShowing);//~1ammR~
      if (!swAlertShowing)                                         //~1ammR~
      {                                                            //~1ammR~
    	int flag= Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE;      //~1ak0I~
        int msgid;                                                 //~1ak0I~
        msgid=R.string.RequestPickupDocumentSaveDir;               //~1ak0I~
        Alert.simpleAlertDialog(this/*callback AlertI*/,null/*title*/,Utils.getStr(msgid,saveDirTop),flag);//~1ak0R~
        swAlertShowing=true;                                       //~1ammR~
      }                                                            //~1ammR~
    }                                                              //~1ak0I~
    @Override	//AlertI                                           //~1ak0I~
    public int alertButtonAction(int Pbuttonid,int PitemPos)       //~1ak0I~
    {                                                              //~1ak0I~
    	if (Dump.Y) Dump.println(CN+"alertButtonAction buttonid="+Integer.toHexString(Pbuttonid)+",itempos="+PitemPos);//~1ak0R~
        swAlertShowing=false;                                      //~1ammR~
        if (Pbuttonid==Alert.BUTTON_POSITIVE)                      //~1ak0I~
    		startPicker();                                         //~1ak0I~
        else                                                       //~1ak0I~
          	UView.showToastLong(R.string.RequestPickupDocumentSaveDirCanceled);//~1ak0I~
    	return 1;                                                  //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void startPicker()                                     //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"startPicker");                //~1ak0R~
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);//~1ak0I~
        int flags=Intent.FLAG_GRANT_READ_URI_PERMISSION            //~1ak0I~
        		| Intent.FLAG_GRANT_WRITE_URI_PERMISSION           //~1ak0I~
				| Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;	//persistent thru device restart//~1ak0I~
        intent.addFlags(flags);                                    //~1ak0I~
        AG.aMain.startActivityForResult(intent,AG.ACTIVITY_REQUEST_SCOPED_OPEN_TREE);//~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	public void onActivityResult(int Prequest, int Presult, Intent Pdata)//~1ak0I~
    {                                                              //~1ak0I~
        try                                                        //~v107I~//~1ak0I~
        {                                                          //~v107I~//~1ak0I~
			activityResult(Prequest,Presult,Pdata);                //~1ak0I~
        }                                                          //~1ak0I~
        catch(Exception e)                                         //~v107I~//~1ak0I~
        {                                                          //~v107I~//~1ak0I~
            Dump.println(e,CN+"onActivityResult");                  //~v107R~//~1ak0R~
        }                                                          //~v107I~//~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void activityResult(int Prequest, int Presult, Intent Pdata)//~1ak0R~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"activityResult result="+Presult+",request="+Prequest);//~1ak0R~//~1amgR~
//      Pmain.onActivityResult(Presult,Prequest,Pdata);            //~1ak0R~
        switch(Prequest)                                           //~1ak0I~
        {                                                          //~1ak0I~
        case AG.ACTIVITY_REQUEST_SCOPED_OPEN_TREE:                                    //~1ak0I~
        	if (Presult== Activity.RESULT_OK) //-1                 //~1ak0R~
            {                                                      //~1ak0I~
            	Uri uri=Pdata.getData();                        //~1ak0I~
				initComp(uri);                                     //~1ak0I~
				treeOpened(uri);                              //~1ak0I~
            }                                                      //~1ak0I~
            else                                                   //~1ak0I~
            {                                                      //~1ak0I~
            	UView.showToast("System picker canceled");          //~1ak0I~
            }                                                      //~1ak0I~
            break;                                                 //~1ak0I~
        }                                                          //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void treeOpened(Uri Puri)                              //~1ak0I~
    {                                                              //~1ak0I~
                String nm=Puri.getPath();                          //~1ak0R~
                if (!nm.endsWith(saveDirTop))                      //~1ak0I~
                {                                                  //~1ak0I~
                    int pos=nm.lastIndexOf(":");                   //~1ak0I~
                    if (pos>=0)                                    //~1ak0I~
                    	nm=nm.substring(pos+1);                    //~1ak0I~
	            	UView.showToastLong(Utils.getStr(R.string.RequestPickupDocumentSaveDirInvalidSelection,nm,//~1ak0I~
                            saveDirTop));                          //~1ak0I~
//TODO test       	return;                                        //~1ak0R~
                }                                                  //~1ak0I~
			    if (Dump.Y) Dump.println(CN+"onActivityResult uri="+Puri+",path="+Puri.getPath()+",scheme="+Puri.getScheme());//~1ak0R~
//  		    File f=new File(Puri.getPath());                   //~1ak0R~//~1amgR~
//  		    if (Dump.Y) Dump.println(CN+"onActivityResult uri to file="+f.toString()+",path="+f.getPath()+",absolutePath="+f.getAbsolutePath());//~1ak0R~//~1amgR~
//TODO test     getPath2(Puri);                                    //~1ak0R~
//              getPath(Puri);                                     //~1ak0R~//~1amgR~
//              getTreeMember(Puri,FILTER_HISTORY);	//good performance//~1ak0R~//~1amgR~
                Prop.putPreference(PREFKEY_SAVE_DIR,Puri.toString());//~1ak0R~
                DocumentFile[] docs=listDir(uriSaveDir);	//slow, bad performance//~1ak0I~
//              if (docs.length>0)                                 //~1ak0I~//~1amgR~
//  	            readDocument(docs[0]);                         //~1ak0I~//~1amgR~
//              if (docs.length>1)                                 //~1ak0I~//~1amgR~
//                  readDocument(docs[1]);                         //~1ak0I~//~1amgR~
//              writeDocument(Puri,docs);                          //~1ak0R~
            if (false)           //TODO test                       //+1amgR~
            {                                                      //~1amgI~
	            writeDocument("Dump10.txt","Over"); //Test         //~1ak0R~//~1amgR~
	            readDocument("Dump10.txt");         //Test         //~1ak0I~//~1amgR~
//              createDocument(Puri);                              //~1ak0R~
	    		deleteMember(Puri,"Dump10.txt");    //Test         //~1amgR~
            }                                                      //~1amgI~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
//*TODO test,slow bad performance                                  //~1ak0I~
//********************************************************         //~1ak0I~
	public DocumentFile[] listDir(Uri Puri)                        //~1ak0R~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"listDir uri="+Puri);          //~1ak0R~
//      DocumentFile tree=DocumentFile.fromTreeUri(AG.context,Puri);//~1ak0R~
        Uri withFilter=Uri.withAppendedPath(Puri,"*.history");     //~1ak0R~
//  	Uri withFilter=getMemberUri(Puri,null,"*.history");        //~1ak0R~
		if (Dump.Y) Dump.println(CN+"listDir uri with filter="+withFilter);//~1ak0R~
        DocumentFile tree=DocumentFile.fromTreeUri(AG.context,withFilter);//~1ak0R~
        DocumentFile[] docs=tree.listFiles();                      //~1ak0I~
        for (DocumentFile doc:docs)                                //~1ak0I~
        {                                                          //~1ak0I~
		    if (Dump.Y) Dump.println(CN+"listDir doc="+doc.getName()+",lastModified="+Utils.getTimeStamp("yy.MM.dd-HH.mm.ss",doc.lastModified())+",uri="+doc.getUri());//~1ak0R~
        }                                                          //~1ak0I~
        return docs;                                               //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	public void readDocument(DocumentFile Pdoc)                    //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"readDocument Pdoc uri="+Pdoc.getUri());//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"readDocument canRead="+Pdoc.canRead()+",canWrite="+Pdoc.canWrite()+",name="+Pdoc.getName()+",type="+Pdoc.getType()+",isFile="+Pdoc.isFile());//~1ak0R~
        if (!Pdoc.isFile())                                        //~1ak0I~
        {                                                          //~1ak0I~
	    	if (Dump.Y) Dump.println(CN+"readDocument return it is not File");//~1ak0R~
        	return;                                                //~1ak0I~
        }                                                          //~1ak0I~
        int lineno=0;                                              //~1ak0I~
        try                                                        //~1ak0I~
        {                                                          //~1ak0I~
        	InputStream is=CR.openInputStream(Pdoc.getUri());      //~1ak0I~
        	InputStreamReader isr=new InputStreamReader(is);       //~1ak0I~
        	BufferedReader br=new BufferedReader(isr);             //~1ak0I~
            for (;;)                                               //~1ak0I~
            {                                                      //~1ak0I~
            	String line=br.readLine();                         //~1ak0I~
                if (line==null)                                    //~1ak0I~
                	break;                                         //~1ak0I~
                lineno++;                                          //~1ak0I~
			    if (Dump.Y) Dump.println(CN+"readDocument lineno="+lineno+"="+line);//~1ak0R~
            }                                                      //~1ak0I~
            br.close();                                            //~1ak0I~
        }                                                          //~1ak0I~
        catch(FileNotFoundException e)                             //~1ak0I~
        {                                                          //~1ak0I~
        	Dump.println(e,CN+"readDocument FileNotFound:"+Pdoc.getUri());//~1ak0R~
        }                                                          //~1ak0I~
        catch(IOException e)                                       //~1ak0I~
        {                                                          //~1ak0I~
        	Dump.println(e,CN+"readDocument IOException:"+Pdoc.getUri());//~1ak0R~
        }                                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"readDocument exit lineno="+lineno);//~1ak0R~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void writeDocument(Uri Puri,DocumentFile[] Pdocs)     //~1ak0R~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"writeDocument uri="+Puri+",memberCount="+Pdocs.length);//~1ak0R~
//      Uri memberUri=Uri.withAppendedPath(Puri,"%2Ftest1.txt");   //~1ak0R~
        DocumentFile doc0=null;                                    //~1ak0I~
		if (Pdocs.length>0)                                        //~1ak0I~
        	doc0=Pdocs[0];                                         //~1ak0I~
//      Uri memberUri=getMemberUri(Puri,doc0,"Dump.txt");          //~1ak0R~
        Uri memberUri=getMemberUri(Puri,"Dump.txt");               //~1ak0I~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,memberUri);//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"writeDocument canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile());//~1ak0R~
        String data="testData";                                    //~1ak0I~
	    writeDocument(memberUri,data);                             //~1ak0I~
		readDocument(doc);                                         //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private Uri getMemberUri(Uri Puri/*tree*/,DocumentFile Pdoc0,String Pmember)//~1ak0R~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+Puri+",member="+Pmember+",member1="+Utils.toString(Pdoc0));//~1ak0R~
	    Uri memberUri;                                             //~1ak0I~
        if (Pdoc0!=null)                                           //~1ak0I~
        {                                                          //~1ak0I~
        	String content0=Pdoc0.getUri().toString();             //~1ak0I~
        	String strUri=Pdoc0.getUri().toString();               //~1ak0I~
        	String name=Pdoc0.getName();                           //~1ak0I~
            int pos=strUri.lastIndexOf(name);                      //~1ak0I~
            String strUriNew=strUri.substring(0,pos)+Pmember;      //~1ak0I~
	    	if (Dump.Y) Dump.println(CN+"getMemberUri strUri="+strUri+",name="+name+",pos="+pos);//~1ak0R~
	        memberUri=Uri.parse(strUriNew);                    //~1ak0I~
        }                                                          //~1ak0I~
        else                                                       //~1ak0I~
        {                                                          //~1ak0I~
        	String parent=Puri.toString();                         //~1ak0R~
        	int pos=parent.indexOf("/tree/");                      //~1ak0R~
        	String member=parent+"/document/"+parent.substring(pos+6)+SEP_MEMBER+Pmember;//~1ak0R~
	        memberUri=Uri.parse(member);                       //~1ak0I~
        }                                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+memberUri);//~1ak0R~
        return memberUri;                                                //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private ArrayList<TreeMember> getTreeMember(Uri Puri, String Pfilter)//~1ak0R~
    {                                                              //~1ak0I~
    	int ctrLine=0;                                             //~1ak0I~
 	    if (Dump.Y) Dump.println(CN+"getTreeMember uri="+Puri+",getPath="+Puri.getPath()+",filetr="+Pfilter);//~1ak0R~
        String[]                                                   //~1ak0I~
        columns={DocumentsContract.Document.COLUMN_DOCUMENT_ID,    //~1ak0I~
                 DocumentsContract.Document.COLUMN_DISPLAY_NAME,   //~1ak0I~
                 DocumentsContract.Document.COLUMN_LAST_MODIFIED,  //~1ak0I~
                 DocumentsContract.Document.COLUMN_SIZE,           //~1ak0I~
                 };                                                //~1ak0I~
        int[] idxCol=new int[columns.length];                      //~1ak0I~
        Uri uriQuery=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~1ak0I~
        Cursor cursor=CR.query(uriQuery,columns,null,null,null);   //~1ak0I~
        if (Dump.Y) Dump.println(CN+"getTreeMember uriQuery="+uriQuery+",cursor="+Utils.toString(cursor));//~1ak0R~
//      int ctrSelected=0;                                         //~1ak0I~
        ArrayList<TreeMember> members=new ArrayList<TreeMember>(0/*initial ctr*/);//~1ak0I~
        if (cursor!=null && cursor.moveToFirst())                  //~1ak0I~
        {                                                          //~1ak0I~
        	for (int ii=0;ii<columns.length;ii++)                  //~1ak0I~
            {                                                      //~1ak0I~
            	idxCol[ii]=cursor.getColumnIndex(columns[ii]);     //~1ak0I~
		        if (Dump.Y) Dump.println(CN+"getTreeMember colidx ii="+ii+",col="+columns[ii]+",idx="+idxCol[ii]);//~1ak0R~
            }                                                      //~1ak0I~
            ctrLine=cursor.getCount();                             //~1ak0I~
//      	members=new TreeMember[ctrLine];                       //~1ak0R~
        	members=new ArrayList<TreeMember>(ctrLine);            //~1ak0I~
		    if (Dump.Y) Dump.println(CN+"getTreeMember ctrLine="+ctrLine);//~1ak0R~
            for (int ii=0;ii<ctrLine;ii++)                         //~1ak0I~
            {                                                      //~1ak0I~
//              for (int jj=0;jj<idxCol.length;jj++)               //~1ak0I~
//              {                                                  //~1ak0I~
//              	if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",jj="+jj+",idx="+idxCol[jj]+",value="+columns[jj]+"="+cursor.getString(jj));//~1ak0R~
//              }                                                  //~1ak0I~
				String nm=cursor.getString(idxCol[1]);             //~1ak0I~
                if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",name="+nm);//~1ak0R~
                if (Pfilter==null || nm.endsWith(Pfilter))         //~1ak0R~
                {                                                  //~1ak0I~
//              	members[ctrSelected]=new TreeMember(nm,cursor.getLong(idxCol[2]),cursor.getLong(idxCol[3]));	//displayname,LastModified,size//~1ak0R~
                	TreeMember tm=new TreeMember(nm,cursor.getLong(idxCol[2]),cursor.getLong(idxCol[3]));	//displayname,LastModified,size//~1ak0R~
                	members.add(tm);                               //~1ak0I~
                	if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",TreeMember="+tm.toString());//~1ak0R~
//                  ctrSelected++;                                 //~1ak0R~
                }                                                  //~1ak0I~
                cursor.moveToNext();                               //~1ak0I~
            }                                                      //~1ak0I~
//            if (ctrSelected!=ctrLine)                            //~1ak0R~
//            {                                                    //~1ak0R~
//                if (ctrSelected!=0)                              //~1ak0R~
//                {                                                //~1ak0R~
//                    TreeMember[] membersSelected=new TreeMember[ctrSelected];//~1ak0R~
//                    System.arraycopy(members,0,membersSelected,0,ctrSelected);//~1ak0R~
//                    members=membersSelected;                     //~1ak0R~
//                }                                                //~1ak0R~
//                else                                             //~1ak0R~
//                    members=null;                                //~1ak0R~
//            }                                                    //~1ak0R~
        }                                                          //~1ak0I~
		if (Dump.Y) Dump.println(CN+"getTreeMember exit members size="+members.size());//~1ak0R~
        return members;                                            //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private TreeMember[] getTreeMember(Uri Puri)                   //~1ak0R~
    {                                                              //~1ak0I~
    	int ctrLine=0;                                             //~1ak0I~
        TreeMember[] members=null;                                 //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getTreeMember uri="+Puri+",getPath="+Puri.getPath());//~1ak0R~
        String[]                                       //~1ak0I~
        columns={DocumentsContract.Document.COLUMN_DOCUMENT_ID,    //~1ak0I~
                 DocumentsContract.Document.COLUMN_DISPLAY_NAME,   //~1ak0I~
                 DocumentsContract.Document.COLUMN_LAST_MODIFIED,  //~1ak0I~
                 DocumentsContract.Document.COLUMN_SIZE,           //~1ak0I~
                 };                                                //~1ak0I~
        int[] idxCol=new int[columns.length];                      //~1ak0I~
        Uri uriQuery=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~1ak0I~
        Cursor cursor=CR.query(uriQuery,columns,null,null,null);   //~1ak0I~
        if (Dump.Y) Dump.println(CN+"getTreeMember uriQuery="+uriQuery+",cursor="+Utils.toString(cursor));//~1ak0R~
        if (cursor!=null && cursor.moveToFirst())                  //~1ak0I~
        {                                                          //~1ak0I~
        	for (int ii=0;ii<columns.length;ii++)                  //~1ak0I~
            {                                                      //~1ak0I~
            	idxCol[ii]=cursor.getColumnIndex(columns[ii]);	   //~1ak0I~
		        if (Dump.Y) Dump.println(CN+"getTreeMember colidx ii="+ii+",col="+columns[ii]+",idx="+idxCol[ii]);//~1ak0R~
            }                                                      //~1ak0I~
            ctrLine=cursor.getCount();                             //~1ak0I~
        	members=new TreeMember[ctrLine];                       //~1ak0I~
		    if (Dump.Y) Dump.println(CN+"getTreeMember ctrLine="+ctrLine);//~1ak0R~
            for (int ii=0;ii<ctrLine;ii++)                         //~1ak0I~
            {                                                      //~1ak0I~
//              for (int jj=0;jj<idxCol.length;jj++)               //~1ak0R~
//              {                                                  //~1ak0R~
//              	if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",jj="+jj+",idx="+idxCol[jj]+",value="+columns[jj]+"="+cursor.getString(jj));//~1ak0R~
//              }                                                  //~1ak0R~
                members[ii]=new TreeMember(cursor.getString(idxCol[1]),cursor.getLong(idxCol[2]),cursor.getLong(idxCol[3]));	//displayname,LastModified,size//~1ak0I~
                if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",TreeMember="+members[ii].toString());//~1ak0R~
                cursor.moveToNext();                               //~1ak0I~
            }                                                      //~1ak0I~
        }                                                          //~1ak0I~
		if (Dump.Y) Dump.println(CN+"getTreeMember exit members="+Utils.toString(members));//~1ak0R~
        return members;                                            //~1ak0R~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private String getPath(Uri Puri)                               //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getPath uri="+Puri+",getPath="+Puri.getPath());//~1ak0R~
        File f=new File(Puri.getPath());                           //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getPath f="+f.toString());    //~1ak0R~
	    if (Dump.Y) Dump.println(CN+"getPath file="+f.getPath()+",abs="+f.getAbsolutePath());//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"getPath exist="+f.exists()+",name="+f.getName()+",isFile="+f.isFile()+",isDir="+f.isDirectory()+",isAbsolute="+f.isAbsolute());//~1ak0R~
 //       f=Puri.toFile();                                           //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getPath uri.toFile exist="+f.exists()+",name="+f.getName()+",isFile="+f.isFile()+",isDir="+f.isDirectory()+",isAbsolute="+f.isAbsolute());//~1ak0R~
                                                                   //~1ak0I~
		try                                                        //~1ak0I~
        {                                                          //~1ak0I~
		    if (Dump.Y) Dump.println(CN+"getPath canonicalPath="+f.getCanonicalPath());//~1ak0R~
        }                                                          //~1ak0I~
        catch (IOException e)                                      //~1ak0I~
        {                                                          //~1ak0I~
	    	Dump.println(CN+"getPath canonicalPath");              //~1ak0R~
        }                                                          //~1ak0I~
        File[] listF;                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getPath list="+Utils.toString(f.list(null)));//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"getPath listFiles="+Utils.toString(f.listFiles()));//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"getPath listFiles="+Utils.toString(f.listRoots()));//~1ak0R~
	    listF=f.listRoots();                                       //~1ak0I~
        for (File lf:listF)                                        //~1ak0I~
		    if (Dump.Y) Dump.println(CN+"getPath listRoot="+lf+",path="+lf.getPath());//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"getPath toPath="+f.toPath()); //API26//~1ak0R~
        String path="";                                            //~1ak0I~
        if (isContentUri(Puri))                                    //~1ak0I~
        {                                                          //~1ak0I~
            String[] columns;                                      //~1ak0R~
//          columns={"_data"};                                     //~1ak0I~
//          columns=null;                                          //~1ak0R~
//          columns={DocumentsContract.Document.COLUMN_DISPLAY_NAME,//~1ak0R~
//                            DocumentsContract.Document.COLUMN_DOCUMENT_ID,//~1ak0R~
//                            DocumentsContract.Document.COLUMN_LAST_MODIFIED,//~1ak0R~
//                            };                                   //~1ak0R~
            columns=null;                                          //~1ak0I~
//          Cursor cursor=CR.query(Puri,columns,null,null,null);   //~1ak0R~
            Uri uriQuery=DocumentsContract.buildDocumentUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~1ak0R~
//  		Uri uriQuery=getMemberUri(Puri,null/*Pmember*/);       //~1ak0R~
            String auth=uriQuery.getAuthority();                    //~1ak0I~
			if (Dump.Y) Dump.println(CN+"getPath uriQuery="+uriQuery+",auth="+auth);//~1ak0R~
			if (Dump.Y) Dump.println(CN+"getPath buildDocumentUriUsingTree getDocumentId="+DocumentsContract.getDocumentId(uriQuery));//~1ak0R~
//  		if (Dump.Y) Dump.println(CN+"getPath Puri getDocumentId="+DocumentsContract.getDocumentId(Puri));//~1ak0R~
            Cursor cursor=CR.query(uriQuery,columns,null,null,null);//~1ak0I~
			if (Dump.Y) Dump.println(CN+"getPath cursor="+Utils.toString(cursor));//~1ak0R~
            String parentDocID=null;                               //~1ak0I~
            if (cursor!=null && cursor.moveToFirst())              //~1ak0I~
            {                                                      //~1ak0I~
                int idx;                                           //~1ak0I~
//  			idx=cursor.getColumnIndex(columns[0]);             //~1ak0R~
				int ctr=cursor.getColumnCount();                   //~1ak0I~
				if (Dump.Y) Dump.println(CN+"getPath colmnNames="+Utils.toString(cursor.getColumnNames()));//~1ak0R~
                                                                   //~1ak0I~
    			for (idx=0;idx<ctr;idx++)                          //~1ak0I~
                	if (!cursor.isNull(idx))                       //~1ak0R~
						if (Dump.Y) Dump.println(CN+"getPath idx="+idx+",column="+cursor.getColumnName(idx)+",value="+cursor.getString(idx));//~1ak0R~
				if (Dump.Y) Dump.println(CN+"getPath  cursor.getCount="+cursor.getCount());//~1ak0R~
				parentDocID=cursor.getString(0);	               //~1ak0R~
	            cursor.close();                                    //~1ak0I~
            }                                                      //~1ak0I~
//          Uri uriChild=DocumentsContract.buildChildDocumentsUri(auth,parentDocID);//~1ak0R~
//          cursor=CR.query(uriChild,columns,null,null,null);      //~1ak0R~
//good performance                                                 //~1ak0I~
            Uri uriChild=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~1ak0I~
			if (Dump.Y) Dump.println(CN+"getPath uriChild="+uriQuery);//~1ak0R~
            cursor=CR.query(uriChild,columns,null,null,null);      //~1ak0I~
            if (cursor!=null && cursor.moveToFirst())              //~1ak0I~
            {                                                      //~1ak0I~
                if (Dump.Y) Dump.println(CN+"getPath  child cursor.getCount="+cursor.getCount());//~1ak0R~
                int ctr=cursor.getColumnCount();                   //~1ak0I~
                if (Dump.Y) Dump.println(CN+"getPath colmnNames="+Utils.toString(cursor.getColumnNames()));//~1ak0R~
                for (int ii=0;ii<cursor.getCount();ii++)           //~1ak0I~
                {                                                  //~1ak0I~
    				for (int idx=0;idx<ctr;idx++)                      //~1ak0I~
                		if (!cursor.isNull(idx))                   //~1ak0I~
							if (Dump.Y) Dump.println(CN+"getPath idx="+idx+",column="+cursor.getColumnName(idx)+",value="+cursor.getString(idx));//~1ak0R~
//                  if (Dump.Y) Dump.println(CN+"getPath ii="+ii+",column[0]="+cursor.getColumnName(0)+",value="+cursor.getString(0));//~1ak0R~
                    cursor.moveToNext();                           //~1ak0I~
                }                                                  //~1ak0I~
            }                                                      //~1ak0I~
        }                                                          //~1ak0I~
		if (Dump.Y) Dump.println(CN+"getPath path="+path);         //~1ak0R~
        return path;
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private String getPath2(Uri Puri)                              //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getPath uri="+Puri);          //~1ak0R~
        String path="";                                            //~1ak0I~
        if (isContentUri(Puri))                                    //~1ak0I~
        {                                                          //~1ak0I~
            String[] columns=null;                                 //~1ak0I~
            Cursor csr=CR.query(Puri,columns,null,null,null);      //~1ak0I~
			if (Dump.Y) Dump.println(CN+"getPath2 colomns="+ Utils.toString(csr.getColumnNames()));//~1ak0R~
			int ctr=csr.getColumnCount();                          //~1ak0I~
            while(csr.moveToNext())                             //~1ak0I~
            {                                                      //~1ak0I~
            	for (int ii=0;ii<ctr;ii++)                         //~1ak0I~
                	if (Dump.Y) Dump.println(CN+"getPath2 getString("+ii+")="+csr.getString(ii));//~1ak0R~
            	                                                   //~1ak0I~
            }                                                      //~1ak0I~
            csr.close();                                        //~1ak0I~
        }                                                          //~1ak0I~
		if (Dump.Y) Dump.println(CN+"getPath2 path="+path);        //~1ak0R~
        return path;                                               //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private boolean isDocumentUir(Uri Puri)                        //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isDocumentUri uri="+Puri);    //~1ak0R~
        boolean rc= DocumentFile.isDocumentUri(AG.context,Puri); //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isDocumentUri rc="+rc);       //~1ak0R~
        return rc;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private boolean isContentUri(Uri Puri)                         //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isContentUri uri="+Puri);     //~1ak0R~
        String scheme=Puri.getScheme();                            //~1ak0I~
        boolean rc="content".equalsIgnoreCase(scheme);             //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isContentUri rc="+rc+",scheme="+scheme);//~1ak0R~
        return rc;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private boolean isFileUri(Uri Puri)                            //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isFileUri uri="+Puri);        //~1ak0R~
        String scheme=Puri.getScheme();                            //~1ak0I~
        boolean rc="file".equalsIgnoreCase(scheme);                //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isFileUri rc="+rc+",scheme="+scheme);//~1ak0R~
        return rc;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
    public class TreeMember                                        //~1ak0R~
    {                                                              //~1ak0I~
        String name; long size;                                    //~1ak0I~
        String lastModified;                                       //~1ak0I~
        public TreeMember(String Pname,long PlastModified,long Psize)//~1ak0I~
        {                                                          //~1ak0I~
        	name=Pname;  size=Psize;//~1ak0I~
            lastModified=Utils.getTimeStamp("yy.MM.dd-HH.mm.ss",PlastModified);//~1ak0I~
        }                                                          //~1ak0I~
        public String toString()                                          //~1ak0I~
        {                                                          //~1ak0I~
        	return "namd="+name+",size="+size+",lastModified="+lastModified;//~1ak0R~
        }                                                          //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
//********************************************************         //~1ak0I~
//********************************************************         //~1ak0I~
    private static UScoped getInstance()                           //~1ak0R~
    {                                                              //~1ak0I~
        UScoped us=AG.aUScoped;                                  //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getInstance AG.aUScoped="+Utils.toString(us));//~1ak0R~
        if (us==null)                                              //~1ak0I~
        	us=new UScoped();                                      //~1ak0I~
        return us;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
    public static boolean isSDMounted()                            //~1ak0R~
    {                                                              //~1ak0I~
        UScoped us=getInstance();                                  //~1ak0R~
        boolean rc=us.chkDocumentSaveDir();                         //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"isSDMounted rc="+rc);         //~1ak0R~
        return rc;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
//AMain-->UFile                                                    //~1ak0I~
//********************************************************         //~1ak0I~
    public static boolean chkWritableSD()                          //~1ak0I~
    {                                                              //~1ak0I~
        UScoped us=getInstance();                                  //~1ak0I~
        boolean rc=us.chkDocumentSaveDir();                         //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"chkWritableSD rc="+rc);       //~1ak0R~
        return rc;
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
//*return uri.toString()                                           //~1ak0I~
//********************************************************         //~1ak0I~
    public static String getSDPath(String Pfnm)                    //~1ak0R~
    {                                                              //~1ak0I~
        UScoped us=getInstance();                                  //~1ak0I~
        String strUri=us.getMemberPath(Pfnm);                           //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getSDPath fnm="+Pfnm+",path="+strUri);//~1ak0R~
        return strUri;
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
    public static OutputStream openOutputSD(String Pmember,boolean PswDeleteExisting)//~1ak0R~
    {                                                              //~1ak0I~
        UScoped us=getInstance();                                  //~1ak0I~
        Uri uri=us.uriSaveDir;                                     //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openOutputSD uriSaveDir="+Utils.toString(uri));//~1ak0R~
        if (uri==null)                                             //~1ak0I~
        	return null;                                           //~1ak0I~
        Uri memberUri=us.getMemberUri(uri,Pmember);                   //~1ak0I~
        OutputStream os=us.openOutputDocument(memberUri,PswDeleteExisting);//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"openOutputSD rc="+Utils.toString(os));//~1ak0R~
        return os;                                                 //~1ak0R~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
    public static InputStream openInputSD(String Pmember)          //~1ak0I~
    {                                                              //~1ak0I~
        UScoped us=getInstance();                                  //~1ak0I~
        Uri uri=us.uriSaveDir;                                     //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openInputSD uriSaveDir="+Utils.toString(uri));//~1ak0R~
        if (uri==null)                                             //~1ak0I~
        	return null;                                           //~1ak0I~
        Uri memberUri=us.getMemberUri(uri,Pmember);                //~1ak0I~
        InputStream is=us.openInputDocument(memberUri);            //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openInputSD rc="+Utils.toString(is));//~1ak0R~
        return is;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0M~
//** rc :true yet confirmed, false:requested confirm               //~1ak0M~
//********************************************************         //~1ak0M~
	public boolean chkDocumentSaveDir()                            //~1ak0M~
    {                                                              //~1401I~//~v@@@R~//~1ak0M~
    	boolean rc=false;                                          //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir");         //~1ak0R~
	    String strUri= Prop.getPreference(PREFKEY_SAVE_DIR,"");    //~1ak0M~
		if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir getPreference strUri="+strUri);//~1ak0R~
        if (strUri.compareTo("")==0)	//1st time                 //~1ak0M~
        {                                                          //~1ak0M~
			requestDocumentTree(true/*swInstall*/);                //~1ak0R~
        }                                                          //~1ak0M~
        else                                                       //~1ak0M~
        {                                                          //~1ak0M~
        	initComp(strUri);                                      //~1ak0R~
		    if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir uriSaveDir="+uriSaveDir);//~1ak0R~
            getTreeMember(uriSaveDir,FILTER_HISTORY);	//using query//~1ak0R~
			treeOpened(uriSaveDir);                                //~1ak0I~
            rc=true;                                               //~1ak0M~
        }                                                          //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir rc="+rc);  //~1ak0R~
        return rc;
    }                                                              //~1ak0M~
//********************************************************         //~1ak0I~
	private String getMemberPath(String Pmember)                   //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getMemberPath uri="+Utils.toString(uriSaveDir)+",member="+Pmember);//~1ak0R~
        if (uriSaveDir==null)                                      //~1ak0I~
        	return null;                                           //~1ak0I~
        Uri uri=getMemberUri(uriSaveDir,Pmember);                  //~1ak0I~
        String strUri=uri.toString();                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"getMemberPath rc="+strUri);   //~1ak0R~
        return strUri;                                             //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0M~
	private Uri getMemberUri(Uri Puri/*tree*/,String Pmember)      //~1ak0M~
    {                                                              //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+Puri+",member="+Pmember);//~1ak0R~
        Uri uri=DocumentsContract.buildDocumentUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~1ak0M~
        Uri memberUri=uri;                                         //~1ak0M~
        if (Pmember!=null)                                         //~1ak0M~
        {                                                          //~1ak0M~
	        String strUri=uri.toString()+SEP_MEMBER+Pmember;       //~1ak0R~
	    	memberUri=Uri.parse(strUri);                           //~1ak0M~
        }                                                          //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+memberUri);//~1ak0R~
        return memberUri;                                          //~1ak0M~
    }                                                              //~1ak0M~
//********************************************************         //~1amgR~
	private void deleteMember(Uri Puri,String Pmember)             //~1amgR~
    {                                                              //~1amgR~
	    if (Dump.Y) Dump.println(CN+"deleteDocument uri="+Puri+",member="+Pmember);//~1amgR~
        Uri uri=getMemberUri(Puri/*tree*/,Pmember);                //~1amgR~
		deleteDocument(uri);                                       //~1amgR~
    }                                                              //~1amgR~
//********************************************************         //~1amgR~
	private void deleteDocument(Uri Puri)                          //~1amgR~
    {                                                              //~1amgR~
	    if (Dump.Y) Dump.println(CN+"deleteDocument uri="+Puri);   //~1amgR~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,Puri);//~1amgR~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument member uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile()+",exists="+doc.exists()+",length="+doc.length());//~1amgR~
        if (doc.exists())                                          //~1amgR~
        {                                                          //~1amgR~
		    if (Dump.Y) Dump.println(CN+"openOutputDocument delete exist uri="+doc.getUri());//~1amgR~
	        doc.delete();                                          //~1amgR~
        }                                                          //~1amgR~
    }                                                              //~1amgR~
//********************************************************         //~1ak0M~
	private OutputStream openOutputDocument(Uri Puri,boolean PswDeleteExisting)//~1ak0R~
    {                                                              //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument swDeleteExisting="+PswDeleteExisting+",uri="+Puri);//~1ak0R~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,Puri);//~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument member uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile()+",exists="+doc.exists()+",length="+doc.length());//~1ak0R~
        if (doc.exists())                                          //~1ak0M~
        {                                                          //~1ak0M~
            if (PswDeleteExisting)  //if not delete ...(1) is created//~1ak0R~
            {                                                      //~1ak0I~
		    	if (Dump.Y) Dump.println(CN+"openOutputDocument delete exist uri="+doc.getUri());//~1ak0R~
	        	doc.delete();                                      //~1ak0R~
            }                                                      //~1ak0I~
        }                                                          //~1ak0M~
        String strUri=Puri.toString();                               //~1ak0I~
        String name=strUri.substring(strUri.lastIndexOf(SEP_MEMBER)+SEP_MEMBER.length());//~1ak0I~
        doc=docDir.createFile("text/plain",name);                  //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument after create uri="+doc.getUri()+",exists="+doc.exists()+",length="+doc.length()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile());//~1ak0R~
        OutputStream os=openOutputStream(Puri);                    //~1ak0R~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument create uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile());//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument os="+Utils.toString(os));//~1ak0R~
        return os;                                                //~1ak0R~
    }                                                              //~1ak0M~
//********************************************************         //~1ak0I~
	private InputStream openInputDocument(Uri Puri)                //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocument uri="+Puri);//~1ak0R~
        InputStream is=null;                                       //~1ak0I~
        try                                                        //~1ak0I~
        {                                                          //~1ak0I~
        	is=CR.openInputStream(Puri);                           //~1ak0R~
        }                                                          //~1ak0I~
        catch(FileNotFoundException e)                             //~1ak0I~
        {                                                          //~1ak0I~
        	Dump.println(e,CN+"openInputDocument FileNotFound:"+Puri);//~1ak0R~
        }                                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocument is="+Utils.toString(is));//~1ak0R~
        return is;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private OutputStream openOutputStream(Uri Puri)                //~1ak0R~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openOutputStream uri="+Puri); //~1ak0R~
        OutputStream os=null;                                      //~1ak0I~
        try                                                        //~1ak0I~
        {                                                          //~1ak0I~
        	os=CR.openOutputStream(Puri);                          //~1ak0I~
        }                                                          //~1ak0I~
        catch(FileNotFoundException e)                             //~1ak0I~
        {                                                          //~1ak0I~
        	Dump.println(e,CN+"openOutputStream openOutputStream FileNotFound:"+Puri);//~1ak0R~
        }                                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument os="+Utils.toString(os));//~1ak0R~
        return os;                                                 //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void createDocument(Uri Puri/*tree*/)                  //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"createDocument uri="+Puri);   //~1ak0R~
        DocumentFile tree=DocumentFile.fromTreeUri(AG.context,Puri);//~1ak0I~
        String name="Dump5.txt";                                   //~1ak0I~
        Uri memberUri=getMemberUri(Puri,name);                     //~1ak0I~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,memberUri);//~1ak0I~
	    if (Dump.Y) Dump.println(CN+"createDocument member uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile()+",exists="+doc.exists());//~1ak0R~
        if (doc.exists())                                          //~1ak0I~
        {                                                          //~1ak0I~
		    if (Dump.Y) Dump.println(CN+"createDocument delete exist uri="+doc.getUri());//~1ak0R~
        	doc.delete();                                          //~1ak0I~
        }                                                          //~1ak0I~
        else                                                       //~1ak0I~
        {                                                          //~1ak0R~
		    if (Dump.Y) Dump.println(CN+"createDocument not found uri="+doc.getUri());//~1ak0R~
	    }                                                          //~1ak0I~
        doc=tree.createFile("text/plain",name);                    //~1ak0I~
        if (Dump.Y) Dump.println(CN+"createDocument create uri="+doc.getUri());//~1ak0R~
	    if (Dump.Y) Dump.println(CN+"createDocument uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile());//~1ak0R~
//      String data="D3\nD4\nD5";   //todo test override           //~1ak0I~
        String data="D6";   //todo test override                   //~1ak0I~
	    writeDocument(name,data);                                  //~1ak0R~
	    writeDocument(doc.getUri(),data);                          //~1ak0I~
		readDocument(doc);                                         //~1ak0I~
    }                                                              //~1ak0I~
//********************************************************         //~1ak0I~
	private void writeDocument(String Pmember,String Pdata)        //~1ak0I~
    {                                                              //~1ak0I~
        if (Dump.Y) Dump.println(CN+"writeDocument member=" + Pmember + ",data=" + Pdata);//~1ak0R~
//      OutputStream os = openOutputSD(Pmember,false/*delete existing*/);//if false ...(1) is created//~1ak0R~
        OutputStream os = openOutputSD(Pmember,true/*delete existing*/);//~1ak0I~
        if (os == null)                                          //~1ak0I~
            return;                                            //~1ak0I~
        OutputStreamWriter osw=null;                               //~1ak0I~
      	try                                                        //~1ak0I~
      	{                                                          //~1ak0I~
        	osw = new OutputStreamWriter(os, "UTF-8");             //~1ak0R~
        }                                                          //~1ak0M~
        catch(UnsupportedEncodingException e)                      //~1ak0M~
        {                                                          //~1ak0M~
            Dump.println(e,"CN+writeDocument FileNotFound:"+Pmember);//~1ak0R~
        }                                                          //~1ak0M~
        if (osw!=null)                                             //~1ak0I~
        {                                                          //~1ak0I~
            try                                                    //~1ak0I~
            {                                                      //~1ak0R~
                PrintWriter pw = new PrintWriter(osw, true/*autoFlush*/);//~1ak0R~
                pw.print(Pdata);                                   //~1ak0R~
                pw.close();                                        //~1ak0R~
            }                                                      //~1ak0R~
            catch(Exception e)                                     //~1ak0R~
            {                                                      //~1ak0R~
                Dump.println(e,CN+"writeDocument IOException:"+Pmember);//~1ak0R~
            }                                                      //~1ak0R~
        }                                                          //~1ak0I~
        if (Dump.Y) Dump.println(CN+"writeDocument exit");         //~1ak0R~
    }//~1ak0I~
//********************************************************         //~1ak0M~
	private void writeDocument(Uri Puri,String Pdata)              //~1ak0M~
    {                                                              //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"writeDocument uri="+Puri+",data="+Pdata);//~1ak0R~
        try                                                        //~1ak0M~
        {                                                          //~1ak0M~
        	OutputStream os=CR.openOutputStream(Puri);             //~1ak0M~
			OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");//~1ak0M~
			PrintWriter pw=new PrintWriter(osw,true/*autoFlush*/);  //~1ak0M~
        	pw.print(Pdata);                                       //~1ak0M~
            pw.close();                                            //~1ak0M~
        }                                                          //~1ak0M~
        catch(FileNotFoundException e)                             //~1ak0M~
        {                                                          //~1ak0M~
        	Dump.println(e,CN+"writeDocument FileNotFound:"+Puri); //~1ak0R~
        }                                                          //~1ak0M~
        catch(IOException e)                                       //~1ak0M~
        {                                                          //~1ak0M~
        	Dump.println(e,CN+"writeDocument IOException:"+Puri);  //~1ak0R~
        }                                                          //~1ak0M~
	    if (Dump.Y) Dump.println(CN+"writeDocument exit");         //~1ak0R~
    }                                                              //~1ak0M~
//********************************************************         //~1ak0I~
	private void readDocument(String Pmember)                      //~1ak0I~
    {                                                              //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"readDocument Member="+Pmember);//~1ak0R~
        InputStream is=openInputSD(Pmember);                       //~1ak0I~
        if (is == null)                                            //~1ak0I~
            return;                                                //~1ak0I~
        int lineno=0;                                              //~1ak0I~
        try                                                        //~1ak0I~
        {                                                          //~1ak0I~
        	InputStreamReader isr=new InputStreamReader(is);       //~1ak0I~
        	BufferedReader br=new BufferedReader(isr);             //~1ak0I~
            for (;;)                                               //~1ak0I~
            {                                                      //~1ak0I~
            	String line=br.readLine();                         //~1ak0I~
                if (line==null)                                    //~1ak0I~
                	break;                                         //~1ak0I~
                lineno++;                                          //~1ak0I~
			    if (Dump.Y) Dump.println(CN+"readDocument lineno="+lineno+"="+line);//~1ak0R~
            }                                                      //~1ak0I~
            br.close();                                            //~1ak0I~
        }                                                          //~1ak0I~
        catch(FileNotFoundException e)                             //~1ak0I~
        {                                                          //~1ak0I~
        	Dump.println(e,CN+"readDocument FileNotFound:"+saveDirTop+"/"+Pmember);//~1ak0R~
        }                                                          //~1ak0I~
        catch(IOException e)                                       //~1ak0I~
        {                                                          //~1ak0I~
        	Dump.println(e,CN+"readDocument IOException:"+saveDirTop+"/"+Pmember);//~1ak0R~
        }                                                          //~1ak0I~
	    if (Dump.Y) Dump.println(CN+"readDocument exit lineno="+lineno);//~1ak0R~
    }                                                              //~1ak0I~
}//class                                                           //~1110I~
