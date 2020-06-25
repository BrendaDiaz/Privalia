package privalia;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefs {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUpTest() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().setSize(new Dimension(1500, 1000));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @After
    public void tearDownTest() {
        driver.quit();
    }

    @When("I am in Privalia Home Page")
    public void iAmInPrivaliaHomePage() {
        driver.get("https://mex.privalia.com/public/");
        //logo privalia id= "claim"
        WebElement privaliaLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claim")));
        assertTrue(privaliaLogo.isDisplayed());
        //boton entrar css= "['.grid_4 #authLogin']"
        // WebElement botonEntrar = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("['.grid_4 #authLogin']")));
        // assertTrue(privaliaLogo.isEnabled());
    }



    @Then("All the featured ads are up to date")
    public void allTheFeaturedAdsAreUpToDate() {
        List<WebElement> promociones = driver.findElements(By.xpath("//h2[ text() = 'Destacados']/following-sibling::article"));
        for(WebElement w: promociones)
        {
            WebElement vigencia = w.findElement(By.cssSelector(".item-dataInfo"));
            System.out.println(vigencia.getText());
        }

    }

    @Then("All the current ads are up to date")
    public void allTheCurrentAdsAreUpToDate() {
        List<WebElement> promociones = driver.findElements(By.xpath("//h2[ text() = 'Actualmente']/following-sibling::article"));
        for(WebElement w: promociones)
        {
            WebElement vigencia = w.findElement(By.cssSelector(".item-dataInfo"));
            System.out.println(vigencia.getText());
        }

    }

    @Then("Coming up promotions don't have a due date")
    public void comingUpPromotionsDonTHaveADueDate() {
        List<WebElement> promociones = driver.findElements(By.xpath("//h2[ text() = 'Próximamente']/following-sibling::article"));
        for(WebElement w: promociones)
        {
            WebElement marcaElement = w.findElement(By.xpath("./a"));
            String marca = marcaElement.getAttribute("href").split("campaign/")[1].split("/")[0];
            WebElement startDate = w.findElement(By.cssSelector(".item-dataInfo"));
            System.out.println(marca + " " + startDate.getText());
        }
    }
    private void assertTrue(boolean displayed) {
    }
}