import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;
class Bouncing
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("enter BOB POSTION   :");
		int x1=Integer.parseInt(s.nextLine());
		System.out.println("enter BOB DOUNCE DISTANCE :");
		int v1=Integer.parseInt(s.nextLine());
		System.out.println("enter ALICE POSTION   :");
		int x2=Integer.parseInt(s.nextLine());
		System.out.println("enter BOB ALICE DISTANCE :");
		int v2=Integer.parseInt(s.nextLine());
		boolean flag=(v1<v2)&&(x1>x2)&&x1!=0?true:false;
		flag=(v2<v1)&&(x2>x1)&&x2!=0?true:false;
		while(flag)
		{
			x1+=v1;
			x2+=v2;
			if(x1==x2)
			{
				System.out.println("YES");
				break;
			}
		}
		if(!flag)
			System.out.println("NO");
}	
}