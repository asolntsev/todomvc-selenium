package todomvc;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditItemTest extends BaseTest {

  private static final String SELECT_ALL = System.getProperty("os.name").toLowerCase().contains("mac") ?
    Keys.COMMAND + "a" :
    Keys.CONTROL + "a";

  @BeforeEach
  void setUp() {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeout);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("h1")));
    wait.until(ExpectedConditions.textToBe(By.tagName("h1"), "todos"));
  }

  @Test
  void editItemsTitle() {
    WebElement newItemTitle = driver.findElement(By.className("new-todo"));
      ((SelenideElement) newItemTitle).setValue("One").pressEnter();
      ((SelenideElement) newItemTitle).setValue("Two").pressEnter();
      ((SelenideElement) newItemTitle).setValue("Three").pressEnter();

      List<WebElement> addedItems = driver.findElement(By.className("todo-list")).findElements(By.cssSelector("li"));
    assertEquals(3, addedItems.size());

    new Actions(driver).doubleClick(addedItems.get(0)).perform();
    WebElement one = addedItems.get(0).findElement(By.className("edit"));
    one.sendKeys(SELECT_ALL, Keys.BACK_SPACE); // one.clear() doesn't work
    one.sendKeys("First 1 11 111 1111 11111");
    one.sendKeys(Keys.ENTER);

    new Actions(driver).doubleClick(addedItems.get(1)).perform();
    WebElement two = addedItems.get(1).findElement(By.className("edit"));
    two.sendKeys(SELECT_ALL, Keys.BACK_SPACE); // two.clear() doesn't work
    two.sendKeys("Second 2 22 222 2222 2222");
    two.sendKeys(Keys.ENTER);

    new Actions(driver).doubleClick(addedItems.get(2)).perform();
    WebElement three = addedItems.get(2).findElement(By.className("edit"));
    three.sendKeys(SELECT_ALL, Keys.BACK_SPACE); // three.clear() doesn't work
    three.sendKeys("Third 3 33 333 3333 33333");
    three.sendKeys(Keys.ENTER);

    assertEquals("First 1 11 111 1111 11111", addedItems.get(0).findElement(By.className("view")).getText());
    assertEquals("Second 2 22 222 2222 2222", addedItems.get(1).findElement(By.className("view")).getText());
    assertEquals("Third 3 33 333 3333 33333", addedItems.get(2).findElement(By.className("view")).getText());
  }

}
