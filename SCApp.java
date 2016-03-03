// Fix the URL to autofill the month/year

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class SCApp {

  static String url = "http://www.teamliquid.net/calendar/?view=month&year=2016&month=02&day=20&team=&league=&game=1";
  static Document document;
  static Elements divElements;
  static String[] divsArray;
  static String fileName = "Library.txt";
  static String sortedFileName = "Sorted.txt";

  static Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
  static int day = c.get(Calendar.DATE);
  static int counter = 0;

  static SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");

  public static void getData() {
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

  public static void sortData() {
    c.add(Calendar.DATE, -day - 1); // set the date back to the start of the month

    try {
    BufferedWriter fileWriter  = new BufferedWriter(new FileWriter(sortedFileName));

      for (int i = 0; i < divsArray.length; i++) {
        if (divsArray[i] != null && counter == 0 && (
                                      divsArray[i].startsWith("Monday") ||
                                      divsArray[i].startsWith("Tuesday") ||
                                      divsArray[i].startsWith("Wednesday") ||
                                      divsArray[i].startsWith("Thursday") ||
                                      divsArray[i].startsWith("Friday") ||
                                      divsArray[i].startsWith("Saturday") ||
                                      divsArray[i].startsWith("Sunday"))) {

          counter++;
        }else if (divsArray[i] != null && counter != 0 && (
                                      divsArray[i].startsWith("Monday") ||
                                      divsArray[i].startsWith("Tuesday") ||
                                      divsArray[i].startsWith("Wednesday") ||
                                      divsArray[i].startsWith("Thursday") ||
                                      divsArray[i].startsWith("Friday") ||
                                      divsArray[i].startsWith("Saturday") ||
                                      divsArray[i].startsWith("Sunday"))) {

          fileWriter.write("Day: ");
          fileWriter.write(divsArray[i]);
          fileWriter.write(" - ");
          c.add(Calendar.DATE, 1);
          fileWriter.write(String.valueOf(sdf.format(c.getTime())));
          fileWriter.newLine();
          fileWriter.newLine();

        } else if (divsArray[i] != null && divsArray[i].length() > 2 && counter != 0 && (
                                divsArray[i].startsWith("0") ||
                                divsArray[i].startsWith("1") ||
                                divsArray[i].startsWith("2"))) {

          fileWriter.write("Event: ");
          fileWriter.write(divsArray[i - 1]);
          fileWriter.write("\t Time: ");
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

  public static void main(String[] args) {
    getData();
    sortData();
  }
}
