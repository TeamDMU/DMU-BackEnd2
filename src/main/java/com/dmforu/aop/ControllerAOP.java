package com.dmforu.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Component
@Slf4j
@Aspect
public class ControllerAOP {

    @Pointcut("execution(* me.gijung.DMforU.controller..*.*(..))")
    public void pointCut() {
    }

//    //Controller 시간
//    @Around("pointcut()")
//    public void time_lapse(ProceedingJoinPoint joinPoint) throws Throwable {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        joinPoint.proceed();
//        stopWatch.stop();
//        log.info(stopWatch.prettyPrint());
//        log.info(String.valueOf(stopWatch.getTotalTimeSeconds()));
//    }

    // Controller 메소드 수행이 성공하면 , Method이름 경로 기록
    @AfterReturning("pointCut()")
    public void controllerLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("Method_Name :: {} ||  URL :: {}",method.getName(),attribute.getRequest().getRequestURI());
    }
}