package vtigerFinalScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateOrgwithTypeAndIndustry extends BaseClass {

	@Test
	public void CreateOrgwithIndustryAndTypeTest() {
		SoftAssert soft = new SoftAssert();
		home.clickOrganizations();
		soft.assertTrue(driver.getTitle().contains("Organizations"));
		org.clickPlusButton();
		soft.assertTrue(createOrg.getPageHeader().equals("Creating New Organization"));
		Map<String, String> map = excel.readFromExcel("OrganizationTestData", "Create Organization");
		String orgName = map.get("Organization Name") + jutil.generateRandomNum(100);
		createOrg.setOrgName(orgName);
		createOrg.selectIndustry(web, map.get("Industry"));
		createOrg.selectType(web, map.get("Type"));
		createOrg.clickSaveButton();

		soft.assertTrue(newOrgInfo.getPageHeader().contains(orgName));
		if (newOrgInfo.getPageHeader().contains(orgName))
			excel.writeToExcel("OrganizationTestData", "Create Organization With Industry and Type", "Pass",
					IConstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("OrganizationTestData", "Create Organization With Industry and Type", "Fail",
					IConstantPath.EXCEL_PATH);

		soft.assertAll();
	}
}