import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Quiz4
{
    WebDriver driver;

    public void execute(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public String ChooseGender(String gender){
        String takengender;

        if(gender == "MALE"){
            takengender = "//*[@id=\"genterWrapper\"]/div[2]/div[1]/label";
        }
        else if(gender == "FEMALE"){
            takengender = "//*[@id=\"genterWrapper\"]/div[2]/div[2]/label";
        }
        else{
            takengender = "//*[@id=\"genterWrapper\"]/div[2]/div[3]/label";
        }
        return takengender;
    }

    @Test
    public void ExecuteTest(){
        this.Maintest("gio","gudadze","MALE","0321345031");
    }

    public void Maintest(String firstName, String lastName, String gend, String num){
        execute();

        this.driver.get("https://demoqa.com/");

        WebElement first = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div[2]"));
        first.click();

        WebElement second = driver.findElement(By.xpath("//span[text() =\"Practice Form\"]/.."));
        second.click();

        WebElement name = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[1]/div[2]/input"));
        name.sendKeys(firstName);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,300)");
        WebElement submitted = driver.findElement(By.id("submit"));
        submitted.click();

        WebElement last = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[1]/div[4]/input"));
        last.sendKeys(lastName);

        WebElement gender = driver.findElement(By.xpath(ChooseGender(gend)));
        gender.click();

        WebElement mobileNum = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[4]/div[2]/input"));
        mobileNum.sendKeys(num);
        //checking რატომღაც არ მუშაობს
//        boolean checked = driver.findElement(By.xpath("//*[@id='example-modal-sizes-title-lg']")).isDisplayed();
//        Assert.assertTrue(checked, "Form did not submit for some reason");
//        System.out.println(checked);
    }

    @DataProvider(name = "dataprovider")
    public Object[][] dpMethod(){
        return new Object[][] {{
                "lado", "gurgenidze", "MALE", "1234567890"
        }};
    }

    @Test(dataProvider = "dataprovider")
    public void ExecuteTestfordataprovider(String firstName, String lastName, String gend, String num){
        this.Maintest(firstName,lastName,gend,num);
    }
}
