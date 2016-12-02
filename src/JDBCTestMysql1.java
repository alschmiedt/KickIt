
//     ---------------------
import java.io.BufferedReader;
import java.io.FileReader;
// -----------------------
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestMysql1 {
    
    private static Connection conn;
    private static int target = 1;

//////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////CREATE CONNECTION///////////////////////

    
    
    public static Double findTeamAverage(String teamId){
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
        return (double) (homeScore + awayScore / (homeCount + awayCount));

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

     //String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/testuser?";
     String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/alschmie?";

        conn = null;
	try { 
     // conn = DriverManager.getConnection(url, "LOGIN_ID", "PASSWORD");
		conn = DriverManager.getConnection(url +"user=alschmie&password=a_1234");
     
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
           }  catch (Exception ee) {System.out.println(ee);}        


        try {
            conn.close();
        }
        catch (Exception ex)
        {
            System.out.println("Unable to close connection");
        };
    }
}