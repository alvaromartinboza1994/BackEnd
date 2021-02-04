package src.main.java.Lambdas;

import java.util.function.BiFunction;

public class Lambdas {

    public static void main(String[] args) {
        System.out.println( processString("Hola mundo!", String::toLowerCase));
        System.out.println( processString2("Hola mundo!", 5, String::substring));
    }

    private static String processString(String str, Processor processor){
        return processor.process(str);
    }

    private static String processString2(String str, Integer i, BiFunction<String, Integer, String> processor){
        return processor.apply(str, i);
    }
}

@FunctionalInterface
interface  Processor {
    public String process(String str);
}
