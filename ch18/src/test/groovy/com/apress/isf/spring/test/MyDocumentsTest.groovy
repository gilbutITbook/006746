package com.apress.isf.spring.test

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.Ignore
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.support.GenericGroovyApplicationContext

import com.apress.isf.java.model.Document;

class MyDocumentsTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsTest.class)
    private final RECORDS = 6
    private final ID = "1acbb68a-a859-49c9-ac88-d9e9322bac55"
    private final ctx = new GenericGroovyApplicationContext(
        "classpath:META-INF/groovy/mydocuments.groovy")

    @Test
    @Ignore
    void testJDBC() {
        log.info "Testing JDBC..."
        assertNotNull ctx
		
        def documentFacade = ctx.getBean("documentFacade")
        assertNotNull documentFacade
		
        int size = documentFacade.allDocuments.size()
        assertEquals RECORDS,size
		
        assertNotNull documentFacade.findDocumentById(ID)
    }

    @Test
    public void testProducer() {
        log.debug("Testing RabbitMQ Producer...");
        def documentFacade = ctx.getBean("documentFacade")
        def rabbitmqProducer = ctx.getBean("rabbitmqProducer")
		
        assertNotNull(rabbitmqProducer)
        assertNotNull(documentFacade)
		
        documentFacade.allDocuments.each {
            rabbitmqProducer.send it
        }
        sleep 5000
    }
}
