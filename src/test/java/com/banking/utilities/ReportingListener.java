package com.banking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportingListener extends TestListenerAdapter {

	public ExtentReports extentReport;
	public ExtentTest extentTest;
	public ExtentSparkReporter sparkReporter;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("MMM dd, yyyy HH.mm.ss").format(new Date());
		String reportName = "test-report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter("./test-output/extent/" + reportName);
		sparkReporter.config().setDocumentTitle("Banking Demo Project");
		sparkReporter.config().setReportName("Funtional Test Report");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
	}

	public void onTestSuccess(ITestResult itr) {
		extentTest = extentReport.createTest(itr.getName());
		extentTest.log(Status.PASS, MarkupHelper.createLabel(itr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult itr) {
		extentTest = extentReport.createTest(itr.getName());
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(itr.getName(), ExtentColor.RED));
		String screenShotPath = System.getProperty("user.dir") + ReadConfig.getPropertyValue(Constants.SCREENSHOT_PATH)
				+ itr.getName() + ".png";
		File file = new File(screenShotPath);
		if (file.exists()) {
			extentTest.addScreenCaptureFromPath(screenShotPath);
		}

	}

	public void onTestSkipped(ITestResult itr) {
		extentTest = extentReport.createTest(itr.getName());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(itr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extentReport.flush();
	}
}
