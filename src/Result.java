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
    	int ret = -1;
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
		PreparedStatement lSelect;
		ResultSet set;
		String[] ret = null;
		
		try
		{
			lSelect = conn.prepareStatement(Queries.getLeagueSelect());
			for (int i = 1; i < 9; i++)
			{
				lSelect.setString(i++, season);
				lSelect.setInt(i, league);
			}
			
			set = lSelect.executeQuery();
			ret = makeStringArray(set);
			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return ret;
	}
    
    //Get the final standings of the season for the given league
    //Returns a String[][] with a String[] for each team holding their name, short, wins, loses, ties, score
    public static String[][] finalSelect(String league, String season)
    {
    	PreparedStatement fSelect;
    	ResultSet set;
    	String[][] ret = null;
    	String[] stringArray = null;
    	ArrayList<ArrayList<String>> tempArray = new ArrayList<>();
    	ArrayList<String> tempLine;
    	int leagueId;
    	
    	if ((leagueId = leagueNameToId(league)) == -1)
    	{
    		return null;
    	}
    	
    	
    	try
    	{
    		fSelect = conn.prepareStatement(Queries.getFinalSelect());
    		
    		fSelect.setInt(1,leagueId);
    		fSelect.setInt(4,leagueId);
    		fSelect.setInt(5,leagueId);
    		fSelect.setInt(8,leagueId);
    		fSelect.setInt(9,leagueId);
    		fSelect.setInt(12,leagueId);
    		fSelect.setInt(13,leagueId);
    		fSelect.setInt(16,leagueId);
    		fSelect.setInt(17,leagueId);
    		fSelect.setInt(20,leagueId);
    		fSelect.setInt(21,leagueId);
    		fSelect.setInt(24,leagueId);
    		fSelect.setString(2, season);
    		fSelect.setString(3, season);
    		fSelect.setString(6, season);
    		fSelect.setString(7, season);
    		fSelect.setString(10, season);
    		fSelect.setString(11, season);
    		fSelect.setString(14, season);
    		fSelect.setString(15, season);
    		fSelect.setString(18, season);
    		fSelect.setString(19, season);
    		fSelect.setString(22, season);
    		fSelect.setString(23, season);
    		
    		set = fSelect.executeQuery();
    		
    		while (set.next())
    		{
    			tempLine = new ArrayList<>();
    			tempLine.add(set.getString(1));
    			tempLine.add(set.getString(2));
    			tempLine.add(set.getString(3));
    			tempLine.add(set.getString(4));
    			tempLine.add(set.getString(5));
    			tempLine.add(set.getString(6));
    			tempArray.add(tempLine);
    		}
    		
    		int i = 0;
    		ret = new String[tempArray.size()][];
    		for (ArrayList<String> list : tempArray)
    		{
    			stringArray = new String[list.size()];
    			stringArray = list.toArray(stringArray);
    			ret[i++] = stringArray;
    		}
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
    
    //Gets the Id for Leagues or Countries - given the table name
    public static int idForLeauge_Country(String table, String name)
    {
    	PreparedStatement select;
    	String query = "SELECT Id FROM " + table + " WHERE Name = ?";
    	ResultSet set = null;
    	
    	try
    	{
    		select = conn.prepareStatement(query);
    		select.setString(1, name);
    		
    		set = select.executeQuery();
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    	return makeIntFromResult(set);
    }
    
    public static int teamIdFromName(String name)
    {
    	PreparedStatement select;
    	String query = "SELECT TeamId FROM Team WHERE TeamName = ? and TeamShort = ?";
    	ResultSet set = null;
    	
    	try
    	{
    		if (name.split(",").length == 2) {
	    		select = conn.prepareStatement(query);
	
	    		select.setString(1, name.split(",")[0]);
	    		select.setString(2, name.split(",")[1].trim());
	    		
	    		set = select.executeQuery();
    		}
    		else
    			return -1;
    		
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
						
			lSelect.setString(1, team.split(",")[0]);
			set = lSelect.executeQuery();
			ret = makeStringArray(set);			
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return ret[1];
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
			lSelect.setString(1, team.split(",")[0]);
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
		int size = 0;
		
		try {
			
			s1 = conn.createStatement();
			result = s1.executeQuery("SELECT count(*) FROM Matches;");
			size = makeIntFromResult(result);
	        result = s1.executeQuery(countryQuery);

	        boolean f = result.next(); 
	        stringList = new String[size][];
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
        String teamQuery = "select concat(TeamName, \",\",TeamShort) from Team;";
		Statement s1;
		ResultSet result = null;
		try {
			s1 = conn.createStatement();
	        result = s1.executeQuery(teamQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return makeStringArray(result);    
    }
    
    public static String[] getMatchInfo(String matchId)
    {
    	String matchQuery = "select l.Name, c.Name, m.Season, m.MatchDate, t.TeamName, t2.TeamName, m.HomeScore, m.AwayScore, t.TeamShort, t2.TeamShort "
    			+ "From Matches m, Team t, League l, Country c, Team t2 "
    			+ "where m.CountryId = c.Id "
    			+ "and m.LeagueId = l.Id "
    			+ "and m.HomeTeamId = t.TeamId "
    			+ "and m.AwayTeamId = t2.TeamId "; 
    	String[] resultStr = new String[9];
    	PreparedStatement p1;
    	Statement s1;
    	ResultSet result;
    	int check;
    	
    	try
    	{
	    	p1 = conn.prepareStatement("SELECT Id FROM Matches WHERE Id = ?;");
	    	p1.setString(1, matchId);
	    	result = p1.executeQuery();
			check = makeIntFromResult(result);
	    	
	    	if (isInt(matchId) && check != -1)
	    		matchQuery += "and m.Id = " + matchId + ";";
	    	else
	    	{
	    		resultStr[0] = "Invalid Match Id";
	    		return resultStr;
	    	}
    		
			s1 = conn.createStatement();
	        result = s1.executeQuery(matchQuery);
	        result.next();
	        resultStr = new String[8];
	        for(int i = 0; i < 8; i++){
	        	resultStr[i] = result.getString(i+1);
	        }
	        
	        resultStr[4] += "," + result.getString(9);
	        resultStr[5] += "," + result.getString(10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	    return resultStr;
    }
    
    public static String getName(String teamName)
    {
    	String name = teamName.split(",")[0];
    	
    	return  "\"" + name +"\"";
    }
    
    public static String getShort(String teamName)
    {
    	String name = teamName.split(",")[1];
    	
    	return  "\"" + name +"\"";
    }

    public static String [][] filterMatches(String teamName, String countryName, String leagueName, String date){
        String matchQuery = Queries.getAllMatches();
        //matchQuery = matchQuery.replaceAll(";", "");
        matchQuery = "select m.Id, l.Name, c.Name, m.Season, m.MatchDate, t.TeamName, t2.TeamName, m.HomeScore, m.AwayScore " +
        "From Matches m, Team t, League l, Country c, Team t2 "
        + "WHERE m.CountryId = c.Id "
        + "and m.LeagueId = l.Id "
        + "and m.HomeTeamId = t.TeamId "
        + "and m.AwayTeamId = t2.TeamId";
		String [][] stringList = null;
		Statement s1;
		ResultSet result = null;

        if (!teamName.equals(" ")){
        	
            matchQuery = matchQuery + " and ((t.TeamName =" +getName(teamName)+ "and t.TeamShort ="+ getShort(teamName)+") or (t2.TeamName ="+ getName(teamName)+ "and t2.TeamShort ="+ getShort(teamName)+"))";
        }
        
        if (!countryName.equals(" ")){
            matchQuery = matchQuery + " and (c.Name = \"" + countryName + "\")";
        }
        
        if (!leagueName.equals(" ")){
            matchQuery = matchQuery + " and (l.Name = \"" + leagueName + "\")";
        }
        
        if (!date.equals(" ")){
            matchQuery = matchQuery + " and (m.MatchDate = \"" + date + "\")";
        }

        matchQuery = matchQuery + ";";
		try {
			s1 = conn.createStatement();
			result = s1.executeQuery("SELECT count(*) FROM Matches;");
			int size = makeIntFromResult(result);
	        result = s1.executeQuery(matchQuery);

	        boolean f = result.next(); 
	        stringList = new String[size][];
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
    
    private static boolean isInt(String i)
    {
    	return i.matches("([0-9]+)");
    }
    
    public static String updateMatches(String id, String country, String league, String season, String date, String home, String away, String hscore, String ascore)
    {
    	String updateFront = "UPDATE Matches SET";
    	String updateBack = " WHERE Id = ";
    	String updateComplete;
    	String setClause = "";
    	PreparedStatement update;
    	ResultSet result;
    	int check;
    	int temp;
    	
    	try
    	{
    		update = conn.prepareStatement("SELECT Id FROM Matches WHERE Id = ?;");
    		update.setString(1, id);
    		result = update.executeQuery();
			check = makeIntFromResult(result);
    	
    	
	    	if (isInt(id) && check != -1)
	    		updateBack += id + ";";
	    	else
	    		return "Invalid Match Id";
	    	
	    	
	    	if (!country.equals("")) {
	    		temp = idForLeauge_Country("Country", country);
	    		if (temp != -1)
	    			setClause += ", CountryId = " + Integer.toString(temp);
	    		else
	    			return "Invalid Country Name";
	    	}
	    	
	    	if (!league.equals("")) {
	    		temp = idForLeauge_Country("League", league);
	    		if (temp != -1)
	    			setClause += ", LeagueId = " + Integer.toString(temp);
	    		else
	    			return "Invalid League Name";
	    	}
    	
	    	if (!season.equals("")) {
	    		if (season.matches("([0-9]{4}/[0-9]{4})"))
	    				setClause += ", Season = \"" + season + "\"";
	    		else
	    			return "Invalid Season";
	    	}
	    	
	    	if (!date.equals("")) {
	    		if (date.matches("([0-9]{4}-[0-9]{2}-[0-9]{2})"))
	    			setClause += ", MatchDate = \"" + date + "\"";
	    		else
	    			return "Invalid Date";
	    	}
	    	
	    	if (!home.equals("")) {
	    		temp = teamIdFromName(home);
	    		if (temp != -1)
	    			setClause += ", HomeTeamId = " + Integer.toString(temp);
	    		else
	    			return "Invalid Team Name";
	    	}
	    	
	    	if (!away.equals("")) {
	    		temp = teamIdFromName(away);
	    		if (temp != -1)
	    			setClause += ", AwayTeamId = " + Integer.toString(temp);
	    		else
	    			return "Invalid Team Name";
	    	}
    	
	    	if (!hscore.equals("")) {
	    		if (isInt(hscore))
	    			setClause += ", HomeScore = " + hscore;
	    		else
	    			return "Invalid Score";
	    	}
	    	
	    	if (!ascore.equals("")) {
	    		if (isInt(ascore))
	    			setClause += ", AwayScore = " + ascore;
	    		else
	    			return "Invalid Score";
	    	}
	    	
	    	updateComplete = updateFront + setClause.substring(1) + updateBack;
    	
       		update.executeUpdate(updateComplete);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}

    	return "OK";
    }
    
    public static String insertMatch(String country, String league, String season, String date, String home, String away, String hscore, String ascore)
    {
    	String insertFront = "INSERT INTO Matches (CountryId, LeagueId, Season, MatchDate, HomeTeamId, AwayTeamId, HomeScore, AwayScore) VALUES (";
    	String insertBack = ")";
    	String insertComplete;
    	String valueClause = "";
    	Statement insert;
    	int temp;
    	
		temp = idForLeauge_Country("Country", country);
		if (temp != -1)
			valueClause += ", " + Integer.toString(temp);
		else
			return "Invalid Country Name";
    	
		temp = idForLeauge_Country("League", league);
		if (temp != -1)
			valueClause += ", " + Integer.toString(temp);
		else
			return "Invalid League Name";
    	
		if (season.matches("([0-9]{4}/[0-9]{4})"))
			valueClause += ", \"" + season + "\"";
		else
			return "Invalid Season";
    	
		if (date.matches("([0-9]{4}-[0-9]{2}-[0-9]{2})"))
			valueClause += ", \"" + date + "\"";
		else
			return "Invalid Date";
    	
		temp = teamIdFromName(home);
		if (temp != -1)
			valueClause += ", " + Integer.toString(temp);
		else
			return "Invalid Team Name";
    	
    	
		temp = teamIdFromName(away);
		if (temp != -1)
			valueClause += ", " + Integer.toString(temp);
		else
			return "Invalid Team Name";
    	
		if (isInt(hscore))
			valueClause += ", " + hscore;
		else
			return "Invalid Score";
    	
    	
		if (isInt(ascore))
			valueClause += ", " + ascore;
		else
			return "Invalid Score";
    	
    	
    	insertComplete = insertFront + valueClause.substring(1) + insertBack;
 	
    	try
    	{
    		insert = conn.createStatement();
    		insert.executeUpdate(insertComplete);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    	return "OK";
    }
    
    public static String deleteFromMatches(String id)
    {
    	PreparedStatement delete;
    	String deleteStatement = "DELETE FROM Matches WHERE Id = ";
    	ResultSet result;
    	int check;
    	
    	try
    	{
    		delete = conn.prepareStatement("SELECT Id FROM Matches WHERE Id = ?");
    		delete.setString(1, id);
    		result = delete.executeQuery();
			check = makeIntFromResult(result);
			
			if (isInt(id) && check != -1)
	    		deleteStatement += id + ";";
	    	else
	    		return "Invalid Match Id";
			
    		delete.executeUpdate(deleteStatement);
    	}
    	catch (Exception e)
    	{
    		System.out.println(e);
    	}
    	
    	return "OK";
    	
    	
    	
    	
    }
}
