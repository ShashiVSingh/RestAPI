package base;

import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import com.wbl.API_Automatiom.Helper.ConfigUtils;

public class BaseAPITest {
	
	protected String endpoint;
	
	@BeforeSuite
	public void beforeSuite(){
		Properties prop = ConfigUtils.loadproperties("Config.properties");
		endpoint = prop.getProperty("url");
	}

}
