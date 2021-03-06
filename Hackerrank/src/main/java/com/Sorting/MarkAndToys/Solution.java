package src.main.java.Sorting.MarkAndToys;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        AtomicInteger count = new AtomicInteger();
        AtomicInteger sum = new AtomicInteger();
        Arrays.sort(prices);
        Arrays.stream(prices).filter(value -> value < k).forEach(x -> {
            if(sum.get() <= k){
                sum.addAndGet(x);
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
