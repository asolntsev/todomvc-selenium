package todomvc;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAndRemoveTest extends BaseTest {
  @Test
  void addAndRemoveItem() {
    WebElement newItemTitle = driver.findElement(By.className("new-todo"));
    newItemTitle.sendKeys("Wake up", Keys.ENTER);
    newItemTitle.sendKeys("Make a coffee", Keys.ENTER);

    WebElement secondItem = driver.findElements(By.cssSelector(".todo-list li .view")).get(1);
    new Actions(driver).moveToElement(secondItem).perform();
    secondItem.findElement(By.cssSelector(".destroy")).click();

    List<WebElement> addedItems = driver.findElements(By.cssSelector(".todo-list li .view label"));
    assertEquals(1, addedItems.size());
    assertEquals("Wake up", addedItems.get(0).getText());
  }
}
