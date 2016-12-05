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
    
   
    private static int makeIntFromResult(ResultSet result)
    {
    	int ret = 0;
    	try
    	{
    		boolean f = result.next();
    		
    		while (f)
    		{
    			ret = Integer.parseInt(result.getString(1));
    			f = result.next();
    		}
    		
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    	return ret;
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
				lSelect.setString(i++, season);
				lSelect.setInt(i, league);
			}
			
			set = lSelect.executeQuery();
			ret = makeStringArray(set);
			for (String s : ret)
				System.out.println(s);			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return ret;
	}
    
    public static int leagueNameToId(String league)
    {
    	PreparedStatement select;
    	String query = "SELECT Id FROM League WHERE Name = ?";
    	ResultSet set = null;
    	
    	try
    	{
    		select = conn.prepareStatement(query);
    		select.setString(1, league);
    		
    		set = select.executeQuery();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    	return makeIntFromResult(set);
    }
	
    public static String averageSelect(String team)
	{
		Queries query = new Queries();
		PreparedStatement lSelect;
		ResultSet set;
		String[] ret = null;
		
		try
		{
			lSelect = conn.prepareStatement(query.getAverageSelect());
			lSelect.setString(1, team);
			set = lSelect.executeQuery();
			ret = makeStringArray(set);
			//turn set into an array
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return ret[0];
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

    public static String [] getTeam(){
        String countryQuery = "SELECT distinct TeamName from Teams;";
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

	
	

}
