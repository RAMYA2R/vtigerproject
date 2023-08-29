package genericLibraries;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener 
{
    private ExtentReports report;
    private ExtentTest test;
    public static ExtentTest stest;
	@Override
	public void onTestStart(ITestResult result) 
	{
		Capabilities cap=((RemoteWebDriver)(BaseClass.sdriver)).getCapabilities();
		report.setSystemInfo("Browser",cap.getBrowserName() );
		report.setSystemInfo("Browser Version", cap.getBrowserVersion());
		test=report.createTest(result.getMethod().getMethodName());
		stest=test;
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.pass(result.getMethod().getMethodName()+"Pass");
		
	}	

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+"Fail");
		test.fail(result.getThrowable());
		WebDriverUtility web=new WebDriverUtility();
		String path=web.takeScreenshot(result.getMethod().getMethodName(),
				BaseClass.sjutil,BaseClass.sdriver);
		test.addScreenCaptureFromPath(path);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+"Skipped");
		test.skip(result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/report.html");
		spark.config().setReportName("Extent Reports");
		spark.config().setDocumentTitle("VTIGER-CRM");
		spark.config().setTheme(Theme.STANDARD);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Author", "Sushanthi");
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("OS Version", System.getProperty("os.version"));
		report.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}