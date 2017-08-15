package jagoclient.gui;

import com.Ahsv.awt.ActionEvent;                                   //+2C26R~
import com.Ahsv.awt.ActionListener;                                //+2C26R~

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

/**
This is a callback class to act as an ActionListener, which calls
back a DoActionListener on any action passing the string name to
its doAction method. 
@see DoActionListener
@see TextFieldAction
@see jagoclient.gui.CloseFrame#doAction
@see jagoclient.gui.CloseDialog#doAction
*/

class TextFieldActionListener 
	implements ActionListener
{   DoActionListener C;
    String Name;
    public TextFieldActionListener (DoActionListener c, String name)
    {   C=c; Name=name;
    }
    public void actionPerformed (ActionEvent e)
    {   C.doAction(Name);
    }
}

