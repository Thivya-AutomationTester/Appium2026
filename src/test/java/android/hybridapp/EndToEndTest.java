package android.hybridapp;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import android.nativeapp.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndTest extends BaseTest {

	@Test(enabled = false)
	public void addToCart() throws InterruptedException {
		// click radio button
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")"))
				.click();
		// select dropdown value
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));"))
				.click();
		// click button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// toast message verification
		String errorMessage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		Assert.assertEquals(errorMessage, "Please enter your name");
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rihana");
		// click button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// scroll to the specific product
		String productName = "Jordan Lift Off";
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + productName + "\"));"));

		// select product and add to cart
		List<WebElement> productlist = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
		System.out.println(productlist.size());
		for (int i = 0; i < productlist.size(); i++) {
			String actualProduct = productlist.get(i).getText();

			if (actualProduct.equalsIgnoreCase(productName)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}

		// Go to cart page and validate if the added product is displayed
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

		String cartProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(cartProduct, productName);

	}

	@Test
	public void placeOrder() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rihana");
		// click button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

		List<WebElement> prodructprices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		Double sum = 0.0;

		for (int i = 0; i < prodructprices.size(); i++) {
			String text = prodructprices.get(i).getText();
			sum += Double.parseDouble(text.substring(1));
		}
		String displayedAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
				.getText();
		Double formattedAmount = Double.parseDouble(displayedAmount.substring(1));
		Assert.assertEquals(formattedAmount, sum);
		longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
		String title = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
		Assert.assertEquals(title, "Terms Of Conditions");
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiSelector().text(\"Send me e-mails on discounts related to selected products in future\")"))
				.click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(2000);
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			System.out.println(context);
		}

		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//div[text()='Reject all']")));
		driver.findElement(By.xpath("//div[text()='Reject all']")).click();
		driver.findElement(By.name("q")).sendKeys("Appium tutorials");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

	}

}
