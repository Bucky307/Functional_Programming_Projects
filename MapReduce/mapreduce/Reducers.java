package mapreduce;

import java.util.List;
import java.util.ArrayList;

public class Reducers {
  public static List<Integer> sum(Pair<String, List<Integer>> pair) {
    List<Integer> result = new ArrayList<>();
    int counter = 0;
    for (int i = 0; i < pair.getSecond().size(); i++) {
      counter += pair.getSecond().get(i);
    }
    result.add(counter);
    return result;
  }

  // TODO?
  public static List<Pair<String, Integer>> locations(Pair<String, List<Pair<String, Integer>>> pair) {
    return pair.getSecond();
  }

  public static List<Pair<String, Integer>> bigramReducer(Pair<String, List<Pair<String, Integer>>> pair) {
    List<Pair<String, Integer>> result = new ArrayList<>();
    List<String> repeat = new ArrayList<>();
    for (int i = 0; i < pair.getSecond().size(); i++)
      {
        int count = 1;
        String word = pair.getSecond().get(i).getFirst();
        if(!(repeat.contains(word)))
        {
          for (int j = i+1; j < pair.getSecond().size(); j++)
            {
              if(word.equals(pair.getSecond().get(j).getFirst()))
                count ++;
            }
          repeat.add(word);
          result.add(new Pair(word, count));
        }
      }
    
    return result;
  }
}