package hardcodedtests;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationNewContact {

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
		driver.findElement(By.xpath("//a[text()='Organizations' and contains(@href,'action=index')]")).click();
		if(driver.getTitle().contains("Organizations"))
			System.out.println("Organizations page is displayed");
		else
			System.out.println("Organizations page not found");
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		WebElement createorg=driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(createorg.getText().equals("Creating New Organization"))
			System.out.println("Creating New Organization page is displayed");
		else
			System.out.println("Creating New Organization is not found");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String orgName="TCS"+randomNum;
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement phone=driver.findElement(By.name("phone"));
		Actions a=new Actions(driver);
		a.moveToElement(phone).perform();
		phone.sendKeys("9985922698");
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String phonesaved=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(phonesaved.contains(" Organization Information"))
			System.out.println("new contact should be saved");
		else
			System.out.println("new contact not saved");
		WebElement adminIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a1=new Actions(driver);
		a.moveToElement(adminIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
	}

}
