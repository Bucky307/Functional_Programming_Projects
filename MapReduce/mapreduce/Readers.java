package mapreduce;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Readers {
  public static List<Pair<String, String>> mapFilenameToLine(File file) {
    List<Pair<String, String>> result = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = br.readLine()) != null) {
        if (line.length() > 0)
          result.add(new Pair<>(file.getName(), line));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
      result.clear();
    }
    return result;
  }

  // TODO ?
  public static List<Pair<String, String>> mapFileToContents(File file) {
    List<Pair<String, String>> result = new ArrayList<>();
    String all = "";
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = br.readLine()) != null) {
        if (line.length() > 0)
          all = all + line + "\n";
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
      result.clear();
    }
    result.add(new Pair<>(file.getName(), all));
    return result;
  }
}