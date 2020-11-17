package com.jt.aop;

import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class RunTimeAspect {
    //任意返回值 service包下所有的类下 所有的方法 任意数量和类型的参数
    @Pointcut("execution(* com.jt.service..*.*(..))")
    public void runTime(){}

    @Around("runTime()")
    public Object getRunTime(ProceedingJoinPoint joinPoint){
        try {

            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }


    }

}
