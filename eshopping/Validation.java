package com.zoho.E_Shopping;
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
	public String isAlphaNumeric(String str) throws IOException
	{
		System.out.print("\n\tEnter "+str+" :");
		String s=bf.readLine();
		while(!Pattern.matches("([a-zA-Z][a-z0-9]*|[a-zA-Z][a-z0-9]*[ ])?([ ]?[a-zA-Z][a-z0-9]*)+",s))
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
		System.out.print("\n\tENTER "+st+"	:");
		str=bf.readLine();
		while(!Pattern.matches("[7-9][0-9]{9}",str))
		{
			System.out.print("\n\t\t *ENTER CORRECT MOBILE NUMBER	:");
			str=bf.readLine();
		}
		return Long.parseLong(str);
	}
	public String isUPI(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\tENTER "+st+"	:");
		str=bf.readLine();
		while(!Pattern.matches("[0-9]*@[a-b]*",str))
		{
			System.out.print("\n\t\t *ENTER CORRECT UPI Number	:");
			str=bf.readLine();
		}
		return str;
	}
	public double isAmount(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\tENTER "+st+"	:");
		str=bf.readLine();
		while(!Pattern.matches("[0-9]+[.]{1}[0-9]+",str))
		{
			System.out.print("\n\t\t *ENTER AMOUNT IS CORRECT FORMATE(eg:1000.00):");
			str=bf.readLine();
		}
		return Double.parseDouble(str);
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
		System.out.print("\n\t"+st+"	:");
		name=bf.readLine();
		while(!Pattern.matches("[YyNn]{1}",name))
		{
			System.out.print("\n\t *Please Enter Correct data	:");
			name=bf.readLine();
		}
		return name;
	}
    public String isMail(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\t"+st+"	:");
		str=bf.readLine();
		while(!Pattern.matches("[a-zA-Z][a-zA-Z0-9_.]*@gmail.com",str))
		{
			System.out.print("\n\t *Please Enter Correct MAIL Id	:");
			str=bf.readLine();
		}
		return str;
	}
    public  String isPassword(String st)  throws IOException
	{
		char[] ch=null;
		System.out.print("\n\t"+st+"	:");
		ch=System.console().readPassword();
		while(!Pattern.matches("[a-zA-Z0-9]*([~`! @#$%^&*()_|:;'<,>.?/][a-zA-Z0-9]*)+",String.valueOf(ch)))
		{
			System.out.print("\n\t *Please Give Strong PASSWORD	:");
			ch=System.console().readPassword();
		}
		return String.valueOf(ch);
	}
}