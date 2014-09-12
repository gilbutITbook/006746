/**
 * 
 */
package com.apress.isf.spring.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author Felipe Gutierrez
 *
 */
public class AfterLoggingModule implements AfterReturningAdvice {
	private static final Logger log = LoggerFactory.getLogger(AfterLoggingModule.class);
	
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		if(log.isDebugEnabled()){
			log.debug("@@@@(AFTER) Method called: " + method.getName());
			if(args.length ==0 )
				log.debug("@@@@(AFTER) No arguments passed.");
			for(Object arg:args)
				log.debug("@@@@(AFTER) Argument passed:" + arg);
			log.debug("@@@(AFTER) Result: " + returnValue);
		}
	}

}
