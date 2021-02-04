package src.main.java.StringManipulation;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {

        final List<Character>[] characterListB = new List[]{getListFromString(b)};
        List<Character> characterListA = getListFromString(a).stream().map(x -> {
            Character finalX = x;
            characterListB[0] = characterListB[0].stream().filter(y -> y.equals(finalX)).map(y -> '-').collect(Collectors.toList());
            /*if(character.get() != x){
                x = '-';
                character.set(' ');
            }*/
            return x;
        }).collect(Collectors.toList());

        return 0;
    }

    static List<Character> getListFromString(String string){
        List<Character> characterList = new LinkedList<>();
        string.chars().forEach(x -> {
            characterList.add((char) x);
        });
        return characterList;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
