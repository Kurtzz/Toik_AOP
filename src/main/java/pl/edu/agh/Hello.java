package pl.edu.agh;

import java.util.Random;

public class Hello {
    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.sayHello();
        hello.sayGoodMorning();
        hello.returnInt();
        hello.sayGoodBye();
    }

    private void sayHello() {
        System.out.println("Hello");
    }

    private void sayGoodMorning() {
        System.out.println("Good Morning");
    }

    private void sayGoodBye() {
        System.out.println("Good Bye");
        throw new RuntimeException();
    }

    private int returnInt() {
        return new Random().nextInt();
    }
}
