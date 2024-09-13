package com.dmforu.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class ServiceAOP {
    @Pointcut("execution(* me.gijung.DMforU.service.token..*.*(..))")
    public void pointcut() {
    }

    //Service 로직 시간 측정
    @Around("pointcut()")
    public void timeLapse(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed();
        stopWatch.stop();
        log.info(declaringClass.getSimpleName());
        log.info(stopWatch.prettyPrint());
        log.info(String.valueOf(stopWatch.getTotalTimeSeconds()));
        log.info(String.valueOf(Thread.currentThread()));
    }
}