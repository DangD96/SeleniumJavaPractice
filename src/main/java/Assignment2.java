import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Assignment2 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.rahulshettyacademy.com/angularpractice/");

        // Fill out form
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("DJD");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("DJD@test.com");
        driver.findElement(By.cssSelector("input#exampleInputPassword1")).sendKeys("DJD password");
        driver.findElement(By.cssSelector("input#exampleCheck1")).click();
        WebElement genderDropdown = driver.findElement(By.cssSelector("select#exampleFormControlSelect1"));
        Select dropdown = new Select(genderDropdown);
        dropdown.selectByIndex(1);
        driver.findElement(By.cssSelector("input[value='option1']")).click();
        driver.findElement(By.cssSelector("input[name='bday']")).sendKeys("01/09/1990");

        // Submit form
        driver.findElement(By.cssSelector("input[value='Submit']")).click();

        // Print out confirmation message
        WebElement alertMessageBanner = driver.findElement(By.cssSelector("div.alert.alert-success.alert-dismissible"));
        String alertMessage = alertMessageBanner.getText();
        System.out.println(alertMessage);
        driver.quit();
    }
}
