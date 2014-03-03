package akka.prime;

/**
 * Created by thecodemaker on 3/3/14.
 */
public class Main {

    public static void main(String[] args) {
        long startNumber = Long.parseLong("0");
        long endNumber = Long.parseLong("100");

        PrimeCalculator primeCalculator = new PrimeCalculator();
        primeCalculator.calculate( startNumber, endNumber );
    }
}
