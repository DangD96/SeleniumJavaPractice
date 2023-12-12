import helper.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

// 1. Go to qaclickacademy.com/practice.php
// 2. Select any checkbox then grab the text from the checkbox
// 3. Then go to the dropdown control and pick the option that matches the checkbox you picked earlier
// 4. Go to the Switch to Alert Example and enter the label/dropdown value in the field
// 5. Click the Alert button and verify the alert has the label/value you typed in
public class Assignment6 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            Wait.forElementVisible(driver, By.id("checkBoxOption3"));
            WebElement checkboxOption = driver.findElement(By.id("checkBoxOption3"));
            checkboxOption.click();
            String myValue = checkboxOption.getAttribute("value");

            WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
            Select select = new Select(dropdown);
            select.selectByValue(myValue);

            driver.findElement(By.id("name")).sendKeys(myValue);

            driver.findElement(By.id("alertbtn")).click();
            Thread.sleep(Duration.ofSeconds(3));
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            Assert.assertTrue(alertText.contains(myValue));
            alert.accept(); // Clicks the OK button on the alert popup

        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
