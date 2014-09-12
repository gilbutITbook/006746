/**
 * 
 */
package com.apress.isf.spring.scheduler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.DocumentService;

/**
 * @author Felipe Gutierrez
 *
 */
@Component
public class DocumentScheduler {
	private static Logger log = LoggerFactory.getLogger(DocumentScheduler.class);
	private static int HTTP_NOT_FOUND_CODE = 404;
	private static int HTTP_OK_CODE = 200;
	
	@Autowired
	DocumentService documentService;
	
	//@Scheduled(cron="*/3 * * * * ?")
	public void sampleCronMethod(){
		log.debug("Running every 3 seconds...");
	}
	
	private Type webType = new Type("WEB",".url");
	
	//@Scheduled(cron="*/10 * * * * ?")
	public void urlCheck() throws IOException{
		log.debug("@@ Checking valid WEB type Document's URL...");
		URL url = null;
		HttpURLConnection connection = null;
		int responseCode = 0;
		List<Document> documents = documentService.findByType(webType);
		for(Document document : documents){
			url = new URL(document.getLocation());
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
            connection.connect();
            responseCode = connection.getResponseCode();
            log.debug("Name lookup: " + document.getName());
            log.debug("Code: " + Integer.toString(responseCode));
            if(HTTP_OK_CODE ==  responseCode)
            		log.info("URL is still valid!!");
            else
            		log.error("URL INVALID! Code: " + HTTP_NOT_FOUND_CODE + ". Please let know the AdministratorL");
         }
	}
	
}
