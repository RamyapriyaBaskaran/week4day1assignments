package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);
		
		String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Price displayed: "+price);
		
		String noOfCustomersReview = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']")).getText();
		System.out.println("No of Customers Reviewed this product: "+noOfCustomersReview);
		
		driver.findElement(By.xpath("(//span[@class='a-icon-alt'])/..")).click();
		String starRating = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-beside-button a-text-bold']")).getText();
		System.out.println("Rating Received from customers: "+ starRating);
		
		String percentage = driver.findElement(By.xpath("(//td[@class='a-text-right a-nowrap'])[1]//a")).getText();
		System.out.println("5 Rating percentage received: "+ percentage);
		
		driver.findElement(By.xpath("//img[@class='s-image']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> newWindow = new ArrayList<String>(windowHandles);	
		driver.switchTo().window(newWindow.get(1));
		driver.findElement(By.id("add-to-cart-button")).click();
		
		driver.findElement(By.id("attach-sidesheet-view-cart-button")).click();
		WebElement element = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']"));
		String subcartTotal = element.getText();
		System.out.println("Total amount displayed in subcart: "+subcartTotal);
		
		if(subcartTotal.contains(price))
			System.out.println("Same Price displayed"); 
		else
		System.out.println("Prices are different..Please Check!!");
	}
}
