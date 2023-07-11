package YBank;
public class InsufficiantBalanceException extends RuntimeException
{
	InsufficiantBalanceException(String str)
	{
		super(str);
	}
}
