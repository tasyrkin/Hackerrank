import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.text.DecimalFormat;

import java.util.Scanner;

public class Leibniz {
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        DecimalFormat df = new DecimalFormat("#.###############");
        df.setRoundingMode(RoundingMode.HALF_UP);

        BigDecimal[] arr2 = new BigDecimal[3000001];
        arr2[0] = BigDecimal.ONE;
        for (int i = 1; i < arr2.length; i++) {
            arr2[i] = arr2[i - 1].add(new BigDecimal(i % 2 == 0 ? 1 : -1).multiply(
                        BigDecimal.ONE.divide(new BigDecimal((2 * i + 1)), MathContext.DECIMAL128)));
        }
        while (t-- > 0) {
            int n = sc.nextInt();

            if (n == 0) {
                System.out.println(1);
            } else if (n >= arr2.length) {
                System.out.println(arr2[arr2.length - 1]);
            } else {
                BigDecimal v = arr2[n - 1].setScale(15, BigDecimal.ROUND_HALF_UP);
                System.out.println(df.format(v));
            }
        }
    }
}
