package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsAndWindows {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String windowHandle = driver.getWindowHandle();
		driver.switchTo().window(windowHandle);
		driver.findElement(By.className("btn-primary")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'FLIGHTS')]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> newWindow = new ArrayList<String>(windowHandles);	
		driver.switchTo().window(newWindow.get(1));
	
		driver.findElement(By.xpath("//button[text()='Allow']")).click();

		driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle']")).click();
		String text = driver.findElement(By.xpath("//a[@class='dropdown-item'][3]")).getText();
		System.out.println("Customer email id: " +text);
		driver.switchTo().window(newWindow.get(0)).close();
	}
}
