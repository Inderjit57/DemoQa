package com.Inderjit.methods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NaveenAutomationSearchCriteria {
	WebDriver wd;
	WebDriverWait wait;
	SoftAssert sf = new SoftAssert();

	@BeforeMethod
	public void setupDriver() {
		WebDriverManager.chromedriver().setup();

		wd = new ChromeDriver();

		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wait = new WebDriverWait(wd, 20);

		wd.manage().window().maximize();
	}
	
	@Test
	public void testSearchCriteria() {
		// Search for content in search bar
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#search input")));
				WebElement searchInput = wd.findElement(By.cssSelector("#search input"));
				searchInput.sendKeys("camera");
				WebElement searchBtn = wd.findElement(By.cssSelector("#search span button"));
				searchBtn.click();

				// click checkbox search in product description
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#description")));
				WebElement descriptionCheckbox = wd.findElement(By.cssSelector("#description"));
				descriptionCheckbox.click();

				WebElement searchBtn2 = wd.findElement(By.cssSelector("input[id='button-search']"));
				searchBtn2.click();

				int count = 0;
				List<WebElement> searchCriteria = wd.findElements(By.xpath("//*[@id='content']/div[3]/div"));
				sf.assertEquals(7, searchCriteria.size());
				System.out.println("Number of items as per search criteria: " + searchCriteria.size());
				
				sf.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
