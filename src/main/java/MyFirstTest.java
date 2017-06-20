import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class MyFirstTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {

        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testOpenLink() throws InterruptedException {

        /* Test1:
        Open http://juliemr.github.io/protractor-demo/
        Assert that page opened */

        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
//      Thread.sleep(10000);

        String link1 = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertEquals(link1, "Super Calculator");
        System.out.println("Test1: Page is opened");

        /* Test2:
        Enter value "1" to first field and second field
        Assert that values are entered */

        driver.findElement(By.xpath("//input[1]")).sendKeys("1");
        driver.findElement(By.xpath("//input[2]")).sendKeys("1");

        String value1 = driver.findElement(By.xpath("//input[@ng-model='first']")).getAttribute("value");
        String value2 = driver.findElement(By.xpath("//input[@ng-model='second']")).getAttribute("value");
        Assert.assertEquals(value1, "1");
        Assert.assertEquals(value2, "1");
        System.out.println("Test2: " + value1 + " + " + value2 + " values are entered");

        /* Test3
        Choose "+" in operator field
        Assert that + is selected */

        driver.findElement(By.xpath("//option[1]")).click();
        String plus = driver.findElement(By.xpath("//option[1]")).getText();
        Assert.assertEquals(plus, "+");
        System.out.println("Test3: " + plus + " is selected");

        /*Test4
        Click Go button
        Assert that current result(big number in the left) equals 2 */
        driver.findElement(By.xpath("//*[@id='gobutton']")).click();
        Thread.sleep(1800);

        String result = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(result, "2");
        System.out.println("Test4: Current result equals " + result);
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
        System.out.println("Browser is closed");
    }

}