import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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
    private static String [] makeStringArray( ResultSet result){
    	ArrayList<String> stringList = new ArrayList<String>();
		try {
	        boolean f = result.next(); 
	        
	        while (f)
           {
        	stringList.add(result.getString(1));
            f=result.next();
           }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] leagueArr = new String[stringList.size()];
		leagueArr = stringList.toArray(leagueArr);


        return leagueArr;
    	
    }
    
    public static String[] leagueSelect(int league, String season)
	{
		Queries query = new Queries();
		PreparedStatement lSelect;
		ResultSet set;
		String[] ret = null;
		
		try
		{
			lSelect = conn.prepareStatement(query.getLeagueSelect());
			for (int i = 1; i < 9; i++)
			{
				lSelect.setInt(i++, league);
				lSelect.setString(i, season);
			}
			
			set = lSelect.executeQuery();
			ret = makeStringArray(set);
			//turn set into an array
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return ret;
	}
	
    public static String [] getLeague(){
        String countryQuery = "SELECT Name from League;";
		Statement s1;
		ResultSet result = null;
		try {
			s1 = conn.createStatement();
	        result = s1.executeQuery(countryQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return makeStringArray(result);    
    }

    public static String [] getSeason(){
        String countryQuery = "SELECT distinct Season from Matches;";
		Statement s1;
		ResultSet result = null;
		try {
			s1 = conn.createStatement();
	        result = s1.executeQuery(countryQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return makeStringArray(result);    
    }

    
	public static String[] starSelect(String table)
	{
		Queries query = new Queries();
		PreparedStatement sSelect;
		ResultSet set;
		String[] ret = null;
		
		try
		{
			sSelect = conn.prepareStatement(query.getSelectStar());
			sSelect.setString(1, table);
			
			set = sSelect.executeQuery();
			ret = makeStringArray(set);
			//turn set into an array
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return ret;
	}
	
	

}
