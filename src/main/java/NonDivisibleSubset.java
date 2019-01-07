import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NonDivisibleSubset {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the nonDivisibleSubset function below.
    static int nonDivisibleSubset(int k, int[] S) {
        if (k == 1) return 0;
        Map<Integer, Integer> mod = new HashMap<>();
        for (int idx = 0; idx < S.length; idx++) {
            int currMod = S[idx] % k;
            Integer count = mod.get(currMod);
            if (count == null) {
                count = 0;
            }
            mod.put(currMod, count + 1);
        }
        Integer[] mods = mod.keySet().toArray(new Integer[0]);
//        System.out.println("mods = " + Arrays.toString(mods));
//        System.out.println("mod = " + mod);
        int max = 0;
        for (int idx = 0; idx < mods.length; idx++) {

            final Set<Integer> chosenMods = new HashSet<>();
            chosenMods.add(mods[idx]);

            int currMax = (mods[idx] + mods[idx]) % k != 0 ? mod.get(mods[idx]) : 1;

            main:
            for (int idx2 = 0; idx2 < mods.length; idx2++) {

                if (idx == idx2) continue;

                for (int alreadyChosen : chosenMods) {
                    if ((alreadyChosen + mods[idx2]) % k == 0) {
                        continue main;
                    }
                }

                if ((mods[idx] + mods[idx2]) % k != 0) {
                    currMax += mod.get(mods[idx2]);
                    chosenMods.add(mods[idx2]);
                }
            }
            if (max < currMax) {
                max = currMax;
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] S = new int[n];

        String[] SItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int SItem = Integer.parseInt(SItems[i]);
            S[i] = SItem;
        }

        int result = nonDivisibleSubset(k, S);

        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
