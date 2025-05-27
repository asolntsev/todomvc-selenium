package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AddTodoPageObjectTest extends BaseTest {
  private TodoPageObject page;

  @BeforeEach
  void setUp() {
    page = page();
    page.newItemTitle.shouldBe(visible, Duration.ofSeconds(5));
  }

  @Test
  void createNewItem() {
    page.addItem("Wake up");
    page.addedItems.shouldHave(texts("Wake up"));
  }

  @Test
  void createTwoItems() {
    page.addItem("Wake up");
    page.addItem("Make a coffee");

    page.addedItems.shouldHave(texts("Wake up", "Make a coffee"));
  }
}
