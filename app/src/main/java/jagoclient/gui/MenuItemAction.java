//*CID://+@@@@R~:                             update#=    1;       //+@@@@I~
package jagoclient.gui;

import com.Ahsv.awt.MenuItem;                                      //+@@@@R~

import jagoclient.Global;

//import java.awt.MenuItem;

//import com.Ahsv.awt.Menu;                                        //+@@@@R~

/**
A menu item with a specified font.
*/

public class MenuItemAction extends MenuItem                       //~1122R~
{   ActionTranslator AT;
	public MenuItemAction (DoActionListener c, String s, String name)
    {   super(s);
	    addActionListener(AT=new ActionTranslator(c,name));
        setFont(Global.SansSerif);
    }
    public MenuItemAction (DoActionListener c, String s)
    {	this(c,s,s);
    }
    public void setString (String s)
    {	AT.setString(s);
    }
}
