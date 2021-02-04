package src.main.java.WarmUp.Ryanair;

import java.io.*;
import java.lang.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

interface Operation {
    boolean check(int number);
}

class Number {
    public boolean checker(Operation operation, int number) {
        return operation.check(number);
    }
//PUT YOUR CODE HERE

    public Operation isOdd() {
        return new Operation() {
            @Override
            public boolean check(int number) {
                return number % 2 != 0;
            }
        };
    }

    public Operation isPalindrome() {
        return new Operation() {
            @Override
            public boolean check(int number) {
                int cantidad = String.valueOf(number).length();
                AtomicBoolean esP = new AtomicBoolean(true);
                char[] aux = String.valueOf(number).toCharArray();
                IntStream.range(0, cantidad).forEach(x -> {
                    if(aux[x] != aux[cantidad - x - 1]){
                        esP.set(false);
                    }
                });
                return esP.get();
            }
        };
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        Number number = new Number();
        String answer = null;
        int conditionToCheck = 1;
        int numberToCheck = 303;
        if (conditionToCheck == 1) {
            Operation operation = number.isOdd();
            boolean result = number.checker(operation, numberToCheck);
            answer = result ? "ODD" : "EVEN";
        } else if (conditionToCheck == 2) {
            Operation operation = number.isPalindrome();
            boolean result = number.checker(operation, numberToCheck);
            answer = result ? "PALINDROME" : "NOT PALINDROME";
        }
        System.out.println(answer);
    }
}
