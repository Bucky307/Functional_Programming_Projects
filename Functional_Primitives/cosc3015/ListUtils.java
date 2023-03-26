package cosc3015;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import cosc3015.Pair;

public class ListUtils {
  public static <A, B> List<B> map(Function<A, B> fn, List<A> list) {
    // TODO
    List<B> m = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      m.add(fn.apply(list.get(i)));
    }
    return m;
  }

  public static <A, B> List<B> flatmap(Function<A, List<B>> fn, List<A> list) {
    // TODO
    List<B> fm = new ArrayList<>();
    List<B> temp = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      temp = fn.apply(list.get(i));
      for (int j = 0; j < temp.size(); j++) {
        fm.add(temp.get(j));
      }
    }
    return fm;
  }

  public static <A> List<A> filter(Function<A, Boolean> fn, List<A> list) {
    // TODO
    List<A> f = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      if (fn.apply(list.get(i)))
        f.add(list.get(i));
    }
    return f;
  }

  public static <A, B> List<Pair<A, B>> zip(List<A> list1, List<B> list2) {
    // TODO
    List<Pair<A, B>> z = new ArrayList<Pair<A, B>>();
    int shortest;
    if (list1.size() <= list2.size())
      shortest = list1.size();
    else
      shortest = list2.size();
    for (int i = 0; i < shortest; i++) {
      Pair<A, B> temp = new Pair<>(list1.get(i), list2.get(i));
      z.add(temp);
    }
    return z;
  }

  public static <A> A fold(BinaryOperator<A> fn, List<A> list, A identity) {
    // TODO
    A f;
    f = fn.apply(identity, list.get(0));
    for (int i = 1; i < list.size(); i++) {
      f = fn.apply(f, list.get(i));
    }
    return f;
  }
}