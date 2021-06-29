/*
 * Copyright (c) 2021. Created by Ihor Hudyma for TestMatick
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public String saveBookName(int i)
    {
        try {
            String bookName =  driver.findElement(By.xpath("//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')][" + i + "]//h2/a/span")).getText();
            return bookName;
        }
        catch (Exception e)
        {
            return "Unavailable";
        }
    }

    public String saveAuthorName(int i)
    {
        String authorName = new String();
        List<WebElement> authorNameRefList = driver.findElements(By.xpath("//div[contains(@class, 'asin')][" + i + "]//div[contains(@class, 'base')]/div[contains(@class, 'a-row')]/a[contains(@class, 'base')]"));
        if(!authorNameRefList.isEmpty()) {
            for (int j = 0; j < authorNameRefList.size(); j++) {
                if (j > 0) authorName += ", ";
                authorName += authorNameRefList.get(j).getText();
            }
        }
        List<WebElement> authorNameTxtList = driver.findElements(By.xpath("//div[contains(@class, 'asin')][" + i + "]//div[contains(@class, 'base')]/div[contains(@class, 'a-row')]//span[contains(@class, 'base') and not(contains(@class, 'color'))]"));
        if(!authorNameTxtList.isEmpty()) {
            for (int j = 0; j < authorNameTxtList.size(); j++) {
                String newName = authorNameTxtList.get(j).getText();
                if (!newName.isEmpty() && !newName.equals("by") && !newName.equals("by ")  && !newName.equals(", ") && !newName.equals(",") && !newName.equals("and")) {
                    if (!authorName.isEmpty() && !newName.equals(", et al.")) authorName += ", ";
                    authorName += newName;
                }
            }
        }
        return authorName;
    }

    public String savePrice(int i)
    {
        String price;
        try
        {
            price = driver.findElement(By.xpath("//div[contains(@class, 'asin')][" + i + "]//div[contains(@class, 'small')]//span[contains(@data-a-color, 'base')]/span[contains(@class, 'offscreen')]")).getAttribute("textContent");
        }
        catch (Exception e)
        {
            price = "Unavailable";
        }
        return price;
    }

    public boolean saveBestSeller(int i)
    {
        List <WebElement> bestSeller = driver.findElements(By.xpath("//div[contains(@class, 'asin')][" + i + "]//span[contains (@class, 'badge-text')]"));
        if (bestSeller.isEmpty()) return false;
        else return true;
    }

    public String saveRef(int i)
    {
        try{
            String ref  = driver.findElement(By.xpath("//div[contains(@class, 'asin')][" + i + "]//h2/a[contains(@class, 'text-normal') and not(contains(@class, 'base'))]")).getAttribute("href");
            return ref;
        }
        catch (Exception e)
        {
            return "Unavailable";
        }
    }

    public void saveInfo(ArrayList books)
    {
        List <WebElement> list = driver.findElements(By.xpath("//div[contains(@class, 's-main-slot') and contains(@class, 's-result-list')]//div[contains(@data-component-type, 's-search-result')]"));
        for(int i = 1; i <= list.size(); i++)
        {
            books.add(new Books(saveBookName(i), saveAuthorName(i), savePrice(i), saveRef(i), saveBestSeller(i)));
        }
    }

    public boolean isListContainBook(ArrayList list, String bookRef)
    {
        for (int i = 0; i< list.size(); i++)
        {
            if (list.get(i).equals(bookRef))
            {
                return true;
            }
        }
        return false;
    }
}
