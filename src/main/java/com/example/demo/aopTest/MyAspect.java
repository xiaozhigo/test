package com.example.demo.aopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(MyAnnotation)")
    public void MyAnntation(){
    }

    @Before("MyAnntation()")
    public void beforePointcut(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        String value = annotation.value();
        System.out.println("开始"+value);
    }

    @After("MyAnntation()")
    public void afterPointcut(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        String value = annotation.value();
        System.out.println("结束"+value);
    }
}
