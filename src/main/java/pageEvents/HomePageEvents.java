package main.java.pageEvents;

import main.java.Pageobjects.HomePage;
import main.java.utils.ElementsFetch;
import org.openqa.selenium.Keys;

public class HomePageEvents {


public void searchKeyWord(String search){
    ElementsFetch ele = new ElementsFetch();
    ele.getElement("xpath", HomePage.searchBox).sendKeys(search, Keys.ENTER);
}




}
