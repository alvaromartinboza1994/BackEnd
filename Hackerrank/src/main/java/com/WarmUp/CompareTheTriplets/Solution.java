package src.main.java.WarmUp.CompareTheTriplets;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the compareTriplets function below.
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> resultado = new LinkedList<>();
        resultado.add(0); resultado.add(0);
        if(a.size() == b.size()){
            IntStream.range(0, a.size()).forEach(x -> {
                if(a.get(x) > b.get(x)){
                    resultado.set(0, resultado.get(0) + 1);
                } else if(a.get(x) < b.get(x)){
                    resultado.set(1, resultado.get(1) + 1);
                }
            });
        }
        return resultado;
    }

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = compareTriplets(a, b);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}