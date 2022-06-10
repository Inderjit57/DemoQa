package com.Inderjit.methods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NaveenAutomationLab {
	WebDriver wd;
	WebDriverWait wait;
	SoftAssert sf = new SoftAssert();
	Actions actions;

	@BeforeMethod
	public void setupDriver() {

		WebDriverManager.chromedriver().setup();

		wd = new ChromeDriver();

		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
//		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(wd, 20);

		wd.manage().window().maximize();
	}

	@Test
	public void purchaseItem() {

		// Click on laptop and notebook Tab on homepage
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/a")));
		WebElement moveToLaptopBtn = wd.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/a"));
		moveToLaptopBtn.click();

		// Click on Show all laptops
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/div/a")));
		WebElement clickShowAllLaptop = wd.findElement(By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/div/a"));
		clickShowAllLaptop.click();

		// Go to SortBy dropdown -Model (A-Z)
		WebElement sortByDropDown = wd.findElement(By.cssSelector(".col-md-4.col-xs-6 div select"));
		Select selectfromSortByList = new Select(sortByDropDown);
		selectfromSortByList.selectByVisibleText("Model (A - Z)");

		// Select from Show dropdown
		WebElement showDropDown = wd.findElement(By.cssSelector(".col-md-3.col-xs-6 div select"));
		Select selectFromShowDropDown = new Select(showDropDown);
		selectFromShowDropDown.selectByVisibleText("100");

		// Click on HP LP3065
		WebElement clickHP3065 = wd.findElement(
				By.cssSelector("#product-category div div[class='col-sm-9'] div:nth-of-type(5) div div a img"));
		clickHP3065.click();

	}

}
