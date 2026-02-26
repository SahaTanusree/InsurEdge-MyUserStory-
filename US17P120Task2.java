//Task 2,3,4: Validate that when checkbox is selected and the Delete button is clicked,
//a Delete confirmation popup should be displayed and when “OK” is clicked,
//a validation popup should appear with the message “Select at least one customer”.

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

public class US17P120Task2 {
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
	public static void CheckBoxSelectedOrNot(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement checkBox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[2]/td[1]/input")));
		if(!checkBox.isSelected()) {
			checkBox.click();
			ClickDeleteButton(driver);
		}
	
		
	}
	public static void ClickDeleteButton(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement button=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Delete' and @type='submit']")));
		try {
			button.click();
			System.out.println("Button Clicked by normal click");
			deletePopup(driver);
		}catch(Exception e) {
			js.executeScript("argumnents[0].click();", button);
			System.out.println("Button Clicked by js click");
			deletePopup(driver);
		}
	}
	public static void OkPopup(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement ok=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
		try {
			
			ok.click();
			System.out.println("Ok Popup Handled.....");
		}catch(Exception e) {
			js.executeScript("argumnents[0].click();", ok);
			System.out.println("Ok Popup Handled.....");
		}
		
	}
	public static void deletePopup(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			WebElement ok=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
			try {
				ok.click();
				System.out.println("Popup handled by click Ok Button");
				OkPopup(driver);
			}catch(Exception e) {
				js.executeScript("argumnents[0].click();", ok);
				System.out.println("Popup handled by jsClick in Ok Button");
				OkPopup(driver);
			}
		}catch(Exception e) {
			WebElement cancel=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel']")));
			try {
				cancel.click();
				System.out.println("Popup handled by click cancel Button");
			}catch(Exception e1) {
				js.executeScript("argumnents[0].click();", cancel);
				System.out.println("Popup handled by jsClick in Cancel Button");
			}
		}
	}
	public static void main(String[] args) {
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
		CheckBoxSelectedOrNot(driver);
		//check no CheckBoxSelect
		ClickDeleteButton(driver);
		
		driver.quit();

	}

}
