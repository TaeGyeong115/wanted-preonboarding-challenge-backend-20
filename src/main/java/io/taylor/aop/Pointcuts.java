package io.taylor.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* io.taylor..*.*(..))")
    public void allAPI() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    @Pointcut("execution(* *..*Controller.*(..))")
    public void allController() {
    }
}