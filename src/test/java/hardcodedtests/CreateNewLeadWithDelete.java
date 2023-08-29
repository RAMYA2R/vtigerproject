package hardcodedtests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateNewLeadWithDelete {

	public static void main(String[] args) throws InterruptedException {
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
		String commonpath="//table[@class='1vt small']/descendant::tr";
		List<WebElement> leadsList=driver.findElements(By.xpath(commonpath));
		for(int i=2;i<leadsList.size();i++)
		{
			String leadName=driver.findElement(By.xpath(commonpath+"["+(i+1)+"]/td[3]")).getText();
			if(leadName.equals("LEA2"))
			{
				driver.findElement(By.xpath(commonpath+"["+(i+1)+"]/td[1]/input")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		List<WebElement> newLeadList=driver.findElements(By.xpath(commonpath));
		boolean status=false;
		for(int i=2;i<newLeadList.size();i++)
		{
			String leadName=driver.findElement(By.xpath(commonpath+"["+(i+1)+"]/td[3]")).getText();
			if(!(leadName.equals("LEA2")))
			{
				status=true;
			}
		}
		if(status)
		{
			System.out.println("Lead deleted successfully");
		}
		else
			System.out.println("Lead deleted failed");
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
	}

}
