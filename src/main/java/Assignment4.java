import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        Duration wait = Duration.ofSeconds(5);
        driver.manage().timeouts().implicitlyWait(wait);

        try {
            driver.get("https://the-internet.herokuapp.com/");
            new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Multiple Windows"))).click();

            // Open up child window
            new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Click Here"))).click();

            // Get window handles
            Set<String> windowHandles = driver.getWindowHandles();
            Iterator<String> it = windowHandles.iterator();
            String parentWindowID = it.next();
            String childWindowID = it.next();

            // Switch to child window and print out text
            driver.switchTo().window(childWindowID);
            printHeaderContents(driver, wait);

            // Switch back to parent window and print out text
            driver.switchTo().window(parentWindowID);
            printHeaderContents(driver, wait);
        } catch(Exception e) {
            System.out.println("Error: " + Arrays.toString(e.getStackTrace()));
        } finally {
            driver.quit();
        }
    }

    public static void printHeaderContents(WebDriver driver, Duration wait) {
        String contents = new WebDriverWait(driver,wait).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3"))).getText();
        System.out.println(contents);
    }
}
