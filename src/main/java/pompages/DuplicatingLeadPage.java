package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicatingLeadPage 
{
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	   private WebElement pageHeader;
	   
	   @FindBy(name="lastname")
	   private WebElement lastNameTF ;
	   
	   @FindBy(xpath="//input[normalize-space(@value)='Save']")
	   private WebElement saveButton;
	   
	   //Initialization
	   public DuplicatingLeadPage(WebDriver driver)
	   {
		   PageFactory.initElements(driver, this);
	   }
	   
	   //Utilization
	   public String getPageHeader()
	   {
		   return pageHeader.getText();
	   }
	   public void setLastName(String lastName)
	   {
		   lastNameTF.clear();
		   lastNameTF.sendKeys(lastName);
	   }
	   public void clickSaveButton()
	   {
		   saveButton.click();
	   }
}