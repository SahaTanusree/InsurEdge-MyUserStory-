package com.tanusree.selenium.AgileInsureEdge;

//Task 5: Validate that “QEA Skill Enable Function” is displayed in Royal Blue color and it’s clickable.

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

public class US17P103Task5 {
	
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
	
	public static void ValidColor(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			WebElement ActualText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='footer']/div[2]/a")));
			String color=ActualText.getCssValue("color");
			if(color.equals("rgba(65, 84, 241, 1)")||color.equals("#4154F1")) {
				System.out.println("PASS:'QEA Skill Enable Function'is written in Royal Blue Color");
			}else {
				System.out.println("FAIL:'QEA Skill Enable Function' is not written in Royal Blue Color");
			}
		}catch(Exception e) {
			throw new RuntimeException("FAIL:'QEA Skill Enable Function' is not written in Royal Blue Color");
		}
		
	}
	public static void Clickable(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			WebElement ActualText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='footer']/div[2]/a")));
			try {
				ActualText.click();
				System.out.println("PASS:'QEA Skill Enable Function' this is clickable");
			}catch(Exception e) {
				js.executeScript("arguments[0].click();", ActualText);
				System.out.println("PASS:'QEA Skill Enable Function' this is clickable");
			}
			
		}catch(Exception e) {
			throw new RuntimeException("FAIL:'QEA Skill Enable Function' this is not clickable");
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
		
			for(String url:pagesURL) {
				driver.navigate().to(url);
					js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
					System.out.println("URL:"+url);
			        ValidColor(driver);
			        Clickable(driver);
					//Thread.sleep(1000);
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
		driver.get("https://qeaskillhub.cognizant.com/AdminDashboard");
		driver.manage().window().maximize();
		logIn(driver);
		scrollingPage(driver);  
        driver.quit();
	}

}
