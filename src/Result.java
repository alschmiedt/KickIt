import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Result {
	
	private static DataBaseConnection database;
	private static Connection conn;
	
	public static void openConnection()
	{
		String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/alschmie?";
		DataBaseConnection newCon = new DataBaseConnection(url);
		database = newCon;
		conn = newCon.getConnection();
	}
	
	public static void closeConnection()
	{
		database.closeConnection(conn);
	}
	
	public static void resetConnection()
	{
		database.resetConnection(conn);
	}
	
	public static String[] leagueSelect(int league, String season)
	{
		Queries query = new Queries();
		PreparedStatement lSelect;
		ResultSet set;
		String[] ret;
		
		try
		{
			lSelect = conn.prepareStatement(query.getLeagueSelect());
			for (int i = 1; i < 9; i++)
			{
				lSelect.setInt(i++, league);
				lSelect.setString(i, season);
			}
			
			set = lSelect.executeQuery();
			
			//turn set into an array
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
	
	public static String[] starSelect(String table)
	{
		Queries query = new Queries();
		PreparedStatement sSelect;
		ResultSet set;
		String[] ret;
		
		try
		{
			sSelect = conn.prepareStatement(query.getSelectStar());
			sSelect.setString(1, table);
			
			set = sSelect.executeQuery();
			
			//turn set into an array
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
	
	

}
