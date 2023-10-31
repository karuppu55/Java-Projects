import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;
class Diagonal
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("enter Matrix Size:");
		int row=Integer.parseInt(s.nextLine());
		System.out.println("enter A Element :");
		int[][] ar=new int[row][row];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<row;j++)
			{
				ar[i][j]=Integer.parseInt(s.nextLine());
			}
		}
		
		System.out.println("ARRAY :");
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<row;j++)
			{
				System.out.print(" "+ar[i][j]);
			}
			System.out.println();
		}
		int pos=row-1;
		int k=0;
		while(k<row)
		{
			int temp=ar[k][pos];
			ar[k][pos]=ar[k][k];
			ar[k][k]=temp;
			k++;
			pos--;
		}
		System.out.println("OUTPUT :");
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<row;j++)
			{
				System.out.print(" "+ar[i][j]);
			}
					System.out.println();
		}
	}	
}