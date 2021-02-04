package src.main.java.WarmUp.CountingValleys;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        char[] aux = String.valueOf(s).toCharArray();
        AtomicInteger nValleys = new AtomicInteger();
        AtomicInteger level = new AtomicInteger();
        IntStream.range(0, n).forEach(x -> {
            if(aux[x] == 'U')
                level.getAndIncrement();
            else if(aux[x] == 'D')
                level.getAndDecrement();
            if(level.get() == 0 && aux[x] == 'U')
                nValleys.getAndIncrement();

        });
        return nValleys.get();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String string = "holaala";
        long contar = Stream.of(string).filter(x -> x.contains("a")).count();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
