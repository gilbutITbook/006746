/**
 * 
 */
package com.apress.isf.spring.annotated.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Felipe Gutierrez
 *
 */
//@Component
@Aspect
public class BeforeLogging {
	private static final Logger log = LoggerFactory.getLogger(BeforeLogging.class);
	
	//@Before("execution(* *(..))")
	@Before("execution(* com.apress.isf.java.service.SearchEngine.*(..))")
	public void log(JoinPoint joinPoint){
		if(log.isDebugEnabled()){
			log.debug("@@@@(BEFORE) Method called: " + joinPoint.getSignature());
			if(joinPoint.getArgs().length ==0 )
				log.debug("@@@@(BEFORE) No arguments passed.");
			for(Object arg:joinPoint.getArgs())
				log.debug("@@@@(BEFORE) Argument passed:" + arg);
		}
	}
}
