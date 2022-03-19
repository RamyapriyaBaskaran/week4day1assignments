package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Login
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//Click on Contacts tab->Click on Merge contact tab
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		
		//Navigate to new window 'Form Contact' and back to home page
		driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> newWindow = new ArrayList<String>(windowHandles);	
		driver.switchTo().window(newWindow.get(1));
		driver.findElement(By.xpath("//a[@class='linktext']")).click();
		driver.switchTo().window(newWindow.get(0));
		
		//Navigate to new window 'To Contact' and back to home page
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		windowHandles.add(driver.getWindowHandle());
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> newWindow1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(newWindow1.get(1));
		driver.findElement(By.xpath("(//a[@class='linktext'])[6]")).click();
	    driver.switchTo().window(newWindow.get(0));
		
	    //Click on Merge->Alert Handling->Titles verification
		driver.findElement(By.className("buttonDangerous")).click();
		Alert confirmAlert = driver.switchTo().alert();
		confirmAlert.accept();
		String expectedTitle = "View Contact | opentaps CRM";
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle))
			System.out.println("Title is validated. Tttle of the page is :  " +actualTitle);
		driver.close();
	}
}
