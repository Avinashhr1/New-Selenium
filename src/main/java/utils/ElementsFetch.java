package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;

import java.util.List;

public class ElementsFetch {


    public WebElement getElement(String elementIdentifierType, String identifierValue) {
        switch (elementIdentifierType) {
            case "ID":
                return BaseTest.driver.findElement(By.id(identifierValue));
            case "xpath":
                return BaseTest.driver.findElement(By.xpath(identifierValue));
            case "css":
                return BaseTest.driver.findElement(By.cssSelector(identifierValue));
            case "TagName":
                return BaseTest.driver.findElement(By.tagName(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getListElements(String elementIdentifierType, String identifierValue) {
        switch (elementIdentifierType) {
            case "ID":
                return BaseTest.driver.findElements(By.id(identifierValue));
            case "xpath":
                return BaseTest.driver.findElements(By.xpath(identifierValue));
            case "css":
                return BaseTest.driver.findElements(By.cssSelector(identifierValue));
            case "TagName":
                return BaseTest.driver.findElements(By.tagName(identifierValue));
            default:
                return null;
        }

    }
}


