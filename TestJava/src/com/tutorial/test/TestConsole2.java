package com.tutorial.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestConsole2 
{

	public static WebDriver driver;
	
	@BeforeSuite
	public void tearUp()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://automationpractice.com");
		driver.manage().window().maximize();	
	}
	
	@Test(priority=1)
	public void test1() throws Exception 
	{ 
		
		WebElement dressesOption=driver.findElement(By.cssSelector(".sf-menu > li:nth-child(2) > a:nth-child(1)"));
		WebElement summerDressesOption=driver.findElement(By.cssSelector(".submenu-container.clearfix.first-in-line-xs>li:nth-child(3)>a")); 
		
		Actions act=new Actions(driver);
		act.moveToElement(dressesOption).build().perform();
		waitForPageToLoad(driver,".submenu-container.clearfix.first-in-line-xs>li:nth-child(3)>a");
		summerDressesOption.click();
		waitForPageToLoad(driver,".category-name");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(2000);
		WebElement printdSummrDress1stImg=driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/h5/a")); 
		Thread.sleep(5000);
		printdSummrDress1stImg.click();
		Thread.sleep(5000);
		WebElement printdSummrDress1stImgToDownload=driver.findElement(By.xpath(".//*[@id='bigpic']")); 
		String logoSRC = printdSummrDress1stImgToDownload.getAttribute("src");
		System.out.println(logoSRC);
		URL imageURL = new URL(logoSRC);
		BufferedImage saveImage = ImageIO.read(imageURL);
		ImageIO.write(saveImage, "png", new File("C:/temp/logo-image.png"));
	}
	
	public void waitForPageToLoad(WebDriver webDriver, String css) 
	{
	    WebDriverWait wait = new WebDriverWait(webDriver, 20);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
	}
	@AfterSuite
	public void tearDown()
	{
		driver.close();	
	}
	
	

}
