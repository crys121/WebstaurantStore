import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.util.List;

public class WebStaurant {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();

        //Go to https://www.webstaurantstore.com/
        driver.get("https://www.webstaurantstore.com/");

        //2.     Search for 'stainless work table'.
        WsSearchPage searchPage = new WsSearchPage(driver);
        searchPage.searchBox.sendKeys("stainless work table" + Keys.ENTER);

        //3.     Check the search result ensuring every product has the word 'Table' in its title.
        boolean resultsOkay=searchPage.ensureMatches("Table");
        Assert.assertTrue(resultsOkay);

        //4.     Add the last of found items to Cart.
        boolean added=searchPage.addLastItemToCart();

        //5.     Empty Cart.
        if (added) {
            Thread.sleep(1000); // give it some time

            WsCartPage cartPage = searchPage.gotoCart();
            cartPage.emptyCart();
        } else {
            System.out.println("Cart is empty. Possibly the last element is out of stock");
        }


        System.out.println("Test PASSED.");

        Thread.sleep(3000);

        driver.quit();
    }

}
