
public class dataSummary 
{
  Double min;
  Double max;
  Double average;
  Double sum;
  Double standardDev;
  Integer count;
  Double zratio;

  public dataSummary(Double a, Double b, Double c, Double d, Double e, Integer f, Double g)
  {
    this.min = a;
    this.max = b;
    this.average = c;
    this.sum = d;
    this.standardDev = e;
    this.count = f;
    this.zratio = g;
  }

  @Override
  public String toString()
  {
    return "(Count: " + this.count + ", Sum: "+ this.sum + " Min: " +this.min + ", Average: " + this.average +", Max " + this.max +   ", standardDev: " + this.standardDev +  ", Zratio: " +this.zratio +")";
  }
}