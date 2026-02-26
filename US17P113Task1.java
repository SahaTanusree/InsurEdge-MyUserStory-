//Task 1: Given I navigate to the User Details section, then a check box should be 
//visible at the start of  the each row under the User Details column.
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

public class US17P113Task1 {
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
	public static void validateExistenceOfCheckBox(WebDriver driver){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		for(int i=2;i<=5;i++){
			try {
				WebElement checkBox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr["+i+"]/td[1]/input")));
				String type=checkBox.getAttribute("type");
				if(type.equalsIgnoreCase(type)) {
					System.out.println("CheckBox is visible");
				}else {
					System.out.println("CheckBox is not visible");
				}
			}catch(Exception e) {
				throw new RuntimeException("CheckBox is not visible");
			}
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
		logIn(driver);
		driver.navigate().to("https://qeaskillhub.cognizant.com/AdminAuthorizeCustomer");
		validateExistenceOfCheckBox(driver);
		driver.quit();
	}
	
}
