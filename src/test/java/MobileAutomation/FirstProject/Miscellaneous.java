package MobileAutomation.FirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Miscellaneous extends BaseTest {

	@Test(enabled=false)
	public void firstTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"3. Preference dependencies\")"))
				.click();
		driver.findElement(By.id("android:id/checkbox")).click();
		DeviceRotation landScape = new DeviceRotation(0, 0, 90);
		driver.rotate(landScape);
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)"))
				.click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		driver.setClipboardText("Rahul");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.findElement(By.id("android:id/button1")).click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

	}

	@Test
	public void openUsingAppPackage() {

		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));

		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)"))
				.click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("Rahul");
		driver.findElement(By.id("android:id/button1")).click();
	}
}
