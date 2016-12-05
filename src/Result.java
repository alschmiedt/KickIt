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
    	stringList.add(" ");
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

    public static String[] percentageSelect(String team)
	{
		Queries query = new Queries();
		PreparedStatement lSelect;
		ResultSet set;
		String[] ret = new String[2];
		
		try
		{
			lSelect = conn.prepareStatement(query.getPercentageSelect());
			lSelect.setString(1, team);
			set = lSelect.executeQuery();
	        boolean f = set.next(); 
	        
	        while (f)
           {
	        	ret[0] = set.getString(1);
	        	ret[1] = set.getString(2);

            f=set.next();
           }
			
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

    public static String [] getCountry(){
        String countryQuery = "SELECT distinct Name from Country;";
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
    
    public static String [] getDates(){
        String countryQuery = "SELECT distinct MatchDate from Matches;";
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
    
    public static String [][] getMatches(){
        String countryQuery = Queries.getAllMatches();
		Statement s1;
		ResultSet result = null;
		String [][] stringList = null;
		
		try {
			s1 = conn.createStatement();
	        result = s1.executeQuery(countryQuery);

	        boolean f = result.next(); 
	        stringList = new String[25979][];
	        int j = 0;
	        while (f)
           {
	        String [] blah = new String[9];
		        for(int i = 0; i < 9; i++){
		        	blah[i] = result.getString(i+1);
		        }
	        stringList[j] = blah;
	        j++;
            f=result.next();
           }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	    return stringList;    
    }
    
    public static String [] getTeam(){
        String countryQuery = "SELECT distinct TeamName from Team Order By TeamName;";
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

    public static String [] filterMatches(String teamName, String countryName, String leagueName, String date){
        String matchQuery = Queries.getAllMatches();
        matchQuery = matchQuery.replaceAll(";", "");
        if (!teamName.equals(" ")){
            matchQuery = matchQuery + " and (t.TeamName = \"" + teamName + "\" OR t2.TeamName = \"" + teamName + "\")";
        }
        
        if (!countryName.equals(" ")){
            matchQuery = matchQuery + " and (c.Name = \"" + countryName + "\")";
        }
        
        if (!leagueName.equals(" ")){
            matchQuery = matchQuery + " and (l.Name = \"" + leagueName + "\")";
        }
        
        if (!date.equals(" ")){
            matchQuery = matchQuery + " and (l.Name = \"" + date + "\")";
        }

        matchQuery = matchQuery + ";";
		Statement s1;
		ResultSet result = null;
		try {
			s1 = conn.createStatement();
	        result = s1.executeQuery(matchQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return makeStringArray(result);    
    }
}
