/**
 * 
 */
package com.apress.isf.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.apress.isf.java.model.Document;

/**
 * @author Felipe Gutierrez
 *
 */
@Component("pdfConsumer")
public class PdfDocumentsConsumer implements RabbitMQConsumer{
	private static final Logger log = LoggerFactory.getLogger(PdfDocumentsConsumer.class);
	
	public void process(Document document) {
		log.debug("PDF Document received: " + document);
	}

}
