public class User
{
	public void menu()
	{
		while(true)
		{
			System.out.println("\n\t\t\tUSER");
			switch(validation.isNumber("Enter Your Option"))
			{
				case 1:
						break;
				case 2:
						break;
				case 3:
						register();
						break;
				case 4:
						return;
				default:
					System.out.println("Enter Correct Option");
			}
		}
	}
}