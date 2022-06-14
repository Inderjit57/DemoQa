package com.Inderjit.methods;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Walmart {

	WebDriver wd;
	WebDriverWait wait;
	SoftAssert sf = new SoftAssert();
	Actions actions;

	@BeforeMethod
	public void setupDriver() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions cp = new ChromeOptions();
		cp.addArguments("--incognito");

		wd = new ChromeDriver(cp);

		wd.get("https://www.walmart.ca/en");
		wait = new WebDriverWait(wd, 10);

		wd.manage().window().maximize();
	}

	@Test
	public void checkWindowHandle() {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("accept-privacy-policies")));
		WebElement acceptCookies = wd.findElement(By.id("accept-privacy-policies"));
		acceptCookies.click();

		String parentHandle = wd.getWindowHandle();
		String facebookHandle = "";

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector("ul[data-automation='social-media-icons'] li:nth-of-type(1)")));
		WebElement facebookIcon = wd
				.findElement(By.cssSelector("ul[data-automation='social-media-icons'] li:nth-of-type(1)"));
		facebookIcon.click();

		Set<String> handle1 = wd.getWindowHandles();
		for (String handle : handle1) {
			if (!handle.equals(parentHandle)) {
				facebookHandle = handle;
			}
		}
		wd.switchTo().window(facebookHandle);
		String facebookHandleTitle = wd.getTitle();
		System.out.println("Homepage Title: " + facebookHandleTitle);
		wd.close();
		wd.switchTo().window(parentHandle);

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
