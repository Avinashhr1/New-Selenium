package main.java.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import test.java.BaseTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuitListener implements ITestListener, IAnnotationTransformer {


    @Override
    public void onTestFailure(ITestResult result) {
        String fileName = result.getMethod().getMethodName();
        File screenShot = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File("./Screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String fileName = result.getMethod().getMethodName();
        File screenShot = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File("./Screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyser.class);
    }
}
