//*CID://+1A51R~: update#= 228;                                    //~1A51R~
//**********************************************************************//~1107I~
//1A51 2014/10/03 actionBar as alternative of menu button for api>=11//~1A51I~
//1091:121124 Menubar list OutOfBoundsException                    //~v109I~
//1078:121208 add "menu" to option menu if high resolusion         //~v107I~
//1076:121208 drop debugtrace menu item if release version         //~v107I~
//V104:121109 onContextMenuItemClosed sheduled before submenu display,NPE abend//~v104I~
//**********************************************************************//~v104I~
//*Menu                                                            //~v104R~
//**********************************************************************//~1107I~
package com.Ahsv;                                         //~1107R~  //~1108R~//~1109R~//~@@@@R~

import jagoclient.Dump;
import jagoclient.gui.DoActionListener;
import jagoclient.dialogs.SayDialog;

import com.Ahsv.awt.MenuBar;                                       //~@@@@R~
import com.Ahsv.Alert;                                             //~@@@@R~
import com.Ahsv.AG;                                                //~@@@@R~
import com.Ahsv.R;                                                 //+1A51R~


import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//**********************************************************************//~1107I~
public class AMenu                                             //~1310R~//~@@@@R~
		 implements AlertI,  DoActionListener                 //~1507R~//~@@@@R~
{                                                                  //~0914I~
//	static private ArrayList<MenuBar> menubarlist;                 //~1425R~
	public View menuRegisteredView;                                //~1425R~
//	private boolean swPopupSubmenu;                                //~1425R~
//	private boolean swShowRequest;                                 //~1427I~
//    private boolean listerPopup;                                   //~1425R~
//    private MenuBar currentMenuBar;                                //~1425R~
                                                                   //~1314I~
	private static String []menuDesc;                              //~1425R~
    public static final int  MENU_STOP=0;                         //~1314R~//~1326R~
//  public static final int  MENU_CLOSE=1;                         //~1326I~//~@@@@R~
//  public static final int  MENU_HELP=2;                          //~1314I~//~1412R~//~@@@@R~
//    public static final int  MENU_CTR=3;                           //~1314I~//~1412R~//~v107R~
//  public static final int  MENU_MENU=3;                          //~v107I~//~@@@@R~
//  public static final int  MENU_CTR=4;                           //~v107I~//~@@@@R~
//  public static final int  MENUMENU_SIZE=700;                    //~v107R~//~@@@@R~
    public static final int  MENU_CANCEL=1;                        //~@@@@R~
    public static final int  MENU_MSG=2;                           //~@@@@I~
    public static final int  MENU_CTR=3;                           //~@@@@R~
                                                                   //~1411I~
//    private static final String HELPTEXT_AJAGOC="aboutAhsv";       //~@@@@R~
//  private static String HELPITEM_AJAGOC="About Android Version";//~1411I~//~v107R~
//    private static String HELPITEM_AJAGOC="Ahsv?";               //~v107I~//~@@@@R~
                                                                   //~1328I~
    public static final int  POPUPSUBMENUID=0x80;                  //~1328I~
    public static final int  AJAGOHELPMENUID=0x7f00;               //~1411I~
	private static final int menuId[]={                                  //~1314I~//~v107R~
				MENU_STOP,                                        //~1314R~//~1326R~
//  			MENU_CLOSE,                                        //~1326I~//~@@@@R~
//              MENU_HELP,                                         //~1314I~//~@@@@R~
    			MENU_CANCEL,                                       //~@@@@I~
                MENU_MSG,                                          //~@@@@I~
//  			MENU_MENU,                                         //~v107I~//~@@@@R~
				MENU_CTR};                                         //~1314I~
	private static final int icons[]={                                   //~1314I~//~v107R~
								android.R.drawable.ic_delete,      //~1412R~
//  							android.R.drawable.ic_menu_close_clear_cancel,//~1404I~//~@@@@R~
//  							android.R.drawable.ic_menu_help,   //~1314I~//~@@@@R~
    							android.R.drawable.ic_menu_revert, //~@@@@I~
    							android.R.drawable.ic_dialog_email,//~@@@@I~
//  							R.drawable.ic_menu_moreoverflow,   //~v107R~//~@@@@R~
								0							};     //~1314I~
//******************                                               //~1121I~
	public AMenu()                                             //~1107R~//~@@@@R~
    {                                                              //~1107I~
//    	menubarlist=new ArrayList<MenuBar>();                      //~1121I~
    }                                                              //~1107I~
//*****************************                                               //~1121I~//~1122R~
//*from component:setMenu()-->menubar-->                           //~1524R~
//*****************************                                    //~1122I~
    public void registerMenuBar(MenuBar Pmenubar)                  //~1123I~
    {                                                              //~1123I~
//        View view;                                                 //~1307I~//~@@@@R~
//    //*********                                                    //~1307I~//~@@@@R~
//        Frame frame=Pmenubar.frame;                                //~1125I~//~@@@@R~
//        if (Pmenubar.childView!=null)   //from Lister for Who/Games//~1411R~//~@@@@R~
//            view=Pmenubar.childView;                               //~1307R~//~@@@@R~
//        else                                                       //~1307I~//~@@@@R~
//        {                                                          //~1331I~//~@@@@R~
//            view=frame.framelayoutview;                                 //~1125I~//~1307R~//~@@@@R~
//            frame.contextMenuView=view; //used by showContextMenu from optionmenu                                //~1314I~//~1331R~//~@@@@R~
//        }                                                          //~1331I~//~@@@@R~
//        Pmenubar.relatedView=view;                                 //~1331I~//~@@@@R~
//        Pmenubar.seqno=menubarlist.size();                          //~1123I~//~@@@@R~
//        Pmenubar.frameid=view.getId();                             //~1125I~//~@@@@R~
//        AG.activity.registerForContextMenu(view); //request callback onCreateContextMenu()//~1123I~//~1125R~//~@@@@R~
//        menubarlist.add(Pmenubar);                                 //~1123I~//~@@@@R~
//        if (Dump.Y) Dump.println("Ajagomenu:add menubar newctr="+menubarlist.size()+":"+((Object)Pmenubar).toString());//~1506R~//~@@@@R~
//        if (Dump.Y) Dump.println("Ajagomenu:register contextmenu frame="+frame.framename+",frameresourceid="+Integer.toHexString(frame.framelayoutresourceid)+//~1125I~//~1506R~//~@@@@R~
//                                ",registeredView id="+Integer.toString(view.getId())+//~1306R~//~@@@@R~
//                                "menubar.frameid="+Integer.toHexString(Pmenubar.frameid));//~1306I~//~1402R~//~@@@@R~
//        if (Dump.Y) Dump.println("Ajagomenu:register contextmenu view="+view.toString());//~1506R~//~@@@@R~
    }                                                              //~1123I~
//*****************************                                    //~1402I~
    public void removeMenuBar(MenuBar PmenuBar)                    //~1402I~
    {                                                              //~1402I~
//        int idx;                                                   //~v109I~//~@@@@R~
//    //*********                                                    //~1402I~//~@@@@R~
//        if (Dump.Y) Dump.println("Ajagomenu:remove menubar oldctr="+menubarlist.size()+":"+((Object)PmenuBar).toString());//~1506R~//~@@@@R~
////      menubarlist.remove(PmenuBar);                              //~1402I~//~v109R~//~@@@@R~
//        idx=PmenuBar.seqno;                                        //~v109I~//~@@@@R~
//        menubarlist.set(idx,null);                                 //~v109I~//~@@@@R~
//        if (Dump.Y) Dump.println("Ajagomenu:remove menubar newctr="+menubarlist.size());//~1506R~//~@@@@R~
    }                                                              //~1402I~
//******************                                               //~1121I~
//    private MenuBar findMenuBar(View Pview)                      //~1121I~//~@@@@R~
//    {                                                              //~1121I~//~@@@@R~
//        MenuBar menubar,menubarfound;                              //~1411R~//~@@@@R~
//        if (Pview instanceof TabHost)   //for Mainframe            //~1123I~//~@@@@R~
//            return menubarlist.get(0);                             //~1123I~//~@@@@R~
//        int viewid=Pview.getId();                                  //~1124I~//~@@@@R~
//        menubarfound=Window.getCurrentFrame().framemenubar;        //~1411R~//~@@@@R~
//        for (int ii=0;ii<menubarlist.size();ii++)                      //~1121I~//~@@@@R~
//        {                                                          //~1121I~//~@@@@R~
//            menubar=menubarlist.get(ii);                           //~1121I~//~@@@@R~
//            if (menubar==null)                                     //~v109I~//~@@@@R~
//                continue;                                          //~v109I~//~@@@@R~
//            if (Dump.Y) Dump.println("AMenu:findView viewId="+Integer.toHexString(viewid)+":menu frameid="+Integer.toHexString(menubar.frameid));//~1124I~//~1125R~//~1506R~//~@@@@R~
//            if (menubar.childView!=null)                           //~1411I~//~@@@@R~
//            {                                                      //~1411I~//~@@@@R~
//                if (menubar.childView==Pview)   //id is both Lister for Who and Games frame//~1411I~//~@@@@R~
//                {                                                  //~1411I~//~@@@@R~
//                    return menubar;                                //~1411I~//~@@@@R~
//                }                                                  //~1411I~//~@@@@R~
//            }                                                      //~1411I~//~@@@@R~
//        }                                                          //~1121I~//~@@@@R~
//        return menubarfound;                                       //~1411R~//~@@@@R~
//    }                                                              //~1121I~//~@@@@R~
////******************                                               //~1124I~//~@@@@R~
//    private MenuBar findMenuBar(int Pitemid)                       //~1124I~//~@@@@R~
//    {                                                              //~1124I~//~@@@@R~
//        int menubarid;                                             //~1124I~//~@@@@R~
//    //*********************                                        //~1124I~//~@@@@R~
//        menubarid=Pitemid>>24;                                     //~1124I~//~@@@@R~
//        if (menubarlist.size()<menubarid)                          //~v109R~//~@@@@R~
//            return null;                                           //~v109I~//~@@@@R~
//        return menubarlist.get(menubarid-1);                       //~1124I~//~@@@@R~
//    }                                                              //~1124I~//~@@@@R~
//*************************************************                //~1122R~
//*menu created at requested by long pressing                   //~1122I~//~v107R~
//*************************************************                //~1122I~
    public void onCreateContextMenu(ContextMenu Pmenu,View Pview,ContextMenuInfo Pinfo)//~1121I~
    {                                                                  //~1107I~//~1121R~
//        boolean swshow;                                            //~1427I~//~@@@@R~
//    //*************                                                //~1411I~//~@@@@R~
//        swPopupSubmenu=false;                                      //~1328I~//~@@@@R~
//        swshow=swShowRequest;                                      //~1427I~//~@@@@R~
//        swShowRequest=false;                                       //~1427I~//~@@@@R~
//        MenuBar menubar=findMenuBar(Pview);                      //~1121I~//~@@@@R~
//        if (menubar==null)                                         //~1121I~//~@@@@R~
//            return;                                                //~1121I~//~@@@@R~
//        if (Dump.Y) Dump.println("AMenu:onCreateContextMenu View="+((Object)Pview).toString()+",layoutid="+Integer.toHexString(menubar.frame.framelayoutresourceid));                                       //~1306I~//~1506R~//~@@@@R~
//        if (menubar.popupSubmenu!=null) //show submenu             //~1328I~//~@@@@R~
//        {                                                          //~1328I~//~@@@@R~
//            com.Ahsv.awt.Menu submenu=menubar.popupSubmenu;      //~1328I~//~@@@@R~
//            menubar.popupSubmenu=null;  //accepted                 //~1328I~//~@@@@R~
//            createPopupSubmenu(Pmenu,menubar,submenu);             //~1328I~//~@@@@R~
//        }                                                          //~1328I~//~@@@@R~
//        else                                                       //~1328I~//~@@@@R~
//        if (menubar.childView!=null)                               //~1307R~//~@@@@R~
//            createListerPopupMenu(Pmenu,menubar,Pinfo);             //~1306I~//~1307R~//~@@@@R~
//        else                                                       //~1306I~//~@@@@R~
//        {                                                          //~1307I~//~@@@@R~
//            if (listerPopup)  //when duplicaed register for child View and Layout//~1307I~//~1331R~//~@@@@R~
//                return;                                            //~1307I~//~@@@@R~
//            if (!swshow)                                           //~1427R~//~@@@@R~
//            {                                                      //~1427I~//~@@@@R~
//                Frame f=menubar.frame;                             //~1427I~//~@@@@R~
//                if (f!=null && f.isBoardFrame)                     //~1427I~//~@@@@R~
//                {                                                  //~1427I~//~@@@@R~
//                    if (Dump.Y) Dump.println("LongPress ContextMenu on Board frame");//~1506R~//~@@@@R~
//                    return; //ignore on board frame                //~1427I~//~@@@@R~
//                }                                                  //~1427I~//~@@@@R~
//            }                                                      //~1427I~//~@@@@R~
//            createMenu(Pmenu,menubar);                                 //~1123I~//~1306R~//~@@@@R~
//        }                                                          //~1307I~//~@@@@R~
//        currentMenuBar=menubar;                                    //~1307I~//~@@@@R~
    }                                                                  //~1107I~//~1121R~
////****************************************************             //~1123I~//~@@@@R~
    public boolean onContextItemSelected(MenuItem Pitem)           //~1121R~//~@@@@R~
    {                                                              //~1121I~//~@@@@R~
        boolean rc=true;                                           //~1121I~//~@@@@R~
//        Object awtitem;                                          //~@@@@R~
//        MenuBar menubar;                                           //~1307I~//~@@@@R~
//        //************************                                     //~1124I~//~@@@@R~
//        int itemid=Pitem.getItemId();                            //~@@@@R~
//        if (Dump.Y) Dump.println("AMenu:menubar itemid="+Integer.toHexString(itemid));//~1121R~     //~1123R~//~1124I~//~1506R~//~@@@@R~
//        menubar=findMenuBar(itemid);                                //~1307I~//~@@@@R~
//        if (menubar==null)  //internal logic err                   //~v109I~//~@@@@R~
//            return true;                                           //~v109I~//~@@@@R~
//        if ((itemid & AJAGOHELPMENUID)==AJAGOHELPMENUID)           //~1411I~//~@@@@R~
//        {                                                          //~1411I~//~@@@@R~
//            helpAhsv();                                          //~1411I~//~@@@@R~
//            return true;                                           //~1411I~//~@@@@R~
//        }                                                          //~1411I~//~@@@@R~
//        awtitem=findItem(menubar,itemid);                          //~1124R~//~1211R~//~1307R~//~@@@@R~
//        if ((itemid & 0xff)==POPUPSUBMENUID)    //3rd level submenu starter//~1328R~//~@@@@R~
//        {                                                          //~1211M~//~@@@@R~
//            if (Dump.Y) Dump.println("AMenu:Sub of Submenu");                  //~1211M~//~1506R~//~@@@@R~
//            popupSubmenu((com.Ahsv.awt.Menu)awtitem,itemid);            //~1211I~//~1328R~//~@@@@R~
//            showContextMenu();  //show submenu                     //~v104I~//~@@@@R~
//            return rc;                                             //~v104R~//~@@@@R~
//        }                                                          //~1211M~//~@@@@R~
//        try                                                        //~1309I~//~@@@@R~
//        {                                                          //~1309I~//~@@@@R~
//            rc=itemAction(menubar,Pitem,awtitem);                              //~1211R~//~1307R~//~1309R~//~@@@@R~
//        }                                                          //~1309I~//~@@@@R~
//        catch(Exception e)                                         //~1309I~//~@@@@R~
//        {                                                          //~1309I~//~@@@@R~
//            Dump.println(e,"AMenu:ContextItemnSelected Exception");       //~1309R~//~1402R~//~@@@@R~
//        }                                                          //~1309I~//~@@@@R~
        return rc;                                                 //~1121R~//~@@@@R~
    }                                                              //~1121I~//~@@@@R~
//****************************************************             //~1211I~
//    public boolean itemAction(MenuBar Pmenubar,MenuItem Pitem,Object Pawtitem)      //~1211R~//~1307R~//~@@@@R~
//    {                                                              //~1211I~//~@@@@R~
//        boolean rc=true;                                           //~1211I~//~@@@@R~
//        CheckboxMenuItem awtchkboxitem;                          //~@@@@R~
//    //************************                                     //~1211I~//~@@@@R~
//        if (Pitem.isCheckable())                                   //~1211R~//~@@@@R~
//        {                                                          //~1211I~//~@@@@R~
//            awtchkboxitem=(CheckboxMenuItem)Pawtitem;              //~1211R~//~@@@@R~
//            if (Pitem.isChecked())                                 //~1211R~//~@@@@R~
//            {                                                      //~1211I~//~@@@@R~
//                Pitem.setChecked(false);                           //~1211R~//~@@@@R~
//                awtchkboxitem.setState(false);                     //~1211R~//~@@@@R~
//            }                                                      //~1211I~//~@@@@R~
//            else                                                   //~1211I~//~@@@@R~
//            {                                                      //~1211I~//~@@@@R~
//                Pitem.setChecked(true);                            //~1211R~//~@@@@R~
//                awtchkboxitem.setState(true);                      //~1211R~//~@@@@R~
//            }                                                      //~1211I~//~@@@@R~
//            if (awtchkboxitem.checkboxtranslator!=null)//ItemListener defined//~1211R~//~@@@@R~
//            {                                                      //~1211I~//~@@@@R~
//                awtchkboxitem.checkboxtranslator.itemStateChanged(new ItemEvent());//~1211R~//~@@@@R~
//            }                                                      //~1211I~//~@@@@R~
//        }                                                          //~1211I~//~@@@@R~
//        if (Pawtitem instanceof MenuItemAction)                    //~1211R~//~@@@@R~
//        {                                                          //~1211I~//~@@@@R~
//            if (Dump.Y) Dump.println("AMenu:MenuitemAction");                  //~1211I~//~1506R~//~@@@@R~
//            ActionEvent.actionPerformedMenu(Pmenubar,(com.Ahsv.awt.MenuItem)Pawtitem);  //execute DoAction//~1408I~//~@@@@R~
//        }                                                          //~1211I~//~@@@@R~
//        return rc;                                                 //~1211I~//~@@@@R~
//    }                                                              //~1211I~//~@@@@R~
//***********************                                          //~1124I~
//    private Object findItem(MenuBar Pmenubar,int Pitemid)          //~1124R~//~@@@@R~
//    {                                                              //~1124I~//~@@@@R~
//        int menuid,itemid,nestid;                        //~1124I~//~@@@@R~
//        com.Ahsv.awt.Menu awtmenu,awtmenunest;                   //~1124I~//~@@@@R~
//        Object awtitem;                                            //~1524R~//~@@@@R~
//    //*******************                                          //~1124I~//~@@@@R~
//        menuid=(Pitemid>>16) & 0xff;                               //~1124I~//~@@@@R~
//        itemid=(Pitemid>>8)  & 0xff;                               //~1124I~//~@@@@R~
//        nestid=Pitemid & 0xff;                                     //~1124I~//~@@@@R~
//        awtmenu=Pmenubar.getMenu(menuid-1);                        //~1124R~//~@@@@R~
//        if (itemid!=0)                                             //~1124I~//~@@@@R~
//        {                                                          //~1124I~//~@@@@R~
//            awtitem=awtmenu.getItem(itemid-1);                        //~1124I~//~@@@@R~
//            if (nestid!=0 && nestid!=POPUPSUBMENUID)               //~1124I~//~1211R~//~1328R~//~@@@@R~
//            {                                                      //~1124I~//~1211R~//~1328R~//~@@@@R~
//                awtmenunest=(com.Ahsv.awt.Menu) awtitem;                               //~1124I~//~1211R~//~1328R~//~@@@@R~
//                awtitem=awtmenunest.getItem(nestid-1);             //~1124I~//~1211R~//~1328R~//~@@@@R~
//            }                                                      //~1124I~//~1211R~//~1328R~//~@@@@R~
//        }                                                          //~1124I~//~@@@@R~
//        else                                                       //~1124I~//~@@@@R~
//            awtitem=awtmenu;                                       //~1124I~//~@@@@R~
//        return awtitem;                                          //~@@@@R~
//    }//~1124I~                                                   //~@@@@R~
//****************************************************             //~1307I~
    public void onContextMenuClosed(Menu Pmenu)                    //~1307I~//~@@@@R~
    {                                                              //~1307I~//~@@@@R~
//        listerPopup=false;                                         //~1307I~//~@@@@R~
//        if (swPopupSubmenu)                                        //~1328R~//~@@@@R~
//            showContextMenu();  //show submenu                     //~1328I~//~@@@@R~
//      else                                                       //~v104R~//~@@@@R~
//          currentMenuBar=null;                                       //~1307I~//~v104R~//~@@@@R~
    }                                                              //~1124I~//~@@@@R~
////****************************************************             //~1123I~//~@@@@R~
////*create menu from stacked Menu/submenu/item                      //~1123I~//~@@@@R~
////*itemid=menubarid(8)+menuid(8)+item(8)+level3 item(8);start from 1//~1123I~//~@@@@R~
////****************************************************             //~1123I~//~@@@@R~
//    private void createMenu(ContextMenu Pcontextmenu,MenuBar Pmenubar)//~1123I~//~@@@@R~
//    {                                                              //~1123I~//~@@@@R~
//        com.Ahsv.awt.Menu awtmenu;                               //~@@@@R~
//        com.Ahsv.awt.Menu helpMenu;                              //~1411I~//~@@@@R~
//        Object awtitem;                                            //~1123I~//~@@@@R~
//        MenuItem androiditem;                                      //~1123I~//~@@@@R~
//        SubMenu  androidsubmenu;                                   //~1123I~//~@@@@R~
//        String itemname;                                           //~1123I~//~@@@@R~
//        int menubarid,menuid,itemctr,itemid,itemid2;               //~1524R~//~@@@@R~
//        int none=android.view.Menu.NONE;                                         //~1123I~//~@@@@R~
//        boolean swHelpOnly;                                        //~1411I~//~@@@@R~
////*******************                                              //~1123I~//~@@@@R~
//        swHelpOnly=Pmenubar.getShowHelpOnly();                     //~1411I~//~@@@@R~
//        Pmenubar.setShowHelpOnly(false);                           //~1411R~//~@@@@R~
//        helpMenu=Pmenubar.helpMenu;                                //~1411I~//~@@@@R~
//        if (Dump.Y) Dump.println("AMenu:createMenu helponly="+swHelpOnly);//~1506R~//~@@@@R~
//        menubarid=(Pmenubar.seqno+1)<<24;                          //~1123R~//~@@@@R~
//        for (int ii=0;;ii++)                                       //~1123I~//~@@@@R~
//        {                                                          //~1123I~//~@@@@R~
//            awtmenu=Pmenubar.getMenu(ii);                  //~1123R~//~@@@@R~
//            if (awtmenu==null)                                     //~1123I~//~@@@@R~
//                break;                                             //~1123I~//~@@@@R~
//            menuid=(ii+1)<<16;                                     //~1123R~//~@@@@R~
//            itemname=awtmenu.name;                               //~@@@@R~
//            itemid=menubarid+menuid;//~1123I~                    //~@@@@R~
//            if (swHelpOnly)                                        //~1412M~//~@@@@R~
//            {                                                      //~1412M~//~@@@@R~
//                if (awtmenu!=helpMenu)                             //~1412M~//~@@@@R~
//                    continue;                                      //~1412M~//~@@@@R~
//                createHelpOnlyMenu(Pcontextmenu,Pmenubar,helpMenu,itemid);//~1412R~//~@@@@R~
//                return;                                            //~1412M~//~@@@@R~
//            }                                                      //~1412M~//~@@@@R~
//            itemid2=1<<8;                                          //~1123I~//~@@@@R~
//            itemctr=awtmenu.getItemCtr();                          //~1412M~//~@@@@R~
//            if (itemctr==0)                                        //~1123I~//~@@@@R~
//            {                                                      //~1123I~//~@@@@R~
//            //*menu item under menubar                             //~1123I~//~@@@@R~
//                itemid+=itemid2;                                   //~1123I~//~@@@@R~
//                androiditem=Pcontextmenu.add(none/*group id*/,itemid,none/*order*/,itemname);//~1123I~//~@@@@R~
//                setItem(androiditem,awtmenu);                      //~1124I~//~@@@@R~
//                if (Dump.Y) Dump.println("AMenu:menuitem "+Integer.toHexString(ii)+"="+itemname);//~1123R~//~1506R~//~@@@@R~
//            }                                                      //~1123I~//~@@@@R~
//            else                                                   //~1123I~//~@@@@R~
//            {                                                      //~1123I~//~@@@@R~
//            //*menu having submenu/menu item                       //~1123R~//~@@@@R~
//                androidsubmenu=Pcontextmenu.addSubMenu(none/*group id*/,itemid,none,itemname);//~1123I~//~@@@@R~
//                setMenu((Menu)androidsubmenu,awtmenu);             //~1124I~//~@@@@R~
//                if (Dump.Y) Dump.println("submenu "+Integer.toHexString(itemid)+"="+itemname);//~1123R~//~1506R~//~@@@@R~
//                setAhsvHelpMenuItem(Pcontextmenu,Pmenubar,awtmenu,androidsubmenu,itemid);//~1412R~//~@@@@R~
//                for (int jj=0;;jj++)                               //~1123R~//~@@@@R~
//                {                                                  //~1123I~//~@@@@R~
//                    itemid2=(itemid+((jj+1)<<8));                         //~1123I~//~@@@@R~
//                    awtitem=awtmenu.getItem(jj);                   //~1123R~//~@@@@R~
//                    if (awtitem==null)                             //~1123I~//~@@@@R~
//                        break;                                     //~1123I~//~@@@@R~
//                    if (awtitem instanceof com.Ahsv.awt.Menu)                  //~1123I~//~@@@@R~
//                    {                                              //~1123I~//~@@@@R~
//                    //*submenu having submenu                      //~1123I~//~@@@@R~
//                        itemname=((com.Ahsv.awt.Menu)awtitem).name+" +";//~1123I~//~1211R~//~@@@@R~
//                        if (Dump.Y) Dump.println("AMenu:menuitem "+Integer.toString(itemid2,16)+"="+itemname);//~1123I~//~1506R~//~@@@@R~
//                        androiditem=androidsubmenu.add(none/*group id*/,itemid2+POPUPSUBMENUID/*sub of sub id*/,none,itemname);//~1123R~//~1211R~//~1328R~//~@@@@R~
//                    }                                              //~1123I~//~@@@@R~
//                    else                                           //~1123I~//~@@@@R~
//                    {                                              //~1123I~//~@@@@R~
//                    //*menuitem under submenu                      //~1123I~//~@@@@R~
//                        itemname=((com.Ahsv.awt.MenuItem)awtitem).name;//~1123I~//~@@@@R~
//                        if (Dump.Y) Dump.println("Ajagomenu:menuitem "+Integer.toString(itemid2,16)+"="+itemname);//~1123I~//~1506R~//~@@@@R~
//                        androiditem=androidsubmenu.add(none/*group id*/,itemid2,none,itemname);//~1123R~//~@@@@R~
//                        setItem(androiditem,awtitem);              //~1123I~//~@@@@R~
//                    }                                              //~1123I~//~@@@@R~
//                }                                                  //~1123I~//~@@@@R~
//            }                                                      //~1123I~//~@@@@R~
//                                          //~1123I~              //~@@@@R~
//        }                                                          //~1123I~//~@@@@R~
//    }                                                              //~1123I~//~@@@@R~
//****************************************************             //~1328I~
//*3rd level submenu                                               //~1328I~
//****************************************************             //~1328I~
//    private void createPopupSubmenu(ContextMenu Pcontextmenu,MenuBar Pmenubar,com.Ahsv.awt.Menu Psubmenu)//~1328I~//~@@@@R~
//    {                                                              //~1328I~//~@@@@R~
//        com.Ahsv.awt.Menu awtmenu;                               //~1328I~//~@@@@R~
//        com.Ahsv.awt.MenuItem awtitem2;                          //~1328I~//~@@@@R~
//        MenuItem androiditem;                                      //~1328I~//~@@@@R~
//        String itemname,submenutitle;                                           //~1328I~//~@@@@R~
//        int itemid2,itemid3;//~1328I~                            //~@@@@R~
//        int none=android.view.Menu.NONE;                           //~1328I~//~@@@@R~
//    //*******************                                          //~1411R~//~@@@@R~
//        awtmenu=Psubmenu;                                          //~1328I~//~@@@@R~
//        itemid2=(Psubmenu.itemid & ~POPUPSUBMENUID);               //~1328R~//~@@@@R~
//        submenutitle=awtmenu.name;                                  //~1328I~//~@@@@R~
//        Pcontextmenu.setHeaderTitle(submenutitle);                  //~1328I~//~@@@@R~
//        for (int kk=0;;kk++)                                       //~1328I~//~@@@@R~
//        {                                                          //~1328I~//~@@@@R~
//            itemid3=itemid2+kk+1;                                  //~1328I~//~@@@@R~
//            awtitem2=(com.Ahsv.awt.MenuItem)(awtmenu.getItem(kk));//~1328I~//~@@@@R~
//            if (awtitem2==null)                                    //~1328I~//~@@@@R~
//                break;                                             //~1328I~//~@@@@R~
//            itemname=awtitem2.name;                                //~1328I~//~@@@@R~
//            androiditem=Pcontextmenu.add(none/*groupid*/,itemid3,none/*order*/,itemname);//~1328I~//~@@@@R~
//            setItem(androiditem,awtitem2);                         //~1328I~//~@@@@R~
//        }                                                          //~1328I~//~@@@@R~
//    }                                                              //~1328I~//~@@@@R~
//****************************************************             //~1412I~
//*help submenu only                                               //~1412I~
//****************************************************             //~1412I~
//    private void createHelpOnlyMenu(ContextMenu Pcontextmenu,MenuBar Pmenubar,com.Ahsv.awt.Menu Psubmenu,int Pitemid)//~1412I~//~@@@@R~
//    {                                                              //~1412I~//~@@@@R~
//        com.Ahsv.awt.Menu awtmenu;                               //~1412I~//~@@@@R~
//        com.Ahsv.awt.MenuItem awtitem2;                          //~1412I~//~@@@@R~
//        MenuItem androiditem;                                      //~1412I~//~@@@@R~
//        String itemname,submenutitle;                              //~1412I~//~@@@@R~
//        int itemid2;                                               //~1412I~//~@@@@R~
//        int none=android.view.Menu.NONE;                           //~1412I~//~@@@@R~
//    //*******************                                          //~1412I~//~@@@@R~
//        awtmenu=Psubmenu;                                          //~1412I~//~@@@@R~
//        submenutitle=awtmenu.name;                                 //~1412I~//~@@@@R~
//        Pcontextmenu.setHeaderTitle(submenutitle);                 //~1412I~//~@@@@R~
//        setAhsvHelpMenuItem(Pcontextmenu,Pmenubar,Psubmenu,null,Pitemid);//~1412I~//~@@@@R~
//        for (int jj=0;;jj++)                                       //~1412I~//~@@@@R~
//        {                                                          //~1412I~//~@@@@R~
//            itemid2=Pitemid+((jj+1)<<8);                           //~1412R~//~@@@@R~
//            awtitem2=(com.Ahsv.awt.MenuItem)(awtmenu.getItem(jj));//~1412I~//~@@@@R~
//            if (awtitem2==null)                                    //~1412I~//~@@@@R~
//                break;                                             //~1412I~//~@@@@R~
//            itemname=awtitem2.name;                                //~1412I~//~@@@@R~
//            if (Dump.Y) Dump.println("Ajagomenu:createHelpOnlyMenu name="+itemname);//~1506R~//~@@@@R~
//            androiditem=Pcontextmenu.add(none/*groupid*/,itemid2,none/*order*/,itemname);//~1412I~//~@@@@R~
//            setItem(androiditem,awtitem2);                         //~1412I~//~@@@@R~
//        }                                                          //~1412I~//~@@@@R~
//    }                                                              //~1412I~//~@@@@R~
//****************************************************             //~1411I~
//    private void setAhsvHelpMenuItem(ContextMenu Pcontextmenu,MenuBar Pmenubar,com.Ahsv.awt.Menu Psubmenu,SubMenu Pandroidsubmenu,int Pitemid)//~1412R~//~@@@@R~
//    {                                                              //~1411I~//~@@@@R~
//        int itemid2;                                               //~1411I~//~@@@@R~
//        int none=android.view.Menu.NONE;                           //~1411I~//~@@@@R~
//    //*******************                                          //~1411I~//~@@@@R~
////      if (Pmenubar.seqno!=0 || !Psubmenu.name.equals(Global.resourceString("Help")))//~1412R~//~@@@@R~
//        if (Pmenubar.seqno!=0 || !Psubmenu.name.equals(AG.resource.getString(R.string.Help)))//~@@@@R~
//            return;                                                //~1411I~//~@@@@R~
//        itemid2=Pitemid|AJAGOHELPMENUID;                           //~1412R~//~@@@@R~
//        if (Dump.Y) Dump.println("AMenu:add AjagoHelp itemid="+Integer.toHexString(itemid2));//~1506R~//~@@@@R~
//        if (Pandroidsubmenu!=null)                                 //~1411I~//~@@@@R~
//            Pandroidsubmenu.add(none/*groupid*/,itemid2,none/*order*/,HELPITEM_AJAGOC);//~1411R~//~@@@@R~
//        else                                                       //~1411I~//~@@@@R~
//            Pcontextmenu.add(none/*groupid*/,itemid2,none/*order*/,HELPITEM_AJAGOC);//~1411I~//~@@@@R~
//    }                                                              //~1411I~//~@@@@R~
//****************************************************             //~1306I~
//*create WhoFrame ActionMenu from stacked Menu/submenu/item       //~1306I~
//*itemid=menubarid(8)+menuid(8)+item(8)+level3 item(8);start from 1//~1306I~
//****************************************************             //~1306I~
//    private void createListerPopupMenu(ContextMenu Pcontextmenu,MenuBar Pmenubar,ContextMenuInfo Pinfo )//~1306I~//~1307R~//~@@@@R~
//    {                                                              //~1306I~//~@@@@R~
//        com.Ahsv.awt.Menu awtmenu;                               //~1306I~//~@@@@R~
//        Object awtitem;                                            //~1306I~//~@@@@R~
//        MenuItem androiditem;                                      //~1306I~//~@@@@R~
//        String itemname;                                           //~1306I~//~@@@@R~
//        int menubarid,menuid,itemctr,itemid,itemid2;               //~1524R~//~@@@@R~
//        int none=android.view.Menu.NONE;                           //~1306I~//~@@@@R~
//        CharSequence selectedItem=null;                                  //~1307I~//~@@@@R~
//        AdapterContextMenuInfo info;                             //~@@@@R~
//        TextView tv;//~1307I~                                    //~@@@@R~
////*******************                                              //~1306I~//~@@@@R~
//        listerPopup=true;                                          //~1307M~//~@@@@R~
//                                                                   //~1307I~//~@@@@R~
//        if (Pinfo!=null)                                           //~1307I~//~@@@@R~
//        {                                                          //+1307I~//~1307I~//~@@@@R~
//            info=(AdapterContextMenuInfo)Pinfo;                    //~1307I~//~@@@@R~
//            if (info.targetView!=null)                             //~1307I~//~@@@@R~
//            {                                                      //~1307I~//~@@@@R~
//                tv=(TextView)info.targetView;                      //~1307I~//~@@@@R~
//                selectedItem=tv.getText();                         //~1307I~//~@@@@R~
//                Pcontextmenu.setHeaderTitle(selectedItem);         //~1307I~//~@@@@R~
//            }                                                      //~1307I~//~@@@@R~
//        }                                                          //~1307I~//~@@@@R~
//        menubarid=(Pmenubar.seqno+1)<<24;                          //~1306I~//~1307R~//~@@@@R~
//        awtmenu=Pmenubar.getMenu(0);                              //~1306I~//~1307R~//~@@@@R~
//        if (awtmenu==null)                                         //~1306I~//~1307R~//~@@@@R~
//            return;                                              //~1306I~//~1307R~//~@@@@R~
//        menuid=1<<16;                                         //~1306I~//~1307R~//~@@@@R~
//        itemctr=awtmenu.getItemCtr();                              //~1306I~//~1307R~//~@@@@R~
//        itemname=awtmenu.name;                                     //~1306I~//~1307R~//~@@@@R~
//        itemid=menubarid+menuid;                                   //~1306I~//~1307R~//~@@@@R~
//        itemid2=1<<8;                                              //~1306I~//~1307R~//~@@@@R~
//        if (itemctr!=0)                                            //~1306I~//~1307R~//~@@@@R~
//        {                                                          //~1306I~//~1307R~//~@@@@R~
//            for (int jj=0;;jj++)                                   //~1306I~//~1307R~//~@@@@R~
//            {                                                      //~1306I~//~1307R~//~@@@@R~
//                itemid2=(itemid+((jj+1)<<8));                      //~1306I~//~1307R~//~@@@@R~
//                awtitem=awtmenu.getItem(jj);                       //~1306I~//~1307R~//~@@@@R~
//                if (awtitem==null)                                 //~1306I~//~1307R~//~@@@@R~
//                    break;                                         //~1306I~//~1307R~//~@@@@R~
//                itemname=((com.Ahsv.awt.MenuItem)awtitem).name;  //~1306I~//~1307R~//~@@@@R~
//                if (Dump.Y) Dump.println("AMenu:menuitem "+Integer.toHexString(itemid2)+"="+itemname);//~1306I~//~1307R~//~1506R~//~@@@@R~
//                androiditem=Pcontextmenu.add(none/*group id*/,itemid2,none,itemname);//~1306I~//~1307R~//~@@@@R~
//                setItem(androiditem,awtitem);                      //~1306I~//~1307R~//~@@@@R~
//            }                                                      //~1306I~//~1307R~//~@@@@R~
//        }                                                          //~1306I~//~1307R~//~@@@@R~
//    }                                                              //~1306I~//~@@@@R~
//***********************                                          //~1123I~
//    private void setMenu(Menu Pandroiditem,com.Ahsv.awt.Menu Pmenu)       //~1123I~//~1124R~//~@@@@R~
//    {                                                              //~1123I~//~@@@@R~
//    //*******************                                          //~1124I~//~@@@@R~
//        if (Pmenu.font!=null)                                      //~1124I~//~@@@@R~
//        {                                                          //~1124I~//~@@@@R~
////          Pmenu.font.setFont((View)Pandroiditem);                            //~1124I~//~@@@@R~
////          Menu dose not support to change font                   //~1124I~//~@@@@R~
//        }                                                          //~1124I~//~@@@@R~
//    }                                                              //~1123I~//~@@@@R~
//***********************                                          //~1124I~
//    private void setItem(MenuItem Pandroiditem,Object Pitem)       //~1124I~//~@@@@R~
//    {                                                              //~1124I~//~@@@@R~
//        com.Ahsv.awt.MenuItem awtitem;                           //~1124I~//~@@@@R~
//    //*******************                                          //~1124I~//~@@@@R~
//        awtitem=(com.Ahsv.awt.MenuItem)Pitem;                    //~1124I~//~@@@@R~
//        if (awtitem instanceof CheckboxMenuItem                    //~1124I~//~@@@@R~
//        ||  awtitem instanceof CheckboxMenuItemAction              //~1124I~//~@@@@R~
//        )                                                          //~1124I~//~@@@@R~
//        {                                                          //~1124I~//~@@@@R~
//            Pandroiditem.setCheckable(true);                       //~1124I~//~@@@@R~
//            Pandroiditem.setChecked(((com.Ahsv.awt.CheckboxMenuItem)awtitem).getState());           //~1124I~//~@@@@R~
//        }                                                          //~1124I~//~@@@@R~
//        if (awtitem.font!=null)                                    //~1124I~//~@@@@R~
//        {                                                          //~1124I~//~@@@@R~
////          font.setFont(Pandroiditem);                            //~1124I~//~@@@@R~
////          Menu dose not support to change font                   //~1124I~//~@@@@R~
//        }                                                          //~1124I~//~@@@@R~
//    }                                                              //~1124I~//~@@@@R~
//***********************                                          //~1211I~
//    private void popupSubmenu(com.Ahsv.awt.Menu Psubmenu,int Pitemid)//~1328I~//~@@@@R~
//    {                                                              //~1328I~//~@@@@R~
//        swPopupSubmenu=true;    //onContextMenuClosed request recursive showContextMenu//~1328I~//~@@@@R~
//        currentMenuBar.popupSubmenu=Psubmenu;   //                 //~1328I~//~@@@@R~
//        Psubmenu.itemid=Pitemid;                                   //~1328I~//~@@@@R~
//    }                                                              //~1328I~//~@@@@R~
//**********************************************************************//~1211I~
//*callback                                                        //~1211I~
//**********************************************************************//~1211I~
    public int alertButtonAction(int Pbuttonid,int Pitempos)       //~1211R~//~@@@@R~
    {                                                              //~1211I~//~@@@@R~
          int rc=1;   //dismiss                                      //~1211I~//~1328R~//~@@@@R~
          return rc;                                                 //~1211M~//~1328R~//~@@@@R~
    }                                                              //~1211I~//~@@@@R~
//****************************************************             //~1411I~
//    private void helpAhsv()                                      //~1411I~//~@@@@R~
//    {                                                              //~1411I~//~@@@@R~
//    //*******************                                          //~1411I~//~@@@@R~
//        new Help(HELPTEXT_AJAGOC);                                     //~1411I~//~@@@@R~
//        if (Dump.Y) Dump.println("AMenu:AjagoHelp");           //~1506R~//~@@@@R~
//    }                                                              //~1411I~//~@@@@R~
//*********************************************                    //~1314I~
//*Option Menu ********************************                    //~1314I~
//*********************************************                    //~1314I~
//* called only once                                               //~1326I~
 	public  void onCreateOptionMenu(Menu Pmenu)                    //~1314R~
	{                                                              //~1314I~
        String str;                                                //~1314I~
    //********************                                         //~1314I~
//        if (AG.osVersion>=AG.HONEYCOMB)  //android3              //~1A51R~
//        {                                                        //~1A51R~
            onCreateOptionMenu_V11(Pmenu);                         //~1A51R~
//            return;                                              //~1A51R~
//        }                                                        //~1A51R~
//        menuDesc=AG.resource.getStringArray(R.array.MenuText);  //~1314I~//~1326R~//~1A51R~
//        for (int ii=0;ii<MENU_CTR;ii++)                            //~1326R~//~1A51R~
//        {                                                          //~1314I~//~1A51R~
//            str=menuDesc[ii];                                      //~1314I~//~1A51R~
//            int id=menuId[ii];                                     //~1314I~//~1A51R~
//            MenuItem item=Pmenu.add(0,id,0,str);                    //~1314I~//~1A51R~
//            item.setIcon(icons[ii]);                               //~1314I~//~1A51R~
//        }                                                          //~1314I~//~1A51R~
    }                                                              //~1314I~
//**************                                                   //~1A51R~
 	public void onCreateOptionMenu_V11(Menu Pmenu)                 //~1A51R~
	{                                                              //~1A51R~
        MenuInflater inf=AG.activity.getMenuInflater();            //~1A51R~
        inf.inflate(R.menu.actionbar,Pmenu);                       //~1A51R~
    }                                                              //~1A51R~
//**************                                                   //~v107I~
//* called each time to diaply                                     //~v107I~
 	public  void onPrepareOptionMenu(Menu Pmenu)                   //~v107I~
	{                                                              //~v107I~
    //********************                                         //~v107I~
//        if (AG.portrait                                            //~v107R~//~@@@@R~
////      ||  AG.scrWidth<MENUMENU_SIZE                              //~v107R~//~@@@@R~
//        )                                                          //~v107I~//~@@@@R~
//            Pmenu.findItem(MENU_MENU).setVisible(false);        //~v107I~//~@@@@R~
//        else                                                       //~v107I~//~@@@@R~
//            Pmenu.findItem(MENU_MENU).setVisible(true);            //~v107I~//~@@@@R~
    }                                                              //~v107I~
//**************                                                   //~1314I~
 	public  int onOptionMenuSelected(MenuItem item)                //~1314I~
	{                                                              //~1314I~
        int itemid=item.getItemId();                               //~1314I~
        if (Dump.Y) Dump.println("AMenu:OptionMenuSelected="+itemid);//~1314I~ //~1326R~//~1506R~//~@@@@R~
        switch(itemid)                                             //~1314I~
        {                                                          //~1314I~
        case    MENU_STOP:                                       //~1314R~//~1326R~
        case    R.id.MENU_STOP:   //action bar menu                //~1A51R~
            stopApp();                                             //~1412I~
            break;                                                 //~1412I~
//        case    MENU_CLOSE:                                        //~1326I~//~@@@@R~
//            closeFrame();                                          //~1314R~//~@@@@R~
//            break;                                                 //~1314I~//~@@@@R~
          case    MENU_CANCEL:                                     //~@@@@I~
              AG.activity.closeOptionsMenu();                                   //~@@@@I~
              break;                                               //~@@@@I~
//        case    MENU_HELP:                                         //~1314I~//~@@@@R~
//            optionMenuHelp();                                      //~1314R~//~@@@@R~
//            break;                                                 //~1314I~//~@@@@R~
        case    MENU_MSG:                                          //~@@@@I~
        case    R.id.MENU_MSG:                                     //~1A51R~
            optionMenuMsg();                                       //~@@@@I~
            break;                                                 //~@@@@I~
//        case    MENU_MENU:                                         //~v107I~//~@@@@R~
//            optionMenuMenu();                                      //~v107I~//~@@@@R~
//            break;                                                 //~v107I~//~@@@@R~
        }                                                          //~1314I~
        return 0;                                                  //~1314I~
    }//selected                                                    //~1314I~
//**************                                                   //~1412I~
    public void stopApp()                                          //~1412I~
    {                                                              //~1412I~
        confirmStop();                                             //~1412I~
    }                                                              //~1412I~
//**************                                                   //~1314I~
//    public void closeFrame()                                       //~1314R~//~@@@@R~
//    {                                                              //~1314I~//~@@@@R~
//        try                                                        //~1423I~//~@@@@R~
//        {                                                          //~1423I~//~@@@@R~
//            if (AG.isTopFrame())                                   //~1423R~//~@@@@R~
//                confirmStop();                                     //~1423R~//~@@@@R~
//            else                                                   //~1423R~//~@@@@R~
//            {                                                      //~1423R~//~@@@@R~
//                if (!ActionEvent.optionMenuClose()) //CloseFrame doAction not scheduled//~1423R~//~@@@@R~
//                    Window.popFrame(true);  //close                //~1423R~//~@@@@R~
//            }                                                      //~1423R~//~@@@@R~
//        }                                                          //~1423I~//~@@@@R~
//        catch(Exception e)                                         //~1423I~//~@@@@R~
//        {                                                          //~1423I~//~@@@@R~
//            Dump.println(e,"AMenu:closeFrame from OptionMenu");//~1423I~//~@@@@R~
//        }                                                          //~1423I~//~@@@@R~
//    }                                                              //~1314I~//~@@@@R~
//**************                                                   //~1314I~
    public void confirmStop()                                      //~1314I~
    {                                                              //~1314I~
    	int flag=Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE|Alert.EXIT;//~1314I~//~@@@@R~
        Alert.simpleAlertDialog(null/*callback*/,null/*title*/,R.string.Qexit,flag);//~1314I~//~@@@@R~
    }                                                              //~1314I~
//**************                                                   //~1314I~
//    public void showContextMenu()                                  //~1314I~//~@@@@R~
//    {                                                              //~1314I~//~@@@@R~
//        View view=Window.getCurrentFrame().contextMenuView;        //~1314I~//~@@@@R~
//        if (view==null)                                            //~1314I~//~@@@@R~
//        {                                                          //~1314I~//~@@@@R~
//            AView.showToast(R.string.NoRegisteredContextMenu); //~1314I~//~@@@@R~
//            return;                                                //~1314I~//~@@@@R~
//        }                                                          //~1314I~//~@@@@R~
//        if (Dump.Y) Dump.println("AMenu showContextMenu view="+view.toString());//~1516M~//~@@@@R~
//        swShowRequest=true;                                        //~1427I~//~@@@@R~
//        view.showContextMenu();                                    //~v104R~//~@@@@R~
////      AG.activity.openContextMenu(view);                         //~v104R~//~@@@@R~
//    }                                                              //~1314I~//~@@@@R~
//**************                                                   //~1314I~
//    public void optionMenuHelp()                                        //~1314I~//~@@@@R~
//    {                                                              //~1314I~//~@@@@R~
//        Frame frame=Window.getCurrentFrame();                      //~1411I~//~@@@@R~
//        MenuBar menubar=frame.framemenubar;                        //~1411I~//~@@@@R~
//        if (menubar==null|| menubar.helpMenu==null)                //~1411I~//~@@@@R~
//        {                                                          //~1411I~//~@@@@R~
//            AView.showToast(R.string.NoRegisteredHelpMenu);    //~1411I~//~@@@@R~
//            return;                                                //~1411I~//~@@@@R~
//        }                                                          //~1411I~//~@@@@R~
//        menubar.setShowHelpOnly(true);                           //~@@@@R~
//        View view=frame.contextMenuView;//~1411I~                //~@@@@R~
//        if (Dump.Y) Dump.println("AMenu optionMenuHelp view="+view.toString());//~1506R~//~@@@@R~
//        swShowRequest=true;                                        //~1427I~//~@@@@R~
//        view.showContextMenu();                                    //~1411I~//~@@@@R~
//        AView.showToastLong(R.string.Help_OptionMenu);           //~@@@@R~
//    }                                                              //~1314I~//~@@@@R~
//**************                                                   //~v107I~
//    public void optionMenuMenu()                                   //~v107I~//~@@@@R~
//    {                                                              //~v107I~//~@@@@R~
//        showContextMenu();  //show submenu                         //~v107I~//~@@@@R~
//    }                                                              //~v107I~//~@@@@R~
//******************************************************           //~@@@@I~
    public void optionMenuMsg()                                    //~@@@@R~
    {                                                              //~@@@@I~
    	if ((AG.RemoteStatus!=AG.RS_IPCONNECTED                    //~@@@@I~
             &&  AG.RemoteStatus!=AG.RS_BTCONNECTED                //~@@@@I~
            )                                                      //~@@@@I~
    	||  AG.msgThread==null                                     //~@@@@R~
        ||  !AG.msgThread.isAlive())                           //~@@@@I~
        {                                                          //~@@@@I~
        	AView.showToast(R.string.ErrNoConnectionForConversation);//~@@@@R~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
        new SayDialog(AG.currentFrame);                            //~@@@@R~
    }                                                              //~@@@@I~
//************************************************                 //~1507I~
//*Ajago Option menu add before help of MainFrame menubar          //~1507I~
//************************************************                 //~1507I~
//    private com.Ahsv.awt.Menu ajagocOptions;                             //~1507I~//~@@@@R~
//    private CheckboxMenuItemAction opt1,opt2,opt3;          //~1507I~//~@@@@R~
//                                                                   //~1507I~//~@@@@R~
//    private static final String DIRECTIONKEY="Use DirectionKey as Shortcut";//~1507I~//~@@@@R~
//    private static final String SEARCHKEY   ="Use Search button as Enter";//~1507I~//~@@@@R~
////    private static final String DEBUGTRACE  ="DebugTrace";         //~1507I~//~@@@@R~
//                                                                   //~1507I~//~@@@@R~
//    public  static final String DIRECTIONKEY_CFGKEY="directionkey";//~1507R~//~@@@@R~
//    public  static final String SEARCHKEY_CFGKEY   ="searchkey";   //~1507R~//~@@@@R~
////    public  static final String DEBUGTRACE_CFGKEY  ="debugtrace";  //~1507R~//~@@@@R~
//                                                                   //~1507I~//~@@@@R~
    public void addAjagoOptionMenu(MenuBar Pmenubar)        //~1507I~//~@@@@R~
    {                                                              //~1507I~//~@@@@R~
//        boolean flag;                                              //~1507R~//~@@@@R~
//        if (ajagocOptions==null)                                   //~1507R~//~@@@@R~
//        {                                                          //~1507R~//~@@@@R~
//            ajagocOptions=new MyMenu(AG.appName+" "+Global.resourceString("Options"));//~1507R~//~@@@@R~
////            opt1=new CheckboxMenuItemAction(this,DIRECTIONKEY);   //~1507R~//~@@@@R~
////            opt2=new CheckboxMenuItemAction(this,SEARCHKEY);     //~1507R~//~@@@@R~
////            opt3=new CheckboxMenuItemAction(this,DEBUGTRACE);    //~1507R~//~@@@@R~
////            ajagocOptions.add(opt1);                                     //~1507R~//~@@@@R~
////            ajagocOptions.add(opt2);                                     //~1507R~//~@@@@R~
////          if (AG.isDebuggable)                                     //~v107I~//~@@@@R~
////            ajagocOptions.add(opt3);                                     //~1507R~//~@@@@R~
//        }                                                          //~1511M~//~@@@@R~
////        flag=Global.getParameter(DIRECTIONKEY_CFGKEY,false);       //~1511R~//~@@@@R~
////        if (Dump.Y) Dump.println("addAjagoOptionMenu directionkey="+flag);//~1511R~//~@@@@R~
////        opt1.setState(flag);                                       //~1511R~//~@@@@R~
////        flag=Global.getParameter(SEARCHKEY_CFGKEY,true);           //~1511R~//~@@@@R~
////        if (Dump.Y) Dump.println("addAjagoOptionMenu searchkey="+flag);//~1511R~//~@@@@R~
////        opt2.setState(flag);                                       //~1511R~//~@@@@R~
////        flag=Global.getParameter(DEBUGTRACE_CFGKEY,false);         //~1511R~//~@@@@R~
////        if (Dump.Y) Dump.println("addAjagoOptionMenu debugtrace="+flag);//~1511R~//~@@@@R~
////        opt3.setState(flag);                                       //~1511R~//~@@@@R~
//        Pmenubar.menuList.add(ajagocOptions);                               //~1507R~//~@@@@R~
    }                                                              //~1507I~//~@@@@R~
    @Override                                                      //~1507I~//~@@@@R~
    public void doAction(String o)                                 //~1507I~//~@@@@R~
    {                                                              //~1507I~//~@@@@R~
    }                                                              //~1507I~//~@@@@R~
    @Override                                                      //~1507I~//~@@@@R~
    public void itemAction(String o,boolean flag)                  //~1507I~//~@@@@R~
    {                                                              //~1507I~//~@@@@R~
//        if (Dump.Y) Dump.println("Ajagomenu ItemAction for AjagoOption "+o+"="+flag);//~1507I~//~@@@@R~
//        if (o.equals(DIRECTIONKEY))                                //~1507I~//~@@@@R~
//        {                                                          //~1507I~//~@@@@R~
//            Global.setParameter(DIRECTIONKEY_CFGKEY,flag);         //~1507I~//~@@@@R~
//            Canvas.optionChanged(flag);                            //~1507I~//~@@@@R~
//        }                                                          //~1507I~//~@@@@R~
//        else                                                       //~1507I~//~@@@@R~
//        if (o.equals(SEARCHKEY))                                   //~1507I~//~@@@@R~
//        {                                                          //~1507I~//~@@@@R~
//            Global.setParameter(SEARCHKEY_CFGKEY,flag);            //~1507I~//~@@@@R~
//            AKey.optionChanged(flag);                          //~1507I~//~@@@@R~
//        }                                                          //~1507I~//~@@@@R~
////        else                                                       //~1507I~//~@@@@R~
////        if (o.equals(DEBUGTRACE))                                  //~1507I~//~@@@@R~
////        {                                                          //~1507I~//~@@@@R~
////            Global.setParameter(DEBUGTRACE_CFGKEY,flag);           //~1507I~//~@@@@R~
////            Dump.setOption(flag);                                  //~1507I~//~@@@@R~
////        }                                                          //~1507I~//~@@@@R~
    }                                                              //~1507I~//~@@@@R~
}//class AMenu                                                 //~1211R~//~@@@@R~
