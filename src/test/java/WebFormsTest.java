import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class WebFormsTest {
    WebDriver driver;
    @BeforeMethod
    public void open(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void task1() throws InterruptedException {
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html ");
        Select progLang = new Select(driver.findElement(By.cssSelector("select#dropdowm-menu-1")));
        Thread.sleep(500);
        progLang.selectByValue("python");
        WebElement selectedOption = progLang.getFirstSelectedOption();
        System.out.println("Is dropdown selected? " + selectedOption.isSelected() + '\n' + "Selected option is: " + selectedOption.getText());

        Thread.sleep(1000);

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (int i=0; i<allCheckboxes.size(); i++){
            WebElement currentCheckbox = allCheckboxes.get(i);
            if (currentCheckbox.isSelected()){
                continue;
            } else {
                currentCheckbox.click();
            }
            Thread.sleep(500);
        }

        Thread.sleep(1000);

        WebElement radioOption = driver.findElement(By.cssSelector("input[value='yellow']"));
        radioOption.click();

        Thread.sleep(1000);

        WebElement dropdown = driver.findElement(By.cssSelector("select#fruit-selects"));
        dropdown.click();
        Thread.sleep(500);
        Select dropdownOption = new Select(driver.findElement(By.cssSelector("select#fruit-selects")));
        try {
            dropdownOption.selectByValue("orange");
        } catch (UnsupportedOperationException ex){
            //nothing
        }

        Thread.sleep(500);
        WebElement orange = driver.findElement(By.cssSelector("option[value='orange']"));
        if (orange.isEnabled()){
            System.out.println("Orange option is enabled");
        } else {
            System.out.println("Orange option is disabled");
        }
    }
}
