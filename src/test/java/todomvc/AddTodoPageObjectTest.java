package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoPageObjectTest extends BaseTest {
  private TodoPageObject page;

  @BeforeEach
  void setUp() {
    page = new TodoPageObject(driver);
    await().atMost(5, SECONDS).until(() -> page.newItemTitle.isDisplayed());
  }

  @Test
  void createNewItem() {
    page.addItem("Wake up");

    assertEquals(1, page.addedItems.size());
    assertEquals("Wake up", page.addedItems.get(0).getText());
  }

  @Test
  void createTwoItems() {
    page.addItem("Wake up");
    page.addItem("Make a coffee");

    assertEquals(2, page.addedItems.size());
    assertEquals("Wake up", page.addedItems.get(0).getText());
    assertEquals("Make a coffee", page.addedItems.get(1).getText());
  }
}
