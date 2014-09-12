/**
 * 
 */
package com.apress.isf.spring.jms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @author Felipe Gutierrez
 *
 */
@Component("jmsProducer")
public class JMSProducer {

	@Value("classpath:META-INF/data/jms.txt")
	private Resource jmstxt;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(){
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(getMessage());
			}
		});
	}
	
	private String getMessage(){
		StringBuilder str = new StringBuilder();
		try{
	          InputStream stream = jmstxt.getInputStream();
	          Scanner scanner = new Scanner(stream);
	      	  while (scanner.hasNext()) {
	      		  str.append(scanner.nextLine());
	      	  }
	      	  scanner.close();
	      	  stream.close();	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
		return str.toString();
	}
}
