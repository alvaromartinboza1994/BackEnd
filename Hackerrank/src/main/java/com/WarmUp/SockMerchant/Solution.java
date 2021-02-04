package src.main.java.WarmUp.SockMerchant;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    // Complete the sockMerchant function below.
    static int sockMerchant(int[] ar) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        IntStream intStream = Arrays.stream(ar);
        intStream.forEach(x -> {
            if(hashMap.containsKey(x)){
                hashMap.replace(x, hashMap.get(x) + 1);
            } else{
                hashMap.put(x, 1);
            }
        });
         hashMap.entrySet()
                .forEach(e -> {
                    if(e.getValue() % 2 != 0){
                        e.setValue(e.getValue() - 1);
                    }
                    if(e.getValue() != 0){
                        e.setValue(e.getValue() / 2);
                    }
                });
         intStream = new ArrayList<>(hashMap.values()).stream().mapToInt(Integer::intValue);
        return intStream.sum();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
