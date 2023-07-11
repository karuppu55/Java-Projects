package LibraryManagement;
import java.io.*;
import java.util.regex.*;
class Validation
{
	static Validation val=null;
	BufferedReader bf=null;
	private Validation()
    {
		if(bf==null)
			bf=new BufferedReader((new InputStreamReader(System.in)));
    }
    public static Validation getInstance()
    {
        if(val==null)
            val=new Validation();
        return val;
    }
	
	public int isDigit(String str) throws IOException
	{
		System.out.print("\n\tEnter "+str+" :");
		String s=bf.readLine();
		while(!Pattern.matches("[0-9]+",s))
		{
			System.out.print("\n\nEnter "+str+" :");
			s=bf.readLine();
		}
		return Integer.parseInt(s);
	}
	public String isLetter(String str) throws IOException
	{
		System.out.print("\n\tEnter "+str+" :");
		String s=bf.readLine();
		while(!Pattern.matches("([a-zA-Z][a-z]*|[a-zA-Z][a-z]*[ ])?([ ]?[a-zA-Z][a-z]*)+",s))
		{
			System.out.print("\n\nEnter "+str+" :");
			s=bf.readLine();
		}
		return s;
	}
	public String isName(String st)  throws IOException
	{
		String name="";		
		System.out.print("\n\tEnter "+st+"	:");
		name=bf.readLine();
		while(!Pattern.matches("([a-zA-Z][a-z]*|[a-zA-Z][a-z]*[ ])?([ ]?[a-zA-Z][a-z]*)+",name))
		{
			System.out.print("\n\t\t\t *Please Enter Correct data	:");
			name=bf.readLine();
		}
		return name;
	}
	public Long isMobile(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\ttENTER "+st+"	:");
		str=bf.readLine();
		while(!Pattern.matches("[7-9][0-9]{9}",str))
		{
			System.out.print("\n\t\t\t *Please Enter Correct MOBILE Number	:");
			str=bf.readLine();
		}
		return Long.parseLong(str);
	}
	public int isAge(String str) throws IOException
	{
		System.out.print("\n\tEnter "+str+" :");
		String s=bf.readLine();
		while(!Pattern.matches("[0-9]{2}",s))
		{
			System.out.print("\n\nEnter "+str+" :");
			s=bf.readLine();
		}
		return Integer.parseInt(s);
	}
	public String isYesorNo(String st)  throws IOException
	{
		String name="";		
		System.out.print("\n\t\t\t"+st+"	:");
		name=bf.readLine();
		while(!Pattern.matches("[YyNn]{1}",name))
		{
			System.out.print("\n\t\t\t *Please Enter Correct data	:");
			name=bf.readLine();
		}
		return name;
	}
	public String isAlphaNumeric(String str) throws IOException
	{
		System.out.print("\n\tEnter "+str+" :");
		String s=bf.readLine();
		while(!Pattern.matches("([a-zA-Z][a-z0-9]*|[a-zA-Z][a-z0-9]*[ ])?([ ]?[a-zA-Z0-9][a-z0-9]*)+",s))
		{
			System.out.print("\n\nEnter "+str+" :");
			s=bf.readLine();
		}
		return s;
	}
}
