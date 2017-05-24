import com.sun.jna.platform.win32.WinDef;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.jws.WebResult;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by IParshykov on 23/05/2017.
 */
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
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
        driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).sendKeys("1");
        driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"gobutton\"]")).click();
        Thread.sleep(5000);
        String str = driver.findElement(By.xpath("/html/body/div/div/form/h2")).getText();
        Assert.assertEquals(str, "2");
    }

    @AfterTest
    public void exit() {
        driver.close();
    }

}
