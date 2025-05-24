package todomvc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverFactory {
  public static WebDriver create() {
    return switch (Config.browser) {
      case CHROME -> new ChromeDriver();
      case FIREFOX -> new FirefoxDriver();
      case EDGE -> new EdgeDriver();
    };
  }
}
