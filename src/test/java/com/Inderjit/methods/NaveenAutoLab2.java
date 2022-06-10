package com.Inderjit.methods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NaveenAutoLab2 {
	WebDriver wd;
	SoftAssert sf = new SoftAssert();

	@BeforeMethod
	public void setupDriver() {

		WebDriverManager.chromedriver().setup();

		wd = new ChromeDriver();

		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wd.manage().window().maximize();
	}

}
