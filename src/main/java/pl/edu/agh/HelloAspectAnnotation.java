package pl.edu.agh;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class HelloAspectAnnotation {
    /**
     * wiczenie 01 / 4.3-4
     * Dodaj punkt cięcia: pointcut pointcutSay() : execution(* pl.edu.*.*.say*(..));
     */
    @Pointcut("execution(* pl.edu.*.*.say*(..))")
    public void pointcutSay(){}

    @Before("pointcutSay()")
    public void before() {
        System.out.println("I will say:");
    }

    /**
     * Ćwiczenie 02 / 1.1
     * Rady uruchamiane przed wywołaniem metody,
     * po wywołaniu metody, po normalnym powrocie z metody i po rzuceniu wyjątku.
     */

    @After("pointcutSay()")
    public void after() {
        System.out.println("And I said.");
    }

    @AfterReturning("pointcutSay()")
    public void afterReturning() {
        System.out.println("I returned.");
    }

    @AfterThrowing("pointcutSay()")
    public void afterThrowing() {
        System.out.println("Exception has been thrown.");
    }

    /**
     * Ćwiczenie 02 / 1.2
     * Rada, która jest uruchamiana przy konkretnym wyjątku (RuntimeException) a ignoruje inne.
     */
    @AfterThrowing(pointcut = "pointcutSay()", throwing = "e")
    public void afterThrowingRuntimeException(RuntimeException e) {
        System.out.println("Runtime Exception has been thrown.");
    }

    /**
     * Ćwiczenie 02 / 1.3
     * Punkt cięcia, który odpowiada jedynie metodom statycznym.
     */
    @Pointcut("execution(static * pl.edu.agh.Hello.*(..))")
    public void staticMethods(){}

    /**
     * Ćwiczenie 02 / 1.4
     * Rada around, która przechwytuje wyjątek i wykonuje jakąś akcję
     * (wypisuje komunikat), gdy on wystąpi.
     */
    @Around("intOnly()")
    public int around(ProceedingJoinPoint joinPoint) {
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("Catch an exception around.");
        }
        return 0;
    }

    /**
     * Ćwiczenie 02 / 1.5
     * Punkt cięcia, który odpowiada metodzie zwracającej int.
     */
    @Pointcut("execution(int pl.edu.agh.Hello.*(..))")
    public void intOnly(){}

    /**
     * Ćwiczenie 02 / 3
     * Punkt cięcia, który odpowiada metodzie zwracającej int.
     */
    @Pointcut("execution(* pl.edu.agh.Point.setXY(int,int)) && target(p) && args(x,y)")
    public void getXY(int x, int y, Point p) {}

    @Before("getXY(x, y, p)")
    public void beforeGetXY(int x, int y, Point p) {
        System.out.println("Point : " + p);
        System.out.println("\t{x, y} = {" + x + ", " + y + "}");
    }

    /**
     * Ćwiczenie 03
     * Stwórz punkt cięcia (typu call), który dopasowuje się do wszystkich metod.
     * Stwórz do niego radę i przetestuj.
     */
    @Pointcut("call(* *(..)) && within(Hello)")
    public void everything(){}

    @Before("everything()")
    public void everythingAdvice(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();
        builder.append(joinPoint.getSignature().getDeclaringTypeName() + " ");
        builder.append(joinPoint.getSourceLocation().getLine() + ": ");
        builder.append(joinPoint.getSignature().getName());
        System.out.println(builder.toString());
        System.out.println("EVERYTHING!");
    }
}
