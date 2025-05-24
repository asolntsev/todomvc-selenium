package todomvc;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TodoPageObject {
  @FindBy(className = "new-todo")
  WebElement newItemTitle;

  @FindBy(css = ".todo-list li .view label")
  List<WebElement> addedItems;

  public TodoPageObject(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void addItem(String title) {
    newItemTitle.sendKeys(title);
    newItemTitle.sendKeys(Keys.ENTER);
  }
}
