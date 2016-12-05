import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;

public class GetPropertyValues {
	String result = "";
	InputStream inputStream;
	Properties prop;
	String propFileName = "dbconf.365";

	public GetPropertyValues(){
		try {
			prop = new Properties();
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			} 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 		
	}
	public String getUserName() {
		String user = "";
		try { 
			user = prop.getProperty("userid");
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return user;
	}
	
	public String getHost() {
		String user = "";
		try { 
			user = prop.getProperty("host");
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return user;
	}

	
	public String getPassword() {
		String user = "";
		try { 
			user = prop.getProperty("password");
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return user;
	}

	
	public void closeFile() {
		try {
			inputStream.close();
		} catch (IOException e) {
			System.out.println("Can't close inputStream file");
			e.printStackTrace();
		}
		
	}

}
