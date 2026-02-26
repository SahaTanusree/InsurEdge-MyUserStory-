//Task3:Validate that the footer section displays 
//“© InsurEdge. All Rights Reserved” on the first line, 
//with “InsurEdge” in bold, and the First line text displayed in Deep Navy Blue / Oxford Blue

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

public class US17P103Task3 {
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
		
			for(String url:pagesURL) {
				driver.navigate().to(url);
					js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
					System.out.println("URL:"+url);
					ValidText( driver);
			        ValidColor(driver);
			        CheckBold(driver);
					//Thread.sleep(1000);
			}
    	}
	public static void ValidText(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		String ExpectedText="© Copyright InsurEdge. All Rights Reserved";
		try {
			String ActualText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='footer']/div[1]"))).getText();
			if(ActualText.equals(ExpectedText)) {
				System.out.println("PASS:''Copyright InsurEdge. All Rights Reserved'should appear on the First line of the Footer");
			}else {
				System.out.println("Fail:'Copyright InsurEdge. All Rights Reserved'should not appear the Footer");
			}
		}catch(Exception e) {
			throw new RuntimeException("Fail:'Copyright InsurEdge. All Rights Reserved'should not appear the Footer"); 
			//System.out.println();
		}
	}
	public static void ValidColor(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			WebElement ActualText=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='footer']/div[1]")));
			String color = ActualText.getCssValue("color");
			if(color.equals("rgba(1, 41, 112, 1)")||color.equals("#012970")) {
				System.out.println("PASS:Text is in Deep Navy Blue / Oxford Blue");
			}else {
				System.out.println("Fail:Display color is not in the Deep Navy Blue / Oxford Blue");
			}
		}catch(Exception e) {
			throw new RuntimeException("Fail:Display color is not in the Deep Navy Blue / Oxford Blue");
		}
		
	}
	public static void CheckBold(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			WebElement boldtext=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='footer']/div/strong/span")));
			String fontWeight=boldtext.getCssValue("font-weight");
			if(fontWeight.equals("bold")||Integer.parseInt(fontWeight)>=700) {
				System.out.println("PASS:'InsurEdge'is appearing in Bold");
			}else {
				System.out.println("FAIL:'InsurEdge'is not appearing in Bold");
			}
		}catch(Exception e) {
			throw new RuntimeException("FAIL:'InsurEdge'is not appearing in Bold");
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
