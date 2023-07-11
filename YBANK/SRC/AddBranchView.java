package YBank;
import YBank.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.sql.SQLException;
public class AddBranchView implements ManageBankViewCaller
{	
	public void bankviewCompile(LoginModel lm)throws IOException,SQLException
	{
		this.addBranch(lm);
	}
	public static void printBankType(HashMap<Integer,BranchTypeModel> bt)
	{
		for(Map.Entry<Integer,BranchTypeModel> bank:bt.entrySet())
			System.out.println("\n\t\t\t\t"+bank.getKey()+"-->"+bank.getValue().getBankType());
	}
	private String generateBankAcno(int bankid,long schemecode)
	{
		Accountnumber an=new Accountnumber(bankid,schemecode);
		return an.toString();	
	}
	public  static void printStates(HashMap<Integer,StateModel> states)
	{
		System.out.println("\n\t\t\t\t STATES");
		for(Map.Entry<Integer,StateModel> state:states.entrySet())
			System.out.println("\n\t\t\t\t"+state.getKey()+"-->"+state.getValue().getState());
	}
	 public static void printDistricts(HashMap<Integer,DistrictModel> districts)
	{
		System.out.println("\n\t\t\t\t DISTRICTS");
		for(Map.Entry<Integer,DistrictModel> district:districts.entrySet())
			System.out.println("\n\t\t\t\t"+district.getKey()+"-->"+district.getValue().getDistrict());
	}
	 public static void printCities(HashMap<Integer,CityModel> cities)
	{
		System.out.println("\n\t\t\t\t CITIES");
		for(Map.Entry<Integer,CityModel> city:cities.entrySet())
			System.out.println("\n\t\t\t\t"+city.getKey()+"-->"+city.getValue().getCity());
	}
	private int isState(HashMap<Integer,StateModel> states)throws IOException,SQLException
	{
		int id=0;
		if(!(states.isEmpty())&&Validation.getInstance().isYesorNo("IF STATE EXISTE PRESS Y OR TO ADD STATE PRESS N").equals("y"))
		{
			id=Validation.getInstance().isNumber("ENTER STATE ID");
			while(!states.containsKey(id))
			{
				System.out.println("\n\t\t\tINVALID ID!!!");
				id=Validation.getInstance().isNumber("ENTER STATE ID");
			}
		}
		else
		{
			String statename=Validation.getInstance().isCharacter("ENTER STATE");
			id=ManageBankControler.getInstance().addState(new StateModel(statename));
		}
		return id;
	}
	private int isDistrict(HashMap<Integer,DistrictModel>  districts,int stateid)throws IOException,SQLException
	{
		int id=0;
			if(!(districts.isEmpty())&&Validation.getInstance().isYesorNo("IF DISTRICT EXISTE PRESS Y OR TO ADD STATE PRESS N").equals("y"))
			{
				id=Validation.getInstance().isNumber("ENTER DISTRICT ID");
				while(!districts.containsKey(id))
				{
					System.out.println("\n\t\t\tINVALID ID!!!");
					id=Validation.getInstance().isNumber("ENTER DISTRICT ID");
				}
			}	
			else
			{
				String districtname=Validation.getInstance().isCharacter("ENTER DISTRICT");
				id=ManageBankControler.getInstance().addDistrict(new DistrictModel(districtname,stateid));
			}
		
		return id;
	}
	private int isCity(HashMap<Integer,CityModel>  cities,int districtid)throws IOException,SQLException
	{
		int id=0;
		if(!(cities.isEmpty())&&Validation.getInstance().isYesorNo("IF CITY EXISTE PRESS Y OR TO ADD STATE PRESS N").equals("y"))
		{
			id=Validation.getInstance().isNumber("ENTER CITY ID");
			while(!cities.containsKey(id))
			{
				System.out.println("\n\t\t\tINVALID ID!!!");
				id=Validation.getInstance().isNumber("ENTER CITY ID");
			}
		}
		else
		{
			String cityname=Validation.getInstance().isCharacter("ENTER CITY");
			id=ManageBankControler.getInstance().addCity(new CityModel(cityname,districtid));
		}
		return id;
	}
	private void addBranch(LoginModel lm) throws IOException,SQLException,DataNotFoundException
	{
		char ch=' ';
		do
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\nUSER ID :"+lm.getUserId()+"\t\t\t*ADD NEW BRANCH*\t\tPOSITION :"+lm.job.getJobType());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			HashMap<Integer,StateModel> states=ManageBankControler.getInstance().getStates();
			printStates(states);
			int stateid=isState(states);
			states=ManageBankControler.getInstance().getStates();
			HashMap<Integer,DistrictModel> districts=ManageBankControler.getInstance().getDistricts(stateid);
			printDistricts(districts);
			int districtid=isDistrict(districts,stateid);
			districts=ManageBankControler.getInstance().getDistricts(stateid);
			HashMap<Integer,CityModel> cities=ManageBankControler.getInstance().getCities(districtid);
			printCities(cities);
			int cityid=isCity(cities,districtid);
			cities=ManageBankControler.getInstance().getCities(districtid);
			HashMap<Integer,BranchTypeModel> bt=ManageBankControler.getInstance().getBranchType();
			String branchname=Validation.getInstance().isCharacter("ENTER BRANCH NAME");
			printBankType(bt);
			int banktypeid=Validation.getInstance().isNumber("ENTER BRANCH TYPE ID");
			while(!bt.containsKey(banktypeid))
			{
				System.out.println("\n\t\t\t**ENTER CORRECT BRANCH TYPE ID!!");
				banktypeid=Validation.getInstance().isNumber("ENTER");
			}
			int bankid=ManageBankControler.getInstance().bankid();
			String acno=generateBankAcno(bankid,10010010);
			System.out.println("\n\t\t\tENTERE DATAS==>\n\t\t\t_____________________________\n\n\t\t\tBRANCH NAME	:"+branchname+"\n\t\t\tBRANCH TYPE	:"+bt.get(banktypeid).getBankType()+"\n\t\t\tACCOUNT NUMBER	:"+acno+"\n\t\t\tSTATE		:"+states.get(stateid).getState()+"\n\t\t\tDISTRICT	:"+districts.get(districtid).getDistrict()+"\n\t\t\tCITY		:"+cities.get(cityid).getCity());
			if(Validation.getInstance().isYesorNo("CHECK ABOVE DETAIL AND CLICK (Y) TO CONTINUE").toLowerCase().equals("y"))
			{
				int id1=ManageBankControler.getInstance().addDetail(new BranchModel(cityid,branchname,banktypeid,acno,lm.getUserId()));
				if(id1==0)
				{
					throw new DataNotFoundException("BRANCH NOT ADDED!!!");
				}
				System.out.println("\n\t\t\t=>NEW BRANCH CREATED BANK ID	:"+bankid);
			}
			else
			{
			System.out.println("\n\t\t\t*CANCELLED PROCESS...");
			}
		ch=Character.toLowerCase(Validation.getInstance().isContinue("DO YOU WANT TO CONTINE PRESS (Y) OTHERWISE (N)"));
		}while(ch=='y'); 
	}
}
