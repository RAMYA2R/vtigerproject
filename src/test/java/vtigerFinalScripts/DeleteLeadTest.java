package vtigerFinalScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class DeleteLeadTest extends BaseClass {

		@Test
		public void deleteLeadTest() {
			SoftAssert soft=new SoftAssert();
			home.clickLeads();
			soft.assertTrue(driver.getTitle().contains("Leads"));
			Map<String,String> map=excel.readFromExcel("Sheet3", "Delete Lead");
			lead.traverseToRequiredLead(web, map.get("Lead Name"));
			lead.clickDelete();
			
		    web.handleAlert("ok"); 
			  List<WebElement> leadNameList = lead.getLeadsNames();
			  boolean status =false;
			  for(WebElement leads: leadNameList) {
			 
			 if(!(leads.getText().equals("lead2"))) { 
				 status=true; 
				 } } 
			  soft.assertTrue(status);
			  
			  if(status)
				  excel.writeToExcel("Sheet3", "Delete Lead", "Pass", IConstantPath.EXCEL_PATH);
			  else
				  excel.writeToExcel("Sheet3", "Delete Lead", "Fail", IConstantPath.EXCEL_PATH);
			  
			//soft.assertAll();
		}
	}

