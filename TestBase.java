package Utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
    public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    
    /**
     * @param browser
     * @throws MalformedURLException
     */
    public synchronized void setUp(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "112");
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "rucha.kelkar41");
        ltOptions.put("accessKey", "AEUv1wACh9YaH4Y2v7iYA3ZS6XuxPhcawUZntVpwa11bPdfwLY");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("build", "JUNIT");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-junit");
        MutableCapabilities browserOptions;
        //browserOptions.setCapability("LT:Options", ltOptions);
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.0.0");
        capabilities.setCapability("LT:Options", ltOptions);

        DriverFactory.getInstances().setDriver(new RemoteWebDriver(new URL(hubURL), capabilities));
	}
	
	public WebDriver driver() {
		return DriverFactory.getInstances().getDriver();
	}
}
