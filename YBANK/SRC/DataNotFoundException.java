package YBank;
public class DataNotFoundException extends RuntimeException
{
	DataNotFoundException(String str)
	{
		super(str);
	}
}
