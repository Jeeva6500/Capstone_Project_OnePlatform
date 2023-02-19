package com.natwest.webpage;

import java.io.IOException;
import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
public class RegisterPageTest {

	@Test
	public void RegisterPage() throws IOException {
		
		WebDriver driver = new ChromeDriver();
		
		String url1 = "http://localhost:3000/home";
				
		driver.get(url1);
		
		WebElement registerButton = driver.findElement(By.id("signupbutton"));
		registerButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mainbutton1")));
		WebElement textField = driver.findElement(By.id("mainbutton1"));
		if (textField.isDisplayed()) {		
		System.out.println("Web page loaded");

		}
		else {
			System.out.println("Page not loaded");
		}
	
		driver.close();
	}

}
