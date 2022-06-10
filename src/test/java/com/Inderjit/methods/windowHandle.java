package com.Inderjit.methods;

import static org.testng.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class windowHandle {

	WebDriver wd;
	SoftAssert sf = new SoftAssert();

	@BeforeMethod
	public void setupDriver() {

		WebDriverManager.chromedriver().setup();

		wd = new ChromeDriver();

		wd.get("https://demoqa.com/browser-windows");
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.manage().window().maximize();
	}

	@Test
	public void windowTabTest() {
		String parentHandle = wd.getWindowHandle();
		String secondHandle = "";
		String thirdHandle = "";

		WebElement newTabBtn = wd.findElement(By.cssSelector("#tabButton"));
		newTabBtn.click();

		Set<String> handle2 = wd.getWindowHandles();
		for (String handle : handle2) {
			System.out.println(handle);
			if (!handle.equals(parentHandle)) {
				secondHandle = handle;
			}
		}

		newTabBtn.click();
		Set<String> handle3 = wd.getWindowHandles();
		for (String handle : handle3) {
			if (!handle.equals(parentHandle) && !handle.equals(secondHandle))
				thirdHandle = handle;
		}

		// Switching to windows
		wd.switchTo().window(thirdHandle);

		WebElement getTextFromThirdHandle = wd.findElement(By.id("sampleHeading"));
		System.out.println("Text on Third Handle :" + getTextFromThirdHandle.getText());

		wd.switchTo().window(secondHandle);
		WebElement getTextFromSecondHandle = wd.findElement(By.id("sampleHeading"));
		System.out.println("Text on Second Handle :" + getTextFromSecondHandle.getText());

		wd.switchTo().defaultContent();

		// asserting Text on window message
		WebElement getTextOnParentWindow = wd.findElement(By.cssSelector("main-header"));
		System.out.println("Text present on parent window :" + getTextOnParentWindow.getText());

		Assert.assertEquals(getTextOnParentWindow, false);

		sf.assertEquals(getTextOnParentWindow, "Browser Windows", "Textt not found on new window");
		sf.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
