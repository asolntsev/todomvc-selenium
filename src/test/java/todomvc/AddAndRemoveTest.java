package todomvc;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddAndRemoveTest extends BaseTest {
  @Test
  void addAndRemoveItem() {
    $(".new-todo").setValue("Wake up").pressEnter();
    $(".new-todo").setValue("Make a coffee").pressEnter();

    SelenideElement secondItem = $(".todo-list li .view", 1);
    secondItem.hover();
    secondItem.find(".destroy").click();

    $$(".todo-list li .view label")
      .shouldHave(size(1))
      .shouldHave(texts("Wake up"));
  }
}
