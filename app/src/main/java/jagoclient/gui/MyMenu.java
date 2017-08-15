package jagoclient.gui;

//import java.awt.*;

import com.Ahsv.awt.Menu;                                          //+2C27R~

import jagoclient.Global;

/**
A menu with a specified font.
*/

public class MyMenu extends Menu
{   public MyMenu (String l)
    {   super(l);
        setFont(Global.SansSerif);
    }
}