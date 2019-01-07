import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CloudyDay {

    static long maximumPeople(long[] p, long[] x, long[] clouds, long[] cloudsRange) {
        Town[] towns = new Town[x.length];
        for (int i = 0; i < towns.length; i++) {
            towns[i] = new Town(x[i], p[i]);
        }
        for (int i = 0; i < clouds.length; i++) {
            int leftIdx = Arrays.binarySearch(x, clouds[i] - cloudsRange[i]);
            if (leftIdx < 0) {
                leftIdx = -leftIdx;
                leftIdx--;
                if (leftIdx < 0) leftIdx = 0;
            }
            int rightIdx = Arrays.binarySearch(x, clouds[i] + cloudsRange[i]);
            if (rightIdx < 0) {
                rightIdx = -rightIdx;
                rightIdx -= 2;
                if (rightIdx >= x.length) rightIdx--;
            }
            //System.out.println("leftIdx:" + leftIdx + ", rightIdx:" + rightIdx);
            if (leftIdx >= 0 && leftIdx < towns.length && rightIdx >= 0 && rightIdx < towns.length) {
                for (int j = leftIdx; j <= rightIdx; j++) {
                    towns[j].cloudCovered++;
                    towns[j].numCloud = i;
                }
            }
        }
        long sumUncovered = 0;
        Map<Integer, Long> cloudCovered = new HashMap<>();
        for (int i = 0; i < towns.length; i++) {
            if (towns[i].cloudCovered == 0) {
                sumUncovered += towns[i].population;
            }
            if (towns[i].cloudCovered == 1) {
                cloudCovered.putIfAbsent(towns[i].numCloud, 0L);
                cloudCovered.put(towns[i].numCloud, cloudCovered.get(towns[i].numCloud) + towns[i].population);
            }
        }
        long maxOnceCovered = 0;
        for(Map.Entry<Integer, Long> entry : cloudCovered.entrySet()) {
            if(maxOnceCovered < entry.getValue()) {
                maxOnceCovered = entry.getValue();
            }
        }

        return sumUncovered + maxOnceCovered;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] p = new long[n];
        for (int p_i = 0; p_i < n; p_i++) {
            p[p_i] = in.nextLong();
        }
        long[] x = new long[n];
        for (int x_i = 0; x_i < n; x_i++) {
            x[x_i] = in.nextLong();
        }
        int m = in.nextInt();
        long[] y = new long[m];
        for (int y_i = 0; y_i < m; y_i++) {
            y[y_i] = in.nextLong();
        }
        long[] r = new long[m];
        for (int r_i = 0; r_i < m; r_i++) {
            r[r_i] = in.nextLong();
        }
        long result = maximumPeople(p, x, y, r);
        System.out.println(result);
        in.close();
    }

    static class Town {
        long pos;
        long population;
        long cloudCovered;
        int numCloud;

        public Town(long pos, long pop) {
            this.pos = pos;
            this.population = pop;
            this.cloudCovered = 0;
            this.numCloud = -1;
        }
    }
}
