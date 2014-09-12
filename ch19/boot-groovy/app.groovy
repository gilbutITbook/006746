@Grab("org.hsqldb:hsqldb:2.3.2")
@Grab("org.codehaus.groovy:groovy-sql:2.3.6")

@Configuration
class MyDocuments {
    // Extra configuration 	
}

beans {
    importBeans("jdbc.xml")
}
