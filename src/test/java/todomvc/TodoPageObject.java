package todomvc;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class TodoPageObject {
  @FindBy(className = "new-todo")
  SelenideElement newItemTitle;

  @FindBy(css = ".todo-list li .view")
  ElementsCollection addedItems;

  @FindBy(className = "todo-count")
  SelenideElement itemsCount;

  @FindBy(css = ".filters a")
  private List<WebElement> filterLinks;

  @FindBy(className = "clear-completed")
  private SelenideElement removeCompletedButton;

  public void addItem(String title) {
    newItemTitle.sendKeys(title);
    newItemTitle.sendKeys(Keys.ENTER);
  }

  public void removeItem(int index) {
    addedItems.get(index)
            .hover()
            .find(".destroy").click();
  }

  public void markAsCompleted(int index) {
    SelenideElement item = addedItems.get(index);
    item.find(".toggle").click();
    item.parent().shouldHave(Condition.cssClass("completed"));
  }

  public void removeCompletedItems() {
    removeCompletedButton.click();
  }

  public void filter(FilterMode mode) {
    switch (mode) {
      case ALL -> filterLinks.get(0).click();
      case ACTIVE -> filterLinks.get(1).click();
      case COMPLETED -> filterLinks.get(2).click();
    }
  }

  public File downloadSource() {
    return $(".source-links a").download();
  }
}
