import java.util.Scanner;

public class Leibniz2 {
    public static void main(final String[] q) {
        Scanner c = new Scanner(System.in);
        int t = c.nextInt();
        while (t-- > 0) {
            double sum = 1, d = 3;
            for (int i = 1, s = -1, N = c.nextInt(); i < N; i++, d += 2, s *= -1) {
                sum = (sum * d + s) / d;
            }

            System.out.format("%.15f%n", sum);
        }
    }
}
