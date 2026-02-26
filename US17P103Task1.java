package com.tanusree.selenium.AgileInsureEdge;

import java.time.Duration;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US17P103Task1 {
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


	public static void scrollingPage(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String[]pagesURL= {"https://qeaskillhub.cognizant.com/AdminDashboard",
    			"https://qeaskillhub.cognizant.com/CreateCustomer",
    			"https://qeaskillhub.cognizant.com/AdminViewCustomer",
    			"https://qeaskillhub.cognizant.com/AdminAuthorizeCustomer",
    			"https://qeaskillhub.cognizant.com/AdminCreateMainCategory",
    			"https://qeaskillhub.cognizant.com/AdminCreateSubCategory",
    			"https://qeaskillhub.cognizant.com/AdminCreatePolicy",
    			"https://qeaskillhub.cognizant.com/AdminAuthorizePolicy",
    			"https://qeaskillhub.cognizant.com/AdminViewPolicy",
    			"https://qeaskillhub.cognizant.com/AdminModifyPolicy",
    			"https://qeaskillhub.cognizant.com/AdminAppliedPolicyHolders",
    			"https://qeaskillhub.cognizant.com/AdminApprovedPolicyHolder",
    			"https://qeaskillhub.cognizant.com/AdminPendingPolicyHolder",
    			"https://qeaskillhub.cognizant.com/AdminRejectedPolicyHolder"};
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			for(String url:pagesURL) {
				driver.navigate().to(url);
					//js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
					js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
					Thread.sleep(2000);
					try {
						WebElement footer=wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("footer")));
						System.out.println("PASS->Footer is Displayed on the page:"+url);
					}catch(Exception e) {
						System.out.println("FAIL->Footer is not Displayed on the page:"+url);
					}
					
					
			}
    	}


	public static void main(String[] args) throws Exception {
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
		
		driver.get("https://qeaskillhub.cognizant.com/LoginPage");
		driver.manage().window().maximize();
		logIn(driver);
        scrollingPage(driver);
        driver.quit();


	}

}
