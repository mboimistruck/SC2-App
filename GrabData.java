import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class GrabData {

  static String url = "http://www.teamliquid.net/calendar/?view=month&year=2016&month=02&day=20&team=&league=&game=1";
  static Document document;
  static Elements divElements;
  static String[] divsArray;
  static String fileName = "Library.txt";


  public static void grabData() {
    try{
      document = Jsoup.connect(url).get();
      divElements = document.select("span");
      divsArray = new String[divElements.size()];

      BufferedWriter fileWriter  = new BufferedWriter(new FileWriter(fileName));

      for (int i = 0; i < divElements.size(); i++) {

        if (divElements.get(i).ownText().length() > 0) {

          divsArray[i] = divElements.get(i).ownText();
          fileWriter.write(divsArray[i]);
          fileWriter.newLine();
        }
      }
      fileWriter.flush();
      fileWriter.close();

    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
