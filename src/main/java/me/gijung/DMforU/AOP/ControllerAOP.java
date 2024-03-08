//package me.gijung.DMforU.AOP;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpRequest;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Component
//@Slf4j
//@Aspect
//public class ControllerAOP {
//
//    @Pointcut("execution(* me.gijung.DMforU.controller..*.*(..))")
//    public void pointcut() {
//    }
//
//    @Before("pointcut()")
//    public void Afterlog(JoinPoint joinPoint) {
//        String ip_addr = null;
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        System.out.println(signature.getMethod());
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            System.out.println(arg.getClass().getSimpleName());
//            System.out.println(arg);
//        }
//        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = sra.getRequest();
//
//        ip_addr = request.getHeader("X-Forwarded-For");
//        if (ip_addr == null) {
//            ip_addr = request.getHeader("Proxy-Client-IP");
//            System.out.println("ip_addr = " + ip_addr);
//        }
//        if (ip_addr == null) {
//            ip_addr = request.getHeader("WL-Proxy-Client-IP");
//            System.out.println("ip_addr = " + ip_addr);
//        }
//        if (ip_addr == null) {
//            ip_addr = request.getHeader("HTTP_CLIENT_IP");
//            System.out.println("ip_addr = " + ip_addr);
//        }
//        if (ip_addr == null) {
//            ip_addr = request.getHeader("HTTP_X_FORWARDED_FOR");
//            System.out.println("ip_addr = " + ip_addr);
//        }
//        if (ip_addr == null) {
//            ip_addr = request.getRemoteAddr();
//            System.out.println("ip_addr = " + ip_addr);
//        }
//
//    }
//
//}
