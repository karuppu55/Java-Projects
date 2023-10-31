import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;
class PhrasePrint
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		String[] ar={"1","1","ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};
		System.out.println("enter a String  :");
		String str=s.nextLine();
		int k=0;
		String res="";
		while(k<str.length())
		{
			for(int i=2;i<ar.length;i++)
			{
				for(int j=0;j<ar[i].length();j++)
				{
					if(str.charAt(k)==ar[i].charAt(j))
					{
						res+=""+i+"("+(j+1)+")";
					}
				}
			}
		k++;
		}
		System.out.println("Output :"+res);
	}
}