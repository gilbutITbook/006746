/**
 * 
 */
package com.apress.isf.spring.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import static java.lang.System.out;

import org.springframework.core.io.Resource;

/**
 * @author Felipe Gutierrez
 *
 */
public class Menu {
	
	private Resource menuFile = null;
	
	public Resource getMenuFile() {
		return menuFile;
	}

	public void setMenuFile(Resource menuFile) {
		this.menuFile = menuFile;
	}
	
	public void printMenu(){
		try{
	          InputStream stream = getMenuFile().getInputStream();
	          Scanner scanner = new Scanner(stream);
	      	  while (scanner.hasNext()) {
	      		  out.println(scanner.nextLine());
	      	  }
	      	  scanner.close();
	      	  stream.close();	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	}
	
	
}
