@Log
@Configuration
@EnableRabbitMessaging
class MyDocumentsRabbitMQStream implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate

    private String queueName = "docs-pdf"
    private String exchangeName = "mydocuments"
    private String routingKey = ".pdf"

    @Bean
    Queue queue() {
        new Queue(queueName, false)
    }

    @Bean
    DirectExchange exchange() {
        new DirectExchange(exchangeName)
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        BindingBuilder
            .bind(queue)
            .to(exchange)
            .with(queueName)
    }

    @Bean
    SimpleMessageListenerContainer container(
            CachingConnectionFactory connectionFactory) {
        return new SimpleMessageListenerContainer(
            connectionFactory: connectionFactory,
            queueNames: [queueName],
            messageListener: new MessageListenerAdapter(
                new MyDocumentsConsumer(), "receive")
        )
    }

    void run(String... args) {
        log.info "Sending Documents..."
        500.times {
            rabbitTemplate.convertAndSend("mydocuments", ".pdf", 
            "Document(id: ${it}, created: ${new Date().format('yyyy-MM-dd HH:mm:ss') })"
                .toString())
            sleep 1000
        }
    }
}

@Log
class MyDocumentsConsumer {

    def receive(String message) {
        log.info "Document Received: ${message}"
    }
	
}
