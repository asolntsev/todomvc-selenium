package todomvc;

import java.time.Duration;

public class Config {
  public static final String URL = "https://todomvc.com/examples/vue/dist/#/";
  public static final Browser browser = Browser.valueOf(System.getProperty("browser", "CHROME"));
  public static final Duration timeout = Duration.ofSeconds(5);
}
