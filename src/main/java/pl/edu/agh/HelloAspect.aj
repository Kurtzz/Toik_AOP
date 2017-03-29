package pl.edu.agh;

public aspect HelloAspect {
    /**
     * wiczenie 01 / 4.3-4
     * Dodaj punkt cięcia: pointcut pointcutSay() : execution(* pl.edu.*.*.say*(..));
     */
    pointcut pointcutSay() : execution(* pl.edu.*.*.say*(..));

    before(): pointcutSay() {
        System.out.println("I will say: ");
    }

    /**
     * Ćwiczenie 02 / 1.1
     * Rady uruchamiane przed wywołaniem metody,
     * po wywołaniu metody, po normalnym powrocie z metody i po rzuceniu wyjątku.
     */
    after() : pointcutSay() {
        System.out.println("And I said.");
    }

    after() returning : pointcutSay() {
        System.out.println("I returned.");
    }

    after() throwing : pointcutSay() {
        System.out.println("Exception has been thrown.");
    }

    /**
     * Ćwiczenie 02 / 1.2
     * Rada, która jest uruchamiana przy konkretnym wyjątku (RuntimeException) a ignoruje inne.
     */
    after() throwing(RuntimeException e) : pointcutSay() {
        System.out.println("Exception has been thrown: " + e.toString());
    }

    /**
     * Ćwiczenie 02 / 1.3
     * Punkt cięcia, który odpowiada jedynie metodom statycznym.
     */
    pointcut staticMethods() : execution(static * *(..));

    /**
     * Ćwiczenie 02 / 1.4
     * Rada around, która przechwytuje wyjątek i wykonuje jakąś akcję
     * (wypisuje komunikat), gdy on wystąpi.
     */
    int around() : intOnly() {
        proceed();
        return 0;
    }

    /**
     * Ćwiczenie 02 / 1.5
     * Punkt cięcia, który odpowiada metodzie zwracającej int.
     */
    pointcut intOnly() : execution(int *(..));

    /**
     * Ćwiczenie 02 / 3
     * Punkt cięcia, który odpowiada metodzie zwracającej int.
     */
    pointcut setXY(Point p, int x, int y) : execution(* pl.ed.agh.Point.setXY(int, int)) && target(p) && args(x,y);

    after(Point p, int x, int y) : setXY(p, x, y) {
        System.out.println("Point : " + p);
        System.out.println("\t{x, y} = {" + x + ", " + y + "}");
    }
}
