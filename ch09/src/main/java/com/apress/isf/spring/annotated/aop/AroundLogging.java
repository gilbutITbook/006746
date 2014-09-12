/**
 * 
 */
package com.apress.isf.spring.annotated.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Felipe Gutierrez
 *
 */
//@Component
@Aspect
public class AroundLogging {
	private static final Logger log = LoggerFactory.getLogger(AroundLogging.class);
	
	@Around("execution(* com.apress.isf.java.service.SearchEngine.*(..))")
	 public Object log(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		if(log.isDebugEnabled()){
			log.debug("@@@@(AROUND-BEFORE) Method called: " + pjp.getSignature());
			if(pjp.getArgs().length ==0 )
				log.debug("@@@@(AROUND_BEFORE) No arguments passed.");
			for(Object arg:pjp.getArgs())
				log.debug("@@@@(AROUND-BEFORE) Argument passed:" + arg);
		}
		
		try{
			if(log.isDebugEnabled())
				log.debug("@@@(AROUND) Processing...");
			
			result = pjp.proceed();
			
			if(log.isDebugEnabled())
				log.debug("@@@(AROUND-AFTER) Result: " + result);
			
			return result;
		}catch(IllegalArgumentException ex){
			log.error("@@@(AROUND) Throws an exception: " + ex.getMessage());
			throw ex;
		}	
	 }
}
