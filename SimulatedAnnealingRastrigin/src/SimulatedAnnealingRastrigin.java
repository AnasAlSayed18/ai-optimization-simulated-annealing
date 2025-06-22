import java.util.*;

public class SimulatedAnnealingRastrigin {



    public static Result optimize() {
        int dimantion = 15;
        double minValue = -2.0, maxValue = 2.0;
        double temp = 100.0, minTemp = 0.001, alpha = 0.95;
        int maxIter = 1000;

        Random rand = new Random();
        double[] current = initializeSolution(dimantion, minValue, maxValue, rand);
        double currentEval = rastrigin(current);
        double[] best = Arrays.copyOf(current, dimantion);
        double bestEval = currentEval;

        List<Double> history = new ArrayList<>();
        history.add(currentEval);

        for (int i = 0; i < maxIter; i++) {
            double[] candidate = findNewNeighbors(current, 0.05, minValue, maxValue, rand);
            double candidateEval = rastrigin(candidate);

            if (accept(candidateEval, currentEval, temp, rand)) {
                current = candidate;
                currentEval = candidateEval;
            }

            if (currentEval < bestEval) {
                best = Arrays.copyOf(current, dimantion);
                bestEval = currentEval;
            }

            history.add(bestEval);
            temp *= alpha;
        }

        return new Result(bestEval, best, history);
    }

    private static double[] initializeSolution(int dim, double lower, double upper, Random rand) {
        double[] solution = new double[dim];
        for (int i = 0; i < dim; i++) {
            solution[i] = lower + (upper - lower) * rand.nextDouble();
        }
        return solution;
    }

    private static boolean accept(double newEnergy, double currentEnergy, double temperature, Random rand) {
        double acceptanceProbability=Math.exp((currentEnergy - newEnergy) / temperature);

        return newEnergy < currentEnergy || acceptanceProbability > rand.nextDouble();
    }

    private static double[] findNewNeighbors(double[] x, double stdDev, double lower, double upper, Random rand) {
        double[] newX = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            newX[i] = x[i] + rand.nextGaussian() * stdDev;
            newX[i] = Math.max(lower, Math.min(upper, newX[i]));
        }
        return newX;
    }

    private static double rastrigin(double[] x) {
        double sum = 10.0* x.length;
        for (double xi : x) {
            sum += xi * xi - 10.0 * Math.cos(2 * Math.PI * xi);
        }
        return sum;
    }
}
