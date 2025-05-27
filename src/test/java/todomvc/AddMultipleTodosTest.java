package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AddMultipleTodosTest extends BaseTest {
  private TodoPageObject page = page();

  @BeforeEach
  void setUp() {

    page.newItemTitle.shouldBe(visible);
  }

  @Test
  void addMultipleItems() {
    for (int i = 1; i <= 100; i++) {
      page.addItem("Repeat action #" + i);
      if (i == 1)
        page.itemsCount.shouldHave(text(i + " item left"));
      else
        page.itemsCount.shouldHave(text(i + " items left"));
    }

    page.addedItems.shouldHave(size(100));
    page.addedItems.get(0).shouldHave(text("Repeat action #1"));
    page.addedItems.get(99).shouldHave(text("Repeat action #100"));
  }

  @Test
  void removeMultipleItems() {
    for (int i = 1; i <= 100; i++) {
      page.addItem("Repeat action #" + i);
    }

    page.addedItems.shouldHave(size(100));
    page.itemsCount.shouldHave(text("100 items left"));

    for (int i = 99; i >= 0; i--) {
      page.removeItem(i);
      if (i == 1)
        page.itemsCount.shouldHave(text(i + " item left"));
      else if (i == 0)
        page.itemsCount.shouldHave(text(""));
      else
        page.itemsCount.shouldHave(text(i + " items left"));
    }
  }
}
