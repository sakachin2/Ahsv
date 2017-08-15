package jagoclient.board;

import jagoclient.Dump;
import jagoclient.StopThread;

/**
A timer for the goboard. It will call the alarm method of the
board in regular time intervals. This is used to update the
timer.
@see jagoclient.board.TimedBoard
*/

public class GoTimer extends StopThread
{	public long Interval;
	TimedBoard B;
	public GoTimer (TimedBoard b, long i)
	{	Interval=i; B=b;
		start();
	}
	public void run ()
	{	try 
		{	while (!stopped())
			{	sleep(Interval);
				if (stopped())                                     //+3124I~
        			break;                                         //+3124I~
				B.alarm();
			}
		}
		catch (Exception e)
		{                                                          //~1506R~
          	Dump.println(e,"GoTimer Exception");                   //~1506R~
		}                                                          //~1506I~
        if (Dump.Y)	Dump.println("GoTimer stopped");               //~3124I~
	}
}
