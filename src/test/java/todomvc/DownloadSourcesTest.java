package todomvc;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadSourcesTest extends BaseTest {
  @Test
  void download() throws IOException {
    driver.navigate().to("https://todomvc.com/");

    List<WebElement> links = driver.findElements(By.tagName("a"));
    for (WebElement link : links) {
      if (link.getText().equals("Download")) {
        String url = link.getAttribute("href");
        File file = new File("build/todomvc-master.zip");
        FileOutputStream fileWriter = new FileOutputStream(file);

        URLConnection urlConnection = new URL(url).openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        byte[] buffer = new byte[1024];
        int count;
        while ((count = inputStream.read(buffer)) > 0) {
          fileWriter.write(buffer, 0, count);
        }
        fileWriter.close();
        assertEquals("todomvc-master.zip", file.getName());
      }
    }
  }
}
