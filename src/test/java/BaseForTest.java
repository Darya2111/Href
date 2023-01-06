import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

abstract public class BaseForTest {
    //первый метод, отвечающий за настройки и инициализацию и установку браузера
    public void setUp(){
        // устанавливаем хром-драйвер
        WebDriverManager.chromedriver().setup();
        // указываем конфигурацию для нашего браузера
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }
    // метод, который мы вызываем До начала тестов. Они должны быть статичными
    @BeforeAll
    static void init(){
        System.out.println("### @BeforeAll");
    }

    // метод, который мы вызываем После тестов
    @AfterAll
    static void tearDown(){
        Selenide.closeWebDriver();
    }

}
