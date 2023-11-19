import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import helper.SayHi;

public class Assignment3 {
    public static void main (String[] args) {
        WebDriver driver = new ChromeDriver();
        /* Implicitly wait UP TO 5 seconds between each step
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        */
        //SayHi.sayHi("");

        driver.get("https://www.rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();

        // Enter login credentials
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("learning");

        // Check the User radio button
        driver.findElement(By.cssSelector("input[value='user']~span")).click();

        // Wait for confirmation popup (explicit wait) and click Yes
        Duration waitTime = Duration.ofSeconds(3);
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-dialog")));
        WebElement warningPopup = driver.findElement(By.cssSelector(".modal-dialog"));
        WebElement warningPopupOkay = warningPopup.findElement(By.cssSelector("button#okayBtn"));
        warningPopupOkay.click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOf(warningPopup));

        // Select role as Consultant in drop down
        WebElement roleDropdown = driver.findElement(By.cssSelector("select.form-control"));
        Select roleDropDown = new Select(roleDropdown);
        roleDropDown.selectByValue("consult"); // This method needs the value HTML attribute. Picks the 'Consultant' option
        //roleDropDown.selectByIndex(2);

        // Agree to terms and conditions
        driver.findElement(By.cssSelector("#terms")).click();

        // Sign in and wait for next page to load (explicit wait)
        driver.findElement(By.cssSelector("#signInBtn")).click();
        new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-card-list")));
        WebElement cardList = driver.findElement(By.cssSelector("app-card-list"));

        // Add all products on page to shopping cart
        List<WebElement> products = cardList.findElements(By.cssSelector("app-card"));
        for (WebElement product: products) {
            product.findElement(By.cssSelector("button")).click();
        }

        // Go to shopping cart
        driver.findElement(By.xpath("//a[contains(text(), 'Checkout')]")).click();

        driver.quit();

    }
}
