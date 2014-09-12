/**
 * 
 */
package com.apress.isf.spring.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

/**
 * @author Felipe Gutierrez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-oxm-context.xml")
public class MyDocumentsOXMTest {
	private static final Logger log = LoggerFactory.getLogger(MyDocumentsOXMTest.class);
	
	@Autowired
	XStreamMarshaller xstreamMarshaller;
	
	@Test
	public void testOXM() throws XmlMappingException, IOException{
		log.debug("Testing Spring OXM...");
		Document document = new Document("RabbitMQ","/tmp/docs/RabbitMQ.pdf","RabbitMQ Tools", new Type("PDF",".pdf"));
		StreamResult result = new StreamResult( new StringWriter() );
		xstreamMarshaller.marshal(document, result);
		String xml  = result.getWriter().toString();
		assertNotNull(xml);
		log.debug(xml);
		Document other = (Document)xstreamMarshaller.unmarshal(new StreamSource(new StringReader(xml)));
		assertNotNull(other);
		log.debug("Document: " + other);
	}
	
}
