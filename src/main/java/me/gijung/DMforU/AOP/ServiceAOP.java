//package me.gijung.DMforU.AOP;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.lang.reflect.Method;
//
//@Aspect
//@Component
//@Slf4j
//public class ServiceAOP {
//    @Pointcut("execution(* me.gijung.DMforU.service..*.*(..))")
//    public void pointcut() {
//    }
//
//    //Service 로직 시간 측정
//    @Around("pointcut()")
//    public void time_lapse(ProceedingJoinPoint joinPoint) throws Throwable {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        joinPoint.proceed();
//        stopWatch.stop();
//        log.info(stopWatch.prettyPrint());
//        log.info(String.valueOf(stopWatch.getTotalTimeSeconds()));
//    }
//}
