
/**
 * An exception used to signal that we want to kill the application thread.
 * 
 * @author Charles Hoot
 * @version 2.0
 */

public class KillThreadException extends RuntimeException
{
	public KillThreadException(String s)
	{
		super(s);
	}
}
