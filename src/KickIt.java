
public class KickIt {

	
	
	public static void main(String[] args)
	{
		
		Result.openConnection();

		GUI.createAndShowGUI();

		
		if (args.length == 1 && args[0].equals("start"))
		{
			DataBaseConnection.setupDataBase();
			
			
		}
		GUI.createAndShowGUI();
		
		Result.leagueSelect(1, "2012/2013");
		
		if (args.length == 1 && args[0].equals("drop"))
		{
			DataBaseConnection.dropDataBase();
		}

		
		//Result.closeConnection();
		

	}
}
