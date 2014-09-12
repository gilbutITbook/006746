
import com.apress.isf.spring.service.SearchEngineService
import com.apress.isf.spring.data.DocumentRepository
import com.apress.isf.spring.data.TypeDataRepository
import com.apress.isf.java.model.Document

beans {
	
	engine(SearchEngineService){ 
		documentDAO = ref("documentDAO")
	}	
	
	documentDAO(DocumentRepository){ 
		documents = [ref("doc1"), ref("doc2"), ref("doc3"), ref("doc4")]
	}
	
	typeDAO(TypeDataRepository){
		types = [pdfType:ref("pdfType"), webType:ref("webType"), noteType: ref("noteType") ]
	}
	
	doc1(Document){
		name = "Book Template"
		type = ref("pdfType")
		location = "/Users/felipeg/Documents/Random/Book Template.pdf"
	}
	
	doc2(Document){
		name = "Sample Contract"
		type = ref("pdfType")
		location = "/Users/felipeg/Documents/Contracts/Sample Contract.pdf"
	}
	
	doc3(Document){
		name = "Clustering with RabbitMQ"
		type = ref("noteType")
		location = "/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt"
	}
	
	doc4(Document){
		name = "Pro Spring Security Book"
		type = ref("webType")
		location = "http://www.apress.com/9781430248187"
	}
	
	webType(com.apress.isf.java.model.Type){
		name = "WEB"
		desc = "Web Link"
		extension = ".url"
	}
	
	pdfType(com.apress.isf.java.model.Type){
		name = "PDF"
		desc = "Portable Document Format"
		extension = ".url"
	}
	
	noteType(com.apress.isf.java.model.Type){
		name = "NOTE"
		desc = "Text Notes"
		extension = ".txt"
	}
}