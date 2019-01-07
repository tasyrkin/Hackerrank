import java.util.Arrays;
import java.util.Scanner;

public class BiggerIsGreater {

    static int getNextToCurrent(char current, char[] chars, int startIdx, int endIdx) {
        char result = 'z'+1;
        int resultIdx = -1;
        for(int idx = startIdx; idx <= endIdx; idx++) {
            if(chars[idx] > current && chars[idx] < result) {
                result = chars[idx];
                resultIdx = idx;
            }
        }
        return resultIdx;
    }

    static String biggerIsGreater(String w) {
        if(w.length() == 1) {
            return "no answer";
        }

        char[] chars = w.toCharArray();
        char maxChar = chars[chars.length-1];
        for(int idx = chars.length - 2; idx >= 0; idx--) {
            if(chars[idx] >= maxChar) {
                maxChar = chars[idx];
            } else {
                int nextToCurrentIdx = getNextToCurrent(chars[idx], chars, idx, chars.length-1);
                char tmp = chars[nextToCurrentIdx];
                chars[nextToCurrentIdx] = chars[idx];
                chars[idx] = tmp;
                Arrays.sort(chars, idx+1, chars.length);
                return new String(chars);
            }
        }
        return "no answer";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int a0 = 0; a0 < T; a0++){
            System.out.println("Processing test: " + a0);
            String w = in.next();
            String result = biggerIsGreater(w);
            System.out.println(result);
        }
        in.close();
    }
}