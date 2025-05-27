package todomvc;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(ScreenShooterExtension.class)
public abstract class BaseTest {
  protected WebDriver driver;

  @BeforeEach
  final void openBrowser() {
    driver = WebdriverFactory.create();
    driver.navigate().to(Config.URL);

    WebDriverRunner.setWebDriver(driver);
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
    Selenide.closeWebDriver();
  }
}
