import java.util.List;

public  class Result {
    public double bestValue;
    public double[] bestX;
    public List<Double> history;

    public Result(double bestValue, double[] bestX, List<Double> history) {
        this.bestValue = bestValue;
        this.bestX = bestX;
        this.history = history;
    }
}