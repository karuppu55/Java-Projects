package YBank;
import YBank.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.sql.SQLException;
public class  BranchDetailsView implements ManageBankViewCaller
{	
	public void bankviewCompile(LoginModel lm)throws SQLException
	{
		this.viewBanks(lm);
	}
	private int selectState(HashMap<Integer,StateModel> states)throws IOException,SQLException
	{
		int id=0;
			id=Validation.getInstance().isNumber("ENTER STATE ID");
			while(!states.containsKey(id))
			{
				System.out.println("\n\t\t\tINVALID ID!!!");
				id=Validation.getInstance().isNumber("ENTER STATE ID");
			}
		return id;
	}
	private int selectDistrict(HashMap<Integer,DistrictModel>  districts)throws IOException,SQLException
	{
		int id=0;
			id=Validation.getInstance().isNumber("ENTER DISTRICT ID");
			while(!districts.containsKey(id))
			{
				System.out.println("\n\t\t\tINVALID ID!!!");
				id=Validation.getInstance().isNumber("ENTER DISTRICT ID");
			}
		return id;
	}
	private int selectCity(HashMap<Integer,CityModel>  cities)throws IOException,SQLException
	{
			int id=0;
			id=Validation.getInstance().isNumber("ENTER CITY ID");
			while(!cities.containsKey(id))
			{
				System.out.println("\n\t\t\tINVALID ID!!!");
				id=Validation.getInstance().isNumber("ENTER CITY ID");
			}
		return id;
	}
	private void viewBanks(LoginModel lm)throws SQLException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*BANK LIST*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			try
			{
				String query="";
				int id=0;
				System.out.println("\n\t\t\t\t1.STATE\n\t\t\t\t2.DISTRICT\n\t\t\t\t3.CITY\n\t\t\t\t4.BRANCH ID");
				int state=0,city=0,district=0,bankid=0;
				HashMap<Integer,StateModel> states=ManageBankControler.getInstance().getStates();
				switch(Validation.getInstance().isNumber("ENTER YOUR CHOICE"))
				{
					case 1:
						AddBranchView.printStates(states);
						state=selectState(states);
						break;
					case 2:
						AddBranchView.printStates(states);
						state=selectState(states);
						HashMap<Integer,DistrictModel> districts=ManageBankControler.getInstance().getDistricts(state);
						AddBranchView.printDistricts(districts);
						district=selectDistrict(districts);
						state=0;
						break;
					case 3: 
						AddBranchView.printStates(states);
						state=selectState(states);
						districts=ManageBankControler.getInstance().getDistricts(state);
						AddBranchView.printDistricts(districts);
						district=selectDistrict(districts);
						HashMap<Integer,CityModel> cities=ManageBankControler.getInstance().getCities(district);
						AddBranchView.printCities(cities);
						city=selectCity(cities);
						state=0;
						district=0;
						break;
					case 4:	
						bankid=Validation.getInstance().isNumber("ENTER BANK ID");
						break;
					default:
						System.out.println("ENTER CORRECT CHOICE");
				}
				ArrayList<BranchModel> al=ManageBankControler.getInstance().getBankDetail(state,district,city,bankid);
				printBank(al);
			ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
			}
			catch(DataNotFoundException dnfe)
			{
				System.out.println("\n"+dnfe.getMessage());
			}
			catch(SQLException sql)
			{
				System.out.println("\n"+sql.getMessage());
			}	
			catch(IOException io)
			{
				System.out.println(io);
			}
			catch(Exception e)
			{
				System.out.println("UNEXPECTED ERROR OCCURE IN MANAGE BANK MENU!!!"+e);
				e.printStackTrace();
			}	
		}while(ch=='y');
	}
	private void printBank(ArrayList<BranchModel> al) throws DataNotFoundException
	{
		System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|","BRANCH ID","STATE","DISTRICT","CITY","BRANCH NAME","TYPE","AC NUMBER"));
		if(al.isEmpty())
		{
			throw new DataNotFoundException("NO BRANCH FOUND!!!");
		}
		for(BranchModel branch:al)
		{
			System.out.println("=====================================================================================================================================================");
			System.out.println(String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|",branch.getBankId(),branch.state.getState(),branch.district.getDistrict(),branch.city.getCity(),branch.getBranchName(),branch.bank.getBankType(),branch.getAcno()));
		}
	}
}
