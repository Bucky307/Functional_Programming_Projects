import cosc3015.Range;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static cosc3015.ListUtils.*;
import cosc3015.Range;
import cosc3015.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import tests.TestSuite;

class Main {
  public static void main(String[] args) {
    runTests();
    System.out.println(crossProduct(20, 'h'));
    List<Integer> row5 = pascal_row(Arrays.asList(1, 4, 6, 4, 1));
    System.out.println(row5);
    int sum_row5 = fold((x, l) -> x + l, row5, 0); // TODO
    System.out.println(sum_row5);

  }

  public static void runTests() {
    Result result = JUnitCore.runClasses(TestSuite.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out
        .println((result.getRunCount() - result.getFailureCount()) + "/" + result.getRunCount() + "  Tests Succeeded");
  }

  public static List<Pair<Character, Integer>> crossProduct(int n, char ch) {
    // Compute cross product of the EVEN integers from 1 to n (inclusive) and the
    // letters from 'a' to ch.
    //
    // Use the functions in ListUtils and Range to compute this. No loops, if
    // statements, or anything else like that !!map, flatmap, and filter!!
    List<Pair<Character, Integer>> cross = new ArrayList<Pair<Character, Integer>>();
    List<Integer> intList = filter(x -> {
      return (x % 2 == 0);
    }, Range.rangeInts(1, n));
    List<Character> charList = cosc3015.Range.rangeChars('a', ch);

    cross = flatmap(c -> {
      return map(x -> {
        return new Pair(x, c);
      }, intList);
    }, charList);
    return cross;
    // TODO
  }

  public static List<Integer> pascal_row(List<Integer> prev_row) {
    // TODO -- use zip and map
    List<Integer> newRowL = new ArrayList<Integer>(prev_row);
    List<Integer> newRowR = new ArrayList<Integer>(prev_row);
    newRowL.add(0, 0);
    newRowR.add(0);

    return map(l -> {
      return l.getFirst() + l.getSecond();
    }, zip(newRowL, newRowR));
  }
}