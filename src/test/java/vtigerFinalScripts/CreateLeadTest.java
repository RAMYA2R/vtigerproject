package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateLeadTest extends BaseClass {

	public static String lastName;

	@Test
	public void createLeadTest() {
		SoftAssert soft = new SoftAssert();
		home.clickLeads();
		soft.assertTrue(driver.getTitle().contains("Leads"));
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");
		Map<String, String> map = excel.readFromExcel("LeadsTestData", "Create Lead");
		lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createLead.setLastName(lastName);
		createLead.setCompantName(map.get("Company"));
		createLead.clickSaveButton();
		soft.assertTrue(newLeadInfo.getPageHeader().contains(lastName));
		if (newLeadInfo.getPageHeader().contains(lastName))
			excel.writeToExcel("LeadsTestData", "Create Lead", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("LeadsTestData", "Create Lead", "Fail", IConstantPath.EXCEL_PATH);

		soft.assertAll();
	}
}
