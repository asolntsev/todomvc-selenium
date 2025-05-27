package todomvc;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.SetValueMethod.JS;
import static com.codeborne.selenide.SetValueOptions.withText;

public class EditItemTest extends BaseTest {

    @Test
    void editItemsTitle() {
        $(".new-todo").setValue("One").pressEnter();
        $(".new-todo").setValue("Two").pressEnter();
        $(".new-todo").setValue("Three").pressEnter();

        $$(".todo-list li").shouldHave(size(3));

        $(".todo-list li", 0)
                .doubleClick()
                .find(".input-container .edit")
                .setValue(withText("First 1 11 111 1111 11111").usingMethod(JS))
                .pressEnter();
        $(".todo-list li", 1)
                .doubleClick()
                .find(".input-container .edit")
                .setValue(withText("Second 2 22 222 2222 2222").usingMethod(JS))
                .pressEnter();
        $(".todo-list li", 2)
                .doubleClick()
                .find(".input-container .edit")
                .setValue(withText("Third 3 33 333 3333 33333").usingMethod(JS))
                .pressEnter();

        $$(".todo-list li .view label").shouldHave(texts(
                "First 1 11 111 1111 11111",
                "Second 2 22 222 2222 2222",
                "Third 3 33 333 3333 33333"
        ));
    }
}
