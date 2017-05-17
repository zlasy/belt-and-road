package persistencedemo.interceptor;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
//@Component
@Slf4j
public class LogInterceptor {

	public LogInterceptor() {
		log.info("init LogInterceptor");
	}
	
    @Pointcut("execution(* persistencedemo..*.findDemo())")
    public void logPoint(){ }
    
    @Before("logPoint()")
    public void beforeSleep(){
    	log.info("before joinPoint");
    }
    
    @AfterReturning("logPoint()")
    public void afterFind(){
    	log.info("sth happened after returning!");
    }
}
