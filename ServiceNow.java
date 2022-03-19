package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev96011.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Pn3xWJ6nTorA");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("incident");
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[@class='selected_action action_context btn btn-primary']")).click();
		
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowSelection = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowSelection.get(1));
		driver.findElement(By.className("glide_ref_item_link")).click();
		
		driver.switchTo().window(windowSelection.get(0));
		driver.switchTo().frame("gsft_main");
		//driver.findElement(By.id("sys_display.incident.caller_id")).sendKeys("survey user");
		Thread.sleep(3000);
		
		driver.findElement(By.id("incident.short_description")).sendKeys("Issue");
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident Number: "+incidentNumber);
		driver.findElement(By.xpath("//button[@class='form_action_button header  action_context btn btn-default']")).click();
		
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//span[@class='input-group-addon-transparent icon-search sysparm-search-icon']")).click();
		driver.findElement(By.id("sysparm_search")).sendKeys(incidentNumber, Keys.ENTER);
		Thread.sleep(10000);
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/img001.jpg");
		FileUtils.copyFile(screenshotAs, image);
	}
}
