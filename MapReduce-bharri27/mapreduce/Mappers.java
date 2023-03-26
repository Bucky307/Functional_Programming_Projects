package mapreduce;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Mappers {
  public static List<Pair<String, Integer>> mapEachWordToOne(Pair<String, String> pair) {
    // TODO
    List<Pair<String, Integer>> result = new ArrayList<>();
    Scanner scan = new Scanner(pair.getSecond());
    while (scan.hasNext()) {
      result.add(new Pair(scan.next(), 1));
    }
    return result;
  }

  // TODO ?
  public static List<Pair<String, Pair<String, Integer>>> mapEachWordToLocation(Pair<String, String> pair) {
    List<Pair<String, Pair<String, Integer>>> result = new ArrayList<>();
    int count = 0;
    Scanner scan = new Scanner(pair.getSecond());
    while (scan.hasNextLine()) {
      Scanner scanX = new Scanner(scan.nextLine());
      while (scanX.hasNext()) {
        String w = scanX.next();
        result.add(new Pair(w, new Pair(pair.getFirst(), count)));
        count += w.length();
      }
    }
    return result;
  }

  public static List<Pair<String, Pair<String, Integer>>> mapBigram(Pair<String, String> pair) {
    List<Pair<String, Pair<String, Integer>>> result = new ArrayList<>();
    Scanner scan = new Scanner(pair.getSecond());
    Scanner scanX = new Scanner(pair.getSecond());
    String word1 = "";
    String word2 = "";
    if (scan.hasNext()) {
      word1 = scan.next();
      if (scan.hasNext()) {
        word2 = scanX.next();
        word2 = scanX.next();
        while (scanX.hasNext()) {
          result.add(new Pair(word1, new Pair(word2, 1)));
          word1 = scan.next();
          word2 = scanX.next();
        }
      }
    }
    return result;
  }
}