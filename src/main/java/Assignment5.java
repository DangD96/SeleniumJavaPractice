import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import helper.Wait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment5 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        try {
            driver.get("https://the-internet.herokuapp.com/");
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Nested Frames")));
            WebElement link = driver.findElement(By.linkText("Nested Frames"));
            Actions a = new Actions(driver);
            a.scrollToElement(link).click(link).build().perform();

            // Print out the inner text of the middle frame. Frames inside frames
            // Switch to correct parent frame first
            WebElement parentFrame = Wait.forElementVisible(driver,By.cssSelector("frame[name='frame-top']"));
            driver.switchTo().frame(parentFrame);

            // Then switch to correct child frame
            WebElement childFrame = Wait.forElementVisible(driver, By.cssSelector("frame[name='frame-middle']"));
            driver.switchTo().frame(childFrame);

            // Print out inner text of childFrame
            System.out.println(Wait.forElementVisible(driver, By.id("content")).getText());

            // Switch back to Window Top
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            driver.quit();
        }
    }
}
