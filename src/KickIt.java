
public class KickIt {

	
	
	public static void main(String[] args)
	{
		Result.openConnection();
		Result.updateMatches(1, "France", "England Premier League", "", "", "Oud-Heverlee Leuven,O-H", "", -1, -1);
		
		Result.getMatches();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

		Result.closeConnection();
		

	}
}
