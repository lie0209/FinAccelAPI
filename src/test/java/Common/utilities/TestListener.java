package Common.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

    public class TestListener implements ITestListener {

        protected static ExtentReports reports;
        protected static ExtentTest test;

        public static String pdate = new SimpleDateFormat("dd_MM_yyy_HH:mm").format(Calendar.getInstance().getTime());

        private static String resultpath = getResultPath();


        public static void deleteDirectory(File directory) {
            if (directory.exists()) {
                File[] files = directory.listFiles();
                if (null != files) {
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(files[i].getName());
                        if (files[i].isDirectory()) {
                            deleteDirectory(files[i]);
                        } else {
                            files[i].delete();
                        }
                    }
                }
            }
        }

        private static String getResultPath() {

            resultpath = System.getProperty("user.dir")+"/src/test/java/reports";//new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
            if (!new File(resultpath).isDirectory()) {
                new File(resultpath);
            }
            return resultpath;
        }

        String ReportLocation = resultpath + "/";

        public void onTestStart(ITestResult result) {

            test = reports.startTest(result.getMethod().getMethodName());
            test.log(LogStatus.INFO, result.getMethod().getMethodName());
            System.out.println(result.getMethod().getMethodName());
        }

        public void onTestSuccess(ITestResult result) {
            test.log(LogStatus.PASS, "Test is pass");

        }

        public void onTestFailure(ITestResult result) {
            test.log(LogStatus.FAIL, "Test is fail");

        }

        public void onTestSkipped(ITestResult result) {
            test.log(LogStatus.SKIP, "Test is skipped");

        }

        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
            // TODO Auto-generated method stub

        }

        public void onStart(ITestContext context) {
            System.out.println(ReportLocation + "API_Automation_Report_"+pdate+".html");
            reports = new ExtentReports(ReportLocation + "API_Automation_Report_"+pdate+".html");

        }

        public void onFinish(ITestContext context) {
            reports.endTest(test);
            reports.flush();

        }

        public void info(String message) {
            test.log(LogStatus.INFO,message);
        }

        // Get log info and print
        public void pass(String message) {
            test.log(LogStatus.PASS, message);
        }

        // Get fail and take screenshot
        public void fail(String message) {
            test.log(LogStatus.FAIL, message);
        }

    }
