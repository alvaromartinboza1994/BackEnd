package com.WarmUp.DiagonalDifference;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {

        int left = leftDiagonal(arr);
        int right = rightDiagonal(arr);

        return Math.abs(left - right);

    }

    private static int rightDiagonal(List<List<Integer>> arr) {
        AtomicInteger inicio = new AtomicInteger();
        AtomicInteger fin = new AtomicInteger(arr.size() - 1);
        AtomicInteger right = new AtomicInteger();
        IntStream.range(0, arr.size()).forEach(x -> {
            right.addAndGet(arr.get(inicio.get()).get(fin.get()));
            inicio.getAndIncrement();
            fin.getAndDecrement();

        });
        return right.get();
    }

    private static int leftDiagonal(List<List<Integer>> arr) {
        AtomicInteger left = new AtomicInteger();
        IntStream.range(0, arr.size()).forEach(x -> {
            IntStream.range(0, arr.size()).forEach(y -> {
                if(x == y){
                    left.addAndGet(arr.get(x).get(y));
                }
            });
        });
        return left.get();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

