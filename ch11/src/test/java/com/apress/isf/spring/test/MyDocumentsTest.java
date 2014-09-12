package com.apress.isf.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.java.utils.XmlUtils;
import com.apress.isf.spring.amqp.RabbitMQProducer;
import com.apress.isf.spring.jms.JMSProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyDocumentsTest {

	private static final Logger log = LoggerFactory.getLogger(MyDocumentsTest.class);
	//Based on the META-INF/data/jms.txt - only one record
	private static final int MAX_ALL_DOCS = 5;
	private static final int MAX_WEB_DOCS = 2;
	private static final String DOCUMENT_ID = "df569fa4-a513-4252-9810-818cade184ca";
	@Autowired
	private SearchEngine engine;

	@Test
	@Ignore
	public void testXmlUtils() {
		log.debug("Testing XML Utils...");
		Type type = new Type();
		type.setTypeId("4980d2e4-a424-4ff4-a0b2-476039682f43");
		type.setName("WEB");
		type.setDesc("Web Link");
		type.setExtension(".url");

		Document document = new Document();
		document.setDocumentId(UUID.randomUUID().toString());
		document.setName("Apress Books");
		document.setLocation("http://www.apress.com");
		document.setDescription("Apress Books");
		document.setType(type);
		document.setCreated(new Date());
		document.setModified(new Date());

		String string = XmlUtils.toXML(document);
		log.debug("\n" + string);

		Document other = XmlUtils.fromXML(string,Document.class);
		assertNotNull(other);
	}

	@Autowired
	JMSProducer jmsProducer;

	@Test
	public void testSpringJMS_1() {
		log.debug("Testing Spring JMS Producer...");
		jmsProducer.send();
	}

	@Test
	public void testSpringJMS_2() throws InterruptedException {
		log.debug("Testing Spring JMS Listener/Insert...");
		assertNotNull(engine);

		//Waiting a least 5 seconds so the message is consumed.
		Thread.sleep(5000);
		//After the JMS message and insert, must be 5 Documents
		assertEquals(MAX_ALL_DOCS, engine.listAll().size());

		Type documentType = new Type("WEB", ".url");
		assertEquals(MAX_WEB_DOCS, engine.findByType(documentType).size());
	}

	@Autowired
	RabbitMQProducer rabbitmqProducer;

	@Test
	public void testSpringRabbitMQ_1() {
		log.debug("Testing RabbitMQ producer...");
		assertNotNull(rabbitmqProducer);

		Document document = engine.findById(DOCUMENT_ID);
		assertNotNull(document);
		rabbitmqProducer.send(document);
	}

	@Test
	public void testSpringRabbitMQ_2() throws InterruptedException {
		log.debug("Testing RabbitMQ Consumer...");
		//Just wait for the RabbitMQ consumer...
		Thread.sleep(5000);
	}

}
