package hardcodedtests;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateNewLeadAndDuplicate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		if(driver.getTitle().contains("vtiger"))
			System.out.println("login page displayed");
		else
			System.out.println("login page not found");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();
		if(driver.getTitle().contains("Home"))
			System.out.println("Home page is displayed");
		else
			System.out.println("Home page not found");
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'action=index')]")).click();
		if(driver.getTitle().contains("Leads"))
			System.out.println("Leads page is displayed");
		else
			System.out.println("Leads page not found");
		driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		WebElement createlead=driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(createlead.getText().equals("Creating New Lead"))
			System.out.println("Creating New Lead page is displayed");
		else
			System.out.println("Creating New Lead is not found");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String leadName="TCS"+randomNum;
		WebElement leadName1=driver.findElement(By.name("salutationtype"));
		Select s=new Select(leadName1);
		s.selectByValue("Mrs.");
		driver.findElement(By.name("firstname")).sendKeys(leadName);
		driver.findElement(By.name("lastname")).sendKeys(leadName);
		driver.findElement(By.name("company")).sendKeys("TCS");
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String neworgInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(neworgInfo.contains(leadName))
			System.out.println("Lead created successfully");
		else
			System.out.println("Lead not created");
		driver.findElement(By.xpath("//input[@title='Duplicate [Alt+U]']")).click();
		WebElement duplicate=driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(duplicate.isDisplayed())
			System.out.println("Duplicate page created");
		else
			System.out.println("Duplicate page not found");
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		WebElement sh=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a2=new Actions(driver);
		a2.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		

	}

}
