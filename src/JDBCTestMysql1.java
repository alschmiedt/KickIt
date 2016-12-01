
//     ---------------------
import java.sql.*;
// -----------------------


import java.util.*;
import java.io.*;
import java.lang.*;

public class JDBCTestMysql1 {
    
    private static Connection conn;
    private static int target = 1;

//////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////CREATE CONNECTION///////////////////////

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
	 
     Statement s4 = conn.createStatement();
     /*ResultSet result = s4.executeQuery("SELECT Title, Author FROM Books");
              boolean f = result.next(); 
              while (f)
                 {
                  String s = result.getString(1);
                  String a = result.getString(2);
                  System.out.println(s+", "+ a);
                  f=result.next();
                 }*/
     		
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