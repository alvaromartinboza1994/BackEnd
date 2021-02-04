package src.main.java.Sorting.BubbleSort;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.regex.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    swap(j, j + 1, a);
                    count++;
                }
            }
        }
        Supplier<IntStream> intStreamSupplier = () -> Arrays.stream(a);
        System.out.println("Array is sorted in " + count + " swaps.");
        intStreamSupplier.get().findFirst().ifPresent(x -> System.out.println("First Element: " + x));
        intStreamSupplier.get().reduce((first, second) -> second).ifPresent(x -> System.out.println("Last Element: " + x));
    }

    static void swap(int pos1, int pos2, int[] a){
        int aux = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = aux;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
