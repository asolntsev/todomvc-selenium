package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddMultipleTodosTest extends BaseTest {
  private TodoPageObject page;

  @BeforeEach
  void setUp() {
    page = new TodoPageObject(driver);
    await().atMost(5, SECONDS).until(() -> page.newItemTitle.isDisplayed());
  }

  @Test
  void addMultipleItems() {
    for (int i = 1; i <= 10; i++) {
      page.addItem("Repeat action #" + i);
      if (i == 1)
        assertEquals(i + " item left", page.itemsCount.getText());
      else
        assertEquals(i + " items left", page.itemsCount.getText());
    }

    assertEquals(10, page.addedItems.size());
    assertEquals("Repeat action #1", page.addedItems.get(0).getText());
    assertEquals("Repeat action #10", page.addedItems.get(9).getText());
  }

  @Test
  void removeMultipleItems() {
    for (int i = 1; i <= 10; i++) {
      page.addItem("Repeat action #" + i);
    }

    assertEquals(10, page.addedItems.size());
    assertEquals("Repeat action #1", page.addedItems.get(0).getText());
    assertEquals("Repeat action #10", page.addedItems.get(9).getText());
    assertEquals("10 items left", page.itemsCount.getText());

    for (int i = 9; i >= 0; i--) {
      page.removeItem(i);
      if (i == 1)
        assertEquals(i + " item left", page.itemsCount.getText());
      else if (i == 0)
        assertEquals("", page.itemsCount.getText());
      else
        assertEquals(i + " items left", page.itemsCount.getText());
    }
  }
}
