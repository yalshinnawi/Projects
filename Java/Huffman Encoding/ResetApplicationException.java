
/**
 * An exception used to signal that we want to reset the application 
 * in the current thread.
 * 
 * @author Charles Hoot
 * @version 2.0
 */

public class ResetApplicationException extends RuntimeException
{
	public ResetApplicationException(String s)
	{
		super(s);
	}
}
