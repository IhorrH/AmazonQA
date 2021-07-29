/*
 * Copyright (c) 2021. Created by Ihor Hudyma for TestMatick
 */

package com.example.pages;

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
        driver.findElement(By.xpath("//select[contains(@class, 'nav-search-dropdown')]")).sendKeys(value);
    }

    public ResultsPage searchResult(String text)
    {
//        setFilter("Books");
        driver.findElement(By.xpath("//input[contains(@id, 'twotabsearchtextbox')]")).sendKeys(text);
        driver.findElement(By.xpath("//span[contains(@class, 'nav-search-submit-text')]")).click();
        return new ResultsPage(driver);
    }
}