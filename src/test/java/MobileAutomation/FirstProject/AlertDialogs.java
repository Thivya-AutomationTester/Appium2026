package MobileAutomation.FirstProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.AppiumBy;

public class AlertDialogs extends BaseTest {

	@BeforeClass
	public void moveToPage() {
		driver.findElement(AppiumBy.accessibilityId("App")).click();
		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
	}
	@Test(enabled=false)
	public void alertDialogWithText() {

		
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons")).click();

		String dialogText = driver.findElement(By.id("android:id/alertTitle")).getText().replace("\r", "")
				.replace("\n", " ").replaceAll("\\s+", " ").trim();

		String expectedText = "Lorem ipsum dolor sit aie consectetur adipiscing Plloaso mako nuto siwuf cakso dodtos anr koop.";
		Assert.assertEquals(dialogText, expectedText);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons")).click();
		driver.findElement(By.id("android:id/button2")).click();
	}

	@Test(enabled=false)

	public void alertDialogWithLongText() {

		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2")).click();

		String dialogText = driver.findElement(By.id("android:id/message")).getText().replace("\r", "")
				.replace("\n", " ")
				.replaceAll("\\s+", " ").trim();

		String expectedText = "Plloaso mako nuto siwuf cakso dodtos anr koop a cupy uf cak vux noaw yerw phuno. Whag schengos, uf efed, quiel ba mada su otrenzr. Swipontgwook proudgs hus yag su ba dagarmidad. Plasa maku noga wipont trenzsa schengos ent kaap zux comy. Wipont trenz kipg naar mixent phona. Cak pwico siructiun ruous nust apoply tyu cak Uhex sisulutiun munityuw uw dseg";
				
		Assert.assertEquals(dialogText, expectedText);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2")).click();
		driver.findElement(By.id("android:id/button2")).click();
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2")).click();
		driver.findElement(By.id("android:id/button3")).click();
	}
	@Test(enabled=false)

	public void alertDialogWithUltraText() {

		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2ultra")).click();

		String actual = normalizeText(
		        driver.findElement(By.id("android:id/message")).getText()
		);

		String expected = normalizeText(
			    "Plloaso mako nuto siwuf cakso dodtos anr koop a cupy uf cak vux noaw yerw phuno. " +
			    "Whag schengos, uf efed, quiel ba mada su otrenzr. " +
			    "Swipontgwook proudgs hus yag su ba dagarmidad. " +
			    "Plasa maku noga wipont trenzsa schengos ent kaap zux comy. " +
			    "Wipont trenz kipg naar mixent phona. " +
			    "Cak pwico siructiun ruous nust apoply tyu cak Uhex sisulutiun munityuw uw dseg"
			);

		Assert.assertTrue(
			    actual.contains(expected),
			    "Dialog text does not contain expected content"
			);
		

		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2ultra")).click();
		driver.findElement(By.id("android:id/button2")).click();
		driver.findElement(By.id("io.appium.android.apis:id/two_buttons2ultra")).click();
		driver.findElement(By.id("android:id/button3")).click();
	}

	private String normalizeText(String text) {
		return text
				.replaceAll("\\.\\s*", ". ") // exactly ONE space after dot
				.trim();
	}
	
	@Test
	public void listDialogs() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("List dialog")).click();
Thread.sleep(2000);
		List<WebElement> ele = driver.findElements(By.xpath("//android.widget.ListView/android.widget.TextView"));
		System.out.println(ele.size());
		for (int i = 0; i < ele.size(); i++) {
			String te=ele.get(i).getText();
			System.out.println(te);
		}
		for (WebElement listele:ele) {
			String te=listele.getText();
			System.out.println(te);
		}
	}
}

