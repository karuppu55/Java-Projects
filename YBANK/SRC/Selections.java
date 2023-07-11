package YBank.utils;
import YBank.*;
public class Selections
{
	/*Select Gender*/
	public static String gender(int choice) {
		String gen;
		switch(choice) {
		case 1:
			gen="Male";
			return gen;
		case 2:
			gen="Female";
			return gen;
		case 3:
			gen="transgender";
			return gen;
		}
		return null;
	}
	public static boolean empselect(int choice) {
		boolean status;
		switch(choice) 
		{
		case 1:
			status=true;
			return status;
		case 2:
			status=false;
			return status;
		}
		return false;
	}
}
