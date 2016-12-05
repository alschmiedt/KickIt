
//     ---------------------
import java.io.BufferedReader;
import java.io.FileReader;
// -----------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCTestMysql1 {
    
    private static Connection conn;
    private static int target = 1;

//////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////CREATE CONNECTION///////////////////////

    
    public static String [] makeStringArray( ResultSet result){
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
        return (String[]) stringList.toArray();
    	
    }
   /* public static String [] getTeamNames(){
        String teamQuery = "SELECT TeamName, TeamShort from Team";
	    return makeStringArray(teamQuery);    
    }*/
    
 /*   public static String [] getLeagues(){
        String leagueQuery = "SELECT Name from League";
        
	    return makeStringArray(leagueQuery);    
    }*/
    
    public static String [] getLeague(){
        String countryQuery = "SELECT Name from League";
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

    /*public static String [] getMatchDate(){
        String countryQuery = "SELECT Name from Country";
	    return makeStringArray(countryQuery);    
    }*/

    
    public static String findTeamAverage(String teamId){
		Integer homeCount = 0;
		Integer homeScore = 0;
		Integer awayCount = 0;
		Integer awayScore = 0;

    	try {
			Statement s1 = conn.createStatement();
	        String homeQuery = "SELECT count(m.Id) as count, SUM(m.HomeScore) as Avg ";
	        homeQuery = homeQuery + "FROM Team t, Matches m ";
	        homeQuery = homeQuery + "WHERE t.TeamId = m.HomeTeamId"
	        		      + " and t.TeamId = " + teamId 
	        		      + " Group by m.HomeTeamId";
	        ResultSet result = s1.executeQuery(homeQuery);
	        boolean f = result.next(); 
            while (f)
               {
                homeCount = Integer.parseInt(result.getString(1));
                homeScore = Integer.parseInt(result.getString(2));
                f=result.next();
               }

	        String awayQuery = "SELECT count(m.Id) as count, SUM(m.AwayScore) as Avg ";
	        awayQuery = awayQuery + "FROM Team t, Matches m ";
	        awayQuery = awayQuery + "WHERE t.TeamId = m.AwayTeamId"
	        		      + " and t.TeamId = " + teamId 
	        		      + " Group by m.AwayTeamId";
	        
	        ResultSet awayResults = s1.executeQuery(awayQuery);
	        boolean away = awayResults.next(); 
            while (away)
               {
                awayCount = Integer.parseInt(awayResults.getString(1));
                awayScore = Integer.parseInt(awayResults.getString(2));
                away=awayResults.next();
               }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.println(homeScore + ", " + awayScore + ", " + (homeCount) + ", " + awayCount);
        return Double.toString((homeScore + awayScore / (homeCount + awayCount)));

    }
    

    
    public static void main(String args[]) {
	try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
            System.out.println("Driver not found");
            System.out.println(ex);
        };
        GetPropertyValues propValues = new GetPropertyValues();
     //String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/testuser?";
     String url = "jdbc:mysql://" + propValues.getHost()+ "/alschmie?";

        conn = null;
	 try { 
     // conn = DriverManager.getConnection(url, "LOGIN_ID", "PASSWORD");
		conn = DriverManager.getConnection(url +"user="+propValues.getUserName()+"&password="+ propValues.getPassword());
        }
        catch (Exception ex)
        {
            System.out.println("Could not open connection");
            System.out.println(ex);
        };
       
      System.out.println("Connected");

      try {
    	  ScriptRunner runner = new ScriptRunner(conn, false, true);
    	  runner.runScript(new BufferedReader(new FileReader("setup.sql")));

         } catch (Exception ee) {System.out.println(ee);}
 

       try {
     	  ScriptRunner runner = new ScriptRunner(conn, false, true);
     	  runner.runScript(new BufferedReader(new FileReader("build-Country.sql")));
     	  runner.runScript(new BufferedReader(new FileReader("build-League.sql")));
     	  runner.runScript(new BufferedReader(new FileReader("build-Team.sql")));
     	  runner.runScript(new BufferedReader(new FileReader("build-Matches.sql")));
       }  
       catch (Exception ee) {System.out.println(ee);}        


        try {
            conn.close();
        }
        catch (Exception ex)
        {
            System.out.println("Unable to close connection");
        };
    }
}