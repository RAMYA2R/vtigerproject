package hardcodedtests;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationNewContactExit {

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
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contacts page is displayed");
		else
			System.out.println("Contacts page not found");
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		WebElement createorg=driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(createorg.getText().equals("Creating New Contact"))
			System.out.println("Creating New contact page is displayed");
		else
			System.out.println("Creating New contact is not found");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String lastname="TCS"+randomNum;
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		String parentID=driver.getWindowHandle();
		Set<String> ids=driver.getWindowHandles();
		for(String s: ids)
		{
			driver.switchTo().window(s);
		}
		List<WebElement> orglist=driver.findElements(By.xpath("//div[@id='ListViewContents']/descendant::table[@cellspacing='1']/descendant::tr/td[1]/a"));
		for(int i=1;i<orglist.size();i++)
		{
			if(orglist.get(i).getText().equals("TCS64"))
			{
				System.out.println(orglist.get(i).getText());
				orglist.get(i).click();
				break;
			}
		}
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		String neworg=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a1=new Actions(driver);
		a1.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

	}

}
