package pompages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class LeadsPage {
    //Declaration
	private String commonPath = "//table[@class='lvt small']/descendant::tr";
	private String leadNamePath = commonPath + "[%d]/td[3]";
	private String leadCheckbox = commonPath + "[%d]/td[1]/input";
	@FindBy(xpath = "//table[@class='lvt small']/descendant::tr")
	private List<WebElement> leadsList;
	@FindBy(xpath = "//img[@alt='Create Lead...']")
	private WebElement plusButton;
	@FindBy (xpath = "//input[@value=\"Delete\"]")
	private WebElement deleteButton;
	@FindBy(xpath = "//table[@class='lvt small']/descendant::tr/td[3]/a")
	private List<WebElement> LeadNames;
	
	//Intialization
	public LeadsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public void clickPlusButton()
	{
		plusButton.click();
	}
	
	public void traverseToRequiredLead(WebDriverUtility web, String requiredLead)
	{
		for(int i = 2; i< leadsList.size(); i++)
		{
			String leadName = web.convertStringToDynamicXpath(leadNamePath, (i+1)).getText();
			if(leadName.equals(requiredLead)) {
				web.convertStringToDynamicXpath(leadCheckbox, (i+1)).click();
				break;
			}
		}
	}
	public List<WebElement> getLeadsNames(){
		return LeadNames;
	}
	
	public void clickDelete() {
		deleteButton.click();
	}

}
