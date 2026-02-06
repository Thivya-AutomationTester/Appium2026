package MobileAutomation.FirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumBasics extends BaseTest {

	@Test(enabled = false)
	public void firstTest() {

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"3. Preference dependencies\")"))
				.click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)"))
				.click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("Rahul");
		driver.findElement(By.id("android:id/button1")).click();
	}

	@Test(enabled = false)
	public void longPressTest() {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Expandable Lists\")")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement longPressElement = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
		longPressAction(longPressElement);
		Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
		String text = driver.findElement(By.id("android:id/title")).getText();
		Assert.assertEquals(text, "Sample menu");

	}

	@Test(enabled = false)
	public void scrollTest() {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// to scroll into specific element using text
		// driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"WebView\"));"));

		// scroll till end
		scrollToEnd();
	}

	@Test(enabled = false)
	public void swipeTest() {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
		WebElement firstImage = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		Assert.assertEquals( driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "true");
		// swipe
		swipeAction(firstImage,"left");
		
		Assert.assertEquals(driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"), "false");

	}
	
	@Test
	public void dragAndDropTest() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement source=driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) source).getId(), "endX", 629, "endY", 520));
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText(), "Dropped!");
		// swipe
		
		}
}
