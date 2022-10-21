import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WsSearchPage {
    WebDriver driver;
    public WsSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//input[@id='searchval']")
    public WebElement searchBox;

    @FindBy(xpath= "//div[@id='product_listing']/div[@id='ProductBoxContainer']")
    public List<WebElement> productList;

    public boolean ensureMatches(String txt) {
        // NOTE: the pagination is ignored
        for (WebElement each : productList) {
            WebElement itemDesc = each.findElement(By.xpath("//a[@data-testid='itemDescription']"));

            // check if it contains 'txt'
            if (!itemDesc.getText().contains(txt)) {
                return false;
            }
        }
        return true;
    }

    public boolean addLastItemToCart() {
        WebElement lastItem = productList.get(0); //productList.size()-1
        Assert.assertTrue(lastItem!=null);
        System.out.println("lastItem.getText(): " + lastItem.getText());

        try {
            WebElement lastSubmitBtn = lastItem.findElement(By.xpath(".//input[@type='submit'][@data-action='addToCart']"));
            if (lastSubmitBtn != null && lastSubmitBtn.isDisplayed()) {
                // add into the cart
                lastSubmitBtn.click();
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }

    public WsCartPage gotoCart() throws InterruptedException {
        WebElement viewCartBtn = this.driver.findElement(By.xpath("//a[@href='/viewcart.cfm']"));
        viewCartBtn.click();
        Thread.sleep(1000);
        return new WsCartPage(this.driver);
    }

}
