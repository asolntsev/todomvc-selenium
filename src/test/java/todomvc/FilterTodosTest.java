package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTodosTest extends BaseTest {
  private TodoPageObject page;

  @BeforeEach
  void setUp() {
    page = new TodoPageObject(driver);
    page.addItem("Wake up");
    page.addItem("Make a coffee");
    page.addItem("Shave yourself");
    page.markAsCompleted(2);
  }

  @Test
  void filterActive() {
    assertEquals(3, page.addedItems.size());

    page.filter(FilterMode.ACTIVE);

    assertEquals(2, page.addedItems.size());
    assertEquals("Wake up", page.addedItems.get(0).getText());
    assertEquals("Make a coffee", page.addedItems.get(1).getText());
  }

  @Test
  void filterCompleted() {
    assertEquals(3, page.addedItems.size());

    page.filter(FilterMode.COMPLETED);

    assertEquals(1, page.addedItems.size());
    assertEquals("Shave yourself", page.addedItems.get(0).getText());
  }

  @Test
  void removeCompletedItems() {
    assertEquals(3, page.addedItems.size());

    page.removeCompletedItems();

    assertEquals(2, page.addedItems.size());
  }

  @Test
  void download() throws IOException {
    File file = page.downloadSource();

    assertEquals("source.html", file.getName());
    assertTrue(readFileToString(file, UTF_8).contains("TodoMVC: Vue"));
    System.out.printf("[[ATTACHMENT|%s]]%n", file.getAbsolutePath());
  }
}
