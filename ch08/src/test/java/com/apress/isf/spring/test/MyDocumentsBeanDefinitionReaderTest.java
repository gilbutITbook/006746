/**
 * 
 */
package com.apress.isf.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractGenericContextLoader;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.test.MyDocumentsBeanDefinitionReaderTest.GenericGroovyContextLoader;

/**
 * @author Felipe Gutierrez gradle
 *         -Dtest.single=MyDocumentsBeanDefinitionReaderTest test
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:META-INF/spring/mydocuments.groovy", loader = GenericGroovyContextLoader.class)
public class MyDocumentsBeanDefinitionReaderTest {

	public static class GenericGroovyContextLoader extends
			AbstractGenericContextLoader {

		@Override
		protected BeanDefinitionReader createBeanDefinitionReader(
				GenericApplicationContext context) {
			return new GroovyBeanDefinitionReader(context);
		}

		@Override
		protected String getResourceSuffix() {
			return ".groovy";
		}

	}

	@Autowired
	private ApplicationContext context;
	private SearchEngine engine;
	private Type webType;

	@Before
	public void setup() {
		context = new GenericGroovyApplicationContext(
				"META-INF/spring/mydocuments.groovy");
	}

	@Test
	public void testWithGroovyAll() {
		engine = context.getBean(SearchEngine.class);
		webType = context.getBean("webType", Type.class);

		List<Document> documents = engine.findByType(webType);
		assertNotNull(documents);
		assertTrue(documents.size() == 1);
		assertEquals(webType.getName(), documents.get(0).getType().getName());
		assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
		assertEquals(webType.getExtension(), documents.get(0).getType()
				.getExtension());

		engine = context.getBean(SearchEngine.class);

		documents = engine.listAll();
		assertNotNull(documents);
		assertTrue(documents.size() == 4);
	}

}
