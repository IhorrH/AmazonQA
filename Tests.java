/*
 * Copyright (c) 2021. Created by Ihor Hudyma for TestMatick
 */

package com.example.tests;

import com.example.pages.ResultsPage;
import com.example.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Tests {
    private WebDriver driver;
    private SearchPage search;
    private ResultsPage result;

    @BeforeSuite
    public void access()
    {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
    }

    @Parameters("filterName")
    @Test
    public void filter(String filterName)
    {
        search = new SearchPage(driver);
        search.setFilter(filterName);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class, 'nav-search-facade')]")).getText(), filterName, "Filter name does not match");
    }

    @Parameters("searchWord")
    @Test(dependsOnMethods = "filter")
    public void searchBooks(String searchWord)
    {
        result = search.searchResult(searchWord);
        String currentUrl = result.getUrl();
        Assert.assertEquals(currentUrl, "https://www.amazon.com/s?k=Java&i=stripbooks-intl-ship&ref=nb_sb_noss", "Url does not match");
    }

    @Test(dependsOnMethods = "searchBooks")
    public void saveData()
    {
        result.saveBooks();
                Assert.assertFalse(result.getBooks().isEmpty(), "No books found");
    }

    @Parameters("bookRef")
    @Test(dependsOnMethods = "saveData")
    public void listContainBook(String bookRef)
    {
        Assert.assertTrue(result.isListContainBook(result.getBooks(), bookRef), "Requested book does not exist");
    }
}
