import helper.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

/*
1. Go to site: https://rahulshettyacademy.com/AutomationPractice/
2. Find the example web table and scroll to it
3. Print # of rows present in table
4. Print # of columns present in table
5. Print entire 2nd row
*/
public class Assignment7 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,450)");
            WebElement webTable = Wait.forElementVisible(driver, By.id("product"));

            // Other way to scroll using Actions API
            /*
            -If element is off-screen
            -scrollToElement seems to stop when element becomes visible (i.e. it didn't scroll to the table bc it was already visible)

            new Actions(driver)
                    .scrollToElement(webTable)
                    .perform();
            Didn't need build() method before perform()
            https://www.selenium.dev/documentation/webdriver/actions_api/
            */

            int numRows = webTable.findElements(By.tagName("tr")).size();
            System.out.printf("Number of rows: %s\n", numRows);

            int numCols = webTable.findElement(By.cssSelector("tr")).findElements(By.tagName("th")).size();
            System.out.printf("Number of columns: %s\n", numCols);

            // Print contents of 2nd row (excluding the table header)
            WebElement secondDataRow = webTable.findElements(By.tagName("tr")).get(2);
            List<WebElement> secondDataRowColumns = secondDataRow.findElements(By.tagName("td"));
            for (WebElement column : secondDataRowColumns) {
                System.out.println(column.getText());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
