public class Agent
{
	public void menu()
	{
		while(true)
		{
			System.out.println("\n\t\t\tUSER");
			System.out.println("\n\t\t\tUSER");
			switch(validation.isNumber("Enter Your Option"))
			{
				case 1:
						break;
				case 2:
						break;
				case 4:
						return;
				default:
					System.out.println("Enter Correct Option");
			}
		}
	}
}