 package hooks;


import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;

public class MyHooks {

	WebDriver driver;
	private ConfigReader configReader;
	@Before
	public void setup() {
		configReader = new ConfigReader();
		Properties prop = configReader.intializeProperties();
		driver= DriverFactory.initializeBrowser(prop.getProperty("browser"));
	//	driver = DriverFactory.getDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@After
	public void tearDown(Scenario scenario) {
		
		String scenarioName = scenario.getName().replaceAll(" ","_");
		if(scenario.isFailed()) {
		byte[]	srcScreenhot =((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(srcScreenhot, "image/png", scenarioName);
		}
		driver.quit();
		
		
	}
	
}
