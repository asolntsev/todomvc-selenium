package todomvc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

  protected void addTodo(WebElement newItemTitle, String title) {
    newItemTitle.sendKeys(title);
    newItemTitle.sendKeys(Keys.ENTER);
  }
}
