import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	
	private static Connection conn;
	
	
	public DataBaseConnection(String url)
	{
		try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
            System.out.println("Driver not found");
            System.out.println(ex);
        };

     //String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/testuser?";
     //String url = "jdbc:mysql://cslvm74.csc.calpoly.edu/alschmie?";

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
        

	}
	
	public static void setupDataBase()
	{
		try 
		{
	 	  ScriptRunner runner = new ScriptRunner(conn, false, true);
	 	  runner.runScript(new BufferedReader(new FileReader("lib/setup.sql")));
	 	  runner.runScript(new BufferedReader(new FileReader("lib/build-Country.sql")));
	 	  runner.runScript(new BufferedReader(new FileReader("lib/build-League.sql")));
	 	  runner.runScript(new BufferedReader(new FileReader("lib/build-Team.sql")));
	 	  runner.runScript(new BufferedReader(new FileReader("lib/build-Matches.sql")));

        }  
        catch (Exception ee) {System.out.println(ee);} 
	}
	
	public static void dropDataBase()
	{
		try
		{
	    	ScriptRunner runner = new ScriptRunner(conn, false, true);
	    	runner.runScript(new BufferedReader(new FileReader("cleanup.sql")));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	
	public void closeConnection(Connection connection)
	{
        try {
            connection.close();
        }
        catch (Exception ex)
        {
            System.out.println("Unable to close connection");
        };
	}
	
	public Connection getConnection()
	{
		return conn;
	}
	
	public void resetConnection(Connection connection)
	{
		try {
			ScriptRunner runner = new ScriptRunner(connection, false, true);
			runner.runScript(new BufferedReader(new FileReader("lib/cleanup.sql")));
			runner.runScript(new BufferedReader(new FileReader("lib/setup.sql")));
			runner.runScript(new BufferedReader(new FileReader("lib/build-Country.sql")));
			runner.runScript(new BufferedReader(new FileReader("lib/build-League.sql")));
			runner.runScript(new BufferedReader(new FileReader("lib/build-Team.sql")));
			runner.runScript(new BufferedReader(new FileReader("lib/build-Matches.sql")));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}
