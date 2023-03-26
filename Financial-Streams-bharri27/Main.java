import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.*;

class Main {
  static Stream<String> fileToStream(String filename) {
    try {
      return Files.lines(Paths.get(filename));
    } catch (Throwable t) {
      t.printStackTrace();
      return Stream.empty();
    }
  }

  static void todo0SeeData() {
    // Nothing to do here. This is just a sample for you.
    fileToStream("data.csv").limit(10).forEach(s -> System.out.println(s));
  }

  static Record toRecord(String str) {
    String[] spl = str.split(",");
    return new Record(spl[0], spl[1], spl[2], spl[3], spl[4], spl[5], spl[6], spl[7]);

  }

  static void todo1ParseData() {
    // TODO-1: Convert the Stream<String> of lines from a file to a Stream<Record>
    // of some Record type you define, which should have a java.util.Date,
    // a String (ticker), doubles (open, high, low, close, adj close) and
    // a long (volume). Be sure to leave evidence that your code is working, e.g.,
    // some sort of output.

    fileToStream("data.csv").skip(1).limit(10).map(s -> toRecord(s)).forEach(s -> System.out.println(s));

  }

  static void todo2DescriptiveStatistics() {
    // TODO-2: Use the summaryStatistics() terminal Stream operation to learn more
    // about the Adj Close column. Take a close look at the output. Does it make
    // sense? Remember that "Adj Close" should be a price. For all future TODO
    // items,
    // you will want to filter the data to take care of this problem.
    DoubleSummaryStatistics stats = fileToStream("data.csv").skip(1).mapToDouble(s -> toRecord(s).getAdjClose()).summaryStatistics();
    Double min = stats.getMin();
    Double max = stats.getMax();
    Double average = stats.getAverage();
    System.out.println("Min: " + min + "\nMax: " + max + "\nAverage: " + average);

  }

  static void todo3StatisticsByTicker() {
    // TODO-3: In TODO-2, you got statistics for all the data combined. But it
    // should
    // really be kept separate by ticker, e.g., statistics for AAPL, statistics for
    // GOOG, etc. Group the Stream by ticker, and then process each individual
    // Stream
    // to get its own summary statistics.

    Map<String, DoubleSummaryStatistics> StatbyTicker = fileToStream("data.csv").skip(1).map(s -> toRecord(s)).filter(s -> s.getAdjClose() >= 0).collect(Collectors.groupingBy(s -> s.getTicker(), Collectors.summarizingDouble(s -> s.getAdjClose())));
    StatbyTicker.forEach((key, value) -> System.out.println(key + ":" + value));
  }

  static void todo4CalculatePercentMove() {
    // TODO-4: The actual price of something is not nearly as important as how much
    // it goes up or down. So add a column to the data that computes the daily
    // percent move for each symbol and day. This is computed as
    // (today-prev)/|prev|.
    // You only need to compute the percent change for one column, namely Adj Close.
    // You should do this with a simple map function, but notice that this function
    // is NOT A PURE function. It will need to maintain state (e.g., to remember the
    // prev value). You may assume that the data is already sorted by date.

    final Map<String, Double> previous = new HashMap<String, Double>();
    previous.put("GOOG", -9999.0); previous.put("BTC", -9999.0);
    previous.put("ETH", -9999.0); previous.put("ADA", -9999.0);
    previous.put("AAPL", -9999.0); previous.put("MSFT", -9999.0);

    Map<String, List<Record>> data = fileToStream("data.csv").skip(1).limit(30).map(s -> toRecord(s)).filter(s -> s.getAdjClose() >= 0).collect(Collectors.groupingBy(s -> s.getTicker(), Collectors.mapping(s -> { if (previous.get(s.getTicker()) == -9999.0) previous.put(s.getTicker(), s.getAdjClose()); s.setDailyPercentMove((s.getAdjClose() - previous.get(s.getTicker())) / Math.abs(previous.get(s.getTicker())));previous.put(s.getTicker(), s.getAdjClose());return s;}, Collectors.toList())));

    for (Map.Entry<String, List<Record>> entry : data.entrySet())
      System.out.println("Ticker(key): " + entry.getKey() + " Data(value): " + entry.getValue());

  }

  static void todo5CalculateZStats() {
    // TODO-5: Start by writing a custom Collector that computes your own summary
    // statistics, which should compute MIN, MAX, AVG, SUM, STDDEV, COUNT, and
    // ZRATIO. Note that with the Streams model, you need to compute these in a
    // single pass. STDDEV is tricky, but you can google for the trick to compute
    // it with a single pass (if you don't know the trick already).
    //
    // So what is ZRATIO? It's used in finance to compare different investment
    // opportunities. The idea is that if you have two opportunities with the same
    // expected profit, but one just steadily gains 0.1% per day (for example)
    // whereas the other gains 10% one day then is down 20% the next then up 5% and
    // so on -- you would prefer the one that grows steadily. Quantitatively, the
    // ZRatio is defined as the average profit per day divided by the standard
    // deviation (also per day). So higher profit is better, but more volatility is
    // worse -- and the ZRatio tries to capture both of these goals.

     Map<String, dataSummary> StatbyTicker = fileToStream("data.csv").skip(1).map(s -> toRecord(s)).filter(s -> s.getAdjClose() >= 0).collect(Collectors.groupingBy(s -> s.getTicker(), newCollector.toDataSet()));
    StatbyTicker.forEach((key, value) -> System.out.println(key + ":" + value));
  }

  public static void main(String[] args) {
    System.out.println("__________Todo0 See Data__________");
    todo0SeeData ();
    System.out.println("\n__________Todo1 Parse Data__________");
    todo1ParseData ();
    System.out.println("\n__________Todo2 Descriptive Statistics__________");    
    todo2DescriptiveStatistics ();
    System.out.println("\n__________Todo3 Statistics By Ticker__________");    
    todo3StatisticsByTicker ();
    System.out.println("\n__________Todo4 Calculate Percent Move__________");    
    todo4CalculatePercentMove();
    System.out.println("\n__________Todo5 Calculate ZStats__________");    
    todo5CalculateZStats();

  }
}