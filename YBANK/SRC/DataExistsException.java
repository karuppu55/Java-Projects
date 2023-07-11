package YBank;
public class DataExistsException extends RuntimeException
{
	DataExistsException(String str)
	{
		super(str);
	}
}
