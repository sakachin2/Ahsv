package jagoclient.board;

//import java.io.PrintWriter;

//import rene.util.list.ListElement;

/**
This is a special action for marks. It will print it content
depending on the "puresgf" parameter. This is because the
new SGF format no longer allows the "M" tag.
@see jagoclient.board.Action
*/

public class MarkAction extends Action
{	BoardInterface GF;
	public MarkAction (String arg, BoardInterface gf)
	{	super("M",arg);
		GF=gf;
	}
	public MarkAction (BoardInterface gf)
	{	super("M");
		GF=gf;
	}
//    public void print (PrintWriter o)                            //~2C31R~
//    {   if (GF.getParameter("puresgf",false))                    //~2C31R~
//        {   o.println(); o.print("MA");                          //~2C31R~
//            ListElement p=Arguments.first();                     //~2C31R~
//            while (p!=null)                                      //~2C31R~
//            {   o.print("["+(String)(p.content())+"]");          //~2C31R~
//                p=p.next();                                      //~2C31R~
//            }                                                    //~2C31R~
//        }                                                        //~2C31R~
//        else super.print(o);                                     //~2C31R~
//    }                                                            //+2C31R~
}
