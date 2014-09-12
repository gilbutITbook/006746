package com.apress.isf.spring.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.spring.service.SecurityServiceFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-context.xml")
public class MyDocumentsTest {

    private static final Logger log = LoggerFactory.getLogger(MyDocumentsTest.class);
    private static String EMAIL = "john@email.com";
    private static String PASSWORD = "doe";

    @Autowired
    SecurityServiceFacade security;

    @Test
    public void testSecurity() {
        log.debug("Testing Security...");
        assertNotNull(security);

        assertTrue(security.areCredentialsValid(EMAIL, PASSWORD));
    }

    @Test
    @Ignore
    public void testGroovy() {
        log.debug("Testing Groovy...");
        assertNotNull(security);

        assertTrue(security.areCredentialsValid(EMAIL, PASSWORD));
    }

    @Test
    @Ignore
    public void testJRuby() {
        log.debug("Testing JRuby...");
        assertNotNull(security);

        assertTrue(security.areCredentialsValid(EMAIL, PASSWORD));
    }

    @Test
    @Ignore
    public void testBeanShell() {
        log.debug("Testing BeanShell...");
        assertNotNull(security);

        assertTrue(security.areCredentialsValid(EMAIL, PASSWORD));
    }

}
