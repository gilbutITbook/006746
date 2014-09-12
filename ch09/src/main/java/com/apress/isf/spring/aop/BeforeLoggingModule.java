/**
 * 
 */
package com.apress.isf.spring.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author Felipe Gutierrez
 *
 */
public class BeforeLoggingModule implements MethodBeforeAdvice {
	private static final Logger log = LoggerFactory.getLogger(BeforeLoggingModule.class);
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		if(log.isDebugEnabled()){
			log.debug("@@@@(BEFORE) Method called: " + method.getName());
			if(args.length ==0 )
				log.debug("@@@@(BEFORE) No arguments passed.");
			for(Object arg:args)
				log.debug("@@@@(BEFORE) Argument passed:" + arg);
		}
	}

}
