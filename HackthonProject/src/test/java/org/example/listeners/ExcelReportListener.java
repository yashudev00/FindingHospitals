
package org.example.listeners;

import org.example.utility.ExcelUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExcelReportListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        ExcelUtils.writeResult(
                result.getMethod().getMethodName(),
                "PASS",
                "Test passed successfully"
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExcelUtils.writeResult(
                result.getMethod().getMethodName(),
                "FAIL",
                result.getThrowable().getMessage()
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExcelUtils.writeResult(
                result.getMethod().getMethodName(),
                "SKIPPED",
                "Test skipped"
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        ExcelUtils.saveExcel();
    }
}