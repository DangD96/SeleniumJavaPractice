import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Assignment1 {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            WebElement firstCheckbox = driver.findElement(By.id("checkBoxOption1"));
            firstCheckbox.click();                          // Check the first checkbox
            Assert.assertTrue(firstCheckbox.isSelected());  // Verify it's checked
            firstCheckbox.click();                          // Uncheck the first checkbox
            Assert.assertFalse(firstCheckbox.isSelected()); // Verify it's unchecked

            // Get number of checkboxes on page
            int numCheckboxes;
            List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("[type='checkbox']"));
            numCheckboxes = allCheckboxes.size();
            System.out.println("Number of checkboxes: " + numCheckboxes);
        }
        finally {
            driver.quit();
        }
    }
}
