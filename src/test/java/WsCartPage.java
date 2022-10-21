import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

public class WsCartPage {
    WebDriver driver;
    public WsCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".emptyCartButton")
    public WebElement emptyBtn;

    @FindBy(xpath = "//footer/button[.='Empty Cart']")
    public WebElement confirmBtn;

    public void emptyCart() throws InterruptedException {
        emptyBtn.click();
        Thread.sleep(1000);
        confirmBtn.click();
    }

}
