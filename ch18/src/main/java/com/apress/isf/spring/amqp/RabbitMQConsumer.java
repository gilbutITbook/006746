/**
 * 
 */
package com.apress.isf.spring.amqp;

import com.apress.isf.java.model.Document;

/**
 * @author Felipe Gutierrez
 *
 */

public interface RabbitMQConsumer{
	public void process(Document document);
}
