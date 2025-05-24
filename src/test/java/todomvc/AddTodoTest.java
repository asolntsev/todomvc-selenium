package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoTest extends BaseTest {
  @BeforeEach
  void setUp() {
    WebDriverWait wait = new WebDriverWait(driver, Config.timeout);
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("h1")));
    wait.until(ExpectedConditions.textToBe(By.tagName("h1"), "todos"));
  }

  @Test
  void createNewItem() {
    WebElement newItemTitle = driver.findElement(By.className("new-todo"));
    newItemTitle.sendKeys("Wake up");
    newItemTitle.sendKeys(Keys.ENTER);

    List<WebElement> addedItems = driver.findElement(By.className("todo-list")).findElements(By.cssSelector("li .view label"));
    assertEquals(1, addedItems.size());
    assertEquals("Wake up", addedItems.get(0).getText());
  }

  @Test
  void createTwoItems() {
    WebElement newItemTitle = driver.findElement(By.className("new-todo"));
    newItemTitle.sendKeys("Wake up");
    newItemTitle.sendKeys(Keys.ENTER);
    newItemTitle.sendKeys("Make a coffee");
    newItemTitle.sendKeys(Keys.ENTER);

    List<WebElement> addedItems = driver.findElement(By.className("todo-list")).findElements(By.cssSelector("li .view label"));
    assertEquals(2, addedItems.size());
    assertEquals("Wake up", addedItems.get(0).getText());
    assertEquals("Make a coffee", addedItems.get(1).getText());
  }
}
