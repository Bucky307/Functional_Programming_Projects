import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collector;
import java.util.Collections;
import java.util.Map;
import java.util.*;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.BiConsumer;
import java.util.Set;

public class newCollector implements Collector<Record, dataSet, dataSummary>{
public static newCollector toDataSet()
  {
    return new newCollector();
  }

  public Supplier<dataSet> supplier()
  {
    return()-> new dataSet();
  }
  public BinaryOperator<dataSet> combiner()
  {
    return (x, y) -> x;
  }
  public BiConsumer<dataSet, Record> accumulator()
  {
    return (data, record) -> 
      {
        Double p = record.getAdjClose();
        if(p<data.getMin())
          data.setMin(p);
        if(p>data.getMax())
          data.setMax(p);
        data.incrementSum(p);
        data.incrementSumOfSquares(p);
        data.incrementCount();
      };
  }
  public Function <dataSet, dataSummary> finisher()
  {
    return data-> new dataSummary(data.getMin(), data.getMax(), data.getSum()/data.getCount(), data.getSum(), Math.sqrt((data.getSumOfSquares()/data.getCount())-(Math.pow(data.getSum()/data.getCount() , 2.0))), data.getCount(), (data.getSum()/data.getCount())/Math.sqrt((data.getSumOfSquares()/data.getCount())-(Math.pow(data.getSum()/data.getCount() , 2.0))));
  }
  //@Overide 
  public Set<Characteristics> characteristics()
  {
    return Set.of(Characteristics.UNORDERED);
  }
}