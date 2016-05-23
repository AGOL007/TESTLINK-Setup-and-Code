
package com.test;

 

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;
 
public class AutomatedUpdateExample {
 //C:\wamp\www\mytestlink\lib\api\xmlrpc\v1\xmlrpc.php
	//http://localhost:8084/mytestlink/lib/api/xmlrpc/v1/xmlrpc.php
public static String DEVKEY="ae8286af5d7995affe4f5a6e60c44c23";
public static String URL="http://172.30.5.81:8085/mytestlink/lib/api/xmlrpc/v1/xmlrpc.php";
 
public static void reportResult(String TestProject,String TestPlan, String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException
{
	TestLinkAPIClient api=new TestLinkAPIClient(DEVKEY, URL);
//	api.reportTestCaseResult(TestProject, TestPlan,TestSuite, Testcase, Build, Notes, Result);
	api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);
	
}
 
@Test
public void Test1()throws Exception
{
	AutomatedUpdateExample a=new AutomatedUpdateExample();
	
	WebDriver driver=new FirefoxDriver();
	WebDriverWait wait=new WebDriverWait(driver, 600);
	
	String testProject="Gmail";
	String testPlan="SampleTestPlan";
	String testSuite = "GmailTestSuite";
	String testCase="GmailLogin1";
	String build="SampleBuild";
	String notes=null;
	String result=null;
	
	try
	{
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
		driver.findElement(By.id("Email")).sendKeys("ajay.giri007@gmail.com");
		driver.findElement(By.id("next")).click();
		Thread.sleep(200);
		driver.findElement(By.id("Passwd")).sendKeys("ag420840");
		driver.findElement(By.id("signIn")).click();
//		driver.switchTo().defaultContent();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("+Testlink")));
		result= TestLinkAPIResults.TEST_PASSED;
		notes="Executed successfully";
	}
	catch(Exception e)
	{
		result=TestLinkAPIResults.TEST_FAILED;
		notes="Execution failed";
	}
	
	finally
	{
		a.reportResult(testProject, testPlan, testCase, build, notes, result);
		driver.quit();
	}
	
}

}
