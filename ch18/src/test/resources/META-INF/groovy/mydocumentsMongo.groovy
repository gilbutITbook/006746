import org.springframework.data.mongodb.core.MongoTemplate as MT

beans {
    xmlns mongo:"http://www.springframework.org/schema/data/mongo"
	
    mongo.'mongo'(id:"mongo",host:"127.0.0.1",port:27017)
    mongo.'db-factory'(id:"mongoDbFactory",dbname:"mydocuments")
    mongoTemplate(MT,ref("mongoDbFactory"))
}