package com.Inderjit.methods;

import java.util.List;
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
import org.testng.annotations.AfterMethod;
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
		wait = new WebDriverWait(wd, 10);

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

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='content']/div[1]/div[2]/h1")));

		// Asserting Laptop model Text and stock availablity
		WebElement laptopModel = wd.findElement(By.xpath("//div[@id='content']/div[1]/div[2]/h1"));
		String getTextOfLaptopModel = laptopModel.getText();
		System.out.println("Laptop model " + getTextOfLaptopModel);
		sf.assertEquals(getTextOfLaptopModel, "HP LP3065");

		WebElement availableInStock = wd.findElement(By.xpath("//div[@id='content']/div[1]/div[2]/ul/li[4]"));
		String getTextOfAvailability = availableInStock.getText();
		System.out.println("Availability: " + getTextOfAvailability);
		sf.assertEquals(getTextOfAvailability, "Availability: In Stock");

		// Asserting the laptop price
		WebElement laptopPrice = wd.findElement(By.xpath("//div[@id='content']/div[1]/div[2]/ul[2]/ li/h2"));
		String getLaptopPrice = laptopPrice.getText();
		sf.assertEquals(getLaptopPrice, "$122.00");

		// Add to cart and asserting the text
		WebElement clickAddToCart = wd.findElement(By.cssSelector(".form-group button[id='button-cart']"));
		clickAddToCart.click();

		WebElement sucessMessage = wd.findElement(By.xpath("//*[@id=\"product-product\"]/div[1]"));
		String getSucessMessage = sucessMessage.getText();
		sf.assertEquals(getSucessMessage, "Success: You have added ");

		// Click on shopping cart
		WebElement shoppingCartBtn = wd.findElement(By.cssSelector("a[title='Shopping Cart']"));
		shoppingCartBtn.click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By
				.xpath("//div[@class='col-sm-12'] // div[@class='col-sm-4 col-sm-offset-8']/table/tbody/tr[4]/td[2]")));
		// Asserting price in the shopping cart
		WebElement totalPrice = wd.findElement(By
				.xpath("//div[@class='col-sm-12'] // div[@class='col-sm-4 col-sm-offset-8']/table/tbody/tr[4]/td[2]"));
		String getTotalPrice = totalPrice.getText();
		System.out.println("Total Price of purchase: " + getTotalPrice);
		sf.assertEquals(getTotalPrice, "$122.00");

		// Compare prices from total price in Shopping cart and laptop price
		if (getTotalPrice.equals(getLaptopPrice)) {
			System.out.println("Price is matching");
		} else {
			System.out.println("Price not matching");
		}

		// press checkout
		WebElement checkoutbtn = wd
				.findElement(By.cssSelector("div[class='buttons clearfix'] div[class='pull-right'] a"));
		checkoutbtn.click();

		// Click in radio buttone - guest and click continue
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='radio'] label input[value='guest']")));
		WebElement guestRadiobtn = wd.findElement(By.cssSelector("div[class='radio'] label input[value='guest']"));
		guestRadiobtn.click();

		WebElement continueBtn = wd.findElement(By.xpath("//input[@id='button-account']"));
		continueBtn.click();

		// Filling form on checkout page
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#input-payment-firstname")));
		WebElement inputFirstName = wd.findElement(By.cssSelector("#input-payment-firstname"));
		inputFirstName.sendKeys("Inderjit");

		WebElement inputLastName = wd.findElement(By.cssSelector("#input-payment-lastname"));
		inputLastName.sendKeys("Singh");

		WebElement inputEmail = wd.findElement(By.cssSelector("#input-payment-email"));
		inputEmail.sendKeys("singhinderjit012021@gmail.com");

		WebElement inputPhoneNo = wd.findElement(By.cssSelector("#input-payment-telephone"));
		inputPhoneNo.sendKeys("1234567890");

		WebElement inputAddress1 = wd.findElement(By.cssSelector("#input-payment-address-1"));
		inputAddress1.sendKeys("123 Random drive");

		WebElement inputCity = wd.findElement(By.cssSelector("#input-payment-city"));
		inputCity.sendKeys("Btown");

		WebElement inputPostCode = wd.findElement(By.cssSelector("#input-payment-postcode"));
		inputPostCode.sendKeys("435667");

		// select country
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#input-payment-country")));
		WebElement countryList = wd.findElement(By.cssSelector("#input-payment-country"));
		Select selectCountry = new Select(countryList);
		selectCountry.selectByValue("38");

		// select province
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#input-payment-zone")));
		WebElement provinceList = wd.findElement(By.cssSelector("#input-payment-zone"));
		Select selectProvince = new Select(provinceList);
		selectProvince.selectByIndex(10);

		WebElement continueBtnOnCheckout = wd.findElement(By.cssSelector("input[id='button-guest']"));
		continueBtnOnCheckout.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='button-shipping-method']")));
		WebElement continueBtnOnDeliveryMethod = wd.findElement(By.cssSelector("input[id='button-shipping-method']"));
		continueBtnOnDeliveryMethod.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='agree']")));
		WebElement agreeBtn = wd.findElement(By.cssSelector("input[name='agree']"));
		agreeBtn.click();

		WebElement continuePaymentMethod = wd.findElement(By.cssSelector("input[id='button-payment-method']"));
		continuePaymentMethod.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='button-confirm']")));
		WebElement confirmBtn = wd.findElement(By.cssSelector("input[id='button-confirm']"));
		confirmBtn.click();

		// Confirm Message
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#content h1")));
		WebElement confirmationMessage = wd.findElement(By.cssSelector("#content h1"));
		String getConfirmationText = confirmationMessage.getText();
		sf.assertEquals(getConfirmationText, "Your order has been placed!");
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a[class='btn btn-primary']")));
		WebElement continueBtnOnConfirmation = wd.findElement(By.cssSelector("a[class='btn btn-primary']"));
		continueBtnOnConfirmation.click();

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
		for (WebElement elements : searchCriteria) {
			count++;
		}
		System.out.println(count);

	}
	
	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
