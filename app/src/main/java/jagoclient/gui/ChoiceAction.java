package jagoclient.gui;

//import java.awt.*;

import com.Ahsv.awt.Choice;                                        //+2C26R~
import com.Ahsv.awt.ItemEvent;                                     //+2C26R~
import com.Ahsv.awt.ItemListener;                                  //+2C26R~
//import java.awt.event.*;

import jagoclient.Global;

public class ChoiceAction extends Choice	//ChoiceTranslator as inner class for accessivility from awt.Choice//~1219I~
{                                                                   //~1219I~
public class ChoiceTranslator implements ItemListener
{   DoActionListener C;
    String S;
    public Choice Ch;
    public ChoiceTranslator
        (Choice ch, DoActionListener c, String s)
    {   C=c; S=s; Ch=ch;
    }
    public void itemStateChanged (ItemEvent e)
    {   C.itemAction(S,e.getStateChange()==ItemEvent.SELECTED);
    }
}

/**
This is a choice item, which sets a specified font and translates
events into strings, which are passed to the doAction method of the
DoActionListener.
@see jagoclient.gui.CloseFrame#doAction
@see jagoclient.gui.CloseDialog#doAction
*/

//public class ChoiceAction extends Choice                         //~1219R~
    public ChoiceAction (DoActionListener c, String s)
    {   addItemListener(new ChoiceTranslator(this,c,s));
        setFont(Global.SansSerif);
    }
}
