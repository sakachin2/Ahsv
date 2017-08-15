package jagoclient.board;

//import java.io.PrintWriter;

//import rene.util.list.ListElement;

/**
This action class takes special care to print labels in SGF form.
Jago notes labes in consecutive letters, but SGF does not have this
feature, thus it outputs labels as LB[field:letter].
*/

public class LabelAction extends Action
{	BoardInterface GF;
	public LabelAction (String arg, BoardInterface gf)
	{	super("L",arg);
		GF=gf;
	}
	public LabelAction (BoardInterface gf)
	{	super("L");
		GF=gf;
	}
//    public void print (PrintWriter o)                            //+2C31R~
//    {   if (GF.getParameter("puresgf",false))                    //+2C31R~
//        {   o.println();                                         //+2C31R~
//            o.print("LB");                                       //+2C31R~
//            char[] c=new char[1];                                //+2C31R~
//            int i=0;                                             //+2C31R~
//            ListElement p=Arguments.first();                     //+2C31R~
//            while (p!=null)                                      //+2C31R~
//            {   c[0]=(char)('a'+i);                              //+2C31R~
//                o.print("["+(String)(p.content())+":"+new String(c)+"]");//+2C31R~
//                i++;                                             //+2C31R~
//                p=p.next();                                      //+2C31R~
//            }                                                    //+2C31R~
//        }                                                        //+2C31R~
//        else super.print(o);                                     //+2C31R~
//    }                                                            //+2C31R~
}
