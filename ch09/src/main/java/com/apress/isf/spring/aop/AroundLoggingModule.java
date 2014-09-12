/**
 * 
 */
package com.apress.isf.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Felipe Gutierrez
 *
 */
public class AroundLoggingModule implements MethodInterceptor {
	private static final Logger log = LoggerFactory.getLogger(AroundLoggingModule.class);
			
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		if(log.isDebugEnabled()){
			log.debug("@@@@(AROUND-BEFORE) Method called: " + invocation.getMethod().getName());
			if(invocation.getArguments().length ==0 )
				log.debug("@@@@(AROUND-BEFORE) No arguments passed.");
			for(Object arg:invocation.getArguments())
				log.debug("@@@@(AROUND-BEFORE) Argument passed:" + arg);
		}
		
		try{
			if(log.isDebugEnabled())
				log.debug("@@@(AROUND) Processing...");
			
			result = invocation.proceed();
			
			if(log.isDebugEnabled())
				log.debug("@@@(AROUND-AFTER) Result: " + result);
			
			return result;
		}catch(IllegalArgumentException ex){
			log.error("@@@(AROUND) Throws an exception: " + ex.getMessage());
			throw ex;
		}	
	}

}
