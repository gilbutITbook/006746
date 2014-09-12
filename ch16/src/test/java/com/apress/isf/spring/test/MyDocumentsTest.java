package com.apress.isf.spring.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.service.DocumentService;
import com.apress.isf.spring.amqp.RabbitMQProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/mydocuments-context.xml",
"classpath:META-INF/spring/mydocuments-mongo-context.xml"})
public class MyDocumentsTest {

	private static final Logger log = LoggerFactory.getLogger(MyDocumentsTest.class);

	@Autowired
	RabbitMQProducer rabbitmqProducer;

	@Autowired
	DocumentService documentFacade;

	@Test
	public void testProducer() {
		log.debug("Testing RabbitMQ Producer...");
		assertNotNull(rabbitmqProducer);
		assertNotNull(documentFacade);
		for (Document document : documentFacade.getAllDocuments())
			rabbitmqProducer.send(document);
	}

	@Test
	public void testJustWait() throws InterruptedException {
		Thread.sleep(5000);
	}

}
