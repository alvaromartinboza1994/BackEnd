package src.main.java.WarmUp.JumpingOnTheClouds;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Solution {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        List<Integer> integerList = new LinkedList<>();
        AtomicInteger actual = new AtomicInteger();
        IntStream.range(0, c.length).forEach(x -> {
            if(integerList.isEmpty() && c[x] == 0){
                integerList.add(actual.addAndGet(x));
            }
            if((actual.get() + 2) < c.length && c[actual.get() + 2] == 0){
                integerList.add(actual.addAndGet(2));
            }
            else if((actual.get() + 1) < c.length && c[actual.get() + 1] == 0){
                integerList.add(actual.addAndGet(1));
            }
        });
        return integerList.size() - 1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
