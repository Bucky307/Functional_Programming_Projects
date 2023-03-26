package cosc3015;

import java.util.List;
import java.util.ArrayList;

public class Range {
  public static List<Integer> rangeInts(int lo, int hi) {
    int size = hi - lo + 1;
    if (size <= 0)
      return new ArrayList<Integer>();
    ArrayList<Integer> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      list.add(i, i + lo);
    return list;
  }

  public static List<Character> rangeChars(char lo, char hi) {
    int size = hi - lo + 1;
    if (size <= 0)
      return new ArrayList<Character>();
    ArrayList<Character> list = new ArrayList<>(size);
    for (char i = 0; i < size; i++)
      list.add(i, (char) (i + lo));
    return list;
  }
}