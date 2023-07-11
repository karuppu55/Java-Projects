package YBank;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
final public class Validation
{
	static Validation val=null;
	BufferedReader reader=null;
	Console readpassword=null;
	private Validation()
    {
		if(reader==null)
			reader=new BufferedReader((new InputStreamReader(System.in)));
		if(readpassword==null)
			readpassword= System.console();
    }
    public static Validation getInstance()
    {
        if(val==null)
            val=new Validation();
        return val;
    }
	public  String isCharacter(String st)  throws IOException
	{
		String name="";		
		System.out.print("\n\t\t\t"+st+"	:");
		name=reader.readLine();
		while(!Pattern.matches("([a-zA-Z][a-z]*|[a-zA-Z][a-z]*[ ])?([ ]?[a-zA-Z][a-z]*)+",name))
		{
			System.out.print("\n\t\t\t *Please Enter Correct data	:");
			name=reader.readLine();
		}
		return name;
	}
	public  String isMobile(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\t\t\t"+st+"	:");
		str=reader.readLine();
		while(!Pattern.matches("[7-9][0-9]{9}",str))
		{
			System.out.print("\n\t\t\t *Please Enter Correct MOBILE Number	:");
			str=reader.readLine();
		}
		return str;
	}
	public  String isAdhar(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\t\t\t"+st+"	:");
		str=reader.readLine();
		while(!Pattern.matches("[0-9]{12}",str))
		{
			System.out.print("\n\t\t\t *Please Enter Correct ADHAR Number	:");
			str=reader.readLine();
		}
		return str;
	}
	public  String isPan(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\t\t\t"+st+"	:");
		str=reader.readLine();
		while(!Pattern.matches("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]",str))
		{
			System.out.print("\n\t\t\t *Please Enter Correct PAN Number	:");
			str=reader.readLine();
		}
		return str;
	}
	public  String isMail(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\t\t\t"+st+"	:");
		str=reader.readLine();
		while(!Pattern.matches("[a-zA-Z][a-zA-Z0-9_.]*@gmail.com",str))
		{
			System.out.print("\n\t\t\t *Please Enter Correct MAIL Id	:");
			str=reader.readLine();
		}
		return str;
	}
	public  String isDate(String st)  throws IOException
	{
		String str="";
		System.out.print("\n\t\t\t"+st+"	:");
		str=reader.readLine();
		while(!datecheck(str))
		{
			System.out.print("\n\t\t\t *Please Enter Correct DATE	:");
			str=reader.readLine();
		}
		return str;
	}
	public  boolean datecheck(String dates)  throws IOException
   	{
        	if(Pattern.matches("\\d{2}-\\d{2}-\\d{4}",dates))
        	{
        		int day=Integer.valueOf(dates.substring(0,2));
     		 	int month=Integer.valueOf(dates.substring(3,5));
     		        int year=Integer.valueOf(dates.substring(6,10));
            	        if(month<1||month>12||day<1||day>31)
            		{
            	    		return false;
            		}
            		if(month==4||month==6||month==9||month==11)
            		{
            		    if(day>30)
            		    {
            		        return false;
            		    }
            		}
            		if(month==2)
            		{
            			boolean leep=(year%4==0&&year%100==0&&year%100==0);
            		       if(leep&&day>29||!leep&&day>28)
               	 	{
              		  	    return false;
                		}
            		}
        	}
        	else
        	{
        		return false;
       	}
        	return true;   
    	}
	public  String isPassword(String st)  throws IOException
	{
		char[] ch=null;
		System.out.print("\n\t\t\t"+st+"	:");
		ch=readpassword.readPassword();
		while(!Pattern.matches("[a-zA-Z0-9]*([~`! @#$%^&*()_|:;'<,>.?/][a-zA-Z0-9]*)+",String.valueOf(ch)))
		{
			System.out.print("\n\t\t\t *Please Give Strong PASSWORD	:");
			ch=readpassword.readPassword();
		}
		return String.valueOf(ch);
	}
	public  char isContinue(String st)  throws IOException
	{
		String ch="";
		System.out.print("\n\t\t\t "+st+"	:");
		ch=reader.readLine();
		while(!Pattern.matches("[ynYN]{1}",String.valueOf(ch)))
		{
			System.out.print("\n\t\t\t * (y or Y )or(n or N) :");
			ch=reader.readLine();
		}
		return ch.charAt(0);
	}

	public  int isPincode(String st)   throws IOException
	{
		String num="";
		System.out.print("\n\t\t\t"+st+"	:");
		num=reader.readLine();
		while(!Pattern.matches("[1-9]{6}",num))
		{
			System.out.print("\n\t\t\t *Please Give Correct PINCODE	:");
			num=reader.readLine();
		}
		return Integer.valueOf(num);
	}
	public  int isNumber(String st)   throws IOException
	{
		String num="";
		System.out.print("\n\t\t\t "+st+"	:");
		num=reader.readLine();
		while(!Pattern.matches("[$0-9]*",num)||num.isEmpty())
		{
			System.out.print("\n\t\t\t *Please Give CORRECT FORMAT	:");
			num=reader.readLine();
		}
		return Integer.valueOf(num);
	}
	public  String isAccountno(String st)   throws IOException
	{
		String num="";
		System.out.print("\n\t\t\t"+st+"	:");
		num=reader.readLine();
		while(!Pattern.matches("[0-9]{12}",num))
		{
			System.out.print("\n\t\t\t *Please Give ACCOUNT NUMBERC ORRECT FORMAT:");
			num=reader.readLine();
		}
		return num;
	}
	public  double isAmount(String st)   throws IOException
	{
		String num="";																
		System.out.print("\n\t\t\t "+st+"	:");
		num=reader.readLine();
		while(!Pattern.matches("[0-9]*[.]{1}[0-9]*",num))
		{
			System.out.print("\n\t\t\t *Please Give AMOUNT in Decimal	:");
			num=reader.readLine();
		}
		return Double.parseDouble(num);
	}
	public  String isLocation(String st)   throws IOException
	{
		String num="";																
		System.out.print("\n\t\t\t "+st+"	:");
		num=reader.readLine();
		while(!Pattern.matches("([/]{2}[A-Za-z0-9._-]*)+",num))
		{
			System.out.print("\n\t\t\t *ENTER CORRECT FORMAT:");
			num=reader.readLine();
		}
		return num;
	}
	public  java.sql.Date toDate(String sdate) 
	{
		java.sql.Date sqlDate=null;
		try
		{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = sdf.parse(sdate); 
                sqlDate = new java.sql.Date(date.getTime());         
                }
                catch(ParseException e)
                {
                	System.out.println(e);
                }
                return sqlDate;
	} 
	public  String isYesorNo(String st)  throws IOException
	{
		String name="";		
		System.out.print("\n\t\t\t"+st+"	:");
		name=reader.readLine();
		while(!Pattern.matches("[YyNn]{1}",name))
		{
			System.out.print("\n\t\t\t *Please Enter Correct data	:");
			name=reader.readLine();
		}
		return name;
	}
}
