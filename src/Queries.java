import java.util.Scanner;
import java.io.File;

public class Queries {
	
	
	public static String getLeagueSelect()
	{
		String leagueSelect = "";	

		try
		{
			Scanner file = new Scanner(new File("leagueSelect.txt"));
			String tmp = "";
			
			while (file.hasNext())
			{
				tmp += file.nextLine() + "\n";
			}
						
			leagueSelect = tmp;
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return leagueSelect;
	}
	
	public static String getSelectStar()
	{
		return "Select * FROM ?;";
	}
	
	public static String getAverageSelect()
	{
		String leagueSelect = "";	

		try
		{
			Scanner file = new Scanner(new File("averageSelect.txt"));
			String tmp = "";
			
			while (file.hasNext())
			{
				tmp += file.nextLine() + "\n";
			}
						
			leagueSelect = tmp;
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return leagueSelect;
	}


}
