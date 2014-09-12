import groovy.sql.Sql
import org.springframework.core.io.Resource
import javax.annotation.Resource as R

@Repository
class MyDocumentsRepo {
    def sqlEngine = Sql.newInstance(
        "jdbc:hsqldb:mem:testdb", "sa", "", "org.hsqldb.jdbcDriver")

    @Autowired
    Resource schema

    @Autowired
    Resource data

    @R
    Map<String,String> sql

    @PostConstruct
    def init() {
        schema.inputStream.eachLine {
            sqlEngine.execute(it)
        }
        data.inputStream.eachLine {
            sqlEngine.execute(it)
        }
    }

    def getAllDocuments() {
        def result = []
        def type, document
        sqlEngine.rows(sql.query).each { row ->

            type = new Type(
                typeId:row.typeId,
                name:row.type_name,
                desc:row.type_desc,
                extension:row.extension)

            document = new Document(
                documentId:row.documentId,
                name:row.name,
                location:row.location,
                created:row.created,
                modified:row.modified,
                description:row.doc_desc,
                type:type)

            result << document

        }
        result
    }

}
