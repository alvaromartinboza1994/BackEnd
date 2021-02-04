package src.main.java.CAvsInt;

public interface Animal {

    public abstract void hacerRuido();

    public default void algo() {
        System.out.println("RUIDO DEFAULT");
    }
}
