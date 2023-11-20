import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class Assignment4 {
    public static void main (String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        try {
            driver.get("https://the-internet.herokuapp.com/");
            waitForElement(driver, By.linkText("Multiple Windows")).click();

            // Open up child window
            waitForElement(driver, By.linkText("Click Here")).click();

            // Get window handles
            Set<String> windowHandles = driver.getWindowHandles();
            Iterator<String> it = windowHandles.iterator();
            String parentWindowID = it.next();
            String childWindowID = it.next();

            // Switch to child window and print out text
            driver.switchTo().window(childWindowID);
            printHeaderContents(driver);

            // Switch back to parent window and print out text
            driver.switchTo().window(parentWindowID);
            printHeaderContents(driver);

        } catch(Exception e) {
            System.out.println("Error: " + Arrays.toString(e.getStackTrace()));

        } finally {
            driver.quit();
        }
    }

    public static void printHeaderContents(WebDriver driver) {
        String contents = waitForElement(driver, By.tagName("h3")).getText();
        System.out.println(contents);
    }
    public static WebElement waitForElement(WebDriver driver, By byMethod) {
        // https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(byMethod));
    }
}
