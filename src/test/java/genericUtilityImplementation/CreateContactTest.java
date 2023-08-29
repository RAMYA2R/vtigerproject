package genericUtilityImplementation;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

public class CreateContactTest 
{
	public static void main(String[] args) 
	{
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility webUtil = new WebDriverUtility();
				
		property.propertiesIntialization(IConstantPath.PROPERTIES_PATH);
		excel.excelIntialization(IConstantPath.EXCEL_PATH);
		
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriver driver = webUtil.launchBrowser(property.readFromProperties("browser"));
		webUtil.maximizeBrowser();
		webUtil.navigateToapp(property.readFromProperties("url"));
		webUtil.waitTillElemntFound(Long.parseLong(property.readFromProperties("time")));
		
		if(driver.getTitle().contains("vtiger"))
			System.out.println("Login Page Displayed");
		else
			System.out.println("Login Page Not Displayed");
		
		driver.findElement(By.name("user_name")).sendKeys("username");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			System.out.println("Home Page is Not Displayed");
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		if(driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page is Displayed");
		else
			System.out.println("Contacts Page is Not Displayed");
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		WebElement createContact = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		
		if(createContact.getText().equals("Creating New Contact"))
			System.out.println("Creating New Contact Page is Displayed");
		else
			System.out.println("Creating New Contact Page is Not Displayed");
		
//		Random random = new Random();
//        int randomNum = random.nextInt(100);
		
		String randomNum = null;
		String contactName = "Bhanu"+randomNum;
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		String newContactInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(newContactInfo.contains(contactName))
			System.out.println("Contact Created Successfully");
		else
			System.out.println("Contact Not Created");
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		driver.quit();
	}
}