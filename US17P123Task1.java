//Task 1: Validate that the "scroll to the top" button appears once the page is scrolled.
package com.tanusree.selenium.AgileInsureEdge;
import java.time.Duration;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P123Task1 {
	public static void scrollTopToBottom(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			WebElement footerLocate=wait.until(ExpectedConditions.elementToBeClickable(By.id("footer")));
			Actions action=new Actions(driver);
			action.moveToElement(footerLocate).perform();
			System.out.println("Using Action class Scroll is done");
		}catch(Exception e) {
			js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
			System.out.println("Using JS Executor Scroll is done");
		}
		
	}
	public static void logIn(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			WebElement id=wait.until(ExpectedConditions.elementToBeClickable(By.id("txtUsername")));
			WebElement pass=wait.until(ExpectedConditions.elementToBeClickable(By.id("txtPassword")));
			WebElement btn=wait.until(ExpectedConditions.elementToBeClickable(By.id("BtnLogin")));
			try {
				id.click();
			}catch(Exception e) {
				js.executeScript("arguments[0].click();", id);
			}
			try {
				id.sendKeys("admin_user");
			}catch(Exception e) {
				js.executeScript("arguments[0].value='admin_user';", id);
			}
			try {
				pass.click();
			}catch(Exception e) {
				js.executeScript("arguments[0].click();", pass);
			}
			try {
				pass.sendKeys("testadmin");	
			}catch(Exception e) {
				js.executeScript("arguments[0].value='testadmin';", pass);
			}
			try {
				btn.click();
			}catch(Exception e) {
				js.executeScript("arguments[0].click();", btn);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	public static void ValidationOfScrollToTheTopButton(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			WebElement scrollToTheTopButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='bi bi-arrow-up-short']")));
			System.out.println("'scroll to the top' button is visible");
		}catch(Exception e) {
			throw new RuntimeException("'scroll to the top' button is not visible");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver =null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter which browser do you want to Automate(Chrome/Edge):");
		String browser= sc.nextLine();		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}else {
			throw new RuntimeException("Browser not Supported");
		}
		driver.get("https://qeaskillhub.cognizant.com/AdminDashboard");
		driver.manage().window().maximize();
		logIn(driver);
		driver.navigate().to("https://qeaskillhub.cognizant.com/AdminAuthorizeCustomer");
		scrollTopToBottom(driver);
		ValidationOfScrollToTheTopButton(driver);
		driver.quit();
		

	}

}
