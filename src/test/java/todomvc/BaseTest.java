package todomvc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
  protected WebDriver driver;

  @BeforeEach
  final void openBrowser() {
    driver = WebdriverFactory.create();
    driver.navigate().to(Config.URL);
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
