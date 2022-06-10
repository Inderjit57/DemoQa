package com.Inderjit.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClickAndSelect {
	WebDriver driver;
	WebDriverWait wdWait;

	@BeforeMethod
	public void setupDriver() {
//		System.setProperty("webdriver.chrome.driver", "C:\\Everything\\chromedriver\\chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
//		driver.get("https://demoqa.com/checkbox");
		driver.navigate().to("https://demoqa.com/select-menu");

		wdWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();

	}

	@Test(enabled = true)
	public void verifyCheckBox() {
		WebElement checkBox = driver
				.findElement(By.cssSelector(".rct-checkbox svg[class='rct-icon rct-icon-uncheck']"));
		checkBox.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void selectMenuTest() {
		WebElement value = driver
				.findElement(By.xpath("//*[@id=\"withOptGroup\"]/div[1]/div[1]"));
		
		Select selectValue =  new Select(value);
		selectValue.selectByValue("react-select-5-option-1-0");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
