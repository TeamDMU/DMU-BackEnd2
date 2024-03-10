package me.gijung.DMforU.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceAOP {
    @Pointcut("execution(* me.gijung.DMforU.service..*.*(..))")
    public void pointcut() {
    }


}
