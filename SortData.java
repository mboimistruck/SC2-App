import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Arrays;

public class SortData extends GrabData {

  static SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
  static Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
  static int day = c.get(Calendar.DATE);
  static boolean passOverCheck;
  static String sortedFileName = "Sorted.txt";


  public static void sortData() {
    c.add(Calendar.DATE, -day - 1); // set the date back to the start of the month

    try {
    BufferedWriter fileWriter  = new BufferedWriter(new FileWriter(sortedFileName));

      for (int i = 0; i < divsArray.length; i++) {
        if (divsArray[i] != null && passOverCheck == false && (
                                      divsArray[i].startsWith("Monday") ||
                                      divsArray[i].startsWith("Tuesday") ||
                                      divsArray[i].startsWith("Wednesday") ||
                                      divsArray[i].startsWith("Thursday") ||
                                      divsArray[i].startsWith("Friday") ||
                                      divsArray[i].startsWith("Saturday") ||
                                      divsArray[i].startsWith("Sunday"))) {

          passOverCheck = true;
        }else if (divsArray[i] != null && passOverCheck != false && (
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

        } else if (divsArray[i] != null && divsArray[i].length() > 2 && passOverCheck != false && (
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

}
