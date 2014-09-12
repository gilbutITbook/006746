beans {
    xmlns context:"http://www.springframework.org/schema/context"
	
    context.'component-scan'('base-package': "com.apress.isf.spring")
	
    importBeans("classpath:META-INF/groovy/mydocumentsJDBC.groovy")
    importBeans("classpath:META-INF/groovy/mydocumentsRabbitMQ.groovy")
    importBeans("classpath:META-INF/groovy/mydocumentsMongo.groovy")	
}