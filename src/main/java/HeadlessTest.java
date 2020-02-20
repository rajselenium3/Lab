import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

public class HeadlessTest {
	Logger log = LoggerFactory.getLogger(HeadlessTest.class);

	@Test
	public void HeadlessChromeDriverTest() throws IOException {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless","--disable-gpu","--ignore-certificate-errors", "--silent");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.navigate().to("https://www.avenuecode.com/");

		String pageTitle = driver.getTitle();
		log.info("Page opened: {}", pageTitle);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// copying the file into /screenshots directory
		Files.copy(scrFile, new File("output/screenshots/homepage.png"));

		Assert.assertTrue(pageTitle.contains("Avenue Code"));
		log.info("Quitting driver");
		driver.quit();
	}
}