import helper.Driver;
import helper.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/*
    1. Go to "Suggestion Class Example" dropdown field
    2. Type in first 3 letters of country you want
    3. Pick the option from the dropdown
    4. Verify the value in the field updates to what you picked
*/
public class Assignment8 {
    public static void main(String[] args) {
        // Use helper function to get driver instance set up with max window and implicit wait
        WebDriver driver = Driver.setUp();
        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            WebElement countryAutoSelectField = Wait.forElementVisible(driver, By.xpath("//input[@id='autocomplete']"));
            countryAutoSelectField.sendKeys("uni");

            WebElement dropdownMenu = Wait.forElementVisible(driver, By.cssSelector(".ui-menu.ui-autocomplete"));
            List<WebElement> dropdownSuggestions = dropdownMenu.findElements(By.tagName("li"));
            for (WebElement suggestion : dropdownSuggestions) {
                if (suggestion.getText().contains("United States")) suggestion.click();
            }
            Assert.assertEquals(countryAutoSelectField.getAttribute("value"),"United States (USA)");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
