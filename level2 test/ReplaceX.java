import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;
class ReplaceX
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("enter a String  :");
		String str=s.nextLine();
		System.out.println("enter Pattern :");
		String pattern=s.nextLine();
		String res="";
		boolean flag=false;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)==pattern.charAt(0)&&(i+pattern.length()-1)<str.length())
			{
					String st="";
					flag=true;
					int start=i;
					for(int j=0;j<pattern.length();j++)
					{
						if(str.charAt(start)!=pattern.charAt(j))
						{
							flag=false;
						}
						start++;
						st+=str.charAt(i);
					}
					if(flag)
					{
						i+=pattern.length()-1;
						res+="X";
					}
			}
			if(!flag)
			{
				res+=str.charAt(i);
			}
			if(flag)
				flag=false;
		}
		System.out.println("Output :"+res);
	}
}