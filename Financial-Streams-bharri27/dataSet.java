import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.*;

public class dataSet{
  Double min, max, sum, sumOfSquares;
  Integer count;
  
public dataSet()
  {
  this.min = 9999999999999999999.0;
  this.max = -999999999999999999.0;
  this.sum = 0.0;
  this.sumOfSquares = 0.0;
  this.count = 0;
  }

public Double getMin()
  {
    return this.min;
  }
public Double getMax()
  {
    return this.max;
  }
public Double getSum()
  {
    return this.sum;
  }
public Double getSumOfSquares()
  {
    return this.sumOfSquares;
  }
public Integer getCount()
  {
    return this.count;
  }

public void setMin(Double num)
  {
    this.min = num; 
  }
  public void setMax(Double num)
  {
    this.max = num; 
  }
  public void incrementSum(Double num)
  {
    this.sum = this.sum + num;
  }
  public void incrementSumOfSquares(Double num)
  {
    this.sumOfSquares = this.sumOfSquares + num * num;
  }
  public void incrementCount()
  {
    this.count = this.count + 1;
  }
}