/**
 * 
 */
package com.apress.isf.spring.annotated.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.isf.java.model.Type;
import com.apress.isf.spring.aop.CachingModule;

/**
 * @author Felipe Gutierrez
 *
 */
//@Component
@Aspect
public class Caching {
	private static final Logger log = LoggerFactory.getLogger(CachingModule.class);
	private static final Map<String, Object> cache = new HashMap<String,Object>();
	
	@Around("execution(* com.apress.isf.java.service.SearchEngine.*(..))")
	public Object caching(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		Type documentType = null;
		
		log.debug("@@@(Caching) review if this call is cachable...");
		
		if("findByType".equals(pjp.getSignature().getName()) && 
				pjp.getArgs().length == 1 && pjp.getArgs()[0] instanceof Type){
			documentType = (Type)pjp.getArgs()[0];
			log.debug("@@@(Caching) Is cachable!!");
			if(cache.containsKey(documentType.getName())){
				log.debug("@@@(Caching) Found in Cache!");
				return cache.get(documentType.getName());
			}
			log.debug("@@@(Caching) Not Found! but is cachable!");
			result = pjp.proceed();
			cache.put(documentType.getName(), result);
			return result;
		}		
		
		return pjp.proceed();
	}
}
