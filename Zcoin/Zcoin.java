class Zcoin
{
	HashMap<String,Account> account;
	Zcoin()
	{
		account=new HashMap<>();
	}
	Validation validation=Validation.getInstance();
	public void menu()
	{
		while(true)
		{
			System.out.println("\n\t\t\tZCOINS");
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
	public void register()
	{

	}
	public static void main(String[] args)
	{
		Zcoin zc=new Zcoin();
		zc.menu();
	}
}