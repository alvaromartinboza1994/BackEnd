package src.main.java.HashMaps.RansomNote;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Solution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        HashMap<String, Integer> wordsMap = new HashMap<>();
        Stream.of(magazine).forEach(x -> {
            if(wordsMap.get(x) != null){
                wordsMap.replace(x, wordsMap.get(x) + 1);
            } else{
                wordsMap.put(x, 1);
            }
        });
        AtomicReference<String> resultado = new AtomicReference<>("Yes");
        Stream.of(note).forEach(x -> {
            if(wordsMap.get(x) == null || wordsMap.get(x) == 0){
                resultado.set("No");
            } else {
                wordsMap.replace(x, wordsMap.get(x) - 1);
            }
        });
        System.out.println(resultado);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
