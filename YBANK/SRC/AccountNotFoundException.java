package YBank;
public class AccountNotFoundException extends RuntimeException
{
	AccountNotFoundException(String str)
	{
		super(str);
	}
}
