import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MinimumLoss {

    static long minimumLoss(long[] prices) {
        Map<Long, Integer> priceToIndex = new HashMap<>();
        for(int i = 0; i < prices.length; i++) {
            priceToIndex.put(prices[i], i);
        }
        Long[] sortedPrices = priceToIndex.keySet().toArray(new Long[0]);
        Arrays.sort(sortedPrices);
        long minDiff = Long.MAX_VALUE;
        for(int i = 0; i < sortedPrices.length-1; i++){
            Integer currentPriceIndex = priceToIndex.get(sortedPrices[i]);
            Integer nextPriceIndex = priceToIndex.get(sortedPrices[i+1]);
            if(currentPriceIndex > nextPriceIndex) {
                if(minDiff > sortedPrices[i+1] - sortedPrices[i]) {
                    minDiff = sortedPrices[i+1] - sortedPrices[i];
                }
            }
        }
        return minDiff;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] price = new long[n];
        for(int price_i = 0; price_i < n; price_i++){
            price[price_i] = in.nextLong();
        }
        long result = minimumLoss(price);
        System.out.println(result);
        in.close();
    }
}
