package com.datadriven.base;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BaseUI {

	public WebDriver driver;
	public Properties prop;

	// open the WebBrowser
	public void InvokeBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\ObjectRepository\\projectConfig.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// open the WebSiteURL
	public void getURL(String WebSiteURLkey) {
		driver.get(prop.getProperty(WebSiteURLkey));
	}

	// Click on the Element
	public void ElementClick(String XpathKey) {
		getElement(XpathKey).click();
	}

	// Enter the text in th textField
	public void sendText(String XpathKey, String data) {
		getElement(XpathKey).sendKeys(data);
	}

	// Click Enter Button
	public void ClickEnter(String XpathKey) {
		getElement(XpathKey).sendKeys(Keys.ENTER);
	}

	// Close the browser
	public void closeBrowser() {
		driver.quit();
	}
	
	
	
	//To select the date from calander
	public void selectDate(String XpathKey1,String XpathKey2,String XpathKey3,String XpathKey4) {
		String month = prop.getProperty(XpathKey3);
		String exp_date = prop.getProperty(XpathKey4);
		while (true) {
			String text=getElement(XpathKey1).getText();
			//String text = driver.findElement(By.xpath("//div[@class='_2gw3JEPK']")).getText();
			if (text.equals(month)) {
				break;
			} else {
				getElement(XpathKey2).click();
				//driver.findElement(By.xpath("//body/div[14]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")).click();
			}
		}

		List<WebElement> allDates = driver
				.findElements(By.xpath("//body/div[14]/div[1]//div[2]/div[1]/div[1]/div[2]/div[1]/div[3]/div/div"));
		for (WebElement element : allDates) {
			String date_text = element.getText();

			if (date_text.equals(exp_date)) {
				element.click();
				break;
			}
		}

	}
	
	public void DisplayHotel(int value,String namekey,String pricekey,String totalkey)
	{
		System.out.println("Details of 3 Holiday Home Stay:");
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println();
		System.out.println("Details of home stay "+value);
		System.out.println();
		// details of 1st hotel
		String HotelName = getElement(namekey).getText();
		String HotelPriceperNight = getElement(pricekey).getText();
		String TotalHotelPrice = getElement(totalkey).getText();
		System.out.println("Home Stay Name: " + HotelName);
		System.out.println("Home Stay Price Per Night: " + HotelPriceperNight.substring(1));
		System.out.println("Home Stay Total Price: " + TotalHotelPrice.substring(1));
	}
	
	
	public void DisplayCruise(String Overviewkey,String Launchkey)
	{
				String parent = driver.getWindowHandle();
				for (String child : driver.getWindowHandles()) {
					if (!child.equalsIgnoreCase(parent)) {
						driver.switchTo().window(child);
					}
				}
				System.out.println("\nDeatils of Cruise Ship");
				System.out.println("\n----------------------");
				// print the languages available using list
				System.out.println("\nLanguages Available:");

				List<WebElement> lan = driver.findElements(By.xpath(
						"//body/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/ul[1]"));
				for (WebElement cl : lan) {
					String abt = cl.getText();
					System.out.println(abt);
				}
				// Information of passengers and crew members
				String k = getElement(Overviewkey).getText();
				System.out.println("\nOverveiw of ship:\n" + k);
				String launched = getElement(Launchkey).getText();
				System.out.println(launched);
	}
	

	

	// To find the Locator on the basis of id or xpath
	public WebElement getElement(String LocatorKey) {
		WebElement element = null;
		try {

			if (LocatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(LocatorKey)));
			} else if (LocatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(LocatorKey)));
			} else {
				Assert.fail("Failing the testcase invalid loctor:" + LocatorKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failing the testcase:" + e.getMessage());
		}
		return element;

	}

}
