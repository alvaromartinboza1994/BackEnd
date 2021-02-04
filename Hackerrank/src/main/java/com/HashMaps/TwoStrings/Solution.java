package src.main.java.HashMaps.TwoStrings;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Solution {

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        AtomicReference<String> resultado = new AtomicReference<>("NO");
        char[] letrasS1 = s1.toCharArray();
        char[] letrasS2 = s2.toCharArray();
        HashMap<Character, Integer> subStringMap = new HashMap<>();
        IntStream.range(0, letrasS1.length).forEach(x -> {
            subStringMap.putIfAbsent(letrasS1[x], 1);
        });
        IntStream.range(0, letrasS2.length).forEach(x -> {
            if(subStringMap.containsKey(letrasS2[x])){
                resultado.set("YES");
            }
        });
        return resultado.get();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
