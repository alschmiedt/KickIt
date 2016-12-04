import java.util.Scanner;
import java.io.File;

public class Queries {
	
	private String leagueSelect;
	
	public Queries() {
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
	}
	
	
	public String getLeagueSelect()
	{
		return leagueSelect;
	}
	
	public String getSelectStar()
	{
		return "Select * FROM ?";
	}

}
