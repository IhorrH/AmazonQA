/*
 * Copyright (c) 2021. Created by Ihor Hudyma for TestMatick
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Main {

        public static void main(String[] args)
        {
            String searchWord = "Java";
            String bookRef = "https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_2?dchild=1&keywords=Java&qid=1610356790&s=books&sr=1-2";
            System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.amazon.com/");
            ArrayList<Books> books = new ArrayList<>();

            SearchPage search = new SearchPage(driver);

            ResultsPage result = search.searchResult(searchWord);

            result.saveInfo(books);
            if(result.isListContainBook(books, bookRef))
                System.out.println("Requested book does exist");
                else System.out.println("Requested book does not exist");

            for(int i = 0; i < books.size(); i ++)
            {
                System.out.println("-------------------------");
                System.out.println(books.get(i).getName());
                System.out.println(books.get(i).getAuthorName());
                System.out.println(books.get(i).getPrice());
                System.out.println(books.get(i).getRef());
                System.out.println("Is bestseller: " + books.get(i).isBestseller());
            }
        }
    }
