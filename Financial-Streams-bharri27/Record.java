import java.util.Arrays;
import java.time.LocalDate;

public class Record {
  LocalDate date;
  String ticker;
  Double open;
  Double high;
  Double low;
  Double close;
  Double adjClose;
  long volume;
  Double dailyPercentMove;

  public Record(String a, String b, String c, String d, String e, String f, String g, String h) {
    this.date = LocalDate.parse(a);
    this.ticker = b;
    this.open = Double.parseDouble(c);
    this.high = Double.parseDouble(d);
    this.low = Double.parseDouble(e);
    this.close = Double.parseDouble(f);
    this.adjClose = Double.parseDouble(g);
    this.volume = Long.parseLong(h);
    this.dailyPercentMove = null;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public String getTicker() {
    return this.ticker;
  }
  public Double getOpen() {
    return this.open;
  }
  public Double getHigh() {
    return this.high;
  }
  public Double getLow() {
    return this.low;
  }
  public Double getClose() {
    return this.close;
  }
  public Double getAdjClose() {
    return this.adjClose;
  }
  public long getVolume() {
    return this.volume;
  }
  public void setDailyPercentMove(Double x){
    this.dailyPercentMove = x;
  }

  @Override
  public String toString() {
    if (this.dailyPercentMove == null)
    {
      return "Date: " + this.date + " Ticker: " + this.ticker + " Open: " + this.open+ " High: " + this.high+ " Low: " + this.low+ " Close: " + this.close+ " adjClose: " + this.adjClose+ " Volume: " + this.volume;
    }
    else{
       return "Date: " + this.date + " Ticker: " + this.ticker + " Open: " + this.open+ " High: " + this.high+ " Low: " + this.low+ " Close: " + this.close+ " adjClose: " + this.adjClose+ " Volume: " + this.volume + " Daily Percent Move: "+ this.dailyPercentMove;
    }
    
  }
}