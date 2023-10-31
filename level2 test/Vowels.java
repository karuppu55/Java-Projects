import java.util.Scanner;
import java.util.Arrays;
class Vowels
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("enter A No of Element :");
		int size=Integer.parseInt(s.nextLine());
		System.out.println("enter A Element :");
		char[] ar=new char[size];
		int len=size-1;
		for(int i=0;i<size;i++)
		{
			ar[i]=s.next().charAt(0);
		}
		for(int i=size-1;i>=0;i--)
		{
			if(ar[i]=='a'||ar[i]=='e'||ar[i]=='i'||ar[i]=='o'||ar[i]=='u')
			{
				char temp=ar[len];
				ar[len]=ar[i];
				ar[i]=temp;
				len--;
			}
		}
		System.out.println("Output :"+Arrays.toString(ar));
	}
}