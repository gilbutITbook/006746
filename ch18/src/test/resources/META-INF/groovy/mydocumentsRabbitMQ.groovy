import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter as MLA

beans {
    xmlns rabbit:"http://www.springframework.org/schema/rabbit"
	
    importBeans("classpath:META-INF/groovy/mydocumentsOXM.groovy")
	
    rabbit.'connection-factory'(id:"rabbitConnectionFactory", host:"localhost")
    rabbit.admin('connection-factory':'rabbitConnectionFactory')
    rabbit.template(id:"rabbitTemplate",
        'connection-factory':"rabbitConnectionFactory",
        'message-converter':"messageConverter")
	
    rabbit.'direct-exchange'(name:"mydocuments") {
        rabbit.bindings {
            rabbit.binding(key:".pdf", queue:"docs-pdf")
            rabbit.binding(key:".txt", queue:"docs-txt")
            rabbit.binding(key:".url", queue:"docs-web")
        }
    }
	
    rabbit.'listener-container'(id:"pdfListener", 
            'connection-factory':"rabbitConnectionFactory"){
        rabbit.listener(ref:"pdfAdapter", queues:"docs-pdf")
        rabbit.listener(ref:"textAdapter", queues:"docs-txt")
        rabbit.listener(ref:"webAdapter", queues:"docs-web")
    }
	
    rabbit.queue(name:"docs-pdf")
    rabbit.queue(name:"docs-txt")
    rabbit.queue(name:"docs-web")
	
    pdfAdapter(MLA, ref("pdfConsumer"), ref("messageConverter")) {
        defaultListenerMethod = "process"
    }
    textAdapter(MLA, ref("textConsumer"), ref("messageConverter")) {
        defaultListenerMethod = "process"
    }
    webAdapter(MLA, ref("webConsumer"), ref("messageConverter")) {
        defaultListenerMethod = "process"
    }
}
