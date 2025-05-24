package todomvc;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoPageObject {
  private final WebDriver driver;

  @FindBy(className = "new-todo")
  WebElement newItemTitle;

  @FindBy(css = ".todo-list li .view")
  List<WebElement> addedItems;

  @FindBy(className = "todo-count")
  WebElement itemsCount;

  @FindBy(css = ".filters a")
  private List<WebElement> filterLinks;

  @FindBy(className = "clear-completed")
  private WebElement removeCompletedButton;

  public TodoPageObject(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void addItem(String title) {
    newItemTitle.sendKeys(title);
    newItemTitle.sendKeys(Keys.ENTER);
  }

  public void removeItem(int index) {
    WebElement item = addedItems.get(index);
    new Actions(driver).moveToElement(item).perform();
    item.findElement(By.className("destroy")).click();
  }

  public void markAsCompleted(int index) {
    WebElement item = addedItems.get(index);
    item.findElement(By.className("toggle")).click();
    assertEquals("completed", item.findElement(By.xpath("..")).getAttribute("class"));
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

  public File downloadSource() throws IOException {
    String url = driver.findElement(By.cssSelector(".source-links a")).getAttribute("href");
    File file = new File("build/source.html");
    IOUtils.copy(new URL(url), file);
    return file;
  }
}
