/*
 * Copyright (c) 2021. Created by Ihor Hudyma for TestMatick
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void setFilter(String value)
    {
        driver.findElement(By.id("searchDropdownBox")).sendKeys(value);
    }

    public ResultsPage searchResult(String text)
    {
        setFilter("Books");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(text);
        driver.findElement(By.className("nav-right")).click();
        return new ResultsPage(driver);
    }
}