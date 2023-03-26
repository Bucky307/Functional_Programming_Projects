package tests;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import cosc3015.Range;
import cosc3015.ListUtils;
import cosc3015.Pair;

public class TestListUtils {
  List<Integer> list1;
  List<Character> listChar;

  @Before
  public void setup() {
    list1 = Range.rangeInts(10, 15);
    listChar = Range.rangeChars('a', 'e');
  }

  @Test
  public void testMap() {
    assertEquals(Arrays.asList(20, 22, 24, 26, 28, 30),
        ListUtils.map(x -> (2 * x), list1));
  }

  @Test
  public void testFlatMap() {
    assertEquals(Arrays.asList(10, 11, 12, 13, 14, 15),
        ListUtils.flatmap(x -> Range.rangeInts(x, x), list1));
  }

  @Test
  public void testFilter() {
    assertEquals(Arrays.asList(10, 12, 14),
        ListUtils.filter(x -> {
          return x % 2 == 0;
        }, list1));
  }

  @Test
  public void testZip() {
    List<Pair> zl = new ArrayList<Pair>();
    for (int i = 10; i < 15; i++)
      zl.add(new Pair((char) (i + 97 - 10), i));

    assertEquals(zl,
        ListUtils.zip(listChar, list1));
  }

  @Test
  public void testFold() {
    assertEquals((Object) 76,
        ListUtils.fold((x, y) -> x + y, list1, 1));
  }

}