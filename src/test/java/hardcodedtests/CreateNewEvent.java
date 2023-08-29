package hardcodedtests;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateNewEvent {

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
		driver.findElement(By.xpath("//option[text()='Quick Create...']")).click();
		WebElement Quickcreate=driver.findElement(By.id("qccombo"));
		Actions a1=new Actions(driver);
		a1.moveToElement(Quickcreate).perform();
		Select s=new Select(Quickcreate);
		s.selectByValue("Events");
		driver.findElement(By.xpath("//b[text()='Create To Do']"));
		if(Quickcreate.isDisplayed())
			System.out.println("New event page displayed");
		else
			System.out.println("New event page not found");
		Random random=new Random();
		int randomNum=random.nextInt(100);
		String subjectname="TCS"+randomNum;
		driver.findElement(By.name("subject")).sendKeys(subjectname);
		driver.findElement(By.id("jscal_trigger_date_start")).click();
	    int reqdate=19;
	    int reqmonth=10;
	    int reqyear=2025;
	    String commonpath="//div[@class='calendar' and contains(@style,'block')]/descendant::td[%s] ";
	    String actMonthYear=driver.findElement(By.xpath(formatPath(commonpath,"@class='title'"))).getText();
	    System.out.println(actMonthYear);
		String[] str=actMonthYear.split(", ");
		int actYear=Integer.parseInt(str[1]);
		while(actYear<reqyear)
		{
			driver.findElement(By.xpath(formatPath(commonpath ,"text()='>>'"))).click();
			actMonthYear=driver.findElement(By.xpath(formatPath(commonpath,"@class='title'"))).getText();
			System.out.println(actMonthYear);
			str=actMonthYear.split(", ");
			actYear=Integer.parseInt(str[1]);
		}
		int actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
		System.out.println(actMonth);
		while(actMonth<reqmonth)
		{
			driver.findElement(By.xpath(formatPath(commonpath,"text()='>'"))).click();
			actMonthYear=driver.findElement(By.xpath(formatPath(commonpath,"@class='title'"))).getText();
			System.out.println(actMonthYear);
			str=actMonthYear.split(", ");
			actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
			System.out.println(actMonth);
		}
		
		while(actMonth>reqmonth)
		{
			driver.findElement(By.xpath(formatPath(commonpath,"text()='<'"))).click();
			actMonthYear=driver.findElement(By.xpath(formatPath(commonpath,"@class='title'"))).getText();
			System.out.println(actMonthYear);
			str=actMonthYear.split(", ");
			actMonth=DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(str[0]).get(ChronoField.MONTH_OF_YEAR);
			System.out.println(actMonth);
		}
		driver.findElement(By.xpath(formatPath(commonpath,"text()='"+reqdate+"'"))).click();
		driver.quit();
	}
		public static String formatPath(String commonPath,String replaceData)
		{
			return String.format(commonPath, replaceData);
			
		}
	
		

}
