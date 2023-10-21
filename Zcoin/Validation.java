import java.util.*;
import java.util.regex.*;
import java.io.*;
final public class Validation
{
	String str;
	Scanner s;
	Console readpassword;
	private  static Validation valid;
	private Validation()
	{
		if(s==null)
			s=new Scanner(System.in);
		if(readpassword==null)
			 readpassword=System.console();
	}
	public  static Validation getInstance()
	{
		if(valid==null)
			valid=new Validation();
		return valid;
	}
	public  String isCharacter(String st)  
	{	
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("([a-zA-Z][a-z]*|[a-zA-Z][a-z]*[ ])?([ ]?[a-zA-Z][a-z]*)+",str))
		{
			System.out.print("\n\t *Please Enter Correct data	:");
			str=s.nextLine();
		}
		return str;
	}
	public  Long isMobile(String st)  
	{
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[7-9][0-9]{9}",str))
		{
			System.out.print("\n\t *Please Enter Correct MOBILE Number	:");
			str=s.nextLine();
		}
		return Long.parseLong(str);
	}
	public  String isAdhar(String st)  
	{
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[0-9]{12}",str))
		{
			System.out.print("\n\t *Please Enter Correct ADHAR Number	:");
			str=s.nextLine();
		}
		return str;
	}
	public  String isPan(String st)  
	{
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]",str))
		{
			System.out.print("\n\t *Please Enter Correct PAN Number	:");
			str=s.nextLine();
		}
		return str;
	}
	public  String isMail(String st)  
	{
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[a-zA-Z][a-zA-Z0-9_.]*@gmail.com",str))
		{
			System.out.print("\n\t *Please Enter Correct MAIL Id	:");
			str=s.nextLine();
		}
		return str;
	}
	public  String isPassword(String st)  
	{
		char[] ch=null;
		System.out.print("\n\t"+st+"	:");
		ch=readpassword.readPassword();
		while(!Pattern.matches("[a-zA-Z0-9]*([!#%><?&*][a-zA-Z0-9]*)+",String.valueOf(ch)))
		{
			System.out.print("\n\t *Please Give Strong PASSWORD	:");
			ch=readpassword.readPassword();
		}
		return String.valueOf(ch);
	}
	public  char isContinue(String st)  
	{
		System.out.print("\n\t "+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[ynYN]{1}",str))
		{
			System.out.print("\n\t * (y or Y )or(n or N) :");
			str=s.nextLine();
		}
		return str.charAt(0);
	}

	public  int isPincode(String st)   
	{
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[1-9]{6}",str))
		{
			System.out.print("\n\t *Please Give Correct PINCODE	:");
			str=s.nextLine();
		}
		return Integer.valueOf(str);
	}
	public  int isNumber(String st)   
	{
		System.out.print("\n\t "+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[$0-9]*",str)||str.isEmpty())
		{
			System.out.print("\n\t *Enter Number in Numerical:");
			str=s.nextLine();
		}
		return Integer.valueOf(str);
	}
	public  String isLocation(String st)   
	{															
		System.out.print("\n\t "+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("([A-Za-z0-9.:_-\\]*)+",str))
		{
			System.out.print("\n\t *ENTER CORRECT FORMAT:");
			str=s.nextLine();
		}
		return str;
	}
	public  String isYesorNo(String st)  
	{	
		System.out.print("\n\t"+st+"	:");
		str=s.nextLine();
		while(!Pattern.matches("[YyNn]{1}",str))
		{
			System.out.print("\n\t *Please Enter Correct data	:");
			str=s.nextLine();
		}
		return str;
	}
}
