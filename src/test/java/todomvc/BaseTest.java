package todomvc;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.TextCheck.FULL_TEXT;

public abstract class BaseTest {
  @BeforeEach
  final void openBrowser() {
    open(Config.URL);
    localStorage().clear();
    refresh();
    Configuration.textCheck = FULL_TEXT;
    $("h1").shouldHave(text("todos"), visible);
  }

  @AfterEach
  void tearDown() {
    //Selenide.closeWebDriver();
  }
}
