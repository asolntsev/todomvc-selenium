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
    for (int i = 1; i <= 100; i++) {
      page.addItem("Repeat action #" + i);
      if (i == 1)
        assertEquals(i + " item left", page.itemsCount.getText());
      else
        assertEquals(i + " items left", page.itemsCount.getText());
    }

    assertEquals(100, page.addedItems.size());
    assertEquals("Repeat action #1", page.addedItems.get(0).getText());
    assertEquals("Repeat action #100", page.addedItems.get(99).getText());
  }

  @Test
  void removeMultipleItems() {
    for (int i = 1; i <= 100; i++) {
      page.addItem("Repeat action #" + i);
    }

    assertEquals(100, page.addedItems.size());
    assertEquals("Repeat action #1", page.addedItems.get(0).getText());
    assertEquals("Repeat action #100", page.addedItems.get(99).getText());
    assertEquals("100 items left", page.itemsCount.getText());

    for (int i = 99; i >= 0; i--) {
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
