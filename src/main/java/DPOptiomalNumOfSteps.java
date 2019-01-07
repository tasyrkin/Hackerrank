import java.util.Arrays;
import java.util.Scanner;

public class DPOptiomalNumOfSteps {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] dp = new int[n + 1];
        int[] path = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = dp[1] = 1;
        path[1] = 1;

        for (int idx = 1; idx < dp.length; idx++) {

            if (idx + 1 < dp.length) {
                if (dp[idx] + 1 < dp[idx + 1]) {
                    path[idx + 1] = 1;
                    dp[idx + 1] = Math.min(dp[idx] + 1, dp[idx + 1]);
                }
            }
            if (idx * 2 < dp.length) {
                if (dp[idx] + 1 < dp[idx * 2]) {
                    path[2 * idx] = 2;
                    dp[idx * 2] = Math.min(dp[idx] + 1, dp[idx * 2]);
                }
            }
            if (idx * 3 < dp.length) {
                if (dp[idx] + 1 < dp[idx * 3]) {
                    path[3 * idx] = 3;
                    dp[idx * 3] = Math.min(dp[idx] + 1, dp[idx * 3]);
                }
            }
        }

        System.out.println(dp[n]);
        //System.out.println(path[n]);

        int num = path[n];
        int currN = n;
        while (num != 0) {
            System.out.print(num + " ");
            if (num == 1) {
                num = path[currN - 1];
                currN--;
            } else if(num == 2) {
                num = path[currN / 2];
                currN /= 2;
            } else {
                num = path[currN / 3];
                currN /= 3;
            }
        }
    }
}
