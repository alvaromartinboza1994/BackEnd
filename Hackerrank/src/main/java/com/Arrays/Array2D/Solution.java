package src.main.java.Arrays.Array2D;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        List<Integer> sumValues = new LinkedList<>();
        IntStream.range(0, 4).forEach(fila -> {
            IntStream.range(0, 4).forEach(columna -> {
                List<Integer> subList = new LinkedList<>();
                IntStream.range(fila, fila + 3).forEach(filaActual -> {
                    IntStream.range(columna, columna + 3).forEach(columnaActual -> {
                        if((filaActual != (fila + 1) && columnaActual != columna) ||
                                (filaActual != (fila + 1) && columnaActual != (columna + 2)) ||
                                (filaActual == (fila + 1) && columnaActual == (columna + 1))){
                            subList.add(arr[filaActual][columnaActual]);
                        }
                    });
                });
                sumValues.add(subList.stream().mapToInt(Integer::intValue).sum());
            });
        });
        return sumValues.stream().mapToInt(Integer::valueOf).max().orElseThrow(NoSuchElementException::new);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
