package todomvc;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadSourcesTest extends BaseTest {
  @Test
  void download() {
    open("https://todomvc.com/");

    File file = $(byText("Download")).download();

    System.out.printf("[[ATTACHMENT|%s]]%n", file.getAbsolutePath());
    assertEquals("todomvc-master.zip", file.getName());
  }
}
