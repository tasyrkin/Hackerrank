import java.math.BigInteger;
import java.util.Scanner;

public class AVeryBigSum {

    static long aVeryBigSum(int n, long[] ar) {
        BigInteger result = BigInteger.ZERO;
        for(int i = 0; i < ar.length; i++) {
            result.add(new BigInteger(String.valueOf(ar[i])));
        }
        return result.longValue();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] ar = new long[n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            ar[ar_i] = in.nextLong();
        }
        long result = aVeryBigSum(n, ar);
        System.out.println(result);
    }
}