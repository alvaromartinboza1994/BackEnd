package src.main.java.WarmUp.SimpleArraySum;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    /*
     * Complete the simpleArraySum function below.
     */
    static int simpleArraySum(int[] ar) {
        IntStream intStream = Arrays.stream(ar);
        return intStream.sum();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arCount = Integer.parseInt(scanner.nextLine().trim());
*/
        int[] ar = { 1, 2, 3, 4, 5 };

        /*String[] arItems = scanner.nextLine().split(" ");

        for (int arItr = 0; arItr < arCount; arItr++) {
            int arItem = Integer.parseInt(arItems[arItr].trim());
            ar[arItr] = arItem;
        }*/

        int result = simpleArraySum(ar);
        System.out.println(result);
    }
}
