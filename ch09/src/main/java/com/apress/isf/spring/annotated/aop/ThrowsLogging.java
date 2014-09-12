/**
 * 
 */
package com.apress.isf.spring.annotated.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Felipe Gutierrez
 *
 */
//@Component
@Aspect
public class ThrowsLogging {
	private static final Logger log = LoggerFactory.getLogger(ThrowsLogging.class);
	
	@AfterThrowing(
		      pointcut = "execution(* com.apress.isf.java.service.SearchEngine.*(..))",
		      throwing= "error")
	public void log(JoinPoint joinPoint, Throwable error) {
		if(log.isDebugEnabled()){
			log.debug("@@@(THROWS) Method called: " + joinPoint.getSignature());
			if(joinPoint.getArgs().length ==0 )
				log.debug("@@@@(THROWS) No arguments passed.");
			for(Object arg:joinPoint.getArgs())
				log.debug("@@@@(THROWS) Argument passed:" + arg);
			log.debug("@@@(THORWS) Error: " + error.getMessage());
		}
	}
}
