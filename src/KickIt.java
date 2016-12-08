
public class KickIt {

	
	
	public static void main(String[] args)
	{
		Result.openConnection();
		//Result.resetConnection();
		//System.out.println(Result.updateMatches("1", "France", "England Premier League", "2008/2009", "2008-10-31", "Oud-Heverlee Leuven, O-H", "", "", ""));
		//System.out.println(Result.insertMatch("France", "England Premier League", "2008/2009", "2008-10-31", "Oud-Heverlee Leuven,O-H", "Oud-Heverlee Leuven, O-H", "1", "2"));
		//System.out.println(Result.deleteFromMatches("26000"));

		//Result.getMatches();
		
		try {
			Thread.sleep(100);
			System.out.println("Hello World");
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

	}
}
