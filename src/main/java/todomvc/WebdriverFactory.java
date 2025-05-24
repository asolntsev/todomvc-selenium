package todomvc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebdriverFactory {
  public static WebDriver create() {
    return switch (Config.browser) {
      case CHROME -> {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Config.headless) {
          chromeOptions.addArguments("--headless=new");
        }
        yield new ChromeDriver(chromeOptions);
      }
      case FIREFOX -> {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Config.headless) {
          firefoxOptions.addArguments("-headless");
        }
        yield new FirefoxDriver(firefoxOptions);
      }
      case EDGE -> {
        EdgeOptions edgeOptions = new EdgeOptions();
        if (Config.headless) {
          edgeOptions.addArguments("--headless=new");
        }
        yield new EdgeDriver(edgeOptions);
      }
    };
  }
}
