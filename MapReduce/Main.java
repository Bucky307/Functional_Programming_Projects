import java.io.File;
import java.util.List;

import mapreduce.Pair;
import mapreduce.MapReduce;
import mapreduce.Readers;
import mapreduce.Mappers;
import mapreduce.Reducers;

class Main {
  public static void main(String[] args) {
    // WordCount
    System.out.println("Word Count:");
    MapReduce<String, String, String, Integer> wc = new MapReduce<>(
        new File("inputs"),
        Readers::mapFilenameToLine,
        Mappers::mapEachWordToOne,
        Reducers::sum,
        (x, y) -> y - x);
    List<Pair<String, List<Integer>>> counts = wc.get();
    counts.sort((p1, p2) -> p2.getSecond().get(0) - p1.getSecond().get(0));
    for (int i = 0; i < 10; i++)
      System.out.println(counts.get(i));

    // TODO: WordIndex
    System.out.println("\nWord Index:");
    MapReduce<String, String, String, Pair<String, Integer>> wi = new MapReduce<>(
        new File("inputs"),
        Readers::mapFileToContents,
        Mappers::mapEachWordToLocation,
        Reducers::locations,
        (x, y) -> x.getSecond() - y.getSecond());
    List<Pair<String, List<Pair<String, Integer>>>> locations = wi.get();
    for (int i = 0; i < 6; i++)
      System.out.println(locations.get(i));
    // TODO: BiGrams (words)
    System.out.println("\nBiGrams:");
    MapReduce<String, String, String, Pair<String, Integer>> bg = new MapReduce<>(
        new File("inputs"),
        Readers::mapFilenameToLine,
        Mappers::mapBigram,
        Reducers::bigramReducer,
        (x, y) -> x.getFirst().compareTo(y.getFirst()));
    List<Pair<String, List<Pair<String, Integer>>>> bigram = bg.get();
    for (int i = 0; i < 10; i++)
      System.out.println(bigram.get(i));
  }
}
