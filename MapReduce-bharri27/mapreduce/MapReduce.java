package mapreduce;

import java.io.File;
import java.io.FilenameFilter;
import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class MapReduce<IK, IV, FK, FV> {
  File dir;
  Function<File, List<Pair<IK, IV>>> reader;
  Function<Pair<IK, IV>, List<Pair<FK, FV>>> mapper;
  Function<Pair<FK, List<FV>>, List<FV>> reducer;
  Comparator<FV> comparator;

  public MapReduce(File dir,
      Function<File, List<Pair<IK, IV>>> reader,
      Function<Pair<IK, IV>, List<Pair<FK, FV>>> mapper,
      Function<Pair<FK, List<FV>>, List<FV>> reducer,
      Comparator<FV> comparator) {
    this.dir = dir;
    this.reader = reader;
    this.mapper = mapper;
    this.reducer = reducer;
    this.comparator = comparator;
  }

  public List<Pair<FK, List<FV>>> get() {
    Map<FK, List<FV>> intermediates = new HashMap<>();
    for (File file : this.dir.listFiles((dir, name) -> name.endsWith(".txt"))) {
      List<Pair<IK, IV>> input = this.reader.apply(file);
      for (Pair<IK, IV> input_pair : input) {
        List<Pair<FK, FV>> mapped_pairs = this.mapper.apply(input_pair);
        for (Pair<FK, FV> mapped_pair : mapped_pairs) {
          FK key = mapped_pair.getFirst();
          FV value = mapped_pair.getSecond();
          List<FV> values = intermediates.computeIfAbsent(key, k -> new ArrayList<>());
          values.add(value);
        }
      }
    }
    List<Pair<FK, List<FV>>> finals = new ArrayList<>();
    for (Map.Entry<FK, List<FV>> entry : intermediates.entrySet()) {
      FK key = entry.getKey();
      List<FV> values = entry.getValue();
      values.sort(this.comparator);
      List<FV> final_values = this.reducer.apply(new Pair<>(key, values));
      finals.add(new Pair<>(key, final_values));
    }
    return finals;
  }
}
