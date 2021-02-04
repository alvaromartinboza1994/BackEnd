package src.main.java.WarmUp.RepeatedString;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long nrepeticiones = (long) ((n / (s.length())));
        int as = (int) s.chars().filter(x -> x == 'a').count();
        if((int) ((n % (s.length()))) != 0){
            String subString = s.substring(0, (int) (n - (s.length() * nrepeticiones)));
            return (nrepeticiones * as) + (int) subString.chars().filter(x -> x == 'a').count();
        }
        return nrepeticiones * as;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
