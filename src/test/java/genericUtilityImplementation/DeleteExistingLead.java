package genericUtilityImplementation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DeleteExistingLead {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("Login Page Displayed");
		else
			System.out.println("Login Page Not Displayed");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("bhanu994850");
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			System.out.println("Home Page is Not Displayed");
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'action=index')]")).click();
		
		if(driver.getTitle().contains("Leads"))
			System.out.println("Leads page is displayed");
		else
			System.out.println("Leads page not found");
	
		String commonPath = "//table[@class='lvt small']/descendant::tr";
		
		List<WebElement> leadList = driver.findElements(By.xpath(commonPath));
		for(int i = 2; i < leadList.size();i++)
		{
			String leadName = driver.findElement(By.xpath(commonPath+"["+(i+1)+"]/td[3]")).getText();
			if(leadName.equals("LEA3"))
			{
				driver.findElement(By.xpath(commonPath+"["+(i+1)+"]/td[1]/input")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		List<WebElement> newLeadsList = driver.findElements(By.xpath(commonPath));
		boolean status = false;
		for(int i = 2; i < newLeadsList.size();i++)
		{
			String leadName = driver.findElement(By.xpath(commonPath+"["+(i+1)+"]/td[3]")).getText();
			
			if(!(leadName.equals("LEA3"))) {
				status = true;
			}
		}
		if(status)
			System.out.println("Lead deleted successfully");
		else
			System.out.println("Lead deletion Failed");
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		driver.quit();		
	}
}