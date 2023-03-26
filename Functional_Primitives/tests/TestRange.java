package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;

import cosc3015.Range;

public class TestRange {
  @Test
  public void testRangeInts() {
    List<Integer> list1 = Range.rangeInts(10, 15);
    assertEquals(Arrays.asList(10, 11, 12, 13, 14, 15),
        list1);
  }

  @Test
  public void testRangeChars() {
    List<Character> list1 = Range.rangeChars('a', 'e');
    assertEquals(Arrays.asList('a', 'b', 'c', 'd', 'e'),
        list1);
  }
}