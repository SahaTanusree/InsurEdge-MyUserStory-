//Task 4:  validate the numbers and text in the pagenation control is 
//clickable and only the current page should be highlighted 
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

public class US17P114Task4 {
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
	public static void ValidatePagination(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		driver.navigate().to("https://qeaskillhub.cognizant.com/AdminAuthorizeCustomer");
		try {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		}catch(Exception e) {
			throw new RuntimeException("Scrolling is not possible");
		}
		try {
			for(int i=2;i<=10;i++) {
				WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table//table/tbody/tr/td["+i+"]")));
				try {
					ele.click();
					System.out.println("Pagination controlis clickable");
				}catch(Exception e) {
					js.executeScript("arguments[0].click();", ele);
				}	
			}
		}catch(Exception e) {
			System.out.println("pagenation control is not clickable");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
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
		ValidatePagination(driver);
		driver.quit();
	}

}
