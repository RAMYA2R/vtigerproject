package genericUtilityImplementation;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateLeadTest {

	public static void main(String[] args) 
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
		
		if(driver.getTitle().contains("Lead"))
			System.out.println("Lead Page is Displayed");
		else
			System.out.println("Lead Page is Not Displayed");
		
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		Random random = new Random();
        int randomNum = random.nextInt(100);
		
        String createLead = "Bhanuteja"+randomNum;
        String orgName = "TCS"+randomNum;
        
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(createLead);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String leadInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(leadInfo.contains(leadInfo))
			System.out.println("Lead Created Successfully");
		else
			System.out.println("Lead Not Created");
		
		driver.quit();
	}

}
