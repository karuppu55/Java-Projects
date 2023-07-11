package YBank;
public class NotEligibleException extends RuntimeException
{
	NotEligibleException(String str)
	{
		super(str);
	}
}
