package todomvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddTodoTest extends BaseTest {
  @BeforeEach
  void setUp() {
    $("h1").shouldHave(text("todos"), visible);
  }

  @Test
  void createNewItem() {
    $(".new-todo").setValue("Wake up").pressEnter();
    $$(".todo-list li .view label")
      .shouldHave(texts("Wake up"));
  }

  @Test
  void createTwoItems() {
    $(".new-todo").setValue("Wake up").pressEnter();
    $(".new-todo").setValue("Make a coffee").pressEnter();

    $$(".todo-list li .view label")
      .shouldHave(texts("Wake up", "Make a coffee"));
  }
}
