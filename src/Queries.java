import java.util.Scanner;
import java.io.File;

public class Queries {
	
	
	public static String getLeagueSelect()
	{
		String leagueSelect = "";	

		try
		{
			Scanner file = new Scanner(new File("lib/leagueSelect.txt"));
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
	
	public static String getFinalSelect()
	{
		String finalSelect = "";
		
		try
		{
			Scanner file = new Scanner(new File("lib/finalStandings.txt"));
			
			while (file.hasNext())
			{
				finalSelect += file.nextLine() + "\n";
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return finalSelect;
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
			Scanner file = new Scanner(new File("lib/averageSelect.txt"));
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
	
	public static String getPercentageSelect()
	{
		String leagueSelect = "";	
		try
		{
			Scanner file = new Scanner(new File("lib/percentageSelect.txt"));
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

	public static String getAllMatches()
	{
		String leagueSelect = "";	
		try
		{
			Scanner file = new Scanner(new File("lib/matchesSelect.txt"));
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
