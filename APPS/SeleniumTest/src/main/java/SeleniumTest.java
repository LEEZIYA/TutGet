import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class SeleniumTest {
    private static String driverLocation = "C:\\Users\\User\\AppData\\Local\\SeleniumBasic\\chromedriver.exe";
    private static String url = "http://localhost:8080/login";
    private static String testLoginId = "StudentSam";
    private static String testPassword = "password";



    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", driverLocation);

        ChromeDriver driver = new ChromeDriver();

        driver.get(url);

        NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);

        ngDriver.waitForAngularRequestsToFinish();


        //TEST LOGIN
        try{
            driver.findElementById("userName").sendKeys(testLoginId);
            driver.findElementById("password").sendKeys(testPassword);
            driver.findElement(ByAngular.buttonText("Login")).click();
        } catch(Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }

        Thread.sleep(2000);
        //TEST CREATE LISTING
        try{

            int screenWidth = driver.manage().window().getSize().getWidth();
            if (screenWidth <= 991) {
                driver.findElementByClassName("navbar-toggler-icon").click();
            }
            driver.findElement(ByAngular.buttonText("Create Listing")).click();

            Select subjOp = new Select(driver.findElementById("subj-dropdown"));
            subjOp.selectByIndex(1);

            driver.findElementById("box-3").click();

            Select hourOp = new Select(driver.findElementById("hour-3"));
            hourOp.selectByIndex(3);

            Select minOp = new Select(driver.findElementById("min-3"));
            minOp.selectByIndex(3);

            Select ampmOp = new Select(driver.findElementById("ampm-3"));
            ampmOp.selectByIndex(1);

            Select hournumOp = new Select(driver.findElementById("hournum-3"));
            hournumOp.selectByIndex(2);

            driver.findElementById("date-picker").sendKeys("02052024");


            Select freqOp = new Select(driver.findElementById("freq-dropdown"));
            freqOp.selectByIndex(2);

            driver.findElementById("hourly-rate").sendKeys("123");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            Thread.sleep(1000);
            driver.findElement(ByAngular.buttonText("Submit")).click();

        } catch(Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }

        Thread.sleep(1000);
        driver.quit();
    }

}
